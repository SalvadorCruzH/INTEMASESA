package es.emasesa.intranet.jaxrs.userexpando;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import es.emasesa.intranet.base.util.CustomCacheSingleUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.jaxrs.restddmselect.application.DDMSelectApplication;
import org.osgi.service.component.annotations.*;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/expando")
@Component(
        immediate = true,
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/expando",
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=emasesa.userexpando",
                "auth.verifier.guest.allowed=true",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)
public class UserExpandoApplication extends Application {

    private static final Log LOG = LoggerUtil.getLog(DDMSelectApplication.class);

    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<Object>();

        //add the automated Jackson marshaller for JSON.
        singletons.add(new JacksonJsonProvider());

        //add this REST endpoints
        singletons.add(this);

        return singletons;
    }

    @GET
    @Path("/get-user-expando-by-userid/{userId}/{fieldName}")
    @Produces(ContentTypes.APPLICATION_TEXT)
    public Response getUserExpandoFieldByUserId(
            @PathParam("userId") long userId,
            @PathParam("fieldName") String fieldName,
            @Context HttpServletRequest request) {

        // cachear
        String cacheKey = "userexpandofield__" + userId + "_" + fieldName;
        String cacheValue = (String) _cache.get(cacheKey);
        if (cacheValue != null) {
            return Response.status(Response.Status.OK).entity(cacheValue).build();
        }

        try {
            // Obtener el usuario por ID
            LoggerUtil.debug(LOG, "Obteniendo el usuario por ID: " + userId);
            User user = UserLocalServiceUtil.getUser(userId);

            LoggerUtil.debug(LOG, "Obteniendo el valor del campo " + fieldName + " del usuario " + userId);
            String expandoValueData = expandoValueLocalService.getData(
                    user.getCompanyId(),
                    "com.liferay.portal.kernel.model.User",
                    "CUSTOM_FIELDS",
                    fieldName,
                    userId,
                    "");

            _cache.put(cacheKey, expandoValueData);
            // Puedes devolver el valor en la respuesta en formato texto
            return Response.status(Response.Status.OK).entity(expandoValueData).build();

        } catch (PortalException e) {
            // Manejar la excepción según tus necesidades
            e.printStackTrace();
            LoggerUtil.error(LOG, "Error al obtener el usuario", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al obtener el usuario").build();
        }
    }

    @Reference
    ExpandoValueLocalService expandoValueLocalService;

    @Reference
    CustomCacheSingleUtil _cache;

}
