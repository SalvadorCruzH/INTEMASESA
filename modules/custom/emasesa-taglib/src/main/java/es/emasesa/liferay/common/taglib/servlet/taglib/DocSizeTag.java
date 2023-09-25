package es.emasesa.liferay.common.taglib.servlet.taglib;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;
import es.emasesa.liferay.common.taglib.servlet.ServletContextUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.util.Locale;

public class DocSizeTag extends IncludeTag {

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);
		setServletContext(ServletContextUtil.getServletContext());
	}

	@Override
	protected void cleanUp() {
		_doc = StringPool.BLANK;
	}

	public String getDoc() {
		return _doc;
	}

	public void setDoc(String doc) {
		this._doc = doc;
	}

	public String getSize() {
		return _size;
	}

	public void setSize(String size) {
		this._size = size;
	}

	public Locale getLocale() {
		return _locale;
	}

	public void setLocale(Locale _locale) {
		this._locale = _locale;
	}

	private static final String PAGE_JSP = "/doc/page.jsp";
	private static final String emasesa_DOC_SIZE = "emasesa:docSize:";

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String _fileName) {
		this._fileName = _fileName;
	}

	public String getFileExtension() {
		return _fileExtension;
	}

	public void setFileExtension(String _fileExtension) {
		this._fileExtension = _fileExtension;
	}

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(emasesa_DOC_SIZE);
		return super.doStartTag();
	}

	@Override
	protected String getPage() {
		return PAGE_JSP;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		prepareAttributes(getDoc());
		request.setAttribute("emasesa:docSize:doc", getDoc());
		request.setAttribute("emasesa:docSize:size",getSize());
		request.setAttribute("emasesa:docSize:fileName",getFileName());
		request.setAttribute("emasesa:docSize:fileExtension",getFileExtension());
		
	}

	protected void prepareAttributes(String doc) {
		int counter = 0;
		int groupId = 0;
		String uuid = "";
		String[] datosDoc = doc.split("/");
		for (String x : datosDoc) {
			if (counter == 2) {
				groupId = Integer.parseInt(x);
			} else if (counter == 5) {
				uuid = x.substring(0, x.indexOf("?"));
			}
			counter = counter + 1;
		}
		DLFileEntry file;
		try {
			file = DLFileEntryLocalServiceUtil.getFileEntryByUuidAndGroupId(uuid, groupId);
			this.setSize( TextFormatter.formatStorageSize(file.getSize(), _locale));
			
			this.setFileExtension(file.getExtension().toUpperCase());

			this.setFileName(Validator.isBlank(file.getDescription())?
					file.getTitle():
					file.getDescription());
		} catch (PortalException e) {
			_log.error("Error al obtener el documento con uuId ="+uuid+"(groupID "+groupId+")",e);
		}
	}

	private String _doc;
	private Locale _locale;
	private String _size;
	private String _fileName;
	private String _fileExtension;
	
    private static final Log _log =
            LogFactoryUtil.getLog(DocSizeTag.class);

}