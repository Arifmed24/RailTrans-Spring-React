package com.abalaev.railtrans.service.api;

import com.abalaev.railtrans.model.User;

public interface UserService {

    User findUserByLogin(String login);

    User login(String login, String password) throws Exception;

    boolean register(User user);

    User create(User user);
}
