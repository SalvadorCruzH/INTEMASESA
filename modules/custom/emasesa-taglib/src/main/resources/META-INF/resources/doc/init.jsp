<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@
taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %><%@
taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.petra.string.StringPool" %>

<%@ page import="com.liferay.portal.kernel.util.*" %>


<%
	String doc = GetterUtil.getString(request.getAttribute("emasesa:docSize:doc"), StringPool.BLANK);
	String size = GetterUtil.getString(request.getAttribute("emasesa:docSize:size"), StringPool.BLANK);
	String fileName = GetterUtil.getString(request.getAttribute("emasesa:docSize:fileName"), StringPool.BLANK);
	String fileExtension = GetterUtil.getString(request.getAttribute("emasesa:docSize:fileExtension"), StringPool.BLANK);
	final String redirect = ParamUtil.getString(request, "redirect", StringPool.BLANK);
%>