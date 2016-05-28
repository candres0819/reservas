package com.carloscardona.tns.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carloscardona.tns.dao.UsuarioRepository;
import com.carloscardona.tns.model.Usuario;

/**
 * 
 * @author candr
 *
 */
@RestController
public class UsuarioRestController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@RequestMapping(path = "usuario")
	List<Usuario> usuario() {
		return usuarioRepository.findAll();
	}
}
