<%@ page
        import="vn.mog.ewallet.operation.web.controller.translog.TransactionWalletUserController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="transaction.api.title.page"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/transaction-logs.css'/>"
        media="none" onload="if(media!='all')media='all'">
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
          <div class="col-xs-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span class="">Zo-Vip Transaction</span></li>
                <li><span class="nav-active">All Transaction</span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <!-- start: page -->

      <spring:message code="transaction.api.search.placeholder" var="placeholder"/>
      <spring:message code="select.choose.all" var="langChooseAll" scope="request"/>
      <spring:message code="select.status" var="langStatus" scope="request"/>
      <spring:message code="select.service" var="langService" scope="request"/>
      <spring:message code="select.merchant" var="langMerchant" scope="request"/>
      <spring:message code="select.provider" var="langProvider" scope="request"/>

      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md">
            <spring:message code="transaction.api.subnavigate.apitransaction"/>
          </div>


          <sec:authorize access="hasAnyRole('ADMIN_OPERATION','STAFF')" var="permitSearchStaff"/>
          <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_MANAGER' , 'SALESUPPORT','CUSTOMERCARE_MANAGER','CUSTOMERCARE')" var="permitAll"/>
          <sec:authorize
              access="hasAnyRole('SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM')"
              var="permitSearchSales"/>
          <sec:authorize
                  access="hasAnyRole('FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')"
                  var="permisFinance"/>
          <sec:authorize
                  access="hasAnyRole('RECONCILIATION_LEADER','RECONCILIATION')"
                  var="permisReconcile"/>

          <div class="tabs">
            <c:if test="${permitAll || permisFinance}">
              <ul class="nav nav-tabs">

                <li class="active"><a onclick="openTab('all');" href="#" data-toggle="tab">All</a>
                </li>
                <li class=""><a onclick="openTab('fund_in');" href="#">Fund In</a></li>
                <li class=""><a onclick="openTab('fund_out');" href="#">Fund Out</a></li>
                <li class=""><a onclick="openTab('p2p_wallet');" href="#">P2P Transfer</a></li>
                <li class=""><a onclick="openTab('internal_wallet');" href="#">Internal Wallet
                  Transfer</a></li>
                <li class=""><a onclick="openTab('sof_transfer');" href="#">SOF Transfer</a></li>
              </ul>
            </c:if>
            <c:if test="${permitSearchSales}">
              <ul class="nav nav-tabs">
                <li class="active"><a onclick="openTab('fund_in');" href="#">Fund In</a></li>
              </ul>
            </c:if>
            <div class="tab-content pl-none pr-none">
              <div id="tab1" class="tab-pane active">
                <c:set var="txnControlUri" scope="application">${contextPath}<%=TransactionWalletUserController.TRANSACTION_WALLET_CONTROLLER%></c:set>
                <jsp:include page="../transaction/transaction-content.jsp">
                  <jsp:param name="isUserSearchWalletSourceOfFund" value="true"/>
                  <jsp:param name="permitSearchSales" value="${permitSearchSales || permitAll || permisFinance || permisReconcile}"/>
                  <jsp:param name="transactionType" value="transaction_wallet"/>
                  <jsp:param name="permitSearchCusType" value="${permitSearchSales || permitAll}"/>
                </jsp:include>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- end: sidebar -->
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
  <jsp:param name="selCustomer" value="true"/>
  <jsp:param name="selCustomerType" value="${permitSearchSales || permitAll}"/>
  <jsp:param name="selProviderType" value="${permitSearchStaff}"/>
  <jsp:param name="selServiceType" value="true"/>
  <jsp:param name="selTxnStatus" value="true"/>
  <jsp:param name="selSourceOfFund" value="true"/>
  <jsp:param name="selWalletSourceOfFund" value="true"/>
</jsp:include>
<script src="<c:url value="/assets/development/static/js/transaction.js"/>" async></script>
<jsp:include page="js_transaction_wallet.jsp"/>
<script type="text/javascript">
  var merchants = [];
  <c:forEach var ="item" items = "${paramValues.multiselect}" >
  merchants.push('${item}');
  </c:forEach >

  var searchText = '<c:out value="${param.id }"/>';
  $('a.detail-link').click(function () {
    var txnId = $(this).attr("txnId");
    var searchURL = '';
    if (window.location.search.indexOf("?") >= 0) {
      searchURL = '${txnControlUri}/detail' + window.location.search + '&txnId=' + txnId;
    } else {
      searchURL = '${txnControlUri}/detail?' + 'txnId=' + txnId;
    }
    window.location.href = searchURL;
  });


</script>
</body>
</html>
