package com.edgar.listacompras.session;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.edgar.listacompras.model.ItemLista;
import com.edgar.listacompras.model.Produto;

@SessionScope
@Component
public class TabelasItensSession {

	private Set<TabelaItensLista> tabelas = new HashSet<>();
	
	public void adicionarItem(String uuid, Produto produto, int quantidade) {
		TabelaItensLista tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(produto, quantidade);
		tabelas.add(tabela);
	}

	public void alterarQuantidadeItens(String uuid, Produto produto, Integer quantidade) {
		TabelaItensLista tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarQuantidadeItens(produto, quantidade);
	}
	
	public void alterarValorItens(String uuid, Produto produto, BigDecimal valor) {
		TabelaItensLista tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarValorItens(produto, valor);
	}

	public void excluirItem(String uuid, Produto produto) {
		TabelaItensLista tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(produto);
	}

	public List<ItemLista> getItens(String uuid) {
		return buscarTabelaPorUuid(uuid).getItens();
	}
	
	public Object getValorTotal(String uuid) {
		return buscarTabelaPorUuid(uuid).getValorTotal();
	}
	
	private TabelaItensLista buscarTabelaPorUuid(String uuid) {
		TabelaItensLista tabela = tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensLista(uuid));
		return tabela;
	}
}
