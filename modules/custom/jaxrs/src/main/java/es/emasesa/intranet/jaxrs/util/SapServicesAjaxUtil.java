package es.emasesa.intranet.jaxrs.util;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.ContentTypes;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.service.util.SapServicesUtil;
import es.emasesa.intranet.webservices.jaxrs.beans.ResponseData;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Set;


@ApplicationPath("/sap-service-util")
@Component(
        immediate = true,
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/sap-service-util",
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=emasesa.sapservicesajaxutil",
                "auth.verifier.guest.allowed=true",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)
public class SapServicesAjaxUtil extends Application{

    @Override
    public Set<Object> getSingletons() {return Collections.<Object>singleton(this);}

    @GET
    @Path("/get-info-personal/{matricula}")
    @Produces(ContentTypes.APPLICATION_JSON)
    public Response markReadObjectEntry(
            @PathParam("matricula") String matricula,
            @Context HttpServletRequest request) {
        Response.ResponseBuilder builder;
        JSONObject json = _sapServicesUtil.getDatosEmpleadoAndDomicilio(matricula);

        if (json.length()> 0){
            builder = Response.ok(new ResponseData(
                    false,
                    json.toString(),
                    null,
                    null));
        } else {
            builder = Response.ok(new ResponseData(
                    true,
                    json.toString(),
                    null,
                    null));
        }

        return builder.build();
    }

    @Reference
    SapServicesUtil _sapServicesUtil;

    private static final Log LOG = LoggerUtil.getLog(SapServicesAjaxUtil.class);
}
