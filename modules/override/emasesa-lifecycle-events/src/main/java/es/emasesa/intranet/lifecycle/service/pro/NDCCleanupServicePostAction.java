package es.emasesa.intranet.lifecycle.service.pro;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.LifecycleAction;
import org.apache.logging.log4j.ThreadContext;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(
        immediate=true,
        property="key=servlet.service.events.post",
        service= LifecycleAction.class
)
public class NDCCleanupServicePostAction extends Action {
    public void run(HttpServletRequest request, HttpServletResponse response) {
        ThreadContext.clearAll();
    }
}
