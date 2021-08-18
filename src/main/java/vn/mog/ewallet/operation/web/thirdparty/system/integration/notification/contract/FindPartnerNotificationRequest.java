package vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type.EventEnum;

@Getter
@Setter
public class FindPartnerNotificationRequest implements Serializable {

  private List<EventEnum> eventEnums;
  private String requestId;
  private Integer offset;
  private Integer limit;
  private String sortField;
  private Direction direction;
}
