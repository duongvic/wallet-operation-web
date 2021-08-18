<%@ page import="vn.mog.ewallet.operation.web.controller.service.TrueServiceController" %>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceLevel" %>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.TelcoServiceType" %>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.TelcoType" %>

<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<c:set var="telcoTypes" value="<%=TelcoType.values()%>" scope="request"/>
<c:set var="telcoServiceTypes" value="<%=TelcoServiceType.values()%>" scope="request"/>
<c:set var="serviceLevels" value="<%=ServiceLevel.values()%>" scope="request"/>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
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
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                                <li><span><spring:message code="menu.left.customer.manager"/></span>
                                </li>
                                <li><span class="nav-active"><spring:message
                                        code="menu.left.customer.block.list"/> </span></li>
                                <li><span class="nav-active"><spring:message
                                        code="common.btn.add"/> </span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <jsp:include page="../include_page/message.jsp"/>

            <!-- start: page -->

            <spring:message code="select.choose.all" var="langChooseAll"/>
            <spring:message code="select.service.type" var="langServicetype"/>
            <spring:message code="system.service.list.form.search.placeholder" var="placeholder"/>

            <div class="content-body-wrap">
                <div class="container-fluid">

                    <div class="tabs">
                        <ul class="nav nav-tabs">
                            <li class="${'SERVICE_BLOCK' == blockType ? 'active' : ''}">
                                <a id="SERVICE_BLOCK" onclick="openTabBlock('SERVICE_BLOCK')"
                                   href="#">BLOCK SERVICE</a>
                            </li>
                            <li class="${'PROVIDER_BLOCK' == blockType ? 'active' : ''}">
                                <a id="PROVIDER" onclick="openTabBlock('PROVIDER_BLOCK')"
                                   href="#">BLOCK PROVIDER</a>
                            </li>
                            <li class="${'PROVIDER_SERVICE_BLOCK' == blockType ? 'active' : ''}">
                                <a id="PROVIDER_SERVICE_BLOCK"
                                   onclick="openTabBlock('PROVIDER_SERVICE_BLOCK')"
                                   href="#">BLOCK PROVIDER SERVICE</a>
                            </li>
                        </ul>

                        <div class="clearfix"></div>

                        <section class="panel search_payment panel-default">
                            <div class="panel-body pt-none">

                                <form action="" method="GET" id="tbl-filter">

                                    <div class="form-group ml-none mr-none">
                                        <div class="input-group input-group-icon">
                                        <span class="input-group-addon"><span class="icon"
                                                                              style="opacity: 0.4"><i
                                                class="fa fa-search-minus"></i></span></span>
                                            <input type="text" id="search" name="search"
                                                   class="form-control mb-xs" placeholder="${placeholder}"
                                                   value="${param.search}"/>
                                            <select class="find-customer" name="customerIds"
                                                    id="customerIds" multiple="multiple">
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-inline">
                                        <c:set var="allcustomerIdsBlocke" value=","/>
                                        <c:forEach var="stcustomerIdsBlock"
                                                   items="${paramValues.customerIdsBlock}">
                                            <c:set var="allcustomerIdsBlock"
                                                   value="${allcustomerIdsBlock}${stcustomerIdsBlock},"/>
                                        </c:forEach>
                                        <c:set var="uaaCustomerBeanClassName"
                                               value="<%= vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer.class.getName() %>"/>

                                        <%--<select class="form-control multiple-select hidden"--%>
                                                <%--multiple="multiple" id="customerIdsBlock"--%>
                                                <%--name="customerIdsBlock">--%>
                                            <%--<c:choose>--%>
                                                <%--<c:when test="${not empty customers && customers.size() > 0 }">--%>
                                                    <%--<c:choose>--%>
                                                        <%--<c:when test="${customers[0]['class'].name eq uaaCustomerBeanClassName}">--%>
                                                            <%--<c:forEach var="item"--%>
                                                                       <%--items="${customers}">--%>
                                                                <%--<option value="${item.cif}" ${fn:contains(allcustomerIdsBlock, item.cif) ? 'selected' : ''} >${item.displayName}&nbsp;(${item.msisdn})</option>--%>
                                                            <%--</c:forEach>--%>
                                                        <%--</c:when>--%>
                                                        <%--<c:otherwise>--%>
                                                            <%--<c:forEach var="item"--%>
                                                                       <%--items="${customers}">--%>
                                                                <%--<option value="${item.cif}" ${fn:contains(allcustomerIdsBlock, item.cif) ? 'selected' : ''} >${item.fullName}&nbsp;(${item.msisdn})</option>--%>
                                                            <%--</c:forEach>--%>
                                                        <%--</c:otherwise>--%>
                                                    <%--</c:choose>--%>
                                                <%--</c:when>--%>
                                                <%--<c:otherwise>--%>
                                                    <%--<option value="">N/A</option>--%>
                                                <%--</c:otherwise>--%>
                                            <%--</c:choose>--%>
                                        <%--</select>--%>

                                        <%--  provider--%>
                                        <c:if test="${blockType eq 'PROVIDER_SERVICE_BLOCK'}">
                                            <jsp:include
                                                    page="../include_component/search_provider_type_one.jsp"/>
                                        </c:if>
                                        <%--provider--%>


                                        <%--serviceType--%>
                                        <c:if test="${blockType == null || blockType =='' || blockType eq 'SERVICE_BLOCK'}">
                                            <jsp:include
                                                    page="../include_component/search_service_type_one.jsp">
                                                <jsp:param name="enableFiltering" value="false"/>
                                            </jsp:include>
                                        </c:if>

                                        <div class='pull-right form-responsive bt-plt'>
                                            <button type="submit" class="btn btn-primary"><i
                                                    class="fa fa-search"></i>&nbsp;<spring:message
                                                    code="system.service.list.search.btn.search"/>
                                            </button>

                                            <sec:authorize access="hasAnyRole('ADMIN_OPERATION')">
                                                <%--<a class="mb-xs mt-xs btn btn-success"--%>
                                                <%--id="btn_add_block"--%>
                                                <%--data-toggle="modal"--%>
                                                <%--data-target="#dialog_add_block">--%>
                                                <%--<i class="fa fa-plus"></i>&nbsp;--%>
                                                <%--<spring:message--%>
                                                <%--code="system.service.navigate.btn.create"/>--%>
                                                <%--</a>--%>

                                                <a href="#" class="mb-xs mt-xs btn btn-success"
                                                   id="btn_add_block">
                                                    <i class="fa fa-plus"></i>&nbsp;
                                                    <spring:message
                                                            code="system.service.navigate.btn.create"/>
                                                </a>

                                            </sec:authorize>

                                        </div>

                                    </div>
                                    <div class="clearfix"></div>


                                    <input type="hidden" id="blockTypes_hidden"
                                           name="blockType"
                                           value="${blockType}">


                                    <textarea name="listNameCusBlock" class="form-control hidden"
                                              disabled
                                              rows="3"
                                              id="listNameCusBlock">${dataListCusBlock}</textarea>

                                    <textarea name="lstParamService" class="form-control hidden"
                                              disabled
                                              rows="3"
                                              id="lstParamService">${lstParamService}</textarea>

                                    <textarea name="lstParamProvider" class="form-control hidden"
                                              disabled
                                              rows="3"
                                              id="lstParamProvider">${lstParamProvider}</textarea>

                                    <textarea name="lstParamProviderService"
                                              class="form-control hidden"
                                              disabled
                                              rows="3"
                                              id="lstParamProviderService">${lstParamProviderService}</textarea>


                                </form>

                                <c:if test="${blockType eq 'SERVICE_BLOCK' && lstTableServices != null}">
                                    <section class="panel search_payment panel-default">
                                        <div class="panel-body">
                                            <div class="clearfix"></div>
                                            <div class="pull-left mt-sm" style="line-height: 30px;">
                                                <spring:message
                                                        code="system.service.list.totalServices"/>&nbsp;<span
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

                                                        <td class="sw center">
                                                            <div class="">
                                                                <div class="checkbox-custom checkbox-info checkboxWrapper"
                                                                     style="margin-left: 49%;">
                                                                    <input type="checkbox"
                                                                           name="checkboxService"
                                                                           value="${item.serviceCode}"
                                                                           id="checkboxService_${index.count}_${item.serviceCode}"
                                                                           serviceId="${item.id}"
                                                                           serviceCode="${item.serviceCode}">
                                                                    <label for="checkboxService_${index.count}_${item.serviceCode}">
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </section>
                                </c:if>

                                <c:if test="${not empty providerTypes && providerTypes.size() > 0 && providerTypes !=null && blockType eq 'PROVIDER_BLOCK'}">
                                    <section class="panel search_payment panel-default">
                                        <div class="panel-body">
                                            <div class="clearfix"></div>
                                            <div class="pull-left mt-sm" style="line-height: 30px;">
                                                <spring:message
                                                        code="system.service.list.totalServices"/>&nbsp;<span
                                                    class="primary_color text-semibold">${totalProvider }</span>
                                            </div>
                                            <div class="clr"></div>

                                            <spring:message var="colStt"
                                                            code="provider.service.table.no"/>
                                            <spring:message var="colCode"
                                                            code="provider.list.table.code"/>
                                            <spring:message var="colName"
                                                            code="provider.list.table.name"/>
                                            <spring:message var="colAction"
                                                            code="provider.service.table.action"/>


                                            <div class="table-responsive qlsp no-per-page">
                                                <table id="table-detail-provider"
                                                       class="dataTable mb-none no-footer table table-bordered table-striped">
                                                    <thead>
                                                    <tr>
                                                        <th>${colCode}</th>
                                                        <th>${colName}</th>
                                                        <th>${colAction}</th>

                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach var="item"
                                                               items="${providerTypes}"
                                                               varStatus="index">
                                                        <tr>
                                                            <td>${item.providerCode}</td>
                                                            <td>${item.name}</td>

                                                            <td class="sw center">
                                                                <div class="">
                                                                    <div class="checkbox-custom checkbox-info checkboxWrapper"
                                                                         style="margin-left: 49%;">
                                                                        <input type="checkbox"
                                                                               name="checkboxProvider"
                                                                               id="checkboxProvider"
                                                                               value="${item.providerCode}">
                                                                        <label for="checkboxProvider"></label>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </section>
                                </c:if>

                                <c:if test="${not empty lstProviderServices && lstProviderServices.size() > 0 && lstProviderServices !=null && blockType eq 'PROVIDER_SERVICE_BLOCK'}">
                                    <section class="panel search_payment panel-default">
                                        <div class="panel-body">
                                            <div class="clearfix"></div>
                                            <div class="pull-left mt-sm" style="line-height: 30px;">
                                                <spring:message
                                                        code="system.service.list.totalServices"/>&nbsp;<span
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
                                            <spring:message var="colHealthy"
                                                            code="provider.service.table.healthy"/>
                                            <spring:message var="colAction"
                                                            code="provider.service.table.action"/>


                                            <div class="table-responsive qlsp no-per-page">
                                                <table id="table-detail-provider-service"
                                                       class="dataTable mb-none no-footer table table-bordered table-striped">
                                                    <thead>
                                                    <tr>
                                                        <th>${colProvider}</th>
                                                        <th>${colServiceType}</th>
                                                        <th>${colCode}</th>
                                                        <th>${colName}</th>
                                                        <th>${colDescription}</th>
                                                        <th>${colAction}</th>

                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach var="item"
                                                               items="${lstProviderServices}"
                                                               varStatus="index">
                                                        <tr>
                                                            <td>${item.provider.providerCode}</td>
                                                            <td>${item.serviceType}</td>
                                                            <td>${item.serviceCode}</td>
                                                            <td>${item.serviceName}</td>
                                                            <td>${item.serviceDesc}</td>

                                                            <td class="sw center">
                                                                <div class="">
                                                                    <div class="checkbox-custom checkbox-info checkboxWrapper"
                                                                         style="margin-left: 49%;">
                                                                        <input type="checkbox"
                                                                               name="checkboxProviderService"
                                                                               id="checkboxProviderService"
                                                                               value="${item.id}">
                                                                        <label for="checkboxProviderService"></label>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </section>
                                </c:if>

                            </div>
                        </section>

                    </div>
                </div>
            </div>
            <!-- end: page -->
        </section>
        <jsp:include page="../include_page/footer.jsp"/>

    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_component/search_form_commons.jsp">
    <jsp:param name="blockType" value="true"/>
    <jsp:param name="selBlockType" value="true"/>
    <jsp:param name="selProviderType" value="true"/>
    <jsp:param name="selServiceType" value="true"/>
