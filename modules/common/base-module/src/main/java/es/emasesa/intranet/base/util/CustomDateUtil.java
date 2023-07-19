package es.emasesa.intranet.base.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.DateUtil;
import org.osgi.service.component.annotations.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


@Component(
        immediate = true,
        service = CustomDateUtil.class
)
public class CustomDateUtil {

    public  final String getApproximateTimeFromISO8601(final Locale locale, final String dateStr) {
        String approxTime;
        try {
            final Date parsedDate = DateUtil.getISO8601Format().parse(dateStr);
            approxTime = LanguageUtil.get(locale, "from")+"&nbsp;"+LanguageUtil.getTimeDescription(locale, System.currentTimeMillis() - parsedDate.getTime(), Boolean.TRUE);
        } catch (Exception e) {
            approxTime = StringPool.BLANK;
            LoggerUtil.error(_log, "Cant get ISO Format "+dateStr);
        }
        return approxTime;
    }

    public String transformDateString(String dateString,String originFormat,String destFormat){
        String date = StringPool.BLANK;
        SimpleDateFormat sdf = new SimpleDateFormat(originFormat);//yyyyMMddHHmmss
        SimpleDateFormat sdfTo = new SimpleDateFormat(destFormat);//dd/MM/yyyy HH:mm

        try {

            date = sdfTo.format(sdf.parse(dateString));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return date;
    }

    public String getDateStrFromTimestamp(long timestamp){
      DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime localDateTime =
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),
                            TimeZone.getDefault().toZoneId());

        return localDateTime.format(DTF);
    }

    private static final Log _log = LoggerUtil.getLog(CustomDateUtil.class);
}
