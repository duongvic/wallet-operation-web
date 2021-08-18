<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 4/26/2021
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include_page/taglibs.jsp" %>

<div class="row"
     style="margin-top: 50px; margin-bottom: 50px"
>
        <%--------------------------------%>
        <%--            total           --%>
        <%--------------------------------%>
    <div
            class="col-lg-2 col-xs-12"
            style="text-align: center; border-right: 3px solid grey; color: grey"
    >
        <p style="color: grey; font-size: 18px">TOTAL</p><br>
        <p style="font-weight: 900; font-size: 20px; color: grey">
            <c:set value="${success.numOfTrans + fail.numOfTrans + open.numOfTrans + pending.numOfTrans + processing.numOfTrans}" var="allTxn"/>
            <c:set value="${oldSuccess.numOfTrans + oldFail.numOfTrans + oldOpen.numOfTrans + oldPending.numOfTrans + oldProcessing.numOfTrans}" var="allTxnOld"/>
            <c:set value="${((allTxn - allTxnOld) / (allTxnOld > 0 ? allTxnOld : 1)) * 100}" var="percent"/>
            <fmt:formatNumber maxFractionDigits="3" value="${allTxn}"/>
        </p>
        <span>
                <c:choose>
                    <c:when test="${percent == 0}">
                        <%--do nothing    --%>
                    </c:when>
                    <c:when test="${percent > 0}">
                        (<span style="color: #45ba55">
                            &uarr; <!--upwards arrow-->
                        </span>
                        <span style="color: #45ba55"><fmt:formatNumber maxFractionDigits="2" value="${percent}"/>&nbsp;%</span>)
                    </c:when>
                    <c:otherwise>
                        (<span style="color: red">
                            &darr; <!--upwards arrow-->
                        </span>
                        <span style="color: red"><fmt:formatNumber maxFractionDigits="2" value="${percent}"/>&nbsp;%</span>)
                    </c:otherwise>
                </c:choose>
        </span>
        <br>
        <p style="color: grey">
            <i>Transactions</i>
        </p>
    </div>
        <%--------------------------------%>
        <%--           success          --%>
        <%--------------------------------%>
    <div
            class="col-lg-2 col-xs-12"
            style="text-align: center; border-right: 3px solid grey;"
    >
        <p style="color: #45ba55; font-size: 18px">SUCCESS</p><br>
        <p style="font-weight: 900; font-size: 20px; color: #45ba55">
            <fmt:formatNumber maxFractionDigits="3" value="${success.numOfTrans}"/>
            <c:set value="${((success.numOfTrans - oldSuccess.numOfTrans) / (oldSuccess.numOfTrans eq 0 ? 1 : oldSuccess.numOfTrans)) * 100}" var="successPercent"/>
        </p>
        <span>
            <c:choose>
                <c:when test="${successPercent == 0}">
                    <%--do nothing        --%>
                </c:when>
                <c:when test="${successPercent > 0}">
                        (<span style="color: #45ba55">
                            &uarr; <!--upwards arrow-->
                        </span>
                    <span style="color: #45ba55"><fmt:formatNumber maxFractionDigits="2" value="${successPercent}"/>&nbsp;%</span>)
                </c:when>
                <c:otherwise>
                        (<span style="color: red">
                            &darr; <!--upwards arrow-->
                        </span>
                    <span style="color: red"><fmt:formatNumber maxFractionDigits="2" value="${successPercent}"/>&nbsp;%</span>)
                </c:otherwise>
            </c:choose>
        </span>
        <br>
        <p style="color: grey">
            <i>Transactions</i>
        </p>
    </div>
        <%--------------------------------%>
        <%--            fail            --%>
        <%--------------------------------%>
    <div
            class="col-lg-2 col-xs-12"
            style="text-align: center; border-right: 3px solid grey; color: red"
    >
        <p style="color: red; font-size: 18px">FAIL</p><br>
        <p style="font-weight: 900; font-size: 20px; color: red">
            <c:choose>
                <c:when test="${fail ne null}">
                    <fmt:formatNumber maxFractionDigits="3" value="${fail.numOfTrans}"/>
                </c:when>
                <c:otherwise>
                    0
                </c:otherwise>
            </c:choose>
            <c:set value="${((fail.numOfTrans - oldFail.numOfTrans) / (oldFail.numOfTrans eq 0 ? 1 : oldFail.numOfTrans)) * 100}" var="failPercent"/>
        </p>
        <span>
            <c:choose>
                <c:when test="${fail ne null}">
                    <c:choose>
                        <c:when test="${failPercent == 0}">
                            <%--do nothing        --%>
                        </c:when>
                        <c:when test="${failPercent > 0}">
                            (<span style="color: red">
                            &uarr; <!--upwards arrow-->
                            </span>
                            <span style="color: red"><fmt:formatNumber maxFractionDigits="2" value="${failPercent}"/>&nbsp;%</span>)
                        </c:when>
                        <c:otherwise>
                            (<span style="color: red">
                            &darr; <!--upwards arrow-->
                            </span>
                            <span style="color: red"><fmt:formatNumber maxFractionDigits="2" value="${failPercent}"/>&nbsp;%</span>)
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <%--        do nothing        --%>
                </c:otherwise>
            </c:choose>
        </span>
        <br>
        <p style="color: grey">
            <i>Transactions</i>
        </p>
    </div>
        <%--------------------------------%>
        <%--         processing         --%>
        <%--------------------------------%>
    <div
            class="col-lg-2 col-xs-12"
            style="text-align: center; border-right: 3px solid grey; color: #FC0"
    >
        <p style="color: #FC0; font-size: 18px">PROCESSING</p><br>
        <p style="font-weight: 900; font-size: 20px; color: #FC0">
            <fmt:formatNumber maxFractionDigits="3" value="${open.numOfTrans + pending.numOfTrans + processing.numOfTrans}"/>
            <c:set var="otherOldVal" value="${oldOpen.numOfTrans + oldPending.numOfTrans + oldProcessing.numOfTrans}"/>
            <c:set value="${(((open.numOfTrans + pending.numOfTrans + processing.numOfTrans) - (oldOpen.numOfTrans + oldPending.numOfTrans + oldProcessing.numOfTrans)) / (otherOldVal eq 0 ? 1: otherOldVal)) * 100}" var="otherPercent"/>
        </p>
        <span>
            <c:choose>
                <c:when test="${otherPercent == 0}">
                    <%--do nothing        --%>
                </c:when>
                <c:when test="${otherPercent > 0}">
                        (<span style="color: #45ba55">
                            &uarr; <!--upwards arrow-->
                        </span>
                    <span style="color: #45ba55"><fmt:formatNumber maxFractionDigits="2" value="${otherPercent}"/>&nbsp;%</span>)
                </c:when>
                <c:otherwise>
                        (<span style="color: red">
                            &darr; <!--upwards arrow-->
                        </span>
                    <span style="color: red"><fmt:formatNumber maxFractionDigits="2" value="${otherPercent}"/>&nbsp;%</span>)
                </c:otherwise>
            </c:choose>
        </span>
        <br>
        <p style="color: grey">
            <i>Transactions</i>
        </p>
    </div>
        <%--------------------------------%>
        <%--           amount           --%>
        <%--------------------------------%>
    <div
            class="col-lg-2 col-xs-12"
            style="text-align: center; border-right: 3px solid grey; color: grey"
    >
        <p style="color: grey; font-size: 18px">TPV</p><br>
        <p style="font-weight: 900; font-size: 20px; color: grey">
            <fmt:formatNumber maxFractionDigits="3" value="${success.sumOfRequestAmount}"/>
            <c:set value="${((success.sumOfRequestAmount - oldSuccess.sumOfRequestAmount) / (oldSuccess.sumOfRequestAmount eq 0 ? 1 : oldSuccess.sumOfRequestAmount)) * 100}" var="requestAmountPercent"/>
        </p>
        <span>
            <c:choose>
                <c:when test="${requestAmountPercent == 0}">
                        <%--do nothing--%>
                </c:when>
                <c:when test="${requestAmountPercent > 0}">
                        (<span style="color: #45ba55">
                            &uarr; <!--upwards arrow-->
                        </span>
                    <span style="color: #45ba55"><fmt:formatNumber maxFractionDigits="2" value="${requestAmountPercent}"/>&nbsp;%</span>)
                </c:when>
                <c:otherwise>
                        (<span style="color: red">
                            &darr; <!--upwards arrow-->
                        </span>
                    <span style="color: red"><fmt:formatNumber maxFractionDigits="2" value="${requestAmountPercent}"/>&nbsp;%</span>)
                </c:otherwise>
            </c:choose>
        </span>
        <br>
        <p style="color: grey">
            <i>VND</i>
        </p>
    </div>
        <%--------------------------------%>
        <%--           revenue          --%>
        <%--------------------------------%>
    <div
            class="col-lg-2 col-xs-12"
            style="text-align: center; color: #85bb65"
    >
        <p style="color: #85bb65; font-size: 18px">REVENUE</p><br>
        <p style="font-weight: 900; font-size: 20px; color: #85bb65">
            <fmt:formatNumber maxFractionDigits="3" value="${success.sumOfRevenueAmount}"/>
            <c:set value="${((success.sumOfRevenueAmount - oldSuccess.sumOfRevenueAmount) / (oldSuccess.sumOfRevenueAmount eq 0 ? 1 : oldSuccess.sumOfRevenueAmount)) * 100}" var="revenuePercent"/>
        </p>
        <span>
                <c:choose>
                    <c:when test="${revenuePercent == 0}">
                        <%--do nothing--%>
                    </c:when>
                    <c:when test="${revenuePercent > 0}">
                            (<span style="color: #45ba55">
                                &uarr; <!--upwards arrow-->
                            </span>
                        <span style="color: #45ba55"><fmt:formatNumber maxFractionDigits="2" value="${revenuePercent}"/>&nbsp;%</span>)
                    </c:when>
                    <c:otherwise>
                            (<span style="color: red">
                                &darr; <!--upwards arrow-->
                            </span>
                        <span style="color: red"><fmt:formatNumber maxFractionDigits="2" value="${revenuePercent}"/>&nbsp;%</span>)
                    </c:otherwise>
                </c:choose>
        </span>
        <br>
        <p style="color: grey">
            <i>VND</i>
        </p>
    </div>
