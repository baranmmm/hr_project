package com.cybertek.service;

import com.cybertek.dto.TaskDTO;

import java.util.List;

public interface TaskService extends CrudService<TaskDTO, Long>{

    List<TaskDTO> findAllCompletedTasks();
}
