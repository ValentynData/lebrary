package com.app.service.service;

import com.app.audit.entities.User;

public interface UserService {
    User getUser(String login);
}
