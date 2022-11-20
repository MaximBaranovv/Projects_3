package com.project.ppp.dao.hibernate;

import com.project.ppp.dao.UserDao;
import com.project.ppp.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class HibernateUserService implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUserService.class);

    @Autowired
    private SessionFactory session;

    @Override
    public void create(User user) {
        LOGGER.info("Method {create} started");
        Session session = this.session.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        LOGGER.info("Method {create} finished");
    }

    @Override
    public void update(User user) {
        LOGGER.info("Method {update} started");
        Session session = this.session.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        LOGGER.info("Method {update} finished");

    }

    @Override
    public void remove(User user) {
        LOGGER.info("Method {remove} started");
        Session session = this.session.openSession();
        session.beginTransaction();
        session.remove(user);
        session.getTransaction().commit();
        session.close();
        LOGGER.info("Method {remove} finished");
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("Method {findAll} started");
        Session session = this.session.openSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root);
        Query<User> query = session.createQuery(cr);
        List<User> list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        LOGGER.info("Method {findAll} finished");
        return list;
    }

    @Override
    public User findById(long id) {
        LOGGER.info("Method {findById} started");
        Session session = this.session.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM jdbc_user WHERE id = :id";
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.setParameter("id", id);
        User user = (User) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        LOGGER.info("Method {findById} finished");

        return user;
    }

    @Override
    public User findByLogin(String login) {
        LOGGER.info("Method {findByLogin} started");
        Session session = this.session.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM jdbc_user WHERE login = :login";
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.setParameter("login", login);
        User user = (User) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        LOGGER.info("Method {findByLogin} finished");

        return user;
    }

    @Override
    public User findByEmail(String email) {
        LOGGER.info("Method {findByEmail} started");
        Session session = this.session.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM jdbc_user WHERE email = :email";
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.setParameter("email", email);
        User user = (User) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        LOGGER.info("Method {findByEmail} finished");

        return user;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        LOGGER.info("Method {findByLoginAndPassword} started");
        Session session = this.session.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM jdbc_user WHERE login = :login AND password = :password";
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        User user = (User) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        LOGGER.info("Method {findByLoginAndPassword} finished");

        return user;
    }
}
