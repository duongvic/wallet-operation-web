<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade bs-example-modal-lg" id="action-delete-account-senPay" role="dialog"
     aria-labelledby="grantRole" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title"><spring:message
                        code="label.delete.account"/></h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="account-id-sendPay">

                <div class="row form-group">
                    <label class="col-md-4 col-sm-4 col-xs-4 control-label" for="displayName-id"><spring:message code="select.account"/></label>
                    <div class="col-md-8 col-sm-8 col-xs-8">
                        <input type="text" id="displayName-id" name="displayName-id" class="form-control" disabled>
                    </div>
                </div>

                <div class="row form-group">
                    <label class="col-md-4 col-sm-4 col-xs-4 control-label" for="totalAmount-id"><spring:message code="wallet.balance.totalBalance"/></label>
                    <div class="col-md-8 col-sm-8 col-xs-8">
                        <input type="text" id="totalAmount-id" name="totalAmount-id" class="form-control" disabled>
                    </div>
                </div>

                <div id="wrapper-chk-customer-confirm-senpay-delete" style="padding-left: 15px;">
                    <input type="checkbox" name="chk-customer-confirm-senpay-delete" id="chk-customer-confirm-senpay-delete">
                    <label for="chk-customer-confirm-senpay-delete"><spring:message
                            code="account.dialog.check.agree"/> </label>
                </div>

                <div id="msg-delete-account-senPay">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" id="btnDelete-account-senPay-save"
                        onclick="deleteBlackList()"><spring:message code="label.save"/></button>
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
                    <spring:message code="label.close"/></button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>