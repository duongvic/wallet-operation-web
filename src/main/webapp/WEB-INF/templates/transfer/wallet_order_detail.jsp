<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="wallet.process.detail.page.header"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <jsp:include page="../include_page/js_merchant.jsp">
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
      <jsp:param name="nav" value="${transferType}"/>
    </jsp:include>
    <!-- end: sidebar -->

    <c:choose>
      <c:when test="${transferType == 'FUND_TRANSFER'}">
        <spring:message code="menu.left.fundout.submenu.request.fundin.sof" var="labeSub"/>
        <spring:message code="menu.left.fund.transfer" var="labelTransfer"/>
      </c:when>
      <c:otherwise>
        <spring:message code="menu.left.fundout.submenu.request.transfer.wallet" var="labeSub"/>
        <spring:message code="menu.left.wallet.transfer" var="labelTransfer"/>
      </c:otherwise>
    </c:choose>


    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                <li><span>${labelTransfer}</span></li>
                <li><span>${labeSub}</span></li>
                <li><span class="nav-active"><spring:message code="common.detail"/> </span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md"></div>
          <section class="panel panel-default">
            <div class="panel-title">
              <h4 class="fl"><spring:message code="transfer.wallet.process.detail.text.info"/></h4>
              <ul class="panel-tools fl tool-filter">
                <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
              </ul>
              <div class="clr"></div>
            </div>
            <div class="panel-body report_search_form">
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="transfer.initiate.transfer.modelTransfer"/></label>
                <div class="col-sm-9 col-md-10 text-normal">${serviceType}</div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="transfer.initiate.transfer.sourceAccount"/></label>
                <div class="col-sm-9 col-md-10 text-normal">${sourceAccount}</div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="transfer.initiate.transfer.targetAccount"/></label>
                <div class="col-sm-9 col-md-10 text-normal">${targetAccount}</div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="transfer.initiate.transfer.transferAmount"/></label>
                <div class="col-sm-9 col-md-10 text-primary">${ewallet:formatNumber(amount)}&nbsp;VND</div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="fundorder.approve.transfer.createdBy"/></label>
                <div class="col-sm-9 col-md-10 text-normal">${creatorName}</div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="request.transfer.notable"/></label>
                <div class="col-sm-9 col-md-10 text-normal"><textarea rows="5" name="remark" minlength="1" maxlength="1024" class="form-control" required>${remark}</textarea></div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="request.transfer.requestFile"/></label>
                <div class="col-sm-9 col-md-10 text-normal">
                  <label id="fileName">
                    <c:choose>
                      <c:when test="${not empty attachmentName}"> ${attachmentName} </c:when>
                      <c:otherwise><spring:message code="wallet.process.detail.file.notexist"/></c:otherwise>
                    </c:choose>
                  </label>
                  <c:forEach var="item" items="${attachments }">
                    <p><img alt="" src="data:image/png;base64, <c:out value='${item.imageBase64}'/>" style="max-width: 90%;"></p>
                    <br/>
                  </c:forEach>
                </div>
              </div>
              <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label"><spring:message code="transfer.detail.status"/></label>
                <div class="col-sm-9 col-md-10 text-normal"><spring:message code="${orderStage}"/></div>
              </div>
            </div>
          </section>
        </div>
      </div>
      <!-- end: page -->
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
</body>
</html>