package com.notas.core.dto.usuario;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioLogin {

	@JsonProperty("nombre")
	@NotBlank
	private String nombre;
	@JsonProperty("contrasenia")
	@NotBlank
	private String contrasenia;
	
	public String getNombre() {
		return nombre;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	@Override
	public String toString() {
		return "<nombre: " + nombre + " | contrasenia: " + contrasenia + ">";
	}
}
