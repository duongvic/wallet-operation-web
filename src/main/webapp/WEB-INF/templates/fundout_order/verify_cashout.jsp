<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderChannelType" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderFlowStageType" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="fundout.order.title.page"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <jsp:include page="../include_page/js.jsp">
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
</head>

<body>
<section class="body">
  <jsp:include page="../include_page/header.jsp"/>
  <jsp:include page="../include_component/constant_application.jsp"/>

  <c:set var="channelBank" value="<%= FundOrderChannelType.BANK_TRANSFER.code %>"/>
  <c:set var="finance_reject" value="<%= FundOrderFlowStageType.FINANCE_MANAGER_REJECTED.code %>"/>
  <c:set var="finance_approve" value="<%= FundOrderFlowStageType.FINANCE_MAMAGER_APPROVE.code %>"/>

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
                <li><span><spring:message code="fundout.navigate.fundout" /></span></li>
                <li><span><a href="list" id="hight-title" class="hight-title"><spring:message code="fundout.request.fundout"/> </a></span></li>
                <li><span class="nav-active"><spring:message code="fundout.request.fundout"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid pt-md">
          <div class="mb-md">
            <div class="wizard-tabs">
              <ul class="wizard-steps">
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"><span class="badge hidden-xs">1</span>&nbsp;<spring:message code="common.btn.request"/></a></li>
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"><span class="badge hidden-xs">2</span>&nbsp;<spring:message code="fundout.order.requestCashout.step.two"/></a></li>
                <li class="col-xs-3 pl-none pr-none ${fundInOrders.stage eq 4 or fundInOrders.stage eq 3 ? 'active' : '' }"><a class="text-center"><span class="badge hidden-xs">3</span>&nbsp;<spring:message code="common.btn.verify"/></a></li>
                <li class="col-xs-3 pl-none pr-none ${fundInOrders.stage eq 6 or fundInOrders.stage eq 5 ? 'active' : '' }"><a class="text-center"><span class="badge hidden-xs">4</span>&nbsp;<spring:message code="common.btn.approve"/></a></li>
              </ul>

              <div class="h4 mb-md">
                <c:choose>
                  <c:when test="${fundInOrders.stage eq 4}">
                    <spring:message code="fundin.order.verify"/>&nbsp;<span class="primary_color">(&nbsp;<spring:message code="${fundInOrders.getTextOrderChannel()}"/>&nbsp;)</span>
                  </c:when>
                  <c:when test="${fundInOrders.stage eq 5}">
                    <spring:message code="fundin.order.step.reject"/>&nbsp;<span class="primary_color">(&nbsp;<spring:message code="${fundInOrders.getTextOrderChannel()}"/>&nbsp;)</span>
                  </c:when>
                  <c:when test="${fundInOrders.stage eq 6}">
                    <spring:message code="fundin.order.step.approve"/>&nbsp;<span class="primary_color">(&nbsp;<spring:message code="${fundInOrders.getTextOrderChannel()}"/>&nbsp;)</span>
                  </c:when>
                </c:choose>
              </div>

            </div>
            <section class="panel search_payment panel-default">
              <form action="" method="post" >
                <div class="panel-body">
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.account"/> </label>
                    <div class="col-md-5">
                      <p>${fundInOrders.username }</p>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.channel"/> </label>
                    <div class="col-md-5">
                      <span>${fundInOrders.orderChannel }</span>
                    </div>
                  </div>


                  <c:if test="${fundInOrders.orderChannel eq channelBank }">
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="fundin.order.bank"/> </label>
                      <div class="col-md-5">${fundInOrders.bankName}</div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="fundin.order.account"/> </label>
                      <div class="col-md-5">${fundInOrders.bankAccountName}</div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="fundin.order.accountNo"/> </label>
                      <div class="col-md-5">${fundInOrders.bankAccountNumber}</div>
                    </div>
                  </c:if>

                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.suggest"/> </label>
                    <div class="col-md-5">${ewallet:formatNumber(fundInOrders.amount)}&nbsp;(VNĐ)</div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.fee"/> </label>
                    <div class="col-md-6 ">${fundInOrders.fee}&nbsp;(VNĐ)</div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundout.order.fundoutAmount"/> </label>
                    <div class="col-md-6 ">${ewallet:formatNumber(fundInOrders.amount)}&nbsp;(VNĐ)</div>
                  </div>

                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.balance"/> </label>
                    <div class="col-md-6 ">${ewallet:formatNumber(customerCurrentBalance)}&nbsp;(VNĐ)</div>
                  </div>

                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.formStock"/> </label>
                    <div class="col-md-6">
                      <c:forEach var="item" items="${attachments }">
                        <p>
                          <img alt="" src="data:image/png;base64, <c:out value='${item.imageBase64}'/>" style="max-width: 90%;">
                        </p>
                        <br/>
                      </c:forEach>
                    </div>
                  </div>

                  <c:if test="${fundInOrders.orderChannel eq 'BANK_TRANSFER'}">
                    <c:choose>
                      <c:when test="${fundInOrders.stage eq financeSupportVerify || fundInOrders.stage eq financeManagerRejected}">
                        <div class="form-group">
                          <label class="col-md-3 control-label"><spring:message code="fundin.order.bankcode"/>&nbsp;<spans style="color: red">*</spans></label>
                          <div class="col-md-5">
                            <select name="profileBankCode" id="profileBankCode" data-plugin-selectTwo required class="form-control " style="width: 100%">
                              <option value=""><spring:message code="request.BankTransfer.bankname"/><spans style="color: red">*</spans></option>
                              <c:forEach var="item" items="${profileBanks}">
                                <option value="${item.bankCode}" ${item.bankCode eq fundInOrders.bankCode ? 'selected' : ''}>${item.bankName} (${item.bankBranch})</option>
                              </c:forEach>
                            </select>
                          </div>
                        </div>
                      </c:when>
                      <c:when test="${fundInOrders.stage eq financeMamagerApprove}">
                        <div class="form-group">
                          <label class="col-md-3 control-label"><spring:message code="fundin.order.bankcode"/></label>
                          <div class="col-md-5">
                            <select name="profileBankCode" id="profileBankCode" data-plugin-selectTwo required class="form-control " style="width: 100%;" readonly="" disabled>
                              <option value=""><spring:message code="request.BankTransfer.bankname"/><spans style="color: red">*</spans></option>
                              <c:forEach var="item" items="${profileBanks}">
                                <option value="${item.bankCode}" ${item.bankCode eq bankCodeSOF ? 'selected' : ''}>${item.bankName} (${item.bankBranch})</option>
                              </c:forEach>
                            </select>
                          </div>
                        </div>
                      </c:when>
                    </c:choose>
                  </c:if>

                  <!-- div show file -->
                  <div class="form-group">
                    <div class="col-md-3"></div>
                    <div class="col-md-6 fileshow"></div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.reason"/> </label>
                    <div class="col-md-5">
                      <textarea rows="5" name="remark" class="form-control">${fundInOrders.info }</textarea>
                    </div>
                  </div>
                  <div class="alert alert-default mb-none mt-md p-sm">
                    <div class="checkbox-custom checkbox-success">
                      <input type="checkbox" name="ckaccess" id="checkboxExample3">
                      <label for="checkboxExample3"><spring:message code="fundin.order.confirm"/> </label>
                    </div>
                  </div>

                  <spring:message code="common.btn.processing.submit" var="waitting"/>
                  <div class="form-group pull-right mt-md">
                    <input type="hidden" name="action">
                    <button type="submit" action="reject" class="btn btn-danger"
                            data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.reject"/></button>

                    <c:if test="${fundInOrders.stage != finance_reject }">
                      <button type="submit" action="approve" id="btnApprove" class="btn btn-success"
                              data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}">
                        <c:choose>
                          <c:when test="${fundInOrders.stage != finance_approve}">
                            <spring:message code="common.btn.approve"/>
                          </c:when>
                          <c:otherwise><spring:message code="common.send.otp"/></c:otherwise>
                        </c:choose>
                        &nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i>
                      </button>
                    </c:if>
                  </div>
                </div>
              <sec:csrfInput /></form>
            </section>
          </div>
        </div>
      </div>
  </div>
</section>

<jsp:include page="../include_page/footer.jsp"/>

</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<script>
  $(document).ready(function () {
    $('form button:submit').click(function () {
      if (!$('#checkboxExample3').is(':checked')) {
        $.MessageBox({message: "<spring:message code="common.not.confirm.the.action"/>"});
        return false;
      }
      if ($("form input[name=action]").val() == 'reject') {
        if ($("form textarea[name=remark]").val().length < 1) {
          $.MessageBox({message: "<spring:message code="common.fill.in.the.reason"/>"});
          return false;
        }
      }

      <c:if test="${fundInOrders.stage eq financeSupportVerify || fundInOrders.stage eq financeManagerRejected}">
      if ($('#profileBankCode').val() === '') {
        $.MessageBox({message: "<spring:message code="message.fundout.order.profile.bankcode.selected"/>"});
        return false;
      }
      </c:if>

      $("input[name=action]").val($(this).attr("action"));
      $(this).button('loading');
    });
  });
</script>
</body>
</html>
