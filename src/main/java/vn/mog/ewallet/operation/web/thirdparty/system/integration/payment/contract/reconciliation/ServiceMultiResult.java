package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceMultiResult<T> implements Serializable {

  @ApiModelProperty(value = "Total")
  private Integer total;

  @ApiModelProperty(value = "Result Set")
  private List<T> list;

  @JsonIgnore
  public int resultSize() {
    return Optional.ofNullable(list).orElse(Collections.emptyList()).size();
  }
}
