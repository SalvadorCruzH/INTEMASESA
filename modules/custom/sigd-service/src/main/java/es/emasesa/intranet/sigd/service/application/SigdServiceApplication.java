package es.emasesa.intranet.sigd.service.application;

import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.template.ServiceLocator;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.base.constant.EmasesaConstants;
import es.emasesa.intranet.base.util.CustomObjectUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.settings.osgi.SigdServicesSettings;
import es.emasesa.intranet.sigd.service.constans.SidgServiceKeys;

@Component(
		configurationPid = "es.emasesa.intranet.settings.configuration.SigdServiceConfiguration",
		immediate = true, 
		property = {}, 
		service = SigdServiceApplication.class
)
public class SigdServiceApplication{
	
	
	/**
     * Llamada al servicio para recuperar los metadatos que hay que rellenar dada una serie y tipo documental.
     * @param objectName
     * @param tipoDocumental
	 * @return 
     * 
     */
	public String crearDocumentoOrigen(String objectName, String tipoDocumental) {
		String responseBody = StringPool.BLANK;
		try {
		
			String url = _configuration.crearDocumentoOrigenEndPoint();
			LoggerUtil.debug(LOG,"Se obtiene URL del enpoint: " + url);
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			LoggerUtil.debug(LOG,"Se crea un HHTP client con la autenticacion: " + httpClient.toString());
			
            URIBuilder builder = new URIBuilder(url);
            builder.addParameter(SidgServiceKeys.CREAR_DOCUMENTO_ID_ORIGEN, "1");
            builder.addParameter(SidgServiceKeys.CREAR_DOCUMENTO_SERIE_DOCUMENTAL, getConfigurationValue(objectName, SidgServiceKeys.CREAR_DOCUMENTO_SERIE_DOCUMENTAL));
            builder.addParameter(SidgServiceKeys.CREAR_DOCUMENTO_TIPO_DOCUMENTAL, getConfigurationValue(objectName, tipoDocumental));
            builder.addParameter(SidgServiceKeys.CREAR_DOCUMENTO_USUARIO_INDEXACION, "LoginUsuario");
            LoggerUtil.debug(LOG,"Se añaden parámetros a : " + builder.build());
			
			HttpGet get = new HttpGet(builder.build());
			get.addHeader(HttpHeaders.AUTHORIZATION, getBasicAuthentication());
			LoggerUtil.debug(LOG,"Peticion GET construida: " + get.toString());
			
			CloseableHttpResponse response = httpClient.execute(get);
			LoggerUtil.debug(LOG,"Respuesta obtenida" + response.toString());
			
			HttpEntity responseEntity = response.getEntity();
		    responseBody = EntityUtils.toString(responseEntity);
		    LoggerUtil.debug(LOG, "Entidad obtenida tras la ejecucion del get: " + responseBody);
		    
			 response.close();
			 httpClient.close();
		} catch (IOException e) {
			LoggerUtil.error(LOG, "Error al ejecutar la llamada get del servicio de crear documento origen de SIGD: ", e);
		} catch (URISyntaxException e) {
			LoggerUtil.error(LOG, "Error al construir la URI con parámetros en el servicio de crear documento origen de SIGD: ", e);
		}
		return responseBody;
	}
	
	
	 /**
     * Llamada al servicio para insertar un documento en SIGD pasando un pdf en base64
     * @param pdf en base 64
     * @return id del elemento
     * 
     */
	public String insertarDocumento(Map<String, Serializable> workflowContext, String pdf, String objectName, String tipoDocumental, long userId, Boolean anexoPortafirmas) {
		
		String idElemento = StringPool.BLANK;
		try {
			String url = _configuration.insertarDocumentoEndPoint();
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
	        HttpPost post = new HttpPost(url);
	        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
	        LoggerUtil.debug(LOG, "Creacion de HtpPost a la URL: " + url);	        
	        
			JSONObject jsonObject = createBody(workflowContext, pdf, objectName, tipoDocumental, userId, anexoPortafirmas);
			
		    String json = jsonObject.toString();
		    StringEntity jsonEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
		    
		    post.addHeader(HttpHeaders.AUTHORIZATION, getBasicAuthentication());
			post.setEntity(jsonEntity);
			
			LoggerUtil.debug(LOG, "Ejecucion de la solicitud post: " + post.toString());
			CloseableHttpResponse response = httpClient.execute(post);
			LoggerUtil.debug(LOG, "Respuesta obtenida: " + response.toString());
	        HttpEntity responseEntity = response.getEntity();
	        String responseBody = EntityUtils.toString(responseEntity);
	        LoggerUtil.debug(LOG, "Entidad obtenida tras la ejecucion del post: " + responseBody);
	       
	        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject(responseBody);

	        // Obtener el valor de la propiedad "idDocumentoFN"
	        idElemento = jsonResponse.getString(SidgServiceKeys.INSERTAR_DOCUMENTO_RESPONSE_NAME);
	        LoggerUtil.debug(LOG, "idElemento: " + idElemento);
	        
	        response.close();
			httpClient.close();
		} catch (IOException e) {
			  LoggerUtil.error(LOG, "Error al ejecutar la llamada post del servicio de insertar documento en SIGD: ", e);
		} catch (PortalException e) {
			 LoggerUtil.error(LOG, "Error al ejecutar la llamada post del servicio de insertar documento en SIGD: ", e);
		}
		
		return idElemento;
	}
	
