<%@ include file="init.jsp" %>

<style>
/* Overriding header margin in form portlets */
.portlet-layout .portlet-header {
    margin-bottom: 0;
}
</style>

<jsp:include page="${formPage}" flush="true" />