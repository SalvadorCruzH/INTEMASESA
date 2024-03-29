<#assign dlFileEntryLocalService = serviceLocator.findService("es.emasesa.intranet.favoritos.service.util.EmasesaFavoritosUtil") />
<#assign ppids = ["p_p_id_com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_nujo_"] />
<div class="ema-color-box ema-color-box--blue ema-favoritos__box">
    <header class="ema-favoritos__header">
        <h3 class="ema-favoritos__title">Mis enlaces</h3>
    </header>
    <div class="ema-favoritos__content">
        
        <#-- Añadir un enlace -->
        <div id="mensaje-error" style="color: red; display: none;"></div>
            <div class="ema-form-row">
                <label for="txt_titulo" id="titulo-ariaLabel">Título <span class="required">*</span></label>
                <input id="txt_titulo" name="txt_titulo" type="text" aria-labelledby="titulo-ariaLabel">
            </div>
            <div class="ema-form-row">
                <label for="enlace" id="enlace-ariaLabel">Enlace <span class="required">*</span></label>
                <input id="enlace" name="enlace" type="url" aria-labelledby="enlace-ariaLabel" placeholder="Debe comenzar por http:// o https://">
            </div>
            <div class="ema-form-row ema-form-row--submit">
                <span class="ema-form-row--submit__item">
                    <input type="reset" aria-label="Borrar formulario" value="Cancelar">
                </span>
                <span class="ema-form-row--submit__item">
                    <i class="fa-solid fa-plus"></i><button onclick="enviarDatos()">Añadir</button>
                </span>
            </div>
            <#-- Añadir un enlace -->
            <#-- Lista de enlaces -->
            <ul class="ema-favoritos__items">
                <#assign enlaces = dlFileEntryLocalService.getEnlacesFavoritosForUser(themeDisplay.getUser())/>
                <#if enlaces?? && enlaces?has_content>
                    <#assign jsonObject = enlaces?eval />				
                    <#list jsonObject as enlace>
                        <li class="ema-favoritos__item">
                            <a href="${enlace.url}" target="_blank" rel="noopener noreferrer" class="ema-favoritos__item__label">${enlace.title}</a>
                            <#-- Editar un enlace 
                            <div class="editable-field-enlaces"> 		
                                <div class="ema-form-row ">
                                    <label for="txt_titulo" id="titulo-ariaLabel">Título</label>
                                    <input id="txt_titulo_edit" name="txt_titulo_edit" type="text" aria-labelledby="titulo-ariaLabel" value="${enlace.title}">
                                </div>
                                <div class="ema-form-row">
                                    <label for="enlace" id="enlace-ariaLabel">Enlace</label>
                                    <input id="enlace_edit" name="enlace_edit" type="url" aria-labelledby="enlace-ariaLabel" value="${enlace.url}">
                                </div>
                            </div> 	
                            <button type="button" onclick="editarEnlace()">Editar</button>
                            Editar un enlace -->
                            <button type="button" onclick="eliminarEnlace(${enlace.id})">
                                <span class="sr-only">Eliminar</span> <i class="fa-regular fa-trash-can"></i>
                            </button>
                        </li>
                    </#list>
                </#if>
            </ul>
        <#-- Lista de enlaces -->
    </div>
</div>
<script>
    function enviarDatos() {
        // Obtener valores de los campos
        var url = $('#enlace').val();
        var nombre = $('#txt_titulo').val();
			  var groupId = Liferay.ThemeDisplay.getScopeGroupId();
			 var errorContainer = document.getElementById('mensaje-error');
			
			// Expresión regular para validar una URL básica
      var urlRegex = /^(https?:\/\/)?([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}([\/?].*)?$/;
			
           // Verificar que ambos campos tengan datos
        if (url && nombre) {
					 if (urlRegex.test(url)) {
				     errorContainer.style.display = 'none'; 
            // Crear objeto de datos para enviar
            var dataFav = {
							  assetEntryId: 0,
							  classNameId:0,
							  groupId: groupId,
                url: url,
							  cmd: 'ADD',
							  fileExtension:'',
                title: nombre
            };

            // Realizar la solicitud AJAX
            $.ajax({
                type: "POST",
                url: '/o/favoritos/saveEnlace',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                async: false,
                data: JSON.stringify(dataFav),
                success: function (data) {
                    if (data != null && data.code == 200) {
                        console.log("Solicitud exitosa");
                       <#list ppids as ppid>
                            Liferay.Portlet.refresh("#${ppid}", {});
                        </#list>
                    }
                },
                error: function (data) {
                    console.error("Error en la solicitud AJAX");
                }
            });
						 } else {
                 errorContainer.innerHTML = 'URL no válida. Debe comenzar por http:// o por https://. Ejemplo: https://www.ejemplo.com';
                 errorContainer.style.display = 'block';
                return false;
             }
        } else {
            console.error("Por favor, complete ambos campos.");
					 errorContainer.innerHTML = 'Por favor, complete ambos campos';
           errorContainer.style.display = 'block';
        }			
    }
	
	function eliminarEnlace(idEnlace) {
		    var groupId = Liferay.ThemeDisplay.getScopeGroupId();
        if (idEnlace) {
						console.log("ELIMINAR: " + idEnlace);
            // Crear objeto de datos para enviar
            var dataFav = {
							  assetEntryId: 0,							  
							  cmd: 'DELETE',
							  idEnlace: idEnlace
            };

            // Realizar la solicitud AJAX
            $.ajax({
                type: "POST",
                url: '/o/favoritos/deleteEnlace',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                async: false,
                data: JSON.stringify(dataFav),
                success: function (data) {
                    if (data != null && data.code == 200) {
                        console.log("Solicitud exitosa");
						 <#list ppids as ppid>

                            Liferay.Portlet.refresh("#${ppid}", {});
                        </#list>
                  
                    }
                },
                error: function (data) {
                    console.error("Error en la solicitud AJAX");
                }
            });
        } else {
            console.error("Por favor, complete ambos campos.");
        }
	}
	
	function editarEnlace(idEnlace) {
		 // Obtener valores de los campos
        var url = $('#enlace_edit').val();
        var nombre = $('#txt_titulo_edit').val();
        var groupId = Liferay.ThemeDisplay.getScopeGroupId();
		   // Crear objeto de datos para enviar
            var dataFav = {
							  assetEntryId: 0,							  
							  cmd: 'EDIT',
							  url: url,
							  title: nombre,
							  idEnlace: idEnlace
            };
		
            // Realizar la solicitud AJAX
            $.ajax({
                type: "POST",
                url: '/o/favoritos/editEnlace',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                async: false,
                data: JSON.stringify(dataFav),
                success: function (data) {
                    if (data != null && data.code == 200) {
                        console.log("Solicitud exitosa");
						 <#list ppids as ppid>

                            Liferay.Portlet.refresh("#${ppid}", {});
                        </#list>
                  
                    }
                },
                error: function (data) {
                    console.error("Error en la solicitud AJAX");
                }
            });
      

}
</script>