package uap.usic.siga.modelos;

import java.util.List;

import uap.usic.siga.entidades.Facultades;
import uap.usic.siga.entidades.FclCarreras;
import uap.usic.siga.entidades.FclEstamentos;
import uap.usic.siga.entidades.InsDireccionesFuncionales;
import uap.usic.siga.entidades.InsSede;
import uap.usic.siga.entidades.InsSedes;
import uap.usic.siga.entidades.InsUbicacionesOrganicas;
import uap.usic.siga.entidades.InsUnidadesFuncionales;
import uap.usic.siga.entidades.Institucion;
import uap.usic.siga.entidades.Instituciones;

/**
 *
 * @author Yessenia
 */
public interface InstitucionesDao {

    public List<InsSedes> listarInsSedesJQPL();

    public List<Instituciones> listarInstitucionesGET();

    void registrarInsSedesSET(InsSedes inssedes);

    public List<InsUbicacionesOrganicas> listarInsUbicacionesOrganicasIdSedeGET(Long idSede);

    public List<String> listarUbicacionesGET(Long id);

    public InsSedes buscarInsSedesGET(Long idSede);

    public List<Institucion> listarInstitucionGET();

    public List<InsSedes> listarSedesPorIdInstitucionJPQL(Long idInstitucion);

    public InsSedes buscarInsSedesPorSedeGET(String sede);

    public void guardarInsSedesSET(InsSedes insSedes);

    public InsUnidadesFuncionales buscarInsUnidadesFuncionalesPorUnidadGET(String unidadFuncional);

    public void guardarInsUnidadesFuncionalesSET(InsUnidadesFuncionales insUnidadesFuncionales);

    public void guardarInstitucionesSET(Instituciones instituciones);

    public List<InsUnidadesFuncionales> listarUnidadesFuncionalesJPQL();

    public List<InsDireccionesFuncionales> listarDireccionesFuncionalesPorIdSedeJPQL(Long idSede);

    public List<InsUnidadesFuncionales> listarUnidadesFuncionalesPorIdDireccionFuncionalJPQL(Long idDireccionFuncional);

    public InsDireccionesFuncionales buscarDireccionFuncionalPorIdDireccionaFuncionalGET(Long idDireccionFuncional);

    public InsUnidadesFuncionales buscarInsUnidadesFuncionalesPorIdUnidadFuncionalGET(Long idUnidadFuncional);

    public void registrarInsDireccionesFuncionalesSET(InsDireccionesFuncionales insDireccionesFuncionales);

    public List<InsUnidadesFuncionales> listarUnidadesFuncionalesPorIdSedeJPQL(Long idSede);
    
    // ================== Nuevos modulso 
    
    public List<Facultades> listarFacultadesPorIdUniversidadJPQL(Long idUniversidad);
    
    public List<FclCarreras> listarCarrerasPorIdFacultadJPQL(Long idFacultad);
    
    public List<FclEstamentos> listarEstamentosJPQL();
    
    public Facultades buscarFacultadPorIdFacultadGET(Long idFacultad);
    
    public FclEstamentos buscarEstamentoPorIdEstamentoGET(Long idEstamento);
    
    public InsDireccionesFuncionales getDireccionFuncional(String direccion);

}
