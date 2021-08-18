<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>


<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content" style="width: 670px;">
      <form name="edit" action="updateTrueService" method="post" >
        <input type="hidden" name="providerCode"/>
        <input type="hidden" name="serviceId"/>

        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="popup.header.icon.close"/></span>
          </button>
          <h4 class="modal-title" id="myModalLabel"><spring:message code="system.service.popup.update.title"/>: <span id="nameService"/></h4>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.serviceType"/>&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" id="serviceTypeEdit" name="serviceTypeEdit" class="form-control inputServiceCharge" required readonly="readonly"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.update.lable.serviceCode"/>&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" id="serviceCodeEdit" name="serviceCodeEdit" class="form-control inputServiceCharge" required readonly="readonly">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.update.lable.serviceName"/>&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" name="serviceName" class="form-control inputServiceCharge" required>
            </div>
          </div>

          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.update.lable.service.short.Name"/>&nbsp;<span style="color: red">*</span></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <input type="text" name="serviceShortName" class="form-control inputServiceCharge" required>
            </div>
          </div>
          
          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.update.lable.service.customerTypeSupported"/>&nbsp;<span style="color: red">*</span></label>
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
              <select id="serviceLevelEdit" name="serviceLevelEdit" class="form-control inputServiceCharge" required>
                <c:forEach var="item" items="${serviceLevels}">
                  <c:if test="${item.code != leverCode0}">
                    <c:set var="serviceLevel1" value=",${item},"/>
                    <option ${fn:contains(allSer, serviceLevel1)?'selected':'' } value="${item.code}">${item.name}</option>
                  </c:if>
                </c:forEach>
              </select>
              <select id="serviceLevelParentEdit" name="serviceLevelParentEdit" class="form-control inputServiceCharge" required style="display:none">
                <c:forEach var="item" items="${serviceLevels}">
                  <c:set var="serviceLevel1" value=",${item},"/>
                  <option ${fn:contains(allSer, serviceLevel1)?'selected':'' } value="${item.code}">${item.name}</option>
                </c:forEach>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.update.lable.parentServiceCode"/></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <select id="parentServiceCodeEdit" name="parentServiceCodeEdit" class="form-control inputServiceCharge"></select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.actor"/></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <div class="radio-custom radio-success radio-inline">
                <input id="isActorPayerEdit" type="radio" name="isActorPayEdit" value="N">
                <label for="isActorPayerEdit"><spring:message code="system.service.popup.create.lable.actor.payer.check"/></label>
              </div>
              <div class="radio-custom radio-success radio-inline mt-none">
                <input id="isActorPayeeEdit" type="radio" name="isActorPayEdit" value="Y">
                <label for="isActorPayeeEdit"><spring:message code="system.service.popup.create.lable.actor.peyee.check"/></label>
              </div>

              <%--<div class="checkbox-custom checkbox-default">
                <input type="checkbox" id="isActorPayeeEdit" name="isActorPayeeEdit" value="Y">
                <label for="isActorPayeeEdit"><spring:message code="system.service.popup.create.lable.actor.check"/></label>
              </div>--%>
            </div>
          </div>

          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.create.lable.parentfee"/></label>
            <div class="col-md-8 input-group" style="width: 72.5%; ">
              <div class="">
                <div class="checkbox-custom checkbox-success checkboxWrapper">
                  <input type="checkbox" name="parentfeeEdit" id="parentfeeEdit" value="Y">
                  <label for="parentfeeEdit"></label>
                </div>
              </div>
            </div>
          </div>

          <div class="form-group">
            <label class="col-md-3 control-label"><spring:message code="system.service.popup.update.lable.decription"/></label>
            <div class="col-md-8 input-group" style=" width: 72.5%; ">
              <textarea name="serviceDesc" class="form-control inputServiceCharge" maxlength="1024"></textarea>
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default " data-dismiss="modal"><spring:message code="system.service.popup.update.lable.cancel"/></button>
          <button type="submit" class="btn btn-primary btnSubmit"><spring:message code="system.service.popup.update.lable.submit"/></button>
        </div>
      <sec:csrfInput /></form>
    </div>
  </div>
</div>


<script type="text/javascript">
  $(document).ready(function () {
    //------------------------ SERVICE EDIT ----------------------
    $(".link-edit").click(function () {
      var serviceCode = $(this).attr("serviceCode");
      var level = $(this).attr("serviceLevel");
      var serviceType = $(this).attr("serviceType");
      var serviceParentId = $(this).attr("serviceParentId");
      var serviceShortName = $(this).attr("serviceShortName");
      var customerTypeSupported = $(this).attr("customerTypeSupported");
      $("form[name=edit] input[name=serviceId]").val($(this).attr("serviceId"));
      $("form[name=edit] input[name=serviceTypeEdit]").val(serviceType);
      $("form[name=edit] input[name=serviceCodeEdit]").val(serviceCode);
      $("form[name=edit] input[name=serviceName]").val($(this).attr("serviceName"));
      $("form[name=edit] input[name=serviceShortName]").val(serviceShortName);
      $("form[name=edit] input[name=customerTypeSupported]").val(customerTypeSupported);
      $("form[name=edit] textarea[name=serviceDesc]").val($(this).attr("serviceDesc"));
      $("form[name=edit] input[name=serviceprice]").val($(this).attr("servicePrices"));

      var isActorPayee = $(this).attr("isActorPayee");
      if (isActorPayee === 'Y') {
        $("form[name=edit] input[id=isActorPayeeEdit]").prop('checked', true);
      } else {
        $("form[name=edit] input[id=isActorPayerEdit]").prop('checked', true);
      }

      if ($(this).attr("isParentFee") === 'true') {
        $("form[name=edit] input[id=parentfeeEdit]").prop('checked', true);
      }


      $('#nameService').text($(this).attr("serviceName") + ' [ ' + serviceCode + ' ] ');
      if (level == "0") {
        $("#serviceLevelEdit").css("display", "none");
        $("#serviceLevelParentEdit").css("display", "block");
        $('#serviceLevelParentEdit').prop('disabled', true);
        $("form[name=edit] select[name=serviceLevelParentEdit]").val(level);
        $('#parentServiceCodeEdit').prop('disabled', true);
      } else {
        $("#serviceLevelEdit").css("display", "block");
        $("#serviceLevelParentEdit").css("display", "none");
        $('#serviceLevelParentEdit').prop('disabled', false);
        $("form[name=edit] select[name=serviceLevelEdit]").val(level);
        $('#parentServiceCodeEdit').prop('disabled', false);
      }
      createParentServiceCode(serviceParentId, serviceType, level, serviceCode);
    });
    $("#serviceLevelEdit").change(function () {
      createParentServiceCode($('#parentServiceCodeEdit').val(), $('#serviceTypeEdit').val(), $('#serviceLevelEdit').val(), $('#serviceCodeEdit').val());
    });
    $("#serviceLevelParentEdit").change(function () {
      createParentServiceCode($('#parentServiceCodeEdit').val(), $('#serviceTypeEdit').val(), $('#serviceLevelParentEdit').val(), $('#serviceCodeEdit').val());
    });
    $('form[name=edit]').submit(function () {
      $(".btnSubmit").button('loading');
      $.post($(this).attr('action'), $(this).serialize(), function (json) {
        $.MessageBox({message: json.message});
        if (json.code == 0) {
          $('#edit').modal('toggle');
          location.reload();
        }
        setTimeout(function () {
          $(".btnSubmit").button('reset');
        }, 1000);
      });
      return false;
    });
  });
</script>

