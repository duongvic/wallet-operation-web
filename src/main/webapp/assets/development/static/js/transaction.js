
var Transaction = function () {

  var searchTransaction = function () {
    $('#btn-search').on('click', function () {
      var url = location.href.replace(/&?id=([^&]$|[^&]*)/i, '');
      if (url.indexOf('?') == -1) {
        url = url + '?';
      }
      location = url + '&id=' + $('#search').val();
    });
    $('#search').on('keypress', function (event) {
      if (event.which === 13) {
        var url = location.href.replace(/&?id=([^&]$|[^&]*)/i, '');
        if (url.indexOf('?') == -1) {
          url = url + '?';
        }
        location = url + '&id=' + $('#search').val();
      }
    });
  };

  return {
    // main function to initiate the module
    init: function () {
      // dropdown navbar
      //$("#navbar-main-1").bootstrapDropdownOnHover();
      //$("#navbar-main-2").bootstrapDropdownOnHover();
      //$("#navbar-main-3").bootstrapDropdownOnHover();
      $(".guide_alert_over").click(function () {
        $("body").addClass("body_over");
      });
      $(".modal-dismiss").click(function () {
        $("body").removeClass("body_over");
      });
      $('.c-column .multiselect-container li').last().after(
          '<li class="btn-rs"><a href="">Reset as default</a></li>');
      //filter by date
      //filterTransactionByDateRange();
      //fulltext search
      searchTransaction();
    }
  };
}();
jQuery(document).ready(function () {
  Transaction.init();
});