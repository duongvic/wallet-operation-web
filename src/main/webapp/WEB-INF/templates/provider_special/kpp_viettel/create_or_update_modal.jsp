<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 1/12/2021
  Time: 5:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../../include_page/taglibs.jsp" %>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="myForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
                            code="popup.header.icon.close"/></span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        <label id="modalTitle"></label>
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="accountName">Account name:</label>
                        <input type="text" class="form-control" id="accountName">
                    </div>
                    <div class="form-group" id="password-group">
                        <label for="password">Password:</label>
                        <input type="text" class="form-control" id="password">
                    </div>
                    <div class="form-group">
                        <label for="phoneNumber">Phone number:</label>
                        <input type="text" class="form-control" id="phoneNumber">
                    </div>
                    <div class="form-group">
                        <label for="serialNumber">Serial number:</label>
                        <input type="text" class="form-control" id="serialNumber">
                    </div>
                    <div class="form-group" id="balance-group">
                        <label for="balance">Balance:</label>
                        <label id="balance"></label>
                    </div>
                    <div class="form-group">
                        <label for="status">Status:</label>
                        <select class="form-control" id="status">
                            <option value="1"><spring:message code="user.status.active"/></option>
                            <option value="0"><spring:message code="user.status.inactive"/></option>
                        </select>
                    </div>
                </div>
                <div class="alert alert-default mb-none mt-md p-sm">
                    <div class="checkbox-custom checkbox-success">
                        <input type="checkbox" name="ckaccess" id="myCheckbox">
                        <label id="labelCheckbox"><spring:message
                                code="common.confirm.action"/> </label>
                    </div>
                </div>
                <input type="hidden" id="formType">
                <div class="modal-footer">
                    <button type="button" class="btn btn-default " data-dismiss="modal"><spring:message
                            code="system.service.popup.update.lable.cancel"/></button>
                    <button type="button" class="btn btn-primary btnSubmit"><spring:message
                            code="system.service.popup.delete.lable.yes"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>

    $(".btnSubmit").click(function () {
        if (!$('#myCheckbox').is(':checked')) {
            $.MessageBox({message: "<spring:message code="common.not.confirm.the.action"/>"});
        } else {
            let accountName = $("#accountName").val();
            let phoneNumber = $("#phoneNumber").val();
            let serialNumber = $("#serialNumber").val();
            let status = $("#status").val();
            let password = $("#password").val();
            let formType = $("#formType").val();
            $.ajax({
                url: ctx + '/ajax-controller/kppvietel/create-or-update/account',
                method: 'POST',
                data: {
                    accountName: accountName,
                    phoneNumber: phoneNumber,
                    serialNumber: serialNumber,
                    status: status,
                    password: password,
                    formType: formType,
                },
                success: function (result) {
                    $.MessageBox({message: "Success!"});
                    setTimeout(function () {
                        location.reload();
                    }, 1500);
                },
                error: function (result) {
                    $.MessageBox({message: result.responseText});
                }
            });
        }
    })
</script>