	/**
     * Save document on SIGD
     * @param pdf
     * @param objectName
     * @param tipoDocumental
     * 
     */
    public String saveDocumentOnSIGD(Map<String, Serializable> workflowContext, String pdf, String objectName, String tipoDocumental, long userId, Boolean anexoPortaFirmas) {
    	
    	LoggerUtil.debug(LOG, "Guardando el documento en el gestor documnetal SIGD. Para el formulario con el tipo documental: " + tipoDocumental);
    	String idDocument = insertarDocumento(workflowContext, pdf, objectName, tipoDocumental, userId, anexoPortaFirmas);
    	LoggerUtil.debug(LOG, "Documento almacenado: " + idDocument);
    	return idDocument;
    }
	
	 /**
	  * Llamada al servicio para obtener los metadatos del documento.
	  * tipo=1 y procesarContenidos=false
	  * @param idElemento obtenido en insertarDocumento()
	  * 
	  * @return responseBody
	 */
	public String obtenerElemento(String idElemento) {
		
		String responseBody = StringPool.BLANK;
		try {
			String url = _configuration.obtenerElementoEndPoint();
			LoggerUtil.debug(LOG,"Se obtiene URL del enpoint: " + url);
			LoggerUtil.debug(LOG,"idElemento: " + idElemento);
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			LoggerUtil.debug(LOG,"Se crea un HHTP client con la autenticacion: " + httpClient.toString());
			
	        URIBuilder builder = new URIBuilder(url);
	        builder.addParameter(SidgServiceKeys.OBTENER_ELEMENTO_TIPO, "1");
	        builder.addParameter(SidgServiceKeys.OBTENER_ELEMENTO_ID, idElemento);
	        builder.addParameter(SidgServiceKeys.OBTENER_ELEMENTO_PROCESAR, "true");
	        builder.addParameter(SidgServiceKeys.OBTENER_ELEMENTO_VERSION, "ultima");
	        builder.addParameter(SidgServiceKeys.OBTENER_ELEMENTO_ID_SISTEMA_ORIGEN, "1");
	        
	        LoggerUtil.debug(LOG,"Se añaden parámetros a : " + builder.build());
			
			HttpGet get = new HttpGet(builder.build());
			get.addHeader(HttpHeaders.AUTHORIZATION, getBasicAuthentication());
			LoggerUtil.debug(LOG,"Peticion GET construida: " + get.toString());
			
			CloseableHttpResponse response;
			response = httpClient.execute(get);
			LoggerUtil.debug(LOG,"Respuesta obtenida" + response.toString());
			HttpEntity responseEntity = response.getEntity();
		    responseBody = EntityUtils.toString(responseEntity);
		    LoggerUtil.debug(LOG, "Entidad obtenida tras la ejecucion del get: " + responseBody);
			
			 response.close();
			 httpClient.close();
        
		} catch (URISyntaxException e) {
			 LoggerUtil.error(LOG, "Error al intentar construir la URI en el servicio de obtener Elemento en SIGD: ", e);
		} catch (IOException e) {
			 LoggerUtil.error(LOG, "Error en el servicio de obtener Elemento en SIGD: ", e);
		}
		
		return responseBody;
	}
	
	
	/**
	  * Se obtiene URL de descarga del SIGD para un id
	  * @param idElemento
	  * 
	  * @return urlDescarga
	  * 
	 */
	public String getUrlDescarga(String idElemento) {
		
		LoggerUtil.debug(LOG,"Se procede a obtener la URL de descarga en SIGD del documento con id: " + idElemento);
		String jsonString = obtenerElemento(idElemento);
		LoggerUtil.debug(LOG,"jsonString: " + jsonString);
		String urlDescarga = StringPool.BLANK;
		try {
	       JSONObject jsonObject = JSONFactoryUtil.createJSONObject(jsonString);
	       if(Validator.isNotNull(jsonObject)) {
	    	   urlDescarga = jsonObject.getJSONObject("obtenerElementoResponse").getJSONObject("documentoOrigen").getString("urlDescarga");
	       }
	       LoggerUtil.debug(LOG, "URL de Descarga: " + urlDescarga);
	    } catch (Exception e) {
	       LoggerUtil.error(LOG, "Error al intentar obtener la URL de descarga del SIGD: ", e);
	    }
		return urlDescarga;
	}
	
	
	/**
	  * Se obtiene URL de descarga de un documento de un objectEntry consultando el campo del object sIGDIds
	  * @param nombreDocumento
	  * @param objectEntryId
	  * 
	  * @return url
	  * 
	 */
	public String getUrlObjectEntryByDocumentName(String nombreDocumento, long objectEntryId){
		ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
		String url = StringPool.BLANK;
		
		try {
			LoggerUtil.debug(LOG,"Se procede a obtener: " + nombreDocumento + " para el objecteEntry: " + objectEntryId);
			Thread.currentThread().setContextClassLoader(actualClassLoader);
			if (_customObjectUtil == null){
	            activate(null);
	        }
			LoggerUtil.debug(LOG,"Obteniendo el valor del campo sIGDIds del objecyEntry: " + objectEntryId);
			String sIGDIds = _customObjectUtil.getValueObjectField(objectEntryId, "sIGDIds");
			LoggerUtil.debug(LOG,"Obtenido sIGDIds: " + sIGDIds);
			
			if(Validator.isNotNull(sIGDIds) && !(sIGDIds.isEmpty())){
				LoggerUtil.debug(LOG,"Procedemos a recuperar el Id del documento: " + nombreDocumento);
				String documentId = getIdSigdByDocumentName(sIGDIds, nombreDocumento);
				LoggerUtil.debug(LOG,"Obtenido el id: " + documentId);				
				if(Validator.isNotNull(documentId) && !(documentId.isEmpty())) {
					LoggerUtil.debug(LOG,"Obteniendo url de descarga" + documentId);
					url = getUrlDescarga(documentId);
					LoggerUtil.debug(LOG,"URL obtenida" + url);
				}else {
					LoggerUtil.debug(LOG,"No existe documento con nombre: " + nombreDocumento + " para el objectEntry: " + objectEntryId);
				}				
			}else {
				LoggerUtil.debug(LOG,"El campo sIGDIds del objectEntry es vacío.");
			}
		
		}finally {
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }
		
		return url;
	}
	
