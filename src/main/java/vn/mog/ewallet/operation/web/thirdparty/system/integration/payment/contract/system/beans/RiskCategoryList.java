package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RiskCategoryList implements Serializable {

  private static final long serialVersionUID = 1L;

  protected List<Integer> riskCategory;

  public List<Integer> getRiskCategory() {
    if (this.riskCategory == null) {
      this.riskCategory = new ArrayList<Integer>();
    }
    return this.riskCategory;
  }
}
