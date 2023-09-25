package es.emasesa.liferay.common.taglib.servlet.taglib;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;
import es.emasesa.liferay.common.taglib.servlet.ServletContextUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

public class HeaderTag extends IncludeTag {

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);
		setServletContext(ServletContextUtil.getServletContext());
	}

	@Override
	protected void cleanUp() {
		_title = StringPool.BLANK;
		_cssClass = StringPool.BLANK;
		_backUrl = StringPool.BLANK;
		_key = Boolean.FALSE;
		_removeBackButton = Boolean.FALSE;
	}

	public String getTitle() {return _title;}
	public void setTitle(String title) {this._title = title;}

    public String getBackUrl() {return _backUrl;}
    public void setBackUrl(String backUrl) {this._backUrl = backUrl;}

	public String getCssClass() {
		return Validator.isNull(_cssClass)?StringPool.BLANK:_cssClass;
	}
	public void setCssClass(String cssClass) {this._cssClass = cssClass;}

	public boolean isKey() {return _key;}
	public boolean getKey() {return isKey();}
	public void setKey(boolean key) {this._key = key;}

	public boolean getRemoveBackButton() {
		return isRemoveBackButton();
	}

	public boolean isRemoveBackButton() {
		return _removeBackButton;
	}
	public void setRemoveBackButton(boolean removeBackButton) {this._removeBackButton = removeBackButton;}

	private static final String HEADER_PAGE_JSP = "/header/page.jsp";
	private static final String emasesa_HEADER = "emasesa:header:";

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(emasesa_HEADER);
		return super.doStartTag();
	}

	@Override
	protected String getPage() {
		return HEADER_PAGE_JSP;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("emasesa:header:title", getTitle());
		request.setAttribute("emasesa:header:backUrl", getBackUrl());
		request.setAttribute("emasesa:header:key", isKey());
		request.setAttribute("emasesa:header:removeBackButton", isRemoveBackButton());
		request.setAttribute("emasesa:header:cssClass", getCssClass());


	}


	private String _title;
	private String _cssClass;
	private String _backUrl;
	private boolean _key;
	private boolean _removeBackButton;

}