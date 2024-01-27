package es.emasesa.intranet.settings.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.SigdServiceConfiguration",
        localization = "content/Language",
        name = "es.emasesa.intranet.settings.configuration.SigdServiceConfiguration"
)
public interface SigdServiceConfiguration {
	
	/*	URLS llamadas a los servicios del SIGD */
	@Meta.AD(
            deflt = "",
            name = "insertarDocumentoEndPoint",
            required = false
    )
    String insertarDocumentoEndPoint();
	
	@Meta.AD(
            deflt = "",
            name = "obtenerElementoEndPoint",
            required = false
    )
    String obtenerElementoEndPoint();
	
	@Meta.AD(
            deflt = "",
            name = "obtenerContenidoEndPoint",
            required = false
    )
    String obtenerContenidoEndPoint();
	
	@Meta.AD(
            deflt = "",
            name = "crearDocumentoOrigenEndPoint",
            required = false
    )
    String crearDocumentoOrigenEndPoint();
	
	/* Credenciales para conectar a los servicios */
	@Meta.AD(
            deflt = "",
            name = "usuario",
            required = false
    )
    String getUser();
	
	@Meta.AD(
            deflt = "",
            name = "contraseña",
            required = false
    )
    String getPassword();
	
	/* Configuraciones de los formularios. Serie, tipo documental y metadatos */
	@Meta.AD(
            deflt = "",
            name = "Usuario indexacion",
            description = "Usuario indexacion. En DES será: P8DesPortal y en PRO será: P8Portal",
            required = false
    )
	String getUsuarioIndexacion();
	
	@Meta.AD(
            deflt = "",
            name = "Nominas",
            description = "Configuraciones del formulario Nominas para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getNominasConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Plan de pensiones",
            description = "Configuraciones del formulario Plan de pensiones para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getPensionesConfiguration();
	@Meta.AD(
			deflt = "",
			name = "Cambio Domicilio",
			description = "Configuraciones del formulario Cambio Domicilio para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
			required = false
	)
	String getCambioDomicilioConfiguration();
	@Meta.AD(
			deflt = "",
			name = "Prestacion",
			description = "Configuraciones del formulario prestaciones para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
			required = false
	)
	String getPensionInvalidezConfiguration();
	@Meta.AD(
			deflt = "",
			name = "Declaracion Voluntates",
			description = "Configuraciones del formulario Declaracion Voluntates para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
			required = false
	)
	String getVoluntadesConfiguration();
	@Meta.AD(
			deflt = "",
			name = "Prestacion",
			description = "Configuraciones del formulario prestaciones para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
			required = false
	)
	String getPrestacionConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Horas extras",
            description = "Configuraciones del formulario Horas extras para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getHorasExtrasConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Vacaciones ausencias y permisos",
            description = "Configuraciones del formulario Vacaciones ausencias y permisos para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getVacacionesConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Registro Mercajes",
            description = "Configuraciones del formulario Registro Mercajes para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getMercajesConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Traslados de puesto",
            description = "Configuraciones del formulario Traslados de puesto para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getTrasladosConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Jubilados parciales",
            description = "Configuraciones del formulario Jubilados parciales para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getJubiladosConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Compatibilidad",
            description = "Configuraciones del formulario Compatibilidad para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getCompatibilidadesConfiguration();

	@Meta.AD(
            deflt = "",
            name = "Flexibilidad horaria",
            description = "Configuraciones del formulario Flexibilidad horaria para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getFlexibilidadHorariaConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Reduccion Jornada",
            description = "Configuraciones del formulario Reduccion Jornada para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getReduccionJornadaConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Licencias sin sueldo",
            description = "Configuraciones del formulario Licencias sin sueldo para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getLicenciasConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Exedencias",
            description = "Configuraciones del formulario Exedencias para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getExedenciasConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Turnos",
            description = "Configuraciones del formulario de Turnos para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getTurnosConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Anticipos vacaciones",
            description = "Configuraciones del formulario de Anticipos vacaciones para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getAnticiposVacacionesConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Anticipos reintegrables",
            description = "Configuraciones del formulario de Anticipos reintegrables para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getAnticiposReintegrablesConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Domiciliación bancaria",
            description = "Configuraciones del formulario de Domiciliación bancaria para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getDomiciliacionBancariaConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Modificacion IRPF",
            description = "Configuraciones del formulario de Modificacion IRPF para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getIRPFConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Prestamos vivienda",
            description = "Configuraciones del formulario de Prestamos vivienda para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getPrestamosViviendaConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Ayuda escolar",
            description = "Configuraciones del formulario de Ayuda escolar para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getAyudaEscolarConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Historial academico",
            description = "Configuraciones del formulario de Historial academico para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getHistorialAcademicoConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Satisfaccion",
            description = "Configuraciones del formulario de Satisfaccion para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getSatisfaccionConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Datos Personales",
            description = "Configuraciones del formulario de Datos Personales para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getDatosPersonalesConfiguration();

	@Meta.AD(
			deflt = "",
			name = "Empleado Act Datos Personales",
			description = "Configuraciones del formulario de Empleado Act Datos Personales para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
			required = false
	)
	String getEmpleadoActDatosPersonalesConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Concursos y procesos",
            description = "Configuraciones del formulario de Concursos y procesos para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getConcursosConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Tribunales",
            description = "Configuraciones del formulario de Tribunales para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getTribunalesConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Sindicales",
            description = "Configuraciones del formulario de Sindicales para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getSindicalesConfiguration();
	
	@Meta.AD(
            deflt = "",
            name = "Formativas",
            description = "Configuraciones del formulario de Formativas para llamadas a los servicios del SIGD. Serie, tipo documental y metadatos",
            required = false
    )
	String getFormativasConfiguration();
}

