package com.example.accountmanager.controller;

import com.example.accountmanager.entity.User;
import com.example.accountmanager.exception.UserAlreadyExistException;
import com.example.accountmanager.exception.UserNotFoundException;
import com.example.accountmanager.exception.WrongEditRequestException;
import com.example.accountmanager.model.MessageResponse;
import com.example.accountmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User newUser(@RequestBody User user) throws UserAlreadyExistException {
        return userService.save(user);
    }

    @PatchMapping("/edit")
    public MessageResponse editUser(@RequestBody User user) throws UserNotFoundException, WrongEditRequestException {
        return userService.updateUser(user);
    }

    @GetMapping("/show")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @DeleteMapping("/{login}")
    public MessageResponse deleteUser(@PathVariable("login") String login) throws UserNotFoundException {
        return userService.deleteUser(login);
    }

    @PatchMapping("/change_pass")
    public MessageResponse changePass(@RequestBody User user) throws UserNotFoundException {
        return userService.changePass(user);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public MessageResponse handleUserNotExist(UserAlreadyExistException e) {
        return new MessageResponse(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public MessageResponse handleUserNotFound(UserNotFoundException e) {
        return new MessageResponse(e.getMessage());
    }

    @ExceptionHandler(WrongEditRequestException.class)
    public MessageResponse handleWrongEditRequest(WrongEditRequestException e) {
        return new MessageResponse(e.getMessage());
    }
}
