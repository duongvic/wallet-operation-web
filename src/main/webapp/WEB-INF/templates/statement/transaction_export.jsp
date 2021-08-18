<%@ page import="vn.mog.ewallet.operation.web.controller.fundin.FundInController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.statement.StatementController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
    <!-- Basic -->
    <meta charset="UTF-8">
    <title><spring:message code="fundin.title.page"/></title>
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <jsp:include page="../include_page/js_merchant.jsp">
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
</head>

<body>
<section class="body">
    <!--        ///////   header ////////-->
    <jsp:include page="../include_page/header.jsp"/>
    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="statement" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><span><a href="#" id="hight-title"
                                             class="hight-title">Statement</a></span></li>
                                <li><span class="nav-active">User Transaction Log</span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>
            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">
                    <div class="h4 mb-md" id="nav-label">
                        User Transaction Log
                    </div>
                    <section class="panel search_payment panel-default">
                        <div class="panel-body pt-none">
                            <form action="" method="GET" id="tbl-filter" class="mb-md"
                                  modelAttribute="searchDataForm">
                                <input type="hidden" name="customerId" value="${customerId}">


                                <div class="form-group ml-none mr-none"></div>

                                <div class="form-inline">

                                    <jsp:include page="../include_component/date_range.jsp"/>

                                    <div class='pull-right form-responsive bt-plt'>

                                        <jsp:include
                                                page="../include_component/search_source_of_fund_type.jsp"/>

                                        <jsp:include
                                                page="../include_component/search_provider_type.jsp"/>

                                        <jsp:include
                                                page="../include_component/search_service_type_multiple.jsp">
                                            <jsp:param name="enableFiltering" value="false"/>
                                        </jsp:include>


                                        <c:set var="allPayType" value=","/>
                                        <c:forEach var="item" items="${paramValues.paytypes}">
                                            <c:set var="allPayType" value="${allPayType}${item},"/>
                                        </c:forEach>
                                        <select class="form-control multiple-select hidden"
                                                multiple="multiple" id="paytypes" name="paytypes">
                                            <option ${fn:contains(allPayType, '0') ? 'selected' : ''}
                                                    value="0">Payer
                                            </option>
                                            <option ${fn:contains(allPayType, '1') ? 'selected' : ''}
                                                    value="1">Payee
                                            </option>
                                        </select>

                                        <script type="application/javascript">
                                          $('#paytypes').multiselect({
                                            includeSelectAllOption: true,
                                            selectAllValue: '',
                                            selectAllText: '<spring:message code="select.choose.all"/>',
                                            maxHeight: 400,
                                            dropUp: true,
                                            nonSelectedText: '<spring:message code="select.status"/>',
                                            inheritClass: true,
                                            numberDisplayed: 1
                                          });
                                        </script>

                                        <button type="submit" class="btn btn-primary"><i
                                                class="fa fa-search"></i>&nbsp;<spring:message
                                                code="transaction.api.button.search"/></button>
                                        <a href="#"
                                           data-url="${contextPath}/wallet/user/statement/user-trans/list/export"
                                           id="exportStatement"
                                           class="btn btn-default"><i class="fa fa-download "></i>&nbsp;<spring:message
                                                code="transaction.log.export"/></a>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                            </form>

                            <spring:message code="transaction.api.table.currency"
                                            var="tCurrencyType"/>
                            <div>
                                <spring:message code="transaction.api.navigate.transaction"/>&nbsp;
                                <span class="primary_color text-semibold">${ewallet:formatNumber(total)}</span>&nbsp;|&nbsp;

                                <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR',
                                      'SALESUPPORT_LEADER', 'SALESUPPORT', 'FINANCE',
                                      'RECONCILIATION', 'RECONCILIATION_LEADER')">
                                    <spring:message
                                            code="transaction.log.capitalValue"/>&nbsp;<span class="primary_color text-semibold">${ewallet:formatNumber(totalCapitalValue)}</span>&nbsp;${tCurrencyType}&nbsp;|&nbsp;
                                </sec:authorize>
                                <spring:message code="transaction.log.amount"/>&nbsp;<span
                                    class="primary_color text-semibold">${ewallet:formatNumber(realAmount)}</span>&nbsp;${tCurrencyType}&nbsp;|&nbsp;
                                <spring:message code="transaction.log.commission"/>&nbsp;<span
                                    class="primary_color text-semibold">${ewallet:formatNumber(commision)}</span>&nbsp;${tCurrencyType}
                            </div>

                            <div class="clearfix"></div>

                            <jsp:include page="../transaction/transaction_table.jsp"/>
                        </div>
                    </section>
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
    <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>
<jsp:include page="../include_component/export_excel.jsp">
    <jsp:param name="serviceCode" value="true"/>
</jsp:include>
<jsp:include page="../include_component/search_form_commons.jsp">
    <jsp:param name="selSourceOfFund" value="true"/>
    <jsp:param name="selProviderType" value="true"/>
    <jsp:param name="selTxnStatus" value="true"/>
    <jsp:param name="selServiceType" value="true"/>
</jsp:include>
<script src="<c:url value="/assets/development/static/js/transaction.js"/>" async></script>
<script type="text/javascript">

  $(document).ready(function () {
    var displayName_statement = getCookie('statement_user');
    $('#nav-label').text($('#nav-label').text() + ': ' + displayName_statement);

    $('a.detail-link').click(function () {
      var txnId = $(this).attr("txnId");
      var searchURL = '';
      if (window.location.search.indexOf("?") >= 0) {
        searchURL = ctx + '<%=FundInController.FUND_IN_HISTORY_CONTROLLER%>/detail'
            + window.location.search + '&txnId=' + txnId;
      } else {
        searchURL = ctx + '<%=FundInController.FUND_IN_HISTORY_CONTROLLER%>/detail?' + 'txnId='
            + txnId;
      }
      window.location.href = searchURL;
    });

    $('#hight-title').click(function () {
      window.location.href = ctx + '<%=StatementController.STATEMENT_LIST%>';
    });

    $('#exportStatement').click(function (e) {
      e.preventDefault();
      if (checkParamExport()) {
        var url = $(this).data("url");
        $('#tbl-filter').attr('action', url).submit();
      }
    });
  });

</script>
</body>
</html>
