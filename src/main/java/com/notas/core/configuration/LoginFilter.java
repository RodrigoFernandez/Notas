package com.notas.core.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notas.core.dto.usuario.UsuarioLogin;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);
	
	public LoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		InputStream body = request.getInputStream();
		
		UsuarioLogin usuario = new ObjectMapper().readValue(body, UsuarioLogin.class);
		LOGGER.debug(usuario.toString());
		
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(usuario.getNombre(),
											 			usuario.getContrasenia(),
														Collections.emptyList())
				);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		JwtUtil.addAuthentication(response, authResult.getName()); //si la autenticacion fue exitosa, agregamos el token a la respuesta
	}
}
