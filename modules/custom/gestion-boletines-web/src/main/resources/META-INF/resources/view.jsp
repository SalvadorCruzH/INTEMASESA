<%@ taglib prefix = "c"
           uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/init.jsp" %>

<% String tabSelected = ParamUtil.getString(request, "tabSelected", GestionBoletinesPortletKeys.INPUT_TIPO_LISTA_CONTROLADA_VALUE);
    PortletURL portletURL = renderResponse.createRenderURL();
    portletURL.setParameter("tabSelected", tabSelected);
    String tabsValues = GestionBoletinesPortletKeys.TIPO_VISTA_GESTION_BOLETIN.concat(",").concat(GestionBoletinesPortletKeys.TIPO_VISTA_ENVIO_BOLETIN);
%>
<div class="container-fluid container-fluid-max-xl">
    <div class="sheet">
        <div class="panel-group panel-group-flush">
            <liferay-ui:tabs names="es.emasesa.intranet.vista.gestionlistas,es.emasesa.intranet.vista.envioBoletin"
                             url="<%= portletURL.toString() %>"
                             tabsValues="<%=tabsValues%>"
                             param="tabSelected"
            />

            <c:choose>
                <c:when test="<%=tabSelected.equals(GestionBoletinesPortletKeys.TIPO_VISTA_ENVIO_BOLETIN)%>">
                    <%@include file="/envio-boletin/view.jsp" %>
                </c:when>
                <c:otherwise>
                    <%@include file="/gestion-listas/view.jsp" %>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>