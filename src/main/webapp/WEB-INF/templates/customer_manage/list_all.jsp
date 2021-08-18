<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>


<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
    <meta charset="UTF-8">
    <title><spring:message code="common.customer"/></title>
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <jsp:include page="../include_page/js_service.jsp">
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
</head>

<body>
<section class="body">
    <jsp:include page="../include_page/header.jsp"/>
    <jsp:include page="../include_component/constant_application.jsp"/>

    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="set-cus" name="nav"/>
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
                                        code="common.customer"/></span>
                                <li><span class=""><spring:message
                                        code="menu.left.customer.management.list"/></span>
                                </li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <jsp:include page="../include_page/message_new.jsp"/>

            <!-- start: page -->

            <sec:authorize
                    access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR','SALESUPPORT_MANAGER','SALESUPPORT')"
                    var="permisAll"/>
            <sec:authorize
                    access="hasAnyRole('SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM')"
                    var="permisSale"/>
            <sec:authorize
                    access="hasAnyRole('FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')"
                    var="permisFinance"/>
            <sec:authorize
                    access="hasAnyRole('CUSTOMERCARE_MANAGER','CUSTOMERCARE')"
                    var="permisCSKH"/>
            <sec:authorize
                    access="hasAnyRole('SALE_AGENT')"
                    var="isSaleAgent"/>
            <sec:authorize
                    access="hasAnyRole('SALE_MERCHANT')"
                    var="isSaleMerchant"/>
            <%--<sec:authentication property="authorities" var="roles" scope="page"/>--%>
            <%--<h4>${roles}</h4>--%>

            <div class="content-body-wrap">
                <div class="container-fluid">
                    <div class="tabs">
                        <ul class="nav nav-tabs">
                            <c:if test="${(permisAll) || (permisFinance) || permisCSKH}">
                                <li class="active"><a onclick="openTabAll();" href="#"
                                                      data-toggle="tab">All</a>
                                </li>
                                <li class=""><a onclick="openTab('1');" href="#">Customer</a></li>
                                <li class=""><a onclick="openTab('2');" href="#">Agent</a></li>
                                <li class=""><a onclick="openTab('3');" href="#">Merchant</a></li>
                                <li class=""><a onclick="openTab('11');" href="#">Provider</a></li>
                                <li class=""><a onclick="openTab('12');" href="#">Property Manager</a></li>
                            </c:if>
                            <c:if test="${(permisSale)}">
                                <c:choose>
                                    <c:when test="${isSaleAgent}">
                                        <li class=""><a onclick="openTab('2');" href="#">Agent</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class=""><a onclick="openTab('3');" href="#">Merchant</a></li>
                                    </c:otherwise>
                                </c:choose>


                            </c:if>
                        </ul>
                        <div class="tab-content pl-none pr-none">
                            <div id="tab1" class="tab-pane active">
                                <section class="panel search_payment panel-default">
                                    <div class="panel-body pt-none">
                                        <spring:message code="account.search.placeholder"
                                                        var="placeholder"/>
                                        <form action="" method="GET" id="tbl-filter">
                                            <input type="hidden" name="${_csrf.parameterName}"
                                                   value="${_csrf.token}"/>
                                            <div class="form-group ml-none mr-none">
                                                <%--<div class='pull-right form-responsive bt-plt'>--%>
                                                <%--<a class="mb-xs mt-xs btn btn-success"--%>
                                                <%--href="${contextPath}/customer/manage/add"><i--%>
                                                <%--class="fa fa-plus"></i>&nbsp;<spring:message--%>
                                                <%--code="system.service.navigate.btn.create"/></a>--%>
                                                <%--</div>--%>

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
                                                                            <c:when
                                                                                    test="${not empty listCusType && listCusType.size() > 0 }">
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

                                                    <%--select wallet type--%>
                                                    <div class="col-md-3 col-lg-3">
                                                        <div class="form-group">
                                                            <div class="row">
                                                                <div class="col-md-4">
                                                                    <label class="control-label nowrap"
                                                                           for="walletTypeComb"
                                                                           style="min-width: 100px"><spring:message
                                                                            code="label.wallet.type"/> </label>
                                                                </div>
                                                                <div class="col-md-8">
                                                                    <select data-plugin-selectTwo
                                                                            class="form-control"
                                                                            id="walletTypeComb"
                                                                            name="walletTypeCb">
                                                                        <option value="">
                                                                            <spring:message
                                                                                    code="label.please.select"/></option>
                                                                        <option value="1" ${walletTypeCb eq 1 ? 'selected' : ''}>
                                                                            Account
                                                                        </option>
                                                                        <option value="2" ${walletTypeCb eq 2 ? 'selected' : ''}>
                                                                            Wallet
                                                                        </option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <%--select user type--%>
                                                    <div class="col-md-3 col-lg-3">
                                                        <div class="form-group">
                                                            <div class="row">
                                                                <div class="col-md-4">
                                                                    <label class="control-label nowrap"
                                                                           for="userTypeComb"
                                                                           style="min-width: 100px"><spring:message
                                                                            code="label.user.type"/> </label>
                                                                </div>
                                                                <div class="col-md-8">
                                                                    <select data-plugin-selectTwo
                                                                            class="form-control"
                                                                            id="userTypeComb"
                                                                            name="userTypeComb">
                                                                        <option value="">
                                                                            <spring:message
                                                                                    code="label.please.select"/></option>
                                                                        <option value="1" ${userTypeCb eq 1 ? 'selected' : ''}>
                                                                            System
                                                                        </option>
                                                                        <option value="2" ${userTypeCb eq 2 ? 'selected' : ''}>
                                                                            User
                                                                        </option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
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
                                                                           for="roleList"
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
                                                                            <c:when
                                                                                    test="${not empty listRoles && listRoles.size() > 0 }">
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
                                                    <div class="col-md-4 col-lg-3">
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

                                                    <%--select payment channel--%>
                                                    <div class="col-md-3 col-lg-3">
                                                        <div class="form-group">
                                                            <div class="row">
                                                                <div class="col-md-4 col-md-text-right">
                                                                    <label class="control-label nowrap"
                                                                           for="positionList"><spring:message
                                                                            code="label.payment.channel"/> </label>
                                                                </div>
                                                                <div class="col-md-8">
                                                                    <select data-plugin-selectTwo
                                                                            name="paymentChannelId"
                                                                            id="paymentChannelId" class="form-control">
                                                                        <option value=""><spring:message
                                                                                code="select.choose.all"/></option>
                                                                        <c:forEach var="item"
                                                                                   items="${paymentChannels}">
                                                                            <option ${item.code eq param.paymentChannelId ? 'selected' : ''}
                                                                                    value="${item.code}">${item.displayText}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- select position -->
                                                    <div class="col-md-3 col-lg-3 hidden">
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
                                                                                            value="${listPosition.id}" ${(param.positionList !=null && param.positionList eq listPosition.id) ? 'selected':''}>
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

                                                    <button type="submit" class="btn btn-primary">
                                                        <i class="fa fa-search"></i>&nbsp;
                                                        <spring:message code="system.service.list.search.btn.search"/>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
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
                                                <spring:message var="colBlackListR"
                                                                code="setting.account.tbl.col.blacklist.reason"/>
                                                <spring:message var="colPositions"
                                                                code="settion.account.tbl.col.position.list"/>
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

                                                <div class="table-responsive">
                                                    <display:table name="list" id="item"
                                                                   requestURI="list"
                                                                   pagesize="${pagesize}"
                                                                   partialList="true"
                                                                   size="total"
                                                                   sort="page"
                                                                   class="table table-bordered table-responsive table-striped mb-none">

                                                        <%@ include
                                                                file="../include_component/display_table.jsp" %>

                                                        <display:column title="#"
                                                                        headerClass="fit_to_content"
                                                                        class="right">
                                    <span id="id${item.id}" class="rowid">
                                    <c:out value="${offset + item_rowNum}"/>
                                    </span>
                                                        </display:column>

                                                        <display:column
                                                                title="${colCode}"
                                                                style="vertical-align: middle"><a
                                                                class="detail-link link-active"
                                                                id="customer-link-${offset + item_rowNum}"
                                                                href="${contextPath}/customer/manage/details/${item.id}">${item.cif}</a></display:column>

                                                        <display:column title="${colName}">
                                                            <div class="user-info-box">
                                                                <div class="avt">
                                                                    <img src="${contextPath}/assets/images/man.svg"
                                                                         class="img-circle list-user-avatar"
                                                                         data-id="${offset + item_rowNum}"
                                                                         data-toggle="modal">
                                                                </div>
                                                                <div class="user-info">
                                                                    <p class="user-name">
                                                                        <b>
                                                                            <a id="customer-link-${offset + item_rowNum}"
                                                                               href="${contextPath}/customer/manage/details/${item.id}">
                                                                                <c:if
                                                                                        test="${(item.lastName ne null || item.firstName ne null) && item.displayName eq null}">
                                                                                    <span class="last-name">${item.lastName}</span>
                                                                                    <span class="first-name">${item.firstName}</span>
                                                                                </c:if>
                                                                                <c:if test="${item.displayName ne null}">
                                                                                    <span class="last-name">${item.displayName}</span>
                                                                                    <span class="first-name"></span>
                                                                                </c:if>
                                                                            </a>
                                                                        </b>
                                                                    </p>
                                                                    <p>${item.email}</p>
                                                                </div>
                                                            </div>

                                                        </display:column>

                                                        <display:column
                                                                title="${colPhone}"
                                                                style="vertical-align: middle">${item.msisdn}</display:column>

                                                        <display:column
                                                                title="${colCustomerType}"
                                                                style="vertical-align: middle">
                                                            ${item.customerType.name}
                                                            <c:if test="${item.linked eq true}">
                                                                &nbsp;&nbsp;<span style="background-color:#FF9D00;" class="badge"><spring:message
                                                                    code="label.pms.linked"/></span>
                                                            </c:if>
                                                        </display:column>

                                                        <display:column title="CHANNEL"
                                                                        style="vertical-align: middle">
                                                            ${item.bizChannelId}
                                                        </display:column>

                                                        <display:column
                                                                title="${colWalletType}"
                                                                style="vertical-align: middle">
                                                            <c:if test="${item.walletTypeId eq null}">
                                                                <div class="wallet-type]">N/A</div>
                                                            </c:if>
                                                            <c:if test="${item.walletTypeId ne null && item.walletTypeId != '' }">
                                                                <spring:message
                                                                        code="label.walletType.${item.walletTypeId}"/>
                                                            </c:if>
                                                        </display:column>

                                                        <display:column
                                                                title="${colPositions}"
                                                                style="vertical-align: middle">
                                                            <c:if test="${item.jobPosition eq null}">
                                                                <div class="job-position]">N/A</div>
                                                            </c:if>
                                                            <c:if test="${item.jobPosition ne null && item.jobPosition != '' }">
                                                                <spring:message
                                                                        code="label.positionList.${item.jobPosition}"/>
                                                            </c:if>

                                                        </display:column>

                                                        <display:column title="${colBlackListR}"
                                                                        style="vertical-align: middle">
                                                            <c:if test="${item.blackListReason == 0}">
                                                <span id="txn-blacklist-reason-${item.id}"
                                                      class="text-success"><spring:message
                                                        code="label.blackListR.${item.blackListReason}"/></span>
                                                            </c:if>
                                                            <c:if test="${item.blackListReason != 0}">
                                                      <span id="txn-blacklist-reason-${item.id}"
                                                            class="text-danger"><spring:message
                                                              code="label.blackListR.${item.blackListReason}"/></span>
                                                            </c:if>

                                                        </display:column>


                                                        <display:column
                                                                title="${colCreateTime}"
                                                                style="vertical-align: middle">${item.created}</display:column>
                                                        <c:if test="${(permisAll eq true) || (permisCSKH eq true)}">
                                                            <display:column title="${colAction}"
                                                                            style="vertical-align: middle"
                                                                            class="action_icon center"
                                                                            headerClass="action_icon center">

                                                                <%--<div style="display: inline-flex">--%>

                                                                <input type="hidden"
                                                                       id="user-${item.id}-blackListReason"
                                                                       value="${item.blackListReason}">
                                                                <label class="switch" style="
                                            margin: 0 3px;
                                            "
                                                                       data-toggle="popover"
                                                                       data-trigger="hover"
                                                                       data-placement="top"
                                                                       title=""
                                                                       data-content="${actionChangeStatus}"
                                                                       for="chk-blacklist-status-${item.id}">
                                                                    <input id="chk-blacklist-status-${item.id}"
                                                                           type="checkbox" ${'0' eq item.blackListReason ? 'checked' : ''}
                                                                           onclick="return blackListAccount(${item.id},${item.blackListReason})">
                                                                    <span class="slider round"></span>
                                                                </label>


                                                                <a href="${contextPath}/customer/manage/reset-password/${item.id}"
                                                                   data-toggle="popover"
                                                                   data-trigger="hover"
                                                                   data-placement="top"
                                                                   title=""
                                                                   data-content="${actionResetPass}"
                                                                   style="vertical-align: middle;">
                                                                    <img style="width: 40px;"
                                                                         src="${contextPath}/assets/images/reset-password.png"
                                                                         data-content="${actionResetPass}">
                                                                </a>
                                                                <c:if test="${'MERCHANT' eq item.customerType.name}">
                                                                    <a onclick="resendInfo('${item.id}')"
                                                                       data-toggle="popover"
                                                                       data-trigger="hover"
                                                                       data-placement="top"
                                                                       title=""
                                                                       data-content="${actionResendInfo}"
                                                                       style="vertical-align: middle;"><i
                                                                            class="fa fa-lg fa-refresh"
                                                                            style="padding-left: 10px; color: #F0A800"></i>
                                                                    </a>
                                                                </c:if>
                                                                <%--</div>--%>

                                                            </display:column>
                                                        </c:if>

                                                    </display:table>
                                                </div>
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
<script>
    $(document).ready(function () {
        $('[data-toggle="popover"]').popover()
    });

    function openTab(paramValue) {
        var searchURL = '';
        searchURL = '${CustomerManageListUrl}' + '?menu=cus&customerType=' + paramValue;
        window.location.href = searchURL;
    }

    function openTabAll() {
        var searchURL = '';
        searchURL = '${CustomerManageListAllUrl}?menu=cus';
        window.location.href = searchURL;
    }
</script>
</body>
</html>