</div>

<%--------------------------------%>
<%--   hidden summanry table    --%>
<%--------------------------------%>
<div class="">
    <a class="view-detail-summary" style="color: #2C8F39;
                        font-weight: bold">&gt;&gt; Chi tiet</a>
</div>
<div style="overflow-x: scroll; display: none" id="summary_tbl">
    <table class="table table-bordered table-striped mb-small mt-none">
    <thead>
    <tr>
        <th>TOTAL</th>
        <th style="color: #67a22c">SUCCESS</th>
        <th style="color: red">FAIL</th>
        <th style="color: #FC0">PROCESSING</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Number Of Transactions</td>
        <td>
            ${success.numOfTrans}
            <c:choose>
                <c:when test="${success.numOfTrans > oldSuccess.numOfTrans}">
                    <span style="color: blue">
                        &uarr; <!--upwards arrow-->
                    </span>
                </c:when>
                <c:when test="${success.numOfTrans < oldSuccess.numOfTrans}">
                    <span style="color: red">
                        &darr; <!-- downwards arrow-->
                    </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            ${fail.numOfTrans}
            <c:choose>
                <c:when test="${fail.numOfTrans > oldFail.numOfTrans}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${fail.numOfTrans < oldFail.numOfTrans}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            ${open.numOfTrans + pending.numOfTrans + processing.numOfTrans}
            <c:set var="othersNumOfTrans" value="${open.numOfTrans + pending.numOfTrans + processing.numOfTrans}"/>
            <c:set var="oldOthersNumOfTrans" value="${oldOpen.numOfTrans + oldPending.numOfTrans + oldProcessing.numOfTrans}"/>
            <c:choose>
                <c:when test="${othersNumOfTrans > oldOthersNumOfTrans}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${othersNumOfTrans < oldOthersNumOfTrans}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td>Sum Of Request Amount</td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${success.sumOfRequestAmount}"/>
            <c:choose>
                <c:when test="${success.sumOfRequestAmount > oldSuccess.sumOfRequestAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${success.sumOfRequestAmount < oldSuccess.sumOfRequestAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
        </c:choose>
        </td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${fail.sumOfRequestAmount}"/>
            <c:choose>
                <c:when test="${fail.sumOfRequestAmount > oldFail.sumOfRequestAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${fail.sumOfRequestAmount < oldFail.sumOfRequestAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${open.sumOfRequestAmount + pending.sumOfRequestAmount + processing.sumOfRequestAmount}"/>
            <c:set var="othersSumOfRequestAmount" value="${open.sumOfRequestAmount + pending.sumOfRequestAmount + processing.sumOfRequestAmount}"/>
            <c:set var="oldOthersSumOfRequestAmount" value="${oldOpen.sumOfRequestAmount + oldPending.sumOfRequestAmount + oldProcessing.sumOfRequestAmount}"/>
            <c:choose>
                <c:when test="${othersSumOfRequestAmount > oldOthersSumOfRequestAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${othersSumOfRequestAmount < oldOthersSumOfRequestAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td>Sum Of Fee Amount</td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${success.sumOfFeeAmount}"/>
            <c:choose>
                <c:when test="${success.sumOfFeeAmount > oldSuccess.sumOfFeeAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${success.sumOfFeeAmount < oldSuccess.sumOfFeeAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${fail.sumOfFeeAmount}"/>
            <c:choose>
                <c:when test="${fail.sumOfFeeAmount > oldFail.sumOfFeeAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${fail.sumOfFeeAmount < oldFail.sumOfFeeAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${open.sumOfFeeAmount + pending.sumOfFeeAmount + processing.sumOfFeeAmount}"/>
            <c:set var="othersSumOfFeeAmount" value="${open.sumOfFeeAmount + pending.sumOfFeeAmount + processing.sumOfFeeAmount}"/>
            <c:set var="oldOthersSumOfFeeAmount" value="${oldOpen.sumOfFeeAmount + oldPending.sumOfFeeAmount + oldProcessing.sumOfFeeAmount}"/>
            <c:choose>
                <c:when test="${othersSumOfFeeAmount > oldOthersSumOfFeeAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${othersSumOfFeeAmount < oldOthersSumOfFeeAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td>Sum Of Commission Amount</td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${success.sumOfCommissionAmount}"/>
            <c:choose>
                <c:when test="${success.sumOfCommissionAmount > oldSuccess.sumOfCommissionAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${success.sumOfCommissionAmount < oldSuccess.sumOfCommissionAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${fail.sumOfCommissionAmount}"/>
            <c:choose>
                <c:when test="${fail.sumOfCommissionAmount > oldFail.sumOfCommissionAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${fail.sumOfCommissionAmount < oldFail.sumOfCommissionAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${open.sumOfCommissionAmount + pending.sumOfCommissionAmount + processing.sumOfCommissionAmount}"/>
            <c:set var="othersSumOfCommissionAmount" value="${open.sumOfCommissionAmount + pending.sumOfCommissionAmount + processing.sumOfCommissionAmount}"/>
            <c:set var="oldOthersSumOfCommissionAmount" value="${oldOpen.sumOfCommissionAmount + oldPending.sumOfCommissionAmount + oldProcessing.sumOfCommissionAmount}"/>
            <c:choose>
                <c:when test="${othersSumOfCommissionAmount > oldOthersSumOfCommissionAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${othersSumOfCommissionAmount < oldOthersSumOfCommissionAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td>Sum Of Cashback Amount</td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${success.sumOfCashbackAmount}"/>
            <c:choose>
                <c:when test="${success.sumOfCashbackAmount > oldSuccess.sumOfCashbackAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${success.sumOfCashbackAmount < oldSuccess.sumOfCashbackAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${fail.sumOfCashbackAmount}"/>
            <c:choose>
                <c:when test="${fail.sumOfCashbackAmount > oldFail.sumOfCashbackAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${fail.sumOfCashbackAmount < oldFail.sumOfCashbackAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${open.sumOfCashbackAmount + pending.sumOfCashbackAmount + processing.sumOfCashbackAmount}"/>
            <c:set var="othersSumOfCashbackAmount" value="${open.sumOfCashbackAmount + pending.sumOfCashbackAmount + processing.sumOfCashbackAmount}"/>
            <c:set var="oldOthersSumOfCashbackAmount" value="${oldOpen.sumOfCashbackAmount + oldPending.sumOfCashbackAmount + oldProcessing.sumOfCashbackAmount}"/>
            <c:choose>
                <c:when test="${othersSumOfCashbackAmount > oldOthersSumOfCashbackAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${othersSumOfCashbackAmount < oldOthersSumOfCashbackAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td>Sum Of Net Amount</td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${success.sumOfNetAmount}"/>
            <c:choose>
                <c:when test="${success.sumOfNetAmount > oldSuccess.sumOfNetAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${success.sumOfNetAmount < oldSuccess.sumOfNetAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${fail.sumOfNetAmount}"/>
            <c:choose>
                <c:when test="${fail.sumOfNetAmount > oldFail.sumOfNetAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${fail.sumOfNetAmount < oldFail.sumOfNetAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${open.sumOfNetAmount + pending.sumOfNetAmount + processing.sumOfNetAmount}"/>
            <c:set var="othersSumOfNetAmount" value="${open.sumOfNetAmount + pending.sumOfNetAmount + processing.sumOfNetAmount}"/>
            <c:set var="oldOthersSumOfNetAmount" value="${oldOpen.sumOfNetAmount + oldPending.sumOfNetAmount + oldProcessing.sumOfNetAmount}"/>
            <c:choose>
                <c:when test="${othersSumOfNetAmount > oldOthersSumOfNetAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${othersSumOfNetAmount < oldOthersSumOfNetAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td>Sum Of Capital Value</td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${success.sumOfCapitalValue}"/>
            <c:choose>
                <c:when test="${success.sumOfCapitalValue > oldSuccess.sumOfCapitalValue}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${success.sumOfCapitalValue < oldSuccess.sumOfCapitalValue}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${fail.sumOfCapitalValue}"/>
            <c:choose>
                <c:when test="${fail.sumOfCapitalValue > oldFail.sumOfCapitalValue}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${fail.sumOfCapitalValue < oldFail.sumOfCapitalValue}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${open.sumOfCapitalValue + pending.sumOfCapitalValue + processing.sumOfCapitalValue}"/>
            <c:set var="othersSumOfCapitalValue" value="${open.sumOfCapitalValue + pending.sumOfCapitalValue + processing.sumOfCapitalValue}"/>
            <c:set var="oldOthersSumOfCapitalValue" value="${oldOpen.sumOfCapitalValue + oldPending.sumOfCapitalValue + oldProcessing.sumOfCapitalValue}"/>
            <c:choose>
                <c:when test="${othersSumOfCapitalValue > oldOthersSumOfCapitalValue}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${othersSumOfCapitalValue < oldOthersSumOfCapitalValue}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td>Sum Of Revenue Amount</td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${success.sumOfRevenueAmount}"/>
            <c:choose>
                <c:when test="${success.sumOfRevenueAmount > oldSuccess.sumOfRevenueAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${success.sumOfRevenueAmount < oldSuccess.sumOfRevenueAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${fail.sumOfRevenueAmount}"/>
            <c:choose>
                <c:when test="${fail.sumOfRevenueAmount > oldFail.sumOfRevenueAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${fail.sumOfRevenueAmount < oldFail.sumOfRevenueAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
        <td>
            <fmt:formatNumber maxFractionDigits="3" value="${open.sumOfRevenueAmount + pending.sumOfRevenueAmount + processing.sumOfRevenueAmount}"/>
            <c:set var="othersSumOfRevenueAmount" value="${open.sumOfRevenueAmount + pending.sumOfRevenueAmount + processing.sumOfRevenueAmount}"/>
            <c:set var="oldOthersSumOfRevenueAmount" value="${oldOpen.sumOfRevenueAmount + oldPending.sumOfRevenueAmount + oldProcessing.sumOfRevenueAmount}"/>
            <c:choose>
                <c:when test="${othersSumOfRevenueAmount > oldOthersSumOfRevenueAmount}">
                        <span style="color: blue">
                            &uarr; <!--upwards arrow-->
                        </span>
                </c:when>
                <c:when test="${othersSumOfRevenueAmount < oldOthersSumOfRevenueAmount}">
                        <span style="color: red">
                            &darr; <!-- downwards arrow-->
                        </span>
                </c:when>
            </c:choose>
        </td>
    </tr>
    </tbody>
</table>
</div>