<%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 5/29/2020
  Time: 8:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel-body" id="enterprise-info">
  <div class="row">

    <div class="form-group">
      <div class="col-sm-12 col-md-6">
        <label class="col-sm-4 col-md-5 col-lg-4 control-label nowrap">Loại xác thực :</label>
        <div class="col-sm-8 col-md-7">Doanh nghiệp</div>
        <input type="hidden" name="kycType" value="ENTERPRISE"/>
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-12 col-md-6">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">Tên công ty* :</label>
        <div class="col-md-8 col-lg-8  col-sm-8">
          <input type="text" id="companyName" name="companyName"
                 value="${customerVerifyKyc.companyName}"
                 class="form-control w100" required>
        </div>
      </div>
      <div class="col-sm-12 col-md-6">
        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">Người đại diện* :</label>
        <div class="col-md-7 col-lg-8 col-sm-8">Test</div>
        <input type="hidden" name="representativeName"
               value="${customerVerifyKyc.representativeName}"/>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="form-group">
      <div class="col-sm-12 col-md-6">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">Giấy DKKD số* :</label>
        <div class="col-md-8 col-lg-8  col-sm-8">Test</div>
        <input type="hidden" name="crn" value="${customerVerifyKyc.crn}"/>
      </div>
      <div class="col-sm-12 col-md-6">
        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">Chức danh* :</label>
        <div class="col-md-7 col-lg-8 col-sm-8">
          <input type="text" id="representativePosition" name="representativePosition"
                 class="form-control w100" value="${customerVerifyKyc.representativePosition}"
                 required>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="form-group">
      <div class="col-sm-12 col-md-6">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">Ngày cấp* :</label>
        <div class="col-md-8 col-lg-8  col-sm-8">
          <input type="text" id="crnDateIssued" name="crnDateIssued"
                 data-inputmask="'alias': 'date'"
                 pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}"
                 class="form-control w100" required value="${customerVerifyKyc.crnDateIssued}">
        </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="form-group">
      <div class="col-sm-12 col-md-6">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">Nơi cấp* :</label>
        <div class="col-md-8 col-lg-8  col-sm-8">
          <input type="text" id="crnIssuePlace" name="crnIssuePlace"
                 class="form-control w100" required value="${customerVerifyKyc.crnIssuePlace}">
        </div>
      </div>
    </div>
  </div>

</div>
