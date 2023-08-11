<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay"  %>
<%@ taglib uri="http://liferay.com/tld/map" prefix="liferay-map" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>

<%@page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext" %><%@
page import="es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys" %>

<liferay-frontend:defineObjects />
<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
    final AjaxSearchDisplayContext ajaxSearchDisplayContext =
            (AjaxSearchDisplayContext) request.getAttribute(AjaxSearchPortletKeys.ATTR_AJAX_SEARCH_DISPLAY_CONTEXT);
%>