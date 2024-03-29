
package servidor;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Publicador", targetNamespace = "http://servidor/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Publicador {


    /**
     * 
     * @param arg0
     * @throws UsuarioRepetidoException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/registrarSocioRequest", output = "http://servidor/Publicador/registrarSocioResponse", fault = {
        @FaultAction(className = UsuarioRepetidoException_Exception.class, value = "http://servidor/Publicador/registrarSocio/Fault/UsuarioRepetidoException")
    })
    public void registrarSocio(
        @WebParam(name = "arg0", partName = "arg0")
        DtSocio arg0)
        throws UsuarioRepetidoException_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws UsuarioRepetidoException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/registrarProfesorRequest", output = "http://servidor/Publicador/registrarProfesorResponse", fault = {
        @FaultAction(className = UsuarioRepetidoException_Exception.class, value = "http://servidor/Publicador/registrarProfesor/Fault/UsuarioRepetidoException")
    })
    public void registrarProfesor(
        @WebParam(name = "arg0", partName = "arg0")
        DtProfesor arg0)
        throws UsuarioRepetidoException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/iniciarSesionRequest", output = "http://servidor/Publicador/iniciarSesionResponse")
    public boolean iniciarSesion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @throws CuponeraYaCompradaException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/comprarCuponeraRequest", output = "http://servidor/Publicador/comprarCuponeraResponse", fault = {
        @FaultAction(className = CuponeraYaCompradaException_Exception.class, value = "http://servidor/Publicador/comprarCuponera/Fault/CuponeraYaCompradaException")
    })
    public void comprarCuponera(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        XMLGregorianCalendar arg2)
        throws CuponeraYaCompradaException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/seguirUsuarioRequest", output = "http://servidor/Publicador/seguirUsuarioResponse")
    public void seguirUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/dejarSeguirUsuarioRequest", output = "http://servidor/Publicador/dejarSeguirUsuarioResponse")
    public void dejarSeguirUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.ColeccionDtPremio
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/consultarPremiosRequest", output = "http://servidor/Publicador/consultarPremiosResponse")
    public ColeccionDtPremio consultarPremios(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/cambiarActividadFavoritaRequest", output = "http://servidor/Publicador/cambiarActividadFavoritaResponse")
    public boolean cambiarActividadFavorita(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/valorarClaseRequest", output = "http://servidor/Publicador/valorarClaseResponse")
    public void valorarClase(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        int arg2);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/existeNicknameRequest", output = "http://servidor/Publicador/existeNicknameResponse")
    public boolean existeNickname(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/existeEmailRequest", output = "http://servidor/Publicador/existeEmailResponse")
    public boolean existeEmail(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getInstitucionDeActividadRequest", output = "http://servidor/Publicador/getInstitucionDeActividadResponse")
    public String getInstitucionDeActividad(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getActividadesDeCategoriaRequest", output = "http://servidor/Publicador/getActividadesDeCategoriaResponse")
    public ColeccionString getActividadesDeCategoria(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getActividadesAceptadasDeCategoriaRequest", output = "http://servidor/Publicador/getActividadesAceptadasDeCategoriaResponse")
    public ColeccionString getActividadesAceptadasDeCategoria(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.ColeccionDtActividad
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/buscarActividadRequest", output = "http://servidor/Publicador/buscarActividadResponse")
    public ColeccionDtActividad buscarActividad(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/sortearPremiosRequest", output = "http://servidor/Publicador/sortearPremiosResponse")
    public void sortearPremios(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/verGanadoresRequest", output = "http://servidor/Publicador/verGanadoresResponse")
    public ColeccionString verGanadores(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getInscriptosAClaseRequest", output = "http://servidor/Publicador/getInscriptosAClaseResponse")
    public ColeccionString getInscriptosAClase(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/finalizarActividadRequest", output = "http://servidor/Publicador/finalizarActividadResponse")
    public void finalizarActividad(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getEspCuponerasVigentesRequest", output = "http://servidor/Publicador/getEspCuponerasVigentesResponse")
    public ColeccionString getEspCuponerasVigentes();

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.ColeccionDtCuponera
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/buscarCuponeraRequest", output = "http://servidor/Publicador/buscarCuponeraResponse")
    public ColeccionDtCuponera buscarCuponera(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/agregarRegistroRequest", output = "http://servidor/Publicador/agregarRegistroResponse")
    public void agregarRegistro(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        XMLGregorianCalendar arg4);

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getClasesDeActividadRequest", output = "http://servidor/Publicador/getClasesDeActividadResponse")
    public ColeccionString getClasesDeActividad(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DtClase
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getDatosDeClaseRequest", output = "http://servidor/Publicador/getDatosDeClaseResponse")
    public DtClase getDatosDeClase(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getActividadesDeInstitucionRequest", output = "http://servidor/Publicador/getActividadesDeInstitucionResponse")
    public ColeccionString getActividadesDeInstitucion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DtActividad
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getDtActividadRequest", output = "http://servidor/Publicador/getDtActividadResponse")
    public DtActividad getDtActividad(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DtCuponera
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getDatosCuponeraRequest", output = "http://servidor/Publicador/getDatosCuponeraResponse")
    public DtCuponera getDatosCuponera(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getActividadesAceptadasDeInstitucionRequest", output = "http://servidor/Publicador/getActividadesAceptadasDeInstitucionResponse")
    public ColeccionString getActividadesAceptadasDeInstitucion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @throws ClaseRepetidaException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/registrarClaseRequest", output = "http://servidor/Publicador/registrarClaseResponse", fault = {
        @FaultAction(className = ClaseRepetidaException_Exception.class, value = "http://servidor/Publicador/registrarClase/Fault/ClaseRepetidaException")
    })
    public void registrarClase(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        DtClase arg3)
        throws ClaseRepetidaException_Exception
    ;

    /**
     * 
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getInstitucionesRequest", output = "http://servidor/Publicador/getInstitucionesResponse")
    public ColeccionString getInstituciones();

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getProfesoresDeInstitucionRequest", output = "http://servidor/Publicador/getProfesoresDeInstitucionResponse")
    public ColeccionString getProfesoresDeInstitucion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getEspCuponerasNoCompradasRequest", output = "http://servidor/Publicador/getEspCuponerasNoCompradasResponse")
    public ColeccionString getEspCuponerasNoCompradas();

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getActividadesFaltantesRequest", output = "http://servidor/Publicador/getActividadesFaltantesResponse")
    public ColeccionString getActividadesFaltantes(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getCategoriasRequest", output = "http://servidor/Publicador/getCategoriasResponse")
    public ColeccionString getCategorias();

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg7
     * @param arg6
     * @param arg8
     * @throws ActividadRepetidaException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/registrarActividadRequest", output = "http://servidor/Publicador/registrarActividadResponse", fault = {
        @FaultAction(className = ActividadRepetidaException_Exception.class, value = "http://servidor/Publicador/registrarActividad/Fault/ActividadRepetidaException")
    })
    public void registrarActividad(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        ColeccionString arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        int arg5,
        @WebParam(name = "arg6", partName = "arg6")
        float arg6,
        @WebParam(name = "arg7", partName = "arg7")
        XMLGregorianCalendar arg7,
        @WebParam(name = "arg8", partName = "arg8")
        byte[] arg8)
        throws ActividadRepetidaException_Exception
    ;

    /**
     * 
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getListaUsuariosRequest", output = "http://servidor/Publicador/getListaUsuariosResponse")
    public ColeccionString getListaUsuarios();

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DtUsuario
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getDatosUsuarioRequest", output = "http://servidor/Publicador/getDatosUsuarioResponse")
    public DtUsuario getDatosUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg6
     * @throws CuponeraRepetidaException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/registrarCuponeraRequest", output = "http://servidor/Publicador/registrarCuponeraResponse", fault = {
        @FaultAction(className = CuponeraRepetidaException_Exception.class, value = "http://servidor/Publicador/registrarCuponera/Fault/CuponeraRepetidaException")
    })
    public void registrarCuponera(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        XMLGregorianCalendar arg2,
        @WebParam(name = "arg3", partName = "arg3")
        XMLGregorianCalendar arg3,
        @WebParam(name = "arg4", partName = "arg4")
        float arg4,
        @WebParam(name = "arg5", partName = "arg5")
        XMLGregorianCalendar arg5,
        @WebParam(name = "arg6", partName = "arg6")
        byte[] arg6)
        throws CuponeraRepetidaException_Exception
    ;

    /**
     * 
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getEspCuponerasRequest", output = "http://servidor/Publicador/getEspCuponerasResponse")
    public ColeccionString getEspCuponeras();

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getCuponerasDisponiblesRequest", output = "http://servidor/Publicador/getCuponerasDisponiblesResponse")
    public ColeccionString getCuponerasDisponibles(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     * @throws SocioYaRegistradoException_Exception
     * @throws ClaseLlenaException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/registrarSocioAClaseRequest", output = "http://servidor/Publicador/registrarSocioAClaseResponse", fault = {
        @FaultAction(className = SocioYaRegistradoException_Exception.class, value = "http://servidor/Publicador/registrarSocioAClase/Fault/SocioYaRegistradoException"),
        @FaultAction(className = ClaseLlenaException_Exception.class, value = "http://servidor/Publicador/registrarSocioAClase/Fault/ClaseLlenaException")
    })
    public void registrarSocioAClase(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        XMLGregorianCalendar arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4)
        throws ClaseLlenaException_Exception, SocioYaRegistradoException_Exception
    ;

    /**
     * 
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getSociosRequest", output = "http://servidor/Publicador/getSociosResponse")
    public ColeccionString getSocios();

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.ColeccionDtClase
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getClasesVigentesRequest", output = "http://servidor/Publicador/getClasesVigentesResponse")
    public ColeccionDtClase getClasesVigentes(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @return
     *     returns servidor.ColeccionString
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getActividadesIngresadasRequest", output = "http://servidor/Publicador/getActividadesIngresadasResponse")
    public ColeccionString getActividadesIngresadas();

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/editarDatosUsuarioRequest", output = "http://servidor/Publicador/editarDatosUsuarioResponse")
    public void editarDatosUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        DtUsuario arg0);

}
