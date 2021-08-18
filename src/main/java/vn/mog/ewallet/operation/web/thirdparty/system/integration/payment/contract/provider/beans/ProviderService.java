package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans;

import java.io.Serializable;
import java.util.Date;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;

public class ProviderService implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private Provider provider;

  private String serviceCode;

  private ServiceType serviceType;

  private String serviceName;

  private String serviceDesc;

  private String servicePrices;

  private Boolean active;

  private Date creationDate;

  private Date updateDate;

  private Boolean enableRankingScore;

  private Double rankingScore;

  private Double rankingScoreMin;

  private Double rankingScoreMax;

  private Double rankingScorePlan;

  private String description;

  private String blackListCif;

  private String customerTypeSupported;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Provider getProvider() {
    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceDesc() {
    return serviceDesc;
  }

  public void setServiceDesc(String serviceDesc) {
    this.serviceDesc = serviceDesc;
  }

  public String getServicePrices() {
    return servicePrices;
  }

  public void setServicePrices(String servicePrices) {
    this.servicePrices = servicePrices;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean getEnableRankingScore() {
    return enableRankingScore;
  }

  public void setEnableRankingScore(Boolean enableRankingScore) {
    this.enableRankingScore = enableRankingScore;
  }

  public Double getRankingScore() {
    return rankingScore;
  }

  public void setRankingScore(Double rankingScore) {
    this.rankingScore = rankingScore;
  }

  public Double getRankingScoreMin() {
    return rankingScoreMin;
  }

  public void setRankingScoreMin(Double rankingScoreMin) {
    this.rankingScoreMin = rankingScoreMin;
  }

  public Double getRankingScoreMax() {
    return rankingScoreMax;
  }

  public void setRankingScoreMax(Double rankingScoreMax) {
    this.rankingScoreMax = rankingScoreMax;
  }

  public Double getRankingScorePlan() {
    return rankingScorePlan;
  }

  public void setRankingScorePlan(Double rankingScorePlan) {
    this.rankingScorePlan = rankingScorePlan;
  }

  public Integer getRankingScoreInt() {
    return rankingScore != null ? rankingScore.intValue() : 0;
  }

  public Integer getRankingScoreMaxInt() {
    return rankingScoreMax != null ? rankingScoreMax.intValue() : 0;
  }

  public Integer getRankingScoreMinInt() {
    return rankingScoreMin != null ? rankingScoreMin.intValue() : 0;
  }

  public Integer getRankingScorePlanInt() {
    return rankingScorePlan != null ? rankingScorePlan.intValue() : 0;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getBlackListCif() {
    return blackListCif;
  }

  public void setBlackListCif(String blackListCif) {
    this.blackListCif = blackListCif;
  }

  public String getCustomerTypeSupported() {
    return customerTypeSupported;
  }

  public void setCustomerTypeSupported(String customerTypeSupported) {
    this.customerTypeSupported = customerTypeSupported;
  }
}
