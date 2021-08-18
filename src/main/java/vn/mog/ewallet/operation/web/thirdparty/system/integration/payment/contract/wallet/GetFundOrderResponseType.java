package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrder;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrderAttachment;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetFundOrderResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private FundOrder fundOrder;
  private List<FundOrderAttachment> attachments;
  private Long customerCurrentBalance;
  private String bankCodeSOF;

  public FundOrder getFundOrder() {
    return fundOrder;
  }

  public void setFundOrder(FundOrder fundOrder) {
    this.fundOrder = fundOrder;
  }

  public List<FundOrderAttachment> getAttachments() {
    if (attachments == null) {
      attachments = new ArrayList<>();
    }
    for (FundOrderAttachment attachment : attachments) {
      attachment.setImageBase64(new Base64().encodeToString(attachment.getContent()));
    }
    return attachments;
  }

  public void setAttachments(List<FundOrderAttachment> attachments) {
    this.attachments = attachments;
  }

  public Long getCustomerCurrentBalance() {
    return customerCurrentBalance;
  }

  public void setCustomerCurrentBalance(Long customerCurrentBalance) {
    this.customerCurrentBalance = customerCurrentBalance;
  }

  public String getBankCodeSOF() {
    return bankCodeSOF;
  }

  public void setBankCodeSOF(String bankCodeSOF) {
    this.bankCodeSOF = bankCodeSOF;
  }
}
