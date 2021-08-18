<%@ page import="vn.mog.ewallet.operation.web.controller.epin.EpinPurchaseOrderController" %>
<%@ page
    import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TxnStatus" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_CUSTOMER" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_AGENT" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_MERCHANT" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_PROVIDER" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.*" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.reconciliation.ReconciliationV2Controller.RECON_CONTROLLER" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.reconciliation.ReconciliationV2Controller.RECON_EXPORT" %>
<%--<%@ page import="static vn.mog.ewallet.operation.web.controller.reconciliation.ReconciliationController" %>--%>
<%@ include file="../../include_page/taglibs.jsp" %>
<spring:message code="data.table.header.paging.showing" var="paging_showing"/>
<spring:message code="data.table.header.paging.to" var="paging_to"/>
<spring:message code="data.table.header.paging.of" var="paging_of"/>
<spring:message code="data.table.header.paging.entries" var="paging_entries"/>
<spring:message code="data.table.header.paging.previous" var="paging_previous" scope="application"/>
<spring:message code="data.table.header.paging.next" var="paging_next" scope="application"/>
<spring:message code="data.table.header.paging.records" var="paging_records"/>

<style>
  .levelTwoWarning {
    background-color: bisque !important;
  }

  .levelThreeWarning {
    background-color: #f5efea !important;
  }
</style>

<spring:message var="colStt" code="transaction.api.table.stt"/>
<spring:message var="colRId" code="transaction.api.table.request_id"/>
<spring:message var="colMerchant"
                code="transaction.api.table.merchant"/>
<spring:message var="colProvider"
                code="transaction.api.table.provider"/>
<spring:message var="colCustomerType"
                code="transaction.api.table.customer.type"/>
<spring:message var="colPaymentMethod" code="transaction.api.table.payment.method"/>
<spring:message var="colTransID"
                code="transaction.api.table.transaction-id"/>
<spring:message var="colTime" code="transaction.api.table.time"/>
<spring:message var="colShortService" code="transaction.api.table.service"/>
<spring:message var="colService" code="transaction.api.table.service.type"/>
<spring:message var="colType" code="transaction.api.table.type"/>
<spring:message var="colValue" code="transaction.api.table.value"/>
<spring:message var="colQTy" code="transaction.api.table.qty"/>
<spring:message var="colSubject" code="transaction.api.table.subject"/>
<spring:message var="colCapitalVal"
                code="transaction.api.table.capitalVal"/>
<spring:message var="colGrossProfit"
                code="transaction.api.table.grossProfit"/>
<spring:message var="colAmount" code="transaction.api.table.amount"/>
<spring:message var="colRealAmount"
                code="transaction.api.table.realamount"/>

<spring:message var="colCommission"
                code="transaction.api.table.commission"/>
<spring:message var="colFee" code="transaction.api.table.fee"/>
<spring:message var="colBalBf" code="transaction.api.table.balance-bf"/>
<spring:message var="colBalAf" code="transaction.api.table.balance-af"/>
<spring:message var="colStatus" code="transaction.api.table.status"/>
<spring:message var="colAction" code="transaction.api.table.action"/>
<spring:message var="colCurrency"
                code="transaction.api.table.currency"/>
<spring:message var="actViewDetail"
                code="transaction.table.action.viewdetail"/>
<spring:message var="actViewReveral"
                code="transaction.table.action.reversalTxn"/>
<spring:message var="colTraceNo" code="transaction.api.table.trace.no.id"/>

<spring:message var="colCusCif" code="label.customer.cif"/>
<spring:message var="colCusName" code="label.customer.name"/>
<spring:message var="colCusMsisdn" code="label.customer.msisdn"/>
<spring:message var="colLastPostBalance" code="report.recon.last.post.balance"/>
<spring:message var="colReconStt" code="label.recon.status"/>
<spring:message var="colCusType" code="label.customer.type"/>
<spring:message var="colBegin" code="report.recon.beginning.balance"/>
<spring:message var="colLoaded" code="report.recon.loaded.period"/>
<spring:message var="colTextin" code="report.recon.text.in.the.period"/>
<spring:message var="colEnd" code="report.recon.end.balance"/>
<spring:message var="colLast" code="report.recon.last.post.balance"/>
<spring:message var="colEqual" code="label.equal"/>
<spring:message var="colNotEqual" code="label.not.equal"/>
<c:set var="CUSTOMER" value="<%=ID_CUSTOMER%>"/>
<c:set var="AGENT" value="<%=ID_AGENT%>"/>
<c:set var="MERCHANT" value="<%=ID_MERCHANT%>"/>
<c:set var="PROVIDER" value="<%=ID_PROVIDER%>"/>
<c:set var="PROPERTY_MANAGER" value="<%=ID_PROPERTY_MANAGER%>"/>

