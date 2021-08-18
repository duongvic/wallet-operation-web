package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum ServiceTypeEnum {

  FUND_IN,
  FUND_OUT,
  FUND_TRANSFER,
  TXN_REVERSAL,
  TXN_DETAIL,
  BALANCE_INQUIRY,
  PHONE_TOPUP,
  EPIN,
  EXPORT_EPIN,
  BILL_PAYMENT,
  DISBURSEMENT,
  SYSTEM_SETUP,
  WALLET_TRANSFER,
  REMITTANCE,
  SMS_CHARGE,
  P2P_TRANSFER,
  WALLET_CASH_IN,
  WALLET_CASH_OUT,
  CASH_BACK;

  public static final Map<String, String> PAYMENT_TYPES = new LinkedHashMap<>();
  public static final Map<String, String> RECONCILIATION = new LinkedHashMap<>();

  static {
    PAYMENT_TYPES.put(ServiceTypeEnum.FUND_IN.name(), ServiceTypeEnum.FUND_IN.name() );
    PAYMENT_TYPES.put(ServiceTypeEnum.FUND_OUT.name(), ServiceTypeEnum.FUND_OUT.name());
    //PAYMENT_TYPES.put(ServiceTypeEnum.FUND_TRANSFER.name(), ServiceTypeEnum.FUND_TRANSFER.name());
    //PAYMENT_TYPES.put(ServiceTypeEnum.TXN_REVERSAL.name(), ServiceTypeEnum.TXN_REVERSAL.name());
    //PAYMENT_TYPES.put(ServiceTypeEnum.TXN_DETAIL.name(), ServiceTypeEnum.TXN_DETAIL.name());
    //PAYMENT_TYPES.put(ServiceTypeEnum.BALANCE_INQUIRY.name(), ServiceTypeEnum.BALANCE_INQUIRY.name());
    PAYMENT_TYPES.put(ServiceTypeEnum.PHONE_TOPUP.name(), ServiceTypeEnum.PHONE_TOPUP.name());
    PAYMENT_TYPES.put(ServiceTypeEnum.EPIN.name(), ServiceTypeEnum.EPIN.name());
    PAYMENT_TYPES.put(ServiceTypeEnum.EXPORT_EPIN.name(), ServiceTypeEnum.EXPORT_EPIN.name());
    PAYMENT_TYPES.put(ServiceTypeEnum.BILL_PAYMENT.name(), ServiceTypeEnum.BILL_PAYMENT.name());
    //PAYMENT_TYPES.put(ServiceTypeEnum.DISBURSEMENT.name(), ServiceTypeEnum.DISBURSEMENT.name());
    //PAYMENT_TYPES.put(ServiceTypeEnum.SYSTEM_SETUP.name(), ServiceTypeEnum.SYSTEM_SETUP.name());
    //PAYMENT_TYPES.put(ServiceTypeEnum.WALLET_TRANSFER.name(), ServiceTypeEnum.WALLET_TRANSFER.name());
    //PAYMENT_TYPES.put(ServiceTypeEnum.REMITTANCE.name(), ServiceTypeEnum.REMITTANCE.name());
    //PAYMENT_TYPES.put(ServiceTypeEnum.SMS_CHARGE.name(), ServiceTypeEnum.SMS_CHARGE.name());
    //PAYMENT_TYPES.put(ServiceTypeEnum.P2P_TRANSFER.name(), ServiceTypeEnum.P2P_TRANSFER.name());
    PAYMENT_TYPES.put(ServiceTypeEnum.WALLET_CASH_IN.name(), ServiceTypeEnum.WALLET_CASH_IN.name());
    PAYMENT_TYPES.put(ServiceTypeEnum.WALLET_CASH_OUT.name(), ServiceTypeEnum.WALLET_CASH_OUT.name());
    PAYMENT_TYPES.put(ServiceTypeEnum.CASH_BACK.name(), ServiceTypeEnum.CASH_BACK.name());

    RECONCILIATION.put(ServiceTypeEnum.EPIN.name(), ServiceTypeEnum.EPIN.name());
    RECONCILIATION.put(ServiceTypeEnum.EXPORT_EPIN.name(), ServiceTypeEnum.EXPORT_EPIN.name());
    RECONCILIATION.put(ServiceTypeEnum.PHONE_TOPUP.name(), ServiceTypeEnum.PHONE_TOPUP.name());
    RECONCILIATION.put(ServiceTypeEnum.BILL_PAYMENT.name(), ServiceTypeEnum.BILL_PAYMENT.name());
  }
}
