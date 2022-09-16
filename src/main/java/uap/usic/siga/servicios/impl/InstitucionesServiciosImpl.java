/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uap.usic.siga.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.entidades.Facultades;
import uap.usic.siga.entidades.FclCarreras;
import uap.usic.siga.entidades.FclEstamentos;
import uap.usic.siga.entidades.InsDireccionesFuncionales;
import uap.usic.siga.entidades.InsSedes;
import uap.usic.siga.entidades.InsUbicacionesOrganicas;
import uap.usic.siga.entidades.InsUnidadesFuncionales;
import uap.usic.siga.entidades.Institucion;
import uap.usic.siga.entidades.Instituciones;
import uap.usic.siga.modelos.InstitucionesDao;
import uap.usic.siga.servicios.InstitucionesServicios;

/**
 *
 * @author Yessenia
 */
@Service("institucionesServicios")
@Transactional
public class InstitucionesServiciosImpl implements InstitucionesServicios {

    @Autowired
    private InstitucionesDao dao;

    @Override
    public List<InsSedes> listarInsSedes() {
        return dao.listarInsSedesJQPL();
    }

    @Override
    public List<Instituciones> listarInstituciones() {
        return dao.listarInstitucionesGET();
    }

    @Override
    public void registrarInsSedes(InsSedes inssedes) {
        dao.registrarInsSedesSET(inssedes);
    }

    @Override
    public List<InsUbicacionesOrganicas> listarInsUbicacionesOrganicasIdSede(Long idSede) {
        return dao.listarInsUbicacionesOrganicasIdSedeGET(idSede);
    }

    @Override
    public List<String> listarUbicaciones(Long id) {
        return dao.listarUbicacionesGET(id);
    }

    @Override
    public InsSedes buscarInsSedes(Long idSede) {
        return dao.buscarInsSedesGET(idSede);
    }

    @Override
    public List<Institucion> listarInstitucion() {
        return dao.listarInstitucionGET();
    }

    @Override
    public List<InsSedes> listarSedesPorIdInstitucion(Long idInstitucion) {
        return dao.listarSedesPorIdInstitucionJPQL(idInstitucion);
    }

    @Override
    public InsSedes buscarInsSedesPorSede(String sede) {
        return dao.buscarInsSedesPorSedeGET(sede);
    }

    @Override
    public void guardarInsSedes(InsSedes insSede) {
        dao.guardarInsSedesSET(insSede);
    }

    @Override
    public InsUnidadesFuncionales buscarInsUnidadesFuncionalesPorUnidad(String unidadFuncional) {
        return dao.buscarInsUnidadesFuncionalesPorUnidadGET(unidadFuncional);
    }

    @Override
    public void guardarInsUnidadesFuncionales(InsUnidadesFuncionales insUnidadesFuncionales) {
        dao.guardarInsUnidadesFuncionalesSET(insUnidadesFuncionales);
    }

    @Override
    public void guardarInstituciones(Instituciones instituciones) {
        dao.guardarInstitucionesSET(instituciones);
    }

    @Override
    public List<InsUnidadesFuncionales> listarUnidadesFuncionales() {
        return dao.listarUnidadesFuncionalesJPQL();
    }

    @Override
    public List<InsDireccionesFuncionales> listarDireccionesFuncionalesPorIdSede(Long idSede) {
        return dao.listarDireccionesFuncionalesPorIdSedeJPQL(idSede);
    }

    @Override
    public List<InsUnidadesFuncionales> listarUnidadesFuncionalesPorIdDireccionFuncional(Long idDireccionFuncional) {
        return dao.listarUnidadesFuncionalesPorIdDireccionFuncionalJPQL(idDireccionFuncional);
    }

    @Override
    public InsDireccionesFuncionales buscarDireccionFuncionalPorIdDireccionaFuncional(Long idDireccionFuncional) {
        return dao.buscarDireccionFuncionalPorIdDireccionaFuncionalGET(idDireccionFuncional);
    }

    @Override
    public InsUnidadesFuncionales buscarInsUnidadesFuncionalesPorIdUnidadFuncional(Long idUnidadFuncional) {
        return dao.buscarInsUnidadesFuncionalesPorIdUnidadFuncionalGET(idUnidadFuncional);
    }

    @Override
    public void registrarInsDireccionesFuncionales(InsDireccionesFuncionales insDireccionesFuncionales) {
        dao.registrarInsDireccionesFuncionalesSET(insDireccionesFuncionales);
    }

    @Override
    public List<InsUnidadesFuncionales> listarUnidadesFuncionalesPorIdSede(Long idSede) {
        return dao.listarUnidadesFuncionalesPorIdSedeJPQL(idSede);
    }

	@Override
	public List<Facultades> listarFacultadesPorIdUniversidad(Long idUniversidad) {
		return dao.listarFacultadesPorIdUniversidadJPQL(idUniversidad);
	}

	@Override
	public List<FclCarreras> listarCarrerasPorIdFacultad(Long idFacultad) {
		return dao.listarCarrerasPorIdFacultadJPQL(idFacultad);
	}

	@Override
	public List<FclEstamentos> listarEstamentos() {
		return dao.listarEstamentosJPQL();
	}

	@Override
	public Facultades buscarFacultadPorIdFaculta(Long idFacultad) {
		return dao.buscarFacultadPorIdFacultadGET(idFacultad);
	}

	@Override
	public FclEstamentos buscarEstamentoPorIdEstamento(Long idEstamento) {
		return dao.buscarEstamentoPorIdEstamentoGET(idEstamento);
	}

	@Override
	public InsDireccionesFuncionales getDireccionFuncional(String direccion) {
		// TODO Auto-generated method stub
		return null;
	}

}
