package com.edgar.listacompras.controller.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.edgar.listacompras.model.Lista;

@Component
public class ListaValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Lista.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Lista lista = (Lista) target;
		
		validarSeInformouItens(errors, lista);
		validarValorTotalNegativo(errors, lista);
	}

	private void validarValorTotalNegativo(Errors errors, Lista lista) {
		if (lista.getValorTotal().compareTo(BigDecimal.ZERO) < 0) {
			errors.reject("", "Valor total não pode ser negativo");
		}
		
	}

	private void validarSeInformouItens(Errors errors, Lista lista) {
		if (lista.getItens().isEmpty()) {
			errors.reject("", "Adicione pelo menos um produto na lista");
		}		
	}

}
