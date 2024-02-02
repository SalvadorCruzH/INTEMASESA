<#assign customExpando = serviceLocator.findService("es.emasesa.intranet.base.util.CustomExpandoUtil")/>
<#assign sapUtil = serviceLocator.findService("es.emasesa.intranet.service.util.SapServicesUtil")/>
<#assign customDate = serviceLocator.findService("es.emasesa.intranet.base.util.CustomDateUtil")/>
<#assign dlService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFileEntryLocalService")>
<#assign ddmFieldLocalService = serviceLocator.findService("com.liferay.dynamic.data.mapping.service.DDMFieldLocalService")/>
<#assign assetCategoryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetCategoryLocalService") />
<#assign orderByComparatorFactoryUtil = staticUtil["com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil"]>
<#assign settingModule = serviceLocator.findService("es.emasesa.intranet.settings.osgi.GlobalThemeSettings")>
<#assign comparator = orderByComparatorFactoryUtil.create("journalarticle", ["lastPublishDate", "ASC"]) />
<#assign vocabularyId = settingModule.eventosCalendarioCategoryId()/>
<#assign assetCategories = assetCategoryLocalService.getVocabularyCategories(vocabularyId, -1, -1, comparator) />

<#macro createEvento journalArticle categories>
	<#assign ddmStructure = journalArticle.getDDMStructure() />
	<#assign ddmForm = ddmStructure.getDDMForm()/>
	<#assign ddmFormValues = ddmFieldLocalService.getDDMFormValues(ddmForm, journalArticle.getId()) />
	<#assign ddmFormFieldValues = ddmFormValues.getDDMFormFieldValues() />

	<#assign
		fechaInicio = ""
		fechaFin = ""
		titulo = ""
		direccion = ""
		resumen = ""
		urlDocumento = ""
		tipoEvento = ""
		modalidad = ""
		tipoFormacion = ""
	/>
	<#assign urlsDocumentos  = []>

	<#list ddmFormFieldValues as fieldName>
		<#if fieldName.getFieldReference() == 'fechaInicio'>
			<#assign fechaInicio = fieldName.getValue().getString(locale) />
		</#if>
		<#if fieldName.getFieldReference() == 'fechaFin'>
			<#assign fechaFin = fieldName.getValue().getString(locale) />
		</#if>
		<#if fieldName.getFieldReference() == 'tituloEventoLiferay'>
			<#assign titulo = fieldName.getValue().getString(locale) />
		</#if>
		<#if fieldName.getFieldReference() == 'direccionEvento'>
			<#assign direccion = fieldName.getValue().getString(locale) />
		</#if>
		<#if fieldName.getFieldReference() == 'resumenEvento'>
			<#assign resumen = fieldName.getValue().getString(locale) />
		</#if>
		<#if fieldName.getFieldReference() == 'tipoFormacion'>
			<#assign tipoFormacion = fieldName.getValue().getString(locale) />
		</#if>
		<#if fieldName.getFieldReference() == 'modalidadFormacion'>
			<#assign modalidad = fieldName.getValue().getString(locale) />
		</#if>
		<#if fieldName.getFieldReference() == 'documentoAdjunto'>
			<#assign documento = fieldName.getValue().getString(locale)?eval/>
			<#if documento?has_content>
				<#assign urlDocumento = documento.url />
				<#assign urlsDocumentos = urlsDocumentos + [urlDocumento]>
			</#if>
		</#if>
	</#list>
	<#list categories as category>
		<#assign tipoEvento = htmlUtil.escape(category.getTitle(locale))?lower_case?trim/>
	</#list>
	,{
		startDate: new Date('${fechaInicio}').toDateString(),
		endDate: new Date('${fechaFin}').toDateString(),
		summary:'/alone/'+'${titulo}',
		eventoLiferay: "si",
		tipoEvento: '${tipoEvento}',
		direccion: '${direccion}',
		modalidad: '${modalidad}',
		formacion: '${tipoFormacion}',
		<#if resumen?has_content>
			resumen: '${resumen}',
		</#if>
		<#if urlDocumento?has_content>
			documento: '<#list urlsDocumentos as urlDocumento><a href="${urlDocumento}" download>ver documento</a><br></#list>',
		</#if>
		<#if fechaInicio != fechaFin>
			propstart: '${fechaInicio} - ${fechaFin}',
		<#else>
			propstart: '${fechaInicio}',
		</#if>
	},
