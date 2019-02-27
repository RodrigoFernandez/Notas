package com.notas.core.converter.usuarios.builders;

import com.notas.core.domain.Usuario;

public class UsuarioBuilder {
	
	private static final String NOOP_ENCODING = "{noop}";
	
	private long id;
	private String nombre;
	private String contrasenia;
	private byte rol;
	private boolean activo;
	
	public UsuarioBuilder setId(long id) {
		this.id = id;
		return this;
	}
	
	public UsuarioBuilder setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public UsuarioBuilder setContrasenia(String contrasenia) {
		/**
		 * mirar esto: https://www.adictosaltrabajo.com/2017/09/25/securizar-un-api-rest-utilizando-json-web-tokens/
		 * para el tema del encoding de las contrasenias mirar: https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-encoding
		 * (para este caso use {noop} para evitar el encoding)
		 * */
		this.contrasenia = (contrasenia != null && !contrasenia.contains(NOOP_ENCODING)) ? NOOP_ENCODING + contrasenia : contrasenia;
		return this;
	}
	
	public UsuarioBuilder setRol(byte rol) {
		this.rol = rol;
		return this;
	}
	
	public UsuarioBuilder setActivo(boolean activo) {
		this.activo = activo;
		return this;
	}
	
	public Usuario build() {
		Usuario rta = new Usuario();
		
		rta.setId(id);
		rta.setNombre(nombre);
		rta.setContrasenia(contrasenia);
		rta.setRol(rol);
		rta.setActivo(activo);
		
		return rta;
	}
}
