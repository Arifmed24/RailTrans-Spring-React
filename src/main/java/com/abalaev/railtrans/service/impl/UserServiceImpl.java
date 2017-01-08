package com.abalaev.railtrans.service.impl;

import com.abalaev.railtrans.dao.api.UserDao;
import com.abalaev.railtrans.model.User;
import com.abalaev.railtrans.service.api.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUserByLogin(String login) {
        User user = null;
        try {
            user = userDao.findByLogin(login);
        } catch (NoResultException ignored) {
        }
        if (user == null) {
            LOG.info("user not found in database");
            return null;
        }
        return user;
    }

    @Override
    public User login(String login, String password) throws Exception {
        LOG.info("user login");
        User result;
        try {
            result = userDao.userLogin(login, password);
        } catch (Exception e) {
            throw new Exception("Unknown exception", e);
        }
        return result;
    }

    @Override
    public boolean register(User user) {
        LOG.info("user registration");
        User findUser = null;
        try {
            findUser = findUserByLogin(user.getLogin());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Error in finding user by login", e);
        }
        if (findUser == null) {
            userDao.create(user);
            LOG.info("new user created");
            return true;
        } else {
            LOG.info("this user is registered yet");
            return false;
        }
    }

    public User create(User user) {
        return userDao.create(user);
    }
}
