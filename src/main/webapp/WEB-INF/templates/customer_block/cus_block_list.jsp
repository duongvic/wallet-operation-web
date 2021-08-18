<%@ page
        import="static vn.mog.ewallet.operation.web.controller.translog.TransactionReimController.TRANS_REIM_DETAIL" %>
<%@ page
        import="static vn.mog.ewallet.operation.web.controller.customer.CustomerBlockController.CUS_BLOCK_LIST_DETAIL_ADD_ALL" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
    <!-- Basic -->
    <meta charset="UTF-8">
    <title><spring:message code="menu.left.customer.block.list"/></title>
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <jsp:include page="../include_page/js_merchant.jsp">
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="dateLib" value="true"/>
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
            <jsp:param value="cus-block" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                                <li><span><spring:message code="menu.left.customer.manager"/></span>
                                </li>
                                <li><span class="nav-active"><spring:message
                                        code="menu.left.customer.block.list"/> </span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">


                    <div class="tabs">
                        <ul class="nav nav-tabs">
                            <li class="${blockTypes == null || '' eq blockTypes ? 'active' : ''}">
                                <a onclick="openTabAllBlock();" href="#" data-toggle="tab">All</a>
                            </li>
                            <li class="${'PROVIDER' == blockTypes ? 'active' : ''}">
                                <a id="PROVIDER" onclick="openTabBlock('PROVIDER')"
                                   href="#">Provider</a>
                            </li>
                            <li class="${'SERVICE' == blockTypes ? 'active' : ''}">
                                <a id="SERVICE" onclick="openTabBlock('SERVICE')" href="#">Service</a>
                            </li>
                        </ul>

                        <div class="clearfix"></div>

                        <section class="panel search_payment panel-default">
                            <div class="panel-body pt-none">

                                <spring:message code="block.customer.search.placeholder"
                                                var="placeholder"/>

                                <spring:message code="select.choose.all" var="langChooseAll"/>
                                <spring:message code="select.status" var="langStatus"/>
                                <spring:message code="select.service" var="langService"/>
                                <spring:message code="select.merchant" var="langMerchant"/>
                                <spring:message code="select.provider" var="langProvider"/>

                                <form action="" method="GET" id="tbl-filter" class="mb-md">

                                    <div class="pull-right form-responsive bt-plt">
                                        <a class="mb-xs mt-xs btn btn-success"
                                           href="${contextPath}/customer-block/add?menu=cus&blockType=SERVICE_BLOCK"><i
                                                class="fa fa-plus"></i>&nbsp;<spring:message
                                                code="btn.add.blackList"/></a>
                                    </div>

                                    <div class="clearfix"></div>

                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-md-3 mb-md">
                                                <select data-plugin-selectTwo
                                                        class="form-control"
                                                        id="quickSearchType"
                                                        name="quickSearchType">
                                                    <c:forEach var="item"
                                                               items="${listQuickSearchType}">
                                                        <option value="${item.value()}" ${item.selectedQuickSearchType(quickSearchType)}>
                                                            <spring:message
                                                                    code="${item.displayText()}"/>
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="col-md-9">
                                                <input type="text" id="quickSearch"
                                                       name="quickSearch"
                                                       class="form-control"
                                                       placeholder="${placeholder}"
                                                       value="${param.quickSearch }"/>
                                            </div>

                                        </div>


                                    </div>


                                    <div class="form-inline">

                                        <%--<jsp:include page="../include_component/date_range.jsp"/>--%>

                                        <%--customer type--%>
                                        <jsp:include
                                                page="../include_component/search_customer_type.jsp"/>
                                        <%--customer type--%>

                                        <%--provider--%>
                                            <c:if test="${blockTypes == null || blockTypes=='' || blockTypes eq 'PROVIDER'}">
                                                <jsp:include
                                                        page="../include_component/search_provider_type.jsp"/>
                                            </c:if>
                                        <%--provider--%>

                                        <%--serviceType--%>
                                            <c:if test="${blockTypes == null || blockTypes=='' || blockTypes eq 'SERVICE'}">
                                        <jsp:include
                                                page="../include_component/search_service_type_multiple.jsp"/>
                                            </c:if>
                                        <%--provider--%>

                                        <div class='pull-right form-responsive'>
                                            <button type="submit" class="btn btn-primary ml-tiny"><i
                                                    class="fa fa-search"></i>&nbsp; <spring:message
                                                    code="common.btn.search"/></button>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>


                                    <input type="hidden" id="blockTypes_hidden"
                                           name="blockTypes"
                                           value="${blockTypes}">

                                </form>

                                <div class="clearfix"></div>

                                <spring:message var="colCIF"
                                                code="setting.account.tbl.col.cif"/>
                                <spring:message var="colFullName"
                                                code="setting.account.tbl.col.full.name"/>
                                <spring:message var="colPhone"
                                                code="setting.account.tbl.col.phone"/>
                                <spring:message var="colCusType"
                                                code="setting.account.tbl.col.customer.type"/>
                                <spring:message var="colAction"
                                                code="setting.account.tbl.col.action"/>

                                <display:table name="transCancels" id="item"
                                               requestURI="list"
                                               pagesize="${pagesize}" partialList="true"
                                               size="total"
                                               sort="page"
                                               class="table table-bordered table-striped mb-none">

                                    <%@ include file="../include_component/display_table.jsp" %>

                                    <display:column title="STT">
                                    <span id="row${list.id}" class="rowid">
                                        <c:out value="${offset + item_rowNum}"/>
                                    </span>
                                    </display:column>

                                    <display:column title="${colCIF}" property="cif"/>
                                    <display:column title="${colFullName}" property="fullName"/>
                                    <display:column title="${colPhone}" property="msisdn"/>

                                    <display:column title="${colCusType}">
                                        ${item.getBlockCustomerTypeName()}
                                    </display:column>

                                    <display:column title="${colAction}" class="action_icon center"
                                                    headerClass="center">
                                        <a href="${contextPath}<%=CUS_BLOCK_LIST_DETAIL_ADD_ALL%>?customersCifs=${item.cif}"
                                           class="detail-link-block link-active"
                                           title="<spring:message code="common.title.view.detail"/>"
                                           txnId="${item.cif}">
                                            <i class="fa fa-info-circle "></i>
                                        </a>
                                        <%--<a href="#" class="detail-link link-active"--%>
                                           <%--title="<spring:message code="common.title.edit.information"/>"--%>
                                           <%--txnId="${item.id}">--%>
                                            <%--<i class="fa fa-pencil"></i>--%>
                                        <%--</a>--%>

                                        <%--<a href="#" class="detail-link link-active"--%>
                                           <%--title="<spring:message code="btn.remove.blacklist"/>"--%>
                                           <%--txnId="${item.id}">--%>
                                            <%--<i class="fa fa-trash"></i>--%>
                                        <%--</a>--%>
                                    </display:column>
                                </display:table>

                            </div>
                        </section>
                    </div>


                </div>
            </div>
            <!-- end: page -->
        </section>
        <jsp:include page="../include_page/footer.jsp"/>
    </div>
