<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 11/6/2020
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../include_page/taglibs.jsp" %>
<style>
    #topup-tbl th {
        background: white;
        position: sticky;
        top: 0; /* Don't forget this, required for the stickiness */
        box-shadow: 0 2px 2px -1px rgba(0, 0, 0, 0.4);
    }

    .my-custom-scrollbar {
        position: relative;
        height: 200px;
        overflow: auto;
    }

    .table-wrapper-scroll-y {
        display: block;
    }
</style>
<%--modal 1--%>
<div class="modal fade" id="txnBatch" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">
                    <spring:message code="label.do.txn.by.batch.again"/>
                </h4>
            </div>
            <div class="modal-body">
                <div role="tabpanel">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active">
                            <a href="#phoneTopupTab" aria-controls="uploadTab" role="tab" data-toggle="tab">
                                <spring:message code="label.phone.topup.batch"/>
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="#billPaymentTab" aria-controls="browseTab" role="tab" data-toggle="tab">
                                <spring:message code="label.bill.payment.batch"/>
                            </a>
                        </li>
                    </ul>
                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="phoneTopupTab">
                            <form id="phoneTopupBatch">
                                <div class="form-group">
                                    <input type="file" name="my-file" id="input">
                                    <a target="_blank" href="${contextPath}/assets/template/Topup-nap-lo.xlsx" class="link-active">Dowload template file</a><br>
                                    <%--NCC--%>
                                    <label><spring:message code="menu.left.provider"/>&nbsp</label>
                                    <select id="provider-form-batch" class="form-control" tabindex="-1">
                                        <option value=""><spring:message
                                                code="setting.account.privilege.select.label"/>
                                        </option>
                                        <c:forEach var="provider" items="${providers}">
                                            <option value="${provider}">${ewallet:getProviderBizCode(provider)}</option>
                                        </c:forEach>
                                    </select>

                                    <div class="fr" style="margin-top: 50px">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">
                                            <spring:message code="system.service.popup.create.lable.cancel"/>
                                        </button>
                                        <button type="button" name="action" id="actionNext" class="btn btn-primary">
                                            <spring:message code="common.btn.next"/>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="billPaymentTab">
                            <form id="billPaymentBatch">
                                <div class="form-group">
                                    <input type="file" name="my-file" id="inputBill">
                                    <a target="_blank" href="${contextPath}/assets/template/Bill-nap-lo.xlsx" class="link-active">Dowload template file</a><br>
                                    <%--NCC--%>
                                    <label><spring:message code="menu.left.provider"/>&nbsp</label>
                                    <select id="provider-bill" class="form-control" tabindex="-1">
                                        <option value=""><spring:message
                                                code="setting.account.privilege.select.label"/></option>
                                        <option value="BILL_ELECTRIC_VIETTELPAY">BILL_ELECTRIC_VIETTELPAY</option>
                                        <option value="BILL_OTHER_VIETTELPAY">BILL_OTHER_VIETTELPAY</option>
                                        <option value="BILL_WATER_VIETTELPAY">BILL_WATER_VIETTELPAY</option>
                                        <option value="BILL_FINANCE_VIETTELPAY">BILL_FINANCE_VIETTELPAY</option>
                                        <option value="BILL_ELECTRIC_VIMO">BILL_ELECTRIC_VIMO</option>
                                        <option value="BILL_BYHAND">BILL_BYHAND</option>
                                    </select>

                                    <div class="fr" style="margin-top: 50px">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">
                                            <spring:message code="system.service.popup.create.lable.cancel"/>
                                        </button>
                                        <button type="button" name="action" id="actionNextBill" class="btn btn-primary">
                                            <spring:message code="common.btn.next"/>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--modal 2--%>
<div class="modal fade" id="napDTKHBatchStep2" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">
                    <spring:message code="label.phone.topup.batch"/>
                </h4>
            </div>
            <div class="modal-body">
                <form id="phoneTopupBatchStep2">
                    <div class="form-group">
                        <div class="table-wrapper-scroll-y my-custom-scrollbar">
                            <table class="table table-bordered" id="topup-tbl">
                                <thead>
                                <tr></tr>
                                </thead>
                                <tbody class="mt-xs"></tbody>
                            </table>
                        </div>
                        <label><spring:message code="menu.left.provider"/>:&nbsp;</label><span
                            id="select-provider"></span><br>

                        <label><spring:message code="service.exportcard.create.note"/></label>
                        <textarea class="form-control" id="note-content-batch"></textarea>
                        <input type="checkbox" id="topup-confirm-batch">
                        <label><spring:message code="label.topup.confirm.label"/></label>
                        <div class="fr" style="margin-top: 50px">
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                <spring:message code="system.service.popup.create.lable.cancel"/>
                            </button>
                            <button type="button" class="btn btn-success" id="btn-back" data-dismiss="modal">
                                <spring:message code="common.btn.back"/>
                            </button>
                            <button type="button" name="action" id="actionSubmit" onclick="confirmTopupBatch()"
                                    class="btn btn-primary">
                                <spring:message code="common.btn.action"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%--modal 3--%>
