package com.cybertek.converter;


import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.service.ProjectService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@ConfigurationPropertiesBinding
@Component
public class ProjectDTOConverter implements Converter<String , ProjectDTO> {

    @Autowired
    ProjectService projectService;


    @Override
    public ProjectDTO convert(String projectCode) {

        return projectService.findById(projectCode);
    }
}
