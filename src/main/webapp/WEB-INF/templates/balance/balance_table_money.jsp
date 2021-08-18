<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="balance.monitoring.header"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="dateLib" value="true"/>
  </jsp:include>
  <link rel="stylesheet"
        href="<c:url value='/assets/development/static/css/balance_monitoring.css'/>">
  <jsp:include page="../include_page/js_merchant.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="tableLib" value="true"/>
  </jsp:include>
  <script type="text/javascript"
          src="<c:url value='/assets/development/static/js/loader.js'/>"></script>
  <style>
    a:hover, a:focus, .btn-link:hover, .btn-link:focus {
      text-decoration: none !important;
      color: white !important;
    }
  </style>
</head>
<body>
<section class="body">
  <c:set var="locale" value="${pageContext.response.locale}"/>
  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="balance-table-money" name="nav"/>
    </jsp:include>

    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><spring:message
                    code="wallet.balance.balance.monitor"/></span></li>
                <li><span class="nav-active"><spring:message
                    code="label.table.money.coordination"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>
      <div class="content-body-wrap">
        <div class="container-fluid">
          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none">
              <div class="form-inline mb-md mt-md">
                <div class='pull-right form-responsive bt-plt'>
                  <a href="#" class="reportBalanceCus btn btn-primary">&nbsp;<spring:message
                      code="label.send.mail.client.statistical.balance"/> </a>
                  <a href="#" class="reportBalanceProvider btn btn-success"><spring:message
                      code="label.send.mail.inventory.statistics"/> </a>
                </div>
              </div>

              <div class="clearfix"></div>
              <spring:message var="colBalanceCustomer" code="label.balance.customer"/>
              <spring:message var="colBalanceProvider" code="label.balance.provider"/>
              <spring:message var="colDifference" code="label.difference"/>

              <div class="table-responsive">
                <table id="table"
                       class="dataTable mb-none no-footer table table-bordered table-striped"
                       style="margin-bottom: 10px !important;">
                  <thead>
                  <tr>
                    <th colspan="2" class="center">${ewallet:toUpperCase(colBalanceCustomer)}</th>
                    <th colspan="2" class="center">${ewallet:toUpperCase(colBalanceProvider)}</th>
                    <th class="center">${ewallet:toUpperCase(colDifference)}</th>
                  </tr>
                  </thead>

                  <tbody>
                  <tr>
                    <td colspan="2" class="col-number-header primary_color">
                      <spring:message code="balance.monitoring.table.total"/>
                      :&nbsp;${totalCustomerMoney}&nbsp;VND
                    </td>
                    <td colspan="2" class="col-number-header primary_color">
                      <spring:message code="balance.monitoring.table.total"/>
                      :&nbsp;${totalProviderMoney}&nbsp;VND
                    </td>

                    <td class="col-number-header primary_color">
                      ${totalDifference}&nbsp;VND
                    </td>
                  </tr>
                  </tbody>

                  <tfoot>
                  <c:set var="numberProvider" value="${sizeProvider - 1}"/>
                  <c:set var="numberCustomers" value="${sizeCustomers - 1}"/>
                  <c:set var="totalBankMoney" value="0"/>

                  <c:choose>
                    <c:when test="${maxRow ge 1}">
                      <c:forEach var="row" begin="0" end="${maxRow -1}">
                        <tr>
                            <%--Số dư khách hàng--%>
                          <td>
                            <c:if test="${row le numberCustomers}">
                              <c:choose>
                                <c:when test="${balanceCustomers[row].name eq 'AGENT'}">
                                  <%--<a class="detail-link link-active"--%>
                                  <%--href="${contextPath}/wallet/balance/sofcash?search=&range=Tat+ca&customerTypeIds=2&multiselect=2#">AGENT</a>--%>
                                  <p>AGENT</p>
                                </c:when>
                                <c:when test="${balanceCustomers[row].name eq 'MERCHANT'}">
                                  <%--<a class="detail-link link-active"--%>
                                  <%--href="${contextPath}/wallet/balance/sofcash?search=&range=Tat+ca&customerTypeIds=3&multiselect=3#">MERCHANT</a>--%>
                                  <p>MERCHANT</p>
                                </c:when>
                                <c:when test="${balanceCustomers[row].name eq 'CUSTOMER'}">
                                  <%--<a class="detail-link link-active"--%>
                                  <%--href="${contextPath}/wallet/balance/sofcash?search=&range=Tat+ca&customerTypeIds=1&multiselect=1#">CUSTOMER</a>--%>
                                  <p>CUSTOMER</p>
                                </c:when>
                                <c:when test="${balanceCustomers[row].name eq 'SALE'}">
                                  <%--<a class="detail-link link-active"--%>
                                  <%--href="${contextPath}/wallet/balance/sofcash?search=&range=Tat+ca&customerTypeIds=4&multiselect=4#">SALE</a>--%>
                                  <p>SALE</p>
                                </c:when>

                              </c:choose>
                            </c:if>
                          </td>
                          <td class="col-number-header">
                            <c:if test="${row le numberCustomers}">
                              ${ewallet:formatNumber(balanceCustomers[row].money)}&nbsp;VND

                            </c:if>
                          </td>

                            <%--Số dư nhà cung cấp--%>
                          <td>
                            <c:if test="${row le numberProvider}">
                              ${balanceProviders[row].name}
                            </c:if>
                          </td>
                          <td class="col-number-header">
                            <c:if test="${row le numberProvider}">
                              ${ewallet:formatNumber(balanceProviders[row].money)}&nbsp;VND
                            </c:if>
                          </td>

                        </tr>
                      </c:forEach>
                    </c:when>
                    <c:otherwise>
                      <tr>
                        <td class="col-number-header">0</td>
                        <td class="col-number-header">0</td>
                        <td class="col-number-header">0</td>
                      </tr>

                    </c:otherwise>
                  </c:choose>
                  </tfoot>
                </table>
              </div>

            </div>
          </section>
        </div>
      </div>
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_page/date_picker.jsp">
  <jsp:param name="isFullTime" value="true"/>
  <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>

