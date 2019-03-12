package com.notas.core.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.notas.core.constantes.Roles;
import com.notas.core.converter.ConverterFactory;
import com.notas.core.converter.exceptions.NoExisteConverter;
import com.notas.core.converter.usuarios.UsuarioConverter;
import com.notas.core.dao.UsuarioDao;
import com.notas.core.domain.Usuario;
import com.notas.core.dto.usuario.UsuarioDto;

@Service("usuarioService")
public class UsuarioService implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;
	
	@Autowired
	@Qualifier("converterFactory")
	private ConverterFactory converterFactory;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByNombre(username);
		
		
		UsuarioConverter uc;
		try {
			uc = (UsuarioConverter)converterFactory.getConverter(UsuarioDto.class.getSimpleName());
			LOGGER.debug("Se busco >>" + username + "<< y se encontro:" + uc.likeJson(usuario));
		} catch (InstantiationException e) {
			throw new UsernameNotFoundException("Error de prueba", e);
		} catch (IllegalAccessException e) {
			throw new UsernameNotFoundException("Error de prueba", e);
		} catch (NoExisteConverter e) {
			throw new UsernameNotFoundException("Error de prueba", e);
		}
		
		return new User(usuario.getNombre(), usuario.getContrasenia(),
						usuario.isActivo(), usuario.isActivo(), usuario.isActivo(), usuario.isActivo(),
						buildgrante(usuario.getRol()));
	}
	
	public List<GrantedAuthority> buildgrante(byte rol){
		return IntStream.range(0, rol).mapToObj(indice -> new SimpleGrantedAuthority(Roles.values()[indice].toString())).collect(Collectors.toList());
	}
	
	public UsuarioDto save(UsuarioDto usuarioDto) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		UsuarioConverter uc = (UsuarioConverter)converterFactory.getConverter(UsuarioDto.class.getSimpleName());
		
		LOGGER.debug("Usuario a salvar: " + uc.likeJson(usuarioDto));
		
		Usuario usuario = uc.convertTo(usuarioDto);
		usuario = usuarioDao.save(usuario);
		
		return uc.convertTo(usuario);
	}
	
	public void delete(long usuarioId) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		UsuarioConverter nc = (UsuarioConverter)converterFactory.getConverter(UsuarioDto.class.getSimpleName());
		
		usuarioDao.delete(nc.getUsuarioForDelete(usuarioId));
	}
	
	public void delete(UsuarioDto usuarioDto) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		UsuarioConverter nc = (UsuarioConverter)converterFactory.getConverter(UsuarioDto.class.getSimpleName());
		
		LOGGER.debug("Usuario a eliminar: " + nc.likeJson(usuarioDto));
		
		usuarioDao.delete(nc.convertTo(usuarioDto));
	}
	
	public List<UsuarioDto> getUsuarios() throws InstantiationException, IllegalAccessException, NoExisteConverter{
		UsuarioConverter uc = (UsuarioConverter)converterFactory.getConverter(UsuarioDto.class.getSimpleName());
		return uc.convertToDto(usuarioDao.findAll());
	}
	
	public UsuarioDto findById(long id) throws InstantiationException, IllegalAccessException, NoExisteConverter {
		UsuarioConverter uc = (UsuarioConverter)converterFactory.getConverter(UsuarioDto.class.getSimpleName());
		
		return uc.convertTo(usuarioDao.findById(id));
	}
}
