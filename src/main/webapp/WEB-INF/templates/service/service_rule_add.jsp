<%@ page import="vn.mog.ewallet.operation.web.controller.service.TrueServiceController" %>
<%@ include file="../include_page/taglibs.jsp" %>

<div class="modal fade" id="service-rule-add-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content" style="width: 770px;">

      <form name="trueServiceMatrix" action="addTrueServiceMatrix" method="post" >
        <input type="hidden" id="matrixServiceId" name="matrixServiceId">
        <input type="hidden" id="action" name="action">
        <div class="modal-content" id="updateService">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">
              <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="popup.header.icon.close"/></span>
            </button>
            <h4 class="modal-title" id="myModalLabel"><spring:message code="system.service.popup.create.service.rule.title"/></h4>
          </div>
          <div class="modal-body">

            <div class="form-group">
              <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.serviceName"/></label>
              <div class="col-md-8 input-group" style="width: 72.5%;" id="lbServiceName"></div>
            </div>

            <div class="form-group mb-none">
              <label class="col-md-6 control-label"><spring:message code="system.service.popup.create.lable.choose.rule"/></label>
            </div>

            <div class="table-responsive">
              <table class="table table-bordered table-responsive table-striped mb-none datatable-default">
                <thead>
                <tr>
                  <th class="center"><spring:message code="system.service.popup.matrix.tranrule.existmatrix"/></th>
                  <th><spring:message code="system.service.popup.matrix.tranrule.code"/></th>
                  <th><spring:message code="system.service.popup.matrix.tranrule.name"/></th>
                  <th><spring:message code="system.service.popup.matrix.tranrule.servicetype"/></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td class="center">
                    <input type="radio" id="tranRuleMatrixId" name="tranRuleMatrixId" value="" checked="">
                  </td>
                  <td>matrixTransRuleCode</td>
                  <td>matrixTransRuleName</td>
                  <td>matrixTransRuleServiceType</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>

          <div class="modal-footer">
            <div class="pull-left">
              <div class="checkbox-custom checkbox-success">
                <input type="checkbox" name="ckaccess" id="confirmChb">
                <label id="labelCheckboxDelete"><spring:message code="system.service.popup.create.lable.messenger"/></label>
              </div>
            </div>

            <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="system.service.popup.create.lable.cancel"/></button>
            <button type="submit" value="delete" class="btn btn-danger btnSubmit deleteRequest" disabled="disabled"><spring:message code="system.service.popup.create.lable.delete"/></button>
            <button type="submit" value="submit" class="btn btn-primary btnSubmit confirmRequest"><spring:message code="system.service.popup.create.lable.submit"/></button>
          </div>
        </div>
      <sec:csrfInput /></form>
    </div>
  </div>
</div>

<script type="text/javascript">
  $(document).ready(function () {
    //------------------------ SERVICE RULE_ADD ----------------------
    $('form[name=trueServiceMatrix] #confirmChb').click(function(){
      if (!$('#confirmChb').is(':checked')){
    		$('button.deleteRequest').prop('disabled', true);
      } else {
    	  $('button.deleteRequest').prop('disabled', false);
      }
    });
    $('form[name=trueServiceMatrix] .deleteRequest').click(function () {
      $("form[name=trueServiceMatrix] button[type=submit]").removeClass("activity");    
       $(this).addClass("activity");
       $(this).button('loading'); 
    });

    $('form[name=trueServiceMatrix] .confirmRequest').click(function () {
      $("form[name=trueServiceMatrix] button[type=submit]").removeClass("activity");
      $(this).addClass("activity");
      $(this).button('loading');
    });
    $('form[name=trueServiceMatrix]').submit(function () {
     
      $("#action").val($(".activity").val());

      $.post($(this).attr('action'), $(this).serialize(), function (json) {
        $.MessageBox({message: json.message});
        if (json.code == 0) {
          $('#trueServiceMatrix').modal('toggle');
          location.reload();
        }
        setTimeout(function () {
          $(".btnSubmit").button('reset');
        }, 1000);
      });
      return false;
    });

    $('.service-rule-add').click(function () {

      var serviceId = $(this).attr("serviceId");
      var serviceCode = $(this).attr("serviceCode");
      var serviceName = $(this).attr("serviceName");

      $("#matrixServiceId").val(serviceId);
      $("#lbServiceName").html(serviceName + ' [ ' + serviceCode + ' ] ');

      $('button.confirmRequest').prop('disabled', false);
      $('button.confirmRequest').button('loading');
      $('button.confirmRequest').show();

      var tbody = $('form[name=trueServiceMatrix] table.datatable-default tbody');
      tbody.html('');

      var url = ctx + '<%=TrueServiceController.TRUE_SERVICE_CONTROLLER%>' + '/findTransactionRuleMatrix';
      $.get(url, {serviceId: serviceId}, function (json) {
        if (json != null && json instanceof Array && json.length > 0) {

          var matrix = json;
          var i = 1;
          $.each(json, function (key, transactionRuleMatrix) {
            var tr = '<tr>' +
                '<td class="center">' +
                '<input type="radio" id="tranRuleMatrixId' + i + '" name="tranRuleMatrixId" value="' + transactionRuleMatrix.id + '" ' + transactionRuleMatrix.existMatrix + '>' +
                '</td>' +
                '<td>' + transactionRuleMatrix.code + '</td>' +
                '<td>' + transactionRuleMatrix.name + '</td>' +
                '<td>' + transactionRuleMatrix.serviceType + '</td>' +
                '</tr>';
            tbody.append(tr);
            i++;
          });
          $('button.confirmRequest').button('reset');
          $('button.deleteRequest').button('reset');
        } else {
          $('button.confirmRequest').prop('disabled', true);
          $('button.confirmRequest').hide();
          $('button.deleteRequest').prop('disabled', true);
          $('button.deleteRequest').hide();
        }
      });
    });
  });
</script>
