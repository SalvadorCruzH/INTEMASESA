package es.emasesa.intranet.sap.base;

import org.osgi.framework.ServiceRegistration;

public class AbstractSelfRegisteringService implements SelfRegisteringService {

    private ServiceRegistration _serviceRegistration;
    private String beanName;

    @Override
    public void setServiceRegistration(ServiceRegistration serviceRegistration) {
        _serviceRegistration = serviceRegistration;
    }

    @Override
    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}