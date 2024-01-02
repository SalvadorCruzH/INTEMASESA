<#--  TODO: Revisar este código (hasta línea 47) para ver qué podemos y que no podemos reutilizar  -->
<#--  <#assign dlService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFileEntryLocalService")>
<#assign ddmFieldLocalService = serviceLocator.findService("com.liferay.dynamic.data.mapping.service.DDMFieldLocalService")/>

<#macro createEvento journalArticle>
	<#assign ddmStructure = journalArticle.getDDMStructure() />
	<#assign ddmForm = ddmStructure.getDDMForm()/>
	<#assign ddmFormValues = ddmFieldLocalService.getDDMFormValues(ddmForm, journalArticle.getId()) />
	<#assign ddmFormFieldValues = ddmFormValues.getDDMFormFieldValues() />

	<#assign
		fechaInicio = ""
		fechaFin = ""
		alias = ""
	/>

	<#list ddmFormFieldValues as fieldName>
		<#if fieldName.getFieldReference() == 'fechaInicio'>
			<#assign fechaInicio = fieldName.getValue().getString(locale) />
		</#if>
		<#if fieldName.getFieldReference() == 'fechaFin'>
			<#assign fechaFin = fieldName.getValue().getString(locale) />
		</#if>
		<#if fieldName.getFieldReference() == 'alias'>
			<#assign alias = fieldName.getValue().getString(locale) />
		</#if>
	</#list>

	<#if fechaInicio == fechaFin>
		{
			startDate: new Date('${fechaInicio}').toDateString(),
			endDate: new Date('${fechaFin}').toDateString(),
			summary:'/alone/'+'${alias}'
		},
		<#else>
		{
			startDate: new Date('${fechaInicio}').toDateString(),
			endDate: new Date('${fechaInicio}').toDateString(),
			summary:'/first/'+'${alias}'
		},
		{
			startDate: new Date('${fechaFin}').toDateString(),
			endDate: new Date('${fechaFin}').toDateString(),
			summary:'/final/'+'${alias}'
		},
	</#if>
</#macro>  -->

