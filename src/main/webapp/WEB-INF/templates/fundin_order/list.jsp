<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.fundin.FundInController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.fundin.FundInOrderController" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
    <meta charset="UTF-8">
    <title><spring:message code="fundin.history.title.page"/></title>
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

<sec:authorize
        access="hasAnyRole('SALE_DIRECTOR','SALE_EXCUTIVE', 'SALE_SUPERVISOR' , 'SALE_ASM', 'SALE_RSM')"
        var="perActionNotGetAllAccount"/>
<sec:authorize access="hasAnyRole('FA_MANAGER')" var="perActionRepaymentVerify"/>
<body>
<section class="body">

    <!--        ///////   header ////////-->
    <jsp:include page="../include_page/header.jsp"/>
    <jsp:include page="../include_component/constant_application.jsp"/>
    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="orderfund_in" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><span><spring:message
                                        code="fundin.history.navigate.fundin"/></span></li>
                                <li><span class="nav-active"><spring:message
                                        code="fundin.history.navigate.fundin.request"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <jsp:include page="../include_page/message.jsp"/>

            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">

                    <div class="form-inline">
                        <div class="pull-left h4 mb-md mt-md"><spring:message
                                code="fundin.order.list.subnav.title"/></div>
                        <div class="fr form-responsive">
                            <sec:authorize
                                    access="hasAnyRole('ADMIN_OPERATION','SALE_EXCUTIVE','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')">
                                <button class="btn btn-success mb-xs mt-xs"
                                        onclick="location.href = 'request-fundin';">
                                    <i class="fa fa-plus"></i>&nbsp;<spring:message
                                        code="common.btn.create.request"/>
                                </button>
                            </sec:authorize>

                        </div>
                    </div>

                    <div class="clearfix"></div>

                    <section class="panel search_payment panel-default">
                        <div class="panel-body pt-none">
                            <form action="" method="GET" id="tbl-filter" class='mb-md'>
                                <div class="form-group ml-none mr-none">
                                    <div class="input-group input-group-icon">
                    <span class="input-group-addon">
                      <span class="icon" style="opacity: 0.4"><i
                              class="fa fa-search-minus"></i></span>
                    </span>
                                        <input type="text" id="search" name="quickSearch"
                                               class="form-control"
                                               placeholder="<spring:message code="fundout.order.form.text.search"/>"
                                               value="${param.quickSearch}"/>
                                    </div>
                                </div>
                                <spring:message code="select.choose.all" var="langChooseAll"
                                                scope="request"/>
                                <spring:message code="select.status" var="langStatus"/>

                                <c:if test="${!perActionNotGetAllAccount}">
                                    <select class="js-data-example-ajax-account"
                                            multiple="multiple" name="customerIds">
                                    </select>
                                </c:if>

                                <div class="form-inline" style="margin-top: 9px">
                                    <jsp:include page="../include_component/date_range.jsp"/>

                                    <div class="pull-right form-responsive bt-plt">
                                        <%--<sec:authorize access="hasAnyRole('ADMIN_OPERATION','FINANCE','SALE_EXCUTIVE', 'SALE_DIRECTOR')" var="permitSearchCustomer">--%>
                                        <%--<jsp:include page="../include_component/search_customer.jsp"/>--%>
                                        <%--</sec:authorize>--%>
                                        <%--<sec:authorize--%>
                                        <%--access="hasAnyRole('SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM')"--%>
                                        <%--var="permitSearchSales">--%>
                                        <c:if test="${perActionNotGetAllAccount}">
                                            <jsp:include
                                                    page="../include_component/search_customer.jsp"/>
                                            <%--</sec:authorize>--%>
                                        </c:if>

                                        <jsp:include
                                                page="../include_component/search_channel.jsp"/>

                                        <jsp:include
                                                page="../include_component/search_fundorder_flow_stage.jsp"/>

                                            <jsp:include
                                                    page="../include_component/searh_isDept.jsp"/>

                                        <button type="submit" class="btn btn-primary ml-tiny"><i
                                                class="fa fa-search"></i>&nbsp;<spring:message
                                                code="common.btn.search"/></button>
                                        <button class="btn btn-default nomal_color_bk bt-cancel"
                                                onclick="ClearFields();"><spring:message
                                                code="common.btn.cancel"/></button>

                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </form>
                            <div class="label-inline">
                                <spring:message code="fundin.title.transaction"/>&nbsp;<span
                                    class="primary_color text-semibold">${ewallet:formatNumber(total)}</span>
                                |
                                <spring:message code="fundin.title.amount"/>&nbsp; <span
                                    class="primary_color text-semibold">${ewallet:formatNumber(totalAmount)}</span>&nbsp;<spring:message
                                    code="fundin.table.header.currency"/>
                            </div>

                            <sec:authorize
                                    access="hasAnyRole('ADMIN_OPERATION','MERCHANT','CUSTOMER','SALE_EXCUTIVE','SALE_DIRECTOR')"
                                    var="permisEditCashOnHand"/>
                            <sec:authorize
                                    access="hasAnyRole('ADMIN_OPERATION','MERCHANT','CUSTOMER')"
                                    var="permisEditBankTransfer"/>

                            <sec:authorize
                                    access="hasAnyRole('ADMIN_OPERATION','SALE_EXCUTIVE', 'SALE_DIRECTOR')"
                                    var="permisSaleExcutiveVerify"/>

                            <sec:authorize
                                    access="hasAnyRole('ADMIN_OPERATION','SALE_EXCUTIVE', 'SALE_DIRECTOR')"
                                    var="permisFinanceSupportReject"/>
                            <sec:authorize access="hasAnyRole('ADMIN_OPERATION','FINANCE')"
                                           var="permisFinanceSupportVerify"/>

                            <sec:authorize access="hasAnyRole('ADMIN_OPERATION','FINANCE')"
                                           var="perFinanceManagerRejected"/>
                            <sec:authorize
                                    access="hasAnyRole('ADMIN_OPERATION','FA_MANAGER','FINANCESUPPORT_LEADER')"
                                    var="permisFinanceMamagerApprove"/>

                            <spring:message var="colStt" code="fundout.order.tbl.col.stt"/>
                            <spring:message var="colCtime" code="fundout.order.tbl.col.ctime"/>
                            <spring:message var="colSyntax" code="fundout.order.tbl.col.syntax"/>
                            <spring:message var="colMerchant"
                                            code="fundout.order.tbl.col.merchant"/>
                            <spring:message var="colChannel" code="fundout.order.tbl.col.channel"/>
                            <spring:message code="fundin.table.column.note" var="colNote"/>
                            <spring:message var="colAmount" code="fundout.order.tbl.col.amount"/>
                            <spring:message var="colStage" code="fundout.order.tbl.col.stage"/>
                            <spring:message var="colTxnId" code="fundout.order.tbl.col.txnId"/>
                            <spring:message var="colStatus" code="fundout.order.tbl.col.status"/>
                            <spring:message var="colAction" code="fundout.order.tbl.col.action"/>

                            <display:table name="fundInOrders" id="item"
                                           requestURI="list"
                                           pagesize="${pagesize}" partialList="true"
                                           size="total"
                                           sort="page"
                                           class="table table-bordered table-striped mb-none">

                                <%@ include file="../include_component/display_table.jsp" %>

                                <display:column title="${colStt}" headerClass="fit_to_content"
                                                class="right">
	                  <span id="row${item.id}" class="rowid">
	                      <c:out value="${offset + item_rowNum}"/>
	                  </span>
                                </display:column>
                                <display:column title="${colCtime}" property="createdTime"
                                                format="{0,date,HH:mm dd-MM-yyyy}"/>
                                <display:column title="${colSyntax}">
                                    ${item.syntax}
                                    <c:if test="${item.isDept eq true}">
                                    &nbsp<span class="badge badge-warning">
                                        <spring:message code="label.pay"/>
                                        </span>
                                    </c:if>
                                </display:column>
                                <display:column title="${colMerchant}">
                                    <%--<jsp:include page="../include_component/column_username.jsp">--%>
                                    <%--<jsp:param name="username" value="${item.username}"/>--%>

                                    <%--</jsp:include>--%>
                                    <c:set value="${item.username}" var="username"/>
                                    <c:choose>

                                        <c:when test="${fn:contains(username, '@')}">
                                            ${fn:substringBefore(username, "@")}</br>@${fn:substringAfter(username, "@")}
                                        </c:when>
                                        <c:otherwise>
                                            <a class="detail-link link-active"
                                               href="${contextPath}/customer/manage/details/${item.customerId}?menu=cus">${username}</a>

                                        </c:otherwise>
                                    </c:choose>

                                </display:column>

                                <display:column title="${colChannel}">
                                    <spring:message code="${item.textOrderChannel()}"/>
                                </display:column>

                                <display:column title="${colNote}">
                                    <spring:message code="${item.info}"/>
                                </display:column>

                                <display:column title="${colAmount}" class="col-number-header"
                                                headerClass="col-number-header">${ewallet:formatNumber(item.amount)}</display:column>
                                <%--Progress_Tiến trình--%>
                                <display:column title="${colStage}" headerClass="center"
                                                class="status_icon center"
                                                style="min-width: 170px;">
                                    <c:choose>
                                        <%--define stage constant inside constant_application.jsp--%>
                                        <%--#1--%>
                                        <c:when test="${item.stage eq saleExcutiveReject}">
                                            <a title="<spring:message code="merchant.request"/> "><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a title="<spring:message code="sales.excutive.reject.request"/>"><i
                                                    class="fa fa-times reject_status"></i></a>
                                            <a class="status_number" title="">2</a>
                                            <a class="status_number" title="">3</a>
                                            <a class="status_number" title="">4</a>
                                            <a class="status_number" title="">5</a>
                                            <a class="status_number" title="">6</a>
                                            <a class="status_number" title="">7</a>
                                            <a class="status_number" title="">8</a>
                                        </c:when>

                                        <%--#2--%>
                                        <c:when test="${item.stage eq saleExcutiveVerify}">
                                            <a title="<spring:message code="merchant.request"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">1</a>
                                            <c:choose>
                                                <c:when test="${permisSaleExcutiveVerify}">
                                                    <a title="<spring:message code="sales.excutive.approve.request"/>"
                                                       href="${contextPath}/wallet/fund-in/request-cash-in?id=${item.id}"><i
                                                            class="fa fa-warning warning_status"></i></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a title="<spring:message code="sales.excutive.approve.request"/>"
                                                       href="#" class="not-role"><i
                                                            class="fa fa-warning warning_status"></i></a>
                                                </c:otherwise>
                                            </c:choose>
                                            <a class="status_number" title="">3</a>
                                            <a class="status_number" title="">4</a>
                                            <a class="status_number" title="">5</a>
                                            <a class="status_number" title="">6</a>
                                            <a class="status_number" title="">7</a>
                                            <a class="status_number" title="">8</a>
                                        </c:when>

                                        <%--#3--%>
                                        <c:when test="${item.stage eq financeSupportReject}">
                                            <a title="<spring:message code="merchant.request"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">1</a>
                                            <a title="<spring:message code="sales.excutive.sales.approved"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <c:choose>
                                                <c:when test="${permisFinanceSupportReject}">
                                                    <a title="<spring:message code="finance.staff.reject.order" /> "
                                                       href="${contextPath}/wallet/fund-in/request-cash-in?id=${item.id}"><i
                                                            class="fa fa-times reject_status"></i></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a title="<spring:message code="finance.staff.reject.order" />"
                                                       href="#" class="not-role"><i
                                                            class="fa fa-times reject_status"></i></a>
                                                </c:otherwise>
                                            </c:choose>
                                            <a class="status_number" title="">4</a>
                                            <a class="status_number" title="">5</a>
                                            <a class="status_number" title="">6</a>
                                            <a class="status_number" title="">7</a>
                                            <a class="status_number" title="">8</a>
                                        </c:when>

                                        <%--#4--%>
                                        <c:when test="${item.stage eq financeSupportVerify}">
                                            <a title="<spring:message code="merchant.request"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">1</a>
                                            <a title="<spring:message code="sales.excutive.sales.approved"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">3</a>

                                            <c:choose>
                                                <c:when test="${permisFinanceSupportVerify}">
                                                    <a title="<spring:message code="finance.staff.approve.order"/> "
                                                       href="${contextPath}/wallet/fund-in/verify-cash-in?id=${item.id}"><i
                                                            class="fa fa-warning warning_status"></i></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a title="<spring:message code="finance.staff.approve.order"/>"
                                                       href="#" class="not-role"><i
                                                            class="fa fa-warning warning_status"></i></a>
                                                </c:otherwise>
                                            </c:choose>

                                            <a class="status_number" title="">5</a>
                                            <a class="status_number" title="">6</a>
                                            <a class="status_number" title="">7</a>
                                            <a class="status_number" title="">8</a>
                                        </c:when>

                                        <%--#5--%>
                                        <c:when test="${item.stage eq financeManagerRejected}">
                                            <a title="<spring:message code="merchant.request"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">1</a>
                                            <a title="<spring:message code="sales.excutive.sales.approved"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">3</a>
                                            <a title="<spring:message code="finance.staff.finance.approved"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <c:choose>
                                                <c:when test="${perFinanceManagerRejected}">
                                                    <a title="<spring:message code="finance.leader.reject.fundin"/> "
                                                       href="${contextPath}/wallet/fund-in/verify-cash-in?id=${item.id}"><i
                                                            class="fa fa-times reject_status"></i></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a title="<spring:message code="finance.leader.reject.fundin"/>"
                                                       href="#" class="not-role"><i
                                                            class="fa fa-times reject_status"></i></a>
                                                </c:otherwise>
                                            </c:choose>
                                            <a class="status_number" title="">6</a>
                                            <a class="status_number" title="">7</a>
                                            <a class="status_number" title="">8</a>
                                        </c:when>

                                        <%--#6--%>
                                        <c:when test="${item.stage eq financeMamagerApprove}">
                                            <a title="<spring:message code="merchant.request"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">1</a>
                                            <a title="<spring:message code="sales.excutive.sales.approved"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">3</a>
                                            <a title="<spring:message code="finance.staff.finance.approved"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">5</a>
                                            <c:choose>
                                                <c:when test="${permisFinanceMamagerApprove}">
                                                    <a title="<spring:message code="finance.leader.approve.fundin"/>"
                                                       class="fundin_approve"
                                                       href="${contextPath}/wallet/fund-in/verify-cash-in?id=${item.id }&stage=${item.stage }"
                                                       fid="${item.id }"><i
                                                            class="fa fa-warning warning_status"></i></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a title="<spring:message code="finance.leader.approve.fundin"/> "
                                                       href="#" class="not-role"><i
                                                            class="fa fa-warning warning_status"></i></a>
                                                </c:otherwise>
                                            </c:choose>
                                            <a class="status_number" title="">7</a>
                                            <a class="status_number" title="">8</a>
                                        </c:when>

                                        <%--#7--%>
                                        <c:when test="${item.stage eq financeManagerRepaymentRejected}">
                                            <a title="<spring:message code="merchant.request"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">1</a>
                                            <a title="<spring:message code="sales.excutive.sales.approved"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">3</a>
                                            <a title="<spring:message code="finance.staff.finance.approved"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">5</a>
                                            <a title="<spring:message code="finance.leader.approved.fundin" />"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a
                                                    <c:if test="${perActionRepaymentVerify}">
                                                    href="${contextPath}/wallet/fund-in/verify-repayment/${item.id}?stage=${item.stage}"
                                                    </c:if>
                                               title="<spring:message code="stage.finance.manager.repayment.verify.failed"/>">
                                                <i class="fa fa-times reject_status"
                                                        <c:if test="${!perActionRepaymentVerify}">
                                                            style="opacity: 0.5;"
                                                        </c:if>
                                                ></i>
                                            </a>
                                            <a class="status_number" title="">8</a>
                                        </c:when>
                                        <%--#8--%>
                                        <c:when test="${item.stage eq financeManagerRepaymentApprove}">
                                            <a title="<spring:message code="merchant.request"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">1</a>
                                            <a title="<spring:message code="sales.excutive.sales.approved"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">3</a>
                                            <a title="<spring:message code="finance.staff.finance.approved"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">5</a>
                                            <a title="<spring:message code="finance.leader.approved.fundin" />"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">7</a>
                                            <a
                                                    <c:if test="${perActionRepaymentVerify}">
                                                        href="${contextPath}/wallet/fund-in/verify-repayment/${item.id}?stage=${item.stage}"
                                                    </c:if>
                                               title="<spring:message code="stage.finance.manager.repayment.ready.to.verify"/>">
                                                <i class="fa fa-warning warning_status"
                                                        <c:if test="${!perActionRepaymentVerify}">
                                                            style="opacity: 0.5;"
                                                        </c:if>
                                                ></i>
                                            </a>
                                        </c:when>

                                        <%--#10--%>
                                        <c:when test="${item.stage eq fundOrderFinished}">
                                            <a title="<spring:message code="merchant.request"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">1</a>
                                            <a title="<spring:message code="sales.excutive.sales.approved"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">3</a>
                                            <a title="<spring:message code="finance.staff.finance.approved"/>"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">5</a>
                                            <a title="<spring:message code="finance.leader.approved.fundin" />"><i
                                                    class="fa fa-check check_status"></i></a>
                                            <a class="status_number" title="">7</a>
                                            <a title="<spring:message code="stage.finance.manager.repayment.verify.success"/>">
                                                <i class="fa fa-check check_status"></i>
                                            </a>
                                        </c:when>

                                        <%--#0--%>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${item.orderChannel eq BANK_TRANSFER and permisEditBankTransfer eq true}">
                                                    <a title="<spring:message code="merchant.confirm"/>"
                                                       class="edit-cash-in" id=${item.id}><i
                                                            class="fa fa-warning warning_status"></i></a>
                                                </c:when>
                                                <c:when test="${item.orderChannel eq CASH_ON_HAND and permisEditCashOnHand eq true}">
                                                    <a title="<spring:message code="merchant.confirm"/>"
                                                       class="edit-cash-in" id=${item.id}><i
                                                            class="fa fa-warning warning_status"></i></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a title="<spring:message code="merchant.confirm"/>"
                                                       href="#" class="not-role"><i
                                                            class="fa fa-warning warning_status"></i></a>
                                                </c:otherwise>
                                            </c:choose>

                                            <a class="status_number" title="">1</a>
                                            <a class="status_number" title="">2</a>
                                            <a class="status_number" title="">3</a>
                                            <a class="status_number" title="">4</a>
                                            <a class="status_number" title="">5</a>
                                            <a class="status_number" title="">6</a>
                                            <a class="status_number" title="">7</a>
                                            <a class="status_number" title="">8</a>
                                        </c:otherwise>
                                    </c:choose>
                                </display:column>
                                <%--End Progress_Tiến trình--%>
                                <display:column title="${colTxnId}" class="col-number-header"
                                                headerClass="col-number-header">
                                    <c:if test="${item.refTxnId != null }">
                                        <a class="link-active"
                                           href="<c:url value="<%=FundInController.FUND_IN_HISTORY_DETAIL%>"/>?txnId=${item.refTxnId}">${item.refTxnId}</a>
                                    </c:if>
                                </display:column>
                                <display:column title="${colStatus}"><spring:message
                                        code="${ewallet:getTxnStatusName(item.refTxnStatus)}"/></display:column>

                                <display:column title="${colAction}" headerClass="action_icon right"
                                                class="action_icon right">
                                    <c:if test="${item.stage eq 0 || list.stage eq 1 }">
                                        <c:choose>
                                            <c:when test="${item.orderChannel eq BANK_TRANSFER and permisEditBankTransfer eq true}">
                                                <a class="edit-cash-in" id="${item.id}"
                                                   title="<spring:message code="common.title.update"/>"><i
                                                        class="fa fa-pencil-square-o "
                                                        aria-hidden="true"></i></a>
                                            </c:when>
                                            <c:when test="${item.orderChannel eq CASH_ON_HAND and permisEditCashOnHand eq true}">
                                                <a class="edit-cash-in" id="${item.id}"
                                                   title="<spring:message code="common.title.update"/>"><i
                                                        class="fa fa-pencil-square-o "
                                                        aria-hidden="true"></i></a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="#" class="not-role"
                                                   title="<spring:message code="common.title.update"/>"><i
                                                        class="fa fa-pencil-square-o "
                                                        aria-hidden="true"></i></a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>

                                    <c:if test="${item.stage != 0 && list.stage != 1 }">
                                        <a href="#" class="not-role"
                                           title="<spring:message code="common.title.update"/>"><i
                                                class="fa fa-pencil-square-o "
                                                aria-hidden="true"></i></a>
                                    </c:if>
                                    <a class="detail-link link-active" id="${item.id}"
                                       title="<spring:message code="common.view.detail"/>"><i
                                            class="fa fa-info-circle "></i></a>
                                </display:column>
                            </display:table>
                        </div>
                    </section>
                </div>
            </div>
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
    <jsp:param name="selChannel" value="true"/>
    <jsp:param name="selOrderStage" value="true"/>
    <jsp:param name="selCustomer" value="true"/>
