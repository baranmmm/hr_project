package com.cybertek.dto;

import com.cybertek.utils.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean enabled;
    private String phone;
    private RoleDTO role;
    private Gender gender;

}