<script>
	!function(t,e,n,a){"use strict";var s="simpleCalendar",i={months:["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],days:["Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"],displayYear:!0,fixedStartDay:!0,displayEvent:!0,disableEventDetails:!1,disableEmptyDetails:!1,events:[],onInit:function(t){},onMonthChange:function(t,e){},onDateSelect:function(t,e){},onEventSelect:function(){},onEventCreate:function(t){},onDayCreate:function(t,e,n,a){}};function d(e,n){this.element=e,this.settings=t.extend({},i,n),this._defaults=i,this._name=s,this.currentDate=new Date,this.init()}t.extend(d.prototype,{init:function(){var e=t(this.element),n=this.currentDate,a=t('<div class="calendar ema-calendario"></div>'),s=t('<header class="ema-calendario__header"><a class="simple-calendar-btn btn-prev ema-calendario__prev" href="#"></a><h3 class="month ema-calendario__mes"></h3><a class="simple-calendar-btn btn-next ema-calendario__next" href="#"></a></header>');this.updateHeader(n,s),a.append(s),this.buildCalendar(n,a),e.append(a),this.bindEvents(),this.settings.onInit(this)},updateHeader:function(t,e){var n=this.settings.months[t.getMonth()];n+=this.settings.displayYear?' <div class="year ema-calendario__year">'+t.getFullYear():"</div>",e.find(".month").html(n)},buildCalendar:function(e,n){var a=this;n.find("table").remove();var s=t("<table></table>"),i=t("<thead></thead>"),d=t("<tbody></tbody>"),r=e.getFullYear(),h=e.getMonth(),o=new Date(r,h,1),l=new Date(r,h+1,0),c=o.getDay();if(!1!==this.settings.fixedStartDay){for(c=!0===this.settings.fixedStartDay?1:this.settings.fixedStartDay;o.getDay()!==c;)o.setDate(o.getDate()-1);for(;l.getDay()!==(c+6)%7;)l.setDate(l.getDate()+1)}for(var v=c;v<c+7;v++)i.append(t("<th>"+this.settings.days[v%7].substring(0,3)+"</th>"));for(var u=o;u<=l;u.setDate(u.getDate())){var f=t("<tr></tr>");for(v=0;v<7;v++){var g=t('<td><div class="day" data-date="'+u.toISOString()+'">'+u.getDate()+"</div></td>"),D=g.find(".day");u.toDateString()===(new Date).toDateString()&&D.addClass("today"),u.getMonth()!=e.getMonth()&&D.addClass("wrong-month");var p=a.getDateEvents(u);p.length&&a.settings.displayEvent?D.addClass(a.settings.disableEventDetails?"has-event disabled":"has-event"):D.addClass(a.settings.disableEmptyDetails?"disabled":""),D.data("todayEvents",p),this.settings.onDayCreate(D,u.getDate(),h,r),f.append(g),u.setDate(u.getDate()+1)}d.append(f)}s.append(i),s.append(d);var y=t('<div class="event-container ema-calendario__contenedor-evento"><div class="close"></div><div class="event-wrapper"></div></div>');n.append(s),n.append(y)},changeMonth:function(e){this.currentDate.setMonth(this.currentDate.getMonth()+e,1),this.buildCalendar(this.currentDate,t(this.element).find(".calendar")),this.updateHeader(this.currentDate,t(this.element).find(".calendar header")),this.settings.onMonthChange(this.currentDate.getMonth(),this.currentDate.getFullYear())},bindEvents:function(){var e=this;t(e.element).off(),t(e.element).on("click",".btn-prev",(function(t){e.changeMonth(-1),t.preventDefault()})),t(e.element).on("click",".btn-next",(function(t){e.changeMonth(1),t.preventDefault()})),t(e.element).on("click",".day",(function(n){var a=new Date(t(this).data("date")),s=e.getDateEvents(a);t(this).hasClass("disabled")||(e.fillUp(n.pageX,n.pageY),e.displayEvents(s)),e.settings.onDateSelect(a,s)})),t(e.element).on("click",".event-container .close",(function(t){e.empty(t.pageX,t.pageY)}))},displayEvents:function(e){var n=this,a=t(this.element).find(".event-wrapper");e.forEach((function(e){var s=new Date(e.startDate),i=new Date(e.endDate),d=t('<div class="event"> <div class="event-hour">'+s.getHours()+":"+(s.getMinutes()<10?"0":"")+s.getMinutes()+'</div> <div class="event-date">'+n.formatDateEvent(s,i,e.summary.substring(0,7))+'</div> <div class="event-summary">'+e.summary.substring(7)+"</div></div>");d.data("event",e),d.click(n.settings.onEventSelect),n.settings.onEventCreate(d),a.append(d)}))},addEvent:function(e){this.settings.events=[...this.settings.events,e],this.buildCalendar(this.currentDate,t(this.element).find(".calendar"))},setEvents:function(e){this.settings.events=e,this.buildCalendar(this.currentDate,t(this.element).find(".calendar"))},fillUp:function(e,n){var a=t(this.element),s=a.offset(),i=t('<div class="filler" style=""></div>');i.css("left",e-s.left),i.css("top",n-s.top),a.find(".calendar").append(i),i.animate({width:"300%",height:"300%"},500,(function(){a.find(".event-container").show(),i.hide()}))},empty:function(e,n){var a=t(this.element),s=(a.offset(),a.find(".filler"));s.css("width","300%"),s.css("height","300%"),s.show(),a.find(".event-container").hide().find(".event").remove(),s.animate({width:"0%",height:"0%"},500,(function(){s.remove()}))},getDateEvents:function(t){var e=this;return e.settings.events.filter((function(n){return e.isDayBetween(new Date(t),new Date(n.startDate),new Date(n.endDate))}))},isDayBetween:function(t,e,n){return e.setHours(0,0,0),n.setHours(23,59,59,999),t.setHours(12,0,0),e<=t&&t<=n},formatDateEvent:function(t,e,opt){var n=""; return n += this.settings.days[e.getDay()] + " - " + e.getDate() + " de " + this.settings.months[e.getMonth()] ,opt==="/first/"  && (n += " comienza"), opt==="/final/" && (n += " finaliza"), n += ":" , n }}),t.fn[s]=function(e){return this.each((function(){t.data(this,"plugin_"+s)||t.data(this,"plugin_"+s,new d(this,e))}))}}(jQuery,window,document);
</script>

<div id="ema-calendario"></div>

<script type="text/javascript">
	$(function(){

        // TODO: Quitaresta llamada cuando se desarrolle la inclusión de eventos
        $("#ema-calendario").simpleCalendar()

        // TODO: Desarrollar desde aquí la inclusión de los eventos
		/* $("#ema-calendario").simpleCalendar({

			// displays events
			displayEvent:true,
			// event dates
			events: [

				<#if entries?has_content>
					<#list entries as curEntry>
						<#assign renderer = curEntry.getAssetRenderer() className = renderer.getClassName() viewURL = (!stringUtil.equals(assetLinkBehavior, "showFullContent"))?then(assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry, true), assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry))
						/>
						<#if className == "com.liferay.journal.model.JournalArticle">
							<#assign journalArticle = renderer.getArticle() />
							${request.setAttribute("viewURL", viewURL )}
							<@createEvento journalArticle/>
						</#if>
					</#list>
				</#if>
			],
			// disable showing event details
			disableEventDetails:false,
			// disable showing empty date details
			disableEmptyDetails:true
		}) */

	})
</script>
