var botaoSalvarProduto = document.querySelector('#botaoSalvarProduto');
var produto = document.querySelector('#form-produto');
var numeroLista = document.querySelector('#lista');

botaoSalvarProduto.addEventListener('click', function(){

	$.post("item",
		    {
				nomeProduto:produto.nome.value,
				uuid:numeroLista.value,
				quantidade:produto.quantidade.value
		    },
		    function(data, status){
		        alert("Data: " + data + "\nStatus: " + status);
		    });
//	var resposta = $.ajax({
//		url: 'item',
//		method: 'POST',
//		data: {
//			nomeProduto:produto.nome.value,
//			uuid:numeroLista.value,
//			quantidade:produto.quantidade.value
//		}
//	});	   

});

$('#cadastroModalProduto').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);

	// var valorProduto = button.data('valor');
	// var nomeUsuario = button.data('nome');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	// if (!action.endsWith('/')) {
	// action += '/';
	// }
	form.attr('action', action);

	modal.find('.modal-body span').html('<strong> salvar </strong>?');

});

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-decimal').maskMoney({
		decimal : ',',
		thousands : '.',
		allowZero : true
	});
});