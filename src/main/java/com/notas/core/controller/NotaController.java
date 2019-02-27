package com.notas.core.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notas.core.controller.exceptions.NotaNoExisteException;
import com.notas.core.converter.exceptions.NoExisteConverter;
import com.notas.core.dto.nota.NotaDto;
import com.notas.core.service.NotaService;

@RestController("notaController")
@RequestMapping("/v1")
public class NotaController {
	
	@Autowired
	@Qualifier("notaService")
	private NotaService notaService;
	
	private void guardarNota(NotaDto notaDto) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		notaService.save(notaDto);
	}
	
	@PutMapping("/nota")
	public void nuevaNota(@RequestBody @Valid NotaDto notaDto) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		guardarNota(notaDto);
	}
	
	@PostMapping("/nota")
	public void actualizarNota(@RequestBody @Valid NotaDto notaDto) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		guardarNota(notaDto);
	}
	
	@PreAuthorize("hasRole('ADMINISTRADOR')")	
	@DeleteMapping("/nota/{id}")
	public void eliminarNota(@PathVariable("id") long id) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		notaService.delete(id);
	}
	
	@GetMapping("/nota/{id}")
	public NotaDto getNota(@PathVariable("id") long id) throws InstantiationException, IllegalAccessException, NoExisteConverter, NotaNoExisteException {
		NotaDto rta = notaService.findById(id);
		
		if(rta == null) {
			throw new NotaNoExisteException("No existe nota con id: " + id);
		}
		
		return rta;
	}
	
	@GetMapping("/notas")
	public List<NotaDto> getNotas(Pageable pageable) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		return notaService.getNotas(pageable);
	}
}
