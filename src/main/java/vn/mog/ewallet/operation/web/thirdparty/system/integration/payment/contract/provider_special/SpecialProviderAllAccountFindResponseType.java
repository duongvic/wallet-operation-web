package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans.SpecialProviderAccount;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class SpecialProviderAllAccountFindResponseType extends MobiliserResponseType
    implements Serializable {
  private static final long serialVersionUID = 1L;

  private List<SpecialProviderAccount> specialProviderAccounts;
  private Long totalOfTotalAmount;
  private Integer total;
  private Integer all;

  public List<SpecialProviderAccount> getSpecialProviderAccounts() {
    if (specialProviderAccounts == null) {
      specialProviderAccounts = Collections.emptyList();
    }
    return specialProviderAccounts;
  }

  public void setSpecialProviderAccounts(List<SpecialProviderAccount> specialProviderAccounts) {
    this.specialProviderAccounts = specialProviderAccounts;
  }

  public Long getTotalOfTotalAmount() {
    return totalOfTotalAmount;
  }

  public void setTotalOfTotalAmount(Long totalOfTotalAmount) {
    this.totalOfTotalAmount = totalOfTotalAmount;
  }

  public Integer getTotal() {
    if (total == null) {
      total = 0;
    }
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public Integer getAll() {
    return all;
  }

  public void setAll(Integer all) {
    this.all = all;
  }
}
