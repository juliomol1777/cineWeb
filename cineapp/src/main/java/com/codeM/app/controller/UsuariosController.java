package com.codeM.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeM.app.model.Perfil;
import com.codeM.app.model.Usuario;
import com.codeM.app.service.IPerfilesService;
import com.codeM.app.service.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private IUsuariosService serviceUsuario;
	
	@Autowired
	private IPerfilesService servicePerfil;
	
	@GetMapping("/create")
	public String crear(@ModelAttribute Usuario usuario) {
		return "usuarios/formUsuario";
	}
	
	@GetMapping("/index")
	public String index() {
		return "usuarios/listUsuarios";
		
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Usuario usuario, @RequestParam("perfil") String perfil) {
		String tmppas= usuario.getPwd();
		String encriptado= encoder.encode(tmppas);
		usuario.setPwd(encriptado);
		usuario.setActivo(1);
		serviceUsuario.guardar(usuario);
		Perfil perfiltmp= new Perfil();
		perfiltmp.setCuenta(usuario.getCuenta());
		perfiltmp.setPerfil(perfil);
		servicePerfil.guardar(perfiltmp);
		return "redirect:/usuarios/index";
	}
	
	@GetMapping(value="demo-bCrypt")
	public String pruebaBCrypt() {
		String password= "mari123";
		String encriptado= encoder.encode(password);
		System.out.println("pass encriptado "+ encriptado );
		return "usuarios/demo";
	}

}
