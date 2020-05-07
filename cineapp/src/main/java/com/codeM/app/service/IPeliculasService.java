package com.codeM.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.codeM.app.model.Pelicula;

public interface IPeliculasService {
	void insertar(Pelicula pelicula);
	List<Pelicula> buscarTodas();
	Pelicula buscarPorId(int idPelicula);
	List<String> buscarGeneros();
	void eliminar (int idPelicula);
	public Page<Pelicula> buscarTodas(Pageable page);
	List<Pelicula> buscarActivas();
	List<Pelicula> buscarPorFecha(Date fecha);

}
