<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="transfer.detail.movement.detail.page.header"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <jsp:include page="../include_page/js_merchant.jsp">
    <jsp:param name="tableLib" value="true"/>
  </jsp:include>
</head>
<body>
<section class="body">
  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="${nav}" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->

    <c:choose>
      <c:when test="${nav == 'FUND_TRANSFER'}">
        <spring:message code="menu.left.fund.transfer" var="labelTransfer"/>
      </c:when>
      <c:otherwise>
        <spring:message code="menu.left.wallet.transfer" var="labelTransfer"/>
      </c:otherwise>
    </c:choose>


    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                <li><span>${labelTransfer}</span></li>
                <li><span><spring:message code="transfer.detail.movement.detail.nav.detail"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md"><spring:message code="transfer.detail.genInfo"/></div>
          <jsp:include page="movement-detail-basic.jsp" />

          <sec:authorize access="hasAnyRole('ADMIN_OPERATION',
	            'SALESUPPORT_LEADER','SALESUPPORT_LEADER','SALESUPPORT','TECH_SUPPORT',
	            'RECONCILIATION','RECONCILIATION_LEADER',
	            'CUSTOMERCARE_MANAGER')">
            <jsp:include page="../transaction/transaction-logs-detail-attribute.jsp"/>
            <jsp:include page="../transaction/transaction-logs-detail-event.jsp"/>
          </sec:authorize>
        </div>
      </div>
      <!-- end: page -->
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
</body>
</html>