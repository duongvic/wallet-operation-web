<%@ page import="vn.mog.ewallet.operation.web.controller.translog.TransactionReversalController" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="reversal.title.request.to.cancel.the.transaction"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <jsp:include page="../include_page/js_merchant.jsp">
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
</head>

<body>
<section class="body">
  <jsp:include page="../include_page/header.jsp"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="reversalTxnRequest" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="transaction.api.navigate.transaction"/></span></li>
                <li><span><a href="<c:url value='<%=TransactionReversalController.TRANS_REVERSAL_LIST%>'/>" id="hight-title" class="hight-title"><spring:message code="common.transaction.cancel"/></a></span></li>
                <li><span class="nav-active"><spring:message code="common.btn.create.request"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>


      <jsp:include page="../include_page/message.jsp"/>

      <!-- start: page -->
      <c:url var="urlTransReversalCon" value="<%=TransactionReversalController.TRANS_REVERSAL_CONTROLLER%>"/>
      <div class="content-body-wrap">
        <div class="container-fluid pt-md">
          <div class="mb-md">
            <div class="wizard-tabs">
              <ul class="wizard-steps">
                <li class="col-xs-6 pl-none pr-none active"><a href="#tab1" data-toggle="tab" class="text-center"> <span class="badge hidden-xs">1</span>&nbsp; <spring:message code="common.btn.request"/> </a></li>
                <li class="col-xs-6 pl-none pr-none"><a class="text-center"> <span class="badge hidden-xs">2</span>&nbsp; <spring:message code="common.btn.approve"/></a></li>
              </ul>
              <div class="h4 mb-md">
                <spring:message code="common.title.request.cancellation.information"/>
              </div>
            </div>
            <div class="tab-content">
              <section class="panel search_payment panel-default">
                <form action="${urlTransReversalCon}/request/verify" method="post" name="transfer">
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="transaction.title.transaction.id"/></label>
                    <div class="col-md-6">
                      <lable id="transactionId">${transaction.id}</lable>
                      <input type="hidden" for="transactionId" name="transactionId" value="${transaction.id}">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="common.title.time"/></label>
                    <div class="col-md-6">
                      <lable><fmt:formatDate value="${transaction.creationDate}" pattern="hh:mm:ss dd/MM/yyyy"/></lable>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="reversal.label.customer.name"/>&nbsp;(Phone):</label>
                    <div class="col-md-6">
                      <lable>${transaction.payerFullname != null ? transaction.payerFullname : (transaction.payeeFullname != null ? transaction.payeeFullname : '')}
                          &nbsp;<span>(${transaction.payerMsisdn != null ? transaction.payerMsisdn : (transaction.payeeMsisdn != null ? transaction.payeeMsisdn : '')})</span>
                      </lable>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="common.title.service"/> </label>
                    <div class="col-md-6">
                      <lable>${transaction.serviceShortName}</lable>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="label.reversal.value.payment"/></label>
                    <div class="col-md-6">
                      <span class="primary_color totalamount">${ewallet:formatNumber(transaction.realAmount)}</span>&nbsp;<span class="primary_color">(VNĐ)</span>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="transaction.title.free"/></label>
                    <div class="col-md-6">
                      <span class="primary_color totalamount">${ewallet:formatNumber(transaction.fee)}</span>&nbsp;<span class="primary_color">(VNĐ)</span>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="common.title.remark"/>:</label>
                    <div class="col-md-3">
                      <textarea rows="5" name="remark" class="form-control">${remark}</textarea>
                    </div>
                  </div>

                  <div class="alert alert-default mb-none mt-md p-sm">
                    <div class="checkbox-custom checkbox-success">
                      <input type="checkbox" name="ckaccess" id="checkboxExample3">
                      <label for="checkboxExample3"><spring:message code="common.message.u.are.sure.information.are.right"/> </label>
                    </div>
                  </div>

                  <div class="report_search_bt pull-right form-responsive">
                    <button type="submit" name="action" value="cancel" class="btn btn-danger"><i class="fa fa-ban" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.cancel"/></button>
                    <button type="submit" name="action" value="submit" class="btn btn-success"><spring:message code="common.btn.initiate"/>&nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                  </div>
                  <div class="clr"></div>
                  <sec:csrfInput/>
                </form>
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
      $("form input[name=action]").val($(this).attr("action"));
    });
    $('form').submit(function () {
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
    });
  });
</script>
</body>
</html>
