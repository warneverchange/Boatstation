package com.portfolio.boatstation.services.security;

import com.portfolio.boatstation.entities.ClientData;
import com.portfolio.boatstation.entities.views.security.Role;
import com.portfolio.boatstation.entities.views.security.User;
import com.portfolio.boatstation.pojo.AuthenticationRequest;
import com.portfolio.boatstation.pojo.AuthenticationResponse;
import com.portfolio.boatstation.pojo.RegisterRequest;
import com.portfolio.boatstation.repositories.ClientDataRepository;
import com.portfolio.boatstation.repositories.security.RoleRepository;
import com.portfolio.boatstation.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final ClientDataRepository clientDataRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional(rollbackFor = RuntimeException.class)
    public AuthenticationResponse register(RegisterRequest request) {
        ClientData clientData = ClientData
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .patronymic(request.getPatronymic())
                .phoneNumber(request.getPhoneNumber())
                .passportData(request.getPassportData())
                .build();
        clientDataRepository.saveNewClientData(clientData);
        Optional<Role> role = roleRepository.getRoleByRoleName("USER");
        Optional<Long> clientDataId = clientDataRepository.getIdByClientData(clientData);

        AuthenticationResponse response = new AuthenticationResponse(null);

        if (role.isEmpty() || clientDataId.isEmpty()) {
            throw new RuntimeException();
        }
        User user = User
                .builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .userRoleId(role.get().getId())
                .clientDataId(clientDataId.get())
                .build();
        userRepository.saveNewUser(user);
        Optional<Long> userId = userRepository.getUserIdByUser(user);

        if (userId.isEmpty()) {
            throw new RuntimeException();
        }

        user.setId(userId.get());
        var jwtToken = jwtService.generateToken(user);
        response.setToken(jwtToken);
        return response;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.getUserByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
