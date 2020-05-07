package com.codeM.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codeM.app.model.Pelicula;
import com.codeM.app.service.IDetallesService;
import com.codeM.app.service.IPeliculasService;
import com.codeM.app.util.Utileria;


@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	@Autowired
	private IDetallesService serviceDetalles;

	@Autowired
	private IPeliculasService servicePelicula;
	
	@GetMapping("/create")
	public String crear(@ModelAttribute Pelicula pelicula, Model modelo) {
		
		return "peliculas/formPelicula";
	}

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Pelicula> lista = servicePelicula.buscarTodas();
		model.addAttribute("peliculas", lista);
		return "peliculas/listPeliculas";
	}
	
	@GetMapping(value="/indexPaginate")
	public String mostrarIndexPaginado(Model modelo, Pageable page) {
		Page<Pelicula> lista= servicePelicula.buscarTodas(page);
		modelo.addAttribute("peliculas", lista);
		return "peliculas/listPeliculas";
	}
	
	
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {

		if (result.hasErrors()) {
			System.out.println("existieron errores");
			return "peliculas/formPelicula";
		}
		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart, request);
			pelicula.setImagen(nombreImagen);
		}

		serviceDetalles.insertar(pelicula.getDetalle());
		servicePelicula.insertar(pelicula);
		
		attributes.addFlashAttribute("mensaje", "El registro fue guardado");
		return "redirect:/peliculas/indexPaginate";
	}
	
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable("id") int idPelicula, Model modelo) {
		
		Pelicula pelicula= servicePelicula.buscarPorId(idPelicula);
		modelo.addAttribute("pelicula", pelicula);
		return "peliculas/formPelicula";
	}
	
	//con modelatribute generos esta disponible para cada metodo, no hace falta que ponga
	//modelo.addAttribute("generos", servicePelicula.buscarGeneros()); en cada uno
	@ModelAttribute("generos")
	public List<String> getGeneros(){
		return servicePelicula.buscarGeneros();
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idPelicula, RedirectAttributes atributes) {
		Pelicula pelicula= servicePelicula.buscarPorId(idPelicula);
		
		servicePelicula.eliminar(idPelicula);
		serviceDetalles.eliminar(pelicula.getDetalle().getId());
		atributes.addFlashAttribute("mensaje", "La pelicula fue eliminada");
		return "redirect:/peliculas/indexPaginate";
	}


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
