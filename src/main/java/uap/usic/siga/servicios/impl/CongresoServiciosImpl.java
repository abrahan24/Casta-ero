package uap.usic.siga.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uap.usic.siga.entidades.CngCongresistas;
import uap.usic.siga.entidades.CngTiposCongresistas;
import uap.usic.siga.entidades.CongresoUap;
import uap.usic.siga.modelos.CongresoDao;
import uap.usic.siga.servicios.CongresoServicios;

/**
 *
 * @author Freddy Morales
 */
@Service("congresoServicios")
@Transactional
public class CongresoServiciosImpl implements CongresoServicios {

	 @Autowired
	 private CongresoDao dao;

	@Override
	public List<CngTiposCongresistas> listarTiposCongresistas() {
		return dao.listarTiposCongresistasJPQL();
	}

	@Override
	public List<CngCongresistas> listarCongresistasPorGestion(Integer gestion) {
		return dao.listarCongresistasPorGestionJPQL(gestion);
	}

	@Override
	public List<CongresoUap> listarCongresosGeneral() {
		return dao.listarCongresosGeneralJPQL();
	}

	@Override
	public CngCongresistas registrarCongresistas(CngCongresistas cngCongresistas) {
		return dao.registrarCongresistasSET(cngCongresistas);
				
	}

 @Override
	public CngCongresistas buscarCngCongresistasPorIdCngCongresistas(Long idCngCongresista) {
		return dao.buscarCngCongresistasPorIdCngCongresistasGET(idCngCongresista);
	}

@Override
public CngCongresistas modificarCngCongresistas(CngCongresistas cngCongresistas) {
	// TODO Esbozo de método generado automáticamente
	return null;
}
	
}
