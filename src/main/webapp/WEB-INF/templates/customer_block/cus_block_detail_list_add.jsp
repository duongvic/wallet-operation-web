<%@ page
        import="static vn.mog.ewallet.operation.web.controller.translog.TransactionReimController.TRANS_REIM_DETAIL" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
    <!-- Basic -->
    <meta charset="UTF-8">
    <title><spring:message code="menu.left.customer.block.list"/></title>
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

    <style>
        .bgCheckbox {
            background: #D3D89F !important;
            /*color: white;*/
        }

        .checkbox-info label:before {
            border: 1px solid #ecd6d6
        }

        .treetable-expanded > td:first-child, .treetable-collapsed > td:first-child {
            padding-left: 2em;
        }

        .treetable-expanded > td:first-child > .treetable-expander, .treetable-collapsed > td:first-child > .treetable-expander {
            top: 0.05em;
            position: relative;
            margin-left: -0.5em;
            margin-right: 0.25em;
        }

        .treetable-expanded .treetable-expander, .treetable-expanded .treetable-expander {
            width: 1em;
            height: 1em;
            cursor: pointer;
            position: relative;
            display: inline-block;
        }

        .treetable-depth-1 > td:first-child {
            padding-left: 3em;
        }

        .treetable-depth-2 > td:first-child {
            padding-left: 4.5em;
        }

        .treetable-depth-3 > td:first-child {
            padding-left: 6em;
        }
    </style>

</head>


