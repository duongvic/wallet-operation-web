<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 4/26/2021
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
    <!-- Basic -->
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
        <jsp:param name="tableLib" value="true"/>
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
    <jsp:include page="../include_component/constant_application.jsp"/>
    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="txnDashBoard" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><a href="${contextPath}/service/transaction-history/list-all"><spring:message code="dashboard.service"/></a></li>
                                <li><span class="nav-active"><spring:message code="menu.left.dashboard"/></span></li>
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

            <div class="content-body-wrap">
                <div class="container-fluid">
                    <div class="tabs">
                        <div class="tab-content pl-none pr-none">
                            <div id="tab1" class="tab-pane active">
                                <section class="panel search_payment panel-default">
                                    <div class="panel-body pt-none">
                                        <div class="mt-lg">
                                            <jsp:include page="search_form_fragment.jsp"/>
                                        </div>
                                        <div class="mt-lg">
                                            <jsp:include page="summary_fragment.jsp"/>
                                        </div>
                                        <div class="mt-lg">
                                            <jsp:include page="line_chart_fragment.jsp"/>
                                        </div>
                                        <div class="mt-lg">
                                            <jsp:include page="pie_chart_tbl_fragment.jsp"/>
                                        </div>
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
    <jsp:param name="currentHour" value="true"/>
    <jsp:param name="currentMonth" value="true"/>
</jsp:include>

<jsp:include page="../include_component/search_form_commons.jsp">
    <jsp:param name="selCustomer" value="true"/>
    <jsp:param name="selCustomerType" value="true"/>
    <jsp:param name="selProviderType" value="true"/>
    <jsp:param name="selServiceType" value="true"/>
    <jsp:param name="selTxnStatus" value="true"/>
    <jsp:param name="selSourceOfFund" value="true"/>
    <jsp:param name="paymentChannelId" value="true"/>
</jsp:include>
<script src="<c:url value="/assets/development/static/js/transaction.js"/>" async></script>
<jsp:include page="services.jsp"/>
</body>
</html>
