package es.emasesa.liferay.common.taglib.servlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.ServletContext;

@Component(immediate = true, service = {})
public class ServletContextUtil {

	public static final ServletContext getServletContext() {
		return _servletContext;
	}

	// Code imported from Liferay Source
	@SuppressWarnings("squid:S2696")
	@Reference(
			target = "(osgi.web.symbolicname=emasesa-taglib)",
			unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private static ServletContext _servletContext;

}