<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
  <c:when test="${fn:contains(param.username, '@')}">
    ${fn:substringBefore(param.username, "@")}</br>@${fn:substringAfter(param.username, "@")}
  </c:when>
  <c:otherwise>
    ${param.username}
  </c:otherwise>
</c:choose>