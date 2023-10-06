package es.emasesa.intranet.sap.proxy;

import es.emasesa.intranet.sap.base.AbstractSelfRegisteringService;
import es.emasesa.intranet.sap.datospersona.service.EmpleadoDatosPersonalesService;
import es.emasesa.intranet.sap.estructura.service.EmpleadoEstructuraService;
import es.emasesa.intranet.sap.marcaje.service.MarcajeService;
import es.emasesa.intranet.sap.nomina.service.JornadaNominaService;
import es.emasesa.intranet.sap.resumenanual.service.ResumenAnualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sapInterfaceService")
public class SapInterfaceService  extends AbstractSelfRegisteringService {

    @Autowired
    EmpleadoEstructuraService empleadoEstructuraService;

    @Autowired
    JornadaNominaService jornadaNominaService;

    @Autowired
    MarcajeService marcajeService;

    @Autowired
    ResumenAnualService resumenAnualService;
    @Autowired
    EmpleadoDatosPersonalesService empleadoDatosPersonalesService;

    public EmpleadoDatosPersonalesService getEmpleadoDatosPersonalesService(){
        return empleadoDatosPersonalesService;
    }
    public EmpleadoEstructuraService getEmpleadoEstructuraService(){
        return empleadoEstructuraService;
    }

    public JornadaNominaService getJornadaNominaService(){
        return jornadaNominaService;
    }

    public MarcajeService  getMarcajeService(){
        return marcajeService;
    }

    public ResumenAnualService  getResumenAnualService(){
        return resumenAnualService;
    }
}
