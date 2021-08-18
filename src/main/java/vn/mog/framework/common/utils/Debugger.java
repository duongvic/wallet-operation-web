package vn.mog.framework.common.utils;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Debugger {

  protected final Logger debugLog = LogManager.getLogger("debugLogger");
  protected Logger logger;

  public Debugger() {
  }

  @SuppressWarnings("rawtypes")
  public Debugger(Class clazz) {
    if (clazz != null) {
      this.logger = LogManager.getLogger(clazz);
    } else {
      this.logger = debugLog;
    }
  }

  public Debugger(String moduleName) {
    if (StringUtils.isNotBlank(moduleName)) {
      this.logger = LogManager.getLogger(moduleName);
    } else {
      this.logger = debugLog;
    }
  }

  public void logInfo(Object title, Object obj) {
    Gson gson = new Gson();
    Logger logger = this.logger;
    String message = "\n#========================>" + title + "<========================#\n" + gson.toJson(obj);
    logger.info(message);
  }

  public void logError(Object title, Object obj) {
    Gson gson = new Gson();
    Logger logger = this.logger;
    String message = "\n#========================>" + title + "<========================#\n" + gson.toJson(obj);
    logger.error(message);
  }

  public void logError(Object message, Throwable t) {
    Logger logger = this.logger;
    logger.error(message, t);
  }

  public void logDebug(Object title, Object obj) {
    Gson gson = new Gson();
    Logger logger = this.logger;
    String message = "\n#========================>" + title + "<========================#\n" + gson.toJson(obj);
    logger.debug(message);
  }

  public void logWarn(Object title, Object obj) {
    Gson gson = new Gson();
    Logger logger = this.logger;
    String message = "\n#========================>" + title + "<========================#\n" + gson.toJson(obj);
    logger.warn(message);
  }

  public void errorException(Exception error) {
    this.logger.error(error);
  }

  public void errorException(Object message, Exception error) {
    this.logger.error(message, error);
  }

  public Logger getLogger() {
    return logger;
  }

  public void setLogger(Logger logger) {
    this.logger = logger;
  }

}
