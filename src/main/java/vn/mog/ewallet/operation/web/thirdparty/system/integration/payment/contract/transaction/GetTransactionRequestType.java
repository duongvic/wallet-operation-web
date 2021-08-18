package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetTransactionRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected Long txnId;
  protected String traceNo;

  public String getTraceNo() {
    return traceNo;
  }

  public void setTraceNo(String traceNo) {
    this.traceNo = traceNo;
  }

  public Long getTxnId() {
    return txnId;
  }

  public void setTxnId(Long txnId) {
    this.txnId = txnId;
  }
}
