<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.account.StaffAccountController" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.type.StatusAccountType" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8"/>
  <title><spring:message code="account.list.accountManagement"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="switchLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <jsp:include page="../include_page/js_merchant.jsp">
    <jsp:param name="switchLib" value="true"/>
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
    <jsp:param name="dateLib" value="true"/>
  </jsp:include>
</head>

<body>
<section class="body">

  <c:url var="transRuleCon" value='<%=StaffAccountController.ACCOUNT_MANAGE_CONTROLLER%>'/>
  <c:set var="listStatusAccount" value="<%= StatusAccountType.values() %>"/>


  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>
  <!--       //////// content ////////-->
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="set-account" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->

    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><spring:message
                    code="system.account.list.title.page"/></span></li>
                <li><span class="nav-active"><spring:message
                    code="system.account.list.title.reset.password"/></span>
                </li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="tab-content">
          <div id="tab-list-customer" class="tab-pane active">
            <!-- form search -->
            <form id="form-reset-password" class="panel panel-default"
                  action="${contextPath}/staff-account/manage/reset-password/${accountId}" method="post">
              <input type="hidden" name="${_csrf.parameterName}"
                     value="${_csrf.token}"/>
              <div class="panel-title">
                <h4><spring:message code="label.reset.user.password"/></h4>
              </div>
              <div class="panel-body">
                <div class="row">
                  <div class="col-md-6 col-md-offset-3">
                    <div class="form-group">
                      <div class="row">
                        <div class="col-md-4">
                          <label class="form-control-static"><spring:message code="account.list.account"/> *</label>
                        </div>
                        <div class="col-md-8">
                          <div class="form-control-static">
                            <input type="text" id="account"
                                   name="account" class="form-control"
                                   value="${account}"
                                   disabled>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="row">
                        <div class="col-md-4">
                          <label for="notification"
                                 class="form-control-static">Notification *</label>
                        </div>
                        <div class="col-md-8">
                          <div class="" style="margin-bottom: 1rem">
                            <select class="form-control"
                                    data-plugin-selectTwo
                                    name="notification"
                                    id="notification">
                              <option value=""><spring:message code="setting.account.select.status"/>
                              </option>
                              <c:forEach
                                  items="${notification_mode_ids}"
                                  var="notification_mode_id">
                                <option value="${notification_mode_id.key}" ${notification_mode_id.key eq notificationMode ? 'selected' : ''}>
                                  <spring:message
                                      code="setting.account.notification.mode.${notification_mode_id.value}"/>
                                </option>
                              </c:forEach>
                            </select>
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label class="form-control-static">Email *</label>
                          </div>
                          <div class="col-md-8">
                            <div class="input-group">
                                                    <span class="input-group-addon"
                                                          style="border:none">
                                                        <i class="fa fa-exclamation-triangle text-danger"></i>
                                                    </span>
                              <spring:message code="label.not.set.email" var="email_not_set"/>
                              <input type="text" class="form-control"
                                     value="${email == null || email eq '' ? email_not_set : email}"
                                     disabled="">

                              <span class="input-group-addon"
                                    style="border:none">
                                                            <a href="#">Verify Email</a>
                                                    </span>

                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label class="form-control-static"><spring:message code="label.phone"/> *</label>
                          </div>
                          <div class="col-md-8">
                            <div class="input-group">
                                                    <span class="input-group-addon"
                                                          style="border:none">
                                                        <i class="fa fa-exclamation-triangle text-danger"></i>
                                                    </span>
                              <input type="text" class="form-control"
                                     value="${phoneNumber}"
                                     disabled="">

                              <span class="input-group-addon"
                                    style="border:none">
                                                        <a href="#"><spring:message code="label.verify.phone"/></a>
                                                    </span>

                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label for="remark"
                                   class="form-control-static"><spring:message code="account.dialog.remark"/> </label>
                          </div>
                          <div class="col-md-8">
                                                        <textarea name="remark" id="remark" rows="5"
                                                                  class="form-control"
                                                                  required=""></textarea>
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-8 col-md-offset-4">
                            <div class="checkbox">
                              <label for="confirm_body_rst_pwd">
                                <input type="checkbox"
                                       name="confirm"
                                       id="confirm_body_rst_pwd"
                                       required=""><spring:message code="account.dialog.check.agree"/></label>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-8 col-md-offset-4">
                            <button class="btn btn-sm btn-primary"
                                    id="save_btn"
                                    value="Save">
                              <i class="fa fa-save"></i>
                              <spring:message
                                  code="btn.reset.password.send"/>
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </form>
            <!-- end form search -->
          </div>
        </div>
        <!-- end: page -->
      </div>
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
  <!--            //////// end content //////-->
</section>

<jsp:include page="../include_page/js_footer.jsp"/>
<script type="text/javascript" src="<c:url value='/assets/javascripts/settings-custom.js'/>"></script>
</body>

</html>