<body>
<section class="body">
    <!--        ///////   header ////////-->
    <jsp:include page="../include_page/header.jsp"/>
    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="cus-block" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                                <li><span><spring:message code="menu.left.customer.manager"/></span>
                                </li>
                                <li><span class="nav-active"><spring:message
                                        code="menu.left.customer.block.list"/> </span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">


                    <%--<div class="tabs">--%>
                    <%--<ul class="nav nav-tabs">--%>
                    <%--<li class="${blockTypes == null || '' eq blockTypes ? 'active' : ''}">--%>
                    <%--<a onclick="openTabAllBlock();" href="#" data-toggle="tab">All</a>--%>
                    <%--</li>--%>
                    <%--<li class="${'SERVICE_BLOCK' == blockType ? 'active' : ''}">--%>
                    <%--<a id="SERVICE_BLOCK" onclick="openTabBlock('SERVICE_BLOCK')"--%>
                    <%--href="#">BLOCK SERVICE</a>--%>
                    <%--</li>--%>
                    <%--<li class="${'PROVIDER_BLOCK' == blockType ? 'active' : ''}">--%>
                    <%--<a id="PROVIDER" onclick="openTabBlock('PROVIDER_BLOCK')"--%>
                    <%--href="#">BLOCK PROVIDER</a>--%>
                    <%--</li>--%>
                    <%--<li class="${'PROVIDER_SERVICE_BLOCK' == blockType ? 'active' : ''}">--%>
                    <%--<a id="PROVIDER_SERVICE_BLOCK"--%>
                    <%--onclick="openTabBlock('PROVIDER_SERVICE_BLOCK')"--%>
                    <%--href="#">BLOCK PROVIDER SERVICE</a>--%>
                    <%--</li>--%>
                    <%--</ul>--%>

                    <div class="clearfix"></div>

                    <section class="panel search_payment panel-default">
                        <div class="panel-body pt-none">

                            <form action="" method="GET" id="tbl-filter" class="mb-md">

                                <div class="pull-right form-responsive bt-plt">
                                    <a class="mb-xs mt-xs btn btn-success"
                                       href="${contextPath}/customer-block/add?menu=cus&blockType=SERVICE_BLOCK"><i
                                            class="fa fa-plus"></i>&nbsp;<spring:message
                                            code="btn.add.blackList"/></a>
                                </div>

                                <div class="clearfix"></div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <input type="text" id="quickSearch"
                                                   name="quickSearch"
                                                   class="form-control"
                                                   placeholder="${placeholder}"
                                                   value="${param.quickSearch }"/>
                                        </div>

                                    </div>
                                </div>


                                <div class="form-inline">

                                    <%--provider--%>
                                    <jsp:include
                                            page="../include_component/search_provider_type.jsp"/>

                                    <%--provider--%>

                                    <%--serviceType--%>
                                    <jsp:include
                                            page="../include_component/search_service_type_multiple.jsp"/>
                                    <%--serviceType--%>

                                    <div class='pull-right form-responsive'>
                                        <button type="submit" class="btn btn-primary ml-tiny"><i
                                                class="fa fa-search"></i>&nbsp; <spring:message
                                                code="common.btn.search"/></button>
                                    </div>
                                </div>
                                <div class="clearfix"></div>

                                <input type="hidden" id="customersCifs_hidden"
                                       name="customersCifs"
                                       value="">

                            </form>

                            <%--<div class="clearfix"></div>--%>

                            <c:if test="${not empty dataProvider && dataProvider.size() > 0 && dataProvider !=null}">
                                <section class="panel search_payment panel-default">
                                    <div class="panel-body">
                                        <div class="clearfix"></div>
                                        <div class="pull-left mt-sm" style="line-height: 30px;">
                                            <spring:message
                                                    code="system.service.list.totalServices"/>&nbsp;<span>PROVIDER BLOCK:</span>&nbsp;<span
                                                class="primary_color text-semibold">${totalProvider }</span>
                                        </div>
                                        <div class="clr"></div>

                                        <spring:message var="colCode"
                                                        code="provider.list.table.code"/>
                                        <spring:message var="colName"
                                                        code="provider.list.table.name"/>
                                        <spring:message var="colAction"
                                                        code="provider.service.table.action"/>


                                        <display:table name="dataProvider" id="item"
                                                       requestURI="list-detail"
                                                       pagesize="${pagesizeProvider}"
                                                       partialList="true"
                                                       size="totalProvider"
                                                       sort="page"
                                                       class="table table-bordered table-striped mb-none">

                                            <%@ include
                                                    file="../include_component/display_table.jsp" %>

                                            <display:column title="STT">
                                    <span id="row${list.id}" class="rowid">
                                        <c:out value="${offset + item_rowNum}"/>
                                    </span>
                                            </display:column>

                                            <display:column title="${colCode}"
                                                            property="providerCode"/>
                                            <display:column title="${colName}" property="name"/>

                                            <display:column title="${colAction}"
                                                            class="action_icon center"
                                                            headerClass="center">
                                                <a href="#" class="remove-block link-active"
                                                   title="<spring:message code="btn.remove.blacklist"/>"
                                                   blocktype="PROVIDER_BLOCK"
                                                   providerCodes="${item.providerCode}"
                                                   servicetype=""
                                                   providerServiceIds=""
                                                >
                                                    <i class="fa fa-trash"></i>
                                                </a>
                                            </display:column>
                                        </display:table>
                                    </div>
                                </section>
                            </c:if>

                            <c:if test="${not empty lstProviderServices && lstProviderServices.size() > 0 && lstProviderServices !=null}">
                                <section class="panel search_payment panel-default">
                                    <div class="panel-body">
                                        <div class="clearfix"></div>
                                        <div class="pull-left mt-sm" style="line-height: 30px;">
                                            <spring:message
                                                    code="system.service.list.totalServices"/>&nbsp;<span>PROVIDER SERVICE BLOCK:</span>&nbsp;<span
                                                class="primary_color text-semibold">${totalProviderService}</span>
                                        </div>
                                        <div class="clr"></div>

                                        <spring:message var="colStt"
                                                        code="provider.service.table.no"/>
                                        <spring:message var="colProvider"
                                                        code="transaction.api.table.provider"/>


                                        <spring:message var="colServiceType"
                                                        code="provider.service.table.service.type"/>
                                        <spring:message var="colCode"
                                                        code="provider.service.table.service.code"/>
                                        <spring:message var="colName"
                                                        code="provider.service.table.service.name"/>
                                        <spring:message var="colDescription"
                                                        code="provider.service.table.description"/>
                                        <spring:message var="colAction"
                                                        code="provider.service.table.action"/>


                                        <div class="table-responsive qlsp no-per-page">
                                            <display:table name="lstProviderServices" id="item"
                                                           requestURI="list-detail"
                                                           pagesize="${pagesizeProviderService}"
                                                           partialList="true"
                                                           size="totalProviderService"
                                                           sort="page"
                                                           class="table table-bordered table-striped mb-none">

                                                <%@ include
                                                        file="../include_component/display_table.jsp" %>

                                                <display:column title="STT">
                                                        <span id="row${list.id}" class="rowid">
                                                            <c:out value="${offset + item_rowNum}"/>
                                                        </span>
                                                </display:column>


                                                <display:column title="${colProvider}"
                                                                class="action_icon center"
                                                                headerClass="center">
                                                    ${item.provider.providerCode}
                                                </display:column>

                                                <display:column title="${colServiceType}"
                                                                class="action_icon center"
                                                                headerClass="center">
                                                    ${item.serviceType}
                                                </display:column>


                                                <display:column title="${colCode}"
                                                                class="action_icon center"
                                                                headerClass="center">
                                                    ${item.serviceCode}
                                                </display:column>

                                                <display:column title="${colName}"
                                                                class="action_icon center"
                                                                headerClass="center">
                                                    ${item.serviceName}
                                                </display:column>

                                                <display:column title="${colDescription}"
                                                                class="action_icon center"
                                                                headerClass="center">
                                                    ${item.serviceDesc}
                                                </display:column>


                                                <display:column title="${colAction}"
                                                                class="action_icon center"
                                                                headerClass="center">
                                                    <a href="#" class="remove-block link-active"
                                                       title="<spring:message code="btn.remove.blacklist"/>"
                                                       blocktype="PROVIDER_SERVICE_BLOCK"
                                                       providerServiceIds="${item.id}"
                                                       providerCodes=""
                                                       serviceCodes=""
                                                    >
                                                        <i class="fa fa-trash"></i>
                                                    </a>
                                                </display:column>
                                            </display:table>
                                        </div>
                                    </div>
                                </section>
                            </c:if>

                            <c:if test="${not empty lstTableServices && lstTableServices.size() > 0 && lstTableServices != null}">
                                <section class="panel search_payment panel-default">
                                    <div class="panel-body">
                                        <div class="clearfix"></div>
                                        <div class="pull-left mt-sm" style="line-height: 30px;">
                                            <spring:message
                                                    code="system.service.list.totalServices"/>&nbsp;<span>SERVICE BLOCK:</span>&nbsp;<span
                                                class="primary_color text-semibold">${totalServices }</span>
                                        </div>
                                        <div class="clr"></div>

                                        <spring:message
                                                code="system.service.list.table.column.action.detail"
                                                var="langDetail"/>
                                        <spring:message
                                                code="system.service.list.table.column.action.edit"
                                                var="langEdit"/>
                                        <spring:message
                                                code="system.service.list.table.column.action.delete"
                                                var="langDelete"/>
                                        <spring:message
                                                code="system.service.list.table.column.action.chooseRule"
                                                var="chooseRule"/>
                                        <spring:message
                                                code="system.service.list.table.col.actor.payee"
                                                var="actorPayee"/>
                                        <spring:message
                                                code="system.service.list.table.col.actor.payer"
                                                var="actorPayer"/>


                                        <div class="table-responsive qlsp no-per-page">
                                            <table id="table"
                                                   class="dataTable mb-none no-footer table table-bordered table-striped table-detail-service">
                                                <thead>
                                                <tr>
                                                    <th><spring:message
                                                            code="system.service.list.table.column.servicecode"/></th>
                                                    <th><spring:message
                                                            code="system.service.list.table.column.servicetype"/></th>
                                                    <th><spring:message
                                                            code="system.service.list.table.column.servicename"/></th>

                                                    <th><spring:message
                                                            code="system.service.list.table.column.level"/></th>

                                                    <th class="center"><spring:message
                                                            code="provider.service.table.action"/></th>

                                                </tr>
                                                </thead>
                                                <tbody>

                                                <c:forEach var="item"
                                                        items="${lstTableServices}"
                                                        varStatus="index">
                                                <c:if test="${item.level eq 0}">
                                                <tr style="color:#428bca"
                                                    data-node="treetable-650__${item.serviceCode}"
                                                    data-pnode="">
                                                    <td class="competency sm-text" data-code="A"
                                                        data-competencyid="650-${item.serviceCode}">
                                                            ${item.serviceCode}
                                                    </td>
                                                    </c:if>
                                                    <c:if test="${item.level != 0}">
                                                <tr data-node="treetable-650__${item.pathTreeUnder}"
                                                    data-pnode="treetable-650__${item.pathParentUnder}">
                                                    <td class="competency sm-text"
                                                        data-code="A.${item.pathParentPoint}.${item.serviceCode}"
                                                        data-competencyid="650-${item.pathParentPoint}.${item.serviceCode}">
                                                            ${item.serviceCode}
                                                    </td>
                                                    </c:if>

                                                    <td>${item.serviceType}</td>
                                                    <td>${item.serviceName}</td>


                                                    <td><spring:message
                                                            code="system.service.list.table.column.level.name"/>&nbsp;${item.level}</td>

                                                    <td class="action_icon center">
                                                        <a href="#"
                                                           class="remove-block link-active"
                                                           title="<spring:message code="btn.remove.blacklist"/>"
                                                           serviceCodes="${item.serviceCode}"
                                                           blocktype="SERVICE_BLOCK"
                                                           servicetype=""
                                                           providerCodes=""
                                                           providerServiceIds=""

                                                        >
                                                            <i class="fa fa-trash"></i>
                                                        </a>
                                                    </td>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </section>
                            </c:if>


                            <%--<c:set var = "customersCifs" scope = "page" value = "${param.customersCifs}"/>--%>


                        </div>
                    </section>

                    <%--</div>--%>


                </div>
            </div>
            <!-- end: page -->
        </section>
        <jsp:include page="../include_page/footer.jsp"/>
    </div>
