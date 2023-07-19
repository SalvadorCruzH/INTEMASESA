<%@ page import="es.camara.intranet.adtcomponent.model.AdtComponent" %>
<%@ include file="init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="cmd" type="hidden" value="update" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
    <div class="display-template">
        <liferay-ddm:template-selector
            className="<%= AdtComponent.class.getName() %>"
            displayStyle="<%= displayStyle %>"
            displayStyleGroupId="<%= displayStyleGroupId %>"
            refreshURL="<%= PortalUtil.getCurrentURL(request) %>"
            showEmptyOption="<%= true %>"
        />
    </div>
	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>