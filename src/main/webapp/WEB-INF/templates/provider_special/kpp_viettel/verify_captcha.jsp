<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 1/13/2021
  Time: 1:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../../include_page/taglibs.jsp" %>

<div class="modal fade" id="captchaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <form id="captchaForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
                            code="popup.header.icon.close"/></span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">VERIFY CAPTCHA</h4>
                </div>
                <div class="modal-body">
                    <div class="center">
                        <input id="accountName2" type="hidden">
                        <img src="" id="captchaImg" alt="base64">
                        <label style="cursor: pointer;" id="get-captcha"><i class="fa fa-refresh"
                                                                            style="font-size: 2rem"></i></label>
                    </div>
                    <div class="mt-md">
                        <div class="form-group">
                            <label for="captcha">Captcha:</label>
                            <input type="text" class="form-control" id="captcha">
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default " data-dismiss="modal"><spring:message
                            code="system.service.popup.update.lable.cancel"/></button>
                    <button type="button" class="btn btn-primary btnVerify">Verify</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(".btnVerify").click(function () {
        let captcha = $("#captcha").val();
        let accountName = $("#accountName2").val();
        if (captcha === null || captcha === "") {
            $.MessageBox({message: "<spring:message code="label.captcha.not.empty"/>"})
        } else {
            $.ajax({
                url: ctx + '/ajax-controller/kppvietel/account/capcha/enter',
                method: 'POST',
                data: {
                    accountName: accountName,
                    capcha: captcha,
                },
                success: function (result) {
                    $.MessageBox({message: "Success!"});
                    setTimeout(function () {
                        location.reload();
                    }, 1500);
                },
                error: function (result) {
                    $.MessageBox({message: result.responseText});
                    getCaptcha(accountName);
                }
            });
        }
    });
    $("#get-captcha").click(function () {
        getCaptcha($("accountName2").val());
    })
</script>

