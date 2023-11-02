package es.emasesa.intranet.settings.osgi;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import es.emasesa.intranet.settings.configuration.SigdServiceConfiguration;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.SigdServiceConfiguration",
        service = SigdServicesSettings.class
)
public class SigdServicesSettings {
	
	 public String getUser() {
	    return configuration.getUser();
	 }

	public String getPassword() {
	    return configuration.getPassword();
	}
	
	public String insertarDocumentoEndPoint() {
		return configuration.insertarDocumentoEndPoint();
	}
	
	public String obtenerElementoEndPoint(){
		return configuration.obtenerElementoEndPoint();
	}
	
	public String obtenerContenidoEndPoint(){
		return configuration.obtenerContenidoEndPoint();
	}
	
	public String crearDocumentoOrigenEndPoint(){
		return configuration.crearDocumentoOrigenEndPoint();
	}
	
	public String getNominasConfiguration(){
		return configuration.getNominasConfiguration();
	}
	
	public String getPensionesConfiguration(){
		return configuration.getPensionesConfiguration();
	}
	
	public String getHorasExtrasConfiguration(){
		return configuration.getHorasExtrasConfiguration();
	}
	
	public String getVacacionesConfiguration(){
		return configuration.getVacacionesConfiguration();
	}
	
	public String getMercajesConfiguration(){
		return configuration.getMercajesConfiguration();
	}
	
	public String getTrasladosConfiguration(){
		return configuration.getTrasladosConfiguration();
	}
	
	public String getJubiladosConfiguration(){
		return configuration.getJubiladosConfiguration();
	}
	
	public String getCompatibilidadesConfiguration(){
		return configuration.getCompatibilidadesConfiguration();
	}
	
	public String getFlexibilidadHorariaConfiguration(){
		return configuration.getFlexibilidadHorariaConfiguration();
	}
	
	public String getReduccionJornadaConfiguration(){
		return configuration.getReduccionJornadaConfiguration();
	}
	
	public String getLicenciasConfiguration(){
		return configuration.getLicenciasConfiguration();
	}
	
	public String getExedenciasConfiguration(){
		return configuration.getExedenciasConfiguration();
	}
	
	public String getTurnosConfiguration(){
		return configuration.getTurnosConfiguration();
	}
	
	public String getAnticiposVacacionesConfiguration(){
		return configuration.getAnticiposVacacionesConfiguration();
	}
	
	public String getAnticiposReintegrablesConfiguration(){
		return configuration.getAnticiposReintegrablesConfiguration();
	}
	
	public String getDomiciliacionBancariaConfiguration(){
		return configuration.getDomiciliacionBancariaConfiguration();
	}
	
	public String getIRPFConfiguration(){
		return configuration.getIRPFConfiguration();
	}
	
	public String getPrestamosViviendaConfiguration(){
		return configuration.getPrestamosViviendaConfiguration();
	}
	
	public String getAyudaEscolarConfiguration(){
		return configuration.getAyudaEscolarConfiguration();
	}
	
	public String getHistorialAcademicoConfiguration(){
		return configuration.getHistorialAcademicoConfiguration();
	}
	
	public String getSatisfaccionConfiguration(){
		return configuration.getSatisfaccionConfiguration();
	}
	
	public String getDatosPersonalesConfiguration(){
		return configuration.getDatosPersonalesConfiguration();
	}
	
	public String getConcursosConfiguration(){
		return configuration.getConcursosConfiguration();
	}
	
	public String getTribunalesConfiguration(){
		return configuration.getTribunalesConfiguration();
	}
	
	public String getSindicalesConfiguration(){
		return configuration.getSindicalesConfiguration();
	}
	
	public String getFormativasConfiguration(){
		return configuration.getFormativasConfiguration();
	}
	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		configuration = ConfigurableUtil.createConfigurable(SigdServiceConfiguration.class, properties);
	}
	
	@Deactivate
	protected void deactivate() {
	    configuration = null;
	}

	private volatile SigdServiceConfiguration configuration;
}
