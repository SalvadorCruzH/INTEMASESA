<%@ include file="/init.jsp" %>

<portlet:actionURL var="sendForm" name="sendForm" />

<liferay-ui:success key="<%=GestionBoletinesPortletKeys.MSG_OK_GUARDADO_DATOS%>" message="es.emasesa.intranet.gestionBoletines.msg.ok.guardado"></liferay-ui:success>
<% String tabSelected = ParamUtil.getString(request, "tabSelected", GestionBoletinesPortletKeys.INPUT_TIPO_LISTA_CONTROLADA_VALUE);
    PortletURL portletURL = renderResponse.createRenderURL();
    portletURL.setParameter("tabSelected", tabSelected);
    String tabsValues = GestionBoletinesPortletKeys.INPUT_TIPO_LISTA_CONTROLADA_VALUE.concat(",").concat(GestionBoletinesPortletKeys.INPUT_TIPO_LISTA_DISTRIBUCION_VALUE);
%>
<div class="container-fluid container-fluid-max-xl">
    <div class="sheet">
        <div class="panel-group panel-group-flush">
            <liferay-ui:tabs names="es.emasesa.intranet.gestionBoletines.listacontrolada,es.emasesa.intranet.gestionBoletines.lista"
                             url="<%= portletURL.toString() %>"
                             tabsValues="<%=tabsValues%>"
                             param="tabSelected"
            />
            <c:if test="<%=tabSelected.equals(GestionBoletinesPortletKeys.INPUT_TIPO_LISTA_CONTROLADA_VALUE)%>">
                <%@include file="listas/listaControlada.jspf" %>
            </c:if>
            <c:if test="<%=tabSelected.equals(GestionBoletinesPortletKeys.INPUT_TIPO_LISTA_DISTRIBUCION_VALUE)%>">
                <%@include file="listas/listaDistribucion.jspf" %>
            </c:if>
        </div>
    </div>
</div>

<script>
    //Invocacion para añadir los tooltips a los label de destinatarios
    AUI()
        .use( 'aui-node' , function ( A ) {
            let label = A.one( '.destinatarios' ).previous();
            let helpIcon = A.Node.create( `<liferay-ui:icon-help message="es.emasesa.intranet.gestionBoletines.tooltip.formatoInputDestinatarios" />` );
            label.appendChild( helpIcon );
        } );
</script>