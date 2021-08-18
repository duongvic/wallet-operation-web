<%@ page import="vn.mog.ewallet.operation.web.controller.epin.EpinPurchaseOrderController" %>
<%@ page
        import="static vn.mog.ewallet.operation.web.controller.epin.EpinPurchaseOrderController.EPIN_PO_CONTROLLER" %>

<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="service.exportcard.export.title.page"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <jsp:include page="../include_page/js.jsp"/>
</head>

<body>
<section class="body">
  <jsp:include page="../include_page/header.jsp"/>
  <c:url var="urlEpinList" value="<%=EpinPurchaseOrderController.EPIN_PO_LIST%>"/>
  <c:url var="epinConUri" value="<%=EPIN_PO_CONTROLLER%>"/>

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
                <li><span><spring:message code="service.exportcard.export.navigation.service"/></span></li>
                <sec:authorize access="hasAnyRole('MERCHANT','CUSTOMER')" var="perExportEpin"/>
                <c:url var="urlEpinList" value="<%=EpinPurchaseOrderController.EPIN_PO_LIST%>" />
                <c:choose>
                  <c:when test="${perExportEpin eq true}">
                    <li><span><a href="${urlEpinList}" id="hight-title" class="hight-title"><spring:message code="service.exportcard.export.navigation.card"/></a></span></li>
                  </c:when>
                  <c:otherwise>
                    <li><span><a href="${urlEpinList}?search=&range=&search=Search&status=1&status=2&multiselect=1&multiselect=2" id="hight-title" class="hight-title"><spring:message code="service.exportcard.export.navigation.card"/> </a></span></li>
                  </c:otherwise>
                </c:choose>
                <li><span class="nav-active"> <spring:message code="service.exportcard.export.navigation.infocard"/> </span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <div class="content-body-wrap">
        <div class="container-fluid">

          <div class="h4 mb-md">
            <spring:message code="service.exportcard.export.subnavigate.title"/>
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
            </div>
          </section>

          <div class="tab-content">
            <div id="tab1" class="tab-pane active">
              <form name="export" action="${epinConUri}/export-mpo" method="post">
                <input type="hidden" name="poMerchantId" value="${param.id}"/>
                <div class="form-group">
                  <label class="col-xs-4 col-sm-1 control-label" for="note">*<spring:message code="common.note"/></label>
                  <div class="col-xs-8 tertiary_color" id="note">
                    <spring:message code="service.exportcard.export.guide.title"/>
                    <spring:message code="service.exportcard.export.guide.content" var="guideContent"/>
                    &nbsp;<i id="note" class="fa fa-question-circle text-muted m-xs"
                             data-toggle="popover"
                             data-trigger="hover"
                             data-placement="right"
                             data-content='${guideContent}' data-html="true" data-original-title="" title=""></i>
                  </div>
                </div>
                <section class="panel panel-default">
                  <div class="form-group">
                    <label class="col-md-3 control-label"></label>
                    <div class="col-md-6">
                      <c:forEach var="phone" items="${listPhone }">
                        <p class="primary_color" style="font-size: 20px;">${phone }</p>
                      </c:forEach>
                      <p class="secondary_color">
                        <spring:message code="service.exportcard.export.guide.note"/>
                      </p>
                    </div>
                  </div>

                  <div class="form-group otp text-center">
                    <button type="submit" class="btn btn-primary submit_bt" name="submit"><i class="fa fa-download" aria-hidden="true"></i> <spring:message code="service.exportcard.export.btn.exportcard"/></button>
                    <button type="submit" class="btn btn-success submit_bt resend-pass" name="submit"><i class="fa fa-refresh" aria-hidden="true"></i> <spring:message code="service.exportcard.export.btn.resendpass"/></button>
                  </div>
                </section>
                <sec:csrfInput/>
              </form>
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
    $('.resend-pass').click(function () {
      var id = "${param.id}";
      $.MessageBox({
        buttonDone: '<spring:message code="popup.button.yes"/>',
        buttonFail: '<spring:message code="popup.button.no"/>',
        message: '<spring:message code="popup.message.confirm.recent.pass"/>'
      }).done(function () {
        $.post('resendPass', {poMerchantId: id}, function (data) {
          if (data.code == 0) {
            $.MessageBox({message: '<spring:message code="popup.message.confirm.receive.pass"/>'});
          } else {
            $.MessageBox({message: data.message});
          }
        });
      });
      return false;
    });
  });
</script>
</body>
</html>