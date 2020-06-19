package com.main.app.controller;

import com.main.app.domain.Entities;
import com.main.app.domain.UserDTO;
import com.main.app.domain.model.User;
import com.main.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing API for register drivers and customers.
 * <p>
 * URL : /user
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;


    @Autowired
    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping(path = "/current")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return new ResponseEntity<>(
                userService.getCurrentUserDTO(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {

        User savedUser = userService.register(userDTO);

        return new ResponseEntity<>(new UserDTO(savedUser), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Entities> getUsers(Pageable page) {
        Entities result = new Entities();

        Page<User> users = userService.getUsers(page);

        result.setEntities(users.getContent());
        result.setTotal(users.getTotalElements());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
