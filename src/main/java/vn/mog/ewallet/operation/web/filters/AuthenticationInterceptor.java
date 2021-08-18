package vn.mog.ewallet.operation.web.filters;


import static vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController.BALANCE_TOTAL_ASSETS_CONTROLLER;

import java.io.IOException;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.constant.SessionConstants;
import vn.mog.ewallet.operation.web.constant.SharedConstants;
import vn.mog.ewallet.operation.web.contract.UserLogin;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.controller.account.StaffAccountController;
import vn.mog.ewallet.operation.web.controller.dashboard.DashboardController;
import vn.mog.ewallet.operation.web.controller.fundin.FundInOrderController;
import vn.mog.ewallet.operation.web.controller.fundout.FundOutOrderController;
import vn.mog.ewallet.operation.web.controller.provider.ProviderController;
import vn.mog.ewallet.operation.web.controller.provider.ProviderSpecialSenpayController;
import vn.mog.ewallet.operation.web.controller.reconciliation.ReconciliationV3Controller;
import vn.mog.ewallet.operation.web.controller.service.TrueServiceController;
import vn.mog.ewallet.operation.web.controller.transfer.FundinSofController;
import vn.mog.ewallet.operation.web.controller.transfer.WalletTransferController;
import vn.mog.ewallet.operation.web.controller.translog.TransactionLogController;
import vn.mog.ewallet.operation.web.controller.translog.TransactionRuleController;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.IWalletEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.IUaaSystemEndpoint;
import vn.mog.ewallet.operation.web.utils.WebUtil;
import vn.mog.framework.common.utils.SessionUtil;
import vn.mog.framework.security.api.CallerInformation;
import vn.mog.framework.security.api.ICallerUtils;


