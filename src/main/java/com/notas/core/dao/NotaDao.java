package com.notas.core.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.notas.core.domain.Nota;

@Repository ("notaDao")
public interface NotaDao extends JpaRepository<Nota, Serializable>,
								PagingAndSortingRepository<Nota, Serializable> {

	public Nota findById(long id);
	
	public Nota findByNombre(String nombre);
	
	public List<Nota> findByTitulo(String titulo);
	public Page<Nota> findByTitulo(String titulo, Pageable pageable);
	
	public Nota findByNombreAndTitulo(String nombre, String titulo);
	
	public Page<Nota> findAll(Pageable pageable);
}
