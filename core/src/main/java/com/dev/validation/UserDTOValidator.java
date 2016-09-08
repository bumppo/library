package com.dev.validation;

import com.dev.dto.UserDTO;
import com.dev.entity.UserEntity;
import com.dev.repository.UserRepository;
import com.dev.validation.annotations.UserDTOConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UserDTOValidator implements ConstraintValidator<UserDTOConstraint, UserDTO> {

    @Autowired
    UserRepository repository;

    @Override
    public void initialize(UserDTOConstraint constraintAnnotation) {

    }

    /**
     * Check if userName already exist in DB.
     *
     * @param value UserDTO
     * @param context context
     * @return false if exist
     */
    @Override
    public boolean isValid(UserDTO value, ConstraintValidatorContext context) {
        for (UserEntity entity : repository.getAll()){
            if (entity.getName().equals(value.getName()) && !entity.getId().equals(value.getId())){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("This User Name already exist!")
                        .addPropertyNode("name").addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
