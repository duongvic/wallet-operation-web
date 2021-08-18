package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer;

import java.util.Date;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.KycType;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class VerifyKycRequestFindRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String msisdn;
  private List<Integer> kycRequestStatusId;
  private Date fromDate;
  private Date toDate;
  private Integer offSet;
  private Integer limit;
  private List<KycType> kycTypes;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public List<Integer> getKycRequestStatusId() {
    return kycRequestStatusId;
  }

  public void setKycRequestStatusId(List<Integer> kycRequestStatusId) {
    this.kycRequestStatusId = kycRequestStatusId;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public Integer getOffSet() {
    return offSet;
  }

  public void setOffSet(Integer offSet) {
    this.offSet = offSet;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public List<KycType> getKycTypes() {
    return kycTypes;
  }

  public void setKycTypes(
      List<KycType> kycTypes) {
    this.kycTypes = kycTypes;
  }
}
