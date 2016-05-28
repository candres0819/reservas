package com.carloscardona.tns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carloscardona.tns.model.Usuario;

@SpringBootApplication
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
				usuarioRepository.save(usuario);

				System.out.println(usuario.toString());
			}
		};
	}
}

interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

@RestController
class UsuarioRestController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@RequestMapping(path = "usuario")
	List<Usuario> usuario() {
		return usuarioRepository.findAll();
	}
}
