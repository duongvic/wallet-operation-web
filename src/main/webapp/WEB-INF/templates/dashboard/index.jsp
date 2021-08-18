<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="dashboard.title"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/dashboard.css'/>" media="none" onload="if(media!='all')media='all'">
  <jsp:include page="../include_page/js.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
</head>

<body>
<section class="body">
  <jsp:include page="../include_page/header.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="dashboard" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span>Dashboard</span>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <div class="content-body-wrap sulist" style="margin-bottom: 8px;">
        <div class="container-fluid">
          <div class="h4 mb-md">
            Dashboard
          </div>
          <form action="#" method="GET" id="tbl-filter">

            <div class="form-inline">
              <div class='pull-left form-responsive'>

                <jsp:include page="../include_component/date_range.jsp"/>     
                <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_LEADER')" var="permitSearchCustomer">
                  <jsp:include page="../include_component/search_customer.jsp">
                    <jsp:param name="isFilter" value="true"/>
                  </jsp:include>
                </sec:authorize>           
              </div>

              <div class='pull-right form-responsive'>             
                <select name="service" id="service" class="form-control" style="margin-bottom: 6px">
                  <option value=""><spring:message code="dashboard.service"/></option>
                  <c:forEach items="${serviceTypes }" var="item">
                    <c:if test="${item.key != 'FUND_IN' && item.key != 'FUND_OUT' }">
                      <option value="${item.key }" ${item.key == param.service ? 'selected':'' }>${item.value }</option>
                    </c:if>
                  </c:forEach>
                </select>
              </div>
            </div>
          </form>
        </div>
      </div>

      <div class="row content-body-wrap sulist" style="background: none;margin-bottom: -1.5px;">

        <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 dashboard-item dashboard-item-left">
          <section class="panel">
            <div class="panel-body">
              <div class="sulist_item clearfix">
                <span>
                  <spring:message code="dashboard.successful-transaction"/> (<span class="text-success"><fmt:formatNumber type="percent" maxFractionDigits="2" value="${rate }"/></span>)
                </span>
                <h3>
                  <fmt:formatNumber maxFractionDigits="3" value="${total }"/>
                  <span style="font-size: 13px; font-weight: 500;"> </span>
                </h3>
              </div>
            </div>
          </section>
        </div>

        <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 dashboard-item dashboard-item-center">
          <section class="panel">
            <div class="panel-body">
              <div class="sulist_item clearfix">
                <span><spring:message code="dashboard.amount"/> (VND)</span>
                <h3>
                  <fmt:formatNumber maxFractionDigits="3" value="${realAmount }"/>
                  <span style="font-size: 13px; font-weight: 500;"> đ</span>
                </h3>
              </div>
            </div>
          </section>
        </div>

        <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 dashboard-item dashboard-item-center">
          <section class="panel">
            <div class="panel-body">
              <div class="sulist_item clearfix">
                <span><spring:message code="dashboard.fee"/> (VND)</span>
                <h3>
                  <fmt:formatNumber maxFractionDigits="3" value="${fee }"/>
                  <span style="font-size: 13px; font-weight: 500;"> đ</span>
                </h3>
              </div>
            </div>
          </section>
        </div>

        <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 dashboard-item dashboard-item-right">
          <section class="panel">
            <div class="panel-body">
              <div class="sulist_item clearfix">
                <span><spring:message code="dashboard.commission"/> (VND)</span>
                <h3>
                  <fmt:formatNumber maxFractionDigits="3" value="${commision }"/>
                  <span style="font-size: 13px; font-weight: 500;"> đ</span>
                </h3>
              </div>
            </div>
          </section>
        </div>

      </div>
      <div class="row content-body-wrap">
        <div class="col-md-12 col-lg-12 col-xl-12">
          <section class="panel panel-default su_chart">
            <div class="panel-body">
              <div class="summarylist">
                <div class="area_chart panel_mar mt-none pl-md" style="border-top: none; height: 150px">
                  <div class="h4 mb-md"><spring:message code="dashboard.total-transaction"/></div>
                </div>
              </div>
              <div style="margin: 0 -15px;">
                <div class="col-md-12 col-lg-12 col-xl-12">
                  <div class="tabs">
                    <ul class="nav nav-tabs">
                      <li class="active"><a href="#tab1" data-toggle="tab"><spring:message code="dashboard.service"/></a></li>
                      <li><a href="#tab2" data-toggle="tab"><spring:message code="dashboard.telco"/></a></li>
                      <li><a href="#tab3" data-toggle="tab"><spring:message code="dashboard.price"/></a></li>
                    </ul>
                    <div class="tab-content">
                      <div id="tab1" class="tab-pane active">
                        <section class="panel panel-default">
                          <div class="panel-body">
                            <div class="row">
                              <div class="col-xs-12 col-sm-12 col-md-7 col-lg-7">
                                <div class="leftchart">
                                </div>
                              </div>
                              <div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
                                <div class="rightchart">
                                </div>
                              </div>
                            </div>
                          </div>
                        </section>
                      </div>
                      <div id="tab2" class="tab-pane"></div>
                      <div id="tab3" class="tab-pane"></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>
        </div>
        <!--end line-summery-->
      </div>
      <!-- end: page -->
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>

<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_page/date_picker.jsp"/>
<jsp:include page="../include_component/search_form_commons.jsp">
  <jsp:param name="selCustomer" value="${permitSearchCustomer}"/>
</jsp:include>
<script type="text/javascript">
  $(document).ready(function () {
    $('select[name="service"]').on('change', function () {
      $('#tbl-filter').submit();
    });
  });
</script>
</body>

</html>