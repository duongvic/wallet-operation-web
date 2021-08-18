<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 6/22/2021
  Time: 5:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../../include_page/taglibs.jsp" %>


<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.recon.profile"/></title>
    <jsp:include page="../../../include_page/head.jsp">
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <jsp:include page="../../../include_page/js_merchant.jsp">
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <style>
        .select2-container .select2-search--inline .select2-search__field {
            box-sizing: border-box;
            border: none;
            font-size: 100%;
            margin-top: -2px;
            padding: 0;
        }
    </style>
</head>

<body>
<section class="body">
    <jsp:include page="../../../include_page/header.jsp"/>
    <jsp:include page="../../../include_component/constant_application.jsp"/>

    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../../../include_page/navigation.jsp">
            <jsp:param value="reconciliation-profile" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><a><span class=""><spring:message
                                        code="service.exportcard.list.navigate.service"/></span></a>
                                </li>
                                <li><a><span class=""><spring:message
                                        code="menu.left.reconciliation"/></span></a></li>
                                <li><a href="${contextPath}/wallet/reconcilation/profile/list"><span class="nav-active"><spring:message
                                        code="label.recon.profile"/></span></a>
                                </li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">
                    <div class="tabs">

                        <div class="tab-content pl-none pr-none">
                            <div id="tab1" class="tab-pane active">
                                <section class="panel search_payment panel-default">
                                    <div class="panel-body pt-none">
                                        <spring:message code="wallet.balance.searchText"
                                                        var="placeholder"/>
                                        <form action="" method="GET" id="tbl-filter">
                                            <input type="hidden" name="${_csrf.parameterName}"
                                                   value="${_csrf.token}"/>
                                            <div class="form-group ml-none mr-none">
                                                <div class="input-group input-group-icon">
                                        <span class="input-group-addon"><span class="icon"
                                                                              style="opacity: 0.4"><i
                                                class="fa fa-search-minus"></i></span></span>
                                                    <input type="text" id="fullTextSearch"
                                                           name="fullTextSearch"
                                                           class="form-control"
                                                           placeholder="${placeholder}"
                                                           value="${param.fullTextSearch }"/>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-lg-12 col-md-12 col-sm-12">
                                                <div class="row">
                                                    <%--select user type--%>
                                                    <div class="col-md-3 col-lg-3">
                                                        <div class="form-group">
                                                            <div class="row">
                                                                <div class="col-md-4">
                                                                    <label class="control-label nowrap"
                                                                           for="customerType"
                                                                           style="min-width: 100px"><spring:message
                                                                            code="select.customerType"/> </label>
                                                                </div>
                                                                <div class="col-md-8">
                                                                    <select data-plugin-selectTwo
                                                                            class="form-control"
                                                                            id="customerType"
                                                                            name="customerType">
                                                                        <option value=""><spring:message
                                                                                code="label.please.select"/></option>
                                                                        <option value="MERCHANT">MERCHANT</option>
                                                                        <option value="AGENT">AGENT</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3 col-lg-3">
                                                        <div class="form-group">
                                                            <div class="row">
                                                                <div class="col-md-4">
                                                                    <label class="control-label nowrap"
                                                                           style="min-width: 100px">Loại dịch vụ</label>
                                                                </div>
                                                                <div class="col-md-8">
                                                                    <select multiple="multiple"
                                                                            id="serviceTypes"
                                                                            name="serviceTypes">
                                                                        <option></option>
                                                                        <option value="all"><spring:message code="label.all"/></option>
                                                                        <c:forEach items="${serviceTypes}"
                                                                                   var="serviceType">
                                                                            <option value="${serviceType.key}"
                                                                                    <c:forEach var='value'
                                                                                               items='${paramValues.serviceTypes}'>
                                                                                        <c:if test="${value eq serviceType.key}">
                                                                                            selected
                                                                                        </c:if>
                                                                                    </c:forEach>
                                                                            ><spring:message
                                                                                    code="${serviceType.value}"/></option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3 col-lg-3">
                                                        <div class="form-group">
                                                            <div class="row">
                                                                <div class="col-md-4">
                                                                    <label class="control-label nowrap"
                                                                           style="min-width: 100px">Chính sách</label>
                                                                </div>
                                                                <div class="col-md-8">
                                                                    <select multiple="multiple"
                                                                            id="paymentPolicies"
                                                                            name="paymentPolicies">
                                                                        <option></option>
                                                                        <option value="all"><spring:message code="label.all"/></option>
                                                                        <option value="PAYMENT_POLICY_1_1"
                                                                                <c:forEach var='value'
                                                                                           items='${paramValues.paymentPolicies}'>
                                                                                    <c:if test="${value eq 'PAYMENT_POLICY_1_1'}">
                                                                                        selected
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                        >
                                                                            Chính sách 1 + 1
                                                                        </option>
                                                                        <option value="PAYMENT_POLICY_7_1"
                                                                                <c:forEach var='value'
                                                                                           items='${paramValues.paymentPolicies}'>
                                                                                    <c:if test="${value eq 'PAYMENT_POLICY_7_1'}">
                                                                                        selected
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                        >
                                                                            Chính sách 7 + 1
                                                                        </option>
                                                                        <option value="PAYMENT_POLICY_30_1"
                                                                                <c:forEach var='value'
                                                                                           items='${paramValues.paymentPolicies}'>
                                                                                    <c:if test="${value eq 'PAYMENT_POLICY_30_1'}">
                                                                                        selected
                                                                                    </c:if>
                                                                                </c:forEach>>
                                                                            Chính sách 30 + 1
                                                                        </option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                            <div class="form-inline">
                                                <div class='pull-right form-responsive bt-plt'>

                                                    <button type="submit" class="btn btn-primary"><i
                                                            class="fa fa-search"></i>&nbsp;<spring:message
                                                            code="system.service.list.search.btn.search"/>
                                                </div>

                                            </div>
                                            <div class="clearfix"></div>
                                        </form>

                                        <section class="panel search_payment panel-default">
                                            <div class="panel-body">
                                                <div class="clearfix"></div>
                                                <div class="pull-left mt-sm"
                                                     style="line-height: 30px;">
                                                </div>
                                                <div class="clr"></div>
                                                <div class="table-responsive">
                                                    <table class="table table-bordered table-responsive table-striped mb-none">
                                                        <thead>
                                                        <tr>
                                                            <th scope="col">STT</th>
                                                            <th scope="col">CIF</th>
                                                            <th scope="col" style="text-transform: uppercase">
                                                                <spring:message
                                                                        code="reversal.label.customer.name"/></th>
                                                            <th scope="col" style="text-transform: uppercase">
                                                                <spring:message code="label.contract.type"/></th>
                                                            <th scope="col" style="text-transform: uppercase">Dịch vụ TT</th>
                                                            <th scope="col" style="text-transform: uppercase">
                                                                <spring:message code="label.policy"/></th>
                                                            <th scope="col" style="text-transform: uppercase">
                                                                <spring:message code="label.contract.no"/></th>
                                                            <th scope="col" style="text-transform: uppercase"><spring:message code="label.sign.date"/></th>
                                                            <th scope="col" style="text-transform: uppercase"><spring:message code="label.expired.contract.date"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach var="profileDTO" items="${profileDTOS}"
                                                                   varStatus="loop">
                                                            <tr
                                                                <c:choose>
                                                                    <c:when test="${profileDTO.getDayBetween() >= 0 && profileDTO.getDayBetween() < 30}">
                                                                        style="background-color: yellow"
                                                                    </c:when>
                                                                    <c:when test="${profileDTO.getDayBetween() < 0}">
                                                                        style="background-color: tomato"
                                                                    </c:when>
                                                                </c:choose>
                                                            >
                                                                <td>${loop.index + 1}</td>
                                                                <td><a class="link-active"
                                                                       href="${contextPath}/wallet/reconcilation/profile/detail/${profileDTO.customerId}">${profileDTO.cif}</a>
                                                                </td>
                                                                <td>${profileDTO.customerName}</td>
                                                                <td>${profileDTO.contractType}</td>
                                                                <td class="center">
                                                                    <c:choose>
                                                                        <c:when test="${fn:contains(profileDTO.serviceTypes, '|EPIN|')}">
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
                                                                        <c:when test="${fn:contains(profileDTO.serviceTypes, '|EXPORT_EPIN|')}">
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
                                                                        <c:when test="${fn:contains(profileDTO.serviceTypes, '|PHONE_TOPUP|')}">
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
                                                                        <c:when test="${fn:contains(profileDTO.serviceTypes, '|BILL_PAYMENT|')}">
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
                                                                </td>
                                                                <td>
                                                                    <ul>
                                                                        <c:forEach items="${profileDTO.profilePaymentPolicies}" var="profilePaymentPolicy">
                                                                                <li>${profilePaymentPolicy.paymentPolicy}&nbsp;
                                                                                    <c:if test="${profilePaymentPolicy.bolOfficial}">
                                                                                        <i style="color: green" class="fa fa-check"></i>
                                                                                    </c:if>
                                                                                </li>
                                                                        </c:forEach>
                                                                    </ul>
                                                                </td>
                                                                <td>${profileDTO.contractNo}</td>
                                                                <td><fmt:formatDate value="${profileDTO.contractDate}"
                                                                                    pattern="dd/MM/yyyy"/></td>
                                                                <td><fmt:formatDate value="${profileDTO.expiredDate}"
                                                                                    pattern="dd/MM/yyyy"/></td>
                                                                    <%--<c:forEach var="profilePaymentPolicie" items="${profileDTO.profilePaymentPolicies}">--%>
                                                                    <%--<c:if test="${profilePaymentPolicie.bolOfficial eq true}">--%>
                                                                    <%--<td>${profilePaymentPolicie.paymentPolicy}</td>--%>
                                                                    <%--</c:if>--%>
                                                                    <%--</c:forEach>--%>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </section>
                                    </div>
                                </section>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end: page -->
        </section>
        <jsp:include page="../../../include_page/footer.jsp"/>

    </div>
</section>
<jsp:include page="../../../include_page/js_footer.jsp"/>
<jsp:include page="../../../include_component/search_form_commons.jsp"/>
<script type="text/javascript"
        src="<c:url value='/assets/javascripts/settings-custom.js'/>"></script>
<script>
    $(document).ready(function () {
        $('#paymentPolicies').select2({
            width: "100%",
            dropdownAutoWidth: true,
            placeholder: ' <spring:message code="label.please.select"/>',
        });

        $('#serviceTypes').select2({
            width: "100%",
            dropdownAutoWidth: true,
            placeholder: ' <spring:message code="label.please.select"/>',
        });

    });
</script>
</body>
</html>

