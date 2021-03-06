$(document).ready(function () {
    var view = window.location.pathname.replace(/\//g, '');
    $('ul.nav > li').removeClass('active');
    $('ul.nav a[href="#' + view + '"]').parent().addClass('active');
});