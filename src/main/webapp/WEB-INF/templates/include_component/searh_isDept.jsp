<%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 4/10/2020
  Time: 10:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderChannelType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<select class="form-control multiple-select" id="isDept" name="isDept" style="margin: 8px 0px;">
    <option value="" selected><spring:message code="select.choose.all"/></option>
    <option ${param["isDept"] eq "false" ?'selected':'' } value="false"><spring:message code="button.no"/>&nbsp<spring:message code="label.pay"/></option>
    <option ${param["isDept"] eq "true" ?'selected':''} value="true"><spring:message code="label.pay"/></option>
</select>
