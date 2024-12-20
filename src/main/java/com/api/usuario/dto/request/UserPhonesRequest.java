package com.api.usuario.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserPhonesRequest {

    private String id;
    private String number;
    @JsonProperty("citycode")
    private String cityCode;
    @JsonProperty("countrycode")
    private String countryCode;

}
