package es.emasesa.intranet.base.servlet;


import com.liferay.portal.kernel.log.Log;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.base.constant.StringConstants;
import es.emasesa.intranet.base.constant.LongConstants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(
        immediate = true,
        property = {
                "osgi.http.whiteboard.context.path=/",
                "osgi.http.whiteboard.servlet.pattern=/heartbeat/check"
        },
        service = Servlet.class
)
public class HeartBeatServlet extends HttpServlet {
    private static final long serialVersionUID = LongConstants.ONE;
    private static final String HEARTBEAT_OK = StringConstants.ONE;

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response){
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(HEARTBEAT_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LoggerUtil.error(_log,e);
        }
    }

    Log _log = LoggerUtil.getLog(HeartBeatServlet.class);

}
