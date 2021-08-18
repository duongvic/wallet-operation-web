<%@ page import="vn.mog.ewallet.operation.web.constant.SharedConstants" %>
<%@ page
        import="static vn.mog.ewallet.operation.web.controller.provider.ProfileProviderController.PROFILE_MANAGER_LIST" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<%String menu = (String) request.getSession().getAttribute("menu");%>
<%if (menu != null) {%>
<c:set var="menu" value="<%=menu%>" scope="page"/>
<%} else {%>
<c:set var="menu" value="${menu}" scope="page"/>
<%}%>

<head>
    <meta charset="UTF-8">
    <title><spring:message code="provider.title.header"/></title>
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <jsp:include page="../include_page/js_merchant.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>

</head>


<c:set
        var="subpathEpinStore"><%=SharedConstants.WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_EPIN_STORE_URL%>
</c:set>
<c:set
        var="subpathEpinStoreN02"><%=SharedConstants.WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_EPIN_STORE_N02_URL%>
</c:set>
<c:set
        var="subpathEpinStoreOffline"><%=SharedConstants.WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_EPIN_STORE_OFFLINE_URL%>
</c:set>

<c:set var="urlProfileProviderCardStore"
       value="${subpathEpinStore}/provider/profile-manager/list?menu=${menu}"/>
<c:set var="urlProfileProviderCardStoreN02"
       value="${subpathEpinStoreN02}/provider/profile-manager/list?menu=${menu}"/>
<c:set var="urlProfileProviderCardStoreOffline"
       value="${subpathEpinStoreOffline}/provider/profile-manager/list?menu=${menu}"/>

