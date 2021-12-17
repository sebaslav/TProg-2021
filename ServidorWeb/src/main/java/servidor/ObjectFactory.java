
package servidor;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servidor package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CuponeraYaCompradaException_QNAME = new QName("http://servidor/", "CuponeraYaCompradaException");
    private final static QName _UsuarioRepetidoException_QNAME = new QName("http://servidor/", "UsuarioRepetidoException");
    private final static QName _ClaseLlenaException_QNAME = new QName("http://servidor/", "ClaseLlenaException");
    private final static QName _ClaseRepetidaException_QNAME = new QName("http://servidor/", "ClaseRepetidaException");
    private final static QName _SocioYaRegistradoException_QNAME = new QName("http://servidor/", "SocioYaRegistradoException");
    private final static QName _ActividadRepetidaException_QNAME = new QName("http://servidor/", "ActividadRepetidaException");
    private final static QName _CuponeraRepetidaException_QNAME = new QName("http://servidor/", "CuponeraRepetidaException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servidor
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DtSocio }
     * 
     */
    public DtSocio createDtSocio() {
        return new DtSocio();
    }

    /**
     * Create an instance of {@link DtSocio.ValoracionesClases }
     * 
     */
    public DtSocio.ValoracionesClases createDtSocioValoracionesClases() {
        return new DtSocio.ValoracionesClases();
    }

    /**
     * Create an instance of {@link DtProfesor }
     * 
     */
    public DtProfesor createDtProfesor() {
        return new DtProfesor();
    }

    /**
     * Create an instance of {@link DtProfesor.Actividades }
     * 
     */
    public DtProfesor.Actividades createDtProfesorActividades() {
        return new DtProfesor.Actividades();
    }

    /**
     * Create an instance of {@link DtProfesor.Clases }
     * 
     */
    public DtProfesor.Clases createDtProfesorClases() {
        return new DtProfesor.Clases();
    }

    /**
     * Create an instance of {@link DtCuponera }
     * 
     */
    public DtCuponera createDtCuponera() {
        return new DtCuponera();
    }

    /**
     * Create an instance of {@link DtCuponera.Actividades }
     * 
     */
    public DtCuponera.Actividades createDtCuponeraActividades() {
        return new DtCuponera.Actividades();
    }

    /**
     * Create an instance of {@link ActividadRepetidaException }
     * 
     */
    public ActividadRepetidaException createActividadRepetidaException() {
        return new ActividadRepetidaException();
    }

    /**
     * Create an instance of {@link CuponeraRepetidaException }
     * 
     */
    public CuponeraRepetidaException createCuponeraRepetidaException() {
        return new CuponeraRepetidaException();
    }

    /**
     * Create an instance of {@link SocioYaRegistradoException }
     * 
     */
    public SocioYaRegistradoException createSocioYaRegistradoException() {
        return new SocioYaRegistradoException();
    }

    /**
     * Create an instance of {@link ClaseLlenaException }
     * 
     */
    public ClaseLlenaException createClaseLlenaException() {
        return new ClaseLlenaException();
    }

    /**
     * Create an instance of {@link ClaseRepetidaException }
     * 
     */
    public ClaseRepetidaException createClaseRepetidaException() {
        return new ClaseRepetidaException();
    }

    /**
     * Create an instance of {@link UsuarioRepetidoException }
     * 
     */
    public UsuarioRepetidoException createUsuarioRepetidoException() {
        return new UsuarioRepetidoException();
    }

    /**
     * Create an instance of {@link CuponeraYaCompradaException }
     * 
     */
    public CuponeraYaCompradaException createCuponeraYaCompradaException() {
        return new CuponeraYaCompradaException();
    }

    /**
     * Create an instance of {@link ColeccionString }
     * 
     */
    public ColeccionString createColeccionString() {
        return new ColeccionString();
    }

    /**
     * Create an instance of {@link DtPremio }
     * 
     */
    public DtPremio createDtPremio() {
        return new DtPremio();
    }

    /**
     * Create an instance of {@link ColeccionDtPremio }
     * 
     */
    public ColeccionDtPremio createColeccionDtPremio() {
        return new ColeccionDtPremio();
    }

    /**
     * Create an instance of {@link ColeccionDtClase }
     * 
     */
    public ColeccionDtClase createColeccionDtClase() {
        return new ColeccionDtClase();
    }

    /**
     * Create an instance of {@link DtActividad }
     * 
     */
    public DtActividad createDtActividad() {
        return new DtActividad();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtValoracion }
     * 
     */
    public DtValoracion createDtValoracion() {
        return new DtValoracion();
    }

    /**
     * Create an instance of {@link ColeccionDtCuponera }
     * 
     */
    public ColeccionDtCuponera createColeccionDtCuponera() {
        return new ColeccionDtCuponera();
    }

    /**
     * Create an instance of {@link ColeccionDtActividad }
     * 
     */
    public ColeccionDtActividad createColeccionDtActividad() {
        return new ColeccionDtActividad();
    }

    /**
     * Create an instance of {@link DtClase }
     * 
     */
    public DtClase createDtClase() {
        return new DtClase();
    }

    /**
     * Create an instance of {@link DtSocio.ValoracionesClases.Entry }
     * 
     */
    public DtSocio.ValoracionesClases.Entry createDtSocioValoracionesClasesEntry() {
        return new DtSocio.ValoracionesClases.Entry();
    }

    /**
     * Create an instance of {@link DtProfesor.Actividades.Entry }
     * 
     */
    public DtProfesor.Actividades.Entry createDtProfesorActividadesEntry() {
        return new DtProfesor.Actividades.Entry();
    }

    /**
     * Create an instance of {@link DtProfesor.Clases.Entry }
     * 
     */
    public DtProfesor.Clases.Entry createDtProfesorClasesEntry() {
        return new DtProfesor.Clases.Entry();
    }

    /**
     * Create an instance of {@link DtCuponera.Actividades.Entry }
     * 
     */
    public DtCuponera.Actividades.Entry createDtCuponeraActividadesEntry() {
        return new DtCuponera.Actividades.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CuponeraYaCompradaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "CuponeraYaCompradaException")
    public JAXBElement<CuponeraYaCompradaException> createCuponeraYaCompradaException(CuponeraYaCompradaException value) {
        return new JAXBElement<CuponeraYaCompradaException>(_CuponeraYaCompradaException_QNAME, CuponeraYaCompradaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioRepetidoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "UsuarioRepetidoException")
    public JAXBElement<UsuarioRepetidoException> createUsuarioRepetidoException(UsuarioRepetidoException value) {
        return new JAXBElement<UsuarioRepetidoException>(_UsuarioRepetidoException_QNAME, UsuarioRepetidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClaseLlenaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "ClaseLlenaException")
    public JAXBElement<ClaseLlenaException> createClaseLlenaException(ClaseLlenaException value) {
        return new JAXBElement<ClaseLlenaException>(_ClaseLlenaException_QNAME, ClaseLlenaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClaseRepetidaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "ClaseRepetidaException")
    public JAXBElement<ClaseRepetidaException> createClaseRepetidaException(ClaseRepetidaException value) {
        return new JAXBElement<ClaseRepetidaException>(_ClaseRepetidaException_QNAME, ClaseRepetidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SocioYaRegistradoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "SocioYaRegistradoException")
    public JAXBElement<SocioYaRegistradoException> createSocioYaRegistradoException(SocioYaRegistradoException value) {
        return new JAXBElement<SocioYaRegistradoException>(_SocioYaRegistradoException_QNAME, SocioYaRegistradoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActividadRepetidaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "ActividadRepetidaException")
    public JAXBElement<ActividadRepetidaException> createActividadRepetidaException(ActividadRepetidaException value) {
        return new JAXBElement<ActividadRepetidaException>(_ActividadRepetidaException_QNAME, ActividadRepetidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CuponeraRepetidaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "CuponeraRepetidaException")
    public JAXBElement<CuponeraRepetidaException> createCuponeraRepetidaException(CuponeraRepetidaException value) {
        return new JAXBElement<CuponeraRepetidaException>(_CuponeraRepetidaException_QNAME, CuponeraRepetidaException.class, null, value);
    }

}
