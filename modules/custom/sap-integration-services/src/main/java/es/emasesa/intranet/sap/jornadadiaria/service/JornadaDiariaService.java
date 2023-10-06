package es.emasesa.intranet.sap.jornadadiaria.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.sap.document.sap.rfc.functions.TABLEOFZPESTEMPLEADOJORNADADIARIA;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStMarcajesHistoricoActu;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOJornadaDiari;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEMARCAJESHISTORICOACT;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEMARCAJESHISTORICOACT_Service;
import com.sun.xml.ws.developer.WSBindingProvider;
import es.emasesa.intranet.sap.jornadadiaria.exception.MarcajeException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component("jornadaDiaria")
public class JornadaDiariaService {


    public JSONArray obtenerMarcajeHistoricoActual(String pernr, String fechaInicio, String fechaFin) throws MarcajeException {
        JSONArray data = JSONFactoryUtil.createJSONArray();
        try {
            TABLEOFZPESTEMPLEADOJORNADADIARIA response = port.zPeEmpleadoJornadaDiaria(fechaFin,fechaInicio,pernr);
            if(response.getItem().size()>0){

                    data = JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(response.getItem()));

            }

        }catch (JSONException e) {
            LOG.error(e.getMessage());
        }catch (Exception e){
            LOG.debug(e.getMessage(),e);
            throw new MarcajeException("Error con el WS:"+e.getMessage());

        }
        return data;
    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }


    @PostConstruct
    public void activate() throws MalformedURLException {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando EmpleadoEstructuraService");
        }
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            SapServicesConfiguration configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOJornadaDiari.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();
            ZWSPEMARCAJESHISTORICOACT_Service service = new ZWSPEMARCAJESHISTORICOACT_Service();
            port = service.getPort(ZWSPEEMPLEADOJornadaDiari.class);

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });

            /*******************UserName & Password ******************************/
            Map<String, Object> requestContext = ((WSBindingProvider) port).getRequestContext();
            WSBindingProvider bp = ((WSBindingProvider) port);
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, configuration.jornadaDiariaEndpoint());
            Map<String, List<String>> headers = new HashMap<String, List<String>>();
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
            /**********************************************************************/


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

    }

    private ZWSPEEMPLEADOJornadaDiari port;
    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(JornadaDiariaService.class);

}
