<#assign dlFileEntryLocalService = serviceLocator.findService("es.emasesa.intranet.favoritos.service.util.EmasesaFavoritosUtil") />
<#assign ppids = ["p_p_id_com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_rcqt_"] />
	<div class="ema-color-box ema-color-box--blue ema-favoritos__box">
            <header class="ema-favoritos__header">
                <h3 class="ema-favoritos__title">Mis enlaces</h3>
                <button id="mis-enlaces-btn" type="button" class="ema-favoritos__btn btn btn-primary">Guardar</button>
            </header>
            <div class="ema-favoritos__content">
			
			<#-- Añadir un enlace -->
                    <div class="ema-form-row">
                        <label for="txt_titulo" id="titulo-ariaLabel">Título</label>
                        <input id="txt_titulo" name="txt_titulo" type="text" aria-labelledby="titulo-ariaLabel">
                    </div>
                    <div class="ema-form-row">
                        <label for="enlace" id="enlace-ariaLabel">Enlace</label>
                        <input id="enlace" name="enlace" type="url" aria-labelledby="enlace-ariaLabel">
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
					<#assign jsonObject = enlaces?eval />				
					<#list jsonObject as enlace>
						<li class="ema-favoritos__item">
							 <a href="${enlace.url}" target="_blank" rel="noopener noreferrer"><span class="ema-favoritos__item__label">${enlace.title}</span></a>
							 
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

        // Verificar que ambos campos tengan datos
        if (url && nombre) {
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
            console.error("Por favor, complete ambos campos.");
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
        } else {
            console.error("Por favor, complete ambos campos.");
        }
		

}
</script>

<css>
.editable-field-enlaces {
    display: none;
}
</css>