<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 2/23/2021
  Time: 9:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include_page/taglibs.jsp" %>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    // Load google charts
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    // Draw the chart and set the chart values
    function drawChart() {
        // Display the chart inside the <div> element with id="piechart"
        var title = '<spring:message code="label.ratio.proportion"/>'
        <c:forEach items="${providerServiceMatrix}" var="group">
            <c:forEach items="${group.value}" var="service">
                <c:choose>
                    <c:when test="${fn:length(service.value) > 0}">
                        var providers = [['Task', 'Hours per Day']];
                        <c:forEach items="${service.value}" var="providerService"
                            varStatus="loop">
                        providers.push([
                            '${providerService.provider.providerBizCode}(${providerService.getRankingScoreInt()})',
                            ${providerService.getRankingScoreInt()}
                        ])
                        </c:forEach>
                        var data = google.visualization.arrayToDataTable(providers);
                        // Optional; add a title and set the width and height of the chart
                        var options = {'sliceVisibilityThreshold': .0, 'title': title, 'width': 800, 'height': 'auto'};
                        new google.visualization.PieChart(document.getElementById('${service.key}_${group.key}')).draw(data, options);
                    </c:when>
                </c:choose>
            </c:forEach>
        </c:forEach>
    }
</script>
