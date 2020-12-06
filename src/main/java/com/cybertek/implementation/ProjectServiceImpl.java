package com.cybertek.implementation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.ProjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {
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
}
