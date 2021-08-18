package vn.mog.framework.common.utils;



import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionUtil {

  private static final Logger LOG = LogManager.getLogger(SessionUtil.class);

  /***
   * Xóa session
   */
  public static void cleanSession(HttpServletRequest request) {
    try {
      HttpSession session = request.getSession();
      Enumeration<?> attrNames = session.getAttributeNames();
      while (attrNames.hasMoreElements()) {
        session.removeAttribute((String) attrNames.nextElement());
      }
      request.getSession().invalidate();
    } catch (Exception e) {
      LOG.error("cleanSession", e);
    }
  }

  public static void setObjectToSession(HttpServletRequest request, String name, Object object) {
    try {
      if (object != null) {
        request.getSession().setAttribute(name, object);
      }
    } catch (Exception e) {
      LOG.error("setSession", e);
    }
  }

  public static Object getObjectFromSession(HttpServletRequest request, String name) {
    try {
      if (StringUtils.isNotEmpty(name)) {
        return request.getSession().getAttribute(name);
      }
    } catch (Exception e) {
      LOG.error("getObjectFromSession", e);
    }
    return null;
  }

  public static Object getAttribute(String name) {
    try {
      if (StringUtils.isNotEmpty(name)) {
        return HttpUtil.getCurrentHttpServletRequest().getSession().getAttribute(name);
      }
    } catch (Exception e) {
      LOG.error("getObjectFromSession", e);
    }
    return null;
  }

  public static Object removeAttribute(String name) {
    try {
      if (StringUtils.isNotEmpty(name)) {
        HttpUtil.getCurrentHttpServletRequest().getSession().removeAttribute(name);
      }
    } catch (Exception e) {
      LOG.error("getObjectFromSession", e);
    }
    return null;
  }

  public static void setAttribute(String attribute, Object object) {
    if (StringUtils.isNotEmpty(attribute) && object != null) {
      HttpUtil.getCurrentHttpServletRequest().getSession().setAttribute(attribute, object);
    }
  }

  public static boolean hasSession(HttpServletRequest request, String sessionName) {
    try {
      HttpSession session = request.getSession();
      String name = StringUtils.trimToEmpty(sessionName);
      if (StringUtils.isNotBlank(name) && session.getAttribute(name) != null) {
        return true;
      }
    } catch (Exception e) {
      LOG.error("hasSession", e);
    }
    return false;
  }

  public static void cleanCookie(HttpServletRequest request, HttpServletResponse response, List<String> cookiesToClear) {
    for (String cookieName : cookiesToClear) {
      Cookie cookie = new Cookie(cookieName, null);
      String cookiePath = request.getContextPath() + "/";
      if (!org.springframework.util.StringUtils.hasLength(cookiePath)) {
        cookiePath = "/";
      }
      cookie.setPath(cookiePath);
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }
  }

  public static void cleanCookie(HttpServletRequest request, HttpServletResponse response) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookieName : cookies) {
        Cookie cookie = new Cookie(cookieName.getName(), null);
        String cookiePath = request.getContextPath() + "/";
        if (!org.springframework.util.StringUtils.hasLength(cookiePath)) {
          cookiePath = "/";
        }
        cookie.setPath(cookiePath);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
      }
    }
  }

  /*
   * public static void cleanCookie(HttpServletRequest request,
   * HttpServletResponse response, Cookie[] cookies) { if (cookies != null)
   * for (Cookie item : cookies) { Cookie cookie = new Cookie(item.getName(),
   * null); String cookiePath = request.getContextPath() + "/"; if
   * (!org.springframework.util.StringUtils.hasLength(cookiePath)) {
   * cookiePath = "/"; } cookie.setPath(cookiePath); cookie.setMaxAge(0);
   * response.addCookie(cookie); } }
   */

}
