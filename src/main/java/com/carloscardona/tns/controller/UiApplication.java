package com.carloscardona.tns.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carloscardona.tns.dao.UsuarioRepository;

/**
 * 
 * @author candr
 *
 */
@RestController
public class UiApplication {

	@Autowired
	UsuarioRepository usuarioRepository;

	@RequestMapping("/resource")
	public Map<String, Object> home() {
		System.out.println("home");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		System.out.println("user: " + user);
		return user;
	}

	@RequestMapping("/singin")
	public String login(HttpServletRequest request) {
		System.out.println("login");
		return "login";
	}

	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, Principal user) {
		System.out.println("logout");
		request.getSession().invalidate();
	}
}