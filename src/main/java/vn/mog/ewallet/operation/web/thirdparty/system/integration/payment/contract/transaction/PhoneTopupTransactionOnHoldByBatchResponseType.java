package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import java.io.Serializable;
import java.util.Map;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class PhoneTopupTransactionOnHoldByBatchResponseType extends MobiliserResponseType
    implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected Map<String, Boolean> results;

  public Map<String, Boolean> getResults() {
    return results;
  }
  
  public void setResults(Map<String, Boolean> results) {
    this.results = results;
  }
}
