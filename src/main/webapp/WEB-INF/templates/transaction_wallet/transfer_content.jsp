<%@ page import="vn.mog.ewallet.operation.web.constant.SharedConstants" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.transfer.FundinSofController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.transfer.WalletTransferController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>


<c:choose>
  <c:when test="${transferType == 'FUND_TRANSFER'}">
    <spring:message code="menu.left.fundout.submenu.request.fundin.sof" var="labelSubmenuRequest"/>
    <c:set var="urlWalletDetail">${contextPath}<%=FundinSofController.FUND_SOF_REQUEST_LIST%></c:set>
    <c:set var="urlMovementHistoryDetail">${contextPath}/wallet/fundin-sof/history/detail</c:set>
    <c:set var="urlRequestTransfer">${contextPath}/wallet/fundin-sof/request-transfer</c:set>
    <c:set var="urlPrefixWalletProcess">${contextPath}/wallet/fundin-sof</c:set>
    <c:set var="titlePage">Fund Transfer</c:set>
  </c:when>
  <c:when test="${transferType == 'WALLET_TRANSFER'}">
    <spring:message code="menu.left.fundout.submenu.request.transfer.wallet" var="labelSubmenuRequest"/>
    <c:set var="urlWalletDetail">${contextPath}<%=WalletTransferController.TRANSFER_WALLET_REQUEST_LIST%></c:set>
    <c:set var="urlMovementHistoryDetail">${contextPath}/wallet/transfer-wallet/history/detail</c:set>
    <c:set var="urlRequestTransfer">${contextPath}/wallet/transfer-wallet/request-transfer</c:set>
    <c:set var="urlPrefixWalletProcess">${contextPath}/wallet/transfer-wallet</c:set>
    <c:set var="titlePage">Internal Wallet Transfer</c:set>
  </c:when>
  <c:otherwise>
    <spring:message code="menu.left.fundout.submenu.request.transfer.p2p" var="labelSubmenuRequest"/>
    <c:set var="urlWalletDetail">${contextPath}<%=WalletTransferController.TRANSFER_WALLET_REQUEST_LIST%></c:set>
    <c:set var="urlMovementHistoryDetail">${contextPath}/wallet/transfer-wallet/history/detail</c:set>
    <c:set var="urlRequestTransfer">${contextPath}/wallet/transfer-wallet/request-transfer</c:set>
    <c:set var="urlPrefixWalletProcess">${contextPath}/wallet/transfer-wallet</c:set>
    <c:set var="titlePage">P2P Transfer</c:set>
  </c:otherwise>
</c:choose>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title>${titlePage}</title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/transfer.css'/>" media="none" onload="if(media!='all')media='all'">
  <jsp:include page="../include_page/js_merchant.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>

  <style>
    .btn-group {
      margin-left: 2px;
    }
  </style>
</head>


