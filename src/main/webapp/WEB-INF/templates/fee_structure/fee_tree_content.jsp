<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="panel search_payment panel-default">
    <div class="panel-body pt-none">

        <spring:message code="system.service.list.form.search.placeholder" var="placeholder"/>

        <form action="" method="GET" id="tbl-filter" modelAttribute="searchDataForm">
            <input type="hidden" id="customerTypeId" name="customerTypeId"
                   value="${customerTypeId == '' or customerTypeId == null ? param.customerTypeId : customerTypeId}">
            <div class="form-group ml-none mr-none">
                <div class="input-group input-group-icon">
                    <span class="input-group-addon"><span class="icon" style="opacity: 0.4"><i
                            class="fa fa-search-minus"></i></span></span>
                    <input type="text" id="search" name="quickSearch" class="form-control" placeholder="${placeholder}"
                           value="${param.quickSearch}"/>
                </div>
            </div>

            <div class="form-inline">

                <div class='pull-right form-responsive bt-plt'>

                    <%--<c:if test="${isCustomerTypeSelect}">
                      <select class="form-control" name="customerTypeId" id="customerTypeId">
                        <c:forEach var="item" items="${customerTypes}">
                          <option ${item.key eq param.customerTypeId ? 'selected' : ''} value="${item.key}">${item.value}</option>
                        </c:forEach>
                      </select>
                    </c:if>--%>

                    <jsp:include page="../include_component/search_payment_channel.jsp"/>
                    <jsp:include page="../include_component/search_service_type_one.jsp">
                        <jsp:param name="enableFiltering" value="false"/>
                    </jsp:include>

                    <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i>&nbsp;<spring:message
                            code="system.service.list.search.btn.search"/></button>
                    <button type="button" class="btn btn-approve ml-tiny"><i class="fa fa-upload"
                                                                             aria-hidden="true"></i>&nbsp;Import Excel
                    </button>
                    <button type="button" class="btn btn-approve ml-tiny"><i class="fa fa-file-excel-o"
                                                                             aria-hidden="true"></i>&nbsp;Export Excel
                    </button>
                    <button type="button" class="btn btn-primary ml-tiny" onclick="addCommissionFeeModal()"><i
                            class="fa fa-plus"></i>&nbsp;Create
                    </button>
                    <a href="?customerTypeId=${param.customerTypeId}"
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

                <spring:message code="system.service.list.table.column.action.detail" var="langDetail"/>
                <spring:message code="system.service.list.table.column.action.edit" var="langEdit"/>
                <spring:message code="system.service.list.table.column.action.delete" var="langDelete"/>
                <spring:message code="system.service.list.table.column.action.chooseRule" var="chooseRule"/>
                <spring:message code="system.service.list.table.col.actor.payee" var="actorPayee"/>
                <spring:message code="system.service.list.table.col.actor.payer" var="actorPayer"/>


                <div class="table-responsive qlsp no-per-page">
                    <table id="table" class="dataTable mb-none no-footer table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th><spring:message code="system.service.list.table.column.servicecode"/></th>
                            <th><spring:message code="transaction.api.table.customer.type"/></th>
                            <th>CHANNEL</th>
                            <th><spring:message code="system.service.list.table.column.servicetype"/></th>
                            <th><spring:message code="system.service.list.table.column.servicename"/></th>
                            <th>COMMISSION</th>
                            <th>FEE</th>
                            <th>COMMISSION <br>FIXED AMOUNT</th>
                            <th>FEE <br>FIXED AMOUNT</th>
                            <th><spring:message code="system.service.list.table.column.level"/></th>
                            <th><spring:message code="system.service.list.table.column.actor"/></th>
                            <th class="center"><spring:message code="system.service.list.table.column.parentfee"/></th>
                            <th class="center"><spring:message code="system.service.list.table.column.status"/></th>
                            <sec:authorize
                                    access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR', 'SALESUPPORT_MANAGER')">
                                <th class="center"><spring:message code="system.service.list.table.column.action"/></th>
                            </sec:authorize>
                        </tr>
                        </thead>
                        <tbody>

                        <c:set var="colCustomerType" value=""/>
                        <c:choose>
                            <c:when test="${isCustomerTab}"><c:set var="colCustomerType" value="CUSTOMER"/></c:when>
                            <c:when test="${isMerchantTab}"><c:set var="colCustomerType" value="MERCHANT"/></c:when>
                            <c:when test="${isProviderTab}"><c:set var="colCustomerType" value="PROVIDER"/></c:when>
                            <c:when test="${isAgentTab}"><c:set var="colCustomerType" value="AGENT"/></c:when>
                            <c:when test="${isPropertyManagerTab}"><c:set var="colCustomerType"
                                                                          value="PROPERTY_MANAGER"/></c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>

                        <c:forEach var="item" items="${services}" varStatus="index">
                            <c:if test="${item.level eq 0}">
                                <tr style="color:#428bca" data-node="treetable-650__${item.serviceCode}" data-pnode="">
                                <td class="competency sm-text" data-code="A"
                                    data-competencyid="650-${item.serviceCode}">
                                        ${item.serviceCode}
                                </td>
                            </c:if>
                            <c:if test="${item.level != 0}">
                                <tr data-node="treetable-650__${item.pathTreeUnder}" data-pnode="treetable-650__${item.pathParentUnder}">
                                <td class="competency sm-text" data-code="A.${item.pathParentPoint}.${item.serviceCode}"
                                    data-competencyid="650-${item.pathParentPoint}.${item.serviceCode}">
                                        ${item.serviceCode}
                                </td>
                            </c:if>
                            <th>${colCustomerType}</th>
                            <td>${item.paymentChannelId}</td>
                            <td>${item.serviceType}</td>
                            <td>${item.serviceName}</td>
                            <td style="text-align: right;">${item.commissionFee.commission}</td>
                            <td style="text-align: right;">${item.commissionFee.fee}</td>
                            <td style="text-align: right;">${item.commissionFee.commissionFixedAmount}</td>
                            <td style="text-align: right;">${item.commissionFee.feeFixedAmount}</td>
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

                            <sec:authorize access="hasRole('ADMIN_OPERATION')" var="perStatisService"/>
                            <td class="sw center">
                                <div class="">
                                    <c:choose>
                                        <c:when test="${item.isParentFeeStructureAllowed()}">
                                            <div class="checkbox-custom checkbox-success checkboxWrapper"
                                                 style="margin-left: 49%;">
                                                <input type="checkbox" name="ckaccess" id="checkboxActive"
                                                       checked="checked" disabled="disabled">
                                                <label for="checkboxActive"></label>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="checkbox-custom checkbox-info checkboxWrapper"
                                                 style="margin-left: 49%;">
                                                <input type="checkbox" name="ckaccess" id="checkboxDiactive"
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
                                            <a href="#" class="switchery-mask link-status" data-toggle="modal"
                                               data-target="#inactive"
                                               id="ck_${item.id }_${index.index}"></a>
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
                                    access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR', 'SALESUPPORT_MANAGER')">
                                <td class="sw center">
                                    <button class="btn btn-success" type="button"
                                            title="<spring:message code="system.service.commission.fee.setup.btn.title"/>"
                                            onclick="editCommissionFeeModal(this)"
                                            serviceId="${item.id}"
                                            serviceType="${item.serviceType}"
                                            serviceCode="${item.serviceCode}"
                                            serviceName="${item.serviceName}"
                                            commission="${item.commissionFee.commission}"
                                            paymentChannelId="${item.paymentChannelId}"
                                            fee="${item.commissionFee.fee}"
                                            commissionFixedAmount="${item.commissionFee.commissionFixedAmount}"
                                            feeFixedAmount="${item.commissionFee.feeFixedAmount}">
                                        <i class="fa fa-pencil" aria-hidden="true"></i>
                                    </button>
                                    <button class="btn btn-primary" type="button"
                                            title="<spring:message code="system.service.commission.fee.default.btn.title"/>"
                                            onclick="resetCommissionFeeModal(this)"
                                            serviceId="${item.id}"
                                            serviceType="${item.serviceType}"
                                            serviceCode="${item.serviceCode}"
                                            serviceName="${item.serviceName}">
                                        <i class="fa fa-history" aria-hidden="true"></i>
                                    </button>
                                </td>
                            </sec:authorize>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
    </div>
</section>