</section>

<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_component/search_form_commons.jsp">
    <jsp:param name="selCustomerType" value="true"/>
    <jsp:param name="selProviderType" value="true"/>
    <jsp:param name="selServiceType" value="true"/>
</jsp:include>
<%--<jsp:include page="../include_page/date_picker.jsp">--%>
<%--<jsp:param name="isFullTime" value="true"/>--%>
<%--</jsp:include>--%>


<script type="text/javascript">
  $(document).ready(function () {
    var customersCifs = '<c:out value = "${param.customersCifs}"/>';
    $('#customersCifs_hidden').val(customersCifs);

    $('.dataTables_info_footer').css('display', 'none');

//    $('#table').treeTable({
//      startCollapsed: false
//    });

//    $('<span style="color: #428bca;" class="icon node-icon glyphicon glyphicon-bookmark"></span>').insertAfter(
//        ".treetable-expander");

  });

  function openTabBlock(paramValue) {
//    $('#blockTypes_hidden').val(paramValue);

    <%--var searchURL = '${BlockCusListUrl}' + '?menu=cus&blockTypes=' + paramValue;--%>
    <%--window.location.href = searchURL;--%>
  }

  function openTabAllBlock() {
    <%--$('#blockTypes_hidden').val(null);--%>

    <%--var searchURL = '${BlockCusListAllUrl}?menu=cus';--%>
    <%--window.location.href = searchURL;--%>
  }

  $('.remove-block').click(function () {

    var customersCifs = '<c:out value = "${param.customersCifs}"/>';
    var textAlert = customersCifs;

    var obj = $(this);
    var blockType = obj.attr('blockType');
    var providerCodes = obj.attr('providerCodes');
    var serviceCodes = obj.attr('serviceCodes');
    var providerServiceIds = obj.attr('providerServiceIds');

    $.MessageBox({
      buttonDone: "<spring:message code="common.btn.Yes"/> ",
      buttonFail: "<spring:message code="common.btn.No"/>",
      message: "<spring:message code="label.are.you.sure.you.want.to.delete.provider.service"/> <b> "
      + textAlert + " </b><spring:message code="label.please.consider.before.making"/>?"
    }).done(function () {
      $.post('remove-blacklist-provider-service', {
            customerCifs: customersCifs,
            blockType: blockType,
            providerCodes: providerCodes,
            serviceCodes: serviceCodes,
            providerServiceIds: providerServiceIds
          },
          function (json) {
            if (json.code === 0) {
              $.MessageBox({message: '<spring:message code="response.status.value.success"/>'});
              setTimeout(function () {
                location.reload();
              }, 1000);

            } else {
              $.MessageBox({message: json.code + ': ' + json.message});
            }
            <%--setTimeout(function () {--%>
            <%--$.MessageBox({message: '<spring:message code="response.status.value.failed"/>'});--%>
            <%--}, 1000);--%>
          });
    });
    return false;

  });

  $('select[name="provider"]').on('change', function () {
    $('#tbl-filter').submit();
  });
  $('select[name="serviceTypeIds"]').on('change', function () {
    $('#tbl-filter').submit();
  });
</script>
</body>
</html>