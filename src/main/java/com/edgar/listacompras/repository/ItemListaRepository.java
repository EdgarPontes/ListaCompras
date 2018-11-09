package com.edgar.listacompras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edgar.listacompras.model.ItemLista;

public interface ItemListaRepository extends JpaRepository<ItemLista, Long> {

	public List<ItemLista> findByListaCodigo(Long idLista) ;

}
