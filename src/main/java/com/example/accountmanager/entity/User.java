package com.example.accountmanager.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Getter
@Table(schema = "dbo", name = "Users")
public class User {
    @Id
    @Column(name = "LOGIN", nullable = false, length = 200)
    private String login;

    @Column(name = "PASSWORD", nullable = false, length = 80)
    private String password;

    @Column(name = "FULL_NAME", nullable = false, length = 80)
    private String fullName;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 11)
    private String phoneNumber;

    @Column(name = "ADRESS", nullable = false, length = 300)
    private String adress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        if (!fullName.equals(user.fullName)) return false;
        if (!phoneNumber.equals(user.phoneNumber)) return false;
        return adress.equals(user.adress);
    }
}
