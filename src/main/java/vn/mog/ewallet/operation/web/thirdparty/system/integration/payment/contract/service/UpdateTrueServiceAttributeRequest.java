package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans.TrueServiceAttribute;

public class UpdateTrueServiceAttributeRequest extends UpdateTrueServiceAttributeRequestType {

  private static final long serialVersionUID = 1L;

  public UpdateTrueServiceAttributeRequest(Long trueServiceId, List<TrueServiceAttribute> attributes) {
    this.trueServiceId = trueServiceId;
    this.attributes = attributes;
  }

  public UpdateTrueServiceAttributeRequest() {
  }
}
