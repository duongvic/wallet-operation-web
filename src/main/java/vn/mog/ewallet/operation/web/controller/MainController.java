package vn.mog.ewallet.operation.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import vn.mog.ewallet.operation.web.constant.SharedConstants;
import vn.mog.ewallet.operation.web.controller.dashboard.TransactionDashboardController;
import vn.mog.ewallet.operation.web.controller.translog.TransactionLogController;


@Controller
public class MainController extends AbstractController {

  private static final String PAGE_HOME = "/home/index";

  private static final String messageDefault = "Don't find the user and password";

  @GetMapping(value = "/")
  public String index() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Set<String> myRoles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

    if(myRoles.contains("ROLE_ADMIN_OPERATION")){
      return String.format("redirect:%s", TransactionDashboardController.DASH_BOARD + "?menu=ser");
    }

    if(myRoles.contains("ROLE_STAFF") && myRoles.contains("ROLE_SALE_EXCUTIVE") && myRoles.size() == 2){
      return String.format("redirect:%s", TransactionLogController.TRANSACTION_LIST + "?idOwnerCustomerTypes=AGENT");
    }
    return String.format("redirect:%s", TransactionLogController.TRANSACTION_LIST_ALL/*
        + "?" + TransactionLogController.TRANSACTION_DEFAULT_FILTER*/);
  }

  @GetMapping(value = "/home")
  public String home() {
    return PAGE_HOME;
  }

  @GetMapping(value = "/service/logout")
//  @PreAuthorize("isAuthenticated()")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    Cookie[] cookies = request.getCookies();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (cookies != null) {
      List<String> cookieNames = Arrays.stream(cookies).map(Cookie::getName).collect(Collectors.toList());
      new CookieClearingLogoutHandler(cookieNames.toArray(new String[0])).logout(request, response, authentication);
    }
    new SecurityContextLogoutHandler().logout(request, response, authentication);
    return String.format("redirect:%s/logout?pre=%s", SharedConstants.WEB_DOMAIN_PLATFORM_UAA_URL, SharedConstants.WEB_DOMAIN_PLATFORM_OPERATION_URL);
  }

  @GetMapping(value = "/wallet")
  @PreAuthorize("isAuthenticated()")
  public String wallet() {
    return String.format("redirect:%s", redirectWalletRole());
  }

  @GetMapping(value = "/provider")
  @PreAuthorize("isAuthenticated()")
  public String provider() {
    return String.format("redirect:%s", redirectProviderRole());
  }

  @GetMapping(value = "/service")
  @PreAuthorize("isAuthenticated()")
  public String service() {
    return String.format("redirect:%s", redirectServiceRole());
  }

  @GetMapping(value = "/common-error")
  public String notificationError(ModelMap model) {
    return "/config/error";
  }

  @GetMapping(value = "/not-found")
  public String notificationNotFound(ModelMap model) {
    return "/config/404";
  }

}
