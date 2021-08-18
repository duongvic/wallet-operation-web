/*Change Status*/
function changeBlackList() {
  var accountId = jQuery('#account-id').val();
  var blacklistIdOld = jQuery('#blacklist-id').val();
  var valueBlackList = blacklistIdOld;

  //validate checkbox
  if (jQuery('#chk-customer-confirm-senpay').is(':checked') == false) {
    alertMsg('Please check Term of use!', 1, 'msg-blacklist');
    return;
  }

  //change blackList reason
  actionChangeBlackListReason(valueBlackList, accountId);

}

function actionChangeBlackListReason(valueBlackList, accountId) {

  var urlChangeBlackList = ctx + '/ajax-controller/sendPay/changeStatus';
  $.ajax({
    type: 'POST',
    url: urlChangeBlackList,
    data: {
      active: valueBlackList,
      accountId: accountId,
    },
    success: function (response) {
      if (response == null || response == "") {
        alertMsg('API BackEnd change status failed!', 1, 'msg-blacklist');
      } else {
        if (response.status.code ==0) {
          //success
          alertMsg('Change Status success!', 0, 'msg-blacklist');
          jQuery('#wrapper-chk-customer-confirm-senpay').hide();
          jQuery('#btn-account-blacklist-save').attr('disabled', true);
          //Update Input Blacklist Reason;
          jQuery('#user-' + accountId + '-blackListReason').val(valueBlackList);

          if (valueBlackList == "0") {
            jQuery('#chk-blacklist-status-' + accountId).removeClass(
                'text-danger');
            jQuery('#chk-blacklist-status-' + accountId).addClass(
                'text-success');
            jQuery('#chk-blacklist-status-' + accountId).prop('checked', true);
          } else {
            jQuery('#chk-blacklist-status-' + accountId).removeClass(
                'text-success');
            jQuery('#chk-blacklist-status-' + accountId).addClass(
                'text-danger');
            jQuery('#chk-blacklist-status-' + accountId).prop('checked', false);
          }
          location.reload();
        } else {
          alertMsg(response.status.value, 1, 'msg-blacklist');
        }
      }
    }, error: function (e) {
      alertMsg('Blacklist change failed!', 1, 'msg-blacklist');
    }
  });
}

/*Change status bill senpay tool*/
function changeBlackListBillSenpayTool() {
    var accountId = jQuery('#account-id').val();
    var blacklistIdOld = jQuery('#blacklist-id').val();
    var valueBlackList = blacklistIdOld;

    //validate checkbox
    if (jQuery('#chk-customer-confirm-senpay').is(':checked') == false) {
        alertMsg('Please check Term of use!', 1, 'msg-blacklist');
        return;
    }

    //change blackList reason
    actionChangeBlackListReasonBillSenpayTool(valueBlackList, accountId);

}

function actionChangeBlackListReasonBillSenpayTool(valueBlackList, accountId) {

    var urlChangeBlackList = ctx + '/ajax-controller/sendPay-tool/changeStatus';
    $.ajax({
        type: 'POST',
        url: urlChangeBlackList,
        data: {
            active: valueBlackList,
            accountId: accountId,
        },
        success: function (response) {
            if (response == null || response == "") {
                alertMsg('API BackEnd change status failed!', 1, 'msg-blacklist');
            } else {
                if (response.status.code ==0) {
                    //success
                    alertMsg('Change Status success!', 0, 'msg-blacklist');
                    jQuery('#wrapper-chk-customer-confirm-senpay').hide();
                    jQuery('#btn-account-blacklist-save').attr('disabled', true);
                    //Update Input Blacklist Reason;
                    jQuery('#user-' + accountId + '-blackListReason').val(valueBlackList);

                    if (valueBlackList == "0") {
                        jQuery('#chk-blacklist-status-' + accountId).removeClass(
                            'text-danger');
                        jQuery('#chk-blacklist-status-' + accountId).addClass(
                            'text-success');
                        jQuery('#chk-blacklist-status-' + accountId).prop('checked', true);
                    } else {
                        jQuery('#chk-blacklist-status-' + accountId).removeClass(
                            'text-success');
                        jQuery('#chk-blacklist-status-' + accountId).addClass(
                            'text-danger');
                        jQuery('#chk-blacklist-status-' + accountId).prop('checked', false);
                    }
                    location.reload();
                } else {
                    alertMsg(response.status.value, 1, 'msg-blacklist');
                }
            }
        }, error: function (e) {
            alertMsg('Blacklist change failed!', 1, 'msg-blacklist');
        }
    });
}

