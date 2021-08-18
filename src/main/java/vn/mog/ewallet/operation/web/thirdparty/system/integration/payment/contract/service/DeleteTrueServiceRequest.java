package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

public class DeleteTrueServiceRequest extends DeleteTrueServiceRequestType {

  private static final long serialVersionUID = 1L;

  public DeleteTrueServiceRequest() {

  }

  public DeleteTrueServiceRequest(Long serviceId) {
    this.serviceId = serviceId;
  }
}
