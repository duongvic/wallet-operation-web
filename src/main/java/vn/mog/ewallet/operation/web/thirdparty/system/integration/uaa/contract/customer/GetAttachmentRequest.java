package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

import java.util.List;

public class GetAttachmentRequest extends GetAttachmentRequestType {

  private Long id;
  private Long CustomerId;
  private List<Integer> typeIds;

  public GetAttachmentRequest(List<Integer> typeIds) {
    this.typeIds = typeIds;
  }

  public GetAttachmentRequest() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return CustomerId;
  }

  public void setCustomerId(Long customerId) {
    CustomerId = customerId;
  }

  public List<Integer> getTypeIds() {
    return typeIds;
  }

  public void setTypeIds(List<Integer> typeIds) {
    this.typeIds = typeIds;
  }
}
