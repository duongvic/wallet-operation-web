<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel-title">
    <h4 style="margin-bottom: 1.5rem">Push Notification</h4>
</div>
<div class="panel-body">
    <div class="row">
        <div class="form-group col-sm-4 col-md-6">
            <label class="col-md-2 col-lg-2 col-sm-8" type="text"><spring:message
                    code="label.heading"/>: *</label>
            <div class="col-md-10 col-lg-10 col-sm-8">
                <input type="text" name="noti_title" id="kyc-noti_title"
                       placeholder="tối đa 60 ký tự" maxlength="60"
                       class="form-control w100" value="${heading}">
            </div>
        </div>

        <div class="form-group col-md-4 col-lg-4 col-sm-12">
            <div class="col-md-5">
                <label class="control-label nowrap"
                       for="user_send_type"
                       style="min-width: 100px"><spring:message
                        code="label.receiver.type"/>: *</label>
            </div>
            <div class="col-md-7">
                <select data-plugin-selectTwo
                        class="form-control"
                        id="user_send_type"
                        name="user_send_type">
                    <option value="2">
                        Agent
                    </option>
                  <%--  <option value="2" ${user_send_type eq 2 ? 'selected' : ''}>
                        Agent
                    </option>
                    <option value="3" ${user_send_type eq 3 ? 'selected' : ''}>
                        Merchant
                    </option>--%>
                </select>
            </div>
        </div>

    </div>
    <div class="row">
        <div class="form-group col-sm-4 col-md-6">
            <label class="col-md-2 col-lg-2 col-sm-8" type="text"><spring:message
                    code="label.content"/>: *</label>
            <div class="col-md-10 col-lg-10 col-sm-8">
                <textarea class="form-control" id="content_input" name="content_input"
                          placeholder="tối đa 250 ký tự" maxlength="250"
                          rows="10">${content}</textarea>
            </div>
        </div>
    </div>
    <div class="col-sm-10 text-right">
        <button class="btn btn-sm btn-success" type="submit"><spring:message
                code="label.button.notification"/></button>
    </div>
</div>