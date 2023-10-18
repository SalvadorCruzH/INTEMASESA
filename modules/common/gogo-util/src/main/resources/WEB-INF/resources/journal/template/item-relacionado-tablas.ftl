<table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%">
	<tbody>
		<tr>
			<td style="vertical-align:top;padding:0 7px;">
				<table border="0" cellpadding="0" cellspacing="0" role="presentation"
					style="" width="100%">
					<tbody>
						<tr>
							<td align="center" background="${imagenPrincipal.imgPrincipal.getData()}" height="100"
								style="
									font-size:0px;
									padding:0;
									word-break:break-word;
									background-image: url('${imagenPrincipal.imgPrincipal.getData()}');
									background-position: center center;
									background-size: cover;
								">
							</td>
						</tr>
						<tr>
							<td align="left"
								style="font-size:0px;padding:10px 0;word-break:break-word;">
								<div style="font-family:Roboto, Helvetica, Arial;font-size:16px;font-weight:bold;line-height:1;text-align:left;color:#000000;">
									<#if (title.getData())??>
										<a style="text-decoration:none; color: #0f71ca;" href="${friendlyURL}">${title.getData()}</a>
									</#if>
								</div>
							</td>
						</tr>
						 <tr>
							<td align="left" style="font-size:0px;padding:0;word-break:break-word;">
								<div style="font-family:Roboto, Helvetica, Arial;text-align:left;color:#000000;">
									<#if (entradilla.getData())??>
										${entradilla.getData()?truncate(90, '&hellip;')}
									</#if>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>

