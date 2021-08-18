package vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServiceEnum {
  ALL("ALL", "label.all_of_service"),
  BILL_ELECTRIC("BILL_ELECTRIC", "label.bill_electric"),
  BILL_FINANCE("BILL_FINANCE", "label.bill_finance"),
  //  BILL_OTHER("BILL_OTHER", "label.bill_other"),
  OTHER("OTHER", "label.other");

  private String code;
  private String name;
}
