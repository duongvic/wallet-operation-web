<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 5/31/2021
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../../include_page/taglibs.jsp" %>
<form action="" method="GET" id="tbl-filter" class="mb-md" modelAttribute="searchReconciliationForm">
    <sec:csrfInput/>

    <input type="hidden" name="paymentPolicieTab" value="${param.paymentPolicieTab}">
    <input type="hidden" name="stageTab" value="${param.stageTab}">
    <section class="panel">
        <div class="panel-title">
            <div style="display: flex; justify-content: space-between; align-items: flex-end">
                <div><h4><spring:message code="label.filter"/></h4></div>
                <div><button class="btn btn-primary" type="button" onclick="openCreateByDate()">
                    <spring:message code="label.reconciliation.create"/>
                </button></div>
            </div>
        </div>
        <div class="panel-body report_search_form">
            <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="label.find.merchant"/> :</label>

                <div class="col-md-3">
                    <select class="find-customer" name="searchCustomer" id="quickSearch" multiple="multiple">
                        <c:forEach items="${profileDTOS}" var="profileDTO">
                            <option value="${profileDTO.customerId}" ${fn:contains(customerIds, profileDTO.customerId) eq true? 'selected': ''}>${profileDTO.customerName}</option>
                        </c:forEach>

                    </select>
                </div>

                <%--<div class="col-md-3">--%>
                <%--<select data-plugin-selectTwo class="form-control populate" tabindex="-1"--%>
                <%--title="" name="serviceTypes" multiple="multiple">--%>
                <%--<option value="all">Tất cả</option>--%>
                <%--<c:forEach var="serviceType" items="${serviceTypes}">--%>
                <%--<option value="${serviceType.key}" ${param.serviceTypes eq serviceType.key ? 'selected' : ''}>${serviceType.value}</option>--%>
                <%--</c:forEach>--%>
                <%--</select>--%>
                <%--</div>--%>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="label.reconciliation"/> :</label>

                <div class="col-md-3">
                    <select data-plugin-selectTwo class="form-control" name="month">
                        <option></option>
                        <option value="0"><spring:message code="label.all"/></option>
                        <c:forEach items="${months}" var="month">
                            <option value="${month}" ${param.month eq month ? 'selected': ''}>Tháng ${month}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-3">
                    <select data-plugin-selectTwo class="form-control" name="year">
                        <option></option>
                        <option value="0"><spring:message code="label.all"/></option>
                        <c:forEach var="i" begin="${currentYear - 2}" end="${currentYear + 2}">
                            <option value="${i}" ${param.year eq i ? 'selected': ''}>${i}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="label.cycle"/> :</label>
                <div class="col-md-3">
                    <select data-plugin-selectTwo class="form-control" name="cycle">
                        <option></option>
                        <option value="0"><spring:message code="label.all"/></option>
                        <c:forEach var="i" begin="1" end="31">
                            <option value="${i}" ${param.cycle eq i ? 'selected': ''}><spring:message
                                    code="label.cycle"/>&nbsp;${i}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3">
                    <select data-plugin-selectTwo class="form-control" name="stages" multiple="multiple">
                        <option value="all"><spring:message code="label.all"/></option>
                        <c:forEach var="stage" items="${stages}">
                            <option value="${stage.key}"
                                ${fn:contains(stagesQuery, stage.key) eq true ? 'selected': ''}>
                                <spring:message code="${stage.value}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="select.service.type"/> :</label>

                <div class="col-md-9">
                    <ul class="clearfix checkbox_form">
                        <li>
                            <div class="checkbox-custom checkbox-default">
                                <input type="checkbox" id="checkboxExample2" name="serviceTypes" class="check_1"
                                       value="PHONE_TOPUP"
                                <c:forEach var='value'
                                           items='${paramValues.serviceTypes}'>
                                <c:if test="${value eq 'PHONE_TOPUP'}">
                                       checked
                                </c:if>
                                </c:forEach>
                                >
                                <label for="checkboxExample2">PHONE_TOPUP</label>
                            </div>
                        </li>
                        <li>
                            <div class="checkbox-custom checkbox-default">
                                <input type="checkbox" id="checkboxExample3" name="serviceTypes" class="check_1"
                                       value="EPIN"
                                <c:forEach var='value'
                                           items='${paramValues.serviceTypes}'>
                                <c:if test="${value eq 'EPIN'}">
                                       checked
                                </c:if>
                                </c:forEach>
                                >
                                <label for="checkboxExample3">EPIN</label>
                            </div>
                        </li>
                        <li>
                            <div class="checkbox-custom checkbox-default">
                                <input type="checkbox" id="checkboxExample4" name="serviceTypes" class="check_1"
                                       value="EXPORT_EPIN"
                                <c:forEach var='value'
                                           items='${paramValues.serviceTypes}'>
                                <c:if test="${value eq 'EXPORT_EPIN'}">
                                       checked
                                </c:if>
                                </c:forEach>
                                >
                                <label for="checkboxExample4">EXPORT_EPIN</label>
                            </div>
                        </li>
                        <li>
                            <div class="checkbox-custom checkbox-default">
                                <input type="checkbox" id="checkboxExample5" name="serviceTypes" class="check_1"
                                       value="BILL_PAYMENT"
                                <c:forEach var='value'
                                           items='${paramValues.serviceTypes}'>
                                <c:if test="${value eq 'BILL_PAYMENT'}">
                                       checked
                                </c:if>
                                </c:forEach>
                                >
                                <label for="checkboxExample5">BILL_PAYMENT</label>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label"><spring:message code="label.policy"/> :</label>

                <div class="col-md-9">
                    <ul class="clearfix checkbox_form">
                        <li>
                            <div class="checkbox-custom checkbox-default">
                                <input type="checkbox" id="checkboxExample7" name="paymentPolicies"
                                       value="PAYMENT_POLICY_1_1" ${fn:contains(paymentPolicySelectsQuery, "PAYMENT_POLICY_1_1") eq true ? 'checked': ''}>
                                <label for="checkboxExample7">1 + 1</label>
                            </div>
                        </li>
                        <li>
                            <div class="checkbox-custom checkbox-default">
                                <input type="checkbox" id="checkboxExample8" name="paymentPolicies"
                                       value="PAYMENT_POLICY_7_1" ${fn:contains(paymentPolicySelectsQuery, "PAYMENT_POLICY_7_1") eq true ? 'checked': ''}>
                                <label for="checkboxExample8">7 + 1</label>
                            </div>
                        </li>
                        <li>
                            <div class="checkbox-custom checkbox-default">
                                <input type="checkbox" id="checkboxExample9" name="paymentPolicies"
                                       value="PAYMENT_POLICY_30_1" ${fn:contains(paymentPolicySelectsQuery, "PAYMENT_POLICY_30_1") eq true ? 'checked': ''}>
                                <label for="checkboxExample9">30 + 1</label>
                            </div>
                        </li>
                    </ul>
                </div>

                <%--<div class="col-md-3">--%>
                <%--<select data-plugin-selectTwo class="form-control populate" tabindex="-1"--%>
                <%--title="" name="paymentPolicies" multiple="multiple">--%>
                <%--<option value="all">Tất cả</option>--%>
                <%--<c:forEach var="paymentPolicy" items="${paymentPolicies}">--%>
                <%--<option value="${paymentPolicy}">${paymentPolicy}</option>--%>
                <%--</c:forEach>--%>
                <%--</select>--%>
                <%--</div>--%>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Official :</label>

                <div class="col-md-3">
                    <select data-plugin-selectTwo class="form-control" name="bolOfficial">
                        <option value="" ${param.bolOfficial eq "" ? 'selected' : ''}><spring:message code="label.all"/></option>
                        <option value="true" ${param.bolOfficial eq "true" ? 'selected' : ''}>Official</option>
                        <option value="false" ${param.bolOfficial eq "false" ? 'selected' : ''}>Ongoing</option>
                    </select>
                </div>
            </div>
            <%--<div class="form-group">--%>
            <%--<label class="col-md-3 control-label">Over sea? :</label>--%>

            <%--<div class="col-md-3">--%>
            <%--<select class="form-control" disabled>--%>
            <%--<option>Tất cả</option>--%>
            <%--<option>Option 2</option>--%>
            <%--<option>Option 3</option>--%>
            <%--</select>--%>
            <%--</div>--%>
            <%--</div>--%>

            <%--<div class="form-group">--%>
            <%--<label class="col-md-3 control-label">--%>
            <%--<div class="checkbox-custom checkbox-default">--%>
            <%--<input type="checkbox" id="checkboxExample1" class="check_all_1" disabled>--%>
            <%--<label for="checkboxExample1">Loại hợp đồng</label>--%>
            <%--</div>--%>
            <%--</label>--%>

            <%--<div class="col-md-9">--%>
            <%--<ul class="clearfix checkbox_form">--%>
            <%--<li>--%>
            <%--<div class="checkbox-custom checkbox-default">--%>
            <%--<input type="checkbox" id="checkboxExample2" class="check_1" disabled>--%>
            <%--<label for="checkboxExample2">AB2B</label>--%>
            <%--</div>--%>
            <%--</li>--%>
            <%--<li>--%>
            <%--<div class="checkbox-custom checkbox-default">--%>
            <%--<input type="checkbox" id="checkboxExample3" class="check_1" disabled>--%>
            <%--<label for="checkboxExample3">AB2B</label>--%>
            <%--</div>--%>
            <%--</li>--%>
            <%--<li>--%>
            <%--<div class="checkbox-custom checkbox-default">--%>
            <%--<input type="checkbox" id="checkboxExample4" class="check_1" disabled>--%>
            <%--<label for="checkboxExample4">AB2B</label>--%>
            <%--</div>--%>
            <%--</li>--%>
            <%--<li>--%>
            <%--<div class="checkbox-custom checkbox-default">--%>
            <%--<input type="checkbox" id="checkboxExample5" class="check_1" disabled>--%>
            <%--<label for="checkboxExample5">AB2B</label>--%>
            <%--</div>--%>
            <%--</li>--%>
            <%--</ul>--%>


            <%--</div>--%>
            <%--</div>--%>

            <%--<div class="form-group">--%>
            <%--<label class="col-md-3 control-label">--%>
            <%--<div class="checkbox-custom checkbox-default">--%>
            <%--<input type="checkbox" id="checkboxExample6" disabled>--%>
            <%--<label for="checkboxExample6">Loại Charging</label>--%>
            <%--</div>--%>
            <%--</label>--%>

            <%--<div class="col-md-9">--%>
            <%--<ul class="clearfix checkbox_form">--%>
            <%--<li>--%>
            <%--<div class="checkbox-custom checkbox-default">--%>
            <%--<input type="checkbox" id="checkboxExample7" disabled>--%>
            <%--<label for="checkboxExample7">SMS</label>--%>
            <%--</div>--%>
            <%--</li>--%>
            <%--<li>--%>
            <%--<div class="checkbox-custom checkbox-default">--%>
            <%--<input type="checkbox" id="checkboxExample8" disabled>--%>
            <%--<label for="checkboxExample8">WAP</label>--%>
            <%--</div>--%>
            <%--</li>--%>
            <%--<li>--%>
            <%--<div class="checkbox-custom checkbox-default">--%>
            <%--<input type="checkbox" id="checkboxExample9" disabled>--%>
            <%--<label for="checkboxExample9">BANK</label>--%>
            <%--</div>--%>
            <%--</li>--%>
            <%--<li>--%>
            <%--<div class="checkbox-custom checkbox-default">--%>
            <%--<input type="checkbox" id="checkboxExample10" disabled>--%>
            <%--<label for="checkboxExample10">VISA</label>--%>
            <%--</div>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <%--</div>--%>
            <%--</div>--%>

            <div class="form-group report_search_bt">
                <button class="btn btn-primary" type="submit"><spring:message code="label.filter"/></button>
            </div>
        </div>
    </section>

</form>

