<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 1/8/2021
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page
        import="static vn.mog.ewallet.operation.web.constant.SharedConstants.SEN_PAY_TRANSACTION" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<spring:message code="label.u.are.perform.inactive" var="lblTxtInActive"/>
<spring:message code="label.u.are.perform.active" var="lblTxtActive"/>
<c:set var="SEN_PAY_TRANSACTION" value="<%=SEN_PAY_TRANSACTION%>"/>
<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
    <meta charset="UTF-8">
    <title><spring:message code="provider.title.header"/></title>
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
        <jsp:param name="tableLib" value="true"/>
    </jsp:include>
    <jsp:include page="../include_page/js_merchant.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>


    <style>
        .levelTwoWarning {
            background-color: #ff2514 !important;
        }

        #myTable_paginate {
            float: right;
        }
    </style>

</head>

<sec:authorize access="hasAnyRole('ADMIN_OPERATION')" var="roleAdmin"/>
<body>
<section class="body">
    <jsp:include page="../include_page/header.jsp"/>

    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="provider-special" name="nav"/>
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
                                <li><span class="nav-active"><spring:message
                                        code="menu.left.provider.special"/></span></li>
                                <li><span><spring:message
                                        code="setting.account.list.subnavigate"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>
            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">
                    <div class="h4 mb-md"></div>
                    <section class="panel search_payment panel-default">
                        <div class="panel-body pt-none">

                            <form action="" method="GET" id="search-provider">
                                <input type="hidden" name="pProService" value="">
                                <spring:message code="label.search.by.phone"
                                                var="placeholderSearchPhone"/>

                                <div>
                                    <!-- Nav tabs -->
                                    <ul class="nav nav-tabs">
                                        <li><a href="${pageContext.request.contextPath}/provider/special/bill_senpay/list">QL
                                            TÀI KHOẢN </a></li>
                                        <li class="active"><a style="text-transform: uppercase;"
                                                              href="${pageContext.request.contextPath}/provider/special/bill_senpay/by-province/list">
                                            <spring:message code="label.province.bill"/>
                                        </a></li>
                                        <li><a data-toggle="tab" href="#tab_menu1">ĐỐI SOÁT THEO
                                            NGÀY</a></li>
                                        <li><a data-toggle="tab" href="#tab_menu2">ĐỐI SOÁT THEO
                                            TK</a></li>

                                    </ul>

                                    <!-- Tab panes -->
                                    <div class="tab-content">
                                        <%--TAB 1--%>
                                        <%--TAB 2--%>
                                        <div class="tab-pane active" id="tab_province_mng">
                                            <section class="panel search_payment panel-default">
                                                <div class="panel-body pt-none">
                                                    <sec:authorize
                                                            access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_LEADER', 'SALESUPPORT_MANAGER' , 'SALESUPPORT')"
                                                            var="perActionSwitch"/>
                                                    <spring:message var="colTransaction"
                                                                    code="table.senpay.transaction"/>
                                                    <spring:message var="colPhone"
                                                                    code="setting.account.tbl.col.phone"/>
                                                    <spring:message var="colTotalBalance"
                                                                    code="table.total.balance"/>
                                                    <spring:message var="colStatus"
                                                                    code="setting.account.tbl.col.status"/>
                                                    <spring:message var="colAction"
                                                                    code="setting.account.tbl.col.action"/>
                                                    <spring:message var="status"
                                                                    code="transfer.detail.status"/>
                                                    <div class="table-responsive">
                                                        <table class="table table-bordered table-striped mb-none"
                                                               width="100%" id="myTable">
                                                            <thead>
                                                            <tr style="background-color:#D2EBC3">
                                                                <th><spring:message code="label.location"/></th>
                                                                <th><spring:message code="label.location.code"/></th>
                                                                <th><spring:message code="label.province.name"/></th>
                                                                <th><spring:message code="label.electric.code"/></th>
                                                                <th style="text-align: center">${status}</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <c:forEach var="specialProviderAllowedProvince"
                                                                       items="${specialProviderAllowedProvinces}">
                                                                <tr>
                                                                    <td>${specialProviderAllowedProvince.region}</td>
                                                                    <td>${specialProviderAllowedProvince.regionCode}</td>
                                                                    <td>${specialProviderAllowedProvince.provinceName}</td>
                                                                    <td>${specialProviderAllowedProvince.provinceElectricityCode}</td>
                                                                    <td style="text-align: center">
                                                                        <label class="switch"
                                                                               style="margin: 0 3px;"
                                                                               data-placement="top"
                                                                               title=""
                                                                               data-original-title=""
                                                                               onclick="changeStatus('${specialProviderAllowedProvince.provinceElectricityCode}', '${specialProviderAllowedProvince.regionCode}', '${specialProviderAllowedProvince.active}')">
                                                                            <input type="checkbox" ${specialProviderAllowedProvince.active ? "checked" : ""}
                                                                                   disabled>
                                                                            <span class="slider round"></span>
                                                                        </label>

                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </section>

                                        </div>
                                        <%--TAB 3--%>
                                        <div class="tab-pane" id="tab_menu1">
                                        </div>

                                        <%--TAB 4--%>
                                        <div class="tab-pane" id="tab_menu2">.ĐỐI SOÁT TÀI KHOẢN..
                                        </div>

                                    </div>

                                </div>

                            </form>


                        </div>
                    </section>
                </div>
            </div>

            <jsp:include page="action_change_status_acount_senPay.jsp"/>
            <jsp:include page="action_delete_acount_senPay.jsp"/>

            <!-- end: page -->
        </section>
        <jsp:include page="../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>


