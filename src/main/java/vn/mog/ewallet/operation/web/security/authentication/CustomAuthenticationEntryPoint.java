package vn.mog.ewallet.operation.web.security.authentication;


import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import vn.mog.ewallet.operation.web.constant.SharedConstants;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

  private final static Logger LOGGER = LogManager.getLogger(CustomAuthenticationEntryPoint.class);

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
    // This is invoked when user tries to access a secured REST resource
    // without supplying any credentials
    // We should just send a 401 Unauthorized response because there is no
    // 'login page' to redirect to

    LOGGER.info(String.format("### AuthenticationEntryPoint: URL [%s] ", request.getServletPath()));
    new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
    response.sendRedirect(SharedConstants.WEB_DOMAIN_PLATFORM_OPERATION_URL + "/login");
  }
}
