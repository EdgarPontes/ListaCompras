package com.edgar.listacompras.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.edgar.listacompras.model.Grupo;
import com.edgar.listacompras.model.Usuario;
import com.edgar.listacompras.repository.Grupos;
import com.edgar.listacompras.repository.Usuarios;
import com.edgar.listacompras.service.exception.EmailUsuarioJaCadastradoException;
import com.edgar.listacompras.service.exception.SenhaObrigatoriaUsuarioException;

@Service
public class CadastroUsuarioService {

	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private Grupos grupos;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarios.findByUsername(usuario.getUsername());
		List<Grupo> grupo = grupos.findAll();
				
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado");
		}

		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getPassword())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para novo usuário");
		}

		if (usuario.isNovo() || !StringUtils.isEmpty(usuario.getPassword())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getPassword()));
			usuario.setDataCriacao(LocalDate.now());
			usuario.setGrupos(grupo);
			
		} else if (StringUtils.isEmpty(usuario.getPassword())) {
			usuario.setSenha(usuarioExistente.get().getPassword());
		}
		usuario.setConfirmacaoSenha(usuario.getPassword());

		if (!usuario.isNovo() && usuario.getAtivo() == null) {
			usuario.setAtivo(usuarioExistente.get().getAtivo());
		}

		usuarios.save(usuario);
	}
}
