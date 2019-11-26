package com.app.web.controller;


import com.app.service.dto.LoginDto;
import com.app.service.dto.SignUpDto;
import com.app.service.responce.ApiResponse;
import com.app.service.responce.JwtAuthenticationResponse;
import com.app.service.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/library/auth")
public class AuthenticateController {

    private AuthenticateService authenticateService;

    @Autowired
    public AuthenticateController(AuthenticateService authenticateService){
        this.authenticateService = authenticateService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginDto loginDto) throws UnsupportedEncodingException {
        return authenticateService.authenticateUser(loginDto);
    }

    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpDto signUpDTO) {
        return  authenticateService.registerUser(signUpDTO);
    }



}
