<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 4/26/2021
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include_page/taglibs.jsp" %>

<script type="text/javascript" src="<c:url value='/assets/javascripts/google-chart/loader.js'/>"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/chart.js@3.2.0/dist/chart.min.js"></script>
<script type="text/javascript">

    google.charts.load('current', {'packages': ['corechart']});
    /////////////////////////////////////////////////////////////////////////////////
    ////////////////////            LINE CHART                ///////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    window.onload = function () {
        var lineChart = document.getElementById('sumReqAmount_tbl').getContext('2d');
        var lineChartSumRevenue = document.getElementById('sumRevenue_tbl').getContext('2d');
        var lineChartNumOfTxns = document.getElementById('numOfTxns_tbl').getContext('2d');

        let dates = [];
        let newDTOfReqAmount = [];
        let newDTOfRevene = [];
        let newDTOfTxn = [];
        let oldDTOfReqAmount = [];
        let oldDTOfRevene = [];
        let oldDTOfTxn = [];
        let labelNew = '${newDate}';
        let labelOld = '${oldDate}';
        let hours = [];

        //['00h', '01h', '02h', '03h', '04h', '05h', '06h', '07h', '08h', '09h', '10h', '11h', '12h', '13h',
        //'14h', '15h', '16h', '17h', '18h', '19h', '20h', '21h', '22h', '23h'
        //];

        let hourTo = '${hourTo}';
        let hourFrom = '${hourFrom}';
        if (hourTo.length !== '' && hourFrom !== '') {
            for (let i = parseInt(hourTo); i < parseInt(hourFrom); i++) {
                hours.push(i+"h");
            }
        }

        <c:choose>
            <c:when test="${dateRange ne null}">
            //labelNew = '${newDate}';
            //labelOld = '${oldDate}';
                <c:forEach items="${dateRange}" var="dt">
                dates.push('${dt}');
                </c:forEach>

                <c:forEach var="entry" items="${summaryByDate}">
                newDTOfReqAmount.push(${entry.value.sumOfRequestAmount});
                newDTOfRevene.push(${entry.value.sumOfRevenueAmount});
                newDTOfTxn.push(${entry.value.numOfTrans});
                </c:forEach>

                <c:forEach var="entry" items="${compareSummaryByDate}">
                oldDTOfReqAmount.push(${entry.value.sumOfRequestAmount});
                oldDTOfRevene.push(${entry.value.sumOfRevenueAmount});
                oldDTOfTxn.push(${entry.value.numOfTrans});
                </c:forEach>
            </c:when>

            <c:otherwise>
            //labelNew = 'Hom nay';
            //labelOld = 'Hom qua';
                <c:forEach var="entry" items="${summaryByHour}">
                newDTOfReqAmount.push(${entry.value.sumOfRequestAmount});
                newDTOfRevene.push(${entry.value.sumOfRevenueAmount});
                newDTOfTxn.push(${entry.value.numOfTrans});
                </c:forEach>

                <c:forEach var="entry" items="${compareSummaryByHour}">
                oldDTOfReqAmount.push(${entry.value.sumOfRequestAmount});
                oldDTOfRevene.push(${entry.value.sumOfRevenueAmount});
                oldDTOfTxn.push(${entry.value.numOfTrans});
                </c:forEach>
            </c:otherwise>
        </c:choose>

        var myChart = new Chart(lineChart, {
            type: 'line',
            data: {
                labels: dates.length > 0 ? dates : hours,
                datasets: [
                    {
                        label: labelNew,
                        data: newDTOfReqAmount,
                        fill: true,
                        backgroundColor: 'rgba(167, 202, 206, 0.8)',
                        tension: 0.5,
                        borderWidth: 1
                    },
                    {
                        label: labelOld,
                        data: oldDTOfReqAmount,
                        fill: true,
                        backgroundColor: 'rgba(139, 166, 185, 0.4)',
                        tension: 0.5,
                        borderWidth: 1
                    }
                ]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                },
            }
        });

        var myLineChartSumRevenue = new Chart(lineChartSumRevenue, {
            type: 'line',
            data: {
                labels: dates.length > 0 ? dates : hours,
                datasets: [
                    {
                        label: labelNew,
                        data: newDTOfRevene,
                        fill: true,
                        backgroundColor: 'rgba(167, 202, 206, 0.8)',
                        tension: 0.5,
                        borderWidth: 1
                    },
                    {
                        label: labelOld,
                        data: oldDTOfRevene,
                        fill: true,
                        backgroundColor: 'rgba(139, 166, 185, 0.4)',
                        tension: 0.5,
                        borderWidth: 1
                    }
                ]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                },
            }
        });

        var myLineChartNumOfTxnse = new Chart(lineChartNumOfTxns, {
            type: 'line',
            data: {
                labels: dates.length > 0 ? dates : hours,
                datasets: [
                    {
                        label: labelNew,
                        data: newDTOfTxn,
                        fill: true,
                        backgroundColor: 'rgba(167, 202, 206, 0.8)',
                        tension: 0.5,
                        borderWidth: 1
                    },
                    {
                        label: labelOld,
                        data: oldDTOfTxn,
                        fill: true,
                        backgroundColor: 'rgba(139, 166, 185, 0.4)',
                        tension: 0.5,
                        borderWidth: 1
                    }
                ]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                },
            }
        });
    };
    /////////////////////////////////////////////////////////////////////////////////
    ////////////////////             PIE CHART                ///////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    google.charts.setOnLoadCallback(drawServiceTypePieChart);
    google.charts.setOnLoadCallback(drawServiceTypePieChart2);
    google.charts.setOnLoadCallback(drawServiceTypePieChart3);
    google.charts.setOnLoadCallback(drawCustomerTypePieChart);
    google.charts.setOnLoadCallback(drawCustomerTypePieChart2);
    google.charts.setOnLoadCallback(drawCustomerTypePieChart3);
    google.charts.setOnLoadCallback(drawCustomerPieChart);
    google.charts.setOnLoadCallback(drawCustomerPieChart2);
    google.charts.setOnLoadCallback(drawCustomerPieChart3);
    google.charts.setOnLoadCallback(drawProviderPieChart);
    google.charts.setOnLoadCallback(drawProviderPieChart2);
    google.charts.setOnLoadCallback(drawProviderPieChart3);

    function drawServiceTypePieChart() {

        var pieData = google.visualization.arrayToDataTable([
            ['Type', 'Percent'],
            <c:forEach var="entry" items="${summaryByServiceType}">
            ['${entry.key}', ${entry.value.sumOfRequestAmount < 0 ? 0 :  entry.value.sumOfRequestAmount}],
            </c:forEach>
        ]);

        var pieOptions = {
            title: 'Amount',
            //width: 400,
            //height: 400,
        };

        var pieChart = new google.visualization.PieChart(document.getElementById('serviceTypePiechart'));

        pieChart.draw(pieData, pieOptions);
    }

    function drawServiceTypePieChart2() {

        var pieData = google.visualization.arrayToDataTable([
            ['Type', 'Percent'],
            <c:forEach var="entry" items="${summaryByServiceType}">
            ['${entry.key}', ${entry.value.numOfTrans < 0 ? 0 :  entry.value.numOfTrans}],
            </c:forEach>
        ]);

        var pieOptions = {
            title: 'Transactions',
            //width: 400,
            //height: 400,
        };

        var pieChart = new google.visualization.PieChart(document.getElementById('serviceTypePiechart2'));

        pieChart.draw(pieData, pieOptions);
    }

    function drawServiceTypePieChart3() {
        var pieData = google.visualization.arrayToDataTable([
            ['Type', 'Percent'],
            <c:forEach var="entry" items="${summaryByServiceType}">
            ['${entry.key}', ${entry.value.sumOfRevenueAmount < 0 ? 0 :  entry.value.sumOfRevenueAmount}],
            </c:forEach>
        ]);

        var pieOptions = {
            title: 'Revenue',
            //width: 400,
            //height: 400,
        };

        var pieChart = new google.visualization.PieChart(document.getElementById('serviceTypePiechart3'));

        pieChart.draw(pieData, pieOptions);
    }

    function drawCustomerTypePieChart() {

        var pieData = google.visualization.arrayToDataTable([
            ['Type', 'Percent'],
            <c:forEach var="entry" items="${summaryByCustomerType}">
            ['${entry.key}', ${entry.value.sumOfRequestAmount < 0 ? 0 :  entry.value.sumOfRequestAmount}],
            </c:forEach>
        ]);

        var pieOptions = {
            title: 'Amount',
            //width: 400,
            //height: 400,
        };

        var pieChart = new google.visualization.PieChart(document.getElementById('customerTypePieChart'));

        pieChart.draw(pieData, pieOptions);
    }
    function drawCustomerTypePieChart2() {

        var pieData = google.visualization.arrayToDataTable([
            ['Type', 'Percent'],
            <c:forEach var="entry" items="${summaryByCustomerType}">
            ['${entry.key}', ${entry.value.numOfTrans < 0 ? 0 :  entry.value.numOfTrans}],
            </c:forEach>
        ]);

        var pieOptions = {
            title: 'Transactions',
            //width: 400,
            //height: 400,
        };

        var pieChart = new google.visualization.PieChart(document.getElementById('customerTypePieChart2'));

        pieChart.draw(pieData, pieOptions);
    }
    function drawCustomerTypePieChart3() {

        var pieData = google.visualization.arrayToDataTable([
            ['Type', 'Percent'],
            <c:forEach var="entry" items="${summaryByCustomerType}">
            ['${entry.key}', ${entry.value.sumOfRevenueAmount < 0 ? 0 :  entry.value.sumOfRevenueAmount}],
            </c:forEach>
        ]);

        var pieOptions = {
            title: 'Revenue',
            //width: 400,
            //height: 400,
        };

        var pieChart = new google.visualization.PieChart(document.getElementById('customerTypePieChart3'));

        pieChart.draw(pieData, pieOptions);
    }

    function drawCustomerPieChart() {

        var pieData = google.visualization.arrayToDataTable([
            ['Type', 'Percent'],
            <c:forEach var="entry" items="${summaryByCustomer}">
            ['${entry.key}', ${entry.value.sumOfRequestAmount < 0 ? 0 :  entry.value.sumOfRequestAmount}],
            </c:forEach>
        ]);

        var pieOptions = {
            title: 'Amount',
            //width: 400,
            //height: 400,
        };

        var pieChart = new google.visualization.PieChart(document.getElementById('customerPieChart'));

        pieChart.draw(pieData, pieOptions);
    }

    function drawCustomerPieChart2() {

        var pieData = google.visualization.arrayToDataTable([
            ['Type', 'Percent'],
            <c:forEach var="entry" items="${summaryByCustomer}">
            ['${entry.key}', ${entry.value.numOfTrans < 0 ? 0 :  entry.value.numOfTrans}],
            </c:forEach>
        ]);

        var pieOptions = {
            title: 'Transactions',
            //width: 400,
            //height: 400,
        };

        var pieChart = new google.visualization.PieChart(document.getElementById('customerPieChart2'));

        pieChart.draw(pieData, pieOptions);
    }

    function drawCustomerPieChart3() {

        var pieData = google.visualization.arrayToDataTable([
            ['Type', 'Percent'],
            <c:forEach var="entry" items="${summaryByCustomer}">
            ['${entry.key}', ${entry.value.sumOfRevenueAmount < 0 ? 0 :  entry.value.sumOfRevenueAmount}],
            </c:forEach>
        ]);

        var pieOptions = {
            title: 'Revenue',
            //width: 400,
            //height: 400,
        };

        var pieChart = new google.visualization.PieChart(document.getElementById('customerPieChart3'));

        pieChart.draw(pieData, pieOptions);
    }

    function drawProviderPieChart() {

        var pieData = google.visualization.arrayToDataTable([
            ['Type', 'Percent'],
            <c:forEach var="entry" items="${summaryByProvider}">
            ['${entry.key}', ${entry.value.sumOfRequestAmount < 0 ? 0 :  entry.value.sumOfRequestAmount}],
            </c:forEach>
        ]);

        var pieOptions = {
            title: 'Amount',
            //width: 400,
            //height: 400,
        };

        var pieChart = new google.visualization.PieChart(document.getElementById('providerTypePieChart'));

        pieChart.draw(pieData, pieOptions);
    }

    function drawProviderPieChart2() {

        var pieData = google.visualization.arrayToDataTable([
            ['Type', 'Percent'],
            <c:forEach var="entry" items="${summaryByProvider}">
            ['${entry.key}', ${entry.value.numOfTrans < 0 ? 0 :  entry.value.numOfTrans}],
            </c:forEach>
        ]);

        var pieOptions = {
            title: 'Transactions',
            //width: 400,
            //height: 400,
        };

        var pieChart = new google.visualization.PieChart(document.getElementById('providerTypePieChart2'));

        pieChart.draw(pieData, pieOptions);
    }

    function drawProviderPieChart3() {

        var pieData = google.visualization.arrayToDataTable([
            ['Type', 'Percent'],
            <c:forEach var="entry" items="${summaryByProvider}">
            ['${entry.key}', ${entry.value.sumOfRevenueAmount < 0 ? 0 :  entry.value.sumOfRevenueAmount}],
            </c:forEach>
        ]);

        var pieOptions = {
            title: 'Revenue',
            //width: 400,
            //height: 400,
        };

        var pieChart = new google.visualization.PieChart(document.getElementById('providerTypePieChart3'));

        pieChart.draw(pieData, pieOptions);
    }

    function setActive(element) {
        document.getElementById(element).classList.add("active");
        document.getElementById(element + "_tbl").style.display = "";

        if (element !== 'serviceType') {
            document.getElementById('serviceType').classList.remove("active");
            document.getElementById('serviceType_tbl').style.display = "none";
        }
        if (element !== 'customerType') {
            document.getElementById('customerType').classList.remove("active");
            document.getElementById('customerType_tbl').style.display = "none";
        }
        if (element !== 'providerType') {
            document.getElementById('providerType').classList.remove("active");
            document.getElementById('providerType_tbl').style.display = "none";
        }

        if (element !== 'customer') {
            document.getElementById('customer').classList.remove("active");
            document.getElementById('customer_tbl').style.display = "none";
        }
    }

    function setActiveLineTbl(element) {
        document.getElementById(element).classList.add("active");
        document.getElementById(element + "_tbl").style.display = "";

        if (element !== 'numOfTxns') {
            document.getElementById('numOfTxns').classList.remove("active");
            document.getElementById('numOfTxns_tbl').style.display = "none";
        }
        if (element !== 'sumReqAmount') {
            document.getElementById('sumReqAmount').classList.remove("active");
            document.getElementById('sumReqAmount_tbl').style.display = "none";
        }
        if (element !== 'sumRevenue') {
            document.getElementById('sumRevenue').classList.remove("active");
            document.getElementById('sumRevenue_tbl').style.display = "none";
        }
    }

    $('.view-detail-summary').click(function () {
        if (document.getElementById('summary_tbl').style.display === 'none') {
            document.getElementById('summary_tbl').style.display = "";
        } else {
            document.getElementById('summary_tbl').style.display = "none";
        }
    });

    function fetchDataCus() {
        var data = [];
        <c:forEach var="customer" items="${customers}">
        data.push({id: ${customer.id}, text: '${customer.fullName}'});
        </c:forEach>
        for (var i = 0; i < data.length; i++) {
            var $option = $("<option selected></option>").val(data[i].id).text(data[i].text);
            $('.js-data-example-ajax-account').append($option);
        }
        $('.js-data-example-ajax-account').select2({
            width: "100%",
            dropdownAutoWidth: true,
            ajax: {
                url: ctx + "/ajax-controller/all/account",
                dataType: 'json',
                type: "POST",
                data: function (params) {
                    var query = {
                        search: params.term,
                        type: 'public'
                    }

                    // Query parameters will be ?search=[term]&type=public
                    return query;
                },
                // Additional AJAX parameters go here; see the end of this chapter for the full code of this example
                processResults: function (data) {
                    // Transforms the top-level key of the response object from 'items' to 'results'
                    var retVal = [];
                    var lineObj = {
                        id: data.id,
                        text: data.fullName
                    }
                    retVal.push(lineObj);
                    return {
                        results: retVal

                    };
                }
            },
            placeholder: '<spring:message code="label.input.customer.phone"/>',
            minimumInputLength: 4,
            language: {
                inputTooShort: function () {
                    return '<spring:message code="label.input.10.character"/>';
                }
            }
        });
    }

    var table = $('#my-cus-tbl').DataTable({
        "pageLength": 20,
        "paging": true,
        "lengthMenu": [[10, 20, 50, -1], [10, 20, 50, "All"]],
        "dom": 'tp',
        "language": {
            "sInfo": "${paging_showing} _START_ ${paging_to} _END_ ${paging_of} _TOTAL_ ${paging_entries}",
            "sLengthMenu": "_MENU_ ${paging_records}",
            "paginate": {
                "previous": "${paging_previous}",
                "next": "${paging_next}"
            },
        },
        "searching": false,
        "ordering": false
    });

    $(document).ready(function () {
        fetchDataCus();
    });
</script>

