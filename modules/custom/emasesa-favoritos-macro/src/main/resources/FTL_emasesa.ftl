<#ftl strip_whitespace=true>

<!--- Scripts para usar con los documentos, accesos directos, contenidos favoritos --->

<#macro favoritosInitScript namespace ppids...>
    <script type="text/javascript">

    var sendData =
        (function(dataFav, that){
            $.ajax({
                type: "POST",
                url: '/o/favoritos/save',
                dataType: 'json',
                contentType:"application/json; charset=utf-8",
                async: false,
                data: JSON.stringify(dataFav),
                success: function (data) {
                    if(data != null && data.code == 200) {
                        $(that).toggleClass('favorito-icon  unfavorito-icon');
                         console.log('${ppids?join(",")}');
                        <#list ppids as ppid>

                            Liferay.Portlet.refresh("#${ppid}", {});
                        </#list>
                    }
                },
                error: function (data){
                }
            });
        })
        window['${namespace}'] = {};
        window['${namespace}'].sendData = sendData;

    </script>
</#macro>

<#macro favoritos userId groupId assetEntryId assetEntryClassNameId customNamespace title url fileExtension ddmStructureKey="">
	<#if !emasesaFavoritosService.isFav(assetEntryId?string) >
	        <span class="${customNamespace} unfavorito-icon" data-groupId="${groupId}" data-assetEntryId="${assetEntryId}"
	              data-classNameId="${assetEntryClassNameId}" data-title="${title}" data-fileextension="${fileExtension}"
	              data-url="${url}" data-ddmStructureKey="${ddmStructureKey}"><i class="fa-regular fa-heart"></i></span>
	    <#else>
	        <span class="${customNamespace} favorito-icon" data-groupId="${groupId}" data-assetEntryId="${assetEntryId}"
	              data-classNameId="${assetEntryClassNameId}" data-title="${title}"
	              data-url="${url}" data-ddmStructureKey="${ddmStructureKey}"><i class="fa-solid fa-heart"></i></span>
	   </#if>
</#macro>

<#macro accesosDirectos userId groupId assetEntryId assetEntryClassNameId customNamespace title url fileExtension ddmStructureKey="">
	<#if ddmStructureKey = "EMA-ACCESO-DIRECTO" >
		<#if !emasesaFavoritosService.isFav(assetEntryId?string) >
		 <button type="button">
	        <span class="${customNamespace} unfavorito-icon" data-groupId="${groupId}" data-assetEntryId="${assetEntryId}"
	              data-classNameId="${assetEntryClassNameId}" data-title="${title}" data-fileextension="${fileExtension}"
	              data-url="${url}" data-ddmStructureKey="${ddmStructureKey}"><i class="fa-solid fa-plus"></i></span>
	         </button>
	    <#else>
	     <button type="button">
	        <span class="${customNamespace} favorito-icon" data-groupId="${groupId}" data-assetEntryId="${assetEntryId}"
	              data-classNameId="${assetEntryClassNameId}" data-title="${title}"
	              data-url="${url}" data-ddmStructureKey="${ddmStructureKey}"><i class="fa-solid fa-minus"></i></span>
	      </button>
	    </#if>
	</#if>
</#macro>

<#macro documentosFavoritos userId groupId assetEntryId assetEntryClassNameId customNamespace title url fileExtension ddmStructureKey="">
		<#if !emasesaFavoritosService.isFav(assetEntryId?string) >
	        <span class="${customNamespace} unfavorito-icon" data-groupId="${groupId}" data-assetEntryId="${assetEntryId}"
	              data-classNameId="${assetEntryClassNameId}" data-title="${title}" data-fileextension="${fileExtension}"
	              data-url="${url}" data-ddmStructureKey="${ddmStructureKey}"><i class="fa-regular fa-trash-can"></i></span>
	    <#else>
	        <span class="${customNamespace} favorito-icon" data-groupId="${groupId}" data-assetEntryId="${assetEntryId}"
	              data-classNameId="${assetEntryClassNameId}" data-title="${title}"
	              data-url="${url}" data-ddmStructureKey="${ddmStructureKey}"><i class="fa-regular fa-trash-can"></i></span>
	   </#if>
</#macro>