/*Delete*/
function deleteBlackList() {
  var accountId = jQuery('#account-id-sendPay').val();

  //validate checkbox
  if (jQuery('#chk-customer-confirm-senpay-delete').is(':checked') == false) {
    alertMsg('Please check Term of use!', 1 ,'msg-delete-account-senPay');
    return;
  }

  //change blackList reason
  actionDeleteBlackListReason(accountId);

}

function actionDeleteBlackListReason(accountId) {

  var urlDeleteBlackList = ctx + '/ajax-controller/sendPay/delete';
  $.ajax({
    type: 'POST',
    url: urlDeleteBlackList,
    data: {
      accountId: accountId,
    },
    success: function (response) {
      if (response == null || response == "") {
        alertMsg('API BackEnd delete account provider special failed!', 1, 'msg-delete-account-senPay');
      } else {
        if (response.status.code ==0) {
          //success
          alertMsg('Success!', 0, 'msg-delete-account-senPay');
          jQuery('#wrapper-chk-customer-confirm-senpay-delete').hide();
          jQuery('#btn-account-blacklist-save').attr('disabled', true);
          //Update Input Blacklist Reason;
          location.reload();
        } else {
          alertMsg(response.status.value, 1, 'msg-delete-account-senPay');
        }
      }
    }, error: function (e) {
      alertMsg('Delete account provider special failed!', 1, 'msg-delete-account-senPay');
    }
  });
}

/*Senpay tool*/
function deleteBlackListSenpayTool() {
    var accountId = jQuery('#account-id-sendPay').val();

    //validate checkbox
    if (jQuery('#chk-customer-confirm-senpay-delete').is(':checked') == false) {
        alertMsg('Please check Term of use!', 1 ,'msg-delete-account-senPay');
        return;
    }

    //change blackList reason
    actionDeleteBlackListReasonSenpayTool(accountId);

}

function actionDeleteBlackListReasonSenpayTool(accountId) {

    var urlDeleteBlackList = ctx + '/ajax-controller/sendPay-tool/delete';
    $.ajax({
        type: 'POST',
        url: urlDeleteBlackList,
        data: {
            accountId: accountId,
        },
        success: function (response) {
            if (response == null || response == "") {
                alertMsg('API BackEnd delete account provider special failed!', 1, 'msg-delete-account-senPay');
            } else {
                if (response.status.code ==0) {
                    //success
                    alertMsg('Success!', 0, 'msg-delete-account-senPay');
                    jQuery('#wrapper-chk-customer-confirm-senpay-delete').hide();
                    jQuery('#btn-account-blacklist-save').attr('disabled', true);
                    //Update Input Blacklist Reason;
                    location.reload();
                } else {
                    alertMsg(response.status.value, 1, 'msg-delete-account-senPay');
                }
            }
        }, error: function (e) {
            alertMsg('Delete account provider special failed!', 1, 'msg-delete-account-senPay');
        }
    });
}


function alertMsg(msg, isSuccess, idMsg) {
  var htmlMsg = '';
  if (isSuccess == 1) {
    htmlMsg += '<i class="fa fa-info-circle text-danger fa-lg" aria-hidden="true"></i> ';
  } else {
    htmlMsg += '<i class="fa fa-check text-success fa-lg" aria-hidden="true"></i> ';
  }
  htmlMsg += msg;
  if (isSuccess == 1) {
    jQuery('#' + idMsg).html(
        '<div class="form-group warp-msg"><div class="alert alert-danger">'
        + htmlMsg
        + '</div></div>'
    );
  } else {
    jQuery('#' + idMsg).html(
        '<div class="form-group warp-msg"><div class="alert alert-success">'
        + htmlMsg
        + '</div></div>'
    );
  }

}