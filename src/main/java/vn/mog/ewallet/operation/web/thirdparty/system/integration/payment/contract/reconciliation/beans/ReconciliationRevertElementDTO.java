package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.ServiceTypeEnum;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReconciliationRevertElementDTO implements Serializable {

  private static final long serialVersionUID = 1L;
  @ApiModelProperty(value = "Id")
  private Long id;

  @ApiModelProperty(notes = "reconciliationId", example = "0")
  private Long reconciliationId;

  @ApiModelProperty(notes = "type of the service", example = "PHONE_TOPUP")
  private ServiceTypeEnum serviceType;

  @ApiModelProperty(notes = "revert amount", example = "20000")
  private Long revertAmount;
}
