<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 5/11/2021
  Time: 3:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page
        import="static vn.mog.ewallet.operation.web.controller.translog.TransactionLogController.TRANSACTION_CONTROLLER" %>
<%@ include file="../include_page/taglibs.jsp" %>
<div class="modal fade" id="napBill" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">
                    <spring:message code="topup.bill.payment"/>
                </h4>
            </div>
            <div class="modal-body">
                <form id="myFormBill">
                    <div class="form-group">
                        <label>
                            <spring:message code="transaction.api.detail.tran-id"/>
                        </label>
                        <input type="hidden" id="txIdInputBill">
                        <label id="txIdBill"></label><br>
                        <label>traceNo: </label>
                        <label id="traceNoBill"></label><br>
                        <%--<label><spring:message code="dashboard.telco"/>&nbsp(<spring:message--%>
                                <%--code="daterange.locale.customRangeLabel"/>)</label>--%>

                        <%--<select id="telco-typeBill" class="form-control">--%>
                            <%--<option value=""><spring:message--%>
                                    <%--code="label.please.select"/></option>--%>
                            <%--<c:forEach var="telco" items="${telcoTypes}">--%>
                                <%--<option value="${telco.code}">${telco.name}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>

                        <label><spring:message code="menu.left.provider"/>&nbsp(<spring:message
                                code="daterange.locale.customRangeLabel"/>):</label>
                        <select id="providerBill" class="form-control" tabindex="-1">
                            <option value=""><spring:message
                                    code="setting.account.privilege.select.label"/>
                            </option>
                            <c:forEach var="provider" items="${providerBillPaymentOnHolds}">
                                <option value="${provider}">${ewallet:getProviderBizCode(provider)}</option>
                            </c:forEach>
                        </select>
                        <label><spring:message code="service.exportcard.create.note"/></label>
                        <textarea class="form-control" id="note-contentBill"></textarea>
                        <input type="checkbox" id="topup-confirmBill">
                        <label><spring:message code="label.topup.confirm.label"/></label>
                        <div class="fr" style="margin-top: 50px">
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                <spring:message code="system.service.popup.create.lable.cancel"/>
                            </button>
                            <button type="button" name="action" id="actionConfirmBill" class="btn btn-primary" onclick="confirmBill()">
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
    function openModalBill(txId, traceNo) {
        $('#txIdBill').text(txId);
        $('#txIdInputBill').val(txId);
        $('#traceNoBill').text(traceNo);
        document.getElementById("myFormBill").reset();
        $('#napBill').modal('show');
    }

    function confirmBill() {
        if ($('#topup-confirmBill').is(':checked')) {
            handleClickBill();
            var btn = $('#actionConfirmBill');
            btn.button('loading')
            setTimeout(function () {
                btn.button('reset')
            }, 3000)
        } else {
            $.MessageBox("<spring:message code="common.not.confirm.the.action"/>")
        }
    }

    function handleClickBill() {
        var provider = $("select#providerBill option:checked").val();
        $.ajax({
            method: 'POST',
            url: "${contextPath}<%=TRANSACTION_CONTROLLER%>/get-bill-payment-transaction-on-hold",
            data: {
                txId: $('#txIdInputBill').val(),
                provider: provider,
                noteContent: $('#note-contentBill').val(),
            },
            success: function (data) {
                $.MessageBox("Success");

                setTimeout(function () {
                    location.reload(true);
                }, 1000);
            },
            error: function (data) {
                if (data.responseJSON){
                    if (data.responseJSON.status)
                        $.MessageBox(data.responseJSON.status.value);
                    else if (data.responseJSON.message)
                        $.MessageBox(data.responseJSON.message);
                } else $.MessageBox("Failed!!!");

                setTimeout(function () {
                    location.reload(true);
                }, 1000);
            }
        })
    }
</script>
