<%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 8/31/2020
  Time: 9:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="vn.mog.ewallet.operation.web.controller.provider.ProviderController" %>
<%@ include file="../include_page/taglibs.jsp" %>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="modal fade" id="fundin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <form name="fundin">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
              code="popup.header.icon.close"/></span>
          </button>
          <h4 class="modal-title ttitle" id="myModalLabel"><spring:message
              code="label.fundin.provider"/><span name="titleName"></span></h4>
        </div>
        <div class="modal-body" style="max-height:500px; overflow:auto;">
          <input name="providerId" id="providerId" type="hidden">
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message
                code="provider.edit.title.name"/></label>
            <div class="col-md-3"><p class="primary_color tname" name="tname">Viettorrent</p></div>
            <label class="col-md-3 control-label"><spring:message
                code="provider.edit.title.healthy"/></label>
            <div class="col-md-3">
              <div class="healthy">
                <button type="button" title="<spring:message code="common.title.dangerous" />"
                        class="mb-xs mt-xs btn btn-xs btn btn-danger RED"
                        style="margin-right: 2px;">bad
                </button>
                <button type="button" title="<spring:message code="common.title.warning" />"
                        class="mb-xs mt-xs btn btn-xs btn btn-primary YELLOW"
                        style="margin-right: 2px;">warning
                </button>
                <button type="button" title="<spring:message code="common.title.stable.operation"/>"
                        class="mb-xs mt-xs btn btn-xs btn btn-success-green GREEN"
                        style="margin-right: 2px;">good
                </button>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message
                code="provider.edit.title.code"/></label>
            <div class="col-md-3">
              <p class="primary_color tcode" name="tcode">Viettorrent</p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message
                code="provider.service.title.rating.point"/> </label>
            <div class="col-md-3">
              <p class="primary_color trating"></p>
            </div>
          </div>
          <div class="panel-title pl-md">
            <h4 class="fl"><spring:message code="label.fundin.provider"/></h4>
            <div class="clr"></div>
          </div>
          <%--<div class="form-group mt-md">--%>
          <%--<input placeholder="<spring:message code="menu.left.fundin"/>" class="form-control"--%>
          <%--style="width: 50%">--%>
          <%--</div>--%>
          <div class="row">
            <div class="col-lg-5">
              <div class="input-group mt-md">
                <input type="text" name="amount" id="amount" autocomplete="off"
                       placeholder="<spring:message code="label.fundin.provider"/>"
                       class="form-control textNumber" required value="${amount}">
                <span class="input-group-addon">VNĐ</span>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-5">
              <div class="form-group mt-md">
              <textarea class="form-control" id="remark"
                        placeholder="<spring:message code="common.title.remark"/>"></textarea>
              </div>
            </div>
          </div>
        </div>
        <div class="alert alert-default m-md p-sm">
          <div class="checkbox-custom checkbox-success">
            <input type="checkbox" name="ckaccess" id="checkboxExample2">
            <label for="checkboxExample2"><spring:message
                code="label.confirm.fundin.provider"/></label>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default " data-dismiss="modal"><spring:message
              code="common.btn.cancel"/></button>
          <button type="button" id="submit-btn" class="btn btn-primary " onclick="submitForm()">
            <spring:message
                code="label.fundin.provider"/></button>
        </div>
        <sec:csrfInput/></form>
    </div>
  </div>
</div>
<script>
  function submitForm() {
    var remark = $("#remark").val();
    var providerId = $("#providerId").val();
    var amount = $("#amount").val().split(",").join("");

    if (amount <= 0) {
      $.MessageBox("<spring:message code="label.fundin.provider.empty"/>");
      return false;
    }

    var answer = $('form[name=fundin] input[name=ckaccess]').is(":checked");
    if (!answer) {
      $.MessageBox(
        {message: '<spring:message code="label.confirm.fundin.provider"/>'});
      return false;
    }

    var fundInProviderRequest = new FormData();
    fundInProviderRequest.append('remark', remark);
    fundInProviderRequest.append('amount', amount);
    fundInProviderRequest.append('providerId', providerId);
    $("#submit-btn").html("Loading...");
    $.ajax({
      type: 'POST',
      url: "${contextPath}/ajax/provider/fundIn",
      data: fundInProviderRequest,
      processData: false,
      contentType: false,
      success: function (data) {
        $.MessageBox({message: "Success!"});
        $('#fundin').modal('hide');
        $("#submit-btn").html("<spring:message code="menu.left.fundin"/>");
      },
      error: function (data) {
        $.MessageBox({message: data.responseJSON.status.value});
        $("#submit-btn").html("<spring:message code="menu.left.fundin"/>");
      }
    });
  }
</script>
