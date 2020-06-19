package com.main.app.service;

import com.main.app.config.SecurityUtils;
import com.main.app.domain.UserDTO;
import com.main.app.domain.model.User;
import com.main.app.enums.Role;
import com.main.app.repository.UserRepository;
import com.main.app.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementation of the service used for management of the User data.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(
            UserRepository repository
    ) {
        this.userRepository = repository;
    }

    public Page<User> getUsers(Pageable page) {
        return userRepository.findAllByDeleted(false, page);
    }



    public Optional<User> getCurrentUser() {
        Optional<String> username = SecurityUtils.getCurrentUserLogin();

        String usernameString = username.get();

        return this.userRepository.findOneByEmail(usernameString);
    }

    public User register(UserDTO userDTO) {

        User user = new User(userDTO);

        user.setPassword(UserUtil.encriptUserPassword(user.getPassword()));
        user.setRole(Role.OPERATOR);

        User dbUser = userRepository.save(user);

        return dbUser;
    }

    public UserDTO getCurrentUserDTO() {

        return new UserDTO(getCurrentUser().get());
    }
}
