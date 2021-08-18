<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 6/17/2021
  Time: 10:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../../../../include_page/taglibs.jsp" %>
<html>
<head>
    <title>Biên bản đối soát</title>
    <link rel="stylesheet" href="<c:url value='/assets/development/static/css/bootstrap.min.css'/>" media="none"
          onload="if(media!='all')media='all'">
    <script type="text/javascript" src="<c:url value='/assets/development/static/js/jquery.min.js'/>"></script>
    <style>
        * {
            font-family: Times, serif;
            font-style: normal;
            font-weight: normal;
            line-height: 1.6;
            color: rgb(0, 0, 0);
            text-decoration: none;
        }

        body {
            padding-top: 50px;
            padding-left: 10%;
            padding-right: 10%;
        }

        .header {
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
            padding-left: 2px;
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
<%--////////////////--%>
<body>
<div class="header">
    <div style="margin-bottom: 16px">
        <div class="cls_002">
            <span class="cls_002">CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</span>
        </div>
        <div class="cls_002">
            <span class="cls_002">Độc lập - Tự do -Hạnh phúc</span>
        </div>
    </div>
    <div>
        <div class="cls_003">
            <span class="cls_003" style="font-weight: bold">BIÊN BẢN ĐỐI SOÁT DỊCH VỤ</span>
        </div>
        <div class="cls_004">
            <span class="cls_004">Kỳ đối soát: [${paymentPolicyName}] Từ: [${dateStringByFromDateAndToDate}]</span>
        </div>
        <div class="cls_002">
            <span class="cls_002">MÃ ĐỐI TÁC: [${reconcil.contract.email}] [${reconcil.contract.customerName}]</span>
        </div>
        <div class="cls_002">
                                                        <span class="cls_002">HỢP ĐỒNG SỐ: [${reconcil.contract.contractNo}]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ký ngày: <fmt:formatDate
                                                                pattern="dd/MM/yyyy"
                                                                value="${reconcil.contract.contractDate}"/></span>
        </div>
        <div class="cls_002">
            <span class="cls_002">LOẠI CHU KỲ : ${paymentPolicyType}</span>
        </div>
    </div>
</div>
<div>
    <div class="cls_005"><span class="cls_005"
                               style="font-weight: bold">I. ĐỐI SOÁT DỊCH VỤ EXPORT EPIN</span>
    </div>
    <c:forEach var="service" items="${reconcil.elementGroupByServiceType}"
               varStatus="loop">
        <c:choose>
            <c:when test="${service.key eq 'EXPORT_EPIN'}">
                <div>
                    <table>
                        <tr style="background-color: rgb(146, 209, 78)">
                            <th class="cls_005"
                                style="text-align: center; font-weight: bold">
                                LOẠI THẺ
                            </th>
                            <th class="cls_005"
                                style="text-align: center; font-weight: bold">
                                MỆNH GIÁ
                            </th>
                            <th class="cls_005"
                                style="text-align: center; font-weight: bold">
                                SỐ
                                LƯỢNG BÁN
                            </th>
                            <th class="cls_005"
                                style="text-align: center; font-weight: bold">
                                SỐ
                                LƯỢNG HOÀN
                            </th>
                            <th class="cls_005"
                                style="text-align: center; font-weight: bold">
                                TỔNG SỐ LƯỢNG
                            </th>
                            <th class="cls_005"
                                style="text-align: center; font-weight: bold">
                                THÀNH TIỀN CHƯA CK
                            </th>
                            <th class="cls_005"
                                style="text-align: center; font-weight: bold">
                                Tỷ
                                lệ CK
                            </th>
                            <th class="cls_005"
                                style="text-align: center; font-weight: bold">
                                TIỀN THANH TOÁN
                            </th>
                        </tr>

                        <c:forEach items="${service.value}" var="item"
                                   varStatus="loop">
                            <tr>
                                <td class="cls_007"
                                    style="text-align: center; font-weight: bold; width: 150px">
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
                                    style="text-align: right;">
                                    <span><fmt:formatNumber type="number" maxFractionDigits="3" value="${item.sumOfNetAmount}" /></span>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr style="background-color: red">
                            <td class="cls_007" style="text-align: center; font-weight: bold">
                                Tổng
                            </td>
                            <td></td>
                            <td class="cls_007"
                                style="text-align: right; font-weight: bold">
                                <span><fmt:formatNumber type="number" maxFractionDigits="3" value="${reconcil.getTotalQuantity('EXPORT_EPIN')}" /></span>
                            </td>
                            <td></td>
                            <td class="cls_007"
                                style="text-align: right; font-weight: bold">
                                <span><fmt:formatNumber type="number" maxFractionDigits="3" value="${reconcil.getTotalQuantity('EXPORT_EPIN')}" /></span>
                            </td>
                            <td class="cls_007"
                                style="text-align: right; font-weight: bold">
                                <span><fmt:formatNumber type="number" maxFractionDigits="3" value="${reconcil.getTotalRequestAmount('EXPORT_EPIN')}" /></span>
                            </td>
                            <td></td>
                            <td class="cls_007"
                                style="text-align: right; font-weight: bold">
                                <span><fmt:formatNumber type="number" maxFractionDigits="3" value="${reconcil.getTotalNetAmount('EXPORT_EPIN')}" /></span>
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
                <span class="cls_008">Tổng doanh thu ZOTA TRADING được hưởng:</span>
            </div>
            <div class="cls_008 col-md-5 col-xs-5">
                <span class="cls_008" style="font-weight: bold"><fmt:formatNumber type="number" maxFractionDigits="3" value="${reconcil.getTotalNetAmount('EXPORT_EPIN')}" /> đồng ( Bao gồm VAT )</span>
            </div>
        </div>

        <div class="cls_006"><span
                class="cls_006">Số tiền bằng chữ: ${ewallet:numberToText(reconcil.getTotalNetAmount('EXPORT_EPIN'))} đồng</span>
        </div>
        <div class="row">
            <div class="cls_006 col-md-4 col-xs-4"><span
                    class="cls_006">(Số tiền đã bao gồm thuế VAT)</span>
            </div>
            <div class="cls_006 col-md-3 col-xs-3"
                 style="display: flex; justify-content: space-around">
                <span class="cls_006">Trong đó</span>
                <div>
                    <span class="cls_006">Giá trị trước thuế</span><br>
                    <span class="cls_006">Thuế GTGT 10%</span>
                </div>
            </div>
            <div class="col-md-1 col-xs-1 flex-money">
                                                            <span class="cls_006"
                                                                  style="font-weight: bold"><fmt:formatNumber type="number" maxFractionDigits="0" value="${reconcil.getTotalNetAmount('EXPORT_EPIN') / 1.1}" /></span>
                <span class="cls_006"
                      style="font-weight: bold"><fmt:formatNumber type="number" maxFractionDigits="0" value="${(reconcil.getTotalNetAmount('EXPORT_EPIN') / 1.1) * 0.1}" /></span>
            </div>
        </div>
        <div class="cls_006"><span class="cls_006">Biên bản này được lập thành 04 bản có giá trị pháp lý như nhau, mỗi bên giữ 02 bản.</span>
        </div>
    </div>
</div>
<div style="margin-bottom: 300px; margin-top: 16px">
    <div class="cls_006" style="text-align: right"><span class="cls_006">Ngày&nbsp;&nbsp;&nbsp;&nbsp;tháng&nbsp;&nbsp;&nbsp;&nbsp;năm ${reconcil.year}</span>
    </div>
    <div class="cls_009 flex">
                                                <span class="cls_009"
                                                      style="font-weight: bold">ĐẠI DIỆN CÔNG TY TNHH ZOTA TRADING</span>
        <span class="cls_009"
              style="font-weight: bold">ĐẠI DIỆN CÔNG TY CỔ PHẦN THANH TOÁN ${reconcil.contract.customerName}</span>
    </div>
</div>
<script>
    $(document).ready(function () {
        window.print();
    });
</script>
</body>
<%--//////////////--%>
</html>
