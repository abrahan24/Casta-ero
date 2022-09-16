/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uap.usic.siga.modelos.impl;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PnlCargos;
import uap.usic.siga.excepciones.ServicioException;
import uap.usic.siga.modelos.CargosDao;

/**
 *
 * @author Yessenia
 */
@Repository("cargosDao")
public class CargosDaoImpl implements CargosDao {

    @PersistenceContext
    private javax.persistence.EntityManager em;

    @Transactional(rollbackFor = {ServicioException.class})

    @Override
    public void registrarPnlCargosSET(PnlCargos cargos) {
        em.persist(cargos);
    }

    @Override
    public PnlCargos buscarPnlCargosPorIdPnlCargoGET(Long idPnlCargos) {
        String sql = " SELECT p "
                + " FROM PnlCargos p "
                + " WHERE p.idPnlCargos = :idPnlCargos "
                + " AND p.idEstado = 'A'";
        Query q = em.createQuery(sql);
        q.setParameter("idPnlCargos", idPnlCargos);
        try {
            return (PnlCargos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PnlCargos buscarPnlCargosPorCargoGET(String cargo) {
      String sql = " SELECT p "
                + " FROM PnlCargos p "
                + " WHERE p.cargo = :cargo "
                + " AND p.idEstado = 'A'";
        Query q = em.createQuery(sql);
        q.setParameter("cargo", cargo);
        try {
            return (PnlCargos) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
