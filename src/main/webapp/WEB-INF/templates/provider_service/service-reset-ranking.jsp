<%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 8/14/2020
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sping" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include_page/taglibs.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="modal fade" id="resetService" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form name="edit" id="reset-form">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span
              aria-hidden="true">&times;</span><span class="sr-only"><spring:message
              code="popup.header.icon.close"/></span></button>
          <h4 class="modal-title ttitle" id="myModalLabel"><spring:message
              code="label.recovery"/></h4>
        </div>
        <div class="modal-body">
          <div class="mb-md">
            <label for="provider2"><spring:message code="provider.list.providerProfile"/>:</label>
            <select class="form-control" name="provider2" id="provider2">
              <c:forEach var="provider" items="${providerAndTheirServices}">
                <option style="font-size: medium"
                        value="${provider.providerCode}">${ewallet:getProviderBizCode(provider.providerCode)}</option>
              </c:forEach>
            </select>
          </div>
          <div class="alert alert-default mb-none mt-md p-sm">
            <div class="checkbox-custom checkbox-success">
              <input type="checkbox" id="reset-accept">&nbsp;
              <label><spring:message
                  code="label.reset.rank.score"/></label>
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-default " data-dismiss="modal"><spring:message
              code="common.btn.cancel"/></button>
          <button type="button" class="btn btn-primary btnSubmit" onclick="handleReset()">
            <spring:message
                code="common.btn.action"/></button>
        </div>
        <sec:csrfInput/></form>
    </div>
  </div>
</div>
<script type="text/javascript">
  <%--function handleReset() {--%>
  <%--if (!$("#reset-accept").prop("checked")) {--%>
  <%--$.MessageBox(--%>
  <%--{message: "<spring:message code="service.exportcard.request.checkorder.checkbox"/>"});--%>
  <%--return;--%>
  <%--}--%>
  <%--var providerCode = $("#provider2").val();--%>
  <%--if (providerCode) {--%>
  <%--$.ajax({--%>
  <%--type: 'GET',--%>
  <%--url: "${contextPath}/ajax/provider/reset/" + providerCode,--%>
  <%--success: function (data) {--%>
  <%--$.MessageBox({message: "Success!"});--%>
  <%--$('#resetService').modal('hide');--%>
  <%--$("#provider").val(providerCode).change();--%>
  <%--},--%>
  <%--error: function (data) {--%>
  <%--$.MessageBox({message: data.responseJSON.status.value});--%>

  <%--}--%>
  <%--});--%>
  <%--}--%>
  <%--}--%>

  function handleReset() {
    if (!$("#reset-accept").prop("checked")) {
      $.MessageBox(
        {message: "<spring:message code="service.exportcard.request.checkorder.checkbox"/>"});
      return;
    }
    var providerCode = $("#provider2").val();
    if (providerCode) {
      $.ajax({
        type: 'GET',
        url: "${contextPath}/ajax/provider/reset/" + providerCode,
        success: function (data) {
          $.MessageBox({message: "Success!"});
          $('#resetService').modal('hide');
          location.reload();
        },
        error: function (data) {
          $.MessageBox({message: data.responseJSON.status.value});

        }
      });
    }
  }
</script>
