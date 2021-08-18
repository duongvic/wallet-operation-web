<%@ include file="../include_page/taglibs.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="edit" id="edit-form" action="updateProvider" method="post">
                <input type="hidden" name="providerCode"/>
                <input type="hidden" name="providerId"/>

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only"><spring:message
                            code="popup.header.icon.close"/></span></button>
                    <h4 class="modal-title ttitle" id="myModalLabel"><spring:message
                            code="provider.edit.title.edit.information"/></h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-md-5 control-label"><spring:message
                                code="provider.edit.title.code"/></label>
                        <div class="col-md-7">
                            <span name="code"/>
                        </div>
                    </div>
                    <%--new--%>
                    <div class="form-group">
                        <label class="col-md-5 control-label">Provider Biz Code</label>
                        <div class="col-md-7">
                            <input type="text" name="providerBizCode" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-5 control-label">Provider Group</label>
                        <div class="col-md-7">
                            <%--<input type="text" name="providerGroup" class="form-control">--%>
                            <select class="form-control" name="providerGroup">
                                <c:forEach items="${providerGroups}" var="providerGroup">
                                    <option value="${providerGroup.getCode()}">
                                            ${providerGroup.getCode()}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-5 control-label">Ranking Group</label>
                        <div class="col-md-7">
                            <select class="form-control" name="rankingGroup">
                                <c:forEach items="${providerRankingGroups}" var="providerRankingGroup">
                                    <option value="${providerRankingGroup.getCode()}">${providerRankingGroup.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-5 control-label">Ranking Level</label>
                        <div class="col-md-7">
                            <select class="form-control" name="rankingLevel">
                                <c:forEach items="${providerRankingLevels}" var="providerRankingLevel">
                                    <option value="${providerRankingLevel.getCode()}">${providerRankingLevel.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--end--%>

                    <div class="form-group">
                        <label class="col-md-5 control-label"><spring:message
                                code="provider.edit.title.name"/></label>
                        <div class="col-md-7">
                            <input type="text" name="name" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-5 control-label"><spring:message
                                code="provider.edit.title.relationship"/></label>
                        <div class="col-md-7">
                            <input type="number" name="relationship" id="rankingScore" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-5 control-label"><spring:message
                                code="provider.edit.title.relationship"/> MIN</label>
                        <div class="col-md-7">
                            <input type="number" name="rankingScoreMin" id="rankingScoreMin" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-5 control-label"><spring:message
                                code="provider.edit.title.relationship"/> MAX</label>
                        <div class="col-md-7">
                            <input type="number" name="rankingScoreMax" id="rankingScoreMax" class="form-control">
                        </div>
                    </div>
                    <%--loại khách hàng--%>
                    <div class="form-group">
                        <label class="col-md-5 control-label"><spring:message
                                code="system.service.popup.update.lable.customerType"/></label>
                        <div class="col-md-7">
                            <input type="text" name="customerTypeSupported" class="form-control">
                        </div>
                    </div>
                    <%--end loại khách hàng--%>
                    <%--loại NCC--%>
                    <div class="form-group">
                        <label class="col-md-5 control-label"><spring:message code="select.provider"/></label>
                        <div class="col-md-7" style="display: flex;">
                            <div class="form-check" style="margin-left: 4px">
                                <input class="form-check-input" style="transform: scale(1.5)" type="radio"
                                       name="provider" id="common-provider" value="false"
                                       onclick="changeListCifLabel('false')"/>
                                <label class="form-check-label" for="common-provider" style="margin: 4px">
                                    <spring:message code="label.common.provider"/>
                                </label>
                            </div>
                            <div class="col-md-2"></div>
                            <div class="form-check">
                                <input class="form-check-input" style="transform: scale(1.5)" type="radio"
                                       name="provider" id="specific-provider" value="true"
                                       onclick="changeListCifLabel('true')"/>
                                <label class="form-check-label" for="specific-provider" style="margin: 4px">
                                    <spring:message code="label.specific.provider"/>
                                </label>
                            </div>
                        </div>
                    </div>
                    <%--end loại NCC--%>
                    <%--khách hàng được gọi dịch vụ--%>
                    <div class="form-group" id="allow">
                        <label class="col-md-5 control-label" id="list-cif-label"><spring:message
                                code="label.customer.allow.call.service"/>&nbsp;*</label>
                        <div class="col-md-7">
                            <input type="text" name="listCif" class="form-control">
                        </div>
                    </div>
                    <%--end khách hàng được gọi dịch vụ--%>
                    <%--khách hàng không được gọi dịch vụ--%>
                    <div class="form-group" id="block">
                        <label class="col-md-5 control-label" id="black-list-cif-label"><spring:message
                                code="label.customer.block.call.service"/></label>
                        <div class="col-md-7">
                            <input type="text" name="blackListCif" class="form-control">
                        </div>
                    </div>
                    <%--end khách hàng không được gọi dịch vụ--%>
                    <div class="alert alert-default mb-none mt-md p-sm">
                        <div class="checkbox-custom checkbox-success">
                            <input type="checkbox" name="ckaccess" id="checkboxExample2">
                            <label for="checkboxExample2"><spring:message
                                    code="common.btn.do.you.want.to.change"/></label>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default " data-dismiss="modal"><spring:message
                            code="common.btn.cancel"/></button>
                    <button type="submit" class="btn btn-primary btnSubmit"><spring:message
                            code="button.update"/></button>
                </div>
                <sec:csrfInput/></form>

            <script type="text/javascript">
                $(document).ready(function () {
                    $(".link-edit").click(function () {
                        resetAllvalue();
                        $("form[name=edit] input[name=providerCode]").val($(this).attr("providerCode"));
                        $("form[name=edit] input[name=providerId]").val($(this).attr("providerId"));
                        $("form[name=edit] span[name=code]").text($(this).attr("providerCode"));
                        $("form[name=edit] input[name=name]").val($(this).attr("name"));
                        $("form[name=edit] input[name=relationship]").val($(this).attr("relationship"));
                        $("form[name=edit] input[name=rankingScoreMin]").val($(this).attr("rankingScoreMin"));
                        $("form[name=edit] input[name=rankingScoreMax]").val($(this).attr("rankingScoreMax"));
                        $("form[name=edit] select[name=providerGroup]").val($(this).attr("providerGroup")).trigger('change');
                        $("form[name=edit] input[name=providerBizCode]").val($(this).attr("providerBizCode"));
                        $("form[name=edit] select[name=rankingGroup]").val($(this).attr("rankingGroup")).trigger('change');
                        $("form[name=edit] select[name=rankingLevel]").val($(this).attr("rankingLevel")).trigger('change');
                        $("form[name=edit] input[name=customerTypeSupported]").val(
                            $(this).attr("customerTypeSupported"));
                        $("#check").text($(this).attr("specific"));
                        if ($(this).attr("specific") == "true") {
                            $("#specific-provider").attr("checked", true);
                            $("#common-provider").removeAttr("checked");
                            $("form[name=edit] input[name=listCif]").prop("disabled", false);
                            $("form[name=edit] input[name=blackListCif]").prop("disabled", true);
                        } else {
                            $("#common-provider").attr("checked", true);
                            $("#specific-provider").removeAttr("checked");
                            $("form[name=edit] input[name=listCif]").prop("disabled", true);
                            $("form[name=edit] input[name=blackListCif]").prop("disabled", false);
                        }

                        $("form[name=edit] input[name=listCif]").val($(this).attr("listCif"));
                        $("form[name=edit] input[name=blackListCif]").val($(this).attr("blackListCif"));
                    });

                    $('form[name=edit]').submit(function () {
                        var rankingScoreMin = parseFloat($('#rankingScoreMin').val());
                        var rankingScoreMax = parseFloat($('#rankingScoreMax').val());
                        var rankingScore = parseFloat($('#rankingScore').val());

                        if (rankingScoreMin == null || isNaN(rankingScoreMin) || rankingScoreMin <= 0) {
                            $.MessageBox(
                                {message: "<spring:message code="label.rank.score.min.isnt.smaller.than.zero"/>"});
                            return false;
                        }

                        if (rankingScoreMax == null || isNaN(rankingScoreMax) || rankingScoreMax <= 0) {
                            $.MessageBox(
                                {message: "<spring:message code="label.rank.score.max.isnt.smaller.than.zero"/>"});
                            return false;
                        }

                        if (rankingScore == null || isNaN(rankingScore) || rankingScore <= 0) {
                            $.MessageBox(
                                {message: "<spring:message code="label.rank.score.isnt.smaller.than.zero"/>"});
                            return false;
                        }

                        if (rankingScore < rankingScoreMin) {
                            $.MessageBox(
                                {message: "<spring:message code="label.rank.score.isnt.smaller.than.rank.score.min"/>"});
                            return false;
                        }

                        if (rankingScore > rankingScoreMax) {
                            $.MessageBox(
                                {message: "<spring:message code="label.rank.score.isnt.larger.than.rank.score.max"/>"});
                            return false;
                        }

                        if (!$("form[name=edit] input[name=listCif]").is(":disabled")) {
                            if (validatePatternListCif("form[name=edit] input[name=listCif]") == false) {
                                $.MessageBox(
                                    {message: '<spring:message code="label.customer.allow.call.service.error"/>'});
                                return false;
                            }
                        }

                        if (!$("form[name=edit] input[name=blackListCif]").is(":disabled") && $(
                            "form[name=edit] input[name=blackListCif]").val().trim() != '') {
                            if (validatePatternListCif("form[name=edit] input[name=blackListCif]") == false) {
                                $.MessageBox(
                                    {message: '<spring:message code="label.customer.block.call.service.error"/>'});
                                return false;
                            }
                        }

                        var answer = $('form[name=edit] input[name=ckaccess]').is(":checked");
                        if (answer) {
                            var poid = $('form[name=edit] input[name=providerCode]').val();
                            $(".btnSubmit").button('loading');
                            $.post($(this).attr('action'), $(this).serialize(), function (json) {
                                $.MessageBox({message: json.message});
                                if (json.code == 0) {
                                    $('#edit').modal('toggle');
                                    location.reload();
                                }
                                setTimeout(function () {
                                    $(".btnSubmit").button('reset');
                                }, 1000);
                            });
                        } else {
                            $.MessageBox(
                                {message: '<spring:message code="message.confirm.the.approval.request"/>'});
                        }
                        return false;
                    });
                });

                function resetAllvalue() {
                    <%--$("form[name=edit] input[name=name]").val("");--%>
                    <%--$("form[name=edit] input[name=relationship]").val("");--%>
                    <%--$("form[name=edit] input[name=providerCode]").val("");--%>
                    <%--$("form[name=edit] input[name=listCif]").val("");--%>
                    <%--$("form[name=edit] input[name=customerTypeSupported]").val("");--%>
                    <%--// $("#common-provider").attr("checked", true);--%>
                    <%--// $("#specific-provider").attr("checked", false);--%>
                    <%--$("#list-cif-label").text("<spring:message code="label.customer.block.call.service"/>")--%>
                    document.getElementById("edit-form").reset();
                }

                function changeListCifLabel(specific) {
                    if (specific == "false") {
                        $("form[name=edit] input[name=listCif]").prop("disabled", true);
                        $("form[name=edit] input[name=blackListCif]").prop("disabled", false);
                    } else {
                        $("form[name=edit] input[name=listCif]").prop("disabled", false);
                        $("form[name=edit] input[name=blackListCif]").prop("disabled", true);
                    }

                }

                function validatePatternListCif(id) {
                    var pattern = /^([|]([0-9])+)+[|]$/g
                    var listCif = $(id).val();
                    return pattern.test(listCif);
                }
            </script>
        </div>
    </div>
</div>