	/**
	  * Obtiene el ID de un documento por su nombre
	  * @param sIGDIds
	  * @param nombreDocumento
	  * 
	  * @return documentId
	  * 
	 */
	public String getIdSigdByDocumentName(String sIGDIds, String nombreDocumento) {
		String documentId = StringPool.BLANK;
		
		try {
			
			LoggerUtil.debug(LOG,"Se procede a obtener: " + nombreDocumento + " del JSON: " + sIGDIds);
            JSONArray jsonArray = JSONFactoryUtil.createJSONArray(sIGDIds);
            LoggerUtil.debug(LOG,"Se convierte a Array: " + jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                LoggerUtil.debug(LOG," Se obtiene el objeto JSON en la posición i: " + jsonObject);

                if (nombreDocumento.equals(jsonObject.getString("documentName"))) {
                	 LoggerUtil.debug(LOG," Verificar si el documentName coincide: " + nombreDocumento);
                	documentId = jsonObject.getString("idSIGD");
               	 	LoggerUtil.debug(LOG,"Obtenido el documentId: " + documentId);
                    break; 
                }
            }
        } catch (JSONException e) {
        	LoggerUtil.error(LOG, "Error al intentar recuperar el id del sigd: ", e);
        }
		
		return documentId;
		
	}
	
