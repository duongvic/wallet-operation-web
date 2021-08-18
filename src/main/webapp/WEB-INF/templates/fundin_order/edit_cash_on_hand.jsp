<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderChannelType" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.fundin.FundInOrderController" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="fundin.header"/></title>
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

              <c:forEach var="st" items="${orderChannel}">
                <input type="hidden" name="orderChannel" value="${st}">
              </c:forEach>

              <input type="hidden" name="d-49520-p" value="${numberPage}">

              <div class="page-header-left">
                <ol class="breadcrumbs">
                  <li><a href="#"><i class="fa fa-home"></i></a></li>
                  <li><span><spring:message code="fundin.header"/> </span></li>
                  <li><span><a href="#" id="hight-title" class="hight-title"><spring:message code="fundin.request"/> </a></span></li>
                  <li><span class="nav-active"><spring:message code="common.offer"/> </span></li>
                </ol>
              </div>
            </form>
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
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"><span class="badge hidden-xs">1</span>&nbsp; <spring:message code="common.btn.request"/> </a></li>
                <li class="col-xs-3 pl-none pr-none active"><a class="text-center"><span class="badge hidden-xs">2</span>&nbsp;<spring:message code="fundin.order.requestCashIn.step.two.submit"/></a></li>
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"><span class="badge hidden-xs">3</span>&nbsp; <spring:message code="common.btn.verify"/> </a></li>
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"><span class="badge hidden-xs">4</span>&nbsp; <spring:message code="common.btn.approve"/> </a></li>
              </ul>
              <div class="h4 mb-md">
                <spring:message code="fundin.order.requestCashIn"/>&nbsp;<span class="primary_color">( <spring:message code="${fundInOrders.getTextOrderChannel()}"/> )</span>
              </div>
            </div>
            <section class="panel search_payment panel-default">
              <form action="" method="post"  name="submitCashIn" enctype="multipart/form-data">
                <input type="hidden" name="fundOrderId" id="fundOrderId" value="${fundInOrders.id}"/>
                <div class="panel-body">
                  <sec:authorize access="!hasAnyRole('MERCHANT','CUSTOMER')">
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="fundin.order.customer"/></label>
                      <div class="col-md-3">
                        <select name="customerId" id="customerId" data-plugin-selectTwo required class="form-control " style="width: 100%">
                          <c:forEach var="item" items="${customers}">
                            <option ${item.username eq fundInOrders.username ? 'selected' : '' } value="${item.id}">${fn:replace(item.username, '@', '<span>@</span>')}</option>
                          </c:forEach>
                        </select>
                      </div>
                    </div>
                  </sec:authorize>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.account"/></label>
                    <div class="col-md-3">${fundInOrders.username}</div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.amount"/></label>
                    <div class="col-md-3">
                      <div class="input-group mb-md">
                        <input type="text" name="amount" autocomplete="off" class="form-control textNumber" value="${ewallet:formatNumber(fundInOrders.amount)}" required>
                        <span class="input-group-addon">VNĐ</span>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.fee"/></label>
                    <div class="col-md-6">${fundInOrders.fee} (VNĐ)</div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.totalAmount"/></label>
                    <div class="col-md-6">${ewallet:formatNumber(fundInOrders.amount)} (VNĐ)</div>
                  </div>

                  <c:set var="bankCode" value="<%= FundOrderChannelType.BANK_TRANSFER.code %>"/>
                  <c:if test="${fundInOrders.orderChannel eq bankCode }">
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="fundin.order.selectBank"/></label>
                      <div class="col-md-3">
                        <select name="bank" data-plugin-selectTwo required class="form-control " style="width: 100%">
                          <spring:message code="fundin.order.selectBank" var="selectBank"/>
                          <option value="${selectBank }"></option>
                          <c:forEach var="item" items="${listBank }">
                            <option ${fundInOrders.bankCode eq item.bankCode ? 'selected' : '' } value="${item.bankCode}" ${item.bankCode eq param.bank ? 'selected' : ''}>${item.bankName}(${item.bankAbbrName })</option>
                          </c:forEach>
                        </select>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="fundin.order.commandCode"/></label>
                      <div class="col-md-3">
                        <input type="text" name="code" autocomplete="off" class="form-control" value="${fundInOrders.commandCode }">
                      </div>
                    </div>
                  </c:if>

                  <div class="form-group" style="margin-bottom: 0px">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.formStock"/></label>
                    <div class="col-md-6">
                      <input type="file" name="fileUpload" id="file-2" class="inputfile inputfile-2" data-multiple-caption="{count} files selected" multiple/>
                      <label for="file-2"><i class="fa fa-upload" aria-hidden="true"></i> <span><spring:message code="common.btn.choose.file"/> </span></label>
                      <p><strong class="secondary_color"><5mb </strong>(*.png,*.jpe,*.jpeg,*.jpg)</p>
                    </div>
                  </div>
                  <!-- div show file -->
                  <div class="form-group">
                    <div class="col-md-3"></div>
                    <div class="col-md-6 fileshow">
                      <c:forEach var="item" items="${attachments }">
                        <p>${item.name }</p>
                      </c:forEach>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                      <c:forEach var="item" items="${attachments }">
                        <p><img alt="" src="data:image/png;base64, <c:out value='${item.imageBase64}'/>" style="max-width: 90%;"></p> <br/>
                      </c:forEach>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="fundin.order.notable"/></label>
                    <div class="col-md-3">
                      <textarea rows="5" name="remark" class="form-control">${fundInOrders.info }</textarea>
                    </div>
                  </div>
                  <div class="alert alert-default mb-none mt-md p-sm">
                    <div class="checkbox-custom checkbox-success">
                      <input type="checkbox" name="ckaccess" id="checkboxExample3">
                      <label for="checkboxExample3"><spring:message code="fundin.order.confirm"/></label>
                    </div>
                  </div>

                  <c:set var="BANK_TRANSFER" value="<%=FundOrderChannelType.BANK_TRANSFER.code%>"/>
                  <c:set var="CASH_ON_HAND" value="<%=FundOrderChannelType.CASH_ON_HAND.code%>"/>

                  <spring:message code="common.btn.processing.submit" var="waitting"/>
                  <div class="form-group pull-right mt-md">
                    <input type="hidden" name="action" value=""/>
                    <c:choose>
                      <c:when test="${fundInOrders.orderChannel eq BANK_TRANSFER}">
                        <sec:authorize access="hasAnyRole('ADMIN_OPERATION','MERCHANT','CUSTOMER')">
                          <button type="submit" action="save" class="btn btn-primary bt-ripple"
                                  data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.save"/> </button>

                          <button type="submit" action="submit" id="btnSubmit" class="btn btn-success"
                                  data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><spring:message code="common.btn.submit"/>&nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                        </sec:authorize>
                      </c:when>
                      <c:when test="${fundInOrders.orderChannel eq CASH_ON_HAND}">
                        <sec:authorize access="hasAnyRole('ADMIN_OPERATION','MERCHANT','CUSTOMER','SALE_EXCUTIVE','SALE_DIRECTOR')">
                          <button type="submit" action="save" class="btn btn-primary bt-ripple"
                                  data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}">&nbsp;<i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.save"/></button>

                          <button type="submit" action="submit" id="btnSubmit" class="btn btn-success"
                                  data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><spring:message code="common.btn.submit"/>&nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                        </sec:authorize>
                      </c:when>
                    </c:choose>
                  </div>
                </div>
              <sec:csrfInput />
              </form>
            </section>
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
        $.MessageBox({message: "<spring:message code="common.not.confirm.the.action"/>"});
        return false;
      }

      $("input[name=action]").val($(this).attr("action"));
      $(this).button('loading');
    });

    $('form input:file').change(function (click) {
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

    var inputs = document.querySelectorAll('.inputfile');
    Array.prototype.forEach.call(inputs, function (input) {
      var label = input.nextElementSibling, labelVal = label.innerHTML;
      input.addEventListener('change', function (e) {
        $('div.fileshow').html('');
        var fileName = '';
        if (this.files && this.files.length > 1)
          fileName = ( this.getAttribute('data-multiple-caption') || '' ).replace('{count}', this.files.length);
        else
          fileName = e.target.value.split('\\').pop();
        if (fileName)
          label.querySelector('span').innerHTML = fileName;
        else
          label.innerHTML = labelVal;
        if (this.files && this.files.length > 0) {
          $.each(e.target.files, function (idx, elm) {
            var size = elm.size / 1024;
            $('div.fileshow').append('<p >' + elm.name + ' - size : ' + size + 'KB</p>')
          });
        }
      });
    });

    $('#hight-title').click(function () {
      $('#search-fundInOrder').submit();
    });
  });
</script>
</body>
</html>
