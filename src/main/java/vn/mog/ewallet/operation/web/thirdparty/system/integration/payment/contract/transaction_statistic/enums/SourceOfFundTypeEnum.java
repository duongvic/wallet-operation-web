package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums;

public enum SourceOfFundTypeEnum {
  ZPOINT, // Nguồn tiền từ Ví điện tử của khách hàng 
  BANK, // Nguồn tiền từ Tài khoản ngân hàng, thẻ ngân hàng nội địa
  CARD, // Nguồn tiền từ Thẻ Quốc tế
  LOYALTY_POINT; // Nguồn điểm thưởng

  public static SourceOfFundTypeEnum getItemType(String label) {
    for (SourceOfFundTypeEnum item : SourceOfFundTypeEnum.values()) {
      if (item.name().equalsIgnoreCase(label))
        return item;
    }
    return null;
  }
}
