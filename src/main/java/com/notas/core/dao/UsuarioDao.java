package com.notas.core.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.notas.core.domain.Usuario;

@Repository ("usuarioDao")
public interface UsuarioDao extends JpaRepository<Usuario, Serializable>,
									PagingAndSortingRepository<Usuario, Serializable> {
	
	public Usuario findById(long id);
	
	public Usuario findByNombre(String nombre);
}
