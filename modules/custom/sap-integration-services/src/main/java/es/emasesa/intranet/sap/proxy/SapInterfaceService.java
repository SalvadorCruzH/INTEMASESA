package es.emasesa.intranet.sap.proxy;

import es.emasesa.intranet.sap.centros.service.DistanciaCentrosService;
import es.emasesa.intranet.sap.empleadoPrestamos.service.EmpleadoPrestamosService;
import es.emasesa.intranet.sap.base.AbstractSelfRegisteringService;
import es.emasesa.intranet.sap.datospersona.service.EmpleadoDatosDomicilioService;
import es.emasesa.intranet.sap.datospersona.service.EmpleadoDatosPersonalesService;
import es.emasesa.intranet.sap.estructura.service.EmpleadoEstructuraService;
import es.emasesa.intranet.sap.jornadadiaria.service.JornadaDiariaService;
import es.emasesa.intranet.sap.marcaje.service.MarcajeService;
import es.emasesa.intranet.sap.nomina.service.JornadaNominaService;
import es.emasesa.intranet.sap.resumenanual.service.ResumenAnualService;
import es.emasesa.intranet.sap.retenciones.service.CertificadoRetencionesService;
import es.emasesa.intranet.sap.subordinados.service.SubordinadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sapInterfaceService")
public class SapInterfaceService  extends AbstractSelfRegisteringService {

    @Autowired
    EmpleadoEstructuraService empleadoEstructuraService;
    @Autowired(required = false)
    JornadaNominaService jornadaNominaService;
    @Autowired(required = false)
    MarcajeService marcajeService;
    @Autowired(required = false)
    ResumenAnualService resumenAnualService;
    @Autowired
    EmpleadoDatosPersonalesService empleadoDatosPersonalesService;
    @Autowired
    EmpleadoDatosDomicilioService empleadoDatosDomicilioService;
    @Autowired(required = false)
    JornadaDiariaService jornadaDiariaService;
    @Autowired(required = false)
    SubordinadosService subordinadosService;
    @Autowired(required = false)
    EmpleadoPrestamosService empleadoPrestamosService;
    @Autowired(required = false)
    CertificadoRetencionesService certificadoRetencionesService;
    @Autowired(required = false)
    DistanciaCentrosService distanciaCentrosService;


    public JornadaDiariaService getJornadaDiariaService(){
        return jornadaDiariaService;
    }

    public EmpleadoDatosDomicilioService getEmpleadoDatosDomicilioService(){
        return empleadoDatosDomicilioService;
    }
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

    public SubordinadosService  getSubordinadosService(){
        return subordinadosService;
    }

    public EmpleadoPrestamosService  getEmpleadoPrestamosService(){
        return empleadoPrestamosService;
    }
    public CertificadoRetencionesService getCertificadoRetencionesService() {
        return certificadoRetencionesService;
    }

    public DistanciaCentrosService getDistanciaCentrosService() {
        return distanciaCentrosService;
    }
}
