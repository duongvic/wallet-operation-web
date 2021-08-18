package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class BalanceInquiryRequestType extends MobiliserRequestType implements Serializable {

  private static final long serialVersionUID = 1L;
  protected long paymentInstrumentId;

  public long getPaymentInstrumentId() {
    return this.paymentInstrumentId;
  }

  public void setPaymentInstrumentId(long value) {
    this.paymentInstrumentId = value;
  }
}
