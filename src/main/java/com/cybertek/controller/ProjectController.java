package com.cybertek.controller;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.service.ProjectService;
import com.cybertek.service.RoleService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;


        @Autowired
        RoleService roleService;

        @Autowired
        UserService userService;


        @GetMapping({"/create"})
        public String newProject(Model model){

            model.addAttribute("project", new ProjectDTO());
            model.addAttribute("projects", projectService.findAll());
            model.addAttribute("managers", userService.findAll());

            return "/project/create";
        }

        @PostMapping("/create")
        public String insertUser(ProjectDTO project, Model model){

            projectService.save(project);     //I am saving the user which created in @GetMapping
            return "redirect:/project/create";

        }





}
