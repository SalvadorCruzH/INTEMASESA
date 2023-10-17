<%@ include file="/init.jsp" %>

<portlet:actionURL var="sendForm" name="sendForm" >
    <portlet:param name="javax.portlet.action" value="processActionEnvioBoletin" />
</portlet:actionURL>

<liferay-ui:success key="<%=GestionBoletinesPortletKeys.MSG_OK_ENVIO_CORREO_CONTROLADA%>" message="es.emasesa.intranet.gestionBoletines.listaControlada.msg.ok.enviado"></liferay-ui:success>
<liferay-ui:success key="<%=GestionBoletinesPortletKeys.MSG_OK_ENVIO_CORREO_DISTRIBUCION%>" message="es.emasesa.intranet.gestionBoletines.listaDistribuida.msg.ok.enviado"></liferay-ui:success>

<%@include file="listas/listaControlada.jspf" %>
<%@include file="listas/listaDistribucion.jspf" %>

<script>
    $(document).ready(function(){
        gestionBoletines.init('<portlet:namespace/>');
        gestionBoletines.initSelect2('<%=GestionBoletinesPortletKeys.INPUT_BOLETIN_CONTROLADA%>');
        gestionBoletines.initSelect2('<%=GestionBoletinesPortletKeys.INPUT_BOLETIN_DISTRIBUIDA%>');
    });
</script>