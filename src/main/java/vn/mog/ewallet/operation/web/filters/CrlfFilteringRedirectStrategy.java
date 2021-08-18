package vn.mog.ewallet.operation.web.filters;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.DefaultRedirectStrategy;


public class CrlfFilteringRedirectStrategy extends DefaultRedirectStrategy {

  /**
   * @see org.springframework.security.web.DefaultRedirectStrategy#sendRedirect(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
   */
  @Override
  public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
    String filteredUrl = url.replaceAll("\\n|\\r", StringUtils.EMPTY);
    super.sendRedirect(request, response, filteredUrl);
  }
}
