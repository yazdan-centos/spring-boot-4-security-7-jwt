package com.mapnaom.foodapp.service.impl;

import com.mapnaom.foodapp.enums.TokenType;
import com.mapnaom.foodapp.exceptions.DisabledUserException;
import com.mapnaom.foodapp.exceptions.InvalidUsernameOrPasswordException;
import com.mapnaom.foodapp.payload.request.AuthenticationRequest;
import com.mapnaom.foodapp.payload.request.RegisterRequest;
import com.mapnaom.foodapp.payload.response.AuthenticationResponse;
import com.mapnaom.foodapp.service.AuthenticationService;
import com.mapnaom.foodapp.service.JwtService;
import com.mapnaom.foodapp.entities.User;
import com.mapnaom.foodapp.repository.UserRepository;
import com.mapnaom.foodapp.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service @Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        user = userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(user.getId());

        var roles = user.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .toList();

        return AuthenticationResponse.builder()
                .accessToken(jwt)
                .email(user.getEmail())
                .username(user.getUsername())
                .id(user.getId())
                .refreshToken(refreshToken.getToken())
                .roles(roles)
                .tokenType( TokenType.BEARER.name())
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidUsernameOrPasswordException("Invalid username or password."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidUsernameOrPasswordException("Invalid username or password.");
        }

        if (!user.isEnabled()) {
            throw new DisabledUserException("User account is disabled.");
        }

        var roles = user.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .toList();

        var jwt = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(user.getId());
        var expiresIn = jwtService.getExpirationTime(user); // Assuming JwtService has a method to calculate expiration time

        return AuthenticationResponse.builder()
                .accessToken(jwt)
                .roles(roles)
                .email(user.getEmail())
                .username(user.getUsername())
                .id(user.getId())
                .refreshToken(refreshToken.getToken())
                .tokenType(TokenType.BEARER.name())
                .expiresIn((Long) expiresIn)
                .build();
    }
}
