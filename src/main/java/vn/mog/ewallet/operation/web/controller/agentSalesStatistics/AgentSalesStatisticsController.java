package vn.mog.ewallet.operation.web.controller.agentSalesStatistics;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.impl.WalletEndpoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value = "/sales/statistics")
public class AgentSalesStatisticsController extends AbstractController {

  public static final String SALES_STATISTICS_CONTROLLER = "/sales/statistics";
  public static final String SALES_STATISTICS_LIST_ALL = SALES_STATISTICS_CONTROLLER + "/list-all";
  public static final String SALES_STATISTICS_LIST = SALES_STATISTICS_CONTROLLER + "/list";

  private static final Logger LOG = LogManager.getLogger(AgentSalesStatisticsController.class);

  @Autowired
  WalletEndpoint walletEndpoint;

  @RequestMapping(value = "/list-all", method = RequestMethod.GET)
  @PreAuthorize("isAuthenticated()")
  public String searchAll(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    return searchSalesStatistics(request, response, map);
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.SALE_EXCUTIVE + "')")
  public String searchSalesStatistics(HttpServletRequest request, HttpServletResponse response,
                                      ModelMap map) throws FrontEndException {
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
      Date date = new Date();
      map.put("date", dateFormat.format(date));
    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }

    return "/agent_sales_statistics/sales_statistics_logs";
  }

}
