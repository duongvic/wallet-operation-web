<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceLevel" %>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.TelcoServiceType" %>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.TelcoType" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../config/404.jsp" %>
<%@ include file="../include_page/taglibs.jsp" %>

<c:set var="telcoTypes" value="<%=TelcoType.values()%>" scope="request"/>
<c:set var="telcoServiceTypes" value="<%=TelcoServiceType.values()%>" scope="request"/>
<c:set var="serviceLevels" value="<%=ServiceLevel.values()%>" scope="request"/>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
    <meta charset="UTF-8">
    <title><spring:message code="fee.structure.agent.title"/></title>
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <jsp:include page="../include_page/js_service.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <style>
        .checkbox-info label:before {
            border: 1px solid #ecd6d6
        }

        .treetable-expanded > td:first-child, .treetable-collapsed > td:first-child {
            padding-left: 2em;
        }

        .treetable-expanded > td:first-child > .treetable-expander, .treetable-collapsed > td:first-child > .treetable-expander {
            top: 0.05em;
            position: relative;
            margin-left: -0.5em;
            margin-right: 0.25em;
        }

        .treetable-expanded .treetable-expander, .treetable-expanded .treetable-expander {
            width: 1em;
            height: 1em;
            cursor: pointer;
            position: relative;
            display: inline-block;
        }

        .treetable-depth-1 > td:first-child {
            padding-left: 3em;
        }

        .treetable-depth-2 > td:first-child {
            padding-left: 4.5em;
        }

        .treetable-depth-3 > td:first-child {
            padding-left: 6em;
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
            <jsp:param value="cus-type-fee" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><span><spring:message
                                        code="fee.structure.navigate.title"/></span></li>
                                <li><span class="nav-active"><spring:message
                                        code="fee.structure.navigate.all.type"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <jsp:include page="../include_page/message.jsp"/>

            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">
                    <div class="h4 mb-md">
                        <spring:message code="fee.structure.type.subnav.title"/>
                    </div>
                    <div class="clearfix"></div>

                    <c:set var="isCustomerTab"
                           value="${param.customerTypeId == cusTypeCustomer or customerTypeId == cusTypeCustomer}"
                           scope="request"/>
                    <c:set var="isMerchantTab"
                           value="${param.customerTypeId == cusTypeMerchant or customerTypeId == cusTypeMerchant}"
                           scope="request"/>
                    <c:set var="isProviderTab"
                           value="${param.customerTypeId == cusTypeProvider or customerTypeId ==cusTypeProvider }"
                           scope="request"/>
                    <c:set var="isAgentTab"
                           value="${param.customerTypeId ==cusTypeAgent  or customerTypeId == cusTypeAgent }"
                           scope="request"/>
                    <c:set var="isPropertyManagerTab"
                           value="${param.customerTypeId ==cusTypePropertyManager  or customerTypeId == cusTypePropertyManager }"
                           scope="request"/>
                    <div class="tabs">
                        <ul class="nav nav-tabs">
                            <li class="${isCustomerTab ? 'active' : ''}"><a
                                    onclick="openFeeTab(${cusTypeCustomer});" ${isCustomerTab ? 'href="#" data-toggle="tab"' : 'href="#"'}>Customer</a>
                            </li>
                            <li class="${isAgentTab ? 'active' : ''}"><a
                                    onclick="openFeeTab(${cusTypeAgent});" ${isAgentTab ? 'href="#" data-toggle="tab"' : 'href="#"'}>Agent</a>
                            </li>
                            <li class="${isMerchantTab ? 'active' : ''}"><a
                                    onclick="openFeeTab(${cusTypeMerchant});" ${isMerchantTab ? 'href="#" data-toggle="tab"' : 'href="#"'}>Merchant</a>
                            </li>
                            <li class="${isProviderTab ? 'active' : ''}"><a
                                    onclick="openFeeTab(${cusTypeProvider});" ${isProviderTab ? 'href="#" data-toggle="tab"' : 'href="#"'}>Provider</a>
                            </li>
                            <li class="${isPropertyManagerTab ? 'active' : ''}"><a
                                onclick="openFeeTab(${cusTypePropertyManager});" ${isPropertyManagerTab ? 'href="#" data-toggle="tab"' : 'href="#"'}>Property Manager</a>
                            </li>
                        </ul>
                        <div class="tab-content pl-none pr-none">
                            <div id="tab1" class="tab-pane active">
                                <jsp:include page="fee_tree_content.jsp">
                                    <jsp:param name="isCustomerTypeSelect" value="false"/>
                                </jsp:include>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="../include_page/footer.jsp"/>
        <spring:message code="system.commission.fee.popup.update.title" var="changeFeeTitle"/>
        <jsp:include page="../include_component/change_service_commission_fee_modal.jsp">
            <jsp:param name="title" value="${changeFeeTitle}"/>
            <jsp:param name="actionURI" value="/customer/commission-fee/update"/>
            <jsp:param name="customerTypeId" value="${param.customerTypeId}"/>
        </jsp:include>
        <jsp:include page="../include_component/add_service_commission_fee_modal.jsp">
            <jsp:param name="title" value="Thêm biểu phí cho dịch vụ"/>
            <jsp:param name="actionURI" value="/customer/commission-fee/update"/>
            <jsp:param name="customerTypeId" value="${param.customerTypeId}"/>
        </jsp:include>
        <spring:message code="system.commission.fee.popup.reset.title" var="resetFeeTitle"/>
        <jsp:include page="../include_component/reset_service_commission_fee_modal.jsp">
            <jsp:param name="title" value="${resetFeeTitle}"/>
            <jsp:param name="actionURI" value="/customer/commission-fee/delete"/>
        </jsp:include>
    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_component/search_form_commons.jsp">
    <jsp:param name="selServiceType" value="true"/>
</jsp:include>

<jsp:include page="service_tree_lib.jsp"/>
</body>


</html>
