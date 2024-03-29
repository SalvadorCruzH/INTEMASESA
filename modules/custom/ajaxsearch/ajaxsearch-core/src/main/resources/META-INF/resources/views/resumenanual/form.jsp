<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--form ema-ajaxsearch-form">
    <button class="ema-ajaxsearch-form__filterbutton">
        <i class="fa-solid fa-filter fa-lg" aria-hidden="true"></i><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.filter-by"></liferay-ui:message><i class="fa-solid fa-chevron-down fa-2xs" aria-hidden="true"></i>
    </button>
    <div class="ema-ajaxsearch-form__filters" aria-hidden="false">
        <c:if test="${role == 'responsable'}">
            <div class="ema-ajaxsearch-filtros__category" id="buscador-subordinado-select">
                <label><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.selectSubordinado"></liferay-ui:message></label>
                <select name="<portlet:namespace />usuario"
                        type="text"
                        value='<%=ajaxSearchDisplayContext.getString("usuarioSelected") %>'
                        data-as-id="usuarioSelected"
                        class="m-searchAjax__input select"
                        id="usuario"
                    >
                    <option value=''>
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.option.selecciona-subordinado"></liferay-ui:message>
                    </option>
                    <c:forEach begin="0" end="${subordinados.length() -1}" var="index">
                            <option value='${subordinados.getJSONObject(index).getString("pernr")}' ${subordinados.getJSONObject(index).getString("pernr") == usuarioSelected ? "selected" : ""}>
                                ${subordinados.getJSONObject(index).getString("pernr")} - ${subordinados.getJSONObject(index).getString("nombre")} ${subordinados.getJSONObject(index).getString("apellido1")} ${subordinados.getJSONObject(index).getString("apellido2")}
                            </option>
                    </c:forEach>

                </select>
            </div>
        </c:if>
        <c:if test="${role == 'administradorRRHH'}">
            <div class="ema-ajaxsearch-filtros__category" id="buscador-usuario-input">
                <label><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.inputPERNR"></liferay-ui:message></label>
                <input  name="<portlet:namespace />usuario"
                        data-as-id="usuarioSelected"
                        class="m-searchAjax__input select"
                        id="usuario"
                        type="text"
                        value='<%=ajaxSearchDisplayContext.getString("usuarioSelected") %>'
                    />
            </div>
        </c:if>

        <div class="ema-ajaxsearch-filtros__category d-none" id="buscador-year-input">
            <div class="ema-ajaxsearch-filtros__category">
                <label><liferay-ui:message key="year"></liferay-ui:message></label>
                <input  name="<portlet:namespace />year"
                    data-as-id="year"
                    class="m-searchAjax__input select"
                    id="year"
                    type="number"
                    min="2000"
                    max="${year}"
                    step="1"
                    value='<%=ajaxSearchDisplayContext.getString("year") %>'
                />
            </div>
        </div>  
        <div class="ema-ajaxsearch-filtros__category" id="buscador-categoria-select">
                <label><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.selectOption"></liferay-ui:message></label>
                <select name="<portlet:namespace />month"
                        type="text"
                        value='<%=ajaxSearchDisplayContext.getString("monthSelected") %>'
                        data-as-id="monthSelected"
                        class="m-searchAjax__input select"
                        id="month"
                    >
                   <option value='resumenanual' ${"resumenanual".equals(monthSelected) ? "selected" : ""}>
                     <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.option.resumenanual"></liferay-ui:message>
                    </option>
                   <option value='resumenanualpasado' ${"resumenanualpasado".equals(monthSelected) ? "selected" : ""}>
                     <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.option.resumenanualpasado"></liferay-ui:message>
                    </option>
                    <c:forEach begin="0" end="${months.length() -1}" var="index">
                           <option value='${months.getJSONObject(index).getString("value")}' ${months.getJSONObject(index).getString("value") == monthSelected ? "selected" : ""}>
                             ${months.getJSONObject(index).getString("label")}
                            </option>
                    </c:forEach>

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
        if($("#month").val() != 'resumenanual' && $("#month").val() != 'resumenanualpasado'){
            window.location.href = "${jornadaDiariaUrl}?usuarioSelected="+$("#usuario").val()+"&monthSelected=" + $("#month").val();
        } else {
            ajaxSearchFeature.doSearch(true, false, true);
        }
    });

    $("#m-searchAjax__clean__button").on("click", function (e){
        $('#year').val('');
        $('#queryText').val('');
         ajaxSearchFeature.doSearch(true, false, true);
    });

    $("#m-searchAjax__button").on("keypress", function (e){
        if(e.which == 13) {
            e.preventDefault();
            if($("#month").val() != 'resumenanual' && $("#month").val() != 'resumenanualpasado'){
                window.location.href = "${jornadaDiariaUrl}?usuarioSelected="+$("#usuario").val()+"&monthSelected=" + $("#month").val();
            } else {
                ajaxSearchFeature.doSearch(true, false, true);
            }
        }
    });

</script>