package com.edgar.listacompras.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.listacompras.model.Produto;
import com.edgar.listacompras.repository.Produtos;
import com.edgar.listacompras.repository.produto.ProdutosQueries;

@Service
public class CadastroProdutoService implements ProdutosQueries{

	@Autowired
	private Produtos produtos;

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void salvar(Produto produto) {
//		Produto produtoUpdate = produtos.findByNome(produto.getNome());
//		
//		if (produtoUpdate != null) {
//			produto.setCodigo(produtoUpdate.getCodigo());
//		}
		produtos.save(produto);
	}
	
	@Transactional
	public void excluir(Produto produto) {
		produtos.delete(produto);
		produtos.flush();
	}

	@Override
	public List<Produto> findByNome(String nome) {
		String jpql = "select p from "+ Produto.class.getName() 
				+" p where lower(nome) like lower(:nome)";
		List<Produto> produtosFiltrados = manager.createQuery(jpql, Produto.class)
					.setParameter("nome", nome + "%")
					.getResultList();
		
		return produtosFiltrados;
	}

}
