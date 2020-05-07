package com.codeM.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeM.app.model.Detalle;
import com.codeM.app.repository.DetallesRepository;

@Service
public class DetallesServiceJPA implements IDetallesService {
	
	@Autowired
	private DetallesRepository detalleRepo;

	@Override
	public void insertar(Detalle detalle) {
		detalleRepo.save(detalle);
		
	}

	@Override
	public void eliminar(int idDetalle) {
		detalleRepo.deleteById(idDetalle);
		
	}

}
