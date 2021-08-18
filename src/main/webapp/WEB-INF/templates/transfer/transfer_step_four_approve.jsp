<%@ page import="static vn.mog.ewallet.operation.web.constant.SharedConstants.TRANSFER_FUND_MAX_MONEY" %>
<%@ page import="static vn.mog.ewallet.operation.web.constant.SharedConstants.TRANSFER_WALLET_MAX_MONEY" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.transfer.FundinSofController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.transfer.WalletTransferController" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="transfer.approve.page.header"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <jsp:include page="../include_page/js_merchant.jsp"/>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/transfer.css'/>" media="none" onload="if(media!='all')media='all'">
</head>

<body >
<section class="body">
  <jsp:include page="../include_page/header.jsp"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param name="nav" value="${transferType}"/>
    </jsp:include>
    <!-- end: sidebar -->

    <c:choose>
      <c:when test="${transferType == 'FUND_TRANSFER'}">
        <spring:message code="menu.left.fundout.submenu.request.fundin.sof" var="labelSubmenuRequest"/>
        <c:url var="movementRequestListUri" value="<%=FundinSofController.FUND_SOF_REQUEST_LIST%>" />
        <c:set var="prefixWalletTransURL">${contextPath}/wallet/fundin-sof</c:set>
        <spring:message code="menu.left.fund.transfer" var="labelTransfer"/>
      </c:when>
      <c:otherwise>
        <spring:message code="menu.left.fundout.submenu.request.transfer.wallet" var="labelSubmenuRequest"/>
        <c:url var="movementRequestListUri" value="<%=WalletTransferController.TRANSFER_WALLET_REQUEST_LIST%>" />
        <c:set var="prefixWalletTransURL">${contextPath}/wallet/transfer-wallet</c:set>
        <spring:message code="menu.left.wallet.transfer" var="labelTransfer"/>
      </c:otherwise>
    </c:choose>


    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><a href="${movementRequestListUri}" id="hight-title" class="hight-title">${labelTransfer}</a></span></li>
                <li><span><spring:message code="common.btn.create.request"/> </span></li>
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
                <li class="col-xs-3 pl-none pr-none"><a href="#tab1" data-toggle="tab" class="text-center"> <span class="badge hidden-xs">1</span>&nbsp;<spring:message code="wallet.transfer.process.step.one.request"/></a></li>
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"> <span class="badge hidden-xs">2</span>&nbsp;<spring:message code="wallet.transfer.process.step.two.initiate"/></a></li>
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"> <span class="badge hidden-xs">3</span>&nbsp;<spring:message code="wallet.transfer.process.step.three.review"/></a></li>
                <li class="col-xs-3 pl-none pr-none active"><a class="text-center"><span class="badge hidden-xs">4</span>&nbsp;<spring:message code="wallet.transfer.process.step.four.approve"/></a></li>
              </ul>
            </div>
            <div id="tab1" class="tab-pane active">
              <section class="panel panel-default">
                <div class="panel-body edit_profile pl-none">
                  <form action="${prefixWalletTransURL}/order-approve" method="post"  name="transfer">
                    <input type="hidden" id="orderId" name="orderId" value="${orderId}">
                    <input type="hidden" id="action" name="action" value=""/>
                    <jsp:include page="transfer_info_common.jsp"/>

                    <div class="pull-right form-responsive">

                      <c:set var="fundTransferMaxMoney" value="<%=TRANSFER_FUND_MAX_MONEY%>"/>
                      <c:set var="walletTransferMaxMoney" value="<%=TRANSFER_WALLET_MAX_MONEY%>"/>

                      <sec:authorize access="hasAnyRole('ADMIN_OPERATION','FINANCE_LEADER')" var="permisReview"/>
                      <sec:authorize access="hasAnyRole('ADMIN_OPERATION','FA_MANAGER')" var="permisApprove"/>
                      <spring:message code="common.btn.processing.submit" var="waitting"/>

                      <c:choose>
                        <c:when test="${((amount lt fundTransferMaxMoney) and (serviceType eq 'FUND_TRANSFER')) ||
                                        ((amount lt walletTransferMaxMoney) and (serviceType eq 'WALLET_TRANSFER'))}">
                          <c:if test="${(permisApprove eq true) or (permisReview eq true)}">
                            <button type="submit" action="reject" class="btn btn-danger"
                                    data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><i class="fa fa-ban" aria-hidden="true"></i>&nbsp;<spring:message code="fundorder.approve.transfer.reject"/></button>

                            <button type="submit" action="approve" class="btn btn-success"
                                    data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><spring:message code="fundorder.approve.transfer.otp"/>&nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                          </c:if>
                        </c:when>
                        <c:when test="${((amount ge fundTransferMaxMoney) and (serviceType eq 'FUND_TRANSFER')) ||
                                        (amount ge walletTransferMaxMoney and (serviceType eq 'WALLET_TRANSFER'))}">
                          <c:if test="${permisApprove eq true}">
                            <button type="submit" action="reject" class="btn btn-danger"
                                    data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><i class="fa fa-ban" aria-hidden="true"></i>&nbsp;<spring:message code="fundorder.approve.transfer.reject"/></button>

                            <button type="submit" action="approve" class="btn btn-success"
                                    data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><spring:message code="fundorder.approve.transfer.otp"/>&nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                          </c:if>
                        </c:when>
                        <c:otherwise>
                          <div><spring:message code="transfer.approve.action.button.role"/></div>
                        </c:otherwise>
                      </c:choose>
                    </div>
                    <sec:csrfInput/></form>
                </div>
              </section>
            </div>
          </div>
        </div>
      </div>
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<script type="text/javascript">
  $(document).ready(function () {
    $('form button:submit').click(function () {

      if (!$('#checkboxExample3').is(':checked')) {
        $.MessageBox({message: "<spring:message code="transfer.review.message.confirm.action" />"});
        return false;
      }
      if ($(".activity").val() == 'back') {
        if ($("form textarea[name=remark]").val().length < 1) {
          $.MessageBox({message: "<spring:message code="transfer.review.message.reason" />"});
          return false;
        }
      }
      if ($(".activity").val() == 'save') {
        $.MessageBox({message: "<spring:message code="transfer.review.message.save.request"/>"});
        return false;
      }

      $(this).button('loading');

      $("form input[name=action]").val($(this).attr("action"));
    });
  });
</script>
</body>
</html>
