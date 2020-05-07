package com.codeM.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codeM.app.model.Noticia;
import com.codeM.app.service.INoticiasService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {
	
	@Autowired
	private INoticiasService serviceNoticias;
	
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Noticia> lista= serviceNoticias.buscarTodas();
		model.addAttribute("noticias", lista);
		return "noticias/listNoticias";
		 
	}
	
	@GetMapping("/create")
	public String crear(@ModelAttribute Noticia noticia) {
		
		return "noticias/formNoticia";
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Noticia noticia, RedirectAttributes attributes) {
	
		serviceNoticias.guardar(noticia);
		attributes.addFlashAttribute("mensaje", "El registro fue guardado");
		return "noticias/index";
		
	}
	
	@GetMapping(value= "/edit/{id}")
	public String editar(@PathVariable("id") int idNoticia, Model modelo) {
		
		Noticia noticiaID=serviceNoticias.buscarNoticiaPorId(idNoticia);
		System.out.println(noticiaID.getTitulo());
		modelo.addAttribute("noticia", noticiaID);
		
		return "noticias/formNoticia";
	}
	
	@GetMapping(value= "/delete/{id}")
	public String eliminar(@PathVariable("id") int idNoticia, RedirectAttributes atributes) {
		
		serviceNoticias.eliminar(idNoticia);
		atributes.addFlashAttribute("mensaje", "La noticia fue eliminada");
		return "redirect:/noticias/index";
	}
}
