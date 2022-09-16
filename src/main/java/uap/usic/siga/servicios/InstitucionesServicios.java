package uap.usic.siga.servicios;

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
public interface InstitucionesServicios {

    public List<InsSedes> listarInsSedes();

    public List<Instituciones> listarInstituciones();

    void registrarInsSedes(InsSedes inssedes);

    public List<InsUbicacionesOrganicas> listarInsUbicacionesOrganicasIdSede(Long idSede);

    public List<String> listarUbicaciones(Long id);

    public InsSedes buscarInsSedes(Long idSede);

    public List<Institucion> listarInstitucion();

    public List<InsSedes> listarSedesPorIdInstitucion(Long idInstitucion);

    public InsSedes buscarInsSedesPorSede(String sede);

    public void guardarInsSedes(InsSedes insSedes);

    public InsUnidadesFuncionales buscarInsUnidadesFuncionalesPorUnidad(String unidadFuncional);

    public void guardarInsUnidadesFuncionales(InsUnidadesFuncionales insUnidadesFuncionales);

    public void guardarInstituciones(Instituciones instituciones);

    public List<InsUnidadesFuncionales> listarUnidadesFuncionales();

    public List<InsDireccionesFuncionales> listarDireccionesFuncionalesPorIdSede(Long idSede);

    public List<InsUnidadesFuncionales> listarUnidadesFuncionalesPorIdDireccionFuncional(Long idDireccionFuncional);

    public InsDireccionesFuncionales buscarDireccionFuncionalPorIdDireccionaFuncional(Long idDireccionFuncional);
    
    public InsDireccionesFuncionales getDireccionFuncional(String direccion);

    public InsUnidadesFuncionales buscarInsUnidadesFuncionalesPorIdUnidadFuncional(Long idUnidadFuncional);

    public void registrarInsDireccionesFuncionales(InsDireccionesFuncionales insDireccionesFuncionales);

    public List<InsUnidadesFuncionales> listarUnidadesFuncionalesPorIdSede(Long idSede);

// ================== Nuevos modulso 
    
    public List<Facultades> listarFacultadesPorIdUniversidad(Long idUniversidad);
    
    public List<FclCarreras> listarCarrerasPorIdFacultad(Long idFacultad);
    
    public List<FclEstamentos> listarEstamentos();
    
 public Facultades buscarFacultadPorIdFaculta(Long idFacultad);
    
    public FclEstamentos buscarEstamentoPorIdEstamento(Long idEstamento);
    
}
