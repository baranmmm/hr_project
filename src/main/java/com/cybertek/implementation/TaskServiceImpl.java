package com.cybertek.implementation;

import com.cybertek.dto.TaskDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO,Long> implements TaskService {

    @Autowired
    TaskService taskService;

    @Override
    public TaskDTO save(TaskDTO task) {
        if(task.getStatus()==null){
            task.setStatus(Status.OPEN);
        }
        if (task.getAssignDate()==null){
            task.setAssignDate(LocalDate.now());
        }

        task.setTaskId(UUID.randomUUID().getMostSignificantBits());
        return super.save(task,task.getTaskId());
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public TaskDTO findById(Long taskId) {
        return super.findById(taskId);
    }

    @Override
    public void deleteById(Long taskId) {
        super.deleteById(taskId);
    }

    @Override
    public void delete(TaskDTO task) {
        super.delete(task);
    }

    @Override
    public void update(TaskDTO task) {

        super.update(task.getTaskId(),task);
    }

    @Override
    public List<TaskDTO> findAllCompletedTasks() {

        List<TaskDTO> completedTasks = super.findAll().stream().filter(taskDTO -> taskDTO.getStatus().equals(Status.COMPLETE)).collect(Collectors.toList());

        return completedTasks;
    }
}
