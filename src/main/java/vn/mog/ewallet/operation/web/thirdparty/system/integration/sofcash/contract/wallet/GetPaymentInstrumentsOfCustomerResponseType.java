package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.beans.PaymentInstrument;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetPaymentInstrumentsOfCustomerResponseType extends MobiliserResponseType implements
    Serializable {

  private static final long serialVersionUID = 1L;

  protected List<PaymentInstrument> paymentInstruments;

  public List<PaymentInstrument> getPaymentInstruments() {
    if (this.paymentInstruments == null) {
      this.paymentInstruments = new ArrayList<PaymentInstrument>();
    }
    return this.paymentInstruments;
  }
}
