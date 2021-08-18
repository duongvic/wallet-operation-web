<%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 8/13/2020
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sping" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include_page/taglibs.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<spring:message var="colRatingPoint" code="provider.list.table.rating.point"/>
<div class="modal fade" id="updateService" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="edit" id="edit-form">
                <input type="hidden" name="providerServiceId" id="providerServiceId"/>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only"><spring:message
                            code="popup.header.icon.close"/></span></button>
                    <h4 class="modal-title ttitle" id="myModalLabel"><spring:message
                            code="provider.edit.title.edit.information"/>
                        </br>
                        <span id="serviceName"></span></h4>
                </div>
                <div class="modal-body">
                    <div>
                        <div>
                            <span><b>${colRatingPoint}</b></span>
                            <input class="form-control" id="rankingScore" name="rankingScore" type="number"
                                   placeholder="${colRatingPoint}"
                            >
                        </div>
                        <div>
                            <span><b>${colRatingPoint} MIN</b></span>
                            <input class="form-control" name="rankingScoreMin" id="rankingScoreMin" type="number"
                                   placeholder="${colRatingPoint} MIN">
                        </div>
                        <div>
                            <span><b>${colRatingPoint} MAX</b></span>
                            <input class="form-control" id="rankingScoreMax" name="rankingScoreMax" type="number"
                                   placeholder="${colRatingPoint} MAX"
                            >
                        </div>
                    </div>
                    <div>
                        <spring:message code="label.plan.point" var="colPlanPoint"/>
                        <span style="text-transform: uppercase;"><b>${colPlanPoint}&nbsp;(<spring:message
                                code="label.input.if.provider.plan.change"/>)</b></span>
                        <input class="form-control" id="rankingScorePlan" name="rankingScorePlan" type="number"
                               placeholder="${colPlanPoint}"
                        >
                    </div>
                    <div>
                        <span style="text-transform: uppercase;"><b><spring:message
                                code="system.service.detail.summary.row.description"/></b></span>
                        <textarea rows="1" class="form-control" id="description" name="description"></textarea>
                    </div>
                    <div style="display: flex" class="mt-md">
                        <label style="align-self: center"><b><spring:message
                                code="reim.table.status"/></b></label>
                        <div class="ml-md">
                            <select class="form-control" id="enableRankingScore" name="enableRankingScore">
                                <option value="true">ON</option>
                                <option value="false">OFF</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default " data-dismiss="modal"><spring:message
                            code="common.btn.cancel"/></button>
                    <button type="button" onclick="handleSubmit()" class="btn btn-primary btnSubmit">
                        <spring:message
                                code="button.update"/></button>
                </div>
                <sec:csrfInput/></form>
        </div>
    </div>
</div>
<script type="text/javascript">
    function handleSubmit() {
        var rankingScoreMin = parseFloat($('#rankingScoreMin').val());
        var rankingScorePlan = parseFloat($('#rankingScorePlan').val());
        var rankingScoreMax = parseFloat($('#rankingScoreMax').val());
        var rankingScore = parseFloat($('#rankingScore').val());
        var enableRankingScore = $("#enableRankingScore").val();
        var description = $("#description").val();
        var updateProviderServiceRankingScoreRequest = new FormData();
        updateProviderServiceRankingScoreRequest.append('rankingScoreMin', rankingScoreMin);
        updateProviderServiceRankingScoreRequest.append('rankingScorePlan', rankingScorePlan);
        updateProviderServiceRankingScoreRequest.append('rankingScoreMax', rankingScoreMax);
        updateProviderServiceRankingScoreRequest.append('rankingScore', rankingScore);
        updateProviderServiceRankingScoreRequest.append('enableRankingScore', enableRankingScore);
        updateProviderServiceRankingScoreRequest.append('description', description);
        updateProviderServiceRankingScoreRequest.append('providerServiceId',
            $('#providerServiceId').val());
        if (rankingScoreMin == null || isNaN(rankingScoreMin) || rankingScoreMin <= 0) {
            $.MessageBox(
                {message: "<spring:message code="label.rank.score.min.isnt.smaller.than.zero"/>"});
            return;
        }

        if (rankingScoreMax == null || isNaN(rankingScoreMax) || rankingScoreMax <= 0) {
            $.MessageBox(
                {message: "<spring:message code="label.rank.score.max.isnt.smaller.than.zero"/>"});
            return;
        }

        if (rankingScore == null || isNaN(rankingScore) || rankingScore <= 0) {
            $.MessageBox({message: "<spring:message code="label.rank.score.isnt.smaller.than.zero"/>"});
            return;
        }

        if (rankingScore < rankingScoreMin) {
            $.MessageBox(
                {message: "<spring:message code="label.rank.score.isnt.smaller.than.rank.score.min"/>"});
            return;
        }

        if (rankingScore > rankingScoreMax) {
            $.MessageBox(
                {message: "<spring:message code="label.rank.score.isnt.larger.than.rank.score.max"/>"});
            return;
        }
        // ------------------------------------------------
        if (rankingScorePlan == null || isNaN(rankingScorePlan) || rankingScorePlan <= 0) {
            $.MessageBox({message: "<spring:message code="label.rank.score.plan.isnt.smaller.than.zero"/>"});
            return;
        }

        if (rankingScorePlan < rankingScoreMin) {
            $.MessageBox(
                {message: "<spring:message code="label.rank.score.plan.isnt.smaller.than.rank.score.min"/>"});
            return;
        }

        if (rankingScorePlan > rankingScoreMax) {
            $.MessageBox(
                {message: "<spring:message code="label.rank.score.plan.isnt.larger.than.rank.score.max"/>"});
            return;
        }

        $.ajax({
            type: 'POST',
            url: "${contextPath}/ajax/provider/update",
            data: updateProviderServiceRankingScoreRequest,
            processData: false,
            contentType: false,
            success: function (data) {
                $.MessageBox({message: "Success!"});
                $('#updateService').modal('hide');
                location.reload();
            },
            error: function (data) {
                $.MessageBox({message: data.responseJSON.status.value});
            }
        });
    }
</script>