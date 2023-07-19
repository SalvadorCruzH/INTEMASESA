package es.emasesa.intranet.gogo.command;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true,
        property = {
                "osgi.command.scope=camaraUtil"
        },
        service = Object.class)
public class CamaraUtilCommand {

}