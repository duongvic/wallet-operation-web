<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 5/31/2021
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../../include_page/taglibs.jsp" %>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
    <!-- Basic -->
    <meta charset="UTF-8">
    <title><spring:message code="menu.left.reconciliation"/></title>
    <jsp:include page="../../include_page/head.jsp">
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <jsp:include page="../../include_page/js_merchant.jsp">
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <style>
        * {
            font-family: 'Open Sans', sans-serif;
        }

        .title-content {
            white-space: nowrap;
            width: 200px;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body>
<section class="body">
    <jsp:include page="../../include_page/header.jsp"/>
    <jsp:include page="../../include_component/constant_application.jsp"/>

    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../../include_page/navigation.jsp">
            <jsp:param value="reconciliation" name="nav"/>
        </jsp:include>

        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><span class=""><spring:message
                                        code="service.exportcard.list.navigate.service"/></span>
                                </li>
                                <li><span class=""><spring:message
                                        code="menu.left.reconciliation"/></span></li>
                                <li><span class="nav-active"><spring:message
                                        code="label.recon.by.payment.service"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <spring:message var="recolAllTxt" code="label.all"/>
            <div class="content-body-wrap">
                <div class="container-fluid">
                    <div class="tabs">
                        <ul class="nav nav-tabs">
                            <li class="${empty param.stageTab ? 'active': ''}">
                                <a href="${contextPath}/wallet/reconcilation/list?${queryString}&paymentPolicieTab=${param.paymentPolicieTab}">
                                    ${recolAllTxt}</a>
                            </li>
                            <%--2,4,6--%>
                            <li class="${param.stageTab eq 'even' ? 'active': ''}">
                                <a href="${contextPath}/wallet/reconcilation/list?${queryString}stageTab=even&paymentPolicieTab=${param.paymentPolicieTab}">
                                    <spring:message code="label.reconciliation"/></a>
                            </li>
                            <%--1,3,5--%>
                            <li class="${param.stageTab eq 'odd' ? 'active': ''}">
                                <a href="${contextPath}/wallet/reconcilation/list?${queryString}stageTab=odd&paymentPolicieTab=${param.paymentPolicieTab}">
                                    <spring:message code="label.cancelled.reconciliation"/></a>
                            </li>
                            <%--8--%>
                            <li class="${param.stageTab eq '8' ? 'active': ''}">
                                <a href="${contextPath}/wallet/reconcilation/list?${queryString}stageTab=8&paymentPolicieTab=${param.paymentPolicieTab}">
                                    <spring:message code="label.successed.reconciliation"/></a>
                            </li>
                            <%--0--%>
                            <li class="${param.stageTab eq '0' ? 'active': ''}">
                                <a href="${contextPath}/wallet/reconcilation/list?${queryString}stageTab=0&paymentPolicieTab=${param.paymentPolicieTab}">
                                    <spring:message code="label.is.supported.reconciliation"/></a>
                            </li>
                        </ul>

                        <div class="tab-content pl-none pr-none">
                            <div id="tab1" class="tab-pane active">
                                <section class="panel search_payment panel-default">
                                    <div class="panel-body pt-none">
                                        <%--filter--%>
                                        <jsp:include page="search_form_fragment.jsp"/>
                                        <spring:message code="label.policy" var="recolPolicyTxt"/>
                                        <%--body--%>
                                        <div class="clearfix"></div>
                                        <div class="panel-body pt-none pb-none">
                                            <div class="tabs">
                                                <ul class="nav nav-tabs">
                                                    <li class="${empty param.paymentPolicieTab ? 'active': ''}">
                                                        <a href="${contextPath}/wallet/reconcilation/list?${queryString}stageTab=${param.stageTab}">
                                                            ${recolAllTxt}</a>
                                                    </li>
                                                    <li class="${param.paymentPolicieTab eq 'PAYMENT_POLICY_1_1' ? 'active': ''}">
                                                        <a href="${contextPath}/wallet/reconcilation/list?${queryString}paymentPolicieTab=PAYMENT_POLICY_1_1&stageTab=${param.stageTab}">
                                                            ${recolPolicyTxt} 1+1</a>
                                                    </li>
                                                    <li class="${param.paymentPolicieTab eq 'PAYMENT_POLICY_7_1' ? 'active': ''}">
                                                        <a href="${contextPath}/wallet/reconcilation/list?${queryString}paymentPolicieTab=PAYMENT_POLICY_7_1&stageTab=${param.stageTab}">
                                                            ${recolPolicyTxt} 7+1</a>
                                                    </li>
                                                    <li class="${param.paymentPolicieTab eq 'PAYMENT_POLICY_30_1' ? 'active': ''}">
                                                        <a href="${contextPath}/wallet/reconcilation/list?${queryString}paymentPolicieTab=PAYMENT_POLICY_30_1&stageTab=${param.stageTab}">
                                                            ${recolPolicyTxt} 30+1</a>
                                                    </li>
                                                </ul>
                                                <spring:message code="label.init" var="initTitle"/>
                                                <spring:message code="label.operation.manager.rejected"
                                                                var="operationManagerRejectedTitle"/>
                                                <spring:message code="label.operation.staff.submited"
                                                                var="operationStaffSubmitedTitle"/>
                                                <spring:message code="label.merchant.rejected"
                                                                var="merchantRejectedTitle"/>
                                                <spring:message code="label.operation.manager.approved"
                                                                var="operationManagerApprovedTitle"/>
                                                <spring:message code="label.finance.manager.rejected"
                                                                var="financeManagerRejectedTitle"/>
                                                <spring:message code="label.merchant.confirmed"
                                                                var="merchantConfirmedTitle"/>
                                                <spring:message code="label.title" var="recolTitleTxt"/>
                                                <spring:message code="label.action" var="recolActionTxt"/>
                                                <spring:message code="label.amount.paid.by.merchant"
                                                                var="recolMerchantPaidTxt"/>
                                                <div class="tab-content pl-none pr-none">
                                                    <div id="tab2" class="tab-pane active">
                                                        <div class="table-responsive">
                                                            <spring:message code="label.recon.status" var="recolStt"/>
                                                            <display:table name="reconciliations" id="item"
                                                                           requestURI="list"
                                                                           pagesize="${pagesize}"
                                                                           partialList="true"
                                                                           size="total"
                                                                           sort="page"
                                                                           class="table table-bordered table-responsive table-striped mb-none">

                                                                <%@ include
                                                                        file="../../include_component/display_table.jsp" %>

                                                                <display:column title="STT" class="right" style="">
                                                                    <span class="rowid">
                                                                      <c:out value="${offset + item_rowNum}"/>
                                                                    </span>
                                                                </display:column>
                                                                <display:column title="Merchant" class=""
                                                                                style="">
                                                                    <a class="detail-link link-active" target="_blank"
                                                                       href="${contextPath}/wallet/reconcilation/profile/detail/${item.contract.customerId}">
                                                                        <c:choose>
                                                                            <c:when test="${ewallet:getDayBetween(item.contract.expiredDate, currentDate) < 0}">
                                                                                <i style="color: #0a6aa1"
                                                                                   class="fa fa-user"></i>
                                                                                <i style="color: rgb(69, 204, 88); position: relative; right: 7px"
                                                                                   class="fa fa-check-circle-o"></i>
                                                                            </c:when>
                                                                            <c:when test="${ewallet:getDayBetween(item.contract.expiredDate, currentDate) < 30}">
                                                                                <i style="color: #0a6aa1"
                                                                                   class="fa fa-user"></i>
                                                                                <i style="color: orange; position: relative; right: 7px"
                                                                                   class="fa fa-exclamation-circle"></i>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <i style="color: #0a6aa1"
                                                                                   class="fa fa-user"></i>
                                                                                <i style="color: red; position: relative; right: 7px"
                                                                                   class="fa fa-times-circle-o"></i>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                            ${item.customerName}
                                                                    </a>
                                                                </display:column>
                                                                <display:column title="${recolTitleTxt}" class=""
                                                                                style="">
                                                                    <div class="title-content" title="${item.title}">
                                                                            ${item.title}
                                                                    </div>
                                                                </display:column>
                                                                <display:column title="Dịch vụ TT" headerClass="center"
                                                                                class="center"
                                                                                style="">
                                                                    <c:choose>
                                                                        <c:when test="${fn:contains(item.geKeyArr(), '%EPIN%')}">
                                                                            <img style="width: 15px;" title="EPIN"
                                                                                 src="${contextPath}/assets/images/terms/i_card_charging_a@2x.png"
                                                                                 alt="">
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <img style="width: 15px;" title="EPIN"
                                                                                 src="${contextPath}/assets/images/terms/i_card_charging_n@2x.png"
                                                                                 alt="">
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                    <c:choose>
                                                                        <c:when test="${fn:contains(item.geKeyArr(), '%EXPORT_EPIN%')}">
                                                                            <img style="width: 15px;"
                                                                                 title="EXPORT_EPIN"
                                                                                 src="${contextPath}/assets/images/terms/i_api_otp_a@2x.png"
                                                                                 alt="">
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <img style="width: 15px;"
                                                                                 title="EXPORT_EPIN"
                                                                                 src="${contextPath}/assets/images/terms/i_api_otp_n@2x.png"
                                                                                 alt="">
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                    <c:choose>
                                                                        <c:when test="${fn:contains(item.geKeyArr(), '%PHONE_TOPUP%')}">
                                                                            <img style="width: 15px;"
                                                                                 title="PHONE_TOPUP"
                                                                                 src="${contextPath}/assets/images/terms/i_sms_charging_a@2x.png"
                                                                                 alt="">
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <img style="width: 15px;"
                                                                                 title="PHONE_TOPUP"
                                                                                 src="${contextPath}/assets/images/terms/i_sms_charging_n@2x.png"
                                                                                 alt="">
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                    <c:choose>
                                                                        <c:when test="${fn:contains(item.geKeyArr(), '%BILL_PAYMENT%')}">
                                                                            <img style="width: 15px;"
                                                                                 title="BILL_PAYMENT"
                                                                                 src="${contextPath}/assets/images/terms/i_bank_charging_a@2x.png"
                                                                                 alt="">
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <img style="width: 15px;"
                                                                                 title="BILL_PAYMENT"
                                                                                 src="${contextPath}/assets/images/terms/i_bank_charging_n@2x.png"
                                                                                 alt="">
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </display:column>
                                                                <display:column title="${recolMerchantPaidTxt}"
                                                                                class="right"
                                                                                style="">
                                                                    <fmt:formatNumber type="number"
                                                                                      maxFractionDigits="3"
                                                                                      value="${item.sumOfNetAmount}"/>
                                                                </display:column>
                                                                <display:column title="${recolStt}" headerClass="center"
                                                                                class="center"
                                                                                style="">
                                                                    <%--<i class="fa fa-check check_status"></i>--%>
                                                                    <%--<i class="fa fa-warning warning_status"></i>--%>
                                                                    <%--<i class="fa fa-times reject_status"></i>--%>
                                                                    <c:choose>
                                                                        <c:when test="${item.stage eq 'INIT'}">
                                                                            <a title="${initTitle}"
                                                                                        onclick="openWorkflowModal(${item.id}, '${item.stage}')"
                                                                               class=""><i
                                                                                    class="fa fa-warning warning_status"></i></a>
                                                                            <a title="${operationManagerRejectedTitle}"
                                                                               class="status_number">1</a>
                                                                            <a title="${operationStaffSubmitedTitle}"
                                                                               class="status_number">2</a>
                                                                            <a title="${merchantRejectedTitle}"
                                                                               class="status_number">3</a>
                                                                            <a title="${operationManagerApprovedTitle}"
                                                                               class="status_number">4</a>
                                                                            <a title="${financeManagerRejectedTitle}"
                                                                               class="status_number">5</a>
                                                                            <a title="${merchantConfirmedTitle}"
                                                                               class="status_number">6</a>
                                                                        </c:when>
                                                                        <c:when test="${item.stage eq 'OPERATION_MANAGER_REJECTED'}">
                                                                            <a title="${initTitle}" class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a onclick="openWorkflowModal(${item.id}, '${item.stage}')"
                                                                                    title="${operationManagerRejectedTitle}"
                                                                                    class=""><i
                                                                                    class="fa fa-times reject_status"></i></a>
                                                                            <a title="${operationStaffSubmitedTitle}"
                                                                               class="status_number">2</a>
                                                                            <a title="${merchantRejectedTitle}"
                                                                               class="status_number">3</a>
                                                                            <a title="${operationManagerApprovedTitle}"
                                                                               class="status_number">4</a>
                                                                            <a title="${financeManagerRejectedTitle}"
                                                                               class="status_number">5</a>
                                                                            <a title="${merchantConfirmedTitle}"
                                                                               class="status_number">6</a>
                                                                        </c:when>
                                                                        <c:when test="${item.stage eq 'OPERATION_STAFF_SUBMITED'}">
                                                                            <a title="${initTitle}" class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a title="${operationManagerRejectedTitle}"
                                                                               class="status_number">1</a>
                                                                            <a
                                                                                    <sec:authorize
                                                                                            access="hasAnyRole('RECONCILIATION', 'ADMIN_OPERATION')">
                                                                                        onclick="openWorkflowModal(${item.id}, '${item.stage}')"
                                                                                    </sec:authorize>
                                                                                    title="${operationStaffSubmitedTitle}"
                                                                                    class=""><i
                                                                                    class="fa fa-warning warning_status"></i></a>
                                                                            <a title="${merchantRejectedTitle}"
                                                                               class="status_number">3</a>
                                                                            <a title="${operationManagerApprovedTitle}"
                                                                               class="status_number">4</a>
                                                                            <a title="${financeManagerRejectedTitle}"
                                                                               class="status_number">5</a>
                                                                            <a title="${merchantConfirmedTitle}"
                                                                               class="status_number">6</a>
                                                                        </c:when>
                                                                        <c:when test="${item.stage eq 'MERCHANT_REJECTED'}">
                                                                            <a title="${initTitle}" class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a title="${operationManagerRejectedTitle}"
                                                                               class="status_number">1</a>
                                                                            <a title="${operationStaffSubmitedTitle}"
                                                                               class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a
                                                                                    <sec:authorize
                                                                                            access="hasAnyRole('RECONCILIATION', 'ADMIN_OPERATION')">
                                                                                        onclick="openWorkflowModal(${item.id}, '${item.stage}')"
                                                                                    </sec:authorize>
                                                                                    title="${merchantRejectedTitle}"
                                                                                    class=""><i
                                                                                    class="fa fa-times reject_status"></i></a>
                                                                            <a title="${operationManagerApprovedTitle}"
                                                                               class="status_number">4</a>
                                                                            <a title="${financeManagerRejectedTitle}"
                                                                               class="status_number">5</a>
                                                                            <a title="${merchantConfirmedTitle}"
                                                                               class="status_number">6</a>
                                                                        </c:when>
                                                                        <c:when test="${item.stage eq 'OPERATION_MANAGER_APPROVED'}">
                                                                            <a title="${initTitle}" class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a title="${operationManagerRejectedTitle}"
                                                                               class="status_number">1</a>
                                                                            <a title="${operationStaffSubmitedTitle}"
                                                                               class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a title="${merchantRejectedTitle}"
                                                                               class="status_number">3</a>
                                                                            <a
                                                                                    <sec:authorize access="hasAnyRole('RECONCILIATION_LEADER', 'ADMIN_OPERATION')">
                                                                                        onclick="openWorkflowModal(${item.id}, '${item.stage}')"
                                                                                    </sec:authorize>
                                                                                    title="${operationManagerApprovedTitle}"
                                                                                    class=""><i
                                                                                    class="fa fa-warning warning_status"></i></a>
                                                                            <a title="${financeManagerRejectedTitle}"
                                                                               class="status_number">5</a>
                                                                            <a title="${merchantConfirmedTitle}"
                                                                               class="status_number">6</a>
                                                                        </c:when>
                                                                        <c:when test="${item.stage eq 'FINANCE_MANAGER_REJECTED'}">
                                                                            <a title="${initTitle}" class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a title="${operationManagerRejectedTitle}"
                                                                               class="status_number">1</a>
                                                                            <a title="${operationStaffSubmitedTitle}"
                                                                               class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a title="${merchantRejectedTitle}"
                                                                               class="status_number">3</a>
                                                                            <a title="${operationManagerApprovedTitle}"
                                                                               class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a
                                                                                    <sec:authorize access="hasAnyRole('RECONCILIATION_LEADER', 'ADMIN_OPERATION')">
                                                                                        onclick="openWorkflowModal(${item.id}, '${item.stage}')"
                                                                                    </sec:authorize>
                                                                                    title="${financeManagerRejectedTitle}"
                                                                                    class=""><i
                                                                                    class="fa fa-times reject_status"></i></a>
                                                                            <a title="${merchantConfirmedTitle}"
                                                                               class="status_number">6</a>
                                                                        </c:when>
                                                                        <c:when test="${item.stage eq 'MERCHANT_CONFIRMED'}">
                                                                            <a title="${initTitle}" class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a title="${operationManagerRejectedTitle}"
                                                                               class="status_number">1</a>
                                                                            <a title="${operationStaffSubmitedTitle}"
                                                                               class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a title="${merchantRejectedTitle}"
                                                                               class="status_number">3</a>
                                                                            <a title="${operationManagerApprovedTitle}"
                                                                               class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a title="${financeManagerRejectedTitle}"
                                                                               class="status_number">5</a>
                                                                            <a
                                                                                    <sec:authorize access="hasAnyRole('MERCHANT', 'ADMIN_OPERATION')">
                                                                                        onclick="openWorkflowModal(${item.id}, '${item.stage}')"
                                                                                    </sec:authorize>
                                                                                    title="${merchantConfirmedTitle}"
                                                                                    class=""><i
                                                                                    class="fa fa-warning warning_status"></i></a>
                                                                        </c:when>
                                                                        <c:when test="${item.stage eq 'FINISHED'}">
                                                                            <a title="${initTitle}" class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a title="${operationManagerRejectedTitle}"
                                                                               class="status_number">1</a>
                                                                            <a title="${operationStaffSubmitedTitle}"
                                                                               class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a title="${merchantRejectedTitle}"
                                                                               class="status_number">3</a>
                                                                            <a title="${operationManagerApprovedTitle}"
                                                                               class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                            <a title="${financeManagerRejectedTitle}"
                                                                               class="status_number">5</a>
                                                                            <a title="${merchantConfirmedTitle}"
                                                                               class=""><i
                                                                                    class="fa fa-check check_status"></i></a>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </display:column>
                                                                <display:column title="${recolActionTxt}"
                                                                                headerClass="center"
                                                                                class="center action_icon"
                                                                                style="">
                                                                    <a title="Chỉnh sửa"
                                                                       href="${contextPath}/wallet/reconcilation/edit/${item.id}"><i
                                                                            class="icon-note"></i></a>
                                                                    <a title="Chi tiết"
                                                                       href="${contextPath}/wallet/reconcilation/detail/${item.id}"><i
                                                                            class="icon-book-open"></i></a>
                                                                    <a title="In"
                                                                       href="${contextPath}/wallet/reconcilation/print/${item.id}"><i
                                                                            class="fa fa-file-word-o"></i></a>
                                                                    <a title="Xuất excel"
                                                                       href="${contextPath}/wallet/reconcilation/export/${item.id}"><i
                                                                            class="fa fa-file-excel-o"></i></a>
                                                                    <a title="Xóa"
                                                                       onclick="deleteReconcilation(${item.id})"
                                                                       title="Xóa">
                                                                        <i class="fa fa-trash"
                                                                           style="color: #67a22c"></i>
                                                                    </a>
                                                                </display:column>

                                                            </display:table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="clearfix"></div>
                                        </br>
                                    </div>
                                </section>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="workflow_modal.jsp"/>
        <jsp:include page="export_excel_bydate_modal.jsp"/>
        <jsp:include page="../../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../../include_page/js_footer.jsp"/>
<jsp:include page="services.jsp"/>
</body>
</html>
