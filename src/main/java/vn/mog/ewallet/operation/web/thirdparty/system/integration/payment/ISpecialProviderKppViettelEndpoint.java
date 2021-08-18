package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment;

import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountCapchaEnterRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountCapchaEnterResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountCapchaGetRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountCapchaGetResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountChangeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountChangeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountChangeStatusRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountChangeStatusResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountGetRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountGetResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAllAccountFindRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAllAccountFindResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAllowedProviderChangeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAllowedProvinceChangeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAllowedProvinceGetResponse;

public interface ISpecialProviderKppViettelEndpoint {

  // provider special
  SpecialProviderAllAccountFindResponse providerSpecialAccountFind(
      SpecialProviderAllAccountFindRequest request) throws FrontEndException;

  SpecialProviderAccountChangeStatusResponse providerSpecialAccoutChangeStatus(
      SpecialProviderAccountChangeStatusRequest request) throws FrontEndException;

  //  SpecialProviderAccountGetResponse providerSpecialAccountGet(
  //      SpecialProviderAccountGetRequest request) throws FrontEndException;

  SpecialProviderAccountChangeResponse providerSpecialAccountAdd(
      SpecialProviderAccountChangeRequest request) throws FrontEndException;

  SpecialProviderAccountChangeResponse providerSpecialAccountUpdate(
      SpecialProviderAccountChangeRequest request) throws FrontEndException;

  SpecialProviderAccountChangeResponse providerSpecialAccountDelete(
      SpecialProviderAccountChangeRequest request) throws FrontEndException;

  SpecialProviderAccountCapchaGetResponse providerSpecialAccountCapchaGet(
      SpecialProviderAccountCapchaGetRequest request) throws FrontEndException;

  SpecialProviderAccountCapchaEnterResponse providerSpecialAccountCapchaEnter(
      SpecialProviderAccountCapchaEnterRequest request) throws FrontEndException;
}
