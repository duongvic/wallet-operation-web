<%@ page import="vn.mog.ewallet.operation.web.constant.SharedConstants" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.transfer.FundinSofController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.transfer.WalletTransferController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="transfer.process.page.header"/></title>
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
      <jsp:param name="nav" value="${transferType}"/>
    </jsp:include>
    <!-- end: sidebar -->

    <c:choose>
      <c:when test="${transferType == 'FUND_TRANSFER'}">
        <spring:message code="menu.left.fundout.submenu.request.fundin.sof" var="labelSubmenuRequest"/>
        <c:set var="urlWalletDetail">${contextPath}<%=FundinSofController.FUND_SOF_REQUEST_LIST%></c:set>
        <c:set var="urlMovementHistoryDetail">${contextPath}/wallet/fundin-sof/history/detail</c:set>
        <c:set var="urlRequestTransfer">${contextPath}/wallet/fundin-sof/request-transfer</c:set>
        <c:set var="urlPrefixWalletProcess">${contextPath}/wallet/fundin-sof</c:set>
        <spring:message code="menu.left.fund.transfer" var="labelTransfer"/>
      </c:when>
      <c:otherwise>
        <spring:message code="menu.left.fundout.submenu.request.transfer.wallet" var="labelSubmenuRequest"/>
        <c:set var="urlWalletDetail">${contextPath}<%=WalletTransferController.TRANSFER_WALLET_REQUEST_LIST%></c:set>
        <c:set var="urlMovementHistoryDetail">${contextPath}/wallet/transfer-wallet/history/detail</c:set>
        <c:set var="urlRequestTransfer">${contextPath}/wallet/transfer-wallet/request-transfer</c:set>
        <c:set var="urlPrefixWalletProcess">${contextPath}/wallet/transfer-wallet</c:set>
        <spring:message code="menu.left.wallet.transfer" var="labelTransfer"/>
      </c:otherwise>
    </c:choose>



    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                <li><span>${labelTransfer}</span></li>
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

          <div class="form-inline">
            <div class="h4 mb-md mt-md pull-left">
              <spring:message code="menu.left.fundout.submenu.transfer"/>
            </div>
            <div class="fr form-responsive">
              <c:if test="${permisRequest}">
                <a href="${urlRequestTransfer}" class="btn btn-success mb-xs mt-xs">
                  <i class="fa fa-plus"></i>&nbsp;<spring:message code="transfer.wallet.process.btn.request.transfer"/>
                </a>
              </c:if>
            </div>
          </div>

          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none">

              <spring:message code="select.choose.all" var="langChooseAll"/>
              <spring:message code="select.status" var="langStatus"/>
              <spring:message code="select.service" var="langService"/>
              <spring:message code="select.merchant" var="langMerchant"/>
              <spring:message code="select.provider" var="langProvider"/>

              <spring:message code="transfer.wallet.process.placeholder" var="placeholder"/>


              <form action="" method="GET" id="tbl-filter" class="mb-md">

                <div class="form-group ml-none mr-none">
                  <div class="input-group input-group-icon">
                    <span class="input-group-addon">
                        <span class="icon" style="opacity: 0.4"><i class="fa fa-search-minus"></i></span>
                    </span>
                    <input type="text" id="search" name="search" class="form-control" placeholder="${placeholder}" value="${param.search}"/>
                  </div>
                </div>

                <div class="form-inline">
                  <jsp:include page="../include_component/date_range.jsp"/>

                  <div class='pull-right form-responsive bt-plt'>

                    <sec:authorize access="hasAnyRole('ADMIN_OPERATION','STAFF')" var="permitSearchStaff">
                      <jsp:include page="../include_component/search_source_merchant.jsp"/>
                      <jsp:include page="../include_component/search_target_merchant.jsp"/>
                    </sec:authorize>


                    <c:set var="allStatus" value=","/>
                    <c:forEach var="st" items="${paramValues.procces}">
                      <c:set var="allStatus" value="${allStatus}${st},"/>
                    </c:forEach>
                    <select class="form-control multiple-select hidden" name="procces" multiple="multiple" id="procces">
                      <c:forEach var="item" items="${walletTransferStages}">
                        <c:set var="status2" value=",${item.key},"/>
                        <option ${fn:contains(allStatus, status2)?'selected':'' } value="${item.key}"><spring:message code="${item.value}"/></option>
                      </c:forEach>
                    </select>

                    <button type="submit" class="btn btn-primary ml-tiny"><i class="fa fa-search"></i>&nbsp;<spring:message code="common.btn.search"/></button>
                    <a href="#" id="export_do" class="btn  btn-default export_link  bt-export-rsp"><i class="fa fa-download "></i>&nbsp;<spring:message code="transaction.log.export"/></a>
                  </div>
                </div>
                <div class="clearfix"></div>
              </form>

              <div>
                <spring:message code="transfer.wallet.process.trans"/>&nbsp;<span class="primary_color text-semibold">${ewallet:formatNumber(total)}</span> |
                <spring:message code="transfer.wallet.process.totalmoney"/>&nbsp;<span class="primary_color text-semibold">${ewallet:formatNumber(totalAmount)}</span><span>&nbsp;<spring:message code="transaction.api.table.currency"/></span>
              </div>
              <div class="clearfix"></div>

              <spring:message var="colNo" code="transfer.wallet.process.table.col.no"/>
              <spring:message var="colTime" code="transfer.wallet.process.table.col.time"/>
              <spring:message var="colCreatedby" code="transfer.wallet.process.table.col.createdby"/>
              <spring:message var="colPayer" code="transfer.wallet.process.table.col.payer"/>
              <spring:message var="colPayee" code="transfer.wallet.process.table.col.payee"/>
              <spring:message var="colAmount" code="transfer.wallet.process.table.col.amount"/>
              <spring:message var="coProcessing" code="transfer.wallet.process.table.col.processing"/>
              <spring:message var="colTxnId" code="transfer.wallet.process.table.col.txnId"/>
              <spring:message var="colStatus" code="transfer.wallet.process.table.col.status"/>
              <spring:message var="colApprove" code="transfer.wallet.process.table.col.approved"/>
              <spring:message var="colAction" code="transfer.wallet.process.table.col.action"/>

              <display:table name="walletTransferOrders" id="item"
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

                <display:column title="${colTime}" property="createdTime" format="{0,date,HH:mm:ss dd/MM/yyyy}"/>
                <display:column title="${colCreatedby}" property="creatorUsername"/>
                <display:column title="${colPayer}" property="payerUsername"/>
                <display:column title="${colPayee}" property="payeeUsername"/>
                <display:column title="${colAmount}" headerClass="col-number-header" class="col-number-header">${ewallet:formatNumber(item.amount)}</display:column>


                <display:column title="${coProcessing}" headerClass="center" class="status_icon center" style="min-width: 170px;">
                  <c:choose>
                    <c:when test="${item.stage eq financeRejected}">
                      <a title="<spring:message code="merchant.request"/>"><i class="fa fa-check check_status"></i></a>

                      <c:choose>
                        <c:when test="${permisRequest eq true}">
                          <a title="<spring:message code="sales.excutive.reject.request"/>"
                             href="${urlPrefixWalletProcess}/request-transfer?orderId=${item.id}"><i class="fa fa-times reject_status"></i></a>
                        </c:when>
                        <c:otherwise>
                          <a title="<spring:message code="sales.excutive.reject.request"/>" class="not-role"><i class="fa fa-times reject_status"></i></a>
                        </c:otherwise>
                      </c:choose>

                      <a class="status_number" title="">2</a>
                      <a class="status_number" title="">3</a>
                      <a class="status_number" title="">4</a>
                      <a class="status_number" title="">5</a>
                      <a class="status_number" title="">6</a>
                    </c:when>

                    <c:when test="${item.stage eq financeReadyToVerified}">
                      <a title="<spring:message code="merchant.request"/>"><i class="fa fa-check check_status"></i></a>
                      <a class="status_number" title="">1</a>
                      <c:choose>
                        <c:when test="${permisInitiate eq true}">
                          <a title="<spring:message code="sales.excutive.approve.request"/>"
                             href="${urlPrefixWalletProcess}/order-initiate?orderId=${item.id}"><i class="fa fa-warning warning_status"></i></a>
                        </c:when>
                        <c:otherwise>
                          <a title="<spring:message code="sales.excutive.approve.request"/>" class="not-role"><i class="fa fa-warning warning_status"></i></a>
                        </c:otherwise>
                      </c:choose>

                      <a class="status_number" title="">3</a>
                      <a class="status_number" title="">4</a>
                      <a class="status_number" title="">5</a>
                      <a class="status_number" title="">6</a>
                    </c:when>

                    <c:when test="${item.stage eq financeLeaderRejected}">
                      <a title="<spring:message code="merchant.request"/>"><i class="fa fa-check check_status"></i></a>
                      <a class="status_number" title="">1</a>
                      <a title="<spring:message code="sales.excutive.sales.approved"/>"><i class="fa fa-check check_status"></i></a>
                      <c:choose>
                        <c:when test="${permisInitiate eq true}">
                          <a title="<spring:message code="finance.staff.reject.order" />"
                             href="${urlPrefixWalletProcess}/order-initiate?orderId=${item.id}"><i class="fa fa-times reject_status"></i></a>
                        </c:when>
                        <c:otherwise>
                          <a title="<spring:message code="finance.staff.reject.order" />" class="not-role"><i class="fa fa-times reject_status"></i></a>
                        </c:otherwise>
                      </c:choose>

                      <a class="status_number" title="">4</a>
                      <a class="status_number" title="">5</a>
                      <a class="status_number" title="">6</a>
                    </c:when>

                    <c:when test="${item.stage eq financeLeaderReadyToReviewed}">
                      <a title="<spring:message code="merchant.request"/>"><i class="fa fa-check check_status"></i></a>
                      <a class="status_number" title="">1</a>
                      <a title="<spring:message code="sales.excutive.sales.approved"/>"><i class="fa fa-check check_status"></i></a>
                      <a class="status_number" title="">3</a>
                      <c:choose>
                        <c:when test="${permisReview eq true}">
                          <a title="<spring:message code="finance.staff.approve.order"/> "
                             href="${urlPrefixWalletProcess}/order-review?orderId=${item.id}"><i class="fa fa-warning warning_status"></i></a>
                        </c:when>
                        <c:otherwise>
                          <a title="<spring:message code="finance.staff.approve.order"/>" class="not-role"><i class="fa fa-warning warning_status"></i></a>
                        </c:otherwise>
                      </c:choose>
                      <a class="status_number" title="">5</a>
                      <a class="status_number" title="">6</a>
                    </c:when>

                    <c:when test="${item.stage eq financeManagerRejected}">
                      <a title="<spring:message code="merchant.request"/>"><i class="fa fa-check check_status"></i></a>
                      <a class="status_number" title="">1</a>
                      <a title="<spring:message code="sales.excutive.sales.approved"/>"><i class="fa fa-check check_status"></i></a>
                      <a class="status_number" title="">3</a>
                      <a title="<spring:message code="finance.staff.finance.approved"/> "><i class="fa fa-check check_status"></i></a>

                      <c:choose>
                        <c:when test="${permisReview eq true}">

                          <a title="<spring:message code="transfer.wallet.process.table.text1" />"
                             href="${urlPrefixWalletProcess}/order-review?orderId=${item.id}"><i class="fa fa-times reject_status"></i></a>
                        </c:when>
                        <c:otherwise>
                          <a title="<spring:message code="transfer.wallet.process.table.text1" />" class="not-role"><i class="fa fa-times reject_status"></i></a>
                        </c:otherwise>
                      </c:choose>

                      <a class="status_number" title="">6</a>
                    </c:when>

                    <c:when test="${item.stage eq financeManagerReadyToApproved}">
                      <a title="<spring:message code="merchant.request"/>"><i class="fa fa-check check_status"></i></a>
                      <a class="status_number" title="">1</a>
                      <a title="<spring:message code="sales.excutive.sales.approved"/>"><i class="fa fa-check check_status"></i></a>
                      <a class="status_number" title="">3</a>
                      <a title="<spring:message code="finance.staff.finance.approved"/>"><i class="fa fa-check check_status"></i></a>
                      <a class="status_number" title="">5</a>

                      <c:choose>
                        <c:when test="${item.refTxnStatus eq 3}">
                          <a title="<spring:message code="transfer.wallet.process.table.txn.faile" />" class="not-role"><i class="fa fa-warning warning_status"></i></a>
                        </c:when>
                        <c:otherwise>
                          <c:choose>
                            <c:when test="${((item.amount lt fundTransferMaxMoney) and (item.serviceType eq 'FUND_TRANSFER')) ||
                                        ((item.amount lt walletTransferMaxMoney) and (item.serviceType eq 'WALLET_TRANSFER'))}">
                              <c:choose>
                                <c:when test="${(permisReview eq true) or (permisApprove eq true)}">
                                  <a title="<spring:message code="finance.leader.approve.fundin"/>"
                                     href="${urlPrefixWalletProcess}/order-approve?orderId=${item.id}"><i class="fa fa-warning warning_status"></i></a>
                                </c:when>
                                <c:otherwise>
                                  <a title="<spring:message code="finance.leader.approve.fundin"/>" class="not-role"><i class="fa fa-warning warning_status"></i></a>
                                </c:otherwise>
                              </c:choose>
                            </c:when>
                            <c:when test="${((item.amount ge fundTransferMaxMoney) and (item.serviceType eq 'FUND_TRANSFER')) ||
                                        ((item.amount ge walletTransferMaxMoney) and (item.serviceType eq 'WALLET_TRANSFER'))}">
                              <c:choose>
                                <c:when test="${permisApprove eq true}">
                                  <a title="<spring:message code="finance.leader.approve.fundin"/>"
                                     href="${urlPrefixWalletProcess}/order-approve?orderId=${item.id}"><i class="fa fa-warning warning_status"></i></a>
                                </c:when>
                                <c:otherwise>
                                  <a title="<spring:message code="finance.leader.approve.fundin"/>" class="not-role"><i class="fa fa-warning warning_status"></i></a>
                                </c:otherwise>
                              </c:choose>
                            </c:when>
                            <c:otherwise>
                              <a title="<spring:message code="finance.leader.approve.fundin"/>" class="not-role"><i class="fa fa-warning warning_status"></i></a>
                            </c:otherwise>
                          </c:choose>
                        </c:otherwise>
                      </c:choose>
                    </c:when>

                    <c:when test="${item.stage eq walletTransferFinished}">
                      <a title="<spring:message code="merchant.request"/>"><i class="fa fa-check check_status"></i></a>
                      <a class="status_number" title="">1</a>
                      <a title="<spring:message code="sales.excutive.sales.approved"/>"><i class="fa fa-check check_status"></i></a>
                      <a class="status_number" title="">3</a>
                      <a title="<spring:message code="finance.staff.finance.approved"/>"><i class="fa fa-check check_status"></i></a>
                      <a class="status_number" title="">5</a>
                      <a title="<spring:message code="finance.leader.approved.fundin" />"><i class="fa fa-check check_status"></i></a>
                    </c:when>

                    <%--walletInit--%>
                    <c:otherwise>
                      <c:choose>
                        <c:when test="${permisRequest eq true}">
                          <a title="<spring:message code="merchant.request"/>"
                             href="${urlPrefixWalletProcess}/request-transfer?orderId=${item.id}"><i class="fa fa-warning warning_status"></i></a>
                        </c:when>
                        <c:otherwise>
                          <a title="<spring:message code="merchant.request"/>" class="not-role"><i class="fa fa-warning warning_status"></i></a>
                        </c:otherwise>
                      </c:choose>

                      <a class="status_number" title="">1</a>
                      <a class="status_number" title="">2</a>
                      <a class="status_number" title="">3</a>
                      <a class="status_number" title="">4</a>
                      <a class="status_number" title="">5</a>
                      <a class="status_number" title="">6</a>
                    </c:otherwise>
                  </c:choose>
                </display:column>

                <display:column title="${colTxnId}" class="col-number-header" headerClass="col-number-header">
                  <c:if test="${item.refTxnId != null }">
                    <a class="link-active" href="${urlMovementHistoryDetail}?txnId=${item.refTxnId}">${item.refTxnId}</a>
                  </c:if>
                </display:column>

                <display:column title="${colStatus}"><spring:message code="${ewallet:getTxnStatusName(item.refTxnStatus)}"/></display:column>
                <display:column title="${colApprove}" property="approverUsername"/>
                <display:column title="${colAction}" class="action_icon right" headerClass="action_icon right">
                  <c:choose>

                    <c:when test="${permisRequest eq true and (item.stage eq walletTransferInit or item.stage eq financeRejected)}">
                      <a href="${urlPrefixWalletProcess}/request-transfer?orderId=${item.id}"
                         class="detail-link link-active" title="<spring:message code="transfer.wallet.process.table.label.edit"/>" txnId="${item.id}">
                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                      </a>
                    </c:when>
                    <c:when test="${permisInitiate eq true and (item.stage eq financeReadyToVerified or item.stage eq financeLeaderRejected)}">
                      <a href="${urlPrefixWalletProcess}/order-initiate?orderId=${item.id}"
                         class="detail-link link-active"
                         title="<spring:message code="transfer.wallet.process.table.label.edit"/>" txnId="${item.id}">
                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                      </a>
                    </c:when>
                    <c:otherwise></c:otherwise>
                  </c:choose>


                  <a href="${urlWalletDetail}/${item.id}"
                     class="detail-link link-active"
                     title="<spring:message code="transfer.wallet.process.table.label.viewdetail"/>"><i class="fa fa-info-circle "></i></a>
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
<jsp:include page="../include_page/date_picker.jsp">
  <jsp:param name="isFullTime" value="true"/>
  <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>
<jsp:include page="../include_component/search_form_commons.jsp">
  <jsp:param name="selSourceMerchant" value="${permitSearchStaff}"/>
  <jsp:param name="selTargetMerchant" value="${permitSearchStaff}"/>
</jsp:include>

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
