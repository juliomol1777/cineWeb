package com.codeM.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codeM.app.model.Banner;
import com.codeM.app.service.IBannerService;
import com.codeM.app.util.Utileria;

@Controller
@RequestMapping("/banners/")
public class BannersController {
	
	@Autowired
	private IBannerService bannersService;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Banner> lista= bannersService.buscarTodos();
		model.addAttribute("banners", lista);
		return "banners/listBanners";
		 
	}
	
	@GetMapping("/create")
	public String crear(@ModelAttribute Banner banner) {
		return "banners/formBanner";
		
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Banner banner, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {
		
		if (result.hasErrors()) {
			System.out.println("existieron errores");
			return "banners/formBanner";
		}
		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart, request);
			banner.setArchivo(nombreImagen);
		}
		bannersService.insertar(banner);
		attributes.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/banners/index";
	}
	
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable("id") int idBanner, Model modelo) {
		
		Banner banner= bannersService.buscarPorId(idBanner);
		modelo.addAttribute("banner", banner);
		return "banners/formBanner";
	}
	
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable("id") int idBanner, RedirectAttributes atributes) {
		bannersService.eliminar(idBanner);
		atributes.addFlashAttribute("mensaje", "La pelicula fue eliminada");
		return "redirect:/banners/index";
	}

}