<#macro favoritosEndScript customNamespace>

    <script type="text/javascript">
        $('.${customNamespace}').click(function (){
            let fav = new Object();
            var classList = $(this).attr('class').split(/\s+/);
            $.each(classList, function(index, item) {
                if (item === 'favorito-icon') {
                    fav.cmd = "DELETE";
                }else if (item === 'unfavorito-icon') {
                    fav.cmd = "ADD";
                }
            });
            fav.assetEntryId  = $(this).data('assetentryid');
            fav.groupId = $(this).data('groupid');
            fav.classNameId = $(this).data('classnameid');
            fav.title = ""+$(this).data('title');
            fav.url = ""+$(this).data('url');
            fav.fileExtension = ""+$(this).data('fileextension');
            fav.ddmStructureKey = ""+$(this).data('ddmstructurekey');
            window['${customNamespace}'].sendData(fav, $(this));
        });

    </script>

</#macro>

<#macro editContent request themeDisplay cont articleId version position renderResponse>

    <#assign
        plid=themeDisplay.getPlid()
        articleId=articleId
        count = cont
        version=version
        redirect=themeDisplay.getURLCurrent()
        positionClass=position
        portletResource=renderResponse.getNamespace()?substring(1,renderResponse.getNamespace()?length-1)
        <#-- siteURL = (themeDisplay.getURLCurrent()?keep_before("/l")!) -->
        siteURL = themeDisplay.getSiteGroupName()
        urlPortal = portal.getControlPanelPortletURL(request, "com_liferay_journal_web_portlet_JournalPortlet", "RENDER_PHASE")
        urlPortal = urlPortal?replace("maximized", "pop_up")
        urlEdit = urlPortal+"&_com_liferay_journal_web_portlet_JournalPortlet_mvcPath=%2Fedit_article.jsp"+
        "&p_p_mode=view&refererPlid="+plid+
        "&_com_liferay_journal_web_portlet_JournalPortlet_articleId="+articleId+
        "&_com_liferay_journal_web_portlet_JournalPortlet_groupId="+groupId+
        "&_com_liferay_journal_web_portlet_JournalPortlet_version="+version+
        "&_com_liferay_journal_web_portlet_JournalPortlet_redirect="+redirect+
        "&_com_liferay_journal_web_portlet_JournalPortlet_referringPortletResource="+portletResource+
        "&_com_liferay_journal_web_portlet_JournalPortlet_portletResource=com_liferay_journal_content_web_portlet_JournalContentPortlet"
    >
    <#if positionClass?has_content>
        <#assign positionClass="float-right">
    </#if>
    <script>
        $(function() {
            var urlEdit = "${urlEdit}";
            $("#editContent-${count}").on("click", function(e){
                Liferay.Util.openWindow({
                    dialog: {
                        destroyOnHide: true,
                        modal: true,
                        after: {
                            render: function(event) {
                                //
                            }
                        }
                    },
                    id: 'EditInfoIntDialog',
                    refreshWindow: window,
                    title: 'Editar',
                    uri: urlEdit
                });
            });
        });
    </script>

    <span class="c-edit-content-pencil ${positionClass}">
        <a href="#" id="editContent-${count}"<@clay.icon symbol="pencil" /></a>
    </span>
</#macro>

