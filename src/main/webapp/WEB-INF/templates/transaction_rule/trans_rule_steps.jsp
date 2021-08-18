<%@ page import="vn.mog.ewallet.operation.web.controller.translog.TransactionRuleController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="system.tranrule.navigate.tranrule.detail"/></title>
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
      <jsp:param value="tranRule" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->

    <c:url var="tranRuleList" value='<%=TransactionRuleController.TRANS_RULE_LIST%>' />
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <form action="${tranRuleList}" method="GET" id="search-transaction">
              <input type="hidden" name="quickSearch" value="${quickSearch}">
              <c:forEach var="st" items="${service}">
                <input type="hidden" name="service" value="${st}">
              </c:forEach>
              <input type="hidden" name="d-49489-p" value="${numberPage}">

              <div class="page-header-left">
                <ol class="breadcrumbs">
                  <li><a href="#"> <i class="fa fa-home"></i></a></li>
                  <li><span><spring:message code="system.tranrule.navigate.system"/></span></li>
                  <li><span><a href="#" id="hight-title" class="hight-title"><spring:message code="system.tranrule.navigate.tranrule"/></a></span></li>
                  <li><span class="nav-active"><spring:message code="system.tranrule.navigate.tranrule.detail"/> </span>
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
          <div class="h4 mb-md">
            <spring:message code="system.tranrule.navigate.tranrule.detail"/>
          </div>
          <section class="panel panel-default">
            <div class="form-group mb-md">
              <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.tranrule.detail.rule-code"/> </label>
              <div class="col-sm-9 col-md-10 text-normal"> ${tranRule.code}</div>
            </div>
            <div class="form-group mb-md">
              <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.tranrule.detail.rule-name"/> </label>
              <div class="col-sm-9 col-md-10 text-normal"> ${tranRule.name} </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.tranrule.detail.servicetype"/> </label>
              <div class="col-sm-9 col-md-10 text-normal">${tranRule.serviceType} </div>
            </div>
          </section>

          <sec:authorize access="hasAnyRole('ADMIN_OPERATION','ADMIN_SYSTEM')">
            <section class="panel search_payment panel-default">
              <div class="panel-title">
                <h4 class="fl"><spring:message code="system.tranrule.detail.list-step"/></h4>
                <ul class="panel-tools fl tool-filter">
                  <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
                </ul>
                <div class="clr"></div>
              </div>
              <div class="panel-body">
                <div class="table-responsive qlsp no-per-page">
                  <table class="table table-bordered table-striped mb-none mt-none">
                    <thead>
                    <tr>
                      <th class="stt"><spring:message code="system.tranrule.list-step.no"/></th>
                      <th style="width: 10%;"><spring:message code="system.tranrule.detail.list-step.order"/></th>
                      <th style="width: 5%;"><spring:message code="system.tranrule.detail.list-step.eventtype"/></th>
                      <th><spring:message code="system.tranrule.detail.list-step.sourcedest"/></th>
                      <th style="width: 5%;"><spring:message code="system.tranrule.detail.list-step.sourceOfFund"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${tranRuleSteps}" var="step" varStatus="var2">
                      <tr>
                        <td>${var2.index + 1}</td>
                        <td>${step.order}</td>
                        <td>${step.eventType}</td>
                        <td style="padding: 0px">
                          <table class="table table-bordered table-striped mb-none mt-none">
                            <tr ${(var2.index + 1)%2==0 ? 'style="background-color: #ffffff;"' : ''}>
                              <td WIDTH="50%">CUSTOMER CIF: <span class="${step.getSource() eq '' ? '' : 'primary_color'}">${step.getSource() eq '' ? 'n/a' : step.getSource()}</span></td>
                              <td>CUSTOMER CIF: <span class="${step.getDest() eq '' ? '' : 'primary_color'}">${step.getDest() eq '' ? 'n/a' : step.getDest()}</span></td>
                            </tr>

                            <%--<tr ${(var2.index + 1)%2==0 ? 'style="background-color: #ffffff;"' : ''}>
                              <td>CUSTOMER TYPE: <span class="${step.getSourceType() eq '' ? '' : 'primary_color'}">${step.getSourceType() eq '' ? 'n/a' : step.getSourceType()}</span></td>
                              <td>CUSTOMER TYPE: <span class="${step.getDestType() eq '' ? '' : 'primary_color'}">${step.getDestType() eq '' ? 'n/a' : step.getDestType()}</span></td>
                            </tr>--%>

                            <tr ${(var2.index + 1)%2==0 ? 'style="background-color: #ffffff;"' : ''}>
                              <%--<td>CUSTOMER MAP WITH TXN PAYER OR PAYEE: <span class="${step.getSourceMapWithTxnPayerOrPayee() eq '' ? '' : 'primary_color'}">${step.getSourceMapWithTxnPayerOrPayee() eq '' ? 'n/a' : step.getSourceMapWithTxnPayerOrPayee()}</span></td>
                              <td>CUSTOMER MAP WITH TXN PAYER OR PAYEE: <span class="${step.getDestMapWithTxnPayerOrPayee() eq '' ? '' : 'primary_color'}">${step.getDestMapWithTxnPayerOrPayee() eq '' ? 'n/a' : step.getDestMapWithTxnPayerOrPayee()}</span></td>--%>

                                <td>CUSTOMER MAP WITH TXN PAYER OR PAYEE: <span class="${step.getSourceMapWithTxnPayerOrPayee() eq '' ? '' : 'primary_color'}">${step.getSourceMapWithTxnPayerOrPayee() eq '' ? 'n/a' : step.isSourceCif}</span></td>
                                <td>CUSTOMER MAP WITH TXN PAYER OR PAYEE: <span class="${step.getDestMapWithTxnPayerOrPayee() eq '' ? '' : 'primary_color'}">${step.getDestMapWithTxnPayerOrPayee() eq '' ? 'n/a' : step.isDestCif}</span></td>
                            </tr>
                          </table>
                        </td>
                        <td>${step.sourceOfFundType}</td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </section>
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
      $('#search-transaction').submit();
    });
  });
</script>
</body>
</html>