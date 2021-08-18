package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.transaction;

import java.io.Serializable;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class AuthorisationCancelRequestType extends MobiliserRequestType implements Serializable {
  private static final long serialVersionUID = 1L;

  protected String orderChannel;
  
  protected String orderId;
  
  protected Long refTxnId;
  
  protected String orderInfo;

  protected String authCode;
  
  public String getOrderChannel() {
    return orderChannel;
  }
  
  public void setOrderChannel(String orderChannel) {
    this.orderChannel = orderChannel;
  }
  
  public String getOrderId() {
    return orderId;
  }
  
  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public Long getRefTxnId() {
    return refTxnId;
  }

  public void setRefTxnId(Long refTxnId) {
    this.refTxnId = refTxnId;
  }
  
  public String getOrderInfo() {
    return orderInfo;
  }
  
  public void setOrderInfo(String orderInfo) {
    this.orderInfo = orderInfo;
  }

  public String getAuthCode() {
    return authCode;
  }

  public void setAuthCode(String authCode) {
    this.authCode = authCode;
  }
}
