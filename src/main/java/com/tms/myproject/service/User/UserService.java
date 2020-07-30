package com.tms.myproject.service.User;

import com.tms.myproject.model.Role;
import com.tms.myproject.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User findByLogin(String login);
    List<User> findAllUser();
}
