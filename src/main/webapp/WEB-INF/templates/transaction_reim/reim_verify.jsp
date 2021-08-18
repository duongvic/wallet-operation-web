<%@ page import="vn.mog.ewallet.operation.web.controller.translog.TransactionReimController" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="common.confirm.transfer"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <jsp:include page="../include_page/js_merchant.jsp"/>
</head>

<body>
<section class="body">
  <jsp:include page="../include_page/header.jsp"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="reimTxn" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">


      <header class="page-header">
        <div class="row">
          <div class="col-xs-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="reim.title.transfer"/></span></li>
                <li><span><a href="<c:url value='<%=TransactionReimController.TRANS_REIM_LIST%>'/>" id="hight-title" class="hight-title"><spring:message code="reim.title.request.transfer"/> </a></span></li>
                <%--<li><span class="nav-active"><spring:message code="common.title.verify.request"/> </span></li>--%>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <!-- start: page -->
      <c:url var="urlTransReimRequestOTP" value="<%=TransactionReimController.TRANS_REIM_CONTROLLER%>"/>
      <div class="content-body-wrap">

        <div class="container-fluid pt-md">
          <div class="mb-md">
            <div class="wizard-tabs">
              <ul class="wizard-steps">
                <li class="col-xs-6 pl-none pr-none"><a href="#tab1" data-toggle="tab" class="text-center"><span class="badge hidden-xs">1</span>&nbsp; <spring:message code="common.btn.request"/> </a></li>
                <li class="col-xs-6 pl-none pr-none active"><a class="text-center"> <span class="badge hidden-xs">2</span>&nbsp; <spring:message code="common.btn.approve"/> </a></li>
              </ul>
            </div>

            <div class="tab-content">
              <section class="panel search_payment panel-default">
                <form action="${urlTransReimRequestOTP}/request/otp" method="post" name="transfer">
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="reim.label.source.account"/> </label>
                    <%--<div class="col-md-6"><p>${merchant}</p></div>--%>
                    <div class="col-md-6"><p>${sourceAccount}vuilachinh</p></div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="reim.label.target.account"/> </label>
                    <div class="col-md-6"><p>${targetAccount} TM SOF Wallet On Hand</p></div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="reim.label.transfer.amount"/> </label>
                    <div class="col-md-6"><p>${amount}5.0000.000 VND</p></div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="reim.label.transaction.reference"/> </label>
                    <div class="col-md-6">
                      <p><a href="#" class="link-active">165324</a></p>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="reim.label.created.by"/> </label>
                    <div class="col-md-6">
                      <span class="">${account}lienlt</span>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="reim.label.remark"/> </label>
                    <div class="col-md-6">
                      <span class="">${remark}Hoan tien Funin</span>
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-md-3 control-label"><spring:message code="common.title.remark"/>:</label>
                    <div class="col-md-6">
                      <textarea rows="5" name="remark" class="form-control">${remark}</textarea>
                    </div>
                  </div>

                  <div class="alert alert-default mb-none mt-md p-sm">
                    <div class="checkbox-custom checkbox-success">
                      <input type="checkbox" name="ckaccess" id="checkboxExample3">
                      <label for="checkboxExample3"><spring:message code="common.message.u.are.sure.information.are.right"/></label>
                    </div>
                  </div>

                  <div class="report_search_bt pull-right form-responsive">
                    <button type="submit" name="action" value="reject" class="btn btn-danger"><i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.reject"/></button>
                    <button type="submit" name="action" value="approve" class="btn btn-success"><spring:message code="common.btn.approve"/>&nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                  </div>
                  <div class="clr"></div>
                  <sec:csrfInput/></form>
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
