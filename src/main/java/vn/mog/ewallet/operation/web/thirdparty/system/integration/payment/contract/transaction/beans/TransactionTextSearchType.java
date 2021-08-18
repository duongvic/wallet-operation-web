package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans;

import org.apache.commons.lang3.StringUtils;

public enum TransactionTextSearchType {
//  ID_TXN, STR_TRACE_NO, STR_ORDER_ID, STR_ORDER_INFO

  ID_TXN("ID_TXN","reim.table.transaction.id"),
  STR_TRACE_NO("STR_TRACE_NO","TRACE NO ID"),
  STR_ORDER_ID("STR_ORDER_ID","label.search.request.code"),
  STR_ORDER_INFO("STR_ORDER_INFO","label.search.order.information");

  public String code;
  public String displayText;

  TransactionTextSearchType(String code, String displayText) {
    this.code = code;
    this.displayText = displayText;
  }


  public String value() {
    return this.code;
  }

  public String displayText() {
    return this.displayText;
  }

  public String selectedTransactionTextSearchType(String value) {
    if (String.valueOf(this.code).equals(value)) {
      return "selected";
    }
    return StringUtils.EMPTY;
  }

  public TransactionTextSearchType getTransactionTextSearchTypeName(String name) {
    if (StringUtils.isNotBlank(name)) {
      for (TransactionTextSearchType textSearchTypes : TransactionTextSearchType.values()) {
        if (name.equals(textSearchTypes.name())) {
          return textSearchTypes;
        }
      }
    }

    return null;
  }
}

