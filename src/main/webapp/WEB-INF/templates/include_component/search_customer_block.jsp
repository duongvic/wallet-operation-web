<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="uaaCustomerBeanClassName"
       value="<%= vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer.class.getName() %>"/>

<select class="form-control multiple-select hidden" multiple="multiple" id="customerIdsBlock"
        name="customerIdsBlock">
    <c:choose>
        <c:when test="${not empty customers && customers.size() > 0 }">
            <c:choose>
                <c:when test="${customers[0]['class'].name eq uaaCustomerBeanClassName}">
                    <c:forEach var="item" items="${customers}">
                        <option value="${item.cif}">${item.displayName}&nbsp;(${item.msisdn})</option>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="item" items="${customers}">
                        <option value="${item.cif}">${item.fullName}&nbsp;(${item.msisdn})</option>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <option value="">N/A</option>
        </c:otherwise>
    </c:choose>
</select>