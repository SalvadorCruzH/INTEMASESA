package es.emasesa.intranet.jornada.nomina.proxy;

import es.emasesa.intranet.jornada.nomina.base.AbstractSelfRegisteringService;
import es.emasesa.intranet.jornada.nomina.service.EmpleadoEstructuraService;
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
