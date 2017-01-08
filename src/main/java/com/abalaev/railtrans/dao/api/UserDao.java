package com.abalaev.railtrans.dao.api;

import com.abalaev.railtrans.model.User;

public interface UserDao extends GenericDao<User, Integer>  {
    User findByLogin(String login);
    User userLogin(String login, String password);
}
