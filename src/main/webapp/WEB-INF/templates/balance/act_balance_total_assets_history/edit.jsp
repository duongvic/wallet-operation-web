<%@ page
    import="static vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController.BALANCE_TOTAL_OF_ASSETS_HISTORY_CREATE" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController.BALANCE_TOTAL_OF_ASSETS_HISTORY" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController.BALANCE_TOTAL_OF_ASSETS_HISTORY_EDIT" %><%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 5/20/2020
  Time: 11:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title>Số dư tổng tài sản</title>
  <jsp:include page="../../include_page/head.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <jsp:include page="../../include_page/js.jsp">
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
    <jsp:param name="dateLib" value="true"/>
  </jsp:include>
  <link rel="stylesheet"
        href="<c:url value='/assets/stylesheets/timepicker/bootstrap-timepicker.min.css'/>">
  <style>
    .select2-container {
      margin-bottom: 5px;
    }
  </style>
</head>

<body>
<section class="body">
  <!--        ///////   header ////////-->
  <jsp:include page="../../include_page/header.jsp"/>
  <jsp:include page="../../include_component/constant_application.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../../include_page/navigation.jsp">
      <jsp:param value="balance-total-assets" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->

    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                <li><span>Nhà cung cấp</span></li>
                <li><span>Hỗ trợ vận hành</span></li>
                <li><a class="nav-active" href="${contextPath}<%=BALANCE_TOTAL_OF_ASSETS_HISTORY%>">Số
                  dư tổng tài sản</a></li>
                <li><span class="nav-active">Chỉnh sửa</span>
                </li>
              </ol>
            </div>
          </div>
        </div>
      </header>
      <jsp:include page="../../include_page/message.jsp"/>
      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md">
            <spring:message code="transaction.api.navigate.transaction.detail"/>
          </div>
          <section class="panel panel-default">
            <div class="panel-body">
              <form
                  action="${contextPath}<%=BALANCE_TOTAL_OF_ASSETS_HISTORY_EDIT%>/${bankHistory.id}"
                  method="POST" class="col-md-6">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <div class="form-group row">
                  <label class="col-sm-3">Ngày phát sinh giao dịch*</label>
                  <div class="col-sm-9"
                       style="padding-right: 16px; padding-left: 16px; display: flex">
                    <input type="text" autocomplete="off"
                           placeholder="dd/mm/yyyy"
                           class="form-control single-date-picker"
                           name="create-date" id="create-date"
                           required value="${txnDate}"><i
                      class="fa fa-2x fa-calendar"
                      style="margin: 2px"></i>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-3">Thời gian*</label>
                  <div class="col-sm-9 input-group bootstrap-timepicker timepicker"
                       style="padding-right: 16px; padding-left: 16px">
                    <input id="create-time" name="create-time" type="text"
                           class="form-control input-small" required value="${txnTime}">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="bankCodes" class="col-sm-3 col-form-label">Ngân hàng*</label>
                  <div class="col-sm-9">
                    <select name="bankCodes" id="bankCodes" data-plugin-selectTwo
                            class="form-control m-xs" style="width: 100%" required>
                      <option value="" disabled><spring:message
                          code="request.BankTransfer.select.bank"/></option>
                      <c:forEach var="item" items="${listBank}">
                        <option
                            value="${item.key}" ${item.key eq bankHistory.bankCode ? 'selected' : ''}>${item.value}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>

                <div class="form-group row">
                  <label for="bankAccountIds" class="col-sm-3 col-form-label">Số tài khoản*</label>
                  <div class="col-sm-9">
                    <select class="find-bankacc m-xs" data-plugin-selectTwo name="bankAccountIds"
                            id="bankAccountIds" style="width: 100%" required>
                      <option value="${bankHistory.bankAccount}"
                              selected>${bankHistory.bankAccount}</option>
                    </select>
                  </div>
                </div>

                <div class="form-group row">
                  <label for="balance-change" class="col-sm-3 col-form-label">Biến động số
                    dư*</label>
                  <div class="col-sm-9">
                    <select name="balance-change" id="balance-change" class="form-control"
                            style="margin-bottom: 5px" required>
                      <option value="CREDIT" ${bankHistory.txnType eq 'CREDIT'? 'selected' : ''}>
                        Phát sinh tăng
                      </option>
                      <option value="DEBIT" ${bankHistory.txnType eq 'DEBIT'? 'selected' : ''}>Phát
                        sinh giảm
                      </option>
                    </select>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="moneys" class="col-sm-3 col-form-label">Số tiền*</label>
                  <div class="col-sm-9 double-input">
                    <input class="form-control" style="margin-bottom: 5px; width: 80%" name="moneys"
                           id="moneys"
                           onkeypress="return event.charCode >= 48 && event.charCode <= 57"
                           oninput="currencyPrice(event)" required
                           value="${ewallet:formatNumber(bankHistory.amount)}">
                    <input class="form-control" value="VND" disabled style="width: 20%">
                  </div>
                </div>

                <div class="form-group row">
                  <label for="balance" class="col-sm-3 col-form-label">Số dư*</label>
                  <div class="col-sm-9 double-input">
                    <input class="form-control" style="margin-bottom: 5px; width: 80%"
                           name="balance"
                           id="balance"
                           onkeypress="return event.charCode >= 48 && event.charCode <= 57"
                           oninput="currencyPrice(event)" required
                           value="${ewallet:formatNumber(bankHistory.postBalance)}">
                    <input class="form-control" value="VND" disabled style="width: 20%">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="note" class="col-sm-3 col-form-label">Mã tham chiếu</label>
                  <div class="col-sm-9">
                    <input class="form-control" style="margin-bottom: 5px" name="bankRef"
                           id="bankRef" value="${bankHistory.bankRefTxn}">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="note" class="col-sm-3 col-form-label">Nội dung*</label>
                  <div class="col-sm-9">
                    <textarea class="form-control" style="margin-bottom: 5px; height: 100px"
                              name="note" id="note"
                              required>${bankHistory.info}</textarea>
                  </div>
                </div>
                <div style="float: right">
                  <button type="button" class="btn btn-default">
                    <spring:message code="common.btn.cancel"/>
                  </button>
                  <button type="submit" class="btn btn-primary">
                    <spring:message code="common.btn.save"/>
                  </button>
                </div>
              </form>
            </div>
          </section>
        </div>
      </div>
    </section>


    <jsp:include page="../../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../../include_page/js_footer.jsp"/>
<script type="text/javascript"
        src="<c:url value='/assets/development/js/balance_total_assets_history/create.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/assets/javascripts/timepicker/bootstrap-timepicker.min.js'/>"></script>
<script type="text/javascript">
  // time picker -------------------------------------------------
  $('#create-time').timepicker({
    showSeconds: true,
    maxHours: 24,
    showMeridian: false,
  });
  // date picker ------------------------------------------------
  var createDate = $('#create-date');
  createDate.daterangepicker({
    autoUpdateInput: false,
    singleDatePicker: true,
    showDropdowns: true,
    locale: {
      format: 'DD/MM/YYYY'
    }
  }, function (chosen_date) {
    createDate.val(chosen_date.format('DD/MM/YYYY'));
  });

  // money currency -----------------------------------------
  function currencyPrice(event) {
    var tmp = event.target.value;
    if (tmp.length > 0) {
      if (tmp.toString().includes(".")) {
        tmp = tmp.split('.').join('');
        event.target.value = formatCurrency(tmp)
      } else {
        event.target.value = formatCurrency(tmp)
      }
    }
  };
</script>

</body>

</html>

