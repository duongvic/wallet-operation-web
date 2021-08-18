<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
  .checkbox-info label:before {border: 1px solid #ecd6d6}
  .treetable-expanded > td:first-child, .treetable-collapsed > td:first-child {padding-left: 2em;}
  .treetable-expanded > td:first-child > .treetable-expander, .treetable-collapsed > td:first-child > .treetable-expander {top: 0.05em;position: relative;margin-left: -0.5em;margin-right: 0.25em;}
  .treetable-expanded .treetable-expander, .treetable-expanded .treetable-expander {width: 1em;height: 1em;cursor: pointer;position: relative;display: inline-block;}
  .treetable-depth-1 > td:first-child {padding-left: 3em;}
  .treetable-depth-2 > td:first-child {padding-left: 4.5em;}
  .treetable-depth-3 > td:first-child {padding-left: 6em;}

  .green-background {
    background-color: #38b449;
  }
</style>

<div class="panel-title">
  <h4 style="margin-bottom: 1.5rem">Commission Fee</h4>
  <div class="panel-tools"></div>
</div>


<section class="panel search_payment panel-default">
  <div class="panel-body pt-none">


    <div class="form-group ml-none mr-none">
    </div>

    <div class="form-inline">
      <div class='form-responsive bt-plt'>

        <select name="serviceTypeIds" id="serviceTypeIds" class="form-control">
          <c:forEach var="item" items="${serviceTypes}">
            <option ${item.key eq param.serviceTypeIds || item.key eq sessionScope.SESSION_SERVICE_TYPE ? 'selected' : ''} value="${item.key}">${item.value}</option>
          </c:forEach>
        </select>

      </div>

    </div>
    <div class="clearfix"></div>


    <section class="panel search_payment panel-default">


      <div class="panel-body">
        <div class="clearfix"></div>
        <div class="pull-left mt-sm" style="line-height: 30px;">
          <spring:message code="system.service.list.totalServices"/>&nbsp;<span class="primary_color text-semibold">${totalServices }</span>
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

            <c:forEach var="item" items="${services}" varStatus="index">
              <c:if test="${item.level eq 0}">
                <tr style="color:#428bca" data-node="treetable-650__${item.serviceCode}" data-pnode="">
                <td class="competency sm-text" data-code="A" data-competencyid="650-${item.serviceCode}">
                    ${item.serviceCode}
                </td>
              </c:if>
              <c:if test="${item.level != 0}">
                <tr data-node="treetable-650__${item.pathTreeUnder}" data-pnode="treetable-650__${item.pathParentUnder}">
                <td class="competency sm-text" data-code="A.${item.pathParentPoint}.${item.serviceCode}" data-competencyid="650-${item.pathParentPoint}.${item.serviceCode}">
                    ${item.serviceCode}
                </td>
              </c:if>

              <td>${item.paymentChannelId}</td>
              <td>${item.serviceType}</td>
              <td>${item.serviceName}</td>

              <c:set var="commissionCss" value=""/>
              <c:choose>
                <c:when test="${item.isUseSpecifiedCommission}">
                  <c:set var="commissionValue" value="${item.specifiedCommissionFee.commission}"/>
                  <c:set var="feeValue" value="${item.specifiedCommissionFee.fee}"/>
                  <c:set var="commissionFixedAmountValue" value="${item.specifiedCommissionFee.commissionFixedAmount}"/>
                  <c:set var="feeFixedAmountValue" value="${item.specifiedCommissionFee.feeFixedAmount}"/>
                  <c:set var="commissionCss" value="green-background"/>
                </c:when>
                <c:otherwise>
                  <c:set var="commissionValue" value="${item.commissionFee.commission}"/>
                  <c:set var="feeValue" value="${item.commissionFee.fee}"/>
                  <c:set var="commissionFixedAmountValue" value="${item.commissionFee.commissionFixedAmount}"/>
                  <c:set var="feeFixedAmountValue" value="${item.commissionFee.feeFixedAmount}"/>
                </c:otherwise>
              </c:choose>

              <td class="${commissionCss}" style="text-align: right;">${commissionValue}</td>
              <td class="${commissionCss}" style="text-align: right;">${feeValue}</td>
              <td class="${commissionCss}" style="text-align: right;">${commissionFixedAmountValue}</td>
              <td class="${commissionCss}" style="text-align: right;">${feeFixedAmountValue}</td>

              <td><spring:message code="system.service.list.table.column.level.name"/>&nbsp;${item.level}</td>

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
                      <div class="checkbox-custom checkbox-success checkboxWrapper" style="margin-left: 49%;">
                        <input type="checkbox" name="ckaccess" id="checkboxActive" checked="checked" disabled="disabled">
                        <label for="checkboxActive"></label>
                      </div>
                    </c:when>
                    <c:otherwise>
                      <div class="checkbox-custom checkbox-info checkboxWrapper" style="margin-left: 49%;">
                        <input type="checkbox" name="ckaccess" id="checkboxDiactive" disabled="disabled">
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
                         id="ck_${item.id }_${index.index}"
                         serviceId="${item.id}"
                         serviceCode="${item.serviceCode}"
                         serviceName="${item.serviceName}"
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
                      access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR', 'SALESUPPORT_MANAGER')">
                <td class="sw center">
                  <button class="btn btn-success" type="button"
                          title="<spring:message code="system.service.specified.commission.fee.setup.btn.title"/>"
                          onclick="editCommissionFeeModal(this)"
                          customerId="${account_object.id}"
                          serviceId="${item.id}"
                          serviceType="${item.serviceType}"
                          serviceCode="${item.serviceCode}"
                          serviceName="${item.serviceName}"
                          commission="${commissionValue}"
                          fee="${feeValue}"
                          commissionFixedAmount="${commissionFixedAmountValue}"
                          feeFixedAmount="${feeFixedAmountValue}">
                    <i class="fa fa-pencil" aria-hidden="true"></i>
                  </button>
                  <button class="btn btn-primary" type="button"
                     title="<spring:message code="system.service.specified.commission.fee.default.btn.title"/>"
                          onclick="resetCommissionFeeModal(this)"
                          customerId="${account_object.id}"
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