<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>


<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
    <meta charset="UTF-8">
    <title><spring:message code="system.account.list.title.page"/></title>
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <jsp:include page="../include_page/js_service.jsp">
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="switchLib" value="true"/>
    </jsp:include>
</head>

<body>
<section class="body">
    <jsp:include page="../include_page/header.jsp"/>
    <jsp:include page="../include_component/constant_application.jsp"/>

    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="set-account" name="nav"/>
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
                                        code="system.account.list.navigate.setting.account"/></span>
                                </li>
                                <li><span class=""><spring:message
                                        code="menu.setting.staff.account.management.list"/></span>
                                </li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <jsp:include page="../include_page/message_new.jsp"/>

            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">
                    <div class="tabs">
                        <sec:authorize access="hasAnyRole('ADMIN_OPERATION')" var="is_view_full"/>
                        <sec:authorize access="hasAnyRole('CUSTOMERCARE_MANAGER')" var="is_cusCareMana_view"/>
                        <ul class="nav nav-tabs">
                            <c:if test="${is_view_full}">
                                <li class="${param.customerType == null || '' eq param.customerType ? 'active' : ''}">
                                    <a onclick="openTabAll();" href="#" data-toggle="tab">All</a>
                                </li>
                                <li class="${'9' == param.customerType ? 'active' : ''}">
                                    <a onclick="openTab('9');" href="#">Admin</a>
                                </li>
                                <li class="${'8' == param.customerType ? 'active' : ''}">
                                    <a onclick="openTab('8');" href="#">Staff</a>
                                </li>
                            </c:if>
                            <c:if test="${is_cusCareMana_view}">
                                <li class="${'8' == param.customerType ? 'active' : ''}">
                                    <a onclick="openTab('8');" href="#">Staff</a>
                                </li>
                            </c:if>
                        </ul>
                        <div class="tab-content pl-none pr-none">
                            <div id="tab1" class="tab-pane active">
                                <section class="panel search_payment panel-default">
                                    <div class="panel-body pt-none">

                                        <div class="form-inline">
                                            <div class="pull-left h4 mb-md mt-md"><spring:message
                                                    code="setting.system.staff.account.list.subnavigate"/></div>
                                            <div class="fr form-responsive">
                                                <c:if test="${param.customerType != null && '' ne param.customerType}">
                                                    <sec:authorize
                                                            access="hasAnyRole('ADMIN_OPERATION')">
                                                        <a class="mb-xs mt-xs btn btn-success"
                                                           href="${contextPath}/staff-account/manage/add${param.customerType != null ? '?customerType='.concat(customerType) : ''}">
                                                            <i class="fa fa-plus"></i>&nbsp;<spring:message
                                                                code="system.service.navigate.btn.create"/>
                                                        </a>
                                                    </sec:authorize>
                                                </c:if>
                                            </div>
                                        </div>

                                        <section class="panel search_payment panel-default">
                                            <div class="panel-body pt-none">
                                                <spring:message code="account.search.placeholder"
                                                                var="placeholder"/>
                                                <form action="" method="GET" id="tbl-filter">
                                                    <input type="hidden"
                                                           name="${_csrf.parameterName}"
                                                           value="${_csrf.token}"/>
                                                    <div class="form-group ml-none mr-none">
                                                        <div class="input-group input-group-icon">
                                        <span class="input-group-addon"><span class="icon"
                                                                              style="opacity: 0.4"><i
                                                class="fa fa-search-minus"></i></span></span>
                                                            <input type="text" id="fullTextSearch"
                                                                   name="fullTextSearch"
                                                                   class="form-control"
                                                                   placeholder="${placeholder}"
                                                                   value="${param.fullTextSearch }"/>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12 col-lg-12 col-md-12 col-sm-12">
                                                        <div class="row">

                                                            <%--select custom type--%>
                                                            <c:if test="${param.customerType == null || '' eq param.customerType}">
                                                                <div class="col-md-3 col-lg-3">
                                                                    <div class="form-group">
                                                                        <div class="row">
                                                                            <div class="col-md-4">
                                                                                <label class="control-label nowrap"
                                                                                       for="customerType"
                                                                                       style="min-width: 100px"><spring:message
                                                                                        code="select.customerType"/> </label>
                                                                            </div>
                                                                            <div class="col-md-8">
                                                                                <select data-plugin-selectTwo
                                                                                        class="form-control"
                                                                                        id="customerType"
                                                                                        name="customerType">
                                                                                    <option value="">
                                                                                        <spring:message
                                                                                                code="label.please.select"/></option>
                                                                                    <c:choose>
                                                                                        <c:when test="${not empty listCusType && listCusType.size() > 0 }">
                                                                                            <c:forEach
                                                                                                    var="cusType"
                                                                                                    items="${listCusType}">
                                                                                                <option
                                                                                                        value="${cusType.id}" ${(param.customerType != null && param.customerType eq cusType.id) ? 'selected':''}>${cusType.name}</option>
                                                                                            </c:forEach>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <option value="">
                                                                                                N/A
                                                                                            </option>
                                                                                        </c:otherwise>
                                                                                    </c:choose>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:if>

                                                            <%--&lt;%&ndash;select wallet type&ndash;%&gt;--%>
                                                            <%--<div class="col-md-3 col-lg-3">--%>
                                                            <%--<div class="form-group">--%>
                                                            <%--<div class="row">--%>
                                                            <%--<div class="col-md-4">--%>
                                                            <%--<label class="control-label nowrap"--%>
                                                            <%--for="walletTypeComb"--%>
                                                            <%--style="min-width: 100px"><spring:message--%>
                                                            <%--code="label.wallet.type"/> </label>--%>
                                                            <%--</div>--%>
                                                            <%--<div class="col-md-8">--%>
                                                            <%--<select data-plugin-selectTwo--%>
                                                            <%--class="form-control"--%>
                                                            <%--id="walletTypeComb"--%>
                                                            <%--name="walletTypeCb">--%>
                                                            <%--<option value=""><spring:message--%>
                                                            <%--code="label.please.select"/></option>--%>
                                                            <%--<option--%>
                                                            <%--value="1" ${account_object.walletTypeId eq 1 ? 'selected' : ''}>--%>
                                                            <%--Account--%>
                                                            <%--</option>--%>
                                                            <%--<option--%>
                                                            <%--value="2" ${account_object.walletTypeId eq 2 ? 'selected' : ''}>--%>
                                                            <%--Wallet--%>
                                                            <%--</option>--%>
                                                            <%--</select>--%>
                                                            <%--</div>--%>
                                                            <%--</div>--%>
                                                            <%--</div>--%>
                                                            <%--</div>--%>

                                                            <%--&lt;%&ndash;select user type&ndash;%&gt;--%>
                                                            <%--<div class="col-md-3 col-lg-3">--%>
                                                            <%--<div class="form-group">--%>
                                                            <%--<div class="row">--%>
                                                            <%--<div class="col-md-4">--%>
                                                            <%--<label class="control-label nowrap"--%>
                                                            <%--for="userTypeComb"--%>
                                                            <%--style="min-width: 100px"><spring:message--%>
                                                            <%--code="label.user.type"/> </label>--%>
                                                            <%--</div>--%>
                                                            <%--<div class="col-md-8">--%>
                                                            <%--<select data-plugin-selectTwo--%>
                                                            <%--class="form-control"--%>
                                                            <%--id="userTypeComb"--%>
                                                            <%--name="userTypeComb">--%>
                                                            <%--<option value=""><spring:message--%>
                                                            <%--code="label.please.select"/></option>--%>
                                                            <%--<option value="1" ${account_object.userTypeId eq 1 ? 'selected' : ''}>--%>
                                                            <%--System--%>
                                                            <%--</option>--%>
                                                            <%--<option value="2" ${account_object.userTypeId eq 2 ? 'selected' : ''}>--%>
                                                            <%--User--%>
                                                            <%--</option>--%>
                                                            <%--</select>--%>
                                                            <%--</div>--%>
                                                            <%--</div>--%>
                                                            <%--</div>--%>
                                                            <%--</div>--%>

                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12 col-lg-12 col-md-12 col-sm-12">
                                                        <div class="row">
                                                            <%--select role--%>
                                                            <div class="col-md-3 col-lg-3">
                                                                <div class="form-group">
                                                                    <div class="row">
                                                                        <div class="col-md-4">
                                                                            <label class="control-label nowrap"
                                                                                   for="customerType"
                                                                                   style="min-width: 100px"><spring:message
                                                                                    code="select.roleType"/> </label>
                                                                        </div>
                                                                        <div class="col-md-8">
                                                                            <select data-plugin-selectTwo
                                                                                    class="form-control"
                                                                                    id="roleList"
                                                                                    name="roleList">
                                                                                <option value="">
                                                                                    <spring:message
                                                                                            code="label.please.select"/></option>
                                                                                <c:choose>
                                                                                    <c:when test="${not empty listRoles && listRoles.size() > 0 }">
                                                                                        <c:forEach
                                                                                                var="role_list"
                                                                                                items="${listRoles}">
                                                                                            <option
                                                                                                    value="${role_list.role}" ${(param.roleList !=null && param.roleList eq role_list.role) ? 'selected':''}>${role_list.description}</option>
                                                                                        </c:forEach>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <option value="">
                                                                                            N/A
                                                                                        </option>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <%--select blackList--%>
                                                            <div class="col-md-3 col-lg-3">
                                                                <div class="form-group">
                                                                    <div class="row">
                                                                        <div class="col-md-4 col-md-text-right">
                                                                            <label class="control-label nowrap"
                                                                                   for="blackList"><spring:message
                                                                                    code="select.blackList"/> </label>
                                                                        </div>
                                                                        <div class="col-md-8">
                                                                            <select data-plugin-selectTwo
                                                                                    class="form-control"
                                                                                    id="blackList"
                                                                                    name="blackList">
                                                                                <option value="">
                                                                                    <spring:message
                                                                                            code="label.please.select"/></option>
                                                                                <c:choose>
                                                                                    <c:when
                                                                                            test="${listBlackReason ne null && listBlackReason.size() > 0 }">
                                                                                        <c:forEach
                                                                                                var="blackReason"
                                                                                                items="${listBlackReason}">
                                                                                            <option
                                                                                                    value="${blackReason.key}" ${(param.blackList !=null && param.blackList eq blackReason.key) ? 'selected':''}>
                                                                                                <spring:message
                                                                                                        code="label.blackListR.${blackReason.key}"/></option>
                                                                                        </c:forEach>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <option value="">
                                                                                            N/A
                                                                                        </option>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <!-- select position -->
                                                            <div class="col-md-3 col-lg-3">
                                                                <div class="form-group">
                                                                    <div class="row">
                                                                        <div class="col-md-4 col-md-text-right">
                                                                            <label class="control-label nowrap"
                                                                                   for="positionList"><spring:message
                                                                                    code="select.positionList"/> </label>
                                                                        </div>
                                                                        <div class="col-md-8">
                                                                            <select data-plugin-selectTwo
                                                                                    class="form-control"
                                                                                    id="positionList"
                                                                                    name="positionList">
                                                                                <option value="">
                                                                                    <spring:message
                                                                                            code="label.please.select"/></option>
                                                                                <c:choose>
                                                                                    <c:when
                                                                                            test="${listPositions ne null && listPositions.size() > 0 }">
                                                                                        <c:forEach
                                                                                                var="listPosition"
                                                                                                items="${listPositions}">
                                                                                            <option
                                                                                                    value="${listPosition.id}" ${(param.positionList != null && param.positionList eq listPosition.id) ? 'selected':''}>
                                                                                                <spring:message
                                                                                                        code="label.positionList.${listPosition.id}"/></option>
                                                                                        </c:forEach>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <option value="">
                                                                                            N/A
                                                                                        </option>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>


                                                        </div>
                                                    </div>
                                                    <div class="form-inline">
                                                        <div class='pull-right form-responsive bt-plt'>

                                                            <button type="button"
                                                                    class="btn btn-primary"
                                                                    onclick="drawTableAccountList()">
                                                                <i
                                                                        class="fa fa-search"></i>&nbsp;<spring:message
                                                                    code="system.service.list.search.btn.search"/>
                                                        </div>

                                                    </div>
                                                    <div class="clearfix"></div>

                                                    <input type="hidden" name="customerTypeSystem"
                                                           value="${customerTypeSystem}">
                                                </form>

                                                <section class="panel search_payment panel-default">
                                                    <div class="panel-body">
                                                        <div class="clearfix"></div>
                                                        <div class="pull-left mt-sm"
                                                             style="line-height: 30px;">
                                                        </div>
                                                        <div class="clr"></div>

                                                        <spring:message var="colServiceType"
                                                                        code="setting.account.tbl.col.status"/>
                                                        <spring:message var="colCode"
                                                                        code="setting.account.tbl.col.account.id"/>
                                                        <spring:message var="colName"
                                                                        code="setting.account.tbl.col.full.name"/>
                                                        <spring:message var="colPhone"
                                                                        code="setting.account.tbl.col.phone"/>
                                                        <spring:message var="colCustomerType"
                                                                        code="setting.account.tbl.col.customer.type"/>
                                                        <spring:message var="colWalletType"
                                                                        code="setting.account.tbl.col.wallet.type"/>
                                                        <spring:message var="colPositions"
                                                                        code="settion.account.tbl.col.position.list"/>
                                                        <spring:message var="colBlackListR"
                                                                        code="setting.account.tbl.col.blacklist.reason"/>
                                                        <spring:message var="colCreateTime"
                                                                        code="setting.account.tbl.col.created.at"/>
                                                        <spring:message var="colAction"
                                                                        code="setting.account.tbl.col.action"/>
                                                        <spring:message var="actionResetPass"
                                                                        code="label.reset.password"/>
                                                        <spring:message var="actionResendInfo"
                                                                        code="label.resend.info"/>
                                                        <spring:message var="actionChangeStatus"
                                                                        code="account.dialog.change.black.list.reason"/>
                                                        <div class="container-fluid">
                                                            <div class="table-responsive">
                                                                <table class="table table-bordered table-responsive table-striped mb-none"
                                                                       id="acount-list-table">
                                                                    <thead>
                                                                    <th>#</th>
                                                                    <th>${colCode}</th>
                                                                    <th>${colName}</th>
                                                                    <th>${colPhone}</th>
                                                                    <th>${colCustomerType}</th>
                                                                    <th>${colWalletType}</th>
                                                                    <th>${colPositions}</th>
                                                                    <th>${colBlackListR}</th>
                                                                    <th>${colCreateTime}</th>
                                                                    <th>${colAction}</th>
                                                                    </thead>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </section>
                                            </div>
                                        </section>
                                    </div>
                                </section>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <jsp:include page="../include_component/action_change_black_list_reason.jsp"/>

            <!-- Modal resend -->
            <jsp:include page="../include_component/action_resend_info.jsp"/>
            <!-- Modal resend -->

            <!-- end: page -->
        </section>
        <jsp:include page="../include_page/footer.jsp"/>

    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<script type="text/javascript"
        src="<c:url value='/assets/javascripts/settings-custom.js'/>"></script>
