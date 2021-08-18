package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;

@Data
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaseSearchForm {
  @ApiModelProperty(
      notes = "Dữ liệu trả về muốn sắp xếp theo trường thông tin này",
      allowableValues = "creationDate, id",
      example = "creationDate, id")
  private String sortField = "creationDate";

  @ApiModelProperty(
      notes = "Loại hình sắp xếp tăng dần, giảm dần",
      allowableValues = "ASC,DESC",
      example = "ASC")
  private Direction direction = Direction.DESC;

  @ApiModelProperty(value = "page number, minimum is 1", example = "1")
  @Min(value = 1, message = "The page number size cannot be less than 1")
  private Integer page = 1;

  @ApiModelProperty(value = "page size", example = "15")
  @Min(value = 0, message = "The number of pages cannot be less than 0")
  private Integer pageSize = 15;
}
