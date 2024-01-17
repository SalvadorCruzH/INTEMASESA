package es.emasesa.intranet.favoritos.rest;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.favoritos.bean.FavoritoBean;
import es.emasesa.intranet.favoritos.service.EmasesaFavoritosService;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.Set;

@Component(
        immediate = true,
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/favoritos",
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=Favoritos.Rest"
        },
        service = Application.class
)
public class EmasesaFavoritosRest extends Application{
	
	@Activate
    public void activate() {
        if (LOG.isInfoEnabled()) {
            LOG.info("FavoritesRest service activated");
        }
    }

    @POST
    @Path("/save")
    @Consumes("application/json")
    @Produces("application/json")
    public String save(FavoritoBean data) {
        PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();
        String msg ="";
        if(permissionChecker.isCheckGuest()) {
            try {
                boolean result = false;
                if("ADD".equalsIgnoreCase(data.getCmd())) {
                    result = _emasesaFavoritosService.addFav(String.valueOf(data.getAssetEntryId()), data.getClassNameId(), data.getGroupId(), data.getTitle(), data.getUrl(), data.getFileExtension(), data.getDdmStructureKey());
                }else{
                    result = _emasesaFavoritosService.deleteFav(String.valueOf(data.getAssetEntryId()));
                }
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

    @GET
    @Path("/test")
    @Produces("text/plain")
    public String test() {
        PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();
        return "true";
    }

    @Reference
    protected EmasesaFavoritosService _emasesaFavoritosService;

    @Override
    public Set<Object> getSingletons() {
        return Collections.singleton((Object) this);
    }

    private static final Log LOG = LoggerUtil.getLog(EmasesaFavoritosRest.class);

}
