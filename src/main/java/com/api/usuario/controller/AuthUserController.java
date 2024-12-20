package com.api.usuario.controller;

import com.api.usuario.dto.AuthUser;
import com.api.usuario.dto.Token;
import com.api.usuario.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthUser authUser){
        Token token = authService.login(authUser);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
