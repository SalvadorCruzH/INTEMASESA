package es.emasesa.intranet.searchframework.constants;

import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.text.Format;

public final class SearchingUtilKeys {

	private SearchingUtilKeys() {}

	// Long constants
	public static final Long LONG_INDEX_MIN_DATE = 19700101000000L;
	public static final Long LONG_INDEX_MAX_DATE = 21000101000000L;
	// String constants
	public static final String STRING_INDEX_MIN_DATE = "19700101000000";
	public static final String STRING_INDEX_MAX_DATE = "21000101000000";
	
	// Index Date Format
	public static final Format INDEX_DATE_FORMAT = FastDateFormatFactoryUtil.getSimpleDateFormat(PropsUtil
			.get(PropsKeys.INDEX_DATE_FORMAT_PATTERN));
	
	// Object Entry Content constant
	public static final String OBJECT_ENTRY_CONTENT = "objectEntryContent"; 
}