<body>
<section class="body">
    <jsp:include page="../include_page/header.jsp"/>

    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="provider" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><span><spring:message code="provider.list.providerManagement"/></span></li>
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
                            <li class="${empty param.tab ? 'active': '' }">
                                <a href="${contextPath}/provider/provider-profile/list">All</a>
                            </li>
                            <c:forEach items="${providerGroups}" var="providerGroup">
                                <li class="${param.tab eq providerGroup.getCode() ? 'active': '' }">
                                    <a href="${contextPath}/provider/provider-profile/list?tab=${providerGroup.getCode()}">${providerGroup.getCode()}</a>
                                </li>
                            </c:forEach>
                        </ul>
                        <div class="tab-content pl-none pr-none">
                            <div id="tab1" class="tab-pane active">
                                <div class="h4 mb-md"><spring:message code="provider.list.providerProfile"/></div>
                                <section class="panel search_payment panel-default">
                                    <div class="panel-body pt-none">

                                        <form action="" method="GET" id="search-provider">
                                            <input type="hidden" name="${_csrf.parameterName}"
                                                   value="${_csrf.token}"/>
                                            <input type="hidden" name="pProService" value="">
                                            <spring:message code="provider.form.placeholder" var="placeholder"/>
                                            <div class="form-group ml-none mr-none">
                                                <div class="input-group input-group-icon">
                                                    <span class="input-group-addon">
                                                      <span class="icon" style="opacity: 0.4"><i
                                                              class="fa fa-search-minus"></i></span>
                                                    </span>
                                                    <input type="text" id="search" name="search" class="form-control"
                                                           placeholder="${placeholder}"
                                                           value="${param.search }"/>
                                                </div>
                                            </div>

                                            <div class="form-inline">
                                                <div class='pull-right form-responsive bt-plt'>
                                                    <c:if test="${empty param.tab}">
                                                        <select name="providerGroup" id="providerGroup"
                                                                class="form-control">
                                                            <option value="">Provider group</option>
                                                            <c:forEach items="${providerGroups}" var="providerGroup">
                                                                <option value="${providerGroup.getCode()}"
                                                                    ${param.providerGroup eq providerGroup.getCode() ? "selected" : "" }>
                                                                        ${providerGroup.getCode()}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </c:if>

                                                    <select name="active" id="active" class="form-control">
                                                        <option value=""><spring:message code="select.status"/></option>
                                                        <option value="1" ${param.active eq "1" ? "selected" : "" }>
                                                            <spring:message
                                                                    code="status.on"/></option>
                                                        <option value="0" ${param.active eq "0" ? "selected" : "" }>
                                                            <spring:message
                                                                    code="status.off"/></option>
                                                    </select>

                                                    <button type="submit" class="btn btn-primary"><i
                                                            class="fa fa-search"></i>&nbsp;<spring:message
                                                            code="common.btn.search"/></button>
                                                    <a href="?" class="btn nomal_color_bk bt-cancel"><spring:message
                                                            code="common.btn.cancel"/></a>
                                                </div>
                                            </div>
                                        </form>

                                        <div class="clearfix"></div>

                                        <sec:authorize
                                                access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_LEADER', 'SALESUPPORT_MANAGER' , 'SALESUPPORT', 'CUSTOMERCARE_MANAGER', 'CUSTOMERCARE')"
                                                var="perActionSwitch"/>
                                        <sec:authorize
                                                access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_LEADER', 'SALESUPPORT_MANAGER' , 'SALESUPPORT' )"
                                                var="perEdit"/>

                                        <spring:message var="colStt" code="provider.list.table.no"/>
                                        <spring:message var="colCode" code="provider.list.table.code"/>
                                        <spring:message var="colName" code="provider.list.table.name"/>
                                        <spring:message var="colCurrentBalance"
                                                        code="provider.list.table.current.balance"/>
                                        <spring:message var="colRatingPoint" code="provider.list.table.rating.point"/>
                                        <spring:message var="colHealthy" code="provider.list.table.healthy"/>
                                        <spring:message var="colActive" code="provider.list.table.active"/>
                                        <spring:message var="colAction" code="provider.list.table.action"/>

                                        <display:table name="list" id="item"
                                                       requestURI="list"
                                                       pagesize="${pagesize}"
                                                       partialList="true"
                                                       size="total"
                                                       sort="page"
                                                       class="table table-bordered table-striped mb-none">

                                            <%@ include file="../include_component/display_table.jsp" %>

                                            <display:column title="${colStt}" headerClass="fit_to_content"
                                                            class="right">
                                    <span id="row${item.id}" class="rowid">
                                      <c:out value="${offset + item_rowNum}"/>
                                    </span>
                                            </display:column>
                                            <%--new--%>
                                            <%--                                <display:column title="PROVIDER GROUP">--%>
                                            <%--                                    ${(item.providerGroup)}--%>
                                            <%--                                </display:column>--%>
                                            <display:column title="${colCode}">
                                                ${ewallet:getProviderBizCode(item.providerCode)}
                                            </display:column>
                                            <%--end--%>
                                            <display:column title="${colName}" property="name"/>
                                            <display:column title="RANKING GROUP">
                                                ${ewallet:getRankingGroupString(item.rankingGroup)}
                                            </display:column>
                                            <display:column title="RANKING LEVEL">
                                                ${ewallet:getRankingLevelString(item.rankingLevel)}
                                            </display:column>
                                            <display:column title="${colCurrentBalance}" headerClass="col-number-header"
                                                            class="col-number-header">
                                                <c:choose>
                                                    <c:when
                                                            test="${item.isSupportCheckBalance eq true}">${ewallet:formatNumber(item.balance)}</c:when>
                                                    <c:otherwise>
                                                        <span><spring:message
                                                                code="common.not.support.check.balance"/> </span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </display:column>

                                            <!-- chuy y: ------------number double -->
                                            <%--ranking score--%>
                                            <display:column title="${colRatingPoint}" class="col-number-header"
                                                            headerClass="col-number-header">
                                                <fmt:formatNumber type="number" maxFractionDigits="3" var="relationship"
                                                                  value="${item.rankingScore}"/>
                                                ${fn:replace(relationship, ',', '.')}
                                            </display:column>
                                            <%--ranking score min--%>
                                            <display:column title="${colRatingPoint} MIN" class="col-number-header"
                                                            headerClass="col-number-header">
                                                <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                  var="rankingScoreMin"
                                                                  value="${item.rankingScoreMin}"/>
                                                ${fn:replace(rankingScoreMin, ',', '.')}
                                            </display:column>
                                            <%--ranking score max--%>
                                            <display:column title="${colRatingPoint} MAX" class="col-number-header"
                                                            headerClass="col-number-header">
                                                <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                  var="rankingScoreMax"
                                                                  value="${item.rankingScoreMax}"/>
                                                ${fn:replace(rankingScoreMax, ',', '.')}
                                            </display:column>
                                            <%--end--%>
                                            <display:column title="${colHealthy}" headerClass="center" class="center">
                                                <div class="" style="min-width: 130px">
                                                    <button type="button"
                                                            title="<spring:message code="common.title.warning" />"
                                                            class="mb-xs mt-xs mr-xs-mini btn btn-xs btn btn-danger" ${item.providerHealthy.state eq 'RED' && item.active ? "" : "disabled" }>
                                                        bad
                                                    </button>
                                                    <button type="button"
                                                            title="<spring:message code="common.title.dangerous" />"
                                                            class="mb-xs mt-xs mr-xs-mini btn btn-xs btn btn-primary" ${item.providerHealthy.state eq 'YELLOW' && item.active ? "" : "disabled" }>
                                                        warning
                                                    </button>
                                                    <button type="button"
                                                            title="<spring:message code="common.title.stable.operation"/>"
                                                            class="mb-xs mt-xs mr-xs-mini btn btn-xs btn btn-success-green" ${item.providerHealthy.state eq 'GREEN' && item.active ? "" : "disabled" }>
                                                        good
                                                    </button>
                                                </div>
                                            </display:column>

                                            <display:column title="${colActive}" class="center" headerClass="center">
                                                <div class="switch switch-sm switch-success">
                                                    <c:choose>
                                                        <c:when test="${perActionSwitch eq true}">
                                                            <input type="checkbox"
                                                                   name="switch" ${item.active ? 'checked' : '' }
                                                                   value="${item.id }"
                                                                   id="ck_${item.id }_${offset + item_rowNum}"/>
                                                            <a href="#" class="switchery-mask"
                                                               id="ck_${item.id }_${offset + item_rowNum}"
                                                               providerCode="${item.providerCode}"
                                                               providerId="${item.id}"
                                                               active="${item.active }"></a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="checkbox" name="switch"
                                                                   disabled ${item.active ? 'checked' : '' }
                                                                   value="${item.id }"
                                                                   id="ck_${item.id }_${offset + item_rowNum}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </display:column>

                                            <display:column title="${colAction}" class="action_icon center"
                                                            headerClass="center">
                                                <fmt:parseNumber var="inRelat" integerOnly="true" type="number"
                                                                 value="${relationship}"/>
                                                <fmt:parseNumber var="scoreMax" integerOnly="true" type="number"
                                                                 value="${rankingScoreMax}"/>
                                                <fmt:parseNumber var="scoreMin" integerOnly="true" type="number"
                                                                 value="${rankingScoreMin}"/>
                                                <c:choose>
                                                    <c:when test="${item.providerCode eq 'CARD_STORE'}">
                                                        <a href="${urlProfileProviderCardStore}"
                                                           title="<spring:message code="common.title.edit.information"/>">
                                                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                                                        </a>
                                                    </c:when>
                                                    <c:when test="${item.providerCode eq 'CARD_STORE_N02'}">
                                                        <a href="${urlProfileProviderCardStoreN02}"
                                                           title="<spring:message code="common.title.edit.information" />">
                                                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                                                        </a>
                                                    </c:when>
                                                    <c:when test="${item.providerCode eq 'OFFLINE_CARD_STORE'}">
                                                        <a href="${urlProfileProviderCardStoreOffline}"
                                                           title="<spring:message code="common.title.edit.information" />">
                                                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="${contextPath}<%=PROFILE_MANAGER_LIST%>?provider=${item.providerCode}&providerName=${item.name}"
                                                           title="<spring:message code="common.title.edit.information" />">
                                                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                                                        </a>
                                                    </c:otherwise>
                                                </c:choose>

                                                <c:if test="${perEdit eq true}">
                                                    <a href="#" class="link-edit"
                                                       data-toggle="modal" data-target="#edit"
                                                       providerCode="${item.providerCode}"
                                                       providerId="${item.id}"
                                                       relationship="${inRelat}"
                                                       rankingScoreMin="${scoreMin}"
                                                       rankingScoreMax="${scoreMax}"
                                                       customerTypeSupported="${item.customerTypeSupported}"
                                                       specific="${item.specific}"
                                                       listCif="${item.specificListCif}"
                                                       blackListCif="${item.blackListCif}"
                                                       name="${item.name}"
                                                       providerGroup="${item.providerGroup}"
                                                       providerBizCode="${item.providerBizCode}"
                                                       rankingGroup="${item.rankingGroup}"
                                                       rankingLevel="${item.rankingLevel}"
                                                       title="<spring:message code="common.title.edit.information" />">
                                                        <i class="fa fa-pencil" aria-hidden="true"></i>
                                                    </a>
                                                </c:if>
                                                <c:if test="${perActionSwitch eq true}">
                                                    <a href="#"
                                                       title="<spring:message code="common.title.service.operation"/> "
                                                       class="detail-link link-active" data-toggle="modal"
                                                       data-target="#detail"
                                                       providerCode="${item.providerCode}"
                                                       customerTypeSupported="${item.customerTypeSupported}"
                                                       blackListCif="${item.blackListCif}"
                                                       providerId="${item.id}">
                                                        <i class="fa fa-cog"></i>
                                                    </a>
                                                </c:if>
                                                <c:if
                                                        test="${item.providerCode eq 'PTU_VIETTELTELECOM' || item.providerCode eq 'BILL_VIETTELPAY'}">
                                                    <a class="fund-in"
                                                       data-toggle="modal" data-target="#fundin"
                                                       name="${item.name}"
                                                       providerCode="${item.providerCode}"
                                                       providerId="${item.id}"
                                                       title="<spring:message code="label.fundin.provider"/>"
                                                    >
                                                        <i class=""><img
                                                                src="${contextPath}/assets/images/icon/menu/cashIn%20-%20Copy.png"
                                                                class="icon-menu-left"></i>
                                                    </a>
                                                </c:if>
                                            </display:column>
                                        </display:table>
                                    </div>
                                </section>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- end: page -->
        </section>
        <jsp:include page="provider-edit.jsp"/>
        <jsp:include page="provider-service.jsp"/>
        <jsp:include page="provider-fundin.jsp"/>
        <jsp:include page="../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<script type="text/javascript">

    <%--$('.epinStoreHref').on('click', function () {--%>
    <%--var menuClick = $(this).data("menu-click");--%>
    <%--if (menuClick == "" || menuClick == undefined)--%>
    <%--setCookie("menu-click", menuClick, 1, "/store")--%>
    <%--window.location.href = '${urlProfileProviderCardStore}';--%>
    <%--&lt;%&ndash;'${urlProfileProviderCardStore}';&ndash;%&gt;--%>
    <%--});--%>

    <%--$('a.detail-link').click(function () {--%>
    <%--var displayName = $(this).data("display-name");--%>
    <%--setCookie('statement_user', displayName, 1, '/');--%>
    <%--window.location.href = ctx + '<%=StatementController.STATEMENT_USER_LIST%>/' +  $(this).data("customer-id");--%>
    <%--});--%>

    $(document).ready(function () {

        $('.switch input[name=switch]').each(function () {
            var item = document.querySelector('#' + $(this).attr('id'));
            var color = '#64bd63';
            var switchery = new Switchery(item, {color: color, size: 'small'});
            if ($(this).disabled)
                switchery.disable();
        });
        $('.switchery-mask').click(function () {
            var obj = $(this);
            var pid = obj.attr('id');
            var providerCode = obj.attr('providerCode');
            var providerId = obj.attr('providerId');
            var active = obj.attr('active');
            var isTrueSet = (active === 'true');
            var textAlert = "<spring:message code="status.on"/> ";
            if (isTrueSet) textAlert = "<spring:message code="status.off"/> ";
            $.MessageBox({
                buttonDone: "<spring:message code="common.btn.Yes"/> ",
                buttonFail: "<spring:message code="common.btn.No"/>",
                message: "<spring:message code="common.are.you.sure.you.want.to"/> <b>" + textAlert
                    + "</b><spring:message code="common.service"/>?"
            }).done(function () {
                $.post('changeProviderStatus', {providerId: providerId, active: active},
                    function (json) {
                        $.MessageBox({message: json.message});
                        if (json.code === 0) {
                            $(".switchery-mask[id=" + pid + "]").closest('.switch').find(
                                'span.switchery').click();
                            obj.attr('active', !isTrueSet);
                            location.reload();
                        }
                        setTimeout(function () {
                            $(".btnStatus").button('reset');
                        }, 1000);
                    });
            });
            return false;
        });
    });

    $('.fund-in').click(function () {
        $("form[name=fundin]")[0].reset();
        $("form[name=fundin] p[name=tcode]").text($(this).attr("providerCode"));
        $("form[name=fundin] p[name=tname]").text($(this).attr("name"));
        $("form[name=fundin] span[name=titleName]").text($(this).attr("name"));
        $("form[name=fundin] input[name=providerId]").val($(this).attr("providerId"));
    });
</script>

</body>

</html>
