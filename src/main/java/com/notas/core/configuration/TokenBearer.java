package com.notas.core.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenBearer {
	
	public static String getTokenForAddAuthentication(String nombreUsuario, String claveSecreta) {
		String token = Jwts.builder()
				.setSubject(nombreUsuario)
				.signWith(SignatureAlgorithm.HS512, claveSecreta) //hash con el que firmaremos la clave
				.compact();
		
		//https://www.adictosaltrabajo.com/2017/09/25/securizar-un-api-rest-utilizando-json-web-tokens/ indica que:
		//en el estándar utilizado (RFC 2616, RFC 6750) lo habitual es devolver en la cabecera HTTP la clave 'Authorization' con el valor: “Bearer “ + token
		return "Bearer " + token;
	}
	
	public static String getUsuarioForGetAuthentication(String receivedToken, String claveSecreta) {
		if(receivedToken == null) {
			return null;
		}
		
		return Jwts.parser()
				.setSigningKey(claveSecreta)
				.parseClaimsJws(receivedToken.replace("Bearer", "")) //este es el metodo que valida
				.getBody()
				.getSubject();
	}
}
