package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

import java.util.LinkedHashMap;
import java.util.Map;

public enum ServiceType {

  FUND_IN, FUND_OUT, FUND_TRANSFER,
  TXN_DETAIL, TXN_REVERSAL, BALANCE_INQUIRY,
  PHONE_TOPUP, EPIN, EXPORT_EPIN, BILL_PAYMENT,
  DISBURSEMENT, SYSTEM_SETUP, WALLET_TRANSFER,
  P2P_TRANSFER, REMITTANCE, GAME_TOPUP, CASH_BACK,
  WALLET_CASH_IN, WALLET_CASH_OUT;

  public static final Map<String, String> SERVICE_TYPES = new LinkedHashMap<>();
  public static final Map<String, String> PAYMENT_TYPES = new LinkedHashMap<>();
  public static final Map<String, String> TRANSACTION_RULES = new LinkedHashMap<>();
  public static final Map<String, String> AGENT_SERVICE_RULES = new LinkedHashMap<>();
  public static final Map<String, String> WALLET_SERVICE_TYPES = new LinkedHashMap<>();
  public static final Map<String, String> FULL_SERVICE_TYPES = new LinkedHashMap<>();

  static {
    SERVICE_TYPES.put(ServiceType.PHONE_TOPUP.name(), "service.payment.phonetopup");
    SERVICE_TYPES.put(ServiceType.EPIN.name(), "service.payment.epin");
    SERVICE_TYPES.put(ServiceType.EXPORT_EPIN.name(), "service.payment.exportepin");
    SERVICE_TYPES.put(ServiceType.FUND_IN.name(), "service.type.fundin");
    SERVICE_TYPES.put(ServiceType.FUND_OUT.name(), "service.type.fundout");
    SERVICE_TYPES.put(ServiceType.BILL_PAYMENT.name(), "service.type.billpayment");

    PAYMENT_TYPES.put(ServiceType.EPIN.name(), "service.payment.epin");
    PAYMENT_TYPES.put(ServiceType.PHONE_TOPUP.name(), "service.payment.phonetopup");
    PAYMENT_TYPES.put(ServiceType.EXPORT_EPIN.name(), "service.payment.exportepin");
    PAYMENT_TYPES.put(ServiceType.BILL_PAYMENT.name(), "service.payment.billpayment");
    PAYMENT_TYPES.put(ServiceType.CASH_BACK.name(), "service.payment.cashback");
    PAYMENT_TYPES.put(ServiceType.WALLET_CASH_IN.name(), "service.payment.cash.in");
    PAYMENT_TYPES.put(ServiceType.WALLET_CASH_OUT.name(), "service.payment.cash.out");
    PAYMENT_TYPES.put(ServiceType.P2P_TRANSFER.name(), "service.payment.p2p.transfer");
    /*
     * PAYMENT_TYPES.put(ServiceType.DISBURSEMENT.name(), "Disbursement");
     */

    WALLET_SERVICE_TYPES.put(ServiceType.FUND_IN.name(), "Fund in");
    WALLET_SERVICE_TYPES.put(ServiceType.FUND_OUT.name(), "Fund out");
    WALLET_SERVICE_TYPES.put(ServiceType.FUND_TRANSFER.name(), "Fund Transfer");
    WALLET_SERVICE_TYPES.put(ServiceType.TXN_DETAIL.name(), "TXN Detail");
    WALLET_SERVICE_TYPES.put(ServiceType.TXN_REVERSAL.name(), "TXN Reversal");
    WALLET_SERVICE_TYPES.put(ServiceType.BALANCE_INQUIRY.name(), "Balance Inquiry");
    WALLET_SERVICE_TYPES.put(ServiceType.DISBURSEMENT.name(), "Disbursement");
    WALLET_SERVICE_TYPES.put(ServiceType.SYSTEM_SETUP.name(), "System Setup");
    WALLET_SERVICE_TYPES.put(ServiceType.WALLET_TRANSFER.name(), "Wallet Transfer");
    WALLET_SERVICE_TYPES.put(ServiceType.P2P_TRANSFER.name(), "P2P Transfer");
    WALLET_SERVICE_TYPES.put(ServiceType.REMITTANCE.name(), "Remittance");

    TRANSACTION_RULES.put(ServiceType.FUND_IN.name(), "Fund in");
    TRANSACTION_RULES.put(ServiceType.FUND_OUT.name(), "Fund out");
    TRANSACTION_RULES.put(ServiceType.PHONE_TOPUP.name(), "Topup");
    TRANSACTION_RULES.put(ServiceType.EPIN.name(), "Buy card");
    TRANSACTION_RULES.put(ServiceType.EXPORT_EPIN.name(), "Export Epin");
    TRANSACTION_RULES.put(ServiceType.BILL_PAYMENT.name(), "Bill Payment");


    AGENT_SERVICE_RULES.put(ServiceType.FUND_IN.name(), "Fund in");
    AGENT_SERVICE_RULES.put(ServiceType.FUND_OUT.name(), "Fund out");
    AGENT_SERVICE_RULES.put(ServiceType.PHONE_TOPUP.name(), "Topup");
    AGENT_SERVICE_RULES.put(ServiceType.EPIN.name(), "Buy card");
    AGENT_SERVICE_RULES.put(ServiceType.BILL_PAYMENT.name(), "Bill Payment");

   
    FULL_SERVICE_TYPES.put(ServiceType.FUND_IN.name(), "Fund In");
    FULL_SERVICE_TYPES.put(ServiceType.FUND_OUT.name(), "Fund Out");
    FULL_SERVICE_TYPES.put(ServiceType.FUND_TRANSFER.name(), "Fund Transfer");
    FULL_SERVICE_TYPES.put(ServiceType.TXN_DETAIL.name(), "TXN Detail");
    FULL_SERVICE_TYPES.put(ServiceType.TXN_REVERSAL.name(), "TXN Reversal");
    FULL_SERVICE_TYPES.put(ServiceType.BALANCE_INQUIRY.name(), "Balance Inquiry");
    FULL_SERVICE_TYPES.put(ServiceType.PHONE_TOPUP.name(), "Phone Topup");
    FULL_SERVICE_TYPES.put(ServiceType.EPIN.name(), "EPIN");
    FULL_SERVICE_TYPES.put(ServiceType.EXPORT_EPIN.name(), "Export EPIN");
    FULL_SERVICE_TYPES.put(ServiceType.BILL_PAYMENT.name(), "Bill Payment");
    FULL_SERVICE_TYPES.put(ServiceType.DISBURSEMENT.name(), "Disbursement");
    FULL_SERVICE_TYPES.put(ServiceType.SYSTEM_SETUP.name(), "System Setup");
    FULL_SERVICE_TYPES.put(ServiceType.WALLET_TRANSFER.name(), "Wallet Transfer");
    FULL_SERVICE_TYPES.put(ServiceType.P2P_TRANSFER.name(), "P2P Transfer");
    FULL_SERVICE_TYPES.put(ServiceType.REMITTANCE.name(), "Remittance");
    FULL_SERVICE_TYPES.put(ServiceType.GAME_TOPUP.name(), "Game Topup");
    FULL_SERVICE_TYPES.put(ServiceType.CASH_BACK.name(), "Cash Back");
    FULL_SERVICE_TYPES.put(ServiceType.WALLET_CASH_IN.name(), "Wallet Cash In");
    FULL_SERVICE_TYPES.put(ServiceType.WALLET_CASH_OUT.name(), "Wallet Cash Out");
  }

  public static ServiceType getServiceType(String label) {
    for (ServiceType item : ServiceType.values()) {
      if (item.name().equals(label)) {
        return item;
      }
    }
    return null;
  }
}
