package com.app.service.service;

import com.app.audit.entities.User;
import com.app.audit.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public User getUser(String login) {

        User user = usersRepository.findByUsername(login);
        return user;
    }
}
