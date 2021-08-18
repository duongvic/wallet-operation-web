<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 6/2/2021
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../../include_page/taglibs.jsp" %>
<div class="modal fade bs-example-modal-lg" id="workflow-modal" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">
                    <spring:message code="common.title.verify.request"/>
                </h4>
            </div>
            <div class="modal-body">
                <form id="workflow-form">
                    <input type="hidden" id="reconcilId">
                    <div class="row form-group">
                        <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                            Merchant
                        </label>
                        <div class="col-md-8 col-sm-8 col-xs-8" id="merchant-name">

                        </div>
                    </div>
                    <div class="row form-group">
                        <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                            Đối soát
                        </label>
                        <div class="col-md-8 col-sm-8 col-xs-8" id="reconcil-title">

                        </div>
                    </div>
                    <div class="row form-group">
                        <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                            Chính sách
                        </label>
                        <div class="col-md-8 col-sm-8 col-xs-8" id="reconcil-policy">

                        </div>
                    </div>
                    <div class="row form-group">
                        <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                            Trạng thái
                        </label>
                        <div class="col-md-8 col-sm-8 col-xs-8" id="reconcil-stage">

                        </div>
                    </div>
                    <div class="row form-group">
                        <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                            Remark
                        </label>
                        <div class="col-md-8 col-sm-8 col-xs-8">
                            <textarea class="form-control" rows="2" name="remark" id="remark"></textarea>
                        </div>
                    </div>
                    <div style="padding-left: 15px;">
                        <input type="checkbox" id="confirm-chkbox">
                        <label><spring:message code="common.confirm.action"/></label>
                    </div>
                    <div></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" onclick="changeReconciliationStage('SUBMIT')"
                        id="submit-btn"><spring:message code="fundin.popup.inquiry.btn.submit"/></button>
                <button type="button" class="btn btn-sm btn-primary" onclick="changeReconciliationStage('APPROVE')"
                        id="approve-btn"><spring:message code="common.btn.approve"/></button>
                <button type="button" class="btn btn-sm btn-danger" onclick="changeReconciliationStage('REJECT')"
                        id="reject-btn">
                    <spring:message code="common.btn.reject"/></button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
