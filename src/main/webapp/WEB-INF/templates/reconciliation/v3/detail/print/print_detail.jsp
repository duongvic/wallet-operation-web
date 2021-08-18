<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 6/11/2021
  Time: 11:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../../../../include_page/taglibs.jsp" %>
<html>
<head>
    <title>Biên bản đối soát</title>
    <link rel="stylesheet" href="<c:url value='/assets/development/static/css/bootstrap.min.css'/>" media="none" onload="if(media!='all')media='all'">
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
            background-color: #dddddd;
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
    </style>
</head>
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
                                            pattern="dd/MM/yyyy" value="${reconcil.contract.contractDate}"/></span>
            </div>
            <div class="cls_002">
                <span class="cls_002">LOẠI CHU KỲ : ${paymentPolicyType}</span>
            </div>
        </div>
    </div>
    <div>
        <div class="cls_005">
            <span class="cls_005" style="font-weight: bold">A. ĐỐI SOÁT DỊCH VỤ</span>
        </div>
        <%--epin--%>
        <div>
            <div class="cls_002"><span
                    class="cls_002">A.I. ĐỐI SOÁT DỊCH VỤ EPIN.</span>
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
                    <tr>
                        <td colspan="2" class="cls_007">Tổng</td>
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
                <div class="cls_006 flex"><span class="cls_006">Tổng dịch vụ Đối tác sử dụng trong kỳ (A.I):</span><span
                        class="cls_007"><fmt:formatNumber
                        type="number"
                        maxFractionDigits="3"
                        value="${reconcil.getTotalNetAmount('EPIN')}"/>	đồng ( Bao gồm VAT )</span>
                </div>
                <div class="cls_006 flex">
                    <span class="cls_006">Số tiền hoàn thẻ lỗi trong kỳ (nếu có)</span>
                    <span class="cls_007"><fmt:formatNumber
                            type="number"
                            maxFractionDigits="3"
                            value="${reconcil.getTotalRevert('EPIN')}"/></span>
                </div>
                <div class="cls_006 flex"><span class="cls_006">Tổng dịch vụ Đối tác sử dụng trong kỳ còn lại (A.I):</span><span
                        class="cls_007"><fmt:formatNumber
                        type="number"
                        maxFractionDigits="3"
                        value="${reconcil.getExtantBalance('EPIN')}"/>	đồng ( Bao gồm VAT )</span>
                </div>
            </div>
        </div>
        <%--export epin--%>
        <div>
            <div class="cls_002"><span
                    class="cls_002">A.II. ĐỐI SOÁT DỊCH VỤ EXPORT_EPIN.</span>
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
                    <tr>
                        <td colspan="2" class="cls_007">Tổng</td>
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
                <div class="cls_006 flex"><span class="cls_006">Tổng dịch vụ Đối tác sử dụng trong kỳ (A.II):</span><span
                        class="cls_007"><fmt:formatNumber
                        type="number"
                        maxFractionDigits="3"
                        value="${reconcil.getTotalNetAmount('EXPORT_EPIN')}"/>	đồng ( Bao gồm VAT )</span>
                </div>
                <div class="cls_006 flex">
                    <span class="cls_006">Số tiền hoàn thẻ lỗi trong kỳ (nếu có)</span>
                    <span class="cls_007"><fmt:formatNumber
                            type="number"
                            maxFractionDigits="3"
                            value="${reconcil.getTotalRevert('EXPORT_EPIN')}"/></span>
                </div>
                <div class="cls_006 flex"><span class="cls_006">Tổng dịch vụ Đối tác sử dụng trong kỳ còn lại (A.II):</span><span
                        class="cls_007"><fmt:formatNumber
                        type="number"
                        maxFractionDigits="3"
                        value="${reconcil.getExtantBalance('EXPORT_EPIN')}"/>	đồng ( Bao gồm VAT )</span>
                </div>
            </div>
        </div>
        <%--topup--%>
        <div>
            <div class="cls_002"><span
                    class="cls_002">A.III. ĐỐI SOÁT DỊCH VỤ PHONE_TOPUP.</span>
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
                    <tr>
                        <td colspan="2" class="cls_007">Tổng</td>
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
                <div class="cls_006 flex"><span class="cls_006">Tổng dịch vụ Đối tác sử dụng trong kỳ (A.III):</span><span
                        class="cls_007"><fmt:formatNumber
                        type="number"
                        maxFractionDigits="3"
                        value="${reconcil.getTotalNetAmount('PHONE_TOPUP')}"/>	đồng ( Bao gồm VAT )</span>
                </div>
                <div class="cls_006 flex">
                    <span class="cls_006">Số tiền hoàn thẻ lỗi trong kỳ (nếu có)</span>
                    <span class="cls_007"><fmt:formatNumber
                            type="number"
                            maxFractionDigits="3"
                            value="${reconcil.getTotalRevert('PHONE_TOPUP')}"/></span>
                </div>
                <div class="cls_006 flex"><span class="cls_006">Tổng dịch vụ Đối tác sử dụng trong kỳ còn lại (A.III):</span><span
                        class="cls_007"><fmt:formatNumber
                        type="number"
                        maxFractionDigits="3"
                        value="${reconcil.getExtantBalance('PHONE_TOPUP')}"/>	đồng ( Bao gồm VAT )</span>
                </div>
            </div>
        </div>
        <%--bill payment--%>
        <div>
            <div class="cls_002"><span
                    class="cls_002">A.IV. ĐỐI SOÁT DỊCH VỤ BILL_PAYMENT.</span>
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
                            Dịch vụ
                        </th>
                        <th colspan="2" class="cls_005"
                            style="text-align: center; font-weight: bold">
                            Mức phí (đã bao gồm VAT)
                        </th>
                        <th class="cls_005"
                            style="text-align: center; font-weight: bold">
                            Số lượng giao
                            dịch
                        </th>
                        <th class="cls_005"
                            style="text-align: center; font-weight: bold">
                            Số
                            giá trị giao dịch (chưa phí)
                        </th>
                        <th class="cls_005"
                            style="text-align: center; font-weight: bold">
                            Phí giao dịch
                        </th>
                        <th class="cls_005"
                            style="text-align: center; font-weight: bold">
                            Tổng tiền phí giao dịch
                        </th>
                        <th class="cls_005"
                            style="text-align: center; font-weight: bold">
                            Tiền phí Đối tác được hưởng
                        </th>
                    </tr>
                    <tr>
                        <td colspan="2" class="cls_007" style="width: 150px">Tổng cộng
                            dịch vụ
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
                <div class="cls_006 flex"><span class="cls_006">Số tiền Đối tác đã tiêu trong kỳ (A.IV):</span><span
                        class="cls_007"><fmt:formatNumber
                        type="number"
                        maxFractionDigits="3"
                        value="${reconcil.getAmountPartnerSpentInPeriod()}"/>	đồng</span>
                </div>
                <div class="cls_006 flex">
                    <span class="cls_006">Bù trừ giao dịch hủy/ điều chỉnh</span>
                    <span class="cls_007"><fmt:formatNumber
                            type="number"
                            maxFractionDigits="3"
                            value="${reconcil.getTotalRevert('BILL_PAYMENT')}"/></span>
                </div>
                <div class="cls_006 flex"><span class="cls_006">Tổng dịch vụ Đối tác sử dụng trong kỳ còn lại (A.IV):</span><span
                        class="cls_007"><fmt:formatNumber
                        type="number"
                        maxFractionDigits="3"
                        value="${reconcil.getExtantBalance('BILL_PAYMENT')}"/>	đồng</span>
                </div>
            </div>
        </div>
        <%--/////////////////////////--%>
        <div class="cls_008 flex"><span class="cls_008"
                                        style="font-weight: bold;">Tổng phí đối tác được hưởng (A.IV*)</span><span
                class="cls_008" style="font-weight: bold"><fmt:formatNumber
                type="number"
                maxFractionDigits="3"
                value="${reconcil.getTotalCommissionAmount('BILL_PAYMENT')}"/></span></div>
    </div>
    <div>
        <div class="cls_008 flex"><span class="cls_008" style="font-weight: bold;">B. CÔNG NỢ</span><span
                class="cls_008" style="font-weight: bold">Giá trị (VNĐ)</span></div>
        <div>
            <table>
                <tr>
                    <td class="cls_006">B.I</td>
                    <td class="cls_006">Số dư đầu kỳ (B.I):</td>
                    <td class="cls_006" style="text-align: right">
                        <fmt:formatNumber type="number"
                                          maxFractionDigits="3"
                                          value="${reconcil.openingBalance}"/></td>
                </tr>
                <tr>
                    <td class="cls_006">B.II</td>
                    <td class="cls_006">Số tiền đã nạp trong kỳ (B.II):</td>
                    <td class="cls_006" style="text-align: right">
                        <fmt:formatNumber type="number"
                                          maxFractionDigits="3"
                                          value="${reconcil.sumOfFundInAmount}"/></td>
                </tr>
                <tr>
                    <td class="cls_006">B.III</td>
                    <td class="cls_006">Số tiền đã rút trong kỳ (B.III):</td>
                    <td class="cls_006" style="text-align: right">
                        <fmt:formatNumber type="number"
                                          maxFractionDigits="3"
                                          value="${reconcil.sumOfFundOutAmount}"/></td>
                </tr>
                <tr>
                    <td class="cls_006">B.IV</td>
                    <td class="cls_006">Dịch vụ đã sử dụng trong kỳ (B.IV.1 +
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
                    <td class="cls_006">Tổng dịch vụ Đối tác sử dụng trong
                        kỳ còn lại
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
                    <td class="cls_006">Tổng dịch vụ Đối tác sử dụng trong
                        kỳ còn lại
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
                    <td class="cls_006">Tổng dịch vụ Đối tác sử dụng trong
                        kỳ còn lại
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
                    <td class="cls_006">Tổng dịch vụ Đối tác sử dụng trong
                        kỳ còn lại
                        (A.IV):
                    </td>
                    <td class="cls_006" style="text-align: right">
                        <fmt:formatNumber
                                type="number" maxFractionDigits="3"
                                value="${reconcil.getExtantBalance('BILL_PAYMENT')}"/></td>
                </tr>
                <tr>
                    <td class="cls_006">B.V</td>
                    <td class="cls_006">Tổng phí đối tác được hưởng
                        (A.IV*)
                    </td>
                    <td class="cls_006" style="text-align: right">
                        <fmt:formatNumber type="number"
                                          maxFractionDigits="3"
                                          value="${reconcil.getTotalCommissionAmount('BILL_PAYMENT')}"/></td>
                </tr>
                <tr>
                    <td class="cls_006">B.VI</td>
                    <td class="cls_006">Số dư cuối kỳ
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
                    <span class="cls_006">Bằng chữ:</span>
                </div>
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
</html>
