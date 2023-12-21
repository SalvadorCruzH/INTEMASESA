package es.emasesa.intranet.jaxrs.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.webservices.jaxrs.beans.ResponseData;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Set;

@Component(
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/setconfigurationvalue",
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=emasesa.SetConfigurationValue",
                "auth.verifier.guest.allowed=true",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)
public class SetConfigurationValue extends Application {

    @Override
    public Set<Object> getSingletons() {return Collections.<Object>singleton(this);}

    @GET
    @Produces(ContentTypes.APPLICATION_JSON)
    @Path("/set-configuration-value/{value}")
    public Response setStringValue(@DefaultValue(StringPool.BLANK) @PathParam("value") String value){
        Response.ResponseBuilder builder;
        boolean isSet = true;

        try {
            JSONArray jsonArray = JSONFactoryUtil.createJSONArray(value);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Configuration configuration = configurationAdmin.getConfiguration(jsonObject.getString("pid"), null);
                Dictionary<String, Object> properties = configuration.getProperties();

                properties.put(jsonObject.getString("configKey"), jsonObject.getString("value"));
                configuration.update(properties);
            }

            builder = Response.ok(new ResponseData(
                    false,
                    isSet,
                    null,
                    null));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }catch (IOException e) {
            isSet = false;
            LoggerUtil.error(LOG, e);

            builder = Response.ok(new ResponseData(
                    true,
                    isSet,
                    "-1",
                    "Se ha producido un error al actualizar la configuracion"));

        }

        return builder.build();
    }

    @Reference
    private ConfigurationAdmin configurationAdmin;

    private static final Log LOG = LogFactoryUtil.getLog(SetConfigurationValue.class);
}
