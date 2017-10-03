package com.cristph.template.core.pojos.entity;

import com.cristph.template.core.pojos.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity<UserDTO>{

    private Long id;

    private String name;

    private String role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                "} " + super.toString();
    }
}