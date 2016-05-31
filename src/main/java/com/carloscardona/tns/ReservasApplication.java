package com.carloscardona.tns;

import org.h2.server.web.WebServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.carloscardona.tns.dao.UsuarioRepository;
import com.carloscardona.tns.model.Usuario;

@SpringBootApplication
//@EnableOAuth2Sso
public class ReservasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservasApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UsuarioRepository usuarioRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				Usuario usuario = new Usuario();
				usuario.setTipoDocumento("CC");
				usuario.setDocumento(new Long(1152197588));
				usuario.setEmail("candres0819@gmail.com");
				usuario.setNombre("Carlos Andrés");
				usuario.setApellido("Cardona");
				usuario.setUserName("candres");
				usuario.setPassword("1234");
				usuarioRepository.save(usuario);
			}
		};
	}

	@Bean
	ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}
}