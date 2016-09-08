package com.dev.dto.converter;

import com.dev.dto.UserDTO;
import com.dev.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class UserConverter implements Converter<UserEntity, UserDTO> {

    /**
     * Pattern from BCryptPasswordEncoder sources.
     */
    private static final Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO convert(UserEntity inputObject) {
        return new UserDTO()
                .setId(inputObject.getId())
                .setName(inputObject.getName())
                .setPassword(inputObject.getPassword());
    }

    @Override
    public List<UserDTO> convert(List<UserEntity> inputList) {
        List<UserDTO> result = new ArrayList<>();
        inputList.forEach(i -> result.add(convert(i)));
        return result;
    }

    @Override
    public UserEntity convertToEntity(UserDTO inputObject) {
        UserEntity entity = new UserEntity();
        entity.setId(inputObject.getId());
        entity.setName(inputObject.getName());
        String rawPassword = inputObject.getPassword();
        entity.setPassword(isPasswordEncoded(rawPassword) ? rawPassword : passwordEncoder.encode(rawPassword));
        return entity;
    }

    private boolean isPasswordEncoded(String rawPassword){
        return BCRYPT_PATTERN.matcher(rawPassword).matches();
    }
}
