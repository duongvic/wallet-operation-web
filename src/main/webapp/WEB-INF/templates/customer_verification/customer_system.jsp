<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ewallet" uri="https://processing.function.template/service/jsp/jstl/functions" %>

<div class="panel-title">
  <h4 style="margin-bottom: 1.5rem">System Information</h4>
  <div class="panel-tools hidden"></div>
</div>

<div class="panel-body">
  <div class="row">
    <div class="form-group">
      <div class="col-sm-12 col-md-6">
        <label class="col-sm-4 control-label nowrap">ID :</label>
        <div class="col-sm-8">${customer.id}</div>
      </div>
      <div class="col-sm-12 col-md-6">
        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">Account Status :</label>
        <div class="col-sm-8 col-md-7 col-lg-8">${customer.active ? "Hoạt động" : "Không hoạt động"}</div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="form-group">
      <div class="col-sm-12 col-md-6">
        <label class="col-sm-4 control-label nowrap">Created Date :</label>
        <div class="col-md-8 col-lg-8  col-sm-8 ">${customer.textCreatedDate()}</div>
      </div>
      <div class="col-sm-12 col-md-6">
        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">Update Date :</label>
        <div class="col-sm-8 col-md-7 col-lg-8">${customer.textUpdatedDate()}</div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="form-group">
      <div class="col-sm-12 col-md-6">
        <label class="col-sm-4 control-label nowrap">Kyc Status :</label>
        <div class="col-md-8 col-lg-8  col-sm-8 ">${customerVerifyKyc.textKycStatus()}</div>
      </div>
      <div class="col-sm-12 col-md-6">
        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">Kyc Update Date :</label>
        <div class="col-sm-8 col-md-7 col-lg-8">${customerVerifyKyc.textUpdatedDate()}</div>
      </div>
    </div>
  </div>


  <%--<div class="row">
    <div class="form-group">
      <div class="col-sm-12 col-md-6">
        <label class="col-sm-4 control-label nowrap">Wallet balance :</label>
        <div class="col-md-8 col-lg-8  col-sm-8 ">${ewallet:formatNumber(customer.balance)} VND</div>
      </div>
    </div>
  </div>--%>

</div>