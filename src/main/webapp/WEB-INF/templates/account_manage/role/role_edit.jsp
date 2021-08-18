<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../include_page/taglibs.jsp" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.account.StaffAccountController" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.type.StatusAccountType" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8"/>
  <title><spring:message code="setting.account.role.title.page"/></title>
  <jsp:include page="../../include_page/head.jsp">
    <jsp:param name="switchLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <jsp:include page="../../include_page/js_merchant.jsp">
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
  <jsp:include page="../../include_page/header.jsp"/>
  <!--       //////// content ////////-->
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../../include_page/navigation.jsp">
      <jsp:param value="set-role" name="nav"/>
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
                    code="setting.account.role.title.page"/></span></li>
                <li><span class="nav-active"><spring:message
                    code="setting.account.role.list.title.page"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../../include_page/message.jsp"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="alert alert-success fade in hidden">
          <p><spring:message code="setting.account.role.update.warning.success"/></p>
        </div>
        <div class="alert alert-warning fade in hidden">
          <p><spring:message code="setting.account.role.update.warning.fail"/></p>
        </div>
        <!-- page -->
        <div class="tab-content">
          <div id="tab-list-customer" class="tab-pane active">
            <!-- form search -->
            <form id="${'edit' eq edit ? 'form-role-detail' : 'form-role-add'}"
                  action="${contextPath}/staff-account/manage/role-edit/${roleId}"
                  class="panel panel-default" method="post">
              <input type="hidden" id="edit-input" name="edit-input" value="${edit}">
              <input type="hidden" id="csrf-token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              <div class="panel-title">
                <h4><spring:message code="setting.account.role.detail.label"/></h4>
                <div class="panel-tools">
                  <c:if test="${roleId != null && roleId ne ''}">
                    <button class="btn btn-sm btn-success" type="button"
                            id="edit_btn"
                            name="edit" value="create" onclick="editRole()">
                      <i class="fa fa-pencil"></i>
                      <spring:message code="label.edit"/>
                    </button>
                  </c:if>
                  <button class="btn btn-sm btn-primary" id="save_btn"
                          onclick="onSubmit()"
                          value="Save" name="edit"
                  ${'edit' eq edit ? '' : 'type="button"'}
                  ${(roleId != null && roleId ne '') ? 'disabled' : ''}>
                    <i class="fa fa-save"></i>
                    <spring:message code="label.save"/>
                  </button>
                </div>
              </div>
              <div class="panel-body">
                <div class="row">
                  <div class="form-group">
                    <div class="col-md-5">
                      <label class="col-md-4 control-label" for="role-name">
                        <spring:message
                            code="setting.account.role.input.name.required.label"/>
                      </label>
                      <div class="col-md-8">
                        ${roleId}
                        <input class="form-control"
                        ${(roleId != null && roleId ne '') ? 'type="hidden"' : ''}
                               id="role-name" name="role-name"
                               value="${roleId}">
                      </div>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group">
                    <div class="col-md-5">
                      <label class="col-md-4 control-label" for="remark">
                        <spring:message
                            code="setting.account.role.input.description.label"/>
                      </label>
                      <div class="col-md-8">
                                                <textarea class="form-control" rows="2"
                                                          id="remark"
                                                          name="remark"
                                                ${(roleId != null && roleId ne '') ? 'disabled' : ''}>${roleDescription}</textarea>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="panel-title">
                <h4><spring:message code="setting.account.privilege.label"/></h4>
                <div class="panel-tools">
                  <button class="btn btn-sm btn-primary" type="button"
                          onclick="createRolePrivilegeModal()"
                          name="grant-privilege"
                  ${(roleId != null && roleId ne '') ? '' : 'disabled'}>
                    <i class="fa fa-users"></i><spring:message
                      code="setting.account.privilege.add.label"/>
                  </button>
                </div>
              </div>
              <div class="panel-body">
                <spring:message var="actionEdit" code="label.edit"/>
                <spring:message var="actionDelete" code="label.delete"/>
                <table class="table table-bordered table-responsive table-striped mb-none"
                       id="datatable-role">
                  <thead style="line-height: 22px; cursor: pointer;">
                  <tr>
                    <th class="min-w">#</th>
                    <th><spring:message
                        code="setting.account.privilege.name.label"/></th>
                    <th><spring:message
                        code="setting.account.privilege.from.date.label"/></th>
                    <th><spring:message
                        code="setting.account.privilege.to.date.label"/></th>
                    <th><spring:message
                        code="setting.account.privilege.description.label"/></th>
                    <th class="text-center min-w"><spring:message
                        code="setting.account.privilege.action.label"/></th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="rolePrivileges"
                             items="${rolePrivileges}"
                             varStatus="role_privileges_id">
                    <tr class="${rolePrivileges.privilege}">
                      <th class="min-w">${role_privileges_id.count}</th>
                      <td>${rolePrivileges.privilege}</td>
                      <td><fmt:formatDate
                          value="${rolePrivileges.validFrom}"
                          pattern="dd/MM/yyyy"/></td>
                      <td><fmt:formatDate
                          value="${rolePrivileges.validTo}"
                          pattern="dd/MM/yyyy"/></td>
                      <td></td>
                      <td>
                        <div style="display: inline-flex">
                          <i class="fa fa-2x fa-pencil-square text-success"
                             data-toggle="popover"
                             data-trigger="hover"
                             data-placement="top"
                             title="" data-content="${actionEdit}"
                             onclick="editRolePrivilegeModal('${rolePrivileges.privilege}',this)"
                             style="cursor: pointer;"
                             aria-hidden="true"></i>
                          &nbsp;
                          <i class="fa fa-2x fa-trash-o text-danger"
                             data-toggle="popover"
                             data-trigger="hover"
                             data-placement="top"
                             title="" data-content="${actionDelete}"
                             onclick="deleteRolePrivilegeModal('${rolePrivileges.privilege}');"
                             style="cursor: pointer;"></i>
                        </div>
                      </td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </form>
            <!-- end form search -->
          </div>
        </div>
        <jsp:include page="../../include_component/action_grant_privilege.jsp"/>
        <jsp:include page="../../include_component/action_role_delete.jsp"/>
        <!-- end: page -->
      </div>
    </section>
    <jsp:include page="../../include_page/footer.jsp"/>
  </div>
  <!--            //////// end content //////-->
</section>

<jsp:include page="../../include_page/js_footer.jsp"/>
<script type="text/javascript" src="<c:url value='/assets/javascripts/settings-custom.js'/>"></script>
</body>

<script>
  function editRole() {
    jQuery("#save_btn").attr("disabled", false);
    jQuery("#remark").attr("disabled", false);
    jQuery("#edit-input").attr("disabled", false);
    jQuery("#csrf-token").attr("disabled", false);
  }

  function onSubmit() {
    var roleAddForm = jQuery('#form-role-add');

    if ('edit' !== '${edit}') {
      roleAddForm.attr('action', encodeURI('${contextPath}/staff-account/manage/role-edit/'.concat(jQuery('#role-name').val())));
    }
    roleAddForm.submit();
  }
</script>

</html>

