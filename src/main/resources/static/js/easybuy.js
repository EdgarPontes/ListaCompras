var EB = EB || {};

EB.onSidebarToggleRequest = function(event) {
  event.preventDefault();
  $(this).blur();

  $('.js-sidebar, .js-content').toggleClass('is-toggled');
};

EB.onSearchModalShowRequest = function(event) {
  event.preventDefault();

  $('.js-search-modal').fadeIn('slow');
  $('body').addClass('aw-no-scroll');
  
  $('.js-search-modal-input').val('').select();
  
};

EB.onSearchModalCloseRequest = function(event) {
  event.preventDefault();

  $('.js-search-modal').hide();
  $('body').removeClass('aw-no-scroll');
};

//EB.onFormLoadingSubmit = function(event) {
  //event.preventDefault();

  //EB.showLoadingComponent();

  //setTimeout(function() {
  //  EB.hideLoadingComponent();
  //}, 2000);
//};

EB.showLoadingComponent = function() {
  $('.js-loading-overlay').css('display', 'table').hide().fadeIn('slow');
};

EB.hideLoadingComponent = function() {
  $('.js-loading-component').fadeOut('fast');
};

EB.initStickyTableHeaders = function() {
  if ($(window).width() >= 992) { 
    var stickyRef = $('.js-sticky-reference');
    var stickyTable = $('.js-sticky-table');

    if (stickyRef && stickyTable) {
      stickyTable.stickyTableHeaders({fixedOffset: stickyRef});
    }
  }
};

EB.onMenuGroupClick = function(event) {
  var subItems = $(this).parent().find('ul');

  if (subItems.length) {
    event.preventDefault();
    $(this).parent().toggleClass('is-expanded');
  }
};

EB.initMenu = function() {
  $('.js-menu > ul > li > a').bind('click', EB.onMenuGroupClick);
  $('.aw-menu__item .is-active').parents('.aw-menu__item').addClass('is-expanded is-active');
};

$(function() {
  if (EB.init) {
    EB.init();
  }

  EB.initMenu();
  EB.initStickyTableHeaders();
  
  // Enable Bootstrap tooltip
  $('.js-tooltip').tooltip();
  
  // Bind events
  $('.js-sidebar-toggle').bind('click', EB.onSidebarToggleRequest);
  $('.js-search-modal-trigger-show').bind('click', EB.onSearchModalShowRequest);
  $('.js-search-modal-close').bind('click', EB.onSearchModalCloseRequest);
  //$('.js-form-loading').bind('submit', EB.onFormLoadingSubmit);
});