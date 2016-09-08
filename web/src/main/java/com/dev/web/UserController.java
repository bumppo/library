package com.dev.web;

import com.dev.dto.UserDTO;
import com.dev.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/rest/users")
public class UserController extends AbstractController<UserService, UserDTO> {


}
