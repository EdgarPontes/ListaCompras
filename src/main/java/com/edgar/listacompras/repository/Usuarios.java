package com.edgar.listacompras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edgar.listacompras.model.Usuario;
import com.edgar.listacompras.repository.usuario.UsuariosQueries;

public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {

	public Optional<Usuario> findByUsername(String username);

	public List<Usuario> findByCodigoIn(Long[] codigos);

}
