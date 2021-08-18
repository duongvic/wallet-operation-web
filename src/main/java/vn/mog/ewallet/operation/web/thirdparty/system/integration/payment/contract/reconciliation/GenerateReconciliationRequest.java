package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateReconciliationRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @ApiModelProperty(notes = "id of the customer")
  private Long customerId;

  @NotNull
  @ApiModelProperty(notes = "payment policy", example = "PAYMENT_POLICY_1_1")
  private PaymentPolicyEnum paymentPolicy;

  @NotNull
  @Min(2000)
  @Max(3000)
  @ApiModelProperty(notes = "year", example = "2021")
  private Integer year;

  @NotNull
  @Min(1)
  @Max(12)
  @ApiModelProperty(notes = "month", example = "6")
  private Integer month;

  @NotNull
  @Min(1)
  @Max(31)
  @ApiModelProperty(notes = "cycle", example = "1")
  private Integer cycle;
}
