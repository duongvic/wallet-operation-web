package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans;

import java.io.Serializable;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.EventType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.SourceOfFundType;
import vn.mog.framework.common.utils.StringUtil;

public class TransactionRuleStep implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private int order;
  private EventType eventType;
  private SourceOfFundType sourceOfFundType;

  private String source;
  private Character isSourceCif;
  private String dest;
  private Character isDestCif;
  private Long transactionRuleId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  public SourceOfFundType getSourceOfFundType() {
    return sourceOfFundType;
  }

  public void setSourceOfFundType(SourceOfFundType sourceOfFundType) {
    this.sourceOfFundType = sourceOfFundType;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Character getIsSourceCif() {
    return isSourceCif;
  }

  public void setIsSourceCif(Character isSourceCif) {
    this.isSourceCif = isSourceCif;
  }

  public String getDest() {
    return dest;
  }

  public void setDest(String dest) {
    this.dest = dest;
  }

  public Character getIsDestCif() {
    return isDestCif;
  }

  public void setIsDestCif(Character isDestCif) {
    this.isDestCif = isDestCif;
  }

  public Long getTransactionRuleId() {
    return transactionRuleId;
  }

  public void setTransactionRuleId(Long transactionRuleId) {
    this.transactionRuleId = transactionRuleId;
  }

  public String getSourceMapWithTxnPayerOrPayee() {
    if (StringUtil.CHAR_YES.equals(isSourceCif)) {
      return StringUtil.S_NO;
    } else {
      return StringUtil.S_YES;
    }

  }

  public String getDestMapWithTxnPayerOrPayee() {
    if (StringUtil.CHAR_YES.equals(isDestCif)) {
      return StringUtil.S_NO;
    } else {
      return StringUtil.S_YES;
    }
  }
}
