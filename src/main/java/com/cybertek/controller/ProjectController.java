package com.cybertek.controller;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.ProjectService;
import com.cybertek.service.RoleService;
import com.cybertek.service.TaskService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;


    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;


    @GetMapping({"/create"})
    public String newProject(Model model) {

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("managers", userService.findManagers());

        return "/project/create";
    }

    @PostMapping("/create")
    public String insertUser(ProjectDTO project, Model model) {

        projectService.save(project);     //I am saving the user which created in @GetMapping
        project.setProjectStatus(Status.OPEN);


        return "redirect:/project/create";

    }

    @GetMapping("/update/{projectCode}")
    public String editProject(@PathVariable("projectCode") String projectCode, Model model) {

        String initialcompletedTaskRate=projectService.findById(projectCode).getCompletedTaskRatio();
        projectService.findById(projectCode).setCompletedTaskRatio(initialcompletedTaskRate);
        projectService.save(projectService.findById(projectCode));
        model.addAttribute("project", projectService.findById(projectCode));
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("managers", userService.findManagers());



        return "/project/update";
    }

    @PostMapping("/update/{projectCode}")
    public String updatedProject(@PathVariable("projectCode") String projectCode, ProjectDTO project, Model model) {

        Status initialProjectStatus = projectService.findById(projectCode).getProjectStatus();
        String initialcompletedTaskRate=projectService.findById(projectCode).getCompletedTaskRatio();
        projectService.update(project);
        project.setCompletedTaskRatio(initialcompletedTaskRate);
        project.setProjectStatus(initialProjectStatus);
        projectService.save(project);


        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("managers", userService.findAll());

        return "/project/create";


    }


    @GetMapping("/delete/{projectCode}")
    public String deleteProject(@PathVariable("projectCode") String projectCode, Model model) {

        projectService.deleteById(projectCode);
        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectCode}")
    public String completeProject(@PathVariable("projectCode") String projectCode, Model model) {

        projectService.completeProject(projectCode);
        return "redirect:/project/create";

    }
    @GetMapping("/status/complete/{projectCode}")
    public String completeProject2(@PathVariable("projectCode") String projectCode, Model model) {

        projectService.completeProject(projectCode);


        return "redirect:/project/status";

    }


    @GetMapping("/status")
    public String projectStatus(Model model){
        UserDTO manager=userService.findById("1");
        List<ProjectDTO> projectList = projectService.listOfManagersProjects(manager);
        model.addAttribute("projects", projectList);
        return "/project/status";
    }


    @GetMapping("/archive")
    public String taskStatus(Model model){

        model.addAttribute("completedTasks", taskService.findAllCompletedTasks());

        return "/project/archive";
    }





}










