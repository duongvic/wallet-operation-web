package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class CreatePaymentInstrumentResponseType extends MobiliserResponseType implements
    Serializable {

  private static final long serialVersionUID = 1L;
  protected Long paymentInstrumentId;

  public Long getPaymentInstrumentId() {
    return this.paymentInstrumentId;
  }

  public void setPaymentInstrumentId(Long value) {
    this.paymentInstrumentId = value;
  }
}
