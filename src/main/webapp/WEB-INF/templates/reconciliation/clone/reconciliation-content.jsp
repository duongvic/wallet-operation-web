<%@ include file="../../include_page/taglibs.jsp" %>

<spring:message code="label.search.recon" var="textPlaceHolder" scope="request"/>

<section class="panel search_payment panel-default">
  <div class="panel-body pt-none">
    <form action="" method="GET" id="tbl-filter" class="mb-md">
      <div class="form-group ml-none mr-none">
        <%--<div class="input-group input-group-icon">--%>
        <%--<span class="input-group-addon"><span class="icon" style="opacity: 0.4">--%>
        <%--<i class="fa fa-search-minus"></i>--%>
        <%--</span></span>--%>
        <%--<input type="text" id="quickSearch" name="quickSearch" class="form-control"--%>
        <%--placeholder="${textPlaceHolder}, Email" value="${param.quickSearch }"/>--%>
        <select class="find-customer" name="quickSearch" id="quickSearch" multiple="multiple">
        </select>
        <%--</div>--%>
      </div>

      <div class="form-inline">

        <jsp:include page="../../include_component/date_range.jsp"/>
        <select name="isBalanceNotMatch" id="isBalanceNotMatch" class="form-control">
          <option disabled selected><spring:message code="label.recon.status"/></option>
          <option value=""><spring:message code="select.choose.all"/></option>
          <option value="false" ${param.isBalanceNotMatch eq 'false' ? 'selected' : ''}>
            <spring:message code="label.equal"/></option>
          <option value="true" ${param.isBalanceNotMatch eq 'true' ? 'selected' : ''}>
            <spring:message code="label.not.equal"/></option>
        </select>

        <div class='pull-right form-responsive bt-plt'>

          <input name="idOwnerCustomerTypes" type="hidden"
                 value="${param.idOwnerCustomerTypes}"/>

          <button type="button" class="btn btn-primary" onclick="fetchData()"><i
              class="fa fa-search"></i>&nbsp;<spring:message
              code="transaction.api.button.search"/></button>
          <%--<a href="${ReconciExportControlUri}" id="export_do"--%>
          <%--class="btn btn-default export_link"><i--%>
          <%--class="fa fa-download "></i>&nbsp;<spring:message--%>
          <%--code="transaction.log.export"/></a>--%>
        </div>
      </div>
      <div class="clearfix"></div>
    </form>

    <div class="clearfix"></div>

    <c:if test="${transactions ne null && transactions.size() > 0}">
      <div class="panel-body pt-none pb-none">
        <div class="form-group total-reconciliation">
          <h4><strong><spring:message code="report.reconciliation"/></strong></h4></br>

          <div class="col-sm-3 col-lg-3 center col-respon">
            <a href="#" class="reconciliation-flag">
              <p class="mb-none"><strong><spring:message
                  code="report.recon.beginning.balance"/> </strong></p>
              <p class="mb-none"><strong>${ewallet:formatNumber(firstPreBalance)}</strong></p>
            </a>
          </div>
          <div class="col-sm-3 col-lg-3 center col-respon">
            <a href="#" class="reconciliation-flag">
              <p class="mb-none"><strong>+</strong></p>
            </a>
          </div>
          <div class="col-sm-3 col-lg-3 center col-respon">
            <a href="#" class="reconciliation-flag">
              <p class="mb-none"><strong><spring:message
                  code="report.recon.loaded.period"/> </strong></p>
              <p class="mb-none"><strong>${ewallet:formatNumber(totalCashIn)}</strong></p>

            </a>
          </div>
          <div class="col-sm-3 col-lg-3 center col-respon">
            <a href="#" class="reconciliation-flag">
              <p class="mb-none"><strong>-</strong></p>
            </a>
          </div>
          <div class="col-sm-3 col-lg-3 center col-respon">
            <a href="#" class="reconciliation-flag">
              <p class="mb-none"><strong><spring:message
                  code="report.recon.text.in.the.period"/> </strong></p>
              <p class="mb-none"><strong>${ewallet:formatNumber(totalCashOut)}</strong></p>
            </a>
          </div>
          <div class="col-sm-3 col-lg-3 center col-respon">
            <a href="#" class="reconciliation-flag">
              <p class="mb-none"><strong>=</strong></p>
            </a>
          </div>
          <div class="col-sm-3 col-lg-3 center col-respon">
            <a href="#" class="reconciliation-flag">
              <p class="mb-none"><strong><spring:message code="report.recon.end.balance"/> </strong>
              </p>
              <p class="mb-none"><strong>${ewallet:formatNumber(endBalance)}</strong></p>
            </a>
          </div>
          <div class="col-sm-3 col-lg-3 center col-respon">
            <a href="#" class="reconciliation-flag">
              <c:choose>
                <c:when test="${endBalance lt lastPostBalance}">
                  <p class="mb-none"><strong><</strong></p>
                </c:when>
                <c:when test="${endBalance gt lastPostBalance}">
                  <p class="mb-none"><strong>></strong></p>
                </c:when>
                <c:otherwise><p class="mb-none"><strong>=</strong></p></c:otherwise>
              </c:choose>

            </a>
          </div>
          <div class="col-sm-3 col-lg-3 center col-respon">
            <a href="#" class="reconciliation-flag">
              <p class="mb-none"><strong><spring:message
                  code="report.recon.balance.of.the.end.transaction"/> </strong></p>
              <p class="mb-none"><strong>${ewallet:formatNumber(lastPostBalance)}</strong></p>
            </a>
          </div>
        </div>
      </div>
    </c:if>

    <div class="clearfix"></div>
    </br>

    <jsp:include page="reconciliation_table.jsp">
      <jsp:param name="transactionType" value="${param.transactionType}"/>
    </jsp:include>
  </div>
</section>

<script>
  $('.find-customer').select2({
    width: "100%",
    dropdownAutoWidth: true,
    ajax: {
      url: ctx + "/ajax-controller/find-customers",
      dataType: 'json',
      type: "POST",
      data: function (params) {
        var query = {
          search: params.term,
          customerType: 3,
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
            text: data[i].fullName
          };
          retVal.push(lineObj);
        }

        return {
          // results: data.items
          results: retVal

        };
      }
    },
    placeholder: '<spring:message code="label.search.customer"/>',
    minimumInputLength: 3,
    language: {
      inputTooShort: function () {
        return '<spring:message code="label.search.max.length"/>';
      }
    }
  });
</script>