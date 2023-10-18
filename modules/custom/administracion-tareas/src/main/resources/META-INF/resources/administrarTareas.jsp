<%@ include file="/META-INF/resources/init.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<WorkflowTask> listWorkFlowTaskRole = (List<WorkflowTask>) request.getAttribute("listWorkFlowTaskRole");
    List<WorkflowTask> listWorkFlowTaskUser = (List<WorkflowTask>) request.getAttribute("listWorkFlowTaskUser");
    List<String> listSelectorOptions = (List<String>) request.getAttribute("listWorkFlowTaskUser");
    long numTaskRole = (listWorkFlowTaskRole != null) ? listWorkFlowTaskRole.size() : 0;
    long numTaskUser = (listWorkFlowTaskUser != null) ? listWorkFlowTaskUser.size() : 0;
    %>

<div class="container">
    <nav class="breadcrumbs" id="breadcrumbs" aria-label="breadcrumbs">
        <ol class="breadcrumbs__items">
            <li class="breadcrumbs__item">
                <a href="/home" title="Home">Home</a>
                <span aria-hidden="true"><i class="fa-solid fa-angle-right"></i></span>
            </li>
            <li class="breadcrumbs__item">
                <a href="/administrar-tareas" title="Parent">Administrar Tareas</a>
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

    <h1 class="page-title">Administrar tareas</h1>
    <div class="widget widget--carrusel widget--tareas-pendientes">
        <div class="widget--tareas-pendientes__info">
            <h3 class="widget-title">Tareas pendientes</h3>
            <div class="widget--tareas-pendientes__asignaciones">
                <div class="widget--tareas-pendientes__asignadas">
                    <span class="widget--tareas-pendientes__number"><%=numTaskUser%></span> <span class="widget--tareas-pendientes__num-label">Asignadas</span>
                </div>
                <div class="widget--tareas-pendientes__sin-asignar">
                    <span class="widget--tareas-pendientes__number"><%=numTaskRole%></span> <span class="widget--tareas-pendientes__num-label">Sin asignar</span>
                </div>
            </div>
        </div>
        <div class="widget--tareas-pendientes__notificaciones">
            <h3 class="carrusel-title">Mis notificaciones</h3>
            <div class="widget--tareas-pendientes__carrusel">
                <div class="widget--tareas-pendientes__carrusel-item">
                    <div class="widget--tareas-pendientes__carrusel-item__inner">
                        <h4 class="widget--tareas-pendientes__title">Nóminas</h4>
                        <p class="notifica-label"><i class="i-icon i-icon--black fa-solid fa-bell"></i> <span class="notifica-num">5</span> Notificaciones nuevas</p>
                        <p><a href="#" title="Cancelar">Cancelar</a></p>
                    </div>
                </div>
                <div class="widget--tareas-pendientes__carrusel-item">
                    <div class="widget--tareas-pendientes__carrusel-item__inner">
                        <h4 class="widget--tareas-pendientes__title">Cambio de turno</h4>
                        <p class="notifica-label"><i class="i-icon i-icon--black fa-solid fa-bell"></i> <span class="notifica-num">5</span> Notificaciones nuevas</p>
                        <p><a href="#" title="Cancelar">Cancelar</a></p>
                    </div>
                </div>
                <div class="widget--tareas-pendientes__carrusel-item">
                    <div class="widget--tareas-pendientes__carrusel-item__inner">
                        <h4 class="widget--tareas-pendientes__title">Vacaciones</h4>
                        <p class="notifica-label"><i class="i-icon i-icon--black fa-solid fa-bell"></i> Sin notificaciones nuevas</p>
                        <p><a href="#" title="Cancelar">Cancelar</a></p>
                    </div>
                </div>
                <div class="widget--tareas-pendientes__carrusel-item">
                    <div class="widget--tareas-pendientes__carrusel-item__inner">
                        <h4 class="widget--tareas-pendientes__title">Nóminas</h4>
                        <p class="notifica-label"><i class="i-icon i-icon--black fa-solid fa-bell"></i> <span class="notifica-num">5</span> Notificaciones nuevas</p>
                        <p><a href="#" title="Cancelar">Cancelar</a></p>
                    </div>
                </div>
                <div class="widget--tareas-pendientes__carrusel-item">
                    <div class="widget--tareas-pendientes__carrusel-item__inner">
                        <h4 class="widget--tareas-pendientes__title">Cambio de turno</h4>
                        <p class="notifica-label"><i class="i-icon i-icon--black fa-solid fa-bell"></i> <span class="notifica-num">5</span> Notificaciones nuevas</p>
                        <p><a href="#" title="Cancelar">Cancelar</a></p>
                    </div>
                </div>
                <div class="widget--tareas-pendientes__carrusel-item">
                    <div class="widget--tareas-pendientes__carrusel-item__inner">
                        <h4 class="widget--tareas-pendientes__title">Vacaciones</h4>
                        <p class="notifica-label"><i class="i-icon i-icon--black fa-solid fa-bell"></i> Sin notificaciones nuevas</p>
                        <p><a href="#" title="Cancelar">Cancelar</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- /.widget--tareas-pendientes -->
</div>

<portlet:actionURL name="/adminTask" var="consultUrl"/>

<section class="white-bg">
    <div class="container">
        <form method="post" id="myForm" action="${consultUrl}">
            <div class="form-row form-row--inline">
                <label for="sel_solicitud" id="seleccione-solicitud" class="sr-only">Seleccione una solicitud</label>
                <select class="selector-select2" id="sel_solicitud" name="<portlet:namespace/>sel_solicitud" aria-labelledby="seleccione-solicitud">
                    <option value="todo" selected>Seleccione una solicitud</option>
                    <c:forEach items="${listSelectorOptions}" var="option">
                        <option value="${option}">${option}</option>
                    </c:forEach>
                </select>
                <div class="btn-wrapper">
                    <input type="submit" value="Consultar" class="btn btn-primary" aria-label="Consultar" />
                </div>
            </div>
        </form>
    </div>
</section>
<!-- /.white-bg -->

<script>
    jQuery(document).ready(function () {
        jQuery('.widget--tareas-pendientes__carrusel').slick({
            dots: false,
            infinite: false,
            speed: 300,
            slidesToShow: 3,
            slidesToScroll: 1,
            responsive: [
                {
                    breakpoint: 768,
                    settings: {
                        slidesToShow: 1
                    }
                },
                {
                    breakpoint: 992,
                    settings: {
                        slidesToShow: 2
                    }
                }
            ]
        })
    })
</script>