package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.WalletTransferOrder;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.WalletTransferOrderAttachment;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetWalletTransferOrderResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private WalletTransferOrder order;
  private List<WalletTransferOrderAttachment> attachments;
  private Long payerCurrentBalance;
  private Long payeeCurrentBalance;

  public WalletTransferOrder getOrder() {
    if (order == null) {
      order = new WalletTransferOrder();
    }
    return order;
  }

  public void setOrder(WalletTransferOrder order) {
    this.order = order;
  }

  public List<WalletTransferOrderAttachment> getAttachments() {
    if (attachments == null) {
      attachments = new ArrayList<>();
    }
    for (WalletTransferOrderAttachment attachment : attachments) {
      attachment.setImageBase64(new Base64().encodeToString(attachment.getContent()));
    }
    return attachments;
  }

  public void setAttachments(List<WalletTransferOrderAttachment> attachments) {
    this.attachments = attachments;
  }

  public Long getPayerCurrentBalance() {
    return payerCurrentBalance;
  }

  public void setPayerCurrentBalance(Long payerCurrentBalance) {
    this.payerCurrentBalance = payerCurrentBalance;
  }

  public Long getPayeeCurrentBalance() {
    return payeeCurrentBalance;
  }

  public void setPayeeCurrentBalance(Long payeeCurrentBalance) {
    this.payeeCurrentBalance = payeeCurrentBalance;
  }
}