<spring:message code="label.blackListR.0" var="black_list_0"/>
<spring:message code="label.blackListR.1" var="black_list_1"/>
<spring:message code="label.blackListR.2" var="black_list_2"/>
<spring:message code="label.blackListR.3" var="black_list_3"/>
<spring:message code="label.blackListR.4" var="black_list_4"/>
<spring:message code="label.blackListR.5" var="black_list_5"/>

<spring:message code="label.positionList.1" var="position_list_1"/>
<spring:message code="label.positionList.2" var="position_list_2"/>
<spring:message code="label.positionList.3" var="position_list_3"/>
<spring:message code="label.positionList.4" var="position_list_4"/>
<spring:message code="label.positionList.5" var="position_list_5"/>
<spring:message code="label.positionList.6" var="position_list_6"/>
<spring:message code="label.positionList.7" var="position_list_7"/>
<spring:message code="label.positionList.8" var="position_list_8"/>
<spring:message code="label.positionList.9" var="position_list_9"/>
<spring:message code="label.positionList.10" var="position_list_10"/>
<spring:message code="label.positionList.11" var="position_list_11"/>
<spring:message code="label.positionList.12" var="position_list_12"/>
<spring:message code="label.positionList.13" var="position_list_13"/>
<spring:message code="label.positionList.14" var="position_list_14"/>
<spring:message code="label.positionList.15" var="position_list_15"/>
<spring:message code="label.positionList.16" var="position_list_16"/>
<spring:message code="label.positionList.17" var="position_list_17"/>
<spring:message code="label.positionList.18" var="position_list_18"/>
<spring:message code="label.positionList.19" var="position_list_19"/>
<spring:message code="label.positionList.20" var="position_list_20"/>
<spring:message code="label.positionList.21" var="position_list_21"/>
<spring:message code="label.positionList.22" var="position_list_22"/>
<spring:message code="label.positionList.23" var="position_list_23"/>
<spring:message code="label.positionList.24" var="position_list_24"/>
<spring:message code="label.positionList.25" var="position_list_25"/>
<spring:message code="label.positionList.26" var="position_list_26"/>
<spring:message code="label.positionList.27" var="position_list_27"/>
<spring:message code="label.positionList.28" var="position_list_28"/>
<spring:message code="label.positionList.29" var="position_list_29"/>
<spring:message code="label.positionList.30" var="position_list_30"/>
<spring:message code="label.positionList.31" var="position_list_31"/>
<spring:message code="label.positionList.32" var="position_list_32"/>

