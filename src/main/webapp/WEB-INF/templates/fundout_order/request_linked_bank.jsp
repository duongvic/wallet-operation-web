<%@ page import="static vn.mog.ewallet.operation.web.constant.SharedConstants.FUND_OUT_ORDER_MIN_VALUE_PER_ORDER" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.fundout.FundOutOrderController" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<div class="panel-body report_search_form pb-none pt-sm linked_bank div_fundin" ${styleDiv == 'linked_bank' ? '' : 'style=\"display: none;\"'} >

  <div class="toggle setupsv toggle-customer" data-plugin-toggle="">
    <section class="toggle active">
      <label class="alert-success">
        <img src="<c:url value="/assets/development/static/images/fundorder/i_api_otp_a.png"/>" class="img-responsive toggle-nav-img" alt="" style="width: 25px;margin-left: -4px">
        <span class="toggle-nav-el"><spring:message code="fundorder.request.linked.bank"/></span>
      </label>

      <c:url var="urlFundOutOrderCon" value="<%=FundOutOrderController.FUND_OUT_ORDER_CONTROLLER%>"/>

      <c:choose>
        <c:when test="${stepLinkBank eq 'request'}">
          <form action="${urlFundOutOrderCon}/linked-bank" method="post"  name="linkedBank">
            <div class="wizard-tabs" style="margin-top: 15px;">
              <ul class="wizard-steps">
                <li class="col-xs-6 pl-none pr-none active"><a href="#tab6" class="text-center" data-toggle="tab"><span class="badge hidden-xs">1</span>&nbsp;<spring:message code="common.btn.request"/></a></li>
                <li class="col-xs-6 pl-none pr-none"><a href="#tab7" class="text-center" data-toggle="tab"><span class="badge hidden-xs">2</span>&nbsp;<spring:message code="common.verify.info"/></a></li>
              </ul>
            </div>

            <c:set var="valueFundOutMin" value="<%=FUND_OUT_ORDER_MIN_VALUE_PER_ORDER%>"/>

            <div class="toggle-content">
              <c:choose>
                <c:when test="${existLinkBank eq true && userLogin.balance > valueFundOutMin}">
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="common.enter.your.cashOut.amount"/>  <span style="color: red">*</span> :</label>
                    <div class="col-md-3">
                      <div class="input-group mb-md">
                        <input type="text" name="amountLinkBank" autocomplete="off" class="form-control textNumber" required value="${amountLinkBank}">
                        <span class="input-group-addon">VNĐ</span>
                      </div>
                    </div>
                  </div>

                  <input type="hidden" name="bankCode" value="${directBank.bank.bankCode}">
                  <input type="hidden" name="fundOrderId" value="${fundOrderId}">
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="common.bank"/></label>
                    <div class="col-md-6 primary_color">${directBank.bank.bankName}</div>
                    <input type="hidden" name="bankName" value="${directBank.bank.bankName}">
                  </div>

                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="request.BankTransfer.accountName"/></label>
                    <div class="col-md-6 primary_color">${directBank.bankAccountName}</div>
                    <input type="hidden" name="bankAccountName" value="${directBank.bankAccountName}">
                  </div>

                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="request.BankTransfer.accountNo"/></label>
                    <div class="col-md-6 primary_color">${directBank.bankAccountNumber}</div>
                    <input type="hidden" name="bankAccountNumber" value="${directBank.bankAccountNumber}">
                  </div>

                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="request.time.request"/> </label>
                    <div class="col-md-6 primary_color">${timeRequest}</div>
                  </div>
                  <input type="hidden" name="timeRequest" id="timeRequest" value="${timeRequest}">

                  <div class="pull-right">
                    <a href="${urlFundOutOrderCon}" class="btn btn-danger"><i class="fa fa-ban" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.cancel"/></a>
                    <button type="submit" name="action" value="request" class="btn btn-success"><spring:message code="common.btn.request"/> &nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                  </div>

                </c:when>
                <c:otherwise>
                  <c:if test="${existLinkBank eq false}">
                    <b><spring:message code = "request.Link.Bank.No.Account"/></b>
                  </c:if>
                  <c:if test="${userLogin.balance < valueFundOutMin}">
                    <br>
                    <b><spring:message code="common.balance.is.under.limitation"/> ( &lt; ${ewallet:formatNumber(valueFundOutMin)}&nbsp;VND)&nbsp;<spring:message code="common.not.enough.to.be.funded.out"/> </b>
                  </c:if>
                </c:otherwise>
              </c:choose>
              <div class="clr"></div>
            </div>
          <sec:csrfInput />
          </form>
        </c:when>


        <c:when test="${stepLinkBank eq 'verify'}">
          <form action="${urlFundOutOrderCon}/linked-bank-otp" method="post"  name="linkedBankOtp">
            <div class="wizard-tabs mt-md" style="margin-top: 15px;">
              <ul class="wizard-steps">
                <li class="col-xs-6 pl-none pr-none"><a href="#tab7" class="text-center" data-toggle="tab"><span class="badge hidden-xs">1</span>&nbsp;<spring:message code="common.btn.request"/></a></li>
                <li class="col-xs-6 pl-none pr-none active"><a ihref="#tab6" class="text-center" data-toggle="tab"><span class="badge hidden-xs">2</span>&nbsp;<spring:message code="common.verify.info"/></a></li>
              </ul>
            </div>

            <div class="toggle-content">

              <input type="hidden" name="bankCode" value="${directBank.bank.bankCode}">
              <input type="hidden" name="fundOrderId" value="${fundOrderId}">
              <input type="hidden" name="amountLinkBank" value="${amountLinkBank}">

              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="common.cash.out.amount" /></label>
                <div class="col-md-6 primary_color">${amountLinkBank}</div>
              </div>

              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="request.BankTransfer.bank"/></label>
                <div class="col-md-6 primary_color">${directBank.bank.bankName}</div>
                <input type="hidden" name="bankName" value="${directBank.bank.bankName}">
              </div>

              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="request.bank.account.name"/></label>
                <div class="col-md-6 primary_color">${directBank.bankAccountName}</div>
                <input type="hidden" name="bankAccountName" value="${directBank.bankAccountName}">
              </div>

              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="request.bank.account.number"/></label>
                <div class="col-md-6 primary_color">${directBank.bankAccountNumber}</div>
                <input type="hidden" name="bankAccountNumber" value="${directBank.bankAccountNumber}">
              </div>

              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="request.time.request"/></label>
                <div class="col-md-6 primary_color">${timeRequest}</div>
              </div>


              <spring:message code="service.exportcard.otp.sent.phone" var="sentPhone"/>
              <spring:message code="service.exportcard.otp.code" var="codeOtp"/>

              <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="request.verify.by.OTP"/>
                  <div id="lblOtp" class="primary_color"><i class="fa fa-check"></i> <spring:message code="service.exportcard.otp.sent.otp"/>
                    <i class="fa  fa-question-circle text-muted m-xs " data-toggle="popover" data-trigger="hover" data-placement="top" data-content="${sentPhone}" data-original-title="" title=""></i>
                  </div>
                </label>

                <div class="col-md-6 primary_color">
                  <input name="otp" type="text" onfocus="clear(this)" autocomplete="off" class="form-control" title="${codeOtp}" placeholder="${codeOtp}" style="max-width: 360px; text-align: center"/>
                  <input type="hidden" name="backUrl"/>
                </div>
              </div>


              <div class="form-group otp" style="padding-left: 33%;">
                <c:if test="${perOtp eq true}">
                  <spring:message code="service.exportcard.otp.waiting" var="waiting"/>
                  <button type="button" class="btn btn-primary resendOtp " data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waiting}">
                    <spring:message code="service.exportcard.otp.btn.resend"/>
                  </button>
                </c:if>
              </div>

              <div class="form-group" style="padding-left: 25%;">
                <div class="col-md-12">
                  <div id="lblAlertResend" style="display: none;" class="tertiary_color">
                    <spring:message code="service.exportcard.otp.waiting.two"/>
                    <span class="spCountDown"></span>..
                  </div>
                </div>
              </div>

              <div class="pull-right otp">
                <c:if test="${perOtp eq true}">
                  <button type="submit" name="action" value="back" class="btn btn-primary cancelBack"><i class="fa fa-arrow-left" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.back"/></button>
                  <button type="submit" name="action" value="cancel" class="btn btn-danger btnCancael"><i class="fa fa-ban" aria-hidden="true"></i>&nbsp;<spring:message code="button.no"/></button>
                  <button type="submit" name="action" value="submit" class="btn btn-success submit_bt"><spring:message code="button.confirm"/>&nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                </c:if>
              </div>
            </div>
            <div class="clr"></div>
          <sec:csrfInput />
          </form>
        </c:when>
      </c:choose>
    </section>
  </div>

