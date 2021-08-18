package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

public class GetTrueServiceOperationRequest extends GetTrueServiceOperationRequestType {

  private static final long serialVersionUID = 1L;

  public GetTrueServiceOperationRequest(String serviceName) {
    this.serviceName = serviceName;
  }
}
