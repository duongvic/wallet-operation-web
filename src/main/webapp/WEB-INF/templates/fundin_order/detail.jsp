<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderChannelType" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.fundin.FundInOrderController" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="fundin.header"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <jsp:include page="../include_page/js.jsp"/>
</head>

<body>
<section class="body">
  <jsp:include page="../include_page/header.jsp"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="orderfund_in" name="nav"/>
    </jsp:include>
    <c:url var="urlFundInOrderList" value="<%=FundInOrderController.FUND_IN_ORDER_LIST%>"/>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">

            <form action="${urlFundInOrderList}" method="GET" id="search-fundInOrder">
              <input type="hidden" name="quickSearch" value="${quickSearch}">
              <input type="hidden" name="range" value="${range}">

              <c:forEach var="st" items="${customerIds}">
                <input type="hidden" name="customerIds" value="${st}">
                <input type="hidden" name="multiselect" value="${st}">
              </c:forEach>

              <c:forEach var="st" items="${stage}">
                <input type="hidden" name="stage" value="${st}">
              </c:forEach>

              <c:forEach var="st" items="${channel}">
                <input type="hidden" name="channel" value="${st}">
              </c:forEach>

              <input type="hidden" name="d-49520-p" value="${numberPage}">

              <div class="page-header-left">
                <ol class="breadcrumbs">
                  <li><a href="#"><i class="fa fa-home"></i></a></li>
                  <li><span><spring:message code="fundin.header" /></span></li>
                  <li><span><a href="#" id="hight-title" class="hight-title"><spring:message code="fundin.request"/></a></span></li>
                  <li><span class="nav-active"><spring:message code="common.detail"/></span></li>
                </ol>
              </div>
            </form>

          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <section class="panel search_payment panel-default">
            <div class="panel-body">
              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="fundin.order.account"/> </label>
                <div class="col-md-3">${fundInOrders.username }</div>
              </div>
              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="fundin.order.channel"/> </label>
                <div class="col-md-3">${fundInOrders.orderChannel }</div>
              </div>

              <c:set var="channelBank" value="<%= FundOrderChannelType.BANK_TRANSFER.code %>"/>
              <c:if test="${fundInOrders.orderChannel eq channelBank }">

                <div class="form-group">
                  <label class="col-md-3 control-label"><spring:message code="fundin.order.bank"/> </label>
                  <div class="col-md-3">${fundInOrders.bankName }</div>
                </div>
                <div class="form-group">
                  <label class="col-md-3 control-label"><spring:message code="fundin.order.commandCode"/> </label>
                  <div class="col-md-3">${fundInOrders.commandCode }</div>
                </div>
                <div class="form-group">
                  <label class="col-md-3 control-label"><spring:message code="fundin.order.syntax"/> </label>
                  <div class="col-md-3">${fundInOrders.syntax }</div>
                </div>
              </c:if>

              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="fundin.order.amount"/></label>
                <div class="col-md-3">${ewallet:formatNumber(fundInOrders.amount)}&nbsp;(VNĐ)</div>
              </div>
              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="fundin.order.fee"/></label>
                <div class="col-md-6">${ewallet:formatNumber(fundInOrders.fee)}&nbsp;(VNĐ)</div>
              </div>
              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="fundin.order.totalAmount"/></label>
                <div class="col-md-6">${ewallet:formatNumber(fundInOrders.amount)}&nbsp;(VNĐ)</div>
              </div>
              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="fundin.order.balance"/></label>
                <div class="col-md-6">${ewallet:formatNumber(customerCurrentBalance)}&nbsp;(VNĐ)</div>
              </div>

              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="fundin.order.formStock"/> </label>
                <div class="col-md-6">
                  <c:forEach var="item" items="${attachments }">
                    <c:if test="${item.contentType ne 'repayment'}">
                        <p>
                            <img alt="" src="data:image/png;base64, <c:out value='${item.imageBase64}'/>" style="max-width: 90%;">
                        </p>
                        <br/>
                    </c:if>
                  </c:forEach>
                </div>
              </div>
              <!-- div show file -->
              <div class="form-group">
                <div class="col-md-3"></div>
                <div class="col-md-6 fileshow"></div>
              </div>

              <%--trạng thái nạp chịu--%><%--ngày dự kiến trả nợ--%>
              <c:choose>
                <c:when test="${fundInOrders.isDept eq true}">
              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message
                        code="label.pay"/> : </label>
                <div class="col-md-6">
                  <p style="color: red"><spring:message
                          code="popup.button.yes"/></p>
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-3 control-label">
                  <spring:message
                          code="label.fundin.request.expected.date.pay"/> :
                </label>
                <div class="col-md-2" style="display: flex">
                  <fmt:formatDate pattern="dd/MM/yyyy"
                                  value="${fundInOrders.expectedRepaymentDate}"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-3 control-label">
                  <spring:message
                          code="label.fundin.request.date.pay"/> :
                </label>
                <div class="col-md-2" style="display: flex">
                  <fmt:formatDate pattern="dd/MM/yyyy"
                                  value="${fundInOrders.repaymentDate}"/>
                </div>
              </div>
              </c:when>
              <c:otherwise>
              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message
                        code="label.pay"/> : </label>
                <div class="col-md-6">
                  <p style="color: red"><spring:message
                          code="popup.button.no"/></p>
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-3 control-label">
                  <spring:message
                          code="label.fundin.request.expected.date.pay"/> :
                </label>
                <div class="col-md-2" style="display: flex">
                </div>
              </div>
              </c:otherwise>
              </c:choose>

              <%--chứng từ--%>
              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="label.document"/></label>
                <div class="col-md-6">
                  <c:if test="${documents ne null}">
                    <c:forEach items="${documents}" var="document">
                      <a download="${document.name}" href="data:application/octet-stream;charset=utf-8;base64,${document.imageBase64}"><i class="fa fa-2x fa-download"></i>&nbsp${document.name}&nbsp${document.createdTime}</a><br>
                    </c:forEach>
                  </c:if>
                </div>
              </div>

              <%--ghi chú--%>
              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="fundorder.approve.transfer.initiateRemark"/> </label>
                <div class="col-md-3">${fundInOrders.info }</div>
              </div>
            <div class="clearfix"></div>
          </section>
        </div>
      </div>
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>

<jsp:include page="../include_page/js_footer.jsp"/>
<script type="text/javascript">
  $(document).ready(function () {
    $('#hight-title').click(function () {
      $('#search-fundInOrder').submit();
    });
  });
</script>
</body>
</html>
