<#assign customExpando = serviceLocator.findService("es.emasesa.intranet.base.util.CustomExpandoUtil")/>
<#assign sapUtil = serviceLocator.findService("es.emasesa.intranet.service.util.SapServicesUtil")/>
<#assign customDate = serviceLocator.findService("es.emasesa.intranet.base.util.CustomDateUtil")/>

<#assign nextDate = customDate.getDateNextMonth()/>
<#assign userPernr = customExpando.getDataValueByUser(themeDisplay.getUser().getUserId(), themeDisplay.getCompanyId(), "matricula")>
<#assign fechaActual = .now?date />
<#assign primerDiaDelMes = fechaActual?string("yyyy-MM-01") />

<#assign eventosRegister = sapUtil.getCalendarioEventos(userPernr, primerDiaDelMes, nextDate)/>
<#assign eventos = eventosRegister.eventos?eval/>


<div id="ema-calendario"></div>
<div id="ema-calendarioInscripcion"></div>

<script>
	!function(t,e,n,a){"use strict";var s="simpleCalendar",i={months:["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],days:["Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"],displayYear:!0,fixedStartDay:!0,displayEvent:!0,disableEventDetails:!1,disableEmptyDetails:!1,events:[],onInit:function(t){},onMonthChange:function(t,e){},onDateSelect:function(t,e){},onEventSelect:function(){},onEventCreate:function(t){},onDayCreate:function(t,e,n,a){}};function d(e,n){this.element=e,this.settings=t.extend({},i,n),this._defaults=i,this._name=s,this.currentDate=new Date,this.init()}t.extend(d.prototype,{init:function(){var e=t(this.element),n=this.currentDate,a=t('<div class="calendar ema-calendario"></div>'),s=t('<header class="ema-calendario__header"><a class="simple-calendar-btn btn-prev ema-calendario__prev" href="#"></a><h3 class="month ema-calendario__mes"></h3><a class="simple-calendar-btn btn-next ema-calendario__next" href="#"></a></header>');this.updateHeader(n,s),a.append(s),this.buildCalendar(n,a),e.append(a),this.bindEvents(),this.settings.onInit(this)},updateHeader:function(t,e){var n=this.settings.months[t.getMonth()];n+=this.settings.displayYear?' <div class="year ema-calendario__year">'+t.getFullYear():"</div>",e.find(".month").html(n)},buildCalendar:function(e,n){var a=this;n.find("table").remove();var s=t("<table></table>"),i=t("<thead></thead>"),d=t("<tbody></tbody>"),r=e.getFullYear(),h=e.getMonth(),o=new Date(r,h,1),l=new Date(r,h+1,0),c=o.getDay();if(!1!==this.settings.fixedStartDay){for(c=!0===this.settings.fixedStartDay?1:this.settings.fixedStartDay;o.getDay()!==c;)o.setDate(o.getDate()-1);for(;l.getDay()!==(c+6)%7;)l.setDate(l.getDate()+1)}for(var v=c;v<c+7;v++)i.append(t("<th>"+this.settings.days[v%7].substring(0,3)+"</th>"));for(var u=o;u<=l;u.setDate(u.getDate())){var f=t("<tr></tr>");for(v=0;v<7;v++){var g=t('<td><div class="day" data-date="'+u.toISOString()+'">'+u.getDate()+"</div></td>"),D=g.find(".day");u.toDateString()===(new Date).toDateString()&&D.addClass("today"),u.getMonth()!=e.getMonth()&&D.addClass("wrong-month");var p=a.getDateEvents(u);p.length&&a.settings.displayEvent?D.addClass(a.settings.disableEventDetails?"has-event disabled":"has-event"):D.addClass(a.settings.disableEmptyDetails?"disabled":""),D.data("todayEvents",p),this.settings.onDayCreate(D,u.getDate(),h,r),f.append(g),u.setDate(u.getDate()+1)}d.append(f)}s.append(i),s.append(d);var y=t('<div class="event-container ema-calendario__contenedor-evento"><div class="close"></div><div class="event-wrapper"></div></div>');n.append(s),n.append(y)},changeMonth:function(e){this.currentDate.setMonth(this.currentDate.getMonth()+e,1),this.buildCalendar(this.currentDate,t(this.element).find(".calendar")),this.updateHeader(this.currentDate,t(this.element).find(".calendar header")),this.settings.onMonthChange(this.currentDate.getMonth(),this.currentDate.getFullYear())},bindEvents:function(){var e=this;t(e.element).off(),t(e.element).on("click",".btn-prev",(function(t){e.changeMonth(-1),t.preventDefault()})),t(e.element).on("click",".btn-next",(function(t){e.changeMonth(1),t.preventDefault()})),t(e.element).on("click",".day",(function(n){var a=new Date(t(this).data("date")),s=e.getDateEvents(a);t(this).hasClass("disabled")||(e.fillUp(n.pageX,n.pageY),e.displayEvents(s)),e.settings.onDateSelect(a,s)})),t(e.element).on("click",".event-container .close",(function(t){e.empty(t.pageX,t.pageY)}))},displayEvents:function(e){var n=this,a=t(this.element).find(".event-wrapper");e.forEach((function(e){var s=new Date(e.startDate),i=new Date(e.endDate),d=t('<div class="ema-calendario__event event"><div class="ema-calendario__detailsClose hide"></div><div class="ema-calendario__titleEvent event-summary">Título del curso: '+e.summary.substring(7)+'</div> <div class="ema-calendario__dateEvent event-date">Fecha inicio-fin: '+e.propstart+'</div><div class="ema-calendario__tiposFormacion hide">Tipo de formación: '+e.formacion+'</div><div class="ema-calendario__modalidadFormacion hide">Modalidad de la formación: '+e.modalidad+'</div><div class="ema-calendario__buttonRegister event-hour"><button class="btn btn-primary" id="btn-register">Inscribir</button></div></div>');d.data("event",e),d.click(n.settings.onEventSelect),n.settings.onEventCreate(d),a.append(d)}))},addEvent:function(e){this.settings.events=[...this.settings.events,e],this.buildCalendar(this.currentDate,t(this.element).find(".calendar"))},setEvents:function(e){this.settings.events=e,this.buildCalendar(this.currentDate,t(this.element).find(".calendar"))},fillUp:function(e,n){var a=t(this.element),s=a.offset(),i=t('<div class="filler" style=""></div>');i.css("left",e-s.left),i.css("top",n-s.top),a.find(".calendar").append(i),i.animate({width:"300%",height:"300%"},500,(function(){a.find(".event-container").show(),i.hide()}))},empty:function(e,n){var a=t(this.element),s=(a.offset(),a.find(".filler"));s.css("width","300%"),s.css("height","300%"),s.show(),a.find(".event-container").hide().find(".event").remove(),s.animate({width:"0%",height:"0%"},500,(function(){s.remove()}))},getDateEvents:function(t){var e=this;return e.settings.events.filter((function(n){return e.isDayBetween(new Date(t),new Date(n.startDate),new Date(n.endDate))}))},isDayBetween:function(t,e,n){return e.setHours(0,0,0),n.setHours(23,59,59,999),t.setHours(12,0,0),e<=t&&t<=n},formatDateEvent:function(t,e,opt){var n=""; return n += this.settings.days[e.getDay()] + " - " + e.getDate() + " de " + this.settings.months[e.getMonth()] ,opt==="/first/"  && (n += " comienza"), opt==="/final/" && (n += " finaliza"), n += ":" , n }}),t.fn[s]=function(e){return this.each((function(){t.data(this,"plugin_"+s)||t.data(this,"plugin_"+s,new d(this,e))}))}}(jQuery,window,document);
</script>

<script type="text/javascript">

	$(function(){
			 $("#ema-calendario").simpleCalendar({
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
					 {
						 startDate: new Date('${item.begda}').toDateString(),
						 endDate: new Date('${item.endda}').toDateString(),
						 summary: '${item.eventoDesc}',
						 <#if fechaInicio != fechaFin>
						 	propstart: '${fechaInicio} - ${fechaFin}',
						 <#else>
						 	propstart: '${fechaInicio}',
						 </#if>
						 formacion: '${item.convocatoria}',
						 modalidad: '${item.modalidad}',
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
					 añadirCheck(true);
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
								 formacion: '${item.convocatoria}',
								 modalidad: '${item.modalidad}',
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
					 añadirCheck(false);
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
		}else{
			btnPrev.off('click');
			btnNext.click(false);
		}
	}

	$(document).on('click', '.ema-calendario__titleEvent', function(){
		$(this).siblings('.ema-calendario__detailsClose').removeClass('hide').show();
		$(this).siblings('.ema-calendario__tiposFormacion').removeClass('hide');
		$(this).siblings('.ema-calendario__modalidadFormacion').removeClass('hide');
		$(this).siblings('.ema-calendario__documentacion').removeClass('hide');
	})

	$(document).on('click', '.ema-calendario__detailsClose', function(e) {
		e.stopPropagation();
		$('.ema-calendario__event').show();
		$('.ema-calendario__detailsClose').hide();
		$('.ema-calendario__tiposFormacion').addClass('hide')
		$('.ema-calendario__modalidadFormacion').addClass('hide')
		$('.ema-calendario__documentacion').addClass('hide')
	});

	$(document).on('click', '#checkMisCursos, #checkMisCursosCheck', function(e) {
		var checked = $(this).is(':checked');
		var calendar = $('#ema-calendario');
		var calendarInscripcion = $('#ema-calendarioInscripcion');

		if(checked){
			calendar.hide();
			calendarInscripcion.show();
			añadirCheck(false);
		}else{
			calendar.show();
			calendarInscripcion.hide();
			$('#checkMisCursos').prop('checked', false);
		}
	});

	function añadirCheck(valor){

		var emaCalendario = $('#ema-calendario');
		var emaCalendarioInscripcion = $('#ema-calendarioInscripcion');
		var noCkeck = $('<div class="ema-calendario__check" id="mostrarMisCursos"><input type="checkbox" id="checkMisCursos" name="checkMisCursos" value="misCursos"><label for="checkMisCursos">Mostrar solo en los que estoy inscrito</label></div>');
		var check = $('<div class="ema-calendario__check" id="mostrarMisCursosCheck"><input type="checkbox" id="checkMisCursosCheck" name="checkMisCursosCheck" value="misCursosCheck" checked><label for="checkMisCursosCheck">Mostrar solo en los que estoy inscrito</label></div>');

		if (valor) {
			$('#mostrarMisCursos').remove();
			emaCalendario.find('table').after(noCkeck);
		}else if (!valor) {
			$('#mostrarMisCursosCheck').remove();
			emaCalendarioInscripcion.find('table').after(check);
		}
	}
	$(document).ready(function(){
		añadirCheck(true);
	});
</script>
