package es.emasesa.intranet.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.template.ServiceLocator;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapException;
import es.emasesa.intranet.sap.nomina.service.JornadaNominaService;
import es.emasesa.intranet.sap.proxy.SapInterfaceService;
import es.emasesa.intranet.sap.estructura.service.EmpleadoEstructuraService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import es.emasesa.intranet.base.util.CustomExpandoUtil;
import es.emasesa.intranet.sap.subordinados.service.CiertosDatosEstructuraService;

@Component(
        immediate = true,
        service = CustomWorkflowUtil.class
)
public class CustomWorkflowUtil {
    /**
     * Retrive employee ID from the soapService
     * @param workflowContext
     * @employeeType employeeType
     * @return users
     */
    public List<User> assignWorkflowUser(Map<String, Serializable> workflowContext, long userId, String employeeType) {
        List<User> users = new ArrayList<>();
        long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
        String matriculaSAPUser = StringPool.BLANK;
        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        User user = null;
        try {
            if (customExpandoUtil == null || empleadoEstructuraService == null){
                activate(null);
            }
            long userIdSolicitante = _objectEntryLocalService.getObjectEntry(GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK))).getUserId();
            String matriculaActualUser  = customExpandoUtil.getDataValueByUser(userIdSolicitante, companyId, "matricula");
            ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            JSONObject json = empleadoEstructuraService.getEmpleadoEstructura(matriculaActualUser);
            Thread.currentThread().setContextClassLoader(actualClassLoader);
            matriculaSAPUser = json.getString(employeeType);
            LOG.debug("Tipo de empleado: "+employeeType);
            LOG.debug("Nombre del empleado: "+matriculaSAPUser);
            user = customExpandoUtil.getUserByExpandoValue(companyId, "matricula", matriculaSAPUser);
            if(Validator.isNotNull(user)) {
                LOG.debug("Se ha encontrado en Liferay un usuario con la matricula: " + matriculaSAPUser);
                users.add(user);
            }else {
                LOG.debug("No existe en Liferay un usuario con la matricula: " + matriculaSAPUser);
            }
        } catch (SapException e) {
            LOG.error("Se ha producido un error a la hora de obtener la estructura del usuario "+matriculaSAPUser, e);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        } finally {
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }

        return users;
    }
    
    public List<User> assignWorkflowUserFromMatriculaForm(Map<String, Serializable> workflowContext, long userId, String employeeType) {
        List<User> users = new ArrayList<>();
        long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
        String matriculaSAPUser = StringPool.BLANK;
        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        User user = null;
        try {
            if (customExpandoUtil == null || empleadoEstructuraService == null){
                activate(null);
            }

            //TODO if gestorDeTiempo seleccionado
            Map<String, Serializable> objectValues = _objectEntryLocalService.getObjectEntry(GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK))).getValues();
            String matriculaSolicitado;
            if(Validator.isNotNull(objectValues.get("seleccion")) && objectValues.get("seleccion").equals("gestorDeTiempo")) {
                matriculaSolicitado = (String) objectValues.get("matriculaSolicitado");
            }else {
                matriculaSolicitado = (String) objectValues.get("matricula");
            }
            //User userDeMatricula = customExpandoUtil.getUserByExpandoValue(companyId, "matricula", matriculaSolicitante);
            JSONObject json = empleadoEstructuraService.getEmpleadoEstructura(matriculaSolicitado);
            matriculaSAPUser = json.getString(employeeType);
            LOG.debug("Tipo de empleado: "+employeeType);
            LOG.debug("Nombre del empleado: "+matriculaSAPUser);
            user = customExpandoUtil.getUserByExpandoValue(companyId, "matricula", matriculaSAPUser);
            if(Validator.isNotNull(user)) {
                LOG.debug("Se ha encontrado en Liferay un usuario con la matricula: " + matriculaSAPUser);
                users.add(user);
            }else {
                LOG.debug("No existe en Liferay un usuario con la matricula: " + matriculaSAPUser);
            }
        } catch (SapException e) {
            LOG.error("Se ha producido un error a la hora de obtener la estructura del usuario "+matriculaSAPUser, e);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        } finally {
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }
        return users;
        
    }

    /**
     * Devuelve usuarios de SAP consejeroId, direccionRrhhRespId, divisionRrhhRespId o subdireccionRrhhRespId
     * @param workflowContext
     * @param userType
     * @return List<User>
     */
    public List<User> assignWorkflowHorizontalUser(Map<String, Serializable> workflowContext, String userType){
    	
    	 LOG.debug("Asignar usuario horizontal: " + userType);
    	 List<User> users = new ArrayList<>();
    	 User user = getUserSap(workflowContext, userType);
    	 users.add(user);
    	 return users;
    }

    /**
     * Devuelve usuario de SAP consejeroId, direccionRrhhRespId, divisionRrhhRespId o subdireccionRrhhRespId
     * @param workflowContext
     * @param userType
     * @return user
     */
    public User getUserSap(Map<String, Serializable> workflowContext, String userType) {
        User user = null;
        long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
        String matriculaUser = StringPool.BLANK;

        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
        	LOG.debug("Buscando usuario del SAP: " + userType);
            ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            if (ciertosDatosEstructuraService == null){
                activate(null);
            }
            JSONObject json = ciertosDatosEstructuraService.getCiertosDatosEstructura();
            Thread.currentThread().setContextClassLoader(actualClassLoader);

            matriculaUser = json.getString(userType);
            LOG.debug("La matricula del usuario es: " + matriculaUser);
            user = customExpandoUtil.getUserByExpandoValue(companyId, "matricula", matriculaUser);

        } catch (SapException e) {
            LOG.error("Se ha producido un error a la hora de obtener la estructura del usuario "+matriculaUser, e);

        } finally {
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }
        return user;
    }
    
    /**
     * Devuelve el usuario de SAP. Busca al subdireccionRrhhRespId y si no existe se trae el direccionRrhhRespId
     * @param workflowContext
     * @return List<User>
     */
    public List<User> assignWorkflowSubdirectorOrDirector(Map<String, Serializable> workflowContext){
    	
    	 LOG.debug("Asignar usuario horizontal de SAP subdireccionRrhhRespId o direccionRrhhRespId en el caso de que el otro no exista.");
    	 List<User> users = new ArrayList<>();
    	 String matriculaUser = StringPool.BLANK;

         ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
         long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
    	 
         try {
         	LOG.debug("Buscando usuario del SAP...");
             ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
             Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
             if (ciertosDatosEstructuraService == null){
                 activate(null);
             }
             JSONObject json = ciertosDatosEstructuraService.getCiertosDatosEstructura();
             Thread.currentThread().setContextClassLoader(actualClassLoader);

             matriculaUser = json.getString("subdireccionRrhhRespId");
             LOG.debug("La matricula del usuario es: " + matriculaUser);
             
             if(Validator.isNotNull(matriculaUser) && ("00000000".equals(matriculaUser))) {
            	 LOG.debug("No existe en SAP el usuario subdireccionRrhhRespId: " + matriculaUser + " . Se busca el direccionRrhhRespId");
            	 matriculaUser = json.getString("direccionRrhhRespId");
            	 LOG.debug("La matricula del usuario direccionRrhhRespId es: " + matriculaUser+ " . Se procede a buscar el usuaruio en liferay con esa matrícula.");
             }else {
            	 LOG.debug("Existe el usuario subdireccionRrhhRespId: " + matriculaUser + " . Se procede a buscar el usuaruio en liferay con esa matrícula.");
             }
            User user = customExpandoUtil.getUserByExpandoValue(companyId, "matricula", matriculaUser);
            users.add(user);

         } catch (SapException e) {
             LOG.error("Se ha producido un error a la hora de obtener la estructura del usuario "+ matriculaUser, e);
         } finally {
             Thread.currentThread().setContextClassLoader(actualClassLoader);
         }
    	 return users;
    }


    public String modificarIRPF(Map<String, Serializable> workflowContext) {
        String i = "";
        String pernr = "";
        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            if (_objectEntryLocalService == null){
                activate(null);
            }
            long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
            String fechaSolicitud = (String) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("fechaSolicitud");
            double IRPF_Solicitado = (double) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("retencinDelIRPFSolicitada");
            pernr = (String) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("nmeroDeMatricula");

            ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            i = jornadaNominaService.guardarIRPF(pernr, fechaSolicitud, IRPF_Solicitado);
            LOG.debug("Se ha guardado el cambio de IRPF: "+IRPF_Solicitado + " para el usuario " +pernr);
        } catch (PortalException e) {
            LOG.error("Se ha producido un error al modificar IRPF para "+ pernr, e);
        } finally{
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }

        return i;
    }

    /**
     * Cambio de domiciliación Bancaria de un empleado. Deberá rellenarse el campo de entrada IBAN, con la matricula del empleado y la fecha de solicitud
     *
     * @param workflowContext
     * @return datosServicio
     */
    public String cambioDomiciliacionBancaria(Map<String, Serializable> workflowContext) {
        String datosServicio = StringPool.BLANK;
        String pernr = StringPool.BLANK;
        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            if (_objectEntryLocalService == null){
                activate(null);
            }
            LOG.debug("Se procede con el cambio de domiciliacion bancaria...");
            long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
            String fechaSolicitud = (String) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("createDate");
            String iban = (String)_objectEntryLocalService.getObjectEntry(classPK).getValues().get("iBAN");
            pernr = (String) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("numeroDeMatricula");
            LOG.debug("fechaSolicitud: " + fechaSolicitud);
            LOG.debug("iban: " + iban);
            LOG.debug("pernr: " + pernr);

            ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            datosServicio = jornadaNominaService.cambioDomiciliacionBancaria(pernr, fechaSolicitud, iban);
            LOG.debug("Se ha guardado el cambio de iban: "+iban + " para el usuario " +pernr);
            LOG.debug("Los datosServicio son: " + datosServicio);

        } catch (PortalException e) {
            LOG.error("Se ha producido un error al modificar de cambio de domiciliacion bancaria para "+ pernr, e);
        } finally {
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }

        return datosServicio;
    }

    public String addPlusSap(Map<String, Serializable> workflowContext){
        String datosServicio = StringPool.BLANK;
        String pernr = StringPool.BLANK;
        String codigoParte = StringPool.BLANK;
        int valueUnits = 0;

        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            if (_objectEntryLocalService == null){
                activate(null);
            }
            LOG.debug("Se procede a añdir plus...");
            long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
            switch ((String) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("pedirParaOtraPersona")){
                case "paraMi":
                    pernr = (String) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("numeroDeMatricula");
                    break;
                case "pedirParaOtraPersona":
                    pernr = (String) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("nmeroDeMatrculaAjena");
                    break;
            }
            LOG.debug("obtenida matricula: " + pernr);
            String listadoString = _objectEntryLocalService.getObjectEntry(classPK).getValues().get("listadoSolicitudes").toString();
            ObjectMapper listadoParse = new ObjectMapper();
            JsonNode listado = listadoParse.readTree(listadoString);

            for (JsonNode solicitud : listado) {
                String fecha = solicitud.get("fecha").asText();
                String parte = solicitud.get("parte").asText();
                String parteModificado = quitarTildes(parte);

                if (parteModificado.equals("Volante")) {
                    codigoParte = "1J09";
                    valueUnits = 1;
                    addFechas(fecha, pernr, codigoParte, valueUnits);
                    return datosServicio;
                } else if(parteModificado.equals("Penoso")) {
                    codigoParte = "1J03";
                    valueUnits = 1;
                }else if(parteModificado.equals("Toxico")) {
                    codigoParte = "1J05";
                    valueUnits = 1;
                    addFechas(fecha, pernr, codigoParte, valueUnits);
                    return datosServicio;
                }else if(parteModificado.equals("Trabajo con Pantalla")) {
                    codigoParte = "1J16";
                    valueUnits = 1;
                    addFechas(fecha, pernr, codigoParte, valueUnits);
                    return datosServicio;
                }else if(parteModificado.equals("Locomocion")) {
                    codigoParte = "1J13";
                    valueUnits = solicitud.get("valor").asInt();
                }
                ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
                Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
                datosServicio = jornadaNominaService.addPlusSap(pernr, fecha, codigoParte, BigDecimal.valueOf(valueUnits));
                LOG.debug("Se ha añadido el plus: " + parte + " para el usuario " + pernr);
                LOG.debug("Los datosServicio son: " + datosServicio);

            }
        } catch (PortalException e) {
            LOG.error("Se ha producido un error al añadir los pluses para "+ pernr, e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }

        return datosServicio;
    }

    public String addMarcaje(Map<String, Serializable> workflowContext){
        String datosServicio = StringPool.BLANK;
        String pernr = StringPool.BLANK;
        String codigoMotivo = StringPool.BLANK;
        int valueUnits = 0;
        LOG.debug("Se procede a añadir marcaje...");

        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            if (_objectEntryLocalService == null || jornadaNominaService == null){
                activate(null);
            }
           
            long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
            LOG.debug("classPK: " + classPK);
            switch ((String) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("pedirParaOtraPersona")){
                case "cuentaPropia":
                	LOG.debug("cuentaPropia");
                    pernr = (String) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("numeroDeMatricula");
                    LOG.debug("numeroDeMatricula: " + pernr);
                    break;
                case "cuentaAjena":
                	LOG.debug("cuentaAjena");
                    pernr = (String) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("nmeroDeMatrculaAjena");
                    LOG.debug("nmeroDeMatrculaAjena: " + pernr);
                    break;
            }
            String listadoString = _objectEntryLocalService.getObjectEntry(classPK).getValues().get("listadoSolicitudes").toString();
            LOG.debug("listadoString: " + listadoString);
            ObjectMapper listadoParse = new ObjectMapper();
            JsonNode listado = listadoParse.readTree(listadoString);

            for (JsonNode solicitud : listado) {
                String fecha = solicitud.get("fecha").asText();
                String parte = solicitud.get("parte").asText();
                String parteModificado = quitarTildes(parte);

                if (parteModificado.equals("Marcaje")) {
                	LOG.debug("Marcaje");
                    String motivo = solicitud.get("detalles").asText().substring("Motivo: ".length());
                    if (motivo.equals("asuntoSindicalASIPE")) {
                        codigoMotivo = "";
                    } else if(motivo.equals("asuntoSindicalCCOO")) {
                        codigoMotivo = "";
                    } else if(motivo.equals("asuntoSindicalUGT")) {
                        codigoMotivo = "";
                    } else if(motivo.equals("olvidoDeterioroTarjeta")) {
                        codigoMotivo = "";
                    } else if(motivo.equals("trabajoFueraDelCT")) {
                        codigoMotivo = "";
                    } else if(motivo.equals("trabajoNoPresencial")) {
                        codigoMotivo = "";
                    }
                    LOG.debug("motivo: " + motivo);

                    ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
                    Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
                    String [] horas = solicitud.get("valor").asText().split(StringPool.DASH);
                    for (String hora : horas) {
                        datosServicio = jornadaNominaService.addMarcaje(pernr, fecha, hora, codigoMotivo);
                        LOG.debug("Se ha añadido el marcaje: " + hora + " para el usuario " + pernr);
                        LOG.debug("Los datosServicio son: " + datosServicio);
                    }

                } else if(parteModificado.equals("Presencia de Formacion")) {
                    String requiereDesplazamiento = solicitud.get("detalles").asText().substring("Requiere Desplazamiento: ".length());


                }
            }
        } catch (PortalException e) {
            LOG.error("Se ha producido un error al añadir los pluses para "+ pernr, e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }

        return datosServicio;
    }

    public String addHorasExtra(Map<String, Serializable> workflowContext) {
        String datosServicio = StringPool.BLANK;
        String pernr = StringPool.BLANK;
        String codigoMotivo = StringPool.BLANK;
        LOG.debug("Se procede a añadir horas Extras...");

        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            if (_objectEntryLocalService == null || jornadaNominaService == null){
                activate(null);
            }
            long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
            LOG.debug("classPK: " + classPK);
            Map<String, Serializable> objectValues = _objectEntryLocalService.getObjectEntry(classPK).getValues();
            if (objectValues.get("seleccion").equals("gestorDeTiempo")) {
                pernr = (String) objectValues.get("matriculaSolicitado");
                LOG.debug("numeroDeMatriculaAjeno: " + pernr);
            } else {
                pernr = (String) objectValues.get("matricula");
                LOG.debug("numeroDeMatricula: " + pernr);
            }
            String fechaInicio = (String) objectValues.get("fechaInicio");
            String horasExtras = (String) objectValues.get("horasPorDiaExtra");
            LOG.debug("horasExtras: " + horasExtras);
            JSONObject json =  JSONFactoryUtil.createJSONObject(horasExtras);
            LOG.debug("json: " + json);
            JSONArray dias = json.names();
            LOG.debug("dias: " + dias);
            for (int i = 0; i < dias.length(); i++) {
                String dia = dias.getString(i);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fecha = LocalDate.parse(fechaInicio, dtf);

                DayOfWeek diaDeLaSemana = fecha.getDayOfWeek();

                int diferencia = getDayOfWeek(dia) - diaDeLaSemana.getValue();
                LocalDate fechaSeleccionada = fecha.plusDays(diferencia);

                String horaInicio = json.getJSONObject(dia).getString("horaInicio");
                String horaFin = json.getJSONObject(dia).getString("horaFin");
                String retribucion = json.getJSONObject(dia).getString("retribucion");
                ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
                Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
                datosServicio = jornadaNominaService.addHorasExtra(pernr, fechaSeleccionada.format(dtf), horaInicio, horaFin, retribucion);

                return datosServicio;
            }

        } catch (PortalException e) {
            LOG.error("Se ha producido un error al añadir las horas extras para "+ pernr, e);
        //} catch (JsonProcessingException e) {
          //  throw new RuntimeException(e);
        } finally {
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }
        return "";
    }

    public int getDayOfWeek(String dia) {
    	switch (dia) {
            case "lunes":
                return DayOfWeek.MONDAY.getValue();
            case "martes":
                return DayOfWeek.TUESDAY.getValue();
            case "miercoles":
                return DayOfWeek.WEDNESDAY.getValue();
            case "jueves":
                return DayOfWeek.THURSDAY.getValue();
            case "viernes":
                return DayOfWeek.FRIDAY.getValue();
            case "sabado":
                return DayOfWeek.SATURDAY.getValue();
            case "domingo":
                return DayOfWeek.SUNDAY.getValue();
            default:
                return DayOfWeek.MONDAY.getValue();
        }
        }
    public static String quitarTildes(String input){
        String cadenaNormalizada = Normalizer.normalize(input, Normalizer.Form.NFD);
        return cadenaNormalizada.replaceAll("[^\\p{ASCII}]", "");
    }

    public void addFechas(String fechas, String pernr, String plusNomina, int plusUnidades){

        String rangofechas = fechas;
        String[] fechasArray = rangofechas.split(" - ");
        String fechaInicioStr = fechasArray[0];
        String fechaFinStr = fechasArray[1];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, formatter);
        LocalDate fechaFin = LocalDate.parse(fechaFinStr, formatter);

        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        if (jornadaNominaService == null){
            activate(null);
        }
        while (!fechaInicio.isAfter(fechaFin)) {
            try {
                ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
                Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
                jornadaNominaService.addPlusSap(pernr, fechaInicio.format(formatter), plusNomina, BigDecimal.valueOf(plusUnidades));
                LOG.debug("Se ha añadido el plus: " + plusNomina + " para el usuario " + pernr);

            } catch (Exception e) {
                LOG.error("Se ha producido un error al añadir los pluses para "+ pernr, e);
            } finally {
                Thread.currentThread().setContextClassLoader(actualClassLoader);
            }
            fechaInicio = fechaInicio.plusDays(1);
        }

        LOG.debug("Se ha añadido el plus: " + plusNomina + " para el usuario " + pernr);
        Thread.currentThread().setContextClassLoader(actualClassLoader);
    }

    /**
     * Retrive employee ID from the soapService
     * @param workflowContext
     * @employeeType role
     * @return users
     */
    public List<Role> assignWorkflowRole(Map<String, Serializable> workflowContext, String roleName) {
        long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
        List<Role> roles = new ArrayList<>();
        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Role role = RoleLocalServiceUtil.getRole(companyId, roleName);
            roles.add(role);
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        } catch (PortalException e) {
            LoggerUtil.error(LOG, e);
        } finally {
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }


        return roles;
    }

    public List<ObjectEntry> getSubObjects(long groupId, long objectRelationshipId, long objectEntryId){
        List<ObjectEntry>  objects = new ArrayList<>();
        try {
            objects = new ArrayList<>(ObjectEntryLocalServiceUtil.getOneToManyObjectEntries(groupId, objectRelationshipId, objectEntryId, Boolean.TRUE, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS));
            if (objects.size()> 1) objects.sort(Comparator.comparing(ObjectEntry::getCreateDate));
        } catch (PortalException e) {
            LoggerUtil.error(LOG, e);
        }
        return objects;
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {

        CustomServiceTracker<EmpleadoEstructuraService> serviceEstructuraTracker = new CustomServiceTracker<>(EmpleadoEstructuraService.class, "getEmpleadoEstructuraService");
        CustomServiceTracker<JornadaNominaService> serviceNomina = new CustomServiceTracker<>(JornadaNominaService.class, "getJornadaNominaService");
        CustomServiceTracker<CiertosDatosEstructuraService> ciertosDatosEstructuraService = new CustomServiceTracker<>(CiertosDatosEstructuraService.class, "getCiertosDatosEstructuraService");
        CustomServiceTracker<JornadaNominaService> jornadaNominaServiceTracker = new CustomServiceTracker<>(JornadaNominaService.class, "getJornadaNominaService");

        try {
            this.empleadoEstructuraService = serviceEstructuraTracker.getService();
            this.jornadaNominaService = serviceNomina.getService();
            this.ciertosDatosEstructuraService = ciertosDatosEstructuraService.getService();
            this.jornadaNominaService = jornadaNominaServiceTracker.getService();
            this.customExpandoUtil = (CustomExpandoUtil) ServiceLocator.getInstance().findService("es.emasesa.intranet.base.util.CustomExpandoUtil");
            this._userLocalService = (UserLocalService) ServiceLocator.getInstance().findService("com.liferay.portal.kernel.service.UserLocalService");
            this._objectEntryLocalService = (ObjectEntryLocalService) ServiceLocator.getInstance().findService("com.liferay.object.service.ObjectEntryLocalService");
            this._objectEntryLocalService = (ObjectEntryLocalService) ServiceLocator.getInstance().findService("com.liferay.object.service.ObjectEntryLocalService");
        } catch (InterruptedException e) {
            LoggerUtil.info(LOG,"Error arrancando servicios", e);
        }
    }
    protected CustomExpandoUtil customExpandoUtil;
    protected UserLocalService _userLocalService;
    protected EmpleadoEstructuraService empleadoEstructuraService;
    protected CiertosDatosEstructuraService ciertosDatosEstructuraService;
    protected ObjectEntryLocalService _objectEntryLocalService;
    protected JornadaNominaService jornadaNominaService;
    private static final Log LOG = LogFactoryUtil.getLog(CustomWorkflowUtil.class);
}