<#macro createContent request themeDisplay position renderResponse>
    <#assign JournalFolderLocalService = serviceLocator.findService("com.liferay.journal.service.JournalFolderLocalService") />
    <#assign folder = JournalFolderLocalService.fetchFolder(themeDisplay.getScopeGroupId(),"FAQs") />
    <#assign
        plid=themeDisplay.getPlid()
        groupId = themeDisplay.getScopeGroupId()
        redirect=themeDisplay.getURLCurrent()
        positionClass=position
        portletResource=renderResponse.getNamespace()?substring(1,renderResponse.getNamespace()?length-1)
        urlPortal = portal.getControlPanelPortletURL(request, "com_liferay_journal_web_portlet_JournalPortlet", "RENDER_PHASE")
        urlPortal = urlPortal?replace("maximized", "pop_up")
        urlEdit = urlPortal+"&_com_liferay_journal_web_portlet_JournalPortlet_mvcPath=%2Fedit_article.jsp"+
        "&p_p_mode=view&refererPlid="+plid+
        "&_com_liferay_journal_web_portlet_JournalPortlet_folderId="+folder.getFolderId()+
        "&_com_liferay_journal_web_portlet_JournalPortlet_groupId="+groupId+
        "&_com_liferay_journal_web_portlet_JournalPortlet_ddmStructureId=562032"+
        "&_com_liferay_journal_web_portlet_JournalPortlet_redirect="+redirect
    >

    <#if positionClass?has_content>
        <#assign positionClass="float-right">
    </#if>
    <script>
        $(function() {
            var urlEdit = "${urlEdit}";

            $("#createContent").on("click", function(e){
                Liferay.Util.openWindow({
                    dialog: {
                        destroyOnHide: true,
                        modal: true,
                        after: {
                            render: function(event) {
                                //
                            }
                        }
                    },
                    id: 'CreateInfoIntDialog',
                    refreshWindow: window,
                    title: 'Crear',
                    uri: urlEdit
                });
            });
        });
    </script>

	<button class="add-faq faq-item btn-groups-secondary" data-toggle="modal" data-target="#addFaqModal">
		<a style="text-decoration: none;" href="#" id="createContent"><@liferay_ui.message  key="es.camara.intranet.group.add.faq"/></a>
	</button>
</#macro>


<!--- Scripts para usar con favoritos de portada --->

<#macro favoritosPortadaInitScript namespace ppids...>
    <script type="text/javascript">

    var sendData =
        (function(dataFav, that){
            $.ajax({
                type: "POST",
                url: '/o/favoritos/savePortada',
                dataType: 'json',
                contentType:"application/json; charset=utf-8",
                async: false,
                data: JSON.stringify(dataFav),
                success: function (data) {
                    if(data != null && data.code == 200) {
                        $(that).toggleClass('favoritoPortada-icon  unfavoritoPortada-icon');
                         console.log('${ppids?join(",")}');
                        <#list ppids as ppid>

                            Liferay.Portlet.refresh("#${ppid}", {});
                        </#list>
                    }
                },
                error: function (data){
                }
            });
        })
        window['${namespace}'] = {};
        window['${namespace}'].sendData = sendData;

    </script>
</#macro>

<#macro accesosDirectosPortada userId groupId assetEntryId assetEntryClassNameId customNamespace title url fileExtension ddmStructureKey="">
	<#if ddmStructureKey = "EMA-ACCESO-DIRECTO" >
		<#if !emasesaFavoritosService.isFavPortada(assetEntryId?string) >
		 <button type="button">
	        <span class="${customNamespace} unfavoritoPortada-icon" data-groupId="${groupId}" data-assetEntryId="${assetEntryId}"
	              data-classNameId="${assetEntryClassNameId}" data-title="${title}" data-fileextension="${fileExtension}"
	              data-url="${url}" data-ddmStructureKey="${ddmStructureKey}"><i class="fa-solid fa-plus"></i></span>
	         </button>
	    <#else>
	     <button type="button">
	        <span class="${customNamespace} favoritoPortada-icon" data-groupId="${groupId}" data-assetEntryId="${assetEntryId}"
	              data-classNameId="${assetEntryClassNameId}" data-title="${title}"
	              data-url="${url}" data-ddmStructureKey="${ddmStructureKey}"><i class="fa-solid fa-minus"></i></span>
	      </button>
	    </#if>
	</#if>
</#macro>

<#macro favoritosPortadaEndScript customNamespace>

    <script type="text/javascript">
        $('.${customNamespace}').click(function (){
            let fav = new Object();
            var classList = $(this).attr('class').split(/\s+/);
            $.each(classList, function(index, item) {
                if (item === 'favoritoPortada-icon') {
                    fav.cmd = "DELETE";
                }else if (item === 'unfavoritoPortada-icon') {
                    fav.cmd = "ADD";
                }
            });
            fav.assetEntryId  = $(this).data('assetentryid');
            fav.groupId = $(this).data('groupid');
            fav.classNameId = $(this).data('classnameid');
            fav.title = ""+$(this).data('title');
            fav.url = ""+$(this).data('url');
            fav.fileExtension = ""+$(this).data('fileextension');
            fav.ddmStructureKey = ""+$(this).data('ddmstructurekey');
            window['${customNamespace}'].sendData(fav, $(this));
        });

    </script>

</#macro>

