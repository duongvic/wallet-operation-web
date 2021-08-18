package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.transaction;

import java.io.Serializable;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class CaptureResponseType extends MobiliserResponseType implements Serializable {
  private static final long serialVersionUID = 1L;

  protected String orderId;

  protected Long txnId;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public Long getTxnId() {
    return txnId;
  }

  public void setTxnId(Long txnId) {
    this.txnId = txnId;
  }
}
