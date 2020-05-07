package com.codeM.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeM.app.model.Banner;
import com.codeM.app.repository.BannersRepository;

@Service
public class BannerServiceJPA implements IBannerService {
	
	@Autowired 
	private BannersRepository bannerRepo;

	@Override
	public void insertar(Banner banner) {
		bannerRepo.save(banner);
		
	}

	@Override
	public List<Banner> buscarTodos() {
		List<Banner> listaBanner= bannerRepo.findAll();
		return listaBanner;
	}

	@Override
	public Banner buscarPorId(int idBanner) {
		Optional<Banner> lista= bannerRepo.findById(idBanner);
		if(lista.isPresent()) {
			return lista.get();
		}
		return null;
	}

	@Override
	public void eliminar(int idBanner) {
		
		bannerRepo.deleteById(idBanner);
	}
	
	@Override
	public List<Banner> buscarActivos() {		
		return bannerRepo.findByEstatusOrderByIdDesc("Activo");
	}


}
