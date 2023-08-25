package es.emasesa.intranet.jornada.nomina.model;

import com.sap.document.sap.soap.functions.mc_style.Bapireturn1;
import java.time.LocalDateTime;
import javax.xml.datatype.DatatypeConfigurationException;

public interface JornadaNominaService {

    public Bapireturn1 peticionHorasExtras(String idEmpleado, LocalDateTime fechaInicio, LocalDateTime fechaFin, String tipoRetribucion) throws DatatypeConfigurationException;
}
