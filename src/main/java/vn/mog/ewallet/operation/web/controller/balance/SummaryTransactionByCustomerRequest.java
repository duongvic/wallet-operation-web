package vn.mog.ewallet.operation.web.controller.balance;

import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Data
@EqualsAndHashCode(callSuper = true)
public class SummaryTransactionByCustomerRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private List<Long> customerIds;
  private Date fromDate;
  private Date toDate;

  private Integer offset;
  private Integer limit;
}
