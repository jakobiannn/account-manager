package com.example.accountmanager.service;

import com.example.accountmanager.entity.User;
import com.example.accountmanager.exception.UserAlreadyExistException;
import com.example.accountmanager.exception.UserNotFoundException;
import com.example.accountmanager.exception.WrongEditRequestException;
import com.example.accountmanager.model.MessageRespone;
import com.example.accountmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) throws UserAlreadyExistException {
        if (userRepository.existsUserByLogin(user.getLogin())) {
            throw new UserAlreadyExistException(user.getLogin());
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public MessageRespone updateUser(User user) throws UserNotFoundException, WrongEditRequestException {
        Optional<User> oldUserOptional = Optional.ofNullable(
                userRepository.findByLogin(user.getLogin()).orElseThrow(() -> new UserNotFoundException(user.getLogin()))
        );
        User oldUserEntity = oldUserOptional.get();
        if (user.getPassword() != null) {
            throw new WrongEditRequestException();
        }
        User editedUser = User
                .builder()
                .adress(user.getAdress() == null ? oldUserEntity.getAdress(): user.getAdress())
                .login(user.getLogin())
                .fullName(user.getFullName() == null ? oldUserEntity.getFullName(): user.getFullName())
                .phoneNumber(user.getPhoneNumber() == null ? oldUserEntity.getPhoneNumber(): user.getPhoneNumber())
                .password(oldUserEntity.getPassword())
                .build();
        if (editedUser.equals(oldUserEntity)) {
            return new MessageRespone("User was not changed. Field for edit are equals.");
        } else {
            userRepository.save(editedUser);
            return new MessageRespone("User edited successfully.");
        }
    }

    @Override
    @Transactional
    public MessageRespone deleteUser(String login) throws UserNotFoundException {
        if (!userRepository.existsUserByLogin(login)) {
            throw new UserNotFoundException(login);
        }
        userRepository.deleteUserByLogin(login);
        return new MessageRespone("User deleted successfully.");
    }

    @Override
    public MessageRespone changePass(User user) throws UserNotFoundException {
        Optional<User> userOptional = Optional.ofNullable(
                userRepository.findByLogin(user.getLogin()).orElseThrow(() -> new UserNotFoundException(user.getLogin()))
        );
        User oldUser = userOptional.get();
        oldUser.setPassword(user.getPassword());
        userRepository.save(oldUser);
        return new MessageRespone("Password changed!");
    }
}
