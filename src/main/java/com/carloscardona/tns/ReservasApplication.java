package com.carloscardona.tns;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.h2.server.web.WebServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.carloscardona.tns.dao.AeropuertoRepository;
import com.carloscardona.tns.dao.UsuarioRepository;
import com.carloscardona.tns.dao.VueloRepository;
import com.carloscardona.tns.model.Aeropuerto;
import com.carloscardona.tns.model.Usuario;
import com.carloscardona.tns.model.Vuelo;

@SpringBootApplication
public class ReservasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservasApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UsuarioRepository usuarioRepository, AeropuertoRepository aeropuertoRepository,
			VueloRepository vueloRepository) {
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

				// Aeropuertos
				Aeropuerto aeropuerto = new Aeropuerto();
				aeropuerto.setNombre("JMC");
				aeropuerto.setCiudad("Medellín");
				aeropuertoRepository.save(aeropuerto);

				aeropuerto = new Aeropuerto();
				aeropuerto.setNombre("Dorado");
				aeropuerto.setCiudad("Bogotá");
				aeropuertoRepository.save(aeropuerto);

				// Vuelos para consulta
				List<Vuelo> vuelos = new ArrayList<Vuelo>();
				Vuelo vuelo;
				for (int i = 1; i < 10; i++) {
					vuelo = new Vuelo();
					vuelo.setAerolinea("AVIANCA");
					vuelo.setOrigen(aeropuertoRepository.findOne(1L));
					vuelo.setDestino(aeropuertoRepository.findOne(2L));
					Calendar cal = Calendar.getInstance();
					if (i % 2 == 0) {
						Date today = cal.getTime();
						vuelo.setSalida(today);
						vuelo.setLlegada(new Date(today.getTime() + 300));
					} else {
						cal.add(Calendar.DATE, -1);
						Date yesterday = cal.getTime();
						vuelo.setSalida(yesterday);
						vuelo.setLlegada(new Date(yesterday.getTime() + 300));
					}
					vuelo.setTarifa(new BigDecimal(1000000 * i));
					vuelos.add(vuelo);
				}
				vueloRepository.save(vuelos);
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