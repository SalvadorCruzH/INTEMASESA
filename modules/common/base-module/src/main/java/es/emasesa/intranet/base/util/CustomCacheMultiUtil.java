package es.emasesa.intranet.base.util;

import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.log.Log;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Component(
immediate = true,
property = {
},
service = CustomCacheMultiUtil.class)
public class CustomCacheMultiUtil {
	
	@Reference
	private MultiVMPool multiVMPool;

	public PortalCache<String, Serializable> cache;
	protected static final String CACHE_NAME = CustomCacheMultiUtil.class.getName();

	public static final int TTL_A_DAY = 86400;
	public static final int TTL_A_HOUR = 3600;
	
	// TODO: Check where is used TTL_INFINITE (for example portletPreferences), because if cache is independent by single node, 
	// at update portlet preferences some node could not be refreshed. Put TTL_A_DAY instead o verify ehCache XML config 
	//(Or clear cache through cluster at change preferences)
	public static final int TTL_INFINITE = 0;
	
    private final static Log LOG = LoggerUtil.getLog(CustomCacheMultiUtil.class);
	
	@SuppressWarnings("unchecked")
	@Activate
    @Modified
    protected void activate(Map<String, Serializable> properties) {
		cache = (PortalCache<String, Serializable>) multiVMPool.getPortalCache(CACHE_NAME);
    }
	
	@Deactivate
    public void deactivate() {
		multiVMPool.removePortalCache(CACHE_NAME);
    }
	
	public void put(String key, Serializable value) {
		cache.put(key, value);
	}
	
	public void put(String key, Serializable value, int timeToLive) {
		cache.put(key, value, timeToLive);
	}
	
	public Serializable get(String key) {
		Serializable value = cache.get(key);
		
		if(LOG.isDebugEnabled()) {
			LOG.debug((value != null ? "HIT": "MISS") + " at get cached data (Multi), with key:" + key);
		}
		
		return value;
	}
	
	public void remove(String key) {
		cache.remove(key);
	}
	
	public List<String> getKeys() {
		return cache.getKeys();
	}
	
	public void removeAll() {
		cache.removeAll();
	}

}
