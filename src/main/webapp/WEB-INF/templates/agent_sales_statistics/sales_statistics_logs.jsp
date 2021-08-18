<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page
    import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.TransactionCustomerType" %>

<%@ include file="../../templates/include_page/taglibs.jsp" %>
<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="menu.left.reconciliation"/></title>
  <jsp:include page="../../templates/include_page/head.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/transaction-logs.css'/>"
        media="none" onload="if(media!='all')media='all'">
  <jsp:include page="../../templates/include_page/js_merchant.jsp">
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
</head>

<body>
<section class="body">
  <!--        ///////   header ////////-->
  <jsp:include page="../../templates/include_page/header.jsp"/>
  <jsp:include page="../../templates/include_component/constant_application.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../../templates/include_page/navigation.jsp">
      <jsp:param value="agent-sales-statistics" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span class=""><spring:message code="balance.monitoring.table.wallet"/></span>
                </li>
                <li><span class="nav-active"><spring:message
                    code="label.agent.sales.statistics"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../../templates/include_page/message.jsp"/>

      <!-- start: page -->

      <spring:message code="transaction.api.search.placeholder" var="placeholder"/>
      <spring:message code="select.choose.all" var="langChooseAll" scope="request"/>
      <spring:message code="select.status" var="langStatus" scope="request"/>
      <spring:message code="select.service" var="langService" scope="request"/>
      <spring:message code="select.merchant" var="langMerchant" scope="request"/>
      <spring:message code="select.provider" var="langProvider" scope="request"/>

      <div class="content-body-wrap">
        <div class="container-fluid">

          <c:set var="IdOwnerCustomerType" value="<%= TransactionCustomerType.values() %>"/>

          <div class="tabs">
            <ul class="nav nav-tabs">
              <%--<li class="${param.idOwnerCustomerTypes == null || '' eq param.idOwnerCustomerTypes ? 'active' : ''}">--%>
              <%--<a onclick="openTabAll('');" href="#" data-toggle="tab">All</a>--%>
              <%--</li>--%>
              <%--<c:forEach var="customerTypeItem" items="${IdOwnerCustomerType}">--%>
              <%--<li class="${customerTypeItem.name() eq param.idOwnerCustomerTypes ? 'active' : ''}">--%>
              <%--<a onclick="openTab('${customerTypeItem}');" href="#">--%>
              <%--${customerTypeItem.getDisplayText()}--%>
              <%--</a>--%>
              <%--</li>--%>
              <%--</c:forEach>--%>
              <li class="active">
                <a onclick="openTab('AGENT');" href="#">
                  AGENT</a>
              </li>
            </ul>

            <div class="h4 mb-md">Tháng &nbsp;${date}</div>

            <div class="tab-content pl-none pr-none">
              <div id="tab1" class="tab-pane active">

                <jsp:include page="sales_statistics_content.jsp"/>

              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- end: page -->
    </section>
    <jsp:include page="../../templates/include_page/footer.jsp"/>
  </div>
</section>

<jsp:include page="../../templates/include_page/js_footer.jsp"/>
<jsp:include page="../../templates/include_page/date_picker.jsp">
  <jsp:param name="isFullTime" value="false"/>
  <jsp:param name="autoFilterDate" value="false"/>
  <jsp:param name="autoFillterYesterday" value="true"/>
</jsp:include>

<script type="text/javascript">

  var searchText = '<c:out value="${param.id }"/>';
  $('a.detail-link').click(function () {
    var txnId = $(this).attr("txnId");
    var searchURL = '';
    if (window.location.search.indexOf("?") >= 0) {
      searchURL = '${txnControlUri}/detail' + window.location.search + '&txnId=' + txnId;
    } else {
      searchURL = '${txnControlUri}/detail?' + 'txnId=' + txnId;
    }
    window.location.href = searchURL;
  });

  function openTab(paramValue) {
    var searchURL = '';
    $('#idOwnerCustomerTypes').val(paramValue);
    if (window.location.search.indexOf("?") >= 0) {
      if (window.location.search.indexOf("idOwnerCustomerTypes") >= 0) {
        searchURL = '${prefixSearchReconciliationdUrl}' + replaceUrlParam(window.location.search,
          'idOwnerCustomerTypes', paramValue);
      } else {
        searchURL = '${prefixSearchReconciliationdUrl}' + window.location.search
          + '&idOwnerCustomerTypes=' + paramValue;
      }

    } else {
      searchURL = '${prefixSearchReconciliationdUrl}' + '?idOwnerCustomerTypes=' + paramValue;
    }
    window.location.href = searchURL;
  }

  function openTabAll() {
    var searchURL = '';
    if (window.location.search.indexOf("?") >= 0 && window.location.search.indexOf(
      "idOwnerCustomerTypes") >= 0) {
      searchURL = '${contextPath}/wallet/balance-deduction-reconcilation-report/list-all'
        + replaceUrlParam(window.location.search, 'idOwnerCustomerTypes', '');
    } else {
      searchURL = '${contextPath}/wallet/balance-deduction-reconcilation-report/list-all';
    }
    window.location.href = searchURL;
  }

</script>
</body>
</html>
