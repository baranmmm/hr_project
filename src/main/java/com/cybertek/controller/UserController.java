package com.cybertek.controller;

import com.cybertek.dto.UserDTO;
import com.cybertek.service.RoleService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;


    @GetMapping({"/create"})
    public String newUser(Model model){

        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("users", userService.findAll());

        return "/user/create";
    }

    @PostMapping("/create")
    public String insertUser(UserDTO user, Model model){

        userService.save(user);     //I am saving the user which created in @GetMapping

        model.addAttribute("user", new UserDTO());              //I need an empty user form
        model.addAttribute("roles", roleService.findAll());     //I need roles in my dropdown
        model.addAttribute("users", userService.findAll());     //I need user information in my table


        return "/user/create";


    }

    @GetMapping("/update/{userId}")
    public String editUser(@PathVariable("userId") String userId, Model model){

        model.addAttribute("user", userService.findById(userId));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.findAll());


        return "/user/update";

    }

    @PostMapping("/update/{userId}")
    public String updateUser(@PathVariable("userId") String userId,UserDTO user, Model model){

        userService.update(user);

        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("users", userService.findAll());



        return "/user/create";
    }
}
