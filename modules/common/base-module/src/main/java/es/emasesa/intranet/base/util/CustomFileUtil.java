package es.emasesa.intranet.base.util;

import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.FileUtil;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component(
immediate = true,
property = {
},
service = CustomFileUtil.class)
public class CustomFileUtil {

	private final static Log LOG = LoggerUtil.getLog(CustomFileUtil.class);

	@SuppressWarnings("unchecked")
	@Activate
    @Modified
    protected void activate(Map<String, Object> properties) {

    }
	
	@Deactivate
    public void deactivate() {

    }

    public File createFile(final String path){
		File file;
		try {
			file = new File(path);
		}catch (Exception e) {
			file = null;
			LoggerUtil.error(_log, e.getMessage());
		}
		return file;
	}

	public File createDirectory(final String path){
		File file;
		try {
			file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
		}catch (Exception e) {
			file = null;
			LoggerUtil.error(_log, e.getMessage());
		}
		return file;
	}

	public File generateTemporalFile(final File uploadFile, final String nameFile) {
		final File tempFile;
		final String path = (uploadFile.getPath().split(uploadFile.getName())[0]).concat(nameFile);
		tempFile = new File(path);
		try {
			FileUtil.copyFile(uploadFile, tempFile);
		}
		catch (IOException e) {
			_log.error("Error saving file to temp folder", e);
		}

		return tempFile;
	}

	private static final Log _log = LoggerUtil.getLog(CustomMailUtil.class);

}
