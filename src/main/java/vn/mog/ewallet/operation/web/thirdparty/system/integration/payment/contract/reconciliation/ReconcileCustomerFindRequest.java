package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReconcileCustomerFindRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  private List<Long> customerIds;
  private String textSearch;
  private List<Integer> customerTypeIds;
  private Boolean isBalanceNotMatch;
  private Date fromDate;
  private Date endDate;
  private Integer offset;
  private Integer limit;
}
