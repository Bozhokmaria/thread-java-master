package com.threadjava.users.model;

import com.threadjava.db.BaseEntity;
import com.threadjava.image.model.Image;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "users")
public class User extends BaseEntity {

    @Email
    @Column(name = "email", unique=true)
    private String email;

    @NotBlank
    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "password")
    private String password;

    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "avatar_id")
    private Image avatar;
}
