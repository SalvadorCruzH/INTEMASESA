package es.emasesa.intranet.sap.proxy;

import es.emasesa.intranet.sap.ayudaEscolar.service.AyudaEscolarService;
import es.emasesa.intranet.sap.calendarioeventos.service.CalendarioEventosService;
import es.emasesa.intranet.sap.centros.service.DistanciaCentrosService;
import es.emasesa.intranet.sap.contratosCategorias.service.ContratosCategoriasService;
import es.emasesa.intranet.sap.datospersona.service.EmpleadoActDatosPersonalesService;
import es.emasesa.intranet.sap.diplomaEventos.service.DiplomaEventosService;
import es.emasesa.intranet.sap.empleadoBanco.service.EmpleadoBancoService;
import es.emasesa.intranet.sap.empleadoPrestamos.service.EmpleadoPrestamosService;
import es.emasesa.intranet.sap.base.AbstractSelfRegisteringService;
import es.emasesa.intranet.sap.datospersona.service.EmpleadoDatosDomicilioService;
import es.emasesa.intranet.sap.datospersona.service.EmpleadoDatosPersonalesService;
import es.emasesa.intranet.sap.estructura.service.EmpleadoEstructuraService;
import es.emasesa.intranet.sap.histoPerCondu.service.HistPerConduService;
import es.emasesa.intranet.sap.historForm.service.HistorFormService;
import es.emasesa.intranet.sap.historialTitulacion.service.HistorialTitulacionService;
import es.emasesa.intranet.sap.innovacion.service.HistoricoInnovacionService;
import es.emasesa.intranet.sap.jornadadiaria.service.JornadaDiariaService;
import es.emasesa.intranet.sap.marcaje.service.MarcajeService;
import es.emasesa.intranet.sap.necesidadesFormacion.service.NecesidadesFormacionService;
import es.emasesa.intranet.sap.nomina.service.JornadaNominaService;
//import es.emasesa.intranet.sap.regina.service.ReginaService;
import es.emasesa.intranet.sap.procesoseleccionlista.service.ProcesoSeleccionListaService;
import es.emasesa.intranet.sap.relacionLaboral.service.RelacionLaboralService;
import es.emasesa.intranet.sap.resumenanual.service.ResumenAnualService;
import es.emasesa.intranet.sap.retenciones.service.CertificadoRetencionesService;
import es.emasesa.intranet.sap.ciertosdatos.service.CiertosDatosEstructuraService;
import es.emasesa.intranet.sap.subordinados.service.SubordinadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sapInterfaceService")
public class SapInterfaceService  extends AbstractSelfRegisteringService {

    @Autowired(required = false)
    protected EmpleadoEstructuraService empleadoEstructuraService;
    @Autowired(required = false)
    protected JornadaNominaService jornadaNominaService;
    @Autowired(required = false)
    protected MarcajeService marcajeService;
    @Autowired(required = false)
    protected ResumenAnualService resumenAnualService;
    @Autowired(required = false)
    protected EmpleadoDatosPersonalesService empleadoDatosPersonalesService;
    @Autowired(required = false)
    protected EmpleadoDatosDomicilioService empleadoDatosDomicilioService;
    @Autowired(required = false)
    protected JornadaDiariaService jornadaDiariaService;
    @Autowired(required = false)
    protected SubordinadosService subordinadosService;
    @Autowired(required = false)
    protected EmpleadoPrestamosService empleadoPrestamosService;
    @Autowired(required = false)
    protected CertificadoRetencionesService certificadoRetencionesService;
    @Autowired(required = false)
    protected DistanciaCentrosService distanciaCentrosService;
    @Autowired(required = false)
    protected AyudaEscolarService ayudaEscolarService;
    @Autowired(required = false)
    protected CiertosDatosEstructuraService ciertosDatosEstructuraService;
    @Autowired(required = false)
    protected RelacionLaboralService relacionLaboralService;
    @Autowired(required = false)
    protected EmpleadoBancoService empleadoBancoService;
    @Autowired(required = false)
    protected NecesidadesFormacionService necesidadesFormacionService;
    @Autowired(required = false)
    protected CalendarioEventosService calendarioEventosService;
    @Autowired(required = false)
    protected EmpleadoActDatosPersonalesService empleadoActDatosPersonalesService;
    @Autowired(required = false)
    protected HistorFormService historFormService;
//    @Autowired(required = false)
//    protected ReginaService reginaService;
    @Autowired(required = false)
    protected ContratosCategoriasService contratosCategoriasService;
    @Autowired(required = false)
    protected HistorialTitulacionService historialTitulacionService;
    @Autowired(required = false)
    protected HistPerConduService histPerConduService;
    @Autowired(required = false)
    protected HistoricoInnovacionService historicoInnovacionService;
    @Autowired(required = false)
    protected DiplomaEventosService diplomaEventosService;
    @Autowired(required = false)
    protected ProcesoSeleccionListaService procesoSeleccionListaService;

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
    public AyudaEscolarService getAyudaEscolarService() {
        return ayudaEscolarService;
    }
    public CiertosDatosEstructuraService getCiertosDatosEstructuraService() {
        return ciertosDatosEstructuraService;
    }
    public RelacionLaboralService getRelacionLaboralService() {
        return relacionLaboralService;
    }
    public EmpleadoBancoService getEmpleadoBancoService(){
        return empleadoBancoService;
    }
    public NecesidadesFormacionService getNecesidadesFormacionService(){
        return necesidadesFormacionService;
    }
    public CalendarioEventosService getCalendarioEventosService() {
        return calendarioEventosService;
    }
    public EmpleadoActDatosPersonalesService getEmpleadoActDatosPersonalesService() {
        return empleadoActDatosPersonalesService;
    }
    public HistorFormService getHistorFormService() {
        return historFormService;
    }

//    public ReginaService getReginaService() {
//        return reginaService;
//    }
    public ContratosCategoriasService getContratosCategoriasService() {
        return contratosCategoriasService;
    }
    public HistorialTitulacionService getHistorialTitulacion() {
        return historialTitulacionService;
    }
    public HistPerConduService getHistPerConduService() {
        return histPerConduService;
    }
    public HistoricoInnovacionService getHistoricoInnovacionService() {
        return historicoInnovacionService;
    }
    public DiplomaEventosService getDiplomaEventosService() {
        return diplomaEventosService;
    }
    public ProcesoSeleccionListaService getProcesoSeleccionLista() {return procesoSeleccionListaService;}
}
