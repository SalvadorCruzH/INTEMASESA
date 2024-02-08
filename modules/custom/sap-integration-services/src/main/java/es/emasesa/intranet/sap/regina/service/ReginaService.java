//package es.emasesa.intranet.sap.regina.service;
//
//import com.espiralms.paw.ObjectFactory;
//import com.espiralms.paw.ProactivaNETServices;
//import com.espiralms.paw.ProactivaNETServicesSoap;
//import com.liferay.portal.kernel.log.Log;
//import com.liferay.portal.kernel.log.LogFactoryUtil;
//import com.liferay.portal.kernel.module.configuration.ConfigurationException;
//import com.sun.xml.ws.api.message.Headers;
//import com.sun.xml.ws.developer.WSBindingProvider;
//import com.espiralms.paw.*;
//import es.emasesa.intranet.sap.base.logging.LogInterceptor;
//import es.emasesa.intranet.sap.util.SapConfigurationUtil;
//import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
//import jakarta.xml.ws.handler.Handler;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.annotation.PostConstruct;
//import javax.xml.namespace.QName;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.List;
//
//@org.springframework.stereotype.Component("reginaService")
//public class ReginaService {
//
//    public void guardarTiquetRegina(String userId, String title/*, String creationDate, String typeId, String portfolioId, String categoryId, String sourceId*/, String description){
//        if (LOG.isDebugEnabled()) {
//            LOG.debug("entro en guardar tiquet regina");
//        }
//        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
//        try {
//            ClassLoader objectFactoryClassLoader =ProactivaNETServicesSoap.class.getClassLoader();
//            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
//            String incidencia= port.newIncidentWithPortfolioAndDescription(userId, title, "", "", "", "","",description);
//            /*NewIncidentWithPortfolioAndDescription tiquet = getObjectFactory().createNewIncidentWithPortfolioAndDescription();
//            tiquet.setUserId(userId);
//            tiquet.setTitle(title);
//            tiquet.setDescription(description);*/
//            if (LOG.isDebugEnabled()) {
//                LOG.debug("tiquet regina: "+incidencia);
//            }
//        } catch (Exception e) {
//            LOG.error("Se ha producido un error al intentar acceder a WS de reginaService", e);
//        }
//
//    }
//
//    private ObjectFactory getObjectFactory() {
//        return new ObjectFactory();
//    }
//
//
//    @PostConstruct
//    public void activate() {
//
//        if (LOG.isDebugEnabled()) {
//            LOG.debug("[I] Activando ReginaService");
//        }
//        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
//        SapServicesConfiguration configuration = null;
//        try {
//            configuration = sapConfigurationUtil.getConfiguration();
//            ClassLoader objectFactoryClassLoader = ProactivaNETServicesSoap.class.getClassLoader();
//            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
//
//            URL urlEndpoint = new URL(configuration.reginaEndpoint());
//            ProactivaNETServices service = new ProactivaNETServices(urlEndpoint);
//            port = service.getPort(ProactivaNETServicesSoap.class);
//
//            /******************* Header ******************************/
//            WSBindingProvider bp = ((WSBindingProvider) port);
//            List<Handler> handlerChain =  bp.getBinding().getHandlerChain();
//            handlerChain.add(new LogInterceptor());
//            bp.getBinding().setHandlerChain(handlerChain);
//            if (LOG.isDebugEnabled()) {
//                LOG.debug("[I] configuration.reginaReferee: "+configuration.reginaReferee());
//            }
//            bp.setOutboundHeaders(
//                    Headers.create(new QName("Referee"),configuration.reginaReferee()/*"F228BBDFF275A6DD23C0F40F8D523C62"*/)
//            );
//
//            /**********************************************************************/
//            LOG.info(this.getClass().getName() +" cargado correctamente");
//        } catch (ConfigurationException e) {
//            if (LOG.isInfoEnabled()) {
//                LOG.info("Se ha producido un error instanciando el servicio de reginaService");
//            }
//        } catch (MalformedURLException e) {
//            if (LOG.isInfoEnabled()) {
//                LOG.info("Error en el WSDL de ReginaService --> " + configuration.reginaEndpoint());
//            }
//        } finally {
//            Thread.currentThread().setContextClassLoader(currentClassLoader);
//        }
//
//        if (LOG.isDebugEnabled()) {
//            LOG.debug("[E] ReginaService");
//        }
//    }
//
//    private ProactivaNETServicesSoap port;
//    @Autowired
//    SapConfigurationUtil sapConfigurationUtil;
//
//    private static final Log LOG = LogFactoryUtil.getLog(ReginaService.class);
//
//}
