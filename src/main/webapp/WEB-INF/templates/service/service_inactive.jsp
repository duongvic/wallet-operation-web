<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>


<div class="modal fade" id="inactive" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form name="changeStatus" action="changeStatus" method="post" >
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="popup.header.icon.close"/></span>
          </button>
          <h4 class="modal-title" id="myModalLabel"><span id="titlePopupInactive"/></h4>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label class="col-md-12 control-label" id="messageInactive"></label>
          </div>
        </div>
        <div class="alert alert-default mb-none mt-md p-sm">
          <div class="checkbox-custom checkbox-success">
            <input type="checkbox" name="ckaccess" id="checkboxInactive">
            <label id="labelCheckbox"><spring:message code="message.do.you.want.to.change.the.status"/> </label>
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
    //------------------------ SERVICE INACTIVE ----------------------
    $('.link-status').click(function () {
      var serviceId = $(this).attr('serviceId');
      var serviceCode = $(this).attr('serviceCode');
      var serviceName = $(this).attr('serviceName');
      var active = $(this).attr('active');
      var isTrueSet = (active === 'Y');
      var textAlert = "<spring:message code="system.service.turn.on.services"/> ";
      if (isTrueSet) {
        textAlert = "<spring:message code="system.service.turn.off.services"/>";
      }
      textAlert = textAlert + serviceName + '[' + serviceCode + ']';
      $('#titlePopupInactive').text(textAlert);
      if (isTrueSet) {
        $('#messageInactive').text('<spring:message code="system.service.popup.inactive.message.inactive"/>');
      } else {
        $('#messageInactive').text('<spring:message code="system.service.popup.inactive.message.active"/>');
      }
      $('form[name=changeStatus]').submit(function () {
        var answer = $('form[name=changeStatus] input[name=ckaccess]').is(":checked");
        if (answer) {
          $(".btnSubmit").button('loading');
          $.post('changeStatus', {serviceId: serviceId, active: active}, function (json) {
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
          $.MessageBox({message: '<spring:message code="message.do.you.want.to.change.the.status"/>'});
        }
        return false;
      });
    });
    $('#labelCheckbox').click(function () {
      if ($('#checkboxInactive').is(':checked')) {
        $('#checkboxInactive').prop('checked', false);
      } else {
        $('#checkboxInactive').prop('checked', true);
      }
    });
  });
</script>

