<%@ page import="vn.mog.ewallet.operation.web.controller.p2p.P2pTransferController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="p2p.title.page"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <jsp:include page="../include_page/js_merchant.jsp">
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
</head>

<body>
<section class="body">
  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="p2p" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span class="nav-active"><spring:message code="p2p.navigate.p2p"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>
      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md">
            <spring:message code="p2p.navigate.p2p"/>
          </div>
          <jsp:include page="../transaction/transaction-content.jsp">
            <jsp:param name="isUserSearchSourceOfFundType" value="false"/>
          </jsp:include>
        </div>
      </div>
      <!-- end: page -->
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_page/date_picker.jsp">
  <jsp:param name="isFullTime" value="true"/>
  <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>
<jsp:include page="../include_component/export_excel.jsp"/>
<jsp:include page="../include_component/search_form_commons.jsp">
  <jsp:param name="selCustomer" value="true"/>
  <jsp:param name="selTxnStatus" value="true"/>
  <jsp:param name="selSourceOfFund" value="true"/>
</jsp:include>
<script src="<c:url value="/assets/development/static/js/transaction.js"/>" async></script>
<script type="text/javascript">
  $(document).ready(function () {
    $('a.detail-link').click(function () {
      var txnId = $(this).attr("txnId");
      var searchURL = '';
      if (window.location.search.indexOf("?") >= 0) {
        searchURL = ctx + '<%=P2pTransferController.P2P_TRANSFER_DETAIL_URL%>' + window.location.search + '&txnId=' + txnId;
      } else {
        searchURL = ctx + '<%=P2pTransferController.P2P_TRANSFER_DETAIL_URL%>?' + 'txnId=' + txnId;
      }
      window.location.href = searchURL;
    });

  });
  function ClearFields() {
    document.getElementById("search").value = "";
  }
</script>
</body>
</html>
