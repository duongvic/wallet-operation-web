package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkflowActionRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "WorkFlowActionTypeEnum (Required)", example = "APPROVE")
  private WorkFlowActionTypeEnum workflowActionType;

  @ApiModelProperty(notes = "id of the entity (Required)")
  private Long entityId;

  @ApiModelProperty(notes = "id of the actor (Required)")
  private Long callerId;

  @ApiModelProperty(notes = "remark of the action", example = "OK")
  private String remark;
}
