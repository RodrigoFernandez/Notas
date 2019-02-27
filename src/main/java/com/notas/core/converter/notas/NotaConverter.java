package com.notas.core.converter.notas;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.notas.core.converter.ConverterI;
import com.notas.core.converter.notas.builders.NotaBuilder;
import com.notas.core.converter.notas.builders.NotaDtoBuilder;
import com.notas.core.domain.Nota;
import com.notas.core.dto.nota.NotaDto;

public class NotaConverter implements ConverterI{

	public Nota convertTo(NotaDto notaDto) {
		if(notaDto == null) {
			return null;
		}
		
		return new NotaBuilder()
				.setId(notaDto.getId())
				.setNombre(notaDto.getNombre())
				.setTitulo(notaDto.getTitulo())
				.setContenido(notaDto.getContenido())
				.build();
	}
	
	public NotaDto convertTo(Nota nota) {
		if(nota == null) {
			return null;
		}
		
		return new NotaDtoBuilder()
				.setId(nota.getId())
				.setNombre(nota.getNombre())
				.setTitulo(nota.getTitulo())
				.setContenido(nota.getContenido())
				.build();
	}
	
	public List<Nota> convertToDomain(List<NotaDto> notasDto) {
		
		if(notasDto == null || notasDto.isEmpty()) {
			return Collections.emptyList();
		}
		
		return notasDto.stream().parallel().map(dto -> convertTo(dto)).collect(Collectors.toList());
	}
	
	public List<NotaDto> convertToDto(List<Nota> notas) {
		if(notas == null || notas.isEmpty()) {
			return Collections.emptyList();
		}
		
		return notas.stream().parallel().map(dto -> convertTo(dto)).collect(Collectors.toList());
	}
	
	public Nota getNotaForDelete(long id) {
		return new NotaBuilder()
				.setId(id)
				.build();
	}
	
	public String likeJson(NotaDto notaDto) {
		return new Gson().toJson(notaDto);
	}
	
	public String likeJson(Nota nota) {
		return new Gson().toJson(nota);
	}
}