	 /**
	  * Llamada al servicio para obtener el contenido del documento en base 64
	  * @param idElemento obtenido en insertarDocumento()
	  * 
	 */
	public void obtenerContenido(String idElemento) {
		
		try {
			String url = _configuration.obtenerContenidoEndPoint();
			LoggerUtil.debug(LOG,"Se obtiene URL del enpoint: " + url);
			LoggerUtil.debug(LOG,"idElemento: " + idElemento);
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			LoggerUtil.debug(LOG,"Se crea un HHTP client con la autenticacion: " + httpClient.toString());
			
	        URIBuilder builder = new URIBuilder(url);
	        builder.addParameter(SidgServiceKeys.OBTENER_CONTENIDO_ID, idElemento);
	        builder.addParameter(SidgServiceKeys.OBTENER_CONTENIDO_VERSION, "ultima");
	        builder.addParameter(SidgServiceKeys.OBTENER_CONTENIDO_DESCARGA, "true");
	        
	        LoggerUtil.debug(LOG,"Se añaden parámetros a : " + builder.build());
			
			HttpGet get = new HttpGet(builder.build());
			get.addHeader(HttpHeaders.AUTHORIZATION, getBasicAuthentication());
			LoggerUtil.debug(LOG,"Peticion GET construida: " + get.toString());
			
			CloseableHttpResponse response;
			response = httpClient.execute(get);
			LoggerUtil.debug(LOG,"Respuesta obtenida" + response.toString());
			HttpEntity responseEntity = response.getEntity();
		    String responseBody = EntityUtils.toString(responseEntity);
		    LoggerUtil.debug(LOG, "Entidad obtenida tras la ejecucion del get: " + responseBody);
			
			 response.close();
			 httpClient.close();
			 
		} catch (URISyntaxException e) {
			 LoggerUtil.error(LOG, "Error al intentar construir la URI en el servicio deobtener Contenido en SIGD: " + e.toString());
		} catch (IOException e) {
			LoggerUtil.error(LOG, "Error al ejecutar la llamada get del servicio de obtener contenido de SIGD: " + e.toString());
		}	    
	}
	
	 /**
     * Se realiza una autenticacion básica por usuario y contraseña obteniendo valores de la configuración del sistema
     * @return authorization token
     * 
     */
	public String getBasicAuthentication() {
		
		LoggerUtil.debug(LOG,"Creando una autenticacion básica con usuario: " + _configuration.getUser() + " y contraseña: " + _configuration.getPassword());
		String auth = _configuration.getUser() + ":" + _configuration.getPassword();
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
		String authHeader = "Basic " + new String(encodedAuth); ;
        LoggerUtil.debug(LOG,"Creada AUTHORIZATION: " + authHeader);
        
        return authHeader;
	}
	
	 /**
	 * Creación del JSON que se debe enviar en la llamada al servicio de insertarDocumento
	 * @param pdf en base 64
	 * @param nombre del object
	 * @param tipo del documento que se quiere subir
	 * 
	 * @return json con el body
	  * 
	 */
	 public JSONObject createBody(Map<String, Serializable> workflowContext, String pdf, String objectName, String tipoDocumental, long userId, Boolean anexoPortaFirmas){
		 
		//String objectName = GetterUtil.getString(workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_TYPE));
		LoggerUtil.debug(LOG, "Object Name: " + objectName);
		
		LoggerUtil.debug(LOG, "Creando el JSON del body" );
		/*Creamos la cabecera del JSON*/
		JSONObject json = JSONFactoryUtil.createJSONObject();
		json.put(SidgServiceKeys.CREAR_DOCUMENTO_ID_ORIGEN, "1");
		json.put(SidgServiceKeys.CREAR_DOCUMENTO_SERIE_DOCUMENTAL, getConfigurationValue(objectName, SidgServiceKeys.CREAR_DOCUMENTO_SERIE_DOCUMENTAL));
		json.put(SidgServiceKeys.CREAR_DOCUMENTO_TIPO_DOCUMENTAL, getConfigurationValue(objectName, tipoDocumental));
		json.put(SidgServiceKeys.CREAR_DOCUMENTO_USUARIO_INDEXACION, _configuration.getUsuarioIndexacion());
		LoggerUtil.debug(LOG, "Cabecera creada: " + json.toString());	
		 
		 /*Agrega el JSONArray "campos" al JSON principal*/
		LoggerUtil.debug(LOG, "Añadiendo los campos con los metadatos" );
		/*Este método ahora mismo no se usa, ya que estamos obteniendo los valores del servicio. Dejar por si a futuro no se quiere llamar al servicio
		 * String metadatos = getConfigurationValue(objectName, SidgServiceKeys.FORM_METADATOS);
		LoggerUtil.debug(LOG, "Se recuperan los metadatos en forma de String de la configuracion del sistema: " + metadatos );*/
		JSONArray metadatosService = obtainMetadatosWithService(objectName, tipoDocumental);
		JSONArray metadatosWithValues = addMetadatosValue(metadatosService, workflowContext, userId, anexoPortaFirmas);
		json.put(SidgServiceKeys.CREAR_DOCUMENTO_CAMPOS, metadatosWithValues);
		LoggerUtil.debug(LOG, "Añadido campos de los metadatos: " + json.toString());	
		 
		 /*Agrega el JSONArray "ficheros" al JSON principal*/
		LoggerUtil.debug(LOG, "Añadiendo los ficheros" );
		JSONArray ficherosArray = JSONFactoryUtil.createJSONArray();
		JSONObject fichero = JSONFactoryUtil.createJSONObject();
		fichero.put(SidgServiceKeys.CREAR_DOCUMENTO_NUMERO_ORDEN, "1");
		fichero.put(SidgServiceKeys.CREAR_DOCUMENTO_EXTENSION, "pdf");
		fichero.put(SidgServiceKeys.CREAR_DOCUMENTO_FICHERO, pdf);
		ficherosArray.put(fichero);
		json.put(SidgServiceKeys.CREAR_DOCUMENTO_FICHEROS, ficherosArray);
		LoggerUtil.debug(LOG, "Añadido ficheros: " + json.toString());	
			
		LoggerUtil.debug(LOG, "Creado el JSON del body: "+  json);	
		
		return json;
	 }
	 
