package es.emasesa.intranet.webservices.jaxrs.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.util.LoggerUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormData implements Serializable {
    // Common fields
    private String name;
    private String surnames;
    private String address;
    private String phone;
    private String email;
    private String grpdAccept;

    // Buzón Ético, Único and Dinamico fields
    private String fileName;
    private String fileB64;

    // Buzón ComercialEstaciones fields
    private String numExpediente;
    private String companyName;

    // Buzón Ético fields
    private String topic;
    private String reportedFact;
    private String affectedPeople;

    // Buzón Ético, Único and ComercialEstaciones fields
    private String nif;
    // Buzón Único and Dinamico fields
    private String message;

    // Form types
    public static final String BUZON_ETICO = "buzonEtico";
    public static final String BUZON_UNICO = "buzonUnico";
    public static final String BUZON_DINAMICO = "buzonDinamico";
    public static final String BUZON_COM_ESTACIONES = "comercialEstaciones";

    private static Log logger = LoggerUtil.getLog(FormData.class);



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getReportedFact() {
        return reportedFact;
    }

    public void setReportedFact(String reportedFact) {
        this.reportedFact = reportedFact;
    }

    public String getAffectedPeople() {
        return affectedPeople;
    }

    public void setAffectedPeople(String affectedPeople) {
        this.affectedPeople = affectedPeople;
    }

    public String getGrpdAccept() {
        return grpdAccept;
    }

    public void setGrpdAccept(String grpdAccept) {
        this.grpdAccept = grpdAccept;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileB64() {
        return fileB64;
    }

    public void setFileB64(String fileB64) {
        this.fileB64 = fileB64;
    }

    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopicAsString() {
        return (!Validator.isBlank(this.topic))?getTopicString():StringPool.BLANK;
    }

    private String getTopicString() {
        final String returnTopicAsStr;
        switch (this.topic) {
            case "1":
                returnTopicAsStr = "Cumplimiento de la legislación";
                break;
            case "2":
                returnTopicAsStr = "Buen uso de los recursos públicos";
                break;
            case "3":
                returnTopicAsStr = "Eficacia y diligencia";
                break;
            case "4":
                returnTopicAsStr = "Buen uso de la información";
                break;
            case "5":
                returnTopicAsStr = "Trato digno y respetuoso a las personas";
                break;

            case "6":
                returnTopicAsStr = "Trato igualitario a clientes y proveedores";
                break;
            case "7":
                returnTopicAsStr = "Salvaguardar la seguridad y la salud";
                break;
            case "8":
                returnTopicAsStr = "Respeto al medio ambiente y al patrimonio";
                break;
            case "9":
                returnTopicAsStr = "Salvaguardar la reputación de Emasesa";
                break;
            default: // 0
                returnTopicAsStr = "Seleccione una temática";
                break;
        }
        return returnTopicAsStr;
    }

    public boolean isValid(String formType) {
        return isValidBase() &&
                isValidBuzon(formType) &&
                isValidFormType(formType);

    }

    private boolean isValidFormType(String formType) {
        return BUZON_DINAMICO.equals(formType) || BUZON_UNICO.equals(formType) || BUZON_COM_ESTACIONES.equals(formType) || !Validator.isBlank(nif);
    }

    private boolean isValidBuzon(String formType) {
        return isValidMessage(formType) || isValidTopicFactAndPeople(formType) || isValidExpedienteAndCompany(formType);
    }

    private boolean isValidMessage(String formType) {
        return isBUnicoOrBDinamico(formType) && !Validator.isBlank(message);
    }

    private boolean isValidTopicFactAndPeople(String formType) {
        return isBEtico(formType) && !Validator.isBlank(topic)
                && !Validator.isBlank(reportedFact) && !Validator.isBlank(affectedPeople);
    }

    private boolean isValidExpedienteAndCompany(String formType) {
        return isBComEstaciones(formType) && !Validator.isBlank(numExpediente);
    }

    private boolean isBComEstaciones(String formType) {
        return BUZON_COM_ESTACIONES.equals(formType);
    }

    private boolean isBEtico(String formType) {
        return BUZON_ETICO.equals(formType);
    }

    private boolean isBUnicoOrBDinamico(String formType) {
        return BUZON_UNICO.equals(formType) || BUZON_DINAMICO.equals(formType);
    }


    private boolean isValidBase() {
        return isValidName() && isValidEmail() && isValidGRPD();
    }

    private boolean isValidName() {
        return !Validator.isBlank(name) && !Validator.isBlank(surnames);
    }

    private boolean isValidEmail() {
        return !Validator.isBlank(email) && Validator.isEmailAddress(email);
    }

    private boolean isValidGRPD() {
        return !Validator.isBlank(grpdAccept) && Boolean.parseBoolean(grpdAccept);
    }

    public boolean containsAttachment() {
        return !Validator.isBlank(fileName) && !Validator.isBlank(fileB64);
    }

    public File getAttachedFile() {
        File file = null;
        if(this.containsAttachment()) {
            int fileNameSep = fileName.lastIndexOf(StringPool.PERIOD);
            try {
                File tempFile;
                if(fileNameSep < 0) {
                    tempFile = File.createTempFile(fileName, null);
                } else {
                    tempFile = File.createTempFile(fileName.substring(0, fileNameSep),fileName.substring(fileNameSep, fileName.length()));
                }
                String[] fileData = fileB64.split(StringPool.COMMA);
                byte[] fileBytes =  Base64.decode(fileData[fileData.length - 1]);
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(fileBytes);

                    file = tempFile;
                } catch (IOException e) {
                    logger.error("Error at get attached file from FormData. FileName: " + fileName, e);
                }
            } catch (IOException e) {
                logger.error("Error at create temporal file to store attached file. FileName: " + fileName, e);
            }
        }

        return file;
    }
}
