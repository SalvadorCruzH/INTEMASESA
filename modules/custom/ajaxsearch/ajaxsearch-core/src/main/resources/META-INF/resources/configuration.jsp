<%@ page import="es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys" %>
<%@ include file="init.jsp" %>

<%
    String currentAjaxPref = GetterUtil.getString(portletPreferences.getValue(AjaxSearchPortletKeys.PORTLET_PREFS_SEARCH_IMPL,""));
    String currentConfig = GetterUtil.getString(portletPreferences.getValue(AjaxSearchPortletKeys.PORTLET_CURRENT_CONFIG,""));
%>
<c:set var="currentAjaxPref" value="<%=currentAjaxPref%>"/>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<div class="custom-config">
    <aui:form action="<%= configurationActionURL %>" method="post" name="fm">
        <aui:input name="cmd" type="hidden" value="update" />
        <aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
    <aui:fieldset>
    <aui:select name="<%=AjaxSearchPortletKeys.PORTLET_PREFS_SEARCH_IMPL %>" id="searchImpl" label="Impl" cssClass="search-impl">
        <aui:option value=""> -- </aui:option>
        <c:forEach items="${currentAjaxImpls}" var="currentImpl">
            <c:choose>
                <c:when test="${currentImpl.getClass().getName() == currentAjaxPref}">
                    <aui:option selected="true" value="${currentImpl.getClass().getName()}"><liferay-ui:message key="${currentImpl.getClass().getName()}"/></aui:option>
                </c:when>
                <c:otherwise>
                    <aui:option value="${currentImpl.getClass().getName()}"><liferay-ui:message key="${currentImpl.getClass().getName()}"/></aui:option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </aui:select>
    <c:forEach items="${currentAjaxImpls}" var="currentImpl">
        <textarea id="CONFIG_${currentImpl.getClass().getName()}" style="display: none">${customPropertiesUtil.getStringFromProperties(currentImpl.getDefaultProperties())}</textarea>
    </c:forEach>
    <c:choose>
        <c:when test="${not empty currentAjaxPref}"><div></c:when>
        <c:otherwise><div style="display:none"></c:otherwise>
    </c:choose>
        <aui:input type="textarea" name="<%=AjaxSearchPortletKeys.PORTLET_CURRENT_CONFIG %>" id="currentConfig" label="currentConfig" value="<%=currentConfig%>" cssClass="current-config"/></div>
    </aui:fieldset>
    <aui:button-row>
        <aui:button value="Reset Config" cssClass="btn-lg" type="button" onClick="resetConfig()" />
        <aui:button cssClass="btn-lg" type="submit" />
    </aui:button-row>
    </aui:form>
</div>

<script>
    function resetConfig(){
        var currentImpl = $(".search-impl").val();
        var currentImplText = $('[id="CONFIG_'+currentImpl+'"]').val();
        $(".current-config").val(currentImplText);
    }
</script>

    <script >
        $( document ).ready(function() {
            $("#searchImpl").on("change", function(){
                if ($("#searchImpl").val() > 0) {
                    $("#currentConfig").show();
                } else {
                    $("#currentConfig").hide();
                }
            });
        });
    </script>
