<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 4/26/2021
  Time: 2:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include_page/taglibs.jsp" %>
<ul class="nav nav-tabs">
    <li role="presentation" onclick="setActive('serviceType')" id="serviceType" class="active"><a>Service Type</a></li>
    <li role="presentation" onclick="setActive('customerType')" id="customerType"><a>Customer Type</a></li>
    <%--<c:if test="${fn:length(customers) > 0}">--%>
        <li role="presentation" onclick="setActive('customer')" id="customer"><a>Customer</a></li>
    <%--</c:if>--%>
    <li role="presentation" onclick="setActive('providerType')" id="providerType"><a>Provider</a></li>
</ul>

<div style="overflow-x: scroll;" id="serviceType_tbl">
    <div class="col-lg-5 col-xs-12">
        <div  id="serviceTypePiechart2"></div>
        <div id="serviceTypePiechart3"></div>
        <div  id="serviceTypePiechart"></div>
    </div>
    <div class="col-lg-7 col-xs-12 mt-lg">
        <table class="table table-bordered table-striped mb-small mt-none">

            <thead>
            <tr>
                <th>Service Type</th>
                <th>Transactions</th>
                <th>TPV</th>
                <th>Revenue</th>
                <%--<th></th>--%>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="entry" items="${revenueByService}">
                    <c:if test="${entry.newData.sumOfRequestAmount ne 0}">
                        <tr>
                            <th>${entry.key}</th>
                            <th style="font-weight: normal;">
                                <fmt:formatNumber maxFractionDigits="3" value="${entry.newData.numOfTrans}"/>
                                <c:choose>
                                    <c:when test="${entry.newData.numOfTrans > entry.oldData.numOfTrans && entry.oldData.numOfTrans ne 0}">
                                        <span style="color: #45ba55">
                                            &uarr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.numOfTrans, entry.oldData.numOfTrans)}"/>%<!--upwards arrow-->
                                        </span>
                                    </c:when>
                                    <c:when test="${entry.newData.numOfTrans < entry.oldData.numOfTrans}">
                                        <span style="color: red">
                                            &darr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.numOfTrans, entry.oldData.numOfTrans)}"/>%<!-- downwards arrow-->
                                        </span>
                                    </c:when>
                                </c:choose>
                            </th>
                            <th style="font-weight: normal;">
                                <fmt:formatNumber maxFractionDigits="3" value="${entry.newData.sumOfRequestAmount}"/>
                                <c:choose>
                                    <c:when test="${entry.newData.sumOfRequestAmount > entry.oldData.sumOfRequestAmount && entry.oldData.sumOfRequestAmount ne 0}">
                                        <span style="color: #45ba55">
                                            &uarr;<fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.sumOfRequestAmount, entry.oldData.sumOfRequestAmount)}"/>% <!--upwards arrow-->
                                        </span>
                                    </c:when>
                                    <c:when test="${entry.newData.sumOfRequestAmount < entry.oldData.sumOfRequestAmount && entry.oldData.sumOfRequestAmount ne 0}">
                                        <span style="color: red">
                                            &darr;<fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.sumOfRequestAmount, entry.oldData.sumOfRequestAmount)}"/>% <!-- downwards arrow-->
                                        </span>
                                    </c:when>
                                </c:choose>
                            </th>
                            <th ${entry.newData.sumOfRevenueAmount >= 0 ? ' style="font-weight: normal;"' : ' style="font-weight: normal; color: red"'}>
                                <fmt:formatNumber maxFractionDigits="3" value="${entry.newData.sumOfRevenueAmount}"/>
                                <c:choose>
                                    <c:when test="${entry.newData.sumOfRevenueAmount > entry.oldData.sumOfRevenueAmount && entry.oldData.sumOfRevenueAmount ne 0}">
                                        <span style="color: #45ba55">
                                            &uarr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.sumOfRevenueAmount, entry.oldData.sumOfRevenueAmount)}"/>%<!--upwards arrow-->
                                        </span>
                                    </c:when>
                                    <c:when test="${entry.newData.sumOfRevenueAmount < entry.oldData.sumOfRevenueAmount && entry.oldData.sumOfRevenueAmount ne 0}">
                                        <span style="color: red">
                                            &darr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.sumOfRevenueAmount, entry.oldData.sumOfRevenueAmount)}"/>%<!-- downwards arrow-->
                                        </span>
                                    </c:when>
                                </c:choose>
                            </th>
                            <%--<th><fmt:formatNumber type="number" maxFractionDigits="3"--%>
                                                  <%--value="${entry.caculateLN()}"/> %</th>--%>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>

        </table>
    </div>
