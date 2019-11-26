package com.app.security.jwt.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JWTToken extends UsernamePasswordAuthenticationToken {

    public JWTToken(String token){
        super(token, null);
    }

    public String getToken(){
        return (String) getPrincipal();
    }
}
