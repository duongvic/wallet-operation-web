
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel-title">
  <h4 style="margin-bottom: 1.5rem">Additional Information</h4>
  <div class="panel-tools hidden"></div>
</div>

<div class="panel-body">
  <div class="row">

    <div class="form-group">
      <div class="col-sm-12 col-md-6">
        <label class="col-sm-4 col-md-5 col-lg-4 control-label nowrap">Email :</label>
        <div class="col-sm-8 col-md-7">${customer.email}</div>
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-12 col-md-6">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">Current Address :</label>
        <div class="col-md-8 col-lg-8  col-sm-8 double-input">${customer.livingAddress}</div>
      </div>
      <div class="col-sm-12 col-md-6">
        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">District :</label>
        <div class="col-md-7 col-lg-8 col-sm-8">${customer_district}</div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="form-group">
      <div class="col-sm-12 col-md-6">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">City :</label>
        <div class="col-md-8 col-lg-8  col-sm-8 double-input">${customer_region}</div>
      </div>
      <div class="col-sm-12 col-md-6">
        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">Nationaly :</label>
        <div class="col-md-7 col-lg-8 col-sm-8">Việt Nam</div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="form-group">
      <div class="col-sm-12 col-md-6">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">Occupation :</label>
        <div class="col-md-8 col-lg-8  col-sm-8 double-input">${customer_jobOccupation}</div>
      </div>
      <div class="col-sm-12 col-md-6">
        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">Position :</label>
        <div class="col-md-7 col-lg-8 col-sm-8">${customer_jobPosition}</div>
      </div>
    </div>
  </div>

</div>