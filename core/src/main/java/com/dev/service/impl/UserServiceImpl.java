package com.dev.service.impl;

import com.dev.dto.UserDTO;
import com.dev.dto.converter.UserConverter;
import com.dev.entity.UserEntity;
import com.dev.repository.UserRepository;
import com.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends AbstractServiceImpl<UserRepository, UserEntity, UserDTO> implements UserService {


}
