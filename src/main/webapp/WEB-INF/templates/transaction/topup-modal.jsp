<%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 3/27/2020
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.translog.TransactionLogController.TRANSACTION_CONTROLLER" %>
<%@ include file="../include_page/taglibs.jsp" %>
<div class="modal fade" id="napDTKH" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title">
          <spring:message code="topup.fundin.phone"/>
        </h4>
      </div>
      <div class="modal-body">
        <form id="myForm">
          <div class="form-group">
            <label>
              <spring:message code="transaction.api.detail.tran-id"/>
            </label>
            <input type="hidden" id="txIdInput">
            <label id="txId"></label><br>
            <label>traceNo: </label>
            <label id="traceNo"></label><br>
            <label><spring:message code="dashboard.telco"/>&nbsp(<spring:message
                code="daterange.locale.customRangeLabel"/>)</label>

            <select id="telco-type" class="form-control">
              <option value=""><spring:message
                  code="label.please.select"/></option>
              <c:forEach var="telco" items="${telcoTypes}">
                <option value="${telco.code}">${telco.name}</option>
              </c:forEach>
            </select>

            <label><spring:message code="menu.left.provider"/>&nbsp(<spring:message
                code="daterange.locale.customRangeLabel"/>):</label>
            <select id="provider" class="form-control" tabindex="-1">
              <option value=""><spring:message
                  code="setting.account.privilege.select.label"/>
              </option>
              <c:forEach var="provider" items="${providers}">
                <option value="${provider}">${ewallet:getProviderBizCode(provider)}</option>
              </c:forEach>
            </select>
            <label><spring:message code="service.exportcard.create.note"/></label>
            <textarea class="form-control" id="note-content"></textarea>
            <input type="checkbox" id="topup-confirm">
            <label><spring:message code="label.topup.confirm.label"/></label>
            <div class="fr" style="margin-top: 50px">
              <button type="button" class="btn btn-default" data-dismiss="modal">
                <spring:message code="system.service.popup.create.lable.cancel"/>
              </button>
              <button type="button" name="action" id="actionConfirm" class="btn btn-primary" onclick="confirmTopup()">
                <spring:message code="common.btn.action"/>
              </button>
            </div>
          </div>
        </form>
      </div>

    </div>
  </div>
</div>
<script>
  function openModal(txId, traceNo) {
    $('#txId').text(txId);
    $('#txIdInput').val(txId);
    $('#traceNo').text(traceNo);
    document.getElementById("myForm").reset();
    $('#napDTKH').modal('show');
  }

  function confirmTopup() {
    if ($('#topup-confirm').is(':checked')) {
      handleClick();
      var btn = $('#actionConfirm');
      btn.button('loading')
      setTimeout(function () {
        btn.button('reset')
      }, 3000)
    } else {
      $.MessageBox("<spring:message code="common.not.confirm.the.action"/>")
    }
  }

  function handleClick() {
    var provider = $("select#provider option:checked").val();
    var telcoType = $("select#telco-type option:checked").val();
    $.ajax({
      method: 'POST',
      url: "${contextPath}<%=TRANSACTION_CONTROLLER%>/get-phone-topup-transaction-on-hold",
      data: {
        txId: $('#txIdInput').val(),
        provider: provider,
        noteContent: $('#note-content').val(),
        telcoType: telcoType
      },
      success: function (data) {
        $.MessageBox("Success");

        setTimeout(function () {
          location.reload(true);
        }, 1000);
      },
      error: function (data) {
        if (data.responseJSON)
          if (data.responseJSON.status)
            $.MessageBox(data.responseJSON.status.value);
          else $.MessageBox(data.responseJSON.message);

        setTimeout(function () {
          location.reload(true);
        }, 1000);
      }
    })
  }
</script>
