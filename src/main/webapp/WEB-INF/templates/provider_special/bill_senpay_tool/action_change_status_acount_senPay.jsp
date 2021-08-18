<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade bs-example-modal-lg" id="action-blacklist" role="dialog"
     aria-labelledby="grantRole" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title"><spring:message
                        code="label.change.status.account"/></h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="account-id">
                <input type="hidden" id="blacklist-id">
                <input type="hidden" id="displayName-id">

                <div class="row form-group">
                        <label class="col-md-12 col-sm-12 col-xs-12 control-label lbl-text-active">
                        </label>
                </div>


                <div id="wrapper-chk-customer-confirm-senpay" style="padding-left: 15px;">
                    <input type="checkbox" name="chk-customer-confirm-senpay" id="chk-customer-confirm-senpay">
                    <label for="chk-customer-confirm-senpay"><spring:message
                            code="account.dialog.check.agree"/> </label>
                </div>

                <div id="msg-blacklist">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" id="btn-account-blacklist-save"
                        onclick="changeBlackListBillSenpayTool()"><spring:message code="label.save"/></button>
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
                    <spring:message code="label.close"/></button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>