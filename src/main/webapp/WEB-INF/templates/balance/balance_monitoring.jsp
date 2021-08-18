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
  <%--<script type="text/javascript" src="<c:url value='/assets/development/static/js/oneweek/jquery.table.hpaging.min.js'/>"></script>--%>
  <script type="text/javascript"
          src="<c:url value='/assets/development/static/js/loader.js'/>"></script>
</head>
<body>
<section class="body">
  <c:set var="locale" value="${pageContext.response.locale}"/>
  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="balance-mo" name="nav"/>
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
                    code="wallet.balance.submenu.dashboard"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>
      <!-- start: page -->

      <!-- start: block1 -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <%------ BEGIN draw pie chart-------%>
          <div class="show-infor" onclick="showHidden_fc()">
            <div class="text"><spring:message
                code="balance.monitoring.nav.title"/></div>
            <div class="icon">
              <i class="fa fa-chevron-down text-xs"></i>
            </div>
          </div>
          <div id="showHiden" class="main-content">
            <div class="">
              <%--<div class="content-pie-chart">--%>
              <div id="chart" style="width: 100%">
                <div class="h4"><spring:message
                    code="wallet.balance.percentBalance"/></div>
                <%--<div id="piechart" class="draw-pieChart" style="width: 100%"></div>--%>
                <div class="content-main">
                  <div class="content-block1">
                    <div id="chart_div" class="draw-pieChart"></div>
                  </div>
                  <div class="content-block2">
                    <div id="legend"></div>
                  </div>
                </div>
                <div class="h4" style="width: 100%">
                  <p id="piechart2">
                    <spring:message
                        code="balance.monitoring.pie.total.initpoolbalance"/>: ${totalInitPoolBalance}&nbsp;VND&nbsp;&nbsp;
                    <spring:message code="balance.monitoring.pie.totalbalance"/>: ${totalInitiateBalance}&nbsp;VND
                  </p>
                </div>
              </div>
              <div id="no-chart" style="width: 100%" class="hidden">
                <div class="draw-no-data">
                  <div class="draw-circle">
                    <div class="draw-circle-text">
                      <spring:message code="balance.monitoring.pie.nodata"/>
                    </div>
                  </div>
                </div>
              </div>
              <%------END draw pie chart-------%>
            </div>

            <div class="">
              <%--<div class="content-table-chart">--%>
              <div class="table-responsive">
                <table id="table"
                       class="dataTable mb-none no-footer table table-bordered table-striped"
                       style="margin-bottom: 10px !important;">
                  <thead>
                  <tr>
                    <th colspan="2" class="center"><spring:message
                        code="balance.monitoring.table.fa"/></th>
                    <th colspan="2" class="center"><spring:message
                        code="balance.monitoring.table.wallet"/></th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:set var="numberFAs" value="${sizeFAs - 1}"/>
                  <c:set var="numberBanks" value="${sizeBanks - 1}"/>
                  <c:set var="numberCustomers" value="${sizeCustomers - 1}"/>
                  <c:set var="totalBankMoney" value="0"/>

                  <c:choose>
                    <c:when test="${maxRow ge 1}">
                      <c:forEach var="row" begin="0" end="${maxRow -1}">
                        <tr>
                          <td>
                            <c:if test="${row le numberFAs}">
                              ${balanceFAs[row].name}
                            </c:if>
                          </td>
                          <td class="col-number-header">
                            <c:if test="${row le numberFAs}">
                              ${ewallet:formatNumber(balanceFAs[row].money)}&nbsp;VND
                            </c:if>
                          </td>
                          <td>
                            <c:if test="${row le numberCustomers}">
                              <c:choose>
                                <c:when test="${balanceCustomers[row].name eq 'AGENT'}">
                                  <a class="detail-link link-active"
                                     href="${contextPath}/wallet/balance/sofcash?search=&range=Tat+ca&customerTypeIds=2&multiselect=2#">AGENT</a>
                                </c:when>
                                <c:when test="${balanceCustomers[row].name eq 'ZO-TA'}">
                                  <a class="detail-link link-active"
                                     href="${contextPath}/wallet/balance/sofcash?search=&range=Tat+ca&customerTypeIds=5&multiselect=5#">ZO-TA</a>
                                </c:when>
                                <c:when test="${balanceCustomers[row].name eq 'PROVIDER'}">
                                  <a class="detail-link link-active"
                                     href="${contextPath}/wallet/balance/sofcash?search=&range=Tat+ca&customerTypeIds=11&multiselect=11#">PROVIDER</a>
                                </c:when>
                                <c:when test="${balanceCustomers[row].name eq 'CUSTOMER'}">
                                  <a class="detail-link link-active"
                                     href="${contextPath}/wallet/balance/sofcash?search=&range=Tat+ca&customerTypeIds=1&multiselect=1#">CUSTOMER</a>
                                </c:when>
                                <c:when test="${balanceCustomers[row].name eq 'MERCHANT'}">
                                  <a class="detail-link link-active"
                                     href="${contextPath}/wallet/balance/sofcash?search=&range=Tat+ca&customerTypeIds=3&multiselect=3#">MERCHANT</a>
                                </c:when>
                                <c:when test="${balanceCustomers[row].name eq 'PROPERTY_MANAGER'}">
                                  <a class="detail-link link-active"
                                     href="${contextPath}/wallet/balance/sofcash?search=&range=Tat+ca&customerTypeIds=12&multiselect=12#">PROPERTY MANAGER</a>
                                </c:when>
                              </c:choose>


                            </c:if>
                          </td>
                          <td class="col-number-header">
                            <c:if test="${row le numberCustomers}">
                              ${ewallet:formatNumber(balanceCustomers[row].money)}&nbsp;VND

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
                  </tbody>

                  <tfoot>
                  <tr>
                    <td colspan="2" class="col-number-header primary_color">
                      <spring:message code="balance.monitoring.table.total"/>
                      :&nbsp;${totalFAMoney}&nbsp;VND
                    </td>
                    <td colspan="2" class="col-number-header primary_color">
                      <spring:message code="balance.monitoring.table.total"/>
                      :&nbsp;${totalCustomerMoney}&nbsp;VND
                    </td>
                  </tr>
                  </tfoot>
                </table>
              </div>
            </div>
          </div>
        </div>
        <%--</div>--%>
      </div>

      <!-- end: block1  -->
      <!-- end: page -->
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
  function formatNumberSeparator(x, locale) {
    var locale = "vi";
    var separator = ",";
    if (locale == "vi") {
      separator = ".";
    }
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, separator);
  }

  function showHidden_fc() {
    var x = document.getElementById('showHiden');
    if (x.style.display === 'none') {
      x.style.display = 'block';
    } else {
      x.style.display = 'none';
    }
  }

  // Load Google Charts
  google.charts.load('visualization', '1.1',
    {packages: ['corechart', 'line'], callback: drawChart});
  var ZTA = ${empty ZOTA ? 0 : ZOTA};
  var SOF = ${empty SOF ? 0 : SOF};
  var MERCHANT = ${empty MERCHANT ? 0 : MERCHANT};
  var CUSTOMER = ${empty CUSTOMER ? 0 : CUSTOMER};
  var SALE = ${empty SALE ? 0 : SALE};
  var AGENT = ${empty AGENT ? 0 : AGENT};
  var POOL = ${empty POOL ? 0 : POOL};
  var PROVIDER = ${empty PROVIDER ? 0 : PROVIDER};
  var PROPERTY_MANAGER = ${empty PROPERTY_MANAGER ? 0 : PROPERTY_MANAGER};
  var data = [
    ['Agent', AGENT],
    ['Customer', CUSTOMER],
    ['Merchant', MERCHANT],
    ['Provider', PROVIDER],
    ['Actual System Account', POOL],
    ['Sof', SOF],
    ['ZO-TA', ZTA],
    ['Property Manager', PROPERTY_MANAGER]
//    ['Sale', SALE],

  ];

  // Callback when API is ready
  function drawChart() {
    drawPieFull();
  }

  function drawPieFull() {
    var chartSelector = '#piechart';
    var labelSelector = '> g:eq(1) g text';
    var dataSof = [], sof;
    dataSof.push(
      ['Agent', AGENT],
      ['Customer', CUSTOMER],
      ['Merchant', MERCHANT],
      ['Provider', PROVIDER],
      ['Actual System Account', POOL],
      ['ZO-TA', ZTA],
      ['Property Manager', PROPERTY_MANAGER]
//        ['Sale', SALE],
    );
    <c:forEach var="item" items="${arrayOfSOF}">
    sof = ['${item.key}', ${item.value}];
    dataSof.push(sof);
    </c:forEach>

    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Topping');
    data.addColumn('number', 'Slices');
    data.addRows(dataSof);
    var colors = [];
    while (colors.length < 100) {
      do {
        var color = Math.floor((Math.random() * 16777215) + 1);
      } while (colors.indexOf(color) >= 0);
      colors.push("#" + ("000000" + color.toString(16)).slice(-6));
    }
    var options = {
//      title: 'Percentage Of Wallet Balance',
      titleTextStyle: {
        fontSize: 16
      },
      colors: colors,
      sliceVisibilityThreshold: 0,
      legend: {
        position: 'none'
      }
    };
    var legend = document.getElementById('legend');
    var legendItem = [];
    var total = 0;
    for (var i = 0; i < data.getNumberOfRows(); i++) {
      // increment the total
      total += data.getValue(i, 1);
    }
    for (var i = 0; i < data.getNumberOfRows(); i++) {
      var label = data.getValue(i, 0);
      var value = data.getValue(i, 1);
      var percent = Number(100 * value / total).toFixed(2);

      // create the legend entry
      legendItem[i] = document.createElement('p');
      legendItem[i].setAttribute('data-row', i);
      legendItem[i].setAttribute('data-value', value);
      legendItem[i].id = 'legend_' + data.getValue(i, 0);
      legendItem[i].innerHTML = '<div class="legendMarker"> <span style="color:' + colors[i]
        + ';"> '
        + "<span style='font-size: 2.5em;margin-right: .25em;vertical-align: -.1em'>&#9632;</span>"
        + '</span>'
        + '	<span style="font-size: 16px "> ' + label + ' : ' + formatNumberSeparator(value,
          "${locale}") + ' VND ' + ' (' + percent + '%)</span></div>';
      // append to the legend legendItemt
      legend.appendChild(legendItem[i]);
    }
    for (var i = 0; i < data.getNumberOfRows(); i++) {
      // increment the total
      total += data.getValue(i, 1);
      if (total > 0) {
        $('#no-chart').remove();
        $('#chart').add();
        // Create the Google Pie Chart
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
        $('#legend p').hover(function () {
          chart.setSelection([{row: $(this).data('row'), column: null}]);
        }, function () {
          chart.setSelection(null);
        })
      } else {
        $('#chart').remove();
        $('#no-chart').add().removeClass('hidden');
      }
    }

    /*

     * Setup the data table with your data.
     */
    <%--var table = new google.visualization.DataTable({--%>
    <%--cols: [--%>
    <%--{id: 'name', label: 'Name', type: 'string'},--%>
    <%--{id: 'value', label: 'Value', type: 'number'}--%>
    <%--]--%>
    <%--});--%>
    <%--// Add data to the table, index cua table--%>
    <%--table.addRows(dataSof);--%>
    <%--var options = {--%>
    <%--title: 'Percentage Of Wallet Balance',--%>
    <%--titleTextStyle: {--%>
    <%--fontSize: 14--%>
    <%--},--%>
    <%--width: 899,--%>
    <%--height: 600,--%>
    <%--vAxis: {maxValue: 1},--%>
    <%--hAxis: {maxValue: 10},--%>
    <%--chartArea: {  width: "73%", height: "70%" },--%>
    <%--sliceVisibilityThreshold: 0  // no display slice other--%>
    <%--};--%>
    <%--// Google Charts needs a raw element. I'm using jQuery to get the chart--%>
    <%--// Container, then indexing into it to get the raw element.--%>
    <%--var chartContainer = $(chartSelector)[0];--%>
    <%--var total = getTotal(data);--%>

    <%--function getTotal(dataArray) {--%>
    <%--var total = 0;--%>
    <%--for (var i = 0; i < dataArray.length; i++) {--%>
    <%--total += dataArray[i][1];--%>
    <%--}--%>
    <%--return total;--%>
    <%--}--%>

    <%--// Changing legend--%>
    <%--if (total != 0) {--%>
    <%--$('#no-chart').remove();--%>
    <%--$('#chart').add();--%>
    <%--// Create the Google Pie Chart--%>
    <%--var chart = new google.visualization.PieChart(chartContainer);--%>
    <%--// Draw the chart.--%>
    <%--chart.draw(table, options);--%>
    <%--} else {--%>
    <%--$('#chart').remove();--%>
    <%--$('#no-chart').add();--%>
    <%--}--%>
    <%--// The <svg/> element rendered by Google Charts--%>
    <%--var svg = $('svg', chartContainer);--%>
    <%--/*--%>
    <%--* Step through all the labels in the legend.--%>
    <%--*/--%>
    <%--$(labelSelector, svg).each(function (i, v) {--%>
    <%--/*--%>
    <%--* I'm retrieving the value of the second column in my data table,--%>
    <%--* which contains the number that I want to display. If your logic--%>
    <%--* is more complicated, change this line to calculate a new total.--%>
    <%--*/--%>
    <%--var value = table.getValue(i, 1);--%>
    <%--// The new label text.--%>
    <%--var newLabel = $(this).text() + ': ' + formatNumberSeparator(value, "${locale}") + ' VND';--%>
    <%--// Update the label text.--%>
    <%--$(this).text(newLabel);--%>
    <%--});--%>
  }

  //-------BEGIN TABLE CHART----------
  <c:if test="${maxRow ge 8}">
  $("#table").hpaging({"limit": 8});
  $("#pg_table").addClass('paginationTableChart');
  </c:if>
  //END
</script>
</body>
</html>