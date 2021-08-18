package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.beans.Position;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetAllPositionResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private List<Position> positions;

  public List<Position> getPositions() {
    return positions;
  }

  public void setPositions(List<Position> positions) {
    this.positions = positions;
  }
}
