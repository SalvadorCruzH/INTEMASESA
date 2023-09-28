<#if campoCarSlide.getSiblings()?has_content>
  <div class="ema-carouselSlide__carouselWrapper">
    <div class="ema-carousel-icons">
      <button aria-label="anterior slide" class="prev ema-slide-btn ema-slide-pre">
          <i class="i-icon--white fa-solid fa-angle-left"></i>
      </button>
      <div class="carousel-dots-wrapper"></div>
      <button aria-label="siguiente slide" class="next ema-slide-btn ema-slide-next">
          <i class="i-icon--white fa-solid fa-angle-right"></i>
      </button>
    </div>
    <div class="ema-carouselSlide__carousel">
    <#list campoCarSlide.getSiblings() as cur_campoCarSlide>
      <div class="ema-carouselSlide__carousel-item">
        <div class="row" style="margin-left: -25px; margin-top: -10px; margin-right: -15px; margin-bottom: -10px;">
            <#if (cur_campoCarSlide.imgCar_Slide.getData()?length > 0)>
                <div class="col-md-6">
                    <div class="ema-carouselSlide__bgImg" style="background-image: url('${cur_campoCarSlide.imgCar_Slide.getData()}'); background-size: cover; background-position: center;"></div>
                </div>
                <div class="col-md-6 ema-carouselSlide__col-content">

                    <!-- Título -->
                    <#if (cur_campoCarSlide.titleCar_Slide.getData())??>
                        <h4>${cur_campoCarSlide.titleCar_Slide.getData()}</h4>
                    </#if>

                    <!-- Descripción -->
                    <#if (cur_campoCarSlide.descCar_Slide.getData())??>
                        <p class="ema-carouselSlide__descCarrusel">${cur_campoCarSlide.descCar_Slide.getData()}</p>
                    </#if>

                    <!-- Enlace Btn -->
                    <#if (cur_campoCarSlide.titleLinkCarSlide.getData())?? && cur_campoCarSlide.titleLinkCarSlide.getData() != "">
                        <p>
                            <a class="ema-carouselSlide__link" href="<#if (cur_campoCarSlide.linkCarSlide.getData())??>${cur_campoCarSlide.linkCarSlide.getData()}</#if>" target="_blank">
                                ${cur_campoCarSlide.titleLinkCarSlide.getData()}
                            </a>
                        </p>
                    </#if>

                </div>
            <#else>
                <!-- Si no hay imagen, mostrar solo una columna con el contenido -->
                <div class="ema-carouselSlide__col-content-alone">

                    <!-- Título -->
                    <#if (cur_campoCarSlide.titleCar_Slide.getData())??>
                        <h4>${cur_campoCarSlide.titleCar_Slide.getData()}</h4>
                    </#if>

                    <!-- Descripción -->
                    <#if (cur_campoCarSlide.descCar_Slide.getData())??>
                        <p class="ema-carouselSlide__descCarrusel">${cur_campoCarSlide.descCar_Slide.getData()}</p>
                    </#if>

                    <!-- Enlace Btn -->
                    <#if (cur_campoCarSlide.titleLinkCarSlide.getData())?? && cur_campoCarSlide.titleLinkCarSlide.getData() != "">
                        <p>
                            <a class="ema-carouselSlide__link" href="<#if (cur_campoCarSlide.linkCarSlide.getData())??>${cur_campoCarSlide.linkCarSlide.getData()}</#if>" target="_blank">
                                ${cur_campoCarSlide.titleLinkCarSlide.getData()}
                            </a>
                        </p>
                    </#if>
                </div>
            </#if>
        </div>
      </div>
    </#list>
	</div>
  </div>
</#if>

<script>
    $(document).ready(function() {
        $('.ema-carouselSlide__carousel').slick({
            dots: true,
            arrows: true,
            autoplay: true,
            autoplaySpeed: 5000,
            infinite: true,
            speed: 100,
            fade: true,
            appendDots: '.carousel-dots',
            dotsClass,: 'carousel-dots',
            prevArrow: $('.prev'),
            nextArrow: $('.next'),
            cssEase: 'linear',
        });
    });
</script>