package com.api.usuario.service;

import com.api.usuario.dto.AuthUser;
import com.api.usuario.dto.Token;
import com.api.usuario.dto.request.UserRequest;
import com.api.usuario.dto.response.UserResponse;
import com.api.usuario.model.User;

import java.util.List;

public interface IUserService {

    /**
     * Retorna lista de usuarios de la base de datos
     * @return List<UserResponse>
     */
    List<UserResponse> getUsers();

    /**
     * Permite obtener un usuario mediante un id
     * @param id identificador del objeto usuario
     * @return UserResponse con datos del usuario seleccionado
     */
    UserResponse getUser(String id);

    /**
     * Permite guardar un usuario
     * @param user objeto con dato del usuario
     * @return UserResponse con datos del usuario guardado
     */
    UserResponse saveUser(UserRequest user);

    /**
     * Permite actualizar datos de un usuario
     * @param user objeto usuario a actualizar
     * @return UserResponse con datos del usuario actualizado
     */
    UserResponse updateUser(UserRequest user);

    /**
     * Permite eliminar un usuario
     * @param id identificador del objeto usuario a eliminar
     */
    void deleteUser(String id);




}
