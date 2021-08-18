package vn.mog.ewallet.operation.web.configuration;

import java.util.EnumSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import vn.mog.ewallet.operation.web.security.listener.CustomSessionListener;


@Configuration
public class WebApplicationConfiguration implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
    servletContext.addListener(new CustomSessionListener());
  }
}
