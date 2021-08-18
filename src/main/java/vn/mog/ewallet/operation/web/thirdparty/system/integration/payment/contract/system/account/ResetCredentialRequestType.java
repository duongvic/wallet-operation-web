package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class ResetCredentialRequestType extends MobiliserRequestType {

  protected Long customerId;
  protected Integer credentialType;
  protected Integer notificationMode;
  protected String remark;
//	protected String newCredential;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Integer getCredentialType() {
    return credentialType;
  }

  public void setCredentialType(Integer credentialType) {
    this.credentialType = credentialType;
  }

  public Integer getNotificationMode() {
    return notificationMode;
  }

  public void setNotificationMode(Integer notificationMode) {
    this.notificationMode = notificationMode;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

//	public String getNewCredential() {
//		return newCredential;
//	}

//	public void setNewCredential(String newCredential) {
//		this.newCredential = newCredential;
//	}


}
