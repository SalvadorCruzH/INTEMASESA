package es.emasesa.intranet.base.util;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPool;
import com.liferay.portal.kernel.log.Log;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.Map;

@Component(
immediate = true,
property = {
},
service = CustomCacheSingleUtil.class)
public class CustomCacheSingleUtil {
	
	@Reference
	private SingleVMPool singleVMPool;

	public PortalCache<String, Object> cache;
	protected static final String CACHE_NAME = CustomCacheSingleUtil.class.getName();

	public static final int TTL_A_MIN = 60;
	public static final int TTL_5_MIN = 300;
	public static final int TTL_10_MIN = 600;
	public static final int TTL_15_MIN = 900;
	public static final int TTL_30_MIN = 1800;
	public static final int TTL_A_DAY = 86400;
	public static final int TTL_A_HOUR = 3600;


 	// NOT RECOMMENDED
	public static final int TTL_INFINITE = 0;
	
    private final static Log LOG = LoggerUtil.getLog(CustomCacheSingleUtil.class);
	
	@SuppressWarnings("unchecked")
	@Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
		cache = (PortalCache<String, Object>) singleVMPool.getPortalCache(CACHE_NAME);
    }
	
	@Deactivate
    public void deactivate() {
		singleVMPool.removePortalCache(CACHE_NAME);
    }
	
	public void put(String key, Object value) {
		cache.put(key, value);
	}
	
	public void put(String key, Object value, int timeToLive) {
		cache.put(key, value, timeToLive);
	}
	
	public Object get(String key) {
		Object value = cache.get(key);
		
		if(LOG.isDebugEnabled()) {
			LOG.debug((value != null ? "HIT": "MISS") + " at get cached data (Single), with key:" + key.substring(0, Math.min(key.length(), 120))+"...");
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
