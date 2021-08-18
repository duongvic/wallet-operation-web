<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${(codeErr != null && codeErr >= 1) || (param.codeErr != null && param.codeErr >= 1) }">
  <div class="row">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
      <div class="alert_das appear-animation fadeInRightBig appear-animation-visible">
        <div class="alert alert-danger">
          <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
          <strong><spring:message code="service.exportcard.create.notification.title"/> ! </strong> <spring:message code="${mesErr != null ? mesErr : param.mesErr}" text="${mesErr != null ? mesErr : param.mesErr}" />
        </div>
      </div>
    </div>
  </div>
</c:if>
<c:if test="${(codeErr != null && codeErr == 0) || (param.codeErr != null && param.codeErr == 0) }">
  <div class="row">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
      <div class="alert_das appear-animation fadeInRightBig appear-animation-visible">
        <div class="alert alert-success">
          <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
          <strong><spring:message code="service.exportcard.create.notification.title"/> ! </strong> <spring:message code="${mesErr != null ? mesErr : param.mesErr}" text="${mesErr != null ? mesErr : param.mesErr}" />
        </div>
      </div>
    </div>
  </div>
</c:if>