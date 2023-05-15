package com.portfolio.boatstation.entities.security;

import com.portfolio.boatstation.services.security.UserService;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_user", schema = "boatstation")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 256, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 256)
    private String password;
    @Column(name = "email", nullable = false, length = 256, unique = true)
    private String email;
    @Column(name ="client_data_id", nullable = false, unique = true)
    private Long clientDataId;


    @Column(name = "_user_role_id", nullable = false)
    private Long userRoleId;

    @Transient
    private UserService userService;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userService.getUserAuthorities(this);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
