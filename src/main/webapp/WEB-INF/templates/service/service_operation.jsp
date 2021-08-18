<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="system.serOperation.title.page"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/service_operation.css'/>" media="none" onload="if(media!='all')media='all'">
  <jsp:include page="../include_page/js_service.jsp">
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
</head>


<body>
<section class="body">
  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="serOperation" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="system.service.list.navigate.system"/></span></li>
                <li><span class="nav-active"><spring:message code="service.operation.ServiceOperation"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md">
            <spring:message code="service.operation.ServiceOperationMonitoring"/>
          </div>

          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none">

              <form action="" method="GET" id="tbl-filter">
                <spring:message code="select.choose.all" var="langChooseAll" scope="request"/>
                <spring:message code="select.status" var="langStatus"/>
                <spring:message code="select.service" var="langService"/>
                <spring:message code="select.merchant" var="langMerchant"/>
                <spring:message code="select.provider" var="langProvider" scope="request"/>
                <spring:message code="system.service.list.form.search.placeholder" var="placeholder"/>

                <div class="form-group ml-none mr-none">
                  <div class="input-group input-group-icon">
                    <span class="input-group-addon">
                        <span class="icon" style="opacity: 0.4"><i class="fa fa-search-minus"></i></span>
                    </span>
                    <input type="text" id="quickSearch" name="quickSearch" class="form-control" placeholder="${placeholder}" value="${param.quickSearch}"/>
                  </div>
                </div>

                <div class="form-inline">
                  <div class='pull-right form-responsive'>

                    <jsp:include page="../include_component/search_service_type_multiple.jsp">
                      <jsp:param name="enableFiltering" value="false"/>
                    </jsp:include>

                    <c:set var="allStatus" value=","/>
                    <c:forEach var="st" items="${paramValues.trueServiceId}">
                      <c:set var="allStatus" value="${allStatus}${st},"/>
                    </c:forEach>
                    <select class="form-control" name="trueServiceId" multiple="multiple" id="trueServiceId">
                      <c:forEach var="item" items="${trueServices}">
                        <c:set var="status2" value=",${item.id},"/>
                        <option ${fn:contains(allStatus, status2)?'selected':'' } value="${item.id}">${item.serviceName}</option>
                      </c:forEach>
                    </select>
                    <script type="text/javascript">
                      $('#trueServiceId').multiselect({
                        enableFiltering: true,
                        includeSelectAllOption: true,
                        selectAllValue: '',
                        selectAllText: '${langChooseAll}',
                        maxHeight: 400,
                        dropUp: true,
                        nonSelectedText: '${langService}',
                        inheritClass: true,
                        numberDisplayed: 1
                      });
                    </script>

                    <jsp:include page="../include_component/search_provider_type.jsp"/>

                    <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i>&nbsp;<spring:message code="transaction.api.button.search"/></button>
                    <a href="?" class="btn nomal_color_bk bt-cancel"><spring:message code="common.btn.cancel"/></a>

                  </div>
                </div>

                <div class="clr"></div>
              </form>

              <div style="height: 10px">&nbsp;</div>

              <div class="table-responsive qlsp no-per-page">
                <table class="table mb-none mt-none tbl-service-operation">
                  <thead>
                  <tr>
                    <th style="background-color: white !important;border-top: 0px !important;">&nbsp;</th>
                    <c:forEach items="${providers}" var="provider">
                      <th class="${provider.value eq 'Y'.charAt(0) ? 'colorOn' : (provider.value eq 'N'.charAt(0) ? 'colorOff' : 'colorNone')}" style="background: #333 !important;">
                        <div align="center"><b>${provider.key}</b></div>
                        <div align="center"><b>${provider.value eq 'Y'.charAt(0) ? '(ON)' : (provider.value eq 'N'.charAt(0) ? '(OFF)' : '')}</b></div>
                      </th>
                    </c:forEach>
                  </tr>
                  </thead>
                  <tbody>

                  <c:forEach items="${serviceOperations}" var="soItem">
                    <tr>
                      <td class="first-column ${soItem.serviceStatus eq 'Y' ? 'colorOn' : (soItem.serviceStatus eq 'N' ? 'colorOff' : 'colorNone')}">
                        <b>${soItem.serviceName}</b><br>
                        <c:choose>
                          <c:when test="${soItem.serviceStatus eq 'Y'}"> <b>(ON)</b> </c:when>
                          <c:when test="${soItem.serviceStatus eq 'N'}"> <b>(OFF)</b> </c:when>
                          <c:otherwise></c:otherwise>
                        </c:choose>
                      </td>
                      <c:set var="providerOperations" value="${soItem.providerOperations}"/>

                      <c:forEach items="${providerOperations}" var="poItem">
                        <c:set var="statusPO" value="${poItem.providerServiceOperation.providerServiceActive}"/>
                        <c:set var="statusMatching" value="${poItem.providerServiceOperation.serviceMatchingActive}"/>
                        <td class="${(statusPO eq 'Y') && (statusMatching eq 'Y') ? 'colorOn' : ((statusPO eq 'N') && (statusMatching eq 'N') ? 'colorOff' : 'colorNone')}">
                          <c:choose>
                            <c:when test="${(statusPO eq 'Y') && (statusMatching eq 'Y')}">
                              <b>ON</b>
                              <%--1 : <b>${poItem.providerServiceOperation.serviceType}</b><br>
                              2 : <b>${poItem.providerServiceOperation.providerServiceCode}</b><br>
                              3 : <b>${poItem.providerServiceOperation.providerServiceName}</b><br>--%>
                            </c:when>
                            <c:when test="${(statusPO eq 'N') && (statusMatching eq 'N')}">
                              <b>OFF</b>
                              <%--1 : <b>${poItem.providerServiceOperation.serviceType}</b><br>
                              2 : <b>${poItem.providerServiceOperation.providerServiceCode}</b><br>
                              3 : <b>${poItem.providerServiceOperation.providerServiceName}</b><br>--%>
                            </c:when>
                            <c:otherwise></c:otherwise>
                          </c:choose>
                        </td>
                      </c:forEach>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
              <%--<div class="d-flex justify-content-between">
                <div style="line-height: 30px;">(1) Service Type &nbsp; (2) Provider Service Code &nbsp; (3) Provider Service Name</div>
              </div>--%>
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
<jsp:include page="../include_component/search_form_commons.jsp">
  <jsp:param name="selProviderType" value="true"/>
  <jsp:param name="selServiceType" value="true"/>
</jsp:include>
</body>
</html>