</div>
<script type="text/javascript">
  function clear(x) {
    x.val = '';
  }
  $(document).ready(function () {
    $('.otp').show();
    $("#lblOtp").show();
    $('.resendOtp').click(function () {
      var fundOrderId = "${fundOrderId}";
      $("#lblOtp").removeClass('secondary_color');
      $("#lblOtp").addClass('primary_color');
      $("#lblOtp").html("<i class='fa fa-spinner fa-spin '></i> " + '<spring:message code="popup.message.waiting"/>');
      $.post('getOtp', {
        id: fundOrderId
      }, function (data) {
        if (data.code == 0) {
          $("#lblOtp").removeClass('secondary_color');
          $("#lblOtp").addClass('primary_color');
          $("#lblOtp").html('<i class="fa fa-check"></i> ' + '<spring:message code="popup.message.sent.opt"/>');
          $("#lblOtp").hide();
          $("#lblOtp").fadeIn(1000);
          CountDown(60);
        } else {
          $("#lblOtp").removeClass('primary_color');
          $("#lblOtp").addClass('secondary_color');
          $("#lblOtp").html('<i class="fa fa-times" aria-hidden="true"></i> ' + data.message);
          $("#lblOtp").hide();
          $("#lblOtp").fadeIn(1000)
        }
      });
      return false;
    });
    $('.btnCancael').click(function () {
      location.href = 'list';
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

    $('form[name=linkedBankOtp]').submit(function () {
      $("#mloading").modal('toggle');
    });
  });
</script>

