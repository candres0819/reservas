package com.carloscardona.tns;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import com.carloscardona.tns.dao.AeropuertoRepository;
import com.carloscardona.tns.dao.UsuarioRepository;
import com.carloscardona.tns.dao.VueloRepository;
import com.carloscardona.tns.model.Aeropuerto;
import com.carloscardona.tns.model.Usuario;
import com.carloscardona.tns.model.Vuelo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReservasApplication.class)
@WebAppConfiguration
class ReservasApplicationTests {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	AeropuertoRepository aeropuertoRepository;

	@Autowired
	VueloRepository vueloRepository;

	@Before
	public void setup() {
		try {
			// Aeropuertos
			Aeropuerto aeropuerto = new Aeropuerto();
			aeropuerto.setNombre("JMC");
			aeropuerto.setCiudad("Medellín");
			aeropuertoRepository.save(aeropuerto);

			aeropuerto = new Aeropuerto();
			aeropuerto.setNombre("Dorado");
			aeropuerto.setCiudad("Bogotá");
			aeropuertoRepository.save(aeropuerto);

			List<Aeropuerto> aeropuertos = aeropuertoRepository.findAll();
			for (Aeropuerto aeropuertoInstance : aeropuertos) {
				System.out.println("Aeropuerto: " + aeropuertoInstance.toString());
			}

			// Vuelos para consulta
			List<Vuelo> vuelos = new ArrayList<>();
			Vuelo vuelo;
			for (int i = 1; i < 10; i++) {
				vuelo = new Vuelo();
				vuelo.setAerolinea("AVIANCA");
				vuelo.setOrigen(aeropuertoRepository.findOne(1L));
				vuelo.setDestino(aeropuertoRepository.findOne(2L));
				vuelo.setSalida(new Date());
				vuelo.setLlegada(new Date(new Date().getTime() + 300));
				vuelo.setTarifa(new BigDecimal(1000000 * i));
				vuelos.add(vuelo);
			}
			vueloRepository.save(vuelos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void createUser() {
		Usuario usuario = new Usuario();
		usuario.setTipoDocumento("CC");
		usuario.setDocumento(1152197588L);
		usuario.setEmail("candres0819@gmail.com");
		usuario.setNombre("Carlos Andrés");
		usuario.setApellido("Cardona");
		usuario.setUserName("candres");
		usuario.setPassword("1234");
		Assert.notNull(usuarioRepository.save(usuario));
	}

// --Commented out by Inspection START (2/03/2018 1:57 AM):
//	@Test
//	public void consultarVuelos() {
//		List<Vuelo> vuelos = vueloRepository.findAll();
//		for (Vuelo vuelo : vuelos) {
//			System.out.println("Vuelo:" + vuelo.toString());
//		}
//	}
// --Commented out by Inspection STOP (2/03/2018 1:57 AM)

	@Test
	public void consultarVueloHorario() {
		Date actual = new Date();
		Date salida = new Date(actual.getTime() - 1000);
		Date llegada = new Date(actual.getTime() - 500);
		List<Vuelo> vuelos = vueloRepository.findByDatesBetween(salida, llegada);
		for (Vuelo vuelo : vuelos) {
			System.out.println("Vuelo:" + vuelo.toString());
		}
	}

	@Test
	public void consultarVueloTarifa() {
		BigDecimal tarifa = new BigDecimal(1000000);
		List<Vuelo> vuelos = vueloRepository.findByTarifa(tarifa);
		for (Vuelo vuelo : vuelos) {
			System.out.println("Vuelo:" + vuelo.toString());
		}
	}

	@Test
	public void consultarVueloEstado() {
		// TODO: implements methos with enum.
	}
}
