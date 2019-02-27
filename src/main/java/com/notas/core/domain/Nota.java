package com.notas.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="notas")
public class Nota implements Serializable{
	private static final long serialVersionUID = -2424534371308170021L;
	
	@Id @GeneratedValue @Column(name="id")
	private long id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="titulo")
	private String titulo;
	@Column(name="contenido")
	private String contenido;
	
	public long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getContenido() {
		return contenido;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contenido == null) ? 0 : contenido.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		if (contenido == null) {
			if (other.contenido != null)
				return false;
		} else if (!contenido.equals(other.contenido))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
}
