package es.emasesa.intranet.portlet.ajaxsearch.impl.nominas.result;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AjaxSearchPortletKeys.PORTLET_AJAXSEARCH_RESULTS,
                "mvc.command.name=consultaNomina"
        },
        service = MVCResourceCommand.class
)
public class DescargaZipMVCResourceCommand extends BaseMVCResourceCommand {

    @Override
    public void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

        JSONArray jsonArray = (JSONArray) resourceRequest.getPortletSession().getAttribute("nominasArrayZip");
        String matricula = (String) resourceRequest.getPortletSession().getAttribute("matricula");
        HttpServletResponse response = PortalUtil.getHttpServletResponse(resourceResponse);

        response.setHeader("Content-Disposition", "attachment; filename=archivos_" + matricula + ".zip");
        response.setContentType("application/zip");

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                agregarArchivoAlZipDesdeUrl(jsonObject, zipOutputStream);
            }

            zipOutputStream.finish();
            byte[] zipBytes = byteArrayOutputStream.toByteArray();

            PortletResponseUtil.sendFile(resourceRequest, resourceResponse, "archivos.zip", zipBytes);

        } catch (Exception e) {
            throw new RuntimeException("Error al crear el ZIP o transmitir el archivo", e);
        }

    }

    private void agregarArchivoAlZipDesdeUrl(JSONObject jsonObject, ZipOutputStream zipOutputStream)
            throws IOException {
        String fechaNomina = jsonObject.getString("fechaNomina");
        String urlNominaDefinitiva = jsonObject.getString("urlNominaDefinitiva");
        String urlNominaProvisional = jsonObject.getString("urlNominaProvisional");
        String urlUltimoRecalculo = jsonObject.getString("urlUltimoRecalculo");

        if(Validator.isNotNull(urlNominaDefinitiva)) {
            agregarArchivoAlZip(urlNominaDefinitiva, fechaNomina + "_Definitiva.pdf", zipOutputStream);
        }
        if(Validator.isNotNull(urlNominaProvisional)) {
            agregarArchivoAlZip(urlNominaProvisional, fechaNomina + "_Provisional.pdf", zipOutputStream);
        }
        if(Validator.isNotNull(urlUltimoRecalculo)) {
            agregarArchivoAlZip(urlUltimoRecalculo, fechaNomina + "_UltimoRecalculo.pdf", zipOutputStream);
        }
    }

    private void agregarArchivoAlZip(String url, String nombreArchivo, ZipOutputStream zipOutputStream)
            throws IOException {
        try (InputStream inputStream = new URL(url).openStream()) {
            zipOutputStream.putNextEntry(new ZipEntry(nombreArchivo));
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                zipOutputStream.write(buffer, 0, bytesRead);
            }

            zipOutputStream.closeEntry();
        } catch (IOException e) {
            throw new RuntimeException("Error al agregar archivos", e);
        }
    }
    @Reference
    Portal _portal;
}