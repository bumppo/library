package com.dev.dto;

import com.dev.validation.CustomValidationGroup;
import com.dev.validation.annotations.UserDTOConstraint;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.GroupSequence;

@GroupSequence({UserDTO.class, CustomValidationGroup.class})
@UserDTOConstraint(groups = CustomValidationGroup.class)
public class UserDTO extends BaseDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String password;

    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
