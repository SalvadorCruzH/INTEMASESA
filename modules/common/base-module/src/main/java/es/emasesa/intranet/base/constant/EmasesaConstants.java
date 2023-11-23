package es.emasesa.intranet.base.constant;

public class EmasesaConstants {


    public static final int REST_CODE_OK = 0;
    public static final int REST_CODE_CHECK = 1;
    public static final int REST_CODE_ERROR = 2;
    public static final String REST_DFLT_OK_MSG = "OK";
    public static final String REST_DFLT_ERROR_MSG = "ERROR";
    public static final String EMASESA_ADMIN_SCREENNAME = "adminpersonas";
    public static final String EMASESA_EXPANDO_DOMICILIO = "domicilio";
    public static final String EMASESA_EXPANDO_NIF = "nif";
    public static final String EMASESA_EXPANDO_LOCALIDAD = "localidad";
    public static final String EMASESA_EXPANDO_PROVINCIA = "provincia";
    public static final String EMASESA_EXPANDO_CP = "codigoPostal";
    public static final String EMASESA_EXPANDO_TELEFONO = "telefono";
    public static final String EMASESA_EXPANDO_APELLIDO1 = "apellido1";
    public static final String EMASESA_EXPANDO_APELLIDO2 = "apellido2";
    public static final String EMASESA_EXPANDO_NOMBRE = "nombre";

    public static final String EMASESA_EXPANDO_USUARIO = "usuario";
    public static final String EMASESA_FOLDER_OBJECTS = "PDF Objects";
    public static final String EMASESA_MIMETYPE = "application/pdf";
    
    public static final String EMASESA_OBJECT_STATUS = "estadoObjeto";
    public static final String EMASESA_OBJECT_HISTORY = "historicoEstado";
    public static final String EMASESA_OBJECT_SIGD_IDS = "sIGDIds";

    //SPEC
    public static final String SUBSTR_MARC_TMP_1_8 = "SUBSTR(MARC_TMP,1,8)";
    public static final String SUBSTR_MARC_TMP_9_4 = "SUBSTR(MARC_TMP,9,4)";
    public static final String YYYY_M_MDD = "yyyyMMdd";
    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final String DIA = "dia";

    public static final String  FORMAT_DATE_DB = "yyyyMMddHHmm";
    public static final String  INIT_DATE_DB = "0";
    public static final String  FINAL_DATE_DB = "999999999999";
    public static final String REGEX_STARTDATE = "--startDate--";
    public static final String REGEX_ENDDATE = "--endDate--";
    public static final String REGEX_SCREENNAME = "--screenNameFilter--";
    public static final String MARC_PERS = "MARC_PERS";
    public static final String EDIT_NAME = "EDIT_NAME";
    
    //WORKFLOW
    public static final String WORKFLOW_USER_ID= "userId";
    public static final String WORKFLOW_HISTORICO= "historicoEstado";
    public static final String WORKFLOW_ROL= "rolName";
    public static final String WORKFLOW_FECHA= "fechaCambioEstado";
    public static final String DDMMYYYYHHmmss= "dd-MM-yyyy HH:mm:ss";
    public static final String USERS_GROUP="usersGroup";

    public static final String OBJECT_ENTRY_ID="objectEntryId";
    public static final String WORKFLOW_TASK_ID="workflowTaskId";
    public static final String ROLE_NAME_ASSIGN="roleNameAssign";
    public static final String NUMERO_MATRICULA="numeroDeMatricula";
    
    //SIGD
    public static final String SIGD_DOCUMENT_NAME="documentName";
    public static final String SIGD_ID="idSIGD";
    
    //PORTAFIRMAS
    public static final String PORTAFIRMAS_SIGN="sign";
    
    
    private EmasesaConstants(){}
}