<script type="text/javascript"
        src="<c:url value='/assets/javascripts/provider-special-custom.js'/>"></script>
<spring:message code="data.table.header.paging.showing" var="paging_showing"/>
<spring:message code="data.table.header.paging.to" var="paging_to"/>
<spring:message code="data.table.header.paging.of" var="paging_of"/>
<spring:message code="data.table.header.paging.entries" var="paging_entries"/>
<spring:message code="data.table.header.paging.previous" var="paging_previous"/>
<spring:message code="data.table.header.paging.next" var="paging_next"/>
<spring:message code="data.table.header.paging.records" var="paging_records"/>

<script type="text/javascript">
    $(document).ready(function () {
        $('#myTable').dataTable({
            language: {
                "sInfo": "${paging_showing} _START_ ${paging_to} _END_ ${paging_of} _TOTAL_ ${paging_entries}",
                "sLengthMenu": "_MENU_ ${paging_records}",
                "paginate": {
                    "previous": "${paging_previous}",
                    "next": "${paging_next}"
                }
            },
            pageLength: 63,
            dom: 'Bfrtip'
        });
    });

    function changeStatus(province, regionCode, status) {
        let active = status === 'true' ? true : false;
        $.MessageBox({
            buttonDone: '<spring:message code="popup.button.yes"/>',
            buttonFail: '<spring:message code="popup.button.no"/>',
            message: "<spring:message code="label.change.status"/> " + regionCode + " " + province + " <spring:message code="label.return"/> " + !active + " ?"
        }).done(function () {
            let actionType = status === 'true' ? 'DELETE' : 'ADD'
            $.ajax({
                url: ctx + '/ajax-controller/sendPay/province/change',
                method: 'POST',
                data: {
                    province: province,
                    regionCode: regionCode,
                    actionType: actionType,
                },
                success: function () {
                    $.MessageBox({message: 'Success!'})
                    setTimeout(function () {
                        location.reload();
                    }, 1500);
                },
                error: function (result) {
                    $.MessageBox({message: result?.responseJSON?.status?.value})
                }
            });
        });
    }

    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href") // activated tab
    });

</script>

</body>

</html>

