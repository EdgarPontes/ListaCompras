package com.edgar.listacompras.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.edgar.listacompras.model.ItemLista;
import com.edgar.listacompras.model.Produto;

class TabelaItensLista {

	private String uuid;
	private List<ItemLista> itens = new ArrayList<>();

	TabelaItensLista(String uuid) {
		this.uuid = uuid;
	}

	public BigDecimal getValorTotal() {
		return itens.stream()
				.map(ItemLista::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Produto produto, Integer quantidade) {
		Optional<ItemLista> itemVendaOptional = buscarItemPorProduto(produto); 
		
		ItemLista itemLista = null;
		
		if (itemVendaOptional.isPresent()) {
			itemLista = itemVendaOptional.get();
			itemLista.setQuantidade(itemLista.getQuantidade() + quantidade);		
		}else {
			itemLista = new ItemLista();
			itemLista.setProduto(produto);
			itemLista.setQuantidade(quantidade);
			itemLista.setValorUnitario(produto.getValorProduto());
			itens.add(0, itemLista);
		}
	}
	
	public void alterarQuantidadeItens(Produto produto, Integer quantidade) {
		 ItemLista itemLista = buscarItemPorProduto(produto).get();
		 itemLista.setQuantidade(quantidade);
	}
	
	public void alterarValorItens(Produto produto, BigDecimal valor) {
		ItemLista itemLista = buscarItemPorProduto(produto).get();
		itemLista.setValorUnitario(valor);
	}
	
	public void excluirItem(Produto produto) {
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i)
						.getProduto()
						.equals(produto))
				.findAny().getAsInt();
		itens.remove(indice);
	}
	
	public int total() {
		return itens.size();
	}
	
	public List<ItemLista> getItens(){
		return itens;
	}

	private Optional<ItemLista> buscarItemPorProduto(Produto produto) {
		return itens.stream()
				.filter(i -> i.getProduto().equals(produto))
				.findAny();
	}
	
	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaItensLista other = (TabelaItensLista) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
}
