<%@ page import="vn.mog.ewallet.operation.web.controller.translog.TransactionReimController" %>
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
      <jsp:param value="reimTxn" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->

    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <c:url var="transReimURI" value="<%=TransactionReimController.TRANS_REIM_LIST%>"/>
            <form action="${transReimURI}" method="GET" id="search-transaction">
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
        <div class="container-fluid pt-md">
          <%--<div class="h4 mb-md">--%>
          <%--<spring:message code="reversal.title.cancel.the.transaction.details"/>--%>
          <%--</div>--%>
          <section class="panel search_payment panel-default">
            <div class="panel-title pl-none">
              <h4 class="fl"><spring:message code="transaction.api.detail.info"/></h4>
              <ul class="panel-tools fl tool-filter">
                <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
              </ul>
              <div class="clr"></div>
            </div>

            <div class="panel-body pt-md">
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="reim.label.transaction.id"/> </label>
                <div class="col-sm-9 col-md-10 text-normal"> ${transaction.id}12345</div>
              </div>
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.time"/> </label>
                <div class="col-sm-9 col-md-10 text-normal">
                  <fmt:formatDate value="${transaction.creationDate}" pattern="HH:mm:ss dd/MM/yyyy"/>
                </div>
              </div>

              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="reim.label.source.account"/> </label>
                <div class="col-sm-9 col-md-10 text-normal"> ${transaction.getService()} vuilachinh@gmail.com</div>
              </div>

              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="reim.label.target.account"/> </label>
                <div class="col-sm-9 col-md-10 text-normal"> ${transaction.getService()} TM SOF Wallet On Hand</div>
              </div>

              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="reim.label.transfer.amount"/></label>
                <div class="col-sm-9 col-md-10 text-normal">${amount}5.000.000 VND</div>
              </div>
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="reim.label.created.at"/></label>
                <div class="col-sm-9 col-md-10 text-normal">20:00:00 22/08/2017</div>
              </div>
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="reim.label.created.by"/></label>
                <div class="col-sm-9 col-md-10 text-normal">lienlt</div>
              </div>

              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="reim.label.created.remark"/></label>
                <div class="col-sm-9 col-md-10 text-normal">Khởi tạo yêu cầu của vân hành</div>
              </div>
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="reim.label.approved.by"/></label>
                <div class="col-sm-9 col-md-10 text-normal">anhtv</div>
              </div>
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="reim.label.approved.remark"/></label>
                <div class="col-sm-9 col-md-10 text-normal">Duyệt đề xuất</div>
              </div>
              <div class="form-group mb-none">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="reim.label.status"/></label>
                <div class="col-sm-9 col-md-10 text-normal">Success</div>
              </div>
            </div>
          </section>

          <%-- cash flow information  --%>
          <div class="clearfix"></div>
          <section class="panel search_payment panel-default">
            <div class="panel-title pl-none">
              <h4 class="fl"><spring:message code="reversal.title.cash.flow.information"/></h4>
              <ul class="panel-tools fl tool-filter">
                <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
              </ul>
              <div class="clr"></div>
            </div>
            <div class="clearfix"></div>

            <div class="panel-body pt-none table-responsive">

              <spring:message var="colStt" code="reim.table.no"/>
              <spring:message var="colUserDirection" code="reim.table.user.direction"/>
              <spring:message var="colAccountType" code="reim.table.account.type"/>
              <spring:message var="colAccountName" code="reim.table.account.name"/>
              <spring:message var="colWalletID" code="reim.table.wallet.id"/>
              <spring:message var="colTransferAmount" code="reim.table.transfer.amount"/>
              <spring:message var="colFee" code="reim.table.fee"/>
              <spring:message var="colPreBalance" code="reim.table.pre.balance"/>
              <spring:message var="colPostBalance" code="reim.table.post.balance"/>

              <div class="clearfix"></div>
              <display:table name="reimDetail" id="item"
                             requestURI="list"
                             pagesize="${pagesize}" partialList="true"
                             size="total"
                             sort="page"
                             class="table table-bordered table-striped mb-none">

                <%@ include file="../include_component/display_table.jsp" %>

                <display:column title="${colStt}">
                    <span id="row${list.id}" class="rowid">
                        <c:out value="${offset + item_rowNum}"/>
                    </span>
                </display:column>
                <display:column title="${colUserDirection}" property="service"/>
                <display:column title="${colAccountType}" property="merchant"/>
                <display:column title="${colAccountName}" property="merchant"/>
                <display:column title="${colWalletID}">
                  <a class="app-name detail-link link-active" href="#" txnId="${item.id}">
                      ${fn:substring(item.id, 0, 16)} <br/>
                      ${fn:substring(item.id, 16, 32)}
                  </a>
                </display:column>
                <display:column title="${colTransferAmount}">${ewallet:formatNumber(amount)}</display:column>
                <display:column title="${colFee}">${ewallet:formatNumber(fee)}</display:column>
                <display:column title="${colPreBalance}">${ewallet:formatNumber(preBalance)}</display:column>
                <display:column title="${colPostBalance}">${ewallet:formatNumber(postBalance)}</display:column>

              </display:table>
            </div>

          </section>
          <%--end block cash flow information--%>
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