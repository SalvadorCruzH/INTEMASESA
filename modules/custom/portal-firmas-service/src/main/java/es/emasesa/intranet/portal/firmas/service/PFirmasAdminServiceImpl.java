package es.emasesa.intranet.portal.firmas.service;

import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.portal.firmas.service.model.PFirmasAdminServices;
import juntadeandalucia.cice.pfirma.admin.v2.AdminService;
import juntadeandalucia.cice.pfirma.admin.v2.AdminServiceService;
import juntadeandalucia.cice.pfirma.admin.v2.PfirmaException;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {}, service = PFirmasAdminServices.class)
public class PFirmasAdminServiceImpl implements PFirmasAdminServices {


    private AdminService _getService() throws PfirmaException {

        if(Validator.isNull(_adminService)){
            AdminServiceService adminService = new AdminServiceService();
            _adminService = adminService.getAdminServicePort();
        }


        return _adminService;

    }

    private AdminService _adminService;
}
