package es.emasesa.intranet.sap.proxy;

import es.emasesa.intranet.sap.base.AbstractSelfRegisteringService;
import es.emasesa.intranet.sap.estructura.service.EmpleadoEstructuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sapInterfaceService")
public class SapInterfaceService  extends AbstractSelfRegisteringService {

    @Autowired
    EmpleadoEstructuraService empleadoEstructuraService;

    public EmpleadoEstructuraService getEmpleadoEstructuraService(){
        return empleadoEstructuraService;
    }
}
