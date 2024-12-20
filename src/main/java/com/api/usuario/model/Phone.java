package com.api.usuario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String number;

    @Column(name = "citycode")
    private String cityCode;

    @Column(name = "countrycode")
    private String countryCode;

}
