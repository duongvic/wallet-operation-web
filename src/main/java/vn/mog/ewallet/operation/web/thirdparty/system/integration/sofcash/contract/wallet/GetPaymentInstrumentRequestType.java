package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetPaymentInstrumentRequestType extends MobiliserRequestType implements Serializable {

  private static final long serialVersionUID = 1L;
  protected long paymentInstrumentId;
  protected boolean includeInactive;

  public long getPaymentInstrumentId() {
    return this.paymentInstrumentId;
  }

  public void setPaymentInstrumentId(long value) {
    this.paymentInstrumentId = value;
  }

  public boolean isIncludeInactive() {
    return this.includeInactive;
  }

  public void setIncludeInactive(boolean value) {
    this.includeInactive = value;
  }
}