<spring:message code="label.walletType.1" var="wallet_type_1"/>
<spring:message code="label.walletType.2" var="wallet_type_2"/>

<spring:message code="data.table.header.paging.showing" var="paging_showing"/>
<spring:message code="data.table.header.paging.to" var="paging_to"/>
<spring:message code="data.table.header.paging.of" var="paging_of"/>
<spring:message code="data.table.header.paging.entries" var="paging_entries"/>
<spring:message code="data.table.header.paging.previous" var="paging_previous"/>
<spring:message code="data.table.header.paging.next" var="paging_next"/>
<spring:message code="data.table.header.paging.records" var="paging_records"/>

<script>
  $(document).ready(function () {
    drawTableAccountList();
  });

  jQuery(window).on("load", function () {
    $('[data-toggle="popover"]').popover();
  });

  function drawTableAccountList() {
    var fullTextSearch = $('#fullTextSearch').val();
    var customerType = $('#customerType').val();
    if ("${param.customerType}") {
      customerType = "${param.customerType}";
    }
    var customerTypeSystem = "${customerTypeSystem}";
    var roleList = $('#roleList').val();
    var blackList = $('#blackList').val();
    var walletTypeIds = $('#walletTypeComb').val();
    var userTypeIds = $('#userTypeComb').val();
    var positionList = $('#positionList').val();

    $('#acount-list-table').dataTable({
      "paging": true,
      "serverSide": true,
      "iDisplayStart": 0,
      "pageLength": 20,
      "lengthMenu": [[10, 20, 50, -1], [10, 20, 50, "All"]],
      "searching": false,
      "language": {
        "sInfo": "${paging_showing} _START_ ${paging_to} _END_ ${paging_of} _TOTAL_ ${paging_entries}",
        "sLengthMenu": "_MENU_ ${paging_records}",
        "paginate": {
          "previous": "${paging_previous}",
          "next": "${paging_next}"
        }
      },
      dom: 'fltip',
      destroy: true,
      "ajax": {
        "url": ctx + "/ajax-controller/customer/find-customers",
        "type": "POST",
        "data": {
          fullTextSearch: fullTextSearch,
          customerType: customerType,
          customerTypeSystem: customerTypeSystem,
          roleList: roleList,
          blackList: blackList,
          walletTypeIds: walletTypeIds,
          userTypeIds: userTypeIds,
          positionList: positionList
        },
        dataSrc: 'dataList'
      },
      "columns": [
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var index = meta.settings.oAjaxData.start + meta.row + 1;

            return index;
          }
        },

        /*cif*/
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var index = meta.settings.oAjaxData.start + meta.row + 1;
            var htmlCode = ''
                + '<a class="detail-link link-active" id="customer-link-'.concat(index)
                .concat('" href="${contextPath}/staff-account/manage/details/')
                .concat(data.id).concat('">'.concat(data.cif)
                    + '</a>\n')
            return htmlCode;
          }
        },

        {
          "data": null,
          "render": function (data, type, full, meta) {
            var displayName = data.displayName == null ? "" : data.displayName;
            var lastName = data.lastName == null ? "" : data.lastName;
            var firstName = data.firstName == null ? "" : data.firstName;
            var index = meta.settings.oAjaxData.start + meta.row + 1;

            var htmlCode = ""
                + "<div class=\"user-info-box\">"
                + " <div class=\"avt\">\n"
                + "    <img src=\"${contextPath}/assets/images/man.svg\"\n"
                + "         class=\"img-circle list-user-avatar\"\n"
                + "         data-id=\"" + index + "\"\n"
                + "         data-toggle=\"modal\">"
                + " </div>"
                + " <div class=\"user-info\">\n"
                + "    <p class=\"user-name\">\n"
                + "        <b>\n"
                + "            <a id=\"customer-link-" + index + "\"\n"
                + "               href=\"${contextPath}/staff-account/manage/details/" + data.id
                + "\">\n"
                + (displayName !== ""
                    ? ("<span class=\"last-name\">" + displayName + "</span>\n"
                        + "<span class=\"first-name\"></span>\n")
                    : ("<span class=\"last-name\">" + lastName + "</span>\n"
                        + "<span class=\"first-name\">" + firstName + "</span>\n"))
                + "            </a>\n"
                + "        </b>\n"
                + "    </p>\n"
                + "    <p>" + (data.email == null ? "" : data.email) + "</p>"
                + " </div>"
                + "</div>";

            return htmlCode;
          }
        }
        ,
        {
          "data":
              "msisdn"
        }
        ,
        {
          "data":
              "customerType.name"
        }
        ,
        /* wallet type*/
        {
          "data": null,
          "render": function (data, type, full, meta) {
            var walletType = data.walletTypeId;
            var walletTypeName = "";
            switch (walletType) {
              case 1: {
                walletTypeName = "${wallet_type_1}";
                break;
              }
              case 2: {
                walletTypeName = "${wallet_type_2}";
                break;
              }
              default: {
                walletTypeName = "N/A";
              }
            }

            var htmlCode = ""
                + "<div id=\"txn-walletType-list-" + data.id + "\" class=\""
                + "\">" + (walletTypeName) + "</div>\n"

            return htmlCode;
          }
        },

        /* postion*/
        {
          "data":
              null,
          "render":
              function (data, type, full, meta) {
                var position = data.jobPosition;
                var positionLs = "";
                switch (position) {
                  case "1": {
                    positionLs = "${position_list_1}";
                    break;
                  }
                  case "2": {
                    positionLs = "${position_list_2}";
                    break;
                  }
                  case "3": {
                    positionLs = "${position_list_3}";
                    break;
                  }
                  case "4": {
                    positionLs = "${position_list_4}";
                    break;
                  }
                  case "5": {
                    positionLs = "${position_list_5}";
                    break;
                  }
                  case "6": {
                    positionLs = "${position_list_6}";
                    break;
                  }
                  case "7": {
                    positionLs = "${position_list_7}";
                    break;
                  }
                  case "8": {
                    positionLs = "${position_list_8}";
                    break;
                  }
                  case "9": {
                    positionLs = "${position_list_9}";
                    break;
                  }
                  case "10": {
                    positionLs = "${position_list_10}";
                    break;
                  }
                  case "11": {
                    positionLs = "${position_list_11}";
                    break;
                  }
                  case "12": {
                    positionLs = "${position_list_12}";
                    break;
                  }
                  case "13": {
                    positionLs = "${position_list_13}";
                    break;
                  }
                  case "14": {
                    positionLs = "${position_list_14}";
                    break;
                  }
                  case "15": {
                    positionLs = "${position_list_15}";
                    break;
                  }
                  case "16": {
                    positionLs = "${position_list_16}";
                    break;
                  }
                  case "17": {
                    positionLs = "${position_list_17}";
                    break;
                  }
                  case "18": {
                    positionLs = "${position_list_18}";
                    break;
                  }
                  case "19": {
                    positionLs = "${position_list_19}";
                    break;
                  }
                  case "20": {
                    positionLs = "${position_list_20}";
                    break;
                  }
                  case "21": {
                    positionLs = "${position_list_21}";
                    break;
                  }
                  case "22": {
                    positionLs = "${position_list_22}";
                    break;
                  }
                  case "23": {
                    positionLs = "${position_list_23}";
                    break;
                  }
                  case "24": {
                    positionLs = "${position_list_24}";
                    break;
                  }
                  case "25": {
                    positionLs = "${position_list_25}";
                    break;
                  }
                  case "26": {
                    positionLs = "${position_list_26}";
                    break;
                  }
                  case "27": {
                    positionLs = "${position_list_27}";
                    break;
                  }
                  case "28": {
                    positionLs = "${position_list_28}";
                    break;
                  }
                  case "29": {
                    positionLs = "${position_list_29}";
                    break;
                  }
                  case "30": {
                    positionLs = "${position_list_30}";
                    break;
                  }
                  case "31": {
                    positionLs = "${position_list_31}";
                    break;
                  }
                  case "32": {
                    positionLs = "${position_list_32}";
                    break;
                  }
                  default: {
                    positionLs = "N/A";
                  }
                }

                var htmlCode = ""
                    + "<div id=\"txn-position-list-" + data.id + "\" class=\""
                    + "\">" + (positionLs) + "</div>\n"

                return htmlCode;
              }
        }
        ,
        {
          "data":
              null,
          "render":

              function (data, type, full, meta) {
                var blackList = data.blackListReason;
                var blackListReason = "";
                switch (blackList) {
                  case 0: {
                    blackListReason = "${black_list_0}";
                    break;
                  }
                  case 1: {
                    blackListReason = "${black_list_1}";
                    break;
                  }
                  case 2: {
                    blackListReason = "${black_list_2}";
                    break;
                  }
                  case 3: {
                    blackListReason = "${black_list_3}";
                    break;
                  }
                  case 4: {
                    blackListReason = "${black_list_4}";
                    break;
                  }
                  case 5: {
                    blackListReason = "${black_list_5}";
                    break;
                  }
                }

                var htmlCode = ""
                    + "<div id=\"txn-blacklist-reason-" + data.id + "\" class=\""
                    + (0 == blackList ? "text-success" : "text-danger")
                    + "\">" + blackListReason + "</div>\n"

                return htmlCode;
              }
        }
        ,
        {
          "data":
              null,
          "render":

              function (data, type, full, meta) {
                if (data.created !== null) {
                  var createdDate = new Date(data.created);
                  return createdDate.getDate() + "-" + createdDate.getMonth() + "-"
                      + createdDate.getFullYear()
                      + " " + createdDate.getHours() + ":" + createdDate.getMinutes() + ":"
                      + createdDate.getSeconds();
                }
                return "";
              }
        }
        ,
        {
          "data":
              null,
          "render":

              function (data, type, full, meta) {
                var htmlCode = ""
                    + "<div style=\"display: inline-flex\">\n"
                    + "    <input type=\"hidden\" id=\"user-" + data.id
                    + "-blackListReason\" value=\""
                    + data.blackListReason + "\">\n"
                    + "    <label class=\"switch\" style=\"margin: 0 3px;\"\n"
                    + "           data-toggle=\"popover\"\n"
                    + "           data-trigger=\"hover\"\n"
                    + "           data-placement=\"top\"\n"
                    + "           title=\"\"\n"
                    + "           data-content=\"${actionChangeStatus}\"\n"
                    + "           for=\"chk-blacklist-status-" + data.id + "\">\n"
                    + "        <input id=\"chk-blacklist-status-" + data.id + "\"\n"
                    + "               type=\"checkbox\" " + (0 == data.blackListReason ? 'checked'
                        : '')
                    + "\n"
                    + "               onclick=\"return blackListAccount(" + data.id + ","
                    + data.blackListReason + ")\">\n"
                    + "        <span class=\"slider round\"></span>\n"
                    + "    </label>\n"
                    + "    <a href=\"${contextPath}/staff-account/manage/reset-password/" + data.id
                    + "\"\n"
                    + "       data-toggle=\"popover\"\n"
                    + "       data-trigger=\"hover\"\n"
                    + "       data-placement=\"top\"\n"
                    + "       title=\"\"\n"
                    + "       data-content=\"${actionResetPass}\"\n"
                    + "       style=\"vertical-align: middle;\">\n"
                    + "        <img style=\"width: 40px;\"\n"
                    + "             src=\"${contextPath}/assets/images/reset-password.png\"\n"
                    + "             data-content=\"${actionResetPass}\">\n"
                    + "    </a>\n"
                    + (3 == data.customerType.id
                        ? ("        <a onclick=\"resendInfo('" + data.id + "')\"\n"
                            + "           data-toggle=\"popover\"\n"
                            + "           data-trigger=\"hover\"\n"
                            + "           data-placement=\"top\"\n"
                            + "           title=\"\"\n"
                            + "           data-content=\"${actionResendInfo}\"\n"
                            + "           style=\"vertical-align: middle;\"><i\n"
                            + "                class=\"fa fa-lg fa-refresh\"\n"
                            + "                style=\"padding-left: 10px; color: #F0A800\"></i>\n"
                            + "        </a>\n")
                        : "")
                    + "</div>";

                return htmlCode;
              }
        }
      ]
    })
    ;
  }

  function openTab(paramValue) {
    var searchURL = '${AccountManageListUrl}' + '?menu=setting&customerType=' + paramValue;
    window.location.href = searchURL;
  }

  function openTabAll() {
    var searchURL = '${AccountManageListUrl}?menu=setting';
    window.location.href = searchURL;
  }
</script>
</body>
</html>
