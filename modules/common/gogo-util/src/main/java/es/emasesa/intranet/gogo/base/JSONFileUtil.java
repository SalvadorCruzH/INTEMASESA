package es.emasesa.intranet.gogo.base;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JSONFileUtil {

    public static final JSONArray getJSONArrayFromFile(final String path){
        JSONArray jsonArray = null;
        try {
            jsonArray = JSONFactoryUtil.createJSONArray(readFile(path));
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static final JSONArray getJSONArrayFromFile(final URL url){
        JSONArray jsonArray = null;
        try {
            jsonArray = JSONFactoryUtil.createJSONArray(getContentUTF8(url));
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static final JSONObject getJSONObjectFromFile(final URL url){
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONFactoryUtil.createJSONObject(getContentUTF8(url));
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    private static final String readFile(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();

        try (final Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public static final String getContentUTF8(URL url) {
        try (java.io.Reader reader = new InputStreamReader(url.openStream(), "UTF-8")) {
            final StringBuilder content = new StringBuilder();
            final char[] buf = new char[1024];

            for (int n = reader.read(buf); n > -1; n = reader.read(buf)) {
                content.append(buf, 0, n);
            }
            return content.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static final boolean fileExists(final URL url){
        boolean exists = Boolean.FALSE;

        try (final InputStreamReader is = new InputStreamReader(url.openStream())){
            exists = is.ready();
        } catch (Exception e) {
            //
        }
        return exists;
    }
}
