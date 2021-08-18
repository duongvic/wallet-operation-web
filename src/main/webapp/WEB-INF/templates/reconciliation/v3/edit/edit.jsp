<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 6/3/2021
  Time: 10:35 AM
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
    <title><spring:message code="menu.left.reconciliation"/></title>
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
    <!-- /////// header ////////-->
    <jsp:include page="../../../include_page/header.jsp"/>
    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../../../include_page/navigation.jsp">
            <jsp:param value="reconciliation" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
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
                                <li>
                                    <a href="${contextPath}/wallet/reconcilation/list?menu=ser&month=<%=LocalDate.now().getMonth().getValue()%>&year=<%=new Date().getYear() + 1900%>"><span
                                            class=""><spring:message
                                            code="menu.left.reconciliation"/></span></a></li>
                                <li><span class="nav-active"><spring:message
                                        code="label.recon.by.payment.service"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>
            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">

                    <div class="form-inline hidden">
                        <div class="pull-left h4 mb-md mt-md panel-title">
                            <spring:message code="menu.left.reconciliation"/>
                        </div>
                        <div class="pull-right form-responsive mb-10">
                        </div>
                    </div>

                    <section>
                        <div>
                            <!-- form search -->
                            <form method="POST" class="panel panel-default form-horizontal">
                                <section class="panel panel-default">
                                    <div class="panel-title pl-none">
                                        <h4 class="fl"><spring:message code="transaction.api.detail.info"/></h4>
                                        <ul class="panel-tools fl tool-filter">
                                            <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a>
                                            </li>
                                        </ul>
                                        <div class="clr"></div>
                                    </div>

                                    <div class="panel-body m-md">
                                        <div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">ID</label>
                                                <div class="col-sm-4 mt-xs">
                                                    <p>${reconcil.id}</p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><spring:message
                                                        code="label.title"/></label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control" id="title"
                                                           placeholder="title" value="${reconcil.title}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="description"
                                                       class="col-sm-2 control-label"><spring:message
                                                        code="label.description"/></label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control" id="description"
                                                           placeholder="description" value="${reconcil.description}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><spring:message
                                                        code="label.customer.type"/></label>
                                                <div class="col-sm-4 mt-xs">
                                                    <p>${reconcil.customerType}</p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Customer ID</label>
                                                <div class="col-sm-4 mt-xs">
                                                    <p>${reconcil.customerId}</p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><spring:message
                                                        code="reversal.label.customer.name"/></label>
                                                <div class="col-sm-4 mt-xs">
                                                    <p>${reconcil.customerName}</p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><spring:message
                                                        code="label.policy"/></label>
                                                <div class="col-sm-4 mt-xs">
                                                    <p>${reconcil.paymentPolicy}</p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><spring:message
                                                        code="label.cycle"/></label>
                                                <div class="col-sm-4">
                                                    <%--<p>${reconcil.cycle}</p>--%>
                                                    <select data-plugin-selectTwo class="form-control" name="cycle"
                                                            id="cycle">
                                                        <option></option>
                                                        <option value="0"><spring:message code="label.all"/></option>
                                                        <c:forEach var="i" begin="1" end="31">
                                                            <option value="${i}" ${reconcil.cycle eq i ? 'selected': ''}>
                                                                Kỳ ${i}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><spring:message
                                                        code="label.year"/></label>
                                                <div class="col-sm-4">
                                                    <%--<p>${reconcil.year}</p>--%>
                                                    <select data-plugin-selectTwo class="form-control" name="year"
                                                            id="year">
                                                        <option></option>
                                                        <option value="0"><spring:message code="label.all"/></option>
                                                        <c:forEach var="i" begin="${currentYear - 2}"
                                                                   end="${currentYear + 2}">
                                                            <option value="${i}" ${reconcil.year eq i ? 'selected': ''}>${i}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><spring:message
                                                        code="label.month"/></label>
                                                <div class="col-sm-4">
                                                    <%--<p>${reconcil.month}</p>--%>
                                                    <select data-plugin-selectTwo class="form-control" name="month"
                                                            id="month">
                                                        <option></option>
                                                        <option value="0">Tất cả</option>
                                                        <c:forEach items="${months}" var="month">
                                                            <option value="${month}" ${reconcil.month eq month ? 'selected': ''}>
                                                                Tháng ${month}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><spring:message
                                                        code="label.status"/></label>
                                                <label class="col-sm-4 mt-xs" style="font-weight: normal">
                                                    <spring:message code="label.${reconcil.stage}"/>
                                                </label>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Official</label>
                                                <div class="col-sm-4">
                                                    <select data-plugin-selectTwo class="form-control"
                                                            name="bolOfficial" id="bolOfficial">
                                                        <option value="true" ${reconcil.bolOfficial eq true ? 'selected' : ''}>
                                                            Official
                                                        </option>
                                                        <option value="false" ${reconcil.bolOfficial eq false ? 'selected' : ''}>
                                                            Ongoing
                                                        </option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group" style="float: right;">
                                                <div class="">
                                                    <button type="button" class="btn btn-primary"
                                                            onclick="updateReconcilation()">
                                                        <spring:message code="button.update"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </form>
                            <%-----------------------------------------------%>
                            <ul id="TabList" class="nav nav-tabs tabs">
                                <li onclick="changeTab('EPIN')" id="EPIN" data-id="EPIN"><a>EPIN</a></li>
                                <li onclick="changeTab('EXPORT_EPIN')" id="EXPORT_EPIN" data-id="EXPORT_EPIN"><a>EXPORT_EPIN</a></li>
                                <li onclick="changeTab('PHONE_TOPUP')" id="PHONE_TOPUP" data-id="PHONE_TOPUP"><a>PHONE_TOPUP</a></li>
                                <li onclick="changeTab('BILL_PAYMENT')" id="BILL_PAYMENT" data-id="BILL_PAYMENT"><a>BILL_PAYMENT</a></li>
                                <li onclick="changeTab('OTHER')" id="OTHER" data-id="OTHER"><a>KHÁC</a></li>
                            </ul>
                            <%--EPIN--%>
                            <div class="mt-lg hidden" id="EPINtbl">

                                <div style="display: flex; justify-content: space-between" class="mb-xs">
                                    <div>
                                        <h3> EPIN</h3>
                                    </div>

                                    <div>
                                        <button class="btn btn-primary" type="button"
                                                onclick="openAddModal('EPIN')"><spring:message code="common.btn.add"/>
                                        </button>
                                    </div>
                                </div>

                                <div class="table-responsive qlsp no-per-page">
                                    <table class="table table-bordered table-striped mb-small mt-none">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th><spring:message code="label.action"/></th>
                                            <th><spring:message code="label.customer.type"/></th>
                                            <th>CustomerID</th>
                                            <th><spring:message code="label.service.type"/></th>
                                            <th>ServiceID</th>
                                            <th><spring:message
                                                    code="system.service.detail.summary.row.servicecode"/></th>
                                            <th><spring:message
                                                    code="system.service.detail.summary.row.servicename"/></th>
                                            <th><spring:message
                                                    code="transaction.export.detail.summary.row.quantity"/></th>
                                            <th><spring:message code="label.price"/></th>
                                            <th>UnitFeeFixedAmount</th>
                                            <th>UnitFeePercentRate</th>
                                            <th>UnitCommissionFixedAmount</th>
                                            <th>UnitCommissionPercentRate</th>
                                            <th><spring:message code="label.num.of.trans"/></th>
                                            <th>SumOfRequestAmount</th>
                                            <th>SumOfFeeAmount</th>
                                            <th>SumOfCommissionAmount</th>
                                            <th>SumOfCashbackAmount</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${reconcil.elementGroupByServiceType}" var="entry">
                                            <c:if test="${entry.key eq 'EPIN'}">
                                                <c:forEach items="${entry.value}" var="reconciliationElementDTO"
                                                           varStatus="var2">
                                                    <tr>
                                                        <td>${reconciliationElementDTO.id}</td>
                                                        <td class="center">
                                                            <a title="Chỉnh sửa thông tin"
                                                               onclick="openUpdateModal(${reconciliationElementDTO.id}, '${entry.key}')">
                                                                <i class="fa fa-edit" style="color: #2582c4"
                                                                   aria-hidden="true"></i>
                                                            </a>
                                                            <a title="Xóa"
                                                               onclick="deleteReconcilationElement(${reconciliationElementDTO.id})">
                                                                <i class="fa fa-trash" style="color: red"
                                                                   aria-hidden="true"></i>
                                                            </a>
                                                        </td>
                                                        <td>${reconciliationElementDTO.customerType}</td>
                                                        <td>${reconciliationElementDTO.customerId}</td>
                                                        <td>${reconciliationElementDTO.serviceType}</td>
                                                        <td>${reconciliationElementDTO.serviceId}</td>
                                                        <td>${reconciliationElementDTO.serviceCode}</td>
                                                        <td>${reconciliationElementDTO.serviceShortName}</td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.quantity}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitPrice}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitFeeFixedAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitFeePercentRate}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitCommissionFixedAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitCommissionPercentRate}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.numOfTrans}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfRequestAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfFeeAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfCommissionAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfCashbackAmount}"/>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <%--EXPORT_EPIN--%>
                            <div class="mt-lg hidden" id="EXPORT_EPINtbl">

                                <div style="display: flex; justify-content: space-between" class="mb-xs">
                                    <div>
                                        <h3> EXPORT_EPIN</h3>
                                    </div>

                                    <div>
                                        <button class="btn btn-primary" type="button"
                                                onclick="openAddModal('EXPORT_EPIN')"><spring:message
                                                code="common.btn.add"/>
                                        </button>
                                    </div>
                                </div>

                                <div class="table-responsive qlsp no-per-page">
                                    <table class="table table-bordered table-striped mb-small mt-none">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th><spring:message code="label.action"/></th>
                                            <th><spring:message code="label.customer.type"/></th>
                                            <th>CustomerID</th>
                                            <th><spring:message code="label.service.type"/></th>
                                            <th>ServiceID</th>
                                            <th><spring:message
                                                    code="system.service.detail.summary.row.servicecode"/></th>
                                            <th><spring:message
                                                    code="system.service.detail.summary.row.servicename"/></th>
                                            <th><spring:message
                                                    code="transaction.export.detail.summary.row.quantity"/></th>
                                            <th><spring:message code="label.price"/></th>
                                            <th>UnitFeeFixedAmount</th>
                                            <th>UnitFeePercentRate</th>
                                            <th>UnitCommissionFixedAmount</th>
                                            <th>UnitCommissionPercentRate</th>
                                            <th><spring:message code="label.num.of.trans"/></th>
                                            <th>SumOfRequestAmount</th>
                                            <th>SumOfFeeAmount</th>
                                            <th>SumOfCommissionAmount</th>
                                            <th>SumOfCashbackAmount</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${reconcil.elementGroupByServiceType}" var="entry">
                                            <c:if test="${entry.key eq 'EXPORT_EPIN'}">
                                                <c:forEach items="${entry.value}" var="reconciliationElementDTO"
                                                           varStatus="var2">
                                                    <tr>
                                                        <td>${reconciliationElementDTO.id}</td>
                                                        <td class="center">
                                                            <a title="Chỉnh sửa thông tin"
                                                               onclick="openUpdateModal(${reconciliationElementDTO.id}, '${entry.key}')">
                                                                <i class="fa fa-edit" style="color: #2582c4"
                                                                   aria-hidden="true"></i>
                                                            </a>
                                                            <a title="Xóa"
                                                               onclick="deleteReconcilationElement(${reconciliationElementDTO.id})">
                                                                <i class="fa fa-trash" style="color: red"
                                                                   aria-hidden="true"></i>
                                                            </a>
                                                        </td>
                                                        <td>${reconciliationElementDTO.customerType}</td>
                                                        <td>${reconciliationElementDTO.customerId}</td>
                                                        <td>${reconciliationElementDTO.serviceType}</td>
                                                        <td>${reconciliationElementDTO.serviceId}</td>
                                                        <td>${reconciliationElementDTO.serviceCode}</td>
                                                        <td>${reconciliationElementDTO.serviceShortName}</td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.quantity}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitPrice}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitFeeFixedAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitFeePercentRate}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitCommissionFixedAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitCommissionPercentRate}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.numOfTrans}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfRequestAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfFeeAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfCommissionAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfCashbackAmount}"/>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <%--PHONE_TOPUP--%>
                            <div class="mt-lg hidden" id="PHONE_TOPUPtbl">

                                <div style="display: flex; justify-content: space-between" class="mb-xs">
                                    <div>
                                        <h3> PHONE_TOPUP</h3>
                                    </div>

                                    <div>
                                        <button class="btn btn-primary" type="button"
                                                onclick="openAddModal('PHONE_TOPUP')"><spring:message
                                                code="common.btn.add"/>
                                        </button>
                                    </div>
                                </div>

                                <div class="table-responsive qlsp no-per-page">
                                    <table class="table table-bordered table-striped mb-small mt-none">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th><spring:message code="label.action"/></th>
                                            <th><spring:message code="label.customer.type"/></th>
                                            <th>CustomerID</th>
                                            <th><spring:message code="label.service.type"/></th>
                                            <th>ServiceID</th>
                                            <th><spring:message
                                                    code="system.service.detail.summary.row.servicecode"/></th>
                                            <th><spring:message
                                                    code="system.service.detail.summary.row.servicename"/></th>
                                            <th><spring:message
                                                    code="transaction.export.detail.summary.row.quantity"/></th>
                                            <th><spring:message code="label.price"/></th>
                                            <th>UnitFeeFixedAmount</th>
                                            <th>UnitFeePercentRate</th>
                                            <th>UnitCommissionFixedAmount</th>
                                            <th>UnitCommissionPercentRate</th>
                                            <th><spring:message code="label.num.of.trans"/></th>
                                            <th>SumOfRequestAmount</th>
                                            <th>SumOfFeeAmount</th>
                                            <th>SumOfCommissionAmount</th>
                                            <th>SumOfCashbackAmount</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${reconcil.elementGroupByServiceType}" var="entry">
                                            <c:if test="${entry.key eq 'PHONE_TOPUP'}">
                                                <c:forEach items="${entry.value}" var="reconciliationElementDTO"
                                                           varStatus="var2">
                                                    <tr>
                                                        <td>${reconciliationElementDTO.id}</td>
                                                        <td class="center">
                                                            <a title="Chỉnh sửa thông tin"
                                                               onclick="openUpdateModal(${reconciliationElementDTO.id}, '${entry.key}')">
                                                                <i class="fa fa-edit" style="color: #2582c4"
                                                                   aria-hidden="true"></i>
                                                            </a>
                                                            <a title="Xóa"
                                                               onclick="deleteReconcilationElement(${reconciliationElementDTO.id})">
                                                                <i class="fa fa-trash" style="color: red"
                                                                   aria-hidden="true"></i>
                                                            </a>
                                                        </td>
                                                        <td>${reconciliationElementDTO.customerType}</td>
                                                        <td>${reconciliationElementDTO.customerId}</td>
                                                        <td>${reconciliationElementDTO.serviceType}</td>
                                                        <td>${reconciliationElementDTO.serviceId}</td>
                                                        <td>${reconciliationElementDTO.serviceCode}</td>
                                                        <td>${reconciliationElementDTO.serviceShortName}</td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.quantity}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitPrice}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitFeeFixedAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitFeePercentRate}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitCommissionFixedAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitCommissionPercentRate}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.numOfTrans}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfRequestAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfFeeAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfCommissionAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfCashbackAmount}"/>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- BILL_PAYMENT -->
                            <div class="mt-lg hidden" id="BILL_PAYMENTtbl">

                                <div style="display: flex; justify-content: space-between" class="mb-xs">
                                    <div>
                                        <h3> BILL_PAYMENT</h3>
                                    </div>

                                    <div>
                                        <button class="btn btn-primary" type="button"
                                                onclick="openAddModal('BILL_PAYMENT')"><spring:message
                                                code="common.btn.add"/>
                                        </button>
                                    </div>
                                </div>

                                <div class="table-responsive qlsp no-per-page">
                                    <table class="table table-bordered table-striped mb-small mt-none">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th><spring:message code="label.action"/></th>
                                            <th><spring:message code="label.customer.type"/></th>
                                            <th>CustomerID</th>
                                            <th><spring:message code="label.service.type"/></th>
                                            <th>ServiceID</th>
                                            <th><spring:message
                                                    code="system.service.detail.summary.row.servicecode"/></th>
                                            <th><spring:message
                                                    code="system.service.detail.summary.row.servicename"/></th>
                                            <th><spring:message
                                                    code="transaction.export.detail.summary.row.quantity"/></th>
                                            <th><spring:message code="label.price"/></th>
                                            <th>UnitFeeFixedAmount</th>
                                            <th>UnitFeePercentRate</th>
                                            <th>UnitCommissionFixedAmount</th>
                                            <th>UnitCommissionPercentRate</th>
                                            <th><spring:message code="label.num.of.trans"/></th>
                                            <th>SumOfRequestAmount</th>
                                            <th>SumOfFeeAmount</th>
                                            <th>SumOfCommissionAmount</th>
                                            <th>SumOfCashbackAmount</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${reconcil.elementGroupByServiceType}" var="entry">
                                            <c:if test="${entry.key eq 'BILL_PAYMENT'}">
                                                <c:forEach items="${entry.value}" var="reconciliationElementDTO"
                                                           varStatus="var2">
                                                    <tr>
                                                        <td>${reconciliationElementDTO.id}</td>
                                                        <td class="center">
                                                            <a title="Chỉnh sửa thông tin"
                                                               onclick="openUpdateModal(${reconciliationElementDTO.id}, '${entry.key}')">
                                                                <i class="fa fa-edit" style="color: #2582c4"
                                                                   aria-hidden="true"></i>
                                                            </a>
                                                            <a title="Xóa"
                                                               onclick="deleteReconcilationElement(${reconciliationElementDTO.id})">
                                                                <i class="fa fa-trash" style="color: red"
                                                                   aria-hidden="true"></i>
                                                            </a>
                                                        </td>
                                                        <td>${reconciliationElementDTO.customerType}</td>
                                                        <td>${reconciliationElementDTO.customerId}</td>
                                                        <td>${reconciliationElementDTO.serviceType}</td>
                                                        <td>${reconciliationElementDTO.serviceId}</td>
                                                        <td>${reconciliationElementDTO.serviceCode}</td>
                                                        <td>${reconciliationElementDTO.serviceName}</td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.quantity}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitPrice}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitFeeFixedAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitFeePercentRate}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitCommissionFixedAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.unitCommissionPercentRate}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.numOfTrans}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfRequestAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfFeeAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfCommissionAmount}"/>
                                                        </td>
                                                        <td class="right">
                                                            <fmt:formatNumber type="number"
                                                                              maxFractionDigits="3"
                                                                              value="${reconciliationElementDTO.sumOfCashbackAmount}"/>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <%--OTHER--%>
                            <div class="mt-lg hidden" id="OTHERtbl">
                                <div style="display: flex; justify-content: space-between" class="mb-xs">
                                    <div>
                                        <h3><spring:message code="label.revert"/></h3>
                                    </div>

                                    <div>
                                        <button class="btn btn-primary" type="button" onclick="openAddRevertModal()">
                                            <spring:message code="common.btn.add"/>
                                        </button>
                                    </div>
                                </div>

                                <div class="table-responsive qlsp no-per-page">
                                    <table class="table table-bordered table-striped mb-small mt-none">
                                        <thead>
                                        <tr>
                                            <th>Loại DV</th>
                                            <th class="center"><spring:message code="label.action"/></th>
                                            <th><spring:message code="label.revert.amount"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${reconcil.reconciliationRevertElements}" var="entry">
                                            <tr>
                                                <td>${entry.serviceType}</td>
                                                <td class="center">
                                                    <a title="Chỉnh sửa thông tin"
                                                       onclick="openUpdateRevertModal(${entry.id})">
                                                        <i class="fa fa-edit" style="color: #2582c4"
                                                           aria-hidden="true"></i>
                                                    </a>
                                                    <a title="Xóa"
                                                       onclick="deleteReconcilationRevertElement(${entry.id})">
                                                        <i class="fa fa-trash" style="color: red"
                                                           aria-hidden="true"></i>
                                                    </a>
                                                </td>
                                                <td class="right">
                                                    <fmt:formatNumber type="number"
                                                                      maxFractionDigits="3"
                                                                      value="${entry.revertAmount}"/>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>

            <!-- end: page -->
        </section>
        <jsp:include page="create_element_modal.jsp"/>
        <jsp:include page="update_element_modal.jsp"/>
        <jsp:include page="update_revert_modal.jsp"/>
        <jsp:include page="create_revert_modal.jsp"/>
        <jsp:include page="../../../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../../../include_page/js_footer.jsp"/>

<script src="${contextPath}/assets/jquery.inputmask.bundle.min.js"></script>

<jsp:include page="services_edit.jsp"/>

</body>

</html>
