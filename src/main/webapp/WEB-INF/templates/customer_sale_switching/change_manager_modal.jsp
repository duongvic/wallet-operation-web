<%@ page
    import="static vn.mog.ewallet.operation.web.controller.customer.sale_switching.manage.SaleSwitchingManageController.URI_CUSTOMER_SALE_SWITCHING_MANAGE" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.customer.sale_switching.manage.SaleSwitchingManageController.URI_CUSTOMER_SALE_SWITCHING_MANAGE_LIST" %><%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 5/8/2020
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<button type="button" class="btn btn-primary pull-right" onclick="handleCheckAgentSize()" id="assign-agent">
  <spring:message code="label.switching.manage"/>
</button>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="exampleModalLabel">
          <spring:message code="common.btn.verify"/>&nbsp;<spring:message
            code="label.switching.manage"/>
        </h4>
      </div>

      <form action="">
        <div class="modal-body">
          <select class="js-data-example-ajax-account" name="customerId" id="customerId">
          </select>
          <div class="checkbox">
            <label><input type="checkbox" value="" id="checkbox-require"><spring:message
                code="label.confirm.switching.manage"/></label>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" style="color: #333333"
                  data-dismiss="modal"><spring:message
              code="common.btn.cancel"/></button>
          <button type="button" onclick="handleAssignSales()" class="btn btn-primary"><spring:message
              code="system.service.popup.delete.lable.yes"/></button>
        </div>
      </form>
    </div>
  </div>
</div>
<script>
  var salePhone = '';
  var ids = new Set();
  // select 2 find manager -------------------------------------------------------
  /* check if keypress is not a number */
  $(document).on('keypress', '.select2-search__field', function () {
    $(this).val($(this).val().replace(/[^\d].+/, ""));
    if ((event.which < 48 || event.which > 57)) {
      event.preventDefault();
    }
  });

  $('.js-data-example-ajax-account').select2({
    width: "100%",
    dropdownAutoWidth: true,
    ajax: {
      url: ctx + "/ajax-controller/all/account",
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
        salePhone = data.msisdn;
        // Transforms the top-level key of the response object from 'items' to 'results'
        var retVal = [];
//        for (var i = 0; i < data.length; i++) {
        var lineObj = {
          id: data.id,
          text: data.fullName,
        }
        retVal.push(lineObj);
//        }
        return {
          // results: data.items
          results: retVal

        };
      }
    },
    placeholder: '<spring:message code="label.input.phone"/>',
    minimumInputLength: 10,
    maximumInputLength: 10,
    language: {
      inputTooShort: function () {
        return 'Nhập tối thiểu 10 số';
      },
      inputTooLong: function () {
        return 'Nhập tối đa 10 số';
      }
    }
  });
  // call ajax assign sales ------------------------------------------------------
  function handleCheckAgentSize() {
    // var agentIds = Array.from(ids);
    var agentIds = [];
    $.each($("input[name='agentId']:checked"), function () {
      agentIds.push($(this).val())
    });
    // console.log(agentIds);
    if (agentIds.length === 0) {
      $.MessageBox("Bạn chưa chọn khách hàng");
      return;
    } else {
      $('#exampleModal').modal('show');
    }
  }
  function handleAssignSales(){
    var agentIds = [];
    $.each($("input[name='agentId']:checked"), function () {
      agentIds.push($(this).val())
    });
    var saleId = $("#customerId").val();
    // var agentIds = Array.from(ids);
    if($('#checkbox-require').is( ":checked" )){
      $.ajax({
        type: 'POST',
        url: "${contextPath}<%=URI_CUSTOMER_SALE_SWITCHING_MANAGE%>/switching-manage",
        data: {
          agentIds: agentIds,
          saleId: saleId
        },
        success: function (data) {
          $.MessageBox("<spring:message code="response.status.value.success"/>");
          setTimeout(function(){
            location.href = "${contextPath}<%=URI_CUSTOMER_SALE_SWITCHING_MANAGE_LIST%>?quickSearch="+salePhone;
          }, 2000);

        },
        error: function (data) {
          $.MessageBox(data.responseJSON.status.value);
        }
      })
    } else {
      $.MessageBox("<spring:message code="common.not.confirm.the.action"/>");
    }
  }

</script>
