package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans.TrueService;

public class CreateTrueServiceRequest extends CreateTrueServiceRequestType {

  private static final long serialVersionUID = 1L;

  public CreateTrueServiceRequest() {

  }

  public CreateTrueServiceRequest(TrueService service) {
    this.service = service;
  }
}
