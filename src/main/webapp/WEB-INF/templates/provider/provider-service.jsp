<%@ page import="vn.mog.ewallet.operation.web.controller.provider.ProviderController" %>
<%@ include file="../include_page/taglibs.jsp" %>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style>
    @media (min-width: 1200px) {
        .modal-xlg {
            width: 90%;
        }
    }

    /*#content {*/
    /*    height: 200px;*/
    /*    !*display: flex;*!*/
    /*    !*flex-direction: column;*!*/
    /*}*/

    /*.table-wrapper {*/
    /*    overflow-y: scroll;*/
    /*    height: 150px;*/
    /*}*/

    /*.table-wrapper th {*/
    /*    position: sticky;*/
    /*    top: 0;*/
    /*}*/

    /*!* A bit more styling to make it look better *!*/

    /*#content table {*/
    /*    border-collapse: collapse;*/
    /*}*/

    /*#content th {*/
    /*    background: #DDD;*/
    /*    z-index: 2;*/
    /*    height: 110%;*/
    /*}*/

    /*#content td, th {*/
    /*    padding: 10px;*/
    /*}*/
</style>
<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-xlg">
        <div class="modal-content">
            <form name="detail" action="getProvider" method="post">
                <input type="hidden" name="providerCode"/>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
                            code="popup.header.icon.close"/></span>
                    </button>
                    <h4 class="modal-title ttitle" id="myModalLabel"><spring:message
                            code="provider.service.title.operation"/></h4>
                </div>
                <div class="modal-body" style="max-height:500px; overflow:auto;">
                    <div class="form-group">
                        <label class="col-md-3 control-label"><spring:message code="provider.edit.title.name"/></label>
                        <div class="col-md-3"><p class="primary_color tname">Viettorrent</p></div>
                        <label class="col-md-3 control-label"><spring:message
                                code="provider.edit.title.healthy"/></label>
                        <div class="col-md-3">
                            <div class="healthy">
                                <button type="button" title="<spring:message code="common.title.dangerous" />"
                                        class="mb-xs mt-xs btn btn-xs btn btn-danger RED" style="margin-right: 2px;">bad
                                </button>
                                <button type="button" title="<spring:message code="common.title.warning" />"
                                        class="mb-xs mt-xs btn btn-xs btn btn-primary YELLOW"
                                        style="margin-right: 2px;">warning
                                </button>
                                <button type="button" title="<spring:message code="common.title.stable.operation"/>"
                                        class="mb-xs mt-xs btn btn-xs btn btn-success-green GREEN"
                                        style="margin-right: 2px;">good
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><spring:message code="provider.edit.title.code"/></label>
                        <div class="col-md-3">
                            <p class="primary_color tcode">Viettorrent</p>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><spring:message
                                code="provider.service.title.rating.point"/> </label>
                        <div class="col-md-3">
                            <p class="primary_color trating">Viettorrent</p>
                        </div>
                    </div>
                    <div class="panel-title pl-md">
                        <h4 class="fl"><spring:message code="provider.service"/></h4>
                        <div class="clr"></div>
                    </div>

                    <spring:message var="colStt" code="provider.service.table.no"/>
                    <spring:message var="colServiceType" code="provider.service.table.service.type"/>
                    <spring:message var="colCode" code="provider.service.table.service.code"/>
                    <spring:message var="colName" code="provider.service.table.service.name"/>
                    <spring:message var="colDescription" code="provider.service.table.description"/>
                    <spring:message var="colHealthy" code="provider.service.table.healthy"/>
                    <spring:message var="colAction" code="provider.service.table.action"/>
                    <spring:message var="colCusType" code="transaction.api.table.customer.type"/>
                    <spring:message var="colCusBlock" code="label.customer.block.call.service"/>


                    <div class="table-responsive pl-md pr-md table-wrapper" style="overflow-x:auto;" id="content">
                        <table id="tb-detail" class="table table-bordered table-striped mb-none">
                            <thead>
                            <tr>
                                <th>${colStt}</th>
                                <th>${colServiceType}</th>
                                <th>${colCode}</th>
                                <th>${colName}</th>
                                <%--<th>${colDescription}</th>--%>
                                <th>${colAction}</th>
                                <th>${colHealthy}</th>
                                <th>${colCusType}</th>
                                <th style="text-transform: uppercase">${colCusBlock}</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="odd">
                                <td class="id"></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default " data-dismiss="modal"><spring:message
                            code="common.btn.cancel"/></button>
                </div>
                <sec:csrfInput/></form>

            <script type="text/javascript">
                String.prototype.replaceBetween = function (start, end, what) {
                    return this.substring(0, start) + what + this.substring(end);
                };
                $(document).ready(function () {
                    var pProService = '${pProService}';
                    var pProviderCode = '${providerCode}';
                    if (pProService === 'on' && pProviderCode != null && pProviderCode != '') {
                        resetAllvalue();
                        $('form[name=detail] input[name=providerCode]').val(pProviderCode);
                        contentPopupProviderService();
                        $('#detail').modal('show');
                    }

                    function contentPopupProviderService() {
                        $.post($('form[name=detail]').attr('action'), $('form[name=detail]').serialize(), function (json) {
                            if (json != null) {
                                if (json.status.code == 0) {
                                    var provider = json.provider;
                                    $("form[name=detail] .tname").html(provider.name);
                                    $("form[name=detail] .tcode").html(provider.providerCode);
                                    $("form[name=detail] .trating").html(provider.relationship);
                                    if (provider.active) {
                                        $("form[name=detail] .healthy button." + provider.providerHealthy.state).prop('disabled', false);
                                    }
                                    var tbody = $('form[name=detail] table#tb-detail tbody');
                                    tbody.html('');
                                    var i = 1;
                                    var list = provider.services;
                                    console.log("list", list);
                                    for (var item in list) {
                                        var cHealthy = provider.providerHealthy.details[list[item].serviceCode];
                                        var bad = 'disabled';
                                        if (provider.active && cHealthy == 'RED') bad = '';
                                        var warning = 'disabled';
                                        if (provider.active && cHealthy == 'YELLOW') warning = '';
                                        var good = 'disabled';
                                        if (provider.active && cHealthy == 'GREEN') good = '';
                                        var blackListCif = list[item].blackListCif ? list[item].blackListCif : "";
                                        var customerTypeSupported = list[item].customerTypeSupported ? list[item].customerTypeSupported : "";
                                        var tr = '<tr>'
                                            + '<td>' + i + '</td>'
                                            + '<td >' + list[item].serviceType + '</td>'
                                            + '<td >' + list[item].serviceCode + '</td>'
                                            + '<td >' + list[item].serviceName + '</td>'
                                            //                        + '<td>' + list[item].serviceDesc + '</td>'
                                            + '<td >'
                                            + '<div class="switch switch-sm switch-success">'
                                            + '<input type="checkbox" name="switch" ' + (list[item].active ? 'checked' : '') + ' value="' + list[item].id + '" id="ck_' + i
                                            + '" data-switchery="true" style="display: none;">' +
                                            (list[item].active
                                                ? '<span class="switchery switchery-small" style="background-color: rgb(100, 189, 99); border-color: rgb(100, 189, 99); box-shadow: rgb(100, 189, 99) 0px 0px 0px 11px inset; transition: border 0.4s, box-shadow 0.4s, background-color 1.2s;"><small style="left: 13px; background-color: rgb(255, 255, 255); transition: background-color 0.4s, left 0.2s;"></small></span>'
                                                : '<span class="switchery switchery-small" style="box-shadow: rgb(223, 223, 223) 0px 0px 0px 0px inset; border-color: rgb(223, 223, 223); background-color: rgb(255, 255, 255); transition: border 0.4s, box-shadow 0.4s;"><small style="left: 0px; transition: background-color 0.4s, left 0.2s;"></small></span>')
                                            + '<a class="provider-switchery switchery-mask" onclick="changeStatusProviderService(' + i + ')" id="proSevice_ck_' + i + '" providerCode="' + list[item].provider.providerCode + '" providerServiceId="' + list[item].id
                                            + '" serviceCode="' + list[item].serviceCode + '" active="' + list[item].active + '"></a>'
                                            + '</div>'
                                            + '</td>'
                                            + '<td style="white-space: nowrap">'
                                            //                        + '<div class="btn-group" style="width: 150px;">'
                                            + '<div class="" style="width: 150px;">'
                                            + '	<button type="button" title="<spring:message code="common.title.dangerous"/>" class="mb-xs mt-xs btn btn-xs btn btn-danger" '
                                            + bad + ' style="margin-right: 2px;">bad</button>'
                                            + '	<button type="button" title="<spring:message code="common.title.warning"/>" class="mb-xs mt-xs btn btn-xs btn btn-warning" '
                                            + warning
                                            + ' style="margin-right: 2px;">warning</button>'
                                            + '	<button type="button" title="<spring:message code="common.title.stable.operation"/>" class="mb-xs mt-xs btn btn-xs btn btn-success-green" '
                                            + good + ' style="margin-right: 2px;">good</button>'
                                            + '</div>'
                                            + '</td>'
                                            + '<td >' + customerTypeSupported + '</td>'
                                            + '<td >' + blackListCif + '</td>'
                                            + '</tr>';
                                        tbody.append(tr);
                                        i++;
                                    }
                                } else {
                                    $.MessageBox({message: json.status.value});
                                }
                            } else {
                                $.MessageBox({message: '<spring:message code="message.erorr.please.try.again"/> '});
                            }
                        });
                    }

                    $(".detail-link").click(function () {
                        resetAllvalue();
                        $('form[name=detail] input[name=providerCode]').val($(this).attr("providerCode"));
                        contentPopupProviderService()
                    });

                    function resetAllvalue() {
                        $("form[name=detail] .tname").html("");
                        $("form[name=detail] .tcode").html("");
                        $("form[name=detail] .trating").html("");
                        $('form[name=detail] table#tb-detail tbody').html('');
                        $("form[name=detail] .healthy button.RED").prop('disabled', true);
                        $("form[name=detail] .healthy button.YELLOW").prop('disabled', true);
                        $("form[name=detail] .healthy button.GREEN").prop('disabled', true);
                    }

                    $('#detail').on('hidden.bs.modal', function () {
                        // do something…
                        var queryString = window.top.location.search.substring(1);
                        var searchURL = '';
                        var begin = queryString.indexOf("pProService=");
                        if (begin != -1) {
                            searchURL = setQueryParameter(queryString, "pProService", "off");
                            window.location.href = ctx + '<%=ProviderController.PROVIDER_LIST%>?' + searchURL;
                        }
                    })
                });

                function changeStatusProviderService(id) {
                    var providerCode = $('#proSevice_ck_' + id).attr('providerCode');
                    var providerServiceId = $('#proSevice_ck_' + id).attr('providerServiceId');
                    var serviceCode = $('#proSevice_ck_' + id).attr('serviceCode');
                    var active = $('#proSevice_ck_' + id).attr('active');
                    var isTrueSet = (active === 'true');
                    var textAlert = "ON";
                    if (isTrueSet) textAlert = "OFF";
                    $.MessageBox({
                        buttonDone: "Yes",
                        buttonFail: "No",
                        message: "Do you want <b>" + textAlert + "</b></br>Provider Service: " + providerCode + " Service Code: " + serviceCode
                    }).done(function () {
                        $.post('changeProviderServiceStatus', {providerServiceId: providerServiceId, active: active},
                            function (json) {
                                $.MessageBox({message: json.message});
                                if (json.code == 0) {
                                    var searchURL = '';
                                    if (window.location.search.indexOf("?") >= 0) {
                                        var queryString = window.top.location.search.substring(1);
                                        var begin = queryString.indexOf("providerCode=");
                                        if (begin != -1) {
                                            searchURL = setQueryParameter(queryString, "providerCode", providerCode)
                                        } else {
                                            searchURL = queryString + '&providerCode=' + providerCode;
                                        }
                                        begin = queryString.indexOf("pProService=");
                                        if (begin != -1) {
                                            searchURL = setQueryParameter(searchURL, "pProService", "on")
                                        } else {
                                            searchURL = searchURL + '&pProService=on';
                                        }
                                        searchURL = ctx + '<%=ProviderController.PROVIDER_LIST%>?' + searchURL;
                                    } else {
                                        searchURL = ctx + '<%=ProviderController.PROVIDER_LIST%>?' + 'providerCode=' + providerCode + '&pProService=on';
                                    }
                                    window.location.href = searchURL;
                                }
                                setTimeout(function () {
                                    $(".btnStatus").button('reset');
                                }, 1000);
                            });
                    });
                    return false;
                }

                function setQueryParameter(queryString, parameterName, value) {
                    var parameterName = parameterName + "=";
                    if (queryString.length > 0) {
                        var begin = queryString.indexOf(parameterName);
                        if (begin != -1) {
                            begin += parameterName.length;
                            var end = queryString.indexOf("&", begin);
                            if (end == -1) {
                                end = queryString.length
                            }
                            return queryString.replaceBetween(begin, end, value);
                        }
                    }
                }
            </script>
        </div>
    </div>
</div>