<#if campoCarSlide.getSiblings()?has_content>
  <div class="ema-carouselSlide__carousel">
    <#list campoCarSlide.getSiblings() as cur_campoCarSlide>
      <div class="carousel-item">
				<div class="row" style="margin-left: -25px; margin-top: -10px; margin-right: -15px; margin-bottom: -10px;">
					<#if (cur_campoCarSlide.imgCar_Slide.getData()?length > 0)>
						<div class="col-md-6">
							<div class="bgImg" style="background-image: url('${cur_campoCarSlide.imgCar_Slide.getData()}'); background-size: cover; background-position: center;"></div>
						</div>
						<div class="col-md-6 col-content">

							<!-- Título -->
							<#if (cur_campoCarSlide.titleCar_Slide.getData())??>
								<h4>${cur_campoCarSlide.titleCar_Slide.getData()}</h4>
							</#if>

							<!-- Descripción -->
							<#if (cur_campoCarSlide.descCar_Slide.getData())??>
								<p>${cur_campoCarSlide.descCar_Slide.getData()}</p>
							</#if>

							<!-- Enlace Btn -->
							<#if (cur_campoCarSlide.titleLinkCarSlide.getData())?? && cur_campoCarSlide.titleLinkCarSlide.getData() != "">
								<a class="ema-carouselSlide__link" href="<#if (cur_campoCarSlide.linkCarSlide.getData())??>${cur_campoCarSlide.linkCarSlide.getData()}</#if>" target="_blank">
										<h5>${cur_campoCarSlide.titleLinkCarSlide.getData()}</h5>
								</a>
							</#if>

						</div>
					<#else>
						<!-- Si no hay imagen, mostrar solo una columna con el contenido -->
						<div class="col-content-alone">

							<!-- Título -->
							<#if (cur_campoCarSlide.titleCar_Slide.getData())??>
								<h4>${cur_campoCarSlide.titleCar_Slide.getData()}</h4>
							</#if>

							<!-- Descripción -->
							<#if (cur_campoCarSlide.descCar_Slide.getData())??>
								<p>${cur_campoCarSlide.descCar_Slide.getData()}</p>
							</#if>

							<!-- Enlace Btn -->
							<#if (cur_campoCarSlide.titleLinkCarSlide.getData())?? && cur_campoCarSlide.titleLinkCarSlide.getData() != "">
								<a class="ema-carouselSlide__link" href="<#if (cur_campoCarSlide.linkCarSlide.getData())??>${cur_campoCarSlide.linkCarSlide.getData()}</#if>" target="_blank">
										<h5>${cur_campoCarSlide.titleLinkCarSlide.getData()}</h5>
								</a>
							</#if>

						</div>
					</#if>
				</div>
      </div>
    </#list>
  </div>
</#if>

<style>
.ema-carouselSlide {
  &__carousel {
    width: 100%;
    display: inline-block;
    padding: 0;
    margin: 0;
  }
}

.ema-carouselSlide__carousel {
	width: 100%;
	display: inline-block;
	padding: 0;
	margin: 0;
}

.carousel-item{
	display: flex;
}

.bgImg{
	height: 429px;
}

.col-content-alone {
    text-align-last: center;
    margin-top: 8rem;
    margin-left: 30rem;
    margin-right: 30rem;
}
.col-content {
    margin-left: 3rem;
    margin-top: 2rem;
    align-self: center;
    max-width: 35%;
    white-space-collapse: preserve-breaks;
}
@media screen and (max-width: 768px) {
  .col-content {
		margin-left: 3rem;
    margin-top: 0;
    align-self: center;
    max-width: 80%;
    white-space-collapse: preserve-breaks;
  }
	.bgImg{
		height: 10rem;
	}
	.col-content-alone {
    margin-top: 8rem;
    margin-left: 4rem;
    margin-right: 4rem;
    text-align: center;
	}
}

.ema-carouselSlide__link{
	color: #a8c855;
	border-radius: 90px;
  background-color: black;
	padding: 15px;
}
@media screen and (max-width: 768px) {
 .ema-carouselSlide__link {
    padding: 8px;
	}
}

a:hover, a.hover {
  color: #a8c855;
  text-decoration: underline;
}

a:link{
	text-decoration:none;
}

.slick-slide{
	margin:0;
}

.slick-slide img{
	width:100%;
	border: 2px solid #fff;
}

.slick-slider.slick-dotted {
    margin-bottom: 0;
}

.slick-slider {
    margin-bottom: 0;
}

.slick-dots {
    bottom: 25rem;
    right: 5rem;
    position: relative;
    list-style: none;
    text-align: end;
}
@media screen and (max-width: 768px) {
	.slick-dots {
		bottom: 0.3rem;
		position: relative;
		list-style: none;
		text-align: center;
		right: 0;
	}
}

.slick-autoplay-toggle-button {
	display:none;
}
</style>

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
			});
  });
</script>