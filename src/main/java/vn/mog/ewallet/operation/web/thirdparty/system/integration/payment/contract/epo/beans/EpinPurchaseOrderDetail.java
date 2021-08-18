package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EpinPurchaseOrderDetail implements Serializable {

  private static final long serialVersionUID = 1L;

  private String cardType;
  private Integer faceValue;
  private Integer quantity;
  private Long purchaseOrderId;

  public EpinPurchaseOrderDetail() {
  }

  public EpinPurchaseOrderDetail(String cardType, Integer faceValue, Integer quantity, Long purchaseOrderId) {
    this.cardType = cardType;
    this.faceValue = faceValue;
    this.quantity = quantity;
    this.purchaseOrderId = purchaseOrderId;
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static void order(List<EpinPurchaseOrderDetail> items) {

    Collections.sort(items, new Comparator() {

      public int compare(Object o1, Object o2) {
        // 1st sort by card type
        String x1 = ((EpinPurchaseOrderDetail) o1).getCardType();
        String x2 = ((EpinPurchaseOrderDetail) o2).getCardType();
        int sComp = x1.compareTo(x2);

        if (sComp != 0) {
          return sComp;
        } else {
          // 2st sort by face value
          Integer x3 = ((EpinPurchaseOrderDetail) o1).getFaceValue();
          Integer x4 = ((EpinPurchaseOrderDetail) o2).getFaceValue();
          return x3.compareTo(x4);
        }
      }
    });
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public Integer getFaceValue() {
    return faceValue;
  }

  public void setFaceValue(Integer faceValue) {
    this.faceValue = faceValue;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }
}
