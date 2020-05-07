package com.codeM.app.service;

import java.util.List;

import com.codeM.app.model.Noticia;

public interface INoticiasService {
	
	void guardar(Noticia noticia);
	List<Noticia> buscarTodas();
	Noticia buscarNoticiaPorId(int idNoticia);
	void eliminar(int idNoticia);
	List<Noticia> buscarUltimas();
}
