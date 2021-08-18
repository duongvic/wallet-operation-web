<%@ page
    import="static vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController.BALANCE_TOTAL_ASSETS_CONTROLLER" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController.BALANCE_TOTAL_OF_ASSETS_HISTORY" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController.BALANCE_TOTAL_OF_ASSETS_HISTORY_CREATE" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController.BALANCE_TOTAL_OF_ASSETS_HISTORY_DETAIL" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController.BALANCE_TOTAL_OF_ASSETS_HISTORY_EDIT" %>
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
      <jsp:include page="../include_page/message.jsp"/>
      <sec:authorize access="hasAnyRole('ADMIN_OPERATION')" var="permitAll"/>

      <div class="content-body-wrap">
        <div class="container-fluid pt-md">
          <div class="tabs">
            <ul class="nav nav-tabs">
              <li class=""><a onclick="openTab('balance');" href="#">${_balance}</a></li>
              <li class="active"><a onclick="openTab('history');" href="#">${tranHistory}</a></li>
            </ul>
            <div class="tab-content pl-none pr-none">
              <div id="tab1" class="tab-pane active">
                <section class="panel search_payment panel-default">

                  <div class="panel-body pt-none">
                    <form action="#" method="GET" id="tbl-filter" class="mb-md">

                      <div class="form-group ml-none mr-none">
                        <div class="col-md-12 col-lg-12">
                          <div class="form-group">
                            <div class="row">
                              <div class="col-md-12">

                                <div class="pull-right form-responsive bt-plt">
                                  <a class="mb-xs mt-xs btn btn-success" style="float: right"
                                     href="${contextPath}<%=BALANCE_TOTAL_OF_ASSETS_HISTORY_CREATE%>">
                                    <i class="fa fa-plus"></i>&nbsp;<spring:message
                                      code="system.service.navigate.btn.create"/>
                                  </a>
                                </div>

                              </div>
                            </div>
                          </div>
                        </div>
                      </div>

                      <div class="form-group ml-none mr-none">
                        <div class="col-md-12 col-lg-12">
                          <div class="form-group">
                            <div class="row">
                              <div class="col-md-12">
                                <div class="form-inline">
                                  <jsp:include page="../include_component/date_range.jsp"/>

                                  <div style="display: inline">
                                    <c:set var="allBank" value=","/>
                                    <c:forEach var="st" items="${paramValues.bankCodes}">
                                      <c:set var="allBank" value="${allBank}${st},"/>
                                    </c:forEach>
                                    <select class="form-control multiple-select mb-xs"
                                            name="bankCodes" id="bankCodes"
                                            multiple="multiple">
                                      <c:forEach var="item" items="${listBank}">
                                        <c:set var="status2" value=",${item.key},"/>
                                        <option ${fn:contains(allBank, status2)?'selected':'' }
                                            value="${item.key}">${item.value}</option>
                                      </c:forEach>
                                    </select>
                                  </div>

                                  <div style="display: inline;">
                                    <spring:message var="chooseBankAccount" code="select.account.number"/>
                                    <select name="bankAccounts" id="bankAccounts"
                                            data-plugin-selectTwo class="form-control"
                                            style="margin: 22px 0">
                                      <option value="">${chooseBankAccount}</option>
                                    </select>
                                  </div>


                                  <div class="pull-right form-responsive bt-plt">
                                    <button type="button"
                                            class="btn btn-primary ml-tiny"
                                            onclick="drawTableBankHistoryList()">
                                      <i class="fa fa-search"></i>&nbsp;
                                      <spring:message code="common.btn.search"/>
                                    </button>
                                    <button type="button" style="color: #33333F"
                                            class="btn btn-default ml-tiny"
                                            onclick="refreshForm()"><spring:message code="common.btn.cancel"/>
                                    </button>
                                  </div>

                                </div>
                              </div>
                            </div>
                          </div>
                        </div>


                      </div>
                    </form>

                    <div class="clearfix"></div>

                    <div style="margin-top: 3px;">
                      <spring:message code="label.total.bank.account"/>:&nbsp;
                      <span id="totalFilter"
                            class="primary_color text-semibold"></span>&nbsp;|&nbsp;
                      <spring:message code="label.total.incurred.increased"/>:&nbsp;
                      <span id="totalCreditAmount"
                            class="primary_color text-semibold"></span>&nbsp;|&nbsp;
                      <spring:message code="label.total.incurred.decreased"/>:&nbsp;<span
                        id="totalTotalDebitAmount"
                        class="primary_color text-semibold"></span>&nbsp;|&nbsp;
                      <spring:message code="label.difference"/>:&nbsp;<span
                        id="totalDiffHis"
                        class="primary_color text-semibold"></span>
                    </div>
                    <spring:message var="colNo" code="balance.customer.table.no"/>
                    <spring:message var="colTimeTransaction" code="label.time.of.the.transaction"/>
                    <spring:message var="colFullname" code="balance.customer.table.fullname"/>
                    <spring:message var="colBankCode" code="label.bankcode"/>
                    <spring:message var="colBankAccount" code="label.account.number"/>
                    <spring:message var="colIncreased" code="label.incurred.increased"/>
                    <spring:message var="colDecreased" code="label.incurred.decreased"/>
                    <spring:message var="colCurBalance" code="label.balance"/>
                    <spring:message var="colInitializationTime" code="label.initialization.time"/>
                    <spring:message var="colAction" code="transaction.api.table.action"/>
                    <spring:message var="actDetail" code="balance.customer.table.action.detail"/>

                    <div class="clearfix"></div>

                    <div class="container-fluid">
                      <div class="table-responsive">
                        <table class="table table-bordered table-responsive table-striped mb-none"
                               id="total-asset-list-history">
                          <thead>
                          <th>${ewallet:toUpperCase(colNo)}</th>
                          <th>${ewallet:toUpperCase(colTimeTransaction)}</th>
                          <th>${ewallet:toUpperCase(colAction)}</th>
                          <th>${ewallet:toUpperCase(colBankCode)}</th>
                          <th>${ewallet:toUpperCase(colBankAccount)}</th>
                          <th>${ewallet:toUpperCase(colIncreased)}</th>
                          <th>${ewallet:toUpperCase(colDecreased)}</th>
                          <th>${ewallet:toUpperCase(colCurBalance)}</th>
                          <th>${ewallet:toUpperCase(colInitializationTime)}</th>
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

        <!-- end: page -->
        <jsp:include page="../include_page/footer.jsp"/>
      </div>
      <div class="modal fade" tabindex="-1" id="deleteHistoryBalance" role="dialog"
           aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">
                <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
                  code="popup.header.icon.close"/></span>
              </button>
              <h4 class="modal-title" id="myModalLabel">Xóa giao dịch</h4>
            </div>

            <div class="modal-body">

              <form id="delete-history-balance-form">
                <div class="form-group">
                  <label class="col-md-12 control-label pl-none">Bạn chắc chắn muốn xóa giao dịch có
                    số tài khoản <label id="name-trans"></label></label>
                  <input type="hidden" id="idTxn">
                </div>
                <input type="checkbox" id="checkboxExample3">&nbsp;<label>Xác nhận</label>
                <sec:csrfInput/>
              </form>
            </div>

            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                  code="service.exportcard.list.popup.summary.sub.cancel"/></button>
              <button type="button" class="btn btn-danger confirmRequest"
                      data-loading-text="<i class='fa fa-spinner fa-spin '></i><spring:message code="common.waitting.pl"/>">
                <spring:message code="system.service.popup.delete.lable.yes"/></button>
            </div>
          </div>
        </div>
      </div>
    </section>
    <jsp:include page="../include_page/js_footer.jsp"/>
    <jsp:include page="../include_page/date_picker.jsp">
      <jsp:param name="isFullTime" value="true"/>
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
        drawTableBankHistoryList();
      }

      function openModal(id, bankAccount) {
        document.getElementById("delete-history-balance-form").reset();
        $('#idTxn').val(id);
        $('#name-trans').text(bankAccount + "?");
        $('#deleteHistoryBalance').modal();
      }

      $('.confirmRequest').click(function () {
        var idTxn = $('#idTxn').val();
        if (!$('#checkboxExample3').is(':checked')) {
          $.MessageBox({message: "<spring:message code="common.not.confirm.the.action"/>"});
          return false;
        }
        $.ajax({
          type: 'GET',
          url: ctx + '/ajax-stock-controller/delete-balance-history/' + idTxn,
          success: function (data) {
            $.MessageBox(data.status.value);
            drawTableBankHistoryList();
            $('#deleteHistoryBalance').modal('hide');
          },
          error: function (data) {
            $.MessageBox(data.status.value);
            drawTableBankHistoryList();
            $('#deleteHistoryBalance').modal('hide');
          }
        })
      })

      $('#bankCodes').multiselect({
        //enableFiltering: true,
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="request.BankTransfer.select.bank"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="request.BankTransfer.select.bank"/>',
        inheritClass: true,
        numberDisplayed: 6
      });

      function drawTableBankHistoryList() {


        var bankCodes = $('#bankCodes').val();
        var arrBankAccounts = [];
        var bankAccounts = $('#bankAccounts').val();
        arrBankAccounts.push(bankAccounts);
        var date = $('#reservation').val();


        $('#total-asset-list-history').dataTable({
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
            // 'loadingRecords': '&nbsp;',
            // 'processing': 'Loading...'
          },
          dom: 'fltip',
          destroy: true,
          "ajax": {
            "url": ctx + "/ajax-stock-controller/find-total-asset-history",
            "type": "POST",
            "data": {
              date: date,
              bankCodes: bankCodes,
              bankAccounts: arrBankAccounts

            },
            dataSrc: 'dataList',
            dataFilter: function (reps) {
              var myObj = jQuery.parseJSON(reps);
              document.getElementById("totalFilter").innerHTML = myObj.recordsTotal;
              var totalCreditAmount = formatCurrency(myObj.total1 == null ? 0 : myObj.total1);
              document.getElementById("totalCreditAmount").innerHTML = totalCreditAmount;
              var totalTotalDebitAmount = formatCurrency(myObj.total2 == null ? 0 : myObj.total2);
              document.getElementById("totalTotalDebitAmount").innerHTML = totalTotalDebitAmount;
              // var totalDiffHis = formatCurrency(myObj.total2- myObj.total1);
              // document.getElementById("totalDiffHis").innerHTML = totalDiffHis;


              var totalDiffHis = formatCurrency(Math.abs((myObj.total1) - (myObj.total2)));
              var html ="";
              if((myObj.total1 - myObj.total2) > 0){
                html += '<span style="color: green ; font-size: 18px;">' + (totalDiffHis) + '&nbsp;&uarr;</span>';

              }
              if((myObj.total1 - myObj.total2) < 0){
                html += '<span style="color: red ; font-size: 18px;">' + totalDiffHis + '&nbsp;&darr;</span>';
              }
              if((myObj.total1 - myObj.total2) == 0){
                html += '<span>' + totalDiffHis + '</span>';
              }
              document.getElementById("totalDiffHis").innerHTML = html;
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
              "render":
                function (data, type, full, meta) {
                  if (data.txnDate !== null) {
                    var txnDate = new Date(data.txnDate);
                    return txnDate.hmdmy();
                  }
                  return "";
                }
            },

            {
              "data": null,
              "render": function (data) {
                return '<div style="display: flex; justify-content: space-evenly;">\n'
                  + '                    <a href="${contextPath}<%=BALANCE_TOTAL_OF_ASSETS_HISTORY_DETAIL%>/'
                  + data.id
                  + '" data-toggle="tooltip" title="Chi tiết"><i class="fa fa-info-circle" style="font-size: 20px"></i></a>\n'
                  + '                    <a href="${contextPath}<%=BALANCE_TOTAL_OF_ASSETS_HISTORY_EDIT%>/'
                  + data.id
                  + '" data-toggle="tooltip" title="Chỉnh sửa"><i class="fa fa-pencil-square" style="color: green; font-size: 20px"></i></a>\n'
                  + '<a onClick="openModal(' + data.id + ', ' + data.bankAccount + ')"\n'
                  + '            ><i class="fa fa-trash" style="font-size: 20px"></i></a>'
                  + '                    </div>'
              }
            },

            {
              "data": 'bankCode'
            },

            {
              "data": 'bankAccount'
            },

            {
              "data": null,
              "render": function (data, type, full, meta) {
                var amount = data.amount;
                var txnType = data.txnType;
                var htmlCode = "";
                if (amount > 0 && txnType === 'CREDIT') {
                  htmlCode +=
                    "<div style='text-align:right;'>" + formatCurrency(amount == null ? 0 : amount) +
                    "<span style='color: green ; font-size: 18px;'>&nbsp;&uarr;</span>" +
                    "</div>";
                }
                if (txnType !== 'CREDIT') {
                  htmlCode += "<div style='text-align: right'>0</div>";
                }

                return htmlCode;
              }
            },

            {
              "data": null,
              "render": function (data, type, full, meta) {
                var amount = data.amount;
                var txnType = data.txnType;
                var htmlCode = "";
                if (amount > 0 && txnType === 'DEBIT') {
                  htmlCode +=
                    "<div style='text-align:right;'>" + formatCurrency(amount == null ? 0 : amount) +
                    "<span style='color: red ; font-size: 18px;'>&nbsp;&darr;</span>" +
                    "</div>";
                }
                if (txnType !== 'DEBIT') {
                  htmlCode += "<div style='text-align: right'>0</div>";
                }

                return htmlCode;
              }
            },

            {
              "data": null,
              "render":
                function (data, type, full, meta) {
                  var postBalance = data.postBalance;
                  return "<div style='text-align: right'>" + formatCurrency(postBalance == null ? 0 : postBalance) + "</div>"
                }
            },
            {
              "data": null,
              "render":
                function (data, type, full, meta) {
                  if (data.createdTime !== null) {
                    var createdTime = new Date(data.createdTime);
                    return createdTime.hmdmy();
                  }
                  return "";
                }
            },


          ]
        })
        ;
      }

      var url = "/wallet/balance/total-assets/findBankAccount";
      var bankCode = '';
      $(document).ready(function () {
        // $('#bankAccounts').select2({
        //   width: "20%"
        // })

        drawTableBankHistoryList();

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
                $('#bankAccounts').append(
                  '<option value="' + bankAccounts[i].bankAccount + '">'
                  + bankAccounts[i].bankAccount + '</option>');
              }
            },
            error: function (data) {
            }
          })
        });

      });


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