<%@ page import = "es.emasesa.intranet.portlet.configuraciones.constants.EmasesaConfiguracionesWebPortletKeys" %><%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:defineObjects />

<portlet:actionURL name="saveItem" var="saveURL">
    <portlet:param name="<%=EmasesaConfiguracionesWebPortletKeys.ACTION%>" value="${requestScope.action}"/>
</portlet:actionURL>

<portlet:renderURL var="goBack" />

<div class="container-fluid container-fluid-max-xl">
    <div class="sheet">
        <div class="panel-group panel-group-flush">
            <aui:form action="${saveURL}" method="post">

                <aui:input type="text" name="OLD_TIPOLOGIA" label=""
                           value="${requestScope['registro'].OLD_TIPOLOGIA}" cssClass="hide"
                />

                <aui:input type="text" name="OLD_SUBTIPOLOGIA" label=""
                           value="${requestScope['registro'].OLD_SUBTIPOLOGIA}" cssClass="hide"
                />

                <aui:input type="text" name="OLD_DESTINATARIO" label=""
                           value="${requestScope['registro'].OLD_DESTINATARIO}" cssClass="hide"
                />

                <aui:input type="text" name="TIPOLOGIA"
                           value="${requestScope['registro'].TIPOLOGIA}"
                           label="Tipología" required="true"
                />

                <aui:input type="text" name="SUBTIPOLOGIA"
                           value="${requestScope['registro'].SUBTIPOLOGIA}"
                           label="Subtipología"
                />

                <aui:select name="DESTINATARIO" label="Destinatario">
                    <c:forEach var="role" items="${requestScope.roles}">
                        <c:set var="selectedRole" value="${requestScope['registro'].DESTINATARIO}" />
                        <aui:option value="${role.getRoleId().toString()}" label="${role.getName()}" selected="${role.getRoleId().toString() eq selectedRole}" />
                    </c:forEach>
                </aui:select>

                <a href="${goBack}" class="btn btn-cancel btn-secondary">Volver</a>
                <aui:button type="submit"
                            value="Guardar"
                />
            </aui:form>
        </div>
    </div>
</div>
