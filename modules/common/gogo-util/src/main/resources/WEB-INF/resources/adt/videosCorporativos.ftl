<#if entries?has_content>
    <div class="ema-videosCorporativos">
        <div class="container">
            <ul class="ema-videosCorporativos__ul">
                <#list entries as curEntry>
                    <#assign journalArticle = curEntry.getAssetRenderer().getAssetObject() />
                    <li class="ema-videosCorporativos__li">
                        <@liferay_journal["journal-article"]
                        articleId=journalArticle.getArticleId()
                        groupId=journalArticle.getGroupId()
                        ddmTemplateKey="EMA-VIDEOSCORPORATIVOS-TEMPLATE"
                        />
                    </li>
                </#list>
            </ul>
        </div>
    </div>
</#if>
<script>
    function toggleVideoControls(videoElement) {
        if (videoElement.paused) {
            videoElement.play();
            videoElement.parentElement.querySelector(".fa-circle-play").style.display = "none";
            videoElement.parentElement.parentElement.querySelector(".ema-videosCorporativos__titleContainer").style.display = "none";
            videoElement.controls = true;
        } else {
            videoElement.pause();
            videoElement.parentElement.querySelector(".fa-circle-play").style.display = "block";
            videoElement.parentElement.parentElement.querySelector(".ema-videosCorporativos__titleContainer").style.display = "flex";
            videoElement.controls = false;
        }
    }

    $(document).on("click", "#${themeDisplay.getPortletDisplay().getId()}_video", function(e) {
        e.preventDefault();
        var videoElement = $(this)[0];
        toggleVideoControls(videoElement);
    });

    $(document).on("click", "#${themeDisplay.getPortletDisplay().getId()}_buttonVideo", function(e) {
        e.preventDefault();
        var videoElement = document.getElementById("${themeDisplay.getPortletDisplay().getId()}_video");
        toggleVideoControls(videoElement);
    });
</script>
