package com.edgar.listacompras.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.listacompras.model.Produto;
import com.edgar.listacompras.repository.Produtos;

@Service
public class CadastroProdutoService {

	@Autowired
	private Produtos produtos;

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

}
