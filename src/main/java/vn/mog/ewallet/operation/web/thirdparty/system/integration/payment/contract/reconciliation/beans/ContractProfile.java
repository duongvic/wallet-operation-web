package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.PaymentPolicyEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractProfile {
  private String profileCode;
  private Long customerId;
  private String customerName;
  private String email;
  private String phone;
  private String address;
  private String identity;
  private String identityImage;
  private String contractNo; // Hợp đồng số
  private String contractType; // Loại hợp đồng
  private String cif;
  private Boolean bolOfficial;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd HH:mm:ss Z",
      timezone = "Asia/Ho_Chi_Minh")
  private Date contractDate; // Ngày ký Hợp đồng

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd HH:mm:ss Z",
      timezone = "Asia/Ho_Chi_Minh")
  private Date expiredDate; // Ngay het hieu luc

  private PaymentPolicyEnum paymentPolicy;
  // ----
}
