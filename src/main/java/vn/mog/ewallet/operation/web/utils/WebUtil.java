package vn.mog.ewallet.operation.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans.AttachmentDTO;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.LogFile;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.StatementAttachment;


public class WebUtil {

  public static final String SSO_PARAM = "sso";
  private static final Logger LOG = LogManager.getLogger(WebUtil.class);
  public static String MERCHANT_DEFAULT = "/template/transaction_logs_template_default.csv";

  public static Set<String> getRolesOfUserLogin() {
    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth != null && auth.isAuthenticated() && auth instanceof OAuth2Authentication) {

        Set<String> grantedAuthorities = auth.getAuthorities().stream().map(authority -> authority.getAuthority())
                .collect(Collectors.toSet());

        return grantedAuthorities;
      }
    } catch (ClassCastException e) {
      LOG.error(e.getMessage(), e);
    }
    return Collections.emptySet();
  }

  public static void exportFile(HttpServletResponse response, Object logFile) throws IOException {
    if (logFile != null) {

      byte[] bytes;
      String contentType, fileName;
      if (logFile instanceof StatementAttachment) {
        StatementAttachment attachment = (StatementAttachment) logFile;
        bytes = attachment.getContent();
        contentType = attachment.getContentType();
        fileName = attachment.getName();
      } else if (logFile instanceof LogFile) {
        LogFile fileLog = (LogFile) logFile;
        bytes = fileLog.getContent();
        contentType = fileLog.getContentType();
        fileName = fileLog.getName();
      } else {
        AttachmentDTO fileLog = (AttachmentDTO) logFile;
        bytes = fileLog.getContent();
        contentType = fileLog.getContentType();
        fileName = fileLog.getName();
      }

      response.reset();
      response.resetBuffer();
      response.setContentType(contentType);
      response.setContentLength(bytes.length);
      response.setCharacterEncoding("UTF-8");
      response.addHeader("Content-disposition", "attachment;filename=" + fileName);

      ServletOutputStream ouputStream = response.getOutputStream();
      ouputStream.write(bytes, 0, bytes.length);
      ouputStream.flush();
      ouputStream.close();
    } else {

      InputStream file = new ClassPathResource(MERCHANT_DEFAULT).getInputStream();
      byte[] bytes = IOUtils.toByteArray(file);

      response.reset();
      response.resetBuffer();
      response.setContentType("text/csv");
      response.setContentLength(bytes.length);
      response.setCharacterEncoding("UTF-8");
      response.addHeader("Content-disposition", "attachment;filename=Log_giao_dich.csv");

      ServletOutputStream ouputStream = response.getOutputStream();
      ouputStream.write(bytes, 0, bytes.length);
      ouputStream.flush();
      ouputStream.close();
    }
  }

}
