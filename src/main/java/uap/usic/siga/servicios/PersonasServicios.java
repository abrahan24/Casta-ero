package uap.usic.siga.servicios;

import java.util.List;
import uap.usic.siga.dto.PersonasResponse;
import uap.usic.siga.entidades.CjaGastosEjecutados;
import uap.usic.siga.entidades.CjaIngresos;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PnlPersonalAdministrativos;
import uap.usic.siga.entidades.PrsCiExpedidos;
import uap.usic.siga.entidades.PrsGradosAcademicos;
import uap.usic.siga.entidades.PrsPaises;
import uap.usic.siga.entidades.PrsTiposSexos;
import uap.usic.siga.entidades.Usuarios;

/**
 *
 * @author fmbma
 */
public interface PersonasServicios {

    public List<Personas> listarPersonas();

    public List<PrsPaises> listarPaises();

    void registrarPersonas(Personas personas);

    public List<Object[]> listarPersonasPorCi();

    public Personas buscarPersonasPorCedulaIdentidad(String ci);

    public Personas buscarPersonaPorIdUsuario(Long idUsuario);

    public void registrarPrsPaises(PrsPaises prsPaises);

    public List<PrsTiposSexos> listarTiposSexos();

    public void registrarPrsTiposSexos(PrsTiposSexos prsTiposSexos);

    public void modificarPersonas(Personas personas);

    public void eliminarPersonas(Personas personas);

    public Personas buscarPersonasPorIdPersona(Long idPersona);

    public List<PrsCiExpedidos> listarCedulaIdentidadexpedidos();

    public void registrarPrsCedulaIdentidadExpedidos(PrsCiExpedidos prsCiExpedidos);

    public List<PersonasResponse> listarPersonasResponsePersonalizado();

    public PnlPersonalAdministrativos buscarPnlPersonalAdministrativosPorIdPersonaIdEstado(Long idPersona, String idEstado);

    public CjaIngresos buscarCjaIngresosPoIdPersonaIdEstado(Long idPersona, String idEstado);

    public CjaGastosEjecutados buscarCjaGastosEjecutadosPorIdPersonaIdEstado(Long idPersona, String idEstado);

    public Usuarios buscarUsuariosPorIdPersonaIdEstado(Long idPersona, String idEstado);

    public Personas buscarPersonasPorIdCjaGastoEjecutado(Long idCjaGastoEjecutado);

    public Personas buscarPersonasPorIdCjaIngresoGET(Long idCjaIngreso);

    public List<Personas> listarCjaGastosEjecutadosPersonasPorIdCjaIngresoIdUsuario(Long idCjaIngreso, Long idUsuario);

    public List<Personas> listarPersonasComprobantes();

    public Personas buscarPersonasComprobantesPorIdPersona(Long idPersona);

    public PersonasResponse buscarPersonaRazonSocialPorIdSacRazonSocialResponse(Long idSacRazonSocial);

    public PersonasResponse buscarPersonaRazonSocialPorIdPersonaResponse(Long idPersona);

    public List<PrsGradosAcademicos> listarGradosAcademicos();

    public void registrarPrsGradosAcademicos(PrsGradosAcademicos prsGradosAcademicos);
    
    public PrsGradosAcademicos getGradoAcademico(Long id);

    public List<Personas> listarPersonalAdministrativoPorGestion(Integer gestion);
    
    public PrsCiExpedidos getCiExpedido(Long id);
    
    public PrsPaises getPais(Long id);
    
    public PrsTiposSexos getSexo(Long id);
}
