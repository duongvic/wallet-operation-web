<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 6/28/2021
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../../include_page/taglibs.jsp" %>
<div class="modal fade bs-example-modal-lg" id="create-by-date-modal" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">
                    <spring:message code="label.reconciliation.create"/>
                </h4>
            </div>
            <div class="modal-body">
                <form id="create-by-date-form">
                    <div class="row form-group">
                        <label class="col-md-4 col-sm-4 col-xs-4 control-label lbl-text-active">
                            Merchant
                        </label>
                        <div class="col-md-8 col-sm-8 col-xs-8">
                            <select class="find-customer" name="merchantId" id="merchantId">
                                <option></option>
                                <c:forEach items="${profileDTOS}" var="profileDTO">
                                    <option value="${profileDTO.customerId}">${profileDTO.customerName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label class="col-md-4 col-sm-4 col-xs-4 control-label lbl-text-active">
                            Loại chu kỳ đối soát
                        </label>
                        <div class="col-md-8 col-sm-8 col-xs-8">
                            <select name="cycleType" id="cycleType" style="width: 100%">
                                <option value="PAYMENT_POLICY_1_1">Chính sách 1+1</option>
                                <option value="PAYMENT_POLICY_7_1">Chính sách 7+1</option>
                                <option value="PAYMENT_POLICY_30_1">Chính sách 30+1</option>
                            </select>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label class="col-md-4 col-sm-4 col-xs-4 control-label lbl-text-active">
                            <spring:message code="label.year"/>
                        </label>
                        <div class="col-md-8 col-sm-8 col-xs-8">
                            <select data-plugin-selectTwo class="form-control" name="yr" id="yr" style="width: 100%">
                                <option></option>
                                <c:forEach var="i" begin="${currentYear - 2}" end="${currentYear + 2}">
                                    <option value="${i}" ${param.year eq i ? 'selected': ''}>${i}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label class="col-md-4 col-sm-4 col-xs-4 control-label lbl-text-active">
                            <spring:message code="label.month"/>
                        </label>
                        <div class="col-md-8 col-sm-8 col-xs-8">
                            <select data-plugin-selectTwo class="form-control" name="mnth" id="mnth" style="width: 100%">
                                <option></option>
                                <c:forEach items="${months}" var="month">
                                    <option value="${month}" ${param.month eq month ? 'selected': ''}>Tháng ${month}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label class="col-md-4 col-sm-4 col-xs-4 control-label lbl-text-active">
                            <spring:message code="label.cycle"/>
                        </label>
                        <div class="col-md-8 col-sm-8 col-xs-8">
                            <select data-plugin-selectTwo class="form-control" name="cyl" id="cyl" style="width: 100%">
                                <c:forEach var="i" begin="1" end="31">
                                    <option value="${i}" ${param.cycle eq i ? 'selected': ''}><spring:message code="label.cycle"/>&nbsp;${i}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" onclick="generateReconciliationByOPS()">
                    <spring:message code="label.reconciliation.create"/>
                </button>
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
                    <spring:message code="button.cancel"/>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