</div>

<div style="overflow-x: scroll; display: none" id="customerType_tbl">
    <div class="col-lg-5 col-xs-12">
        <div id="customerTypePieChart2"></div>
        <div id="customerTypePieChart3"></div>
        <div id="customerTypePieChart"></div>
    </div>
    <div class="col-lg-7 col-xs-12 mt-lg">
        <table class="table table-bordered table-striped mb-small mt-none">
            <thead>
            <tr>
                <th>Customer Type</th>
                <th>Transactions</th>
                <th>TPV</th>
                <th>Revenue</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="entry" items="${revenueByCus}">
                    <c:if test="${entry.newData.sumOfRequestAmount ne 0}">
                        <tr>
                            <th>${entry.key}</th>
                            <th style="font-weight: normal;">
                                <fmt:formatNumber maxFractionDigits="3" value="${entry.newData.numOfTrans}"/>
                                <c:choose>
                                    <c:when test="${entry.newData.numOfTrans > entry.oldData.numOfTrans && entry.oldData.numOfTrans ne 0}">
                                        <span style="color: #45ba55">
                                            &uarr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.numOfTrans, entry.oldData.numOfTrans)}"/>%<!--upwards arrow-->
                                        </span>
                                    </c:when>
                                    <c:when test="${entry.newData.numOfTrans < entry.oldData.numOfTrans && entry.oldData.numOfTrans ne 0}">
                                        <span style="color: red">
                                            &darr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.numOfTrans, entry.oldData.numOfTrans)}"/>%<!-- downwards arrow-->
                                        </span>
                                    </c:when>
                                </c:choose>
                            </th>
                            <th style="font-weight: normal;">
                                <fmt:formatNumber maxFractionDigits="3" value="${entry.newData.sumOfRequestAmount}"/>
                                <c:choose>
                                    <c:when test="${entry.newData.sumOfRequestAmount > entry.oldData.sumOfRequestAmount && entry.oldData.sumOfRequestAmount ne 0}">
                                        <span style="color: #45ba55">
                                            &uarr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.sumOfRequestAmount, entry.oldData.sumOfRequestAmount)}"/>%<!--upwards arrow-->
                                        </span>
                                    </c:when>
                                    <c:when test="${entry.newData.sumOfRequestAmount < entry.oldData.sumOfRequestAmount && entry.oldData.sumOfRequestAmount ne 0}">
                                        <span style="color: red">
                                            &darr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.sumOfRequestAmount, entry.oldData.sumOfRequestAmount)}"/>%<!-- downwards arrow-->
                                        </span>
                                    </c:when>
                                </c:choose>
                            </th>
                            <th ${entry.newData.sumOfRevenueAmount >= 0 ? ' style="font-weight: normal;"' : ' style="font-weight: normal; color: red"'}>
                                <fmt:formatNumber maxFractionDigits="3" value="${entry.newData.sumOfRevenueAmount}"/>
                                <c:choose>
                                    <c:when test="${entry.newData.sumOfRevenueAmount > entry.oldData.sumOfRevenueAmount && entry.oldData.sumOfRevenueAmount ne 0}">
                                        <span style="color: #45ba55">
                                            &uarr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.sumOfRevenueAmount, entry.oldData.sumOfRevenueAmount)}"/>%<!--upwards arrow-->
                                        </span>
                                    </c:when>
                                    <c:when test="${entry.newData.sumOfRevenueAmount < entry.oldData.sumOfRevenueAmount && entry.oldData.sumOfRevenueAmount ne 0}">
                                        <span style="color: red">
                                            &darr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.sumOfRevenueAmount, entry.oldData.sumOfRevenueAmount)}"/>%<!-- downwards arrow-->
                                        </span>
                                    </c:when>
                                </c:choose>
                            </th>
                            <%--<th><fmt:formatNumber type="number" maxFractionDigits="3"--%>
                                                  <%--value="${entry.caculateLN()}"/> %</th>--%>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%--<c:if test="${fn:length(customers) > 0}">--%>
