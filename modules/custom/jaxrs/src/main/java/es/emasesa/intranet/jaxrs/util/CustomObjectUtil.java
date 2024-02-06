package es.emasesa.intranet.jaxrs.util;

import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionServiceUtil;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONObjectWrapper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.ContentTypes;
import es.emasesa.intranet.base.util.CustomExpandoUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component(
    property = {
        JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/custom-object-util",
        JaxrsWhiteboardConstants.JAX_RS_NAME + "=emasesa.CustomObjectUtil",
        "auth.verifier.guest.allowed=true",
        "liferay.access.control.disable=true"
    },
    service = Application.class
)
public class CustomObjectUtil extends Application {

    @Override
    public Set<Object> getSingletons() {return Collections.<Object>singleton(this);}

    @POST
    @Path("/submit-horas-extra")
    @Produces(ContentTypes.APPLICATION_JSON)
    @Consumes(ContentTypes.APPLICATION_JSON)
    public String getInfoPersonal(String data) {

        PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();
        String msg ="";
        if(permissionChecker.isCheckGuest()) {
            try {
                boolean result = false;
                result = crearHorasExtra(JSONFactoryUtil.createJSONObject(data).getJSONObject("form"));
                return result ? "{\"code\": 200}" : "{\"code\": 500}";
            } catch (Exception e) {
                msg = e.getLocalizedMessage();
                if (LOG.isDebugEnabled()) {
                    LOG.debug(e.getMessage(), e);
                }
            }
        }
        return "{\"code\": 500, \"msg\":\""+msg+"\"}";

    }

    private boolean crearHorasExtra(JSONObject data) throws PortalException {

        Map<String, Serializable> params = new HashMap<>();
        params.put("nombre", data.getString("nombre"));
        params.put("primerApellido", data.getString("primerApellido"));
        params.put("segundoApellido", data.getString("segundoApellido"));
        params.put("numeroDeMatricula", data.getString("numeroDeMatricula"));
        params.put("departamento", data.getString("departamento"));
        params.put("centroDeTrabajo", data.getString("centroDeTrabajo"));
        params.put("seleccion", data.getString("seleccion"));

        params.put("causa", data.getString("causa"));
        params.put("fechaDesde", data.getString("fechaDesde"));
        params.put("fechaHasta", data.getString("fechaHasta"));
        params.put("seleccionDiasSemana", data.getString("seleccionDiasSemana"));
        params.put("horasPorDiaOrdinarias", data.getString("horasPorDiaOrdinarias"));
        params.put("horasExcedentesDiasExtras", data.getString("horasExcedentesDiasExtras"));
        params.put("horasPorDiaExtra", data.getString("horasPorDiaExtra"));
        params.put("trabajoRealizado", data.getString("trabajoRealizado"));

        String listadoPersonas = data.getString("listadoPersonas");
        LOG.debug("listadoPersonas: " + listadoPersonas);
        if(listadoPersonas != null && !listadoPersonas.isEmpty() && data.getString("seleccion").equals("gestorDeTiempo")) {
            JSONArray listadoPersonasArray = JSONFactoryUtil.createJSONArray(listadoPersonas);
            for(int i = 0; i < listadoPersonasArray.length(); i++) {
                JSONObject persona = listadoPersonasArray.getJSONObject(i);
                LOG.debug("persona: " + persona);
                params.remove("matriculaSolicitado");
                params.remove("nombreSolicitado");
                params.remove("apellido1Solicitado");
                params.remove("apellido2Solicitado");
                params.put("matriculaSolicitado", persona.getString("pernr"));
                params.put("nombreSolicitado", persona.getString("nombre"));
                params.put("apellido1Solicitado", persona.getString("apellido1"));
                params.put("apellido2Solicitado", persona.getString("apellido2"));

                ObjectEntry objectEntry = _objectEntryLocalService.addObjectEntry(data.getInt("userId"), data.getInt("groupId"), data.getInt("horasExtraObjectId"), params, ServiceContextThreadLocal.getServiceContext());

                _objectEntryLocalService.updateAsset(data.getInt("userId"), objectEntry, new long[0], new String[0], new long[0], null);
            }
        } else {
            ObjectEntry objectEntry = _objectEntryLocalService.addObjectEntry(data.getInt("userId"), data.getInt("groupId"), data.getInt("horasExtraObjectId"), params, ServiceContextThreadLocal.getServiceContext());
            _objectEntryLocalService.updateAsset(data.getInt("userId"), objectEntry, new long[0], new String[0], new long[0], null);
        }
        return true;
    }
    private static final Log LOG = LoggerUtil.getLog(ObjectEntryUtil.class);

    @Reference
    private ObjectEntryLocalService _objectEntryLocalService;
    @Reference
    CustomExpandoUtil _customExpandoUtil;
}
