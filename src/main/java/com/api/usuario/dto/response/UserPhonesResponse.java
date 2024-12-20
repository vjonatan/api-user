package com.api.usuario.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserPhonesResponse {

    private String id;
    private String number;
    private String cityCode;
    private String countryCode;

}
