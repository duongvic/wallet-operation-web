package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans;

import java.io.Serializable;
import java.util.Date;

public class TransactionReversalOrder implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  private Transaction transaction; // Transaction cần được reverse
  private Integer stage;
  private String approver;
  private String requestor;
  private String remark;
  private Date creationDate;
  private Long refReversalTransactionId; // Mã của Transaction (kết quả của Reversal Order)
  private Integer refReversalTransactionStatus; // Trạng thái của Transaction (kết quả của Reversal Order)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  public Integer getStage() {
    return stage;
  }

  public void setStage(Integer stage) {
    this.stage = stage;
  }

  public String getApprover() {
    return approver;
  }

  public void setApprover(String approver) {
    this.approver = approver;
  }

  public String getRequestor() {
    return requestor;
  }

  public void setRequestor(String requestor) {
    this.requestor = requestor;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Long getRefReversalTransactionId() {
    return refReversalTransactionId;
  }

  public void setRefReversalTransactionId(Long refReversalTransactionId) {
    this.refReversalTransactionId = refReversalTransactionId;
  }

  public Integer getRefReversalTransactionStatus() {
    return refReversalTransactionStatus;
  }

  public void setRefReversalTransactionStatus(Integer refReversalTransactionStatus) {
    this.refReversalTransactionStatus = refReversalTransactionStatus;
  }
}
