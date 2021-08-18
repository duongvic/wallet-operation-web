// ---------------------------------------------------------------
var url = "/wallet/balance/total-assets/findBankAccountOnBank";
var bankCode = '';
// ---------------------------------------------------------------
$('#bankCodes').on('select2:select', function (e) {
  bankCode = e.params.data.id;
  $('#bankAccountIds').empty().append(
    '<option selected value="" disabled>Chọn tài khoản</option>');
  $('#bankAccountIds').prop('disabled', false);
  $.ajax({
    url: ctx + url,
    dataType: 'json',
    data: {
      bankCode: bankCode,
    },
    type: 'POST',
    success: function (data) {
      var bankAccounts = data.bankAccounts;
      for (var i = 0; i < bankAccounts.length; i++) {
        $('#bankAccountIds').append(
          '<option value="' + bankAccounts[i].bankAccount + '">'
          + bankAccounts[i].bankAccount + '</option>');
      }
    },
    error: function (data) {

    }
  })
});

// $('#bankAccountIds').select2({
//   width: "100%",
//   dropdownAutoWidth: true,
//   ajax: {
//     url: ctx + url,
//     dataType: 'json',
//     type: "POST",
//     data: function (params) {
//       var query = {
//         // bankAcc: params.term,
//         bankCode: bankCode,
//         type: 'public'
//       }
//
//       // Query parameters will be ?search=[term]&type=public
//       return query;
//     },
//     // Additional AJAX parameters go here; see the end of this chapter for the full code of this example
//     processResults: function (data) {
//       // Transforms the top-level key of the response object from 'items' to 'results'
//       var retVal = [];
//       var bankAccounts = data.bankAccounts;
//       console.log(bankAccounts);
//       for (var i = 0; i < bankAccounts.length; i++) {
//         var lineObj = {
//           id: bankAccounts[i].id,
//           text: bankAccounts[i].bankAccount
//         };
//         retVal.push(lineObj);
//       }
//
//       return {
//         // results: data.items
//         results: retVal
//
//       };
//     }
//   },
//   placeholder: 'Tìm kiếm khách hàng',
//   minimumInputLength: 3,
//   language: {
//     inputTooShort: function () {
//       return 'Nhập bằng hoặc nhiều hơn 3 kí tự';
//     }
//   }
// });