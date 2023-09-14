<#if (boletinTitle.getData())??>
	${boletinTitle.getData()}
</#if>
<br />
<#if (boletinHeaderImage.getData())?? && boletinHeaderImage.getData() != "">
	<img alt="${boletinHeaderImage.getAttribute("alt")}" data-fileentryid="${boletinHeaderImage.getAttribute("fileEntryId")}" src="${boletinHeaderImage.getData()}" />
</#if>
<br />
<#assign boletinFecha_Data = getterUtil.getString(boletinFecha.getData())>

<#if validator.isNotNull(boletinFecha_Data)>
	<#assign boletinFecha_DateObj = dateUtil.parseDate("yyyy-MM-dd", boletinFecha_Data, locale)>

	${dateUtil.getDate(boletinFecha_DateObj, "dd MMM yyyy - HH:mm:ss", locale)}
</#if>
<br />
<#if (boletinIndice.getData())??>
	${boletinIndice.getData()}
</#if>
<br />
<#if boletinNotaPrensa.getSiblings()?has_content>
	<#list boletinNotaPrensa.getSiblings() as cur_boletinNotaPrensa>
		<#assign
			webContentData = jsonFactoryUtil.createJSONObject(cur_boletinNotaPrensa.getData())
		/>

		<#if webContentData?? && webContentData.title??>
			<a href="${cur_boletinNotaPrensa.getFriendlyUrl()}">
				${webContentData.title}
			</a>
		</#if>
	</#list>
</#if>
