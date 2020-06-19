package com.main.app.service;

import com.main.app.domain.UserDTO;
import com.main.app.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The service used for management of the User data.
 */
@Service
public interface UserService {

    Optional<User> getCurrentUser();
    Page<User> getUsers(Pageable page);
    UserDTO getCurrentUserDTO();
    User register(UserDTO userDTO);
}
