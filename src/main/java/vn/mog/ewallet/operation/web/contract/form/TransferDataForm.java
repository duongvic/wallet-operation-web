package vn.mog.ewallet.operation.web.contract.form;

import org.apache.commons.lang3.StringUtils;
import vn.mog.ewallet.operation.web.controller.transfer.AbstractTransferController;
import vn.mog.ewallet.operation.web.controller.transfer.FundinSofController;
import vn.mog.ewallet.operation.web.controller.transfer.WalletTransferController;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.framework.common.utils.NumberUtil;

public class TransferDataForm {

  private String action;
  private long orderId;
  private int orderStage;
  private String remark;
  private String transferType;

  public static String redirectTransferUri(String transferType) {
    return "redirect:" + (ServiceType.FUND_TRANSFER.name().equals(transferType) ?
        FundinSofController.FUND_SOF_REQUEST_LIST :
        WalletTransferController.TRANSFER_WALLET_REQUEST_LIST);
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = (remark != null) ? remark.trim() : StringUtils.EMPTY;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public long getOrderId() {
    return orderId;
  }

  /**
   * input data is string
   */
  public void setOrderId(String orderId) {
    this.orderId = NumberUtil.convertToLong(orderId);
  }

  public int getOrderStage() {
    return orderStage;
  }

  /**
   * input data is string
   */
  public void setOrderStage(String orderStage) {
    this.orderStage = NumberUtil.convertToInt(orderStage);
  }

  public String getTransferType() {
    return transferType;
  }

  public void setTransferType(String transferType) {
    this.transferType = transferType;
  }

  public String redirectTransferUri() {
    return "redirect:" + (ServiceType.FUND_TRANSFER.name().equals(this.transferType) ?
        FundinSofController.FUND_SOF_REQUEST_LIST :
        WalletTransferController.TRANSFER_WALLET_REQUEST_LIST);
  }

  public String renderStepOneRequest() {
    return ServiceType.FUND_TRANSFER.name().equals(transferType) ?
        AbstractTransferController.RENDER_PAGE_FUNDIN_SOF_STEP_ONE_REQUEST :
        AbstractTransferController.RENDER_PAGE_TRANSFER_WALLET_STEP_ONE_REQUEST;
  }
}
