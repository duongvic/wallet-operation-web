package vn.mog.ewallet.operation.web.contract;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.constant.SharedConstants;
import vn.mog.ewallet.operation.web.controller.provider.ProviderController;
import vn.mog.ewallet.operation.web.controller.translog.TransactionLogController;
import vn.mog.ewallet.operation.web.controller.translog.TransactionWalletUserController;


@Service
public class UrlIntergration {

  /* ------------------PROVIDER--------------- */
  public static final String PROVIDER_TAB = ProviderController.PROVIDER_LIST;

  /* ------------------WALLET--------------- */
  public static final String WALLET_TAB = TransactionWalletUserController.TRANSACTION_WALLET_USER_LIST
      + "?" + TransactionWalletUserController.TRANSACTION_WALLET_DEFAULT_FILTER;

  /* ------------------SERVICE--------------- */
  public static final String SERVICE_TAB = TransactionLogController.TRANSACTION_LIST_ALL/*
      + "?" + TransactionLogController.TRANSACTION_DEFAULT_FILTER*/;

  /* ------------------CUSTOMER--------------- */
//  public static String CUSTOMER_TAB = StringUtils.EMPTY;

  /* ------------------OPERATION--------------- */
//  public static String OPERATION_TAB = StringUtils.EMPTY;

//  public static String BILLPAY_TAB = StringUtils.EMPTY;

  /* ------------------SETTING--------------- */
//  public static String SETTING_TAB = StringUtils.EMPTY;

  /* ------------------ACCOUNT--------------- */
  public static String CHANGE_PASSWORD = StringUtils.EMPTY;

 /* @Value("${web.domain.platform.operation.subpath.customer.url}")
  public void setAgentURICustomerTab(String value) {
    CUSTOMER_TAB = value;
  }*/

    /*@Value("${web.domain.platform.operation.subpath.operation.url}")
    public void setAgentURIOperationTab(String value) {
	OPERATION_TAB = value;
    }

    @Value("${web.domain.platform.operation.subpath.billpay.url}")
    public void setAgentURIBillpayTab(String value) {
	BILLPAY_TAB = value;
    }*/

   /* @Value("${web.domain.platform.operation.subpath.setting.url}")
    public void setAgentURISettingTab(String value) {
	SETTING_TAB = value;
    }*/

  @Value("${web.domain.platform.operation.subpath.change.password.uri}")
  public void setChangePasswordURI(String value) {
    CHANGE_PASSWORD =
        SharedConstants.WEB_DOMAIN_PLATFORM_UAA_URL + value + SharedConstants.WEB_DOMAIN_PLATFORM_OPERATION_URL + "/account/manage/list" + "&key_uri="
            + "uOwiQm";
  }
}
