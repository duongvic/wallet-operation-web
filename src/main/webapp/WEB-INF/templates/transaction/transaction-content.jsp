<%@ include file="../include_page/taglibs.jsp" %>

<spring:message code="fundin.search.placeholder" var="textPlaceHolder" scope="request"/>
<sec:authorize access="hasAnyRole('SALE_EXCUTIVE')" var="permitSaleExcutive"/>

<sec:authorize
        access="hasAnyRole('SALE_DIRECTOR','SALE_EXCUTIVE', 'SALE_SUPERVISOR' , 'SALE_ASM', 'SALE_RSM')"
        var="perActionNotGetAllAccount"/>

<section class="panel search_payment panel-default">
    <div class="panel-body pt-none">
        <form action="" method="GET" id="tbl-filter" class="mb-md" modelAttribute="searchDataForm">
            <div class="form-group ml-none mr-none">
                <%--<div class="input-group input-group-icon">--%>
                <%--<span class="input-group-addon"><span class="icon" style="opacity: 0.4"><i--%>
                <%--class="fa fa-search-minus"></i></span></span>--%>
                <%--<input type="text" id="quickSearch" name="quickSearch" class="form-control"--%>
                <%--placeholder="${textPlaceHolder}" value="${param.quickSearch }"/>--%>
                <%--</div>--%>

                <div class="form-group">
                    <div class="row">
                        <div class="col-md-3 mb-md">
                            <select data-plugin-selectTwo
                                    class="form-control"
                                    id="textSearchTypes"
                                    name="textSearchTypes">
                                <c:forEach var="item"
                                           items="${listTransactionTextSearchType}">
                                    <option value="${item.value()}" ${item.selectedTransactionTextSearchType(textSearchTypes)}>
                                        <spring:message code="${item.displayText()}"/></option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-9">
                            <input type="text" id="quickSearch"
                                   name="quickSearch"
                                   class="form-control"
                                   placeholder="${textPlaceHolder}"
                                   value="${param.quickSearch }"/>
                        </div>
                    </div>


                </div>

            </div>

            <%--<c:if test="${!perActionNotGetAllAccount}">--%>
            <%--<select  class="form-control"  id="dropDownCustomerIds" name="customerIds">--%>
            <%--<option value=""><spring:message code="select.account"/></option>--%>
            <%--</select>--%>
            <select class="js-data-example-ajax-account" multiple="multiple" name="customerIds"
                    style="margin-bottom: 6px">
            </select>
            <%--</c:if>--%>

            <div class="form-inline" style="margin-top: 6px">

                <jsp:include page="../include_component/date_range.jsp"/>

                <c:if test="${param.isUserSearchSourceOfFundType}">
                    <jsp:include page="../include_component/search_source_of_fund_type.jsp"/>
                </c:if>

                <c:if test="${param.isUserSearchWalletSourceOfFund}">
                    <jsp:include
                            page="../include_component/search_wallet_source_of_fund_type.jsp"/>
                </c:if>

                <c:if test="${param.permitSearchStaff && !permitSaleExcutive}">
                    <jsp:include page="../include_component/search_provider_type.jsp"/>
                </c:if>

                <%--<c:if test="${perActionNotGetAllAccount}">--%>
                <%--<jsp:include page="../include_component/search_customer.jsp"/>--%>
                <%--</c:if>--%>


                <c:if test="${param.permitSearchCusType}">
                    <jsp:include page="../include_component/search_customer_type.jsp"/>
                </c:if>

                <jsp:include page="../include_component/search_service_type_multiple.jsp">
                    <jsp:param name="enableFiltering" value="false"/>
                </jsp:include>

                <jsp:include page="../include_component/search_transaction_status.jsp"/>

                <select name="paymentChannelId" id="paymentChannelId" class="form-control hidden">
                    <option value=""><spring:message
                            code="label.payment.channel"/></option>
                    <c:forEach var="item" items="${paymentChannels}">
                        <option ${item.code eq param.paymentChannelId ? 'selected' : ''}
                                value="${item.code}">${item.displayText}</option>
                    </c:forEach>
                </select>
                <div class="clearfix"></div>
                <div class='pull-right form-responsive bt-plt'>


                    <input name="idOwnerCustomerTypes" type="hidden"
                           value="${param.idOwnerCustomerTypes}"/>

                    <button type="button" style="color: #33333F"
                            class="btn btn-default ml-tiny"
                            onclick="refreshForm()"><spring:message code="common.btn.cancel"/>
                    </button>

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
            <spring:message code="dashboard.commission"/>&nbsp;<span
                class="primary_color text-semibold">${ewallet:formatNumber(commision)}</span>&nbsp;${tCurrencyType}&nbsp;|&nbsp;
            <spring:message code="transaction.export.detail.summary.row.fee"/>&nbsp;<span
                class="primary_color text-semibold">${ewallet:formatNumber(totalFee)}</span>&nbsp;${tCurrencyType}&nbsp;|&nbsp;
            <spring:message code="transaction.log.real.amount"/>&nbsp;<span
                class="primary_color text-semibold">${ewallet:formatNumber(realAmount)}</span>&nbsp;${tCurrencyType}&nbsp;|&nbsp;

            <%--<spring:message code="transaction.log.commission"/>&nbsp;<span--%>
            <%--class="primary_color text-semibold">${ewallet:formatNumber(commision)}</span>&nbsp;${tCurrencyType}--%>
            <sec:authorize access="hasAnyRole('ADMIN_OPERATION',
                'ACCOUNTING','TECHSUPPORT',
                'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE',
                'MERCHANT','CUSTOMER',
                'CUSTOMERCARE_MANAGER','CUSTOMERCARE',
                'SALE','SALE_ASM','PROVIDER',
                'SALESUPPORT_LEADER','SALESUPPORT_MANAGER','SALESUPPORT')" var="tranlogsRoleWithoutSaleExecutive"/>

            <c:if test="${tranlogsRoleWithoutSaleExecutive}">
                <c:if test="${'transaction_wallet' ne param.transactionType}">
                    &nbsp;|&nbsp;<sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR',
                                      'SALESUPPORT_LEADER', 'SALESUPPORT', 'FINANCE',
                                      'RECONCILIATION', 'RECONCILIATION_LEADER')">
                    <spring:message
                            code="transaction.log.capitalValue"/>&nbsp;<span
                    class="primary_color text-semibold">${ewallet:formatNumber(totalCapitalValue)}</span>&nbsp;${tCurrencyType}&nbsp;|&nbsp;
                </sec:authorize>
                    <spring:message code="transaction.log.gross.profit"/>&nbsp;<span
                    class="primary_color text-semibold">${ewallet:formatNumber(totalGrossProfit)}</span>&nbsp;${tCurrencyType}&nbsp;|&nbsp;
                    <spring:message code="transaction.log.gross.profit.percent"/>&nbsp;<span
                    class="primary_color text-semibold">${percentGrossProfit}%</span>|&nbsp;
                </c:if>

                <%--<c:if test="${hasBillPayment eq true}">--%>
                <spring:message
                        code="transaction.log.cash.back.amount"/>&nbsp;<span
                class="primary_color text-semibold">${ewallet:formatNumber(totalCashBack)}</span>&nbsp;${tCurrencyType}&nbsp;|&nbsp;
                <spring:message
                        code="transaction.log.after.cash.back.amount"/>&nbsp;<span
                class="primary_color text-semibold">${ewallet:formatNumber(afterCashBackAmount)}</span>&nbsp;${tCurrencyType}&nbsp;|&nbsp;
                <spring:message
                        code="transaction.log.percent.after.cash.back.amount"/>&nbsp;<span
                class="primary_color text-semibold">${percentGrossProfitAfterCashBack}%</span>&nbsp;|&nbsp;
            </c:if>
            <spring:message code="transaction.log.rose.amount"/>&nbsp;<span
                class="primary_color text-semibold">${ewallet:formatNumber(roseAmount)}</span>&nbsp;${tCurrencyType}
            <%--</c:if>--%>
        </div>

        <div class="clearfix"></div>

        <jsp:include page="transaction_table.jsp">
            <jsp:param name="transactionType" value="${param.transactionType}"/>
        </jsp:include>
    </div>
