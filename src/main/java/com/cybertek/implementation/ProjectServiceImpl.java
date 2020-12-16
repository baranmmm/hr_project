package com.cybertek.implementation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.ProjectService;
import com.cybertek.service.TaskService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;


    @Override
    public ProjectDTO save(ProjectDTO object) {
        return super.save(object, object.getProjectCode());
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {
        super.update(object.getProjectCode(),object);
    }

    @Override
    public void delete(ProjectDTO object) {
        super.delete(object);
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public void completeProject(String projectCode) {
        super.findById(projectCode).setProjectStatus(Status.COMPLETE);
        super.findById(projectCode).setEndDate(LocalDate.now());
        super.save(super.findById(projectCode), projectCode);
    }

    @Override
    public List<ProjectDTO> findAllCompletedProjects() {

        List<ProjectDTO> completedProjects = super.findAll().stream().filter(projectDTO -> projectDTO.getProjectStatus().equals(Status.COMPLETE)).collect(Collectors.toList());
        return completedProjects;
    }

    public List<ProjectDTO> listOfManagersProjects(UserDTO manager){

        List<ProjectDTO> projectsOfManager = projectService.findAll().stream()
                .filter(projectDTO -> projectDTO.getAssignedManager().equals(manager)).collect(Collectors.toList());


        long allTasks=0;
        long CompletedTasks=0;
        long unfinishedTasks=0;

        for (int i = 0; i < projectsOfManager.size(); i++) {
            int finalI = i;
            CompletedTasks = taskService.findAll().stream().filter(taskDTO -> taskDTO.getProject().equals(projectsOfManager.get(finalI)))
                    .filter(taskDTO -> taskDTO.getStatus().equals(Status.COMPLETE)).count();
            allTasks = taskService.findAll().stream().filter(taskDTO -> taskDTO.getProject().equals(projectsOfManager.get(finalI))).count();
            unfinishedTasks=allTasks-CompletedTasks;
            projectsOfManager.get(finalI).setCompletedTaskRatio(unfinishedTasks+"/"+CompletedTasks);

        }

        return projectsOfManager;
    }
}
