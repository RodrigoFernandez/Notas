package com.notas.core.dto.usuario;

public class UsuarioDto {
	
	private long id;
	private String nombre;
	private String contrasenia;
	private byte rol;
	private boolean activo;
	
	public long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public byte getRol() {
		return rol;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public void setRol(byte rol) {
		this.rol = rol;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (activo ? 1231 : 1237);
		result = prime * result + ((contrasenia == null) ? 0 : contrasenia.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + rol;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UsuarioDto other = (UsuarioDto) obj;
		if (activo != other.activo) {
			return false;
		}
		if (contrasenia == null) {
			if (other.contrasenia != null) {
				return false;
			}
		} else if (!contrasenia.equals(other.contrasenia)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (rol != other.rol) {
			return false;
		}
		return true;
	}
}
