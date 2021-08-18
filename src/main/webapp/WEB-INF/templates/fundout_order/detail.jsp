<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderChannelType" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="fundout.order.title.page"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <jsp:include page="../include_page/js.jsp"/>

</head>

<body>
<section class="body">
  <jsp:include page="../include_page/header.jsp"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="orderfund_out" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><a href="list" id="hight-title" class="hight-title"><spring:message code="fundout.title.page"/></a></span></li>
                <li><span><spring:message code="common.title.detail"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="mb-md">
            <section class="panel search_payment panel-default">
              <div class="panel-body">
                <div class="form-group">
                  <label class="col-md-3 control-label"><spring:message code="fundin.order.account"/> </label>
                  <div class="col-md-3"><p>${fundInOrders.username }</p></div>
                </div>
                <div class="form-group">
                  <label class="col-md-3 control-label"><spring:message code="fundin.order.channel"/> </label>
                  <div class="col-md-3"><span>${fundInOrders.orderChannel }</span></div>
                </div>
                <c:set var="channelBank" value="<%= FundOrderChannelType.BANK_TRANSFER.code %>"/>
                <c:if test="${fundInOrders.orderChannel eq channelBank }">

                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.bank"/> </label>
                    <div class="col-md-3"><span>${fundInOrders.bankName }</span></div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.bank"/> </label>
                    <div class="col-md-3"><span>${fundInOrders.bankBranch }</span></div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundout.detail.subject"/> </label>
                    <div class="col-md-3"><span>${fundInOrders.bankAccountName }</span></div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundout.detail.accoutNo"/></label>
                    <div class="col-md-3"><span>${fundInOrders.bankAccountNumber }</span></div>
                  </div>

                </c:if>
                <div class="form-group">
                  <label class="col-md-3 control-label"><spring:message code="fundin.order.amount"/></label>
                  <div class="col-md-3"><span>${ewallet:formatNumber(fundInOrders.amount)} (VNĐ)</span></div>
                </div>
                <div class="form-group">
                  <label class="col-md-3 control-label"><spring:message code="fundin.order.fee"/></label>
                  <div class="col-md-6"><span>${fundInOrders.fee }</span><span> (VNĐ)</span></div>
                </div>
                <div class="form-group">
                  <label class="col-md-3 control-label"><spring:message code="fundout.detail.fundoutAmount"/></label>
                  <div class="col-md-6"><span>${ewallet:formatNumber(fundInOrders.amount)} (VNĐ)</span></div>
                </div>
                <div class="form-group">
                  <label class="col-md-3 control-label"><spring:message code="fundin.order.balance"/></label>
                  <div class="col-md-6"><span>${ewallet:formatNumber(customerCurrentBalance)} (VNĐ)</span></div>
                </div>

                <div class="form-group">
                  <label class="col-md-3 control-label"><spring:message code="fundin.order.formStock"/></label>
                  <div class="col-md-6">
                    <c:forEach var="item" items="${attachments }">
                      <p><img alt="" src="data:image/png;base64, <c:out value='${item.imageBase64}'/>" style="max-width: 90%;"></p><br/>
                    </c:forEach>
                  </div>
                </div>
                <!-- div show file -->
                <div class="form-group">
                  <div class="col-md-3"></div>
                  <div class="col-md-6 fileshow"></div>
                </div>
                <div class="form-group">
                  <label class="col-md-3 control-label"><spring:message code="fundin.order.notable"/></label>
                  <div class="col-md-3"><span>${fundInOrders.info }</span></div>
                </div>
              </div>
              <div class="clearfix"></div>
            </section>
          </div>

        </div>
      </div>
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
</body>
</html>
