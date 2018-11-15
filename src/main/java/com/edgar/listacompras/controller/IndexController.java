package com.edgar.listacompras.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edgar.listacompras.model.Produto;
import com.edgar.listacompras.model.Usuario;
import com.edgar.listacompras.service.CadastroListaService;
import com.edgar.listacompras.service.CadastroUsuarioService;
import com.edgar.listacompras.service.exception.EmailUsuarioJaCadastradoException;
import com.edgar.listacompras.service.exception.SenhaObrigatoriaUsuarioException;

@Controller
public class IndexController {

	@Autowired
	private CadastroListaService cadastroListaService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@GetMapping("/")
	public ModelAndView dashboard(String email) {
		ModelAndView mv = new ModelAndView("Index");
		
		mv.addObject(new Produto());
		
		mv.addObject("valorTotalLista", cadastroListaService.ultimaListaPorEmail("edgarponte@gmail.com").getValorTotal());
		mv.addObject("totalDeItensLista", cadastroListaService.ultimaListaPorEmail("edgarponte@gmail.com").getTotalDeItens());
		mv.addObject("dataCompra", cadastroListaService.ultimaListaPorEmail("edgarponte@gmail.com").getDataCriacao());
		
		return mv;
	}
	
	@PostMapping(value = "/login", params = "salvar")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
		}
		
		try {
			cadastroUsuarioService.salvar(usuario);
		} catch (EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
		} catch (SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
		}
		
		attributes.addFlashAttribute("mensagem", "Login salvo com sucesso");
		return new ModelAndView("redirect:/login");
	}
	
	@GetMapping("/login")
	public String login() {
		return "Login";
	}
	
	@GetMapping("/403")
	public String acessoNegado() {
		return "403";
	}
}
