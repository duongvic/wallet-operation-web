<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 6/12/2021
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
    <style>
        .my-body {
            padding-top: 50px;
            padding-left: 10%;
            padding-right: 10%;

            font-family: Times, serif;
            font-style: normal;
            font-weight: normal;
            line-height: 1.6;
            color: rgb(0, 0, 0);
            text-decoration: none;
        }

        .my-header {
            text-align: center;
            margin-bottom: 4px;
        }

        .cls_003 {
            font-size: 14px;
        }

        .cls_002 {
            font-size: 11px;
        }

        .cls_004 {
            font-size: 10px;
        }

        .cls_005 {
            font-size: 11px;
        }

        .cls_006 {
            font-family: "Calibri", serif;
            font-size: 11px;
            color: rgb(0, 0, 0);
        }

        .cls_007 {
            font-family: Arial, serif;
            font-size: 11px;
            color: rgb(0, 0, 0);
        }

        .cls_008 {
            font-family: "Calibri Bold", serif;
            font-size: 11px;
            color: rgb(0, 0, 0);
        }

        .cls_009 {
            font-family: Arial, serif;
            font-size: 11px;
            color: rgb(0, 0, 0);
        }

        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid rgb(0, 0, 0);
        }

        th {
            background-color: #dddddd;
            padding-top: 8px;
        }

        td {
            padding-top: 4px;
            padding-right: 2px;
        }

        .flex {
            display: flex;
            justify-content: space-between;
        }
    </style>
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
                                <li><span class=""><spring:message code="service.exportcard.list.navigate.service"/></span>
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
                    <div class="tabs">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a>
                                    TOTAL</a>
                            </li>
                            <li ${fn:contains(reconcil.geKeyArr(), "%EPIN%") eq false ? 'style="display: none"': ''}>
                                <a href="${contextPath}/wallet/reconcilation/detail/epin/${reconcil.id}">
                                    EPIN</a>
                            </li>
                            <li ${fn:contains(reconcil.geKeyArr(), "%EXPORT_EPIN%") eq false ? 'style="display: none"': ''}>
                                <a href="${contextPath}/wallet/reconcilation/detail/export-epin/${reconcil.id}">
                                    EXPORT EPIN</a>
                            </li>
                            <li ${fn:contains(reconcil.geKeyArr(), "%PHONE_TOPUP%") eq false ? 'style="display: none"': ''}>
                                <a href="${contextPath}/wallet/reconcilation/detail/topup/${reconcil.id}">
                                    PHONE TOPUP</a>
                            </li>
                            <li ${fn:contains(reconcil.geKeyArr(), "%BILL_PAYMENT%") eq false ? 'style="display: none"': ''}>
                                <a href="${contextPath}/wallet/reconcilation/detail/bill-payment/${reconcil.id}">
                                    BILL PAYMENT</a>
                            </li>
                        </ul>
                        <div class="tab-content pl-none pr-none">
                            <div id="tab1" class="tab-pane active">
                                <section class="panel search_payment panel-default">
                                    <div class="panel-body pt-none">
                                        <div style="float: right" class="action_icon"><a target="_blank" title="In"
                                                                                         href="${contextPath}/wallet/reconcilation/print/${reconcil.id}"><i
                                                class="fa fa-2x fa-print"></i></a></div>
                                        <div class="clearfix"></div>
                                        <div class="my-body">
                                            <div class="my-header">
                                                <div style="margin-bottom: 16px">
                                                    <div class="cls_002">
                                                        <span class="cls_002">C???NG H??A X?? H???I CH??? NGH??A VI???T NAM</span>
                                                    </div>
                                                    <div class="cls_002">
                                                        <span class="cls_002">?????c l???p - T??? do -H???nh ph??c</span>
                                                    </div>
                                                </div>
                                                <div>
                                                    <div class="cls_003">
                                                        <span class="cls_003" style="font-weight: bold">BI??N B???N ?????I SO??T D???CH V???</span>
                                                    </div>
                                                    <div class="cls_004">
                                                        <span class="cls_004">K??? ?????i so??t: [${paymentPolicyName}] T???: [${dateStringByFromDateAndToDate}]</span>
                                                    </div>
                                                    <div class="cls_002">
                                                        <span class="cls_002">M?? ?????I T??C: [${reconcil.contract.email}] [${reconcil.contract.customerName}]</span>
                                                    </div>
                                                    <div class="cls_002">
                                                        <span class="cls_002">H???P ?????NG S???: [${reconcil.contract.contractNo}]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;K?? ng??y: <fmt:formatDate
                                                                pattern="dd/MM/yyyy"
                                                                value="${reconcil.contract.contractDate}"/></span>
                                                    </div>
                                                    <div class="cls_002">
                                                        <span class="cls_002">LO???I CHU K??? : ${paymentPolicyType}</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div>
                                                <div class="cls_005"><span class="cls_005"
                                                                           style="font-weight: bold">A. ?????I SO??T D???CH V???</span>
                                                </div>
                                                <%--epin--%>
                                                <div>
                                                    <div class="cls_002"><span
                                                            class="cls_002">A.I. ?????I SO??T D???CH V??? EPIN.</span>
                                                    </div>
                                                    <div>
                                                        <table>
                                                            <tr>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    STT
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    LO???I TH???
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    M???NH GI??
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    S???
                                                                    L?????NG B??N
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    S???
                                                                    L?????NG HO??N
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    T???NG S??? L?????NG
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    TH??NH TI???N CH??A CK
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    T???
                                                                    l??? CK
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    TI???N THANH TO??N
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2" class="cls_007">T???ng</td>
                                                                <td></td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                    <span>${reconcil.getTotalQuantity('EPIN')}</span>
                                                                </td>
                                                                <td></td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                    <span>${reconcil.getTotalQuantity('EPIN')}</span>
                                                                </td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                        <span><fmt:formatNumber type="number"
                                                                                                maxFractionDigits="3"
                                                                                                value="${reconcil.getTotalRequestAmount('EPIN')}"/></span>
                                                                </td>
                                                                <td></td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                        <span><fmt:formatNumber type="number"
                                                                                                maxFractionDigits="3"
                                                                                                value="${reconcil.getTotalNetAmount('EPIN')}"/></span>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div>
                                                        <div class="cls_006 flex"><span class="cls_006">T???ng d???ch v??? ?????i t??c s??? d???ng trong k??? (A.I):</span><span
                                                                class="cls_007"><fmt:formatNumber
                                                                type="number"
                                                                maxFractionDigits="3"
                                                                value="${reconcil.getTotalNetAmount('EPIN')}"/>	?????ng ( Bao g???m VAT )</span>
                                                        </div>
                                                        <div class="cls_006 flex">
                                                            <span class="cls_006">S??? ti???n ho??n th??? l???i trong k??? (n???u c??)</span>
                                                            <span class="cls_007"><fmt:formatNumber
                                                                    type="number"
                                                                    maxFractionDigits="3"
                                                                    value="${reconcil.getTotalRevert('EPIN')}"/></span>
                                                        </div>
                                                        <div class="cls_006 flex"><span class="cls_006">T???ng d???ch v??? ?????i t??c s??? d???ng trong k??? c??n l???i (A.I):</span><span
                                                                class="cls_007"><fmt:formatNumber
                                                                type="number"
                                                                maxFractionDigits="3"
                                                                value="${reconcil.getExtantBalance('EPIN')}"/>	?????ng ( Bao g???m VAT )</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <%--export epin--%>
                                                <div>
                                                    <div class="cls_002"><span
                                                            class="cls_002">A.II. ?????I SO??T D???CH V??? EXPORT_EPIN.</span>
                                                    </div>
                                                    <div>
                                                        <table>
                                                            <tr>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    STT
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    LO???I TH???
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    M???NH GI??
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    S???
                                                                    L?????NG B??N
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    S???
                                                                    L?????NG HO??N
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    T???NG S??? L?????NG
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    TH??NH TI???N CH??A CK
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    T???
                                                                    l??? CK
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    TI???N THANH TO??N
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2" class="cls_007">T???ng</td>
                                                                <td></td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                    <span>${reconcil.getTotalQuantity('EXPORT_EPIN')}</span>
                                                                </td>
                                                                <td></td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                    <span>${reconcil.getTotalQuantity('EXPORT_EPIN')}</span>
                                                                </td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                        <span><fmt:formatNumber type="number"
                                                                                                maxFractionDigits="3"
                                                                                                value="${reconcil.getTotalRequestAmount('EXPORT_EPIN')}"/></span>
                                                                </td>
                                                                <td></td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                        <span><fmt:formatNumber type="number"
                                                                                                maxFractionDigits="3"
                                                                                                value="${reconcil.getTotalNetAmount('EXPORT_EPIN')}"/></span>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div>
                                                        <div class="cls_006 flex"><span class="cls_006">T???ng d???ch v??? ?????i t??c s??? d???ng trong k??? (A.II):</span><span
                                                                class="cls_007"><fmt:formatNumber
                                                                type="number"
                                                                maxFractionDigits="3"
                                                                value="${reconcil.getTotalNetAmount('EXPORT_EPIN')}"/>	?????ng ( Bao g???m VAT )</span>
                                                        </div>
                                                        <div class="cls_006 flex">
                                                            <span class="cls_006">S??? ti???n ho??n th??? l???i trong k??? (n???u c??)</span>
                                                            <span class="cls_007"><fmt:formatNumber
                                                                    type="number"
                                                                    maxFractionDigits="3"
                                                                    value="${reconcil.getTotalRevert('EXPORT_EPIN')}"/></span>
                                                        </div>
                                                        <div class="cls_006 flex"><span class="cls_006">T???ng d???ch v??? ?????i t??c s??? d???ng trong k??? c??n l???i (A.II):</span><span
                                                                class="cls_007"><fmt:formatNumber
                                                                type="number"
                                                                maxFractionDigits="3"
                                                                value="${reconcil.getExtantBalance('EXPORT_EPIN')}"/>	?????ng ( Bao g???m VAT )</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <%--topup--%>
                                                <div>
                                                    <div class="cls_002"><span
                                                            class="cls_002">A.III. ?????I SO??T D???CH V??? PHONE_TOPUP.</span>
                                                    </div>
                                                    <div>
                                                        <table>
                                                            <tr>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    STT
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    LO???I TH???
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    M???NH GI??
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    S???
                                                                    L?????NG B??N
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    S???
                                                                    L?????NG HO??N
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    T???NG S??? L?????NG
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    TH??NH TI???N CH??A CK
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    T???
                                                                    l??? CK
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    TI???N THANH TO??N
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2" class="cls_007">T???ng</td>
                                                                <td></td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                    <span>${reconcil.getTotalQuantity('PHONE_TOPUP')}</span>
                                                                </td>
                                                                <td></td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                    <span>${reconcil.getTotalQuantity('PHONE_TOPUP')}</span>
                                                                </td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                        <span><fmt:formatNumber type="number"
                                                                                                maxFractionDigits="3"
                                                                                                value="${reconcil.getTotalRequestAmount('PHONE_TOPUP')}"/></span>
                                                                </td>
                                                                <td></td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                        <span><fmt:formatNumber type="number"
                                                                                                maxFractionDigits="3"
                                                                                                value="${reconcil.getTotalNetAmount('PHONE_TOPUP')}"/></span>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div>
                                                        <div class="cls_006 flex"><span class="cls_006">T???ng d???ch v??? ?????i t??c s??? d???ng trong k??? (A.III):</span><span
                                                                class="cls_007"><fmt:formatNumber
                                                                type="number"
                                                                maxFractionDigits="3"
                                                                value="${reconcil.getTotalNetAmount('PHONE_TOPUP')}"/>	?????ng ( Bao g???m VAT )</span>
                                                        </div>
                                                        <div class="cls_006 flex">
                                                            <span class="cls_006">S??? ti???n ho??n th??? l???i trong k??? (n???u c??)</span>
                                                            <span class="cls_007"><fmt:formatNumber
                                                                    type="number"
                                                                    maxFractionDigits="3"
                                                                    value="${reconcil.getTotalRevert('PHONE_TOPUP')}"/></span>
                                                        </div>
                                                        <div class="cls_006 flex"><span class="cls_006">T???ng d???ch v??? ?????i t??c s??? d???ng trong k??? c??n l???i (A.III):</span><span
                                                                class="cls_007"><fmt:formatNumber
                                                                type="number"
                                                                maxFractionDigits="3"
                                                                value="${reconcil.getExtantBalance('PHONE_TOPUP')}"/>	?????ng ( Bao g???m VAT )</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <%--bill payment--%>
                                                <div>
                                                    <div class="cls_002"><span
                                                            class="cls_002">A.IV. ?????I SO??T D???CH V??? BILL_PAYMENT.</span>
                                                    </div>
                                                    <div>
                                                        <table>
                                                            <tr>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    STT
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    D???ch v???
                                                                </th>
                                                                <th colspan="2" class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    M???c ph?? (???? bao g???m VAT)
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    S??? l?????ng giao
                                                                    d???ch
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    S???
                                                                    gi?? tr??? giao d???ch (ch??a ph??)
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    Ph?? giao d???ch
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    T???ng ti???n ph?? giao d???ch
                                                                </th>
                                                                <th class="cls_005"
                                                                    style="text-align: center; font-weight: bold">
                                                                    Ti???n ph?? ?????i t??c ???????c h?????ng
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2" class="cls_007">T???ng c???ng
                                                                    d???ch v???
                                                                </td>
                                                                <td class="cls_007"
                                                                    style="text-align: right;"></td>
                                                                <td class="cls_007"
                                                                    style="text-align: right;"></td>
                                                                <td class="cls_007"
                                                                    style="text-align: right;">
                                                                    <fmt:formatNumber type="number"
                                                                                      maxFractionDigits="3"
                                                                                      value="${reconcil.getTotalQuantity('BILL_PAYMENT')}"/></td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                        <span><fmt:formatNumber type="number"
                                                                                                maxFractionDigits="3"
                                                                                                value="${reconcil.getTotalRequestAmount('BILL_PAYMENT')}"/></span>
                                                                </td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                    <span></span></td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                        <span><fmt:formatNumber
                                                                                type="number"
                                                                                maxFractionDigits="3"
                                                                                value="${reconcil.getTotalFeeAmount('BILL_PAYMENT')}"/></span>
                                                                </td>
                                                                <td class="cls_007"
                                                                    style="text-align: right">
                                                                        <span><fmt:formatNumber
                                                                                type="number"
                                                                                maxFractionDigits="3"
                                                                                value="${reconcil.getTotalCommissionAmount('BILL_PAYMENT')}"/></span>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div>
                                                        <div class="cls_006 flex"><span class="cls_006">S??? ti???n ?????i t??c ???? ti??u trong k??? (A.IV):</span><span
                                                                class="cls_007"><fmt:formatNumber
                                                                type="number"
                                                                maxFractionDigits="3"
                                                                value="${reconcil.getAmountPartnerSpentInPeriod()}"/>	?????ng</span>
                                                        </div>
                                                        <div class="cls_006 flex">
                                                            <span class="cls_006">B?? tr??? giao d???ch h???y/ ??i???u ch???nh</span>
                                                            <span class="cls_007"><fmt:formatNumber
                                                                    type="number"
                                                                    maxFractionDigits="3"
                                                                    value="${reconcil.getTotalRevert('BILL_PAYMENT')}"/></span>
                                                        </div>
                                                        <div class="cls_006 flex"><span class="cls_006">T???ng d???ch v??? ?????i t??c s??? d???ng trong k??? c??n l???i (A.IV):</span><span
                                                                class="cls_007"><fmt:formatNumber
                                                                type="number"
                                                                maxFractionDigits="3"
                                                                value="${reconcil.getExtantBalance('BILL_PAYMENT')}"/>	?????ng</span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <%--/////////////////////////--%>

                                                <div class="cls_008 flex"><span class="cls_008"
                                                                                style="font-weight: bold;">T???ng ph?? ?????i t??c ???????c h?????ng (A.IV*)</span><span
                                                        class="cls_008" style="font-weight: bold"><fmt:formatNumber
                                                        type="number"
                                                        maxFractionDigits="3"
                                                        value="${reconcil.getTotalCommissionAmount('BILL_PAYMENT')}"/></span></div>
                                            </div>
                                            <div>
                                                <div class="cls_008 flex"><span class="cls_008"
                                                                                style="font-weight: bold;">B. C??NG N???</span><span
                                                        class="cls_008" style="font-weight: bold">Gi?? tr??? (VN??)</span>
                                                </div>
                                                <div>
                                                    <table>
                                                        <tr>
                                                            <td class="cls_006">B.I</td>
                                                            <td class="cls_006">S??? d?? ?????u k??? (B.I):</td>
                                                            <td class="cls_006" style="text-align: right">
                                                                <fmt:formatNumber type="number"
                                                                                  maxFractionDigits="3"
                                                                                  value="${reconcil.openingBalance ne null ? reconcil.openingBalance : 0}"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="cls_006">B.II</td>
                                                            <td class="cls_006">S??? ti???n ???? n???p trong k??? (B.II):</td>
                                                            <td class="cls_006" style="text-align: right">
                                                                <fmt:formatNumber type="number"
                                                                                  maxFractionDigits="3"
                                                                                  value="${reconcil.sumOfFundInAmount ne null  ? reconcil.sumOfFundInAmount : 0}"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="cls_006">B.III</td>
                                                            <td class="cls_006">S??? ti???n ???? r??t trong k??? (B.III):</td>
                                                            <td class="cls_006" style="text-align: right">
                                                                <fmt:formatNumber type="number"
                                                                                  maxFractionDigits="3"
                                                                                  value="${reconcil.sumOfFundOutAmount ne null  ? reconcil.sumOfFundOutAmount : 0}"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="cls_006">B.IV</td>
                                                            <td class="cls_006">D???ch v??? ???? s??? d???ng trong k??? (B.IV.1 +
                                                                B.IV.2 + B.IV.3 + B.IV.4)
                                                            </td>
                                                            <td class="cls_006" style="text-align: right">
                                                                <fmt:formatNumber type="number"
                                                                                  maxFractionDigits="3"
                                                                                  value="${reconcil.getTotalExtantAmount()}"/></td>
                                                        </tr>
                                                        <%--1--%>
                                                        <tr>
                                                            <td class="cls_006">B.IV.1</td>
                                                            <td class="cls_006">T???ng d???ch v??? ?????i t??c s??? d???ng trong
                                                                k??? c??n l???i
                                                                (A.I):
                                                            </td>
                                                            <td class="cls_006" style="text-align: right">
                                                                <fmt:formatNumber
                                                                        type="number" maxFractionDigits="3"
                                                                        value="${reconcil.getExtantBalance('EPIN')}"/></td>
                                                        </tr>
                                                        <%--2--%>
                                                        <tr>
                                                            <td class="cls_006">B.IV.2</td>
                                                            <td class="cls_006">T???ng d???ch v??? ?????i t??c s??? d???ng trong
                                                                k??? c??n l???i
                                                                (A.II):
                                                            </td>
                                                            <td class="cls_006" style="text-align: right">
                                                                <fmt:formatNumber
                                                                        type="number" maxFractionDigits="3"
                                                                        value="${reconcil.getExtantBalance('EXPORT_EPIN')}"/></td>
                                                        </tr>
                                                        <%--3--%>
                                                        <tr>
                                                            <td class="cls_006">B.IV.3</td>
                                                            <td class="cls_006">T???ng d???ch v??? ?????i t??c s??? d???ng trong
                                                                k??? c??n l???i
                                                                (A.III):
                                                            </td>
                                                            <td class="cls_006" style="text-align: right">
                                                                <fmt:formatNumber
                                                                        type="number" maxFractionDigits="3"
                                                                        value="${reconcil.getExtantBalance('PHONE_TOPUP')}"/></td>
                                                        </tr>
                                                        <%--4--%>
                                                        <tr>
                                                            <td class="cls_006">B.IV.4</td>
                                                            <td class="cls_006">T???ng d???ch v??? ?????i t??c s??? d???ng trong
                                                                k??? c??n l???i
                                                                (A.IV):
                                                            </td>
                                                            <td class="cls_006" style="text-align: right">
                                                                <fmt:formatNumber
                                                                        type="number" maxFractionDigits="3"
                                                                        value="${reconcil.getExtantBalance('BILL_PAYMENT')}"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="cls_006">B.V</td>
                                                            <td class="cls_006">T???ng ph?? ?????i t??c ???????c h?????ng
                                                                (A.IV*)
                                                            </td>
                                                            <td class="cls_006" style="text-align: right">
                                                                <fmt:formatNumber type="number"
                                                                                  maxFractionDigits="3"
                                                                                  value="${reconcil.getTotalCommissionAmount('BILL_PAYMENT')}"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="cls_006">B.VI</td>
                                                            <td class="cls_006">S??? d?? cu???i k???
                                                                (B.I+B.II-B.III-B.IV+B.V):
                                                            </td>
                                                            <td class="cls_006" style="text-align: right">
                                                                <fmt:formatNumber type="number"
                                                                                  maxFractionDigits="3"
                                                                                  value="${reconcil.getEndingBalance()}"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div>
                                                    <div class="cls_006 row">
                                                        <div class="col-md-1 col-xs-1"></div>
                                                        <div class="col-md-2 col-xs-2">
                                                            <span class="cls_006">B???ng ch???:</span>
                                                        </div>
                                                    </div>
                                                    <div class="cls_006"><span class="cls_006">Bi??n b???n n??y ???????c l???p th??nh 04 b???n c?? gi?? tr??? ph??p l?? nh?? nhau, m???i b??n gi??? 02 b???n.</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div style="margin-bottom: 300px; margin-top: 16px">
                                                <div class="cls_006" style="text-align: right"><span class="cls_006">Ng??y&nbsp;&nbsp;&nbsp;&nbsp;th??ng&nbsp;&nbsp;&nbsp;&nbsp;n??m ${reconcil.year}</span>
                                                </div>
                                                <div class="cls_009 flex">
                                                    <span class="cls_009" style="font-weight: bold">?????I DI???N C??NG TY TNHH ZOTA TRADING</span>
                                                    <span class="cls_009"
                                                          style="font-weight: bold; text-transform: uppercase;">?????I DI???N C??NG TY C??? PH???N THANH TO??N ${reconcil.contract.customerName}</span>
                                                </div>
                                            </div>
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
        <jsp:include page="../../../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../../../include_page/js_footer.jsp"/>
</body>
<script>
    $(document).ready(function () {
    });
</script>
</html>
