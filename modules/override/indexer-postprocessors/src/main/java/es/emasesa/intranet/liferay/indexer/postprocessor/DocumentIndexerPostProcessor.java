package es.emasesa.intranet.liferay.indexer.postprocessor;


import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalService;
import com.liferay.document.library.util.DLFileEntryTypeUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.kernel.Value;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.base.util.XMLDocumentUtil;
import es.emasesa.intranet.settings.osgi.BaseSettings;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.*;


@Component(
	immediate = true,
	property = {
		"indexer.class.name=com.liferay.document.library.kernel.model.DLFileEntry"
	},
	service = IndexerPostProcessor.class
)
public class DocumentIndexerPostProcessor extends BaseIndexerPostProcessor {
	final String DDMARRAY = "ddmFieldArray";
	final String DDMKEYWORD = "ddm__keyword__";
	final String DDMSTRING = "ddm__text__";
	final String _FECHA_SUBIDA = "__fechaSubida";
	final String _FECHA_VIGENCIA = "__fechaVigencia";
	final String EXPIRATION_DATE_SORTABLE = "expirationDate_sortable";
	final String PUBLISH_DATE_SORTABLE = "publishDate_sortable";

	@Override
	public void postProcessDocument(Document document, Object obj) {
			try {
				final DLFileEntry dl = (DLFileEntry) obj;
				//indexArchive(document, dl);
				indexDocument(document, dl);
			} catch (ClassCastException exception) {
				LoggerUtil.error(LOG, exception);
			}
	}

	private void indexDocument(final Document document, final DLFileEntry dl) {
		long configFileTypeId = baseSettings.fileTypeId();
		if(configFileTypeId == dl.getFileEntryTypeId()) {
			try {
				final DDMStructure fileTypeStr = DLFileEntryTypeUtil.getDDMStructures(dl.getDLFileEntryType()).get(0);
				final Long _structureId = fileTypeStr.getStructureId();

				Field ddmFieldArray = document.getField(DDMARRAY);
				List<Field> ddmFieldArrayList = ddmFieldArray.getFields();

				for(Field field : ddmFieldArrayList) {
					String [] fechaSubida;
					String [] fechaVigencia;
					if(field.getFields().get(0).getValues()[0].equals(DDMKEYWORD + _structureId + _FECHA_SUBIDA)) {
						fechaSubida = field.getFields().get(4).getValues();
						Date fechaSubidaDate = new Date(Long.parseLong(fechaSubida[0]));
						//document.addDateSortable(FECHA_SUBIDA, fechaSubidaDate);
						//dl.setLastPublishDate(fechaSubidaDate);
						document.remove(Field.PUBLISH_DATE);
						document.remove(PUBLISH_DATE_SORTABLE);
						document.addDateSortable(Field.PUBLISH_DATE, fechaSubidaDate);
						System.out.println("last publish");
					}

					if(field.getFields().get(0).getValues()[0].equals(DDMKEYWORD + _structureId + _FECHA_VIGENCIA)) {
						fechaVigencia = field.getFields().get(4).getValues();
						Date fechaVigenciaDate = new Date(Long.parseLong(fechaVigencia[0]));
						//document.addDateSortable(FECHA_VIGENCIA, fechaVigenciaDate);
						//dl.setExpirationDate(fechaVigenciaDate);
						document.remove(Field.EXPIRATION_DATE);
						document.remove(EXPIRATION_DATE_SORTABLE);
						document.addDateSortable(Field.EXPIRATION_DATE, fechaVigenciaDate);
						System.out.println("expiration");
					}
				}
			}catch (Exception e) {
				//
			}
		}
	}
	/*private void indexArchive(final Document document, final DLFileEntry dl) {
		long configDLTypeId = baseSettings.archiveDLTypeId();
		long configSFDRTypeId = baseSettings.archiveSFDRTypeId();
		//long configDLMetadataId = baseSettings.archiveDLMetadataId();
		if (configDLTypeId == dl.getFileEntryTypeId()) {
			try {
				// LISTA
				final String fieldLista = "product";

				final DDMStructure archiveStr = dl.getDLFileEntryType().getDDMStructures().get(0);
				final Long _structureId = archiveStr.getStructureId();


				Field ddmFieldArray = document.getField(DDMARRAY);
				List<Field> ddmFieldArrayList = ddmFieldArray.getFields();

				for (Field field : ddmFieldArrayList) {
					String[] product;
					String[] year;
					String[] gkid;
					if (field.getFields().get(0).getValues()[0].equals(DDMKEYWORD + _structureId + _PRODUCT)) {
						product = field.getFields().get(2).getValues();
						for(int i = 0; i < product.length; i++){
							product[i] = product[i].replace(" ", "");
						}
						document.addText(PRODUCT_NAME, product);
					}

					if (field.getFields().get(0).getValues()[0].equals(DDMKEYWORD + _structureId + _YEAR)) {
						year = field.getFields().get(2).getValues();
						document.addNumber(YEAR, year);
					}

					if (field.getFields().get(0).getValues()[0].equals(DDMSTRING + _structureId + _GKID)) {
						gkid = field.getFields().get(2).getValues();
						document.addText(GKID, gkid);
					}
				}

			} catch (Exception e) {
				//
			}
		} else if(configSFDRTypeId == dl.getFileEntryTypeId()) {
			try {
				// LISTA
				final String fieldLista = "product";

				final DDMStructure archiveStr = dl.getDLFileEntryType().getDDMStructures().get(0);
				final Long _structureId = archiveStr.getStructureId();


				Field ddmFieldArray = document.getField(DDMARRAY);
				List<Field> ddmFieldArrayList = ddmFieldArray.getFields();

				for (Field field : ddmFieldArrayList) {
					String[] fundName;
					String[] fundCode;
					String[] year;
					if (field.getFields().get(0).getValues()[0].equals(DDMSTRING + _structureId + _FUND_NAME)) {
						fundName = field.getFields().get(2).getValues();
						document.addText(FUND_NAME, fundName);
					}

					if (field.getFields().get(0).getValues()[0].equals(DDMKEYWORD + _structureId + _FUND_CODE)) {
						fundCode = field.getFields().get(2).getValues();
						document.addText(FUND_CODE, fundCode);
					}

					if (field.getFields().get(0).getValues()[0].equals(DDMKEYWORD + _structureId + _YEAR)) {
						year = field.getFields().get(2).getValues();
						document.addNumber(YEAR, year);
					}
				}

			} catch (Exception e) {
				//
			}
		}
	}*/

	@Reference
	DLFileEntryLocalService dlFileEntryLocalService;

	@Reference
	DLFileEntryMetadataLocalService dlFileEntryMetadataLocalService;

	@Reference
	BaseSettings baseSettings;

    @Reference
	XMLDocumentUtil xmlDocumentUtil;

	private static final Log LOG = LoggerUtil.getLog(DocumentIndexerPostProcessor.class);

}