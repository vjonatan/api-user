package com.api.usuario.model;

import jakarta.persistence.*;
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
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Phone> phones;

    private LocalDateTime created;
    private LocalDateTime modified;
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    private boolean isActive;

}
