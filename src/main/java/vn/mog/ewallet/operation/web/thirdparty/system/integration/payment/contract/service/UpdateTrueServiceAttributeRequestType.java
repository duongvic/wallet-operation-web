package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans.TrueServiceAttribute;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateTrueServiceAttributeRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long trueServiceId;
  protected List<TrueServiceAttribute> attributes;

  public Long getTrueServiceId() {
    return trueServiceId;
  }

  public void setTrueServiceId(Long trueServiceId) {
    this.trueServiceId = trueServiceId;
  }

  public List<TrueServiceAttribute> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<TrueServiceAttribute> attributes) {
    this.attributes = attributes;
  }
}
