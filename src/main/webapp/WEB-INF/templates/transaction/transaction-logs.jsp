<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.TransactionCustomerType" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
    <!-- Basic -->
    <meta charset="UTF-8">
    <title><spring:message code="transaction.api.title.page"/></title>
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <link rel="stylesheet" href="<c:url value='/assets/development/static/css/transaction-logs.css'/>" media="none"
          onload="if(media!='all')media='all'">
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
    <jsp:include page="../include_component/constant_application.jsp"/>
    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="hisTxn" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><span class="nav-active"><spring:message
                                        code="transaction.api.navigate.transaction"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <jsp:include page="../include_page/message.jsp"/>

            <!-- start: page -->

            <spring:message code="transaction.api.search.placeholder" var="placeholder"/>
            <spring:message code="select.choose.all" var="langChooseAll" scope="request"/>
            <spring:message code="select.status" var="langStatus" scope="request"/>
            <spring:message code="select.service" var="langService" scope="request"/>
            <spring:message code="select.merchant" var="langMerchant" scope="request"/>
            <spring:message code="select.provider" var="langProvider" scope="request"/>

            <sec:authorize access="hasAnyRole('ADMIN_OPERATION',
                'ACCOUNTING','TECHSUPPORT',
                'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE',
                'MERCHANT','CUSTOMER',
                'CUSTOMERCARE_MANAGER','CUSTOMERCARE',
                'SALE','SALE_ASM','PROVIDER',
                'SALESUPPORT_LEADER','SALESUPPORT_MANAGER','SALESUPPORT')" var="tranlogsRoleWithoutSaleExecutive"/>
            <div class="content-body-wrap">
                <div class="container-fluid">
                    <c:if test="${tranlogsRoleWithoutSaleExecutive}">
                        <div class="h4 mb-md" style="display: flex; justify-content: space-between">
                            <p><spring:message code="transaction.api.subnavigate.apitransaction"/></p>
                            <div>
                                <button class="btn btn-success" type="button" id="btn-batch"><spring:message
                                        code="label.do.txn.by.batch.again"/></button>
                            </div>
                        </div>
                    </c:if>
                    <c:set var="IdOwnerCustomerType" value="<%= TransactionCustomerType.values() %>"/>

                    <div class="tabs">
                        <ul class="nav nav-tabs">
                            <c:if test="${tranlogsRoleWithoutSaleExecutive}">
                                <li class="${param.idOwnerCustomerTypes == null || '' eq param.idOwnerCustomerTypes ? 'active' : ''}">
                                    <a onclick="openTabAll('');" href="#" data-toggle="tab">All</a>
                                </li>
                            </c:if>
                            <c:forEach var="customerTypeItem" items="${IdOwnerCustomerType}">
                                <c:if test="${tranlogsRoleWithoutSaleExecutive}">
                                    <li class="${customerTypeItem.name() eq param.idOwnerCustomerTypes ? 'active' : ''}">
                                        <a onclick="openTab('${customerTypeItem}');" href="#">
                                                ${customerTypeItem.getDisplayText()}
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${!tranlogsRoleWithoutSaleExecutive}">
                                    <c:if test="${customerTypeItem.getDisplayText() eq 'Agent'}">
                                        <li class="${customerTypeItem.name() eq param.idOwnerCustomerTypes ? 'active' : ''}">
                                            <a onclick="openTab('${customerTypeItem}');" href="#">
                                                    ${customerTypeItem.getDisplayText()}
                                            </a>
                                        </li>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </ul>

                        <div class="tab-content pl-none pr-none">
                            <div id="tab1" class="tab-pane active">
                                <sec:authorize access="hasAnyRole('ADMIN_OPERATION')" var="permitAll"/>
                                <sec:authorize access="hasAnyRole('RECONCILIATION', 'RECONCILIATION_LEADER')"
                                               var="permitReconcile"/>
                                <sec:authorize access="hasAnyRole('ADMIN_OPERATION', 'STAFF')" var="permitSearchStaff"/>
                                <sec:authorize
                                        access="hasAnyRole('SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM', 'SALESUPPORT', 'SALESUPPORT_MANAGER','SALE_RSM','CUSTOMERCARE_MANAGER','CUSTOMERCARE')"
                                        var="permitSearchSales"/>

                                <jsp:include page="transaction-content.jsp">
                                    <jsp:param name="isUserSearchSourceOfFundType" value="true"/>
                                    <jsp:param name="permitSearchStaff" value="${permitSearchStaff}"/>
                                    <jsp:param name="permitSearchSales"
                                               value="${permitSearchSales || permitAll || permitReconcile}"/>
                                </jsp:include>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end: page -->

            <%--modal phone topup on hold --%>
            <%@ include file="topup-modal.jsp" %>
            <%--modal bill payment on hold --%>
            <%@ include file="topup-bill-modal.jsp" %>
            <%--modal phone topup batch--%>
            <%@ include file="topup-batch-modal.jsp" %>
        </section>
        <jsp:include page="../include_page/footer.jsp"/>
    </div>
</section>

<jsp:include page="../include_page/js_footer.jsp"/>


<jsp:include page="../include_page/date_picker.jsp">
    <jsp:param name="isFullTime" value="false"/>
    <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>

<jsp:include page="../include_component/export_excel.jsp">
    <jsp:param name="serviceCode" value="true"/>
</jsp:include>
<jsp:include page="../include_component/search_form_commons.jsp">
    <jsp:param name="selCustomer" value="true"/>
    <jsp:param name="selProviderType" value="${permitSearchStaff}"/>
    <jsp:param name="selServiceType" value="true"/>
    <jsp:param name="selTxnStatus" value="true"/>
    <jsp:param name="selSourceOfFund" value="true"/>
    <jsp:param name="paymentChannelId" value="true"/>
</jsp:include>
<script src="<c:url value="/assets/development/static/js/transaction.js"/>" async></script>
<script type="text/javascript">

    var merchants = [];
    <c:forEach var ="item" items = "${paramValues.multiselect}" >
    merchants.push('${item}');
    </c:forEach >

    var searchText = '<c:out value="${param.id }"/>';
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

    function openTab(paramValue) {
        var searchURL = '';
        $('#idOwnerCustomerTypes').val(paramValue);
        if (window.location.search.indexOf("?") >= 0) {
            if (window.location.search.indexOf("idOwnerCustomerTypes") >= 0) {
                searchURL = '${prefixSearchChildUrl}' + replaceUrlParam(window.location.search, 'idOwnerCustomerTypes', paramValue);
            } else {
                searchURL = '${prefixSearchChildUrl}' + window.location.search + '&idOwnerCustomerTypes=' + paramValue;
            }

        } else {
            searchURL = '${prefixSearchChildUrl}' + '?idOwnerCustomerTypes=' + paramValue;
        }
        window.location.href = searchURL;
    }

    function openTabAll() {
        var searchURL = '';
        if (window.location.search.indexOf("?") >= 0 && window.location.search.indexOf("idOwnerCustomerTypes") >= 0) {
            searchURL = '${contextPath}/service/transaction-history/list-all' + replaceUrlParam(window.location.search, 'idOwnerCustomerTypes', '');
        } else {
            searchURL = '${contextPath}/service/transaction-history/list-all';
        }
        window.location.href = searchURL;
    }

</script>
</body>
</html>
