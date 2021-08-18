package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

import java.util.LinkedHashMap;
import java.util.Map;


public class ServiceAttributeType {

  public static final String TELCO_TYPE = "TELCO_TYPE";
  public static final transient String TELCO_FACE_VALUE = "TELCO_FACE_VALUE";
  public static final String TELCO_SERVICE_TYPE = "TELCO_SERVICE_TYPE";
  public static final String ORDER_CHANNEL = "ORDER_CHANNEL";
  public static final String BANK_CODE = "BANK_CODE";
  public static final String SOURCE_OF_FUND = "SOURCE_OF_FUND";
  public static final String PARTIAL_PAYMENT_ALLOWED = "PARTIAL_PAYMENT_ALLOWED";
  public static final String PREPAID_BILL_ALLOWED = "PREPAID_BILL_ALLOWED";
  public static final String PAID_TIMES_ONE_BILL_PER_MONTH = "PAID_TIMES_ONE_BILL_PER_MONTH";
  public static final String PAID_TIMES_ONE_BILL_PER_DAY = "PAID_TIMES_ONE_BILL_PER_DAY";
  public static final String WALLET_CODE = "WALLET_CODE";
  public static final transient String GAME_TYPE = "GAME_TYPE";
  public static final transient String GAME_FACE_VALUE = "GAME_FACE_VALUE";
  public static final  String SUB_SERVICE_TYPE = "SUB_SERVICE_TYPE";
  public static final  String SERVICE_ATTRIBUTE_01 = "SERVICE_ATTRIBUTE_01";
  public static final  String SERVICE_ATTRIBUTE_02 = "SERVICE_ATTRIBUTE_02";
  public static final  String SERVICE_ATTRIBUTE_03 = "SERVICE_ATTRIBUTE_03";

  public static final Map<String, String> SERVICE_ATTRIBUTE_TYPES = new LinkedHashMap<>();

  static {
    SERVICE_ATTRIBUTE_TYPES.put(TELCO_TYPE, "Telco Type");
    SERVICE_ATTRIBUTE_TYPES.put(TELCO_FACE_VALUE, "Telco Face Value");
    SERVICE_ATTRIBUTE_TYPES.put(TELCO_SERVICE_TYPE, "Telco Service Type");
    SERVICE_ATTRIBUTE_TYPES.put(ORDER_CHANNEL, "Order Channel");
    SERVICE_ATTRIBUTE_TYPES.put(BANK_CODE, "Bank Code");
    SERVICE_ATTRIBUTE_TYPES.put(SOURCE_OF_FUND, "Source Of Fund");
    SERVICE_ATTRIBUTE_TYPES.put(PARTIAL_PAYMENT_ALLOWED, "Partial Payment Allowed");
    SERVICE_ATTRIBUTE_TYPES.put(PREPAID_BILL_ALLOWED, "Prepaid Bill Allowed");
    SERVICE_ATTRIBUTE_TYPES.put(PAID_TIMES_ONE_BILL_PER_DAY, "Paid Times One Bill Per Day Allowed");
    SERVICE_ATTRIBUTE_TYPES.put(PAID_TIMES_ONE_BILL_PER_MONTH, "Paid Times One Bill Per Month Allowed");
    SERVICE_ATTRIBUTE_TYPES.put(WALLET_CODE, "Wallet Code");
    SERVICE_ATTRIBUTE_TYPES.put(GAME_TYPE, "Game type");
    SERVICE_ATTRIBUTE_TYPES.put(GAME_FACE_VALUE, "Game face value");
    SERVICE_ATTRIBUTE_TYPES.put(SUB_SERVICE_TYPE , "Loại dịch vụ con");
    SERVICE_ATTRIBUTE_TYPES.put(SERVICE_ATTRIBUTE_01, "Thuộc tính dịch vụ 01");
    SERVICE_ATTRIBUTE_TYPES.put(SERVICE_ATTRIBUTE_02, "Thuộc tính dịch vụ 02");
    SERVICE_ATTRIBUTE_TYPES.put(SERVICE_ATTRIBUTE_03, "Thuộc tính dịch vụ 03");

  }
}
