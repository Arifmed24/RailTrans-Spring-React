package com.abalaev.railtrans.dao.impl;

import com.abalaev.railtrans.dao.api.UserDao;
import com.abalaev.railtrans.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {
    @Override
    public User findByLogin(String login) {
        Query query;
        query = em.createNamedQuery("User.findByLogin", User.class);
        query.setParameter("login", login);
        return (User) query.getSingleResult();
    }

    @Override
    public User userLogin(String login, String password) {
        Query query = em.createNamedQuery("User.login", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        return (User) query.getSingleResult();
    }
}
