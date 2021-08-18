<%@ page import="vn.mog.ewallet.operation.web.controller.epin.EpinPurchaseOrderAPIController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="service.exportcard.detail.title.page"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <jsp:include page="../include_page/js.jsp">
    <jsp:param name="tableLib" value="true"/>
  </jsp:include>
</head>

<body>
<section class="body">
  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="merchantpoAPI" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->

    <c:url var="epinListUri" value="<%=EpinPurchaseOrderAPIController.EPIN_PO_LIST%>"/>

    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <form action="${epinListUri}" method="GET" id="search-merchant-po">
              <input type="hidden" name="quickSearch" value="${quickSearch}">
              <input type="hidden" name="range" value="${range}">
              <input type="hidden" name="d-49520-p" value="${numberPage}">
              <c:forEach var="st" items="${status}">
                <input type="hidden" name="status" value="${st}">
              </c:forEach>
              <c:forEach var="st" items="${customerIds}">
                <input type="hidden" name="customerIds" value="${st}">
              </c:forEach>

              <div class="page-header-left">
                <ol class="breadcrumbs">
                  <li><a href="#"><i class="fa fa-home"></i></a></li>
                  <li><span><spring:message code="service.exportcard.list.navigate.service"/></span></li>
                  <li><span><a href="#" id="hight-title" class="hight-title"><spring:message code="service.exportcard.list.navigate.exportcard"/></a></span></li>
                  <li><span class="nav-active"><spring:message code="service.exportcard.detail.navigate.exportdetail"/></span></li>
                </ol>
              </div>
            </form>
          </div>
        </div>
      </header>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">

          <div class="h4 mb-md">
            <spring:message code="service.epin.deetail.subnav.title"/>
          </div>

          <section class="panel panel-default">
            <div class="panel-title pl-none">
              <h4 class="fl"><spring:message code="service.exportcard.detail.info.title"/></h4>
              <ul class="panel-tools fl tool-filter">
                <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
              </ul>
              <div class="clr"></div>
            </div>
            <div class="panel-body">
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"> <spring:message code="service.exportcard.detail.info.mpocode"/> </label>
                <div class="col-sm-9 col-md-10 text-normal"> ${merchantPO.poCode} </div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="service.exportcard.detail.info.merchant"/></label>
                <div class="col-sm-9 col-md-10 text-normal">${merchantPO.merchantName}</div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="service.exportcard.detail.info.keyholder"/></label>
                <div class="col-sm-9 col-md-10 text-normal">${merchantPO.keyHolder}</div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="service.exportcard.detail.info.totalValue"/></label>
                <div class="col-sm-9 col-md-10 primary_color">${ewallet:formatNumber(merchantPO.totalValue)} (VND)</div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="service.exportcard.detail.info.totalQuantity"/></label>
                <div class="col-sm-9 col-md-10 text-normal">${ewallet:formatNumber(merchantPO.totalQuantity)}</div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="service.exportcard.detail.info.totalNetAmount"/></label>
                <div class="col-sm-9 col-md-10 primary_color">${ewallet:formatNumber(merchantPO.totalValue - merchantPO.totalCommission)} (VND)</div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="service.exportcard.detail.info.createdTime"/></label>
                <div class="col-sm-9 col-md-10 text-normal"><fmt:formatDate pattern="HH:mm dd/MM/yyyy" value="${merchantPO.createdTime}"/></div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="service.exportcard.detail.info.status"/></label>
                <div class="col-sm-9 col-md-10 text-normal"><spring:message code="${merchantPO.getLabelState()}"/></div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="service.exportcard.detail.info.remark"/></label>
                <div class="col-sm-9 col-md-10 text-normal">${merchantPO.info}</div>
              </div>
            </div>
          </section>

          <section class="panel search_payment panel-default">
            <div class="panel-title pl-none">
              <h4 class="fl"><spring:message code="service.exportcard.detail.card.title"/></h4>
              <ul class="panel-tools fl tool-filter">
                <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
              </ul>
              <div class="clr"></div>
            </div>
            <div class="panel-body">
              <div class="table-responsive qlsp no-per-page">
                <table class="table table-bordered table-striped mb-none mt-none">
                  <thead>
                  <tr>
                    <th class="stt"><spring:message code="service.exportcard.detail.card.stt"/></th>
                    <th><spring:message code="service.exportcard.detail.card.cardtype"/></th>
                    <th class="text-right"><spring:message code="service.exportcard.detail.card.faceValue"/></th>
                    <th class="text-right"><spring:message code="service.exportcard.detail.card.quantity"/></th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${merchantPO.purchaseOrderDetails}" var="item" varStatus="var2">
                    <tr>
                      <td>${var2.index + 1 }</td>
                      <td>${item.cardType}</td>
                      <td class="text-right">${ewallet:formatNumber(item.faceValue)}</td>
                      <td class="text-right">${item.quantity}</td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
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
  $(document).ready(function () {
    $('#hight-title').click(function () {
      $('#search-merchant-po').submit();
    });
  });
</script>
</body>
</html>
