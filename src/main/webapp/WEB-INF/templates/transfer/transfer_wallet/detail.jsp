<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../transfer/wallet_order_detail.jsp">
  <jsp:param name="transferType" value="<%=ServiceType.WALLET_TRANSFER.name()%>"/>
</jsp:include>