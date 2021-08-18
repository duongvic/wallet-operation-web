//clear data of filter form
function clearDataOf(multiSelects, select2, inputs) {
  if(multiSelects != null && multiSelects.length > 0)
  for(var i = 0; i < multiSelects.length; i++){
    $('#'+multiSelects[i]+' option:selected').each(function () {
      $(this).prop('selected', false);
    });
    $('#'+multiSelects[i]).multiselect('refresh');
  }
  if(select2 != null && select2.length > 0)
  for(var i = 0; i < select2.length; i++){
    $('#'+select2[i]).val(null).trigger('change');
  }

  if(inputs != null && inputs.length > 0)
  for(var i = 0; i < inputs.length; i++){
    $('#'+inputs[i]).val(null);
  }
  $('#reservation').val('Tất cả');
  $('#dateTxt').text('Tất cả');
}

//search options of select using select2 lib by call rest api
function inputSearchBySelect2(inputId, api, placeHolder) {
  $('#'+inputId).select2({
    width: '20%',
    ajax: {
      url: ctx + api,
      dataType: 'json',
      type: "POST",
      data: function (params) {
        var query = {
          search: params.term,
          type: 'public'
        }

        // Query parameters will be ?search=[term]&type=public
        return query;
      },
      // Additional AJAX parameters go here; see the end of this chapter for the full code of this example
      processResults: function (data) {
        // Transforms the top-level key of the response object from 'items' to 'results'
        var retVal = [];
        for (var i = 0; i < data.length; i++) {
          var lineObj = {
            id: data[i].name,
            text: data[i].name
          }
          retVal.push(lineObj);
        }
        return {
          // results: data.items
          results: retVal

        };
      }
    },
    placeholder: placeHolder,
    minimumInputLength: 3,
    language: {
      inputTooShort: function () {
        return 'Nhập bằng hoặc nhiều hơn 3 kí tự';
      }
    }
  });
}

//require when using daterangepicker
Date.prototype.hmdmy = function () {
  var hh = this.getHours();
  var minute = this.getMinutes();
  var seconds = this.getSeconds();
  var dd = this.getDate();
  var mm = this.getMonth() + 1;

  return (hh > 9 ? '' : '0') + hh + ":" + (minute > 9 ? '' : '0') + minute + ":" + (seconds > 9 ? '' : '0') + seconds + "  " + (dd > 9
    ? ''
    : '0') + dd + "-" + (mm > 9 ? '' : '0') + mm + "-"
    + this.getFullYear();
};

Date.prototype.dmy = function () {
  var dd = this.getDate();
  var mm = this.getMonth() + 1;

  return (dd > 9 ? '' : '0') + dd + "-" + (mm > 9 ? '' : '0') + mm + "-" + this.getFullYear();
};

//preview image when upload file
/* How to use
input: <input type="file" id="input"/>
imgPreview: <img src="#" id="imgPreview" alt=""/>
  $("#input").change(function () {
    previewImageUpload(this, "#imgPreview");
  });
*/
function previewImageUpload(input, imgPreview) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
      $(imgPreview).attr('src', e.target.result);
    }
    reader.readAsDataURL(input.files[0]);
  }
}


