package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

public class GetTrueServiceRequest extends GetTrueServiceRequestType {

  private static final long serialVersionUID = 1L;

  public GetTrueServiceRequest(Long serviceId) {
    this.serviceId = serviceId;
  }
}
