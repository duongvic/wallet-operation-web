package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AttachmentDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String contentType;
  private byte[] content;
  private String imageBase64;
}
