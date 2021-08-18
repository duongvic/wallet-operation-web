<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="vn.mog.ewallet.operation.web.controller.translog.TransactionWalletUserController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.translog.TransactionReversalController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="allSearchUri" >${contextPath}<%=TransactionReversalController.TRANS_REVERSAL_LIST%></c:set>
<c:set var="fundinSearchUri" >${contextPath}<%=TransactionWalletUserController.TRANSACTION_WALLET_FUND_IN_LIST%></c:set>
<c:set var="fundoutSearchUri" >${contextPath}<%=TransactionWalletUserController.TRANSACTION_WALLET_FUND_OUT_LIST%></c:set>
<c:set var="internalWalletUri" >${contextPath}<%=TransactionWalletUserController.TRANSACTION_WALLET_INTERNAL_WALLET_LIST%></c:set>
<c:set var="p2pWalletUri" >${contextPath}<%=TransactionWalletUserController.TRANSACTION_WALLET_P2P_WALLET_LIST%></c:set>
<c:set var="sofTransferUri" >${contextPath}<%=TransactionWalletUserController.TRANSACTION_WALLET_SOF_TRANSFER_LIST%></c:set>

<script>

  function openTab(paramValue) {
    var searchURL = '';
    $('#idOwnerCustomerTypes').val(paramValue);
    if (window.location.search.indexOf("?") >= 0) {
      if (window.location.search.indexOf("idOwnerCustomerTypes") >= 0) {
        searchURL = '${allSearchUri}' + replaceUrlParam(window.location.search, 'idOwnerCustomerTypes', paramValue);
      } else {
        searchURL = '${allSearchUri}' + window.location.search + '&idOwnerCustomerTypes=' + paramValue;
      }

    } else {
      searchURL = '${allSearchUri}' + '?idOwnerCustomerTypes=' + paramValue;
    }
    window.location.href = searchURL;
  }

  function openTabAll() {
    var searchURL = '';
    if (window.location.search.indexOf("?") >= 0 && window.location.search.indexOf("idOwnerCustomerTypes") >= 0) {
      searchURL = '${allSearchUri}' + replaceUrlParam(window.location.search, 'idOwnerCustomerTypes', '');
    } else {
      searchURL = '${allSearchUri}';
    }
    window.location.href = searchURL;
  }



</script>