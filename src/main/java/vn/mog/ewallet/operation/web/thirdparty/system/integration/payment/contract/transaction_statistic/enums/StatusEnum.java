package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum StatusEnum {
  OPEN, PENDING, SUCCESS, FAIL, PROCESSING;

  public static final Map<String, String> TRANSACTION_STATUSES = new LinkedHashMap<>();

  static {
    TRANSACTION_STATUSES.put(StatusEnum.OPEN.name(), StatusEnum.OPEN.name() );
    TRANSACTION_STATUSES.put(StatusEnum.PENDING.name(), StatusEnum.PENDING.name());
    TRANSACTION_STATUSES.put(StatusEnum.SUCCESS.name(), StatusEnum.SUCCESS.name());
    TRANSACTION_STATUSES.put(StatusEnum.FAIL.name(), StatusEnum.FAIL.name());
    TRANSACTION_STATUSES.put(StatusEnum.PROCESSING.name(), StatusEnum.PROCESSING.name());
  }
}
