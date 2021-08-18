<%@ include file="../include_page/taglibs.jsp" %>

<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content" style="width: 670px;">
      <form name="add" action="createTrueService" method="post" >
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="popup.header.icon.close"/></span>
          </button>
          <h4 class="modal-title" id="myModalLabel"><spring:message code="system.service.popup.create.title"/></h4>
        </div>
        <div class="modal-body">

          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.serviceType"/>&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <select id="serviceType" name="serviceType" class="form-control inputServiceCharge" required>
                <c:forEach var="item" items="${serviceTypes}">
                  <c:set var="serviceType" value=",${item.key},"/>
                  <option ${fn:contains(allSer, serviceType)?'selected':'' } value="${item.key}">${item.value}</option>
                </c:forEach>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.serviceCode"/>&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
                <input type="text" id="serviceCode" name="serviceCode" class="form-control inputServiceCharge"/>
            </div>
          </div>

          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.serviceName"/>&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" name="serviceName" class="form-control inputServiceCharge" required>
            </div>
          </div>

          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.serviceShortName"/>&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" name="serviceShortName" class="form-control inputServiceCharge" required>
            </div>
          </div>
          
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.customerTypeSupported"/>&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" name="customerTypeSupported" class="form-control inputServiceCharge" required>
            </div>
          </div>
          

          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.serviceprice"/></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" id="serviceprice" name="serviceprice" class="form-control inputServiceCharge"/>
            </div>
          </div>

          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.level"/>&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <select id="serviceLevel" name="serviceLevel" class="form-control inputServiceCharge" required>
                <c:forEach var="item" items="${serviceLevels}">
                  <c:set var="serviceLevel" value=",${item},"/>
                  <option ${fn:contains(allSer, serviceLevel)?'selected':'' } value="${item.code}">${item.name}</option>
                </c:forEach>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.parentServiceCode"/></label>
            <div class="col-md-8 input-group" style="width: 72.5%; ">
              <select id="parentServiceCode" name="parentServiceCode" class="form-control inputServiceCharge"></select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.actor"/>&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style="width: 72.5%; ">
                <div class="radio-custom radio-success radio-inline">
                  <input id="isActorPayerAdd" type="radio" name="isActorPayAdd" value="N" checked="">
                  <label for="isActorPayerAdd"><spring:message code="system.service.popup.create.lable.actor.payer.check"/></label>
                </div>
                <div class="radio-custom radio-success radio-inline mt-none">
                  <input id="isActorPayeeAdd" type="radio" name="isActorPayAdd" value="Y">
                  <label for="isActorPayeeAdd"><spring:message code="system.service.popup.create.lable.actor.peyee.check"/></label>
                </div>
            </div>
          </div>

          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.parentfee"/></label>
            <div class="col-md-8 input-group" style="width: 72.5%; ">
              <div class="">
                <div class="checkbox-custom checkbox-success checkboxWrapper">
                  <input type="checkbox" name="parentfee" id="parentfee" value="Y">
                  <label for="parentfee"></label>
                </div>
              </div>
            </div>
          </div>



          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.decription"/></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <textarea name="serviceDesc" class="form-control inputServiceCharge" maxlength="1024"></textarea>
            </div>
          </div>

        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="system.service.popup.create.lable.cancel"/></button>
          <button type="submit" class="btn btn-primary btnSubmit"><spring:message code="system.service.popup.create.lable.submit"/></button>
        </div>
      <sec:csrfInput /></form>
    </div>
  </div>
