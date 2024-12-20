package com.api.usuario.controller;

import com.api.usuario.dto.AuthUser;
import com.api.usuario.dto.Token;
import com.api.usuario.dto.request.UserRequest;
import com.api.usuario.dto.response.UserResponse;
import com.api.usuario.service.IAuthService;
import com.api.usuario.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping
    public ResponseEntity<?> userRegister(@RequestBody @Valid UserRequest request){
        UserResponse userResponse = userService.saveUser(request);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAllUsers(){
        List<UserResponse> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUser(@PathVariable String id){
        UserResponse user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserRequest request){
        UserResponse userResponse = userService.updateUser(request);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser( String id){
        userService.deleteUser(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