<div class="modal fade" id="napBillPaymentBatchStep2" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">
                    <spring:message code="label.bill.payment.batch"/>
                </h4>
            </div>
            <div class="modal-body">
                <form id="billPaymentBatchStep2">
                    <div class="form-group">
                        <div class="table-wrapper-scroll-y my-custom-scrollbar">
                            <table class="table table-bordered" id="bill-tbl">
                                <thead>
                                <tr></tr>
                                </thead>
                                <tbody class="mt-xs"></tbody>
                            </table>
                        </div>
                        <label><spring:message code="menu.left.provider"/>:&nbsp;</label><span
                            id="select-provider-bill"></span><br>

                        <label><spring:message code="service.exportcard.create.note"/></label>
                        <textarea class="form-control" id="note-content-batch-bill"></textarea>
                        <input type="checkbox" id="bill-confirm-batch">
                        <label><spring:message code="label.topup.confirm.label"/></label>
                        <div class="fr" style="margin-top: 50px">
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                <spring:message code="system.service.popup.create.lable.cancel"/>
                            </button>
                            <button type="button" class="btn btn-success" id="btn-back-bill" data-dismiss="modal">
                                <spring:message code="common.btn.back"/>
                            </button>
                            <button type="button" name="action" id="actionSubmitBill" onclick="confirmBillBatch()"
                                    class="btn btn-primary">
                                <spring:message code="common.btn.action"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%--https://gitlab.com/catamphetamine/read-excel-file#readme--%>
