package es.emasesa.intranet.webservices.settings;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.webservices.settings.RestDDMSelectSettings",
        name = "es.emasesa.intranet.webservices.settings.restddmselect",
        localization = "content/Language"
)
public interface RestDDMSelectSettings {

    @Meta.AD(
            name = "es.emasesa.intranet.systemsettings.estacionStructureId",
            deflt = "emasesa-DETALLE-ESTACION",
            required = false
    )
    String estacionStructureId();

    @Meta.AD(
            name = "es.emasesa.intranet.systemsettings.estacionPromoStructureId",
            deflt = "-1",
            required = false
    )
    long estacionPromoStructureId();

    @Meta.AD(
            name = "es.emasesa.intranet.systemsettings.estacionEventoStructureId",
            deflt = "-1",
            required = false
    )
    long estacionEventoStructureId();

    @Meta.AD(
            name = "es.emasesa.intranet.systemsettings.patrimonioGenericaStructureId",
            deflt = "-1",
            required = false
    )
    String patrimonioBaseStructureId();

    @Meta.AD(
            name = "es.emasesa.intranet.systemsettings.patrimonioEstacionesId",
            deflt = "-1",
            required = false
    )
    String patrimonioEstacionesId();

    @Meta.AD(
            name = "es.emasesa.intranet.systemsettings.patrimonioInstalacionLogisticaId",
            deflt = "-1",
            required = false
    )
    String patrimonioInstalacionLogisticaId();

    @Meta.AD(
            name = "es.emasesa.intranet.systemsettings.instLogisticasStructureId",
            deflt = "-1",
            required = false
    )
    String instLogisticasStructureId();
    
    @Meta.AD(
            name = "es.emasesa.intranet.systemsettings.comunidadesVocabularyId",
            deflt = "0",
            required = false
    )
    long comunidadesVocabularyId();
    
    @Meta.AD(
            name = "es.emasesa.intranet.systemsettings.estacionDestacadaCategoryId",
            deflt = "0",
            required = false
    )
    long estacionDestacadaCategoryId();

    @Meta.AD(
            name = "es.emasesa.intranet.systemsettings.espacioRodajesStructureId",
            deflt = "-1",
            required = false
    )
    String espacioRodajesStructureId();
    
    @Meta.AD(
            name = "es.emasesa.intranet.systemsettings.tipologiasVocabularyId",
            deflt = "0",
            required = false
    )
    long tipologiasVocabularyId();
    
    @Meta.AD(
            name = "es.emasesa.intranet.systemsettings.etiquetasVocabularyId",
            deflt = "0",
            required = false
    )
    long etiquetasVocabularyId();

}