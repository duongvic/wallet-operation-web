<%@ page language="java" trimDirectiveWhitespaces="true"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include_page/taglibs.jsp" %>
<%@ page
    import="vn.mog.ewallet.operation.web.controller.account.StaffAccountController" %>
<%@ page
    import="vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.type.StatusAccountType" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message
      code="setting.account.privilege.title.page"/></title>
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

  <c:set var="addPrivilegeControl">${contextPath}<%=StaffAccountController.ACCOUNT_MANAGE_PRIVILEGE_ADD%></c:set>
  <c:set var="listStatusAccount"
         value="<%= StatusAccountType.values() %>"/>


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
                <li><span><spring:message
                    code="setting.account.privilege.title.page"/></span></li>
                <li><span class="nav-active"><spring:message
                    code="setting.account.privilege.list.title.page"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>


      <jsp:include page="../../include_page/message.jsp"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">

          <div class="form-inline">
            <div class="pull-left h4 mb-md mt-md">
              <spring:message code="setting.account.privilege.list.title.page"/>
            </div>
            <div class="pull-right form-responsive mb-10">
              <%--<sec:authorize access="hasRole('ADMIN_OPERATION')">--%>
              <a class="mb-xs mt-xs btn btn-success" style="margin-right: 1.5rem;"
                 href="${addPrivilegeControl}"><i class="fa fa-plus"></i>&nbsp;<spring:message
                  code="system.service.navigate.btn.create"/></a>
              <%--</sec:authorize>--%>
            </div>
          </div>

          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none">

              <spring:message
                  code="setting.account.privilege.search.placeholder"
                  var="placeholderPrivilege"/>

              <form action="" method="${contextPath}/staff-account/manage/list-privilege" id="tbl-filter">

                <div class="clearfix"></div>

                <div class="form-group ml-none mr-none">
                  <div class="col-md-3 col-lg-3 col-sm-5 px-0 mb-10">
                    <div class="input-group input-group-icon">
												<span class="input-group-addon"> <span class="icon"
                                                               style="opacity: 0.4"><i class="fa fa-search-minus"></i></span>
												</span> <input type="text" id="privilegeNameSearch" name="privilege_name"
                                       class="form-control" placeholder="${placeholderPrivilege}"
                                       value="${privilege_name}">
                    </div>
                  </div>
                  <div class="col-md-5 row col-lg-5 col-sm-5 px-0 mb-10">
                    <label class="col-md-4 col-lg-4 col-sm-4 text-right"><spring:message
                        code="select.accountStatus"/> </label>
                    <div class="col-md-8 col-lg-8 col-sm-8">
                      <select data-plugin-selectTwo class="form-control"
                              id="status" name="status">
                        <option value=""><spring:message
                            code="label.please.select"/></option>
                        <option value="Y" ${status eq 'Y' ? 'selected' : ''}><spring:message
                            code="setting.account.role.status.active"/></option>
                        <option value="N" ${status eq 'N' ? 'selected' : ''}><spring:message
                            code="setting.account.role.status.inactive"/></option>
                      </select>



                    </div>
                  </div>
                  <div
                      class=""col-md-4 col-lg-4 col-sm-2 mb-10 px-0 text-right pull-right pr-none>
                    <button type="submit" class="btn btn-primary bt-search" style="margin-top: 1.5rem">
                      <i class="fa fa-search"></i>&nbsp;
                      <spring:message code="transaction.api.button.search"/>
                    </button>
                  </div>
                </div>
              </form>
              <div class="clearfix"></div>

              <spring:message var="colPrivilegeName"
                              code="setting.account.tbl.col.privilege.name"/>
              <spring:message var="colPrivilegeDes"
                              code="setting.account.tbl.col.privilege.description"/>
              <spring:message var="colAction"
                              code="setting.account.tbl.col.privilege.status"/>
              <spring:message var="actionChangeStatus"
                              code="label.change.status"/>
              <table
                  class="table table-bordered table-responsive table-striped mb-none">
                <thead>
                <th>${colPrivilegeName}</th>
                <th>${colPrivilegeDes}</th>
                <th class="min-w">${colAction}</th>
                </thead>
                <tbody>
                <c:forEach var="privilege" items="${listPrivilege}"
                           varStatus="privilege_id">
                  <tr>
                    <td>${privilege.privilege}</td>
                    <td>${privilege.description}</td>
                    <td class="min-w text-center">
                      <div style="display: inline-flex">
                        <label class="switch" data-toggle="popover"
                               data-trigger="hover" data-placement="top" title=""
                               data-content="${actionChangeStatus}"
                               onclick="return preChangeStatusPrivilege('${privilege.privilege}')">
                          <input id="chk${privilege.privilege}" type="checkbox"
                            ${'Y'.charAt(0) eq privilege.active ? 'checked' : ''}>
                          <span class="slider round"></span>
                        </label> <a href="${contextPath}/staff-account/manage/privilege/details/${privilege.privilege}" data-toggle="popover" data-trigger="hover"
                                    data-placement="top" title=""
                                    data-content="${actionEdit}"
                                    style="vertical-align: middle;"><i
                          class="fa fa-2x fa-pencil-square text-success"
                          style="padding-left: 10px;"></i></a>
                      </div>

                    </td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </section>
        </div>
      </div>
      <jsp:include page="../../include_component/action_change_status_privilege.jsp"/>
      <!-- end: page -->
    </section>
    <jsp:include page="../../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../../include_page/js_footer.jsp"/>
<script type="text/javascript" src="<c:url value='/assets/javascripts/settings-custom.js'/>"></script>
<script type="text/javascript">
  $(document).ready(function () {
    $('[data-toggle="popover"]').popover();
  });
</script>

</body>

</html>
