package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class FundOutRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected String requestId;
  protected Long fundOrderId;

  protected Long customerId;
  protected String remark;

  protected Long amount;
  protected String otp; // TODO

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public Long getFundOrderId() {
    return fundOrderId;
  }

  public void setFundOrderId(Long fundOrderId) {
    this.fundOrderId = fundOrderId;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }
}
