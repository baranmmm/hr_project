package com.cybertek.implementation;

import com.cybertek.dto.UserDTO;
import com.cybertek.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends AbstractMapService<UserDTO,String> implements UserService {
    @Override
    public UserDTO save(UserDTO object) {

        String userId=""+(findAll().size()+1);
        object.setUserId(userId);
        return super.save(object, object.getUserId());
    }

    @Override
    public List<UserDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(UserDTO object) {
        super.delete(object);
    }

    @Override
    public void update(UserDTO object) {
        super.update(object.getUserId(),object);
    }

    @Override
    public void deleteById(String userId) {
        super.deleteById(userId);
    }

    @Override
    public UserDTO findById(String userId) {
        return super.findById(userId);
    }

    @Override
    public List<UserDTO> findManagers() {
        return super.findAll().stream().filter(userDTO -> userDTO.getRole().getId()==2).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findEmployees() {
        return super.findAll().stream().filter(userDTO -> userDTO.getRole().getId()==3).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findAdmins() {
        return super.findAll().stream().filter(userDTO -> userDTO.getRole().getId()==1).collect(Collectors.toList());
    }
}