</jsp:include>
<%--<jsp:param name="selCustomerBlock" value="true"/>--%>

<script type="text/javascript">
  $(document).ready(function () {

    $('#customerIdsBlock').multiselect({
      enableFiltering: true,
      includeSelectAllOption: true,
      selectAllValue: '',
      selectAllText: '<spring:message code="select.choose.all"/>',
      maxHeight: 400,
      dropUp: true,
      nonSelectedText: '<spring:message code="select.account"/>',
      inheritClass: true,
      numberDisplayed: 1,
      onChange: function (element, checked) {
        var brands = $('#customerIdsBlock option:selected');
        var selected = [];
        $(brands).each(function (index, brand) {
          selected.push([$(this).val()]);
          $('#listNameCusBlock').val(selected);
        });
        $('#listNameCusBlock').val(selected);
      }
    });

    $('#table').treeTable({
      startCollapsed: false
    });

    $('<span style="color: #428bca;" class="icon node-icon glyphicon glyphicon-bookmark"></span>').insertAfter(
        ".treetable-expander");
    $('.switch input[name=switch]').each(function () {
      var item = document.querySelector('#' + $(this).attr('id'));
      var color = '#64bd63';
      var switchery = new Switchery(item, {color: color, size: 'small'});
      if ($(this).disabled) {
        switchery.disable();
      }
    });

    /* SERVICE*/
    var $tbl = $('.table-detail-service');
    var $bodychk = $tbl.find('tbody input:checkbox');
    $bodychk.on('change', function () {
      if ($(this).is(':checked')) {

        $(this).closest('tr').addClass('bgCheckbox');
      } else {
        $(this).closest('tr').removeClass('bgCheckbox');
      }

      var lstParamService = [];
      var checkboxes = $("input:checkbox[name=checkboxService]")
      for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
          lstParamService.push(checkboxes[i].value)
        } else {
          lstParamService.slice(checkboxes[i].value)
        }

        $('#lstParamService').val(lstParamService);
      }

    });
    /*END SERVICE*/

    /* PROVIDER*/
    var $tblProvider = $('#table-detail-provider');
    var $bodychkProvider = $tblProvider.find('tbody input:checkbox');
    $bodychkProvider.on('change', function () {

      if ($(this).is(':checked')) {
        $(this).closest('tr').addClass('bgCheckbox');
      } else {
        $(this).closest('tr').removeClass('bgCheckbox');
      }

      var lstParamProvider = [];
      var checkboxes = $("input:checkbox[name=checkboxProvider]")
      for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
          lstParamProvider.push(checkboxes[i].value);
        } else {
          lstParamProvider.slice(checkboxes[i].value);
        }
        $('#lstParamProvider').val(lstParamProvider);
      }

    });
    /*END PROVIDER*/

    /* PROVIDER SERVICE*/

    var $tblProviderService = $('#table-detail-provider-service');
    var $bodychkProviderService = $tblProviderService.find('tbody input:checkbox');
    $bodychkProviderService.on('change', function () {
      if ($(this).is(':checked')) {

        $(this).closest('tr').addClass('bgCheckbox');
      } else {
        $(this).closest('tr').removeClass('bgCheckbox');

      }

      var lstParamProviderService = [];
      var checkboxes = $("input:checkbox[name=checkboxProviderService]")
      for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
          lstParamProviderService.push(checkboxes[i].value)
        } else {
          lstParamProviderService.slice(checkboxes[i].value)
        }

        $('#lstParamProviderService').val(lstParamProviderService);
      }

    });

    /*END  PROVIDER SERVICE*/

  });


  $('#btn_add_block').click(function () {
    var textAlert = '${blockType}';
    if (textAlert == 'SERVICE_BLOCK') {
      textAlert = 'BLOCK SERVICE';
    } else if (textAlert == 'PROVIDER_BLOCK') {
      textAlert = 'BLOCK PROVIDER';
    } else if (textAlert == 'PROVIDER_SERVICE_BLOCK') {
      textAlert = 'BLOCK PROVIDER SERVICE';
    }

    var customerCifs, providerCodes, serviceCodes, providerServiceIds = null;
    if ($('#customerIds').val() != null || $('#customerIds').val() != undefined || $(
      '#customerIds').val() != '') {
      customerCifs = $('#customerIds').val();
    }
    if ($('#lstParamProvider').val() != null || $('#lstParamProvider').val() != undefined || $(
            '#lstParamProvider').val() != '') {
      providerCodes = $('#lstParamProvider').val();
    }
    if ($('#lstParamService').val() != null || $('#lstParamService').val() != undefined || $(
            '#lstParamService').val() != '') {
      serviceCodes = $('#lstParamService').val();
    }
    if ($('#lstParamProviderService').val() != null || $('#lstParamProviderService').val()
        != undefined || $('#lstParamProviderService').val() != '') {
      providerServiceIds = $('#lstParamProviderService').val();
    }

    if ($('#customerIds').val() != "") {
      $.MessageBox({
        buttonDone: "<spring:message code="common.btn.Yes"/> ",
        buttonFail: "<spring:message code="common.btn.No"/>",
        message: "<spring:message code="common.are.you.sure.you.want.to"/> <b> " + textAlert
        + " </b><spring:message code="common.service"/>?"
      }).done(function () {
        $.post('add-block', {
              customerCifs: customerCifs,
              blockType: '${blockType}',
              providerCodes: providerCodes,
              serviceCodes: serviceCodes,
              providerServiceIds: providerServiceIds
            },
            function (json) {
              if (json.code === 0) {
                $.MessageBox({message: '<spring:message code="response.status.value.success"/>'});
//              location.reload();
              }else{
                $.MessageBox({message: json.code +': ' + json.message });
              }
              <%--setTimeout(function () {--%>
              <%--$.MessageBox({message: '<spring:message code="response.status.value.failed"/>'});--%>
              <%--}, 1000);--%>
            });
      });
      return false;
    }else {
      $.MessageBox({
        message: "<b>Thông báo!</b><br>Bạn chưa chọn tài khoản"
      });
    }


  });

  function openTabBlock(paramValue) {
    $('#blockTypes_hidden').val(paramValue);
    var searchURL = '${BlockCusListAddUrl}' + '?menu=cus&blockType=' + paramValue;
    window.location.href = searchURL;
  }

  $('select[name="provider"]').on('change', function () {
    $('#tbl-filter').submit();
  });
  $('select[name="serviceTypeIds"]').on('change', function () {
    $('#tbl-filter').submit();
  });

  // ---------------------------------------------------
  var url = "/ajax-controller/find-customers";
  $('.find-customer').select2({
    width: "100%",
    dropdownAutoWidth: true,
    ajax: {
      url: ctx + url,
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
        for (var i = 0; i < data.length; i++) {
          var lineObj = {
            id: data[i].cif,
            text: data[i].fullName + '(SĐT: ' + data[i].msisdn + ',' + ' cif: ' + data[i].cif + ')'
          };
          retVal.push(lineObj);
        }

        return {
          // results: data.items
          results: retVal

        };
      }
    },
    placeholder: 'Tìm kiếm khách hàng bằng SDT',
    minimumInputLength: 3,
    language: {
      inputTooShort: function () {
        return 'Nhập bằng hoặc nhiều hơn 3 kí tự';
      }
    }
  });
</script>
</body>
</html>
