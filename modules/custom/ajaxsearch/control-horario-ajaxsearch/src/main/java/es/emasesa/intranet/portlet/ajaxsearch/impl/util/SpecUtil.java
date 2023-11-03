package es.emasesa.intranet.portlet.ajaxsearch.impl.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.constant.EmasesaConstants;
import es.emasesa.intranet.base.util.CustomDateUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.settings.osgi.SPECServicesSettings;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(
        immediate = true,
        service = SpecUtil.class
)
public class SpecUtil {

    public List<JSONObject> dbSPECSearch(String startDate, String endDate, String screenName) {
        Connection con = null;
        List<JSONObject> historico = new ArrayList<>();
        try {
            String user = _specServicesSettings.user();
            String password = _specServicesSettings.password();
            String url = _specServicesSettings.urlDBSpec();
            String query = _specServicesSettings.query();
            String marcPers = StringPool.BLANK;
            if (Validator.isNotNull(screenName)) {
                marcPers = "AND MARC_PERS IN(" + screenName + ")";
            }

            query = query.replaceAll(EmasesaConstants.REGEX_STARTDATE, StringPool.APOSTROPHE + startDate + StringPool.APOSTROPHE);
            query = query.replaceAll(EmasesaConstants.REGEX_ENDDATE, StringPool.APOSTROPHE + endDate + StringPool.APOSTROPHE);
            query = query.replaceAll(EmasesaConstants.REGEX_SCREENNAME, marcPers);
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            LoggerUtil.debug(LOG, "Oracle JDBC driver loaded ok.");

            con = DriverManager.getConnection("jdbc:oracle:thin:" + user + StringPool.FORWARD_SLASH + password + StringPool.AT + url);//TODO: GET FROM CONFIG
            LoggerUtil.debug(LOG, "Connected");
            Statement stmt = con.createStatement();

            ResultSet rows = stmt.executeQuery(query);
            ResultSetMetaData metadata = rows.getMetaData();
            int numberOfColumns = metadata.getColumnCount();

            while (rows.next()) {
                JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
                int i = 1;

                while (i <= numberOfColumns) {
                    jsonObject.put(metadata.getColumnName(i), rows.getString(i++));
                }
                historico.add(jsonObject);
            }
            LoggerUtil.debug(LOG, String.valueOf(rows.getFetchSize()));

        } catch (SQLException e) {
            LoggerUtil.error(LOG, e.getMessage());
            LoggerUtil.debug(LOG, e);
        } finally {
            if (Validator.isNotNull(con)) {
                try {
                    con.close();
                } catch (SQLException e) {
                    LoggerUtil.error(LOG, e.getMessage());
                    LoggerUtil.debug(LOG, e);
                }
            }

        }

        return historico;

    }

    public void processData(JSONArray jsonArray, ThemeDisplay themeDisplay, Map<String,List<JSONObject>> jsonGrouped) {

        for (String key : jsonGrouped.keySet()) {
            List<JSONObject> list = jsonGrouped.get(key);

            JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
            String date = list.get(0).getString(EmasesaConstants.SUBSTR_MARC_TMP_1_8);
            jsonObject.put("date", _customDateUtil.transformDateString(date, EmasesaConstants.YYYY_M_MDD, EmasesaConstants.DD_MM_YYYY));
            try {
                jsonObject.put(EmasesaConstants.DIA, _customDateUtil.getDateFieldDisplayName(themeDisplay.getLocale(), date, EmasesaConstants.YYYY_M_MDD,
                        Calendar.DAY_OF_WEEK, Calendar.LONG));
            } catch (java.text.ParseException e) {
                jsonObject.put(EmasesaConstants.DIA, StringPool.BLANK);
            }
            String marcPers = list.get(0).getString(EmasesaConstants.MARC_PERS);
            String editName = list.get(0).getString(EmasesaConstants.EDIT_NAME);
            jsonObject.put(EmasesaConstants.MARC_PERS, marcPers);
            jsonObject.put(EmasesaConstants.EDIT_NAME, editName);
            JSONArray jsonArrayAux = JSONFactoryUtil.createJSONArray();
            for (int i = 0; i < 8; i++) {
                if (i < list.size()) {

                    LocalTime time = LocalTime.parse(list.get(i).getString(EmasesaConstants.SUBSTR_MARC_TMP_9_4), DateTimeFormatter.ofPattern("HHmm"));
                    String timeString = time.format(DateTimeFormatter.ofPattern("HH:mm"));
                    jsonObject.put(String.valueOf(i), timeString);
                } else {
                    jsonObject.put(String.valueOf(i), StringPool.BLANK);
                }

            }
            jsonObject.put("list", jsonArrayAux);
            jsonArray.put(jsonObject);
        }
    }


    public  Map<String,List<JSONObject>> groupRows(List<JSONObject> listJson) {
        Map<String,List<JSONObject>> mapGroupedByName = new HashMap<>();
        List<JSONObject> auxList;
        for(JSONObject json : listJson){
            auxList = new ArrayList<>();
            String key = json.getString(EmasesaConstants.MARC_PERS)+json.getString(EmasesaConstants.SUBSTR_MARC_TMP_1_8);
            if(mapGroupedByName.containsKey(key)){
                auxList =mapGroupedByName.get(key);
                auxList.add(json);
                mapGroupedByName.put(key,auxList);
            }else{
                auxList.add(json);
                mapGroupedByName.put(key,auxList);

            }

        }
        return mapGroupedByName;
    }
    public  void orderByDateAndTime(List<JSONObject> listJson) {
        Collections.sort(listJson, (o1, o2) -> {
            String date1 = o1.getString(EmasesaConstants.SUBSTR_MARC_TMP_1_8);
            String time1 = o1.getString(EmasesaConstants.SUBSTR_MARC_TMP_9_4);
            String date2 = o2.getString(EmasesaConstants.SUBSTR_MARC_TMP_1_8);
            String time2 = o2.getString(EmasesaConstants.SUBSTR_MARC_TMP_9_4);
            return (date1+time1).compareTo(date2+time2);
        });
    }


    @Reference
    SPECServicesSettings _specServicesSettings;
    @Reference
    CustomDateUtil _customDateUtil;
    private static final Log LOG = LoggerUtil.getLog(SpecUtil.class);

}