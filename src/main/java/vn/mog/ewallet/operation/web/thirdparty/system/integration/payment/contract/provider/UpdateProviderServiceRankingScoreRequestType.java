package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateProviderServiceRankingScoreRequestType extends MobiliserRequestType
    implements Serializable {
  private static final long serialVersionUID = 1L;
  protected Long providerServiceId;
  protected Boolean enableRankingScore;
  protected Double rankingScore;
  protected Double rankingScoreMin;
  protected Double rankingScoreMax;
  protected Double rankingScorePlan;
  protected String remark;
  protected String description;

  public Long getProviderServiceId() {
    return providerServiceId;
  }

  public void setProviderServiceId(Long providerServiceId) {
    this.providerServiceId = providerServiceId;
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

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
