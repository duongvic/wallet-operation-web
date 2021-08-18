<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.OutletStoreType" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel-title">
    <h4 style="margin-bottom: 1.5rem"><spring:message code="account.store.infor"/></h4>
</div>
<div class="panel-body">

    <div class="row">
        <div class="">
            <div class="col-md-6 col-lg-6 col-sm-12  form-group">
                <label id="" class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">
                    <spring:message code="account.store.name"/>*&nbsp;
                </label>
                <div class="col-md-8 col-lg-8 col-sm-8">
                    <div class="input-group" style="width: 100%">
                        <%--<c:if test="${account_object.addresses ne null && fn:length(account_object.addresses) gt 0}">--%>
                            <%--<c:forEach var="itemAddress" items="${account_object.addresses}">--%>
                                <%--<c:if test="${itemAddress.addressType eq 2}">--%>
                                    <input type="text" class="form-control"
                                           id="aliasStore" name="aliasStore"
                                           value="${aliasStore}"
                                           placeholder="<spring:message code="account.store.name"/>"
                                           required>
                                <%--</c:if>--%>
                            <%--</c:forEach>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${account_object.addresses eq null}">--%>
                            <%--<input type="text" id="alias" name="alias" class="form-control w100"--%>
                                   <%--value=""/>--%>
                        <%--</c:if>--%>
                    </div>
                </div>
            </div>

            <div class="col-md-6 col-lg-6 col-sm-12  form-group">
                <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap"><spring:message
                    code="label.phone"/>*</label>
                <div class="col-md-7 col-lg-8 col-sm-8">
                    <div class="input-group" style="width: 100%">
                        <%--<c:if--%>
                                <%--test="${account_object.addresses ne null && fn:length(account_object.addresses) gt 0 }">--%>
                            <%--<c:forEach var="itemAddress" items="${account_object.addresses}">--%>
                                <%--<c:if test="${itemAddress.addressType eq 2}">--%>
                                    <input class="form-control"
                                           pattern="[0-9]{10,11}"
                                           id="businessPhoneStore" name="businessPhoneStore"
                                           value="${businessPhoneStore}"
                                           type="text"
                                           placeholder="<spring:message code="label.phone"/>"
                                           required>
                                <%--</c:if>--%>
                            <%--</c:forEach>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${account_object.addresses eq null}">--%>
                            <%--<input type="text"--%>
                                   <%--pattern="[0-9]{10,11}"--%>
                                   <%--placeholder="<spring:message code="label.phone"/>"--%>
                                   <%--id="businessPhone" name="businessPhone" class="form-control w100"--%>
                                   <%--value=""/>--%>
                        <%--</c:if>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="form-group">
            <div class="col-md-6 col-lg-6 col-sm-12  form-group">
                <label id="email-title" class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">
                    <spring:message code="account.info.store.address"/>*
                </label>
                <div class="col-md-8 col-lg-8 col-sm-8">
                    <div class="input-group" style="width: 100%">
                        <%--<c:choose>--%>
                            <%--<c:when test="${account_object.addresses ne null && fn:length(account_object.addresses) gt 0 }">--%>
                                <%--<c:forEach var="itemAddress" items="${account_object.addresses}">--%>
                                    <%--<c:if test="${itemAddress.addressType eq 2}">--%>
                                        <input type="text" class="form-control"
                                               id="street1Store" name="street1Store"
                                               value="${street1Store}"
                                               placeholder="<spring:message code="account.info.store.address"/>"
                                               required>
                                    <%--</c:if>--%>
                                <%--</c:forEach>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%--<input type="text" class="form-control"--%>
                                       <%--id="street1" name="street1"--%>
                                       <%--value=""--%>
                                       <%--placeholder="<spring:message code="account.info.store.address"/>">--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                    </div>
                </div>
            </div>

            <div class="col-md-6 col-lg-6 col-sm-12">
                <label class="col-md-5 col-lg-4  col-sm-4 control-label"><spring:message
                    code="account.shop.group"/>*</label>
                <div class="col-md-7 col-lg-8  col-sm-8">
                    <c:set var="listOutLetStoreType" value="<%=OutletStoreType.values()%>"/>
                    <select class="form-control" id="outletStoreType"
                            data-plugin-selectTwo
                            name="outletStoreType" required>
                        <option value=""><spring:message code="setting.account.select.status"/>
                        </option>
                        <c:forEach items="${listOutLetStoreType}" var="item">
                            <option value="${item.getCode()}" ${outletStoreType != null  && outletStoreType eq item.getCode() ? 'selected' : ''}>${item.getDisplayText()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

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
                        name="edit" onclick="editAccountStore()"
                        value="create"><i
                        class="fa fa-pencil"></i> <spring:message code="common.btn.edit"/>
                </button>

                <c:choose>
                    <c:when test="${'edit' eq edit_type}">
                        <button class="btn btn-sm btn-primary"
                                type="submit" value="save-account-store"
                                name="btn-action" id="save_btn_store"
                            ${'edit' eq edit_type ? 'disabled' : ''}><i
                            class="fa fa-save"></i>
                            <spring:message code="common.btn.save"/>
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-sm btn-primary"
                                type="submit" value="save-account-store"
                                name="btn-action" id="save_btn_store"><i
                            class="fa fa-save"></i>
                            <spring:message code="common.btn.save"/>
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <%--</c:if>--%>
</div>