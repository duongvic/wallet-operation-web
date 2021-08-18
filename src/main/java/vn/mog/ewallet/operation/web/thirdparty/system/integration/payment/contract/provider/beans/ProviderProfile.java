package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class ProviderProfile implements Serializable {

  private static final long serialVersionUID = 1L;
  private String profileCode;
  private String providerCode;
  private String providerName;
  private String providerDesc;

  private String contractNo = "";// Hợp đồng số
  private Date contractDate = new Date();// Ngày ký Hợp đồng
  private String contractType = "A_B2B";
  private int status = 0;
  private Date enableDate;
  private Date expiredDate;

  /*
   * Map<Loại Service, Map<Loại thẻ, Map <Mệnh giá bắt đầu, Chiết khấu theo tỉ lệ>>>
   */
  private Map<String, Map<String, Map<Long, Double>>> discountRates;

  /*
   * Map<Loại Service, Map<Service Code, Map <Mệnh giá bắt đầu, Chiết khấu theo giá trị>>>
   */
  private Map<String, Map<String, Map<Long, Long>>> discountFixedAmounts;

  public String getProfileCode() {
    return profileCode;
  }

  public void setProfileCode(String profileCode) {
    this.profileCode = profileCode;
  }

  public String getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(String providerCode) {
    this.providerCode = providerCode;
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public String getProviderDesc() {
    return providerDesc;
  }

  public void setProviderDesc(String providerDesc) {
    this.providerDesc = providerDesc;
  }

  public String getContractNo() {
    return contractNo;
  }

  public void setContractNo(String contractNo) {
    this.contractNo = contractNo;
  }

  public Date getContractDate() {
    return contractDate;
  }

  public void setContractDate(Date contractDate) {
    this.contractDate = contractDate;
  }

  public String getContractType() {
    return contractType;
  }

  public void setContractType(String contractType) {
    this.contractType = contractType;
  }

  public Map<String, Map<String, Map<Long, Double>>> getDiscountRates() {
    return discountRates;
  }

  public void setDiscountRates(Map<String, Map<String, Map<Long, Double>>> discountRates) {
    this.discountRates = discountRates;
  }

  public Map<String, Map<String, Map<Long, Long>>> getDiscountFixedAmounts() {
    return discountFixedAmounts;
  }

  public void setDiscountFixedAmounts(
      Map<String, Map<String, Map<Long, Long>>> discountFixedAmounts) {
    this.discountFixedAmounts = discountFixedAmounts;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Date getEnableDate() {
    return enableDate;
  }

  public void setEnableDate(Date enableDate) {
    this.enableDate = enableDate;
  }

  public Date getExpiredDate() {
    return expiredDate;
  }

  public void setExpiredDate(Date expiredDate) {
    this.expiredDate = expiredDate;
  }
}
