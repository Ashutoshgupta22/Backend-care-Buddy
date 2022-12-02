package org.mitraz.MITRAz.controller;

import org.mitraz.MITRAz.model.user.User;
import org.mitraz.MITRAz.model.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping("/user/get")
    public User getUser() {
        return userDao.getUser();
    }

    @PostMapping("/user/save")
    public User saveUser(@RequestBody User user) {

        return userDao.saveUser(user);
    }
}
