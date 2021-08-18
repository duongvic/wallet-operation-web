package vn.mog.ewallet.operation.web.thirdparty.system.integration.stock;


import java.io.Serializable;
import lombok.Data;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.beans.BankCode;

@Data
public class BankAccount implements Serializable {

  private Long id;
  private BankCode bankCode;
  private String bankAccount;
  private String bankAccountName;
}
