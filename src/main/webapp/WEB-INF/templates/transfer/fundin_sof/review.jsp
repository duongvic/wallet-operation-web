
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../transfer/transfer_step_three_review.jsp">
  <jsp:param name="transferType" value="<%=ServiceType.FUND_TRANSFER.name()%>"/>
</jsp:include>
