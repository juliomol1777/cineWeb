package com.codeM.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.codeM.app.model.Horario;

public interface IHorariosService {
	
	public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha);
	List<Horario> buscarTodos();
	Horario buscarPorId(int idHorario);
	void insertar(Horario horario);
	void eliminar(int idHorario);
	public Page<Horario> buscarTodas(Pageable page);

}
