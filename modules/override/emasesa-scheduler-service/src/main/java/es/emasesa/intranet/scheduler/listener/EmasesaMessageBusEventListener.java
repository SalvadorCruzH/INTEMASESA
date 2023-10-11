package es.emasesa.intranet.scheduler.listener;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageBusEventListener;
import org.osgi.service.component.annotations.Component;

@Component(service = MessageBusEventListener.class)
public class EmasesaMessageBusEventListener
        implements MessageBusEventListener {

    @Override
    public void destinationAdded(Destination destination) {
        if (_log.isInfoEnabled()) {
            _log.info("Added destination " + destination.getName());
        }
    }

    @Override
    public void destinationRemoved(Destination destination) {
        if (_log.isInfoEnabled()) {
            _log.info("Removed destination " + destination.getName());
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(
            EmasesaMessageBusEventListener.class);

}
