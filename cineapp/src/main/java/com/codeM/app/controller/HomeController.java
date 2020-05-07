package com.codeM.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeM.app.model.Banner;
import com.codeM.app.model.Horario;
import com.codeM.app.model.Noticia;
import com.codeM.app.model.Pelicula;
import com.codeM.app.service.IBannerService;
import com.codeM.app.service.IHorariosService;
import com.codeM.app.service.INoticiasService;
import com.codeM.app.service.IPeliculasService;
import com.codeM.app.util.Utileria;

@Controller
public class HomeController {
	
	@Autowired
	private IPeliculasService servicePeliculas;
	@Autowired
	private IBannerService serviceBanner;
	@Autowired
	private IHorariosService serviceHorario;
	@Autowired
	private INoticiasService serviceNoticia;
	
	private SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome() {
		return "home";
	}
	
	@RequestMapping(value="/acerca", method=RequestMethod.GET)
	public String goAcerca() {
		return "acerca";
	}
	
	@RequestMapping(value="/formLogin", method=RequestMethod.GET)
	public String mostrarLogin() {
		return "formLogin";
	}
	
	/**
	 * Metodo para mostrar la pagina principal de la aplicacion
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String mostrarPrincipal(Model model) {	

		try {
			Date fechaSinHora = dateFormat.parse(dateFormat.format(new Date()));
			List<String> listaFechas = Utileria.getNextDays(4);		
			List<Pelicula> peliculas = servicePeliculas.buscarPorFecha(fechaSinHora);			
			model.addAttribute("fechas", listaFechas);
			model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
			model.addAttribute("peliculas", peliculas);
		} catch (ParseException e) {
			System.out.println("Error: HomeController.mostrarPrincipal" + e.getMessage());
		}
		return "home";

	}
	/**
	 * Metodo para filtrar las peliculas por fecha
	 * @param fecha
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/search", method=RequestMethod.GET)
	public String buscar(@RequestParam("fecha") Date fecha, Model model) {		
		try {			
			Date fechaSinHora = dateFormat.parse(dateFormat.format(fecha));
			List<String> listaFechas = Utileria.getNextDays(4);
			List<Pelicula> peliculas  = servicePeliculas.buscarPorFecha(fechaSinHora);
			model.addAttribute("fechas", listaFechas);			
			// Regresamos la fecha que selecciono el usuario con el mismo formato
			model.addAttribute("fechaBusqueda",dateFormat.format(fecha));			
			model.addAttribute("peliculas", peliculas);			
			return "home";
		} catch (ParseException e) {
			System.out.println("Error: HomeController.buscar" + e.getMessage());
		}
		return "home";
	}
	
	@RequestMapping(value="/detalle/{id}/{fecha}", method=RequestMethod.GET )
	public String mostrarDetalle(Model model, @PathVariable("id") int idPelicula, @PathVariable("fecha") Date fecha) {	
		
		List<Horario> horarios= serviceHorario.buscarPorIdPelicula(idPelicula, fecha);
		model.addAttribute("horarios", horarios);
		model.addAttribute("fechaBusqueda", dateFormat.format(fecha));
		model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));
		return "detalle";
	}
	
	@ModelAttribute("noticias")
	public List<Noticia> getNoticias(){
		return serviceNoticia.buscarUltimas();
	}
	
	@ModelAttribute("banners")
	public List<Banner> getBanners(){
		return serviceBanner.buscarActivos();
	}
	
	/**
	 * Metodo para personalizar el Data Binding para los atributos de tipo Date
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {				
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	
}
