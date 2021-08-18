<%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 8/13/2020
  Time: 2:17 PM
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
<body>
<section class="body">
    <jsp:include page="../include_page/header.jsp"/>

    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="provider-service" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="${contextPath}"><i class="fa fa-home"></i></a></li>
                                <li><span><spring:message code="menu.left.provider.service"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>
            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">
                    <%--<div style="display: flex; justify-content: space-between">--%>
                    <%--<div style="display: flex" class="mt-md">--%>
                    <%--<div class="h4 mb-md"><spring:message code="provider.list.providerProfile"/>:</div>--%>
                    <%--<div class="mb-md ml-md">--%>
                    <%--<select class="form-control" name="provider" id="provider"--%>
                    <%--onchange="injectBodyInsideTable()">--%>
                    <%--<c:forEach var="provider" items="${providers}">--%>
                    <%--<option style="font-size: medium"--%>
                    <%--value="${provider}">${provider}</option>--%>
                    <%--</c:forEach>--%>
                    <%--</select>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<div style="align-self: center;">--%>
                    <%--<button class="btn btn-primary" type="button" onclick="openResetModal()"><i--%>
                    <%--class="fa fa-recycle"></i>&nbsp;RESET SCORE--%>
                    <%--</button>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <section class="panel search_payment panel-default mt-md">
                        <div class="panel-body pt-none">
                            <ol class="breadcrumb" style="background-color: #2582c4;">
                                <li><a href="#" style="color: white">Group A</a></li>
                            </ol>
                            <c:forEach items="${PTU_A}" var="provider">
                                <div class="ml-md">
                                    <div style="display: flex; justify-content: space-between" class="mb-xs">
                                        <h4 style="align-self: center">${ewallet:getProviderBizCode(provider.providerCode)}</h4>
                                        <div>
                                            <button class="btn btn-primary" type="button"
                                                    onclick="openResetModal('${provider.providerCode}')"><i
                                                    class="fa fa-recycle"></i>&nbsp;RESET SCORE
                                            </button>
                                        </div>
                                    </div>
                                    <spring:message var="colRatingPoint" code="provider.list.table.rating.point"/>
                                    <div class="clearfix"></div>
                                    <div style="overflow-x:auto;" class="mb-md">
                                        <table class="table table-bordered table-striped mb-small mt-none" width="100%">
                                            <thead>
                                            <tr>
                                                <th><spring:message code="provider.service.table.service.name"/></th>
                                                <th><spring:message code="provider.service.table.service.code"/></th>
                                                <th><spring:message code="provider.service.table.description"/></th>
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
                                            <c:forEach items="${provider.providerServices}" var="providerService">
                                                <tr>
                                                    <td>${providerService.serviceName}</td>
                                                    <td>${providerService.serviceCode}</td>
                                                    <td>${providerService.serviceDesc}</td>
                                                    <td style="text-align: center">${providerService.getRankingScorePlanInt()}&nbsp;(
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
                                                    <c:set var="on" value="${providerService.enableRankingScore}"/>
                                                    <c:if test="${on}">
                                                        <td class="text-center">
                                                            <div class="switch switch-sm switch-success">
                                                                <input type="checkbox" name="switch"
                                                                       data-switchery="true"
                                                                       style="display: none;"><span
                                                                    class="switchery switchery-small"
                                                                    style="background-color: rgb(100, 189, 99); border-color: rgb(100, 189, 99); box-shadow: rgb(100, 189, 99) 0px 0px 0px 11px inset; transition: border 0.4s ease 0s, box-shadow 0.4s ease 0s, background-color 1.2s ease 0s;"><small
                                                                    style="left: 13px; background-color: rgb(255, 255, 255); transition: background-color 0.4s ease 0s, left 0.2s ease 0s;"></small></span>
                                                                <a href="#" class="switchery-mask" active="false"></a>
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
                                                                <a href="#" class="switchery-mask" active="true"></a>
                                                            </div>
                                                        </td>
                                                    </c:if>
                                                    <td class="action_icon text-center"><a class="link-edit"
                                                                                           onclick="openModal('${providerService.description}','${providerService.serviceName}',${providerService.rankingScoreMin},${providerService.rankingScore},${providerService.rankingScorePlan},${providerService.rankingScoreMax},${providerService.enableRankingScore},${providerService.id})"><i
                                                            class="fa fa-pencil" aria-hidden="true"></i></a></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </c:forEach>
                            <%--xxxxxxxxxxxxxxxx--%>
                            <ol class="breadcrumb" style="background-color: #2582c4;">
                                <li><a href="#" style="color: white">Group B</a></li>
                            </ol>
                            <c:forEach items="${PTU_B}" var="provider">
                                <div class="ml-md">
                                    <div style="display: flex; justify-content: space-between" class="mb-xs">
                                        <h4 style="align-self: center">${ewallet:getProviderBizCode(provider.providerCode)}</h4>
                                        <div>
                                            <button class="btn btn-primary" type="button"
                                                    onclick="openResetModal('${provider.providerCode}')"><i
                                                    class="fa fa-recycle"></i>&nbsp;RESET SCORE
                                            </button>
                                        </div>
                                    </div>
                                    <spring:message var="colRatingPoint" code="provider.list.table.rating.point"/>
                                    <div class="clearfix"></div>
                                    <div style="overflow-x:auto;" class="mb-md">
                                        <table class="table table-bordered table-striped mb-small mt-none" width="100%">
                                            <thead>
                                            <tr>
                                                <th><spring:message code="provider.service.table.service.name"/></th>
                                                <th><spring:message code="provider.service.table.service.code"/></th>
                                                <th><spring:message code="provider.service.table.description"/></th>
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
                                            <c:forEach items="${provider.providerServices}" var="providerService">
                                                <tr>
                                                    <td>${providerService.serviceName}</td>
                                                    <td>${providerService.serviceCode}</td>
                                                    <td>${providerService.serviceDesc}</td>
                                                    <td style="text-align: center">${providerService.getRankingScorePlanInt()}&nbsp;(
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
                                                    <c:set var="on" value="${providerService.enableRankingScore}"/>
                                                    <c:if test="${on}">
                                                        <td class="text-center">
                                                            <div class="switch switch-sm switch-success">
                                                                <input type="checkbox" name="switch"
                                                                       data-switchery="true"
                                                                       style="display: none;"><span
                                                                    class="switchery switchery-small"
                                                                    style="background-color: rgb(100, 189, 99); border-color: rgb(100, 189, 99); box-shadow: rgb(100, 189, 99) 0px 0px 0px 11px inset; transition: border 0.4s ease 0s, box-shadow 0.4s ease 0s, background-color 1.2s ease 0s;"><small
                                                                    style="left: 13px; background-color: rgb(255, 255, 255); transition: background-color 0.4s ease 0s, left 0.2s ease 0s;"></small></span>
                                                                <a href="#" class="switchery-mask" active="false"></a>
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
                                                                <a href="#" class="switchery-mask" active="true"></a>
                                                            </div>
                                                        </td>
                                                    </c:if>
                                                    <td class="action_icon text-center"><a class="link-edit"
                                                                                           onclick="openModal('${providerService.description}','${providerService.serviceName}',${providerService.rankingScoreMin},${providerService.rankingScore},${providerService.rankingScorePlan},${providerService.rankingScoreMax},${providerService.enableRankingScore},${providerService.id})"><i
                                                            class="fa fa-pencil" aria-hidden="true"></i></a></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </c:forEach>
                            <%--xxxxxxxxxxxxxxxx--%>
                            <ol class="breadcrumb" style="background-color: #2582c4;">
                                <li><a href="#" style="color: white">Group C</a></li>
                            </ol>
                            <c:forEach items="${PTU_C}" var="provider">
                                <div class="ml-md">
                                    <div style="display: flex; justify-content: space-between" class="mb-xs">
                                        <h4 style="align-self: center">${ewallet:getProviderBizCode(provider.providerCode)}</h4>
                                        <div>
                                            <button class="btn btn-primary" type="button"
                                                    onclick="openResetModal('${provider.providerCode}')"><i
                                                    class="fa fa-recycle"></i>&nbsp;RESET SCORE
                                            </button>
                                        </div>
                                    </div>
                                    <spring:message var="colRatingPoint" code="provider.list.table.rating.point"/>
                                    <div class="clearfix"></div>
                                    <div style="overflow-x:auto;" class="mb-md">
                                        <table class="table table-bordered table-striped mb-small mt-none"
                                               width="100%">
                                            <thead>
                                            <tr>
                                                <th><spring:message
                                                        code="provider.service.table.service.name"/></th>
                                                <th><spring:message
                                                        code="provider.service.table.service.code"/></th>
                                                <th><spring:message code="provider.service.table.description"/></th>
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
                                            <c:forEach items="${provider.providerServices}" var="providerService">
                                                <tr>
                                                    <td>${providerService.serviceName}</td>
                                                    <td>${providerService.serviceCode}</td>
                                                    <td>${providerService.serviceDesc}</td>
                                                    <td style="text-align: center">${providerService.getRankingScorePlanInt()}&nbsp;(
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
                                                    <c:set var="on" value="${providerService.enableRankingScore}"/>
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
                                                                                           onclick="openModal('${providerService.description}','${providerService.serviceName}',${providerService.rankingScoreMin},${providerService.rankingScore},${providerService.rankingScorePlan},${providerService.rankingScoreMax},${providerService.enableRankingScore},${providerService.id})"><i
                                                            class="fa fa-pencil" aria-hidden="true"></i></a></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </c:forEach>
                            <%--xxxxxxxxxxxxxxxx--%>
                            <c:if test="${fn:length(NO_GROUP) > 0}">
                                <ol class="breadcrumb" style="background-color: #2582c4;">
                                    <li><a href="#" style="color: white">Không trong group nào</a></li>
                                </ol>
                                <c:forEach items="${NO_GROUP}" var="provider">
                                    <div class="ml-md">
                                        <div style="display: flex; justify-content: space-between" class="mb-xs">
                                            <h4 style="align-self: center">${ewallet:getProviderBizCode(provider.providerCode)}</h4>
                                            <div>
                                                <button class="btn btn-primary" type="button"
                                                        onclick="openResetModal('${provider.providerCode}')"><i
                                                        class="fa fa-recycle"></i>&nbsp;RESET SCORE
                                                </button>
                                            </div>
                                        </div>
                                        <spring:message var="colRatingPoint" code="provider.list.table.rating.point"/>
                                        <div class="clearfix"></div>
                                        <div style="overflow-x:auto;" class="mb-md">
                                            <table class="table table-bordered table-striped mb-small mt-none"
                                                   width="100%">
                                                <thead>
                                                <tr>
                                                    <th><spring:message
                                                            code="provider.service.table.service.name"/></th>
                                                    <th><spring:message
                                                            code="provider.service.table.service.code"/></th>
                                                    <th><spring:message code="provider.service.table.description"/></th>
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
                                                <c:forEach items="${provider.providerServices}" var="providerService">
                                                    <tr>
                                                        <td>${providerService.serviceName}</td>
                                                        <td>${providerService.serviceCode}</td>
                                                        <td>${providerService.serviceDesc}</td>
                                                        <td style="text-align: center">${providerService.getRankingScorePlanInt()}&nbsp;(
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
                                                        <c:set var="on" value="${providerService.enableRankingScore}"/>
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
                                                                                               onclick="openModal('${providerService.description}','${providerService.serviceName}',${providerService.rankingScoreMin},${providerService.rankingScore},${providerService.rankingScorePlan},${providerService.rankingScoreMax},${providerService.enableRankingScore},${providerService.id})"><i
                                                                class="fa fa-pencil" aria-hidden="true"></i></a></td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </section>
                </div>
            </div>

            <!-- end: page -->
        </section>
        <jsp:include page="../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="service-edit-ranking.jsp"/>
<jsp:include page="service-reset-ranking.jsp"/>
<jsp:include page="../include_page/js_footer.jsp"/>
<script type="text/javascript">
    $(document).ready(function () {
        // injectBodyInsideTable();
    });

    // unused method
    function injectBodyInsideTable() {
        var providerCode = $("#provider").val();
        $("table tbody").empty();
        $.ajax({
            type: 'GET',
            url: "${contextPath}/ajax/provider/get/" + providerCode,
            success: function (data) {
                var providerServices = data.providerServices;
                if (providerServices) {
                    providerServices.forEach(injectData);
                } else {
                    $("table tbody").append('<tr>\n'
                        + '          <td colspan="8" rowspan="7" style="text-align: center">No Data</td>\n'
                        + '          </tr>');

                }
            },
            error: function (data) {
                $.MessageBox({message: data});

            }
        });
    }

    // unused method
    function injectData(item, index) {
        var serviceName = "'" + item.serviceName.trim() + "'";
        var rankingScoreMin = item.rankingScoreMin;
        var rankingScorePlan = item.rankingScorePlan;
        var rankingScoreMax = item.rankingScoreMax;
        var rankingScore = item.rankingScore;
        var enableRankingScore = item.enableRankingScore;
        var providerServiceId = item.id;
        var status = item.enableRankingScore ? iconActive : iconInactive;
        var percent = (rankingScore / rankingScorePlan) * 0.1;
        var markup = '<tr>\n'
            + '                  <td>' + item.serviceName + '</td>\n'
            + '                  <td>' + item.serviceCode + '</td>\n'
            + '                  <td>' + item.serviceDesc + '</td>\n'
            + '                  <td style="text-align: center">' + item.rankingScorePlan + '(' + percent + ')' + '</td>\n'
            + '                  <td style="text-align: center">' + item.rankingScore + '</td>\n'
            + '                  <td style="text-align: center">' + item.rankingScoreMin + '</td>\n'
            + '                  <td style="text-align: center">' + item.rankingScoreMax + '</td>\n'
            + '                  <td class="text-center">' + status + '</td>\n'
            + '                  <td class="action_icon text-center"><a class="link-edit"\n'
            + '                       onclick="openModal(' + serviceName + ',' + rankingScoreMin + ','
            + rankingScore + ',' + rankingScorePlan + ',' + rankingScoreMax + ',' + enableRankingScore + ',' + providerServiceId
            + ')"><i class="fa fa-pencil" aria-hidden="true"></i></a></td>\n'
            + '                </tr>';
        $("table tbody").append(markup);
    }

    function openModal(description, serviceName, rankingScoreMin, rankingScore, rankingScorePlan, rankingScoreMax,
                       enableRankingScore,
                       providerServiceId) {
        $('#description').val(description);
        $('#serviceName').text(serviceName);
        $('#rankingScoreMin').val(rankingScoreMin);
        $('#rankingScoreMax').val(rankingScoreMax);
        $('#rankingScore').val(rankingScore);
        $('#rankingScorePlan').val(rankingScorePlan);
        $("#enableRankingScore").val(enableRankingScore.toString()).change();
        $('#providerServiceId').val(providerServiceId);
        $('#updateService').modal('show');
    }

    // function openResetModal() {
    //   // resetService
    //   document.getElementById("reset-form").reset();
    //   $("#provider2").val($("#provider").val()).change();
    //   $('#resetService').modal('show');
    // }

    function openResetModal(providerCode) {
        // resetService
        document.getElementById("reset-form").reset();
        $("#provider2").val(providerCode).change();
        $('#resetService').modal('show');
    }

    var iconInactive = '<div class="switch switch-sm switch-success">\n'
        + '                    <input type="checkbox" name="switch" data-switchery="true" style="display: none;"><span class="switchery switchery-small" style="transition: border 0.4s ease 0s, box-shadow 0.4s ease 0s, background-color 1.2s ease 0s;"><small style="left: 0px; background-color: rgb(255, 255, 255); transition: background-color 0.4s ease 0s, left 0.2s ease 0s;"></small></span>\n'
        + '                        <a href="#" class="switchery-mask" active="true"></a>\n'
        + '                      </div>';
    var iconActive = '<div class="switch switch-sm switch-success">\n'
        + '                    <input type="checkbox" name="switch" data-switchery="true" style="display: none;"><span class="switchery switchery-small" style="background-color: rgb(100, 189, 99); border-color: rgb(100, 189, 99); box-shadow: rgb(100, 189, 99) 0px 0px 0px 11px inset; transition: border 0.4s ease 0s, box-shadow 0.4s ease 0s, background-color 1.2s ease 0s;"><small style="left: 13px; background-color: rgb(255, 255, 255); transition: background-color 0.4s ease 0s, left 0.2s ease 0s;"></small></span>\n'
        + '                        <a href="#" class="switchery-mask" active="false"></a>\n'
        + '                      </div>'
</script>

</body>

</html>

