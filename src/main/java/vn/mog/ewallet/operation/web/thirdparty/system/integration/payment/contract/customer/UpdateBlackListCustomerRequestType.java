package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.beans.BlockType;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateBlackListCustomerRequestType extends MobiliserRequestType {
  // ---
  @JsonIgnore
  public static final String ADD = "ADD";
  @JsonIgnore
  public static final String DELETE = "DELETE";

  // ---
  private List<String> customerCifs;
  private BlockType blockType;
  private List<String> providerCodes; // Option - If BlockType.contain(PROVIDER_BLOCK) -> Mandatory
  private List<String> serviceCodes; // Option - If BlockType.contain(SERVICE_BLOCK) -> Mandatory
  private List<Long> providerServiceIds; // Option - If BlockType.contain(PROVIDER_SERVICE_BLOCK) -> Mandatory

  public List<String> getCustomerCifs() {
    return customerCifs;
  }

  public void setCustomerCifs(List<String> customerCifs) {
    this.customerCifs = customerCifs;
  }

  public BlockType getBlockType() {
    return blockType;
  }

  public void setBlockType(BlockType blockType) {
    this.blockType = blockType;
  }

  public List<String> getProviderCodes() {
    return providerCodes;
  }

  public void setProviderCodes(List<String> providerCodes) {
    this.providerCodes = providerCodes;
  }

  public List<String> getServiceCodes() {
    return serviceCodes;
  }

  public void setServiceCodes(List<String> serviceCodes) {
    this.serviceCodes = serviceCodes;
  }

  public List<Long> getProviderServiceIds() {
    return providerServiceIds;
  }

  public void setProviderServiceIds(List<Long> providerServiceIds) {
    this.providerServiceIds = providerServiceIds;
  }

  public String getCommand() {
    return null;
  }
}
