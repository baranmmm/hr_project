package com.cybertek.dto;

import com.cybertek.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@NoArgsConstructor
@Getter
@Setter
public class TaskDTO {

    private Long taskId;
    private ProjectDTO project;
    private UserDTO employee;
    private String taskSubject;
    private String taskDetail;
    private Status status;
    private LocalDate assignDate;


    public TaskDTO(ProjectDTO project, UserDTO employee, String taskSubject, String taskDetail, Status status, LocalDate assignDate) {
        this.project = project;
        this.employee = employee;
        this.taskSubject = taskSubject;
        this.taskDetail = taskDetail;
        this.status = status;
        this.assignDate = assignDate;
        this.taskId= UUID.randomUUID().getMostSignificantBits();
    }
}
