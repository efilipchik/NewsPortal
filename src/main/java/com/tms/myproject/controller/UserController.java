package com.tms.myproject.controller;

import com.tms.myproject.model.Author;
import com.tms.myproject.model.Role;
import com.tms.myproject.model.User;
import com.tms.myproject.service.Author.AuthorService;
import com.tms.myproject.service.Role.RoleService;
import com.tms.myproject.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final AuthorService authorService;

    @Autowired
    public UserController(UserService userService, RoleService roleService, AuthorService authorService) {
        this.userService = userService;
        this.roleService = roleService;
        this.authorService = authorService;
    }

    @GetMapping(value = "getAllUsers")
    public String getAllUsers(Model model) {
        List<User> allUs = userService.findAllUser();
        model.addAttribute("Users", allUs);
        return "getAllUsers";
    }

    @GetMapping(value = "loginUser")
    public String getLoginUser() {
        return "loginUser";
    }

    @GetMapping(value = "saveUser")
    public String getSaveUser(){
        return "saveUser";
    }

    @PostMapping(value = "saveUser")
    public RedirectView postSaveUser(@RequestParam String login, @RequestParam String password, @RequestParam String name) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setRoles(Arrays.asList(roleService.findRoleByName("USER")));
        userService.saveUser(user);

        Author author = new Author();
        author.setFullName(name);
        authorService.saveAuthor(author);
        return new RedirectView("getAllUsers");
    }

//    @PostMapping(value = "changeRole")
//    public RedirectView postChangeRole(@RequestParam  role) {
//        User user = userService.findByLogin(login);
//        user.getId();
//        return new RedirectView("changeRole");
//    }

}