
package servidor;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estadoActividad.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="estadoActividad">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Ingresada"/>
 *     &lt;enumeration value="Aceptada"/>
 *     &lt;enumeration value="Rechazada"/>
 *     &lt;enumeration value="Finalizada"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "estadoActividad")
@XmlEnum
public enum EstadoActividad {

    @XmlEnumValue("Ingresada")
    INGRESADA("Ingresada"),
    @XmlEnumValue("Aceptada")
    ACEPTADA("Aceptada"),
    @XmlEnumValue("Rechazada")
    RECHAZADA("Rechazada"),
    @XmlEnumValue("Finalizada")
    FINALIZADA("Finalizada");
    private final String value;

    EstadoActividad(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EstadoActividad fromValue(String v) {
        for (EstadoActividad c: EstadoActividad.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