<script src="<c:url value="/assets/javascripts/read-excel-file.min.js"/>"></script>
<script>
    var theadHtml = [];
    var tbodyHtml = [];
    $("#btn-batch").click(function () {
        document.getElementById("phoneTopupBatch").reset();
        document.getElementById("phoneTopupBatchStep2").reset();
        document.getElementById("billPaymentBatch").reset();
        document.getElementById("billPaymentBatchStep2").reset();
        $('#txnBatch').modal('show');
    });

    $("#btn-back").click(function () {
        $('#txnBatch').modal('show');
        $('#napDTKHBatchStep2').modal('hide');
    });

    $("#actionNext").click(function () {
        if(theadHtml.length && tbodyHtml.length) {
        } else {
            $.MessageBox({message: "Vui lòng chọn file!"});
            return;
        }

        if($("select#provider-form-batch option:checked").val()) {
        } else {
            $.MessageBox({message: "Vui lòng chọn nhà cung cấp!"});
            return;
        }

        $('#txnBatch').modal('hide');
        $('#napDTKHBatchStep2').modal('show');

        var theadContent = "";
        for (var i = 0; i < theadHtml.length; i++) {
            theadContent += '<th>' + theadHtml[i] + '</th>';
        }
        $("#topup-tbl thead tr").html(theadContent);

        var tbodyContent = "";
        for (var i = 0; i < tbodyHtml.length; i++) {
            tbodyContent += '<tr><td>' + tbodyHtml[i][0] + '</td><td>' + tbodyHtml[i][1] + '</td><td>' + tbodyHtml[i][2] + '</td><td>' + tbodyHtml[i][3] + '</td><td>' + tbodyHtml[i][4] + '</td><td>' + tbodyHtml[i][5] + '</td></tr>'
        }
        $("#topup-tbl tbody").html(tbodyContent);

        $("#select-provider").html('<strong>' + $("select#provider-form-batch option:checked").val() + '</strong>');
    });

    var input = document.getElementById('input')
    input.onchange = function () {
        readXlsxFile(input.files[0]).then(function (data) {
            theadHtml = data[0];
            tbodyHtml = data.slice(1, data.length + 1);
            // `rows` is an array of rows
            // each row being an array of cells.
        }, reason => {
            console.log(reason); // Error!
            $.MessageBox({message: "Lỗi: File không đúng định dạng!!!"});
            document.getElementById('input').value = null;
        })
    };

    function confirmTopupBatch() {
        if ($('#topup-confirm-batch').is(':checked')) {
            handleSubmit();
        } else {
            $.MessageBox("<spring:message code="common.not.confirm.the.action"/>")
        }
    }

    function handleSubmit() {
        var provider = $("select#provider-form-batch option:checked").val();
        var tracenoIds = tbodyHtml.map(e => e[1]);
        var note = $("#note-content-batch").val();
        var btn = $('#actionSubmit');
        btn.button('loading')
        $.ajax({
            method: 'POST',
            url: "${contextPath}<%=TRANSACTION_CONTROLLER%>/get-phone-topup-transaction-on-hold-by-batch",
            data: {
                tracenoIds: tracenoIds,
                provider: provider,
                noteContent: note,
            },
            success: function (data) {
                var arr = Object.entries(data.results);
                var resultTrue = arr.filter(element => element[1] === true).length;
                var resultFalse = arr.filter(element => element[1] === false).length;
                var html = "Số giao dịch thành công: " + resultTrue + "<br><br>";
                html += "Số giao dịch thất bại: " + resultFalse + "<br><br>"
                $.MessageBox(html);
                btn.button('reset');
            },
            error: function (data) {
                if (data.responseJSON) {
                    if (data.responseJSON.status)
                        $.MessageBox(data.responseJSON.status.value);
                    else $.MessageBox(data.responseJSON.message);
                }
                btn.button('reset');
            }
        })

    }
    /////////////////////////////////////////////////////////////////
    var theadHtmlBill = [];
    var tbodyHtmlBill = [];

    $("#btn-back-bill").click(function () {
        $('#txnBatch').modal('show');
        $('#napBillPaymentBatchStep2').modal('hide');
    })

    $("#actionNextBill").click(function () {
        if(theadHtmlBill.length && tbodyHtmlBill.length) {
        } else {
            $.MessageBox({message: "Vui lòng chọn file!"});
            return;
        }

        if($("select#provider-bill option:checked").val()) {
        } else {
            $.MessageBox({message: "Vui lòng chọn nhà cung cấp!"});
            return;
        }

        $('#txnBatch').modal('hide');
        $('#napBillPaymentBatchStep2').modal('show');

        var theadContent = "";
        for (var i = 0; i < theadHtmlBill.length; i++) {
            theadContent += '<th>' + theadHtmlBill[i] + '</th>';
        }
        $("#bill-tbl thead tr").html(theadContent);

        var tbodyContent = "";
        for (var i = 0; i < tbodyHtmlBill.length; i++) {
            tbodyContent += '<tr><td>' + tbodyHtmlBill[i][0] + '</td><td>' + tbodyHtmlBill[i][1] + '</td><td>' + tbodyHtmlBill[i][2] + '</td><td>' + tbodyHtmlBill[i][3] + '</td><td>' + tbodyHtmlBill[i][4] + '</td></tr>'
        }
        $("#bill-tbl tbody").html(tbodyContent);

        $("#select-provider-bill").html('<strong>' + $("select#provider-bill option:checked").val() + '</strong>');
    });

    var inputBill = document.getElementById('inputBill')
    inputBill.onchange = function () {
        readXlsxFile(inputBill.files[0]).then(function (data) {
            theadHtmlBill = data[0];
            tbodyHtmlBill = data.slice(1, data.length + 1);
            // `rows` is an array of rows
            // each row being an array of cells.
        }, reason => {
            console.log(reason); // Error!
            $.MessageBox({message: "Lỗi: File không đúng định dạng!!!"});
            document.getElementById('inputBill').value = null;
        })
    };

    function confirmBillBatch() {
        if ($('#bill-confirm-batch').is(':checked')) {
            handleSubmitBill();
        } else {
            $.MessageBox("<spring:message code="common.not.confirm.the.action"/>")
        }
    }

    function handleSubmitBill() {
        var provider = $("select#provider-bill option:checked").val();
        var tracenoIds = tbodyHtmlBill.map(e => e[1]);
        var note = $("#note-content-batch-bill").val();
        var btn = $('#actionSubmitBill');
        btn.button('loading')
        $.ajax({
            method: 'POST',
            url: "${contextPath}<%=TRANSACTION_CONTROLLER%>/get-bill-payment-transaction-on-hold-by-batch",
            data: {
                tracenoIds: tracenoIds,
                provider: provider,
                noteContent: note,
            },
            success: function (data) {
                var arr = Object.entries(data.results);
                var resultTrue = arr.filter(element => element[1] === true).length;
                var resultFalse = arr.filter(element => element[1] === false).length;
                var html = "Số giao dịch thành công: " + resultTrue + "<br><br>";
                html += "Số giao dịch thất bại: " + resultFalse + "<br><br>"
                $.MessageBox(html);
                btn.button('reset');
            },
            error: function (data) {
                if (data.responseJSON) {
                    if (data.responseJSON.status)
                        $.MessageBox(data.responseJSON.status.value);
                    else $.MessageBox(data.responseJSON.message);
                }
                btn.button('reset');
            }
        })

    }
</script>
