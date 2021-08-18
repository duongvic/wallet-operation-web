<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../include_page/taglibs.jsp" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="panel-title">
    <h4 style="margin-bottom: 1.5rem"><spring:message code="label.info.account"/></h4>
</div>
<div class="panel-body">
    <%-- phone , password--%>
    <div class="row">
        <div class="col-md-6 col-lg-6 col-sm-12  form-group">
            <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap"><spring:message
                    code="label.phone"/>*</label>
            <div class="col-md-7 col-lg-8 col-sm-8">
                <div class="input-group" style="width: 100%">
                    <input class="form-control"
                           pattern="[0-9]{10,11}"
                           required
                           id="phone"
                           value="${account_object.accountName}"
                           type="text" name="phone"
                           placeholder="<spring:message code="label.phone"/>">
                </div>
            </div>
        </div>

        <div class="col-md-6 col-lg-6 col-sm-12  form-group">
            <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">Password *</label>
            <div class="col-md-7 col-lg-8 col-sm-8">
                <div class="input-group" style="width: 100%">
                    <input class="form-control"
                           id="password"
                    <c:if test="${'add' eq edit_type}">
                           required
                    </c:if>
                           value="${account_object.password}"
                           type="text" name="password"
                           placeholder="password">
                </div>
            </div>
        </div>


    </div>

    <%-- voucherCode, voucherCode5M--%>
    <%--    <div class="row">--%>
    <%--        <div class="form-group">--%>
    <%--            <div class="col-md-6 col-lg-6 col-sm-12  form-group">--%>
    <%--                <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">Voucher code--%>
    <%--                </label>--%>
    <%--                <div class="col-md-7 col-lg-8 col-sm-8">--%>
    <%--                    <div class="input-group" style="width: 100%">--%>
    <%--                        <input class="form-control"--%>
    <%--                               id="voucherCode"--%>
    <%--                               value="${account_object.voucherCode}"--%>
    <%--                               type="text" name="voucherCode"--%>
    <%--                               placeholder="voucherCode">--%>
    <%--                    </div>--%>
    <%--                </div>--%>
    <%--            </div>--%>

    <%--            <div class="col-md-6 col-lg-6 col-sm-12  form-group">--%>
    <%--                <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">Voucher Code5M--%>
    <%--                </label>--%>
    <%--                <div class="col-md-7 col-lg-8 col-sm-8">--%>
    <%--                    <div class="input-group" style="width: 100%">--%>
    <%--                        <input class="form-control"--%>
    <%--                               id="voucherCode5M"--%>
    <%--                               value="${account_object.voucherCode5M}"--%>
    <%--                               type="text" name="voucherCode5M"--%>
    <%--                               placeholder="voucherCode5M">--%>
    <%--                    </div>--%>
    <%--                </div>--%>
    <%--            </div>--%>

    <%--        </div>--%>
    <%--    </div>--%>

    <%--    <div class="row">--%>
    <%--        <div class="form-group">--%>
    <%--            <div class="col-md-6 col-lg-6 col-sm-12 form-group">--%>
    <%--                <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">Finance voucher code</label>--%>
    <%--                <div class="col-md-7 col-lg-8 col-sm-8">--%>
    <%--                    <input type="text" class="form-control" value="${account_object.financeVoucherCode}"--%>
    <%--                           name="financeVoucherCode" id="financeVoucherCode" placeholder="finance voucher code">--%>
    <%--                </div>--%>
    <%--            </div>--%>
    <%--            <div class="pull-right col-md-6 col-lg-6 col-sm-12 form-group">--%>
    <%--                <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">Finance voucher code 1M</label>--%>
    <%--                <div class="col-md-7 col-lg-8 col-sm-8">--%>
    <%--                    <input type="text" class="form-control" value="${account_object.financeVoucherCode5M}"--%>
    <%--                           name="financeVoucherCode5M" id="financeVoucherCode5M" placeholder="Finance voucher code 1M">--%>
    <%--                </div>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--    </div>--%>

    <%-- paymentPass , serialNumber--%>
    <div class="row ">
        <%--        <div class="col-md-6 col-lg-6 col-sm-12  form-group">--%>
        <%--            <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">Finance voucher code 500k</label>--%>
        <%--            <div class="col-md-7 col-lg-8 col-sm-8">--%>
        <%--                <div class="input-group" style="width: 100%">--%>
        <%--                    <input class="form-control"--%>
        <%--                           id="financeVoucherCode500K"--%>
        <%--                           value="${account_object.financeVoucherCode500K}"--%>
        <%--                           type="text" name="financeVoucherCode500K"--%>
        <%--                           placeholder="financeVoucherCode500K">--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>

        <div class="col-md-6 col-lg-6 col-sm-12  form-group">
            <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap"><spring:message
                    code="label.imsi"/></label>
            <div class="col-md-7 col-lg-8 col-sm-8">
                <div class="input-group" style="width: 100%">
                    <input class="form-control"
                           pattern="[0-9]{10,30}"
                           id="serialNumber"
                           value="${account_object.serialNumber}"
                           type="text" name="serialNumber"
                           placeholder="<spring:message code="login.form.username"/>">
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-6 col-sm-12  form-group">
            <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">PaymentPass</label>
            <div class="col-md-7 col-lg-8 col-sm-8">
                <div class="input-group" style="width: 100%">
                    <input class="form-control"
                           id="paymentPass"
                           value="${account_object.paymentPass}"
                           type="text" name="paymentPass"
                           placeholder="paymentPass">
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6 col-lg-6 col-sm-12 form-group">
            <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap"><spring:message
                    code="select.status"/>*</label>
            <div class="col-md-7 col-lg-8 col-sm-8">
                <select class="form-control data-plugin-selectTwo" name="isActive"
                        id="isActive">
                    <option value="0" ${account_object.status eq 0 ? "selected" : "" }>
                        <spring:message code="label.user.inactive"/></option>
                    <option value="1" ${account_object.status eq 1 ? "selected" : "" }>
                        <spring:message code="label.user.active"/></option>
                </select>
            </div>
        </div>
    </div>
