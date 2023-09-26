package es.emasesa.intranet.jornada.nomina.servlet;


import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import es.emasesa.intranet.jornada.nomina.service.EmpleadoEstructuraServiceImpl;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = {
                "osgi.http.whiteboard.context.path=/",
                "osgi.http.whiteboard.servlet.pattern=/soap/check"
        },
        service = Servlet.class
)
public class TestSoapServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response){
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            JSONObject json =  _empleadoEstructuraServiceImpl.getEmpleadoEstructura("1002982");

            _log.info(json.toJSONString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            _log.error(e.getMessage());
        }
    }

    @Reference
    EmpleadoEstructuraServiceImpl _empleadoEstructuraServiceImpl;

    Log _log = LogFactoryUtil.getLog(TestSoapServlet.class);

}
