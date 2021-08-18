package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans.TrueService;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class CreateTrueServiceRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected TrueService service;

  public TrueService getService() {
    return service;
  }

  public void setService(TrueService service) {
    this.service = service;
  }
}
