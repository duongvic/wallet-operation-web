package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrgUnit implements Serializable {

  private static final long serialVersionUID = 1L;
  protected String id;
  protected String name;
  protected String currency;
  protected String language;
  protected Long feeSetId;
  protected Long legalAddressId;
  protected Long limitSetId;
  protected String timeZone;
  protected Integer autoCancelAfterMinutes;
  protected BigDecimal vatPrc;
  protected String country;

  public String getId() {
    return this.id;
  }

  public void setId(String value) {
    this.id = value;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public String getCurrency() {
    return this.currency;
  }

  public void setCurrency(String value) {
    this.currency = value;
  }

  public String getLanguage() {
    return this.language;
  }

  public void setLanguage(String value) {
    this.language = value;
  }

  public Long getFeeSetId() {
    return this.feeSetId;
  }

  public void setFeeSetId(Long value) {
    this.feeSetId = value;
  }

  public Long getLegalAddressId() {
    return this.legalAddressId;
  }

  public void setLegalAddressId(Long value) {
    this.legalAddressId = value;
  }

  public Long getLimitSetId() {
    return this.limitSetId;
  }

  public void setLimitSetId(Long value) {
    this.limitSetId = value;
  }

  public String getTimeZone() {
    return this.timeZone;
  }

  public void setTimeZone(String value) {
    this.timeZone = value;
  }

  public Integer getAutoCancelAfterMinutes() {
    return this.autoCancelAfterMinutes;
  }

  public void setAutoCancelAfterMinutes(Integer value) {
    this.autoCancelAfterMinutes = value;
  }

  public BigDecimal getVatPrc() {
    return this.vatPrc;
  }

  public void setVatPrc(BigDecimal value) {
    this.vatPrc = value;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String value) {
    this.country = value;
  }
}
