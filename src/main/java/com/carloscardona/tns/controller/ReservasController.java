package com.carloscardona.tns.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.carloscardona.tns.dao.UsuarioRepository;
import com.carloscardona.tns.model.Usuario;

/**
 * 
 * @author candr
 *
 */
@RestController
public class ReservasController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@RequestMapping("/resource")
	public Map<String, Object> home(HttpServletRequest request) {
		Principal user = request.getUserPrincipal();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", user.getName());
		return model;
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public ResponseEntity<Void> registrar(@RequestBody Usuario user, UriComponentsBuilder ucBuilder) {
		System.out.println(user.toString());
		if (isUserExist(user)) {
			System.out.println("A User with name " + user.getUserName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		usuarioRepository.save(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/usuario/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	private boolean isUserExist(Usuario user) {
		return usuarioRepository.findByUserName(user.getUserName()) != null;
	}
}