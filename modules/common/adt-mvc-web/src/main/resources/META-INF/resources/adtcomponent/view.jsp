<%@ include file="init.jsp" %>

<div class="custom-adt-component-container">
	<liferay-ddm:template-renderer
        className="<%= AdtComponent.class.getName() %>"
	    contextObjects="<%= AdtComponentConstants.ADT_EMPTY_CONTEXT %>"
	    displayStyle="<%= displayStyle %>"
	    displayStyleGroupId="<%= displayStyleGroupId %>"
	    entries="<%= AdtComponentConstants.ADT_EMPTY_LIST %>">
    <div class="alert alert-info">
    	<liferay-ui:message key="adt-mvcportlet.please-configure"/>
    </div>
    
    </liferay-ddm:template-renderer>
</div>