<%@ page import="vn.mog.ewallet.operation.web.controller.epin.EpinPurchaseOrderAPIController" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="service.exportcard.confirm.title.page"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <jsp:include page="../include_page/js.jsp">
    <jsp:param name="tableLib" value="true"/>
  </jsp:include>
</head>

<body>
<section class="body">

  <jsp:include page="../include_page/header.jsp"/>

  <sec:authorize access="hasAnyRole('MERCHANT','CUSTOMER')" var="perExportEpin"/>
  <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT','SALESUPPORT_MANAGER' )" var="perApproveEpin"/>
  <c:set var="urlEpinPoList" value="<%=EpinPurchaseOrderAPIController.EPIN_PO_LIST%>"/>
  <c:url var="epinConURI" value="<%=EpinPurchaseOrderAPIController.EPIN_PO_CONTROLLER%>"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="merchantpoAPI" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="service.exportcard.confirm.navigate.service"/></span></li>
                <c:set var="urlEpinPoList" value="<%=EpinPurchaseOrderAPIController.EPIN_PO_LIST%>"/>
                <li><span>
                <c:choose>
                  <c:when test="${perExportEpin eq true}">
                    <a href="<c:url value="${urlEpinPoList}"/>" id="hight-title" class="hight-title"><spring:message code="service.exportcard.confirm.navigate.exportcard"/></a>
                  </c:when>
                  <c:otherwise>
                  <a href="<c:url value="${urlEpinPoList}"/>?status=1&status=2&multiselect=1&multiselect=2" id="hight-title" class="hight-title">
                    <spring:message code="service.exportcard.confirm.navigate.exportcard"/>
                  </a>
                  </c:otherwise>
                </c:choose>
                </span></li>
                <li><span class="nav-active"><spring:message code="service.exportcard.confirm.navigate.confirmcard"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md">
            <spring:message code="service.exportcard.approve.subnavigate.title"/>
          </div>
        </div>

        <section class="panel search_payment panel-default">
          <div class="panel-body pt-none pb-none">
            <div class="wizard-tabs mt-none mb-none">
              <ul class="wizard-steps">
                <li class="col-xs-4 pl-none pr-none"><a class="text-center"><span class="badge hidden-xs">1</span>&nbsp;<spring:message code="service.exportcard.epin.stepone.request"/> </a></li>
                <li class="col-xs-4 pl-none pr-none active"><a class="text-center"><span class="badge hidden-xs">2</span>&nbsp;<spring:message code="service.exportcard.epin.steptwo.approve"/> </a></li>
                <li class="col-xs-4 pl-none pr-none"><a class="text-center"><span class="badge hidden-xs">3</span>&nbsp;<spring:message code="service.exportcard.epin.stepthree.export"/> </a></li>
              </ul>
            </div>
          </div>

          <div class="tab-content">
            <div id="tab1" class="tab-pane active">
              <section class="panel">
                <div class="panel-body report_search_form pb-none">
                  <div class="row">
                    <form name="telco" id="telco" action="${epinConURI}/epin-approve" method="post" >
                      <input type="hidden" name="epinId" value="${epinId}"/>
                      <div class="col-md-12 col-lg-12 col-xl-12">
                        <section class="panel panel-default po-detail-line">

                          <div class="form-group" style="margin: 0">
                            <label class="col-md-1 control-label" for="note">*&nbsp;<spring:message code="service.exportcard.confirm.guilde.note"/></label>

                            <spring:message code="service.exportcard.confirm.guilde.content" var="guildContent"/>
                            <div class="col-md-4 tertiary_color" id="note">
                              <spring:message code="service.exportcard.confirm.guilde.text"/>&nbsp;
                              <i id="note" class="fa fa-question-circle text-muted m-xs"
                                 data-toggle="popover"
                                 data-trigger="hover"
                                 data-placement="right"
                                 data-content='${guildContent}' data-html="true" data-original-title="" title=""></i>
                            </div>
                          </div>

                          <div class="panel-title">
                            <div class="modal-header pl-none">
                              <h4 class="modal-title">
                                <spring:message code="service.exportcard.confirm.summary.title"/>
                              </h4>
                            </div>
                            <div class="modal-body pl-none pr-none" style="font-size: 13px;">
                              <div class="form-group">
                                <label class="col-xs-4"> <spring:message code="service.exportcard.confirm.summary.totalmoney"/> </label>
                                <div class="col-xs-8 col-lg-5"><p class="primary_color" align="right">${totalMoney}</p></div>
                              </div>
                              <div class="form-group">
                                <label class="col-xs-4"> <spring:message code="service.exportcard.confirm.summary.quantity"/> </label>
                                <div class="col-xs-8 col-lg-5"><p class="primary_color" align="right">${totalQuantity}</p></div>
                              </div>
                              <div class="form-group">
                                <label class="col-xs-4"> <spring:message code="service.exportcard.confirm.summary.commission"/> </label>
                                <div class="col-xs-8 col-lg-5"><p class="primary_color total-am" align="right">${totalCommmision}</p></div>
                              </div>
                              <div class="form-group">
                                <label class="col-xs-6 col-lg-4"> <spring:message code="service.exportcard.confirm.summary.totalpay"/> </label>
                                <div class="col-xs-6 col-lg-5"><p class="primary_color" align="right">${totalPayable}</p></div>
                              </div>
                            </div>
                          </div>

                          <spring:message code="service.exportcard.confirm.card.available" var="langAvailable"/>

                          <div class="panel-body">
                            <div class="table-responsive">
                              <table class="table table-bordered table-striped mb-none">
                                <thead>
                                <tr>
                                  <th><spring:message code="service.exportcard.confirm.table.column.no"/></th>
                                  <th style="width:10%"><spring:message code="service.exportcard.confirm.table.column.cardtype"/></th>
                                  <th class="text-right"><spring:message code="service.exportcard.confirm.table.column.facevalue"/></th>
                                  <th class="text-right"><spring:message code="service.exportcard.confirm.table.column.quantity"/></th>
                                  <%--<th class="text-right"><spring:message code="service.exportcard.confirm.table.column.cardstock"/></th>--%>
                                  <%--<th class="text-center"><spring:message code="service.exportcard.confirm.table.column.status"/></th>--%>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${listPOdetail }" varStatus="status">
                                  <tr class="${item.cardType }">
                                    <td class="stt">${status.index +1}</td>
                                    <td>${item.cardType }</td>
                                    <td class="text-right">${ewallet:formatNumber(item.faceValue)}</td>
                                    <td class="text-right">${ewallet:formatNumber(item.quantity)}</td>
                                    <%--<td class="text-right">${ewallet:formatNumber(item.cardStock)}</td>--%>
                                    <%--<td class="text-center ${item.status == langAvailable ? 'primary_color' : 'secondary_color' }"> ${item.status } </td>--%>
                                  </tr>
                                </c:forEach>
                                </tbody>
                              </table>
                            </div>
                          </div>
                        </section>

                        <div class="panel-body">
                          <div class="form-group">
                            <label class="col-xs-4"><spring:message code="service.exportcard.confirm.summary.remark"/>&nbsp;<spans style="color: red">*</spans></label>
                            <div class="col-xs-8 col-lg-5">
                              <textarea class="form-control" id="remark" name="remark" rows="3" required maxlength="1024" minlength="1"></textarea>
                            </div>
                          </div>
                        </div>

                        <div class="panel-body">
                          <div class="alert alert-default mb-none mt-md p-sm">
                            <div class="checkbox-custom checkbox-success">
                              <input type="checkbox" name="ckaccess" id="checkboxExample3">
                              <label for="checkboxExample3">
                                <spring:message code="service.exportcard.confirm.checkbox"/>
                              </label>
                            </div>
                          </div>
                        </div>

                        <div class="form-group pb-none">
                          <input type="hidden" name="action">
                          <spring:message code="service.exportcard.otp.waiting" var="waiting"/>
                          <c:choose>
                            <c:when test="${perApproveEpin}">
                              <button type="submit" value="approve" class="btn btn-success pull-right ml-xs" ${disabledNext}
                                      data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waiting}"><spring:message code="common.btn.approve"/>&nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>

                              <button type="submit" value="reject" class="btn btn-danger pull-right"
                                      data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waiting}"><i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.reject"/></button>
                              <button type="button" value="previous" id="stepprevious" class="btn btn-default"><i class="fa fa-arrow-left" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.back"/></button>
                            </c:when>
                            <c:otherwise>
                              <button type="button" value="previous" id="stepprevious" class="btn btn-default"><i class="fa fa-arrow-left" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.back"/></button>
                            </c:otherwise>
                          </c:choose>
                          <div class="clr"></div>
                        </div>
                      </div>
                      <sec:csrfInput />
                    </form>
                  </div>
                </div>
              </section>
            </div>
          </div>
          <div id="tab2" class="tab-pane"></div>
        </section>
      </div>
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<script type="text/javascript">
  $(document).ready(function () {
    $('form[name="telco"] button:submit').click(function () {

      var value = $("#telco").context.activeElement.value;
      if (value === "previous") {
      } else {
        if (!$('#checkboxExample3').is(':checked')) {
          $.MessageBox({message: '<spring:message code="message.mpo.stage.approve.confirm"/>'});
          return false;
        }

        if ($('#remark').val().length === 0) {
          $.MessageBox({message: '<spring:message code="message.mpo.stage.approve.remark"/>'});
          return false;
        }
      }

      $('form[name="telco"] input[name=action]').val($(this).val());

      if (value === 'reject') {
        $(this).button('loading');
      } else if (value === 'approve') {
        <c:if test="${disabledNext eq '' or disabledNext eq null}">
        $(this).button('loading');
        </c:if>
      }

    });

    $("#stepprevious").click(function () {
      window.location.href = '${contextPath}<%=EpinPurchaseOrderAPIController.EPIN_PO_LIST%>';
    });
  });
</script>
</body>
</html>
