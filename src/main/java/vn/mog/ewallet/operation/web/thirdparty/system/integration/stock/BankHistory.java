package vn.mog.ewallet.operation.web.thirdparty.system.integration.stock;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.beans.BankCode;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.beans.TransactionType;

@Data
public class BankHistory implements Serializable {

  private Long id;
  private BankCode bankCode;
  private String bankAccount;
  private String bankRefTxn;
  private Date txnDate;
  private Long amount = 0L;
  private Long postBalance = 0L;
  private TransactionType txnType;
  private String info;
  private Date createdTime;
  private Date lastUpdatedTime;

}
