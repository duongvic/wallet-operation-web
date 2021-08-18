package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.beans.CountAndSumBalance;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.beans.PaymentInstrument;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class SearchPaymentInstrumentResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = -5138766580115802598L;
  protected CountAndSumBalance summary;
  protected List<PaymentInstrument> paymentInstruments;

  public CountAndSumBalance getSummary() {
    return summary;
  }

  public void setSummary(CountAndSumBalance summary) {
    this.summary = summary;
  }

  public List<PaymentInstrument> getPaymentInstruments() {
    if (this.paymentInstruments == null) {
      this.paymentInstruments = new ArrayList<PaymentInstrument>();
    }
    return this.paymentInstruments;
  }

  public void setPaymentInstruments(List<PaymentInstrument> paymentInstruments) {
    this.paymentInstruments = paymentInstruments;
  }
}
