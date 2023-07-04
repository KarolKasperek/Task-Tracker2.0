package com.taskTracker.entity;

import com.taskTracker.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @Column(name = "account_id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_name")
    private String name;

    @Column(name = "account_email")
    private String email;

    @Column(name = "account_password")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    private List<Task> taskList;


    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
