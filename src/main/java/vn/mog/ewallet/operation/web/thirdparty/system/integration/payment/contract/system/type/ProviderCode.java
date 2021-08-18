package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

import java.util.Arrays;
import java.util.List;

public enum ProviderCode  {
  /* SANDBOX PROVIDER: For Dev Env */
  UNKNOWN,
  ONEPAY,
  VTC,
  VNPAY,
  VINATOPUP,
  EPAY,
  SOFCASH_SERVICE,
  SOFCOIN_SERVICE,
  SOFCREDIT_SERVICE,
  FIVIPAY,
  TRUE_MONEY,
  LOGICPAY,
  SANDBOX,
  NAPAS,
  FECREDIT,
  HOMECREDIT,
  ONEWORLD,
  MY_BILL,
  WHYPAY,
  IPPAY,
  /*----------------------------- EPIN PROVIDER -------------------------------------------------*/
  VTC_EPIN,
  GARENA_EPIN,
  APPOTA_EPIN,
  VCCORP_EPIN,
  EPIN_IMEDIA,
  EPIN_OCTA,
  IOMEDIA_EPIN,
  CARD_STORE,
  CARD_STORE_N02,
  OFFLINE_CARD_STORE,
  /*----------------------------- TOPUP PROVIDER -------------------------------------------------*/
  PTU_IPPAY_SLOW,
  IOMEDIA_PTU,
  PTU_IMEDIA,
  PTU_CLING,
  PTU_TEPAYLINK_FAST,
  PTU_SIMBANK,
  PTU_3TMEDIA,
  PTU_NAMSON,
  PTU_BYHAND,
  PTU_FINVIET,
  PTU_OCTA,
  PTU_LUYTRELANG,
  PTU_LUYTRELANG_TKC,
  PTU_VIETTELTELECOM,
  PTU_C1_VIETTEL_KPP,
  PTU_NAPTHE24,
  PTU_G63BIZ,
  PTU_G63BIZ02,
  PTU_B1_G63BIZ,
  PTU_EPINSTORE_G63BIZ,
  PTU_ORDER_G63BIZ_20,
  PTU_SIMBANK02,
  PTU_VPS,
  PTU_VPS02,
  PTU_VINAPAY,
  PTU_ORDER_VINAPAY,
  PTU_ORDER_NAMSON,
  PTU_B3_EPINSTORE_TEPAYLINK,
  EXPORT_EPIN_VIETTELPAY,
  WALLET_VIETTELPAY,

  PTU_A0_SPECIAL,
  PTU_C0_SPECIAL,

  PTU_A1_SANDBOX,
  PTU_A2_SANDBOX,
  PTU_B1_SANDBOX,
  PTU_B2_SANDBOX,
  PTU_C1_SANDBOX,
  PTU_C2_SANDBOX,

  VCCORP_GTU,
  VTC_PTU,
  PTU_SIMBANK_ZTC,
  PTU_MOBILENET,
  /*----------------------------- BILL PROVIDER --------------------------------------------------*/
  BILL_BYHAND,

  BILL_A0_SPECIAL,
  BILL_C0_SPECIAL,

  BILL_A1_SANDBOX,
  BILL_A2_SANDBOX,
  BILL_B1_SANDBOX,
  BILL_B2_SANDBOX,
  BILL_C1_SANDBOX,
  BILL_C2_SANDBOX,

  BILL_SENPAY,
  BILL_SENPAY_TOOL,

  BILL_ELECTRIC_SENPAY,
  BILL_WATER_SENPAY,
  BILL_FINANCE_SENPAY,
  BILL_OTHER_SENPAY,

  BILL_ELECTRIC_SENPAY_TOOL,
  BILL_WATER_SENPAY_TOOL,
  BILL_FINANCE_SENPAY_TOOL,
  BILL_OTHER_SENPAY_TOOL,

  BILL_VIMO,
  BILL_ELECTRIC_VIMO,
  BILL_EPAY,
  BILL_FINANCE_EPAY,
  BILL_VIETTELPAY,
  BILL_ELECTRIC_VIETTELPAY,
  BILL_WATER_VIETTELPAY,
  BILL_FINANCE_VIETTELPAY,
  BILL_OTHER_VIETTELPAY,

  BILL_ZOTAPMS,
  BILL_IPPAY,
  /*----------------------------- CASHBACK PROVIDER --------------------------------------------------*/
  CASHBACK_BILL_SANDBOX,
  CASHBACK_BILL_SENPAY,
  ;

  public static List<ProviderCode> PAYMENT_PROVIDER_CODES = Arrays.asList(ProviderCode.values());

  public static ProviderCode getAvailableProviderCode(String label) {
    for (ProviderCode item : ProviderCode.values()) {
      if (item.name().equals(label.trim())) {
        return item;
      }
    }
    return null;
  }
}