public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

  private static final Logger LOGGER = LogManager.getLogger(AuthenticationInterceptor.class);

  @Autowired
  protected IWalletEndpoint walletEndpoint;
  @Autowired
  protected IUaaSystemEndpoint securityService;
  @Autowired
  ICallerUtils callerUtil;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws IOException {

    String requestURI = request.getRequestURI();

    // TODO, Cần xử lý business chỗ này, /error với lõi authenzied
    LOGGER.debug("--preHandle--RequestURI--" + request.getRequestURI());

    boolean ignoreURL =
        StringUtils.EMPTY.equals(requestURI) || "/".equals(requestURI) || "/home".equals(requestURI)
            || AbstractController.PATH_ERROR.equals(requestURI) || "/service/logout".equals(requestURI);

    int statusResponse = response.getStatus();

    if (AbstractController.PATH_ERROR.equals(requestURI)) {
      if (statusResponse == HttpServletResponse.SC_UNAUTHORIZED
          || statusResponse == HttpServletResponse.SC_FORBIDDEN) {
        request.setAttribute("errorCode", HttpServletResponse.SC_UNAUTHORIZED);
      } else {
        request.setAttribute("errorCode", 0);
      }
      return true;
    } else if (ignoreURL) {
      return true;
    }

    Authentication au = SecurityContextHolder.getContext().getAuthentication();
    boolean isAuthen = au != null && au.isAuthenticated() && au instanceof OAuth2Authentication;

    try {
      if (isAuthen) {
        initAttributeToRequest(request);
        return true;
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    new SecurityContextLogoutHandler().logout(request, response, au);
    response.sendRedirect(SharedConstants.WEB_DOMAIN_PLATFORM_OPERATION_URL + "/login");
    return false;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {

    if ("GET".equalsIgnoreCase(request.getMethod()) && modelAndView != null) {
      MessageNotify message = (MessageNotify) SessionUtil
          .getAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY);
      if (message != null) {
        modelAndView.addObject(MessageNotify.codeErr, message.getCode());
        modelAndView.addObject(MessageNotify.mesErr, message.getMessage());
        SessionUtil.removeAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY);
      }

      String requestURI = request.getRequestURI();
      String menu = (String) SessionUtil.getAttribute("menu");
      if (requestURI.contains(DashboardController.DASHBOARD_LIST) || requestURI.contains(TransactionLogController.TRANSACTION_CONTROLLER)
              || requestURI.contains(ReconciliationV3Controller.RECON_CONTROLLER)) {
        menu = (String) SessionUtil.getAttribute("menu");
        if (!"all".equals(menu)) {
          modelAndView.addObject("menu", "ser");
          SessionUtil.setAttribute("menu", "ser");
        }
      } else if (requestURI.contains(FundInOrderController.FUND_IN_ORDER_LIST) || requestURI.contains(FundOutOrderController.FUND_OUT_ORDER_LIST)
          || requestURI.contains(FundinSofController.FUND_SOF_TRANSFER_HISTORY_LIST)
          || requestURI.contains(WalletTransferController.TRANSFER_WALLET_HISTORY_LIST)) {

        menu = (String) SessionUtil.getAttribute("menu");
        if (!"all".equals(menu)) {
          modelAndView.addObject("menu", "wal");
          SessionUtil.setAttribute("menu", "wal");
        }

      } else if (requestURI.contains(ProviderController.PROVIDER_LIST) || requestURI.contains(
          ProviderSpecialSenpayController.PROVIDER_SPECIAL_LIST) || requestURI
          .contains(BALANCE_TOTAL_ASSETS_CONTROLLER)) {
        menu = (String) SessionUtil.getAttribute("menu");
        if (!"all".equals(menu)) {
          modelAndView.addObject("menu", "pro");
          SessionUtil.setAttribute("menu", "pro");
        }
      } else if (requestURI.contains(StaffAccountController.ACCOUNT_MANAGE_LIST) || requestURI
          .contains(StaffAccountController.ACCOUNT_MANAGE_ROLE_LIST) || requestURI.contains(
          StaffAccountController.ACCOUNT_MANAGE_PRIVILEGE_LIST)
          || requestURI.contains(TransactionRuleController.TRANS_RULE_LIST) || requestURI.contains(TrueServiceController.TRUE_SERVICE_LIST)
          || requestURI.contains(TrueServiceController.TRUE_SERVICE_OPERATION)) {
        menu = (String) SessionUtil.getAttribute("menu");
        if (!"all".equals(menu)) {
          modelAndView.addObject("menu", "setting");
          SessionUtil.setAttribute("menu", "setting");
        }
      } else if (requestURI.contains(StaffAccountController.CUSTOMER_MANAGE_LIST)) {
        menu = (String) SessionUtil.getAttribute("menu");
        if (!"all".equals(menu)) {
          modelAndView.addObject("menu", "cus");
          SessionUtil.setAttribute("menu", "cus");
        }
      }

      if (requestURI.contains("menu=")) {
        modelAndView.addObject("uri_all", requestURI.replace("menu=" + menu, "menu=all"));
      } else if (requestURI.contains("?")) {
        modelAndView.addObject("uri_all", requestURI + "&menu=all");
      } else {
        modelAndView.addObject("uri_all", requestURI + "?menu=all");
      }

    } else if (modelAndView != null) {
      modelAndView.addObject("uri_all", "#");
    }
  }

  private void initAttributeToRequest(HttpServletRequest request) {

    long balance = 0;
    Set<String> role = WebUtil.getRolesOfUserLogin();
    boolean roleNotExistBalance = role.contains(RoleConstants.ADMIN_OPERATION) || role.contains(
        RoleConstants.ADMIN_SYSTEM);

    if (!roleNotExistBalance) {
      balance = walletEndpoint.getBalanceUser();
    }

    CallerInformation callerInformation = callerUtil.getCallerInformation();
    SessionUtil.setAttribute(SessionConstants.SESSION_ACCESS_TOKEN, callerInformation.getAccessToken());
    SessionUtil.setAttribute(
        SessionConstants.SESSION_ACCOUNT_LOGIN, new UserLogin(callerInformation, balance));

    String menu = request.getParameter("menu");
    request.setAttribute("menu", menu);
    if (StringUtils.isNotEmpty(menu)) {
      SessionUtil.setAttribute("menu", menu);
    }

    SessionUtil.setAttribute("otp_logined", "1");
  }

}