</#macro>

<#assign nextDate = customDate.getDateNextMonth()/>
<#assign userPernr = customExpando.getDataValueByUser(themeDisplay.getUser().getUserId(), themeDisplay.getCompanyId(), "matricula")>
<#assign fechaActual = .now?date />
<#assign primerDiaDelMes = fechaActual?string("yyyy-MM-01") />
<#assign eventosRegister = sapUtil.getCalendarioEventos(userPernr, primerDiaDelMes, nextDate)/>
<#assign eventos = eventosRegister.eventos?eval/>
<#assign objetivos = eventosRegister.objetivo/>
<#assign contenidos = eventosRegister.contenido/>
<#if eventosRegister.documentos?has_content>
	<#assign documentos = eventosRegister.documentos/>
</#if>

<div id="ema-calendario"><div class="blockCalendar hide"></div></div>
<div id="ema-calendarioInscripcion"><div class="blockCalendar hide"></div></div>

<script>
	!function(t,e,n,a){"use strict";var s="simpleCalendar",i={months:["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],days:["Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"],displayYear:!0,fixedStartDay:!0,displayEvent:!0,disableEventDetails:!1,disableEmptyDetails:!1,events:[],onInit:function(t){},onMonthChange:function(t,e){},onDateSelect:function(t,e){},onEventSelect:function(){},onEventCreate:function(t){},onDayCreate:function(t,e,n,a){}};function d(e,n){this.element=e,this.settings=t.extend({},i,n),this._defaults=i,this._name=s,this.currentDate=new Date,this.init()}t.extend(d.prototype,{init:function(){var e=t(this.element),n=this.currentDate,a=t('<div class="calendar ema-calendario"></div>'),s=t('<header class="ema-calendario__header"><a class="simple-calendar-btn btn-prev ema-calendario__prev" href="#"></a><h3 class="month ema-calendario__mes"></h3><a class="simple-calendar-btn btn-next ema-calendario__next" href="#"></a></header>');this.updateHeader(n,s),a.append(s),this.buildCalendar(n,a),e.append(a),this.bindEvents(),this.settings.onInit(this)},updateHeader:function(t,e){var n=this.settings.months[t.getMonth()];n+=this.settings.displayYear?' <div class="year ema-calendario__year">'+t.getFullYear():"</div>",e.find(".month").html(n)},buildCalendar:function(e,n){var a=this;n.find("table").remove();var s=t("<table></table>"),i=t("<thead></thead>"),d=t("<tbody></tbody>"),r=e.getFullYear(),h=e.getMonth(),o=new Date(r,h,1),l=new Date(r,h+1,0),c=o.getDay();if(!1!==this.settings.fixedStartDay){for(c=!0===this.settings.fixedStartDay?1:this.settings.fixedStartDay;o.getDay()!==c;)o.setDate(o.getDate()-1);for(;l.getDay()!==(c+6)%7;)l.setDate(l.getDate()+1)}for(var v=c;v<c+7;v++)i.append(t("<th>"+this.settings.days[v%7].substring(0,3)+"</th>"));for(var u=o;u<=l;u.setDate(u.getDate())){var f=t("<tr></tr>");for(v=0;v<7;v++){var g=t('<td><div class="day" data-date="'+u.toISOString()+'">'+u.getDate()+"</div></td>"),D=g.find(".day");u.toDateString()===(new Date).toDateString()&&D.addClass("today"),u.getMonth()!=e.getMonth()&&D.addClass("wrong-month");var p=a.getDateEvents(u);p.length && p[0].tipoEvento !== "" ? D.addClass(p[0].tipoEvento) : D.addClass("");p.length&&a.settings.displayEvent?D.addClass(a.settings.disableEventDetails?"has-event disabled":"has-event"):D.addClass(a.settings.disableEmptyDetails?"disabled":""),D.data("todayEvents",p),this.settings.onDayCreate(D,u.getDate(),h,r),f.append(g),u.setDate(u.getDate()+1)}d.append(f)}s.append(i),s.append(d);var y=t('<div class="event-container ema-calendario__contenedor-evento"><div id="closeEvent" class="close"></div><div class="event-wrapper"></div></div>');n.append(s),n.append(y)},changeMonth:function(e){this.currentDate.setMonth(this.currentDate.getMonth()+e,1),this.buildCalendar(this.currentDate,t(this.element).find(".calendar")),this.updateHeader(this.currentDate,t(this.element).find(".calendar header")),this.settings.onMonthChange(this.currentDate.getMonth(),this.currentDate.getFullYear())},bindEvents:function(){var e=this;t(e.element).off(),t(e.element).on("click",".btn-prev",(function(t){e.changeMonth(-1),t.preventDefault()})),t(e.element).on("click",".btn-next",(function(t){e.changeMonth(1),t.preventDefault()})),t(e.element).on("click",".day",(function(n){var a=new Date(t(this).data("date")),s=e.getDateEvents(a);t(this).hasClass("disabled")||(e.fillUp(n.pageX,n.pageY),e.displayEvents(s)),e.settings.onDateSelect(a,s)})),t(e.element).on("click",".event-container .close",(function(t){e.empty(t.pageX,t.pageY)}))},displayEvents:function(e){var n=this,a=t(this.element).find(".event-wrapper");e.forEach((function(e){var s=new Date(e.startDate),i=new Date(e.endDate),d=t('<div class="ema-calendario__event '+e.tipoEvento+' event"><div class="ema-calendario__titleEvent event-summary">Título del curso: <span id="tituloCurso">'+e.summary.substring(7)+'</span></div> <div class="ema-calendario__dateEvent event-date">Fecha inicio-fin: <span id="fechaCurso">'+e.propstart+'</div>'+((e.eventoLiferay === "si") ? ('<div class="ema-calendario__tipo">Tipo de formación: '+e.formacion +'</div><div class="ema-calendario__modalidad">Modalidad: '+e.modalidad +'</div>'+(e.resumen ? '<div class="ema-calendario__duracion">Resumen: '+e.resumen+'</div>' :'')+(e.documento ? e.documento :'')) : '<div class="ema-calendario__duracion">Duración del evento: <span id="duracionEvento">'+e.duracion+'</span>h</div>')+'<div class="ema-calendario__tiposFormacion hide">'+e.formacion+'</div><div class="ema-calendario__modalidadFormacion hide">'+e.modalidad+'</div><div class="hide" id="asisteEmpleado">'+e.asisteElEmpleado+'</div><div class="ema-calendario__buttonRegister event-hour"><input class="eventoId hide" value="'+e.eventoId+'">'+((e.eventoLiferay === "si") ? '<a id="btn-register" class="btn btn-primary btn-enlace" href="'+e.direccion+'" target="_blank">ver detalles</a>' : '<button id="btn-register" class="btn btn-primary btn-register">ver detalles</button>')+'</div></div>');d.data("event",e),d.click(n.settings.onEventSelect),n.settings.onEventCreate(d),a.append(d)}))},addEvent:function(e){this.settings.events=[...this.settings.events,e],this.buildCalendar(this.currentDate,t(this.element).find(".calendar"))},setEvents:function(e){this.settings.events=e,this.buildCalendar(this.currentDate,t(this.element).find(".calendar"))},fillUp:function(e,n){var a=t(this.element),s=a.offset(),i=t('<div class="filler" style=""></div>');i.css("left",e-s.left),i.css("top",n-s.top),a.find(".calendar").append(i),i.animate({width:"300%",height:"300%"},500,(function(){a.find(".event-container").show(),i.hide()}))},empty:function(e,n){var a=t(this.element),s=(a.offset(),a.find(".filler"));s.css("width","300%"),s.css("height","300%"),s.show(),a.find(".event-container").hide().find(".event").remove(),s.animate({width:"0%",height:"0%"},500,(function(){s.remove()}))},getDateEvents:function(t){var e=this;return e.settings.events.filter((function(n){return e.isDayBetween(new Date(t),new Date(n.startDate),new Date(n.endDate))}))},isDayBetween:function(t,e,n){return e.setHours(0,0,0),n.setHours(23,59,59,999),t.setHours(12,0,0),e<=t&&t<=n},formatDateEvent:function(t,e,opt){var n=""; return n += this.settings.days[e.getDay()] + " - " + e.getDate() + " de " + this.settings.months[e.getMonth()] ,opt==="/first/"  && (n += " comienza"), opt==="/final/" && (n += " finaliza"), n += ":" , n }}),t.fn[s]=function(e){return this.each((function(){t.data(this,"plugin_"+s)||t.data(this,"plugin_"+s,new d(this,e))}))}}(jQuery,window,document);
</script>

<script type="text/javascript">

	$(function(){
		$("#ema-calendario").simpleCalendar({
			displayEvent: true,
			events: [
				<#if entries?has_content>
					<#list entries as curEntry>
						<#assign categories = curEntry.getCategories() />
						<#assign renderer = curEntry.getAssetRenderer() className = renderer.getClassName() viewURL = (!stringUtil.equals(assetLinkBehavior, "showFullContent"))?then(assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry, true), assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry))
						/>
						<#if className == "com.liferay.journal.model.JournalArticle">
							<#assign journalArticle = renderer.getArticle() />

							${request.setAttribute("viewURL", viewURL )}
							<@createEvento journalArticle categories/>
						</#if>
					</#list>
				</#if>
				<#list eventos as item>
					<#assign dia=item.begda?substring(8, 10)>
					<#assign diaFin=item.endda?substring(8, 10)>
					<#assign mes=item.begda?substring(5, 7)>
					<#assign mesFin=item.endda?substring(5, 7)>
					<#assign year=item.begda?substring(2, 4)>
					<#assign yearFin=item.endda?substring(2, 4)>
					<#assign fechaInicio = dia+"/"+mes+"/"+year/>
					<#assign fechaFin = diaFin+"/"+mesFin+"/"+yearFin/>
					{
						startDate: new Date('${item.begda}').toDateString(),
						endDate: new Date('${item.endda}').toDateString(),
						summary: '${item.eventoDesc}',
						<#if fechaInicio != fechaFin>
							propstart: '${fechaInicio} - ${fechaFin}',
						<#else>
							propstart: '${fechaInicio}',
						</#if>
						duracion: '${item.duracion}',
						formacion: '${item.convocatoria}',
						modalidad: '${item.modalidad}',
						eventoId: '${item.eventoId}',
						<#if item.asisteElEmpleado != "">
							asisteElEmpleado: '${item.asisteElEmpleado}',
						</#if>

					},
				</#list>
			],
			disableEventDetails: false,
			disableEmptyDetails: true,
			onInit: function (calendar) {
				comparacionMeses(calendar.currentDate.getMonth());
			},
			onMonthChange: function (month, year) {
				comparacionMeses(month);
				addCheck(true);
			},
			onDateSelect: function(date, events) {
			}
		});

		$("#ema-calendarioInscripcion").simpleCalendar({
			displayEvent: true,
			events: [
				<#list eventos as item>
				<#assign dia=item.begda?substring(8, 10)>
				<#assign diaFin=item.endda?substring(8, 10)>
				<#assign mes=item.begda?substring(5, 7)>
				<#assign mesFin=item.endda?substring(5, 7)>
				<#assign year=item.begda?substring(2, 4)>
				<#assign yearFin=item.endda?substring(2, 4)>
				<#assign fechaInicio = dia+"/"+mes+"/"+year/>
				<#assign fechaFin = diaFin+"/"+mesFin+"/"+yearFin/>
				<#if item.asisteElEmpleado != "">
					{
						startDate: new Date('${item.begda}').toDateString(),
						endDate: new Date('${item.endda}').toDateString(),
						summary: '${item.eventoDesc}',
						<#if fechaInicio != fechaFin>
							propstart: '${fechaInicio} - ${fechaFin}',
						<#else>
							propstart: '${fechaInicio}',
						</#if>
						duracion: '${item.duracion}',
						formacion: '${item.convocatoria}',
						modalidad: '${item.modalidad}',
						asisteElEmpleado: '${item.asisteElEmpleado}',
						eventoId: '${item.eventoId}',
					},
				</#if>
				</#list>
			],
			disableEventDetails: false,
			disableEmptyDetails: true,
			onInit: function (calendar) {
				comparacionMeses(calendar.currentDate.getMonth());
			},
			onMonthChange: function (month, year) {
				comparacionMeses(month)
				addCheck(false);
			},
			onDateSelect: function(date, events) {
			}
		});
		$('#ema-calendarioInscripcion').hide();
	});

	function comparacionMeses(calendar){
		var dateActual = new Date();
		var btnPrev = $('.simple-calendar-btn.btn-prev.ema-calendario__prev');
		var btnNext = $('.simple-calendar-btn.btn-next.ema-calendario__next');

		if(dateActual.getMonth() === calendar) {
			btnPrev.click(false);
			btnNext.off('click');
		} else {
			btnPrev.off('click');
			btnNext.click(false);
		}
	}

	$(document).on('click', '.btn-register', function(){
		$('.ema-formulario').removeClass('hide').show();
		$('html, body').animate({
			scrollTop: $('.ema-formulario').offset().top - 200
		}, 'slow');
		verDetalles($(this).siblings('.eventoId').val());
	})

	$(document).on('click', '#checkMisCursos, #checkMisCursosCheck', function(e) {
		var checked = $(this).is(':checked');
		var calendar = $('#ema-calendario');
		var calendarInscripcion = $('#ema-calendarioInscripcion');

		if(checked){
			calendar.hide();
			calendarInscripcion.show();
			addCheck(false);
			comparacionMeses(convertMonths[calendarInscripcion.find('.month.ema-calendario__mes').text().split(' ')[0].trim()]);
		} else {
			calendar.show();
			calendarInscripcion.hide();
			$('#checkMisCursos').prop('checked', false);
			comparacionMeses(convertMonths[calendar.find('.month.ema-calendario__mes').text().split(' ')[0].trim()]);
		}
	});

	$(document).on('click', '#closeEvent', function(e) {
		e.stopPropagation();
		$('#asistenciaEmpleado').addClass('hide');
		$('.ema-formulario').hide();
	});

	function addCheck(valor){
		var emaCalendario = $('#ema-calendario');
		var emaCalendarioInscripcion = $('#ema-calendarioInscripcion');
		var noCkeck = $('<div class="ema-calendario__check" id="mostrarMisCursos"><input type="checkbox" id="checkMisCursos" name="checkMisCursos" value="misCursos"><label for="checkMisCursos">Mostrar solo en los que estoy inscrito</label></div>');
		var check = $('<div class="ema-calendario__check" id="mostrarMisCursosCheck"><input type="checkbox" id="checkMisCursosCheck" name="checkMisCursosCheck" value="misCursosCheck" checked><label for="checkMisCursosCheck">Mostrar solo en los que estoy inscrito</label></div>');

		if (valor) {
			$('#mostrarMisCursos').remove();
			emaCalendario.find('table').after(noCkeck);
		} else if (!valor) {
			$('#mostrarMisCursosCheck').remove();
			emaCalendarioInscripcion.find('table').after(check);
		}
	}
	$(document).ready(function(){
		addCheck(true);
	});
	const convertMonths = {
		'Enero': 0,
		'Febrero': 1,
		'Marzo': 2,
		'Abril': 3,
		'Mayo': 4,
		'Junio': 5,
		'Julio': 6,
		'Agosto': 7,
		'Septiembre': 8,
		'Octubre': 9,
		'Noviembre': 10,
		'Diciembre': 11
	};

	function verDetalles(eventoId){
		$('[name="idDelEvento"]').val(eventoId);
		var contenidos = ${contenidos?string};
		var objetivos = ${objetivos};
		var literalesporId = {};
		var objetivosId = {};

		contenidos.forEach(function(item){
			if (!literalesporId[item.eventoId]){
				literalesporId[item.eventoId] = [];
			}
			literalesporId[item.eventoId].push(item.literal);
		});

		objetivos.forEach(function(item){
			if (!objetivosId[item.eventoId]){
				objetivosId[item.eventoId] = [];
			}
			objetivosId[item.eventoId].push(item.literal);
		});

		$('#objetivoCursos').empty();
		$('#contenidoCursos').empty();

		if (literalesporId[eventoId]) {
			let parrafo = document.createElement('p');
			parrafo.innerHTML  = literalesporId[eventoId].join('<br>');

			$('#contenidoCursos').append(parrafo);
		}

		if (objetivosId[eventoId]) {
			let parrafo = document.createElement('p');
			parrafo.innerHTML  = objetivosId[eventoId].join('<br>');

			$('#objetivoCursos').append(parrafo);
		}
		<#if documentos?has_content>
			var documentos = ${documentos};
			var documentosId = {};
			documentos.forEach(function(item){
				if (!documentosId[item.eventoId]){
					documentosId[item.eventoId] = [];
				}
				documentosId[item.eventoId].push(item.literal);
			});

			if(documentosId[eventoId]) {
				let enlace = document.createElement('a');
				enlace.href = documentosId[eventoId];

				enlace.innerHTML = documentosId[eventoId].join('<br>');
				$('#objetivoCurso').append(enlace);
			}
		</#if>
	}

	// Leyenda de colores
	document.addEventListener('DOMContentLoaded', () => {
		setTimeout(() => {

			if (document.getElementById('ema-calendario') && document.getElementById('ema-calendarioInscripcion')) {
				const calendario = document.getElementById('ema-calendario')
				const calendarioIns = document.getElementById('ema-calendarioInscripcion')

				let leyenda = document.createElement('div')
				leyenda.classList.add('ema-calendario__leyenda')

				leyenda.innerHTML = `
                    <div class="ema-calendario__leyenda__header">
                        <h4 class="ema-calendario__leyenda__title">Leyenda</h4>
                        <ul class="ema-calendario__leyenda__items">
							<#list assetCategories as assetCategory>
                            	<li class="ema-calendario__leyenda__item ema-calendario__leyenda__item--${assetCategory.getName()}">${assetCategory.getName()}</li>
							</#list>
                        </ul>
                    </div>
                `
				calendario.appendChild(leyenda)

				let leyendaIns = document.createElement('div')
				leyendaIns.classList.add('ema-calendario__leyenda')

				calendarioIns.appendChild(leyendaIns)
				leyendaIns.innerHTML = `
                    <div class="ema-calendario__leyenda__header">
                        <h4 class="ema-calendario__leyenda__title">Leyenda</h4>
                        <ul class="ema-calendario__leyenda__items">
							<#list assetCategories as assetCategory>
                            	<li class="ema-calendario__leyenda__item ema-calendario__leyenda__item--${assetCategory.getName()}">${assetCategory.getName()}</li>
							</#list>
                        </ul>
                    </div>
                `
			}

		}, '1250')
	})
</script>
