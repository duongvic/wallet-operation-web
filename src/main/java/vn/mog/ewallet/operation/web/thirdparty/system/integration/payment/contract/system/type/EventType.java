package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

import java.util.LinkedHashMap;
import java.util.Map;

public enum EventType {
  WALLET_BALANCE_INQUIRY, WALLET_CHARGE, WALLET_COMMISSION, WALLET_FEE, WALLET_REFUND, WALLET_FUND_IN, WALLET_FUND_OUT, WALLET_FUND_TRANSFER,

  SERVICE_TOPUP, SERVICE_SOFTPIN, SERVICE_EXPORT_EPIN, SERVICE_BILL_PAYMENT, SERVICE_DISBURSEMENT;

  public static final Map<String, String> BALANCE_EVENT_TYPES = new LinkedHashMap<>();

  static {
    BALANCE_EVENT_TYPES.put(EventType.WALLET_BALANCE_INQUIRY.name(), "Wallet Balance Inquiry");
    BALANCE_EVENT_TYPES.put(EventType.WALLET_CHARGE.name(), "Wallet Charge");
    BALANCE_EVENT_TYPES.put(EventType.WALLET_COMMISSION.name(), "Wallet Commission");
    BALANCE_EVENT_TYPES.put(EventType.WALLET_FEE.name(), "Wallet Fee");
    BALANCE_EVENT_TYPES.put(EventType.WALLET_REFUND.name(), "Wallet Refund");
    BALANCE_EVENT_TYPES.put(EventType.WALLET_FUND_IN.name(), "Wallet Fund_In");
    BALANCE_EVENT_TYPES.put(EventType.WALLET_FUND_OUT.name(), "Wallet Fund_Out");
    BALANCE_EVENT_TYPES.put(EventType.WALLET_FUND_TRANSFER.name(), "Wallet Fund Transfer");
    BALANCE_EVENT_TYPES.put(EventType.SERVICE_TOPUP.name(), "Service Topup");
    BALANCE_EVENT_TYPES.put(EventType.SERVICE_SOFTPIN.name(), "Service Softpin");
    BALANCE_EVENT_TYPES.put(EventType.SERVICE_EXPORT_EPIN.name(), "Service Export Epin");
    BALANCE_EVENT_TYPES.put(EventType.SERVICE_BILL_PAYMENT.name(), "Service Bill Payment");
    BALANCE_EVENT_TYPES.put(EventType.SERVICE_DISBURSEMENT.name(), "Service Disbursement");
  }

  public static EventType getEventType(String label) {
    for (EventType item : EventType.values()) {
      if (item.toString().equals(label)) {
        return item;
      }
    }
    return null;
  }
}