<script type="text/javascript">

  $('a.reportBalanceCus').click(function () {

    var urlAPI = ctx + '/ajax-controller/sendEmail/report/balance/customer';
    $.MessageBox({
        buttonDone: '<spring:message code="popup.button.yes"/>',
        buttonFail: '<spring:message code="popup.button.no"/>',
        message: 'Bạn có muốn gửi mail thống kê số dư khách hàng?'
      }
    ).done(function () {
      $.ajax({
        type: 'GET',
        url: urlAPI,
        timeout: 60000,
        success: function (data) {
          if (data == null || data == "") {
            $.MessageBox({message: 'Call API BackEnd failed!'})
          } else {
            if (data == 'OK') {
              //success
              $.MessageBox({message: 'Success'})
              location.reload();
            } else {
              $.MessageBox({message: 'Call API BackEnd failed!'})
            }
          }
        }
      });
    })
    return false;
  });

  $('a.reportBalanceProvider').click(function () {

    var urlAPI = ctx + '/ajax-controller/sendEmail/report/balance/provider';
    $.MessageBox({
        buttonDone: '<spring:message code="popup.button.yes"/>',
        buttonFail: '<spring:message code="popup.button.no"/>',
        message: 'Bạn có muốn gửi mail thống kê tồn kho?'
      }
    ).done(function () {
      $.ajax({
        type: 'GET',
        url: urlAPI,
        timeout: 60000,
        success: function (data) {
          if (data == null || data == "") {
            $.MessageBox({message: 'Call API BackEnd failed!'})
          } else {
            if (data == 'OK') {
              //success
              $.MessageBox({message: 'Success'})
              location.reload();
            } else {
              $.MessageBox({message: 'Call API BackEnd failed!'})
            }
          }
        }
      });
    })
    return false;
  });

</script>
</body>
</html>