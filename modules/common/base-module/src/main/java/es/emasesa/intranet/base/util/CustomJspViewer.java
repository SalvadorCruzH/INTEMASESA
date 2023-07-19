package es.emasesa.intranet.base.util;

import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.Portal;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.ServletContext;

@Component(
        immediate = true,
        property = { },
        service = CustomJspViewer.class
)
public class CustomJspViewer {

    public boolean render(final ServletContext sc,
                          final PortletRequest pReq,
                          final PortletResponse pResp,
                          final String path) {
        boolean returnedBool=Boolean.TRUE;
        try {
            jspRenderer.renderJSP(sc,
                    portal.getHttpServletRequest(pReq),
                    portal.getHttpServletResponse(pResp),
                    path);
        }catch (Exception e){
            returnedBool=Boolean.FALSE;
            LoggerUtil.error(LOG, e.getMessage());
        }
        return returnedBool;
    }

    @Reference
    JSPRenderer jspRenderer;

    @Reference
    Portal portal;

    Log LOG = LoggerUtil.getLog(CustomJspViewer.class);
}
