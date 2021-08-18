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
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/transaction-logs.css'/>" media="none" onload="if(media!='all')media='all'">
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
      <jsp:param value="hisTxn" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span class="nav-active"><spring:message code="transaction.api.navigate.transaction"/></span></li>
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

          <div class="tabs">
            <ul class="nav nav-tabs">
              <li class="active"><a onclick="openTab('');" href="#" data-toggle="tab">All</a></li>
              <li class=""><a onclick="openTab('ZPOINT');" href="#">Wallet</a></li>
              <li class=""><a onclick="openTab('BANK');" href="#">Local Bank</a></li>
              <li class=""><a onclick="openTab('CARD');" href="#">International</a></li>
            </ul>

            <div class="tab-content pl-none pr-none">
              <div id="tab1" class="tab-pane active">
                <sec:authorize access="hasAnyRole('ADMIN_OPERATION')" var="permitAll"/>
                <sec:authorize access="hasAnyRole('ADMIN_OPERATION', 'STAFF')" var="permitSearchStaff"/>
                <sec:authorize access="hasAnyRole('ADMIN_OPERATION', 'SALE_DIRECTOR','SALE_EXCUTIVE',
                'SALE_SUPERVISOR','SALE_ASM','SALE_RSM','CUSTOMERCARE_MANAGER','CUSTOMERCARE')" var="permitSearchSales"/>
                <jsp:include page="transaction-content.jsp">
                  <jsp:param name="isUserSearchSourceOfFundType" value="true"/>
                  <jsp:param name="permitSearchStaff" value="${permitSearchStaff}"/>
                  <jsp:param name="permitSearchSales" value="${permitSearchSales || permitAll}"/>
                  <jsp:param name="permitSearchCusType" value="${permitSearchSales || permitAll}"/>
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
  <jsp:param name="isFullTime" value="true"/>
  <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>
<jsp:include page="../include_component/export_excel.jsp">
  <jsp:param name="serviceCode" value="true"/>
</jsp:include>
<jsp:include page="../include_component/search_form_commons.jsp">
  <jsp:param name="selCustomer" value="${permitSearchSales || permitAll}"/>
  <jsp:param name="selCustomerType" value="${permitSearchSales || permiAll}"/>
  <jsp:param name="selProviderType" value="${permitSearchStaff}"/>
  <jsp:param name="selServiceType" value="true"/>
  <jsp:param name="selTxnStatus" value="true"/>
  <jsp:param name="selSourceOfFund" value="true"/>
  <jsp:param name="paymentChannelId" value="true"/>
</jsp:include>
<script src="<c:url value="/assets/development/static/js/transaction.js"/>" async></script>
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

  function openTab(paramValue) {
    var searchURL = '';
    if (window.location.search.indexOf("?") >= 0) {
      if (window.location.search.indexOf("sourceOfFundTypeIds") >= 0) {
        searchURL = '${prefixSearchChildUrl}' + replaceUrlParam(window.location.search, 'sourceOfFundTypeIds', paramValue);
      } else {
        searchURL = '${prefixSearchChildUrl}' + window.location.search + '&sourceOfFundTypeIds=' + paramValue;
      }

    } else {
      searchURL = '${prefixSearchChildUrl}' + '?sourceOfFundTypeIds=' + paramValue;
    }
    window.location.href = searchURL;
  }

</script>
</body>
</html>
