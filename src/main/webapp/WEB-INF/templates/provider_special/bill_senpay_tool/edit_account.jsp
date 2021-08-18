<%@ page
        import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_CUSTOMER" %>
<%@ page
        import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_MERCHANT" %>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.beans.Location" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
    <!-- Basic -->
    <meta charset="UTF-8"/>
    <title><spring:message code="setting.account.role.title.page"/></title>
    <jsp:include page="../../include_page/head.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <jsp:include page="../../include_page/js_service.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
        <jsp:param name="dateLib" value="true"/>
    </jsp:include>
    <c:set var="TYPE_CUSTOMER" value="<%=ID_CUSTOMER%>"/>
    <c:set var="TYPE_MERCHANT" value="<%=ID_MERCHANT%>"/>

    <spring:message code="select.district" var="label_countyDistrict"/>
    <c:set var="DISTRICT" value="<%=Location.DISTRICT%>" scope="application"/>
    <c:set var="COMMUNE" value="<%=Location.COMMUE%>" scope="application"/>
</head>

<body>
<section class="body">
    <!-- /////// header ////////-->
    <jsp:include page="../../include_page/header.jsp"/>
    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../../include_page/navigation.jsp">
            <jsp:param value="bill-senpay-tool" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><span class="nav-active"><spring:message
                                        code="provider.list.providerProfile"/></span></li>
                                <li><span class="nav-active"><spring:message code="menu.left.bill.senpay.tool"/></span>
                                </li>
                                <li><span><spring:message code="setting.account.list.subnavigate"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>


            <jsp:include page="../../include_page/message_new.jsp"/>

            <sec:authorize
                    access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_MANAGER' , 'SALESUPPORT', 'CUSTOMERCARE_MANAGER', 'CUSTOMERCARE')"
                    var="permisAdminOperation"/>
            <sec:authorize
                    access="hasAnyRole('SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM')"
                    var="permisSale"/>
            <sec:authorize
                    access="hasAnyRole('FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')"
                    var="permisFinance"/>
            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">

                    <div class="form-inline hidden">
                        <div class="pull-left h4 mb-md mt-md panel-title"><spring:message
                                code="system.account.info.page.title"/></div>
                        <div class="pull-right form-responsive mb-10">
                            <%--<sec:authorize access="hasRole('ADMIN_OPERATION')">--%>
                            <%--</sec:authorize>--%>
                        </div>
                    </div>

                    <section class="panel search_payment panel-default">
                        <div class="panel-body pt-none">
                            <spring:message code="label.un.authenticated" var="unAuthenticated"/>
                            <spring:message code="labe.authenticated" var="authenticated"/>
                            <div class="tab-content">
                                <div id="tab-list-customer-senPay" class="tab-pane active">
                                    <!-- form search -->
                                    <form id="${'edit' eq edit_type ? 'form-account-detail' : 'form-account-create'}"
                                          method="POST"
                                          class="panel panel-default"
                                    <%--onsubmit="return saveAttribute()"--%>
                                    <%--enctype="multipart/form-data"--%>
                                    <%--modelAttribute="customerDataForm"--%>
                                          action="${contextPath}${'edit' eq edit_type ? '/provider/special/bill_senpay_tool/details/'.concat(account_object.accountName) : '/provider/special/bill_senpay_tool/add'}">
                                        <input type="hidden" name="${_csrf.parameterName}"
                                               value="${_csrf.token}"/>
                                        <input type="hidden" name="accountId" id="accountId"
                                               value="${account_object.accountName}">
                                        <input type="hidden" id="edit_type" name="edit_type"
                                               value="${edit_type}">

                                        <!-- Thông tin Ban quản lý-->
                                        <jsp:include page="edit_account_info.jsp"/>

                                    </form>
                                    <!-- end form search -->
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>

            <!-- end: page -->
        </section>
        <jsp:include page="../../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../../include_page/js_footer.jsp"/>

<script src="${contextPath}/assets/jquery.inputmask.bundle.min.js"></script>

<script type="text/javascript">
    (function ($) {
        'use strict';
        $(document).ready(function () {
            $(":input").inputmask();

            //load default disable input for account detail screen
            $('#form-account-detail').find(':input:not(:button:not([type=submit]))').prop(
                'disabled', true);
            $('#form-account-detail').find('[name=atb-save]').prop('disabled', false);
            $('#form-account-detail').find('.not-disable').each(function (index, item) {
                jQuery(item).prop('disabled', false)
            });
            $('#form-account-detail').find('[name=accountId]').prop('disabled', false);
            $('#form-account-detail').find('[name=_csrf]').prop('disabled', false);

            jQuery('#list-voucher').attr('disabled', false);
            jQuery('#list-finance-voucher').attr('disabled', false);
            <c:if test="${'edit' eq edit_type}">
            jQuery('#add-finance-voucher').attr('disabled', true);
            jQuery('#add-voucher').attr('disabled', true);
            </c:if>
        });

        var $limitNum = 255;
        $('textarea[name="account-about"]').keydown(function () {
            var $this = $(this);
            if ($this.val().length > $limitNum) {
                $this.val($this.val().substring(0, $limitNum));
            }
        });
    }(jQuery));

    jQuery('.dataTables_length').css('display', 'none');
    jQuery('.dataTables_filter').css('display', 'none');

    function editAccount() {

        jQuery('#serialNumber').attr('disabled', false);
        // jQuery('#voucherCode').attr('disabled', false);
        // jQuery('#voucherCode5M').attr('disabled', false);
        jQuery('#isActive').attr('disabled', false);
        jQuery('#password').attr('disabled', false);
        jQuery('#paymentPass').attr('disabled', false);
        jQuery('#serialNumber').attr('disabled', false);
        jQuery('#edit_type').attr('disabled', false);
        jQuery('#save_btn').attr('disabled', false);
        // jQuery('#financeVoucherCode').attr('disabled', false);
        // jQuery('#financeVoucherCode5M').attr('disabled', false);
        // jQuery('#financeVoucherCode500K').attr('disabled', false);
        <c:forEach var="financeVoucherRange" items="${account_object.financeVoucherRanges}">
        jQuery("input[name='finance-amount-${financeVoucherRange.amount}-${financeVoucherRange.voucherCode}']").attr('disabled', false);
        jQuery("input[name='finance-voucher-${financeVoucherRange.amount}-${financeVoucherRange.voucherCode}']").attr('disabled', false);
        </c:forEach>

        <c:forEach var="voucherRange" items="${account_object.voucherRanges}">
        jQuery("input[name='amount-${voucherRange.amount}-${voucherRange.voucherCode}']").attr('disabled', false);
        jQuery("input[name='voucher-${voucherRange.amount}-${voucherRange.voucherCode}']").attr('disabled', false);
        </c:forEach>

        jQuery('#add-finance-voucher').attr('disabled', false);
        jQuery('#add-voucher').attr('disabled', false);
    }

    $(document).ready(function () {

        $('#serviceTypeIds').attr('disabled', false);

        $('#serviceTypeIds').change(function () {
            var id = $('#serviceTypeIds').val();
            var detailURL = '';
            if (window.location.search.indexOf("?") >= 0) {
                if (window.location.search.indexOf("serviceTypeIds") >= 0) {
                    detailURL = window.location.pathname + replaceUrlParam(window.location.search,
                        'serviceTypeIds', id);
                } else {
                    detailURL = window.location.pathname + window.location.search + '&serviceTypeIds=' + id;
                }

            } else {
                detailURL = window.location.pathname + '?serviceTypeIds=' + id;
            }
            window.location.href = detailURL;
        });
    });

</script>

</body>

</html>
