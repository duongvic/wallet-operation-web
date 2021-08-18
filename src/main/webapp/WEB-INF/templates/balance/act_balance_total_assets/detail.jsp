<%@ page
    import="static vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController.BALANCE_TOTAL_OF_ASSETS_HISTORY_CREATE" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController.BALANCE_TOTAL_OF_ASSETS_HISTORY" %><%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 5/20/2020
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title>Số dư tổng tài sản</title>
  <jsp:include page="../../include_page/head.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <jsp:include page="../../include_page/js.jsp">
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
    <jsp:param name="dateLib" value="true"/>
  </jsp:include>
  <style>
  </style>
</head>

<body>
<section class="body">
  <!--        ///////   header ////////-->
  <jsp:include page="../../include_page/header.jsp"/>
  <jsp:include page="../../include_component/constant_application.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../../include_page/navigation.jsp">
      <jsp:param value="balance-total-assets" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->

    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                <li><span>Nhà cung cấp</span></li>
                <li><span>Hỗ trợ vận hành</span></li>
                <li><a class="nav-active" href="${contextPath}<%=BALANCE_TOTAL_OF_ASSETS_HISTORY%>">Số
                  dư tổng tài sản</a></li>
                <li><span class="nav-active">Chi tiết</span>
                </li>
              </ol>
            </div>
          </div>
        </div>
      </header>
      <jsp:include page="../../include_page/message.jsp"/>
      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md">
            <spring:message code="transaction.api.navigate.transaction.detail"/>
          </div>
          <section class="panel panel-default">
            <div class="panel-body">

              <form class="col-md-6">
                <c:forEach var="item" items="${detailBankBalanceStatistic}">
                  <div class="form-group row">
                    <label class="col-sm-6 col-form-label">Tên ngân hàng</label>
                    <div class="col-sm-6">
                      <p>${item.bankAccount.bankCode}</p>
                    </div>
                  </div>

                  <div class="form-group row">
                    <label class="col-sm-6 col-form-label">Số tài khoản</label>
                    <div class="col-sm-6">
                      <p>${item.bankAccount.bankAccount}</p>
                    </div>
                  </div>

                  <div class="form-group row">
                    <label class="col-sm-6 col-form-label"><spring:message
                        code="label.time.incurred.beginning.balance"/></label>
                    <div class="col-sm-6">
                      <p><fmt:formatDate pattern="HH:mm dd-MM-yyyy"
                                         value="${item.balanceBeginOfPeriod.txnDate}"/></p>
                    </div>
                  </div>

                  <div class="form-group row">
                    <label class="col-sm-6 col-form-label"><spring:message code="label.beginning.balance"/></label>
                    <div class="col-sm-6">
                      <p>${ewallet:formatNumber(item.balanceBeginOfPeriod.postBalance)}</p>
                    </div>
                  </div>

                  <div class="form-group row">
                    <label class="col-sm-6 col-form-label"><spring:message
                        code="label.time.arises.ending.balance"/></label>
                    <div class="col-sm-6">
                      <p><fmt:formatDate pattern="HH:mm dd-MM-yyyy"
                                         value="${item.balanceEndOfPeriod.txnDate}"/></p>
                    </div>
                  </div>

                  <div class="form-group row">
                    <label class="col-sm-6 col-form-label"><spring:message code="label.ending.balance"/></label>
                    <div class="col-sm-6">
                      <p>${ewallet:formatNumber(item.balanceEndOfPeriod.postBalance)}</p>
                    </div>
                  </div>

                  <div class="form-group row">
                    <label class="col-sm-6 col-form-label"><spring:message code="label.difference"/></label>
                    <div class="col-sm-6">
                      <p>${ewallet:formatNumber(item.balanceEndOfPeriod.postBalance-item.balanceBeginOfPeriod.postBalance)}</p>
                    </div>
                  </div>

                </c:forEach>
              </form>
            </div>
          </section>
        </div>
      </div>
    </section>


    <jsp:include page="../../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../../include_page/js_footer.jsp"/>
</body>

</html>

