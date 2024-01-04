<%@ include file="init.jsp" %>

<liferay-portlet:resourceURL var="getSubCategoriasURL" id="/ajax/get-categorias"/>

<%
    int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    int previousYears = currentYear - 1;
%>

<div class="m-searchAjax m-searchAjax--form ema-ajaxsearch-form">
    <button class="ema-ajaxsearch-form__filterbutton">
        <i class="fa-solid fa-filter fa-lg" aria-hidden="true"></i><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.filter-by"></liferay-ui:message><i class="fa-solid fa-chevron-down fa-2xs" aria-hidden="true"></i>
    </button>
    <div class="ema-ajaxsearch-form__filters" aria-hidden="true" style="display: none;">
        <div class="ema-ajaxsearch-filtros__trainingPlan">
            <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.training-plan"></liferay-ui:message></label><br>
            <select name="<portlet:namespace />trainingPlan" id="trainingPlan" class="m-searchAjax__input select">
                <option value="0"><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.all"></liferay-ui:message></option>
                <option value="1"><%= previousYears - 2 %>-<%= previousYears - 1 %></option>
                <option value="2"><%= previousYears %>-<%= currentYear %></option>
            </select>
        </div>
        <div class="ema-ajaxsearch-filtros__formulated">
            <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.formulated"></liferay-ui:message></label><br>
            <input name="<portlet:namespace />formulated" type="text" id="formulated" value="<%=HtmlUtil.escape(ajaxSearchDisplayContext.getQueryText()) %>"
                   data-as-id="formulated" class="m-searchAjax__input text" placeholder='<liferay-ui:message key="search"></liferay-ui:message>'
            />
        </div>
        <div class="ema-ajaxsearch-filtros__applicant">
            <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.applicant"></liferay-ui:message></label><br>
            <input name="<portlet:namespace />applicant" type="text" id="applicant" value="<%=HtmlUtil.escape(ajaxSearchDisplayContext.getQueryText()) %>"
                   data-as-id="applicant" class="m-searchAjax__input text" placeholder='<liferay-ui:message key="search"></liferay-ui:message>'
            />
        </div>
        <div class="ema-ajaxsearch-filtros__status">
            <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.status"></liferay-ui:message></label><br>
            <select name="<portlet:namespace />status" id="status" class="m-searchAjax__input select">
                <option value="todos"><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.all"></liferay-ui:message></option>
                <option value="pendiente"><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.pendent"></liferay-ui:message></option>
                <option value="convocada"><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.interview-called"></liferay-ui:message></option>
                <option value="autorizada"><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.authorized"></liferay-ui:message></option>
                <option value="rechazada"><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.rejected"></liferay-ui:message></option>
                <option value="aprobada"><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.approved"></liferay-ui:message></option>
            </select>
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