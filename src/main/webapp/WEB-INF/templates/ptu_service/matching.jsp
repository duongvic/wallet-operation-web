<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 2/22/2021
  Time: 10:08 AM
  To change this template use File | Settings | File Templates.
--%>
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
    <title><spring:message code="menu.left.ptu.service"/></title>
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
<body>
<section class="body">
    <jsp:include page="../include_page/header.jsp"/>

    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="ptu-service" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="${contextPath}"><i class="fa fa-home"></i></a></li>
                                <li><span><spring:message code="provider.list.providerManagement"/></span></li>
                                <li><span class="nav-active"><spring:message code="menu.left.ptu.service"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>
            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">
                    <section class="panel search_payment panel-default mt-md">
                        <form action="" method="GET" id="search-service" class="mb-md fr">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                            <div class="form-group">
                                <div>
                                    <jsp:include page="../include_component/search_topup_vt.jsp"/>
                                    <jsp:include page="../include_component/search_ranking_group.jsp"/>
                                    <button type="submit" class="btn btn-primary"><i
                                            class="fa fa-search"></i>&nbsp;<spring:message
                                            code="common.btn.search"/></button>
                                    <button type="button" class="btn nomal_color_bk bt-cancel"><spring:message
                                            code="common.btn.cancel"/></button>
                                </div>
                            </div>
                        </form>
                        <div class="clearfix"></div>
                        <%----------------------------%>
                        <c:forEach items="${providerServiceMatrix}" var="group">
                            <ol class="breadcrumb" style="background-color: #2582c4;">
                                <li><a href="#" style="color: white">${group.key}</a></li>
                            </ol>
                            <c:forEach items="${group.value}" var="service">
                                <div>
                                    <div style="display: flex; justify-content: space-between" class="mb-md">
                                        <h4 style="align-self: center">${ewallet:getTopupViettelNameByCode(service.key)}</h4>
                                    </div>
                                    <c:choose>
                                        <c:when test="${fn:length(service.value) > 0}">
                                            <!--pie chart inject here-->
                                            <div id='${service.key}_${group.key}'></div>
                                            <!--end pie chart-->
                                            <!--my content-->
                                            <div>
                                                <spring:message var="colRatingPoint" code="provider.list.table.rating.point"/>
                                                <div class="clearfix"></div>
                                                <div style="overflow-x:auto;" class="mb-md">
                                                    <table class="table table-bordered table-striped mb-small mt-none"
                                                           width="100%">
                                                        <thead>
                                                        <tr>
                                                            <th><spring:message code="transaction.api.table.provider"/></th>
                                                            <th><spring:message
                                                                    code="provider.service.table.service.code"/></th>
                                                            <th style="text-transform: uppercase;"><spring:message
                                                                    code="label.plan.point"/></th>
                                                            <th>${colRatingPoint}</th>
                                                            <th>${colRatingPoint} MIN</th>
                                                            <th>${colRatingPoint} MAX</th>
                                                            <th><spring:message code="reim.table.status"/></th>
                                                            <th><spring:message code="transaction.api.table.action"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%--loop here--%>
                                                        <c:forEach items="${service.value}" var="providerService"
                                                                   varStatus="loop">
                                                            <tr>
                                                                <td>${providerService.provider.providerBizCode}</td>
                                                                <td>${providerService.serviceCode}</td>
                                                                <td style="text-align: center">${providerService.getRankingScorePlanInt()}&nbsp;
                                                                    (
                                                                    <fmt:formatNumber type="number"
                                                                                      maxIntegerDigits="3"
                                                                                      value="${
                                                                      (providerService.getRankingScorePlanInt() != null && providerService.getRankingScorePlanInt() > 0)
                                                                      ? ((providerService.getRankingScoreInt() / providerService.getRankingScorePlanInt()) * 100) : 0
                                                                      }"/>%
                                                                    )
                                                                </td>
                                                                <td style="text-align: center">${providerService.getRankingScoreInt()}</td>
                                                                <td style="text-align: center">${providerService.getRankingScoreMinInt()}</td>
                                                                <td style="text-align: center">${providerService.getRankingScoreMaxInt()}</td>
                                                                    <%--enalble--%>
                                                                <c:set var="on"
                                                                       value="${providerService.enableRankingScore}"/>
                                                                <c:if test="${on}">
                                                                    <td class="text-center">
                                                                        <div class="switch switch-sm switch-success">
                                                                            <input type="checkbox" name="switch"
                                                                                   data-switchery="true"
                                                                                   style="display: none;"><span
                                                                                class="switchery switchery-small"
                                                                                style="background-color: rgb(100, 189, 99); border-color: rgb(100, 189, 99); box-shadow: rgb(100, 189, 99) 0px 0px 0px 11px inset; transition: border 0.4s ease 0s, box-shadow 0.4s ease 0s, background-color 1.2s ease 0s;"><small
                                                                                style="left: 13px; background-color: rgb(255, 255, 255); transition: background-color 0.4s ease 0s, left 0.2s ease 0s;"></small></span>
                                                                            <a href="#" class="switchery-mask"
                                                                               active="false"></a>
                                                                        </div>
                                                                    </td>
                                                                </c:if>
                                                                    <%--inactive--%>
                                                                <c:if test="${!on}">
                                                                    <td class="text-center">
                                                                        <div class="switch switch-sm switch-success">
                                                                            <input type="checkbox" name="switch"
                                                                                   data-switchery="true"
                                                                                   style="display: none;"><span
                                                                                class="switchery switchery-small"
                                                                                style="transition: border 0.4s ease 0s, box-shadow 0.4s ease 0s, background-color 1.2s ease 0s;"><small
                                                                                style="left: 0px; background-color: rgb(255, 255, 255); transition: background-color 0.4s ease 0s, left 0.2s ease 0s;"></small></span>
                                                                            <a href="#" class="switchery-mask"
                                                                               active="true"></a>
                                                                        </div>
                                                                    </td>
                                                                </c:if>
                                                                <td class="action_icon text-center"><a class="link-edit"
                                                                                                       onclick="openModal('${providerService.serviceCode}',
                                                                                                               '${providerService.provider.providerBizCode}',
                                                                                                               '${providerService.description}',
                                                                                                               '${providerService.serviceName}',
                                                                                                           ${providerService.rankingScoreMin},
                                                                                                           ${providerService.rankingScore},
                                                                                                           ${providerService.rankingScorePlan},
                                                                                                           ${providerService.rankingScoreMax},
                                                                                                           ${providerService.enableRankingScore},
                                                                                                           ${providerService.id})">
                                                                    <i class="fa fa-pencil" aria-hidden="true"></i></a></td>
                                                            </tr>
                                                        </c:forEach>
                                                            <%--end loop--%>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <!--end-->
                                        </c:when>
                                        <c:otherwise>
                                            <p class="mb-lg">No data</p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </c:forEach>
                    </section>
                </div>
            </div>
            <!-- end: page -->
        </section>
        <jsp:include page="service-edit-ranking.jsp"/>
        <jsp:include page="../include_page/footer.jsp"/>
        <jsp:include page="pie-chart.jsp"/>
    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_component/search_form_commons.jsp">
    <jsp:param name="selectServiceCodes" value="true"/>
    <jsp:param name="selectRankingGroups" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function () {
    });

    $('.bt-cancel').click(function () {
        $('#serviceCodes option:selected').each(function () {
            $(this).prop('selected', false);
        });
        $('#serviceCodes').multiselect('refresh');
        //-------------
        $('#rankingGroups option:selected').each(function () {
            $(this).prop('selected', false);
        });
        $('#rankingGroups').multiselect('refresh');
        //--------------
        $('#search-service').submit();
    })

    function openModal(serviceCode, bizCode, description,
                       serviceName, rankingScoreMin, rankingScore,
                       rankingScorePlan, rankingScoreMax,
                       enableRankingScore, providerServiceId) {
        $('#description').val(description);
        $('#serviceName').text(bizCode + " - " + serviceCode + " - " + serviceName);
        $('#rankingScoreMin').val(rankingScoreMin);
        $('#rankingScoreMax').val(rankingScoreMax);
        $('#rankingScore').val(rankingScore);
        $('#rankingScorePlan').val(rankingScorePlan);
        $("#enableRankingScore").val(enableRankingScore.toString()).change();
        $('#providerServiceId').val(providerServiceId);
        $('#updateService').modal('show');
    }
</script>
</body>
</html>


