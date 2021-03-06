package com.trajan.seed.webapp.domain.security.service;

import com.trajan.seed.webapp.domain.security.User;
import com.trajan.seed.webapp.rest.exception.OperationOnNonexistentResourceException;

import java.util.List;

/**
 * @author Tomas Trajan
 * @creaded 2014-07-05
 */
public interface UserService {

    List<User> findAll();

    User findByUsername(String username);

    void createUser(User user);

    void updateUser(String username, User user) throws OperationOnNonexistentResourceException;

    void deleteUser(String username) throws OperationOnNonexistentResourceException;
}
