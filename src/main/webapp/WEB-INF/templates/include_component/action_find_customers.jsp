<%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 5/13/2020
  Time: 10:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<sec:authorize
    access="hasAnyRole('ADMIN_OPERATION','CUSTOMERCARE_MANAGER', 'CUSTOMERCARE', 'SALESUPPORT_MANAGER', 'SALESUPPORT','RECONCILIATION_LEADER', 'RECONCILIATION', 'FINANCE', 'FINANCESUPPORT_LEADER', 'FINANCE_SUPPORT', 'FINANCE_LEADER', 'FA_MANAGER', 'ACCOUNTING', 'ACCOUNTING_LEADER')"
    var="manager"/>
<sec:authorize
    access="hasAnyRole('SALE_DIRECTOR','SALE_EXCUTIVE', 'SALE_SUPERVISOR', 'SALE_ASM', 'SALE_RSM')"
    var="sale"/>

<select class="find-customer" name="customerIds" id="customerIds" multiple="multiple">
</select>

<script>
  <c:choose>
  <c:when test="${manager eq true}">
  var url = "/ajax-controller/find-customers";
  </c:when>
  <c:when test="${sale eq true}">
  var url = "/sale/find-customers";
  </c:when>
  </c:choose>
  $('.find-customer').select2({
    width: "100%",
    dropdownAutoWidth: true,
    ajax: {
      url: ctx + url,
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
        for (var i = 0; i < data.length; i++) {
          var lineObj = {
            id: data[i].id,
            text: data[i].fullName + '(SĐT: ' + data[i].msisdn + ',' + ' cif: ' + data[i].cif + ')'
          };
          retVal.push(lineObj);
        }

        return {
          // results: data.items
          results: retVal

        };
      }
    },
    placeholder: 'Tìm kiếm khách hàng bằng SDT',
    minimumInputLength: 3,
    language: {
      inputTooShort: function () {
        return 'Nhập bằng hoặc nhiều hơn 3 kí tự';
      }
    }
  });
</script>