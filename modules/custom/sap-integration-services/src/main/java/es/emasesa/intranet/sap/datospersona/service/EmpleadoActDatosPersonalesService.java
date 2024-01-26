package es.emasesa.intranet.sap.datospersona.service;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.*;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.datospersona.exception.EmpleadoActDatosPersonalesException;
import es.emasesa.intranet.sap.datospersona.exception.EmpleadoDatosPersonalesException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import java.math.BigDecimal;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

@org.springframework.stereotype.Component("empleadoActDatosPersonalesService")
public class EmpleadoActDatosPersonalesService {

    public String actEmpleadoDatosPersonales(String pernr, String nombre, String apellido1, String apellido2, String calle, String fechaInicio,
                                             String email, String claseId, String codigoPostal, String estadoCivilId, String nifE, String generoId,
                                             BigDecimal numeroHijos, String fechaNacimiento, String poblacionNacimiento, String provinciaNacimientoId, String nacionalidadId,
                                             String nroSs, String numero, String portal, String pisoLetra, String poblacion, String provinciaId, String telefono) throws EmpleadoActDatosPersonalesException, SapCommunicationException {

        LoggerUtil.debug(LOG, "[B] getEmpleadoDatosPersonales");
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADODATOSPERSONA.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            ZpeStActDatosPersonales actDatos = new ZpeStActDatosPersonales();
            actDatos.setPernr(pernr);
            actDatos.setFechaInicio(fechaInicio);
            actDatos.setNombre(nombre);
            actDatos.setApellido1(apellido1);
            actDatos.setApellido2(apellido2);
            actDatos.setNifE(nifE);
            actDatos.setGeneroId(generoId);
            actDatos.setEstadoCivilId(estadoCivilId);
            actDatos.setNumeroHijos(numeroHijos);
            actDatos.setFechaNacimiento(fechaNacimiento);
            actDatos.setPoblacionNacimiento(poblacionNacimiento);
            actDatos.setProvinciaNacimientoId(provinciaNacimientoId);
            actDatos.setNacionalidadId(nacionalidadId);
            actDatos.setNroSs(nroSs);
            actDatos.setEmail(email);
            actDatos.setClaseId(claseId);
            actDatos.setCalle(calle);
            actDatos.setNumero(numero);
            actDatos.setPortal(portal);
            actDatos.setPisoLetra(pisoLetra);
            actDatos.setCodigoPostal(codigoPostal);
            actDatos.setPoblacion(poblacion);
            actDatos.setProvinciaId(provinciaId);
            actDatos.setTelefono(telefono);
            Bapireturn1 bapireturn1 = port.zPeActDatosPersonales(actDatos);

            return bapireturn1.getId();
        } catch (ServerSOAPFaultException e) {
            throw new EmpleadoActDatosPersonalesException("Error llamando al WS para el pernr "+ pernr, e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            LoggerUtil.debug(LOG, "[E] getEmpleadoDatosPersonales");
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando EmpleadoActDatosPersonalesService");
        }
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();

        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEACTDATOSPERSONALES.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.empleadoActDatosPersonalesEndpoint());
            ZWSPEACTDATOSPERSONALES_Service service = new ZWSPEACTDATOSPERSONALES_Service(urlEndpoint);
            port = service.getPort(ZWSPEACTDATOSPERSONALES.class);

            /*******************UserName & Password ******************************/
            WSBindingProvider bp = ((WSBindingProvider) port);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            /**********************************************************************/
            LOG.info(this.getClass().getName() +" cargado correctamente");
        } catch (ConfigurationException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de EmpleadoActDatosPersonalesService");
            }
        } catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEACTDATOSPERSONALES_Service --> " + configuration.empleadoActDatosPersonalesEndpoint());
            }
        }finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] EmpleadoActDatosPersonalesService");
        }
    }

    protected ZWSPEACTDATOSPERSONALES port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(EmpleadoActDatosPersonalesService.class);

}
