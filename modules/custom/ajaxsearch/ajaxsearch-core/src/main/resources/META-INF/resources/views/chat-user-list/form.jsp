<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--form c-messages-form">
   <input name="<portlet:namespace />queryText"
          id="queryText"
          type="text"
          value="<%=HtmlUtil.escape(ajaxSearchDisplayContext.getQueryText()) %>"
          data-as-id="queryText"
          class="m-searchAjax__input text"
          placeholder='<liferay-ui:message key="search.by.name.email"></liferay-ui:message>'
       />
   <select name="<portlet:namespace />groupSelected"
                   type="text"
                   value='<%=ajaxSearchDisplayContext.getLong("groupSelected") %>'
                   data-as-id="groupSelected"
                   class="m-searchAjax__input select form-control"
                   id="groupSelected"
           >
               <option value="" ${ groupSelected == 0 ? "selected" : ""}>
                   <liferay-ui:message key="group" />
               </option>
               <c:forEach items="${groups}" var="group">
                   <option value="${group.getGroupId()}" ${group.getGroupId() == groupSelected ? "selected" : ""}>
                       ${group.getName()}
                   </option>
               </c:forEach>
           </select>
    <select name="<portlet:namespace />area"
                type="text"
                value='<%=ajaxSearchDisplayContext.getLong("areaSelected") %>'
                data-as-id="areaSelected"
                class="m-searchAjax__input select form-control"
                id="area"
        >
            <option value="" ${ groupSelected == 0 ? "selected" : ""}>
                <liferay-ui:message key="es.camara.intranet.area" />
            </option>
            <c:forEach items="${areas}" var="area">
                <option value="${area.getCategoryId()}" ${area.getCategoryId() == areaSelected ? "selected" : ""}>
                    ${area.getName()}
                </option>
            </c:forEach>
        </select>
    <button type="button"
        class="btn btn-primary"
        aria-label='<liferay-ui:message key="search"></liferay-ui:message>'
        title='<liferay-ui:message key="search"></liferay-ui:message>'
        id="m-searchAjax__button">
        <liferay-ui:message key="search"></liferay-ui:message>
    </button>
</div>


<script type="text/javascript">

    // search
    $("#m-searchAjax__button").on("click", function (e){
        e.preventDefault();
        ajaxSearchFeature.doSearch();
    });

    $("#m-searchAjax__button").on("keypress", function (e){
        if(e.which == 13) {
            event.preventDefault();
            ajaxSearchFeature.doSearch(true, false);
        }
    });

    $("#queryText").on("keypress", function (e){
            if(e.which == 13) {
                event.preventDefault();
                ajaxSearchFeature.doSearch(true, false);
            }
        });

    $("#groupSelected").on("change",function(){

            ajaxSearchFeature.doSearch(true, false);

    });

</script>