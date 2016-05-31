/**
 * 
 */
package com.carloscardona.tns.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import com.carloscardona.tns.model.Usuario;

/**
 * @author candr
 *
 */
@RestController
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUserName(String username);

	Usuario findByNombre(String username);

}