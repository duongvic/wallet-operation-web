<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 6/24/2021
  Time: 10:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
    <!-- Basic -->
    <meta charset="UTF-8"/>
    <title><spring:message code="label.recon.profile"/></title>
    <jsp:include page="../../../include_page/head.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <jsp:include page="../../../include_page/js_service.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
        <jsp:param name="dateLib" value="true"/>
    </jsp:include>
</head>

<body>
<section class="body">
    <!--        ///////   header ////////-->
    <jsp:include page="../../../include_page/header.jsp"/>
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
                                <li><a href="${contextPath}/wallet/reconcilation/profile/list"><span class=""><spring:message
                                        code="menu.left.reconciliation"/></span></a></li>
                                <li><a href="${contextPath}/wallet/reconcilation/profile/detail/${profileDTO.customerId}"><span class="nav-active"><spring:message
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

                    <div class="form-inline hidden">
                        <div class="pull-left h4 mb-md mt-md panel-title"><spring:message
                                code="system.account.info.page.title"/></div>
                    </div>

                    <section class="panel search_payment panel-default">
                        <div class="panel-body pt-none">
                            <div class="tab-content">
                                <div id="tab-list-customer" class="tab-pane active">
                                    <!-- form search -->
                                    <form class="panel panel-default">
                                        <!-- persional more -->
                                        <div class="panel-title">
                                            <h4 style="margin-bottom: 1.5rem"><spring:message
                                                    code="system.account.info.page.title"/></h4>
                                        </div>
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-6 col-lg-6 col-sm-12">
                                                        <label class="col-md-4 col-lg-4  col-sm-4 control-label nowrap"><spring:message
                                                                code="lable.last.first.name"/></label>
                                                        <div class="col-md-8 col-lg-8  col-sm-8 mt-xs">
                                                            <span>${profileDTO.customerName}</span>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 col-lg-6 col-sm-12">
                                                        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">
                                                            <spring:message code="label.contract.no"/>
                                                        </label>
                                                        <div class="col-md-7 col-lg-8 col-sm-8 mt-xs">
                                                            <span>${profileDTO.contractNo}</span>
                                                            <%--text here--%>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="">
                                                    <div class="col-md-6 col-lg-6 col-sm-12  form-group">
                                                        <label id="email-title"
                                                               class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">
                                                            CIF
                                                        </label>
                                                        <div class="col-md-8 col-lg-8 col-sm-8 mt-xs">
                                                            <span>${profileDTO.cif}</span>
                                                        </div>

                                                    </div>
                                                    <div class="col-md-6 col-lg-6 col-sm-12  form-group">
                                                        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">
                                                            <spring:message code="label.contract.type"/>
                                                        </label>
                                                        <div class="col-md-7 col-lg-8 col-sm-8 mt-xs">
                                                            <span>${profileDTO.contractType}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-6 col-lg-6 col-sm-12">
                                                        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">Email</label>
                                                        <div class="col-md-8 col-lg-8 col-sm-8 mt-xs">
                                                            <span>${profileDTO.email}</span>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6 col-lg-6 col-sm-12">
                                                        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">
                                                            <spring:message code="label.sign.date"/>
                                                        </label>
                                                        <div class="col-md-7 col-lg-8 col-sm-8 mt-xs">
                                                            <span><fmt:formatDate value="${profileDTO.contractDate}"
                                                                                  pattern="dd/MM/yyyy"/></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-6 col-lg-6 col-sm-12">
                                                        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">
                                                            <spring:message
                                                                    code="transaction.export.detail.summary.row.phone"/>
                                                        </label>
                                                        <div class="col-md-8 col-lg-8 col-sm-8 mt-xs">
                                                            <span>${profileDTO.phone}</span>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6 col-lg-6 col-sm-12">
                                                        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">
                                                            <spring:message code="label.expired.contract.date"/>
                                                        </label>
                                                        <div class="col-md-7 col-lg-8 col-sm-8">
                                                            <span><fmt:formatDate value="${profileDTO.expiredDate}"
                                                                                  pattern="dd/MM/yyyy"/></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-6 col-lg-6 col-sm-12">
                                                        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">
                                                            <spring:message code="transaction.api.detail.servicetype"/>
                                                        </label>
                                                        <div class="col-md-8 col-lg-8 col-sm-8">
                                                            <ul class="clearfix checkbox_form">
                                                                <li>
                                                                    <div
                                                                    <%--class="checkbox-custom checkbox-default"--%>
                                                                    >
                                                                        <%--<input type="checkbox" id="checkboxExample2" name="serviceTypes" class="check_1"--%>
                                                                        <%--value="PHONE_TOPUP" ${fn:contains(profileDTO.serviceTypes, '|PHONE_TOPUP|') ? 'checked':'' } disabled>--%>
                                                                        <label>PHONE_TOPUP</label>
                                                                            <c:if test="${fn:contains(profileDTO.serviceTypes, '|PHONE_TOPUP|')}">
                                                                                <i style="color: green"
                                                                                   class="fa fa-check"></i>
                                                                            </c:if>
                                                                    </div>
                                                                </li>
                                                                <li>
                                                                    <div
                                                                    <%--class="checkbox-custom checkbox-default"--%>
                                                                    >
                                                                        <%--<input type="checkbox" id="checkboxExample3" name="serviceTypes" class="check_1"--%>
                                                                        <%--value="EPIN" ${fn:contains(profileDTO.serviceTypes, '|EPIN|') ? 'checked':'' } disabled>--%>
                                                                        <label>EPIN</label>
                                                                            <c:if test="${fn:contains(profileDTO.serviceTypes, '|EPIN|')}">
                                                                            <i style="color: green"
                                                                               class="fa fa-check"></i>
                                                                        </c:if>
                                                                    </div>
                                                                </li>
                                                                <li>
                                                                    <div
                                                                    <%--class="checkbox-custom checkbox-default"--%>
                                                                    >
                                                                        <%--<input type="checkbox" id="checkboxExample4" name="serviceTypes" class="check_1"--%>
                                                                        <%--value="EXPORT_EPIN" ${fn:contains(profileDTO.serviceTypes, '|EXPORT_EPIN|') ? 'checked':'' } disabled>--%>
                                                                        <label>EXPORT_EPIN</label>
                                                                            <c:if test="${fn:contains(profileDTO.serviceTypes, '|EXPORT_EPIN|')}">
                                                                                <i style="color: green"
                                                                                   class="fa fa-check"></i>
                                                                            </c:if>
                                                                    </div>
                                                                </li>
                                                                <li>
                                                                    <div
                                                                    <%--class="checkbox-custom checkbox-default"--%>
                                                                    >
                                                                        <%--<input type="checkbox" id="checkboxExample5" name="serviceTypes" class="check_1"--%>
                                                                        <%--value="BILL_PAYMENT" ${fn:contains(profileDTO.serviceTypes, '|BILL_PAYMENT|') ? 'checked':'' } disabled>--%>
                                                                        <label>BILL_PAYMENT</label>
                                                                        <c:if test="${fn:contains(profileDTO.serviceTypes, '|BILL_PAYMENT|')}">
                                                                            <i style="color: green"
                                                                               class="fa fa-check"></i>
                                                                        </c:if>
                                                                    </div>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <%--end personal info--%>

                                        <%--chính sách--%>
                                        <div class="panel-title">
                                            <h4 style="margin-bottom: 1.5rem"><spring:message code="label.policy"/> đối
                                                soát
                                            </h4>
                                        </div>
                                        <div class="panel-body">

                                            <c:forEach items="${profileDTO.profilePaymentPolicies}"
                                                       var="profilePaymentPolicie">
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-12 col-lg-12 col-sm-12">
                                                            <label class="col-md-3 col-lg-3 col-sm-3 control-label">
                                                                    ${profilePaymentPolicie.paymentPolicy}
                                                                        <c:if test="${profilePaymentPolicie.bolOfficial}">
                                                                            <i style="color: green" class="fa fa-check"></i>
                                                                        </c:if>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                            <%--<table class="table table-bordered table-responsive table-striped mb-none">--%>
                                            <%--<thead>--%>
                                            <%--<tr>--%>
                                            <%--<th scope="col">Chính sách</th>--%>
                                            <%--<th scope="col" class="center">Trạng thái</th>--%>
                                            <%--</tr>--%>
                                            <%--</thead>--%>
                                            <%--<tbody>--%>
                                            <%--<c:forEach items="${profileDTO.profilePaymentPolicies}"--%>
                                            <%--var="profilePaymentPolicie">--%>
                                            <%--<tr>--%>
                                            <%--<td>${profilePaymentPolicie.paymentPolicy}</td>--%>
                                            <%--<td class="center">--%>
                                            <%--<label class="switch" style="margin: 0 3px;"--%>
                                            <%--data-placement="top">--%>
                                            <%--<input type="checkbox" ${profilePaymentPolicie.bolOfficial eq true ? 'checked' : ''} disabled>--%>
                                            <%--<span class="slider round"></span>--%>
                                            <%--</label>--%>
                                            <%--</td>--%>
                                            <%--</tr>--%>
                                            <%--</c:forEach>--%>
                                            <%--</tbody>--%>
                                            <%--</table>--%>
                                        </div>

                                        <div class="panel-title">
                                            <h4 style="margin-bottom: 1.5rem">Commission Fee</h4>
                                        </div>
                                        <div class="panel-body">
                                            <a class="link-active" target="_blank"
                                               href="${contextPath}/customer/manage/details/${profileDTO.customerId}">
                                                <spring:message code="transaction.table.action.viewdetail"/>
                                            </a>
                                        </div>
                                    </form>
                                    <!-- end form search -->
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </section>
        <jsp:include page="../../../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../../../include_page/js_footer.jsp"/>

<script type="text/javascript"
        src="<c:url value='/assets/javascripts/settings-custom.js'/>"></script>
<script src="${contextPath}/assets/jquery.inputmask.bundle.min.js"></script>

<script type="text/javascript">
    (function ($) {
        'use strict';
        $(document).ready(function () {
        });
    }(jQuery));
</script>

</body>

</html>

