package com.springboot.rest.service;

import com.springboot.rest.entity.myspringappdb.Role;
import com.springboot.rest.entity.myspringappdb.User;
import com.springboot.rest.model.RoleModel;
import com.springboot.rest.model.UserModel;
import com.springboot.rest.repository.myspringappdb.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserModel> usersRes = new ArrayList<>();
        users.stream().forEach(user -> {
            UserModel um = new UserModel();
            um.setUsername(user.getUsername());
            um.setMobileNumber(user.getMobileNumber());
            um.setPassword("********");
            um.setEmail(user.getEmail());
            Set<RoleModel> userRoles = new HashSet<>();
            user.getRoles().forEach(role -> {
                RoleModel rm = new RoleModel();
                rm.setRole(role.getRole());
                userRoles.add(rm);
            });
            um.setRoles(userRoles);
            usersRes.add(um);
        });
        return usersRes;
    }

    public UserModel getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        UserModel userres = new UserModel();
        if (null != user) {
            userres.setUsername(user.getUsername());
            userres.setMobileNumber(user.getMobileNumber());
            userres.setPassword("********");
            userres.setEmail(user.getEmail());
            Set<RoleModel> userRoles = new HashSet<>();
            if (null != user.getRoles()) {
                user.getRoles().forEach(role -> {
                    RoleModel rm = new RoleModel();
                    rm.setRole(role.getRole());
                    userRoles.add(rm);
                });
                userres.setRoles(userRoles);
            }
        }
        return userres;
    }

    public UserModel createUser(UserModel userRequest) {

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setMobileNumber(userRequest.getMobileNumber());

        if (StringUtils.isNotBlank(userRequest.getPassword())) {
            user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
        }

        Set<Role> roles = new HashSet<>();
        if (null != userRequest.getRoles()) {
            userRequest.getRoles().forEach(role -> {
                Role rle = new Role();
                rle.setRole(role.getRole());
                rle.setUser(user);
                roles.add(rle);
            });
            user.setRoles(roles);
        }

        userRepository.save(user);

        return this.getUserByUsername(user.getUsername());
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }


}