</section>

<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_component/search_form_commons.jsp">
    <jsp:param name="selCustomerType" value="true"/>
    <jsp:param name="selProviderType" value="true"/>
    <jsp:param name="selServiceType" value="true"/>
</jsp:include>
<%--<jsp:include page="../include_page/date_picker.jsp">--%>
<%--<jsp:param name="isFullTime" value="true"/>--%>
<%--</jsp:include>--%>


<script type="text/javascript">
  $(document).ready(function () {
    <%--$('a.detail-link-block').click(function () {--%>
      <%--var txnId = $(this).attr("txnId");--%>
      <%--var searchURL = '';--%>
      <%--if (window.location.search.indexOf("?") >= 0) {--%>
        <%--searchURL = ctx + '<%=CUS_BLOCK_LIST_DETAIL_ADD_ALL%>' + window.location.search + '&customersCifs=' + txnId;--%>
      <%--} else {--%>
        <%--searchURL = ctx + '<%=CUS_BLOCK_LIST_DETAIL_ADD_ALL%>?' + 'customersCifs=' + txnId;--%>
      <%--}--%>
      <%--window.location.href = searchURL;--%>
    <%--});--%>

  });

  function openTabBlock(paramValue) {
    $('#blockTypes_hidden').val(paramValue);

    var searchURL = '${BlockCusListUrl}' + '?menu=cus&blockTypes=' + paramValue;
    window.location.href = searchURL;
  }

  function openTabAllBlock() {
    $('#blockTypes_hidden').val(null);

    var searchURL = '${BlockCusListAllUrl}?menu=cus';
    window.location.href = searchURL;
  }

</script>
</body>
</html>