<c:url var="urlEpinPOdetail"
       value="<%=EpinPurchaseOrderController.EPIN_PO_DETAIL%>"/>

<%--<display:table name="reconciles" id="item"--%>
<%--requestURI="list"--%>
<%--partialList="true"--%>
<%--pagesize="${pagesize}"--%>
<%--size="total"--%>
<%--sort="page"--%>
<%--class="table table-bordered table-striped mb-none">--%>


<%--<%@ include file="../../include_component/display_table.jsp" %>--%>

<%--&lt;%&ndash;Class for transation status&ndash;%&gt;--%>
<%--<c:set value="${item.reconcileSummary.endBalance == item.reconcileSummary.lastPostBalance}"--%>
<%--var="isBalanceMatch"/>--%>
<%--<c:set var="backgroundClass"--%>
<%--value="${isBalanceMatch ? '' : 'levelTwoWarning'}"/>--%>

<%--<display:column title="${colStt}" class="${backgroundClass}">--%>
<%--<span id="row${list.id}" class="rowid">--%>
<%--<c:out value="${offset + item_rowNum}"/>--%>
<%--</span>--%>
<%--</display:column>--%>
<%--<display:column title="${colCusCif}" class="${backgroundClass}">--%>
<%--${item.cif}--%>
<%--</display:column>--%>
<%--<display:column title="${colCusName}" class="${backgroundClass}">--%>
<%--${item.name}--%>
<%--</display:column>--%>
<%--<display:column title="${colCusMsisdn}" class="${backgroundClass}">--%>
<%--${item.msisdn}--%>
<%--</display:column>--%>
<%--<display:column title="${colCusType}" class="${backgroundClass}">--%>
<%--<c:choose>--%>
<%--<c:when test="${item.customerType == CUSTOMER}">CUSTOMER</c:when>--%>
<%--<c:when test="${item.customerType == AGENT}">AGENT</c:when>--%>
<%--<c:when test="${item.customerType == MERCHANT}">MERCHANT</c:when>--%>
<%--<c:when test="${item.customerType == PROVIDER}">PROVIDER</c:when>--%>
<%--<c:when test="${item.customerType == PROPERTY_MANAGER}">PROPERTY MANAGER</c:when>--%>
<%--</c:choose>--%>
<%--</display:column>--%>
<%--<display:column title="${ewallet:toUpperCase(colBegin)}" class="${backgroundClass}">--%>
<%--${ewallet:formatNumber(item.reconcileSummary.firstPreBalance)}--%>
<%--</display:column>--%>
<%--<display:column title="${ewallet:toUpperCase(colLoaded)}" class="${backgroundClass}">--%>
<%--${ewallet:formatNumber(item.reconcileSummary.totalCashIn)}--%>
<%--</display:column>--%>
<%--<display:column title="${ewallet:toUpperCase(colTextin)}" class="${backgroundClass}">--%>
<%--${ewallet:formatNumber(item.reconcileSummary.totalCashOut)}--%>
<%--</display:column>--%>
<%--<display:column title="${ewallet:toUpperCase(colEnd)}" class="${backgroundClass}">--%>
<%--${ewallet:formatNumber(item.reconcileSummary.endBalance)}--%>
<%--</display:column>--%>
<%--<display:column title="${ewallet:toUpperCase(colLast)}" class="${backgroundClass}">--%>
<%--${ewallet:formatNumber(item.reconcileSummary.lastPostBalance)}--%>
<%--</display:column>--%>
<%--<display:column title="${ewallet:toUpperCase(colReconStt)}" class="${backgroundClass}">--%>
<%--<c:choose>--%>
<%--<c:when test="${isBalanceMatch}">--%>
<%--${colEqual}--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--<p style="color: red">${colNotEqual}<p>--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--</display:column>--%>
<%--&lt;%&ndash;{transactionType=, quickSearch=, range=Tat ca, idOwnerCustomerTypes=CUSTOMER}&ndash;%&gt;--%>
<%--<display:column title="${colAction}" class="action_icon center ${backgroundClass}">--%>
<%--<a href="${contextPath}<%=RECON_CONTROLLER%>/detail?quickSearch=${item.msisdn}&range=${param.range}"--%>
<%--title="<spring:message code="common.title.detail" />">--%>
<%--<i class="fa fa-info-circle" aria-hidden="true"></i>--%>
<%--</a>--%>
<%--<a href="${contextPath}<%=RECON_EXPORT%>?quickSearch=${item.msisdn}&range=${param.range}"><i--%>
<%--class="fa fa-download export_file"></i>--%>
<%--</a>--%>
<%--</display:column>--%>
<%--</display:table>--%>
<div class="mb-sm table-responsive">
  <table class="table table-bordered table-responsive table-striped"
         id="reconciliation" width="100%">
    <thead>
    <th>${colStt}</th>
    <th>${colCusCif}</th>
    <th>${colCusName}</th>
    <th>${colCusMsisdn}</th>
    <th>${colCusType}</th>
    <th>${ewallet:toUpperCase(colBegin)}</th>
    <th>${ewallet:toUpperCase(colLoaded)}</th>
    <th>${ewallet:toUpperCase(colTextin)}</th>
    <th>${ewallet:toUpperCase(colEnd)}</th>
    <th>${ewallet:toUpperCase(colLast)}</th>
    <th>${ewallet:toUpperCase(colReconStt)}</th>
    <th>${colAction}</th>
    </thead>
  </table>
