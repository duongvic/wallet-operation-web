package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import java.io.Serializable;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class PhoneTopupTransactionOnHoldByBatchRequestType extends MobiliserRequestType
    implements Serializable {
  private static final long serialVersionUID = 1L;
  protected List<String> txnRefIds;
  protected String provider;
  protected String note;

  public List<String> getTxnRefIds() {
    return txnRefIds;
  }
  
  public void setTxnRefIds(List<String> txnRefIds) {
    this.txnRefIds = txnRefIds;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
