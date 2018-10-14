var ListaCompras = ListaCompras || {};

ListaCompras.Autocomplete = (function() {
	
	function Autocomplete() {
		this.nomeInput = $('.js-nome-item-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-item').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(nome) {
				return this.nomeInput.data('url') + '?nome=' + nome;
			}.bind(this),
			getValue: 'nome',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},
			template: {
				type: 'custom',
				method: template.bind(this)
			},
			list: {
				onChooseEvent: onItemSelecionado.bind(this)
			}
		};
		
		this.nomeInput.easyAutocomplete(options);
	}

	function onItemSelecionado() {
		this.emitter.trigger('item-selecionado', this.nomeInput.getSelectedItemData());
		this.nomeInput.val('');
		this.nomeInput.focus();
	}
	
	function template(nome, produto){
		produto.valorFormatado = ListaCompras.formatarMoeda(produto.valorProduto);
		return this.template(produto);
	}
		
	return Autocomplete
	
}());
