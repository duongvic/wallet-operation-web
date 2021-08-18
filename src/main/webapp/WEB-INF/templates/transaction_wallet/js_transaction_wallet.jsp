<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="vn.mog.ewallet.operation.web.controller.translog.TransactionWalletUserController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="allSearchUri" >${contextPath}<%=TransactionWalletUserController.TRANSACTION_WALLET_USER_LIST%></c:set>
<c:set var="fundinSearchUri" >${contextPath}<%=TransactionWalletUserController.TRANSACTION_WALLET_FUND_IN_LIST%></c:set>
<c:set var="fundoutSearchUri" >${contextPath}<%=TransactionWalletUserController.TRANSACTION_WALLET_FUND_OUT_LIST%></c:set>
<c:set var="internalWalletUri" >${contextPath}<%=TransactionWalletUserController.TRANSACTION_WALLET_INTERNAL_WALLET_LIST%></c:set>
<c:set var="p2pWalletUri" >${contextPath}<%=TransactionWalletUserController.TRANSACTION_WALLET_P2P_WALLET_LIST%></c:set>
<c:set var="sofTransferUri" >${contextPath}<%=TransactionWalletUserController.TRANSACTION_WALLET_SOF_TRANSFER_LIST%></c:set>

<script>

  function openTab(paramValue) {
    var searchURL = '';
    if (paramValue == 'fund_in') {
      if (window.location.search.indexOf("?") >= 0) {
        searchURL = '${fundinSearchUri}' + window.location.search;
      } else {
        searchURL = '${fundinSearchUri}';
      }
    } else if (paramValue == 'fund_out') {
      if (window.location.search.indexOf("?") >= 0) {
        searchURL = '${fundoutSearchUri}' + window.location.search;
      } else {
        searchURL = '${fundoutSearchUri}';
      }
    } else if (paramValue == 'internal_wallet') {
      if (window.location.search.indexOf("?") >= 0) {
        searchURL = '${internalWalletUri}' + window.location.search;
      } else {
        searchURL = '${internalWalletUri}';
      }
    } else if (paramValue == 'p2p_wallet') {
      if (window.location.search.indexOf("?") >= 0) {
        searchURL = '${p2pWalletUri}' + window.location.search;
      } else {
        searchURL = '${p2pWalletUri}';
      }
    } else if (paramValue == 'sof_transfer') {
      if (window.location.search.indexOf("?") >= 0) {
        searchURL = '${sofTransferUri}' + window.location.search;
      } else {
        searchURL = '${sofTransferUri}';
      }
    } else if (paramValue == 'all') {
      if (window.location.search.indexOf("?") >= 0) {
        searchURL = '${allSearchUri}' + window.location.search;
      } else {
        searchURL = '${allSearchUri}';
      }
    }
    window.location.href = searchURL;
  }

</script>