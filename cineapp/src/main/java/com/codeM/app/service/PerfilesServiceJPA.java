package com.codeM.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeM.app.model.Perfil;
import com.codeM.app.repository.PerfilesRepository;

@Service
public class PerfilesServiceJPA implements IPerfilesService {
	
	@Autowired
	private PerfilesRepository perfileRepo;

	@Override
	public void guardar(Perfil perfil) {
		
		perfileRepo.save(perfil);
	}

}
