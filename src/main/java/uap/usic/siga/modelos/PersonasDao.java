package uap.usic.siga.modelos;

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
public interface PersonasDao {

    public List<Personas> listarPersonasGET();

    public List<PrsPaises> listarPaisesGET();

    void registrarPersonasSET(Personas personas);

    public List<Object[]> listarPersonasPorCiJPQL();

    public Personas buscarPersonasPorCedulaIdentidadGET(String ci);

    public Personas buscarPersonaPorIdUsuarioGET(Long idUsuario);

    public void registrarPrsPaisesSET(PrsPaises prsPaises);

    public List<PrsTiposSexos> listarTiposSexosJPQL();

    public void registrarPrsTiposSexosSET(PrsTiposSexos prsTiposSexos);

    public void modificarPersonasSET(Personas personas);

    public void eliminarPersonasSET(Personas personas);

    public Personas buscarPersonasPorIdPersonaGET(Long idPersona);

    public List<PrsCiExpedidos> listarCedulaIdentidadexpedidosJPQL();

    public void registrarPrsCedulaIdentidadExpedidosSET(PrsCiExpedidos prsCiExpedidos);

    public PrsCiExpedidos getCiExpedido(Long id);
    
    public List<PersonasResponse> listarPersonasResponsePersonalizadoJPQL();

    public PnlPersonalAdministrativos buscarPnlPersonalAdministrativosPorIdPersonaIdEstadoGET(Long idPersona, String idEstado);

    public CjaIngresos buscarCjaIngresosPoIdPersonaIdEstadoGET(Long idPersona, String idEstado);

    public CjaGastosEjecutados buscarCjaGastosEjecutadosPorIdPersonaIdEstadoGET(Long idPersona, String idEstado);

    public Usuarios buscarUsuariosPorIdPersonaIdEstadoGET(Long idPersona, String idEstado);

    public Personas buscarPersonasPorIdCjaGastoEjecutadoGET(Long idCjaGastoEjecutado);

    public Personas buscarPersonasPorIdCjaIngresoGET(Long idCjaIngreso);

    public List<Personas> listarCjaGastosEjecutadosPersonasPorIdCjaIngresoIdUsuarioJPQL(Long idCjaIngreso, Long idUsuario);
    
    public List<Personas> listarPersonasComprobantesJPQL();
    
    public Personas buscarPersonasComprobantesPorIdPersonaGET(Long idPersona);
    

    public PersonasResponse buscarPersonaRazonSocialPorIdSacRazonSocialResponseGET(Long idSacRazonSocial);
    public PersonasResponse buscarPersonaRazonSocialPorIdPersonaResponseGET(Long idPersona);
    
    public List<PrsGradosAcademicos> listarGradosAcademicosJPQL();

    public void registrarPrsGradosAcademicosSET(PrsGradosAcademicos prsGradosAcademicos);
    
    // ============= Poais
    public List<Personas> listarPersonalAdministrativoPorGestionJPQL(Integer gestion);
    
    public PrsGradosAcademicos getGradoAcademico(Long id);
    
    public PrsPaises getPais(Long id);
    
    public PrsTiposSexos getSexo(Long id);
}
