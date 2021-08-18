package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans;

import java.util.Date;

public class CustomerTransactionBean {

  private Long TxnId;
  private String refOrderId;
  private Long amount;
  private String service;
  private Integer errorCodeId;

  private Date excutionTime;

  public Long getTxnId() {
    return TxnId;
  }

  public void setTxnId(Long txnId) {
    TxnId = txnId;
  }

  public String getRefOrderId() {
    return refOrderId;
  }

  public void setRefOrderId(String refOrderId) {
    this.refOrderId = refOrderId;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public Integer getErrorCodeId() {
    return errorCodeId;
  }

  public void setErrorCodeId(Integer errorCodeId) {
    this.errorCodeId = errorCodeId;
  }

  public Date getExcutionTime() {
    return excutionTime;
  }

  public void setExcutionTime(Date excutionTime) {
    this.excutionTime = excutionTime;
  }

}
