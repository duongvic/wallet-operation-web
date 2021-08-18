package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet;

import java.io.Serializable;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.beans.PaymentInstrument;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetBestPaymentInstrumentOfCustomerResponseType extends MobiliserResponseType implements Serializable {

  private static final long serialVersionUID = 1L;

  protected PaymentInstrument paymentInstrument;

  public PaymentInstrument getPaymentInstrument() {
    return this.paymentInstrument;
  }

  public void setPaymentInstrument(PaymentInstrument value) {
    this.paymentInstrument = value;
  }
}
