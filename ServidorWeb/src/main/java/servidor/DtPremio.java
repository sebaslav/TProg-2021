
package servidor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para dtPremio complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtPremio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fechaSorteo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="descPremio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomClase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomActividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPremio", propOrder = {
    "fechaSorteo",
    "descPremio",
    "nomClase",
    "nomActividad"
})
public class DtPremio {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaSorteo;
    protected String descPremio;
    protected String nomClase;
    protected String nomActividad;

    /**
     * Obtiene el valor de la propiedad fechaSorteo.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaSorteo() {
        return fechaSorteo;
    }

    /**
     * Define el valor de la propiedad fechaSorteo.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaSorteo(XMLGregorianCalendar value) {
        this.fechaSorteo = value;
    }

    /**
     * Obtiene el valor de la propiedad descPremio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescPremio() {
        return descPremio;
    }

    /**
     * Define el valor de la propiedad descPremio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescPremio(String value) {
        this.descPremio = value;
    }

    /**
     * Obtiene el valor de la propiedad nomClase.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomClase() {
        return nomClase;
    }

    /**
     * Define el valor de la propiedad nomClase.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomClase(String value) {
        this.nomClase = value;
    }

    /**
     * Obtiene el valor de la propiedad nomActividad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomActividad() {
        return nomActividad;
    }

    /**
     * Define el valor de la propiedad nomActividad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomActividad(String value) {
        this.nomActividad = value;
    }

}
