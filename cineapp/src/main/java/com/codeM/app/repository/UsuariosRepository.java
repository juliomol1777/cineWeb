package com.codeM.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeM.app.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer>{

}