</section>

<script type="text/javascript"
        src="<c:url value="/assets/development/js/utils.js"/>"></script>

<script>
    $(document).ready(function () {
        fetchDataCus();
        console.log($('.js-data-example-ajax-account').val());
    });

    function refreshForm() {
        var multiSelects = ['sourceOfFundTypeIds', 'provider', 'serviceTypeIds', 'txnStatusIds', 'walletSourceOfFundIds', 'idOwnerCustomerTypes'];
        var select2 = ['textSearchTypes', 'customerIds'];
        var inputs = ['quickSearch'];
        clearDataOf(multiSelects, select2, inputs);
        var urlBase = location.origin + location.pathname;
        location = urlBase + '?' + $('#tbl-filter').serialize();
    }

    function fetchDataCus() {
        var data = [];
        <c:forEach var="customer" items="${customers}">
        data.push({id: ${customer.id}, text: '${customer.fullName}'});
        </c:forEach>
        for (var i = 0; i < data.length; i++) {
            var $option = $("<option selected></option>").val(data[i].id).text(data[i].text);
            $('.js-data-example-ajax-account').append($option);
        }
        $('.js-data-example-ajax-account').select2({
            width: "100%",
            dropdownAutoWidth: true,
            ajax: {
                url: ctx + "/ajax-controller/all/account",
                dataType: 'json',
                type: "POST",
                data: function (params) {
                    var query = {
                        search: params.term,
                        type: 'public'
                    }

                    // Query parameters will be ?search=[term]&type=public
                    return query;
                },
                // Additional AJAX parameters go here; see the end of this chapter for the full code of this example
                processResults: function (data) {
                    // Transforms the top-level key of the response object from 'items' to 'results'
                    var retVal = [];
//        for (var i = 0; i < data.length; i++) {
                    var lineObj = {
                        id: data.id,
                        text: data.fullName
                    }
                    retVal.push(lineObj);
//        }
                    return {
                        // results: data.items
                        results: retVal

                    };
                }
            },
            placeholder: '<spring:message code="label.input.phone"/>',
            minimumInputLength: 4,
            language: {
                inputTooShort: function () {
                    return '<spring:message code="label.input.10.character"/>';
                }
            }
        });
    }

    <%--$("#dropDownCustomerIds").click(function (e) {--%>
    <%--//alert('click');--%>
    <%--$.ajax({--%>
    <%--type: "POST",--%>
    <%--url: ctx + "/ajax-controller/all/account",--%>
    <%--success: function (data) {--%>
    <%--helpers.buildDropdown(--%>
    <%--data,--%>
    <%--$('#dropDownCustomerIds'),--%>
    <%--'<spring:message code="select.account"/>'--%>
    <%--);--%>
    <%--}--%>
    <%--});--%>
    <%--});--%>

    <%--var helpers =--%>
    <%--{--%>
    <%--buildDropdown: function (result, dropdown, emptyMessage) {--%>
    <%--// Remove current options--%>
    <%--dropdown.html('');--%>
    <%--// Add the empty option with the empty message--%>
    <%--dropdown.append('<option value="">' + emptyMessage + '</option>');--%>
    <%--// Check result isnt empty--%>
    <%--if (result != '') {--%>
    <%--// Loop through each of the results and append the option to the dropdown--%>
    <%--$.each(result, function (k, v) {--%>
    <%--dropdown.append('<option value="' + v.id--%>
    <%--+ '" ${(param.customerIds eq v.id) ? 'selected' : ''}>'--%>
    <%--+ v.fullName + ' - '--%>
    <%--+ v.msisdn + '</option>');--%>
    <%--});--%>
    <%--}--%>
    <%--}--%>
    <%--}--%>

</script>