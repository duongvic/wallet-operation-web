<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel-title">
  <h4 style="margin-bottom: 1.5rem"><spring:message code="system.account.info.page.title"/></h4>

  <div class="panel-tools hidden">
    <sec:authorize
        access="hasAnyRole('ADMIN_OPERATION')">
      <a class="mb-xs mt-xs btn btn-success" style="line-height: 28px"
         href="${contextPath}/staff-account/manage/add"><i
          class="fa fa-plus"></i>&nbsp;<spring:message
          code="system.service.navigate.btn.create"/></a>
    </sec:authorize>
  </div>
</div>
<div class="panel-body">
  <div class="row">
    <div class="form-group">
      <div class="col-md-6 col-lg-6 col-sm-12">
        <spring:message
            code="${account_object.firstName != null ? account_object.firstName : (account_object.displayName != null ? account_object.displayName.split(' ')[0] : null)}"
            var="first_name"/>
        <spring:message
            code="${account_object.lastName != null ? account_object.lastName : (account_object.displayName != null ? account_object.displayName.replace(first_name, '').trim() : null)}"
            var="last_name"/>
        <label class="col-md-4 col-lg-4  col-sm-4 control-label nowrap"><spring:message
            code="lable.last.first.name"/>&nbsp; *</label>
        <div class="col-md-8 col-lg-8  col-sm-8 double-input">
          <input class="form-control" id="last-name"
                 <%--pattern="^[^&amp;&gt;&lt;\\*?%:!&quot;#$()+,;=@\[\]{}~\^|`\n\t\r/]+$"--%>
                 required value="${last_name}"
                 type="text" name="last-name"
                 placeholder="Họ">
          <input class="form-control"
                 <%--pattern="^[^&amp;&gt;&lt;\\*?%:!&quot;#$()+,;=@\[\]{}~\^|`\n\t\r/]+$"--%>
                 required value="${first_name}"
                 type="text" name="first-name" id="first-name"
                 placeholder="Tên">

        </div>
      </div>
      <div class="col-md-6 col-lg-6 col-sm-12">
        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap"><spring:message
            code="label.notification"/>&nbsp; *</label>
        <div class="col-md-7 col-lg-8 col-sm-8">
          <select class="form-control" id="notification"
                  data-plugin-selectTwo required
                  name="notification">
            <option value=""><spring:message code="setting.account.select.status"/>
            </option>
            <c:forEach
                items="${notification_mode_ids}"
                var="notification_mode_id">
              <option
                  value="${notification_mode_id.key}" ${notification_mode_id.key eq account_object.notificationModeId ? 'selected' : ''}>
                <spring:message
                    code="setting.account.notification.mode.${notification_mode_id.value}"/>
              </option>
            </c:forEach>
          </select>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="">
      <div class="col-md-6 col-lg-6 col-sm-12  form-group">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap"><spring:message
            code="setting.account.notification.mode.EMAIL"/>&nbsp; *</label>
        <div class="col-md-8 col-lg-8 col-sm-8">
          <div class="input-group" style="width: 100%">
            <input class="form-control"
                   pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[a-z]{2,3}$"
                   required id="email"
                   value="${account_object.email}"
                   type="text" name="email"
                   placeholder="Thư điện tử">
            <span class="input-group-addon border-input hidden"
                  data-toggle="popover"
                  data-trigger="hover"
                  data-placement="top"
                  data-content="${authenticated}">
                                            <i class="fa fa-lg fa-check-circle text-success"
                                               aria-hidden="true"></i>
                                        </span>
          </div>

        </div>

      </div>
      <div class="col-md-6 col-lg-6 col-sm-12  form-group">
        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap"><spring:message
            code="label.phone"/>*</label>
        <div class="col-md-7 col-lg-8 col-sm-8">
          <div class="input-group" style="width: 100%">
            <input class="form-control"
                   pattern="[0-9]{10,11}"
                   required id="phone"
                   value="${account_object.msisdn}"
                   type="text" name="phone"
                   placeholder="<spring:message code="label.phone"/>">
            <span class="input-group-addon border-input hidden"
                  data-toggle="popover"
                  data-trigger="hover"
                  data-placement="top"
                  data-content="${unAuthenticated}">
                                            <i class="fa fa-lg fa-exclamation-triangle text-danger"
                                               aria-hidden="true"></i>
                                        </span>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="form-group">
      <div class="col-md-6 col-lg-6 col-sm-12">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap"
               for="customerType"><spring:message code="label.staff.type"/>
          *</label>
        <c:set var="objectCusType" value="${account_object.customerType.id}"/>
        <input type="hidden" id="customerType" name="customerType" value="${(objectCusType != null && '' ne objectCusType) ? objectCusType : param.customerType}">
        <div class="col-md-8 col-lg-8 col-sm-8" id="lbl-customer-Type" style="padding-left: 24px;">
          <c:forEach items="${customer_type_ids}" var="item">
            <c:if test="${item.key eq objectCusType || item.key eq param.customerType}">
              <c:set var="customerTypeTitle" value="${item.value}"/>
              ${customerTypeTitle}
            </c:if>
          </c:forEach>
          <c:if test="${customerTypeTitle == null}">
            N/A
          </c:if>
        </div>
      </div>

      <div class="col-md-6 col-lg-6 col-sm-12">
        <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap"><spring:message
            code="account.dialog.blacklist.reason"/> </label>
        <div class="col-md-7 col-lg-8 col-sm-8">
          <select class="form-control"
                  data-plugin-selectTwo id="blackList"
                  name="blackList" required>
            <c:forEach
                items="${listBlackReason}"
                var="block_reason"
                varStatus="block_id">
              <option
                  value="${block_reason.key}" ${account_object.blackListReason eq block_reason.key ? 'selected' : ''}>
                <spring:message
                    code="label.blackListR.${block_reason.key}"/></option>
            </c:forEach>
          </select>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="form-group">
      <div class="col-md-6 col-lg-6 col-sm-12">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap"
               for="userType"><spring:message code="label.user.type"/>
          *</label>
        <input type="hidden" id="userType"
               name="userType" value="">
        <div class="col-md-8 col-lg-8 col-sm-8"
             id="lbl-user-type"
             style="padding-left: 24px;">${user_type == null || user_type eq '' ? 'N/A' : user_type}</div>
      </div>


      <div class="col-md-6 col-lg-6 col-sm-12">
        <label class="col-md-5 col-lg-4  col-sm-4 control-label">CIF</label>
        <div class="col-md-7 col-lg-8  col-sm-8">
          <input class="${'edit' eq edit_type ? 'form-control' : 'hidden'}"
                 type="text"
                 name="account-id" id="account-id"
                 disabled
                 value="${account_object.cif}">
          <input class="${'edit' eq edit_type ? 'hidden' : 'form-control'}"
                 type="text"
                 name="cif"
                 pattern="[a-zA-Z0-9-_]{10,11}"
                 placeholder="CIF"
                 value="${account_object.cif}">
        </div>
      </div>

    </div>
  </div>

  <div class="row">
    <div class="form-group">
      <div class="col-md-6 col-lg-6 col-sm-12 form-group">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">Loại
          ví *</label>
        <div class="col-md-8 col-lg-8 col-sm-8 parent-wallet-type">
          <div id="lbl-wallet-type"
               style="padding-left: 10px;">${wallet_type == null || wallet_type eq '' ? "N/A" : wallet_type}</div>
        </div>
      </div>

      <div class="col-md-6 col-lg-6 col-sm-12 form-group">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap"><spring:message
            code="select.positionList"/> </label>
        <div class="col-md-8 col-lg-8 col-sm-8">
          <select data-plugin-selectTwo
                  class="form-control" id="positionList"
                  name="positionList">
            <option value=""><spring:message
                code="label.please.select"/></option>
            <c:choose>
              <c:when
                  test="${listPositions ne null && listPositions.size() > 0 }">
                <c:forEach var="listPosition"
                           items="${listPositions}">
                  <option
                      value="${listPosition.id}" ${(positionList !=null && positionList eq listPosition.id) ? 'selected':''}>
                    <spring:message
                        code="label.positionList.${listPosition.id}"/></option>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <option value="">N/A</option>
              </c:otherwise>
            </c:choose>
          </select>
        </div>
      </div>

    </div>
  </div>

  <div class="row">
    <div class="form-group">
      <%--description--%>
      <div class="col-md-6 col-lg-6 col-sm-12 form-group">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap"><spring:message
            code="label.description"/>
        </label>
        <div class="col-md-8 col-lg-8 col-sm-8">
          <textarea class="form-control" rows="5" id="description" name="description">${account_object.description}</textarea>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="form-group">
      <div class="col-sm-12 text-right">
        <button class="btn btn-sm btn-success ${'edit' eq edit_type ? '' : 'hidden'}"
                type="button" id="edit_btn"
                name="edit" onclick="editAccount()"
                value="create"><i
            class="fa fa-pencil"></i> <spring:message code="common.btn.edit"/>
        </button>
        <button class="btn btn-sm btn-primary"
                type="submit" value="save-account-info"
                name="btn-action" id="save_btn"
        ${'edit' eq edit_type ? 'disabled' : ''}><i
            class="fa fa-save"></i>
          <spring:message code="common.btn.save"/>
        </button>
      </div>
    </div>
  </div>
</div>