<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">

  $(document).ready(function () {
    $('#table').treeTable({
      startCollapsed: false
    });

    $('<span style="color: #428bca;" class="icon node-icon glyphicon glyphicon-bookmark"></span>').insertAfter(".treetable-expander");
    $('.switch input[name=switch]').each(function () {
      var item = document.querySelector('#' + $(this).attr('id'));
      var color = '#64bd63';
      var switchery = new Switchery(item, {color: color, size: 'small'});
      if ($(this).disabled) {
        switchery.disable();
      }
    });
  });

  function createParentServiceCode(parentServiceId, serviceType, serviceLevel, serviceCode) {
    $('#parentServiceCodeEdit').html('refresh', true);
    var urlParentServicdCodeEdit = ctx + '/service/service-profile/listFeeEdit';
    $.ajax({
      type: 'POST',
      url: urlParentServicdCodeEdit,
      data: {
        parentServiceId: parentServiceId, serviceType: serviceType, serviceLevel: serviceLevel, serviceCode: serviceCode
      },
      success: function (data) {
        var listData = data.split(';');
        if (listData != null && listData != "" && listData instanceof Array) {
          var trueServices = JSON.parse(listData[0]);

          function formatResult(node) {
            var $result = $('<span style="padding-left:' + (20 * node.level) + 'px;">'
                + '<span style="color: #428bca;" class="icon node-icon glyphicon glyphicon-bookmark"></span>'
                + '&nbsp;' + node.text + ' - ' + node.code + '</span>');
            return $result;
          }

          $("#parentServiceCodeEdit").select2({
            dropdownParent: $("#edit"),
            placeholder: '<spring:message code="system.service.popup.create.lable.selectParentServiceCode"/>',
            width: "100%",
            data: trueServices,
            formatSelection: function (item) {
              return item.text + ' - ' + item.code
            },
            formatResult: function (item) {
              return item.id
            },
            templateResult: formatResult
          });
          $("#select2-parentServiceCodeEdit-container").html(listData[1]);
          $("#parentServiceCodeEdit").val(listData[2]);
        }
      }
    });
  }

  function openFeeTab(paramValue) {
    var searchURL = '';
    if (window.location.search.indexOf("?") >= 0) {
      if (window.location.search.indexOf("customerTypeId") >= 0) {
        searchURL = window.location.pathname + replaceUrlParam(window.location.search, 'customerTypeId', paramValue);
      } else {
        searchURL = window.location.pathname + window.location.search + '&customerTypeId=' + paramValue;
      }

    } else {
      searchURL = window.location.pathname + '?customerTypeId=' + paramValue;
    }
    window.location.href = searchURL;
  }
</script>