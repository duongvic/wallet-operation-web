package vn.mog.ewallet.operation.web.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import vn.mog.framework.common.utils.Utils;


public class LoggingInterceptor extends HandlerInterceptorAdapter {

  private static final Logger LOGGER = LogManager.getLogger(LoggingInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    // TODO Auto-generated method stub
    try {
      final String ip = Utils.getIP(request);
      ThreadContext.put("IP", ip);
      ThreadContext.put("IPAddress", ip);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    return true;
  }

}
