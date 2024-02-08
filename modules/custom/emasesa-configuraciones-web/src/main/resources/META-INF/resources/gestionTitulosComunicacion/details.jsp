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

                <aui:input type="text" name="OLD_TITULO" label=""
                           value="${requestScope['registro'].OLD_TITULO}" cssClass="hide"
                />

                <aui:input type="text" name="OLD_USUARIO" label=""
                           value="${requestScope['registro'].OLD_USUARIO}" cssClass="hide"
                />

                <aui:input type="text" name="TITULO"
                           value="${requestScope['registro'].TITULO}"
                           label="Título" required="true"
                />

                <aui:select name="USUARIO" label="Usuario">
                    <c:forEach var="role" items="${requestScope.roles}">
                        <c:set var="selectedRole" value="${requestScope['registro'].USUARIO}" />
                        <aui:option value="${role.getName()}" label="${role.getName()}" selected="${role.getName() eq selectedRole}" />
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
