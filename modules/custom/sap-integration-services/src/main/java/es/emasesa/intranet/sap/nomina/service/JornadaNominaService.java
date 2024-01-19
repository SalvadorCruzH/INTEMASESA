package es.emasesa.intranet.sap.nomina.service;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.Bapireturn1;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.ZPeActJornadaNomina;
import com.sap.document.sap.soap.functions.mc_style.ZPeActJornadaNominaResponse;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEACTJORNADANOMINA;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEACTJORNADANOMINA_Service;
import com.sap.document.sap.soap.functions.mc_style.ZpeStActJornadaNomina;
import com.sun.xml.ws.developer.WSBindingProvider;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;

import java.math.BigDecimal;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component("jornadaNominaService")
public class JornadaNominaService {

    public String guardarIRPF(String pernr, String fechaInicio, double IRPF_Solicitado){

        try {
            ZpeStActJornadaNomina zpeStActJornadaNomina = getObjectFactory().createZpeStActJornadaNomina();
            zpeStActJornadaNomina.setPernr(pernr);
            zpeStActJornadaNomina.setFechaInicio(fechaInicio);
            zpeStActJornadaNomina.setIrpfSolicitado(BigDecimal.valueOf(IRPF_Solicitado));

            Bapireturn1 datos =  port.zPeActJornadaNomina(zpeStActJornadaNomina);

            return datos.toString();

        } catch (Exception e) {
            LOG.error("Se ha producido un error al intentar acceder a WS de jornadaNomina", e);
        }
        return null;
    }
    public Bapireturn1 peticionHorasExtras(String idEmpleado, String fechaInicio, String fechaFin, String tipoRetribucion) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fechaInicioDateTime = LocalDateTime.parse("2023-10-15 18:00", formatter);
            LocalDateTime fechaFinDateTime = LocalDateTime.parse("2023-10-15 20:00", formatter);

            return peticionHorasExtras(idEmpleado, fechaInicioDateTime, fechaFinDateTime, tipoRetribucion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Bapireturn1();
    }

    public Bapireturn1 peticionHorasExtras(String idEmpleado, LocalDateTime fechaInicio, LocalDateTime fechaFin, String tipoRetribucion) {
        try {
            ZpeStActJornadaNomina zpeStActJornadaNomina = getObjectFactory().createZpeStActJornadaNomina();
            zpeStActJornadaNomina.setPernr(idEmpleado);
            zpeStActJornadaNomina.setHeInicio(getXMLGregorianCalendar(fechaInicio));
            zpeStActJornadaNomina.setHeFin(getXMLGregorianCalendar(fechaFin));
            zpeStActJornadaNomina.setHeTipoRetribucion(tipoRetribucion);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            zpeStActJornadaNomina.setFechaInicio(fechaInicio.format(dtf));

            Bapireturn1 result1 = port.zPeActJornadaNomina(zpeStActJornadaNomina);

            return result1;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            LOG.debug(e.getMessage(),e);
        }

        return null;
    }
    
    public String cambioDomiciliacionBancaria(String pernr, String fechaInicio, String iban){

        try {
            ZpeStActJornadaNomina zpeStActJornadaNomina = getObjectFactory().createZpeStActJornadaNomina();
            zpeStActJornadaNomina.setPernr(pernr);
            zpeStActJornadaNomina.setFechaInicio(fechaInicio);
            zpeStActJornadaNomina.setIban(iban);

            Bapireturn1 datos =  port.zPeActJornadaNomina(zpeStActJornadaNomina);

            return datos.toString();

        } catch (Exception e) {
            LOG.error("Se ha producido un error al intentar acceder a WS de jornadaNomina", e);
        }
        return null;
    }

    public String addPlusSap(String pernr, String fechaInicio, String parte, BigDecimal unidades){
        try {
            ZpeStActJornadaNomina zpeStActJornadaNomina = getObjectFactory().createZpeStActJornadaNomina();
            zpeStActJornadaNomina.setPernr(pernr);
            zpeStActJornadaNomina.setFechaInicio(fechaInicio);
            zpeStActJornadaNomina.setPlusCcnomina(parte);
            zpeStActJornadaNomina.setPlusUnidades(unidades);

            Bapireturn1 datos =  port.zPeActJornadaNomina(zpeStActJornadaNomina);

            return datos.toString();

        } catch (Exception e) {
            LOG.error("Se ha producido un error al intentar acceder a WS de jornadaNomina", e);
        }
        return null;
    }

    public String addMarcaje(String pernr, String fecha, String hora, String motivo){
        try {
            ZpeStActJornadaNomina zpeStActJornadaNomina = getObjectFactory().createZpeStActJornadaNomina();
            zpeStActJornadaNomina.setPernr(pernr);
            zpeStActJornadaNomina.setFechaInicio(fecha);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime horaInicioDateTime = LocalDateTime.parse(fecha + StringPool.SPACE + hora, formatter);

            XMLGregorianCalendar xmlHoraEntrada = getXMLGregorianCalendar(horaInicioDateTime);
            zpeStActJornadaNomina.setMarcajeHora(xmlHoraEntrada);
            if (!motivo.equals(StringPool.BLANK)) {
                zpeStActJornadaNomina.setMarcajeMotivo(motivo);
            }

            Bapireturn1 datos =  port.zPeActJornadaNomina(zpeStActJornadaNomina);

            return datos.toString();

        } catch (Exception e) {
            LOG.error("Se ha producido un error al intentar acceder a WS de jornadaNomina", e);
        }
        return null;
    }

    private XMLGregorianCalendar getXMLGregorianCalendar(LocalDateTime localDateTime) throws DatatypeConfigurationException {
        ZoneId zoneId = ZoneId.systemDefault();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory
                .newInstance()
                .newXMLGregorianCalendar(
                        gregorianCalendar
                );


        return xmlGregorianCalendar;

    }


    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }


    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando JornadaNominaService");
        }
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEACTJORNADANOMINA.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.jornadaNominaEndpoint());
            ZWSPEACTJORNADANOMINA_Service service = new ZWSPEACTJORNADANOMINA_Service(urlEndpoint);
            port = service.getPort(ZWSPEACTJORNADANOMINA.class);

            /*******************UserName & Password ******************************/
            WSBindingProvider bp = ((WSBindingProvider) port);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            /**********************************************************************/
            LOG.info(this.getClass().getName() +" cargado correctamente");
        } catch (ConfigurationException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de JornadaNominaService");
            }
        } catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEACTJORNADANOMINA_Service --> " + configuration.jornadaNominaEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] JornadaNominaService");
        }
    }

    private ZWSPEACTJORNADANOMINA port;
    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(es.emasesa.intranet.sap.nomina.service.JornadaNominaService.class);

}
