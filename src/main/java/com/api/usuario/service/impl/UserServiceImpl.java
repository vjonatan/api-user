package com.api.usuario.service.impl;

import com.api.usuario.dto.AuthUser;
import com.api.usuario.dto.Token;
import com.api.usuario.dto.request.UserPhonesRequest;
import com.api.usuario.dto.request.UserRequest;
import com.api.usuario.dto.response.UserPhonesResponse;
import com.api.usuario.dto.response.UserResponse;
import com.api.usuario.exception.EmailExistsException;
import com.api.usuario.exception.EmailFormatException;
import com.api.usuario.exception.UserNotFoundException;
import com.api.usuario.model.Phone;
import com.api.usuario.model.User;
import com.api.usuario.repository.UserRepository;
import com.api.usuario.security.JwtUtil;
import com.api.usuario.service.IUserService;
import com.api.usuario.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public UserResponse saveUser(UserRequest user) {

        if(!StringUtils.validateEmailFormat(user.getEmail())){
            throw new EmailFormatException("Formato de email incorrecto");
        }

        if ( validateEmail(user.getEmail()) ) {
            throw new EmailExistsException("Correo ya registrado");
        }

        User toUser = this.mapToUser(user);

        toUser.setCreated(LocalDateTime.now());
        toUser.setModified(LocalDateTime.now());
        toUser.setActive(true);

        User save = userRepository.save(toUser);
        log.info("Usuario guardado");
        UserResponse userResponse = this.mapToUserResponseDto(save);
        userResponse.setToken(jwtUtil.generateToken(user.getEmail()));

        return userResponse;
    }

    @Override
    public List<UserResponse> getUsers() {

        List<UserResponse> userResponseList = userRepository.findAll().stream()
                .map(this::mapToUserResponseDto)
                .collect(Collectors.toList());

        return userResponseList;
    }

    @Override
    public UserResponse getUser(String id) {

        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("Usuario no encontrado");
        }

        return this.mapToUserResponseDto(user.get());
    }

    @Override
    public UserResponse updateUser(UserRequest user) {

        Optional<User> userSaved = userRepository.findById(user.getId());

        if (!userSaved.isPresent()) {
            throw new UserNotFoundException("Usuario no encontrado");
        }

        if(!StringUtils.validateEmailFormat(user.getEmail())){
            throw new EmailFormatException("Formato de email incorrecto");
        }
        User userObject = userSaved.get();
        User userToUpdate = this.mapToUser(user);
        userToUpdate.setModified(LocalDateTime.now());
        userToUpdate.setCreated(userObject.getCreated());
        userToUpdate.setActive(userToUpdate.isActive());

        User save = userRepository.save(userToUpdate);
        log.info("Usuario actualizado");
        return this.mapToUserResponseDto(save);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
        log.info("Usuario eliminado");
    }

    private boolean validateEmail(String email){

        Optional<User> userExists = userRepository.findByEmail(email);

        return userExists.isPresent();

    }

    private UserResponse mapToUserResponseDto(User user){

        List<UserPhonesResponse> userPhonesResponses = user.getPhones().stream()
                .map(this::mapToUserPhonesResponseDto)
                .collect(Collectors.toList());

        return UserResponse.builder().id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phones(userPhonesResponses)
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .isActive(user.isActive())
                .build();
    }

    private UserPhonesResponse mapToUserPhonesResponseDto(Phone phone){
        return UserPhonesResponse.builder().id(phone.getId())
                .countryCode(phone.getCountryCode())
                .cityCode(phone.getCityCode())
                .number(phone.getNumber())
                .build();
    }

    private User mapToUser(UserRequest user){

        List<Phone> userPhonesRequest = user.getPhones().stream()
                .map(this::mapToUserPhones)
                .collect(Collectors.toList());

        return User.builder().id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phones(userPhonesRequest)
                .build();
    }

    private Phone mapToUserPhones(UserPhonesRequest phone){
        return Phone.builder().id(phone.getId())
                .countryCode(phone.getCountryCode())
                .cityCode(phone.getCityCode())
                .number(phone.getNumber())
                .build();
    }
}
