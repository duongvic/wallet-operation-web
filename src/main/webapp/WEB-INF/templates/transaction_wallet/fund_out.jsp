
<%@ page import="vn.mog.ewallet.operation.web.controller.fundout.FundOutController" %>
<%@ page
        import="vn.mog.ewallet.operation.web.controller.translog.TransactionWalletUserController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="fundout.title.page"/></title>
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
  <jsp:include page="../include_component/constant_application.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="wallTrans" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span>Zo-Vip Transaction</span></li>
                <li><span class="nav-active"><spring:message code="fundout.history"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md">
            <spring:message code="fundin.history"/>
          </div>

          <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_MANAGER' , 'SALESUPPORT','CUSTOMERCARE_MANAGER','CUSTOMERCARE')" var="permitAll"/>
          <sec:authorize
                  access="hasAnyRole('SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM')"
                  var="permitSearchSales"/>

          <div class="tabs">
            <ul class="nav nav-tabs">
              <li class=""><a onclick="openTab('all');" href="#" data-toggle="tab">All</a></li>
              <li class=""><a onclick="openTab('fund_in');" href="#">Fund In</a></li>
              <li class="active"><a onclick="openTab('fund_out');" href="#">Fund Out</a></li>
              <li class=""><a onclick="openTab('p2p_wallet');" href="#">P2P Transfer</a></li>
              <li class=""><a onclick="openTab('internal_wallet');" href="#">Internal Wallet Transfer</a></li>
              <li class=""><a onclick="openTab('sof_transfer');" href="#">SOF Transfer</a></li>
            </ul>

            <div class="tab-content pl-none pr-none">
              <div id="tab1" class="tab-pane active">
<%--
                <c:set var="txnControlUri" scope="application">${contextPath}<%=TransactionWalletUserController.TRANSACTION_WALLET_CONTROLLER%></c:set>
--%>
                <jsp:include page="../fundout/fund_out_content.jsp">
                  <jsp:param name="isUserSearchWalletSourceOfFund" value="true"/>
                  <jsp:param name="permitSearchSales" value="${permitSearchSales || permitAll}"/>
                  <jsp:param name="permitSearchCusType" value="${permitSearchSales || permitAll}"/>
                  <jsp:param name="controlUri" value="${TransactionWalletUserController.TRANSACTION_WALLET_CONTROLLER}"/>

                </jsp:include>
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
  <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>
<jsp:include page="../include_component/export_excel.jsp"/>
<spring:message var="fundOutSourceLabel" code="select.fundout.source.of.fund.type"/>
<jsp:include page="../include_component/search_form_commons.jsp">
  <jsp:param name="selCustomer" value="${permitSearchSales || permitAll}"/>
  <jsp:param name="selCustomerType" value="${permitSearchSales || permitAll}"/>
  <jsp:param name="selTxnStatus" value="true"/>
  <jsp:param name="selWalletSourceOfFund" value="true"/>
  <jsp:param name="selWalletSourceOfFundLabel" value="${fundOutSourceLabel}"/>
</jsp:include>
<script src="<c:url value="/assets/development/static/js/transaction.js"/>" async></script>
<jsp:include page="js_transaction_wallet.jsp"/>
<script type="text/javascript">
  $(document).ready(function () {
    $('table#trans').dataTable({
      "paginate": false,
      "sort": true,
      "searching": false,
      "autoWidth": true
    });
    $('a.detail-link').click(function () {
      var txnId = $(this).attr("txnId");
      var searchURL = '';
      if (window.location.search.indexOf("?") >= 0) {
        searchURL = ctx + '<%=FundOutController.FUND_OUT_HISTORY_CONTROLLER%>/detail' + window.location.search + '&txnId=' + txnId;
      } else {
        searchURL = ctx + '<%=FundOutController.FUND_OUT_HISTORY_CONTROLLER%>/detail' + '?txnId=' + txnId;
      }
      window.location.href = searchURL;
    });
  });
  function ClearFields() {
    document.getElementById("search").value = "";
  }
</script>

</body>
</html>