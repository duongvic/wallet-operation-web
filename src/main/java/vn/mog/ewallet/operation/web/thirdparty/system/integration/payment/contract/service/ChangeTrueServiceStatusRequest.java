package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

public class ChangeTrueServiceStatusRequest extends ChangeTrueServiceStatusRequestType {

  private static final long serialVersionUID = 1L;

  public ChangeTrueServiceStatusRequest() {

  }

  public ChangeTrueServiceStatusRequest(Long serviceId, Character active) {
    this.serviceId = serviceId;
    this.active = active;
  }

  public ChangeTrueServiceStatusRequest(Long serviceId, Character active, String remark) {
    this.serviceId = serviceId;
    this.active = active;
    this.remark = remark;
  }
}
