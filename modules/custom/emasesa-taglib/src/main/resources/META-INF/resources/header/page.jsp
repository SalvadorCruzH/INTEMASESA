<%@ include file="init.jsp" %>
<c:if test="<%=showBackButton%>">
<a class="emasesa-back-button ema-color--blue"
      href="javascript:void(0)"
      onclick="<%=onclick%>"      
      <i class="fa-solid fa-arrow-left i-icon--blue"></i>
      <span>
            <liferay-ui:message key="es.emasesa.liferay.taglib.back-button"/>
      </span>
</a>
</c:if>


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