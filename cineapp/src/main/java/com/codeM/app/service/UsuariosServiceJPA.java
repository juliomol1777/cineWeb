package com.codeM.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeM.app.model.Usuario;
import com.codeM.app.repository.UsuariosRepository;

@Service
public class UsuariosServiceJPA implements IUsuariosService {
	
	@Autowired
	private UsuariosRepository usuariosRepo;

	@Override
	public void guardar(Usuario usuario) {
		usuariosRepo.save(usuario);
		
	}

}
