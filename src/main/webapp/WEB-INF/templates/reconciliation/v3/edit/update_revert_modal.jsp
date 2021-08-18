<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 6/21/2021
  Time: 11:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../../../include_page/taglibs.jsp" %>
<div class="modal fade bs-example-modal-lg" id="update-revert-modal" role="dialog" aria-hidden="true" style="top: -10%;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">
                    CHỈNH SỬA HOÀN TIỀN
                </h4>
            </div>
            <div class="modal-body">
                <form id="update-revert-form">
                    <sec:csrfInput/>
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="row form-group">
                            <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                <spring:message code="label.service.type"/>
                            </label>
                            <div class="col-md-8 col-sm-8 col-xs-8">
                                <select class="form-control" name="serviceType" id="serviceType">
                                    <option value="EPIN">EPIN</option>
                                    <option value="EXPORT_EPIN">EXPORT_EPIN</option>
                                    <option value="PHONE_TOPUP">PHONE_TOPUP</option>
                                    <option value="BILL_PAYMENT">BILL_PAYMENT</option>
                                </select>
                            </div>
                        </div>

                        <div class="row form-group">
                            <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                                <spring:message code="label.revert.amount"/>
                            </label>
                            <div class="col-md-8 col-sm-8 col-xs-8">
                                <input class="form-control textNumber" name="revertAmount" id="revertAmount" value="0">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" id="update-revert-btn" onclick="updateOneRevertElement()"><spring:message code="button.update"/></button>
                <button type="button" class="btn btn-sm btn-default" id="cancel-revert-btn" data-dismiss="modal"><spring:message code="button.cancel"/></button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
