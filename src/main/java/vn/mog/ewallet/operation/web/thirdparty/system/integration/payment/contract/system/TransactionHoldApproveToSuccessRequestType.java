package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class TransactionHoldApproveToSuccessRequestType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private Long txnId;
  private String remark;

  public Long getTxnId() {
    return txnId;
  }
  
  public void setTxnId(Long txnId) {
    this.txnId = txnId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
