<%@ page import="vn.mog.ewallet.operation.web.controller.fundout.FundOutController" %>
<%@ include file="../include_page/taglibs.jsp" %>

<c:url var="fundOutControlUri" value="<%=FundOutController.FUND_OUT_HISTORY_CONTROLLER%>" />

<sec:authorize
        access="hasAnyRole('SALE_DIRECTOR','SALE_EXCUTIVE', 'SALE_SUPERVISOR' , 'SALE_ASM', 'SALE_RSM')"
        var="perActionNotGetAllAccount"/>

<section class="panel search_payment panel-default">
  <div class="panel-body pt-none">

    <form action="#" method="GET" id="tbl-filter">

      <spring:message code="fundout.search.placeholder" var="placeholder"/>
      <spring:message code="fundout.search.btn.search" var="btnSearch"/>
      <spring:message code="select.choose.all" var="langChooseAll" scope="request"/>
      <spring:message code="select.status" var="langStatus" scope="request"/>

      <div class="form-group ml-none mr-none">
        <div class="input-group input-group-icon">
                    <span class="input-group-addon">
                      <span class="icon" style="opacity: 0.4">
                        <i class="fa fa-search-minus"></i>
                      </span>
                    </span>
          <input type="text" id="search" name="search" class="form-control" placeholder="${placeholder}" value="${param.search }"/>
        </div>
      </div>

      <c:if test="${!perActionNotGetAllAccount}">
        <select class="js-data-example-ajax-account-fundout" multiple="multiple" name="customerIds">
        </select>
      </c:if>

      <div class="form-inline" style="margin-top: 9px">

        <jsp:include page="../include_component/date_range.jsp"/>

        <div class="pull-right form-responsive bt-plt">
          <jsp:include page="../include_component/search_source_of_fund_type.jsp"/>

          <c:if test="${param.isUserSearchWalletSourceOfFund}">
            <jsp:include page="../include_component/search_wallet_source_of_fund_type.jsp"/>
          </c:if>

          <%--<c:if test="${param.permitSearchSales}">--%>
          <c:if test="${perActionNotGetAllAccount}">
            <jsp:include page="../include_component/search_customer.jsp"/>
          </c:if>

          <c:if test="${param.permitSearchSales}">
            <jsp:include page="../include_component/search_customer_type.jsp"/>
          </c:if>

          <jsp:include page="../include_component/search_transaction_status.jsp"/>
          <button type="submit" class="btn btn-primary ml-tiny">
            <i class="fa fa-search"></i>&nbsp; <spring:message code="common.btn.search"/>
          </button>

          <a href="${param.controlUri}/export" id="export_do" class="btn  btn-default mt-sm export_link bt-export-rsp"><i
                  class="fa fa-download "></i>&nbsp;<spring:message code="transaction.log.export"/></a>
          <%--<button class="btn btn-default nomal_color_bk bt-cancel" onclick="ClearFields();">
            <spring:message code="common.btn.cancel"/>--%>
          </button>
        </div>
        <div class="clearfix"></div>
      </div>
    </form>


    <div style="margin-top:6px">
      <spring:message code="fundout.title.transaction"/>&nbsp;
      <span class="primary_color text-semibold">${ewallet:formatNumber(total)}</span>&nbsp;|&nbsp;
      <spring:message code="fundout.title.amount"/>&nbsp;
      <span class="primary_color text-semibold">${ewallet:formatNumber(realAmount)}</span>&nbsp;
      <spring:message code="fundout.table.header.currency"/>
    </div>

    <spring:message code="fundout.table.column.no" var="colNo"/>
    <spring:message code="fundout.table.column.tranid" var="colTranId"/>
    <spring:message code="transaction.api.table.request_id" var="colRId"/>
    <spring:message code="fundout.table.column.merchant" var="colMerchant"/>
    <spring:message code="fundout.table.column.createdTime" var="colTime"/>
    <spring:message code="fundout.table.column.channel" var="colService"/>
    <spring:message code="fundout.table.column.amount" var="colAmount"/>
    <spring:message code="transaction.api.table.commission" var="colCommission"/>
    <spring:message code="transaction.api.table.fee" var="colFee"/>
    <spring:message code="fundout.table.column.prebalance" var="colPreBalance"/>
    <spring:message code="fundout.table.column.postbalance" var="colPostBalance"/>
    <spring:message code="fundout.table.column.info" var="colInfo"/>
    <spring:message code="fundout.table.column.status" var="colStatus"/>
    <spring:message code="fundout.table.column.action" var="colAction"/>

    <display:table name="list" id="item"
                   requestURI="list"
                   pagesize="${pagesize}" partialList="true" size="total"
                   class="table table-bordered table-striped mb-none"
                   sort="page">
      <%@ include file="../include_component/display_table.jsp" %>

      <display:column title="${colNo}" headerClass="fit_to_content" class="right">
                  <span id="row${item.id}" class="rowid">
                      <c:out value="${offset + item_rowNum }"/>
                  </span>
      </display:column>

      <display:column title="${colTime}" property="creationDate" format="{0,date,HH:mm dd/MM/yyyy}"/>

      <display:column title="${colTranId}">
        <a class="app-name detail-link link-active" href="#" txnId="${item.id}">${item.id}</a>
      </display:column>

      <display:column title="${colRId}">
        ${fn:substring(item.orderId, 0, 16)}<br/>
        ${fn:substring(item.orderId, 16, item.orderId.length())}
      </display:column>

      <display:column title="${colMerchant}" property="payerUsername"/>

      <display:column title="${colService}">
        <spring:message code="${item.textOrderChannel()}"/>
      </display:column>
      <display:column title="${colAmount}" class="col-number-header" headerClass="col-number-header" value="${ewallet:formatNumber(item.amount)}"/>

      <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR',
                                          'SALESUPPORT_LEADER', 'SALESUPPORT_MANAGER', 'SALESUPPORT',
                                          'FINANCE',
                                          'RECONCILIATION','RECONCILIATION_LEADER')">
        <display:column title="${colCommission}" class="col-number-header" headerClass="col-number-header">${ewallet:formatNumber(item.commision)}</display:column>
        <display:column title="${colFee}" class="col-number-header" headerClass="col-number-header">${ewallet:formatNumber(item.fee)}</display:column>
      </sec:authorize>

      <display:column title="${colPreBalance}" class="col-number-header" headerClass="col-number-header" value="${ewallet:formatNumber(item.preBalance)}"/>
      <display:column title="${colPostBalance}" class="col-number-header" headerClass="col-number-header" value="${ewallet:formatNumber(item.postBalance)}"/>

      <c:if test="${item.isBuyCard() }">
        <display:column title="${colInfo}">
          <input type="checkbox" class="read-more-state" id="post-${var.index }"/>
          <p class="read-more-wrap">
            <c:set value="${item.serials.size() > 3 ? 2 : item.serials.size() }" var="seriEnd"/>
            <c:forEach items="${item.serials }" var="step" varStatus="var2" begin="0" end="${seriEnd }">
              ${step.serial }
            </c:forEach>
            <span class="read-more-target">
                        <c:if test="${item.serials.size() > 3 }">
                          <c:forEach items="${item.serials }" var="step" begin="3" end="${item.serials.size()}">
                            ${step.serial }
                          </c:forEach>
                        </c:if>
                      </span>
          </p>
          <c:if test="${item.serials.size() > 3 }">
            <label for="post-${var.index}" class="read-more-trigger"></label>
          </c:if>
        </display:column>
      </c:if>

      <display:column title="${colStatus}">
        <spring:message code="${item.getStatus()}" text="${item.getStatus()}"/>
      </display:column>
    </display:table>
  </div>
</section>

<script>

  $('.js-data-example-ajax-account-fundout').select2({
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
//        for (var i = 0; i < data.length; i++) {
        var lineObj = {
          id: data.id,
          text: data.fullName
        }
        retVal.push(lineObj);
//        }
        return {
          // results: data.items
          results: retVal

        };
      }
    },
    placeholder: '<spring:message code="label.input.phone"/>',
    minimumInputLength: 10,
    language: {
      inputTooShort: function () {
        return '<spring:message code="label.input.10.character"/>';
      }
    }
  });
</script>