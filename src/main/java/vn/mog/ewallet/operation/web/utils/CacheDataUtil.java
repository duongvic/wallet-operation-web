package vn.mog.ewallet.operation.web.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.IPaymentCustomerEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.IProviderEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindFullCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans.Provider;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.IUaaCustomerEndpoint;
import vn.mog.framework.cache.dynacache.PassiveDynaCache;
import vn.mog.framework.cache.dynacache.common.CacheConstants;
import vn.mog.framework.security.api.ICallerUtils;

@Slf4j
@Service
public class CacheDataUtil implements InitializingBean {

  public static final String CACHED_CUSTOMERS_BY_BIZ = "list_all_customers_cached_key";

  public static final String CACHED_ACCOUNT_MERCHANT_BY_BIZ = "list_all_merchant_cached_key";
  
  public static final String CACHED_PROVIDER_BY_BIZ = "list_all_provider_cached_key";

  @Autowired protected IPaymentCustomerEndpoint walletCustomerEndpoint;

  @Autowired protected IUaaCustomerEndpoint customerEndpoint;

  @Autowired private PassiveDynaCache passiveDynaCache;

  @Autowired protected ICallerUtils callerUtil;

  @Autowired protected IProviderEndpoint providerEndpoint;

  @Override
  public void afterPropertiesSet() throws Exception {
    if (this.customerEndpoint == null) {
      throw new IllegalStateException("IPaymentCustomerEndpoint is required");
    }
  }

  //  public List<Customer> getCustomersByBiz() {
  //    try {
  //      String cacheKey = callerUtil.getCallerInformation().getActorCif() + "_" +
  // CACHED_CUSTOMERS_BY_BIZ;
  //      Object cacheItem = passiveDynaCache.getCachedItem(cacheKey);
  //      if (cacheItem == null) {
  //        FindFullCustomerRequest fcRequest = new FindFullCustomerRequest();
  //        fcRequest
  //            .setCustomerTypes(Arrays.asList(CustomerType.ID_CUSTOMER, CustomerType.ID_MERCHANT
  // ,CustomerType.ID_AGENT));
  //
  //        /*
  //         * FUCK: temporay limit 10 record
  //         */
  //        fcRequest.setOffset(0);
  //        fcRequest.setLimit(10);
  //        //----------
  //
  //        List<Customer> customers =
  // walletCustomerEndpoint.findCustomers(fcRequest).getCustomers();
  //
  //        passiveDynaCache.setCachedItem(cacheKey, customers,
  //            CacheConstants.MEMCACHED_TIMEOUT_5_MINS);
  //
  //        return customers;
  //      }
  //      return (List<Customer>) cacheItem;
  //    } catch (Exception e) {
  //      return Collections.emptyList();
  //    }
  //  }

  public List<Customer> getAccountMerchantByBiz() {
    try {
      Object cacheItem = passiveDynaCache.getCachedItem(CACHED_ACCOUNT_MERCHANT_BY_BIZ);
      if (cacheItem == null) {
        FindFullCustomerRequest fcRequest = new FindFullCustomerRequest();
        fcRequest.setCustomerTypes(Arrays.asList(CustomerType.ID_MERCHANT));

        List<Customer> customers = walletCustomerEndpoint.findCustomers(fcRequest).getCustomers();

        passiveDynaCache.setCachedItem(
            CACHED_ACCOUNT_MERCHANT_BY_BIZ, customers, CacheConstants.MEMCACHED_TIMEOUT_5_MINS);

        return customers;
      }
      return (List<Customer>) cacheItem;
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }

  @Async
  public void resetProviderCaching() {
    try {
      FindProviderRequest findReq = new FindProviderRequest();
      findReq.setLimit(-1);
      findReq.setOffset(-1);
      FindProviderResponse findResp = providerEndpoint.findProviders(findReq);
      if (findResp != null && findResp.getProviders() != null) {
        List<Provider> providers = findResp.getProviders();
        passiveDynaCache.setCachedItem(CACHED_PROVIDER_BY_BIZ, providers, CacheConstants.MEMCACHED_TIMEOUT_24_HOURS);
      }
    } catch (Exception e) {
     log.error(e.getMessage(), e); 
    }
  }

  private static boolean isRunning = false;
  @SuppressWarnings("unchecked")
  public List<Provider> getCachingProviders() {
    Object cacheItem = passiveDynaCache.getCachedItem(CACHED_PROVIDER_BY_BIZ);
    if (cacheItem != null) {
      return (List<Provider>) cacheItem;
    }
    // --------------------------------------------------
    if (isRunning)
      return Collections.emptyList();
    isRunning = true;
    // --------------------------------------------------
    try {
      FindProviderRequest findReq = new FindProviderRequest();
      findReq.setLimit(-1);
      findReq.setOffset(-1);
      FindProviderResponse findResp = providerEndpoint.findProviders(findReq);
      if (findResp != null && findResp.getProviders() != null) {
        List<Provider> providers = findResp.getProviders();
        passiveDynaCache.setCachedItem(CACHED_PROVIDER_BY_BIZ, providers,
            CacheConstants.MEMCACHED_TIMEOUT_24_HOURS);
        return providers;
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    } finally {
      isRunning = false;
    }
    return Collections.emptyList();
  }
}
