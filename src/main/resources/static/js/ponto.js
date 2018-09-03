$('#confirmacaoExclusaoModalPonto').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigoPonto = button.data('codigo');
	var nomePonto = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigoPonto);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o ponto <strong>' + nomePonto + ' </strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
});