</div>

<spring:message code="common.btn.add" var="addTxt"/>
<div class="pannel-title">
    <div style="display: flex; justify-content: space-between">
        <h4 style="margin-bottom: 1.5rem; color: #67a22c; align-self: center">Voucher code</h4>
        <button class="btn btn-primary mt-md mb-md fr" type="button" id="add-voucher">${addTxt}</button>
    </div>
</div>
<div class="pannel-body">
    <table class="table table-bordered" id="voucher-code">
        <thead>
        <tr>
            <th>Amount</th>
            <th>Voucher Code</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="voucherRange" items="${account_object.voucherRanges}">
            <tr id="${voucherRange.amount}-${voucherRange.voucherCode}">
                <td>
                    <input type="text" onkeypress="validCurrency(event)"
                           oninput="currencyPrice(event)"
                           name="amount-${voucherRange.amount}-${voucherRange.voucherCode}"
                           class="form-control" value="${ewallet:formatNumber(voucherRange.amount)}">
                </td>
                <td>
                    <input type="text" name="voucher-${voucherRange.amount}-${voucherRange.voucherCode}"
                           class="form-control" value="${voucherRange.voucherCode}">
                </td>
                <td>
                    <div class="center"><span
                            onclick="removeRow('${voucherRange.amount}-${voucherRange.voucherCode}')"><i
                            class="fa fa-trash" style="color: red; font-size: 1.8rem"></i></span></div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="pannel-title">
    <div style="display: flex; justify-content: space-between">
        <h4 style="margin-bottom: 1.5rem; color: #67a22c; align-self: center">Finance voucher code</h4>
        <button class="btn btn-primary mt-md mb-md fr" type="button" id="add-finance-voucher">${addTxt}</button>
    </div>
</div>
<div class="pannel-body">
    <table class="table table-bordered" id="finance-voucher-code">
        <thead>
        <tr>
            <th>Amount</th>
            <th>Voucher Code</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="financeVoucherRange" items="${account_object.financeVoucherRanges}">
            <tr id="${financeVoucherRange.amount}-${financeVoucherRange.voucherCode}">
                <td>
                    <input type="text" onkeypress="validCurrency(event)"
                           oninput="currencyPrice(event)"
                           name="finance-amount-${financeVoucherRange.amount}-${financeVoucherRange.voucherCode}"
                           class="form-control" value="${ewallet:formatNumber(financeVoucherRange.amount)}">
                </td>
                <td>
                    <input type="text"
                           name="finance-voucher-${financeVoucherRange.amount}-${financeVoucherRange.voucherCode}"
                           class="form-control" value="${financeVoucherRange.voucherCode}">
                </td>
                <td>
                    <div class="center"><span
                            onclick="removeFinanceRow('${financeVoucherRange.amount}-${financeVoucherRange.voucherCode}')"><i
                            class="fa fa-trash" style="color: red; font-size: 1.8rem"></i></span></div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<input type="hidden" name="list-voucher" id="list-voucher">
<input type="hidden" name="list-finance-voucher" id="list-finance-voucher">
<div class="row">
    <div class="form-group">
        <div class="col-sm-12 text-right">
            <button class="btn btn-sm btn-success ${'edit' eq edit_type ? '' : 'hidden'}"
                    type="button" id="edit_btn"
                    name="edit" onclick="editAccount()"
                    value="create"><i
                    class="fa fa-pencil"></i> <spring:message code="common.btn.edit"/>
            </button>

            <button class="btn btn-sm btn-primary"
                    type="submit" value="save-account-info"
                    name="btn-action" id="save_btn"
            ${'edit' eq edit_type ? 'disabled' : ''}><i
                    class="fa fa-save"></i>
                <spring:message code="common.btn.save"/>
            </button>
        </div>
    </div>