<div style="overflow-x: scroll; display: none" id="customer_tbl">
    <div class="col-lg-5 col-xs-12">
        <div id="customerPieChart2"></div>
        <div id="customerPieChart3"></div>
        <div id="customerPieChart"></div>
    </div>
    <div class="col-lg-7 col-xs-12 mt-lg">
        <table class="table table-bordered table-striped mb-small mt-none display" id="my-cus-tbl">
            <thead>
            <tr>
                <th>Customer</th>
                <th>Transactions</th>
                <th>TPV</th>
                <th>Revenue</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="entry" items="${summaryByCustomer}">
                        <tr>
                            <th>${entry.key}</th>
                            <th style="font-weight: normal;">
                                <fmt:formatNumber maxFractionDigits="3" value="${entry.value.numOfTrans}"/>
                                <c:choose>
                                    <c:when test="${entry.value.numOfTrans > passSummaryByCustomer.get(entry.key).numOfTrans && passSummaryByCustomer.get(entry.key).numOfTrans ne 0}">
                                        <span style="color: #45ba55">
                                            &uarr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.value.numOfTrans, passSummaryByCustomer.get(entry.key).numOfTrans)}"/>%<!--upwards arrow-->
                                        </span>
                                    </c:when>
                                    <c:when test="${entry.value.numOfTrans < passSummaryByCustomer.get(entry.key).numOfTrans && passSummaryByCustomer.get(entry.key).numOfTrans ne 0}">
                                        <span style="color: red">
                                            &darr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.value.numOfTrans, passSummaryByCustomer.get(entry.key).numOfTrans)}"/>%<!-- downwards arrow-->
                                        </span>
                                    </c:when>
                                </c:choose>
                            </th>
                            <th style="font-weight: normal;">
                                <fmt:formatNumber maxFractionDigits="3" value="${entry.value.sumOfRequestAmount}"/>
                                <c:choose>
                                    <c:when test="${entry.value.sumOfRequestAmount > passSummaryByCustomer.get(entry.key).sumOfRequestAmount && passSummaryByCustomer.get(entry.key).sumOfRequestAmount ne 0}">
                                        <span style="color: #45ba55">
                                            &uarr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.value.sumOfRequestAmount, passSummaryByCustomer.get(entry.key).sumOfRequestAmount)}"/>%<!--upwards arrow-->
                                        </span>
                                    </c:when>
                                    <c:when test="${entry.value.sumOfRequestAmount < passSummaryByCustomer.get(entry.key).sumOfRequestAmount && passSummaryByCustomer.get(entry.key).sumOfRequestAmount ne 0}">
                                        <span style="color: red">
                                            &darr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.value.sumOfRequestAmount, passSummaryByCustomer.get(entry.key).sumOfRequestAmount)}"/>%<!-- downwards arrow-->
                                        </span>
                                    </c:when>
                                </c:choose>
                            </th>
                            <th ${entry.value.sumOfRevenueAmount >= 0 ? ' style="font-weight: normal;"' : ' style="font-weight: normal; color: red"'}>
                                <fmt:formatNumber maxFractionDigits="3" value="${entry.value.sumOfRevenueAmount}"/>
                                <c:choose>
                                    <c:when test="${entry.value.sumOfRevenueAmount > passSummaryByCustomer.get(entry.key).sumOfRevenueAmount && passSummaryByCustomer.get(entry.key).sumOfRevenueAmount ne 0}">
                                        <span style="color: #45ba55">
                                            &uarr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.value.sumOfRevenueAmount, passSummaryByCustomer.get(entry.key).sumOfRevenueAmount)}"/>%<!--upwards arrow-->
                                        </span>
                                    </c:when>
                                    <c:when test="${entry.value.sumOfRevenueAmount < passSummaryByCustomer.get(entry.key).sumOfRevenueAmount && passSummaryByCustomer.get(entry.key).sumOfRevenueAmount ne 0}">
                                        <span style="color: red">
                                            &darr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.value.sumOfRevenueAmount, passSummaryByCustomer.get(entry.key).sumOfRevenueAmount)}"/>%<!-- downwards arrow-->
                                        </span>
                                    </c:when>
                                </c:choose>
                            </th>
                        </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%--</c:if>--%>

