<%@ page import="vn.mog.ewallet.operation.web.controller.translog.TransactionReversalController" %>
<%@ page
        import="static vn.mog.ewallet.operation.web.controller.translog.TransactionReversalController.TRANS_REVERSAL_DETAIL" %>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TransactionReversalOrderFlowStage" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.translog.TransactionLogController.TRANSACTION_DETAIL" %>

<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
            <jsp:param value="reversalTxnRequest" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->

        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                                <li><span><spring:message
                                        code="transaction.api.navigate.transaction"/></span></li>
                                <li><span class="nav-active"><spring:message
                                        code="reversal.title.reversal.transaction"/> </span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <!-- start: page -->
            <c:url var="urlTransReversalRequest"
                   value="<%=TransactionReversalController.TRANS_REVERSAL_CONTROLLER%>"/>
            <%--<input type="hidden" name="d-49489-p" value="${numberPage}">--%>

            <div class="content-body-wrap">
                <div class="container-fluid">

                    <div class="form-inline">
                        <div class="pull-left h4 mb-md mt-md">
                            <spring:message code="reversal.title.reversal.transaction"/>
                        </div>

                    </div>

                    <div class="clearfix"></div>
                    <section class="panel search_payment panel-default">
                        <div class="panel-body pt-none">

                            <spring:message code="transaction.api.search.placeholder"
                                            var="placeholder"/>
                            <spring:message code="select.choose.all" var="langChooseAll"/>
                            <spring:message code="select.status" var="langStatus"/>
                            <spring:message code="select.service" var="langService"/>
                            <spring:message code="select.merchant" var="langMerchant"/>
                            <spring:message code="select.provider" var="langProvider"/>

                            <form action="" method="GET" id="tbl-filter" class="mb-md">

                                <div class="form-group ml-none mr-none">
                                    <div class="input-group input-group-icon">
                                        <span class="input-group-addon"><span class="icon"
                                                                              style="opacity: 0.4"><i
                                                class="fa fa-search-minus"></i></span> </span>
                                        <input type="text" id="quickSearch" name="quickSearch"
                                               class="form-control" placeholder="${placeholder}"
                                               value="${param.quickSearch }"/>
                                    </div>
                                </div>

                                <div class="form-inline">

                                    <jsp:include page="../include_component/date_range.jsp"/>

                                    <div class='pull-right form-responsive'>

                                        <jsp:include
                                                page="../include_component/search_service_type_multiple.jsp"/>


                                        <c:set var="allStatus" value=","/>
                                        <c:forEach var="st" items="${paramValues.states}">
                                            <c:set var="allStatus" value="${allStatus}${st},"/>
                                        </c:forEach>
                                        <select class="form-control" name="states"
                                                multiple="multiple" id="states">
                                            <c:forEach var="item" items="${lstCbStates}">
                                                <c:set var="status2" value=",${item.key},"/>
                                                <option ${fn:contains(allStatus, status2)?'selected':'' }
                                                        value="${item.key}"><spring:message
                                                        code="${item.value}"/></option>
                                            </c:forEach>
                                        </select>
                                        <script type="text/javascript">
                                          $('#states').multiselect({
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

                                        <button type="submit" class="btn btn-primary ml-tiny"><i
                                                class="fa fa-search"></i>&nbsp;<spring:message
                                                code="common.btn.search"/></button>
                                        <a href="#" id="export_do"
                                           class="btn  btn-default mt-sm export_link"
                                           style="margin-top: 0px !important;"><i
                                                class="fa fa-download "></i>&nbsp;<spring:message
                                                code="transaction.log.export"/></a>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>
                            </form>

                            <spring:message code="transaction.api.table.currency"
                                            var="tCurrencyType"/>
                            <div>
                                <spring:message code="reversal.label.transaction.amount"/>
                                &nbsp;<span class="primary_color text-semibold"
                                            name="totalRequestAmount">${ewallet:formatNumber(totalRequestAmount)}</span>&nbsp;|&nbsp;
                                <spring:message code="reversal.label.cashIn.amount"/> &nbsp;<span
                                    class="primary_color text-semibold"
                                    name="totalNetAmount">${ewallet:formatNumber(totalNetAmount)}</span>&nbsp;${tCurrencyType}
                            </div>


                            <spring:message var="colStt" code="transaction.api.table.stt"/>
                            <spring:message var="colTime" code="transaction.api.table.time"/>
                            <spring:message var="colTransReversalID" code="transaction.api.table.transaction-id"/>
                            <spring:message var="colTransRefID" code="reim.table.transaction.reference"/>

                            <spring:message var="colAction" code="transaction.api.table.action"/>
                            <spring:message var="colServiceType"
                                            code="transaction.api.table.service.type"/>
                            <spring:message var="colAmount" code="transaction.api.table.amount"/>
                            <spring:message var="colRealAmount"
                                            code="transaction.api.table.realamount"/>
                            <spring:message var="colCommission"
                                            code="transaction.api.table.commission"/>
                            <spring:message var="colFee" code="transaction.api.table.fee"/>
                            <spring:message var="colBalBf" code="transaction.api.table.balance-bf"/>
                            <spring:message var="colBalAf" code="transaction.api.table.balance-af"/>
                            <spring:message var="colStatus" code="transaction.api.table.status"/>
                            <spring:message var="colStage"
                                            code="service.exportcard.list.table.column.stage"/>
                            <spring:message var="colApprover"
                                            code="reversal.table.approver"/>


                            <c:set var="epinStageInit"
                                   value="<%=TransactionReversalOrderFlowStage.INIT.code%>"/>
                            <c:set var="epinStageSaleRejected"
                                   value="<%=TransactionReversalOrderFlowStage.FINANCE_REJECTED.code%>"/>
                            <c:set var="epinStageSaleVerify"
                                   value="<%=TransactionReversalOrderFlowStage.FINANCE_READY_TO_VERIFY.code%>"/>
                            <c:set var="epinStageFinished"
                                   value="<%=TransactionReversalOrderFlowStage.FINISHED.code%>"/>


                            <sec:authorize
                                    access="hasAnyRole('ADMIN_OPERATION', 'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')"
                                    var="perFinanceVerify"/>


                            <spring:message var="titleStageInit"
                                            code="reversal.flow.stage.declared"/>
                            <spring:message var="titleStageSaleRejected"
                                            code="reversal.flow.stage.finance.rejected"/>
                            <spring:message var="titleStageSaleVerify"
                                            code="reversal.flow.stage.finance.verify"/>

                            <spring:message var="titleStageExportAllowed"
                                            code="service.exportcard.list.tbl.col.stage.export.allowed"/>
                            <spring:message var="titleStageFinish"
                                            code="reversal.flow.stage.finished"/>


                            <div class="clearfix"></div>

                            <display:table name="transCancels" id="item"
                                           requestURI="list-reversal-TxnRequest"
                                           pagesize="${pagesize}" partialList="true"
                                           size="total"
                                           sort="page"
                                           class="table table-bordered table-striped mb-none">

                                <%@ include file="../include_component/display_table.jsp" %>

                                <display:column title="${colStt}">
                                    <span id="row${item.id}" class="rowid">
                                        <c:out value="${offset + item_rowNum}"/>
                                    </span>
                                </display:column>

                                <display:column title="${colTime}">
                                    <fmt:formatDate value="${item.creationDate}" pattern="dd/MM/yyyy HH:mm:ss"/>
                                </display:column>

                                <display:column title="${colTransReversalID}">
                                    <a class="app-name detail-link link-active"
                                       href="${contextPath}<%=TRANSACTION_DETAIL%>?txnId=${item.refReversalTransactionId}"
                                       txnId="${item.refReversalTransactionId}">
                                            ${item.refReversalTransactionId}
                                    </a>
                                </display:column>

                                <display:column title="${colTransRefID}">
                                    <a class="app-name detail-link link-active"
                                       href="${contextPath}<%=TRANSACTION_DETAIL%>?txnId=${item.transaction.id}"
                                       txnId="${item.transaction.id}">
                                            ${item.transaction.id}
                                    </a>
                                </display:column>

                                <display:column title="${colServiceType}">
                                    ${item.transaction.serviceType}
                                </display:column>

                                <display:column title="REQUESTER">
                                    ${item.requestor}
                                </display:column>
                                <display:column
                                        title="${colAmount}">${ewallet:formatNumber(item.transaction.amount)}</display:column>
                                <display:column
                                        title="${colCommission}">${ewallet:formatNumber(item.transaction.commision)}</display:column>
                                <display:column
                                        title="${colFee}">${ewallet:formatNumber(item.transaction.fee)}</display:column>
                                <display:column
                                        title="${colRealAmount}">${ewallet:formatNumber(item.transaction.realAmount)}</display:column>
                                <display:column
                                        title="PRE-BALANCE">${ewallet:formatNumber(item.transaction.preBalance)}</display:column>
                                <display:column
                                        title="POST-BALANCE">${ewallet:formatNumber(item.transaction.postBalance)}</display:column>
                                <display:column title="${colStatus}">
                                    <spring:message code="reversal.status.${item.refReversalTransactionStatus}"/>
                                    </display:column>
                                <display:column title="APPROVER">${item.approver}</display:column>

                                <display:column title="${colStage}" class="center"
                                                headerClass="center" style="min-width: 91px;">
                                    <c:choose>
                                        <c:when test="${item.stage == epinStageInit}">
                                            <c:choose>
                                                <c:when test="${perExportEpin}">
                                                    <a href="edit-po?id=${item.id}"
                                                       title="${titleStageInit}"><i
                                                            class="fa fa-warning warning_status"></i></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a title="${titleStageInit}" class="not-role"><i
                                                            class="fa fa-warning warning_status"></i></a>
                                                </c:otherwise>
                                            </c:choose>
                                            <a title="${titleStageSaleRejected}"
                                               class="status_number">1</a>
                                            <a title="${titleStageSaleVerify}"
                                               class="status_number">2</a>

                                            <%--<a title="${titleStageFinish}"--%>
                                               <%--class="status_number">4</a>--%>
                                            <%--<a title="${titleStageFinish}" class="status_number">6</a>--%>
                                        </c:when>

                                        <c:when test="${item.stage == epinStageSaleRejected}">
                                            <a title="${titleStageInit}"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <c:choose><c:when test="${perExportEpin}">
                                                <a href="edit-po?id=${item.id}"
                                                   title="${titleStageSaleRejected}"><i
                                                        class="fa fa-times reject_status"></i></a>
                                            </c:when><c:otherwise>
                                                <a title="${titleStageSaleRejected}"
                                                   class="not-role"><i
                                                        class="fa fa-times reject_status"></i></a>
                                            </c:otherwise></c:choose>
                                            <a title="${titleStageSaleVerify}"
                                               class="status_number">2</a>

                                            <%--<a title="${titleStageFinish}"--%>
                                               <%--class="status_number">4</a>--%>
                                            <%--<a title="${titleStageFinish}" class="status_number">6</a--%>
                                        </c:when>

                                        <c:when test="${item.stage == epinStageSaleVerify}">
                                            <a title="${titleStageInit}"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a title="${titleStageSaleRejected}"
                                               class="status_number">1</a>
                                            <c:choose>
                                                <c:when test="${perFinanceVerify}">
                                                    <spring:url var="ApproveReversalURI"
                                                                value="${ctx}/service/reversal-history/request/approve?txnReversalId=${item.id}"/>

                                                    <a title="${titleStageSaleVerify}"
                                                       href="${ApproveReversalURI}"><i
                                                            class="fa fa-warning warning_status"></i></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a title="${titleStageSaleVerify}"
                                                       class="not-role"><i
                                                            class="fa fa-warning warning_status"></i></a>
                                                </c:otherwise>
                                            </c:choose>

                                            <%--<a title="${titleStageFinish}"--%>
                                               <%--class="status_number">4</a>--%>
                                            <%--<a title="${titleStageFinish}" class="status_number">6</a>--%>
                                        </c:when>


                                        <c:when test="${item.stage == epinStageFinished}">
                                            <a title="${titleStageInit}"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a title="${titleStageSaleRejected}"
                                               class="status_number">1</a>
                                            <a title="${titleStageFinish}"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <%--<a title="${titleStageFinish}"><i--%>
                                                    <%--class="fa fa-check check_status"></i></a>--%>
                                            <%--<a title="${titleStageFinish}"><i class="fa fa-check check_status"></i></a>--%>
                                        </c:when>

                                        <c:otherwise>
                                            <a title="${titleStageInit}" class="status_number">0</a>
                                            <a title="${titleStageSaleRejected}" class="status_number">1</a>
                                            <a title="${titleStageSaleVerify}" class="status_number">2</a>
                                            <a title="${titleStageFinish}" class="status_number">4</a>
                                            <%--<a title="${titleStageFinish}" class="status_number">6</a>--%>
                                        </c:otherwise>
                                    </c:choose>
                                </display:column>

                                <display:column title="${colAction}" class="action_icon center"
                                                headerClass="center">
                                    <a href="${contextPath}<%=TRANS_REVERSAL_DETAIL%>?txnReversalId=${item.id}"
                                       class="detail-link-reversal link-active"
                                       title="<spring:message code="common.title.view.detail"/>"
                                       txnReversalId="${item.id}">
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
    <jsp:param name="selServiceType" value="true"/>
</jsp:include>
<jsp:include page="../include_page/date_picker.jsp">
    <jsp:param name="isFullTime" value="true"/>
    <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>


<script type="text/javascript">
  $(document).ready(function () {

    <%--$('a.detail-link').click(function () {--%>
      <%--var txnId = $(this).attr("txnId");--%>
      <%--var searchURL = '';--%>
      <%--if (window.location.search.indexOf("?") >= 0) {--%>
        <%--searchURL = '${txnControlUri}/detail' + window.location.search + '&txnId=' + txnId;--%>
      <%--} else {--%>
        <%--searchURL = '${txnControlUri}/detail?' + 'txnId=' + txnId;--%>
      <%--}--%>
      <%--window.location.href = searchURL;--%>
    <%--});--%>

    <%--$('a.detail-link-reversal').click(function () {--%>
      <%--var orderId = $(this).attr("txnReversalId");--%>
      <%--var searchURL = '';--%>
      <%--if (window.location.search.indexOf("?") >= 0) {--%>
        <%--searchURL = ctx + '<%=TRANS_REVERSAL_DETAIL%>' + window.location.search + '&txnReversalId=' + orderId;--%>
      <%--} else {--%>
        <%--searchURL = ctx + '<%=TRANS_REVERSAL_DETAIL%>?' + 'txnReversalId=' + orderId;--%>
      <%--}--%>
      <%--window.location.href = searchURL;--%>
    <%--});--%>
  });
</script>
</body>
</html>