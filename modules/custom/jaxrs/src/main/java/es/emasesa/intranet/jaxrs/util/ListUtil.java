package es.emasesa.intranet.jaxrs.util;

import com.liferay.list.type.model.ListTypeDefinition;
import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.ListTypeDefinitionLocalServiceUtil;
import com.liferay.list.type.service.ListTypeEntryLocalServiceUtil;
import com.liferay.object.rest.dto.v1_0.ListEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.service.ListTypeLocalServiceUtil;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
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
import java.util.List;
import java.util.Set;


@ApplicationPath("/list-util")
@Component(
        immediate = true,
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/list-util",
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=emasesa.listutil",
                "auth.verifier.guest.allowed=true",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)
public class ListUtil extends Application{

    @Override
    public Set<Object> getSingletons() {return Collections.<Object>singleton(this);}

    @GET
    @Path("/get-list/{externalReference}/{companyId}")
    @Produces(ContentTypes.APPLICATION_JSON)
    public Response markReadObjectEntry(
            @PathParam("externalReference") String externalReference,
            @PathParam("companyId") long companyId,
            @Context HttpServletRequest request) {
        Response.ResponseBuilder builder;
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
        try {
            ListTypeDefinition list = ListTypeDefinitionLocalServiceUtil.getListTypeDefinitionByExternalReferenceCode(externalReference, companyId);
            List<ListTypeEntry> listentry= ListTypeEntryLocalServiceUtil.getListTypeEntries(list.getListTypeDefinitionId());

            for (ListTypeEntry listTypeEntry : listentry) {
                JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

                jsonObject.put("key", listTypeEntry.getKey());
                jsonObject.put("name",listTypeEntry.getName("es_ES"));

                jsonArray.put(jsonObject);
            }
        } catch (PortalException e) {
            LoggerUtil.error(LOG, e);
            builder = Response.ok(new ResponseData(
                    true,
                    null,
                    null,
                    null));
        }
          if (jsonArray.length()> 0){
            builder = Response.ok(new ResponseData(
                    false,
                    jsonArray.toString(),
                    null,
                    null));
       } else {
            builder = Response.ok(new ResponseData(
                    true,
                    jsonArray.toString(),
                    null,
                    null));
        }

        return builder.build();
    }

    private static final Log LOG = LoggerUtil.getLog(ListUtil.class);
}
