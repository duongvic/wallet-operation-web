package vn.mog.ewallet.operation.web.constant;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SharedConstants {

  // SOME CHARACTERS
  public static final String EQUAL = "=";
  public static final String COMMA = ",";
  public static final String COLON = ":";
  public static final String DASH = "-";
  public static final String SEMI_CONLON = ";";
  public static final String VERTICAL_BAR = "|";
  public static final String UNDERSCORE = "_";
  public static final String AMPERSAND = "&";
  public static final String SPACE = " ";
  public static final String SLASH = "/";

  public static String WEB_DOMAIN_PLATFORM_OPERATION_URL = StringUtils.EMPTY;
  public static String WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_EPIN_STORE_URL = StringUtils.EMPTY;
  public static String WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_EPIN_STORE_N02_URL = StringUtils.EMPTY;
  public static String WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_EPIN_STORE_OFFLINE_URL = StringUtils.EMPTY;
  public static String WEB_DOMAIN_PLATFORM_UAA_URL = StringUtils.EMPTY;
  public static String WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_PMS_URL = StringUtils.EMPTY;

  public static String WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_SALE_CENTER_URL = StringUtils.EMPTY;
  public static String WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_PURCHASE_CENTER_URL = StringUtils.EMPTY;


  public static String PLATFORM_BACKEND_ZUUL_GATEWAY_API_ENDPOINT = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_PARTNER_GATEWAY_API_ENDPOINT = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_ZPAY_DATA_SERVICE_GATEWAY_API_ENDPOINT = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT = StringUtils.EMPTY;
  public static String PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_CENTRALIZATION_JOB_SERVICE_API_ENDPOINT = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_NOTIFICATION_SERVICE_API_ENDPOINT = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_HISTORYCAL_SERVICE_API_ENDPOINT = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_STOCK_SERVICE_API_ENDPOINT = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT = StringUtils.EMPTY;


  public static String PLATFORM_BACKEND_PMS_PROFILE_COMPOSITE_SERVICE_API_ENDPOINT = StringUtils.EMPTY;
  public static String PLATFORM_BACKEND_PMS_GET_TOKEN_SERVICE_API_ENDPOINT = StringUtils.EMPTY;

  public static int HTTP_DEFAULT_MAX_CONNECTIONS_PER_HOST = 100;
  public static int HTTP_MAX_TOTAL_CONNECTIONS = 5000;
  public static int HTTP_CLIENT_CONNECT_TIMEOUT = 60000;// ms
  public static int HTTP_CLIENT_SOCKET_TIMEOUT = 900000;// ms

  public static long CUSTOMER_MAINTENANCE_BALANCE = 50000L;

  /*----- FUND ORDER -----*/
  /*--- FUND IN ---*/
  public static Long FUND_IN_ORDER_MIN_VALUE_PER_ORDER = 0L;
  public static Long FUND_IN_ORDER_MAX_VALUE_PER_ORDER = 0L;
  public static Long FUND_IN_ORDER_TOTAL_VALUE_PER_DAY = 0L;

  /*--- FUND OUT ---*/
  public static Long FUND_OUT_ORDER_MIN_VALUE_PER_ORDER = 0L;
  public static Long FUND_OUT_ORDER_MAX_VALUE_PER_ORDER = 0L;
  public static Long FUND_OUT_ORDER_TOTAL_VALUE_PER_DAY = 0L;
  public static Long FUND_ORDER_TMV_MIN_VALUE = 0L;
  public static Long FUND_ORDER_TMV_MAX_VALUE = 0L;
  public static Long FUND_ORDER_TMV_TOTAL_VALUE = 0L;

  // --WALLET TRANSFER-//
  public static Long TRANSFER_FUND_MAX_MONEY = 0L;
  public static Long TRANSFER_WALLET_MAX_MONEY = 0L;

  /*-- Export Card - ZTA---*/
  public static Integer QUANTITY_MPO_MAX_ZTA = 0;
  public static String TRANSFER_INTERNAL_WALLET = StringUtils.EMPTY;
  public static String TRANSFER_P2P = StringUtils.EMPTY;


  /*SEN PAY*/

  public static Integer SEN_PAY_TRANSACTION = 0;

  @Value("${platform.backend.zuul.gateway.api.endpoint}")
  public void setPlatformZuulGatewayApiEndpoint(String value) {
    PLATFORM_BACKEND_ZUUL_GATEWAY_API_ENDPOINT = value;
  }
  @Value("${platform.backend.partner.gateway.api.endpoint}")
  public void setPlatformPartnerGatewayApiEndpoint(String value) {
    PLATFORM_BACKEND_PARTNER_GATEWAY_API_ENDPOINT = value;
  }

  @Value("${platform.backend.uaa.service.api.endpoint}")
  public void setPlatformUAAServiceApiEndpoint(String value) {
    PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT = value;
  }

  @Value("${platform.backend.payment.service.api.endpoint}")
  public void setPlatformPaymentServiceApiEndpoint(String value) {
    PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT = value;
  }

  @Value("${platform.backend.internal.provider.service.api.endpoint}")
  public void setPlatformInternalProviderServiceApiEndpoint(String value) {
    PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT = value;
  }

  @Value("${platform.backend.historycal.service.api.endpoint}")
  public void setPlatformHistorycalServiceApiEndpoint(String value) {
    PLATFORM_BACKEND_HISTORYCAL_SERVICE_API_ENDPOINT = value;
  }

  @Value("${platform.backend.sofcash.service.api.endpoint}")
  public void setPlatformSofCashServiceApiEndpoint(String value) {
    PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT = value;
  }

  @Value("${platform.backend.centralization.job.service.api.endpoint}")
  public void setPlatformCentralizationJobApiEndpoint(String value) {
    PLATFORM_BACKEND_CENTRALIZATION_JOB_SERVICE_API_ENDPOINT = value;
  }

  @Value("${platform.backend.notification.service.api.endpoint}")
  public void setPlatformNotificationServiceApiEndpoint(String value) {
    PLATFORM_BACKEND_NOTIFICATION_SERVICE_API_ENDPOINT = value;
  }

  @Value("${web.domain.platform.uaa.url}")
  public void setWebDomainPlatformUAAUrl(String value) {
    WEB_DOMAIN_PLATFORM_UAA_URL = value;
  }

  @Value("${web.domain.platform.operation.url}")
  public void setWebDomainPlatformOperationUrl(String value) {
    WEB_DOMAIN_PLATFORM_OPERATION_URL = value;
  }

  @Value("${web.domain.platform.operation.subpath.epinstore.url}")
  public void setWebDomainPlatformOperationSubpathEpinStoreUrl(String value) {
    WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_EPIN_STORE_URL = value;
  }

  @Value("${web.domain.platform.operation.subpath.epinstore.n02.url}")
  public void setWebDomainPlatformOperationSubpathEpinStoreN02Url(String value) {
    WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_EPIN_STORE_N02_URL = value;
  }

  @Value("${web.domain.platform.operation.subpath.epinstore.offline.url}")
  public void setWebDomainPlatformOperationSubpathEpinStoreOfflineUrl(String value) {
    WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_EPIN_STORE_OFFLINE_URL = value;
  }

  @Value("${web.domain.platform.operation.subpath.pms.url}")
  public void setWebDomainPlatformOperationSubpathPmsUrl(String value) {
    WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_PMS_URL = value;
  }

  @Value("${web.domain.platform.operation.subpath.sale.center.url}")
  public void setWebDomainPlatformOperationSubpathSaleCenterUrl(String value) {
    WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_SALE_CENTER_URL = value;
  }

  @Value("${web.domain.platform.operation.subpath.purchase.center.url}")
  public void setWebDomainPlatformOperationSubpathPurchaseCenterUrl(String value) {
    WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_PURCHASE_CENTER_URL = value;
  }

  @Value("${customer.maintenance.balance}")
  public void setCustomerMaintenanceBalance(Long value) {
    CUSTOMER_MAINTENANCE_BALANCE = value;
  }

  @Value("${wallet.fund.in.order.min.value.per.order}")
  public void setWalletFundInOderMinValuePerOrder(Long value) {
    FUND_IN_ORDER_MIN_VALUE_PER_ORDER = value;
  }

  @Value("${wallet.fund.in.order.max.value.per.order}")
  public void setWalletFundInOderMaxValuePerOrder(Long value) {
    FUND_IN_ORDER_MAX_VALUE_PER_ORDER = value;
  }

  @Value("${wallet.fund.in.order.total.value.per.day}")
  public void setWalletFundInOderTotalValuePerDay(Long value) {
    FUND_IN_ORDER_TOTAL_VALUE_PER_DAY = value;
  }

  @Value("${wallet.fund.out.order.min.value.per.order}")
  public void setWalletFundOutOderMinValuePerOrder(Long value) {
    FUND_OUT_ORDER_MIN_VALUE_PER_ORDER = value;
  }

  @Value("${wallet.fund.out.order.max.value.per.order}")
  public void setWalletFundOutOderMaxValuePerOrder(Long value) {
    FUND_OUT_ORDER_MAX_VALUE_PER_ORDER = value;
  }

  @Value("${wallet.fund.out.order.total.value.per.day}")
  public void setWalletFundOutOderTotalValuePerDay(Long value) {
    FUND_OUT_ORDER_TOTAL_VALUE_PER_DAY = value;
  }

  @Value("${wallet.fund.order.tmv.min.value}")
  public void setWalletFundOderTMVMinValue(Long value) {
    FUND_ORDER_TMV_MIN_VALUE = value;
  }

  @Value("${wallet.fund.order.tmv.max.value}")
  public void setWalletFundOderTMVMaxValue(Long value) {
    FUND_ORDER_TMV_MAX_VALUE = value;
  }

  @Value("${wallet.fund.order.tmv.total.value}")
  public void setWalletFundOderTMVTotalValue(Long value) {
    FUND_ORDER_TMV_TOTAL_VALUE = value;
  }

  @Value("${transfer.fund.order.amount.level}")
  public void setEWalletFundInFundTransfer(Long value) {
    TRANSFER_FUND_MAX_MONEY = value;
  }

  @Value("${transfer.wallet.order.amount.level}")
  public void setEWalletWalletTransfer(Long value) {
    TRANSFER_WALLET_MAX_MONEY = value;
  }

  @Value("${topup.export.card.tmn.quantity.per.mpo.max}")
  public void setQuantityMpoMaxZTA(Integer value) {
    QUANTITY_MPO_MAX_ZTA = value;
  }


  @Value("${wallet.transfer.internal.wallet.transfer}")
  public void setInternalWalletTransfer(String value) {
    TRANSFER_INTERNAL_WALLET = value;
  }


  @Value("${wallet.transfer.p2p.transfer}")
  public void setP2pTransfer(String value) {
    TRANSFER_P2P = value;
  }

  @Value("${wallet.sen.pay.transaction}")
  public void setSenPayTransaction(Integer value) {
    SEN_PAY_TRANSACTION = value;
  }

  //PMS profile
  @Value("${platform.backend.pms.profile.composite.service.api.endpoint}")
  public void setPlatformPMSProfileCompositeServiceApiEndpoint(String value) {
    PLATFORM_BACKEND_PMS_PROFILE_COMPOSITE_SERVICE_API_ENDPOINT = value;
  }

  //PMS token
  @Value("${platform.backend.pms.token.service.api.endpoint}")
  public void setPlatformPMSGetTokenServiceApiEndpoint(String value) {
    PLATFORM_BACKEND_PMS_GET_TOKEN_SERVICE_API_ENDPOINT = value;
  }

  //THỐNG KÊ SỐ DƯ TÀI SẢN
  @Value("${platform.backend.stock.service.api.endpoint}")
  public void setPlatformStockServiceApiEndpoint(String value) {
    PLATFORM_BACKEND_STOCK_SERVICE_API_ENDPOINT = value;
  }

  //Statistic
  @Value("${platform.backend.data.service.api.endpoint}")
  public void setPlatformDataServiceApiEndpoint(String value) {
    PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT = value;
  }
}
