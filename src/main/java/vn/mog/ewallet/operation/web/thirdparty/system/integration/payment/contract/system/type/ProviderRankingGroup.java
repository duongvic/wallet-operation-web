package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProviderRankingGroup {
  GROUP_A(1, "Group A"), 
  GROUP_B(2, "Group B"), 
  GROUP_C(3, "Group C"),
  ;

  private Integer code;
  private String name;
}
