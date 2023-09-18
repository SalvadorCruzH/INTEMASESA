package es.emasesa.intranet.jornada.nomina.service;

import es.emasesa.intranet.jornada.nomina.generated.Bapireturn1;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Return {

    private Bapireturn1 _return;
    @XmlElement(name="Return")
    public Bapireturn1 getReturn() {
        return _return;
    }

    public void setReturn(Bapireturn1 value) {
        this._return = value;
    }
}
