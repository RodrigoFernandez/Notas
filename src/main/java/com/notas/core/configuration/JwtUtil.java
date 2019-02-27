package com.notas.core.configuration;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class JwtUtil {
	
	private static final String ATTRIBUTE_NAME_AUTHORIZATION = "Authorization";
	private static final String CLAVE_SECRETA = "claveSecreta";

	//metodo para crear el JWT y enviarlo al cliente en el header de la respuesta
	public static void addAuthentication(HttpServletResponse response, String nombreUsuario) {
		response.addHeader(ATTRIBUTE_NAME_AUTHORIZATION, TokenBearer.getTokenForAddAuthentication(nombreUsuario, CLAVE_SECRETA));
	}
	
	//metodo para validar el token enviado por el cliente
	public static Authentication getAuthentication(HttpServletRequest request) {
		String usuario = TokenBearer.getUsuarioForGetAuthentication(request.getHeader(ATTRIBUTE_NAME_AUTHORIZATION), CLAVE_SECRETA);
		
		return usuario != null ?
				new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList())
				: null;
	}
}
