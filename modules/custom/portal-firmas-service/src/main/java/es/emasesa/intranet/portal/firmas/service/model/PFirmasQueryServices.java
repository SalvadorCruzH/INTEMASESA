package es.emasesa.intranet.portal.firmas.service.model;

import java.io.File;
import juntadeandalucia.cice.pfirma.query.v2.PfirmaException;
import juntadeandalucia.cice.pfirma.type.v2.User;

public interface PFirmasQueryServices {

    public User queryUser(String query) throws PfirmaException;
    public void sendSignOneUser(User user, String docType, File file) throws PfirmaException;
}
