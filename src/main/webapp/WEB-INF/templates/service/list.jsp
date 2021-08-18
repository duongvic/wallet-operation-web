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
    <title><spring:message code="system.service.list.title.page"/></title>
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
            <jsp:param value="service" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><span><spring:message
                                        code="system.service.list.navigate.system"/></span></li>
                                <li><span class="nav-active"><spring:message
                                        code="system.service.list.navigate.service"/></span></li>
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
                    <div class="form-inline">
                        <div class="pull-left h4 mb-md mt-md"><spring:message
                                code="system.service.list.subnavigate.service"/></div>
                        <div class="fr form-responsive">
                            <sec:authorize access="hasAnyRole('ADMIN_OPERATION')">
                                <a class="mb-xs mt-xs btn btn-success" data-toggle="modal"
                                   data-target="#add"><i
                                        class="fa fa-plus"></i>&nbsp;<spring:message
                                        code="system.service.navigate.btn.create"/></a>
                            </sec:authorize>
                        </div>
                    </div>

                    <section class="panel search_payment panel-default">
                        <div class="panel-body pt-none">

                            <form action="" method="GET" id="tbl-filter">

                                <div class="form-group ml-none mr-none">
                                    <div class="input-group input-group-icon">
                                        <span class="input-group-addon"><span class="icon"
                                                                              style="opacity: 0.4"><i
                                                class="fa fa-search-minus"></i></span></span>
                                        <input type="text" id="search" name="search"
                                               class="form-control" placeholder="${placeholder}"
                                               value="${param.search}"/>
                                    </div>
                                </div>

                                <div class="form-inline">
                                    <jsp:include page="../include_component/date_range.jsp"/>

                                    <div class='pull-right form-responsive bt-plt'>

                                        <select class="form-control" name="status"
                                                style="font: inherit;height: 36px;">
                                            <option value=""><spring:message
                                                    code="select.service.status"/></option>
                                            <option value="true" ${param.status eq 'true' ? 'selected' : '' }>
                                                <spring:message
                                                        code="select.service.active"/></option>
                                            <option value="false" ${param.status eq 'false' ? 'selected' : '' }>
                                                <spring:message
                                                        code="select.service.inactive"/></option>
                                        </select>

                                        <jsp:include
                                                page="../include_component/search_service_type_one.jsp">
                                            <jsp:param name="enableFiltering" value="false"/>
                                        </jsp:include>

                                        <button type="submit" class="btn btn-primary"><i
                                                class="fa fa-search"></i>&nbsp;<spring:message
                                                code="system.service.list.search.btn.search"/>
                                        </button>
                                        <a href="?"
                                           class="btn nomal_color_bk bt-cancel"><spring:message
                                                code="system.service.list.search.btn.cancel"/></a>
                                    </div>

                                </div>
                                <div class="clearfix"></div>
                            </form>

                            <section class="panel search_payment panel-default">
                                <div class="panel-body">
                                    <div class="clearfix"></div>
                                    <div class="pull-left mt-sm" style="line-height: 30px;">
                                        <spring:message code="system.service.list.totalServices"/>&nbsp;<span
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
                                    <spring:message code="system.service.list.table.col.actor.payee"
                                                    var="actorPayee"/>
                                    <spring:message code="system.service.list.table.col.actor.payer"
                                                    var="actorPayer"/>


                                    <div class="table-responsive qlsp no-per-page">
                                        <table id="table"
                                               class="dataTable mb-none no-footer table table-bordered table-striped">
                                            <thead>
                                            <tr>
                                                <th><spring:message
                                                        code="system.service.list.table.column.servicecode"/></th>
                                                <th><spring:message
                                                        code="system.service.list.table.column.servicetype"/></th>
                                                <th><spring:message
                                                        code="system.service.list.table.column.servicename"/></th>
                                                <th class="center"><spring:message
                                                        code="system.service.list.table.column.createdtime"/></th>
                                                <th><spring:message
                                                        code="system.service.list.table.column.level"/></th>
                                                <th><spring:message
                                                        code="system.service.list.table.column.actor"/></th>
                                                <th class="center"><spring:message
                                                        code="system.service.list.table.column.parentfee"/></th>
                                                <th class="center"><spring:message
                                                        code="system.service.list.table.column.status"/></th>
                                                <th class="center col-action"><spring:message
                                                        code="system.service.list.table.column.action"/></th>
                                            </tr>
                                            </thead>
                                            <tbody>

                                            <c:forEach var="item" items="${services}"
                                                       varStatus="index">
                                                <c:if test="${item.level eq 0}">
                                                    <tr style="color:#428bca" data-node="treetable-650__${item.serviceCode}" data-pnode="">
                                                    <td class="competency sm-text" data-code="A"
                                                        data-competencyid="650-${item.serviceCode}">
                                                            ${item.serviceCode}
                                                    </td>
                                                </c:if>
                                                <c:if test="${item.level != 0}">
                                                    <tr data-node="treetable-650__${item.pathTreeUnder}" data-pnode="treetable-650__${item.pathParentUnder}">
                                                    <td class="competency sm-text"
                                                        data-code="A.${item.pathParentPoint}.${item.serviceCode}"
                                                        data-competencyid="650-${item.pathParentPoint}.${item.serviceCode}">
                                                            ${item.serviceCode}
                                                    </td>
                                                </c:if>

                                                <td>${item.serviceType}</td>
                                                <td>${item.serviceName}</td>
                                                <td class="center"><fmt:formatDate
                                                        pattern="HH:mm dd-MM-yyyy"
                                                        value="${item.creationDate}"/></td>

                                                <td><spring:message
                                                        code="system.service.list.table.column.level.name"/>&nbsp;${item.level}</td>

                                                <td>
                                                    <c:choose>
                                                        <c:when test="${item.isActorPayee eq 'Y'.charAt(0)}">
                                                            <a class="link-active">${actorPayee}</a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a class="link-disactive">${actorPayer}</a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>

                                                <sec:authorize
                                                        access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_MANAGER')"
                                                        var="perStatisService"/>
                                                <td class="sw center">
                                                    <div class="">
                                                        <c:choose>
                                                            <c:when test="${item.isParentFeeStructureAllowed()}">
                                                                <div class="checkbox-custom checkbox-success checkboxWrapper"
                                                                     style="margin-left: 49%;">
                                                                    <input type="checkbox"
                                                                           name="ckaccess"
                                                                           id="checkboxActive"
                                                                           checked="checked"
                                                                           disabled="disabled">
                                                                    <label for="checkboxActive"></label>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div class="checkbox-custom checkbox-info checkboxWrapper"
                                                                     style="margin-left: 49%;">
                                                                    <input type="checkbox"
                                                                           name="ckaccess"
                                                                           id="checkboxDiactive"
                                                                           disabled="disabled">
                                                                    <label for="checkboxDiactive"></label>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </td>

                                                <td class="sw center">
                                                    <div class="switch switch-sm switch-success">
                                                        <c:choose>
                                                            <c:when test="${perStatisService eq true}">
                                                                <input type="checkbox"
                                                                       name="switch" ${item.status eq 'Y'.charAt(0) ? 'checked' : ''}
                                                                       value="${item.id}"
                                                                       id="ck_${item.id }_${index.index}"/>
                                                                <a href="#"
                                                                   class="switchery-mask link-status"
                                                                   data-toggle="modal"
                                                                   data-target="#inactive"
                                                                   id="ck_${item.id }_${index.index}"
                                                                   serviceId="${item.id}"
                                                                   serviceCode="${item.serviceCode}"
                                                                   serviceName="${item.serviceName}"
                                                                   serviceShortName="${item.serviceShortName}"
                                                                   customerTypeSupported="${item.customerTypeSupported}"
                                                                   active="${item.status}"></a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="checkbox" disabled
                                                                       name="switch" ${item.status eq 'Y'.charAt(0) ? 'checked' : ''}
                                                                       value="${item.id}"
                                                                       id="ck_${item.id }_${index.index}"/>
                                                                <a href="javascript:;"></a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </td>

                                                <sec:authorize
                                                        access="hasAnyRole('ADMIN_OPERATION')"
                                                        var="roleAction"/>
                                                <td class="action_icon center">
                                                    <c:choose>
                                                        <c:when test="${roleAction}">
                                                            <a class="service-rule-add link-active mr-xs"
                                                               data-toggle="modal"
                                                               data-target="#service-rule-add-modal"
                                                               title="${chooseRule}"
                                                               serviceId="${item.id}"
                                                               serviceCode="${item.serviceCode}"
                                                               serviceShortName="${item.serviceShortName}"
                                                               customerTypeSupported="${item.customerTypeSupported}"
                                                               serviceName="${item.serviceName}">
                                                                <i class="fa fa-plus"
                                                                   aria-hidden="true"></i>
                                                            </a>

                                                            <a href="#" class="link-edit"
                                                               title="${langEdit}"
                                                               data-toggle="modal"
                                                               data-target="#edit"
                                                               serviceId="${item.id}"
                                                               serviceCode="${item.serviceCode}"
                                                               serviceDesc="${item.serviceDesc}"
                                                               serviceType="${item.serviceType}"
                                                               serviceName="${item.serviceName}"
                                                               serviceShortName="${item.serviceShortName}"
                                                               customerTypeSupported="${item.customerTypeSupported}"
                                                               servicePrices="${item.servicePrices}"
                                                               serviceLevel="${item.level}"
                                                               isActorPayee="${item.isActorPayee}"
                                                               isParentFee="${item.isParentFeeStructureAllowed()}"
                                                               serviceParentId="${item.parent.id}"
                                                               parentServiceCode="${item.parentServiceCode}">
                                                                <i class="fa fa-pencil"
                                                                   aria-hidden="true"></i>
                                                            </a>

                                                            <sec:authorize
                                                                    access="hasAnyRole('ADMIN_OPERATION') and !hasAnyRole('CUSTOMERCARE_MANAGER', 'TECH_SUPPORT')">
                                                                <a href="<c:url value="<%=TrueServiceController.TRUE_SERVICE_DETAIL%>"/>/${item.id}"
                                                                   class="detail"
                                                                   title="${langDetail}">
                                                                    <i class="fa fa-info-circle "
                                                                       aria-hidden="true"></i>
                                                                </a>
                                                            </sec:authorize>

                                                            <a href="#" class="link-delete"
                                                               title="${langDelete}"
                                                               data-toggle="modal"
                                                               data-target="#${item.status eq 'Y'.charAt(0) ? 'preDelete' : 'delete'}"
                                                               serviceId="${item.id}"
                                                               serviceCode="${item.serviceCode}"
                                                               serviceShortName="${item.serviceShortName}"
                                                               customerTypeSupported="${item.customerTypeSupported}"
                                                               serviceName="${item.serviceName}">
                                                                <i class="fa fa-trash"
                                                                   aria-hidden="true"></i>
                                                            </a>

                                                        </c:when>
                                                        <c:otherwise>
                                                            <a href="javascript:;" class="not-role"
                                                               title="${chooseRule}">
                                                                <i class="fa fa-plus"
                                                                   aria-hidden="true"></i>
                                                            </a>

                                                            <a href="javascript:;" class="not-role"
                                                               title="${langDetail}">
                                                                <i class="fa fa-info-circle "
                                                                   aria-hidden="true"></i>
                                                            </a>

                                                            <a href="javascript:;" class="not-role"
                                                               title="${langEdit}">
                                                                <i class="fa fa-pencil"
                                                                   aria-hidden="true"></i>
                                                            </a>

                                                            <a href="javascript:;" class="not-role"
                                                               title="${langDelete}">
                                                            <i class="fa fa-trash"
                                                               aria-hidden="true"></i>
                                                            </a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </section>
                        </div>
                    </section>
                </div>
            </div>
            <!-- end: page -->
        </section>
        <jsp:include page="service_add.jsp"/>
        <jsp:include page="service_delete.jsp"/>
        <jsp:include page="service_edit.jsp"/>
        <jsp:include page="service_inactive.jsp"/>
        <jsp:include page="service_rule_add.jsp"/>
        <jsp:include page="../include_page/footer.jsp"/>

    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_page/date_picker.jsp">
    <jsp:param name="isFullTime" value="true"/>
    <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>

