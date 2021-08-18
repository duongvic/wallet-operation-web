/*
Name: 			Tables / Advanced - Examples
Written by: 	Okler Themes - (http://www.okler.net)
Theme Version: 	1.3.0
*/
var paymentTable = null;
var contractTable = null;
var requestTable = null;
var roleTable = null;
var recentAccessTable = null;
(function ($) {

  'use strict';

  var datatableInit = function () {

    $('#datatable-default').dataTable();

    var datatableRole = $('#datatable-role');
    if (datatableRole !== undefined) {
      roleTable = datatableRole.dataTable({
        "columnDefs": [{
          "searchable": false,
          "orderable": false,
          "targets": 0
        }],
        "order": [[1, 'asc']]
      });

      roleTable.api().on('order.dt search.dt', function () {
        roleTable.api().column(0,
            {search: 'applied', order: 'applied'}).nodes().each(
            function (cell, i) {
              cell.innerHTML = i + 1;
            });
      }).draw();
    }

    $('#datatable-atb').dataTable().api().on('order.dt search.dt', function () {
      $('#datatable-atb').dataTable().api().column(0,
          {search: 'applied', order: 'applied'}).nodes().each(
          function (cell, i) {
            cell.innerHTML = i + 1;
          });
    }).draw();

    paymentTable = $('#datatable-payment').dataTable({searching: false});

    contractTable = $('#datatable-contract').dataTable(
        {searching: false, width: '100%'});

    requestTable = $('#table-request').dataTable(
        {searching: false, width: '100%'});
  };

  $(function () {
    datatableInit();
  });

}).apply(this, [jQuery]);