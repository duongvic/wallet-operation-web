package vn.mog.ewallet.operation.web.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.UserType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.WalletType;

public class CustomerUtil {

  private static final Logger LOGGER = LogManager.getLogger(CustomerUtil.class);

  public static Integer getWalletType(Integer customerType) {
    if (customerType == null) {
      return null;
    }
    try {
      if (customerType.equals(CustomerType.ID_POOL) || customerType
          .equals(CustomerType.ID_SOF) || customerType.equals(CustomerType.ID_STAFF) || customerType
          .equals(CustomerType.ID_ADMIN) || customerType.equals(CustomerType.ID_SYSTEM)) {
        return WalletType.ACCOUNT;
      } else {
        return WalletType.WALLET;
      }

    } catch (NumberFormatException ex) {
      LOGGER.error(ex.getMessage(), ex);

      return null;
    }
  }

  public static Integer getUserType(Integer customerType) {
    if (customerType == null) {
      return null;
    }
    try {
      if (customerType.equals(CustomerType.ID_ADMIN)
          || customerType.equals(CustomerType.ID_SYSTEM)) {
        return UserType.SYSTEM;
      } else {
        return UserType.USER;
      }

    } catch (NumberFormatException ex) {
      LOGGER.error(ex.getMessage(), ex);

      return null;
    }
  }

}
