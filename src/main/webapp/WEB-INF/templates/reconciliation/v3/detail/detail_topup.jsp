<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 6/17/2021
  Time: 11:34 AM
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
            /*background-color: #dddddd;*/
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

        .flex-money {
            display: flex;
            flex-direction: column;
            align-items: flex-end;
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
                            <li>
                                <a href="${contextPath}/wallet/reconcilation/detail/${reconcil.id}">
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
                            <li class="active">
                                <a>
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
                                                                                         href="${contextPath}/wallet/reconcilation/print/topup/${reconcil.id}"><i
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
                                                                           style="font-weight: bold">I. ?????I SO??T D???CH V??? PHONE TOPUP</span>
                                                </div>
                                                <c:forEach var="service" items="${reconcil.elementGroupByServiceType}"
                                                           varStatus="loop">
                                                    <c:choose>
                                                        <c:when test="${service.key eq 'PHONE_TOPUP'}">
                                                            <div>
                                                                <table>
                                                                    <tr style="background-color: rgb(146, 209, 78)">
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

                                                                    <c:forEach items="${service.value}" var="item"
                                                                               varStatus="loop">
                                                                        <tr>
                                                                            <td class="cls_007"
                                                                                style="text-align: center; font-weight: bold">
                                                                                    ${item.serviceShortName}
                                                                            </td>
                                                                            <td class="cls_007"
                                                                                style="text-align: right">
                                                                                <fmt:formatNumber type="number" maxFractionDigits="3" value="${item.unitPrice}"/>
                                                                            </td>
                                                                            <td class="cls_007"
                                                                                style="text-align: right">
                                                                                <span><fmt:formatNumber type="number" maxFractionDigits="3" value="${item.quantity}"/></span>
                                                                            </td>
                                                                            <td></td>
                                                                            <td class="cls_007"
                                                                                style="text-align: right">
                                                                                <span><fmt:formatNumber type="number" maxFractionDigits="3" value="${item.quantity}" /></span>
                                                                            </td>
                                                                            <td class="cls_007"
                                                                                style="text-align: right">
                                                                                <span><fmt:formatNumber type="number" maxFractionDigits="3" value="${item.sumOfRequestAmount}" /></span>
                                                                            </td>
                                                                            <td class="cls_007"
                                                                                style="text-align: right">
                                                                                    ${item.unitCommissionPercentRate}%
                                                                            </td>
                                                                            <td class="cls_007"
                                                                                style="text-align: right">
                                                                                <span><fmt:formatNumber type="number" maxFractionDigits="3" value="${item.sumOfNetAmount}" /></span>
                                                                            </td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                    <tr style="background-color: red">
                                                                        <td class="cls_007" style="text-align: center; font-weight: bold">
                                                                            T???ng
                                                                        </td>
                                                                        <td></td>
                                                                        <td class="cls_007"
                                                                            style="text-align: right; font-weight: bold">
                                                                            <span><fmt:formatNumber type="number" maxFractionDigits="3" value="${reconcil.getTotalQuantity('PHONE_TOPUP')}" /></span>
                                                                        </td>
                                                                        <td></td>
                                                                        <td class="cls_007"
                                                                            style="text-align: right; font-weight: bold">
                                                                            <span><fmt:formatNumber type="number" maxFractionDigits="3" value="${reconcil.getTotalQuantity('PHONE_TOPUP')}" /></span>
                                                                        </td>
                                                                        <td class="cls_007"
                                                                            style="text-align: right; font-weight: bold">
                                                                            <span><fmt:formatNumber type="number" maxFractionDigits="3" value="${reconcil.getTotalRequestAmount('PHONE_TOPUP')}" /></span>
                                                                        </td>
                                                                        <td></td>
                                                                        <td class="cls_007"
                                                                            style="text-align: right; font-weight: bold">
                                                                            <span><fmt:formatNumber type="number" maxFractionDigits="3" value="${reconcil.getTotalNetAmount('PHONE_TOPUP')}" /></span>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>
                                            </div>
                                            <div>
                                                <div>
                                                    <div class="row">
                                                        <div class="cls_008 col-md-7 col-xs-7">
                                                            <span class="cls_008">T???ng doanh thu ZOTA TRADING ???????c h?????ng:</span>
                                                        </div>
                                                        <div class="cls_008 col-md-3 col-xs-3">
                                                            <span class="cls_008" style="font-weight: bold"><fmt:formatNumber type="number" maxFractionDigits="3" value="${reconcil.getTotalNetAmount('PHONE_TOPUP')}" /> ?????ng ( Bao g???m VAT )</span>
                                                        </div>
                                                    </div>

                                                    <div class="cls_006"><span
                                                            class="cls_006">S??? ti???n b???ng ch???: ${ewallet:numberToText(reconcil.getTotalNetAmount('PHONE_TOPUP'))} ?????ng</span>
                                                    </div>
                                                    <div class="row">
                                                        <div class="cls_006 col-md-4 col-xs-4"><span
                                                                class="cls_006">(S??? ti???n ???? bao g???m thu??? VAT)</span>
                                                        </div>
                                                        <div class="cls_006 col-md-3 col-xs-3"
                                                             style="display: flex; justify-content: space-around">
                                                            <span class="cls_006">Trong ????</span>
                                                            <div>
                                                                <span class="cls_006">Gi?? tr??? tr?????c thu???</span><br>
                                                                <span class="cls_006">Thu??? GTGT 10%</span>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-1 col-xs-1 flex-money">
                                                            <span class="cls_006"
                                                                  style="font-weight: bold"><fmt:formatNumber type="number" maxFractionDigits="0" value="${reconcil.getTotalNetAmount('PHONE_TOPUP') / 1.1}" /></span>
                                                            <span class="cls_006"
                                                                  style="font-weight: bold"><fmt:formatNumber type="number" maxFractionDigits="0" value="${(reconcil.getTotalNetAmount('PHONE_TOPUP') / 1.1) * 0.1}" /></span>
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
                                                <span class="cls_009"
                                                      style="font-weight: bold">?????I DI???N C??NG TY TNHH ZOTA TRADING</span>
                                                    <span class="cls_009"
                                                          style="font-weight: bold">?????I DI???N C??NG TY C??? PH???N THANH TO??N ${reconcil.contract.customerName}</span>
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

