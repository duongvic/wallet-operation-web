package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

import java.util.LinkedHashMap;
import java.util.Map;

public enum SourceOfFundType {
  ZPOINT, // Nguồn tiền từ Ví điện tử của khách hàng
  ZCOIN, // Nguồn tiền từ Ví xu của khách hàng
  ZCREDIT, // Nguồn tiền từ Ví credit của khách hàng
  BANK, // Nguồn tiền từ Tài khoản ngân hàng, thẻ ngân hàng nội địa
  CARD, // Nguồn tiền từ Thẻ Quốc tế
  LOYALTY_POINT; // Nguồn điểm thưởng

  public static SourceOfFundType getSofType(String label) {
    for (SourceOfFundType item : SourceOfFundType.values()) {
      if (item.name().equalsIgnoreCase(label))
        return item;
    }
    return null;
  }

  public static final Map<String, String> SOF_ID_TYPES = new LinkedHashMap<>();

  static {
    SOF_ID_TYPES.put(SourceOfFundType.ZPOINT.name(), "Wallet");
    SOF_ID_TYPES.put(SourceOfFundType.ZCOIN.name(), "Xu");
    SOF_ID_TYPES.put(SourceOfFundType.ZCREDIT.name(), "Tín dụng");
    SOF_ID_TYPES.put(SourceOfFundType.BANK.name(), "Bank nội địa");
    SOF_ID_TYPES.put(SourceOfFundType.CARD.name(), "Thẻ quốc tế Visa/Master");
    SOF_ID_TYPES.put(SourceOfFundType.LOYALTY_POINT.name(), "LOYALTY POINT");

  }

}
