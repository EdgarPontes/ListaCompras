package com.edgar.listacompras.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.listacompras.model.Lista;
import com.edgar.listacompras.repository.Listas;

@Service
public class CadastroListaService {

	@Autowired
	private Listas listas;
	
	@Transactional
	public Lista salvar(Lista lista) {
		
		if (lista.isNova()) {
			lista.setDataCriacao(LocalDateTime.now());
		}else {
			Lista listaExistente = listas.getOne(lista.getCodigo());
			lista.setDataCriacao(listaExistente.getDataCriacao());
		}
		
		return listas.saveAndFlush(lista);
	}
	
}
