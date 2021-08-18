<%@ page import="vn.mog.ewallet.operation.web.controller.epin.EpinPurchaseOrderController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.epin.beans.EpinPurchaseOrderFlowStage" %>
<%@ page import="static vn.mog.ewallet.operation.web.controller.epin.EpinPurchaseOrderController.EPIN_PO_CONTROLLER" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.epin.beans.EpinPurchaseOrderStatus" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.epin.EpinPurchaseOrderController.EPIN_PO_DETAIL" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="service.exportcard.list.title.page"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/epin.css'/>" media="none" onload="if(media!='all')media='all'">
  <jsp:include page="../include_page/js_merchant.jsp">
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <script type="text/javascript" src="<c:url value='/assets/development/static/js/jquery.fileDownload.js'/>" async></script>
</head>

<body>
<section class="body">
  <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR','SALESUPPORT_MANAGER')" var="permitSearchCustomer"/>
  <jsp:include page="../include_page/header.jsp"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="merchantpo" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="service.exportcard.list.navigate.service"/></span></li>
                <li><span class="nav-active"><spring:message code="service.exportcard.list.navigate.exportcard"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <sec:authorize access="hasAnyRole('MERCHANT','CUSTOMER')" var="perExportEpin"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="form-inline">
            <div class="pull-left h4 mb-md mt-md"><spring:message code="service.exportcard.list.subnavigate.title"/></div>
            <div class="pull-right">
              <c:if test="${perExportEpin eq true}">
                <c:choose>
                  <c:when test="${checkMerchantExportCard eq true}">
                    <a href="request-po" class="btn btn-create mb-xs mt-xs"><i class="fa fa-plus"></i>&nbsp;<spring:message code="service.exportcard.create.subnavigate.btn.creatempo"/></a>
                  </c:when>
                  <c:otherwise>
                    <p style="color: indianred" class="mb-xs mt-sm"><spring:message code="service.exportcard.list.subnavigate.title.creatempo" arguments="${minBalance}"/></p>
                  </c:otherwise>
                </c:choose>
              </c:if>
            </div>
          </div>

          <c:url var="epinPoCon" value="<%=EPIN_PO_CONTROLLER%>"/>
          <spring:message code="service.exportcard.list.subnavigate.placeholder" var="placeholder"/>
          <spring:message code="select.choose.all" var="langChooseAll"/>
          <spring:message code="select.status" var="langStatus"/>
          <spring:message code="select.merchant" var="langMerchant"/>


          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none">

              <form action="" method="GET" id="tbl-filter">
                <div class="form-group ml-none mr-none">
                  <div class="input-group input-group-icon">
                    <span class="input-group-addon"><span class="icon" style="opacity: 0.4"><i class="fa fa-search-minus"></i></span></span>
                    <input type="text" id="search" name="quickSearch" class="form-control mb-xs" placeholder="${placeholder}" value="${param.quickSearch}"/>
                    <c:if test="${permitSearchCustomer eq true}">
                      <jsp:include page="../include_component/action_find_customers.jsp"/>
                    </c:if>
                  </div>
                </div>

                <div class="form-inline">
                  <jsp:include page="../include_component/date_range.jsp"/>

                  <div class="pull-right form-responsive bt-plt">
                    <c:set var="allStatus" value=","/>
                    <c:forEach var="st" items="${paramValues.statusIds}">
                      <c:set var="allStatus" value="${allStatus}${st},"/>
                    </c:forEach>
                    <select class="form-control multiple-select hidden" name="statusIds" multiple="multiple" id="statusIds">
                      <c:forEach var="item" items="${listStatus }">
                        <c:set var="status2" value=",${item.value()},"/>
                        <option ${fn:contains(allStatus, status2)?'selected':'' } value="${item.value()}">
                          <spring:message code="${item.displayText()}"/>
                        </option>
                      </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-primary ml-tiny"><i class="fa fa-search"></i>&nbsp;<spring:message code="common.btn.search"/></button>
                    <a href="?" class="btn btn-default nomal_color_bk bt-cancel"><spring:message code="service.exportcard.list.subnavigate.btn.cancel"/></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
              </form>

              <c:if test="${total > 0}">
                <div class="mt-xs">
                  <spring:message code="service.exportcard.list.table.header.totalcard"/>&nbsp;<b class="primary_color">${ewallet:formatNumber(totalOfCards)}</b> |
                  <spring:message code="service.exportcard.list.table.header.totalvalue"/>&nbsp;<b class="primary_color">${ewallet:formatNumber(totalOfMoney)}</b>&nbsp;(VND)
                  <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR', 'SALESUPPORT_LEADER', 'FINANCE', 'RECONCILIATION')"> |
                    <spring:message code="service.exportcard.list.table.header.totalCapitalValue"/>&nbsp;<b class="primary_color">${ewallet:formatNumber(totalOfCapitalValue)}</b>&nbsp;(VND)
                  </sec:authorize>
                </div>
              </c:if>

              <div class="clr"></div>

              <spring:message var="colNo" code="service.exportcard.list.table.column.no"/>
              <spring:message var="colCode" code="service.exportcard.list.table.column.code"/>
              <spring:message var="colMerchant" code="service.exportcard.list.table.column.merchant"/>
              <spring:message var="colKeyholder" code="service.exportcard.list.table.column.keyholder"/>
              <spring:message var="colFacevalue" code="service.exportcard.list.table.column.facevalue"/>
              <spring:message var="colQuantity" code="service.exportcard.list.table.column.quantity"/>
              <spring:message var="colCommission" code="service.exportcard.list.table.column.commission"/>
              <spring:message var="colCapitalValue" code="service.exportcard.list.table.column.capitalValue"/>
              <spring:message var="colNetamount" code="service.exportcard.list.table.column.netamount"/>
              <spring:message var="colPrebalance" code="service.exportcard.list.table.column.prebalance"/>
              <spring:message var="colPostbalance" code="service.exportcard.list.table.column.postbalance"/>
              <spring:message var="colCreatedtime" code="service.exportcard.list.table.column.createdtime"/>
              <spring:message var="colStage" code="service.exportcard.list.table.column.stage"/>
              <spring:message var="colAction" code="service.exportcard.list.table.column.action"/>

              <spring:message var="stagePending" code="service.exportcard.list.table.column.stage.pending"/>
              <spring:message var="stageInfo" code="service.exportcard.list.table.column.stage.info"/>
              <spring:message var="stageError" code="service.exportcard.list.table.column.stage.error"/>
              <spring:message var="stageDownfile" code="service.exportcard.list.table.column.stage.downfile"/>

              <spring:message var="actionDetail" code="service.exportcard.list.table.column.action.detail"/>
              <spring:message var="titleDownfile" code="service.exportcard.list.table.column.action.downfile"/>
              <spring:message var="titleResendPass" code="service.exportcard.list.table.column.action.repass"/>
              <spring:message var="actionDetail" code="service.exportcard.list.table.column.action.detail"/>
              <spring:message var="titleEditPo" code="service.exportcard.list.table.column.action.edit"/>

              <c:set var="epinStageInit" value="<%=EpinPurchaseOrderFlowStage.INIT.code%>"/>
              <c:set var="epinStageSaleRejected" value="<%=EpinPurchaseOrderFlowStage.SALESUPPORT_REJECTED.code%>"/>
              <c:set var="epinStageSaleVerify" value="<%=EpinPurchaseOrderFlowStage.SALESUPPORT_READY_TO_VERIFY.code%>"/>
              <c:set var="epinStageMerchantCancel" value="<%=EpinPurchaseOrderFlowStage.MERCHANT_CANCEL_ORDER.code%>"/>
              <c:set var="epinStageExportAllowed" value="<%=EpinPurchaseOrderFlowStage.EPIN_EXPORT_ALLOWED.code%>"/>
              <c:set var="epinStageFinished" value="<%=EpinPurchaseOrderFlowStage.FINISHED.code%>"/>

              <spring:message var="titleStageInit" code="service.exportcard.list.tbl.col.stage.init"/>
              <spring:message var="titleStageSaleRejected" code="service.exportcard.list.tbl.col.stage.sale.reject"/>
              <spring:message var="titleStageSaleVerify" code="service.exportcard.list.tbl.col.stage.sale.verify"/>
              <spring:message var="titleStageMerchantCancel" code="service.exportcard.list.tbl.col.stage.merchant.cancel"/>
              <spring:message var="titleStageExportAllowed" code="service.exportcard.list.tbl.col.stage.export.allowed"/>
              <spring:message var="titleStageFinish" code="service.exportcard.list.tbl.col.stage.finished"/>

              <c:set var="typePENDING" value="<%=EpinPurchaseOrderStatus.PENDING.code%>"/>
              <c:set var="typeFAIL" value="<%=EpinPurchaseOrderStatus.FAIL.code%>"/>
              <c:set var="typeSUCCESS" value="<%=EpinPurchaseOrderStatus.SUCCESS.code%>"/>

              <sec:authorize access="hasAnyRole('ADMIN_OPERATION', 'SALESUPPORT','SALESUPPORT_MANAGER')" var="perSaleSupportVerify"/>

              <display:table name="purchaseOrders" id="item"
                             requestURI="list"
                             pagesize="${pagesize}" partialList="true"
                             size="total"
                             sort="page"
                             class="table table-bordered table-striped mb-none">

                <%@ include file="../include_component/display_table.jsp" %>

                <display:column title="${colNo}">
                  <span id="row${item.purchaseOrderId}" class="rowid">
                      <c:out value="${offset + item_rowNum}"/>
                  </span>
                </display:column>

                <display:column title="${colCode}">
                  <a href="${contextPath}<%=EPIN_PO_DETAIL%>?status=1&status=2&poMerchantId=${item.purchaseOrderId}"
                     class="detail-link link-active" poMerchantId="${item.purchaseOrderId }">${item.poCode}</a>
                </display:column>
                <display:column title="${colMerchant}">
                  <c:choose>
                    <c:when test="${fn:contains(item.merchantName, '@')}">
                      ${fn:substringBefore(item.merchantName, "@")}</br>@${fn:substringAfter(item.merchantName, "@")}
                    </c:when>
                    <c:otherwise>
                      ${item.merchantName}
                    </c:otherwise>
                  </c:choose>
                </display:column>
                <display:column title="${colKeyholder}" property="keyHolder"/>
                <display:column title="${colFacevalue}" class="col-number-header" headerClass="col-number-header">${ewallet:formatNumber(item.totalValue)}</display:column>
                <display:column title="${colQuantity}" class="col-number-header" headerClass="col-number-header">${ewallet:formatNumber(item.totalQuantity)}</display:column>
                <display:column title="${colCommission}" class="col-number-header" headerClass="col-number-header">${ewallet:formatNumber(item.totalCommission)}</display:column>

                <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR', 'SALESUPPORT_LEADER', 'FINANCE', 'RECONCILIATION','SALESUPPORT_MANAGER')">
                  <display:column title="${colCapitalValue}" class="col-number-header" headerClass="col-number-header">${ewallet:formatNumber(item.capitalValue)}</display:column>
                </sec:authorize>

                <display:column title="${colNetamount}" class="col-number-header" headerClass="col-number-header">${ewallet:formatNumber(item.totalValue - item.totalCommission)}</display:column>
                <display:column title="${colCreatedtime}" class="right" headerClass="right" property="createdTime" format="{0,date,HH:mm dd-MM-yyyy}"/>

                <display:column title="${colStage}" class="center" headerClass="center" style="min-width: 91px;">
                  <c:choose>
                    <c:when test="${item.stage == epinStageInit}">
                      <c:choose><c:when test="${perExportEpin}">
                        <a href="edit-po?id=${item.purchaseOrderId}" title="${titleStageInit}"><i class="fa fa-warning warning_status"></i></a>
                      </c:when><c:otherwise>
                        <a title="${titleStageInit}" class="not-role"><i class="fa fa-warning warning_status"></i></a>
                      </c:otherwise></c:choose>
                      <a title="${titleStageSaleRejected}" class="status_number">1</a>
                      <a title="${titleStageSaleVerify}" class="status_number">2</a>
                      <a title="${titleStageMerchantCancel}" class="status_number">3</a>
                      <a title="${titleStageExportAllowed}" class="status_number">4</a>
                      <%--<a title="${titleStageFinish}" class="status_number">6</a>--%>
                    </c:when>

                    <c:when test="${item.stage == epinStageSaleRejected}">
                      <a title="${titleStageInit}"><i class="fa fa-check check_status"></i></a>
                      <c:choose><c:when test="${perExportEpin}">
                        <a href="edit-po?id=${item.purchaseOrderId}" title="${titleStageSaleRejected}"><i class="fa fa-times reject_status"></i></a>
                      </c:when><c:otherwise>
                        <a title="${titleStageSaleRejected}" class="not-role"><i class="fa fa-times reject_status"></i></a>
                      </c:otherwise></c:choose>
                      <a title="${titleStageSaleVerify}" class="status_number">2</a>
                      <a title="${titleStageMerchantCancel}" class="status_number">3</a>
                      <a title="${titleStageExportAllowed}" class="status_number">4</a>
                      <%--<a title="${titleStageFinish}" class="status_number">6</a--%>
                    </c:when>

                    <c:when test="${item.stage == epinStageSaleVerify}">
                      <a title="${titleStageInit}"><i class="fa fa-check check_status"></i></a>
                      <a title="${titleStageSaleRejected}" class="status_number">1</a>
                      <c:choose><c:when test="${perSaleSupportVerify}">
                          <a title="${titleStageSaleVerify}" href="epin-approve?epinId=${item.purchaseOrderId}"><i class="fa fa-warning warning_status"></i></a>
                      </c:when><c:otherwise>
                          <a title="${titleStageSaleVerify}" class="not-role"><i class="fa fa-warning warning_status"></i></a>
                      </c:otherwise></c:choose>
                      <a title="${titleStageMerchantCancel}" class="status_number">3</a>
                      <a title="${titleStageExportAllowed}" class="status_number">4</a>
                      <%--<a title="${titleStageFinish}" class="status_number">6</a>--%>
                    </c:when>

                    <c:when test="${item.stage == epinStageMerchantCancel}">
                      <a title="${titleStageInit}"><i class="fa fa-check check_status"></i></a>
                      <a title="${titleStageSaleRejected}" class="status_number">1</a>
                      <a title="${titleStageSaleVerify}"><i class="fa fa-check check_status"></i></a>
                      <a title="${titleStageMerchantCancel}"><i class="fa fa-times reject_status"></i></a>
                      <a title="${titleStageExportAllowed}" class="status_number">4</a>
                      <%--<a title="${titleStageFinish}" class="status_number">6</a>--%>
                    </c:when>

                    <c:when test="${item.stage == epinStageExportAllowed}">
                      <a title="${titleStageInit}"><i class="fa fa-check check_status"></i></a>
                      <a title="${titleStageSaleRejected}" class="status_number">1</a>
                      <a title="${titleStageSaleVerify}"><i class="fa fa-check check_status"></i></a>
                      <a title="${titleStageMerchantCancel}" class="status_number">3</a>
                      <c:choose><c:when test="${perExportEpin}">
                        <a title="${titleStageExportAllowed}" href="epin-export?epinId=${item.purchaseOrderId}"><i class="fa fa-warning warning_status"></i></a>
                      </c:when><c:otherwise>
                        <a title="${titleStageExportAllowed}" class="not-role"><i class="fa fa-warning warning_status"></i></a>
                      </c:otherwise></c:choose>
                      <%--<a title="${titleStageFinish}" class="status_number">6</a>--%>
                    </c:when>

                    <c:when test="${item.stage == epinStageFinished}">
                      <a title="${titleStageInit}"><i class="fa fa-check check_status"></i></a>
                      <a title="${titleStageSaleRejected}" class="status_number">1</a>
                      <a title="${titleStageSaleVerify}"><i class="fa fa-check check_status"></i></a>
                      <a title="${titleStageMerchantCancel}" class="status_number">3</a>
                      <a title="${titleStageExportAllowed}"><i class="fa fa-check check_status"></i></a>
                      <%--<a title="${titleStageFinish}"><i class="fa fa-check check_status"></i></a>--%>
                    </c:when>

                    <c:otherwise>
                      <a title="${titleStageInit}" class="status_number">0</a>
                      <a title="${titleStageSaleRejected}" class="status_number">1</a>
                      <a title="${titleStageSaleVerify}" class="status_number">2</a>
                      <a title="${titleStageMerchantCancel}" class="status_number">3</a>
                      <a title="${titleStageExportAllowed}" class="status_number">4</a>
                      <%--<a title="${titleStageFinish}" class="status_number">6</a>--%>
                    </c:otherwise>
                  </c:choose>
                </display:column>

                <display:column title="${colAction}" class="action_icon right" headerClass="right">

                  <c:choose>
                    <c:when test="${item.stage == epinStageFinished}">
                      <c:choose><c:when test="${perExportEpin eq true}">
                          <a href="#" title="${titleDownfile}" class="link-export" poMerchantId="${item.purchaseOrderId }"> <i class="fa fa-download" aria-hidden="true"></i> </a>
                          <a href="#" title="${titleResendPass}" class="resend-pass" poMerchantId="${item.purchaseOrderId }"> <i class="fa fa-refresh" aria-hidden="true"> </i> </a>
                      </c:when><c:otherwise>
                          <a href="#" class="not-role" title="${titleDownfile}"><i class="fa fa-download" aria-hidden="true"></i></a>
                          <a href="#" class="not-role" title="${titleResendPass}"><i class="fa fa-refresh" aria-hidden="true"></i> </a>
                      </c:otherwise></c:choose>
                      <a href="#" class="not-role" title="${titleEditPo}"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                    </c:when>
                    <c:when test="${item.stage == epinStageInit}">
                      <c:choose><c:when test="${perExportEpin eq true}">
                          <a href="edit-po?id=${item.purchaseOrderId}" title="${titleEditPo}"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                      </c:when><c:otherwise>
                          <a href="#" class="not-role" title="${titleEditPo}"> <i class="fa fa-pencil" aria-hidden="true"></i> </a>
                      </c:otherwise></c:choose>
                    </c:when>
                  </c:choose>
                  <a href="${contextPath}<%=EPIN_PO_DETAIL%>?status=1&status=2&poMerchantId=${item.purchaseOrderId}"
                     title="${actionDetail}"
                     class="detail-link link-active"
                     poMerchantId="${item.purchaseOrderId }"><i class="fa fa-info-circle "></i></a>

                  <a href="#" title="<spring:message code="menu.left.service.submenu.exportcard"/>"
                     class="export-new-link"
                     poMerchantId="${item.purchaseOrderId }">
                    <i class="fa fa-file-excel-o" style="color: #15b11be3"></i>
                  </a>
                </display:column>
              </display:table>
            </div>
          </section>
        </div>
      </div>
      <!-- end: page -->
    </section>
    <jsp:include page="mpo_summary.jsp"/>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_page/date_picker.jsp">
  <jsp:param name="isFullTime" value="true"/>
  <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>

