<%@ include file="init.jsp" %>

<h1 class="<%=cssClass%>">
      <c:if test="<%=showBackButton%>">
            <span role="button"
                  tabindex="0"
                  onclick="<%=onclick%>"
                  style="display: inline-block;background: url('/o/emasesa-theme/images/icons/angle-down.svg') no-repeat;width: 30px;height: 30px;vertical-align: inherit;cursor: pointer;"
                  class="emasesa-back-button"
                  aria-label="<liferay-ui:message key="es.emasesa.main.go-back"/>"></span>
      </c:if>
      <span role="text">
            <c:choose>
                  <c:when test="<%=isKey%>">
                        <liferay-ui:message key="<%=title%>"></liferay-ui:message>
                  </c:when>
                  <c:otherwise>
                        <%=title%>
                  </c:otherwise>
            </c:choose>
      </span>
</h1>

<c:if test="<%=showBackButton%>">
      <script>
            var backButtonFeature = (function () {
                  var _goBack = function () {
                        window.history.back();
                  };

                  var _init = function () {
                        if (typeof history == "undefined" || typeof history.length == "undefined" || history.length == 1) {
                              $(".emasesa-back-button").remove();
                        }
                  };

                  return {
                        init: _init,
                        goBack: _goBack
                  };
            })();

            $(function() {
                  backButtonFeature.init();
            });
      </script>
</c:if>