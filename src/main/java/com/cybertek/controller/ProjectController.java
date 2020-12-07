package com.cybertek.controller;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.ProjectService;
import com.cybertek.service.RoleService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;

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
            model.addAttribute("managers", userService.findManagers());

            return "/project/create";
        }

        @PostMapping("/create")
        public String insertUser(ProjectDTO project, Model model){

            projectService.save(project);     //I am saving the user which created in @GetMapping
            project.setProjectStatus(Status.OPEN);

            return "redirect:/project/create";

        }

        @GetMapping("/update/{projectCode}")
        public String editProject(@PathVariable("projectCode") String projectCode, Model model){


            model.addAttribute("project", projectService.findById(projectCode));
            model.addAttribute("projects", projectService.findAll());
            model.addAttribute("managers", userService.findManagers());

            return "/project/update";
        }

        @PostMapping("/update/{projectCode}")
        public String updatedProject(@PathVariable("projectCode") String projectCode, ProjectDTO project, Model model){

            Status initialProjectStatus = projectService.findById(projectCode).getProjectStatus();
            projectService.update(project);
            projectService.findById(projectCode).setProjectStatus(initialProjectStatus);


            model.addAttribute("project", new ProjectDTO());
            model.addAttribute("projects", projectService.findAll());
            model.addAttribute("managers", userService.findAll());

            return "/project/create";


        }


    @GetMapping("/delete/{projectCode}")
    public String deleteProject(@PathVariable("projectCode") String projectCode, Model model){

        projectService.deleteById(projectCode);
        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectCode}")
    public String completeProject(@PathVariable("projectCode") String projectCode, Model model){

        projectService.completeProject(projectCode);
        return "redirect:/project/create";

    }





}