</div>
<script>
    var listVoucher = [];
    var listFinanceVoucher = [];

    <c:forEach var="financeVoucherRange" items="${account_object.financeVoucherRanges}">
    listFinanceVoucher.push("${financeVoucherRange.amount}-${financeVoucherRange.voucherCode}");
    </c:forEach>

    <c:forEach var="voucherRange" items="${account_object.voucherRanges}">
    listVoucher.push("${voucherRange.amount}-${voucherRange.voucherCode}");
    </c:forEach>

    $("input[name='list-voucher']").val(listVoucher);
    $("input[name='list-finance-voucher']").val(listFinanceVoucher);


    function removeRow(name) {
        if (!$('#save_btn').is(":disabled")) {
            listVoucher = arrayRemove(listVoucher, name);
            $("#" + name).remove();
            $("input[name='list-voucher']").val(listVoucher);
        }
    }

    function removeFinanceRow(name) {
        if (!$('#save_btn').is(":disabled")) {
            listFinanceVoucher = arrayRemove(listFinanceVoucher, name);
            $("#" + name).remove();
            $("input[name='list-finance-voucher']").val(listFinanceVoucher);
        }
    }

    function currencyPrice(event) {
        var tmp = event.target.value;
        if (tmp.length > 0) {
            if (tmp.toString().includes(".")) {
                tmp = tmp.split('.').join('')
                event.target.value = formatCurrency(tmp)
            } else {
                event.target.value = formatCurrency(tmp)
            }
        }
    };

    function validCurrency(event){
        if (event.charCode < 48 || event.charCode > 57) {
            event.target.value = 0;
        }
    }

    $("#add-voucher").click(function () {
        var table = document.getElementById("voucher-code");
        var row = table.insertRow();
        var amount = row.insertCell(0);
        var voucherCode = row.insertCell(1);
        var amountInput = document.createElement("input");
        amountInput.type = "text";
        amountInput.classList.add("form-control");
        amountInput.addEventListener("keypress", function (event) {
            if (event.charCode < 48 || event.charCode > 57) {
                event.target.value = 0;
            }
        });
        amountInput.addEventListener("input", function (event) {
            currencyPrice(event);
        });
        var unique = new Date().getTime();
        listVoucher.push(unique);
        $("input[name='list-voucher']").val(listVoucher);
        amountInput.name = "amount-" + unique;
        var voucherCodeInput = document.createElement("input");
        voucherCodeInput.type = "text";
        voucherCodeInput.name = "voucher-" + unique;
        voucherCodeInput.classList.add("form-control");
        amount.appendChild(amountInput);
        voucherCode.appendChild(voucherCodeInput);

        var action = row.insertCell(2);
        var div = document.createElement("div");
        div.classList.add("center");
        var span = document.createElement("span");
        span.addEventListener("click", function () {
            row.remove();
            listVoucher = arrayRemove(listVoucher, unique);
            $("input[name='list-voucher']").val(listVoucher);
        });
        var i = document.createElement("i");
        i.classList.add("fa", "fa-trash");
        i.style.color = "red";
        i.style.fontSize = "1.8rem";
        i.style.cursor = "pointer"
        span.appendChild(i);
        div.appendChild(span);
        action.appendChild(div);
    });
    $("#add-finance-voucher").click(function () {
        var table = document.getElementById("finance-voucher-code");
        var row = table.insertRow();
        var amount = row.insertCell(0);
        var voucherCode = row.insertCell(1);
        var amountInput = document.createElement("input");
        amountInput.type = "text";
        amountInput.classList.add("form-control");
        amountInput.addEventListener("keypress", function (event) {
            if (event.charCode < 48 || event.charCode > 57) {
                event.target.value = 0;
            }
        });
        amountInput.addEventListener("input", function (event) {
            currencyPrice(event);
        });
        var unique = new Date().getTime();
        listFinanceVoucher.push(unique);
        $("input[name='list-finance-voucher']").val(listFinanceVoucher);
        amountInput.name = "finance-amount-" + unique;
        var voucherCodeInput = document.createElement("input");
        voucherCodeInput.type = "text";
        voucherCodeInput.classList.add("form-control");
        voucherCodeInput.name = "finance-voucher-" + unique;
        amount.appendChild(amountInput);
        voucherCode.appendChild(voucherCodeInput);

        var action = row.insertCell(2);
        var div = document.createElement("div");
        div.classList.add("center");
        var span = document.createElement("span");
        span.addEventListener("click", function () {
            row.remove();
            listFinanceVoucher = arrayRemove(listFinanceVoucher, unique);
            $("input[name='list-finance-voucher']").val(listFinanceVoucher);
        });
        var i = document.createElement("i");
        i.classList.add("fa", "fa-trash");
        i.style.color = "red";
        i.style.fontSize = "1.8rem";
        i.style.cursor = "pointer"
        span.appendChild(i);
        div.appendChild(span);
        action.appendChild(div);
    });

    function arrayRemove(arr, value) {
        return arr.filter(function (ele) {
            return ele != value;
        });
    }
</script>