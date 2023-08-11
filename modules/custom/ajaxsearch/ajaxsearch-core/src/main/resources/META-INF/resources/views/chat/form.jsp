<%@ include file="init.jsp" %>
<div class="c-messages-left">

    <div class="m-searchAjax m-searchAjax--form c-messages-filters">
        <p class="h5 pt-4 mb-n2"><liferay-ui:message key="es.camara.intranet.chat.search-title"/></p>
        <div class="c-messages-search">
           <input name="<portlet:namespace />queryText"
                  id="queryText"
                  type="text"
                  value="<%=HtmlUtil.escape(ajaxSearchDisplayContext.getQueryText()) %>"
                  data-as-id="queryText"
                  class="m-searchAjax__input text"
                  placeholder='<liferay-ui:message key="es.camara.intranet.chat.searchbox"/>'
               />
        </div>
        <p class="c-messages-filters__text"><liferay-ui:message key="es.camara.intranet.chat.filter-by"/></p>
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
                    <option value="${group.getString("groupId")}" ${group.getLong("groupId") == groupSelected ? "selected" : ""}>
                        ${group.getString("title")}
                    </option>
                </c:forEach>
            </select>

    </div>
</div>

<script type="text/javascript">


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