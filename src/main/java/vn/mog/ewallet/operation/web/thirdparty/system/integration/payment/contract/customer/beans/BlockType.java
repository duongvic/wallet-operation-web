package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.beans;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public enum BlockType {

  SERVICE_BLOCK,
  PROVIDER_BLOCK,
  PROVIDER_SERVICE_BLOCK;

  public static final Map<String, String> TAB_BLOCK_ALL= new LinkedHashMap<>();
  public static final Map<String, String> TAB_BLOCK_PROVIDER = new LinkedHashMap<>();
  public static final Map<String, String> TAB_BLOCK_SERVICE = new LinkedHashMap<>();

  static {
    TAB_BLOCK_ALL.put(BlockType.SERVICE_BLOCK.name(), "SERVICE BLOCK");
    TAB_BLOCK_ALL.put(BlockType.PROVIDER_BLOCK.name(), "PROVIDER BLOCK");
    TAB_BLOCK_ALL.put(BlockType.PROVIDER_SERVICE_BLOCK.name(), "PROVIDER SERVICE BLOCK");


    TAB_BLOCK_PROVIDER.put(BlockType.PROVIDER_BLOCK.name(), "PROVIDER BLOCK");
    TAB_BLOCK_PROVIDER.put(BlockType.PROVIDER_SERVICE_BLOCK.name(), "PROVIDER_SERVICE BLOCK");

    TAB_BLOCK_SERVICE.put(BlockType.SERVICE_BLOCK.name(), "SERVICE BLOCK");
  }
  public BlockType getBlockType(String name) {
    if (StringUtils.isNotBlank(name)) {
      for (BlockType blockType : BlockType.values()) {
        if (name.equals(blockType.name())) {
          return blockType;
        }
      }
    }

    return null;
  }

}
