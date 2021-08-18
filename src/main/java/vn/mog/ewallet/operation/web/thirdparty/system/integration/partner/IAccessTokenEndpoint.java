package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner;

import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GetAccessTokenRequest;


public interface IAccessTokenEndpoint {

  GeneralResponse<Object> getAccessTokenPMS(GetAccessTokenRequest request)throws FrontEndException;

}
