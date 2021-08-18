package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProviderRankingLevel {
  LEVEL_1(1, "Level 1"), 
  LEVEL_2(2, "Level 2"), 
  LEVEL_3(3, "Level 3"),
  ;

  private Integer code;
  private String name;
}
