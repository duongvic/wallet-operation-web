package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetPaymentInstrumentsOfCustomerRequestType extends MobiliserRequestType implements
    Serializable {

  private static final long serialVersionUID = 1L;
  protected long customerId;
  protected boolean includeInactive;

  public long getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(long value) {
    this.customerId = value;
  }

  public boolean isIncludeInactive() {
    return this.includeInactive;
  }

  public void setIncludeInactive(boolean value) {
    this.includeInactive = value;
  }
}
