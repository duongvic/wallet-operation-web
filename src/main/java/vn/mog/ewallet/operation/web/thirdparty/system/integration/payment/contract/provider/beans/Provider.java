package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderCode;


@Data
@Getter
@Setter
@NoArgsConstructor
public class Provider implements Serializable{

  private static final long serialVersionUID = 1L;

  private Long id;

  private ProviderHealthy providerHealthy;
  
  private ProviderCode providerCode;

  private String providerBizCode;
  
  private String name;

  private String providerGroup;

  private Integer rankingGroup;

  private Integer rankingLevel;

  private Double rankingScore;

  private Double rankingScoreMin;

  private Double rankingScoreMax;

  private Boolean isSupportCheckBalance;

  private Double discount;
  
  private Long balance;

  private Boolean active;

  private Boolean hidden;

  private String customerTypeSupported;

  private Boolean specific;

  private String specificListCif;

  private String blackListCif;

  private List<ProviderService> services;

  private String description;

  private Date creationDate;

  private Date updateDate;
}