	 /**
	  * Se añaden valores a los campos metadatos
	 * @param metadatosService
	 * @param workflowContext
	 * 
	 * @return JSONArray campos con los metadatos con sus valores añadidos
	  * 
	 */
	 public JSONArray addMetadatosValue(JSONArray metadatosService, Map<String, Serializable> workflowContext, long userId, Boolean envioPortaFirmas) {
		 
		 try {
			 if (Validator.isNotNull(metadatosService)) {
				 if (ServiceContextThreadLocal.getServiceContext() != null) {
						userId = ServiceContextThreadLocal.getServiceContext().getUserId();
				 }
				 long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
				 Date today = new Date();
				 SimpleDateFormat todayFormat = new SimpleDateFormat("dd-MM-yyyy");
				 String dni = StringPool.BLANK;
				 String fechaDocumento = todayFormat.format(today);
				 String numeroPersonal = StringPool.BLANK;
				 String nombre = StringPool.BLANK;
				 
				 if(envioPortaFirmas) {
					 LoggerUtil.debug(LOG, "El documento se envía al portatirmas, se recuperan los valores del usuario actual del workflow: " + userId);
					
					 dni = _expandoValueLocalService.getValue(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, EmasesaConstants.EMASESA_EXPANDO_NIF, userId).getString();
					 numeroPersonal = _expandoValueLocalService.getValue(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, EmasesaConstants.EMASESA_EXPANDO_MATRICULA, userId).getString();
					 nombre = _expandoValueLocalService.getValue(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, EmasesaConstants.EMASESA_EXPANDO_NOMBRE, userId).getString();
				 }else{
					 if(workflowContext.size()>0){
						 LoggerUtil.debug(LOG, "El documento no se envía al portafirmas, se recuperan los valores del object.");
				         long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
				    	 ObjectEntry objectEntry = _objectEntryLocalService.fetchObjectEntry(classPK);
				         Map<String,Serializable> values = objectEntry.getValues();
						 dni = (String) values.get(EmasesaConstants.EMASESA_EXPANDO_NIF);
						 numeroPersonal = (String) values.get(EmasesaConstants.NUMERO_MATRICULA);
						 nombre = (String) values.get(EmasesaConstants.EMASESA_EXPANDO_NOMBRE);
					 }
				 }
				 
				 LoggerUtil.debug(LOG, "DNI: " + dni);
				 LoggerUtil.debug(LOG, "FechaDocumento: " + fechaDocumento);
				 LoggerUtil.debug(LOG, "NumeroPersonal: " + numeroPersonal);
				 LoggerUtil.debug(LOG, "Nombre: " + nombre);
				 
				 LoggerUtil.debug(LOG, "Añadiendo valores a los metadatos del SIGD" );
			        for (int i = 0; i < metadatosService.length(); i++) {
			            JSONObject campoJson = metadatosService.getJSONObject(i);
			            String nombreCampo = campoJson.getString("nombreOrigen").trim();
			            LoggerUtil.debug(LOG, "Metadato: " + nombreCampo);
	
			            switch (nombreCampo) {
			                case SidgServiceKeys.SIGD_METADATO_DNI:
			                    campoJson.put("stringValue", dni);
			                    LoggerUtil.debug(LOG, "Añadido dni: " + dni);
			                    break;
			                case SidgServiceKeys.SIGD_METADATO_FECHA_DOC:
			                    campoJson.put("stringValue", fechaDocumento);
			                    LoggerUtil.debug(LOG, "Añadido fechaDocumento: " + fechaDocumento);
			                    break;
			                case SidgServiceKeys.SIGD_METADATO_NUM_PERSONAL:
			                    campoJson.put("stringValue", numeroPersonal);
			                    LoggerUtil.debug(LOG, "Añadido numeroPersonal: " + numeroPersonal);
			                    break;
			                case  SidgServiceKeys.SIGD_METADATO_NOMBRE:
			                    campoJson.put("stringValue", nombre);
			                    LoggerUtil.debug(LOG, "Añadido nombre: " + nombre);
			                    break;
			                default:
			                    break;
			            }
			       }
			   }
		 }catch(PortalException e) {
	    	LoggerUtil.error(LOG, "Error al recuperar el valor de los expandos: ", e);
		 }
		return metadatosService;
	 }
	 
