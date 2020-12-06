package com.cybertek.bootstrap;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.RoleDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.ProjectService;
import com.cybertek.service.RoleService;
import com.cybertek.service.UserService;
import com.cybertek.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataGenerator implements CommandLineRunner {
    RoleService roleService;
    UserService userService;
    ProjectService projectService;


    @Autowired
    public DataGenerator(RoleService roleService, UserService userService, ProjectService projectService) {
        this.roleService = roleService;
        this.userService = userService;
        this.projectService = projectService;
    }




    @Override
    public void run(String... args) throws Exception {
        RoleDTO adminRole= new RoleDTO(1L, "Admin");
        RoleDTO managerRole= new RoleDTO(2L, "Manager");
        RoleDTO employeeRole= new RoleDTO(3L, "Employee");
        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(employeeRole);
        UserDTO user1 = new UserDTO("1","John", "Kesy",
                "john@cybertek.com", "Abc1", true, "7459684532", managerRole, Gender.MALE);
        UserDTO user5 = new UserDTO("5","Mike", "Smith",
                "mike@cybertek.com", "Abc2", true, "7459684532", adminRole, Gender.MALE);
        UserDTO user2 = new UserDTO("2","Delisa",
                "Norre", "delisa@cybertek.com", "123", true, "8567412358", managerRole, Gender.FEMALE);
        UserDTO user3 = new UserDTO("3","Craig", "Jark",
                "craig@cybertek.com", "Abc3", true, "7777775566", employeeRole, Gender.MALE);
        UserDTO user4 = new UserDTO("4","Shaun",
                "Hayns", "shaun@cybertek.com", "Abc4", true, "3256987412", managerRole, Gender.MALE);
        UserDTO user6 = new UserDTO("6","Elizebeth",
                "Loren", "elizebeth@cybertek.com", "Abc4", true, "5306987412", employeeRole, Gender.FEMALE);
        UserDTO user7 = new UserDTO("7","Maria",
                "Ada", "maria@cybertek.com", "Abc4", true, "9996987412", employeeRole, Gender.FEMALE);
        UserDTO user8 = new UserDTO("8","Bill",
                "Matt", "maria@cybertek.com", "Abc4", true, "8881239846", employeeRole, Gender.MALE);
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);
        userService.save(user8);

        ProjectDTO project1=new ProjectDTO("Spring MVC", "PR001", user1, LocalDate.now(),LocalDate.now().plusDays(30),"Creating Controller", Status.OPEN);
        ProjectDTO project2=new ProjectDTO("Spring ORM", "PR002", user3, LocalDate.now(),LocalDate.now().plusDays(60),"Creating Database", Status.IN_PROGRESS);
        ProjectDTO project3=new ProjectDTO("Spring Container", "PR003", user1, LocalDate.now (),LocalDate.now().plusDays(15),"Creating Container", Status.UAT_TEST);

        projectService.save(project1);
        projectService.save(project2);
        projectService.save(project3);

    }




}
