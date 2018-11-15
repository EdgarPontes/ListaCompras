package com.edgar.listacompras.repository.produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.edgar.listacompras.model.Produto;

public class ProdutosImpl implements ProdutosQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Produto> buscarPorNome(String nome) {
		String jpql = "select p from "+ Produto.class.getName() 
				+" p where lower(nome) like lower(:nome)";
		List<Produto> produtosFiltrados = manager.createQuery(jpql, Produto.class)
					.setParameter("nome", "%" + nome + "%")
					.getResultList();
		
		return produtosFiltrados;
	}
}
