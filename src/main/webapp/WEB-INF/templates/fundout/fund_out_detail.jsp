<%@ page import="vn.mog.ewallet.operation.web.controller.fundout.FundOutController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="fundout.detai.title.page"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <jsp:include page="../include_page/js.jsp">
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
      <jsp:param value="fund_out" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->

    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <c:url var="urlFundOutList" value="<%=FundOutController.FUND_OUT_HISTORY_LIST%>"/>
            <form action="${urlFundOutList}" method="GET" id="search-fundout">
              <input type="hidden" name="search" value="${search}">
              <c:forEach var="st" items="${customerIds}">
                <input type="hidden" name="multiselect" value="${st}">
                <input type="hidden" name="customerIds" value="${st}">
              </c:forEach>
              <c:forEach var="st" items="${txnStatusIds}">
                <input type="hidden" name="txnStatusIds" value="${st}">
              </c:forEach>
              <input type="hidden" name="type" value="${type}">
              <input type="hidden" name="range" value="${range}">
              <input type="hidden" name="d-149386-p" value="${numberPage}">

              <div class="page-header-left">
                <ol class="breadcrumbs">
                  <li><a href="#"> <i class="fa fa-home"></i></a></li>
                  <li><span><a href="#" id=""><spring:message code="fundout.detai.navigate.fundout"/></a></span></li>
                  <li><span><a href="#" id="hight-title" class="hight-title"><spring:message code="fundout.history"/></a></span></li>
                  <li><span class="nav-active"><spring:message code="fundout.detai.navigate.fundout.detail"/></span></li>
                </ol>
              </div>
            </form>
          </div>
        </div>
      </header>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md">
              <spring:message code="transaction.api.navigate.transaction.detail"/>           
          </div>
					
					<jsp:include page="fund-out-detail-info.jsp"/>
					
          <sec:authorize access="hasAnyRole('ADMIN_OPERATION',
            'SALESUPPORT_LEADER','SALESUPPORT_MANAGER','SALESUPPORT',
            'TECH_SUPPORT',
            'RECONCILIATION','RECONCILIATION_LEADER',
            'CUSTOMERCARE_MANAGER')">
              <jsp:include page="../transaction/transaction-logs-detail-attribute.jsp"/>
              <jsp:include page="../transaction/transaction-logs-detail-event.jsp"/>
          </sec:authorize>    

        </div>
      </div>
    </section>

    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>

<jsp:include page="../include_page/js_footer.jsp"/>
<script>
  $(document).ready(function () {
    $('#hight-title').click(function () {
      $('#search-fundout').submit();
    });
  });
</script>
</body>
</html>