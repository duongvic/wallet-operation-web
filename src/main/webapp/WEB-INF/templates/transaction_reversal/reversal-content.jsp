<%@ include file="../include_page/taglibs.jsp" %>

<spring:message code="fundin.search.placeholder" var="textPlaceHolder" scope="request"/>

<section class="panel search_payment panel-default">
    <div class="panel-body pt-none">
        <form action="" method="GET" id="tbl-filter" class="mb-md" modelAttribute="searchDataForm">
            <div class="form-group ml-none mr-none">
                <div class="input-group input-group-icon">
                    <span class="input-group-addon"><span class="icon" style="opacity: 0.4"><i
                            class="fa fa-search-minus"></i></span></span>
                    <input type="text" id="quickSearch" name="quickSearch" class="form-control mb-xs"
                           placeholder="${textPlaceHolder}" value="${param.quickSearch }"/>
                        <jsp:include page="../include_component/action_find_customers.jsp"/>
                </div>
            </div>

            <div class="form-inline">

                <jsp:include page="../include_component/date_range.jsp"/>

                <div class='pull-right form-responsive bt-plt'>
                    <%--<c:if test="${param.isUserSearchSourceOfFundType}">--%>
                        <%--<jsp:include page="../include_component/search_source_of_fund_type.jsp"/>--%>
                    <%--</c:if>--%>

                    <%--<c:if test="${param.isUserSearchWalletSourceOfFund}">--%>
                        <%--<jsp:include--%>
                                <%--page="../include_component/search_wallet_source_of_fund_type.jsp"/>--%>
                    <%--</c:if>--%>

                    <%--<c:if test="${param.permitSearchStaff}">--%>
                        <%--<jsp:include page="../include_component/search_provider_type.jsp"/>--%>
                    <%--</c:if>--%>

                    <%--<c:if test="${param.permitSearchSales}">--%>
                        <%--<jsp:include page="../include_component/search_customer.jsp"/>--%>
                    <%--</c:if>--%>

                    <%--<c:if test="${param.permitSearchCusType}">--%>
                        <%--<jsp:include page="../include_component/search_customer_type.jsp"/>--%>
                    <%--</c:if>--%>

                    <%--<jsp:include page="../include_component/search_service_type_multiple.jsp">--%>
                        <%--<jsp:param name="enableFiltering" value="false"/>--%>
                    <%--</jsp:include>--%>

                    <jsp:include page="../include_component/search_transaction_status.jsp"/>

                    <input name="idOwnerCustomerTypes" type="hidden"
                           value="${param.idOwnerCustomerTypes}"/>

                    <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i>&nbsp;<spring:message
                            code="transaction.api.button.search"/></button>
                    <a href="${txnControlUri}/export" id="export_do"
                       class="btn btn-default export_link"><i
                            class="fa fa-download "></i>&nbsp;<spring:message
                            code="transaction.log.export"/></a>
                </div>
            </div>
            <div class="clearfix"></div>
        </form>

        <spring:message code="transaction.api.table.currency"
                        var="tCurrencyType"/>
        <div>
            <spring:message code="transaction.api.navigate.transaction"/>&nbsp;
            <span class="primary_color text-semibold">${ewallet:formatNumber(total)}</span>&nbsp;|&nbsp;
            <spring:message code="transaction.log.request.amount"/>&nbsp;<span
                class="primary_color text-semibold">${ewallet:formatNumber(totalRequestAmount)}</span>&nbsp;${tCurrencyType}&nbsp;|&nbsp;
            <spring:message code="transaction.log.real.amount"/>&nbsp;<span
                class="primary_color text-semibold">${ewallet:formatNumber(realAmount)}</span>&nbsp;${tCurrencyType}

            <%--<spring:message code="transaction.log.commission"/>&nbsp;<span--%>
            <%--class="primary_color text-semibold">${ewallet:formatNumber(commision)}</span>&nbsp;${tCurrencyType}--%>

            <c:if test="${'transaction_wallet' ne param.transactionType}">
                &nbsp;|&nbsp;<sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR',
                                      'SALESUPPORT_LEADER', 'SALESUPPORT', 'FINANCE',
                                      'RECONCILIATION', 'RECONCILIATION_LEADER')">
                    <spring:message
                            code="transaction.log.capitalValue"/>&nbsp;<span
                    class="primary_color text-semibold">${ewallet:formatNumber(totalCapitalValue)}</span>&nbsp;${tCurrencyType}&nbsp;|&nbsp;
                </sec:authorize>
                <spring:message code="transaction.log.gross.profit"/>&nbsp;<span
                class="primary_color text-semibold">${ewallet:formatNumber(totalGrossProfit)}</span>&nbsp;${tCurrencyType}
            </c:if>
        </div>

        <div class="clearfix"></div>

        <jsp:include page="reversal_table.jsp">
            <jsp:param name="transactionType" value="${param.transactionType}"/>
        </jsp:include>
    </div>
</section>