package es.emasesa.intranet.rest.application;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import es.emasesa.intranet.base.constant.StringConstants;
import es.emasesa.intranet.rest.constant.EmasesaRestConstant;
import es.emasesa.intranet.service.util.SapServicesUtil;
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
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + EmasesaRestConstant.APPLICATION_BASE_URL+EmasesaRestConstant.DISTANCIACENTROS_BASE,
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=DistanciaCentros.Rest"
	},
	service = Application.class
)
public class RestEmasesaDistanciaCentrosApplication extends Application {
	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getCentros(@Context HttpServletRequest request) {

		try {
			JSONArray jsonArray = _sapServicesUtil.getDistanciaCentros(StringConstants.EMPTY, StringConstants.EMPTY);
			jsonArray.forEach(jsonObject -> {
				JSONObject jsonOb = ((JSONObject)jsonObject);
				jsonOb.remove("distancia");
				jsonOb.remove("centroDestinoId");
				jsonOb.remove("centroDestinoDesc");
			});
			return Response
					.status(Response.Status.OK)
					.entity(jsonArray.toString())
					.build();
		} catch (Exception e) {
			LOG.error("Error obteniendo la configuración inicial", e);
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage())
					.build();
		}
	}

	@GET
	@Path("/{origen}/{destino}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getDistanciaEntreCentros(@Context HttpServletRequest request, @PathParam("origen") String origen, @PathParam("destino") String destino) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		try {

			JSONArray jsonArray = _sapServicesUtil.getDistanciaCentros(origen, destino);
			if(jsonArray != null && jsonArray.length() > 0){
				jsonObject = (JSONObject) jsonArray.get(0);
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

	private static final Log LOG = LogFactoryUtil.getLog(RestEmasesaDistanciaCentrosApplication.class);
	@Reference
	protected SapServicesUtil _sapServicesUtil;
}