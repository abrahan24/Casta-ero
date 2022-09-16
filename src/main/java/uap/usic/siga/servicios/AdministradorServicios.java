/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uap.usic.siga.servicios;

import uap.usic.siga.entidades.SisAdministrador;

/**
 *
 * @author Usuario
 */
public interface AdministradorServicios {

    public  SisAdministrador buscarGestionPeriodoSisAdministradorPorIdSisAdministradorGET(Long idMnuTipoFuncion, Long idPersona);
 
}
