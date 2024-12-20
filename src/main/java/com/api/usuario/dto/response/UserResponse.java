package com.api.usuario.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {

    private String id;
    private String name;
    private String email;
    private String password;
    private List<UserPhonesResponse> phones;
    private LocalDateTime created;
    private LocalDateTime modified;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime lastLogin;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean isActive;

}
