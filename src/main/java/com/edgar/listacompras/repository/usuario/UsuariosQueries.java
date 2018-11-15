package com.edgar.listacompras.repository.usuario;

import java.util.List;
import java.util.Optional;

import com.edgar.listacompras.model.Usuario;

public interface UsuariosQueries {

	public Optional<Usuario> porUsernameEAtivo(String username);

	public List<String> permissoes(Usuario usuario);

	public Usuario buscarComGrupos(Long codigo);

}