package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans;

import java.util.LinkedHashMap;
import java.util.Map;


public class TxnStatus {

  public static final int INITIAL = 0;
  public static final int OPENED = 1;
  public static final int FAILED = 3;
  public static final int REVERSED = 5;
  public static final int CANCELLED = 7;
  public static final int HOLD = 9;
  public static final int CLOSED = 10;

  public static final int LOCKING = 2;
  public static final int WAITTING = 6;
//  public static final int PENDING = 11;

  public static final Map<Integer, String> TXN_STATUS_LIST = new LinkedHashMap<>();

  public static final Map<Integer, String> TRANSACTION_STATUSES = new LinkedHashMap<>();

  static {
    TXN_STATUS_LIST.put(INITIAL, "txn.status.initial");
    TXN_STATUS_LIST.put(OPENED, "txn.status.opened");
    TXN_STATUS_LIST.put(FAILED, "txn.status.failed");
    TXN_STATUS_LIST.put(REVERSED, "txn.status.reversed");
    TXN_STATUS_LIST.put(CANCELLED, "txn.status.cancelled");
    TXN_STATUS_LIST.put(CLOSED, "txn.status.closed");
    TXN_STATUS_LIST.put(HOLD, "txt.status.hold");
    TXN_STATUS_LIST.put(LOCKING, "txt.status.locking");
    TXN_STATUS_LIST.put(WAITTING, "txt.status.waitting");

    TRANSACTION_STATUSES.put(TxnStatus.INITIAL, "transaction.api.search.status.init");
    TRANSACTION_STATUSES.put(TxnStatus.OPENED, "transaction.api.search.status.open");
    TRANSACTION_STATUSES.put(TxnStatus.CANCELLED, "transaction.api.search.status.cancel");
//    TRANSACTION_STATUSES.put(TxnStatus.PENDING, "transaction.api.search.status.pending");
    TRANSACTION_STATUSES.put(TxnStatus.FAILED, "transaction.api.search.status.fail");
    TRANSACTION_STATUSES.put(TxnStatus.CLOSED, "transaction.api.search.status.close");
    TRANSACTION_STATUSES.put(TxnStatus.REVERSED, "transaction.api.search.status.reversal");
    TRANSACTION_STATUSES.put(TxnStatus.HOLD, "transaction.api.search.status.hold");
    TRANSACTION_STATUSES.put(TxnStatus.LOCKING, "transaction.api.search.status.locking");
    TRANSACTION_STATUSES.put(TxnStatus.WAITTING, "transaction.api.search.status.waitting");
  }
}
