<%@ include file="../init.jsp" %>

<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="es.emasesa.intranet.adtcomponent.model.AdtComponent"%>
<%@page import="es.emasesa.intranet.adtcomponent.constants.AdtComponentConstants"%>

<%
	String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", ""));
	long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), themeDisplay.getScopeGroupId());
%>