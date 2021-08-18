<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>


<div class="modal fade" id="reset-commission-fee-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content" style="width: 670px;">
      <form id="commission-fee-reset" action="${param.actionURI}" method="post" >
        <input type="hidden" name="customerId"/>
        <input type="hidden" name="customerTypeId" value="${param.customerTypeId}"/>
        <input type="hidden" name="serviceId"/>

        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="popup.header.icon.close"/></span>
          </button>
          <h4 class="modal-title" id="myModalLabel">${param.title}<span id="nameService"/></h4>
        </div>

        <div class="modal-body">
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.serviceType"/>:</label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <label id="serviceTypeResetLabel"></label>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.update.lable.serviceCode"/>:</label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <label id="serviceCodeResetLabel"></label>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.update.lable.serviceName"/>:</label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <label id="serviceNameResetLabel"></label>
            </div>
          </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default " data-dismiss="modal">
            <spring:message code="button.no"/>
          </button>
          <button id="submitReset" type="button" class="btn btn-primary btnSubmit" onclick="submitCommissionFeeReset()">
            <spring:message code="button.yes"/>
          </button>
        </div>
          <sec:csrfInput />
      </form>
    </div>
  </div>
</div>


<script type="text/javascript">
  function resetCommissionFeeModal(element) {
    var serviceType = $(element).attr("serviceType");
    var serviceCode = $(element).attr("serviceCode");
    var serviceName = $(element).attr("serviceName");

    $("#serviceTypeResetLabel").html(serviceType);
    $("#serviceCodeResetLabel").html(serviceCode);
    $("#serviceNameResetLabel").html(serviceName);

    var customerId = $(element).attr("customerId");
    var serviceId = $(element).attr("serviceId");
    $("[name=customerId]").val(customerId);
    $("[name=serviceId]").val(serviceId);

    $("#submitReset").attr("disabled", false);

    $('#reset-commission-fee-modal').modal('toggle');
  }

  function submitCommissionFeeReset() {
    $('#commission-fee-reset').submit();
  }

  $(document).ready(function () {
    $('#commission-fee-reset').submit(function () {
      $(".btnSubmit").button('loading');
      $.post($(this).attr('action'), $(this).serialize(), function (json) {
        $.MessageBox({
          message: json.message
        }).done(function () {
          if (json.code == 0) {
            $('#reset-commission-fee-modal').modal('toggle');
            location.reload();
          } else {
            $(".btnSubmit").button('reset');
          }
        });
      });
      return false;
    });
  });
</script>

