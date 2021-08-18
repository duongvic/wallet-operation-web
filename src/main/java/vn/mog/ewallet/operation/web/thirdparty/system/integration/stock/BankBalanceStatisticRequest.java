package vn.mog.ewallet.operation.web.thirdparty.system.integration.stock;


import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.beans.BankCode;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Data
@EqualsAndHashCode(callSuper = true)
public class BankBalanceStatisticRequest extends MobiliserRequestType {

  private String textSearch;
  private List<BankCode> bankCodes;
  private List<String> bankAccounts;
  private Date beginDatePeriod;
  private Date endDatePeriod;

  private int offset;
  private int limit;
}
