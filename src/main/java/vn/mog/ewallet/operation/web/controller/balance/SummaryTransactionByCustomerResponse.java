package vn.mog.ewallet.operation.web.controller.balance;


import lombok.Data;

import lombok.EqualsAndHashCode;
import vn.mog.ewallet.operation.web.controller.balance.contract.SummaryTransaction;
import vn.mog.framework.contract.base.MobiliserResponseType;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
public class SummaryTransactionByCustomerResponse extends MobiliserResponseType {

  private Collection<SummaryTransaction> transactions;
  private Integer totalRecords;
}
