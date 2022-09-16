/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uap.usic.siga.modelos;

import uap.usic.siga.entidades.SisAdministrador;

/**
 *
 * @author Usuario
 */
public interface AdministradorDao {
    
        public  SisAdministrador buscarGestionPeriodoSisAdministradorPorIdSisAdministradorGET(Long idMnuTipoFuncion, Long idPersona);

    
}
