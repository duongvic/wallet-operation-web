<%@ taglib prefix="ewallet" uri="https://processing.function.template/service/jsp/jstl/functions" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.translog.TransactionLogController.TRANSACTION_DETAIL" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController.BALANCE_TOTAL_ASSETS_CONTROLLER" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController.BALANCE_TOTAL_OF_ASSETS_HISTORY" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController.BALANCE_TOTAL_OF_ASSETS_STATICS_DETAIL" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="balance.customer.header"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <link rel="stylesheet"
        href="<c:url value='/assets/development/static/css/balance_monitoring.css'/>">
  <jsp:include page="../include_page/js_merchant.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <script type="text/javascript"
          src="<c:url value='/assets/development/static/js/loader.js'/>"></script>
  <script type="text/javascript"
          src="<c:url value='/assets/javascripts/tables/tableExport.js'/>"></script>

  <style>
    @media (min-width: 768px) {
      .select2-container {
        width: 140px !important;
      }
    }
  </style>

</head>
<body>
<section class="body">
  <c:set var="locale" value="${pageContext.response.locale}"/>
  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="balance-total-assets" name="nav"/>
    </jsp:include>

    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><spring:message
                    code="wallet.balance.balance.monitor"/></span></li>
                <li>
                  <span class="nav-active">
                    <spring:message code="label.balance.of.total.assets"/>
                  </span>
                </li>
              </ol>
            </div>
          </div>
        </div>
      </header>
      <!-- start: page -->
      <!-- start:block2 -->
      <spring:message code="wallet.balance.searchText" var="textPlaceHolder"/>
      <spring:message code="button.search" var="btnSearch"/>
      <spring:message code="select.choose.all" var="chooseAll"/>
      <spring:message code="select.status" var="langStatus"/>
      <spring:message code="label.transaction.history" var="tranHistory"/>
      <spring:message code="label.balance" var="_balance"/>

      <div class="content-body-wrap">
        <div class="container-fluid pt-md">
          <div class="tabs">
            <ul class="nav nav-tabs">
              <li class="active">
                <a onclick="openTab('balance');" href="#">
                  ${_balance}
                </a>
              </li>
              <li class="">
                <a onclick="openTab('history');" href="#">
                  ${tranHistory}
                </a>
              </li>
            </ul>
            <div class="tab-content pl-none pr-none">
              <div id="tab1" class="tab-pane active">
                <section class="panel search_payment panel-default">
                  <div class="panel-body pt-md">

                    <form action="#" method="GET" id="tbl-filter" class="mb-md">
                      <div class="form-group ml-none mr-none">
                        <div class="input-group input-group-icon"></div>


                        <jsp:include page="../include_component/date_range.jsp"/>

                        <sec:authorize access="hasAnyRole('ADMIN_OPERATION')" var="permitAll"/>


                        <c:set var="allBank" value=","/>
                        <c:forEach var="st" items="${paramValues.bankCodes}">
                          <c:set var="allBank" value="${allBank}${st},"/>
                        </c:forEach>
                        <select class="form-control multiple-select"
                                name="bankCodes" id="bankCodes"
                                multiple="multiple">
                          <c:forEach var="item" items="${listBank}">
                            <c:set var="status2" value=",${item.key},"/>
                            <option ${fn:contains(allBank, status2)?'selected':'' }
                                value="${item.key}">${item.value}</option>
                          </c:forEach>
                        </select>

                        <spring:message var="chooseBankAccount" code="select.account.number"/>

                        <select name="bankAccounts" id="bankAccounts"
                                data-plugin-selectTwo class="form-control"
                                style="margin-bottom: 10px">
                          <option value="">${chooseBankAccount}</option>
                        </select>

                        <div class="pull-right form-responsive bt-plt">
                          <button type="button"
                                  class="btn btn-primary ml-tiny"
                                  onclick="drawTableBankStaticsList()">
                            <i class="fa fa-search"></i>&nbsp;
                            <spring:message code="common.btn.search"/>
                          </button>
                          <button type="button" style="color: #33333F"
                                  class="btn btn-default ml-tiny"
                                  onclick="refreshForm()"><spring:message code="common.btn.cancel"/>
                          </button>
                        </div>
                      </div>
                    </form>

                    <div class="clearfix"></div>
                    <div style="margin-top: 3px;">
                      <spring:message code="label.total.bank.account"/>:&nbsp;
                      <span id="totalFilter"
                            class="primary_color text-semibold"></span>&nbsp;|&nbsp;
                      <spring:message code="label.total.beginning.balance"/>:&nbsp;
                      <span id="totalBeginBalance"
                            class="primary_color text-semibold"></span>&nbsp;|&nbsp;
                      <spring:message code="label.total.ending.balance"/>:&nbsp;<span
                        id="totalEndBalance"
                        class="primary_color text-semibold"></span>&nbsp;|&nbsp;
                      <spring:message code="label.difference"/>:&nbsp;<span
                        id="totalDiff"
                        class="primary_color text-semibold"></span>
                    </div>

                    <spring:message var="colNo" code="balance.customer.table.no"/>
                    <spring:message var="colBankCode" code="label.bankcode"/>
                    <spring:message var="colBankAccount" code="label.account.number"/>
                    <spring:message var="colTimeTransaction" code="label.time.incurred.beginning.balance"/>
                    <spring:message var="colIncreased" code="label.beginning.balance"/>
                    <spring:message var="colTimeEndTransaction" code="label.time.arises.ending.balance"/>
                    <spring:message var="colDecreased" code="label.ending.balance"/>
                    <spring:message var="colDiffBalance" code="label.difference"/>
                    <spring:message var="colInitializationTime" code="label.initialization.time"/>
                    <spring:message var="colAction" code="transaction.api.table.action"/>
                    <spring:message var="actDetail" code="balance.customer.table.action.detail"/>

                    <div class="clearfix"></div>

                    <div class="container-fluid">
                      <div class="table-responsive">
                        <table class="table table-bordered table-responsive table-striped mb-none"
                               id="total-asset-list">
                          <thead>
                          <th>${ewallet:toUpperCase(colNo)}</th>
                          <th>${ewallet:toUpperCase(colBankCode)}</th>
                          <th>${ewallet:toUpperCase(colBankAccount)}</th>
                          <%--<th>${ewallet:toUpperCase(colTimeTransaction)}</th>--%>
                          <th>${ewallet:toUpperCase(colIncreased)}</th>
                          <%--<th>${ewallet:toUpperCase(colTimeEndTransaction)}</th>--%>
                          <th>${ewallet:toUpperCase(colDecreased)}</th>
                          <th>${ewallet:toUpperCase(colDiffBalance)}</th>
                          <th>${ewallet:toUpperCase(colAction)}</th>
                          </thead>
                        </table>
                      </div>
                    </div>
                  </div>
                </section>
              </div>
            </div>
          </div>

        </div>
      </div>


      <!-- end: page -->
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_page/date_picker.jsp">
  <jsp:param name="isFullTime" value="false"/>
  <jsp:param name="autoFilterDate" value="false"/>
