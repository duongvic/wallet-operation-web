package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@Data
public class SpecialProviderAllowedProvince implements Serializable {

  @ApiModelProperty(value = "Vùng, miền", example = "Miền Bắc")
  private String region;

  @ApiModelProperty(value = "Mã vùng miền", example = "Khu vực (Vimo)")
  private String regionCode;

  @ApiModelProperty(value = "Tên tỉnh", example = "Hà Nội")
  private String provinceName;

  @ApiModelProperty(value = "Mã điện", example = "PD")
  private String provinceElectricityCode;

  @ApiModelProperty(value = "Mã điện", example = "PD")
  private Boolean active = false;

}
