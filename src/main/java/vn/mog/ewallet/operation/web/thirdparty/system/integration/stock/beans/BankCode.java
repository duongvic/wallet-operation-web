package vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.beans;

import java.util.Arrays;
import java.util.List;

public enum BankCode {
  VIETCOMBANK,
  BIDV,
  VIETINBANK,
  AGRIBANK,
  TPBANK,
  MBBANK;

  public static List<BankCode> BANK_CODES_ENUM = Arrays.asList(BankCode.values());

  public static BankCode getItemByCode(String code) {
    for (BankCode item : BankCode.values()) {
      if (item.name().equalsIgnoreCase(code)) {
        return item;
      }
    }
    return null;
  }
}
