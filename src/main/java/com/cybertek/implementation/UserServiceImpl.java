package com.cybertek.implementation;

import com.cybertek.dto.UserDTO;
import com.cybertek.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractMapService<UserDTO, String> implements UserService {
    @Override
    public UserDTO save(UserDTO object) {
        return super.save(object.getUserId(), object);
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
}
