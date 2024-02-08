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

                <aui:input type="text" name="OLD_DEPARTAMENTO" label=""
                           value="${requestScope['registro'].OLD_DEPARTAMENTO}" cssClass="hide"
                />

                <aui:input type="text" name="TIPOLOGIA"
                           value="${requestScope['registro'].TIPOLOGIA}"
                           label="Tipología" required="true"
                />

                <aui:input type="text" name="DEPARTAMENTO"
                           value="${requestScope['registro'].DEPARTAMENTO}"
                           label="Departamento" required="true"
                />

                <a href="${goBack}" class="btn btn-cancel btn-secondary">Volver</a>
                <aui:button type="submit"
                            value="Guardar"
                />
            </aui:form>
        </div>
    </div>
</div>
