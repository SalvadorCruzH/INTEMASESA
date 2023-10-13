<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--form ema-ajaxsearch-form">
    <button class="ema-ajaxsearch-form__filterbutton">
        <i class="fa-solid fa-filter fa-lg" aria-hidden="true"></i><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.filter-by"></liferay-ui:message><i class="fa-solid fa-chevron-down fa-2xs" aria-hidden="true"></i>
    </button>
    <div class="ema-ajaxsearch-form__filters" aria-hidden="false">
         <div class="ema-ajaxsearch-filtros__dates">
                   <div class="ema-ajaxsearch-filtros__dateFrom">
                       <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.from-date"></liferay-ui:message></label>
                       <input name="<portlet:namespace />fechaDesde"
                               type="date"
                               value='<%=HtmlUtil.escape(ajaxSearchDisplayContext.getStringRP("fechaDesde")) %>'
                               data-as-id="fechaDesde"
                               class="m-searchAjax__input date"
                               id="fechaDesde"
                       />
                   </div>
                   <div class="ema-ajaxsearch-filtros__dateTo">
                       <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.to-date"></liferay-ui:message></label>
                       <input name="<portlet:namespace />fechaHasta"
                               type="date"
                               value='<%=HtmlUtil.escape(ajaxSearchDisplayContext.getStringRP("fechaHasta")) %>'
                               data-as-id="fechaHasta"
                               class="m-searchAjax__input date"
                               id="fechaHasta"
                       />
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
        $('#year').val('');
        $('#queryText').val('');
         ajaxSearchFeature.doSearch();
    });

    $("#m-searchAjax__button").on("keypress", function (e){
        if(e.which == 13) {
            event.preventDefault();
            ajaxSearchFeature.doSearch(true, false);
        }
    });

</script>