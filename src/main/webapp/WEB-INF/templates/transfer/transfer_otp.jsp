<%@ page import="vn.mog.ewallet.operation.web.controller.transfer.FundinSofController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.transfer.WalletTransferController" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="common.confirm.transfer"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/transfer.css'/>" media="none" onload="if(media!='all')media='all'">
  <jsp:include page="../include_page/js.jsp"/>
  <style type="text/css">
    .form-control {  height: 50px;  border-radius: 7px;  border: 2px solid #eaebec;  box-shadow: none;  outline: none;  color: #aaa;  font-size: 17px;  }  .form-control:focus {  border-color: #38B449;  box-shadow: none;  }  .report_search_bt .submit_bt {  border-radius: 7px;  }  input[type=number]::-webkit-inner-spin-button {  -webkit-appearance: none;  cursor: pointer;  display: block;  text-align: center;  position: relative;  }
  </style>
</head>

<body>
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
                <li><span><spring:message code="common.confirm.transfer"/></span></li>
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
                  <form name="otpAuth" action="${prefixWalletTransURL}/order-otp" method="post" >
                    <input type="hidden" name="orderId" id="orderId" value="${orderId}"/>
                    <section class="panel panel-default">
                      <div class="panel-body edit_profile">
                        <div class="form-group">
                          <div class="col-md-7" align="right">
                            <div id="lblOtp" class="primary_color"><i class="fa fa-check"></i>
                              <spring:message code="service.exportcard.otp.sent.otp"/>
                            </div>
                          </div>
                          <div class="col-md-5" align="left">
                            <spring:message code="service.exportcard.otp.sent.phone" var="sentPhone"/>
                            <i class="fa  fa-question-circle text-muted m-xs " data-toggle="popover" data-trigger="hover" data-placement="top"
                               data-content="${sentPhone}"
                               data-original-title="" title=""></i>
                          </div>
                        </div>
                        <div class="form-group otp">
                          <div class="col-md-12" align="center">
                            <spring:message code="service.exportcard.otp.code" var="codeOtp"/>
                            <input name="otp" type="text" onfocus="clear(this)" autocomplete="off" class="form-control" title="${codeOtp}" placeholder="${codeOtp}" style="max-width: 360px; text-align: center"/>
                            <input type="hidden" name="backUrl"/>
                          </div>
                        </div>

                        <div class="form-group report_search_bt otp" style="text-align: center;">
                          <spring:message code="service.exportcard.otp.waiting" var="waiting"/>
                          <button type="button" class="btn btn-primary resendOtp" data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waiting}">
                            <spring:message code="service.exportcard.otp.btn.resend"/>
                          </button>
                        </div>
                        <div class="form-group">
                          <div class="col-md-12" align="center">
                            <div id="lblAlertResend" style="display: none;" class="tertiary_color">
                              <spring:message code="service.exportcard.otp.waiting.two"/>
                              <span class="spCountDown"></span>..
                            </div>
                          </div>
                        </div>
                        <div class="form-group report_search_bt otp" style="text-align: center;">
                          <input type="reset" class="submit_bt nomal_color_bk cancelBtn" name="cancel" value="<spring:message code="common.btn.cancel"/>"/>
                          <input type="submit" class="submit_bt" name="action" value="Approve"/>
                        </div>
                      </div>
                    </section>
                    <sec:csrfInput/>
                  </form>
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
  function clear(x) {
    x.val = '';
  }
  $(document).ready(function () {
    $('.otp').show();
    $("#lblOtp").show();
    $('.resendOtp').click(function () {
      var id = "${param.id}";
      $("#lblOtp").removeClass('secondary_color');
      $("#lblOtp").addClass('primary_color');
      $("#lblOtp").html("<i class='fa fa-spinner fa-spin '></i> " + '<spring:message code="popup.message.waiting"/>');
      $.post('getOtp', {
        id: id
      }, function (data) {
        if (data.status.code == 0) {
          var textOTP = '<spring:message code="popup.message.sent.opt"/>';
          $("#lblOtp").removeClass('secondary_color');
          $("#lblOtp").addClass('primary_color');
          $("#lblOtp").html('<i class="fa fa-check"></i> ' + textOTP);
          $("#lblOtp").hide();
          $("#lblOtp").fadeIn(1000);
          CountDown(60);
        } else {
          var textOTP = data.status.value;
          $("#lblOtp").removeClass('primary_color');
          $("#lblOtp").addClass('secondary_color');
          $("#lblOtp").html('<i class="fa fa-times" aria-hidden="true"></i> ' + textOTP);
          $("#lblOtp").hide();
          $("#lblOtp").fadeIn(1000)
        }
      });
      return false;
    });
    $('.cancelBtn').click(function () {
      location.href =  '${prefixWalletTransURL}/processing';
    });
    CountDown(60);
    function CountDown(time) {
      $('.spCountDown').html(time);
      $('#lblAlertResend').show();
      $('.resendOtp').button("loading");
      var myVar = setInterval(function () {
        if (time > 0) {
          time--;
          $('.spCountDown').html(time);
        } else {
          clearInterval(myVar);
          $('#lblAlertResend').hide();
          $('.resendOtp').button("reset");
        }
      }, 1000);
    }

    $('form[name=otpAuth]').submit(function () {
      $("#mloading").modal('toggle');
    });
  });
</script>
</body>
</html>
