<#assign dlFileEntryLocalService = serviceLocator.findService("es.emasesa.intranet.favoritos.service.util.EmasesaFavoritosUtil") />
<#assign ppid = "p_p_id_com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_vsxi_">
<div class="ema-color-box ema-color-box--blue ema-favoritos__box">
            <header class="ema-favoritos__header">
                <h3 class="ema-favoritos__title">Mis enlaces</h3>
                <button id="mis-enlaces-btn" type="button" class="ema-favoritos__btn btn btn-primary">Guardar</button>
            </header>
            <div class="ema-favoritos__content">
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
                <ul class="ema-favoritos__items">
									<#assign enlaces = dlFileEntryLocalService.getEnlacesFavoritosForUser(themeDisplay.getUser())/>
                  <#assign jsonObject = enlaces?eval />
									
									
									
                   <#list jsonObject as enlace>
                     <li class="ema-favoritos__item">
                     <a href="${enlace.url}" target="_blank" rel="noopener noreferrer">
                <span class="ema-favoritos__item__label">${enlace.title}</span>
            </a>
                    <button type="button" onclick="editarEnlace(${enlace.id})">
                    <span class="sr-only">Editar</span> <i class="fa-regular fa-pen-to-square"></i>
               </button>
                <button type="button" onclick="eliminarEnlace(${enlace.id})">
                    <span class="sr-only">Eliminar</span> <i class="fa-regular fa-trash-can"></i>
               </button>
               </li>
    </#list>
                </ul>
            </div>
        </div>

<script>
    function enviarDatos() {
        // Obtener valores de los campos
        var url = $('#enlace').val();
        var nombre = $('#txt_titulo').val();
			  var groupId = Liferay.ThemeDisplay.getScopeGroupId();
			  var ppid = 'p_p_id_com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_rcqt_';

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
                        Liferay.Portlet.refresh("#" + ppid, {});
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