package vn.mog.ewallet.operation.web.controller.epin.beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EpinPurchaseOrderDetailTmp implements Serializable {

  private static final long serialVersionUID = 1L;

  private String cardType;
  private Integer faceValue;
  private Integer quantity;
  private String status;
  private Long mPOId;
  private Long cardStock;

  public EpinPurchaseOrderDetailTmp() {

  }

  public EpinPurchaseOrderDetailTmp(String cardType, Integer faceValue, Integer quantity, Long mPOId) {
    this.cardType = cardType;
    this.faceValue = faceValue;
    this.quantity = quantity;
    this.mPOId = mPOId;
  }

  // sort PO Detail items when return client
  @SuppressWarnings({"unchecked", "rawtypes"})
  public static void order(List<EpinPurchaseOrderDetailTmp> items) {

    Collections.sort(items, new Comparator() {

      public int compare(Object o1, Object o2) {
        // 1st sort by card type
        String x1 = ((EpinPurchaseOrderDetailTmp) o1).getCardType();
        String x2 = ((EpinPurchaseOrderDetailTmp) o2).getCardType();
        int sComp = x1.compareTo(x2);

        if (sComp != 0) {
          return sComp;
        } else {
          // 2st sort by face value
          Integer x3 = ((EpinPurchaseOrderDetailTmp) o1).getFaceValue();
          Integer x4 = ((EpinPurchaseOrderDetailTmp) o2).getFaceValue();
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

  public Long getMPOId() {
    return mPOId;
  }

  public void setMPOId(Long mPOId) {
    this.mPOId = mPOId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Long getmPOId() {
    return mPOId;
  }

  public void setmPOId(Long mPOId) {
    this.mPOId = mPOId;
  }

  public Long getCardStock() {
    return cardStock;
  }

  public void setCardStock(Long cardStock) {
    this.cardStock = cardStock;
  }
}