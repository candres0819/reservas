/**
 * 
 */
package com.carloscardona.tns.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carloscardona.tns.model.Usuario;

/**
 * @author candr
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUserName(String username);

	Usuario findByNombre(String username);

}