<script type="text/javascript">

  $(document).ready(function () {
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
  });

  function createParentServiceCode(parentServiceId, serviceType, serviceLevel, serviceCode) {
    $('#parentServiceCodeEdit').html('refresh', true);
    var urlParentServicdCodeEdit = ctx + '/service/service-profile/listFeeEdit';
    $.ajax({
      type: 'POST',
      url: urlParentServicdCodeEdit,
      data: {
        parentServiceId: parentServiceId,
        serviceType: serviceType,
        serviceLevel: serviceLevel,
        serviceCode: serviceCode
      },
      success: function (data) {
        var listData = data.split(';');
        if (listData != null && listData != "" && listData instanceof Array) {
          var trueServices = JSON.parse(listData[0]);

          function formatResult(node) {
            var $result = $('<span style="padding-left:' + (20 * node.level) + 'px;">'
                + '<span style="color: #428bca;" class="icon node-icon glyphicon glyphicon-bookmark"></span>'
                + '&nbsp;' + node.text + ' - ' + node.code + '</span>');
            return $result;
          }

          $("#parentServiceCodeEdit").select2({
            dropdownParent: $("#edit"),
            placeholder: '<spring:message code="system.service.popup.create.lable.selectParentServiceCode"/>',
            width: "100%",
            data: trueServices,
            formatSelection: function (item) {
              return item.text + ' - ' + item.code
            },
            formatResult: function (item) {
              return item.id
            },
            templateResult: formatResult
          });
          $("#select2-parentServiceCodeEdit-container").html(listData[1]);
          $("#parentServiceCodeEdit").val(listData[2]);
        }
      }
    });
  }
</script>
</body>
</html>
