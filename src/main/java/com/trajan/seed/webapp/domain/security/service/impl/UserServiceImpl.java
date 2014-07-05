package com.trajan.seed.webapp.domain.security.service.impl;

import com.google.common.collect.Lists;
import com.trajan.seed.webapp.domain.security.User;
import com.trajan.seed.webapp.domain.security.service.UserService;
import com.trajan.seed.webapp.persistence.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tomas Trajan
 * @creaded 2014-07-05
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void createUser(User user) {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        user.setPassword(bc.encode(user.getPassword()));
        userRepository.save(user);
    }

}
