package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.beans.Occupation;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetAllOccupationResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private List<Occupation> occupations;

  public List<Occupation> getOccupations() {
    return occupations;
  }

  public void setOccupations(List<Occupation> occupations) {
    this.occupations = occupations;
  }
}
