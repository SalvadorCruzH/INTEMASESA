<%@ include file="/init.jsp" %>

<portlet:actionURL var="sendForm" name="sendForm" >
    <portlet:param name="javax.portlet.action" value="processActionGestionListas" />
</portlet:actionURL>

<liferay-ui:success key="<%=GestionBoletinesPortletKeys.MSG_OK_GUARDADO_DATOS_CONTROLADA%>" message="es.emasesa.intranet.gestionBoletines.listaControlada.msg.ok.guardado"></liferay-ui:success>
<liferay-ui:success key="<%=GestionBoletinesPortletKeys.MSG_OK_GUARDADO_DATOS_DISTRIBUCION%>" message="es.emasesa.intranet.gestionBoletines.listaDistribuida.msg.ok.guardado"></liferay-ui:success>

<%@include file="listas/listaControlada.jspf" %>
<%@include file="listas/listaDistribucion.jspf" %>

<script>
    //Invocacion para añadir los tooltips a los label de destinatarios
    AUI()
        .use( 'aui-node' , function ( A ) {
            let destinatariosElements = A.all('.destinatarios');

            destinatariosElements.each(function (destinatario) {
                let label = destinatario.previous();
                let helpIcon = A.Node.create(`<liferay-ui:icon-help message="es.emasesa.intranet.gestionBoletines.tooltip.formatoInputDestinatarios" />`);
                label.appendChild(helpIcon);
            });
        } );
</script>