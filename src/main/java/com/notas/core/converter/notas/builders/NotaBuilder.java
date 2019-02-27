package com.notas.core.converter.notas.builders;

import com.notas.core.domain.Nota;

public class NotaBuilder {
	
	private long id;
	private String nombre;
	private String titulo;
	private String contenido;
	
	public NotaBuilder setId(long id) {
		this.id = id;
		return this;
	}
	public NotaBuilder setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	public NotaBuilder setTitulo(String titulo) {
		this.titulo = titulo;
		return this;
	}
	public NotaBuilder setContenido(String contenido) {
		this.contenido = contenido;
		return this;
	}
	
	public Nota build() {
		Nota rta = new Nota();
		
		rta.setId(id);
		rta.setNombre(nombre);
		rta.setTitulo(titulo);
		rta.setContenido(contenido);
		
		return rta;
	}
}
