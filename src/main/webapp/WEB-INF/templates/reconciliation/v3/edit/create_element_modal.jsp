<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 6/4/2021
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../../../include_page/taglibs.jsp" %>
<div class="modal fade bs-example-modal-lg" id="add-modal" role="dialog" aria-hidden="true" style="top: -10%;">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">
                    <spring:message code="common.btn.add"/>
                </h4>
            </div>
            <div class="modal-body">
                <form id="add-form">
                    <sec:csrfInput/>
                    <div class="row">
                        <div class="col-md-6 col-xs-12">
                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    <spring:message code="label.customer.type"/>
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input class="form-control" name="customerType2" id="customerType" value="MERCHANT" disabled>
                                    <input type="hidden" name="customerType" value="MERCHANT">
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    CustomerID
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input class="form-control" name="customerId2" id="customerId" value="${reconcil.customerId}" disabled>
                                    <input type="hidden" name="customerId" value="${reconcil.customerId}">
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    <spring:message code="label.service.type"/>
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input class="form-control" name="serviceType2" id="serviceType2" disabled>
                                    <input type="hidden" name="serviceType" id="serviceType">
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    <spring:message code="system.service.detail.summary.row.servicecode"/>
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <%--<input class="form-control" name="serviceCode" id="serviceCode">--%>
                                    <select class="find-service-code" name="serviceCode" id="serviceCodeCreate"></select>
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    <spring:message code="system.service.detail.summary.row.servicename"/>
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input class="form-control" name="serviceName" id="serviceName">
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    <spring:message code="transaction.export.detail.summary.row.quantity"/>
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input autocomplete="off" class="form-control textNumber" name="quantity" id="quantity" value="0">
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    <spring:message code="label.price"/>
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input autocomplete="off" class="form-control textNumber" name="unitPrice" id="unitPrice" value="0">
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    UnitFeeFixedAmount
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input autocomplete="off" class="form-control textNumber" name="unitFeeFixedAmount" id="unitFeeFixedAmount" value="0">
                                </div>
                            </div>

                        </div>
                        <%--//////////////////////////////////////--%>
                        <div class="col-md-6 col-xs-12">

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    UnitFeePercentRate&nbsp;(%)
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input type="number" step="0.01" class="form-control" name="unitFeePercentRate" id="unitFeePercentRate" value="0">
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    UnitCommissionFixedAmount
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input autocomplete="off" class="form-control textNumber" name="unitCommissionFixedAmount" id="unitCommissionFixedAmount" value="0">
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    UnitCommissionPercentRate&nbsp;(%)
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input type="number" step="0.01" class="form-control" name="unitCommissionPercentRate" id="unitCommissionPercentRate" value="0">
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    <spring:message code="label.num.of.trans"/>
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input autocomplete="off" class="form-control textNumber" name="numOfTrans" id="numOfTrans" value="0">
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    SumOfRequestAmount
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input autocomplete="off" class="form-control textNumber" name="sumOfRequestAmount" id="sumOfRequestAmount" value="0">
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    SumOfFeeAmount
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input autocomplete="off" class="form-control textNumber" name="sumOfFeeAmount" id="sumOfFeeAmount" value="0">
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    SumOfCommissionAmount
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input autocomplete="off" class="form-control textNumber" name="sumOfCommissionAmount" id="sumOfCommissionAmount" value="0">
                                </div>
                            </div>

                            <div class="row form-group">
                                <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                    SumOfCashbackAmount
                                </label>
                                <div class="col-md-8 col-sm-8 col-xs-8">
                                    <input autocomplete="off" class="form-control textNumber" name="sumOfCashbackAmount" id="sumOfCashbackAmount" value="0">
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--<div style="padding-left: 15px;">--%>
                        <%--<input type="checkbox" id="confirm-chkbox">--%>
                        <%--<label><spring:message code="common.confirm.action"/></label>--%>
                    <%--</div>--%>
                    <%--<div></div>--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" id="add-btn" onclick="addOneElement()"><spring:message code="common.btn.add"/></button>
                <button type="button" class="btn btn-sm btn-default" id="cancel-btn" data-dismiss="modal"><spring:message code="button.cancel"/></button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
