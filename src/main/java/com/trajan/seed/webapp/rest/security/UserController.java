package com.trajan.seed.webapp.rest.security;

import com.google.common.collect.Lists;
import com.trajan.seed.webapp.domain.security.User;
import com.trajan.seed.webapp.domain.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tomas Trajan
 * @creaded 2014-07-05
 */
@RestController
@RequestMapping("/api/secure/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return Lists.newArrayList(userService.findAll());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void createUser (@RequestBody User user) {
        userService.createUser(user);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public User getUserByUsername (@PathVariable String username) {
        return userService.findByUsername(username);
    }
}
