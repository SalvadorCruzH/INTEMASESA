package es.emasesa.intranet.base.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Node;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        immediate = true,
        property = {},
service = XMLDocumentUtil.class)
public class XMLDocumentUtil {

    private static final String XML_READ_INIT = "/root/dynamic-element[@name='";
    private static final String XML_READ_END = "']/dynamic-content";

    private static final String XML_READ_INTERMEDIATE = "']/dynamic-element[@name='";

    /**
     * Auxiliar method to get xml document field
     * @param document com.liferay.portal.kernel.xml.Document
     * @param fieldName String
     * @return String value (blank if not found)
     */
    public final String getFieldText(com.liferay.portal.kernel.xml.Document document, String fieldName) {
        if (document != null && !fieldName.isEmpty()) {
            Node node = document.selectSingleNode(XML_READ_INIT + fieldName + XML_READ_END);
            if (node != null && node.getText().length() > 0) {
                return node.getText();
            }
        }
        return StringPool.BLANK;
    }

    /**
     * Auxiliar method to get xml document field
     * @param document com.liferay.portal.kernel.xml.Document
     * @param fieldName String
     * @return String value (blank if not found)
     */
    public final List<String> getFieldTexts(com.liferay.portal.kernel.xml.Document document, String fieldName) {
        List<String> fieldTexts = new ArrayList<>();
        if (document != null && !fieldName.isEmpty()) {
            final List<Node> nodes = document.selectNodes(XML_READ_INIT + fieldName + XML_READ_END);
            if (nodes != null) {
                nodes.stream().map(s -> s.getText()).forEach(fieldTexts::add);
            }
        }
        return fieldTexts;
    }

    /**
     * Auxiliar method to get xml document field (with a parent field)
     * @param document com.liferay.portal.kernel.xml.Document
     * @param parentField String
     * @param fieldName String
     * @return String value (blank if not found)
     */
    public final String getSecondLevelFieldText(com.liferay.portal.kernel.xml.Document document, String parentField, String fieldName) {
        if (document != null && !fieldName.isEmpty()) {
            Node node = document.selectSingleNode(XML_READ_INIT + parentField + XML_READ_INTERMEDIATE + fieldName + XML_READ_END);
            if (node != null && node.getText().length() > 0) {
                return node.getText();
            }
        }
        return StringPool.BLANK;
    }

    /**
     * Auxiliar method to get xml document field
     * @param document com.liferay.portal.kernel.xml.Document
     * @param fieldName String
     * @return String value (blank if not found)
     */
    public final int getFieldCount(com.liferay.portal.kernel.xml.Document document, String fieldName) {
        if (document != null && !fieldName.isEmpty()) {
            List<Node> nodes = document.selectNodes(XML_READ_INIT + fieldName + XML_READ_END);
            if (nodes != null) {
                return countNodes(nodes);
            }
        }
        return 0;
    }
    
    private final int countNodes(final List<Node> nodes) {
        int count = 0;
    	for (Node node : nodes) {
            if(!Validator.isBlank(node.getStringValue())) {
                count++;
            }
        }
    	return count;
    }
}
