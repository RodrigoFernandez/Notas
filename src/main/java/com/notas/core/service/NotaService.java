package com.notas.core.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.notas.core.converter.ConverterFactory;
import com.notas.core.converter.exceptions.NoExisteConverter;
import com.notas.core.converter.notas.NotaConverter;
import com.notas.core.dao.NotaDao;
import com.notas.core.domain.Nota;
import com.notas.core.dto.nota.NotaDto;

@Service("notaService")
public class NotaService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NotaService.class);
	
	@Autowired
	@Qualifier("notaDao")
	private NotaDao notaDao;
	
	@Autowired
	@Qualifier("converterFactory")
	private ConverterFactory converterFactory;
	
	public NotaDto save(NotaDto notaDto) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		NotaConverter nc = (NotaConverter)converterFactory.getConverter(NotaDto.class.getSimpleName());
		
		LOGGER.debug("Nota a salvar: " + nc.likeJson(notaDto));
		
		Nota notaAux = nc.convertTo(notaDto);
		
		notaAux = notaDao.save(notaAux);
		
		return nc.convertTo(notaAux);
	}
	
	public void delete(long notaId) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		NotaConverter nc = (NotaConverter)converterFactory.getConverter(NotaDto.class.getSimpleName());
		
		notaDao.delete(nc.getNotaForDelete(notaId));
	}
	
	public void delete(NotaDto notaDto) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		NotaConverter nc = (NotaConverter)converterFactory.getConverter(NotaDto.class.getSimpleName());
		
		LOGGER.debug("Nota a eliminar: " + nc.likeJson(notaDto));
		
		notaDao.delete(nc.convertTo(notaDto));
	}
	
	public List<NotaDto> getNotas() throws InstantiationException, IllegalAccessException, NoExisteConverter{
		NotaConverter nc = (NotaConverter)converterFactory.getConverter(NotaDto.class.getSimpleName());
		
		List<Nota> aux = notaDao.findAll();
		
		return nc.convertToDto(aux);
	}
	
	public List<NotaDto> getNotas(Pageable pageable) throws InstantiationException, IllegalAccessException, NoExisteConverter{
		NotaConverter nc = (NotaConverter)converterFactory.getConverter(NotaDto.class.getSimpleName());
		
		List<Nota> aux = notaDao.findAll(pageable).getContent();
		
		return nc.convertToDto(aux);
	}
	
	public NotaDto findByNombreAndTitulo(String nombre, String titulo) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		NotaConverter nc = (NotaConverter)converterFactory.getConverter(NotaDto.class.getSimpleName());
		
		return nc.convertTo(notaDao.findByNombreAndTitulo(nombre, titulo));
	}
	
	public NotaDto findById(long id) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		NotaConverter nc = (NotaConverter)converterFactory.getConverter(NotaDto.class.getSimpleName());
		
		return nc.convertTo(notaDao.findById(id));
	}
}
