package com.project.ppp.dao.hibernate;

import com.project.ppp.dao.RoleDao;
import com.project.ppp.entity.Role;
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
public class HibernateRoleService implements RoleDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateRoleService.class);

    @Autowired
    private SessionFactory session;

    @Override
    public void create(Role role) {
        LOGGER.info("method {create} started");
        Session session = this.session.openSession();
        session.beginTransaction();
        session.save(role);
        session.getTransaction().commit();
        session.close();
        LOGGER.info("method {create} finished");
    }

    @Override
    public void update(Role role) {
        LOGGER.info("method {update} started");
        Session session = this.session.openSession();
        session.beginTransaction();
        session.update(role);
        session.getTransaction().commit();
        session.close();
        LOGGER.info("method {update} finished");
    }

    @Override
    public void remove(Role role) {
        LOGGER.info("method {remove} started");
        Session session = this.session.openSession();
        session.beginTransaction();
        session.remove(role);
        session.getTransaction().commit();
        session.close();
        LOGGER.info("method {remove} finished");
    }

    @Override
    public List<Role> findAll() {
        LOGGER.info("method {findAll} started");
        Session session = this.session.openSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Role> cr = cb.createQuery(Role.class);
        Root<Role> root = cr.from(Role.class);
        cr.select(root);
        Query<Role> query = session.createQuery(cr);
        List<Role> list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        LOGGER.info("method {findAll} finished");
        return list;
    }

    @Override
    public Role findById(long id) {
        LOGGER.info("method {findById} started");
        Session session = this.session.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM jdbc_role WHERE id = :id";
        Query query = session.createNativeQuery(sql).addEntity(Role.class);
        query.setParameter("id", id);
        Role role = (Role) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        LOGGER.info("Method {findById} finished");

        return role;
    }

    @Override
    public Role findByName(String name) {
        LOGGER.info("Method {findByName} started");
        Session session = this.session.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM jdbc_role WHERE name = :name";
        Query query = session.createNativeQuery(sql).addEntity(Role.class);
        query.setParameter("name", name);
        Role role = (Role) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        LOGGER.info("Method {findByName} finished");

        return role;
    }
}
