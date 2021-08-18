package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrgUnitList implements Serializable {

  private static final long serialVersionUID = 1L;
  protected List<String> orgUnit;

  public List<String> getOrgUnit() {
    if (this.orgUnit == null) {
      this.orgUnit = new ArrayList<String>();
    }
    return this.orgUnit;
  }
}
