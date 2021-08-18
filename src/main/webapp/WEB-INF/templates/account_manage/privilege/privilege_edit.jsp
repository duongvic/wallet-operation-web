<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../include_page/taglibs.jsp" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.type.StatusAccountType" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="setting.account.privilege.title.page"/></title>
  <jsp:include page="../../include_page/head.jsp">
    <jsp:param name="switchLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <jsp:include page="../../include_page/js_merchant.jsp">
    <jsp:param name="switchLib" value="true"/>
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
</head>

<body>
<section class="body">

  <c:set var="listStatusAccount" value="<%= StatusAccountType.values() %>"/>


  <!--        ///////   header ////////-->
  <jsp:include page="../../include_page/header.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../../include_page/navigation.jsp">
      <jsp:param value="set-privilege" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="setting.account.privilege.title.page"/></span></li>
                <li><span class=""><spring:message
                    code="setting.account.privilege.list.title.page"/></span></li>
                <li><span class="nav-active"><spring:message
                    code="setting.account.privilege.add.title.page"/></span></li>

              </ol>
            </div>
          </div>
        </div>
      </header>


      <jsp:include page="../../include_page/message.jsp"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <form action="${contextPath}/staff-account/manage/privilege/details/${privilege_name}" method="POST" id="tbl-filter">
          <div class="container-fluid">

            <div class="form-inline">
              <div class="pull-left h4 mb-md mt-md"><spring:message
                  code="setting.account.privilege.create"/></div>
              <div class="pull-right form-responsive mb-10">
                <%--<sec:authorize access="hasRole('ADMIN_OPERATION')">--%>
                <button class="btn btn-sm btn-info" type="button" id="edit_btn" name="edit" value="create" onclick="editRole()"><i class="fa fa-pencil"></i><spring:message code="common.btn.edit"/>
                </button>
                <button id="save_btn" type="submit" class="btn btn-primary bt-search" disabled><i
                    class="fa fa-save"></i>&nbsp;<spring:message
                    code="common.btn.save"/></button>
                <%--</sec:authorize>--%>
              </div>
            </div>

            <section class="panel search_payment panel-default">
              <div class="panel-body pt-none">
                <div class="clearfix"></div>

                <div class="row">
                  <div class="col-md-6 col-md-offset-3">
                    <div class="form-group">
                      <div class="row">
                        <div class="col-md-4">
                          <label class="form-control-static"><spring:message code="setting.account.privilege.name.label"/> *:</label>
                        </div>
                        <div class="col-md-8">
                          ${privilege_name}
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="row">
                        <div class="col-md-4">
                          <label for="description" class="form-control-static"><spring:message code="setting.account.privilege.description.label"/>:</label>
                        </div>
                        <div class="col-md-8">
                          <textarea name="description" id="description" rows="5"
                                    class="form-control" readonly
                                    placeholder="<spring:message code="setting.account.privilege.description.label"/>">${privilege_description}</textarea>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </section>
          </div>
          <input type="hidden" name="edit_type" value="edit_privilege"/>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
      </div>
      <!-- end: page -->
    </section>
    <%--<jsp:include page="trans_rule_delete.jsp"/>--%>
    <jsp:include page="../../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../../include_page/js_footer.jsp"/>
<script type="text/javascript" src="<c:url value='/assets/javascripts/settings-custom.js'/>"></script>
<script type="text/javascript">
  function editRole() {
    jQuery("#save_btn").attr("disabled", false);
    jQuery("#description").attr("readonly", false);
  }

  $(document).ready(function () {
    $('[data-toggle="popover"]').popover();
  });
</script>

</body>

</html>
