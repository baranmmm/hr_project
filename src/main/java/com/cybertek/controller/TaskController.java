package com.cybertek.controller;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.ProjectService;
import com.cybertek.service.TaskService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;


    @GetMapping({"/create"})
    public String createTask(Model model){

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "/task/create";
    }

    @PostMapping("/create")
    public String saveTask(TaskDTO task, Model model){

        taskService.save(task);

        return "redirect:/task/create";

    }

    @GetMapping("/update/{taskId}")
    public String editTask(@PathVariable("taskId") Long taskId, Model model){


        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "/task/update";
    }

    @PostMapping("/update/{taskId}")
    public String updatedTask(@PathVariable("taskId") Long taskId, TaskDTO task, Model model){

        TaskDTO initialTask = taskService.findById(taskId);
        taskService.update(task);
        task.setStatus(initialTask.getStatus());
        task.setAssignDate(initialTask.getAssignDate());

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "/task/create";


    }

    @GetMapping("/delete/{taskId}")
    public String deleteProject(@PathVariable("taskId") Long taskId, Model model){

        taskService.deleteById(taskId);
        return "redirect:/task/create";
    }

    @GetMapping("/status")
    public String taskStatus(TaskDTO task, Model model){

        model.addAttribute("task", task);
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("statuses", taskService.findAllStatuses());


        return "/task/status";
    }

    @GetMapping("/status/{taskId}")
    public String taskStatusChange(@PathVariable("taskId") Long taskId, Model model){

        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("statuses", taskService.findAllStatuses());


        return "/task/status2";
    }

    @PostMapping("/status/{taskId}")
    public String taskStatusUpdated(@PathVariable("taskId") Long taskId, TaskDTO task, Model model){

        TaskDTO initialTask=taskService.findById(taskId);
        taskService.update(taskService.findById(taskId));
        initialTask.setStatus(task.getStatus());

        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("statuses", taskService.findAllStatuses());


        return "redirect:/task/status";
    }

}
