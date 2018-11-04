package com.edgar.listacompras.service;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.listacompras.model.Lista;
import com.edgar.listacompras.repository.Listas;
import com.edgar.listacompras.repository.lista.ListasQueries;

@Service
public class CadastroListaService implements ListasQueries{

	@Autowired
	private Listas listas;
	
	@PersistenceContext
	private EntityManager manager;
	
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

	@Override
	public Lista ultimaListaPorEmail(String email) {
		String jpql = "select l from " + Lista.class.getName() + " l where dataCriacao" + 
				" in (select max(dataCriacao) from "+Lista.class.getName() +" where email = :email)";
		
		Lista lista = manager.createQuery(jpql, Lista.class)
				.setParameter("email", email )
				.getSingleResult();
		
		return lista;
	}
	
}