	 /**
	  * Se obtienen los metadatos llamando al servicio de crearDocumentoOrigen
	 * @param objectName
	 * @param tipoDocumental
	 * 
	 * @return JSONArray campos con los metadatos
	  * 
	 */
	 public JSONArray obtainMetadatosWithService(String objectName, String tipoDocumental) {
		 
		 JSONArray camposArray = null;
		 try {
			 String jsonStr = crearDocumentoOrigen(objectName, tipoDocumental);
			 LoggerUtil.debug(LOG, "Respuesta del servicio: "+  jsonStr);
	         JSONObject jsonObject = JSONFactoryUtil.createJSONObject(jsonStr);
	         JSONObject crearDocumentoOrigenResponse = jsonObject.getJSONObject(SidgServiceKeys.CREAR_DOCUMENTO_RESPONSE);
	         camposArray = crearDocumentoOrigenResponse.getJSONArray(SidgServiceKeys.CREAR_DOCUMENTO_CAMPOS);
	         LoggerUtil.debug(LOG, "Obtenidos metadatos: "+  camposArray.toString());

	        } catch (JSONException e) {
	        	LoggerUtil.error(LOG, "Error al crear el objecto JSON a partir de la respeusta del servicio de crearDocumentoOrigen: ", e);
	        }
		 return camposArray;
	 }
	 
	 
	 /**
	 * Creacion de metadatos a partir de la configuracion del sistema.
	 * @param metadatosCondifguracion
	 * 
	 * @return JSONArray campos con los metadatos
	 * 
	 */
	 public static JSONArray createJSONMedadatos(String metadatosCondifguracion) {
	    JSONArray metadatosResult = JSONFactoryUtil.createJSONArray();
	    LoggerUtil.debug(LOG, "Se crea el JSON con los metadatos obtenidos de la configuracion del sistema: " + metadatosCondifguracion);

	    try {
	    	// Crea un objeto JSON a partir de la cadena de entrada
	    	JSONObject metadatosConfig = JSONFactoryUtil.createJSONObject(metadatosCondifguracion.replace("\n", ""));
	        LoggerUtil.debug(LOG, "Crea un objeto JSON a partir de la cadena de entrada: " + metadatosConfig.toString());

	       for (String clave : metadatosConfig.keySet()) {
	          LoggerUtil.debug(LOG, "clave: " + clave);
	          JSONObject objetoTransformado = JSONFactoryUtil.createJSONObject();
	          objetoTransformado.put(SidgServiceKeys.FORM_METADATO_NOMBRE_ORIGEN, clave);
	          objetoTransformado.put(SidgServiceKeys.FORM_METADATO_TIPO_ORIGEN, metadatosConfig.getString(clave));
	          objetoTransformado.put(SidgServiceKeys.FORM_METADATO_STRING_VALUE, (String) null);
	          objetoTransformado.put(SidgServiceKeys.FORM_METADATO_INT_VALUE, 0);
	          objetoTransformado.put(SidgServiceKeys.FORM_METADATO_BOOLEAN_VALUE, false);
	          objetoTransformado.put(SidgServiceKeys.FORM_METADATO_DOUBLE_VALUE, 0);
	          objetoTransformado.put(SidgServiceKeys.FORM_METADATO_ARRAY_VALUE, (byte[]) null);
	          objetoTransformado.put(SidgServiceKeys.FORM_METADATO_DATE_VALUE, (String) null);
	          objetoTransformado.put(SidgServiceKeys.FORM_METADATO_NOMBRE_DESTINO, clave);
	          objetoTransformado.put(SidgServiceKeys.FORM_METADATO_TIPO_DESTINO, metadatosConfig.getString(clave));

	          metadatosResult.put(objetoTransformado);
	       }
	       LoggerUtil.debug(LOG, "Creado el objeto JSON con los metadatos: " + metadatosResult);
	   } catch (Exception e) {
	       LoggerUtil.error(LOG, "Error al crear el objecto JSON a partir del string de la configuracion: " + metadatosCondifguracion, e);
	   }
	   return metadatosResult;
	}
	 
