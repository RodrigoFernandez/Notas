package com.notas.core.converter.usuarios.builders;

import com.notas.core.dto.usuario.UsuarioDto;

public class UsuarioDtoBuilder {
	
	private static final String NOOP_ENCODING = "{noop}";
	
	private long id;
	private String nombre;
	private String contrasenia;
	private byte rol;
	private boolean activo;
	
	public UsuarioDtoBuilder setId(long id) {
		this.id = id;
		return this;
	}
	
	public UsuarioDtoBuilder setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public UsuarioDtoBuilder setContrasenia(String contrasenia) {
		/**
		 * mirar esto: https://www.adictosaltrabajo.com/2017/09/25/securizar-un-api-rest-utilizando-json-web-tokens/
		 * para el tema del encoding de las contrasenias mirar: https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-encoding
		 * (para este caso use {noop} para evitar el encoding)
		 * */
		this.contrasenia = (contrasenia != null) ? contrasenia.replace(NOOP_ENCODING, "") : null;
		return this;
	}
	
	public UsuarioDtoBuilder setRol(byte rol) {
		this.rol = rol;
		return this;
	}
	
	public UsuarioDtoBuilder setActivo(boolean activo) {
		this.activo = activo;
		return this;
	}
	
	public UsuarioDto build() {
		UsuarioDto rta = new UsuarioDto();
		
		rta.setId(id);
		rta.setNombre(nombre);
		rta.setContrasenia(contrasenia);
		rta.setRol(rol);
		rta.setActivo(activo);
		
		return rta;
	}
}
