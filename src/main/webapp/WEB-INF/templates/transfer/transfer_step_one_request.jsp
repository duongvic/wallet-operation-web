<%@ page import="vn.mog.ewallet.operation.web.controller.transfer.FundinSofController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.transfer.WalletTransferController" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="wallet.transfer.process.title.page"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/transfer.css'/>" media="none" onload="if(media!='all')media='all'">
  <jsp:include page="../include_page/js_merchant.jsp"/>
  <style>
    .downloadFile:hover { cursor: pointer; color: #f39230 } .downloadFile { color: #09ae9d; }
  </style>
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
                <li><span>${labelTransfer}</span></li>
                <li><span>${labelSubmenuRequest}</span></li>
                <li><span class="nav-active"><spring:message code="wallet.transfer.process.nav.create.request"/></span></li>
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
                <li class="col-xs-3 pl-none pr-none active"><a href="#tab1" data-toggle="tab" class="text-center"><span class="badge hidden-xs">1</span>&nbsp;<spring:message code="wallet.transfer.process.step.one.request"/></a></li>
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"><span class="badge hidden-xs">2</span>&nbsp;<spring:message code="wallet.transfer.process.step.two.initiate"/></a></li>
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"><span class="badge hidden-xs">3</span>&nbsp;<spring:message code="wallet.transfer.process.step.three.review"/></a></li>
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"><span class="badge hidden-xs">3</span>&nbsp;<spring:message code="wallet.transfer.process.step.four.approve"/></a></li>
              </ul>
            </div>
            <div id="tab1" class="tab-pane active">
              <section class="panel panel-default">
                <div class="panel-body edit_profile pl-none">

                  <form:form action="${prefixWalletTransURL}/request-transfer" method="post"
                             id="fOrderRequest" name="orderRequest"
                             enctype="multipart/form-data" modelAttribute="transferDataForm">
										<input name="orderId" value="${orderId}" type="hidden">
                    <input name="orderStage" value="${orderStage}" type="hidden" >
                    <input name="transferType" value="${transferType}" type="hidden">

                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="request.transfer.template"/></label>
                      <div class="col-md-6">
                        <p>
                        <c:choose>
                          <c:when test="${transferType == 'FUND_TRANSFER'}">
                            <a class="downloadFile" href="${prefixWalletTransURL}/files/BM_AN_01"><spring:message code="transfer.request.request.sof"/></a>
                          </c:when>
                          <c:otherwise>
                            <a class="downloadFile" href="${prefixWalletTransURL}/files/BM_AN_02"><spring:message code="transfer.request.request.wallet"/></a>
                          </c:otherwise>
                        </c:choose>
                        </p>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="request.transfer.requestFile"/><spans style="color: red">*</spans></label>
                      <div class="col-md-7">
                        <input type="file" id="fileUpload" name="fileUpload" class="form-control hidden">
                        <span id="openFile" class="fa fa-upload fa-2x hight-light-" aria-hidden="true"></span>
                        <label id="fileName" for="fileUpload" class="hight-light">&nbsp;
                          <c:choose>
                            <c:when test="${not empty attachmentName}"> ${attachmentName} </c:when>
                            <c:otherwise><spring:message code="transfer.request.file.upload"/></c:otherwise>
                          </c:choose>
                        </label>
                        <c:forEach var="item" items="${attachments }">
                          <p><img alt="" src="data:image/png;base64, <c:out value='${item.imageBase64}'/>" style="max-width: 90%;"></p>
                          <br/>
                        </c:forEach>
                        <p><strong class="secondary_color"><5mb </strong></p>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="request.transfer.notable"/><spans style="color: red">*</spans></label>
                      <div class="col-md-5">
                        <textarea id="remark" rows="5" cols="5" name="remark" class="form-control" required minlength="1" maxlength="1024">${remark}</textarea>
                      </div>
                    </div>
                    <div class="alert alert-default mb-md mt-md p-sm">
                      <div class="checkbox-custom checkbox-success">
                        <input type="checkbox" name="ckaccess" id="checkboxExample3">
                        <label for="checkboxExample3"><spring:message code="request.transfer.confirm"/></label>
                      </div>
                    </div>

                    <spring:message code="common.btn.processing.submit" var="waitting"/>
                    <div class="pull-right form-responsive">
                      <input type="hidden" id="action" name="action" value=""/>
                      <a href="${movementRequestListUri}" class="btn btn-danger"><i class="fa fa-ban" aria-hidden="true"></i>&nbsp; <spring:message code="request.transfer.cancel"/></a>
                      <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT')">
                        <button type="button" action="save" class="btn btn-primary bt-ripple"
                                data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp;<spring:message code="request.transfer.save"/></button>

                        <button type="button" action="submit" class="btn btn-success bt-ripple"
                                data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><spring:message code="request.transfer.sendRequest"/>&nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                      </sec:authorize>
                    </div>
                  <sec:csrfInput />
                  </form:form>
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
  $("#openFile").click(function () {
    $("#fileUpload").click();
  });

  $("#fileUpload").change(function () {
    var fileName = $("#fileUpload").val().split('\\').pop();
    $('#fileName').html(fileName);
  });

  $(document).ready(function () {
		var filename = $("#filename").val();
		if (filename != ""){
		  $('#fileName').html(filename);
		}
		
    $('form button:button').click(function () {

      if ($('#remark').val().length < 1) {
        $.MessageBox({message: "<spring:message code="transfer.review.message.remark.requirde" />"});
        return false;
      }

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

      $("form input[name=action]").val($(this).attr("action"));
      $(this).button('loading');
      $('#fOrderRequest').submit();
    });

    $('form[name=orderRequest] input:file').change(function (click) {
      var files = $(this)[0].files;
      var exts = ['png', 'jpg', 'jpe', 'jpeg'];
      var checkExt = true;
      if (files.length > 0) {
        for (var i = 0; i < files.length; i++) {
          var get_ext = files[i].name.split('.');
          // reverse name to check extension
          get_ext = get_ext.reverse();
          if ($.inArray(get_ext[0].toLowerCase(), exts) < 0) {
            checkExt = false;
          }
        }
        if (!checkExt) {
          $.MessageBox({message: '<spring:message code="common.upload.file.not.format"/>'});
          $(this).val('');
        }
      }
    });
  });
</script>
</body>
</html>
