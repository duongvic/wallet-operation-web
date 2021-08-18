package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner;

import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.CreateCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.FindCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.FindCustomerRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;

public interface ISystemUserEndpoint {

  GeneralResponse<Object> findAccountList(FindCustomerRequest request) throws FrontEndException;
  GeneralResponse<Object> findAccountFromZota(FindCustomerRequest request) throws FrontEndException;
  GeneralResponse<Object> createAccount(CreateCustomerRequest request) throws FrontEndException;
  GeneralResponse<Object> findAllCustomerRole(FindCustomerRoleRequest request, String accessToken) throws FrontEndException;


}
