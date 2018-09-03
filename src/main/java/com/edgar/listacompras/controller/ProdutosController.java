package com.edgar.listacompras.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edgar.listacompras.model.Produto;
import com.edgar.listacompras.service.CadastroProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private CadastroProdutoService cadastroProdutoService;

	@RequestMapping("/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView();

		return mv;
	}

	@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Produto produto, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(produto);
		}

		cadastroProdutoService.salvar(produto);
		attributes.addFlashAttribute("mensagem", "Produto salva com sucesso!");
		return new ModelAndView("redirect:/produtos/novo");
	}

	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Produto produto) {

		cadastroProdutoService.excluir(produto);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Produto produto) {
		ModelAndView mv = novo(produto);
		mv.addObject(produto);
		return mv;
	}

}
