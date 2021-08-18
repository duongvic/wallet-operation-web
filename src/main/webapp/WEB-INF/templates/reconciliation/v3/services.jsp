<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 5/31/2021
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../../include_page/taglibs.jsp" %>
<script type="text/javascript">

    <%--var data = [];--%>
    <%--<c:forEach var="customer" items="${customers}">--%>
    <%--data.push({id: ${customer.id}, text: '${customer.fullName}'});--%>
    <%--</c:forEach>--%>
    <%--for (var i = 0; i < data.length; i++) {--%>
    <%--var $option = $("<option selected></option>").val(data[i].id).text(data[i].text);--%>
    <%--$('.find-customer').append($option);--%>
    <%--}--%>

    $(document).ready(function () {

        $('.find-customer').select2({
            width: "100%",
            dropdownAutoWidth: true,
            placeholder: ' <spring:message code="label.search.customer"/>',
        });
        <%--$('.find-customer').select2({--%>
        <%--width: "100%",--%>
        <%--dropdownAutoWidth: true,--%>
        <%--ajax: {--%>
        <%--url: ctx + "/ajax-controller/v2/find-customers",--%>
        <%--dataType: 'json',--%>
        <%--type: "POST",--%>
        <%--data: function (params) {--%>
        <%--var query = {--%>
        <%--search: params.term,--%>
        <%--customerType: 3,--%>
        <%--type: 'public',--%>
        <%--page: params.page || 0--%>
        <%--}--%>

        <%--// Query parameters will be ?search=[term]&type=public--%>
        <%--return query;--%>
        <%--},--%>
        <%--// Additional AJAX parameters go here; see the end of this chapter for the full code of this example--%>
        <%--processResults: function (data, params) {--%>
        <%--params.page = params.page || 0;--%>
        <%--// Transforms the top-level key of the response object from 'items' to 'results'--%>
        <%--var retVal = [];--%>
        <%--var cus = data.customers;--%>
        <%--for (var i = 0; i < cus.length; i++) {--%>
        <%--var lineObj = {--%>
        <%--id: cus[i].id,--%>
        <%--text: cus[i].fullName--%>
        <%--};--%>
        <%--retVal.push(lineObj);--%>
        <%--}--%>

        <%--return {--%>
        <%--// results: data.items--%>
        <%--results: retVal,--%>
        <%--pagination: {--%>
        <%--more: (params.page * 20) < data.total--%>
        <%--}--%>
        <%--};--%>
        <%--}--%>
        <%--},--%>
        <%--placeholder: ' <spring:message code="label.search.customer"/> bằng SĐT',--%>
        <%--&lt;%&ndash;minimumInputLength: 3,&ndash;%&gt;--%>
        <%--&lt;%&ndash;language: {&ndash;%&gt;--%>
        <%--&lt;%&ndash;inputTooShort: function () {&ndash;%&gt;--%>
        <%--&lt;%&ndash;return '<spring:message code="label.search.max.length"/>';&ndash;%&gt;--%>
        <%--&lt;%&ndash;}&ndash;%&gt;--%>
        <%--&lt;%&ndash;}&ndash;%&gt;--%>
        <%--});--%>

        ////////////////////////////////////////////////
        ////////////////////////////////////////////////
        ////////////////////////////////////////////////

        $('select[name="stages"]').select2({
            placeholder: " Trạng thái"
        });

        $('select[name="year"]').select2({
            placeholder: " Năm",
        });

        $('select[name="month"]').select2({
            placeholder: " Tháng",
        });

        $('select[name="cycle"]').select2({
            placeholder: " Kỳ",
        });

        $('select[name="cycleType"]').select2({
            placeholder: " Kỳ",
        });

    });

    function openCreateByDate() {
        $("#create-by-date-modal").modal('show');
        $("#create-by-date-form")[0].reset();
        $('#merchantId').val(null).trigger('change');
    }

    function openWorkflowModal(id, stage) {
        if (stage === 'OPERATION_MANAGER_REJECTED' || stage === 'MERCHANT_REJECTED' || stage === 'FINANCE_MANAGER_REJECTED') {
            $("#reject-btn").addClass("hidden");
            $("#approve-btn").addClass("hidden");
            $("#submit-btn").removeClass("hidden");
        } else {
            $("#reject-btn").removeClass("hidden");
            $("#approve-btn").removeClass("hidden");
            $("#submit-btn").addClass("hidden");
        }
        $('#workflow-form')[0].reset();
        $("#reconcilId").val(id);

        var reconcilsJSON = JSON.parse('${reconciliationsJSON}');

        const reconcil = reconcilsJSON.find(element => element.id === id);

        var policy = '';
        switch (reconcil?.payment_policy) {
            case 'PAYMENT_POLICY_1_1':
                policy = "Chính sách 1 + 1";
                break;
            case 'PAYMENT_POLICY_7_1':
                policy = "Chính sách 7 + 1";
                break;
            case 'PAYMENT_POLICY_30_1':
                policy = "Chính sách 30 + 1";
                break;
            default:
                policy = "";
        }

        var stage = '';
        switch (reconcil?.stage) {
            case 'INIT':
                stage = "Đã khai báo";
                break;
            case 'OPERATION_MANAGER_REJECTED':
                stage = "QL Từ chối";
                break;
            case 'OPERATION_STAFF_SUBMITED':
                stage = "Chờ NV Gửi yêu cầu";
                break;
            case 'MERCHANT_REJECTED':
                stage = "Merchant từ chối";
                break;
            case 'OPERATION_MANAGER_APPROVED':
                stage = "Chờ QL Phê duyệt";
                break;
            case 'FINANCE_MANAGER_REJECTED':
                stage = "TP TCKT Từ chối";
                break;
            case 'MERCHANT_CONFIRMED':
                stage = "Chờ Merchant Xác nhận";
                break;
            case 'FINISHED':
                stage = "Kết thúc";
                break;
            default:
                stage = "";
        }

        $("#merchant-name").html(reconcil?.customer_name);
        $("#reconcil-title").html(reconcil?.title);
        $("#reconcil-policy").html(policy);
        $("#reconcil-stage").html(stage);


        $("#workflow-modal").modal('show');
    }

    function changeReconciliationStage(action) {

        if ($('#confirm-chkbox').is(':checked')) {
        } else {
            $.MessageBox({message: "<spring:message code="common.not.confirm.the.action"/>"});
            return false;
        }

        var id = $("#reconcilId").val();
        var remark = $("#remark").val();

        $.ajax({
            url: ctx + '/ajax-controller/reconciliation/workflow/action',
            method: 'POST',
            data: {
                id: id,
                remark: remark,
                action: action,
            },
            success: function (data) {
                $.MessageBox({message: 'SUCCESS!'}).done(function () {
                    location.reload();
                });
            },
            error: function (data) {
                $.MessageBox({message: 'FAILED!'});
            }

        });
    }

    function deleteReconcilation(id) {
        $.MessageBox({
            buttonDone: '<spring:message code="popup.button.yes"/>',
            buttonFail: '<spring:message code="popup.button.no"/>',
            message: 'Bạn có chắc chắn muốn xóa?'
        }).done(function () {
            $.ajax({
                url: ctx + '/ajax-reconciliation-controller/delete/' + id,
                method: 'GET',
                success: function (data) {
                    $.MessageBox({message: 'SUCCESS!'}).done(function () {
                        location.reload();
                    });
                },
                error: function (data) {
                    $.MessageBox({message: 'FAILED!'});
                }
            });
        });
    }

    function generateReconciliationByOPS() {
        if(!$("#merchantId").val()) {
            $.MessageBox({message: "Yêu cầu chọn khách hàng!"});
            return;
        }
        $.ajax({
            url: ctx + '/ajax-reconciliation-controller/generate-reconciliation/by-ops',
            method: 'POST',
            data: {
                merchantId: $("#merchantId").val(),
                cycleType: $("#cycleType").val(),
                yr: $("#yr").val(),
                mnth: $("#mnth").val(),
                cyl: $("#cyl").val(),
            },
            success: function (data) {
                $.MessageBox({message: 'Thành công!'}).done(function () {
                    location.reload();
                });
            },
            error: function (data) {
                $.MessageBox({message: 'Thất bại!'});
            }
        });
    }
</script>
