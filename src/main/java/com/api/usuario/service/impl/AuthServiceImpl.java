package com.api.usuario.service.impl;

import com.api.usuario.dto.AuthUser;
import com.api.usuario.dto.Token;
import com.api.usuario.exception.UserNotFoundException;
import com.api.usuario.model.User;
import com.api.usuario.repository.UserRepository;
import com.api.usuario.security.JwtUtil;
import com.api.usuario.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Token login(AuthUser authUser) {

        Optional<User> user = userRepository.findByName(authUser.getUserName());
        if(!user.isPresent()){
            throw new UserNotFoundException("Usuario no encontrado");
        }

        if(!passwordEncoder.matches(authUser.getPassword(), user.get().getPassword())){
            User userToUpdate = user.get();
            userToUpdate.setLastLogin(LocalDateTime.now());
            userRepository.save(userToUpdate);
            return new Token(jwtUtil.generateToken(user.get().getEmail()));
        }

        return null;
    }

}
