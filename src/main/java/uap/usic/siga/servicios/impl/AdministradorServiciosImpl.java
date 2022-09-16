package uap.usic.siga.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.SisAdministrador;
import uap.usic.siga.modelos.AdministradorDao;
import uap.usic.siga.servicios.AdministradorServicios;

/**
 *
 * @author Usuario
 */
@Service("administradorServicios")
@Transactional
public class AdministradorServiciosImpl implements AdministradorServicios{
    
    @Autowired
    private AdministradorDao dao;
       

    @Override
    public SisAdministrador buscarGestionPeriodoSisAdministradorPorIdSisAdministradorGET(Long idMnuTipoFuncion, Long idPersona) {
        return dao.buscarGestionPeriodoSisAdministradorPorIdSisAdministradorGET(idMnuTipoFuncion, idPersona);
    }
}
