<%@ page import="static vn.mog.ewallet.operation.web.controller.translog.TransactionReimController.TRANS_REIM_DETAIL" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="common.transaction.cancel"/></title>
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
      <jsp:param value="reimTxn" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="reim.title.wallet.reim"/></span></li>
                <li><span class="nav-active"><spring:message code="reim.title.request.transfer"/> </span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <!-- start: page -->
      <%--<c:url var="urlTransReimRequest" value="<%=TransReimController.TRANS_REIM_CONTROLLER%>"/>--%>
      <div class="content-body-wrap">
        <div class="container-fluid">

          <div class="form-inline">
            <%--<div class="pull-left h4 mb-md mt-md"><spring:message code="reversal.title.reversal.transaction"/></div>--%>
            <div class="pull-left h4 mb-md mt-md"></div>
            <div class="fr form-responsive">
              <%--<sec:authorize access="hasRole('ADMIN_OPERATION')">--%>
              <%--&lt;%&ndash;<button class="btn btn-success mb-xs mt-xs" onclick="location.href = '${urlTransReimRequest}/request';">&ndash;%&gt;--%>
              <%--&lt;%&ndash;<i class="fa fa-plus"></i>&nbsp;<spring:message code="reversal.btn.request.reversal"/>&ndash;%&gt;--%>
              <%--&lt;%&ndash;</button>&ndash;%&gt;--%>
              <%--</sec:authorize>--%>
            </div>
          </div>

          <div class="clearfix"></div>
          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none">

              <spring:message code="transaction.api.search.placeholder" var="placeholder"/>
              <spring:message code="select.choose.all" var="langChooseAll"/>
              <spring:message code="select.status" var="langStatus"/>
              <spring:message code="select.service" var="langService"/>
              <spring:message code="select.merchant" var="langMerchant"/>
              <spring:message code="select.provider" var="langProvider"/>

              <form action="" method="GET" id="tbl-filter" class="mb-md">

                <div class="form-group ml-none mr-none">
                  <div class="input-group input-group-icon">
                    <span class="input-group-addon"><span class="icon" style="opacity: 0.4"><i class="fa fa-search-minus"></i></span> </span>
                    <input type="text" id="quickSearch" name="quickSearch" class="form-control" placeholder="${placeholder}" value="${param.quickSearch }"/>
                  </div>
                </div>

                <div class="form-inline">

                  <jsp:include page="../include_component/date_range.jsp"/>

                  <div class='pull-right form-responsive'>
                    <%--payer--%>
                    <jsp:include page="../include_component/search_source_merchant.jsp"/>
                    <%--payee--%>
                    <span>
                      <jsp:include page="../include_component/search_target_merchant.jsp"/>
                    </span>

                    <c:set var="allStatus" value=","/>
                    <c:forEach var="st" items="${paramValues.status}">
                      <c:set var="allStatus" value="${allStatus}${st},"/>
                    </c:forEach>
                    <select class="form-control" name="status" multiple="multiple" id="status">
                      <c:forEach var="item" items="${statuses}">
                        <c:set var="status2" value=",${item.key},"/>
                        <option ${fn:contains(allStatus, status2)?'selected':'' } value="${item.key}"><spring:message code="${item.value}"/></option>
                      </c:forEach>
                    </select>
                    <script type="text/javascript">
                      $('#status').multiselect({
                        includeSelectAllOption: true,
                        selectAllValue: '',
                        selectAllText: '${langChooseAll}',
                        maxHeight: 400,
                        dropUp: true,
                        nonSelectedText: '${langStatus}',
                        inheritClass: true,
                        numberDisplayed: 1
                      });
                    </script>


                    <button type="submit" class="btn btn-primary ml-tiny"><i class="fa fa-search"></i>&nbsp; <spring:message code="common.btn.search"/></button>
                    <a href="#" id="export_do" class="btn  btn-default mt-sm export_link bt-export-rsp"><i class="fa fa-download "></i>&nbsp;<spring:message code="transaction.log.export"/></a>
                  </div>
                </div>
                <div class="clearfix"></div>
              </form>

              <spring:message code="transaction.api.table.currency" var="tCurrencyType"/>
              <div>
                <spring:message code="reim.label.transaction"/> &nbsp;<span class="primary_color text-semibold">${ewallet:formatNumber(transactionAmount)}</span>&nbsp;|&nbsp;
                <spring:message code="reim.label.transaction.amount"/> &nbsp;<span class="primary_color text-semibold">${ewallet:formatNumber(cashInAmount)}</span>&nbsp;${tCurrencyType}
              </div>

              <div class="clearfix"></div>

              <spring:message var="colStt" code="reim.table.no"/>
              <spring:message var="colTransID" code="reim.table.transaction.id"/>
              <spring:message var="colTime" code="reim.table.time"/>
              <spring:message var="colSourceAccount" code="reim.table.source.account"/>
              <spring:message var="colTargetAccount" code="reim.table.target.account"/>
              <spring:message var="colTransferAmount" code="reim.table.transfer.amount"/>
              <spring:message var="colProccessing" code="reim.table.proccessing"/>

              <spring:message var="colTransReference" code="reim.table.transaction.reference"/>
              <spring:message var="colCreateAt" code="reim.table.created.at"/>
              <spring:message var="colCreateBy" code="reim.table.created.by"/>
              <spring:message var="colApproedBy" code="reim.table.approed.by"/>
              <spring:message var="colStatus" code="reim.table.status"/>
              <spring:message var="colAction" code="transaction.api.table.action"/>

              <display:table name="transCancels" id="item"
                             requestURI="list"
                             pagesize="${pagesize}" partialList="true"
                             size="total"
                             sort="page"
                             class="table table-bordered table-striped mb-none">

                <%@ include file="../include_component/display_table.jsp" %>

                <display:column title="${colStt}">
                    <span id="row${list.id}" class="rowid">
                        <c:out value="${offset + item_rowNum}"/>
                    </span>
                </display:column>

                <display:column title="${colTransID}">
                  <a class="app-name detail-link link-active" href="#" txnId="${item.id}">
                      ${fn:substring(item.id, 0, 16)} <br/>
                      ${fn:substring(item.id, 16, 32)}
                  </a>
                </display:column>

                <display:column title="${colSourceAccount}" property="service"/>
                <display:column title="${colTargetAccount}" property="merchant"/>
                <display:column title="${colTime}" property="createdDate" format="{0,date,HH:mm:ss dd/MM/yyyy}"/>
                <display:column title="${colTransferAmount}">${ewallet:formatNumber(amount)}</display:column>

                <display:column title="${colCreateAt}" property="createdDate" format="{0,date,HH:mm:ss dd/MM/yyyy}"/>
                <display:column title="${colCreateBy}" property="createdDate" format="{0,date,HH:mm:ss dd/MM/yyyy}"/>
                <display:column title="${colApproedBy}">${ewallet:formatNumber(approver)}</display:column>
                <display:column title="${colStatus}">${item.getStatusCode(item.status)}</display:column>


                <display:column title="${colAction}" class="action_icon center" headerClass="center">
                  <a href="#" class="detail-link link-active" title="<spring:message code="common.title.view.detail"/>" txnId="${item.id}">
                    <i class="fa fa-info-circle "></i>
                  </a>
                </display:column>
              </display:table>
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
<jsp:include page="../include_component/search_form_commons.jsp">
  <jsp:param name="selSourceMerchant" value="true"/>
  <jsp:param name="selTargetMerchant" value="true"/>
  <jsp:param name="selServiceType" value="true"/>
</jsp:include>
<jsp:include page="../include_page/date_picker.jsp">
  <jsp:param name="isFullTime" value="true"/>
  <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>


<script type="text/javascript">
  $(document).ready(function () {
    $('a.detail-link').click(function () {
      var txnId = $(this).attr("txnId");
      var searchURL = '';
      if (window.location.search.indexOf("?") >= 0) {
        searchURL = ctx + '<%=TRANS_REIM_DETAIL%>' + window.location.search + '&txnId=' + txnId;
      } else {
        searchURL = ctx + '<%=TRANS_REIM_DETAIL%>?' + 'txnId=' + txnId;
      }
      window.location.href = searchURL;
    });
  });
</script>
</body>
</html>