package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;


public class UpdateProviderRequestType extends MobiliserRequestType implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Long providerId;
  protected String name;
  protected Double rankingScore;
  protected Double rankingScoreMin;
  protected Double rankingScoreMax;

  private String customerTypeSupported;
  private Boolean specific;
  private String specificListCif;
  private String blackListCif;


  private String providerGroup;
  private String providerBizCode;
  private Integer rankingGroup;
  private Integer rankingLevel;

  public String getCustomerTypeSupported() {
    return customerTypeSupported;
  }

  public void setCustomerTypeSupported(String customerTypeSupported) {
    this.customerTypeSupported = customerTypeSupported;
  }

  public Boolean getSpecific() {
    return specific;
  }

  public void setSpecific(Boolean specific) {
    this.specific = specific;
  }

  public String getSpecificListCif() {
    return specificListCif;
  }

  public void setSpecificListCif(String specificListCif) {
    this.specificListCif = specificListCif;
  }

  public String getBlackListCif() {
    return blackListCif;
  }

  public void setBlackListCif(String blackListCif) {
    this.blackListCif = blackListCif;
  }

  public Long getProviderId() {
    return providerId;
  }

  public void setProviderId(Long providerId) {
    this.providerId = providerId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getProviderGroup() {
    return providerGroup;
  }

  public void setProviderGroup(String providerGroup) {
    this.providerGroup = providerGroup;
  }

  public String getProviderBizCode() {
    return providerBizCode;
  }

  public void setProviderBizCode(String providerBizCode) {
    this.providerBizCode = providerBizCode;
  }

  public Integer getRankingGroup() {
    return rankingGroup;
  }

  public void setRankingGroup(Integer rankingGroup) {
    this.rankingGroup = rankingGroup;
  }

  public Integer getRankingLevel() {
    return rankingLevel;
  }

  public void setRankingLevel(Integer rankingLevel) {
    this.rankingLevel = rankingLevel;
  }

}
