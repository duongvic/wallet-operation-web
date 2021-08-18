package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import vn.mog.framework.contract.base.MobiliserResponseType;

import java.util.Map;


public class SummaryBalanceResponseType extends MobiliserResponseType {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  protected Map<String, Long> summary;
  
  public Map<String, Long> getSummary() {
    return summary;
  }
  
  public void setSummary(Map<String, Long> summary) {
    this.summary = summary;
  }
}
