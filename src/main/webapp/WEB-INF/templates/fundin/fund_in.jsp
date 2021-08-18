<%@ page import="vn.mog.ewallet.operation.web.controller.fundin.FundInController" %>
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
  <jsp:include page="../include_component/constant_application.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="fund_in" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="fundin.navigate.fundin"/></span></li>
                <li><span class="nav-active"><spring:message code="fundin.navigate.fundin.history"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>
      <!-- start: page -->
      <sec:authorize access="hasAnyRole('ADMIN_OPERATION')" var="permitAll"/>
      <sec:authorize access="hasAnyRole('SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM')"
          var="permitSearchSales"/>
      <sec:authorize access="hasAnyRole('FA_MANAGER','FINANCESUPPORT_LEADER','FINANCE_LEADER','FINANCE_FA','FINANCE')"
              var="permitFinance"/>

      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md">
            <spring:message code="fundin.history"/>
          </div>
          <%--<c:set var="txnControlUri" scope="application">${contextPath}<%=FundInController.FUND_IN_HISTORY_CONTROLLER%></c:set>--%>
          <jsp:include page="fund_in_content.jsp">
            <jsp:param name="permitSearchSales" value="${permitSearchSales || permitAll || permitFinance}"/>
            <jsp:param name="controlUri" value="${FundInController.FUND_IN_HISTORY_CONTROLLER}"/>
          </jsp:include>
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
</jsp:include>
<jsp:include page="../include_component/export_excel.jsp"/>
<jsp:include page="../include_component/search_form_commons.jsp">
  <jsp:param name="selCustomer" value="true"/>
  <jsp:param name="selTxnStatus" value="true"/>
  <jsp:param name="selSourceOfFund" value="true"/>
  <jsp:param name="selCustomerType" value="true"/>
  <jsp:param name="selPaymentChannel" value="true"/>
</jsp:include>
<script src="<c:url value="/assets/development/static/js/transaction.js"/>" async></script>
<script type="text/javascript">
  $(document).ready(function () {
    $('a.detail-link').click(function () {
      var txnId = $(this).attr("txnId");
      var searchURL = '';
      if (window.location.search.indexOf("?") >= 0) {
        searchURL = ctx + '<%=FundInController.FUND_IN_HISTORY_CONTROLLER%>/detail' + window.location.search + '&txnId=' + txnId;
      } else {
        searchURL = ctx + '<%=FundInController.FUND_IN_HISTORY_CONTROLLER%>/detail?' + 'txnId=' + txnId;
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
