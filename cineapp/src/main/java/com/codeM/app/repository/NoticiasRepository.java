package com.codeM.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeM.app.model.Noticia;

@Repository
public interface NoticiasRepository extends JpaRepository<Noticia, Integer> {
	//declaro metodos en la interfaz usando query metodos y se implementan
	List<Noticia> findBy();
	List<Noticia> findByEstatus(String estatus);
	List<Noticia> findByFecha(Date fecha);
	//buscar con dos condiciones and y or
	List<Noticia> findByEstatusAndFecha(String estatus, Date fecha);
	List<Noticia> findByEstatusOrFecha(String estatus, Date fecha);
	//buscar entre 2 valores del mismo campo
	List<Noticia> findByFechaBetween(Date fecha1, Date fecha2);
	// select * from Noticias where estatus = ? order by id desc limit 3
	public List<Noticia> findTop3ByEstatusOrderByIdDesc(String estatus);

}