</div>

<script>
  $(document).ready(function () {
    fetchData();
  });

  function checkParamExport() {
    <%--<c:if test="${param.serviceCode eq 'true'}">--%>
    <%--if (!($("select[name=serviceTypeIds]").val() != null && $("select[name=serviceTypeIds]").val().length < 2)) {--%>
    <%--$.MessageBox({message: '<spring:message code="transaction.log.alert.confirm-export.servicetype"/>'});--%>
    <%--return false;--%>
    <%--}--%>
    <%--</c:if>--%>

    var rangeDate = $("#reservation").val();
    if (rangeDate === null || rangeDate === "" || rangeDate.length === 0 || rangeDate
      === 'Tat ca') {
      $.MessageBox(
        {message: '<spring:message code="transaction.log.alert.confirm-export.daterange"/>'});
      return false;
    }
    var startRaw = rangeDate.split("-")[0];
    var endRaw = rangeDate.split("-")[1];
    var startDay = rangeDate.split("-")[0].split(" ")[0];
    var startHour = rangeDate.split("-")[0].split(" ")[1];
    var startDate = new Date(startDay.split("/")[2], startDay.split("/")[1], startDay.split("/")[0],
      startHour.split(":")[0], startHour.split(":")[1], startHour.split(":")[2], 0);

    var endDay = rangeDate.split("-")[1].split(" ")[1];
    var endHour = rangeDate.split("-")[1].split(" ")[2];
    var endDate = new Date(endDay.split("/")[2], endDay.split("/")[1], endDay.split("/")[0],
      endHour.split(":")[0], endHour.split(":")[1], endHour.split(":")[2], 0);

    var h = endDate - startDate;
    if (h > (24 * 60 * 60 * 1000 * 7)) {
      $.MessageBox(
        {message: '<spring:message code="transaction.log.alert.confirm-export.daterange"/>'});
      return false;
    } else {
      return true;
    }
  }

  function fetchData() {
    var quickSearch = $('#quickSearch').val();
    var range = $('#reservation').val();
    var idOwnerCustomerTypes = 3;
    var isBalanceNotMatch = $('#isBalanceNotMatch').val();

    $.fn.dataTable.ext.errMode = 'throw';
    $('#reconciliation').dataTable({
      "processing": true,
      "paging": true,
      "serverSide": true,
      "iDisplayStart": 0,
      "pageLength": 20,
      "lengthMenu": [[10, 20, 50, -1], [10, 20, 50, "All"]],
      "searching": false,
      "lengthChange": false,
      "language": {
        "sInfo": "${paging_showing} _START_ ${paging_to} _END_ ${paging_of} _TOTAL_ ${paging_entries}",
        "sLengthMenu": "_MENU_ ${paging_records}",
        "paginate": {
          "previous": "${paging_previous}",
          "next": "${paging_next}"
        }
      },
      dom: 'frtip',
      destroy: true,
      "ajax": {
        "url": ctx + "/ajax-controller/find-reconciliation",
        "type": "POST",
        "data": {
          quickSearch: quickSearch,
          range: range,
          idOwnerCustomerTypes: idOwnerCustomerTypes,
          isBalanceNotMatch: isBalanceNotMatch
        },
        dataSrc: 'dataList'
      },
      "createdRow": function (row, data, dataIndex) {
        if (data.balanceMatch === 'Y') {
            $(row).addClass('');
          } else {
            $(row).addClass('levelTwoWarning');
          }
      },
      "columns": [
        {
          // STT
          "data": null,
          "render": function (data, type, full, meta) {
            var index = meta.settings.oAjaxData.start + meta.row + 1;
            return index;
          }
        },
        {
          // mã kh
          "data": null,
          "render": function (data, type, full, meta) {
            return data.cif;
          }
        },

        {
          // họ và tên
          "data": null,
          "render": function (data, type, full, meta) {
            return data.name;
          }
        }
        ,
        {
          // số điện thoại
          "data": null,
          "render": function (data) {
            return data.msisdn;
          }

        }
        ,
        {
          // loại khách hàng
          "data": null,
          "render": function (data) {
            if (data.customerType == 1) {
              return 'CUSTOMER';
            }
            if (data.customerType == 2) {
              return 'AGENT';
            }
            if (data.customerType == 3) {
              return 'MERCHANT';
            }
            if (data.customerType == 11) {
              return 'PROVIDER';
            }
            if (data.customerType == 12) {
              return 'PROPERTY_MANAGER';
            }
            return '';
          }

        },
        {
          // số dư đầu kỳ
          "data": null,
          "render": function (data, type, full, meta) {
            if (data.reconcileSummary) {
              return formatCurrency(data.reconcileSummary.firstPreBalance);
            }
            return '';
          }
        },

        {
          // nạp trong kỳ
          "data": null,
          "render": function (data, type, full, meta) {
            if (data.reconcileSummary) {
              return formatCurrency(data.reconcileSummary.totalCredit);
            }
            return '';
          }
        }
        ,
        {
          // tiêu trong kỳ
          "data": null,
          "render": function (data, type, full, meta) {
            if (data.reconcileSummary) {
              return formatCurrency(data.reconcileSummary.totalDebit);
            }
            return '';
          }
        }
        ,
        {
          // số dư cuối kỳ
          "data": null,
          "render": function (data, type, full, meta) {
            if (data.reconcileSummary) {
              return formatCurrency(data.reconcileSummary.actualBalance);
            }
            return '';
          }
        }
        ,
        {
          // số dư sau gd cuối kỳ
          "data": null,
          "render": function (data, type, full, meta) {
            if (data.reconcileSummary) {
              return formatCurrency(data.reconcileSummary.lastPostBalance);
            }
            return '';
          }
        },
        {
          // trạng thái đối soát
          "data": null,
          "render": function (data, type, full, meta) {
            if (data.balanceMatch === 'Y') {
                return '${colEqual}';
              } else {
                return '<p style="color: red">' + '${colNotEqual}' + '</p>';
              }
            return '';
          }
        },
        {
          // thao tác
          "data": null,
          "render": function (data, type, full, meta) {

            return '<div class="action_icon center">'
              + '<a href="${contextPath}<%=RECON_CONTROLLER%>/detail?id=' + data.customerId
              + '&range=' + range + '"\n'
              + '       title="<spring:message code="common.title.detail" />">\n'
              + '      <i class="fa fa-info-circle" aria-hidden="true"></i>\n'
              + '    </a>\n'
              + '    <a class="export_link" href="${contextPath}<%=RECON_EXPORT%>?quickSearch='
              + data.msisdn + '&range=' + range + '"><i\n'
              + '        class="fa fa-download" onclick="return checkParamExport()"></i>\n'
              + '    </a>'
              + '</div>';
          }
        }
      ]
    })
  }
</script>