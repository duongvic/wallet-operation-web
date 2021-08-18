<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel-title">
    <h4 style="margin-bottom: 1.5rem"><spring:message code="system.account.info.page.parent.title"/></h4>

    <div class="panel-tools hidden">
        <sec:authorize
                access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_MANAGER' , 'SALESUPPORT')">
            <a class="mb-xs mt-xs btn btn-success" style="line-height: 28px"
               href="${contextPath}/customer/manage/add"><i
                    class="fa fa-plus"></i>&nbsp;<spring:message
                    code="system.service.navigate.btn.create"/></a>
        </sec:authorize>
    </div>
</div>
<div class="panel-body">

    <div class="row">
        <div class="">
            <div class="col-md-6 col-lg-6 col-sm-12  form-group">
                <label id="" class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">
                    <spring:message code="lable.last.first.name"/>&nbsp;
                </label>
                <div class="col-md-8 col-lg-8 col-sm-8">
                    <div class="input-group" style="width: 100%">
                        <input class="form-control"
                               id="parentName" name="parentName"
                               value="${account_object.parentName}"
                               type="text" name="parentName">
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
                               id="parentMsisdn"
                               value="${account_object.parentMsisdn}"
                               type="text" name="parentMsisdn"
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
            <div class="col-md-6 col-lg-6 col-sm-12  form-group">
                <label id="email-title" class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">
                    <spring:message code="setting.account.notification.mode.EMAIL"/>&nbsp; *
                </label>
                <div class="col-md-8 col-lg-8 col-sm-8">
                    <div class="input-group" style="width: 100%">
                        <input class="form-control"
                               pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[a-z]{2,3}$"
                               id="prarentEmail"
                               value="${account_object.prarentEmail}"
                               type="text" name="prarentEmail"
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


            <c:if test="${'edit' eq edit_type}">
                <div class="col-md-6 col-lg-6 col-sm-12">
                    <label class="col-md-5 col-lg-4  col-sm-4 control-label">CIF</label>
                    <div class="col-md-7 col-lg-8  col-sm-8">
                        <input class="form-control"
                               type="text"
                               name="parentCif" id="parentCif"
                               disabled
                               value="${account_object.parentCif}">
                    </div>
                </div>
            </c:if>

        </div>
    </div>

    <sec:authorize
            access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_MANAGER' , 'SALESUPPORT')"
            var="roleAdmmin"/>
    <sec:authorize
            access="hasAnyRole('SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM')"
            var="roleSales"/>
    <%--<c:if test="${('edit' ne edit_type && (roleAdmmin || roleSales)) || ('edit' eq edit_type && roleAdmmin)}">--%>
        <div class="row">
            <div class="form-group">
                <div class="col-sm-12 text-right">
                    <button class="btn btn-sm btn-success ${'edit' eq edit_type ? '' : 'hidden'}"
                            type="button" id="edit_btn"
                            name="edit" onclick="editAccountParent()"
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
    <%--</c:if>--%>
</div>