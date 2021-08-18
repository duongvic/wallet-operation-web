package vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.bean.NotificationDTO;

@Getter
@Setter
public class FindPartnerNotificationResponse implements Serializable {

  private List<NotificationDTO> notificationDTOs;
  private Long total;
}
