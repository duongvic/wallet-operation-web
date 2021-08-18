<%@ page import="vn.mog.ewallet.operation.web.controller.transfer.FundinSofController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.transfer.WalletTransferController" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="common.confirm.transfer"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <jsp:include page="../include_page/js_merchant.jsp">
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/transfer.css'/>"
        media="none" onload="if(media!='all')media='all'">
</head>

<body>
<section class="body">

  <c:choose>
    <c:when test="${transferType == 'FUND_TRANSFER'}">
      <c:set var="prefixWalletTransURL">${contextPath}/wallet/fundin-sof</c:set>
      <c:url var="transferRequestListUrl" value="<%=FundinSofController.FUND_SOF_REQUEST_LIST%>"/>
      <spring:message code="menu.left.fund.transfer" var="labelTransfer"/>
    </c:when>
    <c:otherwise>
      <c:set var="prefixWalletTransURL">${contextPath}/wallet/transfer-wallet</c:set>
      <c:url var="transferRequestListUrl" value="<%=WalletTransferController.TRANSFER_WALLET_REQUEST_LIST%>"/>
      <spring:message code="menu.left.wallet.transfer" var="labelTransfer"/>
    </c:otherwise>
  </c:choose>



  <jsp:include page="../include_page/header.jsp"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param name="nav" value="${transferType}"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><a href="${transferRequestListUrl}" id="hight-title" class="hight-title">${labelTransfer}</a></span></li>
                <li><span class="nav-active"><spring:message code="transfer.initiate.nav.text"/></span></li>
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
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"><span
                    class="badge hidden-xs">1</span>&nbsp;<spring:message
                    code="wallet.transfer.process.step.one.request"/></a></li>
                <li class="col-xs-3 pl-none pr-none active"><a
                    class="text-center"><span class="badge hidden-xs">2</span>&nbsp;<spring:message
                    code="wallet.transfer.process.step.two.initiate"/></a></li>
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"><span
                    class="badge hidden-xs">3</span>&nbsp;<spring:message
                    code="wallet.transfer.process.step.three.review"/></a></li>
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"><span
                    class="badge hidden-xs">4</span>&nbsp;<spring:message
                    code="wallet.transfer.process.step.four.approve"/></a></li>
              </ul>
            </div>

            <div id="tab1" class="tab-pane active">
              <section class="panel panel-default">
                <div class="panel-body edit_profile pl-none">

                  <form action="${prefixWalletTransURL}/order-initiate" method="post"
                        name="transfer">
                    <input type="hidden" id="orderId" name="orderId"
                           value="${orderId}">
                    <input type="hidden" id="orderStage" name="orderStage"
                           value="${orderStage}">


                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message
                          code="request.transfer.typeTransfer"/>
                        <spans style="color: red">*</spans>
                      </label>
                      <div class="col-md-2" ${transferType eq 'FUND_TRANSFER' ? '' : 'hidden'}>
                        <input type="radio" name="typeTransfer"
                               value="fund" ${(serviceType eq 'FUND_TRANSFER' or serviceType eq '') ? 'checked="checked"' : ''}
                               required onchange="changeTransfer('fund')">
                        <spring:message code="transfer.initiate.transfer.fundTransfer"/>
                      </div>
                      <div class="col-md-2" ${transferType eq 'WALLET_TRANSFER' ? '' : 'hidden'}>
                        <input type="radio" name="typeTransfer"
                               value="wallet" ${(serviceType eq 'WALLET_TRANSFER') ? 'checked="checked"' : ''}
                               required onchange="changeTransfer('wallet')">
                        <spring:message code="transfer.initiate.transfer.walletTransfer"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message
                          code="transfer.initiate.transfer.sourceAccount"/>
                        <spans style="color: red">*</spans>
                      </label>
                      <div class="col-md-5">
                        <select class="form-control " name="sourceAccount"
                                id="sourceAccount" required>
                          <c:forEach var="item" items="${sourceAccount}">
                            <option value="${item.id}" ${(item.id eq payerId) ? 'selected' : ''}>${item.fullName}</option>
                          </c:forEach>
                        </select>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message
                          code="transfer.initiate.transfer.targetAccount"/>
                        <spans style="color: red">*</spans>
                      </label>
                      <div class="col-md-5">
                        <select class="form-control" name="targetAccount"
                                id="targetAccount" required>
                          <c:forEach var="item" items="${targetAccount}">
                            <option value="${item.id}" ${(item.id eq payeeId) ? 'selected' : ''}>${item.fullName}</option>
                          </c:forEach>
                        </select>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message
                          code="transfer.initiate.transfer.transferAmount"/>
                        <spans style="color: red">*</spans>
                      </label>
                      <div class="col-md-5">
                        <div class="input-group mb-md">
                          <input id="amount" name="amount"
                                 autocomplete="off"
                                 class="form-control textNumber" required
                                 value="${amount}">
                          <span class="input-group-addon">VNĐ</span>
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message
                          code="request.transfer.notable"/>
                        <spans style="color: red">*</spans>
                      </label>
                      <div class="col-md-5">
                                                <textarea rows="5" id="remark" name="remark"
                                                          class="form-control" minlength="1"
                                                          maxlength="1024"
                                                          required>${remark}</textarea>
                      </div>
                    </div>

                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message
                          code="request.transfer.requestFile"/>
                        <spans style="color: red">*</spans>
                      </label>
                      <div class="col-md-6">
                        <label id="fileName">
                          <c:choose>
                            <c:when test="${not empty attachmentName}"> ${attachmentName} </c:when>
                            <c:otherwise><spring:message
                                code="transfer.initiate.file.name"/></c:otherwise>
                          </c:choose>
                        </label>
                        <c:forEach var="item" items="${attachments }">
                          <p><img alt=""
                                  src="data:image/png;base64, <c:out value='${item.imageBase64}'/>"
                                  style="max-width: 90%;"></p>
                          <br/>
                        </c:forEach>
                      </div>
                    </div>

                    <div class="alert alert-default mp-md">
                      <div class="checkbox-custom checkbox-success">
                        <input type="checkbox" name="ckaccess"
                               id="checkboxExample3"> <label
                          for="checkboxExample3"><spring:message
                          code="request.transfer.confirm"/></label>
                      </div>
                    </div>

                    <spring:message code="common.btn.processing.submit"
                                    var="waitting"/>
                    <div class="pull-right form-responsive">
                      <input type="hidden" id="action" name="action"
                             value=""/>
                      <sec:authorize
                          access="hasAnyRole('ADMIN_OPERATION','FINANCE')">
                        <button type="submit" action="reject"
                                class="btn btn-danger"
                                data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}">
                          <i class="fa fa-ban" aria-hidden="true"></i>&nbsp;<spring:message
                            code="request.transfer.reject"/></button>

                        <button type="submit" action="save"
                                class="btn btn-primary"
                                data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}">
                          <i class="fa fa-floppy-o"
                             aria-hidden="true"></i>&nbsp;<spring:message
                            code="request.transfer.save"/></button>

                        <button type="submit" action="submit"
                                class="btn btn-success"
                                data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}">
                          <spring:message
                              code="request.transfer.sendRequest"/>&nbsp;<i
                            class="fa fa-arrow-right"
                            aria-hidden="true"></i></button>
                      </sec:authorize>
                    </div>
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

  function changeTransfer(typeWallet) {

    var url = '${prefixWalletTransURL}/findCustomerByTypeWallet';
    $.get(url, {typeWallet: typeWallet}, function (json) {
      if (json != null && json.length > 0) {
        var data = JSON.parse(json);
        var sourceCustomers = JSON.parse(data.sourceCustomers);
        var desCustomers = JSON.parse(data.desCustomers);

        $('#sourceAccount').empty();
        $.each(sourceCustomers, function (key, account) {
          $('#sourceAccount').append('<option value="' + account.id + '">' + account.fullName
              + '</option>');
        });

        $('#targetAccount').empty();
        $.each(desCustomers, function (key, account) {
          $('#targetAccount').append('<option value="' + account.id + '">' + account.fullName
              + '</option>');
        });

      } else {
        $('#sourceAccount').empty().append(
            '<option selected="selected" value=""><spring:message var="chooseAccount" code="transfer.initiate.choose.account" /></option>');
        $('#targetAccount').empty().append(
            '<option selected="selected" value=""><spring:message var="chooseAccount" code="transfer.initiate.choose.account" />/option>');

      }
    });
  }

  $(document).ready(function () {
    $('form button:submit').click(function () {

      //e.preventDefault();
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
      var amount = $('#amount').val();
      if (amount != undefined || amount != '') {
        amount = parseFloat(amount).toString();
      }
      var remark = $('#remark').val();
      if ((amount.length > 1 && remark.length > 1) || (amount > 0 && remark.length > 1)) {
        $("form input[id=action]").val($(this).attr("action"));
        $(this).button('loading');
      } else {
        $.MessageBox({message: "<spring:message code="transfer.review.message.amount"/>"});
        return false;
      }
    });
  });

</script>
</body>
</html>
