<%@ include file="init.jsp" %>

<liferay-portlet:resourceURL var="getSubCategoriasURL" id="/ajax/get-categorias"/>

<div class="m-searchAjax m-searchAjax--form ema-ajaxsearch-form">
    <button class="ema-ajaxsearch-form__filterbutton">
        <i class="fa-solid fa-filter fa-lg" aria-hidden="true"></i><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.filter-by"></liferay-ui:message><i class="fa-solid fa-chevron-down fa-2xs" aria-hidden="true"></i>
    </button>
    <div class="ema-ajaxsearch-form__filters" aria-hidden="true" style="display: none;">
        <div class="ema-ajaxsearch-filtros__text ema-ajaxsearch-filtros__multiple">
            <div class="m-searchAjax__block_date">
                <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.fecha-peticion-desde"></liferay-ui:message></label>
                <input name="<portlet:namespace /><%=AjaxSearchPortletKeys.FECHA_DESDE%>"
                       type="date"
                       id="<%=AjaxSearchPortletKeys.FECHA_DESDE%>"
                       data-as-id="<%=AjaxSearchPortletKeys.FECHA_DESDE%>"
                       class="m-searchAjax__input date"
                />
            </div>
             <div class="m-searchAjax__block_date">
                <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.fecha-peticion-hasta"></liferay-ui:message></label>
                <input name="<portlet:namespace /><%=AjaxSearchPortletKeys.FECHA_HASTA%>"
                       type="date"
                       id="<%=AjaxSearchPortletKeys.FECHA_HASTA%>"
                       data-as-id="<%=AjaxSearchPortletKeys.FECHA_HASTA%>"
                       class="m-searchAjax__input date"
                />
            </div>
            <div>
                <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.matricula"></liferay-ui:message></label>
                <input name="<portlet:namespace /><%=AjaxSearchPortletKeys.MATRICULA%>"
                       type="text"
                       id="<%=AjaxSearchPortletKeys.MATRICULA%>"
                       data-as-id="<%=AjaxSearchPortletKeys.MATRICULA%>"
                       class="m-searchAjax__input text"
                       placeholder='<liferay-ui:message key="search"></liferay-ui:message>'
                />
            </div>
            <div>
                <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.nombre"></liferay-ui:message></label>
                <input name="<portlet:namespace /><%=AjaxSearchPortletKeys.NOMBRE%>"
                       type="text"
                       id="<%=AjaxSearchPortletKeys.NOMBRE%>"
                       data-as-id="<%=AjaxSearchPortletKeys.NOMBRE%>"
                       class="m-searchAjax__input text"
                       placeholder='<liferay-ui:message key="search"></liferay-ui:message>'
                />
            </div>
            <div class="m-searchAjax__block_select" >
                <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.estado"></liferay-ui:message></label>
                <select multiple name="<portlet:namespace /><%=AjaxSearchPortletKeys.ESTADO%>"
                       id="<%=AjaxSearchPortletKeys.ESTADO%>"
                       data-as-id="<%=AjaxSearchPortletKeys.ESTADO%>"
                       class="m-searchAjax__input select"
                >
                    <option value="<%=AjaxSearchPortletKeys.PENDIENTE_JEFE_DIVISION%>"><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.accionformativafueraplan.option.pendientejefedivision"></liferay-ui:message></option>
                    <option value="<%=AjaxSearchPortletKeys.PENDIENTE_JEFE_DEPARTAMENTO%>"><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.accionformativafueraplan.option.pendientejefedepartamento"></liferay-ui:message></option>
                    <option value="<%=AjaxSearchPortletKeys.PENDIENTE_SUBDIRECTOR_DIRECTOR_CONSEJERO%>"><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.accionformativafueraplan.option.pendienteseubdirectordirectorconsejero"></liferay-ui:message></option>
                    <option value="<%=AjaxSearchPortletKeys.APROBADO%>"><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.accionformativafueraplan.option.aprobado"></liferay-ui:message></option>
                    <option value="<%=AjaxSearchPortletKeys.RECHAZADO%>"><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.accionformativafueraplan.option.rechazado"></liferay-ui:message></option>
                    <option value="<%=AjaxSearchPortletKeys.LEIDO%>"><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.accionformativafueraplan.option.leido"></liferay-ui:message></option>
                </select>
            </div>
        </div>
        <div class="ema-ajaxsearch-filtros__buttons">
                <button type="button"
                        class="btn btn-primary search"
                        aria-label='<liferay-ui:message key="search"></liferay-ui:message>'
                        title='<liferay-ui:message key="search"></liferay-ui:message>'
                        id="m-searchAjax__button">
                        <i class="fa-solid fa-magnifying-glass fa-xl" aria-hidden="true"></i>
                </button>
                <button type="button"
                        class="btn btn-secondary clear"
                        aria-label='<liferay-ui:message key="clear"></liferay-ui:message>'
                        title='<liferay-ui:message key="clear"></liferay-ui:message>'
                        id="m-searchAjax__clean__button">
                    <liferay-ui:message key="clear"></liferay-ui:message>
                </button>
            </div>
    </div>
    <div class="ema-ajaxsearch-filtros__sortby--wrapper">
        <div class="ema-ajaxsearch-filtros__sortby">
            <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.sort-by"></liferay-ui:message></label>
            <select name="<portlet:namespace />sortby"
                    type="text"
                    value='<%=ajaxSearchDisplayContext.getLong("sortby") %>'
                    data-as-id="sortby"
                    class="m-searchAjax__input select"
                    id="sortby"
                >
                    <option value="name-asc" >
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.sort.name-asc" />
                    </option>
                    <option value="name-desc" >
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.sort.name-desc" />
                    </option>
                    <option value="date-asc" >
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.sort.date-asc" />
                    </option>
                    <option value="date-desc" >
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.sort.date-desc" />
                    </option>
            </select>
        </div>
    </div>
</div>


<script type="text/javascript">
    //
    window.getCurrentBaseURL = function(){
        let currentURL = window.location.href;
        if (currentURL.indexOf('?') > 0) {
            return currentURL.split('?')[0];
        }
        return currentURL;
    };
    // search
    $("#m-searchAjax__button").on("click", function (e){
        e.preventDefault();
        ajaxSearchFeature.doSearch();
    });

    $("#m-searchAjax__clean__button").on("click", function (e){
        $('#queryText').val('');
         ajaxSearchFeature.doSearch();
    });

    $("#m-searchAjax__button").on("keypress", function (e){
        if(e.which == 13) {
            event.preventDefault();
            ajaxSearchFeature.doSearch(true, false);
        }
    });

    $('select#sortby').on('change', function() {
        ajaxSearchFeature.doSearch(true, false);
    });
</script>