package es.emasesa.intranet.sap.nomina.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sun.xml.ws.developer.WSBindingProvider;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;

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
import javax.xml.ws.handler.MessageContext;

import com.sap.document.sap.soap.functions.mc_style.*;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component("jornadaNominaService")
public class JornadaNominaService {


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
            ZPeActJornadaNomina zpeStActJornadaNominaParent = getObjectFactory().createZPeActJornadaNomina();
            ZpeStActJornadaNomina zpeStActJornadaNomina = getObjectFactory().createZpeStActJornadaNomina();
            zpeStActJornadaNomina.setPernr(idEmpleado);
            zpeStActJornadaNomina.setHeInicio(getXMLGregorianCalendar(fechaInicio));
            zpeStActJornadaNomina.setHeFin(getXMLGregorianCalendar(fechaFin));
            zpeStActJornadaNomina.setHeTipoRetribucion(tipoRetribucion);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            zpeStActJornadaNomina.setFechaInicio(fechaInicio.format(dtf));
            zpeStActJornadaNominaParent.setTEmpleados(zpeStActJornadaNomina);
            ZPeActJornadaNominaResponse result = getObjectFactory().createZPeActJornadaNominaResponse();

            Bapireturn1 result1 = port.zPeActJornadaNomina(zpeStActJornadaNomina);

            return result1;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            LOG.debug(e.getMessage(),e);
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
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOESTRUCTURA.class.getClassLoader();
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

    private static final Log LOG = LogFactoryUtil.getLog(JornadaNominaService.class);

}
