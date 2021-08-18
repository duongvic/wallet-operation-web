<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 2/22/2021
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../include_page/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<c:set var="allRankingGroup" value=","/>
<c:forEach var="st" items="${paramValues.rankingGroups}">
    <c:set var="allRankingGroup" value="${allRankingGroup}${st},"/>
</c:forEach>

<select class="form-control hidden" name="rankingGroups" id="rankingGroups" multiple="multiple">
    <c:forEach var="rankingGroup" items="${rankingGroups}">
        <c:set var="rankingGroup2" value=",${rankingGroup.code},"/>
        <option ${fn:contains(allRankingGroup, rankingGroup2)?'selected':'' } value="${rankingGroup.code}">${rankingGroup.name}</option>
    </c:forEach>
</select>
