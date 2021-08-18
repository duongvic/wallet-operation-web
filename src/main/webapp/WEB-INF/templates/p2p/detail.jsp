<%@ page import="static vn.mog.ewallet.operation.web.controller.translog.TransactionLogController.TRANSACTION_LIST" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title>P2P Transfer Detail</title>
  <jsp:include page="../include_page/head.jsp"/>
  <jsp:include page="../include_page/js.jsp">
    <jsp:param name="tableLib" value="true"/>
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
      <jsp:param value="p2p" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->

    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                <li><span>P2P Transfer</span></li>
                <li><span class="nav-active">Detail</span>
                </li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md">
            <spring:message code="transaction.api.navigate.transaction.detail"/>
          </div>

          <jsp:include page="../transaction/transaction-logs-detail-common.jsp"/>
          <jsp:include page="../transaction/transaction-logs-detail-attribute.jsp"/>

          <c:if test="${transaction.isBuyCard()}">
            <section class="panel panel-default">
              <div class="panel-title pl-none">
                <h4 class="fl"><spring:message code="transaction.api.detail.list-item"/></h4>
                <ul class="panel-tools fl tool-filter">
                  <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
                </ul>
                <div class="clr"></div>
              </div>
              <div class="panel-body">
                <div class="table-responsive qlsp no-per-page">
                  <table class="table table-bordered table-striped mb-small mt-none">
                    <thead>
                    <tr>
                      <th class="stt"><spring:message code="transaction.api.detail.table.no"/></th>
                      <th style="width:250px"><spring:message code="transaction.api.detail.table.serial"/></th>
                      <th><spring:message code="transaction.api.detail.table.expiry"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${transaction.serials }" var="serial" varStatus="var2">
                      <tr>
                        <td>${var2.index + 1 }</td>
                        <td>${serial.serial }</td>
                        <td><fmt:formatDate value="${serial.expiredDate }" pattern="dd/MM/yyyy"/></td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </section>
          </c:if>


          <sec:authorize access="hasAnyRole('ADMIN_OPERATION',
                        'FINANCE',
                        'SALESUPPORT_LEADER', 'SALESUPPORT',
                        'RECONCILIATION','RECONCILIATION_LEADER',
                        'CUSTOMERCARE_MANAGER',
                        'SALE_ASM','SALE_DIRECTOR',
                        'TECH_SUPPORT')">
            <jsp:include page="../transaction/transaction-logs-detail-event.jsp"/>
          </sec:authorize>
        </div>
      </div>
    </section>


    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
</body>
</html>