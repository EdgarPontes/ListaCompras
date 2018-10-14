var ListaCompras = ListaCompras || {};

ListaCompras.MaskMoney = (function() {
	
	function MaskMoney() {
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}
	
	MaskMoney.prototype.enable = function() {
//		this.decimal.maskMoney({ decimal: ',', thousands: '.' });
//		this.plain.maskMoney({ precision: 0, thousands: '.' });
		this.decimal.maskNumber({ decimal: ',', thousands: '.' });
		this.plain.maskNumber({ integer: true, thousands: '.' });
	}
	
	return MaskMoney;
	
}());

ListaCompras.MaskDate = (function() {
	
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			autoclose: true
		});
	}
	
	return MaskDate;
	
}());

numeral.language('pt-br');

ListaCompras.formatarMoeda = function(valor) {
	return numeral(valor).format('0,0.00');
}

ListaCompras.recuperarValor = function(valorFormatado) {
	return numeral().unformat(valorFormatado);
}

$(function() {
	var maskMoney = new ListaCompras.MaskMoney();
	maskMoney.enable();
	
	var maskDate = new ListaCompras.MaskDate();
	maskDate.enable();
	
});