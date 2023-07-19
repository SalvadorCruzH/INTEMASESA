<#if isNew>
    <h2>Creación de cuenta</h2>
    <p>Se ha creado la cuenta. Puede acceder con la contraseña auto-generada siguiente</p>
    <p><em>${newPwd}</em></p>
    <p>Pulse <a href="${portalUrl}">aquí</a> para acceder a la plataforma. No olvide cambiar la contraseña tras el primer acceso</p>
<#else>
    <h2>Restauración de contraseña</h2>
    <p>Se ha solicitado la restauración de la contraseña de la cuenta. Puede acceder con la contraseña auto-generada siguiente</p>
    <p><em>${newPwd}</em></p>
    <p>Pulse <a href="${portalUrl}">aquí</a> para acceder a la plataforma. No olvide cambiar la contraseña tras el primer acceso</p>
</#if>