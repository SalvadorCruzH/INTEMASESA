<#if entries?has_content>
    <div class="aplications">
        <div class="modal aplications-modal" id="aplicationsModal" tabindex="-1" aria-labelledby="aplicationsModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="aplications-modal-title" id="exampleModalLabel">Mis aplicaciones</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span class="icon-aplications-modal-close" aria-hidden="true"></span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <ul class="aplications-modal-list">
                            <#list entries as curEntry>

                                <#assign assetRenderer = curEntry.getAssetRenderer() />
                                <#assign journalArticle = assetRenderer.getArticle() />
                                ${request.setAttribute("appGroupId", journalArticle.getGroupId())}
                                <@liferay_journal["journal-article"]
                                articleId=journalArticle.getArticleId()
                                ddmTemplateKey="CE-APPLICATION"<#--Detalle app-->
                                showTitle=false
                                groupId=journalArticle.getGroupId()
                                />

                            </#list>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>

<script>
    $(document).on("click",".${themeDisplay.getPortletDisplay().getId()}_markFav",function(e){
        var element = $(this);
        var journalId = element.attr("data-journalid");
        var groupId = element.attr("data-groupid");
        element.toggleClass("aplication-favorite-icon aplication-unfavorite-icon");
        element.toggleClass("${themeDisplay.getPortletDisplay().getId()}_markFav ${themeDisplay.getPortletDisplay().getId()}_markNoFav");
        sendAppFavorite(journalId,groupId,${themeDisplay.getUserId()},"REMOVE",'p_p_id_com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_yyhl_','');
    });

    $(document).on("click",".${themeDisplay.getPortletDisplay().getId()}_markNoFav",function(e){
        var element = $(this);
        var journalId = $(this).attr("data-journalid");
        var groupId = $(this).attr("data-groupid");
        element.toggleClass("aplication-unfavorite-icon aplication-favorite-icon");
        element.toggleClass("${themeDisplay.getPortletDisplay().getId()}_markNoFav ${themeDisplay.getPortletDisplay().getId()}_markFav");
        sendAppFavorite(journalId,groupId,${themeDisplay.getUserId()},"ADD",'p_p_id_com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_yyhl_','');
    });

</script>