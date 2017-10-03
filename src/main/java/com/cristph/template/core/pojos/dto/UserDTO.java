package com.cristph.template.core.pojos.dto;

import com.cristph.template.core.pojos.entity.User;
import com.cristph.template.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO<User> {

    private Long id;

    private String name;

    private String role;

    @Override
    public boolean checkLegal() {
        if (StringUtils.isEmptyOrWhitespaceOnly(name)) {
            return false;
        }
        return true;
    }
}