</jsp:include>
<%--<jsp:include page="../include_component/search_form_commons.jsp">--%>
<%----%>
<%--</jsp:include>--%>

<script type="text/javascript"
        src="<c:url value="/assets/development/js/utils.js"/>"></script>

<spring:message code="data.table.header.paging.showing" var="paging_showing"/>
<spring:message code="data.table.header.paging.to" var="paging_to"/>
<spring:message code="data.table.header.paging.of" var="paging_of"/>
<spring:message code="data.table.header.paging.entries" var="paging_entries"/>
<spring:message code="data.table.header.paging.previous" var="paging_previous"/>
<spring:message code="data.table.header.paging.next" var="paging_next"/>
<spring:message code="data.table.header.paging.records" var="paging_records"/>

<script type="text/javascript">
  function refreshForm() {
    var multiSelects = ['bankCodes'];
    var select2 = ['bankAccounts'];
    var inputs = [];
    clearDataOf(multiSelects, select2, inputs);
    drawTableBankStaticsList();
  }

  $(document).ready(function () {
    // $('#bankAccounts').select2({
    //   width: "20%"
    // })

    drawTableBankStaticsList();

    $('#bankCodes').on('change', function () {
      bankCode = $('#bankCodes').val();

      $('#bankAccounts').val(null).trigger('change');

      $('#bankAccounts').empty().append('<option selected value="" disabled>${chooseBankAccount}</option>');

      $.ajax({
        url: ctx + url,
        dataType: 'json',
        data: {
          prmBankCodes: bankCode,
        },
        type: 'POST',
        success: function (data) {
          var bankAccounts = data.bankAccounts;
          console.log(bankAccounts);
          for (var i = 0; i < bankAccounts.length; i++) {
            $('#bankAccounts').append('<option value="' + bankAccounts[i].bankAccount + '">' + bankAccounts[i].bankAccount + '</option>');
          }
        },
        error: function (data) {
        }
      })
    });
// ---------------------------------------------------------------
  });


  $('#bankCodes').multiselect({
    //enableFiltering: true,
    includeSelectAllOption: true,
    selectAllValue: '',
    selectAllText: '<spring:message code="request.BankTransfer.select.bank"/>',
    maxHeight: 400,
    dropUp: true,
    nonSelectedText: '<spring:message code="request.BankTransfer.select.bank"/>',
    inheritClass: true,
    numberDisplayed: 4
  });

  function drawTableBankStaticsList() {

    var bankCodes = $('#bankCodes').val();
    var arrBankAccounts = [];
    var bankAccounts = $('#bankAccounts').val();
    arrBankAccounts.push(bankAccounts);
    var date = $('#reservation').val();


    $('#total-asset-list').dataTable({
      "paging": true,
      "processing": true,
      "serverSide": true,
      "iDisplayStart": 0,
      "pageLength": 20,
      "lengthMenu": [[10, 20, 50, -1], [10, 20, 50]],
      "searching": false,
      "language": {
        "sInfo": "${paging_showing} _START_ ${paging_to} _END_ ${paging_of} _TOTAL_ ${paging_entries}",
        "sLengthMenu": "_MENU_ ${paging_records}",
        "paginate": {
          "previous": "${paging_previous}",
          "next": "${paging_next}"
        },
        "processing": "<img src='../../../assets/images/loading_airline.gif'/>"
      },
      dom: 'fltip',
      destroy: true,
      "ajax": {
        "url": ctx + "/ajax-stock-controller/find-total-asset-statics",
        "type": "POST",
        "data": {
          date: date,
          bankCodes: bankCodes,
          bankAccounts: arrBankAccounts,
        },
        dataSrc: 'dataList',
        dataFilter: function (reps) {
          var myObj = jQuery.parseJSON(reps);
          document.getElementById("totalFilter").innerHTML = myObj.recordsTotal;
          var totalBeginBalance = formatCurrency(myObj.total1 == null ? 0 : myObj.total1);
          document.getElementById("totalBeginBalance").innerHTML = totalBeginBalance;
          var totalEndBalance = formatCurrency(myObj.total2 == null ? 0 : myObj.total2);
          document.getElementById("totalEndBalance").innerHTML = totalEndBalance;

          var totalDiff = formatCurrency(Math.abs((myObj.total2) - (myObj.total1)));
          var html ="";
          if((myObj.total2 - myObj.total1) > 0){
            html += '<span style="color: green ; font-size: 18px;">' + totalDiff + '&nbsp;&uarr;</span>';
          }
          if((myObj.total2 - myObj.total1) < 0){
            html += '<span style="color: red ; font-size: 18px;">' + totalDiff + '&nbsp;&darr;</span>';
          }
          if((myObj.total2 - myObj.total1) == 0){
            html += '<span>' + totalDiff + '</span>';
          }
          document.getElementById("totalDiff").innerHTML = html;
          return reps;
        },
        error: function (err) {
          console.log(err);
        }
      },
      "columns": [
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var index = meta.settings.oAjaxData.start + meta.row + 1;
            return index;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var bankCode = data.bankAccount.bankCode;
            return bankCode;
          }
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var bankAccount = data.bankAccount.bankAccount;
            return bankAccount;
          }
        },
        // {
        //   "data": null,
        //   "render":
        //     function (data, type, full, meta) {
        //       if (data.balanceBeginOfPeriod.txnDate !== null) {
        //         var txnDate = new Date(data.balanceBeginOfPeriod.txnDate);
        //         return txnDate.hmdmy();
        //       }
        //       return "";
        //     }
        // },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var postBalanceBegin = data.balanceBeginOfPeriod.postBalance;
            var htmlCode = "<div style='text-align: right'>" + formatCurrency(postBalanceBegin == null ? 0 : postBalanceBegin)
              + "</div>";
            return htmlCode;
          }
        },
        // {
        //   "data": null,
        //   "render":
        //     function (data, type, full, meta) {
        //       if (data.balanceEndOfPeriod.txnDate !== null) {
        //         var txnDate = new Date(data.balanceEndOfPeriod.txnDate);
        //         return txnDate.hmdmy();
        //       }
        //       return "";
        //     }
        // },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var postBalanceEnd = data.balanceEndOfPeriod.postBalance;
            var htmlCode = "<div style='text-align: right'>" + formatCurrency(postBalanceEnd == null ? 0 : postBalanceEnd)
              + "</div>";
            return htmlCode;
          }
        },
        {
          "data": null,
          "render":
            function (data, type, full, meta) {
              var postBalanceBegin = data.balanceBeginOfPeriod.postBalance;
              var postBalanceEnd = data.balanceEndOfPeriod.postBalance;
              var postBalance = postBalanceEnd - postBalanceBegin;
              var htmlCode = "";
              if (postBalance > 0) {
                htmlCode +=
                  "<div style='text-align:right;'>" + formatCurrency(postBalance == null ? 0 : postBalance) +
                  "<span style='color: green ; font-size: 18px;'>&nbsp;&uarr;</span>" +
                  "</div>";
              }
              if (postBalance < 0) {
                htmlCode +=
                  "<div style='text-align:right;'>" + formatCurrency(postBalance == null ? 0 : postBalance) +
                  "<span style='color: red ; font-size: 18px;'>&nbsp;&darr;</span>" +
                  "</div>";
              }
              if (postBalance == 0) {
                htmlCode +=
                  "<div style='text-align:right;'>" + formatCurrency(postBalance == null ? 0 : postBalance) +
                  "</div>";
              }
              return htmlCode;
            }
        },
        {
          "data": null,
          "render": function (data) {
            return '<div style="text-align: center">\n'
              + '                    <a href="${contextPath}<%=BALANCE_TOTAL_OF_ASSETS_STATICS_DETAIL%>/' + data.bankAccount.bankCode + '/' + data.bankAccount.bankAccount + '?range=' + date
              + '" data-toggle="tooltip" title="Chi tiết"><i class="fa fa fa-info-circle" style="font-size: 20px"></i></a>\n'
          }
        },

      ]
    })
    ;
  }

  var url = "/wallet/balance/total-assets/findBankAccount";
  var bankCode = '';


  function exportAll(type) {
    $('.table').tableExport({
      filename: 'BALANCE CUSTOMER_%DD%-%MM%-%YY%',
      format: type
    });
  }

  function openTab(paramValue) {
    var searchURL = '';
    if (paramValue === 'history') {
      searchURL = ctx + '<%=BALANCE_TOTAL_OF_ASSETS_HISTORY%>?menu=pro';
    }
    if (paramValue === 'balance') {
      searchURL = ctx + '<%=BALANCE_TOTAL_ASSETS_CONTROLLER%>?menu=pro';
    }
    window.location.href = searchURL;
  }

</script>

</body>
</html>