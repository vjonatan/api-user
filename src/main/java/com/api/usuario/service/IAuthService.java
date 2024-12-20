package com.api.usuario.service;

import com.api.usuario.dto.AuthUser;
import com.api.usuario.dto.Token;

public interface IAuthService {
    Token login(AuthUser authUser);
}
