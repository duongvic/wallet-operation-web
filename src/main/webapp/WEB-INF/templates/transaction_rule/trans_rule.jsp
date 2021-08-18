<%@ page import="vn.mog.ewallet.operation.web.controller.translog.TransactionRuleController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="system.tranrule.title.page"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <jsp:include page="../include_page/js_merchant.jsp">
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
</head>

<body>
<section class="body">
  <c:url var="transRuleCon" value='<%=TransactionRuleController.TRANS_RULE_CONTROLLER%>'/>

  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="tranRule" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="movement.rule.navigate.movement.rule"/></span></li>
                <li><span class="nav-active"><spring:message code="movement.rule.navigate.movement.rule.list"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>


      <jsp:include page="../include_page/message.jsp"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">

          <div class="form-inline">
            <div class="pull-left h4 mb-md mt-md"><spring:message code="system.tranrule.subnavigate.tranrule"/></div>
            <div class="pull-right form-responsive">
              <sec:authorize access="hasRole('ADMIN_OPERATION')">
                <a class="mb-xs mt-xs btn btn-success" href="${transRuleCon}/add"><i class="fa fa-plus"></i>&nbsp;<spring:message code="system.service.navigate.btn.create"/></a>
              </sec:authorize>
            </div>
          </div>

          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none">

              <spring:message code="system.tranrule.search.placeholder" var="placeholder"/>
              <spring:message code="select.choose.all" var="langChooseAll"/>
              <spring:message code="select.status" var="langStatus"/>
              <spring:message code="select.service" var="langService"/>
              <spring:message code="select.merchant" var="langMerchant"/>

              <form action="" method="GET" id="tbl-filter">

                <div class="form-group ml-none mr-none">
                  <div class="input-group input-group-icon">
                    <span class="input-group-addon">
                      <span class="icon" style="opacity: 0.4"><i class="fa fa-search-minus"></i></span>
                    </span>
                    <input type="text" id="quickSearch" name="quickSearch" class="form-control" placeholder="${placeholder}" value="${param.quickSearch }"/>
                  </div>
                </div>

                <div class="d-flex justify-content-between">
                  <div class="text-right">

                    <jsp:include page="../include_component/search_service_type_multiple.jsp">
                      <jsp:param name="enableFiltering" value="false"/>
                    </jsp:include>

                    <button type="submit" class="btn btn-primary bt-search"><i class="fa fa-search"></i>&nbsp;<spring:message code="transaction.api.button.search"/></button>
                  </div>
                </div>
              </form>
              <div class="clearfix"></div>

              <spring:message var="colStt" code="system.tranrule.table.col.stt"/>
              <spring:message var="colServiceType" code="system.tranrule.table.col.servicetype"/>
              <spring:message var="colCode" code="system.tranrule.table.col.code"/>
              <spring:message var="colName" code="system.tranrule.table.col.name"/>
              <spring:message var="colAction" code="system.tranrule.table.col.action"/>
              <spring:message var="editMoveRule" code="system.tranrule.table.col.action.edit"/>
              <spring:message var="viewdetail" code="system.tranrule.table.col.action.viewdetail"/>
              <spring:message var="deleteRule" code="system.tranrule.table.col.action.delete"/>


              <display:table name="transRules" id="item"
                             requestURI="list"
                             pagesize="${pagesize}" partialList="true"
                             size="total"
                             sort="page"
                             class="table table-bordered table-responsive table-striped mb-none">

                <%@ include file="../include_component/display_table.jsp" %>

                <display:column title="${colStt}">
                  <span id="row${item.id}" class="rowid">
                    <c:out value="${offset + item_rowNum}"/>
                  </span>
                </display:column>

                <display:column title="${colCode}" property="code"/>
                <display:column title="${colName}" property="name"/>
                <display:column title="${colServiceType}" property="serviceType"/>

                <display:column title="${colAction}" class="action_icon center" headerClass="action_icon center">

                  <a href="${transRuleCon}/updateRule?id=${item.id}" class="link-edit" title="${editMoveRule}">
                    <i class="fa fa-pencil" aria-hidden="true"></i>
                  </a>

                  <a href="#" class="link-active detail-link" title="${viewdetail}" tranRuleId="${item.id}">
                    <i class="fa fa-info-circle "></i>
                  </a>

                  <a href="#" class="link-delete" title="${deleteRule}"
                     data-toggle="modal"
                     data-target="#deleteTransRule"
                     tranRuleId="${item.id}"
                     tranRuleCode="${item.code}"
                     tranRuleName="${item.name}">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                  </a>

                </display:column>
              </display:table>
            </div>
          </section>
        </div>
      </div>
      <!-- end: page -->
    </section>
    <jsp:include page="trans_rule_delete.jsp"/>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_component/search_form_commons.jsp">
  <jsp:param name="selServiceType" value="true"/>
</jsp:include>
<script type="text/javascript">
  $(document).ready(function () {
    $('a.detail-link').click(function () {
      var tranRuleId = $(this).attr("tranRuleId");
      var searchURL = '';
      if (window.location.search.indexOf("?") >= 0) {
        searchURL = ctx + '<%=TransactionRuleController.TRANS_RULE_DETAIL%>' + window.location.search + '&tranRuleId=' + tranRuleId;
      } else {
        searchURL = ctx + '<%=TransactionRuleController.TRANS_RULE_DETAIL%>?' + 'tranRuleId=' + tranRuleId;
      }
      window.location.href = searchURL;
    });
  });

</script>

</body>

</html>
