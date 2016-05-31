package com.carloscardona.tns.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import com.carloscardona.tns.model.Vuelo;

/**
 * 
 * @author candr
 *
 */
@RestController
public interface VueloRepository extends JpaRepository<Vuelo, Long> {

	@Query("select b from Vuelo b where b.salida between ?1 and ?2 and b.llegada between ?1 and ?2")
	List<Vuelo> findByDatesBetween(Date departure, Date arrival);

}