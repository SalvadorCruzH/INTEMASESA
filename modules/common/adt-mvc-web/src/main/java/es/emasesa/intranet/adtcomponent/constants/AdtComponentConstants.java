package es.emasesa.intranet.adtcomponent.constants;

import es.emasesa.intranet.adtcomponent.model.AdtComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdtComponentConstants {

	public static final String ADT_COMPONENT_PORTLET_KEY = "es_emasesa_adtcomponent_portlet_AdtMVCPortlet";
	public static final String ADT_COMPONENT_TEMPLATE_NAME_DEFAULT = "ADT Portlet";
	public static final String ADT_COMPONENT_TEMPLATE_RESOURCENAME_DEFAULT = ADT_COMPONENT_PORTLET_KEY;  
	
	public static final Map<String, Object> ADT_EMPTY_CONTEXT = new HashMap<>();
	protected static final List<AdtComponent> ADT_EMPTY_LIST = new ArrayList<>();

	private AdtComponentConstants(){}

}