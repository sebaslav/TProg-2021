
package servidor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para dtClase complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtClase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaHora" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="maxSocios" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="minSocios" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fechaDeRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imagen" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="actividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="profesor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="videoURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descPremio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cantPremios" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fechaSorteo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cantInscriptos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="valoraciones" type="{http://servidor/}dtValoracion" minOccurs="0"/>
 *         &lt;element name="finalizada" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtClase", propOrder = {
    "nombre",
    "fechaHora",
    "maxSocios",
    "minSocios",
    "fechaDeRegistro",
    "url",
    "imagen",
    "actividad",
    "profesor",
    "videoURL",
    "descPremio",
    "cantPremios",
    "fechaSorteo",
    "cantInscriptos",
    "valoraciones",
    "finalizada"
})
public class DtClase {

    protected String nombre;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaHora;
    protected int maxSocios;
    protected int minSocios;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaDeRegistro;
    protected String url;
    protected byte[] imagen;
    protected String actividad;
    protected String profesor;
    protected String videoURL;
    protected String descPremio;
    protected int cantPremios;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaSorteo;
    protected int cantInscriptos;
    protected DtValoracion valoraciones;
    protected boolean finalizada;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaHora.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaHora() {
        return fechaHora;
    }

    /**
     * Define el valor de la propiedad fechaHora.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaHora(XMLGregorianCalendar value) {
        this.fechaHora = value;
    }

    /**
     * Obtiene el valor de la propiedad maxSocios.
     * 
     */
    public int getMaxSocios() {
        return maxSocios;
    }

    /**
     * Define el valor de la propiedad maxSocios.
     * 
     */
    public void setMaxSocios(int value) {
        this.maxSocios = value;
    }

    /**
     * Obtiene el valor de la propiedad minSocios.
     * 
     */
    public int getMinSocios() {
        return minSocios;
    }

    /**
     * Define el valor de la propiedad minSocios.
     * 
     */
    public void setMinSocios(int value) {
        this.minSocios = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaDeRegistro.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    /**
     * Define el valor de la propiedad fechaDeRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaDeRegistro(XMLGregorianCalendar value) {
        this.fechaDeRegistro = value;
    }

    /**
     * Obtiene el valor de la propiedad url.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Define el valor de la propiedad url.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Obtiene el valor de la propiedad imagen.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * Define el valor de la propiedad imagen.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImagen(byte[] value) {
        this.imagen = value;
    }

    /**
     * Obtiene el valor de la propiedad actividad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActividad() {
        return actividad;
    }

    /**
     * Define el valor de la propiedad actividad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActividad(String value) {
        this.actividad = value;
    }

    /**
     * Obtiene el valor de la propiedad profesor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfesor() {
        return profesor;
    }

    /**
     * Define el valor de la propiedad profesor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfesor(String value) {
        this.profesor = value;
    }

    /**
     * Obtiene el valor de la propiedad videoURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideoURL() {
        return videoURL;
    }

    /**
     * Define el valor de la propiedad videoURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideoURL(String value) {
        this.videoURL = value;
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
     * Obtiene el valor de la propiedad cantPremios.
     * 
     */
    public int getCantPremios() {
        return cantPremios;
    }

    /**
     * Define el valor de la propiedad cantPremios.
     * 
     */
    public void setCantPremios(int value) {
        this.cantPremios = value;
    }

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
     * Obtiene el valor de la propiedad cantInscriptos.
     * 
     */
    public int getCantInscriptos() {
        return cantInscriptos;
    }

    /**
     * Define el valor de la propiedad cantInscriptos.
     * 
     */
    public void setCantInscriptos(int value) {
        this.cantInscriptos = value;
    }

    /**
     * Obtiene el valor de la propiedad valoraciones.
     * 
     * @return
     *     possible object is
     *     {@link DtValoracion }
     *     
     */
    public DtValoracion getValoraciones() {
        return valoraciones;
    }

    /**
     * Define el valor de la propiedad valoraciones.
     * 
     * @param value
     *     allowed object is
     *     {@link DtValoracion }
     *     
     */
    public void setValoraciones(DtValoracion value) {
        this.valoraciones = value;
    }

    /**
     * Obtiene el valor de la propiedad finalizada.
     * 
     */
    public boolean isFinalizada() {
        return finalizada;
    }

    /**
     * Define el valor de la propiedad finalizada.
     * 
     */
    public void setFinalizada(boolean value) {
        this.finalizada = value;
    }

}
