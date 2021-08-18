<%@ page
        import="static vn.mog.ewallet.operation.web.controller.service.TrueServiceController.SESSION_SERVICE_TYPE" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<%
  String vServiceType = (String) request.getSession().getAttribute(SESSION_SERVICE_TYPE);
  if (StringUtils.isBlank(vServiceType)) {
    vServiceType = "FUND_IN";
  }
%>
<c:set var="vServiceType" value="<%=vServiceType%>"/>

<div class="modal fade" id="add-commission-fee-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content" style="width: 670px;">
      <form id="commission-fee-add" action="${param.actionURI}" method="post" >
        <input type="hidden" name="customerId"/>
        <input type="hidden" name="customerTypeId" value="${param.customerTypeId}"/>
        <input type="hidden" name="serviceId"/>

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
              <label id="serviceTypeAddLabel">
                ${vServiceType}
              </label>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.update.lable.serviceCode"/>:</label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <label id="serviceCodeAddLabel">
              </label>
            </div>
          </div>
          <c:if test="${null ne param.customerTypeId && '' ne param.customerTypeId}">
            <div class="form-group">
              <label class="col-md-3 control-label"><spring:message code="system.service.popup.update.lable.customerType"/>:</label>
              <div class="col-md-8 input-group" style=" width: 72.5%; ">
                <label id="customerTypeAddLabel"><spring:message code="label.customerType.${param.customerTypeId}"/></label>
              </div>
            </div>
          </c:if>

          <div class="form-group">
            <label class="col-md-3 control-label">Commission:&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" id="commissionAddInput" name="commissionInput" class="form-control" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Fee:&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" id="feeAddInput" name="feeInput" class="form-control" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Commission fixed amount:&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" id="commissionFixedAmountAddInput" name="commissionFixedAmountInput" class="form-control" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label">Fee fixed amount:&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" id="feeFixedAmountAddInput" name="feeFixedAmountInput" class="form-control" required>
            </div>
          </div>

          <div id="currentCommissionFee" class="hidden">

            <div class="form-group">
              <label class="col-md-12 control-label"><span style="color: red"><spring:message code="system.commission.fee.popup.update.curent.exist.label"/></span></label>
            </div>
            <div class="form-group">
              <label class="col-md-3 control-label"><span style="color: red">Current</span> Commission:</label>
              <div class="col-md-8 input-group" style=" width: 72.5%; ">
                <input type="text" id="commissionCurrent" class="form-control" disabled>
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-3 control-label"><span style="color: red">Current</span> Fee:</label>
              <div class="col-md-8 input-group" style=" width: 72.5%; ">
                <input type="text" id="feeCurrent" class="form-control" disabled>
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-3 control-label"><span style="color: red">Current</span> Commission fixed amount:</label>
              <div class="col-md-8 input-group" style=" width: 72.5%; ">
                <input type="text" id="commissionFixedAmountCurrent" class="form-control" disabled>
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-3 control-label"><span style="color: red">Current</span> Fee fixed amount:</label>
              <div class="col-md-8 input-group" style=" width: 72.5%; ">
                <input type="text" id="feeFixedAmountCurrent" class="form-control" disabled>
              </div>
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default " data-dismiss="modal">
            <spring:message code="system.service.popup.update.lable.cancel"/>
          </button>
          <button id="submitAdd" type="button" class="btn btn-primary btnSubmit" onclick="submitCommissionFeeAdd()">
            <spring:message code="system.service.popup.update.lable.submit"/>
          </button>
        </div>
          <sec:csrfInput />
      </form>

      <form id="getServices" action="/service/service-profile/ajax/list" method="POST">
        <input type="hidden" name="serviceTypeIds" value="${vServiceType}"/>
        <sec:csrfInput />
      </form>

      <form id="getCommission" action="/customer/commission-fee/get" method="POST">
        <input type="hidden" id="serviceIdGet" name="serviceId"/>
        <input type="hidden" id="customerTypeIdGet" name="customerTypeId" value="${param.customerTypeId}"/>
        <sec:csrfInput />
      </form>

    </div>
  </div>
</div>


<script type="text/javascript">
  $(document).ready(function () {
    onSubmitAdd();
  });

  function addCommissionFeeModal() {
    var commissionInput = $("#commissionAddInput");
    var feeInput = $("#feeAddInput");
    var commissionFixedAmountInput = $("#commissionFixedAmountAddInput");
    var feeFixedAmountInput = $("#feeFixedAmountAddInput");

    commissionInput.attr("disabled", false);
    feeInput.attr("disabled", false);
    commissionFixedAmountInput.attr("disabled", false);
    feeFixedAmountInput.attr("disabled", false);

    $("#submitAdd").attr("disabled", false);
    $("#currentCommissionFee").addClass("hidden");

    showServiceList();

    $('#add-commission-fee-modal').modal('toggle');
  }

  function submitCommissionFeeAdd() {
    $('#commission-fee-add').submit();
  }

  function showServiceList() {
    var getServiceForm = $("#getServices");
    var serviceCodeLabel = $("#serviceCodeAddLabel");
    $.post(getServiceForm.attr('action'), getServiceForm.serialize(), function (json) {
      if (json.status && json.status.code == 0) {
        var options = "                <select name=\"serviceIds\" id=\"serviceIds\" class=\"form-control\" onchange='onSelectService()'>\n"
            + "<option value=''>".concat("N/A").concat("</option>\n");;
        for (var i = 0; i < json.services.length; ++i) {
          var item = json.services[i];
          options += ""
              + "<option value=\"".concat(item.id).concat("\">").concat(item.serviceCode).concat(" - ").concat(item.serviceName).concat("</option>\n");
        }
        options += "</select>";
        serviceCodeLabel.html(options);
      } else {
        if (json.status.value) {
          serviceCodeLabel.html(json.status.value);
        } else {
          serviceCodeLabel.html(json.message);
        }
      }
    });
  }

  function onSubmitAdd() {
    $('#commission-fee-add').submit(function () {
      $(".btnSubmit").button('loading');
      $.post($(this).attr('action'), $(this).serialize(), function (json) {
        $.MessageBox({
          message: json.message
        }).done(function () {
          if (json.code == 0) {
            $('#add-commission-fee-modal').modal('toggle');
            location.reload();
          } else {
            $(".btnSubmit").button('reset');
          }
        });
      });
      return false;
    });
  }

  function onSelectService() {
    var getCommissionForm = $("#getCommission");
    $("[name=serviceId]").val($('#serviceIds').val());
    $.post(getCommissionForm.attr('action'), getCommissionForm.serialize(), function (json) {
      if (json.status && json.status.code == 0) {
        if (json.commissionFee && json.commissionFee != null && json.commissionFee.commissionFeeValues && json.commissionFee.commissionFeeValues != null) {
          $("#commissionCurrent").val(json.commissionFee.commissionFeeValues.commission);
          $("#commissionFixedAmountCurrent").val(json.commissionFee.commissionFeeValues.commissionFixedAmount);
          $("#feeCurrent").val(json.commissionFee.commissionFeeValues.fee);
          $("#feeFixedAmountCurrent").val(json.commissionFee.commissionFeeValues.feeFixedAmount);
          $("#currentCommissionFee").removeClass("hidden");
        } else {
          $("#currentCommissionFee").addClass("hidden");
        }
      } else {
        $("#currentCommissionFee").addClass("hidden");
//        if (json.status.value) {
//          serviceCodeLabel.html(json.status.value);
//        } else {
//          serviceCodeLabel.html(json.message);
//        }
      }
    });
  }
</script>

