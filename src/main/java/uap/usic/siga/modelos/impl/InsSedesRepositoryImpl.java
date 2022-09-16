package uap.usic.siga.modelos.impl;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.InsSedes;
import uap.usic.siga.modelos.InsSedesRepository;

@Repository
@Transactional
public class InsSedesRepositoryImpl implements InsSedesRepository {

      @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(InsSedes t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(InsSedes t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InsSedes findOne(Long id) {
         return sessionFactory.getCurrentSession().load(InsSedes.class, id);
    }

    @Override
    public List<InsSedes> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUser(InsSedes t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
