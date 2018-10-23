package com.edgar.listacompras.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edgar.listacompras.controller.validator.ListaValidator;
import com.edgar.listacompras.model.Lista;
import com.edgar.listacompras.model.Produto;
import com.edgar.listacompras.repository.Produtos;
import com.edgar.listacompras.service.CadastroListaService;
import com.edgar.listacompras.session.TabelasItensSession;

@Controller
@RequestMapping("listas")
public class ListasController {
	
	@Autowired
	private TabelasItensSession tabelaItens;
	
	@Autowired
	private Produtos produtos;
	
	@Autowired
	private ListaValidator listaValidator;
	
	@Autowired
	private CadastroListaService cadastroListaService;

	@GetMapping("/nova")
	public ModelAndView nova(Lista lista, Produto produto) {
		ModelAndView mv = new ModelAndView("CadastroListas");
		
		setUuid(lista);
		
		mv.addObject(produto);
		
		mv.addObject("itens", lista.getItens());
		
		return mv;
	}
	
	@PostMapping(value = "/nova", params = "salvar")
	public ModelAndView salvar(Lista lista, BindingResult result, RedirectAttributes attributes) {
		validarLista(lista, result);
		if (result.hasErrors()) {
			return null;
		}
		
		lista.setValorTotal(lista.getValorTotalItens());
		cadastroListaService.salvar(lista);
		attributes.addFlashAttribute("mensagem", "Lista salva com sucesso");
		return new ModelAndView("redirect:/listas/nova");
	}
	
	@PostMapping(value="/nova", params = "cancelar")
	public ModelAndView cancelar(Lista lista, BindingResult result, RedirectAttributes attributes) {
		
		return new ModelAndView("redirect:/listas/nova");
	}
	@PostMapping(value = "/item")
	public ModelAndView adicionarItem(Long codigoItem, String uuid) {

		Produto produto = produtos.getOne(codigoItem);
		tabelaItens.adicionarItem(uuid, produto, 1);
		
		return mvTabelaItensLista(uuid);
	}
	
	@PutMapping("/item/{codigoProduto}/quantidade")
	public ModelAndView alterarQuantidadeItem(@PathVariable("codigoProduto") Produto produto
			, Integer quantidade, String uuid) {
		tabelaItens.alterarQuantidadeItens(uuid, produto, quantidade);
		
		return mvTabelaItensLista(uuid);
	}
	@PutMapping("/item/{codigoProduto}/valor")
	public ModelAndView alterarQuantidadeItem(@PathVariable("codigoProduto") Produto produto
			,String uuid, BigDecimal valor) {
		tabelaItens.alterarValorItens(uuid, produto, valor);
		
		return mvTabelaItensLista(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{codigoProduto}")
	public ModelAndView excluirItem(@PathVariable("codigoProduto") Produto produto
			, @PathVariable String uuid) {
		tabelaItens.excluirItem(uuid, produto);
		return mvTabelaItensLista(uuid);
	}
	
	private void validarLista(Lista lista, BindingResult result) {
		lista.adicionarItens(tabelaItens.getItens(lista.getUuid()));
			
		listaValidator.validate(lista, result);
	}
	
	private ModelAndView mvTabelaItensLista(String uuid) {
		ModelAndView mv = new ModelAndView("TabelaItemLista");
		mv.addObject("itens", tabelaItens.getItens(uuid));
		mv.addObject("valorTotal", tabelaItens.getValorTotal(uuid));
		return mv;
	}
	
	private void setUuid(Lista lista) {
		if (StringUtils.isEmpty(lista.getUuid())) {
			lista.setUuid(UUID.randomUUID().toString());
		}
	}
}
