<#if entries?has_content>
	<div class="ema-newsADT">
        <ul class="carrusel-noticias">
            <#list entries as curEntry>
                <#assign journalArticle = curEntry.getAssetRenderer().getAssetObject() />
                <li class="ema-newsADT__li m-link-accessible-wrapper carrusel-noticias__item">
                    <@liferay_journal["journal-article"]
                        articleId=journalArticle.getArticleId()
                        ddmTemplateKey="EMA-NOTICIA-TARJETA-TEMPLATE"
                        groupId=journalArticle.getGroupId()
                    />
                </li>
            </#list>
        </ul>
	</div>
</#if>

<script>
    $( document ).ready(function() {
        $('.carrusel-noticias').slick({
            infinite: false,
            slidesToShow: 3,
            slidesToScroll: 1,
            arrows: false,
            dots: false,
            autoplay: true,
            autoplaySpeed: 5000
        });
    });
</script>

<style>
    .carrusel-main-container {
        overflow: hidden;
    }

    .carrusel-noticias {
        padding: 0;
    }

    .carrusel-noticias .slick-autoplay-toggle-button {
        display: none;
    }

    .carrusel-noticias .slick-list {
        padding-block: 1rem;
        overflow: visible;
    }

    .carrusel-noticias .slick-slide > div {
        padding: 15px;
    }

    .carrusel-noticias .slick-track {
        display: flex;
    }

    .carrusel-noticias .slick-track .slick-slide {
        height: auto;
    }

    .carrusel-noticias .slick-track .slick-slide > div,
    .carrusel-noticias .slick-track .slick-slide > div > li {
        height: 100%;
    }
</style>
