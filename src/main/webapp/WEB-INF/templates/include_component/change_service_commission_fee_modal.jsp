<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>


<div class="modal fade" id="change-commission-fee-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content" style="width: 670px;">
      <form id="commission-fee-edit" action="${param.actionURI}" method="post" >
        <input type="hidden" name="customerId"/>
        <input type="hidden" name="customerTypeId" value="${param.customerTypeId}"/>
        <input type="hidden" name="serviceId"/>
        <input type="hidden" name="paymentChannelId"/>

        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="popup.header.icon.close"/></span>
          </button>
          <h4 class="modal-title" id="myModalLabel">${param.title}: <span id="nameService"/></h4>
        </div>

        <div class="modal-body">
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.serviceType"/>:</label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <label id="serviceTypeLabel"></label>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.update.lable.serviceCode"/>:</label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <label id="serviceCodeLabel"></label>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.update.lable.serviceName"/>:</label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <label id="serviceNameLabel"></label>
            </div>
          </div>
          <c:if test="${null ne param.customerTypeId && '' ne param.customerTypeId}">
            <div class="form-group">
              <label class="col-md-3 control-label"><spring:message code="system.service.popup.update.lable.customerType"/>:</label>
              <div class="col-md-8 input-group" style=" width: 72.5%; ">
                <label id="customerTypeLabel"><spring:message code="label.customerType.${param.customerTypeId}"/></label>
              </div>
            </div>
          </c:if>

          <div class="form-group">
            <label class="col-md-3 control-label">Commission:&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" id="commissionInput" name="commissionInput" class="form-control" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Fee:&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" id="feeInput" name="feeInput" class="form-control" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Commission fixed amount:&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" id="commissionFixedAmountInput" name="commissionFixedAmountInput" class="form-control" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Fee fixed amount:&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" id="feeFixedAmountInput" name="feeFixedAmountInput" class="form-control" required>
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default " data-dismiss="modal">
            <spring:message code="system.service.popup.update.lable.cancel"/>
          </button>
          <button id="submitEdit" type="button" class="btn btn-primary btnSubmit" onclick="submitCommissionFeeEdit()">
            <spring:message code="system.service.popup.update.lable.submit"/>
          </button>
        </div>
          <sec:csrfInput />
      </form>
    </div>
  </div>
</div>


<script type="text/javascript">
  function editCommissionFeeModal(element) {
    var serviceType = $(element).attr("serviceType");
    var serviceCode = $(element).attr("serviceCode");
    var serviceName = $(element).attr("serviceName");
    var paymentChannelId = $(element).attr("paymentChannelId");

    $("#serviceTypeLabel").html(serviceType);
    $("#serviceCodeLabel").html(serviceCode);
    $("#serviceNameLabel").html(serviceName);

    var customerId = $(element).attr("customerId");
    var serviceId = $(element).attr("serviceId");
    $("[name=customerId]").val(customerId);
    $("[name=serviceId]").val(serviceId);
    $("[name=paymentChannelId]").val(paymentChannelId);

    var commission = $(element).attr("commission");
    var fee = $(element).attr("fee");
    var commissionFixedAmount = $(element).attr("commissionFixedAmount");
    var feeFixedAmount = $(element).attr("feeFixedAmount");

    var commissionInput = $("#commissionInput");
    var feeInput = $("#feeInput");
    var commissionFixedAmountInput = $("#commissionFixedAmountInput");
    var feeFixedAmountInput = $("#feeFixedAmountInput");

    commissionInput.val(commission);
    feeInput.val(fee);
    commissionFixedAmountInput.val(commissionFixedAmount);
    feeFixedAmountInput.val(feeFixedAmount);

    commissionInput.attr("disabled", false);
    feeInput.attr("disabled", false);
    commissionFixedAmountInput.attr("disabled", false);
    feeFixedAmountInput.attr("disabled", false);

    $("#submitEdit").attr("disabled", false);

    $('#change-commission-fee-modal').modal('toggle');
  }

  function submitCommissionFeeEdit() {
    $('#commission-fee-edit').submit();
  }

  $(document).ready(function () {
    $('#commission-fee-edit').submit(function () {
      $(".btnSubmit").button('loading');
      $.post($(this).attr('action'), $(this).serialize(), function (json) {
        $.MessageBox({
          message: json.message
        }).done(function () {
          if (json.code == 0) {
            $('#change-commission-fee-modal').modal('toggle');
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