<div style="overflow-x: scroll; display: none" id="providerType_tbl" class="row">
    <div class="col-lg-5 col-xs-12">
        <div id="providerTypePieChart2"></div>
        <div id="providerTypePieChart3"></div>
        <div id="providerTypePieChart"></div>
    </div>
    <div class="col-lg-7 col-xs-12 mt-lg">
        <table class="table table-bordered table-striped mb-small mt-none">
            <thead>
            <tr>
                <th>Provider</th>
                <th>Transactions</th>
                <th>TPV</th>
                <th>Revenue</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="entry" items="${revenueByProdvider}">
                    <c:if test="${entry.newData.sumOfRequestAmount ne 0}">
                        <tr>
                            <th>${entry.key}</th>
                            <th style="font-weight: normal;">
                                <fmt:formatNumber maxFractionDigits="3" value="${entry.newData.numOfTrans}"/>
                                <c:choose>
                                    <c:when test="${entry.newData.numOfTrans > entry.oldData.numOfTrans && entry.oldData.numOfTrans ne 0}">
                                        <span style="color: #45ba55">
                                            &uarr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.numOfTrans, entry.oldData.numOfTrans)}"/>%<!--upwards arrow-->
                                        </span>
                                    </c:when>
                                    <c:when test="${entry.newData.numOfTrans < entry.oldData.numOfTrans && entry.oldData.numOfTrans ne 0}">
                                        <span style="color: red">
                                            &darr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.numOfTrans, entry.oldData.numOfTrans)}"/>%<!-- downwards arrow-->
                                        </span>
                                    </c:when>
                                </c:choose>
                            </th>
                            <th style="font-weight: normal;">
                                <fmt:formatNumber maxFractionDigits="3" value="${entry.newData.sumOfRequestAmount}"/>
                                <c:choose>
                                    <c:when test="${entry.newData.sumOfRequestAmount > entry.oldData.sumOfRequestAmount && entry.oldData.sumOfRequestAmount ne 0}">
                                        <span style="color: #45ba55">
                                            &uarr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.sumOfRequestAmount, entry.oldData.sumOfRequestAmount)}"/>%<!--upwards arrow-->
                                        </span>
                                    </c:when>
                                    <c:when test="${entry.newData.sumOfRequestAmount < entry.oldData.sumOfRequestAmount && entry.oldData.sumOfRequestAmount ne 0}">
                                        <span style="color: red">
                                            &darr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.sumOfRequestAmount, entry.oldData.sumOfRequestAmount)}"/>%<!-- downwards arrow-->
                                        </span>
                                    </c:when>
                                </c:choose>
                            </th>
                            <th ${entry.newData.sumOfRevenueAmount >= 0 ? ' style="font-weight: normal;"' : ' style="font-weight: normal; color: red"'}>
                                <fmt:formatNumber maxFractionDigits="3" value="${entry.newData.sumOfRevenueAmount}"/>
                                <c:choose>
                                    <c:when test="${entry.newData.sumOfRevenueAmount > entry.oldData.sumOfRevenueAmount && entry.oldData.sumOfRevenueAmount ne 0}">
                                        <span style="color: #45ba55">
                                            &uarr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.sumOfRevenueAmount, entry.oldData.sumOfRevenueAmount)}"/>%<!--upwards arrow-->
                                        </span>
                                    </c:when>
                                    <c:when test="${entry.newData.sumOfRevenueAmount < entry.oldData.sumOfRevenueAmount && entry.oldData.sumOfRevenueAmount ne 0}">
                                        <span style="color: red">
                                            &darr; <fmt:formatNumber maxFractionDigits="2" value="${ewallet:getGrowthPercent(entry.newData.sumOfRevenueAmount, entry.oldData.sumOfRevenueAmount)}"/>%<!-- downwards arrow-->
                                        </span>
                                    </c:when>
                                </c:choose>
                            </th>
                            <%--<th><fmt:formatNumber type="number" maxFractionDigits="3"--%>
                                                  <%--value="${entry.caculateLN()}"/> %</th>--%>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