<jsp:include page="../include_component/search_form_commons.jsp">
  <jsp:param name="selChannel" value="true"/>
</jsp:include>

<script type="text/javascript">
  $(document).ready(function () {

    $('#statusIds').multiselect({
      enableFiltering: true,
      includeSelectAllOption: true,
      selectAllValue: '',
      selectAllText: '<spring:message code="select.choose.all"/>',
      maxHeight: 400,
      dropUp: true,
      nonSelectedText: '<spring:message code="select.status"/>',
      inheritClass: true,
      numberDisplayed: 1
    });

    <c:if test="${perExportEpin eq false}">
    var data = '1,2';
    var valArr = data.split(",");
    var i = 0, size = valArr.length;
    for (i; i < size; i++) {
      $('#status').multiselect('select', valArr[i]);
    }
    </c:if>

    $('.link-export').click(function () {
      var id = $(this).attr('poMerchantId');
      $.MessageBox({
        buttonDone: '<spring:message code="popup.button.yes"/>',
        buttonFail: '<spring:message code="popup.button.no"/>',
        message: '<spring:message code="popup.message.confirm.download.file"/>'
      }).done(function () {
        var urlEpin = '${epinPoCon}/export-epin?poMerchantId=' + id;
        $.fileDownload(urlEpin)
        .done(function () { $.MessageBox({message: '<spring:message code="common.file.download.success"/>'}); })
        .fail(function () { $.MessageBox({message: '<spring:message code="common.file.download.fail"/>'}); });
      });
      return false;
    });

    $('.resend-pass').click(function () {
      var id = $(this).attr('poMerchantId');
      if (id != null && id != '') {
        $.MessageBox({
          buttonDone: '<spring:message code="popup.button.yes"/>',
          buttonFail: '<spring:message code="popup.button.no"/>',
          message: '<spring:message code="popup.message.confirm.reset.pass"/>'
        }).done(function () {
          $.ajax({
            type: 'POST',
            url: 'resendPass',
            data: {
              poMerchantId: id
            },
            success: function (data) {
              if (data.code == 0) {
                $.MessageBox({message: '<spring:message code="popup.message.confirm.receive.pass"/>'});
              } else {
                $.MessageBox({message: data.message});
              }
            }
          });
        });
        return false;
      } else {
        $.MessageBox({message: "<spring:message code="message.data.error"/> "});
        return false;
      }
    });

    $('button.confirmRequest').click(function () {
      var id = $('form[name=sumary] input[name=poMerchantId]').val();
      if (id != null && id != '') {
        $(this).button('loading');
        $.post('getOtp', {poMerchantId: id}, function (data) {
          if (data.code == 0) {
            location.href = "request-otp?id=" + id;
          } else {
            $.MessageBox({message: data.message});
            $(this).button('reset');
          }
        });
      }
      return false;
    });

    <%--$('a.detail-link').click(function () {--%>
      <%--var poMerchantId = $(this).attr("poMerchantId");--%>
      <%--var searchURL = '';--%>
      <%--if (window.location.search.indexOf("?") >= 0) {--%>
        <%--searchURL = ctx + '<%=EPIN_PO_DETAIL%>' + window.location.search + '&poMerchantId=' + poMerchantId;--%>
      <%--} else {--%>
        <%--searchURL = ctx + '<%=EPIN_PO_DETAIL%>?' + 'poMerchantId=' + poMerchantId;--%>
      <%--}--%>
      <%--window.location.href = searchURL;--%>
    <%--});--%>

    $('a.export-new-link').click(function () {
      var id = $(this).attr('poMerchantId');
      $.MessageBox({
        buttonDone: '<spring:message code="popup.button.yes"/>',
        buttonFail: '<spring:message code="popup.button.no"/>',
        message: '<spring:message code="popup.message.confirm.download.file"/>'
      }).done(function () {
        var urlEpin = '<%=request.getContextPath()%>/service/merchant-po/export-po-new?poMerchantId=' + id;
        $.fileDownload(urlEpin)
        .done(function () { $.MessageBox({message: '<spring:message code="common.file.download.success"/>'}); })
        .fail(function () { $.MessageBox({message: '<spring:message code="common.file.download.fail"/>'}); });
      });
      return false;
    });

  });
</script>
</body>
</html>
