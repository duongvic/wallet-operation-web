package vn.mog.ewallet.operation.web.thirdparty.system.integration;


import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.constant.SharedConstants;


@Service
public class AuthServerAPIClient extends AbstractAPIClient {

  @Override
  protected String getClientID() {
    return "uaa-service";
  }

  @Override
  protected String getBaseRequestURL() {
    return SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT;
  }

  @Override
  protected String getBearerHeaderPrefix() {
    return BEARER_HEADER_PREFIX;
  }

  @Override
  protected boolean isSessionStorage() {
    return true;
  }
}
