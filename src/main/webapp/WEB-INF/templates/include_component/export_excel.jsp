<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
  $(document).ready(function () {

    var searchParam = location.search;
    var urlAttr = $(".export_link").attr("href");
    $(".export_link").attr("href", urlAttr + searchParam);

    $(".export_link").click(function () {
        return checkParamExport();
    });
  });

  function checkParamExport() {
    <%--<c:if test="${param.serviceCode eq 'true'}">--%>
    <%--if (!($("select[name=serviceTypeIds]").val() != null && $("select[name=serviceTypeIds]").val().length < 2)) {--%>
      <%--$.MessageBox({message: '<spring:message code="transaction.log.alert.confirm-export.servicetype"/>'});--%>
      <%--return false;--%>
    <%--}--%>
    <%--</c:if>--%>

    var rangeDate = $("#reservation").val();
    if (rangeDate === null || rangeDate === "" || rangeDate.length === 0 || rangeDate === 'Tat ca') {
      $.MessageBox({message: '<spring:message code="transaction.log.alert.confirm-export.daterange"/>'});
      return false;
    }
    var startRaw = rangeDate.split("-")[0];
    var endRaw = rangeDate.split("-")[1];
    var startDay = rangeDate.split("-")[0].split(" ")[0];
    var startHour = rangeDate.split("-")[0].split(" ")[1];
    var startDate = new Date(startDay.split("/")[2], startDay.split("/")[1], startDay.split("/")[0], startHour.split(":")[0], startHour.split(":")[1], startHour.split(":")[2], 0);

    var endDay = rangeDate.split("-")[1].split(" ")[1];
    var endHour = rangeDate.split("-")[1].split(" ")[2];
    var endDate = new Date(endDay.split("/")[2], endDay.split("/")[1], endDay.split("/")[0], endHour.split(":")[0], endHour.split(":")[1], endHour.split(":")[2], 0);

    var h = endDate - startDate;
    if (h > (24 * 60 * 60 * 1000 * 7)) {
      $.MessageBox({message: '<spring:message code="transaction.log.alert.confirm-export.daterange"/>'});
      return false;
    } else {
      return true;
    }
  }
</script>
