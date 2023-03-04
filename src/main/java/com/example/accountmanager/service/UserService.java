package com.example.accountmanager.service;

import com.example.accountmanager.entity.User;
import com.example.accountmanager.exception.UserAlreadyExistException;
import com.example.accountmanager.exception.UserNotFoundException;
import com.example.accountmanager.exception.WrongEditRequestException;
import com.example.accountmanager.model.MessageResponse;

import java.util.List;

public interface UserService {
    User save(User user) throws UserAlreadyExistException;
    List<User> findAll();
    MessageResponse updateUser(User user) throws UserNotFoundException, WrongEditRequestException;
    MessageResponse deleteUser(String login) throws UserNotFoundException;

    MessageResponse changePass(User user) throws UserNotFoundException;
}
