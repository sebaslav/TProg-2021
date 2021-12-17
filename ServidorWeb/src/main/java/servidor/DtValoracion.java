
package servidor;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtValoracion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtValoracion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantValores" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="valorPromedio" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtValoracion", propOrder = {
    "cantValores",
    "valorPromedio"
})
public class DtValoracion {

    @XmlElement(nillable = true)
    protected List<Integer> cantValores;
    protected float valorPromedio;

    /**
     * Gets the value of the cantValores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cantValores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCantValores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getCantValores() {
        if (cantValores == null) {
            cantValores = new ArrayList<Integer>();
        }
        return this.cantValores;
    }

    /**
     * Obtiene el valor de la propiedad valorPromedio.
     * 
     */
    public float getValorPromedio() {
        return valorPromedio;
    }

    /**
     * Define el valor de la propiedad valorPromedio.
     * 
     */
    public void setValorPromedio(float value) {
        this.valorPromedio = value;
    }

}
