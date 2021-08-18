<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>


<div class="modal fade" id="preDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">
          <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="popup.header.icon.close"/></span>
        </button>
        <h4 class="modal-title"><spring:message code="system.service.popup.delete.title"/>:&nbsp;<span class="textService"/></h4>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label class="col-md-12 control-label"><spring:message code="system.service.popup.delete.lable.message"/></label>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default " data-dismiss="modal"><spring:message code="system.service.popup.update.lable.cancel"/></button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form name="delete" action="deleteTrueService" method="post" >
        <input type="hidden" name="serviceCode" class="form-control">
        <input type="hidden" name="serviceId" class="form-control">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="popup.header.icon.close"/></span>
          </button>
          <h4 class="modal-title" id="myModalLabel"><spring:message code="system.service.popup.delete.title"/>:&nbsp;<span class="textService"/></h4>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label class="col-md-12 control-label" id="messageDelete"></label>
          </div>
        </div>
        <div class="alert alert-default mb-none mt-md p-sm">
          <div class="checkbox-custom checkbox-success">
            <input type="checkbox" name="ckaccess" id="checkboxDelete">
            <label id="labelCheckboxDelete"><spring:message code="system.service.popup.delete.lable.message.deletequestion"/></label>
          </div>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default " data-dismiss="modal"><spring:message code="system.service.popup.update.lable.cancel"/></button>
          <button type="submit" class="btn btn-primary btnSubmit"><spring:message code="system.service.popup.delete.lable.yes"/></button>
        </div>
      <sec:csrfInput /></form>
    </div>
  </div>
</div>
<script type="text/javascript">
  $(document).ready(function () {
    //------------------------ SERVICE DELETE ----------------------
    $(".link-delete").click(function () {
      $("form[name=delete] input[name=serviceCode]").val($(this).attr("serviceCode"));
      $("form[name=delete] input[name=serviceId]").val($(this).attr("serviceId"));
      $(".textService").html($(this).attr("serviceName") + ' [ ' + $(this).attr("serviceCode") + ' ]');
      $.ajax({
        type: 'GET',
        url: ctx + '/service/service-profile/listChildrenDelete',
        data: {
          serviceId: $(this).attr("serviceId")
        },
        success: function (data) {
          if (data.length > 1) {
            $("#messageDelete").html('<spring:message code="system.service.popup.delete.lable.message.deleteAll"/>');
          } else {
            $("#messageDelete").html('<spring:message code="system.service.popup.delete.lable.message.delete"/>');
          }
        }
      });
    });
    $('form[name=delete]').submit(function () {
      var answer = $('form[name=delete] input[name=ckaccess]').is(":checked");
      if (answer) {
        $(".btnSubmit").button('loading');
        $.post($(this).attr('action'), $(this).serialize(), function (json) {
          $.MessageBox({message: json.message});
          if (json.code == 0) {
            $('#delete').modal('toggle');
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