</jsp:include>

<script type="text/javascript">
  $(document).ready(function () {
    $('a.detail-link').click(function () {
      var id = $(this).attr("id");
      var detailURL = '';
      if (window.location.search.indexOf("?") >= 0) {
        detailURL = ctx + '<%=FundInOrderController.FUND_IN_ORDER_DETAIL%>' + window.location.search
            + '&id=' + id;
      } else {
        detailURL = ctx + '<%=FundInOrderController.FUND_IN_ORDER_DETAIL%>?' + 'id=' + id;
      }
      window.location.href = detailURL;
    });
    $('a.edit-cash-in').click(function () {
      var id = $(this).attr("id");
      var editCashInURL = '';
      if (window.location.search.indexOf("?") >= 0) {
        editCashInURL = ctx + '<%=FundInOrderController.FUND_IN_ORDER_CONTROLLER%>/edit-cash-in'
            + window.location.search + '&id=' + id;
      } else {
        editCashInURL = ctx + '<%=FundInOrderController.FUND_IN_ORDER_CONTROLLER%>/edit-cash-in?'
            + 'id=' + id;
      }
      window.location.href = editCashInURL;
    });
  });

  function ClearFields() {
    document.getElementById("search").value = "";
  }

  $('.js-data-example-ajax-account').select2({
    width: "100%",
    dropdownAutoWidth: true,
    ajax: {
      url: ctx + "/ajax-controller/all/account",
      dataType: 'json',
      type: "POST",
      data: function (params) {
        var query = {
          search: params.term,
          type: 'public'
        }

        // Query parameters will be ?search=[term]&type=public
        return query;
      },
      // Additional AJAX parameters go here; see the end of this chapter for the full code of this example
      processResults: function (data) {
        // Transforms the top-level key of the response object from 'items' to 'results'
        var retVal = [];
//        for (var i = 0; i < data.length; i++) {
        var lineObj = {
          id: data.id,
          text: data.fullName
        }
        retVal.push(lineObj);
//        }
        return {
          // results: data.items
          results: retVal

        };
      }
    },
    placeholder: '<spring:message code="label.input.phone"/>',
    minimumInputLength: 10,
    language: {
      inputTooShort: function () {
        return '<spring:message code="label.input.10.character"/>';
      }
    }
  });

</script>

</body>
</html>
