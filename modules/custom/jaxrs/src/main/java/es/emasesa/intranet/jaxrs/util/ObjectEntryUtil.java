package es.emasesa.intranet.jaxrs.util;


import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import es.emasesa.intranet.base.constant.EmasesaConstants;
import es.emasesa.intranet.base.util.CustomWorkflowBaseUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.jaxrs.constant.JaxrsConstants;
import es.emasesa.intranet.webservices.jaxrs.beans.ResponseData;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@ApplicationPath("/object-entry-util")
@Component(
        immediate = true,
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/object-entry-util",
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=emasesa.objectentryutil",
                "auth.verifier.guest.allowed=true",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)
public class ObjectEntryUtil extends Application {

    @Override
    public Set<Object> getSingletons() {return Collections.<Object>singleton(this);}

    @GET
    @Path("/delete-object-entry/{objectEntryId}")
    @Produces(ContentTypes.APPLICATION_JSON)
    public Response deleteObjectEntry(
            @PathParam("objectEntryId") long objectEntryId,
            @Context HttpServletRequest request) {
        Response.ResponseBuilder builder;
        JSONObject json = JSONFactoryUtil.createJSONObject();

        try {
            ObjectEntryLocalServiceUtil.deleteObjectEntry(objectEntryId);
            json.put(JaxrsConstants.IS_REMOVE, Boolean.TRUE);
            builder = Response.ok(new ResponseData(
                    false,
                    json.toString(),
                    null,
                    null));
        } catch (PortalException e) {
            LoggerUtil.error(LOG, e);
            json.put(JaxrsConstants.IS_REMOVE, Boolean.FALSE);
            builder = Response.ok(new ResponseData(
                    true,
                    json.toString(),
                    null,
                    null));
        }

        return builder.build();
    }

    @GET
    @Path("/mark-read/{objectEntryId}")
    @Produces(ContentTypes.APPLICATION_JSON)
    public Response markReadObjectEntry(
            @PathParam("objectEntryId") long objectEntryId,
            @Context HttpServletRequest request) {
        Response.ResponseBuilder builder;
        JSONObject json = JSONFactoryUtil.createJSONObject();

        try {
            ObjectEntry objectentry = _objectEntryLocalService.getObjectEntry(objectEntryId);
            Map<String, Serializable> map = objectentry.getValues();
            map.put(EmasesaConstants.EMASESA_OBJECT_STATUS, JaxrsConstants.STATUS_READ);

            ServiceContext serviceContext = new ServiceContext();
            _objectEntryLocalService.updateObjectEntry(
                    objectentry.getUserId(), objectentry.getObjectEntryId(), map,
                    serviceContext);

            json.put(JaxrsConstants.IS_MARK_READ, Boolean.TRUE);
            builder = Response.ok(new ResponseData(
                    false,
                    json.toString(),
                    null,
                    null));
        } catch (PortalException e) {
            LoggerUtil.error(LOG, e);
            json.put(JaxrsConstants.IS_MARK_READ, Boolean.FALSE);
            builder = Response.ok(new ResponseData(
                    true,
                    json.toString(),
                    null,
                    null));
        }

        return builder.build();
    }

    @Reference
    ObjectEntryLocalService _objectEntryLocalService;

    @Reference
    CustomWorkflowBaseUtil _customWorkflowBaseUtil;

    private static final Log LOG = LoggerUtil.getLog(ObjectEntryUtil.class);

}
