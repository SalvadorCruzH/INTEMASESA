package es.emasesa.intranet.jornada.nomina.proxy;

import es.emasesa.intranet.jornada.nomina.base.AbstractSelfRegisteringService;
import es.emasesa.intranet.jornada.nomina.service.EmpleadoEstructuraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sapInterfaceService")
public class SapInterfaceService  extends AbstractSelfRegisteringService {

    @Autowired
    EmpleadoEstructuraServiceImpl empleadoEstructuraService;


    public EmpleadoEstructuraServiceImpl getEmpleadoEstructuraService(){
        return empleadoEstructuraService;
    }
}
