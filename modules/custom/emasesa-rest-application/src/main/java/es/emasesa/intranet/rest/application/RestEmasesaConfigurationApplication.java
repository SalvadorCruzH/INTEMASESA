package es.emasesa.intranet.rest.application;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import es.emasesa.intranet.rest.constant.EmasesaRestConstant;
import es.emasesa.intranet.settings.osgi.ClientExtensionsSettings;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;

@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + EmasesaRestConstant.APPLICATION_BASE_URL+EmasesaRestConstant.CONFIGURATION_BASE,
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Configuration.Rest"
	},
	service = Application.class
)
public class RestEmasesaConfigurationApplication extends Application {
	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getConfiguration(@Context HttpServletRequest request) {

		try {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			Method[] classMethods = _clientExtensionsSettings.getClass().getDeclaredMethods();
			for(Method method: classMethods){
				if(!method.getReturnType().getName().equals("void")) {
					try{
						JSONObject jsonObjectRetorno = JSONFactoryUtil.createJSONObject(method.invoke(_clientExtensionsSettings).toString());
						jsonObject.put(method.getName(), jsonObjectRetorno);
					}catch(Exception e){
						jsonObject.put(method.getName(), ""+method.invoke(_clientExtensionsSettings));
					}
				}
			}

			return Response
					.status(Response.Status.OK)
					.entity(jsonObject.toString())
					.build();
		} catch (Exception e) {
			LOG.error("Error obteniendo la configuración inicial", e);
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage())
					.build();
		}
	}

	private static final Log LOG = LogFactoryUtil.getLog(RestEmasesaConfigurationApplication.class);
	@Reference
	protected ClientExtensionsSettings _clientExtensionsSettings;
}