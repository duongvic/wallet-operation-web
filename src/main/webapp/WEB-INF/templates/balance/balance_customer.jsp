<%@ page
        import="static vn.mog.ewallet.operation.web.controller.translog.TransactionLogController.TRANSACTION_DETAIL" %>
<%@ page
        import="static vn.mog.ewallet.operation.web.controller.balance.BalanceSoftCashStatisticsController.BALANCE_CONTROLLER" %>
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

</head>
<body>
<section class="body">
    <c:set var="locale" value="${pageContext.response.locale}"/>
    <!--        ///////   header ////////-->
    <jsp:include page="../include_page/header.jsp"/>

    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="balance-cust" name="nav"/>
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
                                <li><span class="nav-active"><spring:message
                                        code="wallet.balance.submenu.balance.customer"/></span></li>
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

            <div class="content-body-wrap">
                <div class="container-fluid pt-md">
                    <section class="panel search_payment panel-default">
                        <div class="panel-body pt-md">

                            <form action="#" method="GET" id="tbl-filter" class="mb-md">
                                <div class="form-group ml-none mr-none">
                                    <div class="input-group input-group-icon">
                    <span class="input-group-addon">
                      <span class="icon" style="opacity: 0.4"><i
                              class="fa fa-search-minus"></i></span>
                    </span>
                                        <input type="text" id="search" name="search"
                                               class="form-control" placeholder="${textPlaceHolder}"
                                               value="${param.search }"/>
                                    </div>
                                </div>
                                <div class="form-inline">

                                    <jsp:include page="../include_component/date_range.jsp"/>

                                    <div class="pull-right form-responsive bt-plt">
                                        <c:set var="allStatus" value=","/>
                                        <c:forEach var="st" items="${paramValues.customerTypeIds}">
                                            <c:set var="allStatus" value="${allStatus}${st},"/>
                                        </c:forEach>
                                        <select name="customerTypeIds" id="customerTypeIds"
                                                class="form-control hidden" multiple="multiple">
                                            <c:out value="testing value"/>
                                            <c:forEach var="item" items="${customerTypeIds}">
                                                <c:set var="status2" value=",${item.key},"/>
                                                <option ${fn:contains(allStatus, status2)?'selected':'' }
                                                        value="${item.key}"><spring:message
                                                        code="${item.value}"/></option>
                                            </c:forEach>
                                        </select>


                                        <c:set var="allStatus" value=","/>
                                        <c:forEach var="st" items="${paramValues.userTypeIds}">
                                            <c:set var="allStatus" value="${allStatus}${st},"/>
                                        </c:forEach>
                                        <select name="userTypeIds" id="userTypeIds"
                                                class="form-control hidden" multiple="multiple">
                                            <c:out value="testing value"/>
                                            <c:forEach var="item" items="${userTypeIds}">
                                                <c:set var="status2" value=",${item.key},"/>
                                                <option ${fn:contains(allStatus, status2)?'selected':'' }
                                                        value="${item.key}"><spring:message
                                                        code="${item.value}"/></option>
                                            </c:forEach>
                                        </select>


                                        <c:set var="allStatus" value=","/>
                                        <c:forEach var="st" items="${paramValues.walletTypeIds}">
                                            <c:set var="allStatus" value="${allStatus}${st},"/>
                                        </c:forEach>
                                        <select name="walletTypeIds" id="walletTypeIds"
                                                class="form-control hidden" multiple="multiple">
                                            <c:out value="testing value"/>
                                            <c:forEach var="item" items="${walletTypeIds}">
                                                <c:set var="status2" value=",${item.key},"/>
                                                <option ${fn:contains(allStatus, status2)?'selected':'' }
                                                        value="${item.key}"><spring:message
                                                        code="${item.value}"/></option>
                                            </c:forEach>
                                        </select>


                                        <c:set var="allStatus" value=","/>
                                        <c:forEach var="st" items="${paramValues.status}">
                                            <c:set var="allStatus" value="${allStatus}${st},"/>
                                        </c:forEach>
                                        <select class="form-control" name="status"
                                                multiple="multiple" id="status">
                                            <c:forEach var="item" items="${statuses}">
                                                <c:set var="status2" value=",${item.key},"/>
                                                <option ${fn:contains(allStatus, status2)?'selected':'' }
                                                        value="${item.key}"><spring:message
                                                        code="${item.value}"/></option>
                                            </c:forEach>
                                        </select>

                                        <%--<input type="submit" value="${btnSearch}" class="btn btn-primary bt-search"/>--%>
                                        <button type="submit" class="btn btn-primary ml-tiny">
                                            <i class="fa fa-search"></i>&nbsp; <spring:message
                                                code="common.btn.search"/>
                                        </button>
                                        <a href="#" class="btn btn-default mt-none bt-export-rsp"
                                           onclick="exportAll('xls');">
                                            <i class="fa fa-download "></i>&nbsp;<spring:message
                                                code="fundin.search.btn.export"/>
                                        </a>
                                    </div>
                                </div>
                            </form>

                            <div class="clearfix"></div>
                            <div style="margin-top: 3px;">
                                <spring:message code="wallet.balance.totalAccount"/>&nbsp; <span
                                    class="primary_color text-semibold">${ewallet:formatNumber(total)}</span>&nbsp;|&nbsp;
                                <spring:message code="wallet.balance.totalBalance"/>&nbsp; <span
                                    class="primary_color text-semibold">${totalUserBalance}</span>&nbsp;
                                <spring:message code="fundin.table.header.currency"/>
                            </div>
                            <c:url var="balanCon" value="<%=BALANCE_CONTROLLER%>"/>

                            <spring:message var="colNo" code="balance.customer.table.no"/>
                            <spring:message var="colFullname"
                                            code="balance.customer.table.fullname"/>
                            <spring:message var="colCif" code="balance.customer.table.cif"/>
                            <spring:message var="colPhoneNumber"
                                            code="balance.customer.table.phone.number"/>
                            <spring:message var="colCurBalance"
                                            code="balance.customer.table.currenBalance"/>
                            <spring:message var="colCustomerType"
                                            code="balance.customer.table.customertype"/>
                            <spring:message var="colAction" code="balance.customer.table.action"/>
                            <spring:message var="actDetail"
                                            code="balance.customer.table.action.detail"/>
                            <spring:message var="colStatus" code="balance.customer.table.satus"/>

                            <div class="clearfix"></div>
                            <display:table name="customers" id="item"
                                           requestURI="balance-customer"
                                           pagesize="${pagesize}" partialList="true"
                                           size="total"
                                           sort="page"
                                           class="table table-bordered table-striped mb-none">

                                <%@ include file="../include_component/display_table.jsp" %>

                                <display:column title="${colNo}">
                <span id="row${item.id}" class="rowid">
                  <c:out value="${offset + item_rowNum}"/>
                </span>
                                </display:column>


                                <display:column title="${colFullname}" property="fullName"/>
                                <display:column title="${colCif}" property="cif"/>
                                <display:column title="${colPhoneNumber}" property="msisdn"/>
                                <display:column title="${colCurBalance}" class="col-number-header"
                                                headerClass="col-number-header">${ewallet:formatNumber(item.balance)}</display:column>
                                <display:column title="${colCustomerType}"><spring:message code="${item.getCustomerTypeDisplayName()}"/></display:column>
                                <%--<display:column title="${colCustomerType}"--%>
                                                <%--property="${item.getCustomerTypeDisplayName()}"/>--%>
                                <%--<display:column title="${colAction}">--%>
                                <%--<a title="${actDetail}" class="detail-link link-active" href="${balanCon}/customer/detail?traceNo=${item.getLastTransactionIdValue()} ">${item.getLastTransactionIdValue()}</a>--%>
                                <%--</display:column>--%>
                                <display:column title="${colStatus}">
                                    <c:if test="${item.active eq true}"><spring:message
                                            code="label.user.active"/>
                                    </c:if>
                                    <c:if test="${item.active ne true}"><spring:message
                                            code="label.user.inactive"/>
                                    </c:if>
                                    <%--<spring:message code="${item.active}"/></display:column>--%>
                                </display:column>
                            </display:table>
                        </div>
                    </section>
                </div>
            </div>
            <!-- end:block2 -->

            <!-- end: page -->
        </section>
        <jsp:include page="../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_page/date_picker.jsp">
    <jsp:param name="isFullTime" value="true"/>
  <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>

