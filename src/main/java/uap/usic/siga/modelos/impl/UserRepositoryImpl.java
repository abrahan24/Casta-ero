package uap.usic.siga.modelos.impl;

import uap.usic.siga.modelos.UserRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import uap.usic.siga.entidades.Usuarios;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Usuarios user) {
        return (Long) sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean update(Usuarios user) {
        // Todod
        return false;
    }

    @Override
    public Usuarios findOne(Long id) {
        return sessionFactory.getCurrentSession().get(Usuarios.class, id);
    }

    @Override
    public List<Usuarios> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Usuarios", Usuarios.class).list();
    }

    @Override
    public Usuarios getUserByUsername(String username) {
        Query<Usuarios> query = sessionFactory.getCurrentSession().createQuery("FROM Usuarios u where u.usuario=:username", Usuarios.class);
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    @Override
    public List<Usuarios> getAllAdmins() {
        NativeQuery<Usuarios> query = sessionFactory.getCurrentSession().createNativeQuery("SELECT * FROM Usuarios u where id in (select user_id FROM user_authority where authority_id=(select id FROM authority where name=:role))");
        query.setParameter("role", "ROLE_ADMIN");
        query.addEntity(Usuarios.class);
        return query.list();
    }

    @Override
    public void updateUser(Usuarios user) {
        sessionFactory.getCurrentSession().update(user);
    }

   
    
}
