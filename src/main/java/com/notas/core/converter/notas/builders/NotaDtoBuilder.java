package com.notas.core.converter.notas.builders;

import com.notas.core.dto.nota.NotaDto;

public class NotaDtoBuilder {
	
	private long id;
	private String nombre;
	private String titulo;
	private String contenido;
	
	public NotaDtoBuilder setId(long id) {
		this.id = id;
		return this;
	}
	public NotaDtoBuilder setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	public NotaDtoBuilder setTitulo(String titulo) {
		this.titulo = titulo;
		return this;
	}
	public NotaDtoBuilder setContenido(String contenido) {
		this.contenido = contenido;
		return this;
	}
	
	public NotaDto build() {
		NotaDto rta = new NotaDto();
		
		rta.setId(id);
		rta.setNombre(nombre);
		rta.setTitulo(titulo);
		rta.setContenido(contenido);
		
		return rta;
	}
}
