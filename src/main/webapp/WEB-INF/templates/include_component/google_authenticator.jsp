<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="modal fade bs-example-modal-lg in" id="action-show-google-authen" role="dialog" aria-labelledby="grantRole" aria-hidden="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="${contextPath}/ajax-controller/consumer/resetGoogleAuthenticatorInfo" id="reset_google_authen">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="account_id" id="accountId" value="${account_object.id}">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">x</span></button>
                    <h4 class="modal-title"><spring:message code="setting.account.authenticator.info.title"/></h4>
                </div>
                <div class="modal-body">
                    <div class="row form-group text-center">
                        <img id="img_google_secret" src="${contextPath}/assets/development/static/images/loading_icon.gif">
                    </div>
                    <div id="msg-google-authen-role">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" id="show-google-authen" class="btn btn-sm btn-primary"><spring:message code="label.reset"/></button>
                    <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><spring:message code="label.close"/></button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>