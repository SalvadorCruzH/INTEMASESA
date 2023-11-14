package es.emasesa.intranet.lifecycle.service.pre;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.apache.logging.log4j.ThreadContext;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(
        immediate=true,
        property="key=servlet.service.events.pre",
        service= LifecycleAction.class
)
public class NDCSetupServicePreAction extends Action {

    public void run(HttpServletRequest request, HttpServletResponse response) {
        try {

            ThemeDisplay themeDisplay =
                    (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            User user = null;

            if (themeDisplay != null) {
                user = themeDisplay.getUser();
            }

            if (user == null || user.isDefaultUser()) {
                ThreadContext.push("Guest");
            } else {
                ThreadContext.push(user.getScreenName());
            }
        } catch (Exception e) {
            ThreadContext.push("Unknown");
        }
    }
}