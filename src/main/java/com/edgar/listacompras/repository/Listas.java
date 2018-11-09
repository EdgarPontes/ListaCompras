package com.edgar.listacompras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edgar.listacompras.model.Lista;

public interface Listas extends JpaRepository<Lista, Long>{

	List<Lista> findByEmail(String email);

}
