package com.edgar.listacompras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edgar.listacompras.model.Produto;

public interface Produtos extends JpaRepository<Produto, Long>{

}
