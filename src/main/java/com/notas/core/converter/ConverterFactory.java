package com.notas.core.converter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.notas.core.converter.exceptions.NoExisteConverter;
import com.notas.core.converter.notas.NotaConverter;
import com.notas.core.converter.usuarios.UsuarioConverter;
import com.notas.core.dto.nota.NotaDto;
import com.notas.core.dto.usuario.UsuarioDto;

@Component("converterFactory")
public class ConverterFactory {
	//http://migranitodejava.blogspot.com/2011/05/factory-method.html
	//https://www.arquitecturajava.com/usando-el-patron-factory/
	
	//devuelve el nota converter correspondiente
	
	private Map<String, Class<? extends ConverterI>> disponibles;
	
	public ConverterFactory() {
		this.disponibles = new HashMap<String, Class<? extends ConverterI>>();
		
		this.disponibles.put(NotaDto.class.getSimpleName(), NotaConverter.class);
		this.disponibles.put(UsuarioDto.class.getSimpleName(), UsuarioConverter.class);
	}
	
	public ConverterI getConverter(String clave) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		Class<? extends ConverterI> rta = this.disponibles.get(clave);
		
		if(rta == null) {
			throw new NoExisteConverter(clave);
		}
		
		return rta.newInstance();
	}
}
