package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.PaymentPolicyEnum;
import vn.mog.framework.common.utils.Utils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "Customter Id")
  private Long customerId;

  @ApiModelProperty(value = "Customter Name")
  private String customerName;

  @ApiModelProperty(value = "Customter CIF")
  private String cif;

  @ApiModelProperty(value = "Customter Email")
  private String email;

  @ApiModelProperty(value = "Customter Phone")
  private String phone;

  @ApiModelProperty(value = "Contract No")
  private String contractNo;

  @ApiModelProperty(value = "Contract Type")
  private String contractType;

  @ApiModelProperty(value = "Service Type")
  private String serviceTypes;

  @ApiModelProperty(value = "Start date of Contract")
  private Date contractDate;

  @ApiModelProperty(value = "End date of Contract")
  private Date expiredDate;

  @ApiModelProperty(value = "Set of payment policy profiles")
  private Set<ProfilePaymentPolicyDTO> profilePaymentPolicies;

  public int getDayBetween() {
    try {
      Date dt = new Date();
      return Utils.daysBetween(dt, expiredDate);
    } catch (Exception e) {
    }
    return 0;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class ProfilePaymentPolicyDTO implements Serializable {

    @ApiModelProperty(value = "Is official payment policy")
    private Boolean bolOfficial;

    @ApiModelProperty(value = "Payment policy")
    private PaymentPolicyEnum paymentPolicy;
  }
}
