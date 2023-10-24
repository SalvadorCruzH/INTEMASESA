package es.emasesa.intranet.sigd.service.application;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.core.Application;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.settings.configuration.SigdServiceConfiguration;


@Component(
		configurationPid = "es.emasesa.intranet.settings.configuration.SigdServiceConfiguration",
		immediate = true, 
		property = {}, 
		service = SigdServiceApplication.class
)
public class SigdServiceApplication extends Application {

	
	 /**
     * Llamada al servicio para inserdar un documento en SIGD
     * 
     */
	public void insertarDocumento() {
		try {
			String url = _configuration.insertarDocumentoEndPoint();
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			
			CredentialsProvider provider = new BasicCredentialsProvider();
	        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(_configuration.getUser(), _configuration.getPassword());
	        provider.setCredentials(AuthScope.ANY, credentials);
	        
	        LoggerUtil.debug(LOG, "Creacion de HtpPost a la URL: " + url);
	        HttpPost post = new HttpPost(url);
	        post.setHeader("Content-Type", "application/json");
	       
			JSONObject jsonObject = createBody();
		    
		    String json = jsonObject.toString();
		    StringEntity jsonEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
			post.setEntity(jsonEntity);
			
			// Ejecutar la solicitud POST
			LoggerUtil.debug(LOG, "Ejecucion de la solicitud post: " + post.toString());
			CloseableHttpResponse response = httpClient.execute(post);
			LoggerUtil.debug(LOG, "Respuesta obtenida: " + response.toString());
	        HttpEntity responseEntity = response.getEntity();
	        String responseBody = EntityUtils.toString(responseEntity);
	        LoggerUtil.debug(LOG, "Entidad obtenida tras la ejecucion del post: " + responseBody);
			
			httpClient.close();
		} catch (IOException e) {
			  LoggerUtil.error(LOG, "Error al ejecutar la llamada post del servicio de insertar documento en SIGD: " + e.toString());
		}
	}
	

	
	 /**
	  * Llamada al servicio para obtener los metadatos del documento. L
	  * tipo=1 y procesarContenidos=false
	  * 
	 */
	public void obtenerElemento() {
		String url = _configuration.obtenerElementoEndPoint();
		HttpGet get = new HttpGet(url);
	}
	
	 /**
	  * Llamada al servicio para obtener el contenido del documento en base 64
	  * 
	 */
	public void obtenerContenido() {
		
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			String url = _configuration.obtenerContenidoEndPoint();
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse response;
			response = httpClient.execute(get);
			LoggerUtil.debug(LOG,"Respuesta obtenida" + response.toString());
			
			String json = EntityUtils.toString(response.getEntity());
			if (json != null) {
				JSONArray data = JSONFactoryUtil.createJSONArray(json);
			}
			
			 response.close();
			 httpClient.close();
		} catch (IOException e) {
			LoggerUtil.error(LOG, "Error al ejecutar la llamada get del servicio de obtener contenido de SIGD: " + e.toString());
		} catch (JSONException e) {
			LoggerUtil.error(LOG, "Error al intentar crear un JSON array a partir de un string en el método de obtener contenido de SIGD: " + e.toString());
		}
	    
	}
	
	 /**
	  * Creación del JSON que se debe enviar en la llamada al servicio de insertarDocumento
	  * 
	 */
	 public JSONObject createBody() {
		 
		 JSONObject json = JSONFactoryUtil.createJSONObject();

	        // Agrega los valores directos al JSON
	        json.put("identificadorOrigen", "{{ID_SISTEMA_ORIGEN}}");
	        json.put("serieDocumental", "037");
	        json.put("tipoDocumental", "2111");
	        json.put("usuarioIndexacion", "loginUsuario");

	        // Crea un JSONArray para "campos"
	        JSONArray camposArray = JSONFactoryUtil.createJSONArray();

	        // Crea un JSONObject para cada campo y agr�galo al JSONArray
	        JSONObject campo1 = JSONFactoryUtil.createJSONObject();
	        campo1.put("nombreOrigen", "NumeroExpediente");
	        campo1.put("tipoOrigen", "java.lang.String");
	        campo1.put("stringValue", "003/2019");
	        campo1.put("intValue", 0);
	        campo1.put("booleanValue", false);
	        campo1.put("doubleValue", 0);
		    campo1.put("byteArrayValue", "");
		    campo1.put("dateValue", "");
	        campo1.put("nombreDestino", "NumeroExpediente");
	        campo1.put("tipoDestino", "java.lang.String");
	        camposArray.put(campo1);

	        // Agrega el JSONArray "campos" al JSON principal
	        json.put("campos", camposArray);

	        // Crea un JSONArray para "ficheros"
	        JSONArray ficherosArray = JSONFactoryUtil.createJSONArray();

	        // Crea un JSONObject para "ficheros" y agr�galo al JSONArray
	        JSONObject fichero1 = JSONFactoryUtil.createJSONObject();
	        fichero1.put("numeroOrden", "1");
	        fichero1.put("extension", "png");
	        fichero1.put("fichero", "{{FICHERO_PDF}");
	        ficherosArray.put(fichero1);

	        // Agrega el JSONArray "ficheros" al JSON principal
	        json.put("ficheros", ficherosArray);
	 
	        return json;
	 }
	
	
	private volatile SigdServiceConfiguration _configuration;

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {

	        _configuration = ConfigurableUtil.createConfigurable(
	        		SigdServiceConfiguration.class, properties);
	    }
	private static final Log LOG = LogFactoryUtil.getLog(
			SigdServiceApplication.class);

}

