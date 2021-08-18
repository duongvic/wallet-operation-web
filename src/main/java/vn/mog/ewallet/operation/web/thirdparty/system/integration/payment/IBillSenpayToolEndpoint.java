package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment;

import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.*;

public interface IBillSenpayToolEndpoint {
  //provider special
  SpecialProviderAllAccountFindResponse providerSpecialAccountFind(
          SpecialProviderAllAccountFindRequest request) throws FrontEndException;

  SpecialProviderAccountChangeStatusResponse providerSpecialAccoutChangeStatus(
          SpecialProviderAccountChangeStatusRequest request) throws FrontEndException;

  SpecialProviderAccountGetResponse providerSpecialAccountGet(
          SpecialProviderAccountGetRequest request) throws FrontEndException;

  SpecialProviderAccountChangeResponse providerSpecialAccountAdd(
          SpecialProviderAccountChangeRequest request) throws FrontEndException;

  SpecialProviderAccountChangeResponse providerSpecialAccountUpdate(
          SpecialProviderAccountChangeRequest request) throws FrontEndException;

  SpecialProviderAccountChangeResponse providerSpecialAccountDelete(
          SpecialProviderAccountChangeRequest request) throws FrontEndException;

  SpecialProviderAllowedProvinceGetResponse allowedProvinceGet() throws FrontEndException;

  SpecialProviderAllowedProvinceChangeResponse allowedProvinceChange(
          SpecialProviderAllowedProviderChangeRequest request) throws FrontEndException;
}
