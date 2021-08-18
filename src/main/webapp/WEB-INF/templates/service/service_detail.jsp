<%@ page import="vn.mog.ewallet.operation.web.controller.service.TrueServiceController" %>
<%@ page import="static vn.mog.ewallet.operation.web.controller.service.TrueServiceController.TRUE_SERVICE_CONTROLLER" %>
<%@ page import="static vn.mog.ewallet.operation.web.controller.service.TrueServiceController.TRUE_SERVICE_DETAIL" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>
<c:set var="requestServiceCode" value="${param['serviceCode']}"/>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="system.service.list.title.page"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="switchLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>

  <jsp:include page="../include_page/js_service.jsp">
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="switchLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <style>
    .checkbox-info label:before {border: 1px solid #ecd6d6}
    .treetable-expanded > td:first-child, .treetable-collapsed > td:first-child {padding-left: 2em;}
    .treetable-expanded > td:first-child > .treetable-expander, .treetable-collapsed > td:first-child > .treetable-expander {top: 0.05em;position: relative;margin-left: -0.5em;margin-right: 0.25em;}
    .treetable-expanded .treetable-expander, .treetable-expanded .treetable-expander {width: 1em;height: 1em;cursor: pointer;position: relative;display: inline-block;}
    .treetable-depth-1 > td:first-child {padding-left: 3em;}
    .treetable-depth-2 > td:first-child {padding-left: 4.5em;}
    .treetable-depth-3 > td:first-child {padding-left: 6em;}
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

    <c:url var="urlTrueServiceContro" value='<%=TRUE_SERVICE_CONTROLLER%>'/>
    <c:set var="urlTrueServiceDetail" value="<%=TRUE_SERVICE_DETAIL%>"/>

    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="system.service.detail.navigate.system"/></span></li>
                <li><span><a href="<c:url value="<%=TrueServiceController.TRUE_SERVICE_LIST%>"/> " class="hight-title"><spring:message code="system.service.detail.navigate.service"/></a></span></li>
                <li><span class="nav-active"><spring:message code="system.service.detail.navigate.servicedetail"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <spring:message code="system.service.list.table.col.actor.payee" var="actorPayee"/>
      <spring:message code="system.service.list.table.col.actor.payer" var="actorPayer"/>
      <spring:message code="system.service.list.table.column.action.chooseRule" var="chooseRule"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div id="tab1">
            <section class="panel panel-default">
              <div class="panel-title">
                <h4 class="fl"><spring:message code="system.service.detail.summary.title"/></h4>
                <ul class="panel-tools fl tool-filter">
                  <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a>
                  </li>
                </ul>
                <div class="clr"></div>
              </div>
              <div class="panel-body report_search_form">
                <div class="form-group">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.service.detail.summary.row.parentservice"/> </label>
                  <div class="col-sm-9 col-md-10 text-normal">${service.parentServiceCode }</div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.service.detail.summary.row.servicetype"/> </label>
                  <div class="col-sm-9 col-md-10 text-normal">${service.serviceType }</div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.service.detail.summary.row.servicecode"/> </label>
                  <div class="col-sm-9 col-md-10 text-normal">${service.serviceCode}</div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.service.detail.summary.row.servicename"/> </label>
                  <div class="col-sm-9 col-md-10 text-normal">${service.serviceName}
                  </div>
                </div>

                <div class="form-group">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.service.detail.summary.row.service.short.name"/> </label>
                  <div class="col-sm-9 col-md-10 text-normal">${service.serviceShortName}
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.service.detail.summary.row.service.customerTypeSupported"/> </label>
                  <div class="col-sm-9 col-md-10 text-normal">${service.customerTypeSupported}
                  </div>
                </div>

                <div class="form-group">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.service.detail.summary.row.serviceprice"/> </label>
                  <div class="col-sm-9 col-md-10 text-normal">${service.servicePrices}
                  </div>
                </div>

                <div class="form-group">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.service.detail.summary.row.createdtime"/> </label>
                  <div class="col-sm-9 col-md-10 text-normal">
                    <fmt:formatDate pattern="HH:mm dd-MM-yyyy" value="${service.creationDate}"/>
                  </div>
                </div>

                <div class="form-group">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.service.detail.summary.row.status"/> </label>
                  <div class="col-sm-9 col-md-10 text-normal">
                    <c:choose>
                      <c:when test="${service.status eq 'Y'.charAt(0) }">
                        <spring:message code="select.service.active"/>
                      </c:when>
                      <c:otherwise>
                        <spring:message code="select.service.inactive"/>
                      </c:otherwise>
                    </c:choose>
                  </div>
                </div>

                <div class="form-group">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.service.detail.summary.row.actor"/> </label>
                  <div class="col-sm-9 col-md-10 text-normal">
                    <c:choose>
                      <c:when test="${service.isActorPayee eq 'Y'.charAt(0)}">
                        <a class="link-active">${actorPayee}</a>
                      </c:when>
                      <c:otherwise>
                        <a class="link-disactive">${actorPayer}</a>
                      </c:otherwise>
                    </c:choose>
                  </div>
                </div>

                <div class="form-group">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.service.detail.summary.row.parentfee"/></label>
                  <div class="col-sm-9 col-md-10 text-normal">
                    <div class="pull-left">
                      <div class="checkbox-custom checkbox-success checkboxWrapper" style="margin-left: 49%;">
                        <c:choose>
                          <c:when test="${service.isParentFeeStructureAllowed()}">
                            <input type="checkbox" name="ckaccess" id="checkboxExample3" checked="checked" disabled="disabled">
                          </c:when>
                          <c:otherwise>
                            <input type="checkbox" name="ckaccess" disabled="disabled">
                          </c:otherwise>
                        </c:choose>
                        <label for="checkboxExample3"></label>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="form-group">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.service.detail.summary.row.description"/> </label>
                  <div class="col-sm-9 col-md-10 text-normal">${service.serviceDesc} </div>
                </div>
              </div>
            </section>

            <%-- service attribute  --%>
            <div class="clearfix"></div>
            <section class="panel search_payment panel-default">
              <div class="panel-title">
                <h4 class="fl"><spring:message code="system.service.attribute.table.title"/></h4>
                <ul class="panel-tools fl tool-filter">
                  <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
                </ul>
                <div class="clr"></div>
              </div>

              <div class="panel-body">
                <form class="table-tranrule-add" action="${urlTrueServiceContro}/updateTrueServiceAttribute" method="post">
                  <input type="hidden" id="serviceId" name="serviceId" value="${service.id}">
                  <div class="clr"></div>
                  <div class="table-responsive qlsp no-per-page">
                    <table id="tblServiceAttribute" class="dataTable mb-none no-footer table table-bordered table-striped">
                      <thead>
                      <tr>
                        <th><spring:message code="system.trueservice.attribute.table.col.no"/></th>
                        <th><spring:message code="system.trueservice.attribute.table.col.attribute.type"/>&nbsp;<span style="color: red">*</span></th>
                        <th><spring:message code="system.trueservice.attribute.table.col.attribute.value"/>&nbsp;<span style="color: red">*</span></th>
                        <th class="center"><spring:message code="system.trueservice.attribute.table.col.action"/></th>
                      </tr>
                      </thead>
                      <tbody>
                      <spring:message code="system.trueservice.attribute.table.col.text.deleterule" var="deleteAttributeRule"/>
                      <c:choose>
                        <c:when test="${numberRow > 0}">
                          <input type="hidden" id="numberRow" name="numberRow" value="${numberRow}">
                          <c:set var="row" value="0"/>
                          <c:forEach var="attribute" items="${serviceAttributes}">
                            <tr id="rangeStep${row}">
                              <td>${row + 1}</td>
                              <td>
                                <select id="serviceAttributeId${row}" name="serviceAttributeId${row}" class="form-control" required>
                                  <c:forEach var="item" items="${serviceAttributeTypes}">
                                    <option value="${item.key}" ${item.key eq attribute.serviceAttributeType ? 'selected' : ''}>${item.value}</option>
                                  </c:forEach>
                                </select>
                              </td>
                              <td>
                                <input type="text" id="attributeValue${row}" name="attributeValue${row}" value="${attribute.value}" class="form-control" required>
                              </td>
                              <td class="action_icon center">
                                <a href="#" class="link-delete" onclick="removeServiceAttribute(${row})" title="${deleteAttributeRule}"><i class="fa fa-trash" aria-hidden="true"></i></a>
                              </td>
                            </tr>
                            <c:set var="row" value="${row + 1}"/>
                          </c:forEach>
                        </c:when>
                        <c:otherwise>
                          <input type="hidden" id="numberRow" name="numberRow" value="0">
                        </c:otherwise>
                      </c:choose>
                      </tbody>
                    </table>
                  </div>
                  <div class="pull-right mb-md mt-xl">
                    <a id="add_row" class="btn btn-primary"><i class="fa fa-plus" aria-hidden="true"></i>&nbsp;<spring:message code="system.trueservice.attribute.table.btn.addAttribute"/></a>
                    <button type="submit" name="action" value="submit" class="btn btn-success"><spring:message code="system.trueservice.attribute.table.btn.save"/>&nbsp;<i class="fa fa-floppy-o" aria-hidden="true"></i></button>
                  </div>
                  <sec:csrfInput/></form>
              </div>
            </section>

            <%-- ----------------service tree-------------  --%>
            <div class="clearfix"></div>
            <section class="panel search_payment panel-default">
              <div class="panel-title">
                <h4 class="fl"><spring:message code="system.service.detail.table.title"/></h4>
                <ul class="panel-tools fl tool-filter">
                  <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
                </ul>
                <div class="clr"></div>
              </div>
              <div class="panel-body">
                <div class="clr"></div>

                <spring:message code="system.service.list.table.column.action.detail" var="langDetail"/>
                <spring:message code="system.service.list.table.column.action.edit" var="langEdit"/>
                <spring:message code="system.service.list.table.column.action.delete" var="langDelete"/>

                <div class="table-responsive qlsp no-per-page">
                  <table id="table" class="dataTable mb-none no-footer table table-bordered table-striped">
                    <thead>
                    <tr>
                      <th><spring:message code="system.service.list.table.column.servicecode"/></th>
                      <th><spring:message code="system.service.list.table.column.servicetype"/></th>
                      <th><spring:message code="system.service.list.table.column.servicename"/></th>
                      <th><spring:message code="system.service.list.table.column.createdtime"/></th>
                      <th><spring:message code="system.service.list.table.column.actor"/></th>
                      <th class="center"><spring:message code="system.service.list.table.column.parentfee"/></th>
                      <th class="center"><spring:message code="system.service.list.table.column.status"/></th>
                      <th class="center"><spring:message code="system.service.list.table.column.action"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                      <c:when test="${serviceChildrens != null}">
                        <c:forEach var="item" items="${sysArrays}" varStatus="index">
                          <c:set var="serviceCodeChildrens" value="${serviceCodeChildrens}"/>
                          <c:set var="serviceCode" value="${item.serviceCode}"/>
                          <c:choose>
                            <c:when test="${serviceChildrens  != null && fn:contains(serviceCodeChildrens, serviceCode)}">
                              <tr data-node="treetable-650__${item.pathTreeUnder}" data-pnode="treetable-650__${item.pathParentUnder}">
                              <td class="competency sm-text" data-code="A.${item.pathParentPoint}.${item.serviceCode}" data-competencyid="650-${item.pathParentPoint}.${item.serviceCode}">
                                ${item.serviceCode}
                              </td>
                            </c:when>
                            <c:otherwise>
                              <tr style="color:#428bca" data-node="treetable-650__${item.serviceCode}" data-pnode="">
                              <td class="competency sm-text" data-code="A" data-competencyid="650-${item.serviceCode}">
                                ${item.serviceCode}
                              </td>
                            </c:otherwise>
                          </c:choose>
                          <td>${item.serviceType}</td>
                          <td>${item.serviceName}</td>

                          <td><fmt:formatDate pattern="HH:mm dd-MM-yyyy" value="${item.creationDate}"/></td>

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
                          <td class="center">
                            <div class="">
                              <c:choose>
                                <c:when test="${item.isParentFeeStructureAllowed()}">
                                  <div class="checkbox-custom checkbox-success checkboxWrapper" style="margin-left: 49%;">
                                    <input type="checkbox" name="ckaccess" id="checkboxActive1" checked="checked" disabled="disabled">
                                    <label for="checkboxActive1"></label>
                                  </div>
                                </c:when>

                                <c:otherwise>
                                  <div class="checkbox-custom checkbox-info checkboxWrapper" style="margin-left: 49%;">
                                    <input type="checkbox" name="ckaccess" id="checkboxExample6" disabled="disabled">
                                    <label for="checkboxExample6"></label>
                                  </div>
                                </c:otherwise>
                              </c:choose>
                            </div>
                          </td>
                          <sec:authorize access="hasRole('ADMIN_OPERATION')" var="perActionSwitch"/>
                          <c:choose>
                            <c:when test="${item.serviceCode eq requestServiceCode}">
                              <c:choose>
                                <c:when test="${perActionSwitch eq true}">
                                  <td class="sw">
                                    <div class="switch switch-sm switch-success">
                                      <input type="checkbox"
                                             name="switch" ${item.status eq 'Y'.charAt(0) ? 'checked' : ''}
                                             value="${item.id}"
                                             id="ck_${item.id}_${index.index}" readonly="readonly"/>
                                    </div>
                                  </td>
                                  <td class="action_icon">
                                    <a class="service-rule-add link-active mr-xs" data-toggle="modal" data-target="#service-rule-add-modal" title="${chooseRule}"
                                       serviceId="${item.id}"
                                       serviceCode="${item.serviceCode}"
                                       serviceName="${item.serviceName}">
                                      <i class="fa fa-plus" aria-hidden="true"></i>
                                    </a>
                                    <a title="${langDetail}" class="not-role"> <i class="fa fa-info-circle " aria-hidden="true"></i> </a>
                                    <a title="${langEdit}" class="not-role"> <i class="fa fa-pencil" aria-hidden="true"></i> </a>
                                    <a title="${langDelete}" class="not-role"> <i class="fa fa-trash" aria-hidden="true"></i> </a>
                                  </td>
                                </c:when>
                                <c:otherwise>
                                  <td class="sw">
                                    <div class="switch switch-sm switch-success">
                                      <input type="checkbox" disabled
                                             name="switch" ${item.status eq 'Y'.charAt(0) ? 'checked' : ''}
                                             value="${item.id}"
                                             id="ck_${item.id }_${index.index}" readonly="readonly"/>
                                    </div>
                                  </td>
                                  <td class="action_icon">
                                    <a class="service-rule-add link-active mr-xs" data-toggle="modal" data-target="#service-rule-add-modal" title="${chooseRule}"
                                       serviceId="${item.id}"
                                       serviceCode="${item.serviceCode}"
                                       serviceName="${item.serviceName}">
                                      <i class="fa fa-plus" aria-hidden="true"></i>
                                    </a>
                                    <a title="${langDetail}" class="not-role"> <i class="fa fa-info-circle " aria-hidden="true"></i> </a>
                                    <a title="${langEdit}" class="not-role"> <i class="fa fa-pencil" aria-hidden="true"></i> </a>
                                  </td>
                                </c:otherwise>
                              </c:choose>
                            </c:when>
                            <c:otherwise>
                              <c:choose>
                                <c:when test="${perActionSwitch eq true}">
                                  <td class="sw center">
                                    <div class="switch switch-sm switch-success">
                                      <input type="checkbox"
                                             name="switch" ${item.status eq 'Y'.charAt(0) ? 'checked' : ''}
                                             value="${item.id}"
                                             id="ck_${item.id }_${index.index}"/>
                                      <a href="#" class="switchery-mask"
                                         id="ck_${item.id }_${index.index}"
                                         serviceId="${item.id }"
                                         serviceCode="${item.serviceCode}"
                                         serviceName="${item.serviceName}"
                                         active="${item.status}"></a>
                                    </div>
                                  </td>


                                  <td class="action_icon center">
                                    <a class="service-rule-add link-active mr-xs" data-toggle="modal" data-target="#service-rule-add-modal" title="${chooseRule}"
                                       serviceId="${item.id}"
                                       serviceCode="${item.serviceCode}"
                                       serviceName="${item.serviceName}">
                                      <i class="fa fa-plus" aria-hidden="true"></i>
                                    </a>
                                    <a href="<c:url value="${urlTrueServiceDetail}/${item.id}"/>" class="detail" title="${langDetail}">
                                      <i class="fa fa-info-circle " aria-hidden="true"></i>
                                    </a>
                                    <a href="#" class="link-edit" title="${langEdit}"
                                       data-toggle="modal" data-target="#edit"
                                       serviceDesc="${item.serviceDesc }"
                                       serviceType="${item.serviceType }"
                                       serviceCode="${item.serviceCode }"
                                       serviceName="${item.serviceName}"
                                       serviceShortName="${item.serviceShortName}"
                                       customerTypeSupported="${item.customerTypeSupported}"
                                       servicePrices="${item.servicePrices}"
                                       serviceLevel="${item.level}"
                                       isParentFee="${item.isParentFeeStructureAllowed()}"
                                       parentServiceCode="${item.parentServiceCode}">
                                      <i class="fa fa-pencil" aria-hidden="true"></i>
                                    </a>
                                    <a href="#" class="link-delete" title="${langDelete}"
                                       data-toggle="modal"
                                       data-target="#${item.status eq 'Y'.charAt(0) ? 'preDelete' : 'delete'}"
                                       serviceCode="${item.serviceCode}"
                                       serviceName="${item.serviceName}">
                                      <i class="fa fa-trash" aria-hidden="true"></i>
                                    </a>
                                  </td>
                                </c:when>
                                <c:otherwise>

                                  <td class="sw center">
                                    <div class="switch switch-sm switch-success">
                                      <input type="checkbox" disabled
                                             name="switch" ${item.status eq 'Y'.charAt(0) ? 'checked' : ''}
                                             value="${item.id}"
                                             id="ck_${item.id }_${index.index}"/>
                                    </div>
                                  </td>

                                  <td class="action_icon center">
                                    <a class="service-rule-add link-active mr-xs" data-toggle="modal" data-target="#service-rule-add-modal" title="${chooseRule}"
                                       serviceId="${item.id}"
                                       serviceCode="${item.serviceCode}"
                                       serviceName="${item.serviceName}">
                                      <i class="fa fa-plus" aria-hidden="true"></i>
                                    </a>
                                    <a href="<c:url value="${urlTrueServiceDetail}/${item.id}"/>" class="detail" title="${langDetail}">
                                      <i class="fa fa-info-circle " aria-hidden="true"></i>
                                    </a>
                                    <a href="#" title="${langEdit}"
                                       data-toggle="modal" data-target="#edit"
                                       serviceDesc="${item.serviceDesc }"
                                       serviceType="${item.serviceType }"
                                       serviceCode="${item.serviceCode }"
                                       serviceName="${item.serviceName}"
                                       serviceShortName="${item.serviceShortName}"
                                       customerTypeSupported="${item.customerTypeSupported}"
                                       servicePrices="${item.servicePrices}"
                                       serviceLevel="${item.level}"
                                       isParentFee="${item.isParentFeeStructureAllowed()}"
                                       parentServiceCode="${item.parentServiceCode}">
                                      <i class="fa fa-pencil" aria-hidden="true"></i>
                                    </a>
                                  </td>
                                </c:otherwise>
                              </c:choose>
                            </c:otherwise>
                          </c:choose>
                          </tr>
                        </c:forEach>
                      </c:when>
                      <c:otherwise>

                        <c:forEach var="item" items="${serviceChildrens}" varStatus="index">
                          <c:set var="serviceCodeChildrens" value="${serviceCodeChildrens}"/>
                          <c:set var="serviceCode" value="${item.serviceCode}"/>

                          <c:choose>
                            <c:when test="${serviceChildrens  != null && fn:contains(serviceCodeChildrens, serviceCode)}">
                              <tr data-node="treetable-650__${item.pathTreeUnder}" data-pnode="treetable-650__${item.pathParentUnder}">
                                <td class="competency sm-text" data-code="A.${item.pathParentPoint}.${item.serviceCode}" data-competencyid="650-${item.pathParentPoint}.${item.serviceCode}">
                                  ${item.serviceCode}
                                </td>
                            </c:when>
                            <c:otherwise>
                              <tr style="color:#428bca" data-node="treetable-650__${item.serviceCode}" data-pnode="">
                                <td class="competency sm-text" data-code="A" data-competencyid="650-${item.serviceCode}">
                                  ${item.serviceCode}
                                </td>
                            </c:otherwise>
                          </c:choose>
                          <td>${item.serviceType}</td>
                          <td>${item.serviceName}</td>
                          <td>${item.servicePrices}</td>

                          <td><fmt:formatDate pattern="HH:mm dd-MM-yyyy" value="${item.creationDate}"/></td>
                          <td class="center">
                            <div class="">
                              <c:choose>
                                <c:when test="${item.isParentFeeStructureAllowed()}">
                                  <div class="checkbox-custom checkbox-success checkboxWrapper" style="margin-left: 49%;">
                                    <input type="checkbox" name="ckaccess" id="checkboxActive4" checked="checked" disabled="disabled">
                                    <label for="checkboxActive4"></label>
                                  </div>
                                </c:when>

                                <c:otherwise>
                                  <div class="checkbox-custom checkbox-info checkboxWrapper" style="margin-left: 49%;">
                                    <input type="checkbox" name="ckaccess" id="checkboxExample5" disabled="disabled">
                                    <label for="checkboxExample5"></label>
                                  </div>
                                </c:otherwise>
                              </c:choose>
                            </div>
                          </td>
                          <c:choose>
                            <c:when test="${item.serviceCode eq requestServiceCode}">
                              <td class="sw center">
                                <div class="switch switch-sm switch-success">
                                  <input type="checkbox"
                                         name="switch" ${item.status eq 'Y'.charAt(0) ? 'checked' : ''}
                                         value="${item.id}"
                                         id="ck_${item.id }_${index.index}" readonly="readonly"/>
                                </div>
                              </td>

                              <td class="action_icon center">
                                <a class="service-rule-add link-active mr-xs" data-toggle="modal" data-target="#service-rule-add-modal" title="${chooseRule}"
                                   serviceId="${item.id}"
                                   serviceCode="${item.serviceCode}"
                                   serviceName="${item.serviceName}">
                                  <i class="fa fa-plus" aria-hidden="true"></i>
                                </a>
                                <a title="${langDetail}" class="not-role"> <i class="fa fa-info-circle " aria-hidden="true"></i> </a>
                                <a title="${langEdit}" class="not-role"> <i class="fa fa-pencil" aria-hidden="true"></i> </a>
                                <a title="${langDelete}" class="not-role"> <i class="fa fa-trash" aria-hidden="true"></i> </a>
                              </td>
                            </c:when>
                            <c:otherwise>
                              <td class="sw center">
                                <div class="switch switch-sm switch-success">
                                  <input type="checkbox"
                                         name="switch" ${item.status eq 'Y'.charAt(0) ? 'checked' : ''}
                                         value="${item.id}"
                                         id="ck_${item.id }_${index.index}"/>
                                  <a href="#" class="switchery-mask"
                                     id="ck_${item.id }_${index.index}"
                                     serviceId="${item.id }"
                                     serviceCode="${item.serviceCode}"
                                     serviceName="${item.serviceName}"
                                     active="${item.status}"></a>
                                </div>
                              </td>

                              <td class="action_icon center">
                                <a class="service-rule-add link-active mr-xs" data-toggle="modal" data-target="#service-rule-add-modal" title="${chooseRule}"
                                   serviceId="${item.id}"
                                   serviceCode="${item.serviceCode}"
                                   serviceName="${item.serviceName}">
                                  <i class="fa fa-plus" aria-hidden="true"></i>
                                </a>
                                <a href="<c:url value="${urlTrueServiceDetail}/${item.id}"/>" class="detail" title="${langDetail}">
                                  <i class="fa fa-info-circle " aria-hidden="true"></i>
                                </a>
                                <a href="#" class="link-edit" title="${langEdit}"
                                   data-toggle="modal" data-target="#edit"
                                   serviceDesc="${item.serviceDesc }"
                                   serviceType="${item.serviceType }"
                                   serviceCode="${item.serviceCode }"
                                   servicePrices="${item.servicePrices}"
                                   serviceLevel="${item.level}"
                                   serviceName="${item.serviceName}"
                                   serviceShortName="${item.serviceShortName}"
                                   customerTypeSupported="${item.customerTypeSupported}"
                                   isParentFee="${item.isParentFeeStructureAllowed()}"
                                   parentServiceCode="${item.parentServiceCode}">
                                  <i class="fa fa-pencil" aria-hidden="true"></i>
                                </a>
                                <a href="#" class="link-delete" title="${langDelete}"
                                   data-toggle="modal"
                                   data-target="#${item.status eq 'Y'.charAt(0) ? 'preDelete' : 'delete'}"
                                   serviceCode="${item.serviceCode}"
                                   serviceName="${item.serviceName}">
                                  <i class="fa fa-trash" aria-hidden="true"></i>
                                </a>
                              </td>
                            </c:otherwise>
                          </c:choose>
                          </tr>
                        </c:forEach>
                      </c:otherwise>
                    </c:choose>
                    </tbody>
                  </table>
                </div>
              </div>
            </section>
          </div>
        </div>
      </div>
      <!-- end: page -->
    </section>
    <jsp:include page="service_add.jsp"/>
    <jsp:include page="service_edit.jsp"/>
    <jsp:include page="service_delete.jsp"/>
    <jsp:include page="service_rule_add.jsp"/>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<script type="text/javascript">
  $(document).ready(function () {

    $('#table').treeTable({
      startCollapsed: false
    });

    $('<span style="color: #428bca;" class="icon node-icon glyphicon glyphicon-bookmark"></span>').insertAfter(".treetable-expander");
    $('.switch input[name=switch]').each(function () {
      var item = document.querySelector('#' + $(this).attr('id'));
      var color = '#64bd63';
      var switchery = new Switchery(item, {color: color, size: 'small'});
      if ($(this).disabled) {
        switchery.disable();
      }
    });
    $('.switchery-mask').click(function () {
      var obj = $(this);
      var pid = obj.attr('id');
      var serviceId = obj.attr('serviceId');
      var serviceCode = obj.attr('serviceCode');
      var serviceName = obj.attr('serviceName');
      var active = obj.attr('active');
      var isTrueSet = (active === 'Y');
      var textAlert = "Bạn có chắc muốn bật dịch vụ ?</br>";
      if (isTrueSet) textAlert = "Bạn có chắc muốn tắt dịch vụ ?</br>";
      textAlert = textAlert + serviceName + '[' + serviceCode + ']';
      $.MessageBox({
        buttonDone: 'Yes',
        buttonFail: 'No',
        message: textAlert
      }).done(function () {
        $.post('changeStatus', {serviceCode: serviceCode, active: active},
            function (json) {
              $.MessageBox({message: json.message});
              if (json.code === 0) {
                $(".switchery-mask[id=" + pid + "]").closest('.switch').find('span.switchery').click();
                obj.attr('active', !isTrueSet);
                location.reload();
              }
              setTimeout(function () {
                $(".btnStatus").button('reset');}
                , 1000);
            });
      });
      return false;
    });


    var optionAttributeTypes = '';
    <c:forEach var ="item" items = "${serviceAttributeTypes}" >
    optionAttributeTypes = optionAttributeTypes + '<option value="${item.key}">${item.value}</option>';
    </c:forEach>
    $("#add_row").click(function () {
      var numberRow = $("input[name=numberRow]").val();
      var iplus = parseInt(numberRow) + 1;
      var indexRow = iplus - 1;
      var div = '<tr id="rangeStep' + indexRow + '">' +
          '<td>' + iplus + '</td>' +
          '<td>' +
          '<select id="serviceAttributeId' + indexRow + '" name="serviceAttributeId' + indexRow + '" class="form-control" required>' + optionAttributeTypes + '</select>' +
          '</td>' +
          '<td>' +
          '<input type="text" id="attributeValue' + indexRow + '" name="attributeValue' + indexRow + '" value="" class="form-control" required>' +
          '</td>' +
          '<td class="action_icon center">' +
          '<a href="#" class="link-delete" onclick="removeServiceAttribute(' + indexRow + ')" title="${deleteAttributeRule}">' +
          '<i class="fa fa-trash" aria-hidden="true"></i>' +
          '</a>' +
          '</td>' +
          '</tr>';
      $('#tblServiceAttribute tr:last').after(div);
      $("input[name=numberRow]").val(iplus);
    });
  });

  function removeServiceAttribute(row) {
    $('#rangeStep' + row).remove();
    var numberRow = $("input[name=numberRow]").val();
    $("input[name=numberRow]").val(parseInt(numberRow) - 1);
  }
</script>
</body>
</html>
