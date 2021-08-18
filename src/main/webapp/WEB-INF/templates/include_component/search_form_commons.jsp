<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<script type="text/javascript">
    <c:if test="${param.selChannel eq 'true'}">
    $('#channel').multiselect({
        /*enableFiltering: true,*/
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.choose.channel"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.selPaymentChannel eq 'true'}">
    $('#channels').multiselect({
        /*enableFiltering: true,*/
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.choose.channel"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.selOrderStage eq 'true'}">
    $('#stage').multiselect({
        /*enableFiltering: true,*/
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.choose.forder.process"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.selServiceType eq 'true'}">
    $('#serviceTypeIds').multiselect({
        enableFiltering: true,
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.serviceType"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.selTxnStatus eq 'true'}">
    $('#txnStatusIds').multiselect({
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.status"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.selSourceOfFund eq 'true'}">
    $('#sourceOfFundTypeIds').multiselect({
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.source.of.fund.type"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.selWalletSourceOfFund eq 'true'}">
    <spring:message code="select.source.of.fund.type" var="walletSourceOfFundLabel"/>
    $('#walletSourceOfFundIds').multiselect({
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText:
            '${param.selWalletSourceOfFundLabel != null && '' ne param.selWalletSourceOfFundLabel
         ? param.selWalletSourceOfFundLabel : walletSourceOfFundLabel}',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.selCustomerType eq 'true'}">
    $('#idOwnerCustomerTypes').multiselect({
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.customerType"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.selCustomer eq 'true'}">
    var isFilter = '<c:out value="${param.isFilter}"/>';
    $('#customerIds').multiselect({
        onDropdownHidden: function (event) {
            if (isFilter === 'true') {
                $('#tbl-filter').submit();
            }
        },
        enableFiltering: true,
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.account"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    var customerIds = [];
    <c:forEach var="item" items="${paramValues.multiselect}">
    customerIds.push('${item}');
    </c:forEach>
    if (customerIds != '' && customerIds.length > 0) {
        $('.multiple-select').find('option').each(
            function () {
                if ($.inArray($(this).val(), customerIds) > -1) {
                    $('.multiple-select').multiselect('select', $(this).val(), true);
                } else {
                    $('.multiple-select').multiselect('deselect', $(this).val());
                }
            }
        );
    }
    </c:if>

    <c:if test="${param.selProviderType eq 'true'}">
    $('#provider').multiselect({
        enableFiltering: true,
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.provider"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.selSourceMerchant eq 'true'}">
    $('#sourceMerchant').multiselect({
        enableFiltering: true,
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.choose.payer"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    var sourceMerchant = [];
    <c:forEach var="item" items="${paramValues.sourceMerchant}">
    sourceMerchant.push('${item}');
    </c:forEach>
    if (sourceMerchant != '' && sourceMerchant.length > 0) {
        $('.multiple-select').find('option').each(
            function () {
                if ($.inArray($(this).val(), sourceMerchant) > -1) {
                    $('.multiple-select').multiselect('select', $(this).val(), true);
                } else {
                    $('.multiple-select').multiselect('deselect', $(this).val());
                }
            }
        );
    }
    </c:if>

    <c:if test="${param.selTargetMerchant eq 'true'}">
    $('#targetMerchant').multiselect({
        enableFiltering: true,
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.choose.payee"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    var targetMerchant = [];
    <c:forEach var="item" items="${paramValues.targetMerchant}">
    targetMerchant.push('${item}');
    </c:forEach>
    if (targetMerchant != '' && targetMerchant.length > 0) {
        $('.multiple-select').find('option').each(
            function () {
                if ($.inArray($(this).val(), targetMerchant) > -1) {
                    $('.multiple-select').multiselect('select', $(this).val(), true);
                } else {
                    $('.multiple-select').multiselect('deselect', $(this).val());
                }
            }
        );
    }
    </c:if>

    <c:if test="${param.selTransferType eq 'true'}">
    $('#transferTypeIds').multiselect({
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.choose.servicetype"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.selVerifiableSatus eq 'true'}">
    $('#verifiableSatus').multiselect({
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.choose.servicetype"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.cusWalletTypeCb eq 'true'}">
    $('#walletTypeComb').multiselect({
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.choose.wallettype"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.selBlockType eq 'true'}">
    $('#blockType').multiselect({
        enableFiltering: true,
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="label.blockType"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.selKycType eq 'true'}">
    $('#kycRequestStatus').multiselect({
        enableFiltering: true,
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="select.status"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.selectServiceCodes eq 'true'}">
    $('#serviceCodes').multiselect({
        enableFiltering: true,
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="transaction.log.service"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.selectRankingGroups eq 'true'}">
    $('#rankingGroups').multiselect({
        enableFiltering: true,
        includeSelectAllOption: true,
        selectAllValue: '',
        selectAllText: '<spring:message code="select.choose.all"/>',
        maxHeight: 400,
        dropUp: true,
        nonSelectedText: '<spring:message code="label.ranking.group"/>',
        inheritClass: true,
        numberDisplayed: 1
    });
    </c:if>

    <c:if test="${param.paymentChannelId eq 'true'}">
    $('#paymentChannelId').multiselect({});
    </c:if>

    <%--<c:if test="${param.selCustomerBlock eq 'true'}">--%>
    <%--$('#customerIdsBlock').multiselect({--%>
    <%--enableFiltering: true,--%>
    <%--includeSelectAllOption: true,--%>
    <%--selectAllValue: '',--%>
    <%--selectAllText: '<spring:message code="select.choose.all"/>',--%>
    <%--maxHeight: 400,--%>
    <%--dropUp: true,--%>
    <%--nonSelectedText: '<spring:message code="select.account"/>',--%>
    <%--inheritClass: true,--%>
    <%--numberDisplayed: 1--%>
    <%--});--%>
    <%--</c:if>--%>

</script>