</div>
<script type="text/javascript">
  $(document).ready(function () {
//------------------------ SERVICE ADD ----------------------
    $('.link-add').click(function () {
      resetAllvalue();
      createParentServiceCodeAdd($("#serviceType").val(), $("#serviceLevel").val());
    });
    $('form[name=add]').submit(function () {
      $(".btnSubmit").button('loading');
      $.post($(this).attr('action'), $(this).serialize(), function (json) {
        $.MessageBox({message: json.message});
        if (json.code === 0) {
          $('#add').modal('toggle');
          location.reload();
        }
        setTimeout(function () {
          $(".btnSubmit").button('reset');
        }, 1000);
      });
      return false;
    });
    function resetAllvalue() {
      $("form[name=add] input[name=serviceCodeSuffix]").val("");
      $("form[name=add] input[name=serviceCode]").val("");
      $("form[name=add] input[name=serviceName]").val("");
      $("form[name=add] input[name=serviceShortName]").val("");
      $("form[name=add] input[name=customerTypeSupported]").val("");
      $("form[name=add] select[name=serviceType]").val("TOPUP");
      $("form[name=add] select[name=parentServiceCode]").val("");
      $("form[name=add] textarea[name=serviceDesc]").val("");
      $("form[name=add] select[name=serviceLevel]").val("0");
      $('#scServiceType').val($('#serviceType').val());
      if ($('#serviceType').val() === 'TOPUP') {
        $("#scTelcoType").css("display", "none");
        $("#scTelcoType").blur();
        if ($("#scTelcoType").is(":visible")) {
          $("#scTelcoType").hide();
          $("#scTelcoType").prop('required', null);
          $("#scTelcoType").val();
        }
        $("#scTelcoServiceType").show();
        $("#scTelcoServiceType").attr('required', 'required');
      } else {
        $("#scTelcoType").show();
        $("#scTelcoType").attr('required', 'required');
        $("#scTelcoServiceType").blur();
        if ($("#scTelcoServiceType").is(":visible")) {
          $("#scTelcoServiceType").hide();
          $("#scTelcoServiceType").prop('required', null);
          $("#scTelcoServiceType").val();
        }
      }
    }

    //change input default
    $("#serviceType").change(function () {
      $('#scServiceType').val($('#serviceType').val());
      createParentServiceCodeAdd($('#serviceType').val(), $('#serviceLevel').val());
      if ($('#serviceType').val() === 'TOPUP') {
        $("#scTelcoType").blur();
        if ($("#scTelcoType").is(":visible")) {
          $("#scTelcoType").hide();
          $("#scTelcoType").prop('required', null);
          $("#scTelcoType").val();
        }
        $("#scTelcoServiceType").show();
        $("#scTelcoServiceType").attr('required', 'required');
      } else {
        $("#scTelcoType").show();
        $("#scTelcoType").attr('required', 'required');
        $("#scTelcoServiceType").blur();
        if ($("#scTelcoServiceType").is(":visible")) {
          $("#scTelcoServiceType").hide();
          $("#scTelcoServiceType").prop('required', null);
          $("#scTelcoServiceType").val();
        }
      }
    });
    $("#serviceLevel").change(function () {
      createParentServiceCodeAdd($('#serviceType').val(), $('#serviceLevel').val());
    });

    function createParentServiceCodeAdd(serviceType, serviceLevel) {
      $('#parentServiceCode').html('refresh', true);
      var urlServiceCodeAdd = ctx + '/service/service-profile/listFeeAdd';
      $.ajax({
        type: 'POST',
        url: urlServiceCodeAdd,
        data: {
          serviceType: serviceType, serviceLevel: serviceLevel
        },
        success: function (data) {
          var data = JSON.parse(data);
          if (data != null && data != "") {
            $("#parentServiceCode").append("<option  value=''><spring:message code='system.service.popup.create.lable.selectParentServiceCode'/></option>");

            function formatResult(node) {
              var $result = $('<span style="padding-left:' + (20 * node.level) + 'px;">'
                  + '<span style="color: #428bca;" class="icon node-icon glyphicon glyphicon-bookmark"></span>'
                  + '&nbsp;' + node.text + ' - ' + node.id + '</span>');
              return $result;
            }
            $("#parentServiceCode").select2({
              dropdownParent: $("#add"),
              placeholder: '<spring:message code="system.service.popup.create.lable.selectParentServiceCode"/>',
              width: "100%",
              data: data,
              formatSelection: function (item) {
                return item.text
              },
              formatResult: function (item) {
                return item.text
              },
              templateResult: formatResult
            });
          }
        }
      });
    }
  });
</script>
