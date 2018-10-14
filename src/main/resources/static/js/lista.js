ListaCompras.Lista = (function() {
	
	function Lista(tabelaItens) {
		this.tabelaItens = tabelaItens;		
		this.valorTotalBox = $('.js-valor-total-box');
		this.valorTotalBoxContainer = $('.js-valor-total-box-container');
		this.valorTotalItens = this.tabelaItens.valorTotal();
	}
	
	Lista.prototype.iniciar = function() {
		this.tabelaItens.on('tabela-itens-atualizada', onTabelaItensAtualizada.bind(this));
		this.tabelaItens.on('tabela-itens-atualizada', onValoresAlterados.bind(this));
		
		onValoresAlterados.call(this);
	}
	
	function onTabelaItensAtualizada(evento, valorTotalItens) {
		this.valorTotalItens = valorTotalItens == null ? 0 : valorTotalItens;
	}
	
	function onValoresAlterados() {
		var valorTotal = numeral(this.valorTotalItens);
		this.valorTotalBox.html(ListaCompras.formatarMoeda(valorTotal));
		
		this.valorTotalBoxContainer.toggleClass('negativo', valorTotal < 0);

	}
	
	return Lista;
	
}());

$(function() {
	
	var autocomplete = new ListaCompras.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new ListaCompras.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
	var lista = new ListaCompras.Lista(tabelaItens);
	lista.iniciar();
	
});