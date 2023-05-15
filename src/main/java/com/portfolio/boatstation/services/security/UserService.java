package com.portfolio.boatstation.services.security;

import com.portfolio.boatstation.entities.security.User;
import com.portfolio.boatstation.exceptions.UserNotFoundException;
import com.portfolio.boatstation.repositories.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Collection<? extends GrantedAuthority> getUserAuthorities(User user){
        Optional<String> userAuthority = userRepository.getUserAuthority(user);

        if (userAuthority.isEmpty()) {
            throw new UserNotFoundException("Can't get grant authorities of user");
        }
        return List.of(new SimpleGrantedAuthority(userAuthority.get()));
    }


    public void deleteCurrentUserAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long currentUserId = ((User) auth.getPrincipal()).getId();
        userRepository.deleteUserAccountById(currentUserId);
    }
}
