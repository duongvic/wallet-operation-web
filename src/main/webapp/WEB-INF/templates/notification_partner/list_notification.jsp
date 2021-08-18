<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 6/29/2019
  Time: 10:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include_page/taglibs.jsp" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
    <!-- Basic -->
    <meta charset="UTF-8"/>
    <%--<title>Customer Verification Detail</title--%>>
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
        <jsp:param name="tableLib" value="true"/>
    </jsp:include>
    <jsp:include page="../include_page/js_merchant.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
        <jsp:param name="dateLib" value="true"/>
    </jsp:include>
    <style>
        #myTable_paginate {
            float: right;
        }

        th {
            text-transform: uppercase;
        }

        div.b {
            white-space: nowrap;
            width: 50px;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body>
<section class="body">
    <jsp:include page="../include_page/header.jsp"/>
    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="cus-type-noti-partner-ls" name="nav"/>
        </jsp:include>
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><span><spring:message code="common.customer"/></span></li>
                                <li><span><spring:message
                                        code="menu.left.customer.notification"/></span></li>
                                <li><span class="nav-active"><spring:message
                                        code="menu.left.customer.notification.list"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>
            <div class="content-body-wrap">
                <div class="container-fluid">
                    <section class="panel search_payment panel-default">
                        <div class="panel-body pt-none mt-md">
                            <form action="" method="GET" id="search-provider">
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>
                                <input type="hidden" name="pProService" value="">
                                <spring:message code="provider.form.placeholder" var="placeholder"/>
                                <div class="form-inline">
                                    <div class='form-responsive bt-plt'>
                                        <div style="float: left">
                                        <select class="form-control multiple-select" multiple="multiple"
                                                id="eventEnums">
                                            <c:forEach items="${eventEnumList}" var="eventEnum">
                                                <option value="${eventEnum}"><spring:message
                                                        code="${eventEnum.getName()}"/></option>
                                            </c:forEach>
                                        </select>
                                        <select class="form-control" id="sort">
                                            <option value="" disabled selected><spring:message
                                                    code="label.sort.by.created.date"/></option>
                                            <option value="DESC"><spring:message code="label.newest"/></option>
                                            <option value="ASC"><spring:message code="label.oldest"/></option>
                                        </select>
                                        <spring:message var="colRequestId" code="label.notification.code"/>
                                        <input class="form-control" id="requestId" placeholder="${colRequestId}">
                                        </div>
                                        <div style="float: right">
                                        <button type="button" id="search-btn" class="btn btn-primary"
                                                onclick="drawTable()"><i
                                                class="fa fa-search"></i>&nbsp;<spring:message
                                                code="common.btn.search"/></button>
                                        <a href="?" id="cancel-btn" class="btn nomal_color_bk bt-cancel"
                                           onclick="clearForm()"><spring:message
                                                code="common.btn.cancel"/></a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </section>
                </div>
                <div class="clearfix"></div>
                <div class="content-body-wrap">
                    <div class="container-fluid">
                        <spring:message var="colCreateTime"
                                        code="setting.account.tbl.col.created.at"/>
                        <spring:message var="colHeading"
                                        code="table.column.heading"/>
                        <spring:message var="colContent"
                                        code="table.column.content"/>
                        <spring:message var="serviceLbl"
                                        code="menu.left.service"/>
                        <spring:message var="serviceTypeLbl"
                                        code="transaction.api.table.service.type"/>
                        <spring:message var="colUpdate"
                                        code="label.update.notification"/>
                        <spring:message var="colAct" code="provider.service.table.action"/>
                        <spring:message var="colRefRequestId" code="label.notification.reference.code"/>
                        <spring:message var="colEventType" code="label.message.type"/>
                        <table class="table table-bordered table-responsive table-striped mb-none"
                               id="myTable" width="100%" style="margin-top: 0px">
                            <thead>
                            <th>#</th>
                            <th class="center" style="text-transform: uppercase;">${colAct}</th>
                            <th>${colCreateTime}</th>
                            <th>${colEventType}</th>
                            <th>${serviceLbl}</th>
                            <th>${serviceTypeLbl}</th>
                            <th>${colHeading}</th>
                            <th>${colContent}</th>
                            <th>${colRequestId}</th>
                            <th>${colRefRequestId}</th>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<spring:message code="data.table.header.paging.showing" var="paging_showing"/>
<spring:message code="data.table.header.paging.to" var="paging_to"/>
<spring:message code="data.table.header.paging.of" var="paging_of"/>
<spring:message code="data.table.header.paging.entries" var="paging_entries"/>
<spring:message code="data.table.header.paging.previous" var="paging_previous"/>
<spring:message code="data.table.header.paging.next" var="paging_next"/>
<spring:message code="data.table.header.paging.records" var="paging_records"/>

<script>
    $(document).ready(function () {
        drawTable();

        $('#eventEnums').multiselect({
            /*enableFiltering: true,*/
            includeSelectAllOption: true,
            selectAllValue: '',
            selectAllText: '<spring:message code="select.choose.all"/>',
            maxHeight: 400,
            dropUp: true,
            nonSelectedText: '<spring:message code="label.message.type"/>',
            inheritClass: true,
            numberDisplayed: 1
        });
    });

    $(document).ajaxStart(function (event, xhr, settings) {
        document.getElementById("search-btn").innerHTML = "Loading...";
        document.getElementById("search-btn").disabled = true;
        document.getElementById("cancel-btn").innerHTML = "Loading...";
        document.getElementById("cancel-btn").disabled = true;
    });
    $(document).ajaxComplete(function (event, xhr, settings) {
        document.getElementById("search-btn").innerHTML = "<spring:message code="button.search"/>";
        document.getElementById("search-btn").disabled = false;
        document.getElementById("cancel-btn").innerHTML = "<spring:message code="common.btn.cancel"/>";
        document.getElementById("cancel-btn").disabled = false;
    });

    function clearForm() {
        $('#eventEnums option:selected').each(function () {
            $(this).prop('selected', false);
        });
        $('#eventEnums').multiselect('refresh');
        $('#eventEnums').val(null).trigger('change');
    }

    Date.prototype.hmdmy = function () {
        var hh = this.getHours();
        var minute = this.getMinutes();
        var seconds = this.getSeconds();
        var dd = this.getDate();
        var mm = this.getMonth() + 1;

        return (hh > 9 ? '' : '0') + hh + ":" + (minute > 9 ? '' : '0') + minute + ":" + (seconds > 9 ? '' : '0') + seconds + "  " + (dd > 9
            ? ''
            : '0') + dd + "-" + (mm > 9 ? '' : '0') + mm + "-"
            + this.getFullYear();
    };

    function drawTable() {
        let eventEnums = $("#eventEnums").val();
        let requestId = $("#requestId").val();
        let sort = $("#sort").val();
        $('#myTable').dataTable({
            "scrollX": true,
            "paging": true,
            "processing": true,
            "serverSide": true,
            "iDisplayStart": 0,
            "pageLength": 20,
            "lengthMenu": [[10, 20, 50, -1], [10, 20, 50]],
            "searching": false,
            "language": {
                "sInfo": "${paging_showing} _START_ ${paging_to} _END_ ${paging_of} _TOTAL_ ${paging_entries}",
                "sLengthMenu": "_MENU_ ${paging_records}",
                "paginate": {
                    "previous": "${paging_previous}",
                    "next": "${paging_next}"
                },
            },
            dom: 'ftip',
            destroy: true,
            "ajax": {
                "url": ctx + "/ajax-controller/partner/notifications",
                "type": "POST",
                "data": {
                    eventEnums: eventEnums,
                    requestId: requestId,
                    sort: sort,
                },
                dataSrc: 'dataList',
            },
            "columns": [
                {
                    "data": null,
                    "render": function (data, type, full, meta) {
                        var index = meta.settings.oAjaxData.start + meta.row + 1;
                        return index;
                    }
                },
                {
                    "data": null,
                    "render": function (data, type, full, meta) {
                        return '<div class="center">' +
                            '<a class="mr-xs" title="${colUpdate}" href="${contextPath}/notification-partner/broadcast/update/' + data?.request_id + '">' +
                            '<i class="fa fa-reply" style="font-size: 2rem; color: #2582c4"></i>' +
                            '</a>' +
                            '<a href="${contextPath}/notification-partner/broadcast/detail/' + data?.request_id + '">' +
                            '<i class="fa fa-info-circle" style="font-size: 2rem;"></i>' +
                            '</a>' +
                            '</div>';
                    }
                },
                {
                    "data": null,
                    "render": function (data, type, full, meta) {
                        let date = new Date(data?.request_time);
                        return date ? date.hmdmy() : '';
                    }
                },
                {
                    "data": null,
                    "render": function (data, type, full, meta) {
                        let event = data?.event;
                        switch (event) {
                            case "MAINTAIN_START":
                                return '<spring:message code="label.maintain_start"/>';
                            case "MAINTAIN_STOP":
                                return '<spring:message code="label.maintain_stop"/>';
                            case "PROMOTION_START":
                                return '<spring:message code="label.promotion_start"/>';
                            case "PROMOTION_STOP":
                                return '<spring:message code="label.promotion_stop"/>';
                            default:
                                return "";
                        }
                    }
                },
                {
                    "data": null,
                    "render": function (data, type, full, meta) {
                        let service = data?.service;
                        switch (service) {
                            case "ALL":
                                return 'Tất cả';
                            case "BILL_ELECTRIC":
                                return 'Hóa đơn điện';
                            case "BILL_FINANCE":
                                return 'Hóa đơn tài chính';
                            case "OTHER":
                                return data?.service_detail_name;
                            default:
                                return "";
                        }
                    }
                },
                {
                    "data": null,
                    "render": function (data, type, full, meta) {
                        let serviceType = data?.service_type;
                        switch (serviceType) {
                            case "PHONE_TOPUP":
                                return 'PHONE_TOPUP';
                            case "EPIN":
                                return 'EPIN';
                            case "BILL_PAYMENT":
                                return 'BILL_PAYMENT';
                            case "ALL":
                                return "Tất cả";
                            default:
                                return "";
                        }
                    }
                },
                {
                    "data": null,
                    "render": function (data, type, full, meta) {
                        return data?.subject;
                    }
                },
                {
                    "data": null,
                    "render": function (data, type, full, meta) {
                        return '<div style="cursor: pointer" title="' + data?.content + '" class="b">' + data?.content + '</div>';
                    }
                },
                {
                    "data": null,
                    "render": function (data) {
                        return data?.request_id;
                    }
                },
                {
                    "data": null,
                    "render": function (data) {
                        return data?.ref_request_id;
                    }
                },
            ]
        })
        ;
    }
</script>
</body>
</html>
