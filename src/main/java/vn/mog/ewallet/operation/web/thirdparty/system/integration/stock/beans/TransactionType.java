package vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.beans;

public enum TransactionType {
  CREDIT,
  DEBIT;

  public static TransactionType getItemByCode(String code) {
    for (TransactionType item : TransactionType.values()) {
      if (item.name().equalsIgnoreCase(code)) {
        return item;
      }
    }
    return null;
  }
}
