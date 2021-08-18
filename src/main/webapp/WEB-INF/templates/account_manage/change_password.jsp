<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8"/>
  <title><spring:message code="setting.account.role.title.page"/></title>
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

  <style>
    .note-block ul li {
      display: list-item;
      list-style: inside disc;
    }
  </style>

</head>

<body>
<section class="body">
  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="set-cus" name="nav"/>
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
                    code="account.list.accountManagement"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>


      <%--<jsp:include page="../include_page/message_new.jsp"/>--%>
      <div id="msg-change-password"></div>
      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <section class="panel search_payment panel-default">
            <form method="post" action="">
              <div class="panel-title">
                <div class="form-group row">
                  <h4 class="pull-left"><spring:message code="system.account.change.password"/> </h4>
                  <div class="pull-right">
                    <a href="#" class="btn btn-sm btn-default"
                       onclick="window.history.back()"><spring:message
                        code="system.service.list.search.btn.cancel"/> </a>
                    <a class="btn btn-sm btn-primary"
                       onclick="changePassword()"><i class="fa fa-save"></i>
                      <spring:message code="system.account.change.password"/>
                    </a>
                  </div>
                </div>
              </div>
              <div class="panel-body">
                <div class="row" style="margin-top: 3rem;">
                  <div class="col-md-12 col-xs-12 col-lg-6 col-lg-offset-3">
                    <div class="form-group">
                      <div class="row">
                        <div class="col-md-4"><label for="currentPwd">Mật
                                                                      khẩu hiện tại*</label></div>
                        <div class="col-md-8"><input type="password"
                                                     id="currentPwd"
                                                     name="currentPassword"
                                                     class="form-control"
                                                     autocomplete="off">
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="row">
                        <div class="col-md-4"><label for="newPassword">Mật
                                                                       khẩu mới*</label></div>
                        <div class="col-md-8"><input type="password"
                                                     id="newPassword"
                                                     name="newPassword"
                                                     class="form-control"
                                                     autocomplete="off">
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="row">
                        <div class="col-md-4"><label
                            for="confirmNewPassword">Xác nhận mật khẩu
                                                     mới*</label></div>
                        <div class="col-md-8"><input type="password"
                                                     id="confirmNewPassword"
                                                     name="confirmNewPassword"
                                                     class="form-control"
                                                     autocomplete="off">
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="row">
                        <div class="col-md-8 col-md-offset-4">
                          <div class="checkbox">
                            <label for="confirm_modal_rst_pwd">
                              <input type="checkbox" name="confirm"
                                     id="confirm_modal_rst_pwd"
                                     value="1"> Tôi xác nhận đổi mật
                                                khẩu </label>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="col-md-8 col-md-offset-4 note-block">
                        Chú ý: Mật khẩu bắt buộc:
                        <ul>
                          <li>Có ít nhất 8 ký tự</li>
                          <li>Chứa ít nhất 1chữ in hoa</li>
                          <li>Chứa ít nhất 1 ký tự đặc biệt</li>
                          <li>Bao gồm cả chữ và số</li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <input type="hidden" name="${_csrf.parameterName}"
                     value="${_csrf.token}"/>
            </form>
          </section>
        </div>
      </div>
      <!-- end: page -->
    </section>
    <%--<jsp:include page="trans_rule_delete.jsp"/>--%>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<script type="text/javascript" src="<c:url value='/assets/javascripts/settings-custom.js'/>"></script>
<spring:message code="message.please.confirm.your.action" var="plConfirmAction"/>
<spring:message code="message.change.pass.success" var="changePassSuccess"/>
<spring:message code="message.changpass.currentpass.null" var="currentpassNull"/>
<spring:message code="message.changpass.newpassword.null" var="newpassNull"/>
<spring:message code="message.changpass.password.similar" var="confirmPass"/>
<script src="${contextPath}/assets/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript">
  (function ($) {
    'use strict';
    $(document).ready(function () {
      $(":input").inputmask();
    });
  });

  function changePassword() {
    var currentPassword = jQuery('#currentPwd').val();
    if(currentPassword === null || currentPassword === ''){
      alertMsg('${currentpassNull}', 0, 'msg-change-password');
      return;
    }
    var newPassword = jQuery('#newPassword').val();
    if(newPassword === null || newPassword === ''){
      alertMsg('${newpassNull}', 0, 'msg-change-password');
      return;
    }
    var confirmNewPassword = jQuery('#confirmNewPassword').val();
    if(confirmNewPassword === null || confirmNewPassword === ''){
      if(confirmNewPassword !== newPassword){
        alertMsg('${confirmPass}', 0, 'msg-change-password');
        return;
      }
    }
    //validate checkbox
    if (jQuery('#confirm_modal_rst_pwd').is(':checked') === false) {
      alertMsg('${plConfirmAction}', 0, 'msg-change-password');
      return;
    }
    $.ajax({
      url: ctx + '/ajax-controller/change-password',
      method: 'POST',
      data: {
        currentPassword: currentPassword,
        newPassword: newPassword
      },
      success: function (result) {
        if (result !== null && result !== "" && result.status.code === 0) {
          //success
          alertMsg('${changePassSuccess}', 1, 'msg-change-password');
        } else {
          alertMsg(result.status.value, 0, 'msg-change-password');
        }
      }
    });
  }
</script>
</body>

</html>
