<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:actionURL name="saveItem" var="saveURL">
    <portlet:param name="javax.portlet.action" value="saveItem"/>
</portlet:actionURL>

<liferay-ui:success key="<%=EmasesaConfiguracionesWebPortletKeys.MSG_SAVE_OK%>" message="es.emasesa.intranet.portlet.configuraciones.saveok"></liferay-ui:success>
<liferay-ui:error key="<%=EmasesaConfiguracionesWebPortletKeys.MSG_SAVE_KO%>" message="es.emasesa.intranet.portlet.configuraciones.saveko"></liferay-ui:error>

<div class="container-fluid container-fluid-max-xl">
    <div class="sheet">
        <div class="panel-group panel-group-flush">
             <form action="${saveURL}" method="post" >
                <label class="" for="<portlet:namespace /><%=EmasesaConfiguracionesWebPortletKeys.STAR_DATE%>">Fecha de Inicio</label>
                <input class="form-control" id="<portlet:namespace /><%=EmasesaConfiguracionesWebPortletKeys.STAR_DATE%>" name="<portlet:namespace /><%=EmasesaConfiguracionesWebPortletKeys.STAR_DATE%>" value="${requestScope.startDate}" type="date" style="width: 200px;" />

                 <label class="" for="<portlet:namespace /><%=EmasesaConfiguracionesWebPortletKeys.END_DATE%>">Fecha de Fin</label>
                 <input class="form-control" id="<portlet:namespace /><%=EmasesaConfiguracionesWebPortletKeys.END_DATE%>" name="<portlet:namespace /><%=EmasesaConfiguracionesWebPortletKeys.END_DATE%>" value="${requestScope.endDate}" type="date" style="width: 200px;" />

                 <button type="submit" class="btn btn-primary">Guardar</button>
            </form>
        </div>
    </div>
</div>