<body>
<section class="body">
  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>
  <jsp:include page="../include_component/constant_application.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param name="nav" value="wallTrans"/>
    </jsp:include>
    <!-- end: sidebar -->


    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                <li><span>Zo-Vip Transaction</span></li>
                <li><span class="nav-active">${labelSubmenuRequest}</span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">

          <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT')" var="permisRequest"/>
          <sec:authorize access="hasAnyRole('ADMIN_OPERATION','FINANCE')" var="permisInitiate"/>
          <sec:authorize access="hasAnyRole('ADMIN_OPERATION','FINANCE_LEADER')" var="permisReview"/>
          <sec:authorize access="hasAnyRole('ADMIN_OPERATION','FA_MANAGER')" var="permisApprove"/>
          <c:set var="fundTransferMaxMoney" value="<%=SharedConstants.TRANSFER_FUND_MAX_MONEY%>"/>
          <c:set var="walletTransferMaxMoney" value="<%=SharedConstants.TRANSFER_WALLET_MAX_MONEY%>"/>

          <div class="h4 mb-md">
            <spring:message code="menu.left.fundout.submenu.transfer"/>
          </div>

          <div class="tabs">
            <ul class="nav nav-tabs">
              <li class=""><a onclick="openTab('all');" href="#" data-toggle="tab">All</a></li>
              <li class=""><a onclick="openTab('fund_in');" href="#">Fund In</a></li>
              <li class=""><a onclick="openTab('fund_out');" href="#">Fund Out</a></li>
              <li class="${transferType == 'P2P_TRANSFER' ? 'active' : ''}"><a onclick="openTab('p2p_wallet');" href="#">P2P Transfer</a></li>
              <li class="${transferType == 'WALLET_TRANSFER' ? 'active' : ''}"><a onclick="openTab('internal_wallet');" href="#">Internal Wallet Transfer</a></li>
              <li class="${transferType == 'FUND_TRANSFER' ? 'active' : ''}"><a onclick="openTab('sof_transfer');" href="#">SOF Transfer</a></li>
            </ul>

            <div class="tab-content pl-none pr-none">
              <div id="tab1" class="tab-pane active">
                <section class="panel search_payment panel-default">
                  <div class="panel-body pt-none">
                    <form action="" method="GET" id="tbl-filter" class="mb-md">

                      <div class="form-group ml-none mr-none">
                        <div class="input-group input-group-icon">
                          <span class="input-group-addon"> <span class="icon" style="opacity: 0.4"><i class="fa fa-search-minus"></i></span> </span>
                          <input type="text" id="search" name="search" class="form-control" placeholder="<spring:message code="transaction.title.transaction.id"/>" value="${param.search }"/>
                        </div>
                      </div>

                      <div class="form-inline">

                        <jsp:include page="../include_component/date_range.jsp"/>

                        <div class='pull-right form-responsive bt-plt'>

                          <sec:authorize access="hasAnyRole('ADMIN_OPERATION','STAFF')" var="permitSearchStaff">
                            <jsp:include page="../include_component/search_source_merchant.jsp"/>
                            <jsp:include page="../include_component/search_target_merchant.jsp"/>
                          </sec:authorize>

                          <jsp:include page="../include_component/search_transaction_status.jsp"/>


                          <button type="submit" class="btn btn-primary ml-tiny"><i class="fa fa-search"></i>&nbsp; <spring:message code="common.btn.search"/></button>
                          <a href="#" id="export_do" class="btn  btn-default mt-sm export_link bt-export-rsp"><i class="fa fa-download "></i>&nbsp;<spring:message code="transaction.log.export"/></a>
                        </div>
                      </div>
                      <div class="clearfix"></div>
                    </form>


                    <spring:message var="colCurrency" code="transaction.api.table.currency"/>
                    <div><spring:message code="movement.history.total.amount" />&nbsp;<span class="primary_color text-semibold">${amountTransaction}&nbsp;${colCurrency}</span>
                    </div>

                    <div class="clearfix"></div>

                    <spring:message var="colNo" code="movement.history.table.col.no" />
                    <spring:message var="colTxnId" code="movement.history.table.col.txnId" />
                    <spring:message var="colsrcAccount" code="movement.history.table.col.srcAccount" />
                    <spring:message var="colTagAcc" code="movement.history.table.col.tagAccount" />
                    <spring:message var="colpreBalan" code="movement.history.table.col.preBalan" />
                    <spring:message var="coltranAcc" code="movement.history.table.col.tranAcc" />
                    <spring:message var="colpostBa" code="movement.history.table.col.postBa" />
                    <spring:message var="colcreateDae" code="movement.history.table.col.createDae" />
                    <spring:message var="colStatus" code="movement.history.table.col.status" />
                    <spring:message var="colAction" code="movement.history.table.col.action" />
                    <spring:message var="actDetail" code="movement.history.table.col.action.detail"/>

                    <display:table name="transactions" id="item"
                                   requestURI="list"
                                   pagesize="${pagesize}" partialList="true"
                                   size="total"
                                   sort="page"
                                   class="table table-bordered table-striped mb-none">

                      <%@ include file="../include_component/display_table.jsp" %>

                      <display:column title="${colNo}">
                  <span id="row${item.id}" class="rowid">
                      <c:out value="${offset + item_rowNum}"/>
                  </span>
                      </display:column>

                      <display:column title="${colTxnId}" >
                        <a class="app-name detail-link link-active" href="#" txnId="${item.id}">${item.id}</a>
                      </display:column>
                      <display:column title="${colsrcAccount}" property="payerUsername"/>
                      <display:column title="${colTagAcc}" property="payeeUsername"/>
                      <display:column title="${colpreBalan}" class="col-number-header" headerClass="col-number-header">${ewallet:formatNumber(item.preBalance)}</display:column>
                      <display:column title="${coltranAcc}" class="col-number-header" headerClass="col-number-header">${ewallet:formatNumber(item.realAmount)}</display:column>
                      <display:column title="${colpostBa}" class="col-number-header" headerClass="col-number-header">${ewallet:formatNumber(item.postBalance)}</display:column>
                      <display:column title="${colcreateDae}" property="creationDate" format="{0,date,HH:mm:ss dd/MM/yyyy}"/>
                      <display:column title="${colStatus}"><spring:message code="${item.status }"/> </display:column>
                      <display:column title="${colAction}" class="action_icon center" headerClass="center">
                        <a href="#" class="detail-link link-active" title="${actDetail}" txnId="${item.id}">
                          <i class="fa fa-info-circle "></i>
                        </a>
                      </display:column>
                    </display:table>

                  </div>
                </section>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- end: page -->
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_page/date_picker.jsp">
  <jsp:param name="isFullTime" value="false"/>
  <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>
<jsp:include page="../include_component/search_form_commons.jsp">
  <jsp:param name="selSourceMerchant" value="${permitSearchStaff}"/>
  <jsp:param name="selTargetMerchant" value="${permitSearchStaff}"/>
</jsp:include>
<jsp:include page="js_transaction_wallet.jsp"/>

<script type="text/javascript">
  $('#procces').multiselect({
    //enableFiltering: true,
    includeSelectAllOption: true,
    selectAllValue: '',
    selectAllText: '${langChooseAll}',
    maxHeight: 400,
    dropUp: true,
    nonSelectedText: '<spring:message code="common.process.status"/>',
    inheritClass: true,
    numberDisplayed: 1
  });

  $(document).ready(function () {
    $('a.detail-link').click(function () {
      var txnId = $(this).attr("txnId");
      var searchURL = '';
      if (window.location.search.indexOf("?") >= 0) {
        searchURL = '${urlMovementHistoryDetail}' + window.location.search + '&txnId=' + txnId;
      } else {
        searchURL = '${urlMovementHistoryDetail}' + '?txnId=' + txnId;
      }
      window.location.href = searchURL;
    });
  });
</script>

</body>
</html>
