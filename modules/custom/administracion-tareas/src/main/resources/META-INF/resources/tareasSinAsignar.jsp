<%@ include file="/META-INF/resources/init.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%
    List<WorkflowTask> listAllWorkflowTask = (List<WorkflowTask>) request.getAttribute("listAllWorkflowTask");
    String select = request.getAttribute("select").toString();
%>
<liferay-portlet:resourceURL var="assignmentTask" id="/assignmentTask"/>
<div class="container">
    <nav class="breadcrumbs" id="breadcrumbs" aria-label="breadcrumbs">
        <ol class="breadcrumbs__items">
            <li class="breadcrumbs__item">
                <a href="/home" title="Home">Home</a>
                <span aria-hidden="true"><i class="fa-solid fa-angle-right"></i></span>
            </li>
            <li class="breadcrumbs__item">
                <a href="/administrar-tareas" title="Parent">Administrar tareas</a>
                <span aria-hidden="true"><i class="fa-solid fa-angle-right"></i></span>
            </li>
            <li class="breadcrumbs__item">
                <a href="/" title="Current" aria-current="location">Current</a>
                <span aria-hidden="true"><i class="fa-solid fa-angle-right"></i></span>
            </li>
        </ol>
    </nav>
    <!-- /.breadcrumbs -->

    <div class="favorite">
        <a href="#" title="Agregar a favorito" class="favorite__link">
            <img src="/o/emasesa-theme/images/icons/ico-fav-no.svg" alt="" class="favorite__ico">
            <span class="favorite__label">En favoritos</span>
        </a>
    </div>
    <!-- /.favorite -->

    <h1 class="page-title">Consulta de Tareas por asignar</h1>

    <div class="ema-table-wrapper">
        <table id="table-id" class="ema-table last">
            <caption class="sr-only">Sumario de la tabla</caption>
            <thead>
            <tr>
                <th scope="col"><i class="fa-solid fa-sort fa-lg"></i>Matrícula</th>
                <th scope="col"></i>Nombre</th>
                <th scope="col"><i class="fa-solid fa-sort fa-lg"></i>Tipo</th>
                <th scope="col"><i class="fa-solid fa-sort fa-lg"></i>Fecha solicitud</th>
                <th scope="col"><i class="fa-solid fa-sort fa-lg"></i>Informe resolución</th>
                <th scope="col"><i class="fa-solid fa-sort fa-lg"></i>Recibí</th>
                <th scope="col"><i class="fa-solid fa-ellipsis fa-rotate-90 fa-2xl"></i></th>
                <th scope="col"></th>

            </tr>
            </thead>
            <tbody>
            <%
                for (WorkflowTask workflowTask : listAllWorkflowTask) {
                    if (select.equals(workflowTask.getOptionalAttributes().get("entryType")) || select.equals("todo")) {
            %>
                <tr>
                    <td><%=workflowTask.getWorkflowInstanceId()%></td>
                    <td><%=workflowTask.getUserName()%></td>
                    <td><%=workflowTask.getOptionalAttributes().get("entryType")%></td>
                    <td><fmt:formatDate value="<%=workflowTask.getCreateDate()%>" pattern="dd MMM yyyy"/></td>
                    <td></td>
                    <td></td>
                    <td><liferay-ui:icon-menu
                            direction="left-side"
                            icon="<%= StringPool.BLANK %>"
                            markupView="lexicon"
                            message="<%= StringPool.BLANK %>"
                            showWhenSingleIcon="<%= true %>"
                    >
                        <liferay-ui:icon
                                message="asignar"
                                onClick='<%= "javascript:assignToMe(" + workflowTask.getWorkflowTaskId() + ");" %>'
                                url="javascript:void(0);"
                        />
                        <liferay-ui:icon
                                message="aprobar"
                                url="javascript:void(0);"
                        />
                        <liferay-ui:icon
                                message="rechazar"
                                url="javascript:void(0);"
                        />
                    </liferay-ui:icon-menu></td>
                    <td><i class="fa-solid fa-circle-plus"></i></td>
            <%}}%>
            </tbody>
        </table>
    </div>
</div>
<script>
    function assignToMe(workflowTask) {

        $.ajax({
            type: "POST",
            url: "${assignmentTask}",
            data:{ <portlet:namespace/>workflowTask : workflowTask},
            success: function (response) {
                console.log("Solicitud exitosa");
            },
            error: function (xhr, status, error) {
                console.error("Error en la solicitud: " + error);
            }
        });
    }
</script>
