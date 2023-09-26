<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@
taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %><%@
taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.petra.string.StringPool" %>
<%@ page import="com.liferay.portal.kernel.theme.ThemeDisplay" %>
<%@ page import="com.liferay.portal.kernel.util.*" %>

<%
	String title = GetterUtil.getString(request.getAttribute("emasesa:header:title"), StringPool.BLANK);
	final boolean isKey = GetterUtil.getBoolean(request.getAttribute("emasesa:header:key"), Boolean.FALSE);
	boolean showBackButton = !GetterUtil.getBoolean(request.getAttribute("emasesa:header:removeBackButton"), Boolean.FALSE)
			&& ParamUtil.getBoolean(request, "bb", Boolean.TRUE);
	if (Validator.isBlank(title) && !isKey) {
		final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		if (Validator.isNotNull(themeDisplay)) {
			title = themeDisplay.getLayout().getName(themeDisplay.getLocale());
		}
	}

	final String cssClass = GetterUtil.getString(request.getAttribute("emasesa:header:cssClass"), StringPool.BLANK);
	final String backUrl = GetterUtil.getString(request.getAttribute("emasesa:header:backUrl"), StringPool.BLANK);
	final String redirect = ParamUtil.getString(request, "redirect", StringPool.BLANK);
	String onclick;

	if (showBackButton) {
		if (!Validator.isBlank(backUrl) && backUrl.startsWith("/")) {
			onclick = "window.location='" + backUrl + "'";
		} else if (!Validator.isBlank(redirect) && redirect.startsWith("/")) {
			onclick = "window.location='" + HtmlUtil.escapeJS(redirect) + "'";
		} else {
			onclick = "backButtonFeature.goBack();";
		}
	} else {
		onclick = "";
	}
%>