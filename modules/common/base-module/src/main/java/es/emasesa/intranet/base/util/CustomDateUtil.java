package es.emasesa.intranet.base.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.DateUtil;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
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

    public String atStartOfDay(Date date,String format) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return startOfDay.format(DateTimeFormatter.ofPattern(format));
    }

    public String atEndOfDay(Date date,String format) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);

        return endOfDay.format(DateTimeFormatter.ofPattern(format));
    }


    public LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public String getDateStrFromTimestamp(long timestamp){
      DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime localDateTime =
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),
                            TimeZone.getDefault().toZoneId());

        return localDateTime.format(DTF);
    }

    public String getDateFieldDisplayName(Locale locale,String date,String format,int field,int style) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);//"yyyy-MM-dd"
        Date fecha = sdf.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        return calendar.getDisplayName(field, style, locale);

    }

    public int getDateNumber(Locale locale,String date,String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);//"yyyy-MM-dd"
        Date fecha = sdf.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        return calendar.get(Calendar.DAY_OF_MONTH);

    }

    private static final Log _log = LoggerUtil.getLog(CustomDateUtil.class);
}