	 /**
	     * Obtener el valor de las configuraciones del sistema para un formulario y un campo concreto
	     * @param objectName
	     * @param key
	     * @return value
	     * 
	     */
		public String getConfigurationValue(String objectName, String key) {
			
			LoggerUtil.debug(LOG, "Se obtiene el valor de la configuración");
			JSONObject jsonObject = getJSONObjectConfiguration(objectName);
			LoggerUtil.debug(LOG, "jsonObject obtenido con valor: " + jsonObject);
			String value = extractValue(jsonObject, key);
			LoggerUtil.debug(LOG, "value obtenido para el key: "+ key + " con valor: " + value);
			return value;
		}

		/**
	     * Extraer el valor de un campo de un JSON a partir de una key
	     * @param jsonObject
	     * @param key
	     * @return jsonValue
	     * 
	     */
		public String extractValue(JSONObject jsonObject, String key) {
		    String jsonValue = StringPool.BLANK;
		    LoggerUtil.debug(LOG, "Extrayendo el value con key: " + key +" del JSONObject: " + jsonObject.toString());
		    for (String jsonKey : jsonObject.keySet()) {
		        if (jsonKey.equals(key)) {
		            jsonValue = jsonObject.getString(key);
		            LoggerUtil.debug(LOG, "El valor de jsonValue es: " + jsonValue);
		            break;
		        } else if (jsonObject.get(jsonKey) instanceof JSONObject) {
		            jsonValue = extractValue(jsonObject.getJSONObject(jsonKey), key);
		            if (!jsonValue.isEmpty()) {
		                break;
		            }
		        }
		    }
		    return jsonValue;
		}
		
		/**
	     * Obtiene el JSONObject a partir del String almacenado en la configuración
	     * @param objectName
	     * @return jsonObject
	     * 
	     */
		public JSONObject getJSONObjectConfiguration(String objectName) {
			JSONObject jsonObject =  null;
		    try {
		        LoggerUtil.debug(LOG, "Obteniendo los valores del objectName: " + objectName);
		        String formConfiguration = getJSONConfigurationForm(objectName).replace("\n", "");
		        LoggerUtil.debug(LOG, "Se obtiene la configuracion del formulario: " + formConfiguration);
		        jsonObject = JSONFactoryUtil.createJSONObject(formConfiguration);
		        LoggerUtil.debug(LOG, "Creado el JSONObject a partir del String obtenido en la configuracion: " + jsonObject.toString());
		    } catch (Exception e) {
		        LoggerUtil.error(LOG, "Error al ejecutar la llamada get del servicio de crear documento origen de SIGD: ", e);
		    }
		    return jsonObject;
		}
		   
