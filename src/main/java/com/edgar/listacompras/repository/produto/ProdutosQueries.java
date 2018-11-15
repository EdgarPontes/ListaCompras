package com.edgar.listacompras.repository.produto;

import java.util.List;

import com.edgar.listacompras.model.Produto;

public interface ProdutosQueries {

    public List<Produto> buscarPorNome(String nome);
    
}