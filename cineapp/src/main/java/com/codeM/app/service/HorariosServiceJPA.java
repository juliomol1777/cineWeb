package com.codeM.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codeM.app.model.Horario;
import com.codeM.app.repository.HorariosRepository;

@Service
public class HorariosServiceJPA implements IHorariosService{
	
	@Autowired
	private HorariosRepository horarioRepo;

	@Override
	public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha) {
		
		return horarioRepo.findByPelicula_IdAndFechaOrderByHora(idPelicula, fecha);
	
	}

	@Override
	public List<Horario> buscarTodos() {
		
		return horarioRepo.findAll();
	}

	@Override
	public Horario buscarPorId(int idHorario) {
		Optional<Horario> optional= horarioRepo.findById(idHorario);
		if(optional.isPresent()) {
			optional.get();
		}
		return null;
	}

	@Override
	public void insertar(Horario horario) {
		horarioRepo.save(horario);
		
	}

	@Override
	public void eliminar(int idHorario) {
		horarioRepo.deleteById(idHorario);
		
	}

	@Override
	public Page<Horario> buscarTodas(Pageable page) {
		
		return horarioRepo.findAll(page);
	}

}
