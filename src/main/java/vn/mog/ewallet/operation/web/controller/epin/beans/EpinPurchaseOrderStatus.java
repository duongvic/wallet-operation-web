package vn.mog.ewallet.operation.web.controller.epin.beans;

import org.apache.commons.lang3.StringUtils;

public enum EpinPurchaseOrderStatus {

  /*
   * PENDING(0, "Ch\u01B0a gi\u1EA3i quy\u1EBFt"), FAIL(1,
   * "Th\u1EA5t b\u1EA1i"), SUCCESS(2, "Th\u00E0nh c\u00F4ng")
   */

  PENDING(0, "purchase.order.merchant.type.pending"), FAIL(1, "purchase.order.merchant.type.fail"), SUCCESS(2, "purchase.order.merchant.type.success");

  public Integer code;
  public String displayText;

  EpinPurchaseOrderStatus(int code, String displayText) {
    this.code = code;
    this.displayText = displayText;
  }

  public static EpinPurchaseOrderStatus getPurchaseOrderMerchantType(int value) {
    for (EpinPurchaseOrderStatus state : EpinPurchaseOrderStatus.values()) {
      if (state.code == value) {
        return state;
      }
    }
    return null;
  }

  public Integer value() {
    return this.code;
  }

  public String cssValue(String value) {
    if (String.valueOf(this.code).equals(value)) {
      return "selected";
    }
    return StringUtils.EMPTY;
  }

  public String displayText() {
    return this.displayText;
  }
}
