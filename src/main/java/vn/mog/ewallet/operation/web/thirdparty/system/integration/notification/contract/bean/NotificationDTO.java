package vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type.EventEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type.ServiceEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type.ServiceTypeEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NotificationDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  //@ApiModelProperty(notes = "RequestId  of the notification", example = "641ea790-50c6-11eb-ae93-0242ac130002")
  private String requestId;

  //@ApiModelProperty(notes = "Event of the notification", example = "MAINTAIN_START")
  private EventEnum event;

  //@ApiModelProperty(notes = "ServiceType of the notification", example = "BILL_PAYMENT")
  private ServiceTypeEnum serviceType;

  //@ApiModelProperty(notes = "Service of the notification", example = "BILL_ELECTRIC")
  private ServiceEnum service;

  //@ApiModelProperty(notes = "Detail name service of the notification", example = "Điện Hà Nội")
  private String serviceDetailName;

  //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "GMT+7")
  //@ApiModelProperty(value = "fromDate (Optional)", example = "2020-01-01T00:00:00+0700")
  private Date fromDate;

  //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "GMT+7")
  //@ApiModelProperty(required = false, value = "endDate (Optional)", example = "2021-01-01T00:00:00+0700")
  private Date toDate;

  //@ApiModelProperty(value = "Subject of notification", example = "Bảo trì hệ thống")
  private String subject;

  //@ApiModelProperty(value = "Content of notification", example = "Dịch vụ hóa đơn điện sẽ được bảo trì ...")
  private String content;

  //@ApiModelProperty(value = "Signature", example = "SHA256(|requestId|event|serviceType|service|)")
  private String signature;

  private Date requestTime;

  private String refRequestId;

//  public String getDataSign() {
//    StringBuffer dataSign = new StringBuffer(SharedConstants.VERTICAL_BAR);
//    dataSign.append(this.requestId).append(SharedConstants.VERTICAL_BAR);
//    dataSign.append(this.event.getCode()).append(SharedConstants.VERTICAL_BAR);
//    dataSign.append(this.serviceType.getCode()).append(SharedConstants.VERTICAL_BAR);
//    dataSign.append(this.service.getCode()).append(SharedConstants.VERTICAL_BAR);
//    return dataSign.toString();
//  }
}
