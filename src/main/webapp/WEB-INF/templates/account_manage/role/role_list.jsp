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
  </jsp:include>
</head>

<body>
<section class="body">

  <c:set var="transRuleCon">${contextPath}<%=StaffAccountController.ACCOUNT_MANAGE_CONTROLLER%></c:set>
  <c:set var="listStatusAccount" value="<%= StatusAccountType.values() %>"/>


  <!--        ///////   header ////////-->
  <jsp:include page="../../include_page/header.jsp"/>
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
        <div class="container-fluid">

          <div class="form-inline">
            <div class="pull-left h4 mb-md mt-md"><spring:message
                code="setting.account.role.list.title.page"/></div>
            <div class="pull-right form-responsive mb-10">
              <%--<sec:authorize access="hasRole('ADMIN_OPERATION')">--%>
              <a class="mb-xs mt-xs btn btn-success"
                 href="${contextPath}/staff-account/manage/role-add"><i
                  class="fa fa-plus"></i>&nbsp;<spring:message
                  code="system.service.navigate.btn.create"/></a>
              <%--</sec:authorize>--%>
            </div>
          </div>

          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none">

              <spring:message code="setting.account.role.search.placeholder"
                              var="placeholder"/>
              <form action="${contextPath}/staff-account/manage/role-list" id="tbl-filter">
                <div class="clearfix"></div>

                <div class="form-group ml-none mr-none">
                  <div class="col-md-3 col-lg-3 col-sm-5 px-0 mb-10">
                    <label class="col-md-4 col-lg-4 col-sm-5 control-label"
                           for="roleNameSearch">Role
                      Name</label>
                    <div class="col-md-8 col-lg-8 col-sm-7 px-0">
                      <input class="form-control" type="text"
                             id="roleNameSearch"
                             name="role-name"
                             placeholder="Role Name" value="">
                    </div>
                  </div>
                  <div class="col-md-5 row col-lg-5 col-sm-5 px-0 mb-10">
                    <label class="col-md-4 col-lg-4 col-sm-4 text-right">
                      <spring:message code="select.accountStatus"/>
                    </label>
                    <div class="col-md-8 col-lg-8 col-sm-8">
                      <select class="form-control multiple-select"
                              id="status"
                              name="status">
                        <option value="Y" ${param.status != null && param.status eq 'Y' ? 'selected':''}><spring:message
                            code="setting.account.role.status.active"/></option>
                        <option value="N" ${param.status != null && param.status eq 'N' ? 'selected':''}><spring:message
                            code="setting.account.role.status.inactive"/></option>
                      </select>

                      <%--<select class="form-control multiple-select hidden" multiple="multiple" id="customerIds" name="customerIds">--%>
                      <%--<c:choose>--%>
                      <%--<c:when test="${not empty customers && customers.size() > 0 }">--%>
                      <%--<c:forEach var="item" items="${customers}">--%>
                      <%--<option value="${item.id}">${item.fullName} ( ${item.cif} )</option>--%>
                      <%--</c:forEach>--%>
                      <%--</c:when>--%>
                      <%--<c:otherwise>--%>
                      <%--<option value="">N/A</option>--%>
                      <%--</c:otherwise>--%>
                      <%--</c:choose>--%>
                      <%--</select>--%>

                    </div>
                  </div>
                  <div class="col-md-4 col-lg-4 col-sm-2 mb-10 px-0 text-right pull-right pr-none">
                    <button class="btn btn-primary bt-search"><i
                        class="fa fa-search"></i>&nbsp;<spring:message
                        code="transaction.api.button.search"/></button>
                  </div>
                </div>
              </form>
              <div class="clearfix"></div>

              <spring:message var="colRoleName"
                              code="setting.account.tbl.col.role.name"/>
              <spring:message var="colRoleDes"
                              code="setting.account.tbl.col.description"/>
              <spring:message var="colAction" code="setting.account.tbl.col.action"/>
              <spring:message var="actionEdit" code="label.edit"/>
              <spring:message var="actionChangeStatus" code="label.change.status"/>
              <table class="table table-bordered table-responsive table-striped mb-none">
                <thead>
                <tr>
                  <th class="text-center min-w">#</th>
                  <th>${colRoleName}</th>
                  <th>${colRoleDes}</th>
                  <th class="min-w">${colAction}</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="role" items="${listRole}"
                           varStatus="role_id">
                  <tr>
                    <th class="text-center min-w">${role_id.count}</th>
                    <td>${role.role}</td>
                    <td>${role.description}</td>
                    <td class="min-w">
                      <div style="display: inline-flex">
                        <label class="switch"
                               data-toggle="popover"
                               data-trigger="hover"
                               data-placement="top"
                               title=""
                               data-content="${actionChangeStatus}"
                               onclick="return preChangeStatusRole('${role.role}')">
                          <input id="chk${role.role}"
                                 type="checkbox" ${'Y'.charAt(0) eq role.active ? 'checked' : ''}>
                          <span class="slider round"></span>
                        </label>
                        <a href="${contextPath}/staff-account/manage/role-edit/${role.role}"
                           data-toggle="popover"
                           data-trigger="hover"
                           data-placement="top"
                           title="" data-content="${actionEdit}"
                           style="vertical-align: middle;"><i
                            class="fa fa-2x fa-pencil-square text-success"
                            style="padding-left: 10px;"></i>
                        </a>
                      </div>
                    </td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
              <%--<display:table name="listRole" id="item"--%>
              <%--requestURI="${listRole}"--%>
              <%--pagesize="${pagesize}" partialList="true"--%>
              <%--size="total"--%>
              <%--sort="page"--%>
              <%--class="table table-bordered table-responsive table-striped mb-none">--%>

              <%--<%@ include file="../../include_component/display_table.jsp" %>--%>

              <%--<display:column title="#">--%>
              <%--<span id="row${item.id}" class="rowid">--%>
              <%--<c:out value="${offset + item_rowNum}"/>--%>
              <%--</span>--%>
              <%--</display:column>--%>

              <%--<display:column title="${colRoleName}" property="code"/>--%>

              <%--<display:column title="${colRoleDes}" property="name"/>--%>

              <%--<display:column title="${colAction}" class="action_icon center"--%>
              <%--headerClass="action_icon center">--%>

              <%--<a href="${transRuleCon}/updateRule?id=${item.id}"--%>
              <%--class="link-edit"--%>
              <%--title="${editMoveRule}">--%>
              <%--<i class="fa fa-pencil" aria-hidden="true"></i>--%>
              <%--</a>--%>

              <%--<a href="#" class="link-active detail-link"--%>
              <%--title="${viewdetail}"--%>
              <%--tranRuleId="${item.id}">--%>
              <%--<i class="fa fa-info-circle "></i>--%>
              <%--</a>--%>

              <%--<a href="#" class="link-delete" title="${deleteRule}"--%>
              <%--data-toggle="modal"--%>
              <%--data-target="#deleteTransRule"--%>
              <%--tranRuleId="${item.id}"--%>
              <%--tranRuleCode="${item.code}"--%>
              <%--tranRuleName="${item.name}">--%>
              <%--<i class="fa fa-trash"></i>--%>
              <%--</a>--%>
              <%--<a><i class="fa fa-trash"></i></a>--%>
              <%--</display:column>--%>
              <%--</display:table>--%>
            </div>
          </section>
        </div>
      </div>
      <jsp:include page="../../include_component/action_change_status_role.jsp"/>
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

    $('#status').multiselect({
      includeSelectAllOption: true,
      dropUp: false,
      selectAllValue: '',
      selectAllText: '<spring:message code="select.choose.all"/>',
      maxHeight: 400,
      <%--nonSelectedText: '<spring:message code="select.choose.all"/>',--%>
      inheritClass: true,
      enableCaseInsensitiveFiltering: true,
      enableFiltering: true,
      numberDisplayed: 1
    });
  });
</script>

</body>

</html>
