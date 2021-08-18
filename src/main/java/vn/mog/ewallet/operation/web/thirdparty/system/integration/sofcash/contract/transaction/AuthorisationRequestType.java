package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.transaction;

import java.io.Serializable;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class AuthorisationRequestType extends MobiliserRequestType implements Serializable {
  private static final long serialVersionUID = 1L;

  protected String serviceId;

  protected Long payerPiId;

  protected Long payeePiId;

  protected Long amount;

  protected String orderInfo;

  protected Boolean autoCapture;

  protected String orderChannel;

  protected String orderId;

  public Long getPayeePiId() {
    return payeePiId;
  }

  public void setPayeePiId(Long payeePiId) {
    this.payeePiId = payeePiId;
  }

  public Long getPayerPiId() {
    return payerPiId;
  }

  public void setPayerPiId(Long payerPiId) {
    this.payerPiId = payerPiId;
  }

  public Long getAmount() {
    return this.amount;
  }

  public void setAmount(Long value) {
    this.amount = value;
  }

  public String getOrderId() {
    return this.orderId;
  }

  public void setOrderId(String value) {
    this.orderId = value;
  }

  public String getOrderInfo() {
    return orderInfo;
  }
  
  public void setOrderInfo(String orderInfo) {
    this.orderInfo = orderInfo;
  }

  public boolean isAutoCapture() {
    if (this.autoCapture == null) {
      return false;
    }
    return this.autoCapture.booleanValue();
  }

  public void setAutoCapture(Boolean value) {
    this.autoCapture = value;
  }

  public String getOrderChannel() {
    return this.orderChannel;
  }

  public void setOrderChannel(String value) {
    this.orderChannel = value;
  }

  public String getServiceId() {
    return this.serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }
}