<script type="text/javascript">
  $('#customerTypeIds').multiselect({
    includeSelectAllOption: true,
    selectAllValue: '',
    selectAllText: '<spring:message code="select.choose.all" />',
    maxHeight: 400,
    dropUp: true,
    nonSelectedText: '<spring:message code="select.choose.account" />',
    inheritClass: true,
    numberDisplayed: 1
  });

  $('#userTypeIds').multiselect({
    includeSelectAllOption: true,
    selectAllValue: '',
    selectAllText: '<spring:message code="select.choose.all" />',
    maxHeight: 400,
    dropUp: true,
    nonSelectedText: '<spring:message code="select.choose.usertype" />',
    inheritClass: true,
    numberDisplayed: 1
  });

  $('#walletTypeIds').multiselect({
    includeSelectAllOption: true,
    selectAllValue: '',
    selectAllText: '<spring:message code="select.choose.all" />',
    maxHeight: 400,
    dropUp: true,
    nonSelectedText: '<spring:message code="select.choose.wallettype" />',
    inheritClass: true,
    numberDisplayed: 1
  });

  $('#status').multiselect({
    includeSelectAllOption: true,
    selectAllValue: '',
    selectAllText: '<spring:message code="select.choose.all" />',
    maxHeight: 400,
    dropUp: true,
    nonSelectedText: '<spring:message code="select.choose.status" />',
    inheritClass: true,
    numberDisplayed: 1
  });

  $('a.detail-link').click(function () {
    var txnId = $(this).attr("txnId");
    var searchURL = '';
    if (window.location.search.indexOf("?") >= 0) {
      searchURL = ctx + '<%=TRANSACTION_DETAIL%>' + window.location.search + '&txnId=' + txnId;
    } else {
      searchURL = ctx + '<%=TRANSACTION_DETAIL%>' + '?txnId=' + txnId;
    }
    window.location.href = searchURL;
  });

  function downloadCSV(csv, filename) {
    var csvFile;
    var downloadLink;

    csvFile = new Blob([csv], {type: "text/csv"});

    downloadLink = document.createElement("a");
    downloadLink.download = filename;
    downloadLink.href = window.URL.createObjectURL(csvFile);
    downloadLink.style.display = "none";

    document.body.appendChild(downloadLink);

    downloadLink.click();
  }

  function exportTableToCSV(filename) {
    var csv = [];
    var rows = document.querySelectorAll("table tr");

    for (var i = 0; i < rows.length; i++) {
      var row = [], cols = rows[i].querySelectorAll("td, th");
      for (var j = 0; j < cols.length; j++) {
        row.push(cols[j].innerText);

        csv.push(row.join(","));
      }

      // download csv file
      downloadCSV(csv.join("\n"), filename);
    }
  }

  function exportTo(type) {
    $('.table').tableExport({
      filename: 'table_%DD%-%MM%-%YY%',
      format: type,
      cols: '2,3,4'
    });

  }

  function exportAll(type) {
    $('.table').tableExport({
      filename: 'BALANCE CUSTOMER_%DD%-%MM%-%YY%',
      format: type
    });
  }
</script>

</body>
</html>