package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system;

import java.util.Collections;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.beans.Location;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindLocationResponseType extends MobiliserResponseType {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private List<Location> locations;

  public FindLocationResponseType() {
    super();
  }

  public List<Location> getLocations() {
    return locations == null ? Collections.emptyList() : locations;
  }

  public void setLocations(List<Location> locations) {
    this.locations = locations;
  }


}
