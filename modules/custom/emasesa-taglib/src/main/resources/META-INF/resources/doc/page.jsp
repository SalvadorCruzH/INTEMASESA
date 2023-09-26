<%@ include file="init.jsp" %>



	      <%-- NOT DOWNLOAD, OPEN IT IN A NEW TAB
	      <a href="<%=doc%>" download=""> --%>
	      <a href="<%=doc%>" target="_blank" class="download-file-link">
			<i class="fa-solid fa-file-<%=fileExtension %>"></i>
			<%=fileName%><span class="sr-only"><liferay-ui:message key="es.emasesa.prensa.descarga-tipo"/></span> (<%=fileExtension %>,  <%=size%>)
	      </a>

