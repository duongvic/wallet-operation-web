<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 6/4/2021
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../../../include_page/taglibs.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        var tab = "${tab}";
        switch (tab) {
            case "EPIN": changeTab("EPIN"); break;
            case "EXPORT_EPIN": changeTab("EXPORT_EPIN"); break;
            case "PHONE_TOPUP": changeTab("PHONE_TOPUP"); break;
            case "BILL_PAYMENT": changeTab("BILL_PAYMENT"); break;
            default: changeTab("EPIN");
        }
    });

    $(document).ajaxStart(function () {
        $(".btn").button('loading');
    });

    $(document).ajaxComplete(function () {
        $(".btn").button('reset');
    });

    var tab = "";
    function changeTab(service) {
        var serviceTypes = ['EPIN', 'EXPORT_EPIN', 'PHONE_TOPUP', 'BILL_PAYMENT', 'OTHER'];
        serviceTypes.forEach(serviceType => {
            $("#" + serviceType).removeClass("active");
            $("#" + serviceType + "tbl").addClass("hidden");
        });

        $("#" + service).addClass("active");
        $("#" + service + "tbl").removeClass("hidden");
        tab = service;
    }

    function getTextNumber(val) {
        var text = val + "";
        var number = text.replace(/,/g, '');
        number = number.replace(/(\d)(?=(\d{3})+\b)/g, '$1,');
        return number;
    }

    function openAddModal(serviceType) {
        $('#add-form')[0].reset();
        $('#serviceCodeCreate').val(null).trigger('change');
        $("#add-modal").modal('show');

        var inputs = document.getElementById("add-form").elements;
        inputs["serviceType"].value = serviceType;
        inputs["serviceType2"].value = serviceType;
    }

    function openAddRevertModal() {
        $('#add-revert-form')[0].reset();
        $("#add-revert-modal").modal('show');
    }

    function openUpdateRevertModal(id) {
        $('#update-revert-form')[0].reset();

        var reconcilJSON = JSON.parse('${reconcilJSON}');
        var element = reconcilJSON.reconciliation_revert_elements.find(element => element.id === id);

        var inputs = document.getElementById("update-revert-form").elements;
        inputs["id"].value = element.id;
        inputs["serviceType"].value = element.service_type;
        inputs["revertAmount"].value = getTextNumber(element.revert_amount);

        $("#update-revert-modal").modal('show');
    }

    function openUpdateModal(id, serviceType) {
        $('#update-form')[0].reset();

        var reconcilJSON = JSON.parse('${reconcilJSON}');
        var element = null;
        for (const [key, value] of Object.entries(reconcilJSON.element_group_by_service_type)) {
            if (key == serviceType) {
                value.forEach(e => {
                    if (e.id == id) {
                        element = e;
                        return;
                    }
                });
            }
        }

        var inputs = document.getElementById("update-form").elements;
        inputs["id"].value = element.id;
        inputs["customerType"].value = element.customer_type;
        inputs["customerType2"].value = element.customer_type;
        inputs["customerId"].value = element.customer_id;
        inputs["customerId2"].value = element.customer_id;
        inputs["serviceType"].value = element.service_type;
        inputs["serviceType2"].value = element.service_type;
        //inputs["serviceId"].value = element.service_id;
        inputs["serviceCode"].value = element.service_code;
        inputs["serviceName"].value = element.service_short_name;
        inputs["quantity"].value = getTextNumber(element.quantity);
        inputs["unitPrice"].value = getTextNumber(element.unit_price);
        inputs["unitFeeFixedAmount"].value = getTextNumber(element.unit_fee_fixed_amount);
        inputs["unitFeePercentRate"].value = getTextNumber(element.unit_fee_percent_rate);
        inputs["unitCommissionFixedAmount"].value = getTextNumber(element.unit_commission_fixed_amount);
        inputs["unitCommissionPercentRate"].value = getTextNumber(element.unit_commission_percent_rate);
        inputs["numOfTrans"].value = getTextNumber(element.num_of_trans);
        inputs["sumOfRequestAmount"].value = getTextNumber(element.sum_of_request_amount);
        inputs["sumOfFeeAmount"].value = getTextNumber(element.sum_of_fee_amount);
        inputs["sumOfCommissionAmount"].value = getTextNumber(element.sum_of_commission_amount);
        inputs["sumOfCashbackAmount"].value = getTextNumber(element.sum_of_cashback_amount);

        $("#update-modal").modal('show');
    }

    function addOneElement() {
        var myForm = document.getElementById('add-form');
        var formData = new FormData(myForm);

        if (formData.get('unitFeePercentRate') > 100 || formData.get('unitCommissionPercentRate') > 100) {
            $.MessageBox({message: "UnitFeePercentRate hoặc UnitCommissionPercentRate phải nhỏ hơn 100!"});
            return;
        }

        for (var [key, value] of formData.entries()) {
            if (key == 'quantity'
                || key == 'unitPrice'
                || key == 'unitFeeFixedAmount'
                || key == 'unitCommissionFixedAmount'
                || key == 'numOfTrans'
                || key == 'sumOfRequestAmount'
                || key == 'sumOfFeeAmount'
                || key == 'sumOfCommissionAmount'
                || key == 'sumOfCashbackAmount') {
                value = value.replaceAll(",", '');
                formData.set(key, value);
            }
        }

        $.ajax({
            type: 'POST',
            url: ctx + "/ajax-reconciliation-controller/create-element/${reconcil.id}",
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                $.MessageBox("Success!").done(function () {
                    location.replace("${contextPath}/wallet/reconcilation/edit/${reconcil.id}?tab="+tab);
                });
            },
            error: function (data) {
            }
        });
    }

    function addOneRevertElement() {
        var myForm = document.getElementById('add-revert-form');
        var formData = new FormData(myForm);

        for (var [key, value] of formData.entries()) {
            if (key == 'revertAmount') {
                value = value.replaceAll(",", '');
                formData.set(key, value);
            }
        }

        $.ajax({
            type: 'POST',
            url: ctx + "/ajax-reconciliation-controller/create-revert-element/${reconcil.id}",
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                $.MessageBox("Success!").done(function () {
                    location.replace("${contextPath}/wallet/reconcilation/edit/${reconcil.id}?tab="+tab);
                });
            },
            error: function (data) {
            }
        });
    }

    function updateOneElement() {
        var myForm = document.getElementById('update-form');
        var formData = new FormData(myForm);

        if (formData.get('unitFeePercentRate') > 100 || formData.get('unitCommissionPercentRate') > 100) {
            $.MessageBox({message: "UnitFeePercentRate hoặc UnitCommissionPercentRate phải nhỏ hơn 100!"});
            return;
        }

        for (var [key, value] of formData.entries()) {
            if (key == 'quantity'
                || key == 'unitPrice'
                || key == 'unitFeeFixedAmount'
                || key == 'unitCommissionFixedAmount'
                || key == 'numOfTrans'
                || key == 'sumOfRequestAmount'
                || key == 'sumOfFeeAmount'
                || key == 'sumOfCommissionAmount'
                || key == 'sumOfCashbackAmount') {
                value = value.replaceAll(",", '');
                formData.set(key, value);
            }
        }

        $.ajax({
            type: 'POST',
            url: ctx + "/ajax-reconciliation-controller/update-element/${reconcil.id}",
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                $.MessageBox("Success!").done(function () {
                    location.replace("${contextPath}/wallet/reconcilation/edit/${reconcil.id}?tab="+tab);
                });
            },
            error: function (data) {
            }
        });
    }

    function updateOneRevertElement() {
        var myForm = document.getElementById('update-revert-form');
        var formData = new FormData(myForm);

        for (var [key, value] of formData.entries()) {
            if (key == 'revertAmount') {
                value = value.replaceAll(",", '');
                formData.set(key, value);
            }
        }

        $.ajax({
            type: 'POST',
            url: ctx + "/ajax-reconciliation-controller/update-revert-element/${reconcil.id}",
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                $.MessageBox("Success!").done(function () {
                    location.replace("${contextPath}/wallet/reconcilation/edit/${reconcil.id}?tab="+tab);
                });
            },
            error: function (data) {
            }
        });
    }

    function updateReconcilation() {
        $.ajax({
            url: ctx + '/ajax-reconciliation-controller/update/${reconcil.id}',
            method: 'POST',
            data: {
                description: $('#description').val(),
                title: $('#title').val(),
                cycle: $('#cycle').val(),
                year: $('#year').val(),
                month: $('#month').val(),
                bolOfficial: $('#bolOfficial').val(),
            },
            success: function (data) {
                $.MessageBox({message: 'SUCCESS!'}).done(function () {
                    location.replace("${contextPath}/wallet/reconcilation/edit/${reconcil.id}?tab="+tab);
                });
            },
            error: function (data) {
                $.MessageBox({message: 'FAILED!'});
            }

        });
    }

    function deleteReconcilationElement(elementId) {
        $.MessageBox({
            buttonDone: '<spring:message code="popup.button.yes"/>',
            buttonFail: '<spring:message code="popup.button.no"/>',
            message: 'Bạn có chắc chắn muốn xóa?'
        }).done(function () {
            $.ajax({
                url: ctx + '/ajax-reconciliation-controller/delete-element/${reconcil.id}',
                method: 'POST',
                data: {
                    elementId: elementId,
                },
                success: function (data) {
                    $.MessageBox({message: 'SUCCESS!'}).done(function () {
                        location.replace("${contextPath}/wallet/reconcilation/edit/${reconcil.id}?tab="+tab);
                    });
                },
                error: function (data) {
                    $.MessageBox({message: 'FAILED!'});
                }
            });
        });
    }

    function deleteReconcilationRevertElement(elementId) {
        $.MessageBox({
            buttonDone: '<spring:message code="popup.button.yes"/>',
            buttonFail: '<spring:message code="popup.button.no"/>',
            message: 'Bạn có chắc chắn muốn xóa?'
        }).done(function () {
            $.ajax({
                url: ctx + '/ajax-reconciliation-controller/delete-revert-element/${reconcil.id}',
                method: 'POST',
                data: {
                    elementId: elementId,
                },
                success: function (data) {
                    $.MessageBox({message: 'SUCCESS!'}).done(function () {
                        location.replace("${contextPath}/wallet/reconcilation/edit/${reconcil.id}?tab="+tab);
                    });
                },
                error: function (data) {
                    $.MessageBox({message: 'FAILED!'});
                }
            });
        });
    }

    $('.find-service-code').select2({
        width: "100%",
        dropdownAutoWidth: true,
        ajax: {
            url: ctx + "/ajax-controller/search/true-service",
            dataType: 'json',
            type: "POST",
            data: function (params) {
                var query = {
                    search: params.term,
                    type: 'public',
                }

                // Query parameters will be ?search=[term]&type=public
                return query;
            },
            // Additional AJAX parameters go here; see the end of this chapter for the full code of this example
            processResults: function (data, params) {
                // Transforms the top-level key of the response object from 'items' to 'results'
                var retVal = [];
                var sers = data.services;
                for (var i = 0; i < sers.length; i++) {
                    var lineObj = {
                        id: sers[i].serviceCode,
                        text: sers[i].serviceCode + ' - ' + sers[i].serviceName,
                    };
                    retVal.push(lineObj);
                }

                return {
                    // results: data.items
                    results: retVal,
                };
            }
        },
        placeholder: 'nhập mã hoặc tên dịch vụ',
        minimumInputLength: 3,
        language: {
            inputTooShort: function () {
                return '<spring:message code="label.search.max.length"/>';
            }
        }
    });

    $('#serviceCodeCreate').on('select2:select', function (e) {
        var serviceCode = e?.params?.data?.id;

        $.ajax({
            url: ctx + '/ajax-controller/pricing/search-by-code',
            method: 'POST',
            data: {
                serviceCode: serviceCode,
            },
            success: function (data) {
                var inputs = document.getElementById("add-form").elements;
                inputs["unitPrice"].value = getTextNumber(data.unit_price);
                inputs["serviceName"].value = getTextNumber(data.service_name);
                inputs["unitFeeFixedAmount"].value = getTextNumber(data.unit_fee_fixed_amount);
                inputs["unitFeePercentRate"].value = getTextNumber(data.unit_fee_percent_rate);
                inputs["unitCommissionFixedAmount"].value = getTextNumber(data.unit_commission_fixed_amount);
                inputs["unitCommissionPercentRate"].value = getTextNumber(data.unit_commission_percent_rate);
            },
            error: function (data) {
                $.MessageBox({message: 'Tự động điền dữ liệu thất bại! vui lòng điền thủ công'});
            }
        });
    });
</script>
