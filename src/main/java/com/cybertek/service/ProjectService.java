package com.cybertek.service;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.UserDTO;

import java.util.List;

public interface ProjectService extends CrudService<ProjectDTO,String>{

    void completeProject(String projectCode);
    List<ProjectDTO> findAllCompletedProjects();
    List<ProjectDTO> listOfManagersProjects(UserDTO manager);
}
