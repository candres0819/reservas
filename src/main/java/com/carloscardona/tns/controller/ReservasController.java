package com.carloscardona.tns.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.carloscardona.tns.dao.VueloRepository;
import com.carloscardona.tns.model.Usuario;
import com.carloscardona.tns.model.Vuelo;

/**
 * 
 * @author candr
 *
 */
@RestController
public class ReservasController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	VueloRepository vueloRepository;

	@RequestMapping("/resource")
	public Map<String, Object> home(HttpServletRequest request) {
		Principal user = request.getUserPrincipal();

		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();

		cal.add(Calendar.DATE, -1);
		Date yesterday = cal.getTime();

		List<Vuelo> vuelos = vueloRepository.findByDatesBetween(yesterday, today);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", user.getName());
		model.put("vuelos", vuelos);
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