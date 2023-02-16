package com.example.accountmanager.controller;

import com.example.accountmanager.entity.User;
import com.example.accountmanager.exception.UserAlreadyExistException;
import com.example.accountmanager.exception.UserNotFoundException;
import com.example.accountmanager.exception.WrongEditRequestException;
import com.example.accountmanager.model.MessageRespone;
import com.example.accountmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserHandler {
    private UserService userService;

    @Autowired
    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    User newUser(@RequestBody User user) throws UserAlreadyExistException {
        return userService.save(user);
    }

    @PatchMapping("/edit")
    MessageRespone editUser(@RequestBody User user) throws UserNotFoundException, WrongEditRequestException {
        return userService.updateUser(user);
    }

    @GetMapping("/show")
    List<User> getUsers() {
        return userService.findAll();
    }

    @DeleteMapping("/{login}")
    public MessageRespone deleteUser(@PathVariable("login") String login) throws UserNotFoundException {
        return userService.deleteUser(login);
    }

    @PatchMapping("/change_pass")
    public MessageRespone changePass(@RequestBody User user) throws UserNotFoundException {
        return userService.changePass(user);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public MessageRespone handleUserNotExist(UserAlreadyExistException e) {
        return new MessageRespone(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public MessageRespone handleUserNotFound(UserNotFoundException e) {
        return new MessageRespone(e.getMessage());
    }

    @ExceptionHandler(WrongEditRequestException.class)
    public MessageRespone handleWrongEditRequest(WrongEditRequestException e) {
        return new MessageRespone(e.getMessage());
    }
}
