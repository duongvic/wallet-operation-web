package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans;

import java.io.Serializable;
import java.util.Date;

public class TransactionAttribute implements Serializable {

  private static final long serialVersionUID = 1L;

  private String transactionAttributeType;
  private String transactionAttributeValue;
  private Long transactionId;

  private Date createdTime;
  private Long creatorId;
  private Date lastUpdatedTime;
  private Long lastUpdatedId;

  public TransactionAttribute() {
    super();
  }

  public String getTransactionAttributeType() {
    return transactionAttributeType;
  }

  public void setTransactionAttributeType(String transactionAttributeType) {
    this.transactionAttributeType = transactionAttributeType;
  }

  public String getTransactionAttributeValue() {
    return transactionAttributeValue;
  }

  public void setTransactionAttributeValue(String transactionAttributeValue) {
    this.transactionAttributeValue = transactionAttributeValue;
  }

  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Long getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Long creatorId) {
    this.creatorId = creatorId;
  }

  public Date getLastUpdatedTime() {
    return lastUpdatedTime;
  }

  public void setLastUpdatedTime(Date lastUpdatedTime) {
    this.lastUpdatedTime = lastUpdatedTime;
  }

  public Long getLastUpdatedId() {
    return lastUpdatedId;
  }

  public void setLastUpdatedId(Long lastUpdatedId) {
    this.lastUpdatedId = lastUpdatedId;
  }
}
