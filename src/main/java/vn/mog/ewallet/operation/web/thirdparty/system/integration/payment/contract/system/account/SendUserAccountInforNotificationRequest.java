package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

public class SendUserAccountInforNotificationRequest
    extends SendUserAccountInforNotificationRequestType {

  private static final long serialVersionUID = 1L;

  private Long customerId;

  public SendUserAccountInforNotificationRequest(Long customerId) {
    this.customerId = customerId;
  }

  public SendUserAccountInforNotificationRequest() {
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }
}
