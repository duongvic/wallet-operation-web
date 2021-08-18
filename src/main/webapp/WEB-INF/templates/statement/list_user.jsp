<%@ page import="vn.mog.ewallet.operation.web.controller.statement.StatementController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title>User Statement</title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <jsp:include page="../include_page/js_merchant.jsp">
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
</head>

<body>
<section class="body">
  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>
  <jsp:include page="../include_component/constant_application.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="statement" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span>Statement</span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>
      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md">User Statement Management</div>


          <spring:message code="account.search.placeholder" var="textPlaceHolder"/>
          <spring:message code="fundin.search.btn.search" var="btnSearch" />
          <spring:message code="select.choose.all" var="langChooseAll" />
          <spring:message code="select.status" var="langStatus" />


          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none">
              <form action="" method="GET" id="tbl-filter" modelAttribute="customerDataForm">

                <div class="form-group ml-none mr-none">
                  <div class="input-group input-group-icon">
                    <span class="input-group-addon"><span class="icon" style="opacity: 0.4"><i class="fa fa-search-minus"></i></span></span>
                    <input type="text" id="textSearch" name="textSearch" class="form-control" placeholder="${textPlaceHolder}" value="${param.textSearch}"/>
                  </div>
                </div>

                <div class="col-xs-12 col-lg-12 col-md-12 col-sm-12">
                  <div class="row">

                    <%--select custom type--%>
                    <div class="col-md-3 col-lg-3">
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label class="control-label nowrap" for="customerTypes" style="min-width: 100px"><spring:message code="select.customerType"/></label>
                          </div>
                          <div class="col-md-8">
                            <select data-plugin-selectTwo class="form-control" id="customerTypes" name="customerTypes">
                              <option value=""><spring:message code="label.please.select"/></option>
                              <option value="1" ${fn:contains(param.customerTypes, cusTypeCustomer) ? 'selected' : ''}>CUSTOMER</option>
                              <option value="2" ${fn:contains(param.customerTypes, cusTypeAgent) ? 'selected' : ''}>AGENT</option>
                              <option value="3" ${fn:contains(param.customerTypes, cusTypeMerchant) ? 'selected' : ''}>MERCHANT</option>
                              <option value="11" ${fn:contains(param.customerTypes, cusTypeProvider) ? 'selected' : ''}>PROVIDER</option>
                            </select>
                          </div>
                        </div>
                      </div>
                    </div>

                    <%--select blackList--%>
                    <div class="col-md-3 col-lg-3">
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4 col-md-text-right">
                            <label class="control-label nowrap" for="blackLists"><spring:message code="select.blackList"/> </label>
                          </div>
                          <div class="col-md-8">
                            <select data-plugin-selectTwo class="form-control" id="blackLists" name="blackLists">
                              <option value=""><spring:message code="label.please.select"/></option>
                              <c:choose>
                                <c:when test="${listBlackReason ne null && listBlackReason.size() > 0 }">
                                  <c:forEach var="blackReason" items="${listBlackReason}">
                                    <option value="${blackReason.key}" ${(param.blackLists != null && param.blackLists eq blackReason.key) ? 'selected':''}>
                                      <spring:message code="label.blackListR.${blackReason.key}"/></option>
                                  </c:forEach>
                                </c:when>
                                <c:otherwise>
                                  <option value="">N/A</option>
                                </c:otherwise>
                              </c:choose>
                            </select>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="form-inline">
                  <div class='pull-right form-responsive bt-plt'>
                    <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i>&nbsp;<spring:message code="system.service.list.search.btn.search"/></button>
                  </div>
                </div>
                <div class="clearfix"></div>
              </form>

              <div>Tổng số khách hàng:&nbsp;<span class="primary_color text-semibold">${ewallet:formatNumber(total)}</span>&nbsp;</div>

              <spring:message var="colNo" code="fundin.table.column.no"/>
              <spring:message var="colCode" code="setting.account.tbl.col.account.id"/>
              <spring:message var="colName" code="setting.account.tbl.col.full.name"/>
              <spring:message var="colPhone" code="setting.account.tbl.col.phone"/>
              <spring:message var="colCustomerType" code="setting.account.tbl.col.customer.type"/>
              <spring:message var="colBlackListR" code="setting.account.tbl.col.blacklist.reason"/>
              <spring:message var="colCreateTime" code="setting.account.tbl.col.created.at"/>
              <spring:message var="colAction" code="setting.account.tbl.col.action"/>

              <display:table name="list" id="item"
                             requestURI="list"
                             pagesize="${pagesize}" partialList="true" size="total"
                             class="table table-bordered table-striped mb-none"
                             sort="page">
                <%@ include file="../include_component/display_table.jsp" %>

                <display:column title="${colNo}" headerClass="fit_to_content" class="right">
                  <span id="row${item.id}" class="rowid">
                      <c:out value="${offset + item_rowNum}"/>
                  </span>
                </display:column>

                <display:column title="${colCode}" headerClass="col-number-header" class="col-number-header">
                  <a class="detail-link link-active" id="customer-link-${offset + item_rowNum}"
                     data-customer-id="${item.id}"
                     data-display-name="${item.displayName}"
                     href="#">${item.cif}</a>
                </display:column>
                <display:column title="${colName}">
                  <div class="user-info-box">
                    <div class="avt">
                      <img src="${contextPath}/assets/images/man.svg" class="img-circle list-user-avatar" data-id="${offset + item_rowNum}" data-toggle="modal">
                    </div>
                    <div class="user-info">
                      <p class="user-name">
                        <b>
                          <a id="customer-link-${offset + item_rowNum}" class="detail-link" href="#"
                             data-customer-id="${item.id}"
                             data-display-name="${item.displayName}">
                            <c:if test="${(item.lastName ne null || item.firstName ne null) && item.displayName eq null}">
                              <span class="last-name">${item.lastName}</span>
                              <span class="first-name">${item.firstName}</span>
                            </c:if>
                            <c:if test="${item.displayName ne null}">
                              <span class="last-name">${item.displayName}</span>
                              <span class="first-name"></span>
                            </c:if>
                          </a>
                        </b>
                      </p>
                      <p>${item.email}</p>
                    </div>
                  </div>
                </display:column>
                <display:column title="${colPhone}" class="center" headerClass="center">${item.msisdn}</display:column>
                <display:column title="${colCustomerType}" class="col-number-header" headerClass="col-number-header">${item.customerType.name}</display:column>
                <display:column title="${colBlackListR}" class="col-number-header" headerClass="col-number-header">
                  <c:choose>
                    <c:when test="${item.blackListReason == 0}">
                      <span id="txn-blacklist-reason-${item.id}" class="text-success"><spring:message code="label.blackListR.${item.blackListReason}"/></span>
                    </c:when>
                    <c:otherwise>
                      <span id="txn-blacklist-reason-${item.id}" class="text-danger"><spring:message code="label.blackListR.${item.blackListReason}"/></span>
                    </c:otherwise>
                  </c:choose>
                </display:column>
                <display:column title="${colCreateTime}" class="center" headerClass="center" property="created" format="{0,date,HH:mm:ss dd/MM/yyyy}"/>
                <display:column title="${colAction}" class="center action_icon" headerClass="center action_icon">
                  <a href="#"
                     data-customer-id="${item.id}"
                     data-display-name="${item.displayName}"
                     class="detail-link link-active" title="Xem lịch sử giao dịch">
                    <i class="fa fa-info-circle "></i>
                  </a>
                </display:column>

              </display:table>
            </div>
          </section>
        </div>
      </div>
      <!-- end: page -->
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_page/date_picker.jsp">
  <%--<jsp:param name="isFullTime" value="true"/>--%>
  <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>

<jsp:include page="../include_component/export_excel.jsp"/>
<jsp:include page="../include_component/search_form_commons.jsp">
  <jsp:param name="selCustomer" value="${permitSearchCustomer}"/>
  <jsp:param name="selTxnStatus" value="true"/>
  <jsp:param name="selSourceOfFund" value="true"/>
</jsp:include>
<script src="<c:url value="/assets/development/static/js/transaction.js"/>" async></script>
<script type="text/javascript">
  $(document).ready(function () {
    $('a.detail-link').click(function () {
      var displayName = $(this).data("display-name");
      setCookie('statement_user', displayName, 1, '/');
      window.location.href = ctx + '<%=StatementController.STATEMENT_USER_LIST%>/' +  $(this).data("customer-id");
    });

  });
</script>
</body>
</html>
