package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class PhoneTopupTransactionOnHoldRequestType extends MobiliserRequestType
    implements Serializable {

  public static final long serialVersionUID = 1L;
  protected Long txnId;
  protected String provider;
  protected String note;
  protected String telcoType;
  

  public Long getTxnId() {
    return txnId;
  }

  public void setTxnId(Long txnId) {
    this.txnId = txnId;
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

  public String getTelcoType() {
    return telcoType;
  }

  public void setTelcoType(String telcoType) {
    this.telcoType = telcoType;
  }
}
