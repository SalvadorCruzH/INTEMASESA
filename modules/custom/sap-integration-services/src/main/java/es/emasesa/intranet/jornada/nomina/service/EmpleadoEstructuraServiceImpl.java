package es.emasesa.intranet.jornada.nomina.service;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoEstructura;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOESTRUCTURA;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOESTRUCTURA_Service;
import com.sap.document.sap.soap.functions.mc_style.ZpeStEmpleadoEstructura;
import es.emasesa.intranet.jornada.nomina.model.EmpleadoEstructuraService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

@Component(immediate = true, property = {}, service = EmpleadoEstructuraServiceImpl.class)
public class EmpleadoEstructuraServiceImpl {



    public JSONObject getEmpleadoEstructura(String pernr){
        JSONObject jsonResult = JSONFactoryUtil.createJSONObject();
        TableOfZpeStEmpleadoEstructura result =  port.zPeEmpleadoEstructura(pernr);
       ZpeStEmpleadoEstructura empleadoEstructura =  result.getItem().stream().findFirst().orElse(null);

        try {
             jsonResult = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerializeDeep(empleadoEstructura));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonResult;

    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }


    @Activate
    @Modified
    public void activate(BundleContext bundleContext, Map<String, Object> properties) throws MalformedURLException {

        String userName = "pe_hrcons"; //TODO: Poner en settings, estan creadas
        String password = "J2iea.117";//TODO: Poner en settings, estan creadas

        URL url = new URL("http://tc0002.desevilla.org:8010/sap/bc/srt/rfc/sap/z_ws_pe_act_jornada_nomina/010/z_ws_pe_act_jornada_nomina/z_ws_pe_act_jornada_nomina");

        ZWSPEEMPLEADOESTRUCTURA_Service service = new ZWSPEEMPLEADOESTRUCTURA_Service(url);
        port = service.getPort(ZWSPEEMPLEADOESTRUCTURA.class);

        /*******************UserName & Password ******************************/
        Map<String, Object> requestContext = ((BindingProvider)port).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Username", Collections.singletonList(userName));
        headers.put("Password", Collections.singletonList(password));
        requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
        /**********************************************************************/
    }

    protected ZWSPEEMPLEADOESTRUCTURA port;

}
