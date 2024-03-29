<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:renderURL var="addURL">
    <portlet:param name="jspPage" value="/gestionTitulosComunicacion/details.jsp" />
</portlet:renderURL>

<c:set var="configValue" value="${requestScope.configValue}" />

<liferay-ui:success key="<%=EmasesaConfiguracionesWebPortletKeys.MSG_SAVE_OK%>" message="es.emasesa.intranet.portlet.configuraciones.saveok"></liferay-ui:success>
<liferay-ui:error key="<%=EmasesaConfiguracionesWebPortletKeys.MSG_SAVE_KO%>" message="es.emasesa.intranet.portlet.configuraciones.saveko"></liferay-ui:error>
<liferay-ui:success key="<%=EmasesaConfiguracionesWebPortletKeys.MSG_DELETE_OK%>" message="es.emasesa.intranet.portlet.configuraciones.deleteok"></liferay-ui:success>
<liferay-ui:error key="<%=EmasesaConfiguracionesWebPortletKeys.MSG_DELETE_KO%>" message="es.emasesa.intranet.portlet.configuraciones.deleteko"></liferay-ui:error>

<%
    JSONArray jsonArray = (JSONArray)request.getAttribute("configValue");
    List<JSONObject> jsonList = new ArrayList<JSONObject>();
    for (int i=0; i < jsonArray.length(); i++) {
        jsonList.add(jsonArray.getJSONObject(i));
    }
%>
<div class="container-fluid container-fluid-max-xl">
    <div class="sheet">
        <div class="panel-group panel-group-flush">
            <div class="em-config__titulos">
                <a id="addNewRow" href="${addURL}" class="btn btn-primary">Añadir nuevo elemento</a>

                <liferay-ui:search-container delta="20" emptyResultsMessage="No se han encontrado Titulos">
                    <liferay-ui:search-container-results
                            results="<%= jsonList %>"/>
                    <liferay-ui:search-container-row
                            className="com.liferay.portal.kernel.json.JSONObject"
                            keyProperty="TITULO"
                            modelVar="rowObject">
                        <liferay-ui:search-container-column-text name="<%=EmasesaConfiguracionesWebPortletKeys.TITULO%>" value='<%= rowObject.getString("TITULO") %>'/>

                        <c:set var="foundRole" value="false" />
                        <c:forEach var="role" items="${requestScope.roles}">
                            <c:if test='${role.getName() == rowObject.getString("USUARIO")}'>
                                <c:set var="foundRole" value="true" />
                                <liferay-ui:search-container-column-text name="<%=EmasesaConfiguracionesWebPortletKeys.USUARIO%>" value="${role.name}"/>
                            </c:if>
                        </c:forEach>
                        <c:if test="${!foundRole}">
                            <liferay-ui:search-container-column-text name="<%=EmasesaConfiguracionesWebPortletKeys.USUARIO%>" value=""/>
                        </c:if>

                        <liferay-ui:search-container-column-text name="">
                            <portlet:renderURL var="editURL">
                                <portlet:param name="jspPage" value="/gestionTitulosComunicacion/details.jsp" />
                                <portlet:param name="<%=EmasesaConfiguracionesWebPortletKeys.TITULO%>" value='<%=rowObject.getString("TITULO") %>'/>
                                <portlet:param name="<%=EmasesaConfiguracionesWebPortletKeys.OLD_TITULO%>" value='<%=rowObject.getString("TITULO") %>'/>
                                <portlet:param name="<%=EmasesaConfiguracionesWebPortletKeys.USUARIO%>" value='<%=rowObject.getString("USUARIO") %>'/>
                                <portlet:param name="<%=EmasesaConfiguracionesWebPortletKeys.OLD_USUARIO%>" value='<%=rowObject.getString("USUARIO") %>'/>
                            </portlet:renderURL>

                            <portlet:actionURL name="deleteItem" var="deleteURL">
                                <portlet:param name="<%=EmasesaConfiguracionesWebPortletKeys.TITULO%>" value='<%= rowObject.getString("TITULO") %>'/>
                                <portlet:param name="<%=EmasesaConfiguracionesWebPortletKeys.ACTION%>" value="delete"/>
                            </portlet:actionURL>

		                        <liferay-ui:icon-menu>
		                          <liferay-ui:icon message="Editar" url="${editURL}" />
		                          <liferay-ui:icon message="Eliminar" url="${deleteURL}" />
		                        </liferay-ui:icon-menu>
                        </liferay-ui:search-container-column-text>

                    </liferay-ui:search-container-row>
                    <liferay-ui:search-iterator />
                </liferay-ui:search-container>
            </div>
        </div>
    </div>
</div>
