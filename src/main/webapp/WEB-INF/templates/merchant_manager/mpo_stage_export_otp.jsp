<%@ page import="vn.mog.ewallet.operation.web.controller.epin.EpinPurchaseOrderController" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="service.exportcard.otp.title.page"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <jsp:include page="../include_page/js.jsp"/>
  <style type="text/css">
    .form-control { height: 50px; border-radius: 7px; border: 2px solid #eaebec; box-shadow: none; outline: none; color: #aaa; font-size: 17px; } .form-control:focus { border-color: #38B449; box-shadow: none; } .report_search_bt .submit_bt { border-radius: 7px; } input[type=number]::-webkit-inner-spin-button { -webkit-appearance: none; cursor: pointer; display: block; text-align: center; position: relative; }
  </style>
</head>

<body>
<section class="body">
  <jsp:include page="../include_page/header.jsp"/>
  <c:url var="urlEpoList" value="<%=EpinPurchaseOrderController.EPIN_PO_LIST%>"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="merchantpo" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="service.exportcard.otp.navigate.service"/></span></li>
                <sec:authorize access="hasAnyRole('MERCHANT','CUSTOMER')" var="perExportEpin"/>
                <c:url var="urlEpoList" value="<%=EpinPurchaseOrderController.EPIN_PO_LIST%>"/>
                <c:choose>
                  <c:when test="${perExportEpin eq true}">
                    <li><span><a href="${urlEpoList}" id="hight-title" class="hight-title">
                          <spring:message code="service.exportcard.otp.navigate.exportcard"/>
                    </a></span></li>
                  </c:when>
                  <c:otherwise>
                    <li>
                      <span>
                        <a href="${urlEpoList}?search=&range=&search=Search&status=1&status=2&multiselect=1&multiselect=2" id="hight-title" class="hight-title">
                          <spring:message code="service.exportcard.otp.navigate.exportcard"/>
                        </a>
                      </span>
                    </li>
                  </c:otherwise>
                </c:choose>
                <li><span class="nav-active"> <spring:message code="service.exportcard.otp.navigate.otp"/> </span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md">
            <spring:message code="service.exportcard.otp.subnavigate.title"/>
          </div>

          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none pb-none">
              <div class="wizard-tabs mt-none mb-none">
                <ul class="wizard-steps">
                  <li class="col-xs-4 pl-none pr-none"><a class="text-center"> <span class="badge hidden-xs">1</span>&nbsp;<spring:message code="service.exportcard.epin.stepone.request"/> </a></li>
                  <li class="col-xs-4 pl-none pr-none"><a class="text-center"> <span class="badge hidden-xs">2</span>&nbsp;<spring:message code="service.exportcard.epin.steptwo.approve"/> </a></li>
                  <li class="col-xs-4 pl-none pr-none active"><a class="text-center"> <span class="badge hidden-xs">3</span>&nbsp;<spring:message code="service.exportcard.epin.stepthree.export"/> </a></li>
                </ul>
              </div>

              <div class="tab-content">
                <div class="form-group" style="margin-top: 20px">
                  <label class="col-xs-4 col-sm-1 control-label" for="note">
                    <spring:message code="service.exportcard.otp.guide.note"/>
                  </label>
                  <div class="col-xs-8 tertiary_color" id="note">
                    <spring:message code="service.exportcard.otp.guide"/> <spring:message code="service.exportcard.otp.guide.content" var="guideContent"/> &nbsp;
                    <i id="note" class="fa fa-question-circle text-muted m-xs"
                       data-toggle="popover"
                       data-trigger="hover"
                       data-placement="top"
                       data-content='${guideContent}'
                       data-html="true"
                       data-original-title=""
                       title=""></i>
                  </div>
                </div>

                <spring:message code="service.exportcard.otp.sent.phone" var="sentPhone"/>
                <spring:message code="service.exportcard.otp.code" var="codeOtp"/>

                <div id="tab1" class="tab-pane active">
                  <sec:authorize access="hasAnyRole('MERCHANT','CUSTOMER')" var="perOtp">
                  <form name="otpAuth" action="" method="post">
                    </sec:authorize>
                    <input type="hidden" name="id" value="${param.id }"/>
                    <section class="panel panel-default">
                      <div class="panel-body edit_profile">
                        <div class="form-group">
                          <div class="col-xs-12" align="right">
                            <div id="lblOtp" class="primary_color pull-left"><i class="fa fa-check"></i>
                              <spring:message code="service.exportcard.otp.sent.otp"/>
                            </div>
                          </div>
                          <i class="fa  fa-question-circle text-muted m-xs pull-left" data-toggle="popover" data-trigger="hover" data-placement="top"
                             data-content="${sentPhone}"
                             data-original-title="" title=""></i>
                        </div>
                        <div class="form-group otp">
                          <div class="col-md-12" align="center">
                            <input name="otp" type="text" onfocus="clear(this)" autocomplete="off" class="form-control"
                                   title="${codeOtp}" placeholder="${codeOtp}" style="max-width: 360px; text-align: center"/>
                            <input type="hidden" name="backUrl"/>
                          </div>
                        </div>

                        <div class="form-group report_search_bt otp" style="text-align: center;">
                          <c:if test="${perOtp eq true}">
                            <spring:message code="service.exportcard.otp.waiting" var="waiting"/>
                            <button type="button" class="btn btn-primary resendOtp"
                                    data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waiting}">
                              <spring:message code="service.exportcard.otp.btn.resend"/>
                            </button>
                          </c:if>
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
                          <c:if test="${perOtp eq true}">
                            <button type="reset" class="submit_bt nomal_color_bk cancelBtn" name="cancel"><i class="fa fa-times-circle"></i>&nbsp;<spring:message code="button.no"/></button>
                            <button type="submit" class="btn btn-success" name="submit"><i class="fa fa-plus"></i>&nbsp;<spring:message code="button.confirm"/></button>
                          </c:if>
                        </div>
                      </div>
                    </section>
                    <sec:csrfInput />
                  </form>
                </div>
              </div>
            </div>
          </section>
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
        poMerchantId: id
      }, function (data) {
        if (data.code == 0) {
          var textOTP = '<spring:message code="popup.message.sent.opt"/>';
          $("#lblOtp").removeClass('secondary_color');
          $("#lblOtp").addClass('primary_color');
          $("#lblOtp").html('<i class="fa fa-check"></i> ' + textOTP);
          $("#lblOtp").hide();
          $("#lblOtp").fadeIn(1000);
          CountDown(60);
        }
        else {
          var textOTP = data.message;
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
        }
        else {
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
