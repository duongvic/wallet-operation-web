<%@ include file="../include_page/taglibs.jsp" %>

<div class="form-group">
  <label class="col-md-3 control-label"><spring:message code="transfer.initiate.transfer.modelTransfer"/></label>
  <div class="col-md-6">${serviceType}</div>
</div>

<div class="form-group">
  <label class="col-md-3 control-label"><spring:message code="transfer.initiate.transfer.sourceAccount"/><spans style="color: red">*</spans></label>
  <div class="col-md-6">${sourceAccount}</div>
</div>

<div class="form-group">
  <label class="col-md-3 control-label"><spring:message code="transfer.initiate.transfer.targetAccount"/><spans style="color: red">*</spans></label>
  <div class="col-md-6">${targetAccount}</div>
</div>

<div class="form-group">
  <label class="col-md-3 control-label"><spring:message code="transfer.initiate.transfer.transferAmount"/><spans style="color: red">*</spans></label>
  <div class="col-md-6">${ewallet:formatNumber(amount)} VND</div>
</div>

<div class="form-group">
  <label class="col-md-3 control-label"><spring:message code="fundorder.approve.transfer.createdBy"/> :</label>
  <div class="col-md-6">${creatorName}</div>
</div>

<div class="form-group">
  <label class="col-md-3 control-label"><spring:message code="request.transfer.notable"/></label>
  <div class="col-md-5"><textarea rows="5" name="remark" minlength="1" maxlength="1024" class="form-control" required>${remark}</textarea></div>
</div>

<div class="form-group">
  <label class="col-md-3 control-label"><spring:message code="request.transfer.requestFile"/><spans style="color: red">*</spans></label>
  <div class="col-md-6">
    <label id="fileName">
      <c:choose>
        <c:when test="${not empty attachmentName}"> ${attachmentName} </c:when>
        <c:otherwise><spring:message code="transfer.info.commom.file"/></c:otherwise>
      </c:choose>
    </label>
    <c:forEach var="item" items="${attachments }">
      <p><img alt="" src="data:image/png;base64, <c:out value='${item.imageBase64}'/>" style="max-width: 90%;"></p>
      <br/>
    </c:forEach>
  </div>
</div>

<div class="alert alert-default mp-md">
  <div class="checkbox-custom checkbox-success">
    <input type="checkbox" name="ckaccess" id="checkboxExample3"> <label for="checkboxExample3"><spring:message code="request.transfer.confirm"/></label>
  </div>
</div>