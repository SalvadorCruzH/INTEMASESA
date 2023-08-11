package es.emasesa.intranet.portal.firmas.service.model;

import juntadeandalucia.cice.pfirma.modify.v2.PfirmaException;
import juntadeandalucia.cice.pfirma.type.v2.Request;

public interface PFirmasModifyServices {

    public void sendRequest(Request request) throws PfirmaException;
}
