<%@ page import="vn.mog.ewallet.operation.web.controller.translog.TransactionReversalController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="common.transaction.cancel"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <jsp:include page="../include_page/js.jsp"/>
</head>

<body>
<section class="body">
  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="reversalTxn" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->

    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <c:url var="transReversalURI" value="<%=TransactionReversalController.TRANS_REVERSAL_LIST%>"/>
            <form action="${transReversalURI}" method="GET" id="search-transaction">
              <input type="hidden" name="quickSearch" value="${quickSearch}">
              <c:forEach var="st" items="${merchants}">
                <input type="hidden" name="merchants" value="${st}">
                <input type="hidden" name="multiselect" value="${st}">
              </c:forEach>
              <c:forEach var="st" items="${status}">
                <input type="hidden" name="status" value="${st}">
              </c:forEach>
              <input type="hidden" name="type" value="${type}">
              <c:forEach var="st" items="${service}">
                <input type="hidden" name="service" value="${st}">
              </c:forEach>
              <c:forEach var="st" items="${provider}">
                <input type="hidden" name="provider" value="${st}">
              </c:forEach>

              <input type="hidden" name="range" value="${range}">
              <input type="hidden" name="d-49489-p" value="${numberPage}">

              <div class="page-header-left">
                <ol class="breadcrumbs">
                  <li><a href="#"> <i class="fa fa-home"></i></a></li>
                  <li><span><spring:message code="transaction.api.navigate.transaction"/></span></li>
                  <li><span><a href="#" id="hight-title" class="hight-title"><spring:message code="common.transaction.cancel"/></a></span></li>
                  <%--<li><span class="nav-active"><spring:message code="common.title.detail"/></span>--%>
                  </li>
                </ol>
              </div>
            </form>
          </div>
        </div>
      </header>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <%--<div class="h4 mb-md">--%>
          <%--<spring:message code="reversal.title.cancel.the.transaction.details"/>--%>
          <%--</div>--%>
          <section class="panel panel-default">
            <div class="panel-title">
              <h4 class="fl"><spring:message code="transaction.api.detail.info"/></h4>
              <ul class="panel-tools fl tool-filter">
                <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
              </ul>
              <div class="clr"></div>
            </div>

            <div class="panel-body report_search_form">
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="reversal.detail.order.id"/> </label>
                <div class="col-sm-9 col-md-10 text-normal"> ${tranReversal.id}</div>
              </div>
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.time"/> </label>
                <div class="col-sm-9 col-md-10 text-normal">
                  <fmt:formatDate value="${tranReversal.creationDate}" pattern="HH:mm:ss dd/MM/yyyy"/>
                </div>
              </div>

              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.servicetype"/>:</label>
                <div class="col-sm-9 col-md-10 text-normal"> ${tranReversal.transaction.serviceShortName}</div>
              </div>


              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.amount"/></label>
                <div class="col-sm-9 col-md-10 text-normal"> ${ewallet:formatNumber(tranReversal.transaction.amount)}&nbsp;(VNĐ)</div>
              </div>
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.fee"/></label>
                <div class="col-sm-9 col-md-10 text-normal"> ${ewallet:formatNumber(tranReversal.transaction.fee)}&nbsp;(VNĐ)</div>
              </div>
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.commission"/></label>
                <div class="col-sm-9 col-md-10 text-normal"> ${ewallet:formatNumber(tranReversal.transaction.commision)}&nbsp;(VNĐ)</div>
              </div>

              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.preBalance"/>:</label>
                <div class="col-sm-9 col-md-10 text-normal">${ewallet:formatNumber(tranReversal.transaction.preBalance)}&nbsp;(VNĐ)</div>
              </div>
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.postBalance"/>:</label>
                <div class="col-sm-9 col-md-10 text-normal">${ewallet:formatNumber(tranReversal.transaction.postBalance)}&nbsp;(VNĐ)</div>
              </div>

              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="reversal.requestor"/></label>
                <div class="col-sm-9 col-md-10 text-normal">${tranReversal.requestor}</div>
              </div>
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="reversal.approver"/></label>
                <div class="col-sm-9 col-md-10 text-normal">${tranReversal.approver}</div>
              </div>
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="reim.label.remark"/></label>
                <div class="col-sm-9 col-md-10 text-normal">${tranReversal.remark}</div>
              </div>
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.status"/>:</label>
                <div class="col-sm-9 col-md-10 text-normal">
                  <spring:message code="reversal.states.${tranReversal.stage}"/>
                </div>
              </div>
            </div>
          </section>
          <%-- cash flow information  --%>
            <jsp:include page="transaction-reversal-logs-detail-attribute.jsp"/>

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
      $('#search-transaction').submit();
    });
  });
</script>

</body>
</html>