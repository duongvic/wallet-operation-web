<%@ page import="vn.mog.ewallet.operation.web.controller.translog.TransactionRuleController" %>
<%@ include file="../include_page/taglibs.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:url var="urlTransRuleCon" value="<%=TransactionRuleController.TRANS_RULE_CONTROLLER%>"/>
<div class="modal fade" id="deleteTransRule" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form name="deleteTransRule" id="" action="${urlTransRuleCon}/deleteTransRule" method="post" >
        <input type="hidden" name="transRuleCode" id="transRuleCode" class="form-control">
        <input type="hidden" name="transRuleId" id="transRuleId" class="form-control">

        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="popup.header.icon.close"/></span>
          </button>
          <h4 class="modal-title" id="myModalLabel"><spring:message code="system.trans.rule.popup.delete.title"/>:&nbsp;<span class="deleteTitle"/></h4>
        </div>

        <div class="modal-body">
          <label class="col-md-12 control-label" id="bodyMessage"></label>
        </div>

        <div class="alert alert-default mb-none mt-md p-sm">
          <div class="checkbox-custom checkbox-success">
            <input type="checkbox" name="ckaccess" id="checkboxDelete">
            <label id="labelCheckboxDelete"><spring:message code="system.trans.rule.popup.delete.lable.deletequestion"/></label>
          </div>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default " data-dismiss="modal"><spring:message code="popup.footer.label.cancel"/></button>
          <button type="submit" class="btn btn-primary btnSubmit"><spring:message code="popup.footer.label.save"/></button>
        </div>
      <sec:csrfInput /></form>
    </div>
  </div>
</div>
<script type="text/javascript">
  $(document).ready(function () {
    $(".link-delete").click(function () {
      $("form[name=deleteTransRule] input[name=transRuleCode]").val($(this).attr("tranRuleCode"));
      $("form[name=deleteTransRule] input[name=transRuleId]").val($(this).attr("tranRuleId"));
      $(".deleteTitle").html($(this).attr("tranRuleName") + ' [ ' + $(this).attr("tranRuleCode") + ' ]');
      $("#bodyMessage").html('<spring:message code="system.trans.rule.popup.delete.lable.message"/>');
    });
    $('form[name=deleteTransRule]').submit(function () {
      var answer = $('form[name=deleteTransRule] input[name=ckaccess]').is(":checked");
      if (answer) {
        $(".btnSubmit").button('loading');
        $.post($(this).attr('action'), $(this).serialize(), function (json) {
          $.MessageBox({message: json.message});
          if (json.code == 0) {
            $('#deleteTransRule').modal('toggle');
            location.reload();
          }
          setTimeout(function () {
            $(".btnSubmit").button('reset');
          }, 1000);
        });
      } else {
        $.MessageBox({message: 'Xác nhận xóa dịch vụ !'});
      }
      return false;
    });
    $('#labelCheckboxDelete').click(function () {
      if ($('#checkboxDelete').is(':checked')) {
        $('#checkboxDelete').prop('checked', false);
      } else {
        $('#checkboxDelete').prop('checked', true);
      }
    });
  });
</script>