		   /**
		     * Obtener la configuraciones del sistema de un formulario por su nombre
		     * @param objectName
		     * @return formConfiguration
		     * 
		     */
		   public String getJSONConfigurationForm(String objectName) {
			   
				 String formConfiguration = StringPool.BLANK;
				 LoggerUtil.debug(LOG, "Codificar el nombre del objeto en UTF-8: " + objectName);
				 byte[] objectNameBytes = objectName.getBytes(StandardCharsets.UTF_8);
				 objectName = new String(objectNameBytes, StandardCharsets.UTF_8);
				 LoggerUtil.debug(LOG, "Codificado: " + objectName);
				   
				 switch (objectName.trim()) {
				    case SidgServiceKeys.FORM_COMPATIBILDIAD:
				    	formConfiguration = _configuration.getCompatibilidadesConfiguration();
				        break;
				    case SidgServiceKeys.FORM_NOMINAS:
				    	formConfiguration = _configuration.getNominasConfiguration();
				        break;
				    case SidgServiceKeys.FORM_PENSIONES:
				    	formConfiguration = _configuration.getPensionesConfiguration();
				        break;
				    case SidgServiceKeys.FORM_HORAS_EXTRAS:
				    	formConfiguration = _configuration.getHorasExtrasConfiguration();
				        break;
				    case SidgServiceKeys.FORM_VACACIONES:
				    	formConfiguration = _configuration.getVacacionesConfiguration();
				        break;
				    case SidgServiceKeys.FORM_MERCAJES:
				    	formConfiguration = _configuration.getMercajesConfiguration();
				        break;
				    case SidgServiceKeys.FORM_TRASLADOS:
				    	formConfiguration = _configuration.getTrasladosConfiguration();
				        break;
				    case SidgServiceKeys.FORM_JUBILADOS:
				    	formConfiguration = _configuration.getJubiladosConfiguration();
				        break;
				    case SidgServiceKeys.FORM_FLEXIBILIDAD_HORARIA:
				    	formConfiguration = _configuration.getFlexibilidadHorariaConfiguration();
				        break;
				    case SidgServiceKeys.FORM_REDUCCION_JORNADA:
				    	formConfiguration = _configuration.getReduccionJornadaConfiguration();
				        break;
				    case SidgServiceKeys.FORM_LICENCIAS:
				    	formConfiguration = _configuration.getLicenciasConfiguration();
				        break;
				    case SidgServiceKeys.FORM_EXEDENCIAS:
				    	formConfiguration = _configuration.getExedenciasConfiguration();
				        break;
				    case SidgServiceKeys.FORM_TURNOS:
				    	formConfiguration = _configuration.getTurnosConfiguration();
				        break;
				    case SidgServiceKeys.FORM_ANTICIPOS_VACACIONES:
				    	formConfiguration = _configuration.getAnticiposVacacionesConfiguration();
				        break;
				    case SidgServiceKeys.FORM_ANTICIPOS_REINTEGRABLES:
				    	formConfiguration = _configuration.getAnticiposReintegrablesConfiguration();
				        break;
				    case SidgServiceKeys.FORM_DOMICILIACION_BANCARIA:
				    	formConfiguration = _configuration.getDomiciliacionBancariaConfiguration();
				        break;
				    case SidgServiceKeys.FORM_IRPF:
				    	formConfiguration = _configuration.getIRPFConfiguration();
				        break;
				    case SidgServiceKeys.FORM_PRESTAMOS_VIVIENDA:
				    	formConfiguration = _configuration.getPrestamosViviendaConfiguration();
				        break;
				    case SidgServiceKeys.FORM_AYUDA_ESCOLAR:
				    	formConfiguration = _configuration.getAyudaEscolarConfiguration();
				        break;
				    case SidgServiceKeys.FORM_HISTORIAL_ACADEMICO:
				    	formConfiguration = _configuration.getHistorialAcademicoConfiguration();
				        break;
				    case SidgServiceKeys.FORM_SATISFACCION:
				    	formConfiguration = _configuration.getSatisfaccionConfiguration();
				        break;
				    case SidgServiceKeys.FORM_DATOS_PERSONALES:
				    	formConfiguration = _configuration.getDatosPersonalesConfiguration();
				        break;
				    case SidgServiceKeys.FORM_CONCURSOS_PROCESOS:
				    	formConfiguration = _configuration.getConcursosConfiguration();
				        break;
				    case SidgServiceKeys.FORM_TRIBUNALES:
				    	formConfiguration = _configuration.getTribunalesConfiguration();
				        break;
				    case SidgServiceKeys.FORM_SINDICALES:
				    	formConfiguration = _configuration.getSindicalesConfiguration();
				        break;
				    case SidgServiceKeys.FORM_FORMATIVAS:
				    	formConfiguration = _configuration.getFormativasConfiguration();
				        break;
				    default:
				    	LoggerUtil.debug(LOG, "No se ha encontrado la configuracion del formulario con nombre: " + objectName +" " +SidgServiceKeys.FORM_IRPF);
				}			   
			   return formConfiguration;
		   }
		   
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		this._customObjectUtil = (CustomObjectUtil) ServiceLocator.getInstance().findService("es.emasesa.intranet.base.util.CustomObjectUtil");
	}
	
	
	@Reference
	private SigdServicesSettings _configuration;
	
	@Reference
    ExpandoValueLocalService _expandoValueLocalService;
	
	private static final Log LOG = LogFactoryUtil.getLog(
			SigdServiceApplication.class);
	
	 @Reference
	private ObjectEntryLocalService _objectEntryLocalService;
	 
	private CustomObjectUtil _customObjectUtil;
}

