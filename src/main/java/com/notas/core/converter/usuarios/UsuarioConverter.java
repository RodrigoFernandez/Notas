package com.notas.core.converter.usuarios;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.notas.core.converter.ConverterI;
import com.notas.core.converter.usuarios.builders.UsuarioBuilder;
import com.notas.core.converter.usuarios.builders.UsuarioDtoBuilder;
import com.notas.core.domain.Usuario;
import com.notas.core.dto.usuario.UsuarioDto;

public class UsuarioConverter implements ConverterI {

	public Usuario convertTo(UsuarioDto usuarioDto) {
		if(usuarioDto == null) {
			return null;
		}
		
		return new UsuarioBuilder()
				.setId(usuarioDto.getId())
				.setNombre(usuarioDto.getNombre())
				.setContrasenia(usuarioDto.getContrasenia())
				.setRol(usuarioDto.getRol())
				.setActivo(usuarioDto.isActivo())
				.build();
	}
	
	public UsuarioDto convertTo(Usuario usuario) {
		if(usuario == null) {
			return null;
		}
		
		return new UsuarioDtoBuilder()
				.setId(usuario.getId())
				.setNombre(usuario.getNombre())
				.setContrasenia(usuario.getContrasenia())
				.setRol(usuario.getRol())
				.setActivo(usuario.isActivo())
				.build();
	}
	
	public List<Usuario> convertToDomain(List<UsuarioDto> usuariosDto) {
		
		if(usuariosDto == null || usuariosDto.isEmpty()) {
			return Collections.emptyList();
		}
		
		return usuariosDto.stream().parallel().map(dto -> convertTo(dto)).collect(Collectors.toList());
	}
	
	public List<UsuarioDto> convertToDto(List<Usuario> usuarios) {
		if(usuarios == null || usuarios.isEmpty()) {
			return Collections.emptyList();
		}
		
		return usuarios.stream().parallel().map(dto -> convertTo(dto)).collect(Collectors.toList());
	}
	
	public Usuario getUsuarioForDelete(long id) {
		return new UsuarioBuilder()
				.setId(id)
				.build();
	}
	
	public String likeJson(UsuarioDto usuarioDto) {
		return new Gson().toJson(usuarioDto);
	}
	
	public String likeJson(Usuario usuario) {
		return new Gson().toJson(usuario);
	}
}
