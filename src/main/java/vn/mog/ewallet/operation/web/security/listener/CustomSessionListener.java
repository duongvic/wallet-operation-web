package vn.mog.ewallet.operation.web.security.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomSessionListener implements HttpSessionListener {

  private static final Logger LOG = LogManager.getLogger(CustomSessionListener.class);

  @Override
  public void sessionCreated(HttpSessionEvent event) {
    LOG.info("Session is created");
    event.getSession().setMaxInactiveInterval(24*60*60*7);
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent event) {
    LOG.info("Session is destroyed");
  }
}
