package com.tms.myproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User extends BaseModel{

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Role> roles;
}
