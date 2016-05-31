package com.carloscardona.tns.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import com.carloscardona.tns.model.Aeropuerto;

/**
 * 
 * @author candr
 *
 */
@RestController
public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Long> {

}
