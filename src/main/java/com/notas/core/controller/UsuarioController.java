package com.notas.core.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notas.core.controller.exceptions.UsuarioNoExisteException;
import com.notas.core.converter.exceptions.NoExisteConverter;
import com.notas.core.dto.usuario.UsuarioDto;
import com.notas.core.service.UsuarioService;

@RestController("usuarioController")
@RequestMapping("/v1")
public class UsuarioController {
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
	private void guardarUsuario(UsuarioDto usuarioDto) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		usuarioService.save(usuarioDto);
	}
	
	@PutMapping("/usuario")
	public void nuevoUsuario(@RequestBody @Valid UsuarioDto usuarioDto) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		guardarUsuario(usuarioDto);
	}
	
	@PostMapping("/usuario")
	public void actualizarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		guardarUsuario(usuarioDto);
	}
	
	@PreAuthorize("hasRole('ADMINISTRADOR')")	
	@DeleteMapping("/usuario/{id}")
	public void eliminarUsuario(@PathVariable("id") long id) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		usuarioService.delete(id);
	}
	
	@GetMapping("/usuario/{id}")
	public UsuarioDto getUsuario(@PathVariable("id") long id) throws InstantiationException, IllegalAccessException, NoExisteConverter, UsuarioNoExisteException {
		UsuarioDto rta = usuarioService.findById(id);
		
		if(rta == null) {
			throw new UsuarioNoExisteException("No existe usuario con id: " + id);
		}
		
		return rta;
	}
	
	@GetMapping("/usuarios")
	public List<UsuarioDto> getUsuarios() throws InstantiationException, IllegalAccessException, NoExisteConverter {
		return usuarioService.getUsuarios();
	}
}
