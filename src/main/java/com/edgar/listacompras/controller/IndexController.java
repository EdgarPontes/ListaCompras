package com.edgar.listacompras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edgar.listacompras.model.Produto;
import com.edgar.listacompras.service.CadastroListaService;

@Controller
public class IndexController {

	@Autowired
	private CadastroListaService cadastroListaService;

	@GetMapping("/")
	public ModelAndView dashboard(String email) {
		ModelAndView mv = new ModelAndView("Index");
		
		mv.addObject(new Produto());
		
		mv.addObject("valorTotalLista", cadastroListaService.ultimaListaPorEmail("edgarponte@gmail.com").getValorTotal());
		mv.addObject("totalDeItensLista", cadastroListaService.ultimaListaPorEmail("edgarponte@gmail.com").getTotalDeItens());
		mv.addObject("dataCompra", cadastroListaService.ultimaListaPorEmail("edgarponte@gmail.com").getDataCriacao());
		
		return mv;
	}
	
}
