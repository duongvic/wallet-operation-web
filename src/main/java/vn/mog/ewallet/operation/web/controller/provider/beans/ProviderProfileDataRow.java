package vn.mog.ewallet.operation.web.controller.provider.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ProviderProfileDataRow implements Serializable {
  private static final long serialVersionUID = 1L;

  private String providerCode;
  private String providerName;
  private String serviceType;
  private String service;
  private String nameService;
  private Long faceValue;
  private Double discountRate;
  private Long discountAmount;
}
