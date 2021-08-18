package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

import java.util.Collections;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.CustomerAccessHistory;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.EntityEventStore;

@SuppressWarnings("serial")
public class FindEntityEventStoreByDateResponse extends FindEntityEventStoreByDateResponseType {

  private List<EntityEventStore> entityEventStores;
  private List<CustomerAccessHistory> accessHistorys;

  public List<EntityEventStore> getEntityEventStores() {
    return entityEventStores;
  }

  public void setEntityEventStores(List<EntityEventStore> entityEventStores) {
    this.entityEventStores = entityEventStores;
  }

  public List<CustomerAccessHistory> getAccessHistorys() {
    return accessHistorys != null ? accessHistorys : Collections.emptyList();
  }

  public void setAccessHistorys(List<CustomerAccessHistory> accessHistorys) {
    this.accessHistorys = accessHistorys;
  }

}
