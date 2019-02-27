package com.notas.core.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.notas.core.service.UsuarioService;

/**
 * mirar esto: https://www.adictosaltrabajo.com/2017/09/25/securizar-un-api-rest-utilizando-json-web-tokens/
 * para el tema del encoding de las contrasenias mirar: https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-encoding
 * (para este caso use {noop} para evitar el encoding)
 * */
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private static final String URL_LOGIN = "/login";
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(URL_LOGIN).permitAll() //permite el acceso al login para todos
		.anyRequest().authenticated() //cualquier peticion que no sea login requiere autenticacion
		.and()
		.addFilterBefore(new LoginFilter(URL_LOGIN, authenticationManager()),
							UsernamePasswordAuthenticationFilter.class) //las peticiones de login pasan antes por este filtro, en UsernamePasswordAuthenticationFilter se define que "/login" usa "POST"
		.addFilterBefore(new JwtFilter(),
							UsernamePasswordAuthenticationFilter.class); //el resto de las peticiones pasan antes por este filtro para validar el token
	}
}
