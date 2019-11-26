package com.app.service.service;

import com.app.service.dto.LoginDto;
import com.app.service.dto.SignUpDto;
import com.app.service.dto.UserDto;
import org.springframework.http.ResponseEntity;
import com.app.service.responce.ApiResponse;
import com.app.service.responce.JwtAuthenticationResponse;

import java.io.UnsupportedEncodingException;

public interface AuthenticateService {

    void addUser(UserDto userDto);
    ResponseEntity<JwtAuthenticationResponse> authenticateUser(LoginDto loginDto) throws UnsupportedEncodingException;
    ResponseEntity<ApiResponse> registerUser(SignUpDto signUpDto);

}
