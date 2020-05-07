package com.codeM.app.service;

import java.util.List;

import com.codeM.app.model.Banner;

public interface IBannerService {
	
	void insertar(Banner banner);
	List<Banner> buscarTodos();
	Banner buscarPorId(int idBanner);
	void eliminar(int idBanner);
	List<Banner> buscarActivos();
}
