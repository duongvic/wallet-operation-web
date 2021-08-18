package vn.mog.ewallet.operation.web.controller.balance.contract;

import java.io.Serializable;
import lombok.Data;

@Data
public class SummaryTransaction implements Serializable {

  private static final long serialVersionUID = 1L;

  private String fullName;
  private String msisdn;
  private String email;
  private Integer idCustomerType;
  private Long totalEpinAmount;
  private Long totalPhoneTopupAmount;
  private Long totalBillPaymentAmount;
  private Long totalDebit;

}
